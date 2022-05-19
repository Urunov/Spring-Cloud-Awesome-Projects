package com.qqb.travelclub.store;


import com.qqb.travelclub.aggregate.club.Membership;

import java.util.List;

public interface MembershipStore {
    //
    String create(Membership membership);
    Membership retrieve(String membershipId);
    Membership retrieveByClubIdAndMemberId(String clubId, String memberId);
    List<Membership> retrieveByClubId(String clubId);
    List<Membership> retrieveByMemberId(String memberId);
    List<Membership> retrieveMemberships();
    void update(Membership membership);
    void delete(String membershipId);
    boolean exists(String membershipId);
}
