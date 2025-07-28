package com.jagex.runetek4.graphics.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.jagex.runetek4.graphics.render.MaterialManager;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.graphics.render.UnderwaterMaterialRenderer;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.util.IntUtils;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!hg")
public final class GlTile extends Node {

	@OriginalMember(owner = "runetek4.client!hg", name = "J", descriptor = "Ljava/nio/ByteBuffer;")
	public static ByteBuffer aByteBuffer4;
	@OriginalMember(owner = "runetek4.client!hg", name = "U", descriptor = "Lclient!wa;")
	public static Packet aClass3_Sub15_3;
	@OriginalMember(owner = "runetek4.client!hg", name = "K", descriptor = "Lclient!wa;")
	public static Packet aClass3_Sub15_2;
	@OriginalMember(owner = "runetek4.client!hg", name = "Q", descriptor = "Ljava/nio/ByteBuffer;")
	public static ByteBuffer aByteBuffer5;
	@OriginalMember(owner = "runetek4.client!hg", name = "s", descriptor = "Ljava/nio/ByteBuffer;")
	private ByteBuffer aByteBuffer3;

	@OriginalMember(owner = "runetek4.client!hg", name = "t", descriptor = "[F")
	private float[] aFloatArray4;

	@OriginalMember(owner = "runetek4.client!hg", name = "v", descriptor = "[I")
	private int[] anIntArray226;

	@OriginalMember(owner = "runetek4.client!hg", name = "w", descriptor = "[I")
	private int[] anIntArray227;

	@OriginalMember(owner = "runetek4.client!hg", name = "x", descriptor = "[I")
	private int[] anIntArray228;

	@OriginalMember(owner = "runetek4.client!hg", name = "z", descriptor = "[I")
	private int[] anIntArray229;

	@OriginalMember(owner = "runetek4.client!hg", name = "A", descriptor = "Lclient!sc;")
	private HashTable aClass133_8;

	@OriginalMember(owner = "runetek4.client!hg", name = "B", descriptor = "[F")
	private float[] aFloatArray5;

	@OriginalMember(owner = "runetek4.client!hg", name = "E", descriptor = "[[I")
	private int[][] anIntArrayArray17;

	@OriginalMember(owner = "runetek4.client!hg", name = "F", descriptor = "[I")
	private int[] anIntArray230;

	@OriginalMember(owner = "runetek4.client!hg", name = "I", descriptor = "[[I")
	private int[][] anIntArrayArray18;

	@OriginalMember(owner = "runetek4.client!hg", name = "L", descriptor = "Lclient!vi;")
	private GlVertexBufferObject aClass155_3;

	@OriginalMember(owner = "runetek4.client!hg", name = "N", descriptor = "[F")
	private float[] aFloatArray6;

	@OriginalMember(owner = "runetek4.client!hg", name = "P", descriptor = "[I")
	private int[] anIntArray231;

	@OriginalMember(owner = "runetek4.client!hg", name = "R", descriptor = "[F")
	private float[] aFloatArray7;

	@OriginalMember(owner = "runetek4.client!hg", name = "S", descriptor = "[Z")
	private boolean[] aBooleanArray54;

	@OriginalMember(owner = "runetek4.client!hg", name = "T", descriptor = "[I")
	private int[] anIntArray232;

