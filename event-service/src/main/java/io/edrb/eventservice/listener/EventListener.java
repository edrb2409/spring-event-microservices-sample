package io.edrb.eventservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.edrb.eventservice.model.Event;
import io.edrb.eventservice.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = "${eventQueue}")
public class EventListener {

    private final ObjectMapper mapper;

    private final EventService eventService;

    public EventListener(ObjectMapper mapper, EventService eventService) {
        this.mapper = mapper;
        this.eventService = eventService;
    }

    @RabbitHandler
    public void receiveEvents(String event) {
        if(StringUtils.isEmpty(event))
            log.error("event is null or empty");

        log.debug("event {}", event);
        try{
            Event incomeEvent = mapper.readValue(event, Event.class);

            log.debug("Event deserialized: {}", incomeEvent);

            eventService.save(incomeEvent);
        } catch (IOException e) {
            log.error("Exception: {}", e.getMessage());
        }
    }


}
