package com.panaseer.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public NewTopic createTopic() {
        short one = 1;
        return new NewTopic("A_NEW_TOPIC", 1, one);
    }
}
