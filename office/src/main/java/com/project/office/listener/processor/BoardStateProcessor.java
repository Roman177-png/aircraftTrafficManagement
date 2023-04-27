package com.project.office.listener.processor;

import com.project.common.bean.Airport;
import com.project.common.bean.Board;
import com.project.common.bean.Route;
import com.project.common.messages.AirPortStateMessage;
import com.project.common.messages.BoardStateMessage;
import com.project.common.processor.MessageConverter;
import com.project.common.processor.MessageProcessor;

import com.project.office.provider.AirPortsProvider;
import com.project.office.provider.BoardsProvider;
import com.project.office.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component("BOARD_STATE")
@RequiredArgsConstructor
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {
    private final MessageConverter messageConverter;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final WaitingRoutesService waitingRoutesService;
    private final BoardsProvider boardsProvider;
    private final AirPortsProvider airPortsProvider;
    @Override
    public void process(String jsonMessage) {
        BoardStateMessage message = messageConverter.extractMessage(jsonMessage,BoardStateMessage.class);
        Board board = message.getBoard();
        Optional<Board> previousOpt = boardsProvider.getBoard(board.getName());
        Airport airPort = airPortsProvider.getAirPort(board.getLocation());

        boardsProvider.addBoard(board);
        if(previousOpt.isPresent() && board.hasRoute() && previousOpt.get().hasRoute()){
            Route route = board.getRoute();
            waitingRoutesService.remove(route);
        }

        if(previousOpt.isEmpty() || !board.isBusy() && previousOpt.get().isBusy()){
            airPort.addBoard(board.getName());
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airPort)));

        }
    }
}
