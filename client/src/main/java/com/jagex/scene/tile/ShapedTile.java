package com.jagex.scene.tile;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!fg")
public final class ShapedTile {

	@OriginalMember(owner = "runetek4.client!fg", name = "i", descriptor = "[[I")
	public static final int[][] SHAPE_VERTEX_INDICES = new int[][] { { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 2, 6 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 13, 14 } };

	@OriginalMember(owner = "runetek4.client!fg", name = "j", descriptor = "[[I")
	public static final int[][] SHAPE_TRIANGLE_DATA = new int[][] { { 0, 1, 2, 3, 0, 0, 1, 3 }, { 1, 1, 2, 3, 1, 0, 1, 3 }, { 0, 1, 2, 3, 1, 0, 1, 3 }, { 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 }, { 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 }, { 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 }, { 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 }, { 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 }, { 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 }, { 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 }, { 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3 }, { 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3 }, { 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5 } };

	@OriginalMember(owner = "runetek4.client!fg", name = "s", descriptor = "Z")
	public boolean isFlat = true;

	@OriginalMember(owner = "runetek4.client!fg", name = "a", descriptor = "I")
	public final int shapeType;

	@OriginalMember(owner = "runetek4.client!fg", name = "b", descriptor = "I")
	public final int rotation;

	@OriginalMember(owner = "runetek4.client!fg", name = "p", descriptor = "I")
	public final int underlayRGB;

	@OriginalMember(owner = "runetek4.client!fg", name = "k", descriptor = "I")
	public final int overlayRGB;

	@OriginalMember(owner = "runetek4.client!fg", name = "q", descriptor = "[I")
	public final int[] vertexX;

	@OriginalMember(owner = "runetek4.client!fg", name = "e", descriptor = "[I")
	public final int[] vertexY;

	@OriginalMember(owner = "runetek4.client!fg", name = "h", descriptor = "[I")
	public final int[] vertexZ;

	@OriginalMember(owner = "runetek4.client!fg", name = "n", descriptor = "[I")
	public final int[] triangleA;

	@OriginalMember(owner = "runetek4.client!fg", name = "g", descriptor = "[I")
	public final int[] triangleB;

	@OriginalMember(owner = "runetek4.client!fg", name = "c", descriptor = "[I")
	public final int[] triangleC;

	@OriginalMember(owner = "runetek4.client!fg", name = "o", descriptor = "[I")
	public final int[] triangleHSLA;

	@OriginalMember(owner = "runetek4.client!fg", name = "v", descriptor = "[I")
	public final int[] triangleHSLB;

	@OriginalMember(owner = "runetek4.client!fg", name = "u", descriptor = "[I")
	public final int[] triangleHSLC;

	@OriginalMember(owner = "runetek4.client!fg", name = "f", descriptor = "[I")
	public int[] triangleTextureIds;

