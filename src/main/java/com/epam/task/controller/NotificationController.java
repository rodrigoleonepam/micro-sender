package com.epam.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.task.exception.MessageNotFoundException;
import com.epam.task.model.Notification;
import com.epam.task.model.Status;
import com.epam.task.service.RabbitService;
import com.epam.task.util.JSONUtil;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class NotificationController {
    
    @Autowired
    private RabbitService rabbitService;

    @PostMapping(value = "/notification", produces = "application/json")
    public ResponseEntity<Status> sendNotification(@RequestBody Notification notification) {
        log.info("Notification received: {}", notification);

        String message = JSONUtil.toJson(notification);
        rabbitService.sendNotification(message);
        
        Status status = Status.builder()
                .code(0)
                .message("Notification sent successfully")
                .build();
        return ResponseEntity.ok(status); 
    }

    @GetMapping(value = "/retrieve", produces = "application/json")
    public ResponseEntity<Notification> retrieveNotification() {
        log.info("Retrieving notification from queue");

        Notification notification = rabbitService.retrieveNotification();
        return ResponseEntity.ok(notification);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Notification> handleMessageNotFoundException(RuntimeException e) {
        log.error("Error occurred: {}", e.getMessage());
        return ResponseEntity.status(404).body(null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Status> handleRuntimeException(RuntimeException e) {
        log.error("Error occurred: {}", e.getMessage(), e);
        Status status = Status.builder()
                .code(1)
                .message("Error occurred while sending notification")
                .build();
        return ResponseEntity.status(500).body(status);
    }
}
