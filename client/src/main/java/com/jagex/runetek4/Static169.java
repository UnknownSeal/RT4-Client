package com.jagex.runetek4;

import java.awt.Frame;

import com.jagex.runetek4.cache.media.component.Component;
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
				BrowserControl.method1758(GameShell.signLink.anApplet2, "openjs", new Object[] { arg0.method3127(Static230.anApplet_Sub1_1.getCodeBase()).toString() });
				return;
			} catch (@Pc(48) Throwable local48) {
			}
		}
		try {
			Static230.anApplet_Sub1_1.getAppletContext().showDocument(arg0.method3127(Static230.anApplet_Sub1_1.getCodeBase()), "_blank");
		} catch (@Pc(59) Exception local59) {
		}
	}

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(IIIIILsignlink!ll;)Ljava/awt/Frame;")
	public static Frame method3176(@OriginalArg(2) int arg0, @OriginalArg(3) int arg1, @OriginalArg(4) int arg2, @OriginalArg(5) SignLink arg3) {
		if (!arg3.method5111()) {
			return null;
		}
		@Pc(20) Class114[] local20 = Static197.method3558(arg3);
		if (local20 == null) {
			return null;
		}
		@Pc(27) boolean local27 = false;
		for (@Pc(29) int local29 = 0; local29 < local20.length; local29++) {
			if (arg2 == local20[local29].anInt4248 && arg1 == local20[local29].anInt4250 && (!local27 || local20[local29].anInt4251 > arg0)) {
				arg0 = local20[local29].anInt4251;
				local27 = true;
			}
		}
		if (!local27) {
			return null;
		}
		@Pc(90) PrivilegedRequest local90 = arg3.method5129(arg0, arg1, arg2);
		while (local90.status == 0) {
			PreciseSleep.sleep(10L);
		}
		@Pc(103) Frame local103 = (Frame) local90.result;
		if (local103 == null) {
			return null;
		} else if (local90.status == 2) {
			Static25.method714(local103, arg3);
			return null;
		} else {
			return local103;
		}
	}

}
