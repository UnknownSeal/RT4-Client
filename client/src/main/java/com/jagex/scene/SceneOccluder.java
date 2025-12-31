package com.jagex.scene;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!pe")
public final class SceneOccluder {

	@OriginalMember(owner = "runetek4.client!pe", name = "b", descriptor = "I")
	public int minY;

	@OriginalMember(owner = "runetek4.client!pe", name = "c", descriptor = "I")
	public int worldZ1;

	@OriginalMember(owner = "runetek4.client!pe", name = "e", descriptor = "I")
	public int tileZ1;

	@OriginalMember(owner = "runetek4.client!pe", name = "f", descriptor = "I")
	public int maxY;

	@OriginalMember(owner = "runetek4.client!pe", name = "g", descriptor = "I")
	public int anInt4448;

	@OriginalMember(owner = "runetek4.client!pe", name = "h", descriptor = "I")
	public int worldZ2;

	@OriginalMember(owner = "runetek4.client!pe", name = "i", descriptor = "I")
	public int anInt4450;

	@OriginalMember(owner = "runetek4.client!pe", name = "k", descriptor = "I")
	public int tileX1;

	@OriginalMember(owner = "runetek4.client!pe", name = "l", descriptor = "I")
	public int plane;

	@OriginalMember(owner = "runetek4.client!pe", name = "m", descriptor = "I")
	public int anInt4454;

	@OriginalMember(owner = "runetek4.client!pe", name = "p", descriptor = "I")
	public int anInt4456;

	@OriginalMember(owner = "runetek4.client!pe", name = "s", descriptor = "I")
	public int worldX2;

	@OriginalMember(owner = "runetek4.client!pe", name = "t", descriptor = "I")
	public int anInt4459;

	@OriginalMember(owner = "runetek4.client!pe", name = "u", descriptor = "I")
	public int worldX1;

	@OriginalMember(owner = "runetek4.client!pe", name = "v", descriptor = "I")
	public int tileX2;

	@OriginalMember(owner = "runetek4.client!pe", name = "w", descriptor = "I")
	public int anInt4462;

	@OriginalMember(owner = "runetek4.client!pe", name = "x", descriptor = "I")
	public int anInt4463;

	@OriginalMember(owner = "runetek4.client!pe", name = "y", descriptor = "I")
	public int tileZ2;

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIIIIII)V")
	public static void addOccluder(@OriginalArg(0) int plane, @OriginalArg(1) int x1, @OriginalArg(2) int z1, @OriginalArg(3) int x2, @OriginalArg(4) int z2, @OriginalArg(5) int minY, @OriginalArg(6) int maxY) {
		@Pc(3) SceneOccluder local3 = new SceneOccluder();
		local3.tileX1 = x1 / 128;
		local3.tileZ1 = z1 / 128;
		local3.tileX2 = x2 / 128;
		local3.tileZ2 = z2 / 128;
		local3.plane = plane;
		local3.worldX1 = x1;
		local3.worldZ1 = z1;
		local3.worldX2 = x2;
		local3.worldZ2 = z2;
		local3.minY = minY;
		local3.maxY = maxY;
		SceneGraph.renderables[SceneGraph.renderableCount++] = local3;
	}
}
