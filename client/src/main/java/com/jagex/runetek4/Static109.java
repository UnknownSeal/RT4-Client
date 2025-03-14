package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.NPCEntity;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static109 {

	@OriginalMember(owner = "runetek4.client!ig", name = "b", descriptor = "I")
	public static int anInt2882;

	@OriginalMember(owner = "runetek4.client!ig", name = "d", descriptor = "I")
	public static int anInt2883;

	@OriginalMember(owner = "runetek4.client!ig", name = "f", descriptor = "I")
	public static int anInt2884;

	@OriginalMember(owner = "runetek4.client!ig", name = "i", descriptor = "I")
	public static int anInt2886;

	@OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(I)V")
	public static void updateNpcs() {
		for (@Pc(7) int i = 0; i < Static272.npcCount; i++) {
			@Pc(18) int id = Static33.npcIds[i];
			@Pc(22) NPCEntity npc = Static175.npcs[id];
			if (npc != null) {
				Static263.updateEntity(npc.type.size, npc);
			}
		}
	}

}
