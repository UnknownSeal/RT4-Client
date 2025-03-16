package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static64 {

	@OriginalMember(owner = "runetek4.client!fb", name = "n", descriptor = "[[B")
	public static byte[][] aByteArrayArray9;

	@OriginalMember(owner = "runetek4.client!fb", name = "i", descriptor = "Lclient!na;")
	public static final JString MAX_AGE = JString.parse("; Max)2Age=");

	@OriginalMember(owner = "runetek4.client!fb", name = "m", descriptor = "Z")
	public static boolean aBoolean111 = true;

	@OriginalMember(owner = "runetek4.client!fb", name = "p", descriptor = "Lclient!na;")
	public static final JString RED2 = JString.parse("<col=ff3000>");

	@OriginalMember(owner = "runetek4.client!fb", name = "q", descriptor = "[I")
	public static final int[] anIntArray154 = new int[] { -1, -1, 1, 1 };

	@OriginalMember(owner = "runetek4.client!fb", name = "b", descriptor = "(B)V")
	public static void getPlayer() {
		Static116.entityUpdateCount = 0;
		Static240.entityRemovalCount = 0;
		Static17.method527();
		Protocol.readPlayerInfo();
		Static225.getPlayerNewVis();
		Static245.getPlayerExtended();
		@Pc(23) int i;
		for (i = 0; i < Static240.entityRemovalCount; i++) {
			@Pc(30) int index = Static52.entityRemovalIds[i];
			if (client.loop != PlayerList.players[index].cycle) {
				if (PlayerList.players[index].soundRadius > 0) {
					AreaSoundManager.remove(PlayerList.players[index]);
				}
				PlayerList.players[index] = null;
			}
		}
		if (Static223.packetSize != Protocol.inboundBuffer.offset) {
			throw new RuntimeException("gpp1 pos:" + Protocol.inboundBuffer.offset + " psize:" + Static223.packetSize);
		}
		for (i = 0; i < PlayerList.playerCount; i++) {
			if (PlayerList.players[Static105.playerIds[i]] == null) {
				throw new RuntimeException("gpp2 pos:" + i + " size:" + PlayerList.playerCount);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!fb", name = "a", descriptor = "(JB)V")
	public static void addFriend(@OriginalArg(0) long username) {
		if (username == 0L) {
			return;
		}
		if (FriendList.friendCount >= 100 && !Class6.members || FriendList.friendCount >= 200) {
			Chat.addMessage(JString.EMPTY, 0, LocalizedText.FRIENDLISTFULL);
			return;
		}
		@Pc(35) JString displayName = Base37.decode37(username).method3125();
		@Pc(42) int i;
		for (i = 0; i < FriendList.friendCount; i++) {
			if (Static92.friendName37[i] == username) {
				Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLISTDUPE}));
				return;
			}
		}
		for (i = 0; i < Static35.ignoreCount; i++) {
			if (username == Static190.ignoreName37[i]) {
				Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { LocalizedText.REMOVESOCIAL1, displayName, LocalizedText.REMOVEIGNORE}));
				return;
			}
		}
		if (displayName.method3108(PlayerList.self.username)) {
			Chat.addMessage(JString.EMPTY, 0, LocalizedText.FRIENDCANTADDSELF);
			return;
		}
		Static122.friendName[FriendList.friendCount] = displayName;
		Static92.friendName37[FriendList.friendCount] = username;
		Static104.friendWorld[FriendList.friendCount] = 0;
		Static214.aClass100Array170[FriendList.friendCount] = JString.EMPTY;
		Static106.anIntArray258[FriendList.friendCount] = 0;
		Static3.aBooleanArray135[FriendList.friendCount] = false;
		FriendList.friendCount++;
		Static185.anInt4369 = InterfaceList.transmitTimer;
		Protocol.outboundBuffer.pIsaac1(120);
		Protocol.outboundBuffer.p8(username);
	}
}
