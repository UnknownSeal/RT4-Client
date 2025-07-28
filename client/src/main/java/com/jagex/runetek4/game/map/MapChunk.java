package com.jagex.runetek4.game.map;

import com.jagex.runetek4.core.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!lh")
public final class MapChunk extends Node {

	@OriginalMember(owner = "runetek4.client!lh", name = "y", descriptor = "I")
	public final int displayMinZ;

	@OriginalMember(owner = "runetek4.client!lh", name = "v", descriptor = "I")
	public final int displayMaxX;

	@OriginalMember(owner = "runetek4.client!lh", name = "w", descriptor = "I")
	public final int displayMaxZ;

	@OriginalMember(owner = "runetek4.client!lh", name = "t", descriptor = "I")
	public final int displayMinX;

	@OriginalMember(owner = "runetek4.client!lh", name = "<init>", descriptor = "(IIII)V")
	public MapChunk(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		this.displayMinZ = arg3;
		this.displayMaxX = arg1;
		this.displayMaxZ = arg2;
		this.displayMinX = arg0;
	}

	@OriginalMember(owner = "runetek4.client!lh", name = "a", descriptor = "(BII)Z")
	public final boolean containsDisplay(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		return arg1 >= this.displayMinX && this.displayMaxZ >= arg1 && this.displayMaxX <= arg0 && arg0 <= this.displayMinZ;
	}
}
