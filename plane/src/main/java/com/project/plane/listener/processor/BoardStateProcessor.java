package com.project.plane.listener.processor;

import com.project.common.messages.BoardStateMessage;
import com.project.common.processor.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("BOARD_STATE")
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    @Override
    public void process(String jsonMessage) {

    }
}
