package com.project.plane.listener.processor;

import com.project.common.bean.Route;
import com.project.common.messages.OfficeRouteMessage;
import com.project.common.processor.MessageConverter;
import com.project.common.processor.MessageProcessor;

import com.project.plane.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {
    private final BoardProvider boardProvider;
    private final MessageConverter messageConverter;
    @Override
    public void process(String jsonMessage) {
        OfficeRouteMessage msg = messageConverter.extractMessage(jsonMessage, OfficeRouteMessage.class);
        Route route = msg.getRoute();
        boardProvider.getBoards().stream()
                .filter(board -> board.noBusy() && route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);
                });
    }
}
