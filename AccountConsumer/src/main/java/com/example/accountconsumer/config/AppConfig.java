package com.example.accountconsumer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class AppConfig {

    @Bean
    //Creates connections to activemq server
    public ConnectionFactory activeMqConnectionFactory(){
        return new ActiveMQConnectionFactory();
    }

    @Bean
    //create listeners that wait for messages to be added to the queue
    public DefaultJmsListenerContainerFactory listenerContainer(){
        DefaultJmsListenerContainerFactory jmsListenerFactory = new DefaultJmsListenerContainerFactory();

        jmsListenerFactory.setConnectionFactory(activeMqConnectionFactory());

        return jmsListenerFactory;
    }
}
