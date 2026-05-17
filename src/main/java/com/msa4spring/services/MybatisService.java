package com.msa4spring.services;

import com.msa4spring.entities.Employee;
import com.msa4spring.mappers.EmployeeMapper;
import com.msa4spring.requests.EmployeesDeleteRequest;
import com.msa4spring.requests.EmployeesModifyRequest;
import com.msa4spring.requests.EmployeesStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service 어노테이션 : 스프링부트가 서비스라고 인지함
// @RequiredArgsConstructor : DI 기능 이용
@Service
@RequiredArgsConstructor
public class MybatisService {
    private final EmployeeMapper employeeMapper;

    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Transactional
    public Employee store(EmployeesStoreRequest employeesStoreRequest) {
        Employee employee = new Employee();
        employee.setBirth(employeesStoreRequest.birth());
        employee.setGender(employeesStoreRequest.gender());
        employee.setName(employeesStoreRequest.name());

        employeeMapper.store(employee);

        return employeeMapper.findByPk(employee.getEmpId());
    }

    public Employee modify(EmployeesModifyRequest employeesModifyRequest) {
        Employee employee = new Employee();
        employee.setEmpId(employeesModifyRequest.empId());
        employee.setBirth(employeesModifyRequest.birth());

        employeeMapper.modify(employee);

        return employeeMapper.findByPk(employee.getEmpId());
    }

    public Employee delete(EmployeesDeleteRequest employeesDeleteRequest) {
        // 물리적 삭제
        employeeMapper.hardDelete(employeesDeleteRequest.empId());
        // 소프트 삭제
        // employeeMapper.delete(employeesDeleteRequest.empId());

        return employeeMapper.findByPk(employeesDeleteRequest.empId());
    }

}
