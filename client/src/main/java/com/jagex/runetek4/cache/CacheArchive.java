package com.jagex.runetek4.cache;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class CacheArchive {

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

}