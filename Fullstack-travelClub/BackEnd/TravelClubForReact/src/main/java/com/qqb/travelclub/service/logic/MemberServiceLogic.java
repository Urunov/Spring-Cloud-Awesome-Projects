/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.service.logic;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.MemberService;
import com.qqb.travelclub.service.sdo.MemberCdo;
import com.qqb.travelclub.shared.NameValueList;
import com.qqb.travelclub.store.MemberStore;
import com.qqb.travelclub.util.exception.MemberDuplicationException;
import com.qqb.travelclub.util.exception.NoSuchMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {
    //
    @Autowired
    private MemberStore memberStore;

    @Override
    public String registerMember(MemberCdo member) {
        //
        CommunityMember foundMember = memberStore.retrieveByEmail(member.getEmail());
        if (foundMember != null){
            throw new MemberDuplicationException("Member already exists with email: " + member.getEmail());
        }

        CommunityMember communityMember = new CommunityMember(member.getEmail(),member.getName(),member.getPhoneNumber());
        communityMember.setBirthDay(member.getBirthDay());
        communityMember.setNickName(member.getNickName());
        communityMember.checkValidation();
        memberStore.create(communityMember);
        return member.getEmail();
    }

    @Override
    public CommunityMember findMemberById(String memberId) {
        return memberStore.retrieve(memberId);
    }

    @Override
    public CommunityMember findMemberByEmail(String memberEmail) {
        return memberStore.retrieveByEmail(memberEmail);
    }

    @Override
    public List<CommunityMember> findMembersByName(String name) {
        return memberStore.retrieveByName(name);
    }

    @Override
    public void modifyMember(String memberId, NameValueList member) {
        CommunityMember communityMember = memberStore.retrieve(memberId);
        if (communityMember != null){
            communityMember.modifyValues(member);
            memberStore.update(communityMember);
            return;
        }

        throw new NoSuchMemberException("Member not found.");
    }

    @Override
    public void removeMember(String memberId) {
        if (!memberStore.exists(memberId)){
            throw new NoSuchMemberException("No such member with id --> " + memberId);
        }
        memberStore.delete(memberId);
    }

    @Override
    public List<CommunityMember> findMembers() {
        //
        return memberStore.retrieveMembers();
    }
}
