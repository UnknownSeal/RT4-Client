package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
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

}
