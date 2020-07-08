package com.panaseer.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Component
public class Producer {
    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public Producer(@Value("${app.kafka.producer.topic:arj-test}") String topic,
                    KafkaTemplate<String, String> kafkaTemplate,
                    @Value("${arj.hello:did not work}") String arj) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage() {
        LOG.info("KAFKA CONFIG: " + kafkaTemplate.toString());
        IntStream.range(1, 20).forEach(this::send);
    }

    private void send(int value) {
        String message = String.format("%d - %s", value, DateTimeFormatter.ISO_INSTANT.format(Clock.systemUTC().instant()));
        LOG.info("Publishing message {}", message);
        kafkaTemplate.send(topic, message);
    }
}
