package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.data.cache.CacheArchive;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.config.types.flu.FluTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!si")
public final class TextureOpRasterizer extends TextureOp {

	@OriginalMember(owner = "runetek4.client!ic", name = "j", descriptor = "I")
	public static int anInt2869 = 100;
	@OriginalMember(owner = "runetek4.client!nj", name = "i", descriptor = "I")
	public static int anInt4164 = 0;
	@OriginalMember(owner = "runetek4.client!vl", name = "h", descriptor = "I")
	public static int anInt5773 = 0;
	@OriginalMember(owner = "runetek4.client!si", name = "U", descriptor = "[Lclient!kf;")
	private TextureOp29SubOp[] aClass18Array1;

	@OriginalMember(owner = "runetek4.client!si", name = "<init>", descriptor = "()V")
	public TextureOpRasterizer() {
		super(0, true);
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= anInt4164 && arg3 <= FluTypeList.anInt5063) {
			@Pc(22) int local22 = IntUtils.clamp(anInt2869, arg1, anInt5773);
			@Pc(28) int local28 = IntUtils.clamp(anInt2869, arg0, anInt5773);
			TextureOp29SubOp4.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!id", name = "a", descriptor = "(IIBII)V")
	public static void method2263(@OriginalArg(3) int arg0, @OriginalArg(4) int arg1) {
		FluTypeList.anInt5063 = arg1;
		anInt4164 = 0;
		anInt5773 = 0;
		anInt2869 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IIZII)V")
	public static void method4019(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= anInt5773 && arg3 <= anInt2869) {
			@Pc(15) int local15 = IntUtils.clamp(FluTypeList.anInt5063, arg0, anInt4164);
			@Pc(21) int local21 = IntUtils.clamp(FluTypeList.anInt5063, arg2, anInt4164);
			TextureOp29SubOp4.method3826(arg1, arg3, local21, local15);
		}
	}

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(13) int[] local13 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			this.method3934(this.monochromeImageCache.method3446());
		}
		return local13;
	}

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(I[[I)V")
	private void method3934(@OriginalArg(1) int[][] arg0) {
		@Pc(7) int local7 = Texture.height;
		@Pc(9) int local9 = Texture.width;
		ObjType.method1751(arg0);
		method2263(Texture.heightMask, Texture.widthMask);
		if (this.aClass18Array1 == null) {
			return;
		}
		for (@Pc(23) int local23 = 0; local23 < this.aClass18Array1.length; local23++) {
			@Pc(33) TextureOp29SubOp local33 = this.aClass18Array1[local23];
			@Pc(36) int local36 = local33.anInt5228;
			@Pc(39) int local39 = local33.anInt5229;
			if (local36 >= 0) {
				if (local39 < 0) {
					local33.method4013(local9, local7);
				} else {
					local33.method4007(local7, local9);
				}
			} else if (local39 >= 0) {
				local33.method4009(local7, local9);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.aClass18Array1 = new TextureOp29SubOp[packet.g1()];
			for (@Pc(11) int local11 = 0; local11 < this.aClass18Array1.length; local11++) {
				@Pc(24) int local24 = packet.g1();
				if (local24 == 0) {
					this.aClass18Array1[local11] = CacheArchive.method184(packet);
				} else if (local24 == 1) {
					this.aClass18Array1[local11] = TextureOp29SubOp3.method620(packet);
				} else if (local24 == 2) {
					this.aClass18Array1[local11] = TextureOp29SubOp4.method2960(packet);
				} else if (local24 == 3) {
					this.aClass18Array1[local11] = TextureOp29SubOp2.method2664(packet);
				}
			}
		} else if (code == 1) {
			this.monochrome = packet.g1() == 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!si", name = "b", descriptor = "(II)[[I")
	@Override
	public final int[][] getColorOutput(@OriginalArg(1) int arg0) {
		@Pc(14) int[][] local14 = this.colorImageCache.get(arg0);
		if (this.colorImageCache.invalid) {
			@Pc(20) int local20 = Texture.width;
			@Pc(22) int local22 = Texture.height;
			@Pc(26) int[][] local26 = new int[local22][local20];
			@Pc(31) int[][][] local31 = this.colorImageCache.method3168();
			this.method3934(local26);
			for (@Pc(37) int local37 = 0; local37 < Texture.height; local37++) {
				@Pc(44) int[] local44 = local26[local37];
				@Pc(48) int[][] local48 = local31[local37];
				@Pc(52) int[] local52 = local48[0];
				@Pc(56) int[] local56 = local48[1];
				@Pc(60) int[] local60 = local48[2];
				for (@Pc(62) int local62 = 0; local62 < Texture.width; local62++) {
					@Pc(73) int local73 = local44[local62];
					local60[local62] = (local73 & 0xFF) << 4;
					local56[local62] = local73 >> 4 & 0xFF0;
					local52[local62] = local73 >> 12 & 0xFF0;
				}
			}
		}
		return local14;
	}
}
