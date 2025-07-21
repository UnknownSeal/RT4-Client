package com.unknownseal.mixinlauncher.events;

import java.awt.event.MouseWheelEvent;

public class MouseWheelMoved {
    private final MouseWheelEvent event;

    public MouseWheelMoved(MouseWheelEvent event) {
        this.event = event;
    }

    public MouseWheelEvent getEvent() {
        return event;
    }
}

