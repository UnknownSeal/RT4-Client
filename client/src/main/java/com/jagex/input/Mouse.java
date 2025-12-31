package com.jagex.input;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.jagex.core.utils.SystemTimer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ug")
public final class Mouse implements MouseListener, MouseMotionListener, FocusListener {

	@OriginalMember(owner = "runetek4.client!lk", name = "Z", descriptor = "I")
	public static int clickButton = 0;

    @OriginalMember(owner = "runetek4.client!ok", name = "f", descriptor = "J")
    public static long prevClickTime = 0L;

	@OriginalMember(owner = "runetek4.client!sc", name = "v", descriptor = "I")
	public static int lastMouseY = 0;

	@OriginalMember(owner = "runetek4.client!rh", name = "o", descriptor = "I")
	public static int lastMouseX = 0;

    @OriginalMember(owner = "client!em", name = "y", descriptor = "I")
    public static int mouseClickY = 0;

	@OriginalMember(owner = "client!ah", name = "s", descriptor = "I")
	public static int mouseClickX = 0;

	@OriginalMember(owner = "client!bl", name = "Q", descriptor = "I")
	public static int pressedButton = 0;

    @OriginalMember(owner = "runetek4.client!wl", name = "u", descriptor = "I")
    public static int lastClickY = 0;

	@OriginalMember(owner = "runetek4.client!wi", name = "W", descriptor = "I")
	public static int lastClickX = 0;

	@OriginalMember(owner = "runetek4.client!he", name = "bb", descriptor = "Lclient!ug;")
	public static Mouse instance = new Mouse();

	@OriginalMember(owner = "runetek4.client!he", name = "Y", descriptor = "I")
	public static volatile int anInt2467 = 0;

	@OriginalMember(owner = "runetek4.client!ra", name = "jb", descriptor = "J")
	public static volatile long aLong161 = 0L;

	@OriginalMember(owner = "client!ck", name = "k", descriptor = "I")
	public static volatile int anInt1034 = 0;

	@OriginalMember(owner = "client!eg", name = "w", descriptor = "I")
	public static volatile int anInt1759 = 0;

	@OriginalMember(owner = "runetek4.client!kf", name = "c", descriptor = "J")
	public static long clickTime = 0L;

	@OriginalMember(owner = "client!dc", name = "W", descriptor = "I")
	public static volatile int anInt1313 = 0;

	@OriginalMember(owner = "runetek4.client!nb", name = "j", descriptor = "I")
	public static volatile int currentMouseY = -1;

	@OriginalMember(owner = "runetek4.client!lh", name = "u", descriptor = "I")
	public static volatile int currentMouseX = -1;

	@OriginalMember(owner = "runetek4.client!sa", name = "Y", descriptor = "I")
	public static volatile int anInt4973 = 0;

	@OriginalMember(owner = "runetek4.client!dl", name = "a", descriptor = "(II)V")
	public static void setIdleLoops(@OriginalArg(1) int loops) {
		@Pc(10) Mouse mouse = instance;
		synchronized (instance) {
			anInt2467 = loops;
		}
	}

    @OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(I)V")
    public static void quit() {
        if (instance != null) {
            @Pc(5) Mouse mouse = instance;
            synchronized (instance) {
                instance = null;
            }
        }
    }

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(ILjava/awt/runetek4.Component;)V")
	public static void stop(@OriginalArg(1) Component component) {
		component.removeMouseListener(instance);
		component.removeMouseMotionListener(instance);
		component.removeFocusListener(instance);
		anInt1759 = 0;
	}

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(Ljava/awt/runetek4.Component;Z)V")
	public static void start(@OriginalArg(0) Component component) {
		component.addMouseListener(instance);
		component.addMouseMotionListener(instance);
		component.addFocusListener(instance);
	}

    @OriginalMember(owner = "runetek4.client!ii", name = "b", descriptor = "(I)V")
    public static void loop() {
        @Pc(2) Mouse mouse = instance;
        synchronized (instance) {
            pressedButton = anInt1759;
            lastMouseX = currentMouseX;
            lastMouseY = currentMouseY;
            clickButton = anInt1313;
            mouseClickX = anInt1034;
            anInt2467++;
            mouseClickY = anInt4973;
            clickTime = aLong161;
            anInt1313 = 0;
        }
    }

	@OriginalMember(owner = "runetek4.client!lc", name = "a", descriptor = "(B)I")
	public static int getIdleLoops() {
		return anInt2467;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseMoved", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseMoved(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			currentMouseX = event.getX();
			currentMouseY = event.getY();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final synchronized void focusLost(@OriginalArg(0) FocusEvent event) {
		if (instance != null) {
			anInt1759 = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseDragged", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseDragged(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			currentMouseX = event.getX();
			currentMouseY = event.getY();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseReleased(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			anInt1759 = 0;
			@Pc(14) int local14 = event.getModifiers();
			// TODO why is this here?
			if ((local14 & 0x10) == 0) {
			}
			if ((local14 & 0x4) == 0) {
			}
			if ((local14 & 0x8) == 0) {
			}
		}
		if (event.isPopupTrigger()) {
			event.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseClicked", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseClicked(@OriginalArg(0) MouseEvent event) {
		if (event.isPopupTrigger()) {
			event.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent event) {
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mousePressed(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			anInt1034 = event.getX();
			anInt4973 = event.getY();
			aLong161 = SystemTimer.safetime();
			if ((event.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0) {
				anInt1313 = 1;
				anInt1759 = 1;
			} else {
				anInt1313 = 2;
				anInt1759 = 2;
			}
			@Pc(29) int local29 = event.getModifiers();
			// TODO why is this here?
			if ((local29 & 0x10) == 0) {
			}
			if ((local29 & 0x4) != 0) {
			}
			if ((local29 & 0x8) != 0) {
			}
		}
		if (event.isPopupTrigger()) {
			event.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseExited", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseExited(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			currentMouseX = -1;
			currentMouseY = -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseEntered", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseEntered(@OriginalArg(0) MouseEvent event) {
		if (instance != null) {
			anInt2467 = 0;
			currentMouseX = event.getX();
			currentMouseY = event.getY();
		}
	}
}
