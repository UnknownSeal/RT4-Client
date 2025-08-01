package com.unknownseal.mixinlauncher.events;

import java.awt.event.MouseEvent;

public class MouseClicked {
    private final MouseEvent mouseEvent;
    private final int mouseX;
    private final int mouseY;
    private final int button;
    private final boolean ctrlPressed;
    private final boolean shiftPressed;
    private final boolean altPressed;

    public MouseClicked(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.button = mouseEvent.getButton();
        this.ctrlPressed = mouseEvent.isControlDown();
        this.shiftPressed = mouseEvent.isShiftDown();
        this.altPressed = mouseEvent.isAltDown();
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getButton() {
        return button;
    }

    public boolean isCtrlPressed() {
        return ctrlPressed;
    }

    public boolean isShiftPressed() {
        return shiftPressed;
    }

    public boolean isAltPressed() {
        return altPressed;
    }

    public boolean isLeftClick() {
        return button == MouseEvent.BUTTON1;
    }

    public boolean isRightClick() {
        return button == MouseEvent.BUTTON3;
    }

    public boolean isCtrlLeftClick() {
        return isCtrlPressed() && isLeftClick();
    }
}