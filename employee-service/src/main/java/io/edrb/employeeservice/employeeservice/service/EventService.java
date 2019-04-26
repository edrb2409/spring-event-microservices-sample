package io.edrb.employeeservice.employeeservice.service;

import io.edrb.employeeservice.employeeservice.model.Event;

public interface EventService {

    void sendEvent(Event event);
}
