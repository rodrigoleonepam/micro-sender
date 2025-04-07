package com.epam.task.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Status {
    
    private int code;
    private String message;

}
