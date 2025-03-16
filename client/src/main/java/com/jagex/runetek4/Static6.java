package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.input.Keyboard;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static6 {

	@OriginalMember(owner = "client!ag", name = "P", descriptor = "Lclient!i;")
	public static final PacketBit outboundBuffer = new PacketBit(5000);

	@OriginalMember(owner = "client!ag", name = "ab", descriptor = "Lclient!na;")
	public static final JString aClass100_17 = Static28.parse("0");

	@OriginalMember(owner = "client!ag", name = "bb", descriptor = "Lclient!na;")
	public static final JString GREEN2 = Static28.parse("<col=40ff00>");

	@OriginalMember(owner = "client!ag", name = "cb", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array1 = new AnimFrameset[14];

	@OriginalMember(owner = "client!ag", name = "h", descriptor = "(I)V")
	public static void method82() {
		if (Static10.aClass149_1 != null) {
			@Pc(4) Keyboard local4 = Static10.aClass149_1;
			synchronized (Static10.aClass149_1) {
				Static10.aClass149_1 = null;
			}
		}
	}

	@OriginalMember(owner = "client!ag", name = "j", descriptor = "(I)V")
	public static void method84() {
		SceneGraph.clear();
		for (@Pc(9) int local9 = 0; local9 < 4; local9++) {
			PathFinder.collisionMaps[local9].reset();
		}
		System.gc();
	}

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(ILjava/lang/String;)Ljava/lang/Class;")
	public static Class method85(@OriginalArg(1) String arg0) throws ClassNotFoundException {
		if (arg0.equals("B")) {
			return Byte.TYPE;
		} else if (arg0.equals("I")) {
			return Integer.TYPE;
		} else if (arg0.equals("S")) {
			return Short.TYPE;
		} else if (arg0.equals("J")) {
			return Long.TYPE;
		} else if (arg0.equals("Z")) {
			return Boolean.TYPE;
		} else if (arg0.equals("F")) {
			return Float.TYPE;
		} else if (arg0.equals("D")) {
			return Double.TYPE;
		} else if (arg0.equals("C")) {
			return Character.TYPE;
		} else {
			return Class.forName(arg0);
		}
	}

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(IIIIIIIII)V")
	public static void method86(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		if (Component.load(arg0)) {
			Static87.drawGame(arg1, arg7, arg3, Component.cachedComponents[arg0], arg2, -1, arg6, arg5, arg4);
		} else if (arg4 == -1) {
			for (@Pc(27) int local27 = 0; local27 < 100; local27++) {
				Static186.aBooleanArray100[local27] = true;
			}
		} else {
			Static186.aBooleanArray100[arg4] = true;
		}
	}
}
