package com.jagex.runetek4;

import com.jagex.runetek4.entity.NPCEntity;
import org.openrs2.deob.annotation.OriginalArg;
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

	@OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(BI)V")
	public static void method2275(@OriginalArg(1) int arg0) {
		if (arg0 == -1 || !Static223.aBooleanArray115[arg0]) {
			return;
		}
		Static202.aClass153_84.method4490(arg0);
		if (Static241.components[arg0] == null) {
			return;
		}
		@Pc(27) boolean local27 = true;
		for (@Pc(29) int local29 = 0; local29 < Static241.components[arg0].length; local29++) {
			if (Static241.components[arg0][local29] != null) {
				if (Static241.components[arg0][local29].anInt452 == 2) {
					local27 = false;
				} else {
					Static241.components[arg0][local29] = null;
				}
			}
		}
		if (local27) {
			Static241.components[arg0] = null;
		}
		Static223.aBooleanArray115[arg0] = false;
	}
}
