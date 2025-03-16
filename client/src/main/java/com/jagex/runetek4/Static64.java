package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static64 {

	@OriginalMember(owner = "runetek4.client!fb", name = "n", descriptor = "[[B")
	public static byte[][] aByteArrayArray9;

	@OriginalMember(owner = "runetek4.client!fb", name = "i", descriptor = "Lclient!na;")
	public static final JString MAX_AGE = Static28.parse("; Max)2Age=");

	@OriginalMember(owner = "runetek4.client!fb", name = "l", descriptor = "[Lclient!na;")
	public static final JString[] aClass100Array62 = new JString[100];

	@OriginalMember(owner = "runetek4.client!fb", name = "m", descriptor = "Z")
	public static boolean aBoolean111 = true;

	@OriginalMember(owner = "runetek4.client!fb", name = "p", descriptor = "Lclient!na;")
	public static final JString RED2 = Static28.parse("<col=ff3000>");

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
			if (client.loop != Static159.players[index].cycle) {
				if (Static159.players[index].soundRadius > 0) {
					AreaSoundManager.remove(Static159.players[index]);
				}
				Static159.players[index] = null;
			}
		}
		if (Static223.packetSize != Static57.in.offset) {
			throw new RuntimeException("gpp1 pos:" + Static57.in.offset + " psize:" + Static223.packetSize);
		}
		for (i = 0; i < Static267.playerCount; i++) {
			if (Static159.players[Static105.playerIds[i]] == null) {
				throw new RuntimeException("gpp2 pos:" + i + " size:" + Static267.playerCount);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!fb", name = "a", descriptor = "(JB)V")
	public static void addFriend(@OriginalArg(0) long username) {
		if (username == 0L) {
			return;
		}
		if (CacheArchive.friendCount >= 100 && !Class6.members || CacheArchive.friendCount >= 200) {
			Static103.addMessage(Static186.aClass100_827, 0, LocalizedText.FRIENDLISTFULL);
			return;
		}
		@Pc(35) JString displayName = Base37.decode37(username).method3125();
		@Pc(42) int i;
		for (i = 0; i < CacheArchive.friendCount; i++) {
			if (Static92.friendName37[i] == username) {
				Static103.addMessage(Static186.aClass100_827, 0, Static34.method882(new JString[] { displayName, LocalizedText.FRIENDLISTDUPE}));
				return;
			}
		}
		for (i = 0; i < Static35.ignoreCount; i++) {
			if (username == Static190.ignoreName37[i]) {
				Static103.addMessage(Static186.aClass100_827, 0, Static34.method882(new JString[] { LocalizedText.REMOVESOCIAL1, displayName, LocalizedText.REMOVEIGNORE}));
				return;
			}
		}
		if (displayName.method3108(PlayerList.self.username)) {
			Static103.addMessage(Static186.aClass100_827, 0, LocalizedText.FRIENDCANTADDSELF);
			return;
		}
		Static122.friendName[CacheArchive.friendCount] = displayName;
		Static92.friendName37[CacheArchive.friendCount] = username;
		Static104.friendWorld[CacheArchive.friendCount] = 0;
		Static214.aClass100Array170[CacheArchive.friendCount] = Static186.aClass100_827;
		Static106.anIntArray258[CacheArchive.friendCount] = 0;
		Static3.aBooleanArray135[CacheArchive.friendCount] = false;
		CacheArchive.friendCount++;
		Static185.anInt4369 = Static119.transmitTimer;
		Static6.outboundBuffer.pIsaac1(120);
		Static6.outboundBuffer.p8(username);
	}
}
