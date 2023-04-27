package com.project.plane.provider;

import java.util.ArrayList;
import java.util.List;

import com.project.common.bean.Board;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class BoardProvider {
    private final List<Board> boards = new ArrayList<>();
}