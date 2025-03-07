package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.lighttype.LightType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static140 {

	@OriginalMember(owner = "runetek4.client!la", name = "i", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray12;

	@OriginalMember(owner = "runetek4.client!la", name = "f", descriptor = "Lclient!ce;")
	public static final SecondaryLinkedList aClass16_7 = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(Lclient!wa;Z)V")
	public static void method2705(@OriginalArg(0) Packet arg0) {
		@Pc(15) byte[] local15 = new byte[24];
		if (client.uid != null) {
			try {
				client.uid.method1459(0L);
				client.uid.method1457(local15);
				@Pc(28) int local28;
				for (local28 = 0; local28 < 24 && local15[local28] == 0; local28++) {
				}
				if (local28 >= 24) {
					throw new IOException();
				}
			} catch (@Pc(55) Exception local55) {
				for (@Pc(57) int local57 = 0; local57 < 24; local57++) {
					local15[local57] = -1;
				}
			}
		}
		arg0.pdata(local15, 24);
	}

	@OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(IJ)V")
	public static void method2707(@OriginalArg(1) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		if (Static35.size >= 100) {
			Static103.method2231(Static186.aClass100_827, 0, LocalizedText.IGNORELISTFULL);
			return;
		}
		@Pc(34) JagString local34 = Static79.decode37(arg0).method3125();
		@Pc(36) int local36;
		for (local36 = 0; local36 < Static35.size; local36++) {
			if (Static190.encodedUsernames[local36] == arg0) {
				Static103.method2231(Static186.aClass100_827, 0, Static34.method882(new JagString[] { local34, LocalizedText.IGNORELISTDUPE}));
				return;
			}
		}
		for (local36 = 0; local36 < Static9.anInt178; local36++) {
			if (Static92.aLongArray3[local36] == arg0) {
				Static103.method2231(Static186.aClass100_827, 0, Static34.method882(new JagString[] { LocalizedText.REMOVESOCIAL2, local34, LocalizedText.REMOVEFRIEND}));
				return;
			}
		}
		if (local34.method3108(Static173.self.username)) {
			Static103.method2231(Static186.aClass100_827, 0, LocalizedText.IGNORECANTADDSELF);
			return;
		}
		Static190.encodedUsernames[Static35.size] = arg0;
		Static193.aClass100Array134[Static35.size++] = Static79.decode37(arg0);
		Static185.anInt4369 = Static119.transmitTimer;
		Static6.outboundBuffer.pIsaac1(34);
		Static6.outboundBuffer.p8(arg0);
	}

	@OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(II)Lclient!ic;")
	public static LightType method2709(@OriginalArg(1) int arg0) {
		@Pc(10) LightType local10 = (LightType) Static220.aClass99_28.get((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(26) byte[] local26 = Static85.aClass153_36.getfile(31, arg0);
		local10 = new LightType();
		if (local26 != null) {
			local10.decode(new Packet(local26));
		}
		Static220.aClass99_28.method3095(local10, (long) arg0);
		return local10;
	}

}
