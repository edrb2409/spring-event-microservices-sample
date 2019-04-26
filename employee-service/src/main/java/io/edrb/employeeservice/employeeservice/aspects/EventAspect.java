package io.edrb.employeeservice.employeeservice.aspects;

import io.edrb.employeeservice.employeeservice.model.Employee;
import io.edrb.employeeservice.employeeservice.model.Event;
import io.edrb.employeeservice.employeeservice.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Aspect
@Component
public class EventAspect {

    private final EventService service;

    public EventAspect(EventService service) {
        this.service = service;
    }

    @AfterReturning(
            value = "@annotation(io.edrb.employeeservice.employeeservice.aspects.annotation.CreateEvent)",
            returning = "savedEmployee")
    public void afterCreateEmployee(JoinPoint joinPoint, Employee savedEmployee) {
        log.debug("joinPoint {}:", joinPoint.toString());
        log.debug("saved Employee: {}", savedEmployee);

        service.sendEvent(Event.builder()
                .payload(savedEmployee)
                .timestamp(LocalDateTime.now())
                .type("create")
                .build());
    }

    @AfterReturning(
            value = "@annotation(io.edrb.employeeservice.employeeservice.aspects.annotation.UpdateEvent)",
            returning = "savedEmployee")
    public void afterUpdateEmployee(JoinPoint joinPoint, Employee savedEmployee) {
        log.debug("joinPoint {}:", joinPoint.toString());
        log.debug("updated Employee: {}", savedEmployee);

        service.sendEvent(Event.builder()
                .payload(savedEmployee)
                .timestamp(LocalDateTime.now())
                .type("update")
                .build());
    }

    @AfterReturning(
            value = "@annotation(io.edrb.employeeservice.employeeservice.aspects.annotation.DeleteEvent)",
            returning = "savedEmployee")
    public void afterSaveUser(JoinPoint joinPoint, Employee savedEmployee) {
        log.debug("joinPoint {}:", joinPoint.toString());
        log.debug("updated Employee: {}", savedEmployee);

        service.sendEvent(Event.builder()
                .payload(savedEmployee)
                .timestamp(LocalDateTime.now())
                .type("delete")
                .build());
    }

}
