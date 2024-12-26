package com.towatalk.towatalk_backend.model;

import lombok.Data;

@Data
public class WebSocketResponse {
    private String type;
    private boolean success;
    private String message;

    public static WebSocketResponse success(String type, String message) {
        WebSocketResponse response = new WebSocketResponse();
        response.setType(type);
        response.setSuccess(true);
        response.setMessage(message);
        return response;
    }

    public static WebSocketResponse error(String type, String message) {
        WebSocketResponse response = new WebSocketResponse();
        response.setType(type);
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
