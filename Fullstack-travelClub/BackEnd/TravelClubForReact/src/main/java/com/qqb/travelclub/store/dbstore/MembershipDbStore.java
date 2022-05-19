/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.dbstore;

import com.qqb.travelclub.aggregate.club.Membership;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.store.MembershipStore;
import com.qqb.travelclub.store.jpo.CommunityMemberJpo;
import com.qqb.travelclub.store.jpo.MembershipJpo;
import com.qqb.travelclub.store.jpo.TravelClubJpo;
import com.qqb.travelclub.store.jpo.repo.CommunityMemberRepository;
import com.qqb.travelclub.store.jpo.repo.MembershipRepository;
import com.qqb.travelclub.store.jpo.repo.TravelClubRepository;
import com.qqb.travelclub.util.exception.MemberDuplicationException;
import com.qqb.travelclub.util.exception.NoSuchClubException;
import com.qqb.travelclub.util.exception.NoSuchMembershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MembershipDbStore implements MembershipStore {
    //
    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    TravelClubRepository travelClubRepository;

    @Autowired
    CommunityMemberRepository communityMemberRepository;
    @Override
    public String create(Membership membership) {
        //
        MembershipJpo membershipJpo = new MembershipJpo(membership);

        TravelClubJpo travelClubJpo = travelClubRepository.findById(Long.valueOf(membership.getClub().getId())).get();
        membershipJpo.setClub(travelClubJpo);
        CommunityMemberJpo communityMemberJpo = communityMemberRepository.findById(Long.valueOf(membership.getMember().getId())).get();
        membershipJpo.setMember(communityMemberJpo);

        membershipJpo = membershipRepository.save(membershipJpo);
        return String.valueOf(membershipJpo.getId());
    }

    @Override
    public Membership retrieve(String membershipId) {
        //
        if (!exists(membershipId)){
            throw new NoSuchMembershipException("Membership not found.");
        }
        MembershipJpo membershipJpo = membershipRepository.findById(Long.valueOf(membershipId)).get();
        return membershipJpo.toDomain();
    }

    @Override
    public Membership retrieveByClubIdAndMemberId(String clubId, String memberId) {
        //
        MembershipJpo membershipJpo = membershipRepository.findByClubIdAndMemberId(Long.valueOf(clubId),Long.valueOf(memberId));
        if (membershipJpo != null) {
            return membershipJpo.toDomain();
        }
        return null;
//        throw new NoSuchMembershipException("Member not found " + clubId);
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId) {
        //
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByClubId(Long.valueOf(clubId));
        return MembershipJpo.toDomains(membershipJpos);
    }

    @Override
    public List<Membership> retrieveByMemberId(String memberId) {
        //
        List<MembershipJpo> membershipJpos = membershipRepository.findAllByMemberId(Long.valueOf(memberId));
        return MembershipJpo.toDomains(membershipJpos);
    }

    @Override
    public void update(Membership membership) {
        //
        MembershipJpo membershipJpo = new MembershipJpo(membership);
        membershipJpo.setId(Long.valueOf(membership.getId()));
        if (membershipRepository.findById(Long.valueOf(membership.getId())).isPresent()){

            membershipRepository.save(membershipJpo);
            return;
        }
        throw new NoSuchMembershipException("No membership with this id --> " + membershipJpo.getId());
    }

    @Override
    public void delete(String membershipId) {
        //
        if (exists(membershipId)){
            membershipRepository.deleteById(Long.valueOf(membershipId));
            return;
        }
        throw new NoSuchMembershipException("No membership with this id --> " + membershipId);
    }

    @Override
    public boolean exists(String membershipId) {
        //
        return membershipRepository.findById(Long.valueOf(membershipId)).isPresent();
    }

    @Override
    public List<Membership> retrieveMemberships() {
        //
        List<MembershipJpo> jpos = membershipRepository.findAll();
        return MembershipJpo.toDomains(jpos);
    }
}
