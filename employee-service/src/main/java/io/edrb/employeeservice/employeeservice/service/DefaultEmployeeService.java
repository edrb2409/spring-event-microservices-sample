package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.exception.DepartmentNotFoundException;
import io.edrb.employeeservice.employeeservice.exception.EmployeeNotFoundException;
import io.edrb.employeeservice.employeeservice.exception.NotUniqueEmailException;
import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.model.Employee;
import io.edrb.employeeservice.employeeservice.repository.DepartmentRepository;
import io.edrb.employeeservice.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository repository;

    private final DepartmentRepository departmentRepository;

    public DefaultEmployeeService(EmployeeRepository repository,
                                  DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee create(Employee employee) {
        Department department = getDepartment(employee.getDepartment().getName());

        log.debug("department found: {}", department);

        employee.setDepartment(department);

        return save(employee);
    }

    @Override
    public Employee update(String id, Employee employee) {
        findById(id);

        Department department = getDepartment(employee.getDepartment().getName());

        log.debug("department found: {}", department);

        employee.setDepartment(department);

        employee.setId(id);

        return save(employee);
    }

    @Override
    public Employee getById(String id) {
        return findById(id);
    }

    @Override
    public Employee delete(String id) {
        Employee employee = findById(id);

        repository.deleteById(id);

        return employee;
    }

    private Employee save(Employee employee) {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException ex) {
            log.error("Exception: {}", ex.getMessage(), ex);
            throw new NotUniqueEmailException();
        }
    }

    private Employee findById(String id) {
        return repository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private Department getDepartment(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(DepartmentNotFoundException::new);
    }
}
