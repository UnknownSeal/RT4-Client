package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.dash3d.entity.ObjStackEntity;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static69 {

	@OriginalMember(owner = "client!fh", name = "U", descriptor = "[[Z")
	public static final boolean[][] aBooleanArrayArray2 = new boolean[][] { new boolean[0], { true, false, true }, { true, false, false, true }, { false, false, true, true }, { true, true, false }, { false, true, true }, { true, false, false, true }, { false, false, false, true, true }, { false, true, true }, { true, false, true, true, true }, { false, true, true, true, true }, { false, true, true, true, true, false } };

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIII)I")
	public static int method1540(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (arg1 == arg2) {
			return arg1;
		} else {
			@Pc(17) int local17 = 128 - arg0;
			@Pc(50) int local50 = arg0 * (arg2 >>> 7 & 0x1FE01FE) + local17 * (arg1 >>> 7 & 0x1FE01FE) & 0xFF00FF00;
			@Pc(65) int local65 = local17 * (arg1 & 0xFF00FF) + (arg2 & 0xFF00FF) * arg0 & 0xFF00FF00;
			return local50 + (local65 >> 7);
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(JI)V")
	public static void method1542(@OriginalArg(0) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		for (@Pc(12) int local12 = 0; local12 < IgnoreList.ignoreCount; local12++) {
			if (IgnoreList.encodedIgnores[local12] == arg0) {
				IgnoreList.ignoreCount--;
				for (@Pc(36) int local36 = local12; local36 < IgnoreList.ignoreCount; local36++) {
					IgnoreList.encodedIgnores[local36] = IgnoreList.encodedIgnores[local36 + 1];
					Static193.ignoreName[local36] = Static193.ignoreName[local36 + 1];
				}
				FriendList.transmitAt = InterfaceList.transmitTimer;
				Protocol.outboundBuffer.pIsaac1(213);
				Protocol.outboundBuffer.p8(arg0);
				break;
			}
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIIILclient!th;JLclient!th;Lclient!th;)V")
	public static void method1543(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) Entity arg4, @OriginalArg(5) long arg5, @OriginalArg(6) Entity arg6, @OriginalArg(7) Entity arg7) {
		@Pc(3) ObjStackEntity local3 = new ObjStackEntity();
		local3.aClass8_9 = arg4;
		local3.anInt3064 = arg1 * 128 + 64;
		local3.anInt3061 = arg2 * 128 + 64;
		local3.anInt3057 = arg3;
		local3.aLong108 = arg5;
		local3.aClass8_7 = arg6;
		local3.aClass8_8 = arg7;
		@Pc(34) int local34 = 0;
		@Pc(42) SceneTile local42 = Static130.levelTiles[arg0][arg1][arg2];
		if (local42 != null) {
			for (@Pc(46) int local46 = 0; local46 < local42.entityCount; local46++) {
				@Pc(55) Scenery local55 = local42.sceneries[local46];
				if ((local55.hash & 0x400000L) == 4194304L) {
					@Pc(66) int local66 = local55.entity.getMinY();
					if (local66 != -32768 && local66 < local34) {
						local34 = local66;
					}
				}
			}
		}
		local3.offset = -local34;
		if (Static130.levelTiles[arg0][arg1][arg2] == null) {
			Static130.levelTiles[arg0][arg1][arg2] = new SceneTile(arg0, arg1, arg2);
		}
		Static130.levelTiles[arg0][arg1][arg2].aClass79_1 = local3;
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!th;IIIII)V")
	public static void method1544(@OriginalArg(0) Entity arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(1) boolean local1 = true;
		@Pc(3) int local3 = arg2;
		@Pc(7) int local7 = arg2 + arg4;
		@Pc(11) int local11 = arg3 - 1;
		@Pc(15) int local15 = arg3 + arg5;
		for (@Pc(17) int local17 = arg1; local17 <= arg1 + 1; local17++) {
			if (local17 != Static126.anInt3114) {
				for (@Pc(28) int local28 = local3; local28 <= local7; local28++) {
					if (local28 >= 0 && local28 < Static152.anInt3594) {
						for (@Pc(39) int local39 = local11; local39 <= local15; local39++) {
							if (local39 >= 0 && local39 < Static99.anInt2550 && (!local1 || local28 >= local7 || local39 >= local15 || local39 < arg3 && local28 != arg2)) {
								@Pc(71) SceneTile local71 = Static130.levelTiles[local17][local28][local39];
								if (local71 != null) {
									@Pc(158) int local158 = (SceneGraph.tileHeights[local17][local28][local39] + SceneGraph.tileHeights[local17][local28 + 1][local39] + SceneGraph.tileHeights[local17][local28][local39 + 1] + SceneGraph.tileHeights[local17][local28 + 1][local39 + 1]) / 4 - (SceneGraph.tileHeights[arg1][arg2][arg3] + SceneGraph.tileHeights[arg1][arg2 + 1][arg3] + SceneGraph.tileHeights[arg1][arg2][arg3 + 1] + SceneGraph.tileHeights[arg1][arg2 + 1][arg3 + 1]) / 4;
									@Pc(161) Wall local161 = local71.wall;
									if (local161 != null) {
										if (local161.primary.method4543()) {
											arg0.method4544(local161.primary, (local28 - arg2) * 128 + (1 - arg4) * 64, local158, (local39 - arg3) * 128 + (1 - arg5) * 64, local1);
										}
										if (local161.modelB != null && local161.modelB.method4543()) {
											arg0.method4544(local161.modelB, (local28 - arg2) * 128 + (1 - arg4) * 64, local158, (local39 - arg3) * 128 + (1 - arg5) * 64, local1);
										}
									}
									for (@Pc(232) int local232 = 0; local232 < local71.entityCount; local232++) {
										@Pc(241) Scenery local241 = local71.sceneries[local232];
										if (local241 != null && local241.entity.method4543() && (local28 == local241.anInt1701 || local28 == local3) && (local39 == local241.anInt1696 || local39 == local11)) {
											@Pc(270) int local270 = local241.anInt1713 + 1 - local241.anInt1701;
											@Pc(278) int local278 = local241.anInt1698 + 1 - local241.anInt1696;
											arg0.method4544(local241.entity, (local241.anInt1701 - arg2) * 128 + (local270 - arg4) * 64, local158, (local241.anInt1696 - arg3) * 128 + (local278 - arg5) * 64, local1);
										}
									}
								}
							}
						}
					}
				}
				local3--;
				local1 = false;
			}
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1546(@OriginalArg(0) Packet arg0) {
		for (@Pc(7) int local7 = 0; local7 < Static106.anInt2871; local7++) {
			@Pc(18) int local18 = arg0.gSmart1or2();
			@Pc(22) int local22 = arg0.g2();
			if (local22 == 65535) {
				local22 = -1;
			}
			if (Static196.aClass10_Sub1Array2[local18] != null) {
				Static196.aClass10_Sub1Array2[local18].players = local22;
			}
		}
	}
}
