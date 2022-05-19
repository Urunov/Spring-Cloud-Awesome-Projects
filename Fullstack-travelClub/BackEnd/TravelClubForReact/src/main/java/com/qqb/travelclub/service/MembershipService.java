package com.qqb.travelclub.service;



import com.qqb.travelclub.aggregate.club.Membership;
import com.qqb.travelclub.service.sdo.MembershipCdo;
import com.qqb.travelclub.shared.NameValueList;

import java.util.List;

public interface MembershipService {
    //
    String registerMembership(MembershipCdo membershipCdo);
    Membership findMembership(String membershipId);
    Membership findMembershipByClubIdAndMemberId(String clubId, String memberId);
    Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    List<Membership> findAllMemberships();
    void modifyMembership(String membershipId, NameValueList nameValueList);
    void removeMembership(String membershipId);
}
