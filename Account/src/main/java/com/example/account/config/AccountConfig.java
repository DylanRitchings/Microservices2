package com.example.account.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class AccountConfig {
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ConnectionFactory activeMqConnectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        return connectionFactory;
    }

}
