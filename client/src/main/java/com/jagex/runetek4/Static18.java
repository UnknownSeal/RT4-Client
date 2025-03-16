package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static18 {

	@OriginalMember(owner = "client!bh", name = "M", descriptor = "Z")
	public static boolean aBoolean40;

	@OriginalMember(owner = "client!bh", name = "s", descriptor = "Lclient!na;")
	public static final JString P11_FULL = Static28.parse("p11_full");

	@OriginalMember(owner = "client!bh", name = "t", descriptor = "I")
	public static int mouseInvInterfaceIndex = 0;

	@OriginalMember(owner = "client!bh", name = "v", descriptor = "Lclient!na;")
	public static final JString FLOORSHADOWS = Static28.parse("floorshadows");

	@OriginalMember(owner = "client!bh", name = "z", descriptor = "Z")
	public static boolean sentToServer = true;

	@OriginalMember(owner = "client!bh", name = "C", descriptor = "Lclient!na;")
	public static final JString aClass100_108 = Static28.parse("<br>(X100(U(Y");

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(Lclient!fe;Z)V")
	public static void method553(@OriginalArg(0) PathingEntity arg0) {
		@Pc(8) int local8 = arg0.anInt3395 - client.loop;
		@Pc(20) int local20 = arg0.anInt3380 * 128 + arg0.size() * 64;
		@Pc(36) int local36 = arg0.anInt3428 * 128 + arg0.size() * 64;
		if (arg0.anInt3431 == 0) {
			arg0.dstYaw = 1024;
		}
		arg0.xFine += (local20 - arg0.xFine) / local8;
		arg0.zFine += (local36 - arg0.zFine) / local8;
		if (arg0.anInt3431 == 1) {
			arg0.dstYaw = 1536;
		}
		arg0.anInt3417 = 0;
		if (arg0.anInt3431 == 2) {
			arg0.dstYaw = 0;
		}
		if (arg0.anInt3431 == 3) {
			arg0.dstYaw = 512;
		}
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IB)I")
	public static int method554(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIIIIIII)V")
	public static void orbitCamera(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		@Pc(5) int local5;
		@Pc(29) int local29;
		if (GlRenderer.enabled) {
			local5 = arg1 - 334;
			if (local5 < 0) {
				local5 = 0;
			} else if (local5 > 100) {
				local5 = 100;
			}
			local29 = local5 * (Static187.aShort27 - Static263.aShort30) / 100 + Static263.aShort30;
			arg3 = local29 * arg3 >> 8;
		}
		local5 = 2048 - arg6 & 0x7FF;
		local29 = 2048 - arg4 & 0x7FF;
		@Pc(55) int local55 = 0;
		@Pc(57) int local57 = arg3;
		@Pc(59) int local59 = 0;
		@Pc(72) int local72;
		@Pc(68) int local68;
		if (local5 != 0) {
			local68 = MathUtils.anIntArray225[local5];
			local72 = MathUtils.anIntArray223[local5];
			local59 = local72 * -arg3 >> 16;
			local57 = local68 * arg3 >> 16;
		}
		if (local29 != 0) {
			local72 = MathUtils.anIntArray223[local29];
			local68 = MathUtils.anIntArray225[local29];
			local55 = local72 * local57 >> 16;
			local57 = local57 * local68 >> 16;
		}
		Camera.cameraPitch = arg6;
		Camera.cameraYaw = arg4;
		Camera.renderZ = arg5 - local57;
		Camera.renderX = arg0 - local55;
		Camera.cameraY = arg2 - local59;
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(B)Lclient!ba;")
	public static GWCWorld method556() {
		Static51.anInt1682 = 0;
		return Static88.method1821();
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIII)V")
	public static void method559(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return;
		}
		@Pc(13) WallDecor local13 = local7.wallDecor;
		if (local13 != null) {
			local13.xOffset = local13.xOffset * arg3 / 16;
			local13.zOffset = local13.zOffset * arg3 / 16;
		}
	}
}
