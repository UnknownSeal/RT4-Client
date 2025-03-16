package com.jagex.runetek4.audio;

import com.jagex.runetek4.PcmSound;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!sl")
public final class SynthSound {

	@OriginalMember(owner = "runetek4.client!sl", name = "b", descriptor = "[Lclient!pj;")
	private final SynthInstrument[] SynthInstruments = new SynthInstrument[10];

	@OriginalMember(owner = "runetek4.client!sl", name = "c", descriptor = "I")
	private int loop_begin;

	@OriginalMember(owner = "runetek4.client!sl", name = "a", descriptor = "I")
	private int loop_end;

	@OriginalMember(owner = "runetek4.client!sl", name = "<init>", descriptor = "(Lclient!wa;)V")
	public SynthSound(@OriginalArg(0) Packet packet) {
		for (@Pc(7) int local7 = 0; local7 < 10; local7++) {
			@Pc(14) int local14 = packet.g1();
			if (local14 != 0) {
				packet.position--;
				this.SynthInstruments[local7] = new SynthInstrument();
				this.SynthInstruments[local7].decode(packet);
			}
		}
		this.loop_begin = packet.g2();
		this.loop_end = packet.g2();
	}

	@OriginalMember(owner = "runetek4.client!sl", name = "a", descriptor = "(Lclient!ve;II)Lclient!sl;")
	public static SynthSound create(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(5) byte[] local5 = arg0.getfile(arg1, arg2);
		return local5 == null ? null : new SynthSound(new Packet(local5));
	}

	@OriginalMember(owner = "runetek4.client!sl", name = "a", descriptor = "()[B")
	private byte[] method3987() {
		@Pc(1) int local1 = 0;
		@Pc(3) int local3;
		for (local3 = 0; local3 < 10; local3++) {
			if (this.SynthInstruments[local3] != null && this.SynthInstruments[local3].anInt4546 + this.SynthInstruments[local3].begin > local1) {
				local1 = this.SynthInstruments[local3].anInt4546 + this.SynthInstruments[local3].begin;
			}
		}
		if (local1 == 0) {
			return new byte[0];
		}
		local3 = local1 * 22050 / 1000;
		@Pc(52) byte[] local52 = new byte[local3];
		for (@Pc(54) int local54 = 0; local54 < 10; local54++) {
			if (this.SynthInstruments[local54] != null) {
				@Pc(72) int local72 = this.SynthInstruments[local54].anInt4546 * 22050 / 1000;
				@Pc(82) int local82 = this.SynthInstruments[local54].begin * 22050 / 1000;
				@Pc(94) int[] local94 = this.SynthInstruments[local54].synthesize(local72, this.SynthInstruments[local54].anInt4546);
				for (@Pc(96) int local96 = 0; local96 < local72; local96++) {
					@Pc(111) int local111 = local52[local96 + local82] + (local94[local96] >> 8);
					if ((local111 + 128 & 0xFFFFFF00) != 0) {
						local111 = local111 >> 31 ^ 0x7F;
					}
					local52[local96 + local82] = (byte) local111;
				}
			}
		}
		return local52;
	}

	@OriginalMember(owner = "runetek4.client!sl", name = "b", descriptor = "()Lclient!kj;")
	public final PcmSound toPcmSound() {
		@Pc(2) byte[] local2 = this.method3987();
		return new PcmSound(22050, local2, this.loop_begin * 22050 / 1000, this.loop_end * 22050 / 1000);
	}

	@OriginalMember(owner = "runetek4.client!sl", name = "c", descriptor = "()I")
	public final int delay() {
		@Pc(1) int offset = 9999999;
		@Pc(3) int i;
		for (i = 0; i < 10; i++) {
			if (this.SynthInstruments[i] != null && this.SynthInstruments[i].begin / 20 < offset) {
				offset = this.SynthInstruments[i].begin / 20;
			}
		}
		if (this.loop_begin < this.loop_end && this.loop_begin / 20 < offset) {
			offset = this.loop_begin / 20;
		}
		if (offset == 9999999 || offset == 0) {
			return 0;
		}
		for (i = 0; i < 10; i++) {
			if (this.SynthInstruments[i] != null) {
				this.SynthInstruments[i].begin -= offset * 20;
			}
		}
		if (this.loop_begin < this.loop_end) {
			this.loop_begin -= offset * 20;
			this.loop_end -= offset * 20;
		}
		return offset;
	}
}