	@OriginalMember(owner = "runetek4.client!fg", name = "<init>", descriptor = "(IIIIIIIIIIIIIIIIIII)V")
	public ShapedTile(@OriginalArg(0) int shapeType, @OriginalArg(1) int rotation, @OriginalArg(2) int texture, @OriginalArg(3) int localX, @OriginalArg(4) int localZ, @OriginalArg(5) int swHeight, @OriginalArg(6) int seHeight, @OriginalArg(7) int neHeight, @OriginalArg(8) int nwHeight, @OriginalArg(9) int swUnderlayHSL, @OriginalArg(10) int seUnderlayHSL, @OriginalArg(11) int neUnderlayHSL, @OriginalArg(12) int nwUnderlayHSL, @OriginalArg(13) int swOverlayHSL, @OriginalArg(14) int seOverlayHSL, @OriginalArg(15) int neOverlayHSL, @OriginalArg(16) int nwOverlayHSL, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18) {
		if (swHeight != seHeight || swHeight != neHeight || swHeight != nwHeight) {
			this.isFlat = false;
		}
		this.shapeType = shapeType;
		this.rotation = rotation;
		this.underlayRGB = arg17;
		this.overlayRGB = arg18;
		@Pc(30) short TILE_SIZE = 128;
		@Pc(34) int HALF_TILE = TILE_SIZE / 2;
		@Pc(38) int QUARTER_TILE = TILE_SIZE / 4;
		@Pc(44) int THREE_QUARTER_TILE = TILE_SIZE * 3 / 4;
		@Pc(48) int[] shapedTileMesh = SHAPE_VERTEX_INDICES[shapeType];
		@Pc(51) int local51 = shapedTileMesh.length;
		this.vertexX = new int[local51];
		this.vertexY = new int[local51];
		this.vertexZ = new int[local51];
		@Pc(66) int[] underlayHSLValues = new int[local51];
		@Pc(69) int[] overlayHSLValues = new int[local51];
		@Pc(73) int worldX = localX * TILE_SIZE;
		@Pc(77) int worldZ = localZ * TILE_SIZE;
		@Pc(86) int vertexIndex;
		@Pc(142) int x;
		@Pc(144) int z;
		@Pc(146) int height;
		@Pc(148) int underlayHSL;
		@Pc(150) int overlayHSL;
		for (@Pc(79) int local79 = 0; local79 < local51; local79++) {
			vertexIndex = shapedTileMesh[local79];
			if ((vertexIndex & 0x1) == 0 && vertexIndex <= 8) {
				vertexIndex = (vertexIndex - rotation - rotation - 1 & 0x7) + 1;
			}
			if (vertexIndex > 8 && vertexIndex <= 12) {
				vertexIndex = (vertexIndex - rotation - 9 & 0x3) + 9;
			}
			if (vertexIndex > 12 && vertexIndex <= 16) {
				vertexIndex = (vertexIndex - rotation - 13 & 0x3) + 13;
			}
			if (vertexIndex == 1) {
				x = worldX;
				z = worldZ;
				height = swHeight;
				underlayHSL = swUnderlayHSL;
				overlayHSL = swOverlayHSL;
			} else if (vertexIndex == 2) {
				x = worldX + HALF_TILE;
				z = worldZ;
				height = swHeight + seHeight >> 1;
				underlayHSL = swUnderlayHSL + seUnderlayHSL >> 1;
				overlayHSL = swOverlayHSL + seOverlayHSL >> 1;
			} else if (vertexIndex == 3) {
				x = worldX + TILE_SIZE;
				z = worldZ;
				height = seHeight;
				underlayHSL = seUnderlayHSL;
				overlayHSL = seOverlayHSL;
			} else if (vertexIndex == 4) {
				x = worldX + TILE_SIZE;
				z = worldZ + HALF_TILE;
				height = seHeight + neHeight >> 1;
				underlayHSL = seUnderlayHSL + neUnderlayHSL >> 1;
				overlayHSL = seOverlayHSL + neOverlayHSL >> 1;
			} else if (vertexIndex == 5) {
				x = worldX + TILE_SIZE;
				z = worldZ + TILE_SIZE;
				height = neHeight;
				underlayHSL = neUnderlayHSL;
				overlayHSL = neOverlayHSL;
			} else if (vertexIndex == 6) {
				x = worldX + HALF_TILE;
				z = worldZ + TILE_SIZE;
				height = neHeight + nwHeight >> 1;
				underlayHSL = neUnderlayHSL + nwUnderlayHSL >> 1;
				overlayHSL = neOverlayHSL + nwOverlayHSL >> 1;
			} else if (vertexIndex == 7) {
				x = worldX;
				z = worldZ + TILE_SIZE;
				height = nwHeight;
				underlayHSL = nwUnderlayHSL;
				overlayHSL = nwOverlayHSL;
			} else if (vertexIndex == 8) {
				x = worldX;
				z = worldZ + HALF_TILE;
				height = nwHeight + swHeight >> 1;
				underlayHSL = nwUnderlayHSL + swUnderlayHSL >> 1;
				overlayHSL = nwOverlayHSL + swOverlayHSL >> 1;
			} else if (vertexIndex == 9) {
				x = worldX + HALF_TILE;
				z = worldZ + QUARTER_TILE;
				height = swHeight + seHeight >> 1;
				underlayHSL = swUnderlayHSL + seUnderlayHSL >> 1;
				overlayHSL = swOverlayHSL + seOverlayHSL >> 1;
			} else if (vertexIndex == 10) {
				x = worldX + THREE_QUARTER_TILE;
				z = worldZ + HALF_TILE;
				height = seHeight + neHeight >> 1;
				underlayHSL = seUnderlayHSL + neUnderlayHSL >> 1;
				overlayHSL = seOverlayHSL + neOverlayHSL >> 1;
			} else if (vertexIndex == 11) {
				x = worldX + HALF_TILE;
				z = worldZ + THREE_QUARTER_TILE;
				height = neHeight + nwHeight >> 1;
				underlayHSL = neUnderlayHSL + nwUnderlayHSL >> 1;
				overlayHSL = neOverlayHSL + nwOverlayHSL >> 1;
			} else if (vertexIndex == 12) {
				x = worldX + QUARTER_TILE;
				z = worldZ + HALF_TILE;
				height = nwHeight + swHeight >> 1;
				underlayHSL = nwUnderlayHSL + swUnderlayHSL >> 1;
				overlayHSL = nwOverlayHSL + swOverlayHSL >> 1;
			} else if (vertexIndex == 13) {
				x = worldX + QUARTER_TILE;
				z = worldZ + QUARTER_TILE;
				height = swHeight;
				underlayHSL = swUnderlayHSL;
				overlayHSL = swOverlayHSL;
			} else if (vertexIndex == 14) {
				x = worldX + THREE_QUARTER_TILE;
				z = worldZ + QUARTER_TILE;
				height = seHeight;
				underlayHSL = seUnderlayHSL;
				overlayHSL = seOverlayHSL;
			} else if (vertexIndex == 15) {
				x = worldX + THREE_QUARTER_TILE;
				z = worldZ + THREE_QUARTER_TILE;
				height = neHeight;
				underlayHSL = neUnderlayHSL;
				overlayHSL = neOverlayHSL;
			} else {
				x = worldX + QUARTER_TILE;
				z = worldZ + THREE_QUARTER_TILE;
				height = nwHeight;
				underlayHSL = nwUnderlayHSL;
				overlayHSL = nwOverlayHSL;
			}
			this.vertexX[local79] = x;
			this.vertexY[local79] = height;
			this.vertexZ[local79] = z;
			underlayHSLValues[local79] = underlayHSL;
			overlayHSLValues[local79] = overlayHSL;
		}
		@Pc(534) int[] triangleData = SHAPE_TRIANGLE_DATA[shapeType];
		vertexIndex = triangleData.length / 4;
		this.triangleA = new int[vertexIndex];
		this.triangleB = new int[vertexIndex];
		this.triangleC = new int[vertexIndex];
		this.triangleHSLA = new int[vertexIndex];
		this.triangleHSLB = new int[vertexIndex];
		this.triangleHSLC = new int[vertexIndex];
		if (texture != -1) {
			this.triangleTextureIds = new int[vertexIndex];
		}
		x = 0;
		for (z = 0; z < vertexIndex; z++) {
			height = triangleData[x];
			underlayHSL = triangleData[x + 1];
			overlayHSL = triangleData[x + 2];
			@Pc(599) int vertexCIndex = triangleData[x + 3];
			x += 4;
			if (underlayHSL < 4) {
				underlayHSL = underlayHSL - rotation & 0x3;
			}
			if (overlayHSL < 4) {
				overlayHSL = overlayHSL - rotation & 0x3;
			}
			if (vertexCIndex < 4) {
				vertexCIndex = vertexCIndex - rotation & 0x3;
			}
			this.triangleA[z] = underlayHSL;
			this.triangleB[z] = overlayHSL;
			this.triangleC[z] = vertexCIndex;
			if (height == 0) {
				this.triangleHSLA[z] = underlayHSLValues[underlayHSL];
				this.triangleHSLB[z] = underlayHSLValues[overlayHSL];
				this.triangleHSLC[z] = underlayHSLValues[vertexCIndex];
				if (this.triangleTextureIds != null) {
					this.triangleTextureIds[z] = -1;
				}
			} else {
				this.triangleHSLA[z] = overlayHSLValues[underlayHSL];
				this.triangleHSLB[z] = overlayHSLValues[overlayHSL];
				this.triangleHSLC[z] = overlayHSLValues[vertexCIndex];
				if (this.triangleTextureIds != null) {
					this.triangleTextureIds[z] = texture;
				}
			}
		}
		z = swHeight;
		height = seHeight;
		if (seHeight < swHeight) {
			z = seHeight;
		}
		if (seHeight > seHeight) {
			height = seHeight;
		}
		if (neHeight < z) {
			z = neHeight;
		}
		if (neHeight > seHeight) {
			height = neHeight;
		}
		if (nwHeight < z) {
			;
		}
		if (nwHeight > height) {
			;
		}
	}
}
