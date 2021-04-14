package com.example.accountconsumer.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @JmsListener(destination = "account-log")
    public void receiveMessage(String message){
        System.out.println("Received: <" + message + ">");
    }
}
