package com.beta.orange.event;

public class xEvent {

    public final Object[] message;

    public xEvent(Object... message) {
        this.message = message;
    }

    public Object[] getMessage() {
        return message;
    }
}
