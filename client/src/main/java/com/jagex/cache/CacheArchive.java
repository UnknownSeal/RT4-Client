package com.jagex.cache;

import com.jagex.game.runetek4.client.GameShell;
import com.jagex.core.io.Packet;
import com.jagex.clientscript.ClientScriptRunner;
import com.jagex.graphics.textureops.TextureOp29SubOp1;
import com.jagex.ui.component.InterfaceManager;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class CacheArchive {

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Z)V")
	public static void method182() {
		InterfaceManager.dragChildren = null;
		ClientScriptRunner.renderOrInvalidateComponent(InterfaceManager.topLevelInterface, 0, GameShell.canvasWidth, 0, -1, GameShell.canvasHeight, 0, 0);
		if (InterfaceManager.dragChildren != null) {
			InterfaceManager.draw(0, InterfaceManager.dragOffsetY, InterfaceManager.dragOffsetX, InterfaceManager.dragChildren, GameShell.canvasWidth, -1412584499, 0, GameShell.canvasHeight, InterfaceManager.dragLayer.rectangle);
			InterfaceManager.dragChildren = null;
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(ILclient!wa;)Lclient!ci;")
	public static TextureOp29SubOp1 method184(@OriginalArg(1) Packet arg0) {
		return new TextureOp29SubOp1(arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g3(), arg0.g1());
	}

}