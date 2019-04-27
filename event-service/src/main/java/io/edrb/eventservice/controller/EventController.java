package io.edrb.eventservice.controller;

import io.edrb.eventservice.controller.mapper.EventMapper;
import io.edrb.eventservice.model.dto.EventDTO;
import io.edrb.eventservice.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEventsByEmployee(@RequestParam String employeeId) {
        log.debug("events for employee: {}", employeeId);

        return ResponseEntity.ok(
                EventMapper.toEventDTO(service.getEventsFor(employeeId)));
    }
}
