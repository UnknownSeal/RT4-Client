package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static225 {

	@OriginalMember(owner = "runetek4.client!se", name = "l", descriptor = "Lclient!ve;")
	public static Js5 aClass153_92;

	@OriginalMember(owner = "runetek4.client!se", name = "h", descriptor = "I")
	public static int anInt5073 = -1;

	@OriginalMember(owner = "runetek4.client!se", name = "t", descriptor = "[I")
	public static final int[] anIntArray445 = new int[] { 12543016, 15504954, 15914854, 16773818 };

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(I)V")
	public static void getPlayerNewVis() {
		while (true) {
			if (Protocol.inboundBuffer.bitsAvailable(Static223.packetSize) >= 11) {
				@Pc(20) int index = Protocol.inboundBuffer.gBit(11);
				if (index != 2047) {
					@Pc(27) boolean local27 = false;
					if (PlayerList.players[index] == null) {
						PlayerList.players[index] = new Player();
						local27 = true;
						if (PlayerList.PLAYER_APPEARANCE_PACKET[index] != null) {
							PlayerList.players[index].read(PlayerList.PLAYER_APPEARANCE_PACKET[index]);
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

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(IIIIZ)I")
	public static int method3891(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(8) int local8 = arg3 & 0xF;
		@Pc(29) int local29 = local8 >= 4 ? (local8 == 12 || local8 == 14 ? arg0 : arg1) : arg2;
		@Pc(42) int local42 = local8 < 8 ? arg0 : arg2;
		return ((local8 & 0x1) == 0 ? local42 : -local42) + ((local8 & 0x2) == 0 ? local29 : -local29);
	}

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(III)V")
	public static void method3893(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(5, arg0);
		local14.method1017();
		local14.intArg1 = arg1;
	}

}
