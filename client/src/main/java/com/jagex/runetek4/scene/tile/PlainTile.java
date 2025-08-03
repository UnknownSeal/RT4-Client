package com.jagex.runetek4.scene.tile;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!rh")
public final class PlainTile {

	@OriginalMember(owner = "client!rh", name = "m", descriptor = "Z")
	public boolean isFlat = true;

	@OriginalMember(owner = "client!rh", name = "f", descriptor = "I")
	public final int swHeight;

	@OriginalMember(owner = "client!rh", name = "c", descriptor = "I")
	public final int seHeight;

	@OriginalMember(owner = "client!rh", name = "n", descriptor = "I")
	public final int neHeight;

	@OriginalMember(owner = "client!rh", name = "l", descriptor = "I")
	public final int rgbColor;

	@OriginalMember(owner = "client!rh", name = "b", descriptor = "I")
	public final int nwHeight;

	@OriginalMember(owner = "client!rh", name = "i", descriptor = "I")
	public final int texture;

	@OriginalMember(owner = "client!rh", name = "<init>", descriptor = "(IIIIIIZ)V")
	public PlainTile(@OriginalArg(0) int neHeight, @OriginalArg(1) int swHeight, @OriginalArg(2) int seHeight, @OriginalArg(3) int nwHeight, @OriginalArg(4) int texture, @OriginalArg(5) int rgbColor, @OriginalArg(6) boolean isFlat) {
		this.swHeight = swHeight;
		this.seHeight = seHeight;
		this.neHeight = neHeight;
		this.rgbColor = rgbColor;
		this.isFlat = isFlat;
		this.nwHeight = nwHeight;
		this.texture = texture;
	}
}
