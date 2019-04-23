package io.edrb.employeeservice.employeeservice.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class DepartmentVO {

    @NotBlank
    private final String name;

}
