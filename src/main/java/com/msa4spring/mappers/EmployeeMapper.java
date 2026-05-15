package com.msa4spring.mappers;

import com.msa4spring.entities.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// @Mapper 어노테이션 : 마이바티스의 메퍼 역할
@Mapper
public interface EmployeeMapper {
    List<Employee> getEmployees();

    Employee findByPk(Long empId);

    int store(Employee employee);
}
