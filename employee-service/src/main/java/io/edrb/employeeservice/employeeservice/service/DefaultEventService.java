package io.edrb.employeeservice.employeeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.edrb.employeeservice.employeeservice.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DefaultEventService implements EventService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue eventsQueue;

    private final ObjectMapper mapper;

    public DefaultEventService(RabbitTemplate rabbitTemplate,
                               Queue eventsQueue,
                               ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.eventsQueue = eventsQueue;
        this.mapper = mapper;
    }

    @Override
    @Async
    public void sendEvent(Event event) {
        log.debug("Sending event {}", event);

        try {
            rabbitTemplate.convertAndSend(eventsQueue.getName(), mapper.writeValueAsString(event));
        } catch (IOException e) {
            log.error("Serialization Error: {}", e.getMessage(), e);
        }
    }
}
