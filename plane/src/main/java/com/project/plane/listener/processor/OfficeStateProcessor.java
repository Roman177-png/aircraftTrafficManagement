package com.project.plane.listener.processor;

import com.project.common.messages.BoardStateMessage;
import com.project.common.messages.OfficeStateMessage;
import com.project.common.processor.MessageConverter;
import com.project.common.processor.MessageProcessor;
import com.project.plane.provider.BoardProvider;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {
    private final MessageConverter messageConverter;
    private final BoardProvider boardProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        boardProvider.getBoards().forEach(board -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new BoardStateMessage(board)));
        });
    }
}
