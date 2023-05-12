package ru.nsu.fit.chat.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDto implements Serializable {
    private String username;
    private String text;
}
