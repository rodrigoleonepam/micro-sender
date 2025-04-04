package com.epam.task.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.task.model.Notification;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class NotificationController {
    
    @PostMapping(value = "/notification", produces = "application/json")
    public void sendNotification(@RequestBody Notification notification) {
        log.info("Notification received: {}", notification);

        
    }
}
