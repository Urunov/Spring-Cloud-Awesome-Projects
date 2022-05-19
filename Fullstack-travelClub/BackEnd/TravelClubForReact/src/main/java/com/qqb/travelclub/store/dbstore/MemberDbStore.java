/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.dbstore;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.store.MemberStore;
import com.qqb.travelclub.store.jpo.CommunityMemberJpo;
import com.qqb.travelclub.store.jpo.MemberPostsJpo;
import com.qqb.travelclub.store.jpo.TravelClubJpo;
import com.qqb.travelclub.store.jpo.repo.CommunityMemberRepository;
import com.qqb.travelclub.store.jpo.repo.MemberPostsRepository;
import com.qqb.travelclub.store.jpo.repo.PostCommentsRepository;
import com.qqb.travelclub.util.exception.NoSuchMemberException;
import com.qqb.travelclub.util.exception.NoSuchMembershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDbStore implements MemberStore {

    @Autowired
    CommunityMemberRepository communityMemberRepository;

    @Autowired
    MemberPostsRepository memberPostsRepository;

    @Autowired
    PostCommentsRepository postCommentsRepository;

    @Override
    public String create(CommunityMember member) {
        CommunityMemberJpo communityMemberJpo = new CommunityMemberJpo(member);
        communityMemberJpo = communityMemberRepository.save(communityMemberJpo);
        return String.valueOf(communityMemberJpo.getId());
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        if (communityMemberRepository.findById(Long.valueOf(memberId)).isPresent()) {
            CommunityMemberJpo communityMemberJpo = communityMemberRepository
                    .findById(Long.valueOf(memberId)).get();
            return communityMemberJpo.toDomain();
        }
        throw new NoSuchMemberException("No member with this id --> " + memberId);
    }

    @Override
    public CommunityMember retrieveByEmail(String email) {
        CommunityMemberJpo communityMemberJpo = communityMemberRepository.findByEmail(email);
        if (communityMemberJpo != null){
            return communityMemberJpo.toDomain();
        }
        return  null;
//        throw new NoSuchMemberException("Member not found with email: " + email);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<CommunityMemberJpo> communityMemberJpos = communityMemberRepository.findAllByName(name);
        return CommunityMemberJpo.toDomains(communityMemberJpos);
    }

    @Override
    public void update(CommunityMember member) {
        CommunityMemberJpo communityMemberJpo = new CommunityMemberJpo(member);
        communityMemberJpo.setId(Long.valueOf(member.getId()));
        if (communityMemberRepository.findById(Long.valueOf(member.getId())).isPresent()){
            communityMemberRepository.save(communityMemberJpo);
            return;
        }
        throw new NoSuchMemberException("No member with this id --> " + member.getId());
    }

    @Override
    public void delete(String memberId) {
        //
        if (exists(memberId)){
            communityMemberRepository.deleteById(Long.valueOf(memberId));
            return;
        }
        throw new NoSuchMemberException("No member with this id --> " + memberId);
    }

    @Override
    public boolean exists(String memberId) {
        return communityMemberRepository.findById(Long.valueOf(memberId)).isPresent();
    }

    @Override
    public List<CommunityMember> retrieveMembers() {
        List<CommunityMemberJpo> jpos = communityMemberRepository.findAll();
        return CommunityMemberJpo.toDomains(jpos);
    }
}
