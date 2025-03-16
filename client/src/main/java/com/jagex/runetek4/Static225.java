package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static225 {

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "I")
	public static int originX;

	@OriginalMember(owner = "runetek4.client!se", name = "l", descriptor = "Lclient!ve;")
	public static Js5 aClass153_92;

	@OriginalMember(owner = "runetek4.client!se", name = "h", descriptor = "I")
	public static int anInt5073 = -1;

	@OriginalMember(owner = "runetek4.client!se", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_961 = Static28.parse(" )2>");

	@OriginalMember(owner = "runetek4.client!se", name = "t", descriptor = "[I")
	public static final int[] anIntArray445 = new int[] { 12543016, 15504954, 15914854, 16773818 };

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(I)V")
	public static void getPlayerNewVis() {
		while (true) {
			if (Static57.in.bitsAvailable(Static223.packetSize) >= 11) {
				@Pc(20) int index = Static57.in.gBit(11);
				if (index != 2047) {
					@Pc(27) boolean local27 = false;
					if (Static159.players[index] == null) {
						Static159.players[index] = new Player();
						local27 = true;
						if (Static115.playerAppearanceBuffer[index] != null) {
							Static159.players[index].read(Static115.playerAppearanceBuffer[index]);
						}
					}
					Static105.playerIds[Static267.playerCount++] = index;
					@Pc(65) Player player = Static159.players[index];
					player.cycle = Static83.loopCycle;
					@Pc(73) int local73 = Static57.in.gBit(1);
					if (local73 == 1) {
						Static44.entityUpdateIds[Static116.entityUpdateCount++] = index;
					}
					@Pc(92) int dx = Static57.in.gBit(5);
					@Pc(99) int local99 = Static56.anIntArray141[Static57.in.gBit(3)];
					if (dx > 15) {
						dx -= 32;
					}
					if (local27) {
						player.dstYaw = player.anInt3381 = local99;
					}
					@Pc(116) int jump = Static57.in.gBit(1);
					@Pc(121) int dz = Static57.in.gBit(5);
					if (dz > 15) {
						dz -= 32;
					}
					player.teleport(dx + Static173.localPlayer.pathTileX[0], jump == 1, Static173.localPlayer.pathTileZ[0] + dz);
					continue;
				}
			}
			Static57.in.accessBytes();
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

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
	public static void method3896(@OriginalArg(0) JString arg0, @OriginalArg(1) JString arg1, @OriginalArg(2) int arg2) {
		Static186.password = arg1;
		Static5.anInt39 = arg2;
		Static186.username = arg0;
		if (Static186.username.method3108(Static186.aClass100_827) || Static186.password.method3108(Static186.aClass100_827)) {
			Static266.anInt5336 = 3;
		} else if (Static125.worldId == -1) {
			Static20.anInt673 = 0;
			Static196.anInt4587 = 0;
			Static266.anInt5336 = -3;
			Static219.anInt4937 = 1;
			@Pc(43) Packet local43 = new Packet(128);
			local43.p1(10);
			local43.p2((int) (Math.random() * 99999.0D));
			local43.p2(530);
			local43.p8(Static186.username.encode37());
			local43.p4((int) (Math.random() * 9.9999999E7D));
			local43.pjstr(Static186.password);
			local43.p4((int) (Math.random() * 9.9999999E7D));
			local43.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
			Static6.outboundBuffer.position = 0;
			Static6.outboundBuffer.p1(210);
			Static6.outboundBuffer.p1(local43.position);
			Static6.outboundBuffer.pdata(local43.data, local43.position);
		} else {
			Static49.method1208();
		}
	}
}
