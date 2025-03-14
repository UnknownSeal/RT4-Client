package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.Entity;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.js5.CacheArchive;
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
	public static void method1878(@OriginalArg(0) CacheArchive arg0) {
		Static182.aClass153_77 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
	public static int method1879(@OriginalArg(0) JString arg0) {
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
