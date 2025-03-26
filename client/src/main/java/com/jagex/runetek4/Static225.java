package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static225 {

	@OriginalMember(owner = "runetek4.client!se", name = "l", descriptor = "Lclient!ve;")
	public static Js5 aClass153_92;

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(I)V")
	public static void getPlayerNewVis() {
		while (true) {
			if (Protocol.inboundBuffer.bitsAvailable(Protocol.packetSize) >= 11) {
				@Pc(20) int index = Protocol.inboundBuffer.gBit(11);
				if (index != 2047) {
					@Pc(27) boolean local27 = false;
					if (PlayerList.players[index] == null) {
						PlayerList.players[index] = new Player();
						local27 = true;
						if (PlayerList.appearanceCache[index] != null) {
							PlayerList.players[index].decodeAppearance(PlayerList.appearanceCache[index]);
						}
					}
					PlayerList.playerIds[PlayerList.playerCount++] = index;
					@Pc(65) Player player = PlayerList.players[index];
					player.cycle = client.loop;
					@Pc(73) int local73 = Protocol.inboundBuffer.gBit(1);
					if (local73 == 1) {
						Static44.entityUpdateIds[Static116.entityUpdateCount++] = index;
					}
					@Pc(92) int dx = Protocol.inboundBuffer.gBit(5);
					@Pc(99) int local99 = PathingEntity.ANGLES[Protocol.inboundBuffer.gBit(3)];
					if (dx > 15) {
						dx -= 32;
					}
					if (local27) {
						player.dstYaw = player.anInt3381 = local99;
					}
					@Pc(116) int jump = Protocol.inboundBuffer.gBit(1);
					@Pc(121) int dz = Protocol.inboundBuffer.gBit(5);
					if (dz > 15) {
						dz -= 32;
					}
					player.teleport(dx + PlayerList.self.movementQueueX[0], jump == 1, PlayerList.self.movementQueueZ[0] + dz);
					continue;
				}
			}
			Protocol.inboundBuffer.accessBytes();
			return;
		}
	}

}
