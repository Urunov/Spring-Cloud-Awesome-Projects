/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo.repo;

import com.qqb.travelclub.store.jpo.MemberPostsJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPostsRepository extends JpaRepository<MemberPostsJpo,Long> {
}
