package com.jagex.runetek4;

import java.io.IOException;
import java.net.Socket;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.NPCType;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static158 {

	@OriginalMember(owner = "runetek4.client!mh", name = "S", descriptor = "I")
	public static int anInt3846;

	@OriginalMember(owner = "runetek4.client!mh", name = "hb", descriptor = "Lclient!bn;")
	public static Map aClass3_Sub2_Sub4_3;

	@OriginalMember(owner = "runetek4.client!mh", name = "X", descriptor = "I")
	public static int anInt3851 = -1;

	@OriginalMember(owner = "runetek4.client!mh", name = "Y", descriptor = "Z")
	public static boolean aBoolean187 = false;

	@OriginalMember(owner = "runetek4.client!mh", name = "eb", descriptor = "I")
	public static int anInt3857 = 0;

	@OriginalMember(owner = "runetek4.client!mh", name = "f", descriptor = "(B)V")
	public static void loop() {
		if (Static179.step == 0) {
			return;
		}
		try {
			if (++Static226.loops > 2000) {
				if (Static124.socket != null) {
					Static124.socket.closeGracefully();
					Static124.socket = null;
				}
				if (Static57.errors >= 1) {
					Static223.reply = -5;
					Static179.step = 0;
					return;
				}
				Static179.step = 1;
				Static226.loops = 0;
				Static57.errors++;
				if (Static271.defaultPort == Static209.port) {
					Static209.port = Static55.alternatePort;
				} else {
					Static209.port = Static271.defaultPort;
				}
			}
			if (Static179.step == 1) {
				Static72.aClass212_3 = GameShell.signLink.openSocket(Static60.hostname, Static209.port);
				Static179.step = 2;
			}
			@Pc(120) int local120;
			if (Static179.step == 2) {
				if (Static72.aClass212_3.status == 2) {
					throw new IOException();
				}
				if (Static72.aClass212_3.status != 1) {
					return;
				}
				Static124.socket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
				Static72.aClass212_3 = null;
				Static124.socket.write(Static6.outboundBuffer.data, Static6.outboundBuffer.pos);
				if (Static11.aClass62_1 != null) {
					Static11.aClass62_1.method3571();
				}
				if (Static147.aClass62_2 != null) {
					Static147.aClass62_2.method3571();
				}
				local120 = Static124.socket.read();
				if (Static11.aClass62_1 != null) {
					Static11.aClass62_1.method3571();
				}
				if (Static147.aClass62_2 != null) {
					Static147.aClass62_2.method3571();
				}
				if (local120 != 21) {
					Static223.reply = local120;
					Static179.step = 0;
					Static124.socket.closeGracefully();
					Static124.socket = null;
					return;
				}
				Static179.step = 3;
			}
			if (Static179.step == 3) {
				if (Static124.socket.available() < 1) {
					return;
				}
				Static229.aClass100Array156 = new JagString[Static124.socket.read()];
				Static179.step = 4;
			}
			if (Static179.step == 4) {
				if (Static124.socket.available() < Static229.aClass100Array156.length * 8) {
					return;
				}
				Static57.in.pos = 0;
				Static124.socket.read(0, Static229.aClass100Array156.length * 8, Static57.in.data);
				for (local120 = 0; local120 < Static229.aClass100Array156.length; local120++) {
					Static229.aClass100Array156[local120] = Static79.decode37(Static57.in.g8());
				}
				Static223.reply = 21;
				Static179.step = 0;
				Static124.socket.closeGracefully();
				Static124.socket = null;
				return;
			}
		} catch (@Pc(238) IOException ioException) {
			if (Static124.socket != null) {
				Static124.socket.closeGracefully();
				Static124.socket = null;
			}
			if (Static57.errors < 1) {
				Static57.errors++;
				if (Static271.defaultPort == Static209.port) {
					Static209.port = Static55.alternatePort;
				} else {
					Static209.port = Static271.defaultPort;
				}
				Static226.loops = 0;
				Static179.step = 1;
			} else {
				Static223.reply = -4;
				Static179.step = 0;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!mh", name = "c", descriptor = "(II)V")
	public static void method3010() {
		Static110.aClass99_15.method3102(5);
	}

	@OriginalMember(owner = "runetek4.client!mh", name = "h", descriptor = "(B)V")
	public static void method3013() {
		@Pc(10) int local10 = Static191.aByteArrayArray15.length;
		for (@Pc(16) int local16 = 0; local16 < local10; local16++) {
			if (Static191.aByteArrayArray15[local16] != null) {
				@Pc(25) int local25 = -1;
				for (@Pc(27) int local27 = 0; local27 < Static157.anInt3811; local27++) {
					if (Static217.anIntArray434[local27] == Static238.anIntArray470[local16]) {
						local25 = local27;
						break;
					}
				}
				if (local25 == -1) {
					Static217.anIntArray434[Static157.anInt3811] = Static238.anIntArray470[local16];
					local25 = Static157.anInt3811++;
				}
				@Pc(67) int local67 = 0;
				@Pc(74) Packet local74 = new Packet(Static191.aByteArrayArray15[local16]);
				while (local74.pos < Static191.aByteArrayArray15[local16].length && local67 < 511) {
					@Pc(97) int local97 = local67++ << 6 | local25;
					@Pc(103) int local103 = local74.g2();
					@Pc(107) int local107 = local103 >> 14;
					@Pc(113) int local113 = local103 >> 7 & 0x3F;
					@Pc(125) int local125 = local113 + (Static238.anIntArray470[local16] >> 8) * 64 - Static225.originX;
					@Pc(129) int local129 = local103 & 0x3F;
					@Pc(142) int local142 = local129 + (Static238.anIntArray470[local16] & 0xFF) * 64 - Static142.originZ;
					@Pc(148) NPCType local148 = Static214.get(local74.g2());
					if (Static175.npcs[local97] == null && (local148.walkflags & 0x1) > 0 && local107 == Static41.anInt1316 && local125 >= 0 && local148.size + local125 < 104 && local142 >= 0 && local142 + local148.size < 104) {
						Static175.npcs[local97] = new NPCEntity();
						@Pc(198) NPCEntity local198 = Static175.npcs[local97];
						Static33.npcIds[Static272.npcCount++] = local97;
						local198.cycle = Static83.loopCycle;
						local198.method2698(local148);
						local198.setSize(local198.type.size);
						local198.dstYaw = local198.anInt3381 = Static56.anIntArray141[local198.type.respawndir];
						local198.anInt3376 = local198.type.turnspeed;
						if (local198.anInt3376 == 0) {
							local198.anInt3381 = 0;
						}
						local198.anInt3365 = local198.type.bas;
						local198.teleport(local198.size(), local125, local142, true);
					}
				}
			}
		}
	}
}
