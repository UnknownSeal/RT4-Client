package com.jagex.runetek4.cache;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class CacheArchive {

	@OriginalMember(owner = "client!al", name = "e", descriptor = "I")
	public static int anInt172;

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Z)V")
	public static void method182() {
		ClientScriptRunner.aClass13Array13 = null;
		Static6.method86(InterfaceList.topLevelInterface, 0, GameShell.canvasWidth, 0, -1, GameShell.canvasHeigth, 0, 0);
		if (ClientScriptRunner.aClass13Array13 != null) {
			ClientScriptRunner.renderComponent(0, ClientScriptRunner.anInt3126, ClientScriptRunner.anInt4696, ClientScriptRunner.aClass13Array13, GameShell.canvasWidth, -1412584499, 0, GameShell.canvasHeigth, ClientScriptRunner.aClass13_1.rectangle);
			ClientScriptRunner.aClass13Array13 = null;
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(ILclient!wa;)Lclient!ci;")
	public static TextureOp29SubOp1 method184(@OriginalArg(1) Packet arg0) {
		return new TextureOp29SubOp1(arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g3(), arg0.g1());
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(III)Z")
	public static boolean method187(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) int local7 = Static140.anIntArrayArrayArray12[arg0][arg1][arg2];
		if (local7 == -Static13.anInt437) {
			return false;
		} else if (local7 == Static13.anInt437) {
			return true;
		} else {
			@Pc(22) int local22 = arg1 << 7;
			@Pc(26) int local26 = arg2 << 7;
			if (Static256.method4394(local22 + 1, SceneGraph.tileHeights[arg0][arg1][arg2], local26 + 1) && Static256.method4394(local22 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg2], local26 + 1) && Static256.method4394(local22 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg2 + 1], local26 + 128 - 1) && Static256.method4394(local22 + 1, SceneGraph.tileHeights[arg0][arg1][arg2 + 1], local26 + 128 - 1)) {
				Static140.anIntArrayArrayArray12[arg0][arg1][arg2] = Static13.anInt437;
				return true;
			} else {
				Static140.anIntArrayArrayArray12[arg0][arg1][arg2] = -Static13.anInt437;
				return false;
			}
		}
	}
}