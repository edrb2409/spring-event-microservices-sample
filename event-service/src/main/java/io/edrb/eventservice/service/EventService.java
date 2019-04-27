package io.edrb.eventservice.service;

import io.edrb.eventservice.model.Event;

import java.util.List;

public interface EventService {

    void save(Event event);

    List<Event> getEventsFor(String employeeId);
}
