package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.game.shared.framework.gwc.World;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static97 {

	@OriginalMember(owner = "runetek4.client!hi", name = "f", descriptor = "J")
	public static long aLong89 = 0L;

	@OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1962(@OriginalArg(0) Packet packet) {
		@Pc(9) int local9 = packet.gSmart1or2();
		Static203.aGWCLocationArray1 = new GWCLocation[local9];
		for (int index = 0; index < local9; index++) {
			Static203.aGWCLocationArray1[index] = new GWCLocation();
			Static203.aGWCLocationArray1[index].flag = packet.gSmart1or2();
			Static203.aGWCLocationArray1[index].name = packet.gjstr2();
		}
		WorldList.minId = packet.gSmart1or2();
		WorldList.maxId = packet.gSmart1or2();
		Static106.anInt2871 = packet.gSmart1or2();
		WorldList.worlds = new World[WorldList.maxId + 1 - WorldList.minId];
		for (int index = 0; index < Static106.anInt2871; index++) {
			@Pc(77) int local77 = packet.gSmart1or2();
			@Pc(85) World local85 = WorldList.worlds[local77] = new World();
			local85.country = packet.g1();
			local85.flags = packet.g4();
			local85.id = local77 + WorldList.minId;
			local85.activity = packet.gjstr2();
			local85.hostname = packet.gjstr2();
		}
		Static80.anInt4702 = packet.g4();
		WorldList.loaded = true;
	}

}
