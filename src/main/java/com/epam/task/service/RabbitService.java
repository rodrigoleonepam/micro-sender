package com.epam.task.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.task.exception.MessageNotFoundException;
import com.epam.task.model.Notification;
import com.epam.task.receiver.RabbitRepository;
import com.epam.task.util.JSONUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RabbitService {

    @Autowired
    private AmqpTemplate amqpTemplate;
  
    @Value("${rabbitmq.queue}")
    String queue;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void sendNotification(String message) {
        log.info("Sending message to queue: {}", queue);

        amqpTemplate.convertAndSend(queue, message);
        log.info("Message sent: {}", message);        
    }

    public Notification retrieveNotification() {
        log.info("Retrieving message from queue: {}", queue);
        String message = RabbitRepository.getInstance().getMessage();
        validateMessage(message);
        log.info("Message retrieved: {}", message);
        Notification notification = JSONUtil.fromJson(message, Notification.class);
        return notification;
    }

    private void validateMessage(String message) {
        if (message == null || message.isEmpty()) {
            throw new MessageNotFoundException("Message not found in the queue");
        }
    }

}
