package com.project.common.messages;

import com.project.common.bean.Source;
import com.project.common.bean.Type;
import com.project.common.bean.Airport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirPortStateMessage extends Message {
    private Airport airPort;

    public AirPortStateMessage() {
        this.source = Source.AIRPORT;
        this.type = Type.STATE;
    }

    public AirPortStateMessage(Airport airPort) {
        this();
        this.airPort = airPort;
    }
}
