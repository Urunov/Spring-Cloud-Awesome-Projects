/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.dbstore;

import com.qqb.travelclub.aggregate.club.TravelClub;
import com.qqb.travelclub.store.ClubStore;
import com.qqb.travelclub.store.jpo.TravelClubJpo;
import com.qqb.travelclub.store.jpo.repo.TravelClubRepository;
import com.qqb.travelclub.util.exception.NoSuchClubException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubDbStore implements ClubStore {

    @Autowired
    TravelClubRepository travelClubRepository;

    @Override
    public String create(TravelClub club) {
        TravelClubJpo travelClubJpo  = new TravelClubJpo(club);
        travelClubJpo = travelClubRepository.save(travelClubJpo);
        return String.valueOf(travelClubJpo.getId());
    }

    @Override
    public TravelClub retrieve(String clubId) {
        if (travelClubRepository.findById(Long.valueOf(clubId)).isPresent()){
            TravelClubJpo travelClubJpo = travelClubRepository.findById(Long.valueOf(clubId)).get();
            TravelClub travelClub = travelClubJpo.toDomain();
            travelClub.setId(String.valueOf(travelClubJpo.getId()));
            return travelClub;
        }
        throw new NoSuchClubException("No club with this id --> " + clubId);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        List<TravelClubJpo> jpos = travelClubRepository.findAllByName(name);
        return TravelClubJpo.toDomains(jpos);
    }

    @Override
    public void update(TravelClub club) {
        TravelClubJpo travelClubJpo = new TravelClubJpo(club);
        travelClubJpo.setId(Long.valueOf(club.getId()));
        travelClubRepository.findById(travelClubJpo.getId())
                .orElseThrow(() -> new NoSuchClubException("No club with this id --> " + club.getId()));
        travelClubRepository.save(travelClubJpo);
    }

    @Override
    public void delete(String clubId) {
        if (exists(clubId)){
            travelClubRepository.deleteById(Long.valueOf(clubId));
            return;
        }
        throw new NoSuchClubException("No club with this id --> " + clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return travelClubRepository.findById(Long.valueOf(clubId)).isPresent();
    }

    @Override
    public List<TravelClub> retrieveClubs() {
        List<TravelClubJpo> jpos = travelClubRepository.findAll();
        return TravelClubJpo.toDomains(jpos);
    }
}
