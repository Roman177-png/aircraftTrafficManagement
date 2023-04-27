package com.project.common.processor;

import com.project.common.messages.Message;

public interface MessageProcessor<T extends Message> {
    void process(String jsonMessage);
}
