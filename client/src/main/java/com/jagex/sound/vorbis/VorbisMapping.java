package com.jagex.sound.vorbis;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!uk")
public final class VorbisMapping {

	@OriginalMember(owner = "runetek4.client!uk", name = "d", descriptor = "I")
	public final int submaps;

	@OriginalMember(owner = "runetek4.client!uk", name = "a", descriptor = "I")
	public int mux;

	@OriginalMember(owner = "runetek4.client!uk", name = "b", descriptor = "[I")
	public final int[] submapFloor;

	@OriginalMember(owner = "runetek4.client!uk", name = "c", descriptor = "[I")
	public final int[] submapResidue;

	@OriginalMember(owner = "runetek4.client!uk", name = "<init>", descriptor = "()V")
	public VorbisMapping() {
		VorbisSound.gbit(16);
		this.submaps = VorbisSound.g1() == 0 ? 1 : VorbisSound.gbit(4) + 1;
		if (VorbisSound.g1() != 0) {
			VorbisSound.gbit(8);
		}
		VorbisSound.gbit(2);
		if (this.submaps > 1) {
			this.mux = VorbisSound.gbit(4);
		}
		this.submapFloor = new int[this.submaps];
		this.submapResidue = new int[this.submaps];
		for (@Pc(42) int local42 = 0; local42 < this.submaps; local42++) {
			VorbisSound.gbit(8);
			this.submapFloor[local42] = VorbisSound.gbit(8);
			this.submapResidue[local42] = VorbisSound.gbit(8);
		}
	}
}
