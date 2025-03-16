package com.jagex.runetek4.input;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

import com.jagex.runetek4.*;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!uf")
public final class Keyboard implements KeyListener, FocusListener {

	public static final int KEY_SHIFT = 81;
	public static final int KEY_CTRL = 82;
	public static final int KEY_ENTER = 84;
	public static final int KEY_BACK_SPACE = 85;
	public static final int KEY_ALT = 86;
	public static final int KEY_LEFT = 96;
	public static final int KEY_RIGHT = 97;
	public static final int KEY_UP = 98;
	public static final int KEY_DOWN = 99;
	@OriginalMember(owner = "runetek4.client!hn", name = "Z", descriptor = "I")
	public static int keyCode;

	@OriginalMember(owner = "client!ch", name = "a", descriptor = "(Ljava/awt/Component;I)V")
	public static void stop(@OriginalArg(0) Component arg0) {
		arg0.removeKeyListener(Static10.aClass149_1);
		arg0.removeFocusListener(Static10.aClass149_1);
		Static114.anInt5844 = -1;
	}

	@OriginalMember(owner = "client!ag", name = "h", descriptor = "(I)V")
    public static void quit() {
        if (Static10.aClass149_1 != null) {
            @Pc(4) Keyboard local4 = Static10.aClass149_1;
            synchronized (Static10.aClass149_1) {
                Static10.aClass149_1 = null;
            }
        }
    }

	@OriginalMember(owner = "runetek4.client!mf", name = "e", descriptor = "(I)V")
	public static void init() {
		if (SignLink.javaVendor.toLowerCase().indexOf("microsoft") != -1) {
			Static196.anIntArray407[187] = 27;
			Static196.anIntArray407[223] = 28;
			Static196.anIntArray407[221] = 43;
			Static196.anIntArray407[188] = 71;
			Static196.anIntArray407[222] = 59;
			Static196.anIntArray407[192] = 58;
			Static196.anIntArray407[191] = 73;
			Static196.anIntArray407[219] = 42;
			Static196.anIntArray407[190] = 72;
			Static196.anIntArray407[186] = 57;
			Static196.anIntArray407[220] = 74;
			Static196.anIntArray407[189] = 26;
			return;
		}
		if (SignLink.setFocusTraversalKeysEnabled == null) {
			Static196.anIntArray407[192] = 58;
			Static196.anIntArray407[222] = 59;
		} else {
			Static196.anIntArray407[222] = 58;
			Static196.anIntArray407[192] = 28;
			Static196.anIntArray407[520] = 59;
		}
		Static196.anIntArray407[45] = 26;
		Static196.anIntArray407[61] = 27;
		Static196.anIntArray407[91] = 42;
		Static196.anIntArray407[59] = 57;
		Static196.anIntArray407[93] = 43;
		Static196.anIntArray407[44] = 71;
		Static196.anIntArray407[92] = 74;
		Static196.anIntArray407[46] = 72;
		Static196.anIntArray407[47] = 73;
	}

	@OriginalMember(owner = "client!bi", name = "a", descriptor = "(BLjava/awt/runetek4.Component;)V")
	public static void start(@OriginalArg(1) Component arg0) {
		@Pc(10) Method local10 = SignLink.setFocusTraversalKeysEnabled;
		if (local10 != null) {
			try {
				local10.invoke(arg0, Boolean.FALSE);
			} catch (@Pc(25) Throwable local25) {
			}
		}
		arg0.addKeyListener(Static10.aClass149_1);
		arg0.addFocusListener(Static10.aClass149_1);
	}

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(I)Z")
	public static boolean nextKey() {
		@Pc(6) Keyboard local6 = Static10.aClass149_1;
		synchronized (Static10.aClass149_1) {
			if (Static228.anInt5105 == Static102.anInt2678) {
				return false;
			} else {
				keyCode = BZip2State.anIntArray375[Static102.anInt2678];
				Static193.keyChar = Static264.anIntArray413[Static102.anInt2678];
				Static102.anInt2678 = Static102.anInt2678 + 1 & 0x7F;
				return true;
			}
		}
	}

	@OriginalMember(owner = "client!uf", name = "keyPressed", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public synchronized void keyPressed(@OriginalArg(0) KeyEvent arg0) {
		if (Static10.aClass149_1 == null) {
			return;
		}
		Static229.anInt5140 = 0;
		@Pc(7) int local7 = arg0.getKeyCode();
		if (local7 >= 0 && Static196.anIntArray407.length > local7) {
			local7 = Static196.anIntArray407[local7];
			if ((local7 & 0x80) != 0) {
				local7 = -1;
			}
		} else {
			local7 = -1;
		}
		if (Static114.anInt5844 >= 0 && local7 >= 0) {
			Static17.anIntArray53[Static114.anInt5844] = local7;
			Static114.anInt5844 = Static114.anInt5844 + 1 & 0x7F;
			if (Static114.anInt5844 == Static227.anInt5087) {
				Static114.anInt5844 = -1;
			}
		}
		@Pc(68) int local68;
		if (local7 >= 0) {
			local68 = Static53.anInt1708 + 1 & 0x7F;
			if (local68 != Static102.anInt2678) {
				BZip2State.anIntArray375[Static53.anInt1708] = local7;
				Static264.anIntArray413[Static53.anInt1708] = -1;
				Static53.anInt1708 = local68;
			}
		}
		local68 = arg0.getModifiers();
		if ((local68 & 0xA) != 0 || local7 == 85 || local7 == 10) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "client!uf", name = "keyTyped", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public void keyTyped(@OriginalArg(0) KeyEvent arg0) {
		if (Static10.aClass149_1 != null) {
			@Pc(9) int local9 = Static136.method2650(arg0);
			if (local9 >= 0) {
				@Pc(21) int local21 = Static53.anInt1708 + 1 & 0x7F;
				if (Static102.anInt2678 != local21) {
					BZip2State.anIntArray375[Static53.anInt1708] = -1;
					Static264.anIntArray413[Static53.anInt1708] = local9;
					Static53.anInt1708 = local21;
				}
			}
		}
		arg0.consume();
	}

	@OriginalMember(owner = "client!uf", name = "keyReleased", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public synchronized void keyReleased(@OriginalArg(0) KeyEvent arg0) {
		if (Static10.aClass149_1 != null) {
			Static229.anInt5140 = 0;
			@Pc(11) int local11 = arg0.getKeyCode();
			if (local11 >= 0 && Static196.anIntArray407.length > local11) {
				local11 = Static196.anIntArray407[local11] & 0xFFFFFF7F;
			} else {
				local11 = -1;
			}
			if (Static114.anInt5844 >= 0 && local11 >= 0) {
				Static17.anIntArray53[Static114.anInt5844] = ~local11;
				Static114.anInt5844 = Static114.anInt5844 + 1 & 0x7F;
				if (Static227.anInt5087 == Static114.anInt5844) {
					Static114.anInt5844 = -1;
				}
			}
		}
		arg0.consume();
	}

	@OriginalMember(owner = "client!uf", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public void focusGained(@OriginalArg(0) FocusEvent focusEvent) {
	}

	@OriginalMember(owner = "client!uf", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public synchronized void focusLost(@OriginalArg(0) FocusEvent focusEvent) {
		if (Static10.aClass149_1 != null) {
			Static114.anInt5844 = -1;
		}
	}
}
