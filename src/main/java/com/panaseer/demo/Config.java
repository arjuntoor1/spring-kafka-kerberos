package com.panaseer.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public NewTopic createTopic(@Value("${com.panaseer.demo.topic:com-panaseer-demo}") String topic) {
        short one = 1;
        return new NewTopic(topic, 1, one);
    }
}
