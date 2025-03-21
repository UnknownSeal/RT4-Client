package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static196 {

	@OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "[Lclient!ba;")
	public static GWCWorld[] aClass10_Sub1Array2;

	@OriginalMember(owner = "runetek4.client!pl", name = "f", descriptor = "Lclient!na;")
	public static final JString TRADEREQ = JString.parse(":tradereq:");

    @OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(ZI)V")
	public static void method3535(@OriginalArg(0) boolean arg0) {
		BZip2State.anIntArray376 = new int[104];
		Static139.anIntArray325 = new int[104];
		Static146.firstvisibleLevel = 99;
		Static251.blendChroma = new int[104];
		@Pc(14) byte local14;
		if (arg0) {
			local14 = 1;
		} else {
			local14 = 4;
		}
		Static163.aByteArrayArrayArray11 = new byte[local14][104][104];
		Static128.blendMagnitude = new int[104];
		Static60.anIntArrayArrayArray6 = new int[local14][105][105];
		Static118.levelShademap = new byte[local14][105][105];
		Static240.aByteArrayArrayArray14 = new byte[local14][104][104];
		Static279.anIntArray568 = new int[104];
		Static4.aByteArrayArrayArray1 = new byte[local14][104][104];
		Static253.levelTileUnderlayIds = new byte[local14][104][104];
	}

	@OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(I)Lclient!mm;")
	public static SoftwareSprite method3537() {
		@Pc(13) int local13 = SpriteLoader.innerWidths[0] * SpriteLoader.innerHeights[0];
		@Pc(17) byte[] local17 = SpriteLoader.pixels[0];
		@Pc(20) int[] local20 = new int[local13];
		for (@Pc(22) int local22 = 0; local22 < local13; local22++) {
			local20[local22] = Static259.anIntArray513[local17[local22] & 0xFF];
		}
		@Pc(57) SoftwareSprite local57 = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], local20);
		SpriteLoader.clear();
		return local57;
	}
}
