package io.edrb.employeeservice.employeeservice.controller;

import io.edrb.employeeservice.employeeservice.controller.mapper.DepartmentMapper;
import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.model.dto.DepartmentDTO;
import io.edrb.employeeservice.employeeservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        log.info("department: {}", departmentDTO);

        DepartmentDTO departmentDTOCreated = DepartmentMapper.toDTO(
                service.create(DepartmentMapper.toDepartment(departmentDTO)));

        return ResponseEntity.ok().header("id", String.valueOf(departmentDTOCreated.getId())).build();
    }
}
