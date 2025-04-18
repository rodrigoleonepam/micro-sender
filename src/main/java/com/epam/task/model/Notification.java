package com.epam.task.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Notification {

    private String userName;
    private String message;

}
