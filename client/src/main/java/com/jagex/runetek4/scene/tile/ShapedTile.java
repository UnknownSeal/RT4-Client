package com.jagex.runetek4.scene.tile;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!fg")
public final class ShapedTile {

	@OriginalMember(owner = "runetek4.client!fg", name = "i", descriptor = "[[I")
	public static final int[][] shapedTilePointData = new int[][] { { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 2, 6 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 13, 14 } };
	@OriginalMember(owner = "runetek4.client!fg", name = "j", descriptor = "[[I")
	public static final int[][] shapedTileElementData = new int[][] { { 0, 1, 2, 3, 0, 0, 1, 3 }, { 1, 1, 2, 3, 1, 0, 1, 3 }, { 0, 1, 2, 3, 1, 0, 1, 3 }, { 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 }, { 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 }, { 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 }, { 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 }, { 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 }, { 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 }, { 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 }, { 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3 }, { 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3 }, { 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5 } };
	@OriginalMember(owner = "runetek4.client!fg", name = "s", descriptor = "Z")
	public boolean aBoolean113 = true;

	@OriginalMember(owner = "runetek4.client!fg", name = "a", descriptor = "I")
	public final int anInt1966;

	@OriginalMember(owner = "runetek4.client!fg", name = "b", descriptor = "I")
	public final int anInt1967;

	@OriginalMember(owner = "runetek4.client!fg", name = "p", descriptor = "I")
	public final int underlayRGB;

	@OriginalMember(owner = "runetek4.client!fg", name = "k", descriptor = "I")
	public final int overlayRGB;

	@OriginalMember(owner = "runetek4.client!fg", name = "q", descriptor = "[I")
	public final int[] anIntArray168;

	@OriginalMember(owner = "runetek4.client!fg", name = "e", descriptor = "[I")
	public final int[] anIntArray160;

