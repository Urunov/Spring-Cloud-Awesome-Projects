package com.qqb.travelclub.store;


import com.qqb.travelclub.aggregate.club.TravelClub;

import java.util.List;

public interface ClubStore {
	//
	String create(TravelClub club);
	TravelClub retrieve(String clubId);
	List<TravelClub> retrieveByName(String name);
	List<TravelClub> retrieveClubs();
	void update(TravelClub club);
	void delete(String clubId);

	boolean exists(String clubId);
}
