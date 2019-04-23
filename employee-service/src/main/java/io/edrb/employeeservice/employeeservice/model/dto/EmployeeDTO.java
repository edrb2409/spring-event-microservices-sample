package io.edrb.employeeservice.employeeservice.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;


@Data
@Builder
public class EmployeeDTO {

    private final String id;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String fullName;

    @Past
    private final Date birthday;


    private final @Valid DepartmentVO department;


}
