package com.jagex.runetek4.audio.vorbis;

import com.jagex.runetek4.Static117;
import com.jagex.runetek4.Static204;
import com.jagex.runetek4.Static288;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ji")
public final class VorbisCodebook {

	@OriginalMember(owner = "client!ji", name = "f", descriptor = "[I")
	private int[] entryTree;

	@OriginalMember(owner = "client!ji", name = "c", descriptor = "I")
	public final int anInt3055;

	@OriginalMember(owner = "client!ji", name = "e", descriptor = "I")
	private final int entries;

	@OriginalMember(owner = "client!ji", name = "a", descriptor = "[I")
	private final int[] lengthList;

	@OriginalMember(owner = "client!ji", name = "d", descriptor = "[I")
	private int[] anIntArray287;

	@OriginalMember(owner = "client!ji", name = "b", descriptor = "[[F")
	private float[][] aFloatArrayArray1;

	@OriginalMember(owner = "client!ji", name = "<init>", descriptor = "()V")
	public VorbisCodebook() {
		VorbisSound.read(24);
		this.anInt3055 = VorbisSound.read(16);
		this.entries = VorbisSound.read(24);
		this.lengthList = new int[this.entries];
		@Pc(23) boolean local23 = VorbisSound.readBit() != 0;
		@Pc(27) int local27;
		@Pc(32) int local32;
		@Pc(46) int local46;
		if (local23) {
			local27 = 0;
			local32 = VorbisSound.read(5) + 1;
			while (local27 < this.entries) {
				@Pc(44) int local44 = VorbisSound.read(Static204.method3674(this.entries - local27));
				for (local46 = 0; local46 < local44; local46++) {
					this.lengthList[local27++] = local32;
				}
				local32++;
			}
		} else {
			@Pc(66) boolean local66 = VorbisSound.readBit() != 0;
			for (local32 = 0; local32 < this.entries; local32++) {
				if (local66 && VorbisSound.readBit() == 0) {
					this.lengthList[local32] = 0;
				} else {
					this.lengthList[local32] = VorbisSound.read(5) + 1;
				}
			}
		}
		this.makeWords();
		local27 = VorbisSound.read(4);
		if (local27 > 0) {
			@Pc(103) float local103 = Static117.method2340(VorbisSound.read(32));
			@Pc(107) float local107 = Static117.method2340(VorbisSound.read(32));
			local46 = VorbisSound.read(4) + 1;
			@Pc(118) boolean local118 = VorbisSound.readBit() != 0;
			@Pc(127) int local127;
			if (local27 == 1) {
				local127 = Static288.method2416(this.entries, this.anInt3055);
			} else {
				local127 = this.entries * this.anInt3055;
			}
			this.anIntArray287 = new int[local127];
			@Pc(140) int local140;
			for (local140 = 0; local140 < local127; local140++) {
				this.anIntArray287[local140] = VorbisSound.read(local46);
			}
			this.aFloatArrayArray1 = new float[this.entries][this.anInt3055];
			@Pc(169) float local169;
			@Pc(171) int local171;
			@Pc(173) int local173;
			if (local27 == 1) {
				for (local140 = 0; local140 < this.entries; local140++) {
					local169 = 0.0F;
					local171 = 1;
					for (local173 = 0; local173 < this.anInt3055; local173++) {
						@Pc(183) int local183 = local140 / local171 % local127;
						@Pc(195) float local195 = (float) this.anIntArray287[local183] * local107 + local103 + local169;
						this.aFloatArrayArray1[local140][local173] = local195;
						if (local118) {
							local169 = local195;
						}
						local171 *= local127;
					}
				}
			} else {
				for (local140 = 0; local140 < this.entries; local140++) {
					local169 = 0.0F;
					local171 = local140 * this.anInt3055;
					for (local173 = 0; local173 < this.anInt3055; local173++) {
						@Pc(246) float local246 = (float) this.anIntArray287[local171] * local107 + local103 + local169;
						this.aFloatArrayArray1[local140][local173] = local246;
						if (local118) {
							local169 = local246;
						}
						local171++;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ji", name = "a", descriptor = "()[F")
	public float[] method2413() {
		return this.aFloatArrayArray1[this.decodeScalar()];
	}

	@OriginalMember(owner = "client!ji", name = "b", descriptor = "()V")
	private void makeWords() {
		@Pc(3) int[] r = new int[this.entries];
		@Pc(6) int[] marker = new int[33];
		@Pc(8) int local8;
		@Pc(17) int local17;
		@Pc(26) int local26;
		@Pc(30) int local30;
		@Pc(44) int local44;
		@Pc(53) int local53;
		@Pc(69) int local69;

		for (int i = 0; i < this.entries; i++) {
			int length = this.lengthList[i];

			if (length != 0) {
				local26 = 0x1 << 32 - length;
				int entry = marker[length];
				r[i] = entry;
				@Pc(60) int local60;
				if ((entry & local26) == 0) {
					local44 = entry | local26;
					for (local53 = length - 1; local53 >= 1; local53--) {
						local60 = marker[local53];
						if (local60 != entry) {
							break;
						}
						local69 = 0x1 << 32 - local53;
						if ((local60 & local69) != 0) {
							marker[local53] = marker[local53 - 1];
							break;
						}
						marker[local53] = local60 | local69;
					}
				} else {
					local44 = marker[length - 1];
				}
				marker[length] = local44;
				for (local53 = length + 1; local53 <= 32; local53++) {
					local60 = marker[local53];
					if (local60 == entry) {
						marker[local53] = local44;
					}
				}
			}
		}
		this.entryTree = new int[8];
		@Pc(122) int local122 = 0;
		for (local8 = 0; local8 < this.entries; local8++) {
			local17 = this.lengthList[local8];
			if (local17 != 0) {
				local26 = r[local8];
				local30 = 0;
				for (local44 = 0; local44 < local17; local44++) {
					local53 = Integer.MIN_VALUE >>> local44;
					if ((local26 & local53) == 0) {
						local30++;
					} else {
						if (this.entryTree[local30] == 0) {
							this.entryTree[local30] = local122;
						}
						local30 = this.entryTree[local30];
					}
					if (local30 >= this.entryTree.length) {
						@Pc(184) int[] local184 = new int[this.entryTree.length * 2];
						for (local69 = 0; local69 < this.entryTree.length; local69++) {
							local184[local69] = this.entryTree[local69];
						}
						this.entryTree = local184;
					}
				}
				this.entryTree[local30] = ~local8;
				if (local30 >= local122) {
					local122 = local30 + 1;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ji", name = "c", descriptor = "()I")
	public int decodeScalar() {
		@Pc(1) int index;
		for (index = 0; this.entryTree[index] >= 0; index = VorbisSound.readBit() == 0 ? index + 1 : this.entryTree[index]) {
		}
		return ~this.entryTree[index];
	}
}
