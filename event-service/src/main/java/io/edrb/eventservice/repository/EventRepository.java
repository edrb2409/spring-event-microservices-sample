package io.edrb.eventservice.repository;

import io.edrb.eventservice.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {

    List<Event> findByEmployeeIdOrderByTimestampAsc(String employeeId);
}
