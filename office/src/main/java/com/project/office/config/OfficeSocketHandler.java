package com.project.office.config;

import com.project.common.messages.OfficeStateMessage;
import com.project.common.processor.MessageConverter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.github.benmanes.caffeine.cache.Cache;
@Slf4j
@Component
@RequiredArgsConstructor
public class OfficeSocketHandler extends TextWebSocketHandler {
    private final MessageConverter messageConverter;
    private final Cache<String, WebSocketSession> sessionCache;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (session.isOpen()) {
            sessionCache.put(session.getId(), session);
        }
        if (message.getPayload().equals("update")) {
            kafkaTemplate.sendDefault(messageConverter.toJson(new OfficeStateMessage()));
        }
    }
}