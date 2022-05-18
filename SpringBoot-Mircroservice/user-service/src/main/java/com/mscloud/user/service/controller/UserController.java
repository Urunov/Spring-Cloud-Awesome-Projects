package com.mscloud.user.service.controller;

import com.mscloud.user.service.VO.ResponseTemplateVO;
import com.mscloud.user.service.entity.User;
import com.mscloud.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {user-service}
 * @Date: {2022/05/14 && 3:01 PM}
 */

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser of UserController.");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside getUserWithDepartment of UserController. ");
        return userService.getUserWithDepartment(userId);
    }
}
