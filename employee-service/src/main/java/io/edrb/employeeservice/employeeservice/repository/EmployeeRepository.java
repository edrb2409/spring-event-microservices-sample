package io.edrb.employeeservice.employeeservice.repository;

import io.edrb.employeeservice.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
