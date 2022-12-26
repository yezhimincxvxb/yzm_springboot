package com.yzm.listener.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 监听事件
 */
@Getter
@Setter
public class MyApplicationEvent extends ApplicationEvent {

    private static final long serialVersionUID = 4545712047718627688L;
    private Task task;

    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

}

