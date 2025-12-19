package com.jagex.runetek4.audio.midi;

import com.jagex.runetek4.audio.AudioChannel;
import com.jagex.runetek4.audio.core.PcmStream;
import com.jagex.runetek4.audio.core.SoundBank;
import com.jagex.runetek4.audio.core.SoundPcmStream;
import com.jagex.runetek4.audio.pcm.PcmSound;

import com.jagex.runetek4.audio.streaming.Song;
import com.jagex.runetek4.core.buffer.ByteArrayNode;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.data.js5.Js5;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!va")
public final class MidiPcmStream extends PcmStream {

	@OriginalMember(owner = "client!va", name = "Kb", descriptor = "Z")
	private boolean aBoolean293;

	@OriginalMember(owner = "client!va", name = "Lb", descriptor = "J")
	private long aLong188;

	@OriginalMember(owner = "client!va", name = "Mb", descriptor = "I")
	private int anInt5674;

	@OriginalMember(owner = "client!va", name = "Nb", descriptor = "I")
	private int anInt5675;

	@OriginalMember(owner = "client!va", name = "Ob", descriptor = "J")
	private long aLong189;

	@OriginalMember(owner = "client!va", name = "Qb", descriptor = "Lclient!rf;")
	private Song aClass3_Sub29_2;

	@OriginalMember(owner = "client!va", name = "Rb", descriptor = "I")
	private int anInt5676;

	@OriginalMember(owner = "client!va", name = "Sb", descriptor = "Z")
	private boolean aBoolean294;

	@OriginalMember(owner = "client!va", name = "z", descriptor = "[I")
	private final int[] channelPan = new int[16];

	@OriginalMember(owner = "client!va", name = "D", descriptor = "[I")
	private final int[] anIntArray499 = new int[16];

	@OriginalMember(owner = "client!va", name = "F", descriptor = "[I")
	private final int[] channelModulation = new int[16];

	@OriginalMember(owner = "client!va", name = "B", descriptor = "[I")
	private final int[] channelPitch = new int[16];

	@OriginalMember(owner = "client!va", name = "ab", descriptor = "I")
	private final int anInt5646 = 1000000;

	@OriginalMember(owner = "client!va", name = "cb", descriptor = "[[Lclient!mf;")
	private final MidiNote[][] aClass3_Sub25ArrayArray2 = new MidiNote[16][128];

	@OriginalMember(owner = "client!va", name = "kb", descriptor = "[I")
	private final int[] channelProgram = new int[16];

	@OriginalMember(owner = "client!va", name = "C", descriptor = "[I")
	private final int[] channelParameter = new int[16];

	@OriginalMember(owner = "client!va", name = "U", descriptor = "[I")
	public final int[] anIntArray503 = new int[16];

	@OriginalMember(owner = "client!va", name = "x", descriptor = "[I")
	private final int[] channelVolume = new int[16];

	@OriginalMember(owner = "client!va", name = "ib", descriptor = "[I")
	private final int[] channelExpression = new int[16];

	@OriginalMember(owner = "client!va", name = "tb", descriptor = "[I")
	public final int[] channelFlags = new int[16];

	@OriginalMember(owner = "client!va", name = "mb", descriptor = "[I")
	private final int[] anIntArray507 = new int[16];

	@OriginalMember(owner = "client!va", name = "Cb", descriptor = "[I")
	private final int[] anIntArray510 = new int[16];

	@OriginalMember(owner = "client!va", name = "N", descriptor = "[I")
	private final int[] anIntArray502 = new int[16];

	@OriginalMember(owner = "client!va", name = "Bb", descriptor = "[I")
	public final int[] anIntArray509 = new int[16];

	@OriginalMember(owner = "client!va", name = "bb", descriptor = "[[Lclient!mf;")
	private final MidiNote[][] notes = new MidiNote[16][128];

	@OriginalMember(owner = "client!va", name = "Eb", descriptor = "I")
	private int anInt5668 = 256;

	@OriginalMember(owner = "client!va", name = "H", descriptor = "[I")
	private final int[] anIntArray501 = new int[16];

	@OriginalMember(owner = "client!va", name = "Z", descriptor = "[I")
	private final int[] channelPortamento = new int[16];

	@OriginalMember(owner = "client!va", name = "K", descriptor = "Lclient!ki;")
	private final MidiDecoder aClass84_1 = new MidiDecoder();

	@OriginalMember(owner = "client!va", name = "Pb", descriptor = "Lclient!te;")
	private final MidiNoteStream noteStream = new MidiNoteStream(this);

