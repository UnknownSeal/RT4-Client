package com.jagex.runetek4.textureops;

import com.jagex.runetek4.Texture;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ui")
public final class TextureOpTexture extends TextureOp {

	@OriginalMember(owner = "runetek4.client!ui", name = "U", descriptor = "[I")
	private int[] pixels;

	@OriginalMember(owner = "runetek4.client!ui", name = "gb", descriptor = "I")
	private int width;

	@OriginalMember(owner = "runetek4.client!ui", name = "ib", descriptor = "I")
	private int height;

	@OriginalMember(owner = "runetek4.client!ui", name = "lb", descriptor = "I")
	private int textureId = -1;

	@OriginalMember(owner = "runetek4.client!ui", name = "<init>", descriptor = "()V")
	public TextureOpTexture() {
		super(0, false);
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "b", descriptor = "(II)[[I")
	@Override
	public final int[][] getColorOutput(@OriginalArg(1) int arg0) {
		@Pc(17) int[][] local17 = this.colorImageCache.get(arg0);
		if (this.colorImageCache.invalid && this.loadTexture()) {
			@Pc(42) int local42 = (this.height == Texture.height ? arg0 : this.height * arg0 / Texture.height) * this.width;
			@Pc(46) int[] local46 = local17[0];
			@Pc(50) int[] local50 = local17[1];
			@Pc(54) int[] local54 = local17[2];
			@Pc(64) int local64;
			@Pc(73) int local73;
			if (Texture.width == this.width) {
				for (local64 = 0; local64 < Texture.width; local64++) {
					local73 = this.pixels[local42++];
					local54[local64] = (local73 & 0xFF) << 4;
					local50[local64] = local73 >> 4 & 0xFF0;
					local46[local64] = local73 >> 12 & 0xFF0;
				}
			} else {
				for (local64 = 0; local64 < Texture.width; local64++) {
					local73 = this.width * local64 / Texture.width;
					@Pc(122) int local122 = this.pixels[local42 + local73];
					local54[local64] = (local122 & 0xFF) << 4;
					local50[local64] = local122 >> 4 & 0xFF0;
					local46[local64] = local122 >> 12 & 0xFF0;
				}
			}
		}
		return local17;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(Z)Z")
	private boolean loadTexture() {
		if (this.pixels != null) {
			return true;
		} else if (this.textureId < 0) {
			return false;
		} else {
			@Pc(22) int local22 = Texture.width;
			@Pc(24) int local24 = Texture.height;
			@Pc(34) int local34 = Texture.provider.isLowDetail(this.textureId) ? 64 : 128;
			this.pixels = Texture.provider.getPixels(this.textureId);
			this.height = local34;
			this.width = local34;
			Texture.setSize(local24, local22);
			return this.pixels != null;
		}
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.textureId = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "e", descriptor = "(B)V")
	@Override
	public final void clearImageCache() {
		super.clearImageCache();
		this.pixels = null;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "d", descriptor = "(B)I")
	@Override
	public final int method4627() {
		return this.textureId;
	}
}
