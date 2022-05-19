/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.service.logic;

import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.service.ClubService;
import com.qqb.travelclub.service.sdo.TravelClubCdo;
import com.qqb.travelclub.shared.NameValueList;
import com.qqb.travelclub.store.ClubStore;
import com.qqb.travelclub.util.exception.NoSuchClubException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceLogic implements ClubService {
    //
    @Autowired
    private ClubStore clubStore;

    @Override
    public String registerClub(TravelClubCdo clubCdo) {
        //
        TravelClub club = new TravelClub(clubCdo.getName(),clubCdo.getIntro());
        club.checkValidation();
        String clubId = clubStore.create(club);
        return clubId;
    }

    @Override
    public TravelClub findClubById(String id) {
        return clubStore.retrieve(id);
    }

    @Override
    public List<TravelClub> findClubsByName(String name) {
        return clubStore.retrieveByName(name);
    }

    @Override
    public void modify(String clubId, NameValueList nameValues) {
        TravelClub travelClub = clubStore.retrieve(clubId);
        if (travelClub != null){
            travelClub.modifyValues(nameValues);
            clubStore.update(travelClub);
            return;
        }

        throw new NoSuchClubException("Club not found.");
    }

    @Override
    public void remove(String clubId) {
        clubStore.delete(clubId);
    }

    @Override
    public List<TravelClub> findClubs() {
        return clubStore.retrieveClubs();
    }
}
