package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!si")
public final class TextureOp29 extends TextureOp {

	@OriginalMember(owner = "runetek4.client!si", name = "U", descriptor = "[Lclient!kf;")
	private TextureOp29SubOp[] aClass18Array1;

	@OriginalMember(owner = "runetek4.client!si", name = "<init>", descriptor = "()V")
	public TextureOp29() {
		super(0, true);
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
		Static107.method2263(Texture.heightMask, Texture.widthMask);
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
					this.aClass18Array1[local11] = Static21.method620(packet);
				} else if (local24 == 2) {
					this.aClass18Array1[local11] = Static156.method2960(packet);
				} else if (local24 == 3) {
					this.aClass18Array1[local11] = Static137.method2664(packet);
				}
			}
		} else if (code == 1) {
			this.monochrome = packet.g1() == 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!si", name = "b", descriptor = "(II)[[I")
	@Override
	public final int[][] getColorOutput(@OriginalArg(1) int arg0) {
		@Pc(14) int[][] local14 = this.clearImageCache.get(arg0);
		if (this.clearImageCache.invalid) {
			@Pc(20) int local20 = Texture.width;
			@Pc(22) int local22 = Texture.height;
			@Pc(26) int[][] local26 = new int[local22][local20];
			@Pc(31) int[][][] local31 = this.clearImageCache.method3168();
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
