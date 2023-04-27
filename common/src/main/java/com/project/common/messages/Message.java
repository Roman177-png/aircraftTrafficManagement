package com.project.common.messages;
import com.project.common.bean.Source;
import com.project.common.bean.Type;
import lombok.NoArgsConstructor;
public class Message {
    protected Type type;
    protected Source source;

    public String getCode() {
        return source.name() + "_" + type.name();
    }
}
