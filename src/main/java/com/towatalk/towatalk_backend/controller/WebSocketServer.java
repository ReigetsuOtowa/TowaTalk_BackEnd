package com.towatalk.towatalk_backend.controller;

import com.towatalk.towatalk_backend.service.UserService;
import com.towatalk.towatalk_backend.model.LoginRequest;
import com.towatalk.towatalk_backend.model.WebSocketResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {

    private static UserService userService;

    private static final ConcurrentHashMap<String, Session> SESSIONS = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setUserService(UserService service) {
        userService = service;
    }

    @OnOpen
    public void onOpen(Session session) {
        // 连接建立时的处理
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(message, LoginRequest.class);

            if ("LOGIN".equals(loginRequest.getType())) {
                handleLogin(loginRequest, session);
            }
        } catch (Exception e) {
            sendMessage(session, WebSocketResponse.error("ERROR", "Invalid message format"));
        }
    }

    private void handleLogin(LoginRequest loginRequest, Session session) throws IOException {
        boolean isValid = userService.validateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        if (isValid) {
            SESSIONS.put(loginRequest.getUsername(), session);
            sendMessage(session, WebSocketResponse.success(
                    "LOGIN",
                    "Login successful"
            ));
        } else {
            sendMessage(session, WebSocketResponse.error(
                    "LOGIN",
                    "Invalid username or password"
            ));
        }
    }

    private void sendMessage(Session session, WebSocketResponse response) throws IOException {
        session.getBasicRemote().sendText(objectMapper.writeValueAsString(response));
    }

    @OnClose
    public void onClose(Session session) {
        // 连接关闭时的处理
        SESSIONS.values().remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        // 发生错误时的处理
    }
}
