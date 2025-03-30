package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.textureops.TextureOp;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!mi")
public final class TextureMonochromeFill extends TextureOp {

	@OriginalMember(owner = "runetek4.client!mi", name = "bb", descriptor = "I")
	private int anInt3894;

	@OriginalMember(owner = "runetek4.client!mi", name = "<init>", descriptor = "(I)V")
	private TextureMonochromeFill(@OriginalArg(0) int arg0) {
		super(0, true);
		this.anInt3894 = 4096;
		this.anInt3894 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!mi", name = "<init>", descriptor = "()V")
	public TextureMonochromeFill() {
		this(4096);
	}

	@OriginalMember(owner = "runetek4.client!mi", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt3894 = (packet.g1() << 12) / 255;
		}
	}

	@OriginalMember(owner = "runetek4.client!mi", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(17) int[] local17 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			ArrayUtils.fill(local17, 0, Texture.width, this.anInt3894);
		}
		return local17;
	}
}
