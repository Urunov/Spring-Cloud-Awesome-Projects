package com.qqb.travelclub.service;


import com.qqb.travelclub.aggregate.club.CommunityMember;
import com.qqb.travelclub.service.sdo.MemberCdo;
import com.qqb.travelclub.shared.NameValueList;

import java.util.List;

public interface MemberService {
	//
	String registerMember(MemberCdo member);
	CommunityMember findMemberById(String memberId);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	List<CommunityMember> findMembers();
	void modifyMember(String memberId, NameValueList member);
	void removeMember(String memberId);
}