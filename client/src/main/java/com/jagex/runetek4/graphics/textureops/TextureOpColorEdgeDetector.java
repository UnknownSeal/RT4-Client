package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!an")
public final class TextureOpColorEdgeDetector extends TextureOp {

	@OriginalMember(owner = "runetek4.client!an", name = "R", descriptor = "I")
	private int anInt316 = 4096;

	@OriginalMember(owner = "runetek4.client!an", name = "bb", descriptor = "Z")
	private boolean aBoolean11 = true;

	@OriginalMember(owner = "runetek4.client!an", name = "<init>", descriptor = "()V")
	public TextureOpColorEdgeDetector() {
		super(1, false);
	}

	@OriginalMember(owner = "runetek4.client!an", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt316 = packet.g2();
		} else if (code == 1) {
			this.aBoolean11 = packet.g1() == 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!an", name = "b", descriptor = "(II)[[I")
	@Override
	public final int[][] getColorOutput(@OriginalArg(1) int arg0) {
		@Pc(11) int[][] local11 = this.colorImageCache.get(arg0);
		if (this.colorImageCache.invalid) {
			@Pc(25) int[] local25 = this.getChildMonochromeOutput(0, Texture.heightMask & arg0 - 1);
			@Pc(31) int[] local31 = this.getChildMonochromeOutput(0, arg0);
			@Pc(41) int[] local41 = this.getChildMonochromeOutput(0, arg0 + 1 & Texture.heightMask);
			@Pc(45) int[] local45 = local11[0];
			@Pc(49) int[] local49 = local11[1];
			@Pc(53) int[] local53 = local11[2];
			for (@Pc(55) int local55 = 0; local55 < Texture.width; local55++) {
				@Pc(70) int local70 = this.anInt316 * (local41[local55] - local25[local55]);
				@Pc(90) int local90 = this.anInt316 * (local31[local55 + 1 & Texture.widthMask] - local31[Texture.widthMask & local55 - 1]);
				@Pc(94) int local94 = local70 >> 12;
				@Pc(98) int local98 = local90 >> 12;
				@Pc(104) int local104 = local94 * local94 >> 12;
				@Pc(110) int local110 = local98 * local98 >> 12;
				@Pc(124) int local124 = (int) (Math.sqrt((double) ((float) (local110 + local104 + 4096) / 4096.0F)) * 4096.0D);
				@Pc(131) int local131;
				@Pc(133) int local133;
				@Pc(129) int local129;
				if (local124 == 0) {
					local129 = 0;
					local131 = 0;
					local133 = 0;
				} else {
					local129 = 16777216 / local124;
					local133 = local70 / local124;
					local131 = local90 / local124;
				}
				if (this.aBoolean11) {
					local133 = (local133 >> 1) + 2048;
					local129 = (local129 >> 1) + 2048;
					local131 = (local131 >> 1) + 2048;
				}
				local45[local55] = local131;
				local49[local55] = local133;
				local53[local55] = local129;
			}
		}
		return local11;
	}
}
