package ru.nsu.fit.chat.template;

import lombok.Data;

@Data
public class RegistrationInfo {
    private boolean error;
    private String message;

    public RegistrationInfo(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}
