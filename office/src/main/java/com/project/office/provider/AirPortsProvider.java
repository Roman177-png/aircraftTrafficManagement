package com.project.office.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


import com.project.common.bean.Airport;
import com.project.common.bean.RoutePoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class AirPortsProvider {
    private final List<Airport> ports = new ArrayList<>();

    public Airport findAirPortAndRemoveBoard(String boardName) {
        AtomicReference<Airport> res = new AtomicReference<>();
        ports.stream().filter(airPort -> airPort.getBoards().contains(boardName))
                .findFirst()
                .ifPresent(airPort -> {
                    airPort.removeBoard(boardName);
                    res.set(airPort);
                });
        return res.get();
    }
    public Airport getAirPort(String airPortName){
        return ports.stream()
                .filter(airPort -> airPort.getName().equals(airPortName))
                .findFirst()
                .orElse(new Airport());
    }

    public RoutePoint getRoutePoint(String airPortName){
        return new RoutePoint(getAirPort(airPortName));
    }
}