	@OriginalMember(owner = "runetek4.client!fg", name = "h", descriptor = "[I")
	public final int[] anIntArray163;

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
	public ShapedTile(@OriginalArg(0) int arg0, @OriginalArg(1) int rotation, @OriginalArg(2) int texture, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14, @OriginalArg(15) int arg15, @OriginalArg(16) int arg16, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18) {
		if (arg5 != arg6 || arg5 != arg7 || arg5 != arg8) {
			this.aBoolean113 = false;
		}
		this.anInt1966 = arg0;
		this.anInt1967 = rotation;
		this.underlayRGB = arg17;
		this.overlayRGB = arg18;
		@Pc(30) short c = 128;
		@Pc(34) int local34 = c / 2;
		@Pc(38) int local38 = c / 4;
		@Pc(44) int local44 = c * 3 / 4;
		@Pc(48) int[] shapedTileMesh = shapedTilePointData[arg0];
		@Pc(51) int local51 = shapedTileMesh.length;
		this.anIntArray168 = new int[local51];
		this.anIntArray160 = new int[local51];
		this.anIntArray163 = new int[local51];
		@Pc(66) int[] local66 = new int[local51];
		@Pc(69) int[] local69 = new int[local51];
		@Pc(73) int local73 = arg3 * c;
		@Pc(77) int local77 = arg4 * c;
		@Pc(86) int vertexCount;
		@Pc(142) int offset;
		@Pc(144) int vertex;
		@Pc(146) int overlayOrUnderlay;
		@Pc(148) int idxA;
		@Pc(150) int idxB;
		for (@Pc(79) int local79 = 0; local79 < local51; local79++) {
			vertexCount = shapedTileMesh[local79];
			if ((vertexCount & 0x1) == 0 && vertexCount <= 8) {
				vertexCount = (vertexCount - rotation - rotation - 1 & 0x7) + 1;
			}
			if (vertexCount > 8 && vertexCount <= 12) {
				vertexCount = (vertexCount - rotation - 9 & 0x3) + 9;
			}
			if (vertexCount > 12 && vertexCount <= 16) {
				vertexCount = (vertexCount - rotation - 13 & 0x3) + 13;
			}
			if (vertexCount == 1) {
				offset = local73;
				vertex = local77;
				overlayOrUnderlay = arg5;
				idxA = arg9;
				idxB = arg13;
			} else if (vertexCount == 2) {
				offset = local73 + local34;
				vertex = local77;
				overlayOrUnderlay = arg5 + arg6 >> 1;
				idxA = arg9 + arg10 >> 1;
				idxB = arg13 + arg14 >> 1;
			} else if (vertexCount == 3) {
				offset = local73 + c;
				vertex = local77;
				overlayOrUnderlay = arg6;
				idxA = arg10;
				idxB = arg14;
			} else if (vertexCount == 4) {
				offset = local73 + c;
				vertex = local77 + local34;
				overlayOrUnderlay = arg6 + arg7 >> 1;
				idxA = arg10 + arg11 >> 1;
				idxB = arg14 + arg15 >> 1;
			} else if (vertexCount == 5) {
				offset = local73 + c;
				vertex = local77 + c;
				overlayOrUnderlay = arg7;
				idxA = arg11;
				idxB = arg15;
			} else if (vertexCount == 6) {
				offset = local73 + local34;
				vertex = local77 + c;
				overlayOrUnderlay = arg7 + arg8 >> 1;
				idxA = arg11 + arg12 >> 1;
				idxB = arg15 + arg16 >> 1;
			} else if (vertexCount == 7) {
				offset = local73;
				vertex = local77 + c;
				overlayOrUnderlay = arg8;
				idxA = arg12;
				idxB = arg16;
			} else if (vertexCount == 8) {
				offset = local73;
				vertex = local77 + local34;
				overlayOrUnderlay = arg8 + arg5 >> 1;
				idxA = arg12 + arg9 >> 1;
				idxB = arg16 + arg13 >> 1;
			} else if (vertexCount == 9) {
				offset = local73 + local34;
				vertex = local77 + local38;
				overlayOrUnderlay = arg5 + arg6 >> 1;
				idxA = arg9 + arg10 >> 1;
				idxB = arg13 + arg14 >> 1;
			} else if (vertexCount == 10) {
				offset = local73 + local44;
				vertex = local77 + local34;
				overlayOrUnderlay = arg6 + arg7 >> 1;
				idxA = arg10 + arg11 >> 1;
				idxB = arg14 + arg15 >> 1;
			} else if (vertexCount == 11) {
				offset = local73 + local34;
				vertex = local77 + local44;
				overlayOrUnderlay = arg7 + arg8 >> 1;
				idxA = arg11 + arg12 >> 1;
				idxB = arg15 + arg16 >> 1;
			} else if (vertexCount == 12) {
				offset = local73 + local38;
				vertex = local77 + local34;
				overlayOrUnderlay = arg8 + arg5 >> 1;
				idxA = arg12 + arg9 >> 1;
				idxB = arg16 + arg13 >> 1;
			} else if (vertexCount == 13) {
				offset = local73 + local38;
				vertex = local77 + local38;
				overlayOrUnderlay = arg5;
				idxA = arg9;
				idxB = arg13;
			} else if (vertexCount == 14) {
				offset = local73 + local44;
				vertex = local77 + local38;
				overlayOrUnderlay = arg6;
				idxA = arg10;
				idxB = arg14;
			} else if (vertexCount == 15) {
				offset = local73 + local44;
				vertex = local77 + local44;
				overlayOrUnderlay = arg7;
				idxA = arg11;
				idxB = arg15;
			} else {
				offset = local73 + local38;
				vertex = local77 + local44;
				overlayOrUnderlay = arg8;
				idxA = arg12;
				idxB = arg16;
			}
			this.anIntArray168[local79] = offset;
			this.anIntArray160[local79] = overlayOrUnderlay;
			this.anIntArray163[local79] = vertex;
			local66[local79] = idxA;
			local69[local79] = idxB;
		}
		@Pc(534) int[] shapedTileElements = shapedTileElementData[arg0];
		vertexCount = shapedTileElements.length / 4;
		this.triangleA = new int[vertexCount];
		this.triangleB = new int[vertexCount];
		this.triangleC = new int[vertexCount];
		this.triangleHSLA = new int[vertexCount];
		this.triangleHSLB = new int[vertexCount];
		this.triangleHSLC = new int[vertexCount];
		if (texture != -1) {
			this.triangleTextureIds = new int[vertexCount];
		}
		offset = 0;
		for (vertex = 0; vertex < vertexCount; vertex++) {
			overlayOrUnderlay = shapedTileElements[offset];
			idxA = shapedTileElements[offset + 1];
			idxB = shapedTileElements[offset + 2];
			@Pc(599) int idxC = shapedTileElements[offset + 3];
			offset += 4;
			if (idxA < 4) {
				idxA = idxA - rotation & 0x3;
			}
			if (idxB < 4) {
				idxB = idxB - rotation & 0x3;
			}
			if (idxC < 4) {
				idxC = idxC - rotation & 0x3;
			}
			this.triangleA[vertex] = idxA;
			this.triangleB[vertex] = idxB;
			this.triangleC[vertex] = idxC;
			if (overlayOrUnderlay == 0) {
				this.triangleHSLA[vertex] = local66[idxA];
				this.triangleHSLB[vertex] = local66[idxB];
				this.triangleHSLC[vertex] = local66[idxC];
				if (this.triangleTextureIds != null) {
					this.triangleTextureIds[vertex] = -1;
				}
			} else {
				this.triangleHSLA[vertex] = local69[idxA];
				this.triangleHSLB[vertex] = local69[idxB];
				this.triangleHSLC[vertex] = local69[idxC];
				if (this.triangleTextureIds != null) {
					this.triangleTextureIds[vertex] = texture;
				}
			}
		}
		vertex = arg5;
		overlayOrUnderlay = arg6;
		if (arg6 < arg5) {
			vertex = arg6;
		}
		if (arg6 > arg6) {
			overlayOrUnderlay = arg6;
		}
		if (arg7 < vertex) {
			vertex = arg7;
		}
		if (arg7 > arg6) {
			overlayOrUnderlay = arg7;
		}
		if (arg8 < vertex) {
			;
		}
		if (arg8 > overlayOrUnderlay) {
			;
		}
	}
}
