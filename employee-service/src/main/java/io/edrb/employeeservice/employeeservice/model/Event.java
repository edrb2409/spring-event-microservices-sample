package io.edrb.employeeservice.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    private String type;

    private LocalDateTime timestamp;

    private Object payload;

}
