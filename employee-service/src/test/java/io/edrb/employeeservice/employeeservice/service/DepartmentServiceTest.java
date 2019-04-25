package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.exception.NoUniqueDepartment;
import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private DepartmentService departmentService;

    @Mock DepartmentRepository repository;

    @BeforeEach void init_() {
        departmentService = new DefaultDepartmentService(repository);
    }

    @Test void shouldSaveADepartment() {
        Department it = getITDepartment();

        when(repository.save(it)).thenReturn(Department.builder().id(1L).name("IT").build());

        Assertions.assertEquals(1L, departmentService.create(it).getId());
    }

    @Test void shouldRaiseAnExceptionWhenDepartmentIsNotUnique() {
        Department it = getITDepartment();

        when(repository.save(it)).thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(NoUniqueDepartment.class,
                () -> departmentService.create(it));
    }

    private Department getITDepartment() {
        return Department.builder()
                .name("IT")
                .build();
    }

}
