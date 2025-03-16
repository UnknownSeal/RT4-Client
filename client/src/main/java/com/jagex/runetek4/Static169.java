package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static169 {

	@OriginalMember(owner = "runetek4.client!nf", name = "c", descriptor = "[S")
	public static short[] aShortArray52;

	@OriginalMember(owner = "runetek4.client!nf", name = "d", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_5;

	@OriginalMember(owner = "runetek4.client!nf", name = "e", descriptor = "I")
	public static int anInt4073;

	@OriginalMember(owner = "runetek4.client!nf", name = "h", descriptor = "Lclient!be;")
	public static Component aClass13_18;

	@OriginalMember(owner = "runetek4.client!nf", name = "f", descriptor = "Lclient!n;")
	public static final NodeCache modelCacheStatic = new NodeCache(500);

	@OriginalMember(owner = "runetek4.client!nf", name = "i", descriptor = "I")
	public static int anInt4075 = -1;

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(Lclient!na;BZ)V")
	public static void openUrl(@OriginalArg(0) JString arg0, @OriginalArg(2) boolean arg1) {
		if (!arg1) {
			try {
				Static230.anApplet_Sub1_1.getAppletContext().showDocument(arg0.method3127(Static230.anApplet_Sub1_1.getCodeBase()), "_top");
			} catch (@Pc(22) Exception local22) {
			}
			return;
		}
		if (GlRenderer.enabled && Static40.aBoolean78) {
			try {
				BrowserControl.call(GameShell.signLink.applet, "openjs", new Object[] { arg0.method3127(Static230.anApplet_Sub1_1.getCodeBase()).toString() });
				return;
			} catch (@Pc(48) Throwable local48) {
			}
		}
		try {
			Static230.anApplet_Sub1_1.getAppletContext().showDocument(arg0.method3127(Static230.anApplet_Sub1_1.getCodeBase()), "_blank");
		} catch (@Pc(59) Exception local59) {
		}
	}

}
