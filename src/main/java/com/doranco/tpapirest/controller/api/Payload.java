package com.doranco.tpapirest.controller.api;

import java.time.LocalDateTime;

public class Payload {

    private String message;
    private Object content;
    private LocalDateTime dateTime;

    public Payload() {
        this.dateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
