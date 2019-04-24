package io.edrb.employeeservice.employeeservice.repository;

import io.edrb.employeeservice.employeeservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


}
