package com.mscloud.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {cloud-gateway}
 * @Date: {2022/05/23 && 10:57 AM}
 */
@RestController
public class FallBackMethodController {
    //
    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod(){
        return "User service is taking longer than Expected." + "Please try again.";
    }

    @GetMapping("/deparmentServiceFallBack")
    public String departmentServiceFallBackMethod(){
        return "Department Service is taking longer than Expected. " + "Please try again.";
    }

}
