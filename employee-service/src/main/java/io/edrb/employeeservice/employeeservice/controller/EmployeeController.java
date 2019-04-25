package io.edrb.employeeservice.employeeservice.controller;

import io.edrb.employeeservice.employeeservice.controller.mapper.EmployeeMapper;
import io.edrb.employeeservice.employeeservice.model.dto.EmployeeDTO;
import io.edrb.employeeservice.employeeservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        log.debug("Getting employee {}", id);

        return ResponseEntity.ok(EmployeeMapper.toDTO(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.debug("Creating employee {}", employeeDTO);

        EmployeeDTO employeeCreated = EmployeeMapper.toDTO(
                service.create(EmployeeMapper.toEmployee(employeeDTO)));

        return ResponseEntity.ok().header("id", employeeCreated.getId()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable String id,
                                         @Valid @RequestBody EmployeeDTO employeeDTO) {
        log.debug("updating employee {}, with {}", id, employeeDTO);

        service.update(id, EmployeeMapper.toEmployee(employeeDTO));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        log.debug("deleting employee {}", id);

        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
