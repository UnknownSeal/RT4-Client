package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static206 {

	@OriginalMember(owner = "runetek4.client!qk", name = "c", descriptor = "[I")
	public static int[] anIntArray427;

	@OriginalMember(owner = "runetek4.client!qk", name = "g", descriptor = "Lclient!ma;")
	public static BufferedSocket js5Socket;

	@OriginalMember(owner = "runetek4.client!qk", name = "a", descriptor = "Lclient!na;")
	public static final JString aClass100_899 = Static28.parse("0(U");

	@OriginalMember(owner = "runetek4.client!qk", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_900 = Static28.parse("tbrefresh");

	@OriginalMember(owner = "runetek4.client!qk", name = "f", descriptor = "I")
	public static int anInt4773 = 0;

	@OriginalMember(owner = "runetek4.client!qk", name = "h", descriptor = "I")
	public static int cameraAnticheatOffsetZ = 0;

	@OriginalMember(owner = "runetek4.client!qk", name = "a", descriptor = "(ZIIIIFII)[I")
	public static int[] method3679(@OriginalArg(5) float arg0) {
		@Pc(11) int[] local11 = new int[2048];
		@Pc(15) TextureOp4 local15 = new TextureOp4();
		local15.anInt646 = 8;
		local15.anInt642 = 4;
		local15.anInt650 = 35;
		local15.anInt641 = 8;
		local15.anInt648 = (int) (arg0 * 4096.0F);
		local15.aBoolean44 = true;
		local15.postDecode();
		Texture.setSize(1, 2048);
		local15.method584(0, local11);
		return local11;
	}

	@OriginalMember(owner = "runetek4.client!qk", name = "a", descriptor = "(ZI)I")
	public static int method3681(@OriginalArg(1) int arg0) {
		return arg0 >>> 8;
	}
}
