package io.edrb.employeeservice.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email is not unique")
public class NotUniqueEmailException extends RuntimeException {
}
