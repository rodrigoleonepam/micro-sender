package com.epam.task.receiver;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RabbitRepository {
    
    private static RabbitRepository instance = null;
    private Queue<String> messageQueue = null;

    private RabbitRepository() {
        messageQueue = new ConcurrentLinkedQueue<>();
    }

    public static RabbitRepository getInstance() {
        if (instance == null) {
            instance = new RabbitRepository();
        }
        return instance;
    }

    public void addMessage(String message) {
        messageQueue.add(message);
    }

    public String getMessage() {
        return messageQueue.poll();
    }

    public int getQueueSize() {
        return messageQueue.size();
    }
}
