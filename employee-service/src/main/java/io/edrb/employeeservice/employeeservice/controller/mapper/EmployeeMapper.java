package io.edrb.employeeservice.employeeservice.controller.mapper;

import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.model.Employee;
import io.edrb.employeeservice.employeeservice.model.dto.DepartmentVO;
import io.edrb.employeeservice.employeeservice.model.dto.EmployeeDTO;

public final class EmployeeMapper {

    private EmployeeMapper() {}

    public static Employee toEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .fullname(dto.getFullName())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .department(Department.builder().name(dto.getDepartment().getName()).build())
                .build();
    }

    public static EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .birthday(employee.getBirthday())
                .email(employee.getEmail())
                .fullName(employee.getFullname())
                .department(DepartmentVO.builder().name(employee.getDepartment().getName()).build())
                .build();
    }

}
