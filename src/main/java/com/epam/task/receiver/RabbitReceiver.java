package com.epam.task.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@RabbitListener(queues = "${rabbitmq.queue}", id = "rabbitListener")
@Log4j2
public class RabbitReceiver {
    
    @RabbitHandler
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        RabbitRepository.getInstance().addMessage(message);

        RabbitRepository.getInstance().getQueueSize();
        log.info("Queue size: {}", RabbitRepository.getInstance().getQueueSize());
    }

}
