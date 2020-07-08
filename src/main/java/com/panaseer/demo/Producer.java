package com.panaseer.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Component
public class Producer {
    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage() {
        IntStream.range(1, 20).forEach(this::send);
    }

    private void send(int value) {
        String message = String.format("%d - %s", value, DateTimeFormatter.ISO_INSTANT.format(Clock.systemUTC().instant()));
        LOG.info("Publishing message {}", message);
        kafkaTemplate.send("com-panaseer-demo", message);
    }
}
