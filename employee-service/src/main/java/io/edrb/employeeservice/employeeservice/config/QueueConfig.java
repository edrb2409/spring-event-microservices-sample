package io.edrb.employeeservice.employeeservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {


    @Bean
    public Queue eventsQueue(@Value("${services.events.queueName}") String queueName) {
        return new Queue(queueName);
    }

}
