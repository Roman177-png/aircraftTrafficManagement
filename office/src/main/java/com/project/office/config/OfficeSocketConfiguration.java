package com.project.office.config;


import com.project.common.processor.MessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.github.benmanes.caffeine.cache.Cache;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class OfficeSocketConfiguration implements WebSocketConfigurer {
    private final MessageConverter messageConverter;
    private final Cache<String, WebSocketSession> sessionCache;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(new OfficeSocketHandler(messageConverter, sessionCache, kafkaTemplate),"/websocket")
                .setAllowedOrigins("*");
    }
}