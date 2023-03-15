package com.example.technicalTask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Comments {
    private LocalDateTime localDateTime;
    private String text;
}
