package com.jagex.runetek4.graphics.texture;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!m")
public interface TextureProvider {

	@OriginalMember(owner = "runetek4.client!m", name = "a", descriptor = "(BI)Z")
	boolean isOpaque(@OriginalArg(1) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "a", descriptor = "(IZ)V")
	void method3227(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "b", descriptor = "(IZ)I")
	int method3228(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "a", descriptor = "(II)I")
	int method3229(@OriginalArg(1) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "b", descriptor = "(II)Z")
	boolean method3230(@OriginalArg(1) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "c", descriptor = "(II)Z")
	boolean method3231(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "a", descriptor = "(IZF)[I")
	int[] method3232(@OriginalArg(0) int arg0, @OriginalArg(2) float arg1);

	@OriginalMember(owner = "runetek4.client!m", name = "b", descriptor = "(BI)Z")
	boolean isLowDetail(@OriginalArg(1) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "d", descriptor = "(II)I")
	int getAverageColor(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "e", descriptor = "(II)[I")
	int[] getPixels(@OriginalArg(1) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "f", descriptor = "(II)Z")
	boolean method3236(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "g", descriptor = "(II)I")
	int getMaterialType(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!m", name = "h", descriptor = "(II)I")
	int method3238(@OriginalArg(1) int arg0);
}
