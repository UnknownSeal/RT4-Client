package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!fm")
public final class TextureOpColorFill extends TextureOp {

	@OriginalMember(owner = "runetek4.client!fm", name = "P", descriptor = "I")
	private int anInt2073;

	@OriginalMember(owner = "runetek4.client!fm", name = "X", descriptor = "I")
	private int anInt2078;

	@OriginalMember(owner = "runetek4.client!fm", name = "Z", descriptor = "I")
	private int anInt2080;

	@OriginalMember(owner = "runetek4.client!fm", name = "<init>", descriptor = "(I)V")
	private TextureOpColorFill(@OriginalArg(0) int arg0) {
		super(0, false);
		this.method1595(arg0);
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "<init>", descriptor = "()V")
	public TextureOpColorFill() {
		this(0);
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(BI)V")
	private void method1595(@OriginalArg(1) int arg0) {
		this.anInt2078 = arg0 >> 4 & 0xFF0;
		this.anInt2080 = (arg0 & 0xFF) << 4;
		this.anInt2073 = arg0 >> 12 & 0xFF0;
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "b", descriptor = "(II)[[I")
	@Override
	public final int[][] method4638(@OriginalArg(1) int arg0) {
		@Pc(22) int[][] local22 = this.aClass103_41.method3173(arg0);
		if (this.aClass103_41.aBoolean195) {
			@Pc(31) int[] local31 = local22[0];
			@Pc(35) int[] local35 = local22[1];
			@Pc(39) int[] local39 = local22[2];
			for (@Pc(41) int local41 = 0; local41 < Texture.width; local41++) {
				local31[local41] = this.anInt2073;
				local35[local41] = this.anInt2078;
				local39[local41] = this.anInt2080;
			}
		}
		return local22;
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.method1595(packet.g3());
		}
	}
}
