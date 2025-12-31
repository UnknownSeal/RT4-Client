package com.jagex.sound.synthesis;

import java.util.Random;

import com.jagex.core.io.Packet;
import com.jagex.core.utils.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!pj")
public final class SynthInstrument {

	@OriginalMember(owner = "runetek4.client!pj", name = "o", descriptor = "[I")
	public static final int[] samples = new int[220500];
	@OriginalMember(owner = "runetek4.client!pj", name = "p", descriptor = "[I")
	public static final int[] oscillatorIntervalRanges = new int[5];
	@OriginalMember(owner = "runetek4.client!pj", name = "q", descriptor = "[I")
	public static final int[] oscillatorMinIntervals = new int[5];
	@OriginalMember(owner = "runetek4.client!pj", name = "r", descriptor = "[I")
	public static final int[] oscillatorTimes = new int[5];
	@OriginalMember(owner = "runetek4.client!pj", name = "s", descriptor = "[I")
	public static final int[] oscillatorStartSamples = new int[5];
	@OriginalMember(owner = "runetek4.client!pj", name = "t", descriptor = "[I")
	public static final int[] scaledOscillatorAmplitudes = new int[5];
	@OriginalMember(owner = "runetek4.client!pj", name = "k", descriptor = "[I")
	private static final int[] NOISE = new int[32768];

