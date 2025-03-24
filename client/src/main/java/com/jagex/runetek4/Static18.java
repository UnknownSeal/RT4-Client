package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static18 {

	@OriginalMember(owner = "client!bh", name = "M", descriptor = "Z")
	public static boolean aBoolean40;

	@OriginalMember(owner = "client!bh", name = "t", descriptor = "I")
	public static int mouseInvInterfaceIndex = 0;

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(Lclient!fe;Z)V")
	public static void method553(@OriginalArg(0) PathingEntity arg0) {
		@Pc(8) int local8 = arg0.anInt3395 - client.loop;
		@Pc(20) int local20 = arg0.anInt3380 * 128 + arg0.getSize() * 64;
		@Pc(36) int local36 = arg0.anInt3428 * 128 + arg0.getSize() * 64;
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

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(B)Lclient!ba;")
	public static GWCWorld method556() {
		Static51.anInt1682 = 0;
		return Static88.method1821();
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIII)V")
	public static void method559(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(7) Tile local7 = SceneGraph.tiles[arg0][arg1][arg2];
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
