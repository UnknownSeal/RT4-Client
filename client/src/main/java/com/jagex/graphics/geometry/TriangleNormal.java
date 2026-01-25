package com.jagex.graphics.geometry;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!qj")
public final class TriangleNormal {

	@OriginalMember(owner = "client!qj", name = "c", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!qj", name = "e", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!qj", name = "f", descriptor = "I")
	public int y;
}