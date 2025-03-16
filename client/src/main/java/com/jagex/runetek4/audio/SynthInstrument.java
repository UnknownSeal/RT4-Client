package com.jagex.runetek4.audio;

import java.util.Random;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!pj")
public final class SynthInstrument {

	@OriginalMember(owner = "runetek4.client!pj", name = "k", descriptor = "[I")
	private static final int[] noise = new int[32768];

	@OriginalMember(owner = "runetek4.client!pj", name = "h", descriptor = "[I")
	private static final int[] sine;

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "Lclient!ff;")
	private SynthEnvelope vol_mod_amp_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "b", descriptor = "Lclient!ff;")
	private SynthEnvelope gating_release_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "d", descriptor = "Lclient!ff;")
	private SynthEnvelope vol_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "e", descriptor = "Lclient!ff;")
	private SynthEnvelope gating_attack_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "f", descriptor = "Lclient!ff;")
	private SynthEnvelope pitch_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "i", descriptor = "Lclient!ff;")
	private SynthEnvelope filter_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "l", descriptor = "Lclient!ff;")
	private SynthEnvelope vol_mod_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "w", descriptor = "Lclient!nl;")
	private SynthFilter filter;

	@OriginalMember(owner = "runetek4.client!pj", name = "x", descriptor = "Lclient!ff;")
	private SynthEnvelope pitch_mod_amp_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "y", descriptor = "Lclient!ff;")
	private SynthEnvelope pitch_mod_env;

	@OriginalMember(owner = "runetek4.client!pj", name = "c", descriptor = "I")
	public int anInt4546 = 500;

	@OriginalMember(owner = "runetek4.client!pj", name = "g", descriptor = "I")
	private int delay_time = 0;

	@OriginalMember(owner = "runetek4.client!pj", name = "j", descriptor = "I")
	public int begin = 0;

	@OriginalMember(owner = "runetek4.client!pj", name = "m", descriptor = "[I")
	private final int[] oscill_vol = new int[] { 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "runetek4.client!pj", name = "n", descriptor = "[I")
	private final int[] oscill_delay = new int[] { 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "runetek4.client!pj", name = "u", descriptor = "I")
	private int delay_feedback = 100;

	@OriginalMember(owner = "runetek4.client!pj", name = "v", descriptor = "[I")
	private final int[] anIntArray404 = new int[] { 0, 0, 0, 0, 0 };

	static {
		@Pc(7) Random random = new Random(0L);
		@Pc(9) int i;
		for (i = 0; i < 32768; i++) {
			noise[i] = (random.nextInt() & 0x2) - 1;
		}
		sine = new int[32768];
		for (i = 0; i < 32768; i++) {
			sine[i] = (int) (Math.sin((double) i / 5215.1903D) * 16384.0D);
		}
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(III)I")
	private int evaluateWave(@OriginalArg(0) int phase, @OriginalArg(1) int amplitude, @OriginalArg(2) int table) {
		if (table == 1) {
			return (phase & 0x7FFF) < 16384 ? amplitude : -amplitude;
		} else if (table == 2) {
			return sine[phase & 0x7FFF] * amplitude >> 14;
		} else if (table == 3) {
			return ((phase & 0x7FFF) * amplitude >> 14) - amplitude;
		} else if (table == 4) {
			return noise[phase / 2607 & 0x7FFF] * amplitude;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(II)[I")
	public final int[] synthesize(@OriginalArg(0) int arg0, @OriginalArg(1) int dt) {
		ArrayUtils.clear(Static194.output, 0, arg0);
		if (dt < 10) {
			return Static194.output;
		}
		@Pc(16) double local16 = (double) arg0 / ((double) dt + 0.0D);
		this.pitch_env.reset();
		this.vol_env.reset();
		@Pc(24) int pitch_mod_step = 0;
		@Pc(26) int pitch_mod_base_step = 0;
		@Pc(28) int pitch_mod_phase = 0;
		if (this.pitch_mod_env != null) {
			this.pitch_mod_env.reset();
			this.pitch_mod_amp_env.reset();
			pitch_mod_step = (int) ((double) (this.pitch_mod_env.end - this.pitch_mod_env.start) * 32.768D / local16);
			pitch_mod_base_step = (int) ((double) this.pitch_mod_env.start * 32.768D / local16);
		}
		@Pc(63) int vol_mod_step = 0;
		@Pc(65) int vol_mod_base_step = 0;
		@Pc(67) int vol_mod_phase = 0;
		if (this.vol_mod_env != null) {
			this.vol_mod_env.reset();
			this.vol_mod_amp_env.reset();
			vol_mod_step = (int) ((double) (this.vol_mod_env.end - this.vol_mod_env.start) * 32.768D / local16);
			vol_mod_base_step = (int) ((double) this.vol_mod_env.start * 32.768D / local16);
		}
		@Pc(102) int counter;
		for (counter = 0; counter < 5; counter++) {
			if (this.oscill_vol[counter] != 0) {
				Static194.phases[counter] = 0;
				Static194.delays[counter] = (int) ((double) this.oscill_delay[counter] * local16);
				Static194.vol_step[counter] = (this.oscill_vol[counter] << 14) / 100;
				Static194.pitch_step[counter] = (int) ((double) (this.pitch_env.end - this.pitch_env.start) * 32.768D * Math.pow(1.0057929410678534D, (double) this.anIntArray404[counter]) / local16);
				Static194.pitch_base_step[counter] = (int) ((double) this.pitch_env.start * 32.768D / local16);
			}
		}
		@Pc(185) int pitch_change;
		@Pc(190) int vol_change;
		@Pc(198) int mod;
		@Pc(203) int mod_amp;
		for (counter = 0; counter < arg0; counter++) {
			pitch_change = this.pitch_env.step(arg0);
			vol_change = this.vol_env.step(arg0);
			if (this.pitch_mod_env != null) {
				mod = this.pitch_mod_env.step(arg0);
				mod_amp = this.pitch_mod_amp_env.step(arg0);
				pitch_change += this.evaluateWave(pitch_mod_phase, mod_amp, this.pitch_mod_env.anInt1958) >> 1;
				pitch_mod_phase += (mod * pitch_mod_step >> 16) + pitch_mod_base_step;
			}
			if (this.vol_mod_env != null) {
				mod = this.vol_mod_env.step(arg0);
				mod_amp = this.vol_mod_amp_env.step(arg0);
				vol_change = vol_change * ((this.evaluateWave(vol_mod_phase, mod_amp, this.vol_mod_env.anInt1958) >> 1) + 32768) >> 15;
				vol_mod_phase += (mod * vol_mod_step >> 16) + vol_mod_base_step;
			}
			for (mod = 0; mod < 5; mod++) {
				if (this.oscill_vol[mod] != 0) {
					mod_amp = counter + Static194.delays[mod];
					if (mod_amp < arg0) {
						Static194.output[mod_amp] += this.evaluateWave(Static194.phases[mod], vol_change * Static194.vol_step[mod] >> 15, this.pitch_env.anInt1958);
						Static194.phases[mod] += (pitch_change * Static194.pitch_step[mod] >> 16) + Static194.pitch_base_step[mod];
					}
				}
			}
		}
		/* gating effect */
		@Pc(356) int off_step;
		if (this.gating_release_env != null) {
			this.gating_release_env.reset();
			this.gating_attack_env.reset();
			counter = 0;
			@Pc(341) boolean muted = true;
			for (mod = 0; mod < arg0; mod++) {
				mod_amp = this.gating_release_env.step(arg0);
				off_step = this.gating_attack_env.step(arg0);
				if (muted) {
					pitch_change = this.gating_release_env.start + ((this.gating_release_env.end - this.gating_release_env.start) * mod_amp >> 8);
				} else {
					pitch_change = this.gating_release_env.start + ((this.gating_release_env.end - this.gating_release_env.start) * off_step >> 8);
				}
				counter += 256;
				if (counter >= pitch_change) {
					counter = 0;
					muted = !muted;
				}
				if (muted) {
					Static194.output[mod] = 0;
				}
			}
		}
		/* delay effect */
		if (this.delay_time > 0 && this.delay_feedback > 0) {
			counter = (int) ((double) this.delay_time * local16);
			for (pitch_change = counter; pitch_change < arg0; pitch_change++) {
				Static194.output[pitch_change] += Static194.output[pitch_change - counter] * this.delay_feedback / 100;
			}
		}
		/* filter */
		if (this.filter.num_pairs[0] > 0 || this.filter.num_pairs[1] > 0) {
			this.filter_env.reset();
			counter = this.filter_env.step(arg0 + 1);
			pitch_change = this.filter.method3251(0, (float) counter / 65536.0F);
			vol_change = this.filter.method3251(1, (float) counter / 65536.0F);
			if (arg0 >= pitch_change + vol_change) {
				mod = 0;
				mod_amp = vol_change;
				if (vol_change > arg0 - pitch_change) {
					mod_amp = arg0 - pitch_change;
				}
				@Pc(523) int local523;
				while (mod < mod_amp) {
					off_step = (int) ((long) Static194.output[mod + pitch_change] * (long) Static174.inv_unity >> 16);
					for (local523 = 0; local523 < pitch_change; local523++) {
						off_step += (int) ((long) Static194.output[mod + pitch_change - local523 - 1] * (long) Static174.coef[0][local523] >> 16);
					}
					for (local523 = 0; local523 < mod; local523++) {
						off_step -= (int) ((long) Static194.output[mod - local523 - 1] * (long) Static174.coef[1][local523] >> 16);
					}
					Static194.output[mod] = off_step;
					counter = this.filter_env.step(arg0 + 1);
					mod++;
				}
				mod_amp = 128;
				while (true) {
					if (mod_amp > arg0 - pitch_change) {
						mod_amp = arg0 - pitch_change;
					}
					while (mod < mod_amp) {
						off_step = (int) ((long) Static194.output[mod + pitch_change] * (long) Static174.inv_unity >> 16);
						for (local523 = 0; local523 < pitch_change; local523++) {
							off_step += (int) ((long) Static194.output[mod + pitch_change - local523 - 1] * (long) Static174.coef[0][local523] >> 16);
						}
						for (local523 = 0; local523 < vol_change; local523++) {
							off_step -= (int) ((long) Static194.output[mod - local523 - 1] * (long) Static174.coef[1][local523] >> 16);
						}
						Static194.output[mod] = off_step;
						counter = this.filter_env.step(arg0 + 1);
						mod++;
					}
					if (mod >= arg0 - pitch_change) {
						while (mod < arg0) {
							off_step = 0;
							for (local523 = mod + pitch_change - arg0; local523 < pitch_change; local523++) {
								off_step += (int) ((long) Static194.output[mod + pitch_change - local523 - 1] * (long) Static174.coef[0][local523] >> 16);
							}
							for (local523 = 0; local523 < vol_change; local523++) {
								off_step -= (int) ((long) Static194.output[mod - local523 - 1] * (long) Static174.coef[1][local523] >> 16);
							}
							Static194.output[mod] = off_step;
							this.filter_env.step(arg0 + 1);
							mod++;
						}
						break;
					}
					pitch_change = this.filter.method3251(0, (float) counter / 65536.0F);
					vol_change = this.filter.method3251(1, (float) counter / 65536.0F);
					mod_amp += 128;
				}
			}
		}
		for (counter = 0; counter < arg0; counter++) {
			if (Static194.output[counter] < -32768) {
				Static194.output[counter] = -32768;
			}
			if (Static194.output[counter] > 32767) {
				Static194.output[counter] = 32767;
			}
		}
		return Static194.output;
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(Lclient!wa;)V")
	public final void decode(@OriginalArg(0) Packet packet) {
		this.pitch_env = new SynthEnvelope();
		this.pitch_env.decode(packet);
		this.vol_env = new SynthEnvelope();
		this.vol_env.decode(packet);
		@Pc(21) int option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.pitch_mod_env = new SynthEnvelope();
			this.pitch_mod_env.decode(packet);
			this.pitch_mod_amp_env = new SynthEnvelope();
			this.pitch_mod_amp_env.decode(packet);
		}
		option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.vol_mod_env = new SynthEnvelope();
			this.vol_mod_env.decode(packet);
			this.vol_mod_amp_env = new SynthEnvelope();
			this.vol_mod_amp_env.decode(packet);
		}
		option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.gating_release_env = new SynthEnvelope();
			this.gating_release_env.decode(packet);
			this.gating_attack_env = new SynthEnvelope();
			this.gating_attack_env.decode(packet);
		}
		for (@Pc(109) int j = 0; j < 10; j++) {
			@Pc(116) int jj = packet.gSmart1or2();
			if (jj == 0) {
				break;
			}
			this.oscill_vol[j] = jj;
			this.anIntArray404[j] = packet.gSmart1or2s();
			this.oscill_delay[j] = packet.gSmart1or2();
		}
		this.delay_time = packet.gSmart1or2();
		this.delay_feedback = packet.gSmart1or2();
		this.anInt4546 = packet.g2();
		this.begin = packet.g2();
		this.filter = new SynthFilter();
		this.filter_env = new SynthEnvelope();
		this.filter.decode(packet, this.filter_env);
	}
}
