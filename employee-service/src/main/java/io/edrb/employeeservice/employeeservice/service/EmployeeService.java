package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.model.Employee;

public interface EmployeeService {

    Employee create(Employee employee);

    Employee update(String id, Employee employee);

    Employee getById(String id);

    Employee delete(String id);
}
