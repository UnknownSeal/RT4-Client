package com.jagex.runetek4.graphics;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.Entity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gb")
public final class ModelUnlit extends Entity {

	@OriginalMember(owner = "client!gb", name = "s", descriptor = "[B")
	public byte[] aByteArray26;

	@OriginalMember(owner = "client!gb", name = "t", descriptor = "S")
	private short aShort13;

	@OriginalMember(owner = "client!gb", name = "u", descriptor = "[B")
	public byte[] aByteArray27;

	@OriginalMember(owner = "client!gb", name = "v", descriptor = "[S")
	public short[] textureTriangleVertex2;

	@OriginalMember(owner = "client!gb", name = "w", descriptor = "[I")
	public int[] anIntArray192;

	@OriginalMember(owner = "client!gb", name = "z", descriptor = "S")
	private short aShort14;

	@OriginalMember(owner = "client!gb", name = "A", descriptor = "[S")
	public short[] aShortArray21;

	@OriginalMember(owner = "client!gb", name = "B", descriptor = "[B")
	public byte[] textureTriangleTranslationU;

	@OriginalMember(owner = "client!gb", name = "D", descriptor = "S")
	private short aShort15;

	@OriginalMember(owner = "client!gb", name = "E", descriptor = "[S")
	public short[] aShortArray22;

	@OriginalMember(owner = "client!gb", name = "F", descriptor = "[[I")
	public int[][] anIntArrayArray15;

	@OriginalMember(owner = "client!gb", name = "G", descriptor = "[B")
	public byte[] aByteArray29;

	@OriginalMember(owner = "client!gb", name = "H", descriptor = "[S")
	public short[] unmodifiedTriangleTexture;

	@OriginalMember(owner = "client!gb", name = "I", descriptor = "[B")
	public byte[] aByteArray30;

	@OriginalMember(owner = "client!gb", name = "J", descriptor = "[I")
	public int[] anIntArray193;

	@OriginalMember(owner = "client!gb", name = "M", descriptor = "I")
	public int texturedFaceCount;

	@OriginalMember(owner = "client!gb", name = "O", descriptor = "[I")
	public int[] anIntArray196;

	@OriginalMember(owner = "client!gb", name = "P", descriptor = "[I")
	public int[] anIntArray197;

	@OriginalMember(owner = "client!gb", name = "Q", descriptor = "[B")
	public byte[] aByteArray31;

	@OriginalMember(owner = "client!gb", name = "R", descriptor = "[B")
	public byte[] aByteArray32;

	@OriginalMember(owner = "client!gb", name = "T", descriptor = "[B")
	public byte[] textureTriangleTranslationV;

	@OriginalMember(owner = "client!gb", name = "U", descriptor = "[S")
	public short[] unmodifiedTriangleColour;

	@OriginalMember(owner = "client!gb", name = "W", descriptor = "[Lclient!hd;")
	public VertexNormal[] aVertexNormalArray1;

	@OriginalMember(owner = "client!gb", name = "X", descriptor = "S")
	private short aShort16;

	@OriginalMember(owner = "client!gb", name = "Y", descriptor = "S")
	private short aShort17;

	@OriginalMember(owner = "client!gb", name = "ab", descriptor = "S")
	public short aShort18;

	@OriginalMember(owner = "client!gb", name = "bb", descriptor = "[B")
	public byte[] aByteArray34;

	@OriginalMember(owner = "client!gb", name = "cb", descriptor = "[I")
	public int[] anIntArray200;

	@OriginalMember(owner = "client!gb", name = "db", descriptor = "S")
	public short aShort19;

	@OriginalMember(owner = "client!gb", name = "eb", descriptor = "S")
	private short aShort20;

	@OriginalMember(owner = "client!gb", name = "fb", descriptor = "[I")
	public int[] anIntArray201;

	@OriginalMember(owner = "client!gb", name = "gb", descriptor = "[S")
	public short[] aShortArray25;

	@OriginalMember(owner = "client!gb", name = "hb", descriptor = "[Lclient!hd;")
	public VertexNormal[] aVertexNormalArray2;

	@OriginalMember(owner = "client!gb", name = "ib", descriptor = "[S")
	public short[] textureTriangleVertex1;

	@OriginalMember(owner = "client!gb", name = "jb", descriptor = "[I")
	public int[] anIntArray202;

	@OriginalMember(owner = "client!gb", name = "kb", descriptor = "[Lclient!qj;")
	public TriangleNormal[] aClass126Array1;

	@OriginalMember(owner = "client!gb", name = "mb", descriptor = "[S")
	public short[] aShortArray27;

	@OriginalMember(owner = "client!gb", name = "nb", descriptor = "[B")
	public byte[] facePriorities;

	@OriginalMember(owner = "client!gb", name = "ob", descriptor = "[[I")
	public int[][] anIntArrayArray16;

	@OriginalMember(owner = "client!gb", name = "pb", descriptor = "[S")
	public short[] textureTriangleVertex3;

	@OriginalMember(owner = "client!gb", name = "qb", descriptor = "[I")
	public int[] anIntArray203;

	@OriginalMember(owner = "client!gb", name = "rb", descriptor = "[S")
	public short[] vertexSourceModels;

	@OriginalMember(owner = "client!gb", name = "C", descriptor = "Z")
	private boolean aBoolean121 = false;

	@OriginalMember(owner = "client!gb", name = "y", descriptor = "I")
	public int faceCount = 0;

	@OriginalMember(owner = "client!gb", name = "x", descriptor = "B")
	public byte modelPriority = 0;

