package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static169 {

	@OriginalMember(owner = "runetek4.client!nf", name = "d", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_5;

	@OriginalMember(owner = "runetek4.client!nf", name = "e", descriptor = "I")
	public static int anInt4073;

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(Lclient!na;BZ)V")
	public static void openUrl(@OriginalArg(0) JString arg0, @OriginalArg(2) boolean arg1) {
		if (!arg1) {
			try {
				GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_top");
			} catch (@Pc(22) Exception local22) {
			}
			return;
		}
		if (GlRenderer.enabled && GameShell.openWindowJavaScript) {
			try {
				BrowserControl.call(GameShell.signLink.applet, "openjs", new Object[] { arg0.method3127(GameShell.instance.getCodeBase()).toString() });
				return;
			} catch (@Pc(48) Throwable local48) {
			}
		}
		try {
			GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_blank");
		} catch (@Pc(59) Exception local59) {
		}
	}

}
