package com.jagex.runetek4.audio.vorbis;

import com.jagex.runetek4.audio.vorbis.VorbisSound;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!uk")
public final class VorbisMapping {

	@OriginalMember(owner = "runetek4.client!uk", name = "d", descriptor = "I")
	public final int anInt5563;

	@OriginalMember(owner = "runetek4.client!uk", name = "a", descriptor = "I")
	public int anInt5562;

	@OriginalMember(owner = "runetek4.client!uk", name = "b", descriptor = "[I")
	public final int[] anIntArray490;

	@OriginalMember(owner = "runetek4.client!uk", name = "c", descriptor = "[I")
	public final int[] anIntArray491;

	@OriginalMember(owner = "runetek4.client!uk", name = "<init>", descriptor = "()V")
	public VorbisMapping() {
		VorbisSound.read(16);
		this.anInt5563 = VorbisSound.readBit() == 0 ? 1 : VorbisSound.read(4) + 1;
		if (VorbisSound.readBit() != 0) {
			VorbisSound.read(8);
		}
		VorbisSound.read(2);
		if (this.anInt5563 > 1) {
			this.anInt5562 = VorbisSound.read(4);
		}
		this.anIntArray490 = new int[this.anInt5563];
		this.anIntArray491 = new int[this.anInt5563];
		for (@Pc(42) int local42 = 0; local42 < this.anInt5563; local42++) {
			VorbisSound.read(8);
			this.anIntArray490[local42] = VorbisSound.read(8);
			this.anIntArray491[local42] = VorbisSound.read(8);
		}
	}
}
