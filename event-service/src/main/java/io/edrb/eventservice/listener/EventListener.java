package io.edrb.eventservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Component
@RabbitListener(queues = "${eventQueue}")
public class EventListener {

    private final ObjectMapper mapper;

    public EventListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @RabbitHandler
    public void receiveEvents(String event) {
        if(StringUtils.isEmpty(event))
            log.error("event is null or empty");

        log.debug("event {}", event);
    }


}
