/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo.repo;

import com.qqb.travelclub.store.jpo.CommunityMemberJpo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommunityMemberRepository extends CrudRepository<CommunityMemberJpo, Long> {
    CommunityMemberJpo findByEmail(String email);
    List<CommunityMemberJpo> findAllByName(String name);
    List<CommunityMemberJpo> findAll();
}
