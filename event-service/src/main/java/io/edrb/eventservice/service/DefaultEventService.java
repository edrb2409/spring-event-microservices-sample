package io.edrb.eventservice.service;

import io.edrb.eventservice.model.Event;
import io.edrb.eventservice.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DefaultEventService implements EventService {

    private final EventRepository repository;

    public DefaultEventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Event event) {
        repository.save(event);
    }

    @Override
    public List<Event> getEventsFor(String employeeId) {
        return repository.findByEmployeeIdOrderByTimestampAsc(employeeId);
    }
}
