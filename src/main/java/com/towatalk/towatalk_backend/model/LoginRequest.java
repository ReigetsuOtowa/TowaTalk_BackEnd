package com.towatalk.towatalk_backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String type; // 消息类型，例如 "LOGIN"
}
