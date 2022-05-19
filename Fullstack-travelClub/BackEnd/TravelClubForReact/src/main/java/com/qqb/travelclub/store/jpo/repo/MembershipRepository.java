/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo.repo;

import com.qqb.travelclub.store.jpo.CommunityMemberJpo;
import com.qqb.travelclub.store.jpo.MembershipJpo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MembershipRepository extends CrudRepository<MembershipJpo,Long> {
    //
    MembershipJpo findByClubIdAndMemberId(Long clubId, Long memberId);
    List<MembershipJpo> findAllByClubId(Long clubId);
    List<MembershipJpo> findAllByMemberId(Long memberId);
    List<MembershipJpo> findAll();
}
