/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo.repo;

import com.qqb.travelclub.store.jpo.TravelClubJpo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TravelClubRepository extends CrudRepository<TravelClubJpo,Long> {

    List<TravelClubJpo> findAllByName(String name);
    List<TravelClubJpo> findAll();
}
