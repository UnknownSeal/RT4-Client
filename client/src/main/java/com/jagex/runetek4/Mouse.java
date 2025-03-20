package com.jagex.runetek4;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

	@OriginalMember(owner = "runetek4.client!dl", name = "a", descriptor = "(II)V")
	public static void setIdleLoops(@OriginalArg(1) int arg0) {
		@Pc(10) Mouse local10 = Static93.aClass150_1;
		synchronized (Static93.aClass150_1) {
			Static93.anInt2467 = arg0;
		}
	}

    @OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(I)V")
    public static void quit() {
        if (Static93.aClass150_1 != null) {
            @Pc(5) Mouse local5 = Static93.aClass150_1;
            synchronized (Static93.aClass150_1) {
                Static93.aClass150_1 = null;
            }
        }
    }

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(ILjava/awt/runetek4.Component;)V")
	public static void stop(@OriginalArg(1) Component arg0) {
		arg0.removeMouseListener(Static93.aClass150_1);
		arg0.removeMouseMotionListener(Static93.aClass150_1);
		arg0.removeFocusListener(Static93.aClass150_1);
		Static57.anInt1759 = 0;
	}

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(Ljava/awt/runetek4.Component;Z)V")
	public static void start(@OriginalArg(0) Component arg0) {
		arg0.addMouseListener(Static93.aClass150_1);
		arg0.addMouseMotionListener(Static93.aClass150_1);
		arg0.addFocusListener(Static93.aClass150_1);
	}

    @OriginalMember(owner = "runetek4.client!ii", name = "b", descriptor = "(I)V")
    public static void loop() {
        @Pc(2) Mouse local2 = Static93.aClass150_1;
        synchronized (Static93.aClass150_1) {
            pressedButton = Static57.anInt1759;
            lastMouseX = Static147.anInt3521;
            lastMouseY = Static165.anInt4039;
            clickButton = Static41.anInt1313;
            mouseClickX = Static34.anInt1034;
            Static93.anInt2467++;
            mouseClickY = Static222.anInt4973;
            Static133.clickTime = Static209.aLong161;
            Static41.anInt1313 = 0;
        }
    }

    @OriginalMember(owner = "runetek4.client!ug", name = "mouseMoved", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseMoved(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static147.anInt3521 = arg0.getX();
			Static165.anInt4039 = arg0.getY();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final synchronized void focusLost(@OriginalArg(0) FocusEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static57.anInt1759 = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseDragged", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseDragged(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static147.anInt3521 = arg0.getX();
			Static165.anInt4039 = arg0.getY();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseReleased(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static57.anInt1759 = 0;
			@Pc(14) int local14 = arg0.getModifiers();
			if ((local14 & 0x10) == 0) {
			}
			if ((local14 & 0x4) == 0) {
			}
			if ((local14 & 0x8) == 0) {
			}
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseClicked", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseClicked(@OriginalArg(0) MouseEvent arg0) {
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mousePressed(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static34.anInt1034 = arg0.getX();
			Static222.anInt4973 = arg0.getY();
			Static209.aLong161 = MonotonicTime.currentTimeMillis();
			if ((arg0.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0) {
				Static41.anInt1313 = 1;
				Static57.anInt1759 = 1;
			} else {
				Static41.anInt1313 = 2;
				Static57.anInt1759 = 2;
			}
			@Pc(29) int local29 = arg0.getModifiers();
			if ((local29 & 0x10) == 0) {
			}
			if ((local29 & 0x4) != 0) {
			}
			if ((local29 & 0x8) != 0) {
			}
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseExited", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseExited(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static147.anInt3521 = -1;
			Static165.anInt4039 = -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "mouseEntered", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseEntered(@OriginalArg(0) MouseEvent arg0) {
		if (Static93.aClass150_1 != null) {
			Static93.anInt2467 = 0;
			Static147.anInt3521 = arg0.getX();
			Static165.anInt4039 = arg0.getY();
		}
	}
}
