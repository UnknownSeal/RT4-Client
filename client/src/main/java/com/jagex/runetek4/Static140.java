package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.lighttype.LightType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static140 {

	@OriginalMember(owner = "runetek4.client!la", name = "i", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray12;

	@OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(IJ)V")
	public static void addIgnore(@OriginalArg(1) long username) {
		if (username == 0L) {
			return;
		}
		if (IgnoreList.ignoreCount >= 100) {
			Chat.addMessage(JString.EMPTY, 0, LocalizedText.IGNORELISTFULL);
			return;
		}
		@Pc(34) JString displayName = Base37.decode37(username).toTitleCase();
		@Pc(36) int i;
		for (i = 0; i < IgnoreList.ignoreCount; i++) {
			if (IgnoreList.encodedIgnores[i] == username) {
				Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { displayName, LocalizedText.IGNORELISTDUPE}));
				return;
			}
		}
		for (i = 0; i < FriendList.friendCount; i++) {
			if (FriendList.encodedUsernames[i] == username) {
				Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { LocalizedText.REMOVESOCIAL2, displayName, LocalizedText.REMOVEFRIEND}));
				return;
			}
		}
		if (displayName.strEquals(PlayerList.self.username)) {
			Chat.addMessage(JString.EMPTY, 0, LocalizedText.IGNORECANTADDSELF);
			return;
		}
		IgnoreList.encodedIgnores[IgnoreList.ignoreCount] = username;
		Static193.ignoreName[IgnoreList.ignoreCount++] = Base37.decode37(username);
		FriendList.transmitAt = InterfaceList.transmitTimer;
		Protocol.outboundBuffer.pIsaac1(34);
		Protocol.outboundBuffer.p8(username);
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
