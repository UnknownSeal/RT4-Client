package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static121 {

	@OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "I")
	public static int anInt3038;

	@OriginalMember(owner = "runetek4.client!jg", name = "b", descriptor = "I")
	public static int anInt3039;

    @OriginalMember(owner = "runetek4.client!jg", name = "g", descriptor = "I")
	public static int anInt3041;

	@OriginalMember(owner = "runetek4.client!jg", name = "j", descriptor = "Lclient!ve;")
	public static Js5 aClass153_45;

	@OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(I)[Lclient!ek;")
	public static SoftwareIndexedSprite[] method2406() {
		@Pc(2) SoftwareIndexedSprite[] local2 = new SoftwareIndexedSprite[Static165.anInt4038];
		for (@Pc(8) int local8 = 0; local8 < Static165.anInt4038; local8++) {
			local2[local8] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local8], SpriteLoader.yOffsets[local8], SpriteLoader.innerWidths[local8], SpriteLoader.innerHeights[local8], SpriteLoader.pixels[local8], Static259.anIntArray513);
		}
		SpriteLoader.clear();
		return local2;
	}

	@OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(IBIII)V")
	public static void method2407(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		for (@Pc(3) int local3 = 0; local3 < InterfaceList.rectangles; local3++) {
			if (arg0 < InterfaceList.rectangleX[local3] + InterfaceList.rectangleWidth[local3] && arg0 + arg3 > InterfaceList.rectangleX[local3] && InterfaceList.rectangleY[local3] + InterfaceList.rectangleHeight[local3] > arg1 && InterfaceList.rectangleY[local3] < arg2 + arg1) {
				InterfaceList.rectangleRedraw[local3] = true;
			}
		}
	}
}
