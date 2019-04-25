package io.edrb.employeeservice.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Department is not unique")
public class NoUniqueDepartmentException extends RuntimeException {
}
