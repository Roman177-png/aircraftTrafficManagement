package com.project.common.messages;

import com.project.common.bean.Route;
import com.project.common.bean.Source;
import com.project.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OfficeRouteMessage extends Message {

    private Route route;

    public OfficeRouteMessage() {
        this.source = Source.OFFICE;
        this.type = Type.ROUTE;
    }

    public OfficeRouteMessage(Route val) {
        this();
        this.route = val;
    }
}
