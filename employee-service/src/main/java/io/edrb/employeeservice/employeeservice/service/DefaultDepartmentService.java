package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.exception.NoUniqueDepartment;
import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultDepartmentService implements DepartmentService {

    private final DepartmentRepository repository;

    public DefaultDepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department create(Department department) {
        try {
            return repository.save(department);
        } catch (DataIntegrityViolationException ex) {
            log.error("Exception: {}", ex.getMessage(), ex);
            throw new NoUniqueDepartment();
        }
    }
}
