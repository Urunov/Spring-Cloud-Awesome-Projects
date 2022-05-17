package com.microservice.userservice.repository;

import com.microservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {user-service}
 * @Date: {2022/05/14 && 3:00 PM}
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //
    User findByUserId(Long userId);
}
