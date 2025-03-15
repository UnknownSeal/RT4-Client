package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.cache.CacheArchive;
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
				client.uid.setReadIndex(0L);
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
	public static void addIgnore(@OriginalArg(1) long username) {
		if (username == 0L) {
			return;
		}
		if (Static35.ignoreCount >= 100) {
			Static103.addMessage(Static186.aClass100_827, 0, LocalizedText.IGNORELISTFULL);
			return;
		}
		@Pc(34) JString displayName = Static79.decode37(username).method3125();
		@Pc(36) int i;
		for (i = 0; i < Static35.ignoreCount; i++) {
			if (Static190.ignoreName37[i] == username) {
				Static103.addMessage(Static186.aClass100_827, 0, Static34.method882(new JString[] { displayName, LocalizedText.IGNORELISTDUPE}));
				return;
			}
		}
		for (i = 0; i < CacheArchive.friendCount; i++) {
			if (Static92.friendName37[i] == username) {
				Static103.addMessage(Static186.aClass100_827, 0, Static34.method882(new JString[] { LocalizedText.REMOVESOCIAL2, displayName, LocalizedText.REMOVEFRIEND}));
				return;
			}
		}
		if (displayName.method3108(Static173.localPlayer.name)) {
			Static103.addMessage(Static186.aClass100_827, 0, LocalizedText.IGNORECANTADDSELF);
			return;
		}
		Static190.ignoreName37[Static35.ignoreCount] = username;
		Static193.ignoreName[Static35.ignoreCount++] = Static79.decode37(username);
		Static185.anInt4369 = Static119.transmitTimer;
		Static6.outboundBuffer.pIsaac1(34);
		Static6.outboundBuffer.p8(username);
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
		Static220.aClass99_28.put(local10, (long) arg0);
		return local10;
	}

}