	@OriginalMember(owner = "runetek4.client!hg", name = "p", descriptor = "I")
	public int anInt2482 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "D", descriptor = "I")
	private int anInt2487 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "G", descriptor = "I")
	private int anInt2488 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "q", descriptor = "I")
	public int anInt2483 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "r", descriptor = "I")
	public int anInt2484 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "O", descriptor = "I")
	private int anInt2489 = 0;

	@OriginalMember(owner = "runetek4.client!hg", name = "y", descriptor = "I")
	public final int texture;

	@OriginalMember(owner = "runetek4.client!hg", name = "V", descriptor = "F")
	private final float aFloat12;

	@OriginalMember(owner = "runetek4.client!hg", name = "M", descriptor = "Z")
	public final boolean blend;

	@OriginalMember(owner = "runetek4.client!hg", name = "u", descriptor = "Z")
	private final boolean aBoolean139;

	@OriginalMember(owner = "runetek4.client!hg", name = "C", descriptor = "I")
	public final int underwaterColor;

	@OriginalMember(owner = "runetek4.client!hg", name = "<init>", descriptor = "(IFZZI)V")
	public GlTile(@OriginalArg(0) int arg0, @OriginalArg(1) float arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4) {
		this.texture = arg0;
		this.aFloat12 = arg1;
		this.blend = arg2;
		this.aBoolean139 = arg3;
		this.underwaterColor = arg4;
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "a", descriptor = "()V")
	public static void method1939() {
		aClass3_Sub15_3 = null;
		aClass3_Sub15_2 = null;
		aByteBuffer5 = null;
		aByteBuffer4 = null;
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "b", descriptor = "()V")
	public final void method1940() {
		this.anIntArray232 = new int[this.anInt2482];
		this.anIntArray230 = new int[this.anInt2482];
		this.anIntArray229 = new int[this.anInt2482];
		if (this.aBoolean139) {
			this.aFloatArray4 = new float[this.anInt2482];
		}
		this.anIntArray226 = new int[this.anInt2482];
		this.aFloatArray7 = new float[this.anInt2482];
		this.aFloatArray6 = new float[this.anInt2482];
		this.aFloatArray5 = new float[this.anInt2482];
		this.anIntArray228 = new int[this.anInt2484];
		this.anIntArray227 = new int[this.anInt2484];
		this.anIntArray231 = new int[this.anInt2484];
		this.anIntArrayArray17 = new int[this.anInt2484][];
		this.aClass133_8 = new HashTable(IntUtils.bitceil(this.anInt2482));
		if (this.blend) {
			this.anIntArrayArray18 = new int[this.anInt2484][];
			this.aBooleanArray54 = new boolean[this.anInt2484];
		}
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "a", descriptor = "(IIIFFFIF)I")
	public final int method1941(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) float arg3, @OriginalArg(4) float arg4, @OriginalArg(5) float arg5, @OriginalArg(6) int arg6, @OriginalArg(7) float arg7) {
		@Pc(1) long local1 = 0L;
		if ((arg0 & 0x7F) == 0 || (arg2 & 0x7F) == 0) {
			local1 = (long) (arg0 + (arg2 << 16)) + ((long) arg6 << 32);
			@Pc(28) IntWrapper local28 = (IntWrapper) this.aClass133_8.get(local1);
			if (local28 != null) {
				if (arg1 < this.anIntArray230[local28.value]) {
					this.anIntArray230[local28.value] = arg1;
				}
				return local28.value;
			}
		}
		this.anIntArray232[this.anInt2483] = arg0;
		this.anIntArray230[this.anInt2483] = arg1;
		this.anIntArray229[this.anInt2483] = arg2;
		if (this.aBoolean139) {
			this.aFloatArray4[this.anInt2483] = arg7;
		}
		this.aFloatArray7[this.anInt2483] = arg3;
		this.aFloatArray6[this.anInt2483] = arg4;
		this.aFloatArray5[this.anInt2483] = arg5;
		this.anIntArray226[this.anInt2483] = arg6;
		if (local1 != 0L) {
			this.aClass133_8.put(new IntWrapper(this.anInt2483), local1);
		}
		return this.anInt2483++;
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "d", descriptor = "()V")
	public final void method1943() {
		@Pc(12) Packet local12 = new Packet((this.aBoolean139 ? 40 : 36) * this.anInt2483);
		for (@Pc(14) int local14 = 0; local14 < this.anInt2483; local14++) {
			if (GlRenderer.bigEndian) {
				local12.pFloat((float) this.anIntArray232[local14]);
				local12.pFloat((float) this.anIntArray230[local14]);
				local12.pFloat((float) this.anIntArray229[local14]);
				local12.p4(this.anIntArray226[local14]);
				local12.pFloat(this.aFloatArray7[local14]);
				local12.pFloat(this.aFloatArray6[local14]);
				local12.pFloat(this.aFloatArray5[local14]);
				local12.pFloat((float) this.anIntArray232[local14] / this.aFloat12);
				local12.pFloat((float) this.anIntArray229[local14] / this.aFloat12);
				if (this.aBoolean139) {
					local12.pFloat(this.aFloatArray4[local14]);
				}
			} else {
				local12.gFloat((float) this.anIntArray232[local14]);
				local12.gFloat((float) this.anIntArray230[local14]);
				local12.gFloat((float) this.anIntArray229[local14]);
				local12.p4(this.anIntArray226[local14]);
				local12.gFloat(this.aFloatArray7[local14]);
				local12.gFloat(this.aFloatArray6[local14]);
				local12.gFloat(this.aFloatArray5[local14]);
				local12.gFloat((float) this.anIntArray232[local14] / this.aFloat12);
				local12.gFloat((float) this.anIntArray229[local14] / this.aFloat12);
				if (this.aBoolean139) {
					local12.gFloat(this.aFloatArray4[local14]);
				}
			}
		}
		if (GlRenderer.arbVboSupported) {
			@Pc(200) ByteBuffer local200 = ByteBuffer.wrap(local12.data, 0, local12.offset);
			this.aClass155_3 = new GlVertexBufferObject();
			this.aClass155_3.setArrayBuffer(local200);
		} else {
			this.aByteBuffer3 = ByteBuffer.allocateDirect(local12.offset).order(ByteOrder.nativeOrder());
			this.aByteBuffer3.put(local12.data, 0, local12.offset);
			this.aByteBuffer3.flip();
		}
		this.anIntArray232 = null;
		this.anIntArray230 = null;
		this.anIntArray229 = null;
		this.anIntArray226 = null;
		this.aFloatArray7 = null;
		this.aFloatArray6 = null;
		this.aFloatArray5 = null;
		this.aClass133_8 = null;
		this.aFloatArray4 = null;
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "a", descriptor = "([[[Lclient!bj;FZ)V")
	public final void method1944(@OriginalArg(0) Tile[][][] arg0, @OriginalArg(1) float arg1, @OriginalArg(2) boolean arg2) {
		if (aClass3_Sub15_3 == null || aClass3_Sub15_3.data.length < this.anInt2488 * 4) {
			aClass3_Sub15_3 = new Packet(this.anInt2488 * 4);
		} else {
			aClass3_Sub15_3.offset = 0;
		}
		if (aClass3_Sub15_2 == null || aClass3_Sub15_2.data.length < this.anInt2487 * 4) {
			aClass3_Sub15_2 = new Packet(this.anInt2487 * 4);
		} else {
			aClass3_Sub15_2.offset = 0;
		}
		@Pc(47) int local47;
		@Pc(68) Tile local68;
		@Pc(111) Packet local111;
		@Pc(78) int[] local78;
		@Pc(86) int[] local86;
		@Pc(90) int local90;
		@Pc(116) int local116;
		if (GlRenderer.bigEndian) {
			for (local47 = 0; local47 < this.anInt2489; local47++) {
				local68 = arg0[this.anIntArray231[local47]][this.anIntArray228[local47]][this.anIntArray227[local47]];
				if (local68 != null && local68.aBoolean45) {
					local78 = this.anIntArrayArray17[local47];
					if (this.blend) {
						local86 = this.anIntArrayArray18[local47];
						if (local86 != null) {
							for (local90 = 0; local90 < local86.length; local90++) {
								aClass3_Sub15_2.p4(local86[local90]);
							}
						}
						local111 = this.aBooleanArray54[local47] ? aClass3_Sub15_2 : aClass3_Sub15_3;
					} else {
						local111 = aClass3_Sub15_3;
					}
					for (local116 = 1; local116 < local78.length - 1; local116++) {
						local111.p4(local78[0]);
						local111.p4(local78[local116]);
						local111.p4(local78[local116 + 1]);
					}
				}
			}
		} else {
			for (local47 = 0; local47 < this.anInt2489; local47++) {
				local68 = arg0[this.anIntArray231[local47]][this.anIntArray228[local47]][this.anIntArray227[local47]];
				if (local68 != null && local68.aBoolean45) {
					local78 = this.anIntArrayArray17[local47];
					if (this.blend) {
						local86 = this.anIntArrayArray18[local47];
						if (local86 != null) {
							for (local90 = 0; local90 < local86.length; local90++) {
								aClass3_Sub15_2.p4le(local86[local90]);
							}
						}
						local111 = this.aBooleanArray54[local47] ? aClass3_Sub15_2 : aClass3_Sub15_3;
					} else {
						local111 = aClass3_Sub15_3;
					}
					for (local116 = 1; local116 < local78.length - 1; local116++) {
						local111.p4le(local78[0]);
						local111.p4le(local78[local116]);
						local111.p4le(local78[local116 + 1]);
					}
				}
			}
		}
		if (aClass3_Sub15_3.offset == 0 && aClass3_Sub15_2.offset == 0) {
			return;
		}
		@Pc(257) GL2 local257 = GlRenderer.gl;
		if (this.texture == -1 || arg2) {
			GlRenderer.setTextureId(-1);
			MaterialManager.setMaterial(0, 0);
		} else {
			Rasterizer.textureProvider.method3227(this.texture);
		}
		@Pc(282) int local282 = this.aBoolean139 ? 40 : 36;
		if (this.aClass155_3 == null) {
			if (GlRenderer.arbVboSupported) {
				local257.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
			}
			this.aByteBuffer3.position(0);
			local257.glVertexPointer(3, GL2.GL_FLOAT, local282, this.aByteBuffer3);
			this.aByteBuffer3.position(12);
			local257.glColorPointer(4, GL2.GL_UNSIGNED_BYTE, local282, this.aByteBuffer3);
			if (Preferences.highDetailLighting) {
				this.aByteBuffer3.position(16);
				local257.glNormalPointer(GL2.GL_FLOAT, local282, this.aByteBuffer3);
			}
			this.aByteBuffer3.position(28);
			local257.glTexCoordPointer(2, GL2.GL_FLOAT, local282, this.aByteBuffer3);
			if (this.aBoolean139) {
				local257.glClientActiveTexture(UnderwaterMaterialRenderer.method4607());
				this.aByteBuffer3.position(36);
				local257.glTexCoordPointer(1, GL2.GL_FLOAT, local282, this.aByteBuffer3);
				local257.glClientActiveTexture(GL2.GL_TEXTURE0);
			}
		} else {
			this.aClass155_3.bindArray();
			local257.glVertexPointer(3, GL2.GL_FLOAT, local282, 0L);
			local257.glColorPointer(4, GL2.GL_UNSIGNED_BYTE, local282, 12L);
			if (Preferences.highDetailLighting) {
				local257.glNormalPointer(GL2.GL_FLOAT, local282, 16L);
			}
			local257.glTexCoordPointer(2, GL2.GL_FLOAT, local282, 28L);
			if (this.aBoolean139) {
				local257.glClientActiveTexture(UnderwaterMaterialRenderer.method4607());
				local257.glTexCoordPointer(1, GL2.GL_FLOAT, local282, 36L);
				local257.glClientActiveTexture(GL2.GL_TEXTURE0);
			}
		}
		if (GlRenderer.arbVboSupported) {
			local257.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, 0);
		}
		if (aClass3_Sub15_3.offset != 0) {
			if (aByteBuffer5 == null || aByteBuffer5.capacity() < aClass3_Sub15_3.offset) {
				aByteBuffer5 = ByteBuffer.allocateDirect(aClass3_Sub15_3.offset).order(ByteOrder.nativeOrder());
			} else {
				aByteBuffer5.clear();
			}
			aByteBuffer5.put(aClass3_Sub15_3.data, 0, aClass3_Sub15_3.offset);
			aByteBuffer5.flip();
			GlRenderer.method4159(arg1);
			local257.glDrawElements(GL2.GL_TRIANGLES, aClass3_Sub15_3.offset / 4, GL2.GL_UNSIGNED_INT, aByteBuffer5);
		}
		if (aClass3_Sub15_2.offset == 0) {
			return;
		}
		if (aByteBuffer4 == null || aByteBuffer4.capacity() < aClass3_Sub15_2.offset) {
			aByteBuffer4 = ByteBuffer.allocateDirect(aClass3_Sub15_2.offset).order(ByteOrder.nativeOrder());
		} else {
			aByteBuffer4.clear();
		}
		aByteBuffer4.put(aClass3_Sub15_2.data, 0, aClass3_Sub15_2.offset);
		aByteBuffer4.flip();
		GlRenderer.method4159(arg1 - 100.0F);
		GlRenderer.disableDepthMask();
		local257.glDrawElements(GL2.GL_TRIANGLES, aClass3_Sub15_2.offset / 4, GL2.GL_UNSIGNED_INT, aByteBuffer4);
		GlRenderer.enableDepthMask();
	}

	@OriginalMember(owner = "runetek4.client!hg", name = "a", descriptor = "(III[I[IZ)I")
	public final int method1945(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4, @OriginalArg(5) boolean arg5) {
		if (this.blend) {
			this.anIntArrayArray18[this.anInt2489] = arg4;
			this.aBooleanArray54[this.anInt2489] = arg5;
			if (arg4 != null) {
				this.anInt2487 += arg4.length;
			}
			if (arg5) {
				this.anInt2487 += (arg3.length - 2) * 3;
			} else {
				this.anInt2488 += (arg3.length - 2) * 3;
			}
		} else {
			this.anInt2488 += (arg3.length - 2) * 3;
		}
		this.anIntArray231[this.anInt2489] = arg0;
		this.anIntArray228[this.anInt2489] = arg1;
		this.anIntArray227[this.anInt2489] = arg2;
		this.anIntArrayArray17[this.anInt2489] = arg3;
		return this.anInt2489++;
	}
}
