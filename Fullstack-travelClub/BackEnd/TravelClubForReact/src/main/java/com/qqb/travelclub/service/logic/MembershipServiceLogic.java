/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.service.logic;

import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.aggregate.club.Membership;
import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.MembershipService;
import com.qqb.travelclub.service.sdo.MembershipCdo;
import com.qqb.travelclub.shared.NameValueList;
import com.qqb.travelclub.store.ClubStore;
import com.qqb.travelclub.store.MemberStore;
import com.qqb.travelclub.store.MembershipStore;
import com.qqb.travelclub.util.exception.MembershipDuplicationException;
import com.qqb.travelclub.util.exception.NoSuchClubException;
import com.qqb.travelclub.util.exception.NoSuchMemberException;
import com.qqb.travelclub.util.exception.NoSuchMembershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {
    //
    @Autowired
    private MembershipStore membershipStore;
    @Autowired
    private MemberStore memberStore;
    @Autowired
    private ClubStore clubStore;

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        TravelClub club = clubStore.retrieve(membershipCdo.getClubId());

        if (club == null){
            throw new NoSuchClubException("Club not found");
        }

        CommunityMember communityMember = memberStore.retrieve(membershipCdo.getMemberId());

        if (communityMember == null){
            throw new NoSuchMemberException("Member not found");
        }

        Membership membership = findMembershipByClubIdAndMemberId(membershipCdo.getClubId(), membershipCdo.getMemberId());

        if (membership != null){
            throw new MembershipDuplicationException("Membership already exists with clubId --> " + membershipCdo.getClubId());
        }

        Membership newMembership = new Membership(club,communityMember);
        newMembership.setRole(membershipCdo.getRole());
        return membershipStore.create(newMembership);
    }

    @Override
    public Membership findMembership(String membershipId) {
        return membershipStore.retrieve(membershipId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberId) {

        TravelClub club = clubStore.retrieve(clubId);

        if (club == null){
            throw new NoSuchClubException("Club not found");
        }

        CommunityMember communityMember = memberStore.retrieve(memberId);

        if (communityMember == null){
            throw new NoSuchMemberException("Member not found");
        }
        return membershipStore.retrieveByClubIdAndMemberId(clubId, memberId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail) {

        CommunityMember foundMember =  memberStore.retrieveByEmail(memberEmail);
        if (foundMember == null){
            throw new NoSuchMemberException("Member not found with email --> " + memberEmail);
        }
        String memberId = foundMember.getId();

        TravelClub club = clubStore.retrieve(clubId);

        if (club == null){
            throw new NoSuchClubException("Club not found");
        }

        CommunityMember communityMember = memberStore.retrieve(memberId);

        if (communityMember == null){
            throw new NoSuchMemberException("Member not found");
        }

        return membershipStore.retrieveByClubIdAndMemberId(clubId,memberId);
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        TravelClub club = clubStore.retrieve(clubId);

        if (club == null){
            throw new NoSuchClubException("Club not found");
        }

        return membershipStore.retrieveByClubId(clubId);
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberId) {

        CommunityMember communityMember = memberStore.retrieve(memberId);

        if (communityMember == null){
            throw new NoSuchMemberException("Member not found");
        }

        return membershipStore.retrieveByMemberId(memberId);
    }

    @Override
    public void modifyMembership(String membershipId, NameValueList nameValueList) {
        //
        Membership membership = membershipStore.retrieve(membershipId);
        if (membership != null){
            membership.modifyValues(nameValueList);
            membershipStore.update(membership);
            return;
        }

        throw new NoSuchMembershipException("Membership not found.");
    }

    @Override
    public void removeMembership(String membershipId) {
        //
        if (!membershipStore.exists(membershipId)){
            throw new NoSuchMemberException("No such membership with id --> " + membershipId);
        }
        membershipStore.delete(membershipId);
    }

    @Override
    public List<Membership> findAllMemberships() {
        //
        return membershipStore.retrieveMemberships();
    }
}
