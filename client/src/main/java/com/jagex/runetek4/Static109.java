package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static109 {

	@OriginalMember(owner = "runetek4.client!ig", name = "d", descriptor = "I")
	public static int anInt2883;

	@OriginalMember(owner = "runetek4.client!ig", name = "i", descriptor = "I")
	public static int anInt2886;

	@OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(I)V")
	public static void updateNpcs() {
		for (@Pc(7) int i = 0; i < NpcList.npcCount; i++) {
			@Pc(18) int id = Static33.npcIds[i];
			@Pc(22) Npc npc = NpcList.npcs[id];
			if (npc != null) {
				PathingEntity.updateEntity(npc.type.size, npc);
			}
		}
	}

}
