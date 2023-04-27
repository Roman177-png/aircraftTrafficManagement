package com.project.plane.listener;
import java.util.HashMap;
import java.util.Map;

import com.project.common.messages.Message;
import com.project.common.processor.MessageConverter;
import com.project.common.processor.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final MessageConverter messageConverter;

    @Autowired
    private Map<String, MessageProcessor<? extends Message>> processors = new HashMap<>();

    @KafkaListener(id="BoardId", topics="officeRoutes")
    public void radarListener(String data){
        String code = messageConverter.extractCode(data);
        try {
            processors.get(code).process(data);
        } catch (Exception e) {
            log.error("Code: " + code + ". " +  e.getLocalizedMessage());
        }
    }
}
