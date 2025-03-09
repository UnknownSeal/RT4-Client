package com.jagex.runetek4;

import com.jagex.runetek4.game.config.iftype.Component;
import com.jagex.runetek4.game.scene.entities.PathingEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static263 {

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "[Lclient!na;")
	public static JagString[] aClass100Array174;

	@OriginalMember(owner = "runetek4.client!vg", name = "e", descriptor = "[I")
	public static int[] anIntArray516;

	@OriginalMember(owner = "runetek4.client!vg", name = "b", descriptor = "S")
	public static short aShort30 = 256;

	@OriginalMember(owner = "runetek4.client!vg", name = "c", descriptor = "Z")
	public static boolean aBoolean299 = false;

	@OriginalMember(owner = "runetek4.client!vg", name = "d", descriptor = "I")
	public static int minimapAngleModifier = 2;

	@OriginalMember(owner = "runetek4.client!vg", name = "f", descriptor = "Lclient!na;")
	public static final JagString GREEN3 = Static28.parse("<col=80ff00>");

	@OriginalMember(owner = "runetek4.client!vg", name = "h", descriptor = "Lclient!na;")
	public static final JagString aClass100_1082 = Static28.parse("; Expires=");

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(I[S)[S")
	public static short[] method4511(@OriginalArg(1) short[] arg0) {
		if (arg0 == null) {
			return null;
		} else {
			@Pc(19) short[] local19 = new short[arg0.length];
			Static289.method2616(arg0, 0, local19, 0, arg0.length);
			return local19;
		}
	}

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
	public static void method4512(@OriginalArg(0) JagString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) Component local8 = Static201.method1418(arg3, arg1);
		if (local8 == null) {
			return;
		}
		if (local8.anObjectArray29 != null) {
			@Pc(19) HookRequest local19 = new HookRequest();
			local19.anObjectArray31 = local8.anObjectArray29;
			local19.source = local8;
			local19.aClass100_598 = arg0;
			local19.anInt3101 = arg2;
			Static82.method1767(local19);
		}
		@Pc(37) boolean local37 = true;
		if (local8.anInt453 > 0) {
			local37 = Static249.method4265(local8);
		}
		if (!local37 || !Static36.method940(local8).method503(arg2 - 1)) {
			return;
		}
		if (arg2 == 1) {
			Static6.outboundBuffer.pIsaac1(155);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 2) {
			Static6.outboundBuffer.pIsaac1(196);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 3) {
			Static6.outboundBuffer.pIsaac1(124);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 4) {
			Static6.outboundBuffer.pIsaac1(199);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 5) {
			Static6.outboundBuffer.pIsaac1(234);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 6) {
			Static6.outboundBuffer.pIsaac1(168);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 7) {
			Static6.outboundBuffer.pIsaac1(166);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 8) {
			Static6.outboundBuffer.pIsaac1(64);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 9) {
			Static6.outboundBuffer.pIsaac1(53);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
		if (arg2 == 10) {
			Static6.outboundBuffer.pIsaac1(9);
			Static6.outboundBuffer.p4(arg3);
			Static6.outboundBuffer.p2(arg1);
		}
	}

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(IILclient!fe;)V")
	public static void updateEntity(@OriginalArg(0) int arg0, @OriginalArg(2) PathingEntity entity) {
		if (Static83.loopCycle < entity.anInt3395) {
			Static18.method553(entity);
		} else if (entity.anInt3386 >= Static83.loopCycle) {
			Static280.method4665(entity);
		} else {
			Static104.method2247(entity);
		}
		if (entity.x < 128 || entity.z < 128 || entity.x >= 13184 || entity.z >= 13184) {
			entity.primarySeqId = -1;
			entity.spotanimFrame = -1;
			entity.anInt3395 = 0;
			entity.anInt3386 = 0;
			entity.x = entity.pathTileX[0] * 128 + entity.size() * 64;
			entity.z = entity.pathTileZ[0] * 128 + entity.size() * 64;
			entity.method2689();
		}
		if (entity == Static173.localPlayer && (entity.x < 1536 || entity.z < 1536 || entity.x >= 11776 || entity.z >= 11776)) {
			entity.spotanimFrame = -1;
			entity.anInt3395 = 0;
			entity.anInt3386 = 0;
			entity.primarySeqId = -1;
			entity.x = entity.pathTileX[0] * 128 + entity.size() * 64;
			entity.z = entity.pathTileZ[0] * 128 + entity.size() * 64;
			entity.method2689();
		}
		Static37.method949(entity);
		Static34.method879(entity);
	}
}
