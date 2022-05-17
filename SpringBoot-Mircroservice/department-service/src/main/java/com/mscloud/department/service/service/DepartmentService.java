package com.mscloud.department.service.service;

import com.mscloud.department.service.entity.Department;
import com.mscloud.department.service.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {department-service}
 * @Date: {2022/05/14 && 2:41 PM}
 */
@Service
@Slf4j
public class DepartmentService {
    //
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department){
        log.info("Inside saveDepartment method of DepartmentController.");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId){
        log.info("Inside saveDepartment of Department Service.");
        return departmentRepository.findByDepartmentId(departmentId);
    }
}
