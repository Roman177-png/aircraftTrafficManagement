package com.project.office.listener.processor;

import com.project.common.messages.AirPortStateMessage;
import com.project.common.messages.OfficeStateMessage;
import com.project.common.processor.MessageConverter;
import com.project.common.processor.MessageProcessor;

import com.project.office.provider.AirPortsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor

public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final MessageConverter messageConverter;
    private final AirPortsProvider airPortsProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage){
        airPortsProvider.getPorts().forEach(airport -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airport)));
        });
    }
}
