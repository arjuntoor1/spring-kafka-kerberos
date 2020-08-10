package com.panaseer.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Producer {
    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;
    private int messageCounter;

    public Producer(KafkaTemplate<String, String> kafkaTemplate,
                    NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic.name();
        this.messageCounter = 0;
    }

    @Scheduled(fixedRateString = "${com.panaseer.demo.message-interval-millis:10000}", initialDelay = 5000L)
    public void send() {
        String message = String.format("msg-%d", ++messageCounter);
        try {
            LOG.info("Publishing message: {}", message);
            long ts = kafkaTemplate.send(topic, message).get(5L, TimeUnit.SECONDS).getRecordMetadata().offset();
            LOG.info("Offset: " + ts);
        }
        catch (Exception e) {
            LOG.warn("Error sending / receiving ack", e);
        }
    }
}
