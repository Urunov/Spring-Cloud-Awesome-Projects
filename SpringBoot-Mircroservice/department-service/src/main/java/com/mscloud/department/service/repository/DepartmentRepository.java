package com.mscloud.department.service.repository;


import com.mscloud.department.service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {department-service}
 * @Date: {2022/05/14 && 2:40 PM}
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long departmentId);
}
