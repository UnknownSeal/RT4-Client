package com.jagex.sound.synthesis;

import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ff")
public final class SynthEnvelope {

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "I")
	public int wavetable;

	@OriginalMember(owner = "runetek4.client!ff", name = "d", descriptor = "I")
	public int minInterval;

	@OriginalMember(owner = "runetek4.client!ff", name = "f", descriptor = "I")
	public int maxInterval;

	@OriginalMember(owner = "runetek4.client!ff", name = "g", descriptor = "I")
	private int phase;

	@OriginalMember(owner = "runetek4.client!ff", name = "h", descriptor = "I")
	private int level;

	@OriginalMember(owner = "runetek4.client!ff", name = "i", descriptor = "I")
	private int slope;

	@OriginalMember(owner = "runetek4.client!ff", name = "j", descriptor = "I")
	private int nextTime;

	@OriginalMember(owner = "runetek4.client!ff", name = "k", descriptor = "I")
	private int time;

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "I")
	private int stages = 2;

	@OriginalMember(owner = "runetek4.client!ff", name = "e", descriptor = "[I")
	private int[] times = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "c", descriptor = "[I")
	private int[] levels = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "<init>", descriptor = "()V")
	public SynthEnvelope() {
		this.times[0] = 0;
		this.times[1] = 65535;
		this.levels[0] = 0;
		this.levels[1] = 65535;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(I)I")
	public final int nextLevel(@OriginalArg(0) int arg0) {
		if (this.time >= this.nextTime) {
			this.level = this.levels[this.phase++] << 15;
			if (this.phase >= this.stages) {
				this.phase = this.stages - 1;
			}
			this.nextTime = (int) ((double) this.times[this.phase] / 65536.0D * (double) arg0);
			if (this.nextTime > this.time) {
				this.slope = ((this.levels[this.phase] << 15) - this.level) / (this.nextTime - this.time);
			}
		}
		this.level += this.slope;
		this.time++;
		return this.level - this.slope >> 15;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "()V")
	public final void reset() {
		this.nextTime = 0;
		this.phase = 0;
		this.slope = 0;
		this.level = 0;
		this.time = 0;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(Lclient!wa;)V")
	public final void decodeStages(@OriginalArg(0) Packet packet) {
		this.stages = packet.g1();
		this.times = new int[this.stages];
		this.levels = new int[this.stages];
		for (@Pc(16) int i = 0; i < this.stages; i++) {
			this.times[i] = packet.g2();
			this.levels[i] = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "(Lclient!wa;)V")
	public final void decode(@OriginalArg(0) Packet packet) {
		this.wavetable = packet.g1();
		this.minInterval = packet.g4();
		this.maxInterval = packet.g4();
		this.decodeStages(packet);
	}
}
