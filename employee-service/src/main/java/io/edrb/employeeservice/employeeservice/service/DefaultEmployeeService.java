package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.exception.EmployeeNotFoundException;
import io.edrb.employeeservice.employeeservice.exception.NoUniqueDepartmentException;
import io.edrb.employeeservice.employeeservice.exception.NotUniqueEmailException;
import io.edrb.employeeservice.employeeservice.model.Employee;
import io.edrb.employeeservice.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository repository;

    public DefaultEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);

        return save(employee);
    }

    @Override
    public Employee update(String id, Employee employee) {
        findById(id);

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
}