	@OriginalMember(owner = "runetek4.client!pj", name = "h", descriptor = "[I")
	private static final int[] SINE;

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "Lclient!ff;")
	private SynthEnvelope amplitudeModulationAmplitudeEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "b", descriptor = "Lclient!ff;")
	private SynthEnvelope gateClosedPhaseEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "d", descriptor = "Lclient!ff;")
	private SynthEnvelope amplitudeEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "e", descriptor = "Lclient!ff;")
	private SynthEnvelope gateOpenPhaseEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "f", descriptor = "Lclient!ff;")
	private SynthEnvelope phaseEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "i", descriptor = "Lclient!ff;")
	private SynthEnvelope filterEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "l", descriptor = "Lclient!ff;")
	private SynthEnvelope amplitudeModulationEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "w", descriptor = "Lclient!nl;")
	private SynthFilter filter;

	@OriginalMember(owner = "runetek4.client!pj", name = "x", descriptor = "Lclient!ff;")
	private SynthEnvelope phaseModulationAmplitudeEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "y", descriptor = "Lclient!ff;")
	private SynthEnvelope phaseModulationEnvelope;

	@OriginalMember(owner = "runetek4.client!pj", name = "c", descriptor = "I")
	public int length = 500;

	@OriginalMember(owner = "runetek4.client!pj", name = "g", descriptor = "I")
	private int reverbDelay = 0;

	@OriginalMember(owner = "runetek4.client!pj", name = "j", descriptor = "I")
	public int start = 0;

	@OriginalMember(owner = "runetek4.client!pj", name = "m", descriptor = "[I")
	private final int[] harmonicVolume = new int[] { 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "runetek4.client!pj", name = "n", descriptor = "[I")
	private final int[] harmonicDelay = new int[] { 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "runetek4.client!pj", name = "u", descriptor = "I")
	private int reverbVolume = 100;

	@OriginalMember(owner = "runetek4.client!pj", name = "v", descriptor = "[I")
	private final int[] harmonicSemitone = new int[] { 0, 0, 0, 0, 0 };

	static {
		@Pc(7) Random random = new Random(0L);
		@Pc(9) int i;
		for (i = 0; i < 32768; i++) {
			NOISE[i] = (random.nextInt() & 0x2) - 1;
		}
		SINE = new int[32768];
		for (i = 0; i < 32768; i++) {
			SINE[i] = (int) (Math.sin((double) i / 5215.1903D) * 16384.0D);
		}
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(III)I")
	private int evaluateWave(@OriginalArg(0) int phase, @OriginalArg(1) int amplitude, @OriginalArg(2) int table) {
		if (table == 1) {
			return (phase & 0x7FFF) < 16384 ? amplitude : -amplitude;
		} else if (table == 2) {
			return SINE[phase & 0x7FFF] * amplitude >> 14;
		} else if (table == 3) {
			return ((phase & 0x7FFF) * amplitude >> 14) - amplitude;
		} else if (table == 4) {
			return NOISE[phase / 2607 & 0x7FFF] * amplitude;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(II)[I")
	public final int[] getSamples(@OriginalArg(0) int arg0, @OriginalArg(1) int dt) {
		ArrayUtils.clear(samples, 0, arg0);
		if (dt < 10) {
			return samples;
		}
		@Pc(16) double local16 = (double) arg0 / ((double) dt + 0.0D);
		this.phaseEnvelope.reset();
		this.amplitudeEnvelope.reset();
		@Pc(24) int pitch_mod_step = 0;
		@Pc(26) int pitch_mod_base_step = 0;
		@Pc(28) int pitch_mod_phase = 0;
		if (this.phaseModulationEnvelope != null) {
			this.phaseModulationEnvelope.reset();
			this.phaseModulationAmplitudeEnvelope.reset();
			pitch_mod_step = (int) ((double) (this.phaseModulationEnvelope.maxInterval - this.phaseModulationEnvelope.minInterval) * 32.768D / local16);
			pitch_mod_base_step = (int) ((double) this.phaseModulationEnvelope.minInterval * 32.768D / local16);
		}
		@Pc(63) int vol_mod_step = 0;
		@Pc(65) int vol_mod_base_step = 0;
		@Pc(67) int vol_mod_phase = 0;
		if (this.amplitudeModulationEnvelope != null) {
			this.amplitudeModulationEnvelope.reset();
			this.amplitudeModulationAmplitudeEnvelope.reset();
			vol_mod_step = (int) ((double) (this.amplitudeModulationEnvelope.maxInterval - this.amplitudeModulationEnvelope.minInterval) * 32.768D / local16);
			vol_mod_base_step = (int) ((double) this.amplitudeModulationEnvelope.minInterval * 32.768D / local16);
		}
		@Pc(102) int counter;
		for (counter = 0; counter < 5; counter++) {
			if (this.harmonicVolume[counter] != 0) {
				oscillatorTimes[counter] = 0;
				oscillatorStartSamples[counter] = (int) ((double) this.harmonicDelay[counter] * local16);
				scaledOscillatorAmplitudes[counter] = (this.harmonicVolume[counter] << 14) / 100;
				oscillatorIntervalRanges[counter] = (int) ((double) (this.phaseEnvelope.maxInterval - this.phaseEnvelope.minInterval) * 32.768D * Math.pow(1.0057929410678534D, (double) this.harmonicSemitone[counter]) / local16);
				oscillatorMinIntervals[counter] = (int) ((double) this.phaseEnvelope.minInterval * 32.768D / local16);
			}
		}
		@Pc(185) int pitch_change;
		@Pc(190) int vol_change;
		@Pc(198) int mod;
		@Pc(203) int mod_amp;
		for (counter = 0; counter < arg0; counter++) {
			pitch_change = this.phaseEnvelope.nextLevel(arg0);
			vol_change = this.amplitudeEnvelope.nextLevel(arg0);
			if (this.phaseModulationEnvelope != null) {
				mod = this.phaseModulationEnvelope.nextLevel(arg0);
				mod_amp = this.phaseModulationAmplitudeEnvelope.nextLevel(arg0);
				pitch_change += this.evaluateWave(pitch_mod_phase, mod_amp, this.phaseModulationEnvelope.wavetable) >> 1;
				pitch_mod_phase += (mod * pitch_mod_step >> 16) + pitch_mod_base_step;
			}
			if (this.amplitudeModulationEnvelope != null) {
				mod = this.amplitudeModulationEnvelope.nextLevel(arg0);
				mod_amp = this.amplitudeModulationAmplitudeEnvelope.nextLevel(arg0);
				vol_change = vol_change * ((this.evaluateWave(vol_mod_phase, mod_amp, this.amplitudeModulationEnvelope.wavetable) >> 1) + 32768) >> 15;
				vol_mod_phase += (mod * vol_mod_step >> 16) + vol_mod_base_step;
			}
			for (mod = 0; mod < 5; mod++) {
				if (this.harmonicVolume[mod] != 0) {
					mod_amp = counter + oscillatorStartSamples[mod];
					if (mod_amp < arg0) {
						samples[mod_amp] += this.evaluateWave(oscillatorTimes[mod], vol_change * scaledOscillatorAmplitudes[mod] >> 15, this.phaseEnvelope.wavetable);
						oscillatorTimes[mod] += (pitch_change * oscillatorIntervalRanges[mod] >> 16) + oscillatorMinIntervals[mod];
					}
				}
			}
		}
		/* gating effect */
		@Pc(356) int off_step;
		if (this.gateClosedPhaseEnvelope != null) {
			this.gateClosedPhaseEnvelope.reset();
			this.gateOpenPhaseEnvelope.reset();
			counter = 0;
			@Pc(341) boolean muted = true;
			for (mod = 0; mod < arg0; mod++) {
				mod_amp = this.gateClosedPhaseEnvelope.nextLevel(arg0);
				off_step = this.gateOpenPhaseEnvelope.nextLevel(arg0);
				if (muted) {
					pitch_change = this.gateClosedPhaseEnvelope.minInterval + ((this.gateClosedPhaseEnvelope.maxInterval - this.gateClosedPhaseEnvelope.minInterval) * mod_amp >> 8);
				} else {
					pitch_change = this.gateClosedPhaseEnvelope.minInterval + ((this.gateClosedPhaseEnvelope.maxInterval - this.gateClosedPhaseEnvelope.minInterval) * off_step >> 8);
				}
				counter += 256;
				if (counter >= pitch_change) {
					counter = 0;
					muted = !muted;
				}
				if (muted) {
					samples[mod] = 0;
				}
			}
		}
		/* delay effect */
		if (this.reverbDelay > 0 && this.reverbVolume > 0) {
			counter = (int) ((double) this.reverbDelay * local16);
			for (pitch_change = counter; pitch_change < arg0; pitch_change++) {
				samples[pitch_change] += samples[pitch_change - counter] * this.reverbVolume / 100;
			}
		}
		/* filter */
		if (this.filter.pairs[0] > 0 || this.filter.pairs[1] > 0) {
			this.filterEnvelope.reset();
			counter = this.filterEnvelope.nextLevel(arg0 + 1);
			pitch_change = this.filter.compute(0, (float) counter / 65536.0F);
			vol_change = this.filter.compute(1, (float) counter / 65536.0F);
			if (arg0 >= pitch_change + vol_change) {
				mod = 0;
				mod_amp = vol_change;
				if (vol_change > arg0 - pitch_change) {
					mod_amp = arg0 - pitch_change;
				}
				@Pc(523) int local523;
				while (mod < mod_amp) {
					off_step = (int) ((long) samples[mod + pitch_change] * (long) SynthFilter.inverseA0 >> 16);
					for (local523 = 0; local523 < pitch_change; local523++) {
						off_step += (int) ((long) samples[mod + pitch_change - local523 - 1] * (long) SynthFilter.coefficients[0][local523] >> 16);
					}
					for (local523 = 0; local523 < mod; local523++) {
						off_step -= (int) ((long) samples[mod - local523 - 1] * (long) SynthFilter.coefficients[1][local523] >> 16);
					}
					samples[mod] = off_step;
					counter = this.filterEnvelope.nextLevel(arg0 + 1);
					mod++;
				}
				mod_amp = 128;
				while (true) {
					if (mod_amp > arg0 - pitch_change) {
						mod_amp = arg0 - pitch_change;
					}
					while (mod < mod_amp) {
						off_step = (int) ((long) samples[mod + pitch_change] * (long) SynthFilter.inverseA0 >> 16);
						for (local523 = 0; local523 < pitch_change; local523++) {
							off_step += (int) ((long) samples[mod + pitch_change - local523 - 1] * (long) SynthFilter.coefficients[0][local523] >> 16);
						}
						for (local523 = 0; local523 < vol_change; local523++) {
							off_step -= (int) ((long) samples[mod - local523 - 1] * (long) SynthFilter.coefficients[1][local523] >> 16);
						}
						samples[mod] = off_step;
						counter = this.filterEnvelope.nextLevel(arg0 + 1);
						mod++;
					}
					if (mod >= arg0 - pitch_change) {
						while (mod < arg0) {
							off_step = 0;
							for (local523 = mod + pitch_change - arg0; local523 < pitch_change; local523++) {
								off_step += (int) ((long) samples[mod + pitch_change - local523 - 1] * (long) SynthFilter.coefficients[0][local523] >> 16);
							}
							for (local523 = 0; local523 < vol_change; local523++) {
								off_step -= (int) ((long) samples[mod - local523 - 1] * (long) SynthFilter.coefficients[1][local523] >> 16);
							}
							samples[mod] = off_step;
							this.filterEnvelope.nextLevel(arg0 + 1);
							mod++;
						}
						break;
					}
					pitch_change = this.filter.compute(0, (float) counter / 65536.0F);
					vol_change = this.filter.compute(1, (float) counter / 65536.0F);
					mod_amp += 128;
				}
			}
		}
		for (counter = 0; counter < arg0; counter++) {
			if (samples[counter] < -32768) {
				samples[counter] = -32768;
			}
			if (samples[counter] > 32767) {
				samples[counter] = 32767;
			}
		}
		return samples;
	}

	@OriginalMember(owner = "runetek4.client!pj", name = "a", descriptor = "(Lclient!wa;)V")
	public final void decode(@OriginalArg(0) Packet packet) {
		this.phaseEnvelope = new SynthEnvelope();
		this.phaseEnvelope.decode(packet);
		this.amplitudeEnvelope = new SynthEnvelope();
		this.amplitudeEnvelope.decode(packet);
		@Pc(21) int option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.phaseModulationEnvelope = new SynthEnvelope();
			this.phaseModulationEnvelope.decode(packet);
			this.phaseModulationAmplitudeEnvelope = new SynthEnvelope();
			this.phaseModulationAmplitudeEnvelope.decode(packet);
		}
		option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.amplitudeModulationEnvelope = new SynthEnvelope();
			this.amplitudeModulationEnvelope.decode(packet);
			this.amplitudeModulationAmplitudeEnvelope = new SynthEnvelope();
			this.amplitudeModulationAmplitudeEnvelope.decode(packet);
		}
		option = packet.g1();
		if (option != 0) {
			packet.offset--;
			this.gateClosedPhaseEnvelope = new SynthEnvelope();
			this.gateClosedPhaseEnvelope.decode(packet);
			this.gateOpenPhaseEnvelope = new SynthEnvelope();
			this.gateOpenPhaseEnvelope.decode(packet);
		}
		for (@Pc(109) int j = 0; j < 10; j++) {
			@Pc(116) int jj = packet.gSmart1or2();
			if (jj == 0) {
				break;
			}
			this.harmonicVolume[j] = jj;
			this.harmonicSemitone[j] = packet.gSmart1or2s();
			this.harmonicDelay[j] = packet.gSmart1or2();
		}
		this.reverbDelay = packet.gSmart1or2();
		this.reverbVolume = packet.gSmart1or2();
		this.length = packet.g2();
		this.start = packet.g2();
		this.filter = new SynthFilter();
		this.filterEnvelope = new SynthEnvelope();
		this.filter.decode(packet, this.filterEnvelope);
	}
}