	@OriginalMember(owner = "client!gb", name = "lb", descriptor = "I")
	public int vertexCount = 0;

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "()V")
	private ModelUnlit() {
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "([B)V")
	public ModelUnlit(@OriginalArg(0) byte[] arg0) {
		if (arg0[arg0.length - 1] == -1 && arg0[arg0.length - 2] == -1) {
			this.method1674(arg0);
		} else {
			this.method1688(arg0);
		}
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "(III)V")
	public ModelUnlit(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.anIntArray202 = new int[arg0];
		this.anIntArray201 = new int[arg0];
		this.anIntArray203 = new int[arg0];
		this.anIntArray193 = new int[arg0];
		this.anIntArray197 = new int[arg1];
		this.anIntArray200 = new int[arg1];
		this.anIntArray196 = new int[arg1];
		this.aByteArray30 = new byte[arg1];
		this.facePriorities = new byte[arg1];
		this.aByteArray26 = new byte[arg1];
		this.unmodifiedTriangleColour = new short[arg1];
		this.unmodifiedTriangleTexture = new short[arg1];
		this.aByteArray31 = new byte[arg1];
		this.anIntArray192 = new int[arg1];
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "([Lclient!gb;I)V")
	public ModelUnlit(@OriginalArg(0) ModelUnlit[] count, @OriginalArg(1) int arg1) {
		@Pc(15) boolean local15 = false;
		@Pc(17) boolean copyPriority = false;
		@Pc(19) boolean local19 = false;
		@Pc(21) boolean local21 = false;
		@Pc(23) boolean local23 = false;
		@Pc(25) boolean local25 = false;
		this.vertexCount = 0;
		this.faceCount = 0;
		this.texturedFaceCount = 0;
		this.modelPriority = -1;
		@Pc(43) int i;
		for (i = 0; i < arg1; i++) {
			@Pc(50) ModelUnlit model = count[i];
			if (model != null) {
				this.vertexCount += model.vertexCount;
				this.faceCount += model.faceCount;
				this.texturedFaceCount += model.texturedFaceCount;
				if (model.facePriorities == null) {
					if (this.modelPriority == -1) {
						this.modelPriority = model.modelPriority;
					}
					if (this.modelPriority != model.modelPriority) {
						copyPriority = true;
					}
				} else {
					copyPriority = true;
				}
				local15 |= model.aByteArray30 != null;
				local19 |= model.aByteArray26 != null;
				local21 |= model.anIntArray192 != null;
				local23 |= model.unmodifiedTriangleTexture != null;
				local25 |= model.aByteArray31 != null;
			}
		}
		this.anIntArray202 = new int[this.vertexCount];
		this.anIntArray201 = new int[this.vertexCount];
		this.anIntArray203 = new int[this.vertexCount];
		this.anIntArray193 = new int[this.vertexCount];
		this.vertexSourceModels = new short[this.vertexCount];
		this.anIntArray197 = new int[this.faceCount];
		this.anIntArray200 = new int[this.faceCount];
		this.anIntArray196 = new int[this.faceCount];
		if (local15) {
			this.aByteArray30 = new byte[this.faceCount];
		}
		if (copyPriority) {
			this.facePriorities = new byte[this.faceCount];
		}
		if (local19) {
			this.aByteArray26 = new byte[this.faceCount];
		}
		if (local21) {
			this.anIntArray192 = new int[this.faceCount];
		}
		if (local23) {
			this.unmodifiedTriangleTexture = new short[this.faceCount];
		}
		if (local25) {
			this.aByteArray31 = new byte[this.faceCount];
		}
		this.unmodifiedTriangleColour = new short[this.faceCount];
		this.aShortArray22 = new short[this.faceCount];
		if (this.texturedFaceCount > 0) {
			this.aByteArray29 = new byte[this.texturedFaceCount];
			this.textureTriangleVertex1 = new short[this.texturedFaceCount];
			this.textureTriangleVertex2 = new short[this.texturedFaceCount];
			this.textureTriangleVertex3 = new short[this.texturedFaceCount];
			this.aShortArray27 = new short[this.texturedFaceCount];
			this.aShortArray25 = new short[this.texturedFaceCount];
			this.aShortArray21 = new short[this.texturedFaceCount];
			this.aByteArray27 = new byte[this.texturedFaceCount];
			this.aByteArray32 = new byte[this.texturedFaceCount];
			this.aByteArray34 = new byte[this.texturedFaceCount];
			this.textureTriangleTranslationU = new byte[this.texturedFaceCount];
			this.textureTriangleTranslationV = new byte[this.texturedFaceCount];
		}
		this.vertexCount = 0;
		this.faceCount = 0;
		this.texturedFaceCount = 0;
		for (i = 0; i < arg1; i++) {
			@Pc(323) short local323 = (short) (0x1 << i);
			@Pc(327) ModelUnlit model = count[i];
			if (model != null) {
				@Pc(331) int f;
				for (f = 0; f < model.faceCount; f++) {
					if (local15 && model.aByteArray30 != null) {
						this.aByteArray30[this.faceCount] = model.aByteArray30[f];
					}
					if (copyPriority) {
						if (model.facePriorities == null) {
							this.facePriorities[this.faceCount] = model.modelPriority;
						} else {
							this.facePriorities[this.faceCount] = model.facePriorities[f];
						}
					}
					if (local19 && model.aByteArray26 != null) {
						this.aByteArray26[this.faceCount] = model.aByteArray26[f];
					}
					if (local21 && model.anIntArray192 != null) {
						this.anIntArray192[this.faceCount] = model.anIntArray192[f];
					}
					if (local23) {
						if (model.unmodifiedTriangleTexture == null) {
							this.unmodifiedTriangleTexture[this.faceCount] = -1;
						} else {
							this.unmodifiedTriangleTexture[this.faceCount] = model.unmodifiedTriangleTexture[f];
						}
					}
					if (local25) {
						if (model.aByteArray31 == null || model.aByteArray31[f] == -1) {
							this.aByteArray31[this.faceCount] = -1;
						} else {
							this.aByteArray31[this.faceCount] = (byte) (model.aByteArray31[f] + this.texturedFaceCount);
						}
					}
					this.unmodifiedTriangleColour[this.faceCount] = model.unmodifiedTriangleColour[f];
					this.aShortArray22[this.faceCount] = local323;
					this.anIntArray197[this.faceCount] = this.method1666(model, model.anIntArray197[f], local323);
					this.anIntArray200[this.faceCount] = this.method1666(model, model.anIntArray200[f], local323);
					this.anIntArray196[this.faceCount] = this.method1666(model, model.anIntArray196[f], local323);
					this.faceCount++;
				}
				for (f = 0; f < model.texturedFaceCount; f++) {
					@Pc(530) byte local530 = this.aByteArray29[this.texturedFaceCount] = model.aByteArray29[f];
					if (local530 == 0) {
						this.textureTriangleVertex1[this.texturedFaceCount] = (short) this.method1666(model, model.textureTriangleVertex1[f], local323);
						this.textureTriangleVertex2[this.texturedFaceCount] = (short) this.method1666(model, model.textureTriangleVertex2[f], local323);
						this.textureTriangleVertex3[this.texturedFaceCount] = (short) this.method1666(model, model.textureTriangleVertex3[f], local323);
					}
					if (local530 >= 1 && local530 <= 3) {
						this.textureTriangleVertex1[this.texturedFaceCount] = model.textureTriangleVertex1[f];
						this.textureTriangleVertex2[this.texturedFaceCount] = model.textureTriangleVertex2[f];
						this.textureTriangleVertex3[this.texturedFaceCount] = model.textureTriangleVertex3[f];
						this.aShortArray27[this.texturedFaceCount] = model.aShortArray27[f];
						this.aShortArray25[this.texturedFaceCount] = model.aShortArray25[f];
						this.aShortArray21[this.texturedFaceCount] = model.aShortArray21[f];
						this.aByteArray27[this.texturedFaceCount] = model.aByteArray27[f];
						this.aByteArray32[this.texturedFaceCount] = model.aByteArray32[f];
						this.aByteArray34[this.texturedFaceCount] = model.aByteArray34[f];
					}
					if (local530 == 2) {
						this.textureTriangleTranslationU[this.texturedFaceCount] = model.textureTriangleTranslationU[f];
						this.textureTriangleTranslationV[this.texturedFaceCount] = model.textureTriangleTranslationV[f];
					}
					this.texturedFaceCount++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "(Lclient!gb;ZZZZ)V")
	public ModelUnlit(@OriginalArg(0) ModelUnlit arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) boolean arg4) {
		this.vertexCount = arg0.vertexCount;
		this.faceCount = arg0.faceCount;
		this.texturedFaceCount = arg0.texturedFaceCount;
		@Pc(57) int local57;
		if (arg1) {
			this.anIntArray202 = arg0.anIntArray202;
			this.anIntArray201 = arg0.anIntArray201;
			this.anIntArray203 = arg0.anIntArray203;
		} else {
			this.anIntArray202 = new int[this.vertexCount];
			this.anIntArray201 = new int[this.vertexCount];
			this.anIntArray203 = new int[this.vertexCount];
			for (local57 = 0; local57 < this.vertexCount; local57++) {
				this.anIntArray202[local57] = arg0.anIntArray202[local57];
				this.anIntArray201[local57] = arg0.anIntArray201[local57];
				this.anIntArray203[local57] = arg0.anIntArray203[local57];
			}
		}
		if (arg2) {
			this.unmodifiedTriangleColour = arg0.unmodifiedTriangleColour;
		} else {
			this.unmodifiedTriangleColour = new short[this.faceCount];
			for (local57 = 0; local57 < this.faceCount; local57++) {
				this.unmodifiedTriangleColour[local57] = arg0.unmodifiedTriangleColour[local57];
			}
		}
		if (arg3 || arg0.unmodifiedTriangleTexture == null) {
			this.unmodifiedTriangleTexture = arg0.unmodifiedTriangleTexture;
		} else {
			this.unmodifiedTriangleTexture = new short[this.faceCount];
			for (local57 = 0; local57 < this.faceCount; local57++) {
				this.unmodifiedTriangleTexture[local57] = arg0.unmodifiedTriangleTexture[local57];
			}
		}
		this.aByteArray26 = arg0.aByteArray26;
		this.anIntArray197 = arg0.anIntArray197;
		this.anIntArray200 = arg0.anIntArray200;
		this.anIntArray196 = arg0.anIntArray196;
		this.aByteArray30 = arg0.aByteArray30;
		this.facePriorities = arg0.facePriorities;
		this.aByteArray31 = arg0.aByteArray31;
		this.modelPriority = arg0.modelPriority;
		this.aByteArray29 = arg0.aByteArray29;
		this.textureTriangleVertex1 = arg0.textureTriangleVertex1;
		this.textureTriangleVertex2 = arg0.textureTriangleVertex2;
		this.textureTriangleVertex3 = arg0.textureTriangleVertex3;
		this.aShortArray27 = arg0.aShortArray27;
		this.aShortArray25 = arg0.aShortArray25;
		this.aShortArray21 = arg0.aShortArray21;
		this.aByteArray27 = arg0.aByteArray27;
		this.aByteArray32 = arg0.aByteArray32;
		this.aByteArray34 = arg0.aByteArray34;
		this.textureTriangleTranslationU = arg0.textureTriangleTranslationU;
		this.textureTriangleTranslationV = arg0.textureTriangleTranslationV;
		this.anIntArray193 = arg0.anIntArray193;
		this.anIntArray192 = arg0.anIntArray192;
		this.anIntArrayArray16 = arg0.anIntArrayArray16;
		this.anIntArrayArray15 = arg0.anIntArrayArray15;
		this.aVertexNormalArray1 = arg0.aVertexNormalArray1;
		this.aClass126Array1 = arg0.aClass126Array1;
		this.aVertexNormalArray2 = arg0.aVertexNormalArray2;
		this.aShort19 = arg0.aShort19;
		this.aShort18 = arg0.aShort18;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!ve;II)Lclient!gb;")
	public static ModelUnlit get(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1) {
		@Pc(5) byte[] local5 = arg0.getfile(arg1, 0);
		return local5 == null ? null : new ModelUnlit(local5);
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "()V")
	public void method1660() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.anIntArray202[local1] = -this.anIntArray202[local1];
			this.anIntArray203[local1] = -this.anIntArray203[local1];
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "e", descriptor = "()V")
	public void method1662() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.anIntArray202[local1];
			this.anIntArray202[local1] = this.anIntArray203[local1];
			this.anIntArray203[local1] = -local10;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(I)V")
	private void method1663(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = Static77.anIntArray198[arg0];
		@Pc(7) int local7 = Static77.anIntArray195[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.anIntArray201[local9] * local3 + this.anIntArray202[local9] * local7 >> 16;
			this.anIntArray201[local9] = this.anIntArray201[local9] * local7 - this.anIntArray202[local9] * local3 >> 16;
			this.anIntArray202[local9] = local29;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "f", descriptor = "()V")
	private void method1664() {
		if (this.aBoolean121) {
			return;
		}
		this.aBoolean121 = true;
		@Pc(8) int local8 = 32767;
		@Pc(10) int local10 = 32767;
		@Pc(12) int local12 = 32767;
		@Pc(14) int local14 = -32768;
		@Pc(16) int local16 = -32768;
		@Pc(18) int local18 = -32768;
		for (@Pc(20) int local20 = 0; local20 < this.vertexCount; local20++) {
			@Pc(29) int local29 = this.anIntArray202[local20];
			@Pc(34) int local34 = this.anIntArray201[local20];
			@Pc(39) int local39 = this.anIntArray203[local20];
			if (local29 < local8) {
				local8 = local29;
			}
			if (local29 > local14) {
				local14 = local29;
			}
			if (local34 < local10) {
				local10 = local34;
			}
			if (local34 > local16) {
				local16 = local34;
			}
			if (local39 < local12) {
				local12 = local39;
			}
			if (local39 > local18) {
				local18 = local39;
			}
		}
		this.aShort14 = (short) local8;
		this.aShort16 = (short) local14;
		this.aShort15 = (short) local10;
		this.aShort13 = (short) local16;
		this.aShort20 = (short) local12;
		this.aShort17 = (short) local18;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(III)V")
	public void scale(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.anIntArray202[local1] = this.anIntArray202[local1] * arg0 / 128;
			this.anIntArray201[local1] = this.anIntArray201[local1] * arg1 / 128;
			this.anIntArray203[local1] = this.anIntArray203[local1] * arg2 / 128;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(III)Lclient!th;")
	@Override
	public Entity method4539() {
		return this.applyLightning(this.aShort19, this.aShort18, -50, -10, -50);
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!gb;IS)I")
	private int method1666(@OriginalArg(0) ModelUnlit arg0, @OriginalArg(1) int arg1, @OriginalArg(2) short arg2) {
		@Pc(4) int local4 = arg0.anIntArray202[arg1];
		@Pc(9) int local9 = arg0.anIntArray201[arg1];
		@Pc(14) int local14 = arg0.anIntArray203[arg1];
		for (@Pc(16) int local16 = 0; local16 < this.vertexCount; local16++) {
			if (local4 == this.anIntArray202[local16] && local9 == this.anIntArray201[local16] && local14 == this.anIntArray203[local16]) {
				this.vertexSourceModels[local16] |= arg2;
				return local16;
			}
		}
		this.anIntArray202[this.vertexCount] = local4;
		this.anIntArray201[this.vertexCount] = local9;
		this.anIntArray203[this.vertexCount] = local14;
		this.vertexSourceModels[this.vertexCount] = arg2;
		if (arg0.anIntArray193 != null) {
			this.anIntArray193[this.vertexCount] = arg0.anIntArray193[arg1];
		}
		return this.vertexCount++;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([[IIIIII)V")
	private void method1667(@OriginalArg(0) int[][] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(10) int local10 = -arg4 / 2;
		@Pc(15) int local15 = -arg5 / 2;
		@Pc(24) int local24 = Static77.method1680(arg0, arg1 + local10, arg3 + local15);
		@Pc(28) int local28 = arg4 / 2;
		@Pc(33) int local33 = -arg5 / 2;
		@Pc(42) int local42 = Static77.method1680(arg0, arg1 + local28, arg3 + local33);
		@Pc(47) int local47 = -arg4 / 2;
		@Pc(51) int local51 = arg5 / 2;
		@Pc(60) int local60 = Static77.method1680(arg0, arg1 + local47, arg3 + local51);
		@Pc(64) int local64 = arg4 / 2;
		@Pc(68) int local68 = arg5 / 2;
		@Pc(77) int local77 = Static77.method1680(arg0, arg1 + local64, arg3 + local68);
		@Pc(84) int local84 = local24 < local42 ? local24 : local42;
		@Pc(91) int local91 = local60 < local77 ? local60 : local77;
		@Pc(98) int local98 = local42 < local77 ? local42 : local77;
		@Pc(105) int local105 = local24 < local60 ? local24 : local60;
		if (arg5 != 0) {
			@Pc(120) int local120 = (int) (Math.atan2(local84 - local91, arg5) * 325.95D) & 0x7FF;
			if (local120 != 0) {
				this.method1677(local120);
			}
		}
		if (arg4 != 0) {
			@Pc(140) int local140 = (int) (Math.atan2(local105 - local98, arg4) * 325.95D) & 0x7FF;
			if (local140 != 0) {
				this.method1663(local140);
			}
		}
		@Pc(149) int local149 = local24 + local77;
		if (local42 + local60 < local149) {
			local149 = local42 + local60;
		}
		local149 = (local149 >> 1) - arg2;
		if (local149 != 0) {
			this.translate(0, local149, 0);
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
	}

	@OriginalMember(owner = "client!gb", name = "g", descriptor = "()V")
	public void method1668() {
		if (this.aVertexNormalArray1 != null) {
			return;
		}
		this.aVertexNormalArray1 = new VertexNormal[this.vertexCount];
		@Pc(10) int local10;
		for (local10 = 0; local10 < this.vertexCount; local10++) {
			this.aVertexNormalArray1[local10] = new VertexNormal();
		}
		for (local10 = 0; local10 < this.faceCount; local10++) {
			@Pc(34) int local34 = this.anIntArray197[local10];
			@Pc(39) int local39 = this.anIntArray200[local10];
			@Pc(44) int local44 = this.anIntArray196[local10];
			@Pc(54) int local54 = this.anIntArray202[local39] - this.anIntArray202[local34];
			@Pc(64) int local64 = this.anIntArray201[local39] - this.anIntArray201[local34];
			@Pc(74) int local74 = this.anIntArray203[local39] - this.anIntArray203[local34];
			@Pc(84) int local84 = this.anIntArray202[local44] - this.anIntArray202[local34];
			@Pc(94) int local94 = this.anIntArray201[local44] - this.anIntArray201[local34];
			@Pc(104) int local104 = this.anIntArray203[local44] - this.anIntArray203[local34];
			@Pc(112) int local112 = local64 * local104 - local94 * local74;
			@Pc(120) int local120 = local74 * local84 - local104 * local54;
			@Pc(128) int local128;
			for (local128 = local54 * local94 - local84 * local64; local112 > 8192 || local120 > 8192 || local128 > 8192 || local112 < -8192 || local120 < -8192 || local128 < -8192; local128 >>= 0x1) {
				local112 >>= 0x1;
				local120 >>= 0x1;
			}
			@Pc(174) int local174 = (int) Math.sqrt(local112 * local112 + local120 * local120 + local128 * local128);
			if (local174 <= 0) {
				local174 = 1;
			}
			local112 = local112 * 256 / local174;
			local120 = local120 * 256 / local174;
			local128 = local128 * 256 / local174;
			@Pc(201) byte local201;
			if (this.aByteArray30 == null) {
				local201 = 0;
			} else {
				local201 = this.aByteArray30[local10];
			}
			if (local201 == 0) {
				@Pc(214) VertexNormal local214 = this.aVertexNormalArray1[local34];
				local214.x += local112;
				local214.y += local120;
				local214.z += local128;
				local214.magnitude++;
				@Pc(243) VertexNormal local243 = this.aVertexNormalArray1[local39];
				local243.x += local112;
				local243.y += local120;
				local243.z += local128;
				local243.magnitude++;
				@Pc(272) VertexNormal local272 = this.aVertexNormalArray1[local44];
				local272.x += local112;
				local272.y += local120;
				local272.z += local128;
				local272.magnitude++;
			} else if (local201 == 1) {
				if (this.aClass126Array1 == null) {
					this.aClass126Array1 = new TriangleNormal[this.faceCount];
				}
				@Pc(317) TriangleNormal local317 = this.aClass126Array1[local10] = new TriangleNormal();
				local317.anInt4769 = local112;
				local317.anInt4770 = local120;
				local317.anInt4767 = local128;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(SS)V")
	public void retexture(@OriginalArg(0) short arg0, @OriginalArg(1) short arg1) {
		if (this.unmodifiedTriangleTexture == null) {
			return;
		}
		for (@Pc(5) int local5 = 0; local5 < this.faceCount; local5++) {
			if (this.unmodifiedTriangleTexture[local5] == arg0) {
				this.unmodifiedTriangleTexture[local5] = arg1;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(II[[I[[IIIIZZ)Lclient!gb;")
	public ModelUnlit method1670(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[][] arg2, @OriginalArg(3) int[][] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		this.method1664();
		@Pc(6) int local6 = arg4 + this.aShort14;
		@Pc(11) int local11 = arg4 + this.aShort16;
		@Pc(16) int local16 = arg6 + this.aShort20;
		@Pc(21) int local21 = arg6 + this.aShort17;
		if ((arg0 == 1 || arg0 == 2 || arg0 == 3 || arg0 == 5) && (local6 < 0 || local11 + 128 >> 7 >= arg2.length || local16 < 0 || local21 + 128 >> 7 >= arg2[0].length)) {
			return this;
		}
		if (arg0 == 4 || arg0 == 5) {
			if (arg3 == null) {
				return this;
			}
			if (local6 < 0 || local11 + 128 >> 7 >= arg3.length || local16 < 0 || local21 + 128 >> 7 >= arg3[0].length) {
				return this;
			}
		} else {
			local6 >>= 0x7;
			local11 = local11 + 127 >> 7;
			local16 >>= 0x7;
			local21 = local21 + 127 >> 7;
			if (arg2[local6][local16] == arg5 && arg2[local11][local16] == arg5 && arg2[local6][local21] == arg5 && arg2[local11][local21] == arg5) {
				return this;
			}
		}
		@Pc(147) ModelUnlit local147 = new ModelUnlit();
		local147.vertexCount = this.vertexCount;
		local147.faceCount = this.faceCount;
		local147.texturedFaceCount = this.texturedFaceCount;
		local147.anIntArray197 = this.anIntArray197;
		local147.anIntArray200 = this.anIntArray200;
		local147.anIntArray196 = this.anIntArray196;
		local147.aByteArray30 = this.aByteArray30;
		local147.facePriorities = this.facePriorities;
		local147.aByteArray26 = this.aByteArray26;
		local147.aByteArray31 = this.aByteArray31;
		local147.unmodifiedTriangleColour = this.unmodifiedTriangleColour;
		local147.unmodifiedTriangleTexture = this.unmodifiedTriangleTexture;
		local147.modelPriority = this.modelPriority;
		local147.aByteArray29 = this.aByteArray29;
		local147.textureTriangleVertex1 = this.textureTriangleVertex1;
		local147.textureTriangleVertex2 = this.textureTriangleVertex2;
		local147.textureTriangleVertex3 = this.textureTriangleVertex3;
		local147.aShortArray27 = this.aShortArray27;
		local147.aShortArray25 = this.aShortArray25;
		local147.aShortArray21 = this.aShortArray21;
		local147.aByteArray27 = this.aByteArray27;
		local147.aByteArray32 = this.aByteArray32;
		local147.aByteArray34 = this.aByteArray34;
		local147.textureTriangleTranslationU = this.textureTriangleTranslationU;
		local147.textureTriangleTranslationV = this.textureTriangleTranslationV;
		local147.anIntArray193 = this.anIntArray193;
		local147.anIntArray192 = this.anIntArray192;
		local147.anIntArrayArray16 = this.anIntArrayArray16;
		local147.anIntArrayArray15 = this.anIntArrayArray15;
		local147.aShort19 = this.aShort19;
		local147.aShort18 = this.aShort18;
		local147.aVertexNormalArray1 = this.aVertexNormalArray1;
		local147.aClass126Array1 = this.aClass126Array1;
		local147.aVertexNormalArray2 = this.aVertexNormalArray2;
		if (arg0 == 3) {
			local147.anIntArray202 = Static115.method2308(this.anIntArray202);
			local147.anIntArray201 = Static115.method2308(this.anIntArray201);
			local147.anIntArray203 = Static115.method2308(this.anIntArray203);
		} else {
			local147.anIntArray202 = this.anIntArray202;
			local147.anIntArray201 = new int[local147.vertexCount];
			local147.anIntArray203 = this.anIntArray203;
		}
		@Pc(326) int local326;
		@Pc(337) int local337;
		@Pc(344) int local344;
		@Pc(348) int local348;
		@Pc(352) int local352;
		@Pc(356) int local356;
		@Pc(360) int local360;
		@Pc(382) int local382;
		@Pc(408) int local408;
		@Pc(420) int local420;
		if (arg0 == 1) {
			for (local326 = 0; local326 < local147.vertexCount; local326++) {
				local337 = this.anIntArray202[local326] + arg4;
				local344 = this.anIntArray203[local326] + arg6;
				local348 = local337 & 0x7F;
				local352 = local344 & 0x7F;
				local356 = local337 >> 7;
				local360 = local344 >> 7;
				local382 = arg2[local356][local360] * (128 - local348) + arg2[local356 + 1][local360] * local348 >> 7;
				local408 = arg2[local356][local360 + 1] * (128 - local348) + arg2[local356 + 1][local360 + 1] * local348 >> 7;
				local420 = local382 * (128 - local352) + local408 * local352 >> 7;
				local147.anIntArray201[local326] = this.anIntArray201[local326] + local420 - arg5;
			}
		} else {
			@Pc(547) int local547;
			if (arg0 == 2) {
				for (local326 = 0; local326 < local147.vertexCount; local326++) {
					local337 = (this.anIntArray201[local326] << 16) / this.aShort15;
					if (local337 < arg1) {
						local344 = this.anIntArray202[local326] + arg4;
						local348 = this.anIntArray203[local326] + arg6;
						local352 = local344 & 0x7F;
						local356 = local348 & 0x7F;
						local360 = local344 >> 7;
						local382 = local348 >> 7;
						local408 = arg2[local360][local382] * (128 - local352) + arg2[local360 + 1][local382] * local352 >> 7;
						local420 = arg2[local360][local382 + 1] * (128 - local352) + arg2[local360 + 1][local382 + 1] * local352 >> 7;
						local547 = local408 * (128 - local356) + local420 * local356 >> 7;
						local147.anIntArray201[local326] = this.anIntArray201[local326] + (local547 - arg5) * (arg1 - local337) / arg1;
					} else {
						local147.anIntArray201[local326] = this.anIntArray201[local326];
					}
				}
			} else if (arg0 == 3) {
				local326 = (arg1 & 0xFF) * 4;
				local337 = (arg1 >> 8 & 0xFF) * 4;
				this.method1667(arg2, arg4, arg5, arg6, local326, local337);
			} else if (arg0 == 4) {
				local326 = this.aShort13 - this.aShort15;
				for (local337 = 0; local337 < this.vertexCount; local337++) {
					local344 = this.anIntArray202[local337] + arg4;
					local348 = this.anIntArray203[local337] + arg6;
					local352 = local344 & 0x7F;
					local356 = local348 & 0x7F;
					local360 = local344 >> 7;
					local382 = local348 >> 7;
					local408 = arg3[local360][local382] * (128 - local352) + arg3[local360 + 1][local382] * local352 >> 7;
					local420 = arg3[local360][local382 + 1] * (128 - local352) + arg3[local360 + 1][local382 + 1] * local352 >> 7;
					local547 = local408 * (128 - local356) + local420 * local356 >> 7;
					local147.anIntArray201[local337] = this.anIntArray201[local337] + local547 + local326 - arg5;
				}
			} else if (arg0 == 5) {
				local326 = this.aShort13 - this.aShort15;
				for (local337 = 0; local337 < this.vertexCount; local337++) {
					local344 = this.anIntArray202[local337] + arg4;
					local348 = this.anIntArray203[local337] + arg6;
					local352 = local344 & 0x7F;
					local356 = local348 & 0x7F;
					local360 = local344 >> 7;
					local382 = local348 >> 7;
					local408 = arg2[local360][local382] * (128 - local352) + arg2[local360 + 1][local382] * local352 >> 7;
					local420 = arg2[local360][local382 + 1] * (128 - local352) + arg2[local360 + 1][local382 + 1] * local352 >> 7;
					local547 = local408 * (128 - local356) + local420 * local356 >> 7;
					local408 = arg3[local360][local382] * (128 - local352) + arg3[local360 + 1][local382] * local352 >> 7;
					local420 = arg3[local360][local382 + 1] * (128 - local352) + arg3[local360 + 1][local382 + 1] * local352 >> 7;
					@Pc(890) int local890 = local408 * (128 - local356) + local420 * local356 >> 7;
					@Pc(894) int local894 = local547 - local890;
					local147.anIntArray201[local337] = ((this.anIntArray201[local337] << 8) / local326 * local894 >> 8) - (arg5 - local547);
				}
			}
		}
		this.aBoolean121 = false;
		return local147;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(IIIII)Lclient!w;")
	public SoftwareModel method1671(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		return new SoftwareModel(this, arg0, arg1, -50, -10, -50);
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(III)V")
	public void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.anIntArray202[local1] += arg0;
			this.anIntArray201[local1] += arg1;
			this.anIntArray203[local1] += arg2;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "h", descriptor = "()V")
	public void rotateY180() {
		@Pc(1) int local1;
		for (local1 = 0; local1 < this.vertexCount; local1++) {
			this.anIntArray203[local1] = -this.anIntArray203[local1];
		}
		for (local1 = 0; local1 < this.faceCount; local1++) {
			@Pc(27) int local27 = this.anIntArray197[local1];
			this.anIntArray197[local1] = this.anIntArray196[local1];
			this.anIntArray196[local1] = local27;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "()Z")
	@Override
	public boolean method4543() {
		return true;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([B)V")
	private void method1674(@OriginalArg(0) byte[] arg0) {
		@Pc(4) Packet local4 = new Packet(arg0);
		@Pc(9) Packet local9 = new Packet(arg0);
		@Pc(14) Packet local14 = new Packet(arg0);
		@Pc(19) Packet local19 = new Packet(arg0);
		@Pc(24) Packet local24 = new Packet(arg0);
		@Pc(29) Packet local29 = new Packet(arg0);
		@Pc(34) Packet local34 = new Packet(arg0);
		local4.position = arg0.length - 23;
		@Pc(44) int local44 = local4.g2();
		@Pc(48) int local48 = local4.g2();
		@Pc(52) int local52 = local4.g1();
		@Pc(56) int local56 = local4.g1();
		@Pc(65) boolean local65 = (local56 & 0x1) == 1;
		@Pc(74) boolean local74 = (local56 & 0x2) == 2;
		@Pc(78) int local78 = local4.g1();
		@Pc(82) int local82 = local4.g1();
		@Pc(86) int local86 = local4.g1();
		@Pc(90) int local90 = local4.g1();
		@Pc(94) int local94 = local4.g1();
		@Pc(98) int local98 = local4.g2();
		@Pc(102) int local102 = local4.g2();
		@Pc(106) int local106 = local4.g2();
		@Pc(110) int local110 = local4.g2();
		@Pc(114) int local114 = local4.g2();
		@Pc(116) int local116 = 0;
		@Pc(118) int local118 = 0;
		@Pc(120) int local120 = 0;
		@Pc(131) int local131;
		if (local52 > 0) {
			this.aByteArray29 = new byte[local52];
			local4.position = 0;
			for (local131 = 0; local131 < local52; local131++) {
				@Pc(143) byte local143 = this.aByteArray29[local131] = local4.g1s();
				if (local143 == 0) {
					local116++;
				}
				if (local143 >= 1 && local143 <= 3) {
					local118++;
				}
				if (local143 == 2) {
					local120++;
				}
			}
		}
		local131 = local52 + local44;
		@Pc(169) int local169 = local131;
		if (local65) {
			local131 += local48;
		}
		@Pc(177) int local177 = local131;
		local131 += local48;
		@Pc(183) int local183 = local131;
		if (local78 == 255) {
			local131 += local48;
		}
		@Pc(192) int local192 = local131;
		if (local86 == 1) {
			local131 += local48;
		}
		@Pc(201) int local201 = local131;
		if (local94 == 1) {
			local131 += local44;
		}
		@Pc(210) int local210 = local131;
		if (local82 == 1) {
			local131 += local48;
		}
		@Pc(219) int local219 = local131;
		local131 += local110;
		@Pc(225) int local225 = local131;
		if (local90 == 1) {
			local131 += local48 * 2;
		}
		@Pc(236) int local236 = local131;
		local131 += local114;
		@Pc(242) int local242 = local131;
		local131 += local48 * 2;
		@Pc(250) int local250 = local131;
		local131 += local98;
		@Pc(256) int local256 = local131;
		local131 += local102;
		@Pc(262) int local262 = local131;
		local131 += local106;
		@Pc(268) int local268 = local131;
		local131 += local116 * 6;
		@Pc(276) int local276 = local131;
		local131 += local118 * 6;
		@Pc(284) int local284 = local131;
		local131 += local118 * 6;
		@Pc(292) int local292 = local131;
		local131 += local118;
		@Pc(298) int local298 = local131;
		local131 += local118;
		@Pc(304) int local304 = local131;
		local131 += local118 + local120 * 2;
		this.vertexCount = local44;
		this.faceCount = local48;
		this.texturedFaceCount = local52;
		this.anIntArray202 = new int[local44];
		this.anIntArray201 = new int[local44];
		this.anIntArray203 = new int[local44];
		this.anIntArray197 = new int[local48];
		this.anIntArray200 = new int[local48];
		this.anIntArray196 = new int[local48];
		if (local94 == 1) {
			this.anIntArray193 = new int[local44];
		}
		if (local65) {
			this.aByteArray30 = new byte[local48];
		}
		if (local78 == 255) {
			this.facePriorities = new byte[local48];
		} else {
			this.modelPriority = (byte) local78;
		}
		if (local82 == 1) {
			this.aByteArray26 = new byte[local48];
		}
		if (local86 == 1) {
			this.anIntArray192 = new int[local48];
		}
		if (local90 == 1) {
			this.unmodifiedTriangleTexture = new short[local48];
		}
		if (local90 == 1 && local52 > 0) {
			this.aByteArray31 = new byte[local48];
		}
		this.unmodifiedTriangleColour = new short[local48];
		if (local52 > 0) {
			this.textureTriangleVertex1 = new short[local52];
			this.textureTriangleVertex2 = new short[local52];
			this.textureTriangleVertex3 = new short[local52];
			if (local118 > 0) {
				this.aShortArray27 = new short[local118];
				this.aShortArray25 = new short[local118];
				this.aShortArray21 = new short[local118];
				this.aByteArray27 = new byte[local118];
				this.aByteArray32 = new byte[local118];
				this.aByteArray34 = new byte[local118];
			}
			if (local120 > 0) {
				this.textureTriangleTranslationU = new byte[local120];
				this.textureTriangleTranslationV = new byte[local120];
			}
		}
		local4.position = local52;
		local9.position = local250;
		local14.position = local256;
		local19.position = local262;
		local24.position = local201;
		@Pc(473) int local473 = 0;
		@Pc(475) int local475 = 0;
		@Pc(477) int local477 = 0;
		@Pc(479) int local479;
		@Pc(486) int local486;
		@Pc(488) int local488;
		@Pc(498) int local498;
		@Pc(508) int local508;
		for (local479 = 0; local479 < local44; local479++) {
			local486 = local4.g1();
			local488 = 0;
			if ((local486 & 0x1) != 0) {
				local488 = local9.gSmart1or2s();
			}
			local498 = 0;
			if ((local486 & 0x2) != 0) {
				local498 = local14.gSmart1or2s();
			}
			local508 = 0;
			if ((local486 & 0x4) != 0) {
				local508 = local19.gSmart1or2s();
			}
			this.anIntArray202[local479] = local473 + local488;
			this.anIntArray201[local479] = local475 + local498;
			this.anIntArray203[local479] = local477 + local508;
			local473 = this.anIntArray202[local479];
			local475 = this.anIntArray201[local479];
			local477 = this.anIntArray203[local479];
			if (local94 == 1) {
				this.anIntArray193[local479] = local24.g1();
			}
		}
		local4.position = local242;
		local9.position = local169;
		local14.position = local183;
		local19.position = local210;
		local24.position = local192;
		local29.position = local225;
		local34.position = local236;
		for (local479 = 0; local479 < local48; local479++) {
			this.unmodifiedTriangleColour[local479] = (short) local4.g2();
			if (local65) {
				this.aByteArray30[local479] = local9.g1s();
			}
			if (local78 == 255) {
				this.facePriorities[local479] = local14.g1s();
			}
			if (local82 == 1) {
				this.aByteArray26[local479] = local19.g1s();
			}
			if (local86 == 1) {
				this.anIntArray192[local479] = local24.g1();
			}
			if (local90 == 1) {
				this.unmodifiedTriangleTexture[local479] = (short) (local29.g2() - 1);
			}
			if (this.aByteArray31 != null) {
				if (this.unmodifiedTriangleTexture[local479] == -1) {
					this.aByteArray31[local479] = -1;
				} else {
					this.aByteArray31[local479] = (byte) (local34.g1() - 1);
				}
			}
		}
		local4.position = local219;
		local9.position = local177;
		local479 = 0;
		local486 = 0;
		local488 = 0;
		local498 = 0;
		@Pc(700) int local700;
		for (local508 = 0; local508 < local48; local508++) {
			local700 = local9.g1();
			if (local700 == 1) {
				local479 = local4.gSmart1or2s() + local498;
				local486 = local4.gSmart1or2s() + local479;
				local488 = local4.gSmart1or2s() + local486;
				local498 = local488;
				this.anIntArray197[local508] = local479;
				this.anIntArray200[local508] = local486;
				this.anIntArray196[local508] = local488;
			}
			if (local700 == 2) {
				local486 = local488;
				local488 = local4.gSmart1or2s() + local498;
				local498 = local488;
				this.anIntArray197[local508] = local479;
				this.anIntArray200[local508] = local486;
				this.anIntArray196[local508] = local488;
			}
			if (local700 == 3) {
				local479 = local488;
				local488 = local4.gSmart1or2s() + local498;
				local498 = local488;
				this.anIntArray197[local508] = local479;
				this.anIntArray200[local508] = local486;
				this.anIntArray196[local508] = local488;
			}
			if (local700 == 4) {
				@Pc(803) int local803 = local479;
				local479 = local486;
				local486 = local803;
				local488 = local4.gSmart1or2s() + local498;
				local498 = local488;
				this.anIntArray197[local508] = local479;
				this.anIntArray200[local508] = local803;
				this.anIntArray196[local508] = local488;
			}
		}
		local4.position = local268;
		local9.position = local276;
		local14.position = local284;
		local19.position = local292;
		local24.position = local298;
		local29.position = local304;
		for (local508 = 0; local508 < local52; local508++) {
			local700 = this.aByteArray29[local508] & 0xFF;
			if (local700 == 0) {
				this.textureTriangleVertex1[local508] = (short) local4.g2();
				this.textureTriangleVertex2[local508] = (short) local4.g2();
				this.textureTriangleVertex3[local508] = (short) local4.g2();
			}
			if (local700 == 1) {
				this.textureTriangleVertex1[local508] = (short) local9.g2();
				this.textureTriangleVertex2[local508] = (short) local9.g2();
				this.textureTriangleVertex3[local508] = (short) local9.g2();
				this.aShortArray27[local508] = (short) local14.g2();
				this.aShortArray25[local508] = (short) local14.g2();
				this.aShortArray21[local508] = (short) local14.g2();
				this.aByteArray27[local508] = local19.g1s();
				this.aByteArray32[local508] = local24.g1s();
				this.aByteArray34[local508] = local29.g1s();
			}
			if (local700 == 2) {
				this.textureTriangleVertex1[local508] = (short) local9.g2();
				this.textureTriangleVertex2[local508] = (short) local9.g2();
				this.textureTriangleVertex3[local508] = (short) local9.g2();
				this.aShortArray27[local508] = (short) local14.g2();
				this.aShortArray25[local508] = (short) local14.g2();
				this.aShortArray21[local508] = (short) local14.g2();
				this.aByteArray27[local508] = local19.g1s();
				this.aByteArray32[local508] = local24.g1s();
				this.aByteArray34[local508] = local29.g1s();
				this.textureTriangleTranslationU[local508] = local29.g1s();
				this.textureTriangleTranslationV[local508] = local29.g1s();
			}
			if (local700 == 3) {
				this.textureTriangleVertex1[local508] = (short) local9.g2();
				this.textureTriangleVertex2[local508] = (short) local9.g2();
				this.textureTriangleVertex3[local508] = (short) local9.g2();
				this.aShortArray27[local508] = (short) local14.g2();
				this.aShortArray25[local508] = (short) local14.g2();
				this.aShortArray21[local508] = (short) local14.g2();
				this.aByteArray27[local508] = local19.g1s();
				this.aByteArray32[local508] = local24.g1s();
				this.aByteArray34[local508] = local29.g1s();
			}
		}
		if (!local74) {
			return;
		}
		local4.position = local131;
		local508 = local4.g1();
		if (local508 > 0) {
			local4.position += local508 * 4;
		}
		local700 = local4.g1();
		if (local700 > 0) {
			local4.position += local700 * 4;
		}
	}

	@OriginalMember(owner = "client!gb", name = "i", descriptor = "()Lclient!gb;")
	public ModelUnlit method1675() {
		@Pc(3) ModelUnlit local3 = new ModelUnlit();
		if (this.aByteArray30 != null) {
			local3.aByteArray30 = new byte[this.faceCount];
			System.arraycopy(this.aByteArray30, 0, local3.aByteArray30, 0, this.faceCount);
		}
		local3.vertexCount = this.vertexCount;
		local3.faceCount = this.faceCount;
		local3.texturedFaceCount = this.texturedFaceCount;
		local3.anIntArray202 = this.anIntArray202;
		local3.anIntArray201 = this.anIntArray201;
		local3.anIntArray203 = this.anIntArray203;
		local3.anIntArray197 = this.anIntArray197;
		local3.anIntArray200 = this.anIntArray200;
		local3.anIntArray196 = this.anIntArray196;
		local3.facePriorities = this.facePriorities;
		local3.aByteArray26 = this.aByteArray26;
		local3.aByteArray31 = this.aByteArray31;
		local3.unmodifiedTriangleColour = this.unmodifiedTriangleColour;
		local3.unmodifiedTriangleTexture = this.unmodifiedTriangleTexture;
		local3.modelPriority = this.modelPriority;
		local3.aByteArray29 = this.aByteArray29;
		local3.textureTriangleVertex1 = this.textureTriangleVertex1;
		local3.textureTriangleVertex2 = this.textureTriangleVertex2;
		local3.textureTriangleVertex3 = this.textureTriangleVertex3;
		local3.aShortArray27 = this.aShortArray27;
		local3.aShortArray25 = this.aShortArray25;
		local3.aShortArray21 = this.aShortArray21;
		local3.aByteArray27 = this.aByteArray27;
		local3.aByteArray32 = this.aByteArray32;
		local3.aByteArray34 = this.aByteArray34;
		local3.textureTriangleTranslationU = this.textureTriangleTranslationU;
		local3.textureTriangleTranslationV = this.textureTriangleTranslationV;
		local3.anIntArray193 = this.anIntArray193;
		local3.anIntArray192 = this.anIntArray192;
		local3.anIntArrayArray16 = this.anIntArrayArray16;
		local3.anIntArrayArray15 = this.anIntArrayArray15;
		local3.aVertexNormalArray1 = this.aVertexNormalArray1;
		local3.aClass126Array1 = this.aClass126Array1;
		local3.aShort19 = this.aShort19;
		local3.aShort18 = this.aShort18;
		return local3;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIBSB)I")
	public int method1676(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) short arg3, @OriginalArg(5) byte arg4) {
		this.anIntArray197[this.faceCount] = arg0;
		this.anIntArray200[this.faceCount] = arg1;
		this.anIntArray196[this.faceCount] = arg2;
		this.aByteArray30[this.faceCount] = 1;
		this.aByteArray31[this.faceCount] = -1;
		this.unmodifiedTriangleColour[this.faceCount] = arg3;
		this.unmodifiedTriangleTexture[this.faceCount] = -1;
		this.aByteArray26[this.faceCount] = arg4;
		return this.faceCount++;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(I)V")
	private void method1677(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = Static77.anIntArray198[arg0];
		@Pc(7) int local7 = Static77.anIntArray195[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.anIntArray201[local9] * local7 - this.anIntArray203[local9] * local3 >> 16;
			this.anIntArray203[local9] = this.anIntArray201[local9] * local3 + this.anIntArray203[local9] * local7 >> 16;
			this.anIntArray201[local9] = local29;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "j", descriptor = "()V")
	private void method1678() {
		this.aVertexNormalArray1 = null;
		this.aVertexNormalArray2 = null;
		this.aClass126Array1 = null;
		this.aBoolean121 = false;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(IIIII)Lclient!ak;")
	public Model applyLightning(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (GlRenderer.enabled) {
			@Pc(9) GlModel local9 = new GlModel(this, arg0, arg1, true);
			local9.method4099();
			return local9;
		} else {
			return new SoftwareModel(this, arg0, arg1, arg2, arg3, arg4);
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "()I")
	@Override
	public int getHeight() {
		if (!this.aBoolean121) {
			this.method1664();
		}
		return this.aShort15;
	}

	@OriginalMember(owner = "client!gb", name = "k", descriptor = "()V")
	public void method1681() {
		this.anIntArray193 = null;
		this.anIntArray192 = null;
		this.anIntArrayArray16 = null;
		this.anIntArrayArray15 = null;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!th;IIIZ)V")
	@Override
	public void method4544(@OriginalArg(0) Entity arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) boolean arg4) {
		@Pc(2) ModelUnlit local2 = (ModelUnlit) arg0;
		local2.method1664();
		local2.method1668();
		Static77.anInt2138++;
		@Pc(12) int local12 = 0;
		@Pc(15) int[] local15 = local2.anIntArray202;
		@Pc(18) int local18 = local2.vertexCount;
		@Pc(20) int local20;
		for (local20 = 0; local20 < this.vertexCount; local20++) {
			@Pc(29) VertexNormal local29 = this.aVertexNormalArray1[local20];
			if (local29.magnitude != 0) {
				@Pc(40) int local40 = this.anIntArray201[local20] - arg2;
				if (local40 >= local2.aShort15 && local40 <= local2.aShort13) {
					@Pc(56) int local56 = this.anIntArray202[local20] - arg1;
					if (local56 >= local2.aShort14 && local56 <= local2.aShort16) {
						@Pc(72) int local72 = this.anIntArray203[local20] - arg3;
						if (local72 >= local2.aShort20 && local72 <= local2.aShort17) {
							for (@Pc(83) int local83 = 0; local83 < local18; local83++) {
								@Pc(91) VertexNormal local91 = local2.aVertexNormalArray1[local83];
								if (local56 == local15[local83] && local72 == local2.anIntArray203[local83] && local40 == local2.anIntArray201[local83] && local91.magnitude != 0) {
									if (this.aVertexNormalArray2 == null) {
										this.aVertexNormalArray2 = new VertexNormal[this.vertexCount];
									}
									if (local2.aVertexNormalArray2 == null) {
										local2.aVertexNormalArray2 = new VertexNormal[local18];
									}
									@Pc(131) VertexNormal local131 = this.aVertexNormalArray2[local20];
									if (local131 == null) {
										local131 = this.aVertexNormalArray2[local20] = new VertexNormal(local29);
									}
									@Pc(148) VertexNormal local148 = local2.aVertexNormalArray2[local83];
									if (local148 == null) {
										local148 = local2.aVertexNormalArray2[local83] = new VertexNormal(local91);
									}
									local131.x += local91.x;
									local131.y += local91.y;
									local131.z += local91.z;
									local131.magnitude += local91.magnitude;
									local148.x += local29.x;
									local148.y += local29.y;
									local148.z += local29.z;
									local148.magnitude += local29.magnitude;
									local12++;
									Static77.anIntArray194[local20] = Static77.anInt2138;
									Static77.anIntArray199[local83] = Static77.anInt2138;
								}
							}
						}
					}
				}
			}
		}
		if (local12 < 3 || !arg4) {
			return;
		}
		for (local20 = 0; local20 < this.faceCount; local20++) {
			if (Static77.anIntArray194[this.anIntArray197[local20]] == Static77.anInt2138 && Static77.anIntArray194[this.anIntArray200[local20]] == Static77.anInt2138 && Static77.anIntArray194[this.anIntArray196[local20]] == Static77.anInt2138) {
				if (this.aByteArray30 == null) {
					this.aByteArray30 = new byte[this.faceCount];
				}
				this.aByteArray30[local20] = 2;
			}
		}
		for (local20 = 0; local20 < local2.faceCount; local20++) {
			if (Static77.anIntArray199[local2.anIntArray197[local20]] == Static77.anInt2138 && Static77.anIntArray199[local2.anIntArray200[local20]] == Static77.anInt2138 && Static77.anIntArray199[local2.anIntArray196[local20]] == Static77.anInt2138) {
				if (local2.aByteArray30 == null) {
					local2.aByteArray30 = new byte[local2.faceCount];
				}
				local2.aByteArray30[local20] = 2;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(I)V")
	public void method1682() {
		@Pc(3) int local3 = Static77.anIntArray198[256];
		@Pc(7) int local7 = Static77.anIntArray195[256];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.anIntArray203[local9] * local3 + this.anIntArray202[local9] * local7 >> 16;
			this.anIntArray203[local9] = this.anIntArray203[local9] * local7 - this.anIntArray202[local9] * local3 >> 16;
			this.anIntArray202[local9] = local29;
		}
		this.method1678();
	}

	@OriginalMember(owner = "client!gb", name = "l", descriptor = "()V")
	public void method1683() {
		@Pc(5) int[] local5;
		@Pc(7) int local7;
		@Pc(22) int local22;
		@Pc(9) int local9;
		@Pc(18) int local18;
		if (this.anIntArray193 != null) {
			local5 = new int[256];
			local7 = 0;
			for (local9 = 0; local9 < this.vertexCount; local9++) {
				local18 = this.anIntArray193[local9];
				local22 = local5[local18]++;
				if (local18 > local7) {
					local7 = local18;
				}
			}
			this.anIntArrayArray16 = new int[local7 + 1][];
			for (local9 = 0; local9 <= local7; local9++) {
				this.anIntArrayArray16[local9] = new int[local5[local9]];
				local5[local9] = 0;
			}
			local9 = 0;
			while (local9 < this.vertexCount) {
				local18 = this.anIntArray193[local9];
				this.anIntArrayArray16[local18][local5[local18]++] = local9++;
			}
			this.anIntArray193 = null;
		}
		if (this.anIntArray192 == null) {
			return;
		}
		local5 = new int[256];
		local7 = 0;
		for (local9 = 0; local9 < this.faceCount; local9++) {
			local18 = this.anIntArray192[local9];
			local22 = local5[local18]++;
			if (local18 > local7) {
				local7 = local18;
			}
		}
		this.anIntArrayArray15 = new int[local7 + 1][];
		for (local9 = 0; local9 <= local7; local9++) {
			this.anIntArrayArray15[local9] = new int[local5[local9]];
			local5[local9] = 0;
		}
		local9 = 0;
		while (local9 < this.faceCount) {
			local18 = this.anIntArray192[local9];
			this.anIntArrayArray15[local18][local5[local18]++] = local9++;
		}
		this.anIntArray192 = null;
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(III)V")
	public void method1684(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(5) int local5;
		@Pc(9) int local9;
		@Pc(11) int local11;
		@Pc(31) int local31;
		if (arg2 != 0) {
			local5 = Static77.anIntArray198[arg2];
			local9 = Static77.anIntArray195[arg2];
			for (local11 = 0; local11 < this.vertexCount; local11++) {
				local31 = this.anIntArray201[local11] * local5 + this.anIntArray202[local11] * local9 >> 16;
				this.anIntArray201[local11] = this.anIntArray201[local11] * local9 - this.anIntArray202[local11] * local5 >> 16;
				this.anIntArray202[local11] = local31;
			}
		}
		if (arg0 != 0) {
			local5 = Static77.anIntArray198[arg0];
			local9 = Static77.anIntArray195[arg0];
			for (local11 = 0; local11 < this.vertexCount; local11++) {
				local31 = this.anIntArray201[local11] * local9 - this.anIntArray203[local11] * local5 >> 16;
				this.anIntArray203[local11] = this.anIntArray201[local11] * local5 + this.anIntArray203[local11] * local9 >> 16;
				this.anIntArray201[local11] = local31;
			}
		}
		if (arg1 == 0) {
			return;
		}
		local5 = Static77.anIntArray198[arg1];
		local9 = Static77.anIntArray195[arg1];
		for (local11 = 0; local11 < this.vertexCount; local11++) {
			local31 = this.anIntArray203[local11] * local5 + this.anIntArray202[local11] * local9 >> 16;
			this.anIntArray203[local11] = this.anIntArray203[local11] * local9 - this.anIntArray202[local11] * local5 >> 16;
			this.anIntArray202[local11] = local31;
		}
	}

	@OriginalMember(owner = "client!gb", name = "e", descriptor = "(III)I")
	public int method1685(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			if (this.anIntArray202[local1] == arg0 && this.anIntArray201[local1] == 0 && this.anIntArray203[local1] == arg1) {
				return local1;
			}
		}
		this.anIntArray202[this.vertexCount] = arg0;
		this.anIntArray201[this.vertexCount] = 0;
		this.anIntArray203[this.vertexCount] = arg1;
		return this.vertexCount++;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(SS)V")
	public void recolor(@OriginalArg(0) short arg0, @OriginalArg(1) short arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.faceCount; local1++) {
			if (this.unmodifiedTriangleColour[local1] == arg0) {
				this.unmodifiedTriangleColour[local1] = arg1;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "([B)V")
	private void method1688(@OriginalArg(0) byte[] arg0) {
		@Pc(1) boolean local1 = false;
		@Pc(3) boolean local3 = false;
		@Pc(8) Packet local8 = new Packet(arg0);
		@Pc(13) Packet local13 = new Packet(arg0);
		@Pc(18) Packet local18 = new Packet(arg0);
		@Pc(23) Packet local23 = new Packet(arg0);
		@Pc(28) Packet local28 = new Packet(arg0);
		local8.position = arg0.length - 18;
		@Pc(38) int local38 = local8.g2();
		@Pc(42) int local42 = local8.g2();
		@Pc(46) int local46 = local8.g1();
		@Pc(50) int local50 = local8.g1();
		@Pc(54) int local54 = local8.g1();
		@Pc(58) int local58 = local8.g1();
		@Pc(62) int local62 = local8.g1();
		@Pc(66) int local66 = local8.g1();
		@Pc(70) int local70 = local8.g2();
		@Pc(74) int local74 = local8.g2();
		@Pc(78) int local78 = local8.g2();
		@Pc(82) int local82 = local8.g2();
		@Pc(90) int local90 = local38;
		@Pc(92) int local92 = local90;
		local90 += local42;
		@Pc(98) int local98 = local90;
		if (local54 == 255) {
			local90 += local42;
		}
		@Pc(107) int local107 = local90;
		if (local62 == 1) {
			local90 += local42;
		}
		@Pc(116) int local116 = local90;
		if (local50 == 1) {
			local90 += local42;
		}
		@Pc(125) int local125 = local90;
		if (local66 == 1) {
			local90 += local38;
		}
		@Pc(134) int local134 = local90;
		if (local58 == 1) {
			local90 += local42;
		}
		@Pc(143) int local143 = local90;
		local90 += local82;
		@Pc(149) int local149 = local90;
		local90 += local42 * 2;
		@Pc(157) int local157 = local90;
		local90 += local46 * 6;
		@Pc(165) int local165 = local90;
		local90 += local70;
		@Pc(171) int local171 = local90;
		local90 += local74;
		this.vertexCount = local38;
		this.faceCount = local42;
		this.texturedFaceCount = local46;
		this.anIntArray202 = new int[local38];
		this.anIntArray201 = new int[local38];
		this.anIntArray203 = new int[local38];
		this.anIntArray197 = new int[local42];
		this.anIntArray200 = new int[local42];
		this.anIntArray196 = new int[local42];
		if (local46 > 0) {
			this.aByteArray29 = new byte[local46];
			this.textureTriangleVertex1 = new short[local46];
			this.textureTriangleVertex2 = new short[local46];
			this.textureTriangleVertex3 = new short[local46];
		}
		if (local66 == 1) {
			this.anIntArray193 = new int[local38];
		}
		if (local50 == 1) {
			this.aByteArray30 = new byte[local42];
			this.aByteArray31 = new byte[local42];
			this.unmodifiedTriangleTexture = new short[local42];
		}
		if (local54 == 255) {
			this.facePriorities = new byte[local42];
		} else {
			this.modelPriority = (byte) local54;
		}
		if (local58 == 1) {
			this.aByteArray26 = new byte[local42];
		}
		if (local62 == 1) {
			this.anIntArray192 = new int[local42];
		}
		this.unmodifiedTriangleColour = new short[local42];
		local8.position = 0;
		local13.position = local165;
		local18.position = local171;
		local23.position = local90;
		local28.position = local125;
		@Pc(301) int local301 = 0;
		@Pc(303) int local303 = 0;
		@Pc(305) int local305 = 0;
		@Pc(307) int local307;
		@Pc(314) int local314;
		@Pc(316) int local316;
		@Pc(326) int local326;
		@Pc(336) int local336;
		for (local307 = 0; local307 < local38; local307++) {
			local314 = local8.g1();
			local316 = 0;
			if ((local314 & 0x1) != 0) {
				local316 = local13.gSmart1or2s();
			}
			local326 = 0;
			if ((local314 & 0x2) != 0) {
				local326 = local18.gSmart1or2s();
			}
			local336 = 0;
			if ((local314 & 0x4) != 0) {
				local336 = local23.gSmart1or2s();
			}
			this.anIntArray202[local307] = local301 + local316;
			this.anIntArray201[local307] = local303 + local326;
			this.anIntArray203[local307] = local305 + local336;
			local301 = this.anIntArray202[local307];
			local303 = this.anIntArray201[local307];
			local305 = this.anIntArray203[local307];
			if (local66 == 1) {
				this.anIntArray193[local307] = local28.g1();
			}
		}
		local8.position = local149;
		local13.position = local116;
		local18.position = local98;
		local23.position = local134;
		local28.position = local107;
		for (local307 = 0; local307 < local42; local307++) {
			this.unmodifiedTriangleColour[local307] = (short) local8.g2();
			if (local50 == 1) {
				local314 = local13.g1();
				if ((local314 & 0x1) == 1) {
					this.aByteArray30[local307] = 1;
					local1 = true;
				} else {
					this.aByteArray30[local307] = 0;
				}
				if ((local314 & 0x2) == 2) {
					this.aByteArray31[local307] = (byte) (local314 >> 2);
					this.unmodifiedTriangleTexture[local307] = this.unmodifiedTriangleColour[local307];
					this.unmodifiedTriangleColour[local307] = 127;
					if (this.unmodifiedTriangleTexture[local307] != -1) {
						local3 = true;
					}
				} else {
					this.aByteArray31[local307] = -1;
					this.unmodifiedTriangleTexture[local307] = -1;
				}
			}
			if (local54 == 255) {
				this.facePriorities[local307] = local18.g1s();
			}
			if (local58 == 1) {
				this.aByteArray26[local307] = local23.g1s();
			}
			if (local62 == 1) {
				this.anIntArray192[local307] = local28.g1();
			}
		}
		local8.position = local143;
		local13.position = local92;
		local307 = 0;
		local314 = 0;
		local316 = 0;
		local326 = 0;
		@Pc(545) int local545;
		@Pc(648) int local648;
		for (local336 = 0; local336 < local42; local336++) {
			local545 = local13.g1();
			if (local545 == 1) {
				local307 = local8.gSmart1or2s() + local326;
				local314 = local8.gSmart1or2s() + local307;
				local316 = local8.gSmart1or2s() + local314;
				local326 = local316;
				this.anIntArray197[local336] = local307;
				this.anIntArray200[local336] = local314;
				this.anIntArray196[local336] = local316;
			}
			if (local545 == 2) {
				local314 = local316;
				local316 = local8.gSmart1or2s() + local326;
				local326 = local316;
				this.anIntArray197[local336] = local307;
				this.anIntArray200[local336] = local314;
				this.anIntArray196[local336] = local316;
			}
			if (local545 == 3) {
				local307 = local316;
				local316 = local8.gSmart1or2s() + local326;
				local326 = local316;
				this.anIntArray197[local336] = local307;
				this.anIntArray200[local336] = local314;
				this.anIntArray196[local336] = local316;
			}
			if (local545 == 4) {
				local648 = local307;
				local307 = local314;
				local314 = local648;
				local316 = local8.gSmart1or2s() + local326;
				local326 = local316;
				this.anIntArray197[local336] = local307;
				this.anIntArray200[local336] = local648;
				this.anIntArray196[local336] = local316;
			}
		}
		local8.position = local157;
		for (local336 = 0; local336 < local46; local336++) {
			this.aByteArray29[local336] = 0;
			this.textureTriangleVertex1[local336] = (short) local8.g2();
			this.textureTriangleVertex2[local336] = (short) local8.g2();
			this.textureTriangleVertex3[local336] = (short) local8.g2();
		}
		if (this.aByteArray31 != null) {
			@Pc(721) boolean local721 = false;
			for (local545 = 0; local545 < local42; local545++) {
				local648 = this.aByteArray31[local545] & 0xFF;
				if (local648 != 255) {
					if ((this.textureTriangleVertex1[local648] & 0xFFFF) == this.anIntArray197[local545] && (this.textureTriangleVertex2[local648] & 0xFFFF) == this.anIntArray200[local545] && (this.textureTriangleVertex3[local648] & 0xFFFF) == this.anIntArray196[local545]) {
						this.aByteArray31[local545] = -1;
					} else {
						local721 = true;
					}
				}
			}
			if (!local721) {
				this.aByteArray31 = null;
			}
		}
		if (!local3) {
			this.unmodifiedTriangleTexture = null;
		}
		if (!local1) {
			this.aByteArray30 = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "m", descriptor = "()V")
	public void method1689() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.anIntArray203[local1];
			this.anIntArray203[local1] = this.anIntArray202[local1];
			this.anIntArray202[local1] = -local10;
		}
		this.method1678();
	}
}
