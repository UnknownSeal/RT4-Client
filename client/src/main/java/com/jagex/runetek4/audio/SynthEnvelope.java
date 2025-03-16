package com.jagex.runetek4.audio;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ff")
public final class SynthEnvelope {

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "I")
	public int anInt1958;

	@OriginalMember(owner = "runetek4.client!ff", name = "d", descriptor = "I")
	public int start;

	@OriginalMember(owner = "runetek4.client!ff", name = "f", descriptor = "I")
	public int end;

	@OriginalMember(owner = "runetek4.client!ff", name = "g", descriptor = "I")
	private int phase_idx;

	@OriginalMember(owner = "runetek4.client!ff", name = "h", descriptor = "I")
	private int amp;

	@OriginalMember(owner = "runetek4.client!ff", name = "i", descriptor = "I")
	private int step;

	@OriginalMember(owner = "runetek4.client!ff", name = "j", descriptor = "I")
	private int critical;

	@OriginalMember(owner = "runetek4.client!ff", name = "k", descriptor = "I")
	private int ticks;

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "I")
	private int num_phases = 2;

	@OriginalMember(owner = "runetek4.client!ff", name = "e", descriptor = "[I")
	private int[] phase_dur = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "c", descriptor = "[I")
	private int[] phase_peak = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "<init>", descriptor = "()V")
	public SynthEnvelope() {
		this.phase_dur[0] = 0;
		this.phase_dur[1] = 65535;
		this.phase_peak[0] = 0;
		this.phase_peak[1] = 65535;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(I)I")
	public final int step(@OriginalArg(0) int arg0) {
		if (this.ticks >= this.critical) {
			this.amp = this.phase_peak[this.phase_idx++] << 15;
			if (this.phase_idx >= this.num_phases) {
				this.phase_idx = this.num_phases - 1;
			}
			this.critical = (int) ((double) this.phase_dur[this.phase_idx] / 65536.0D * (double) arg0);
			if (this.critical > this.ticks) {
				this.step = ((this.phase_peak[this.phase_idx] << 15) - this.amp) / (this.critical - this.ticks);
			}
		}
		this.amp += this.step;
		this.ticks++;
		return this.amp - this.step >> 15;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "()V")
	public final void reset() {
		this.critical = 0;
		this.phase_idx = 0;
		this.step = 0;
		this.amp = 0;
		this.ticks = 0;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(Lclient!wa;)V")
	public final void decode_shape(@OriginalArg(0) Packet packet) {
		this.num_phases = packet.g1();
		this.phase_dur = new int[this.num_phases];
		this.phase_peak = new int[this.num_phases];
		for (@Pc(16) int i = 0; i < this.num_phases; i++) {
			this.phase_dur[i] = packet.g2();
			this.phase_peak[i] = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "(Lclient!wa;)V")
	public final void decode(@OriginalArg(0) Packet packet) {
		this.anInt1958 = packet.g1();
		this.start = packet.g4();
		this.end = packet.g4();
		this.decode_shape(packet);
	}
}
