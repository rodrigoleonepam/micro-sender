package com.epam.task.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Notification {

    private String userName;
    private String message;

}
