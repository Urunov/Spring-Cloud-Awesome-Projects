package com.mscloud.user.service.service;

import com.mscloud.user.service.VO.Department;
import com.mscloud.user.service.VO.ResponseTemplateVO;
import com.mscloud.user.service.entity.User;
import com.mscloud.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {user-service}
 * @Date: {2022/05/14 && 3:00 PM}
 */
@Service
@Slf4j
public class UserService {
    //
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user){
        log.info("Inside saveUser of UserService.");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

       // Department department = restTemplate.getForObject("http://localhost:9001/departments/" +
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" +
                user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }
}
