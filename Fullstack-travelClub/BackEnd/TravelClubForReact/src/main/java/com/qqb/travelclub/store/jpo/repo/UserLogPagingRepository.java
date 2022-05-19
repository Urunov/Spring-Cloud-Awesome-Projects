/*
 * COPYRIGHT (c) QQB 2022
 * This software is the proprietary of QQB.
 *
 * @author <a href="mailto:azizbek@qqb.io">Azizbek, Husanov</a>
 * @since 2022. 1. 1.
 */

package com.qqb.travelclub.store.jpo.repo;

import com.qqb.travelclub.auditable.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserLogPagingRepository extends PagingAndSortingRepository<UserLog, Long> {
    //
    Page<UserLog> findAllByUserName(String userName, Pageable pageable);
}
