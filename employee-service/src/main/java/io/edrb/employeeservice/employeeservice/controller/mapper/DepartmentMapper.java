package io.edrb.employeeservice.employeeservice.controller.mapper;

import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.model.dto.DepartmentDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static Department toDepartment(DepartmentDTO dto) {
        return Department.builder()
                .name(dto.getName())
                .build();
    }

    public static DepartmentDTO toDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }
}
