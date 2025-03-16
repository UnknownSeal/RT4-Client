package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static263 {

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array174;

	@OriginalMember(owner = "runetek4.client!vg", name = "e", descriptor = "[I")
	public static int[] anIntArray516;

	@OriginalMember(owner = "runetek4.client!vg", name = "b", descriptor = "S")
	public static short aShort30 = 256;

	@OriginalMember(owner = "runetek4.client!vg", name = "c", descriptor = "Z")
	public static boolean aBoolean299 = false;

	@OriginalMember(owner = "runetek4.client!vg", name = "d", descriptor = "I")
	public static int minimapAngleModifier = 2;

	@OriginalMember(owner = "runetek4.client!vg", name = "f", descriptor = "Lclient!na;")
	public static final JString GREEN3 = JString.parse("<col=80ff00>");

	@OriginalMember(owner = "runetek4.client!vg", name = "h", descriptor = "Lclient!na;")
	public static final JString aClass100_1082 = JString.parse("; Expires=");

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
	public static void method4512(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
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
			ClientScriptRunner.run(local19);
		}
		@Pc(37) boolean local37 = true;
		if (local8.contentType > 0) {
			local37 = Static249.method4265(local8);
		}
		if (!local37 || !Static36.method940(local8).method503(arg2 - 1)) {
			return;
		}
		if (arg2 == 1) {
			Protocol.outboundBuffer.pIsaac1(155);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 2) {
			Protocol.outboundBuffer.pIsaac1(196);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 3) {
			Protocol.outboundBuffer.pIsaac1(124);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 4) {
			Protocol.outboundBuffer.pIsaac1(199);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 5) {
			Protocol.outboundBuffer.pIsaac1(234);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 6) {
			Protocol.outboundBuffer.pIsaac1(168);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 7) {
			Protocol.outboundBuffer.pIsaac1(166);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 8) {
			Protocol.outboundBuffer.pIsaac1(64);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 9) {
			Protocol.outboundBuffer.pIsaac1(53);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
		if (arg2 == 10) {
			Protocol.outboundBuffer.pIsaac1(9);
			Protocol.outboundBuffer.p4(arg3);
			Protocol.outboundBuffer.p2(arg1);
		}
	}

	@OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(IILclient!fe;)V")
	public static void updateEntity(@OriginalArg(0) int arg0, @OriginalArg(2) PathingEntity entity) {
		if (client.loop < entity.anInt3395) {
			Static18.method553(entity);
		} else if (entity.anInt3386 >= client.loop) {
			Static280.method4665(entity);
		} else {
			Static104.method2247(entity);
		}
		if (entity.xFine < 128 || entity.zFine < 128 || entity.xFine >= 13184 || entity.zFine >= 13184) {
			entity.primarySeqId = -1;
			entity.spotanimFrame = -1;
			entity.anInt3395 = 0;
			entity.anInt3386 = 0;
			entity.xFine = entity.movementQueueX[0] * 128 + entity.getSize() * 64;
			entity.zFine = entity.movementQueueZ[0] * 128 + entity.getSize() * 64;
			entity.method2689();
		}
		if (entity == PlayerList.self && (entity.xFine < 1536 || entity.zFine < 1536 || entity.xFine >= 11776 || entity.zFine >= 11776)) {
			entity.spotanimFrame = -1;
			entity.anInt3395 = 0;
			entity.anInt3386 = 0;
			entity.primarySeqId = -1;
			entity.xFine = entity.movementQueueX[0] * 128 + entity.getSize() * 64;
			entity.zFine = entity.movementQueueZ[0] * 128 + entity.getSize() * 64;
			entity.method2689();
		}
		Static37.method949(entity);
		Static34.method879(entity);
	}
}
