package com.epam.task.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RabbitService {
    
    public void sendNotification(String notification) {
        log.info("Notification sent: {}", notification);
        
        
    }

}