	@OriginalMember(owner = "client!va", name = "P", descriptor = "Lclient!sc;")
	private final HashTable instruments = new HashTable(128);

	@OriginalMember(owner = "client!va", name = "<init>", descriptor = "()V")
	public MidiPcmStream() {
		this.method4424();
		this.reset(true);
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(Lclient!rf;ILclient!ve;Lclient!le;I)Z")
	public synchronized boolean isSongReady(@OriginalArg(0) Song arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(3) SoundBank arg2) {
		arg0.createPrograms();
		@Pc(5) boolean local5 = true;
		@Pc(20) int[] local20 = new int[] { 22050 };
		for (@Pc(34) ByteArrayNode local34 = (ByteArrayNode) arg0.programs.head(); local34 != null; local34 = (ByteArrayNode) arg0.programs.next()) {
			@Pc(40) int local40 = (int) local34.nodeId;
			@Pc(48) MidiInstrument local48 = (MidiInstrument) this.instruments.get(local40);
			if (local48 == null) {
				local48 = MidiInstrument.method2320(arg1, local40);
				if (local48 == null) {
					local5 = false;
					continue;
				}
				this.instruments.put(local48, local40);
			}
			if (!local48.isReady(arg2, local20, local34.value)) {
				local5 = false;
			}
		}
		if (local5) {
			arg0.releasePrograms();
		}
		return local5;
	}

	@OriginalMember(owner = "client!va", name = "d", descriptor = "(B)V")
	public synchronized void releaseInstruments() {
		for (@Pc(15) MidiInstrument local15 = (MidiInstrument) this.instruments.head(); local15 != null; local15 = (MidiInstrument) this.instruments.next()) {
			local15.release();
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(III)V")
	private void method4413() {
		this.anIntArray499[9] = 128;
		this.anIntArray502[9] = 128;
		this.method4425(128, 9);
	}

	@OriginalMember(owner = "runetek4.client!va", name = "d", descriptor = "(I)Z")
	public synchronized boolean isValid() {
		return this.aClass84_1.isValid();
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(ZLclient!rf;ZB)V")
	private synchronized void method4416(@OriginalArg(0) boolean arg0, @OriginalArg(1) Song arg1, @OriginalArg(2) boolean arg2) {
		this.method4448(arg2);
		this.aClass84_1.init(arg1.midiBytes);
		this.aBoolean293 = arg0;
		this.aLong189 = 0L;
		@Pc(24) int local24 = this.aClass84_1.getTrackCount();
		for (@Pc(26) int local26 = 0; local26 < local24; local26++) {
			this.aClass84_1.loadTrackPosition(local26);
			this.aClass84_1.addDeltaTime(local26);
			this.aClass84_1.saveTrackPosition(local26);
		}
		this.anInt5675 = this.aClass84_1.getNextTrack();
		this.anInt5674 = this.aClass84_1.times[this.anInt5675];
		this.aLong188 = this.aClass84_1.getTimeMillis(this.anInt5674);
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(III)V")
	private void method4417(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.channelPitch[arg0] = arg1;
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "()I")
	@Override
	public synchronized int method4404() {
		return 0;
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(IZI)V")
	private void method4419(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(BII)V")
	public synchronized void init() {
		this.method4413();
	}

	@OriginalMember(owner = "client!va", name = "c", descriptor = "(II)V")
	private void resetAllControllers(@OriginalArg(1) int channel) {
		if (channel < 0) {
			for (@Pc(10) int i = 0; i < 16; i++) {
				this.resetAllControllers(i);
			}
			return;
		}
		this.channelVolume[channel] = 12800;
		this.channelPan[channel] = 8192;
		this.channelExpression[channel] = 16383;
		this.channelPitch[channel] = 8192;
		this.channelModulation[channel] = 0;
		this.channelPortamento[channel] = 8192;
		this.method4443(channel);
		this.method4438(channel);
		this.channelFlags[channel] = 0;
		this.channelParameter[channel] = 32767;
		this.anIntArray501[channel] = 256;
		this.anIntArray509[channel] = 0;
		this.method4423(channel, 8192);
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(BI)V")
	private void method4422(@OriginalArg(1) int arg0) {
		for (@Pc(20) MidiNote local20 = (MidiNote) this.noteStream.notes.head(); local20 != null; local20 = (MidiNote) this.noteStream.notes.next()) {
			if (arg0 < 0 || local20.channel == arg0) {
				if (local20.stream != null) {
					local20.stream.method384(AudioChannel.sampleRate / 100);
					if (local20.stream.method412()) {
						this.noteStream.mixer.addSubStream(local20.stream);
					}
					local20.method2957();
				}
				if (local20.anInt3767 < 0) {
					this.notes[local20.channel][local20.midiKey] = null;
				}
				local20.unlink();
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(BII)V")
	private void method4423(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		this.anIntArray510[arg0] = arg1;
		this.anIntArray503[arg0] = (int) (Math.pow(2.0D, (double) arg1 * 5.4931640625E-4D) * 2097152.0D + 0.5D);
	}

	@OriginalMember(owner = "client!va", name = "c", descriptor = "(III)V")
	private synchronized void method4424() {
		for (@Pc(5) int local5 = 0; local5 < 16; local5++) {
			this.anIntArray507[local5] = 256;
		}
	}

	@OriginalMember(owner = "client!va", name = "d", descriptor = "(III)V")
	private void method4425(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (this.channelProgram[arg1] != arg0) {
			this.channelProgram[arg1] = arg0;
			for (@Pc(21) int local21 = 0; local21 < 128; local21++) {
				this.aClass3_Sub25ArrayArray2[arg1][local21] = null;
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "e", descriptor = "(I)V")
	public synchronized void clearInstruments() {
		for (@Pc(7) MidiInstrument local7 = (MidiInstrument) this.instruments.head(); local7 != null; local7 = (MidiInstrument) this.instruments.next()) {
			local7.unlink();
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(IIII)V")
	private void noteOn(@OriginalArg(0) int velocity, @OriginalArg(1) int channel, @OriginalArg(3) int key) {
		this.noteOff(key, 64, channel);
		if ((this.channelFlags[channel] & 0x2) != 0) {
			for (@Pc(28) MidiNote note = (MidiNote) this.noteStream.notes.tail(); note != null; note = (MidiNote) this.noteStream.notes.prev()) {
				if (channel == note.channel && note.anInt3767 < 0) {
					this.notes[channel][note.midiKey] = null;
					this.notes[channel][key] = note;
					@Pc(72) int local72 = note.anInt3769 + (note.anInt3764 * note.anInt3781 >> 12);
					note.anInt3764 = 4096;
					note.anInt3769 += key - note.midiKey << 8;
					note.anInt3781 = local72 - note.anInt3769;
					note.midiKey = key;
					return;
				}
			}
		}
		@Pc(118) MidiInstrument instrument = (MidiInstrument) this.instruments.get(this.channelProgram[channel]);
		if (instrument == null) {
			return;
		}
		@Pc(126) PcmSound sound = instrument.sounds[key];
		if (sound == null) {
			return;
		}
		@Pc(133) MidiNote note = new MidiNote();
		note.sound = sound;
		note.instrument = instrument;
		note.channel = channel;
		note.aMidiClass162_1 = instrument.aMidiClass162Array1[key];
		note.anInt3776 = instrument.aByteArray44[key];
		note.midiKey = key;
		note.anInt3772 = instrument.aByteArray45[key] * velocity * velocity * instrument.anInt3078 + 1024 >> 11;
		note.pan = instrument.keyPan[key] & 0xFF;
		note.anInt3769 = (key << 8) - (instrument.aShortArray36[key] & 0x7FFF);
		note.anInt3767 = -1;
		note.anInt3770 = 0;
		note.anInt3777 = 0;
		note.anInt3782 = 0;
		note.anInt3763 = 0;
		if (this.anIntArray509[channel] == 0) {
			note.stream = SoundPcmStream.create(sound, this.method4439(note), this.method4449(note), this.method4437(note));
		} else {
			note.stream = SoundPcmStream.create(sound, this.method4439(note), 0, this.method4437(note));
			this.method4442(note, instrument.aShortArray36[key] < 0);
		}
		if (instrument.aShortArray36[key] < 0) {
			note.stream.setLoops(-1);
		}
		if (note.anInt3776 >= 0) {
			@Pc(289) MidiNote local289 = this.aClass3_Sub25ArrayArray2[channel][note.anInt3776];
			if (local289 != null && local289.anInt3767 < 0) {
				this.notes[channel][local289.midiKey] = null;
				local289.anInt3767 = 0;
			}
			this.aClass3_Sub25ArrayArray2[channel][note.anInt3776] = note;
		}
		this.noteStream.notes.push(note);
		this.notes[channel][key] = note;
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(BI)V")
	private void processMessage(@OriginalArg(1) int message) {
		@Pc(9) int type = message & 0xF0;
		@Pc(20) int channel;
		@Pc(32) int controller;
		@Pc(26) int value;
		if (type == 128) {
			channel = message & 0xF;
			value = message >> 16 & 0x7F;
			controller = message >> 8 & 0x7F;
			this.noteOff(controller, value, channel);
		} else if (type == 144) {
			controller = message >> 8 & 0x7F;
			channel = message & 0xF;
			value = message >> 16 & 0x7F;
			if (value > 0) {
				this.noteOn(value, channel, controller);
			} else {
				this.noteOff(controller, 64, channel);
			}
		} else if (type == 160) {
			channel = message & 0xF;
			controller = message >> 8 & 0x7F;
			value = message >> 16 & 0x7F;
			this.setKeyPressure(value, controller, channel);
		} else if (type == 176) {
			controller = message >> 8 & 0x7F;
			channel = message & 0xF;
			value = message >> 16 & 0x7F;
			if (controller == 0) {
				this.anIntArray502[channel] = (this.anIntArray502[channel] & 0xFFE03FFF) + (value << 14);
			}
			if (controller == 32) {
				this.anIntArray502[channel] = (this.anIntArray502[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 1) {
				this.channelModulation[channel] = (this.channelModulation[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 33) {
				this.channelModulation[channel] = (this.channelModulation[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 5) {
				this.channelPortamento[channel] = (this.channelPortamento[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 37) {
				this.channelPortamento[channel] = (this.channelPortamento[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 7) {
				this.channelVolume[channel] = (this.channelVolume[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 39) {
				this.channelVolume[channel] = (this.channelVolume[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 10) {
				this.channelPan[channel] = (this.channelPan[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 42) {
				this.channelPan[channel] = (this.channelPan[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 11) {
				this.channelExpression[channel] = (this.channelExpression[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 43) {
				this.channelExpression[channel] = (this.channelExpression[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 64) {
				if (value >= 64) {
					this.channelFlags[channel] |= 0x1;
				} else {
					this.channelFlags[channel] &= 0xFFFFFFFE;
				}
			}
			if (controller == 65) {
				if (value < 64) {
					this.method4443(channel);
					this.channelFlags[channel] &= 0xFFFFFFFD;
				} else {
					this.channelFlags[channel] |= 0x2;
				}
			}
			if (controller == 99) {
				this.channelParameter[channel] = (this.channelParameter[channel] & 0x7F) + (value << 7);
			}
			if (controller == 98) {
				this.channelParameter[channel] = (this.channelParameter[channel] & 0x3F80) + value;
			}
			if (controller == 101) {
				this.channelParameter[channel] = (this.channelParameter[channel] & 0x7F) + (value << 7) + 16384;
			}
			if (controller == 100) {
				this.channelParameter[channel] = (this.channelParameter[channel] & 0x3F80) + value + 16384;
			}
			if (controller == 120) {
				this.method4422(channel);
			}
			if (controller == 121) {
				this.resetAllControllers(channel);
			}
			if (controller == 123) {
				this.method4430(channel);
			}
			@Pc(522) int local522;
			if (controller == 6) {
				local522 = this.channelParameter[channel];
				if (local522 == 16384) {
					this.anIntArray501[channel] = (this.anIntArray501[channel] & 0xFFFFC07F) + (value << 7);
				}
			}
			if (controller == 38) {
				local522 = this.channelParameter[channel];
				if (local522 == 16384) {
					this.anIntArray501[channel] = (this.anIntArray501[channel] & 0xFFFFFF80) + value;
				}
			}
			if (controller == 16) {
				this.anIntArray509[channel] = (this.anIntArray509[channel] & 0xFFFFC07F) + (value << 7);
			}
			if (controller == 48) {
				this.anIntArray509[channel] = (this.anIntArray509[channel] & 0xFFFFFF80) + value;
			}
			if (controller == 81) {
				if (value >= 64) {
					this.channelFlags[channel] |= 0x4;
				} else {
					this.method4438(channel);
					this.channelFlags[channel] &= 0xFFFFFFFB;
				}
			}
			if (controller == 17) {
				this.method4423(channel, (value << 7) + (this.anIntArray510[channel] & 0xFFFFC07F));
			}
			if (controller == 49) {
				this.method4423(channel, (this.anIntArray510[channel] & 0xFFFFFF80) + value);
			}
		} else if (type == 192) {
			controller = message >> 8 & 0x7F;
			channel = message & 0xF;
			this.method4425(this.anIntArray502[channel] + controller, channel);
		} else if (type == 208) {
			channel = message & 0xF;
			controller = message >> 8 & 0x7F;
			this.method4419(channel, controller);
		} else if (type == 224) {
			channel = message & 0xF;
			controller = (message >> 9 & 0x3F80) + ((message & 0x7FBE) >> 8);
			this.method4417(channel, controller);
		} else {
			type = message & 0xFF;
			if (type == 255) {
				this.reset(true);
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "d", descriptor = "(II)V")
	private void method4430(@OriginalArg(1) int arg0) {
		for (@Pc(12) MidiNote local12 = (MidiNote) this.noteStream.notes.head(); local12 != null; local12 = (MidiNote) this.noteStream.notes.next()) {
			if ((arg0 < 0 || arg0 == local12.channel) && local12.anInt3767 < 0) {
				this.notes[local12.channel][local12.midiKey] = null;
				local12.anInt3767 = 0;
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(ZLclient!rf;I)V")
	public synchronized void method4431(@OriginalArg(0) boolean arg0, @OriginalArg(1) Song arg1) {
		this.method4416(arg0, arg1, true);
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "([III)V")
	@Override
	public synchronized void read(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (this.aClass84_1.isValid()) {
			@Pc(18) int local18 = this.aClass84_1.division * this.anInt5646 / AudioChannel.sampleRate;
			do {
				@Pc(28) long local28 = this.aLong189 + (long) arg2 * (long) local18;
				if (this.aLong188 - local28 >= 0L) {
					this.aLong189 = local28;
					break;
				}
				@Pc(59) int local59 = (int) ((this.aLong188 + (long) local18 - this.aLong189 - 1L) / (long) local18);
				this.aLong189 += (long) local18 * (long) local59;
				this.noteStream.read(arg0, arg1, local59);
				arg2 -= local59;
				arg1 += local59;
				this.method4435();
			} while (this.aClass84_1.isValid());
		}
		this.noteStream.read(arg0, arg1, arg2);
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(IILclient!mf;B[I)Z")
	public boolean method4433(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) MidiNote arg2, @OriginalArg(4) int[] arg3) {
		arg2.anInt3771 = AudioChannel.sampleRate / 100;
		if (arg2.anInt3767 >= 0 && (arg2.stream == null || arg2.stream.method411())) {
			arg2.method2957();
			arg2.unlink();
			if (arg2.anInt3776 > 0 && arg2 == this.aClass3_Sub25ArrayArray2[arg2.channel][arg2.anInt3776]) {
				this.aClass3_Sub25ArrayArray2[arg2.channel][arg2.anInt3776] = null;
			}
			return true;
		}
		@Pc(54) int local54 = arg2.anInt3764;
		if (local54 > 0) {
			local54 -= (int) (Math.pow(2.0D, (double) this.channelPortamento[arg2.channel] * 4.921259842519685E-4D) * 16.0D + 0.5D);
			if (local54 < 0) {
				local54 = 0;
			}
			arg2.anInt3764 = local54;
		}
		arg2.stream.method410(this.method4439(arg2));
		@Pc(103) Midi_Class162 local103 = arg2.aMidiClass162_1;
		arg2.anInt3768 += local103.anInt5814;
		arg2.anInt3774++;
		@Pc(134) double local134 = (double) ((arg2.midiKey - 60 << 8) + (arg2.anInt3764 * arg2.anInt3781 >> 12)) * 5.086263020833333E-6D;
		@Pc(136) boolean local136 = false;
		if (local103.anInt5815 > 0) {
			if (local103.anInt5807 > 0) {
				arg2.anInt3782 += (int) (Math.pow(2.0D, local134 * (double) local103.anInt5807) * 128.0D + 0.5D);
			} else {
				arg2.anInt3782 += 128;
			}
			if (arg2.anInt3782 * local103.anInt5815 >= 819200) {
				local136 = true;
			}
		}
		if (local103.aByteArray80 != null) {
			if (local103.anInt5810 <= 0) {
				arg2.anInt3770 += 128;
			} else {
				arg2.anInt3770 += (int) (Math.pow(2.0D, (double) local103.anInt5810 * local134) * 128.0D + 0.5D);
			}
			while (arg2.anInt3763 < local103.aByteArray80.length - 2 && arg2.anInt3770 > (local103.aByteArray80[arg2.anInt3763 + 2] & 0xFF) << 8) {
				arg2.anInt3763 += 2;
			}
			if (local103.aByteArray80.length - 2 == arg2.anInt3763 && local103.aByteArray80[arg2.anInt3763 + 1] == 0) {
				local136 = true;
			}
		}
		if (arg2.anInt3767 >= 0 && local103.aByteArray81 != null && (this.channelFlags[arg2.channel] & 0x1) == 0 && (arg2.anInt3776 < 0 || this.aClass3_Sub25ArrayArray2[arg2.channel][arg2.anInt3776] != arg2)) {
			if (local103.anInt5813 > 0) {
				arg2.anInt3767 += (int) (Math.pow(2.0D, (double) local103.anInt5813 * local134) * 128.0D + 0.5D);
			} else {
				arg2.anInt3767 += 128;
			}
			while (local103.aByteArray81.length - 2 > arg2.anInt3777 && (local103.aByteArray81[arg2.anInt3777 + 2] & 0xFF) << 8 < arg2.anInt3767) {
				arg2.anInt3777 += 2;
			}
			if (arg2.anInt3777 == local103.aByteArray81.length - 2) {
				local136 = true;
			}
		}
		if (!local136) {
			arg2.stream.method417(arg2.anInt3771, this.method4449(arg2), this.method4437(arg2));
			return false;
		}
		arg2.stream.method384(arg2.anInt3771);
		if (arg3 == null) {
			arg2.stream.skip(arg0);
		} else {
			arg2.stream.read(arg3, arg1, arg0);
		}
		if (arg2.stream.method412()) {
			this.noteStream.mixer.addSubStream(arg2.stream);
		}
		arg2.method2957();
		if (arg2.anInt3767 >= 0) {
			arg2.unlink();
			if (arg2.anInt3776 > 0 && this.aClass3_Sub25ArrayArray2[arg2.channel][arg2.anInt3776] == arg2) {
				this.aClass3_Sub25ArrayArray2[arg2.channel][arg2.anInt3776] = null;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "()Lclient!qb;")
	@Override
	public synchronized PcmStream firstSubStream() {
		return this.noteStream;
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(BIII)V")
	private void noteOff(@OriginalArg(1) int key, @OriginalArg(2) int arg1, @OriginalArg(3) int channel) {
		@Pc(12) MidiNote note = this.notes[channel][key];
		if (note == null) {
			return;
		}
		this.notes[channel][key] = null;
		if ((this.channelFlags[channel] & 0x2) == 0) {
			note.anInt3767 = 0;
			return;
		}
		for (@Pc(44) MidiNote other = (MidiNote) this.noteStream.notes.head(); other != null; other = (MidiNote) this.noteStream.notes.next()) {
			if (other.channel == note.channel && other.anInt3767 < 0 && other != note) {
				note.anInt3767 = 0;
				break;
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "f", descriptor = "(I)V")
	private void method4435() {
		@Pc(8) int local8 = this.anInt5675;
		@Pc(11) int local11 = this.anInt5674;
		@Pc(20) long local20 = this.aLong188;
		if (this.aClass3_Sub29_2 != null && local11 == this.anInt5676) {
			this.method4416(this.aBoolean293, this.aClass3_Sub29_2, this.aBoolean294);
			this.method4435();
			return;
		}
		while (this.anInt5674 == local11) {
			while (local11 == this.aClass84_1.times[local8]) {
				this.aClass84_1.loadTrackPosition(local8);
				@Pc(64) int local64 = this.aClass84_1.getNextEvent(local8);
				if (local64 == 1) {
					this.aClass84_1.loadEndOfTrackPosition();
					this.aClass84_1.saveTrackPosition(local8);
					if (this.aClass84_1.hasNextTrack()) {
						if (this.aClass3_Sub29_2 != null) {
							this.method4431(this.aBoolean293, this.aClass3_Sub29_2);
							this.method4435();
							return;
						}
						if (!this.aBoolean293 || local11 == 0) {
							this.reset(true);
							this.aClass84_1.release();
							return;
						}
						this.aClass84_1.setStartMillis(local20);
					}
					break;
				}
				if ((local64 & 0x80) != 0) {
					this.processMessage(local64);
				}
				this.aClass84_1.addDeltaTime(local8);
				this.aClass84_1.saveTrackPosition(local8);
			}
			local8 = this.aClass84_1.getNextTrack();
			local11 = this.aClass84_1.times[local8];
			local20 = this.aClass84_1.getTimeMillis(local11);
		}
		this.anInt5675 = local8;
		this.aLong188 = local20;
		this.anInt5674 = local11;
		if (this.aClass3_Sub29_2 != null && local11 > this.anInt5676) {
			this.anInt5675 = -1;
			this.anInt5674 = this.anInt5676;
			this.aLong188 = this.aClass84_1.getTimeMillis(this.anInt5674);
		}
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(IIII)V")
	private void setKeyPressure(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(ILclient!mf;)I")
	private int method4437(@OriginalArg(1) MidiNote arg0) {
		@Pc(5) int local5 = this.channelPan[arg0.channel];
		return local5 < 8192 ? arg0.pan * local5 + 32 >> 6 : 16384 - ((128 - arg0.pan) * (-local5 + 16384) + 32 >> 6);
	}

	@OriginalMember(owner = "client!va", name = "c", descriptor = "(I)V")
	@Override
	public synchronized void skip(@OriginalArg(0) int arg0) {
		if (this.aClass84_1.isValid()) {
			@Pc(15) int local15 = this.aClass84_1.division * this.anInt5646 / AudioChannel.sampleRate;
			do {
				@Pc(25) long local25 = this.aLong189 + (long) arg0 * (long) local15;
				if (this.aLong188 - local25 >= 0L) {
					this.aLong189 = local25;
					break;
				}
				@Pc(57) int local57 = (int) (((long) local15 + this.aLong188 - this.aLong189 - 1L) / (long) local15);
				arg0 -= local57;
				this.aLong189 += (long) local57 * (long) local15;
				this.noteStream.skip(local57);
				this.method4435();
			} while (this.aClass84_1.isValid());
		}
		this.noteStream.skip(arg0);
	}

	@OriginalMember(owner = "client!va", name = "e", descriptor = "(II)V")
	private void method4438(@OriginalArg(0) int arg0) {
		if ((this.channelFlags[arg0] & 0x4) == 0) {
			return;
		}
		for (@Pc(24) MidiNote local24 = (MidiNote) this.noteStream.notes.head(); local24 != null; local24 = (MidiNote) this.noteStream.notes.next()) {
			if (local24.channel == arg0) {
				local24.anInt3775 = 0;
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(BLclient!mf;)I")
	private int method4439(@OriginalArg(1) MidiNote arg0) {
		@Pc(6) Midi_Class162 local6 = arg0.aMidiClass162_1;
		@Pc(17) int local17 = (arg0.anInt3781 * arg0.anInt3764 >> 12) + arg0.anInt3769;
		local17 += this.anIntArray501[arg0.channel] * (this.channelPitch[arg0.channel] - 8192) >> 12;
		@Pc(62) int local62;
		if (local6.anInt5814 > 0 && (local6.anInt5809 > 0 || this.channelModulation[arg0.channel] > 0)) {
			local62 = local6.anInt5809 << 2;
			@Pc(67) int local67 = local6.anInt5811 << 1;
			if (local67 > arg0.anInt3774) {
				local62 = arg0.anInt3774 * local62 / local67;
			}
			local62 += this.channelModulation[arg0.channel] >> 7;
			@Pc(102) double local102 = Math.sin((double) (arg0.anInt3768 & 0x1FF) * 0.01227184630308513D);
			local17 += (int) ((double) local62 * local102);
		}
		local62 = (int) ((double) (arg0.sound.rate * 256) * Math.pow(2.0D, (double) local17 * 3.255208333333333E-4D) / (double) AudioChannel.sampleRate + 0.5D);
		return local62 >= 1 ? local62 : 1;
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(Z)I")
	public int getVolume() {
		return this.anInt5668;
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(ZB)V")
	private void reset(@OriginalArg(0) boolean arg0) {
		if (arg0) {
			this.method4422(-1);
		} else {
			this.method4430(-1);
		}
		this.resetAllControllers(-1);
		@Pc(29) int local29;
		for (local29 = 0; local29 < 16; local29++) {
			this.channelProgram[local29] = this.anIntArray499[local29];
		}
		for (local29 = 0; local29 < 16; local29++) {
			this.anIntArray502[local29] = this.anIntArray499[local29] & 0xFFFFFF80;
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(Lclient!mf;ZB)V")
	public void method4442(@OriginalArg(0) MidiNote arg0, @OriginalArg(1) boolean arg1) {
		@Pc(8) int local8 = arg0.sound.samples.length;
		@Pc(27) int local27;
		if (arg1 && arg0.sound.aBoolean165) {
			@Pc(37) int local37 = local8 + local8 - arg0.sound.start;
			local8 <<= 0x8;
			local27 = (int) ((long) local37 * (long) this.anIntArray509[arg0.channel] >> 6);
			if (local27 >= local8) {
				arg0.stream.method409();
				local27 = local8 + local8 - local27 - 1;
			}
		} else {
			local27 = (int) ((long) local8 * (long) this.anIntArray509[arg0.channel] >> 6);
		}
		arg0.stream.method401(local27);
	}

	@OriginalMember(owner = "client!va", name = "f", descriptor = "(II)V")
	private void method4443(@OriginalArg(0) int arg0) {
		if ((this.channelFlags[arg0] & 0x2) == 0) {
			return;
		}
		for (@Pc(20) MidiNote local20 = (MidiNote) this.noteStream.notes.head(); local20 != null; local20 = (MidiNote) this.noteStream.notes.next()) {
			if (arg0 == local20.channel && this.notes[arg0][local20.midiKey] == null && local20.anInt3767 < 0) {
				local20.anInt3767 = 0;
			}
		}
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(Lclient!mf;I)Z")
	public boolean method4445(@OriginalArg(0) MidiNote arg0) {
		if (arg0.stream != null) {
			return false;
		}
		if (arg0.anInt3767 >= 0) {
			arg0.unlink();
			if (arg0.anInt3776 > 0 && this.aClass3_Sub25ArrayArray2[arg0.channel][arg0.anInt3776] == arg0) {
				this.aClass3_Sub25ArrayArray2[arg0.channel][arg0.anInt3776] = null;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!va", name = "f", descriptor = "(B)V")
	public synchronized void method4446() {
		this.method4448(true);
	}

	@OriginalMember(owner = "client!va", name = "g", descriptor = "(II)V")
	public synchronized void setVolume(@OriginalArg(1) int arg0) {
		this.anInt5668 = arg0;
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(ZB)V")
	private synchronized void method4448(@OriginalArg(0) boolean arg0) {
		this.aClass84_1.release();
		this.aClass3_Sub29_2 = null;
		this.reset(arg0);
	}

	@OriginalMember(owner = "client!va", name = "b", descriptor = "(BLclient!mf;)I")
	private int method4449(@OriginalArg(1) MidiNote arg0) {
		if (this.anIntArray507[arg0.channel] == 0) {
			return 0;
		}
		@Pc(21) Midi_Class162 local21 = arg0.aMidiClass162_1;
		@Pc(37) int local37 = this.channelVolume[arg0.channel] * this.channelExpression[arg0.channel] + 4096 >> 13;
		@Pc(45) int local45 = local37 * local37 + 16384 >> 15;
		@Pc(54) int local54 = arg0.anInt3772 * local45 + 16384 >> 15;
		@Pc(63) int local63 = local54 * this.anInt5668 + 128 >> 8;
		local37 = this.anIntArray507[arg0.channel] * local63 + 128 >> 8;
		if (local21.anInt5815 > 0) {
			local37 = (int) (Math.pow(0.5D, (double) arg0.anInt3782 * 1.953125E-5D * (double) local21.anInt5815) * (double) local37 + 0.5D);
		}
		@Pc(105) int local105;
		@Pc(113) int local113;
		@Pc(143) int local143;
		@Pc(133) int local133;
		if (local21.aByteArray80 != null) {
			local105 = arg0.anInt3770;
			local113 = local21.aByteArray80[arg0.anInt3763 + 1];
			if (local21.aByteArray80.length - 2 > arg0.anInt3763) {
				local133 = (local21.aByteArray80[arg0.anInt3763 + 2] & 0xFF) << 8;
				local143 = (local21.aByteArray80[arg0.anInt3763] & 0xFF) << 8;
				local113 += (local21.aByteArray80[arg0.anInt3763 + 3] - local113) * (local105 - local143) / (local133 - local143);
			}
			local37 = local113 * local37 + 32 >> 6;
		}
		if (arg0.anInt3767 > 0 && local21.aByteArray81 != null) {
			local105 = arg0.anInt3767;
			local113 = local21.aByteArray81[arg0.anInt3777 + 1];
			if (local21.aByteArray81.length - 2 > arg0.anInt3777) {
				local143 = (local21.aByteArray81[arg0.anInt3777] & 0xFF) << 8;
				local133 = (local21.aByteArray81[arg0.anInt3777 + 2] & 0xFF) << 8;
				local113 += (local105 - local143) * (-local113 + local21.aByteArray81[arg0.anInt3777 + 3]) / (local133 - local143);
			}
			local37 = local37 * local113 + 32 >> 6;
		}
		return local37;
	}

	@OriginalMember(owner = "client!va", name = "d", descriptor = "()Lclient!qb;")
	@Override
	public synchronized PcmStream nextSubStream() {
		return null;
	}
}
