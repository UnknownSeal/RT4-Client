package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.dash3d.entity.Npc;
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

	@OriginalMember(owner = "runetek4.client!mh", name = "c", descriptor = "(II)V")
	public static void method3010() {
		Static110.aClass99_15.clear(5);
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
				while (local74.position < Static191.aByteArrayArray15[local16].length && local67 < 511) {
					@Pc(97) int local97 = local67++ << 6 | local25;
					@Pc(103) int local103 = local74.g2();
					@Pc(107) int local107 = local103 >> 14;
					@Pc(113) int local113 = local103 >> 7 & 0x3F;
					@Pc(125) int local125 = local113 + (Static238.anIntArray470[local16] >> 8) * 64 - Static225.originX;
					@Pc(129) int local129 = local103 & 0x3F;
					@Pc(142) int local142 = local129 + (Static238.anIntArray470[local16] & 0xFF) * 64 - Static142.originZ;
					@Pc(148) NpcType local148 = NpcType.getDefinition(local74.g2());
					if (NpcList.npcs[local97] == null && (local148.walkflags & 0x1) > 0 && local107 == Static41.anInt1316 && local125 >= 0 && local148.size + local125 < 104 && local142 >= 0 && local142 + local148.size < 104) {
						NpcList.npcs[local97] = new Npc();
						@Pc(198) Npc local198 = NpcList.npcs[local97];
						Static33.npcIds[Static272.npcCount++] = local97;
						local198.cycle = client.loop;
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
