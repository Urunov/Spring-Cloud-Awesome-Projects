package com.qqb.travelclub.service;


import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.sdo.TravelClubCdo;
import com.qqb.travelclub.shared.NameValueList;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	List<TravelClub> findClubs();
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
