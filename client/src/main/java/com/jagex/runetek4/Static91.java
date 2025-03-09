package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.Entity;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static91 {

	@OriginalMember(owner = "runetek4.client!hc", name = "O", descriptor = "[Lclient!pe;")
	public static Class120[] aClass120Array1;

	@OriginalMember(owner = "runetek4.client!hc", name = "P", descriptor = "I")
	public static int anInt2428;

	@OriginalMember(owner = "runetek4.client!hc", name = "d", descriptor = "(I)I")
	public static int getZoom() {
		if ((double) Static138.aFloat14 == 3.0D) {
			return 37;
		} else if ((double) Static138.aFloat14 == 4.0D) {
			return 50;
		} else if ((double) Static138.aFloat14 == 6.0D) {
			return 75;
		} else if ((double) Static138.aFloat14 == 8.0D) {
			return 100;
		} else {
			return 200;
		}
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(IB)Lclient!qc;")
	public static ClientScript method1875(@OriginalArg(0) int arg0) {
		@Pc(12) ClientScript local12 = (ClientScript) Static105.aClass54_9.get((long) arg0);
		if (local12 != null) {
			return local12;
		}
		@Pc(22) byte[] local22 = Static16.aClass153_9.getfile(arg0, 0);
		if (local22 == null) {
			return null;
		}
		local12 = new ClientScript();
		@Pc(42) Packet local42 = new Packet(local22);
		local42.pos = local42.data.length - 2;
		@Pc(53) int local53 = local42.g2();
		@Pc(63) int local63 = local42.data.length - local53 - 12 - 2;
		local42.pos = local63;
		@Pc(70) int local70 = local42.g4();
		local12.anInt4667 = local42.g2();
		local12.anInt4671 = local42.g2();
		local12.anInt4665 = local42.g2();
		local12.anInt4669 = local42.g2();
		@Pc(98) int local98 = local42.g1();
		@Pc(107) int local107;
		@Pc(114) int local114;
		if (local98 > 0) {
			local12.aClass133Array1 = new IterableMap[local98];
			for (local107 = 0; local107 < local98; local107++) {
				local114 = local42.g2();
				@Pc(121) IterableMap local121 = new IterableMap(Static165.bitceil(local114));
				local12.aClass133Array1[local107] = local121;
				while (local114-- > 0) {
					@Pc(136) int local136 = local42.g4();
					@Pc(140) int local140 = local42.g4();
					local121.pushNode(new IntWrapper(local140), (long) local136);
				}
			}
		}
		local42.pos = 0;
		local12.aClass100_880 = local42.gjstrFast();
		local12.anIntArray415 = new int[local70];
		local12.aClass100Array140 = new JagString[local70];
		local107 = 0;
		local12.anIntArray416 = new int[local70];
		while (local63 > local42.pos) {
			local114 = local42.g2();
			if (local114 == 3) {
				local12.aClass100Array140[local107] = local42.gjstr();
			} else if (local114 >= 100 || local114 == 21 || local114 == 38 || local114 == 39) {
				local12.anIntArray416[local107] = local42.g1();
			} else {
				local12.anIntArray416[local107] = local42.g4();
			}
			local12.anIntArray415[local107++] = local114;
		}
		Static105.aClass54_9.put(local12, (long) arg0);
		return local12;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!km;Z)V")
	public static void method1877(@OriginalArg(0) NPCEntity arg0) {
		for (@Pc(13) AreaSound local13 = (AreaSound) AreaSoundManager.npcSounds.head(); local13 != null; local13 = (AreaSound) AreaSoundManager.npcSounds.next()) {
			if (arg0 == local13.npc) {
				if (local13.primaryStream != null) {
					Static204.soundStream.removeSubStream(local13.primaryStream);
					local13.primaryStream = null;
				}
				local13.unlink();
				return;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void method1878(@OriginalArg(0) Js5 arg0) {
		Static182.aClass153_77 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
	public static int method1879(@OriginalArg(0) JagString arg0) {
		if (Static203.aMapElementTypeList_1 == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static203.aMapElementTypeList_1.anInt5074; local20++) {
			if (Static203.aMapElementTypeList_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).method3108(arg0)) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIIIJ)V")
	public static void method1880(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) Entity arg4, @OriginalArg(5) Entity arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) long arg10) {
		if (arg4 == null) {
			return;
		}
		@Pc(6) Decor local6 = new Decor();
		local6.aLong52 = arg10;
		local6.x = arg1 * 128 + 64;
		local6.z = arg2 * 128 + 64;
		local6.y = arg3;
		local6.model = arg4;
		local6.aClass8_2 = arg5;
		local6.type = arg6;
		local6.angle = arg7;
		local6.anInt1394 = arg8;
		local6.anInt1392 = arg9;
		for (@Pc(46) int local46 = arg0; local46 >= 0; local46--) {
			if (Static130.levelTiles[local46][arg1][arg2] == null) {
				Static130.levelTiles[local46][arg1][arg2] = new Ground(local46, arg1, arg2);
			}
		}
		Static130.levelTiles[arg0][arg1][arg2].decor = local6;
	}
}
