package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.exception.EmployeeNotFoundException;
import io.edrb.employeeservice.employeeservice.exception.NotUniqueEmailException;
import io.edrb.employeeservice.employeeservice.model.Department;
import io.edrb.employeeservice.employeeservice.model.Employee;
import io.edrb.employeeservice.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private EmployeeService service;

    @Mock EmployeeRepository repository;

    @BeforeEach void init_() {
        service = new DefaultEmployeeService(repository);
    }

    @Test void shouldCreateNewEmployee() {
        String id = UUID.randomUUID().toString();

        when(repository.save(getEmployeeForCreating())).thenReturn(getEmployee(id));

        Employee employeeCreated = service.create(getEmployeeForCreating());

        Assertions.assertEquals(id, employeeCreated.getId());
    }

    @Test void shouldRaiseAnExceptionWhenEmailIsNotUniqueOnCreation() {
        when(repository.save(getEmployeeForCreating())).thenThrow(NotUniqueEmailException.class);

        Assertions.assertThrows(NotUniqueEmailException.class,
                () -> service.create(getEmployeeForCreating()));
    }

    @Test void shouldUpdateAnEmployee() {
        String id = UUID.randomUUID().toString();

        when(repository.findById(id)).thenReturn(Optional.of(getEmployee(id)));
        when(repository.save(getEmployee(id))).thenReturn(getEmployee(id));

        Employee employeeCreated = service.update(id, getEmployee(id));

        Assertions.assertEquals(id, employeeCreated.getId());
    }

    @Test void shouldRaiseAnExceptionWhenEmailIsNotUniqueOnUpdate() {
        String id = UUID.randomUUID().toString();

        when(repository.findById(id)).thenReturn(Optional.of(getEmployee(id)));
        when(repository.save(getEmployee(id))).thenThrow(NotUniqueEmailException.class);

        Assertions.assertThrows(NotUniqueEmailException.class,
                () -> service.update(id, getEmployee(id)));
    }

    @Test void shouldRaiseAnExceptionWhenEmployeeDoesNotExistsOnUpdate() {
        when(repository.findById("12")).thenReturn(Optional.empty());

        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> service.update("12", getEmployee("12")));
    }

    @Test void shouldGetAEmployeeById() {
        when(repository.findById("123")).thenReturn(Optional.of(getEmployee("123")));

        Assertions.assertEquals("123", service.getById("123").getId());

    }

    @Test void shouldRaiseAnExceptionWhenEmployeeDoesNotExistsOnGetById() {
        when(repository.findById("12")).thenReturn(Optional.empty());

        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> service.getById("12"));
    }

    @Test void shouldDeleteAEmployee() {
        when(repository.findById("123")).thenReturn(Optional.of(getEmployee("123")));
        verify(repository, times(1)).deleteById("123");

        service.delete("123");
    }

    @Test void shouldRaiseAnExceptionWhenEmployeeDoesNotExistsOnDelete() {
        when(repository.findById("123")).thenReturn(Optional.of(getEmployee("123")));

        service.delete("123");
    }

    private Employee getEmployeeForCreating() {
        return Employee.builder()
                .birthday(LocalDate.of(1986, Month.SEPTEMBER, 24))
                .department(Department.builder().id(1L).name("IT").build())
                .email("me@some.com")
                .fullname("me some")
                .build();
    }

    private Employee getEmployee(String id) {
        return Employee.builder()
                .birthday(LocalDate.of(1986, Month.SEPTEMBER, 24))
                .department(Department.builder().id(1L).name("IT").build())
                .email("me@some.com")
                .fullname("me some")
                .id(id)
                .build();
    }

}
