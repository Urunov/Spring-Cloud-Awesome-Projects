package com.qqb.travelclub.store;


import com.qqb.travelclub.aggregate.club.CommunityMember;

import java.util.List;

public interface MemberStore {
	//
	String create(CommunityMember member);
	CommunityMember retrieve(String memberId);
	CommunityMember retrieveByEmail(String email);
	List<CommunityMember> retrieveByName(String name);
	List<CommunityMember> retrieveMembers();
	void update(CommunityMember member);
	void delete(String email);
	
	boolean exists(String memberId);
}
