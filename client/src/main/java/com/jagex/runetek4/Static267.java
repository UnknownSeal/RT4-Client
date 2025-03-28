package com.jagex.runetek4;

import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static267 {

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "Lclient!n;")
	public static final NodeCache skeletonCache = new NodeCache(100);

	@OriginalMember(owner = "runetek4.client!vl", name = "f", descriptor = "Lclient!na;")
	public static final JString CABBAGE = JString.parse("Cabbage");

	@OriginalMember(owner = "runetek4.client!vl", name = "h", descriptor = "I")
	public static int anInt5773 = 0;

	@OriginalMember(owner = "runetek4.client!vl", name = "i", descriptor = "[I")
	public static int[] anIntArray518 = new int[2];

	@OriginalMember(owner = "runetek4.client!vl", name = "l", descriptor = "I")
	public static int anInt5776 = 0;

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "(I)Z")
	public static boolean method4527() {
		if (client.objectTag) {
			try {
				return !((Boolean) Static119.SHOWINGVIDEOAD.browserControlCall(GameShell.signLink.applet));
			} catch (@Pc(21) Throwable local21) {
			}
		}
		return true;
	}

}
