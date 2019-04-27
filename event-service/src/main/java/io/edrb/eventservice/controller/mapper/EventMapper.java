package io.edrb.eventservice.controller.mapper;

import io.edrb.eventservice.model.Event;
import io.edrb.eventservice.model.dto.EventDTO;

import java.util.List;
import java.util.stream.Collectors;

public final class EventMapper {

    private EventMapper() {}

    public static List<EventDTO> toEventDTO(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventDTO)
                .collect(Collectors.toList());
    }

    private static EventDTO toEventDTO(Event event) {
        return EventDTO.builder()
                .employeeId(event.getEmployeeId())
                .payload(event.getPayload())
                .timestamp(event.getTimestamp())
                .type(event.getType())
                .build();
    }
}
