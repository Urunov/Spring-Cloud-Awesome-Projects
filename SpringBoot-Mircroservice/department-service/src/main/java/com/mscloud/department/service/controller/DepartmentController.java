package com.mscloud.department.service.controller;

import com.mscloud.department.service.entity.Department;
import com.mscloud.department.service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {department-service}
 * @Date: {2022/05/14 && 2:42 PM}
 */
@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    //
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside saveDeparment method of Department Controller.");
        return departmentService.saveDepartment(department);
    }


    @GetMapping("/{id}")
    public Department findDepartmentID(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById method of DepartmentController.");
        return departmentService.findDepartmentById(departmentId);
    }
}
