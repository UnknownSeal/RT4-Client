package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static64 {

	@OriginalMember(owner = "client!fb", name = "n", descriptor = "[[B")
	public static byte[][] aByteArrayArray9;

	@OriginalMember(owner = "client!fb", name = "p", descriptor = "Lclient!na;")
	public static final JString RED2 = JString.parse("<col=ff3000>");

	@OriginalMember(owner = "client!fb", name = "q", descriptor = "[I")
	public static final int[] anIntArray154 = new int[] { -1, -1, 1, 1 };

	@OriginalMember(owner = "client!fb", name = "b", descriptor = "(B)V")
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
		if (Protocol.packetSize != Protocol.inboundBuffer.offset) {
			throw new RuntimeException("gpp1 pos:" + Protocol.inboundBuffer.offset + " psize:" + Protocol.packetSize);
		}
		for (i = 0; i < PlayerList.playerCount; i++) {
			if (PlayerList.players[PlayerList.playerIds[i]] == null) {
				throw new RuntimeException("gpp2 pos:" + i + " size:" + PlayerList.playerCount);
			}
		}
	}

}
