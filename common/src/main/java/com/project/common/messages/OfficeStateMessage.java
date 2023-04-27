package com.project.common.messages;

import com.project.common.bean.Source;
import com.project.common.bean.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OfficeStateMessage extends Message{
    public OfficeStateMessage() {
        this.source = Source.OFFICE;
        this.type = Type.STATE;
    }
}
