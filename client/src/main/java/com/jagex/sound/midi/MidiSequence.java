package com.jagex.sound.midi;

import com.jagex.core.io.Packet;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static com.jagex.sound.midi.Midi.TRACK_HEADER;

@OriginalClass("client!ki")
public final class MidiSequence {

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "[B")
	public static final byte[] EVENT_LENGTHS = new byte[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "[I")
	public int[] trackDeltas;

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "[I")
	private int[] tracks;

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "I")
	public int timeDivision;

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "[I")
	private int[] trackOffsets;

	@OriginalMember(owner = "client!ki", name = "g", descriptor = "J")
	private long globalDelay;

	@OriginalMember(owner = "client!ki", name = "h", descriptor = "[I")
	private int[] eventHistory;

	@OriginalMember(owner = "client!ki", name = "i", descriptor = "I")
	private int tempo;

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "Lclient!wa;")
	private final Packet packet = new Packet(null);

	@OriginalMember(owner = "client!ki", name = "<init>", descriptor = "()V")
	public MidiSequence() {
	}

	@OriginalMember(owner = "client!ki", name = "<init>", descriptor = "([B)V")
	public MidiSequence(@OriginalArg(0) byte[] data) {
		this.decode(data);
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(I)J")
	public long delayForDelta(@OriginalArg(0) int delta) {
		return this.globalDelay + (long) delta * (long) this.tempo;
	}

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "()Z")
	public boolean isComplete() {
		@Pc(3) int count = this.tracks.length;
		for (@Pc(5) int i = 0; i < count; i++) {
			if (this.tracks[i] >= 0) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(J)V")
	public void reset(@OriginalArg(0) long delay) {
		this.globalDelay = delay;
		@Pc(6) int count = this.tracks.length;
		for (@Pc(8) int i = 0; i < count; i++) {
			this.trackDeltas[i] = 0;
			this.eventHistory[i] = 0;
			this.packet.offset = this.trackOffsets[i];
			this.step(i);
			this.tracks[i] = this.packet.offset;
		}
	}

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "()Z")
	public boolean isPlaying() {
		return this.packet.data != null;
	}

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "()I")
	public int trackCount() {
		return this.tracks.length;
	}

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "(I)I")
	public int nextEvent(@OriginalArg(0) int track) {
		return this.nextEventData(track);
	}

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "(I)V")
	public void switchTrack(@OriginalArg(0) int track) {
		this.packet.offset = this.tracks[track];
	}

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "(I)V")
	public void step(@OriginalArg(0) int track) {
		@Pc(4) int deltaTime = this.packet.gVarInt();
		this.trackDeltas[track] += deltaTime;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(II)I")
	private int nextEventData(@OriginalArg(0) int track, @OriginalArg(1) int event) {
		@Pc(12) int value;
		if (event != 255) {
			@Pc(78) byte length = EVENT_LENGTHS[event - 128];
			value = event;
			if (length >= 1) {
				value = event | this.packet.g1() << 8;
			}
			if (length >= 2) {
				value |= this.packet.g1() << 16;
			}
			return value;
		}
		@Pc(7) int type = this.packet.g1();
		value = this.packet.gVarInt();
		if (type == 47) {
			this.packet.offset += value;
			return 1;
		} else if (type == 81) {
			@Pc(32) int newTempo = this.packet.g3();
			value -= 3;
			@Pc(38) int delta = this.trackDeltas[track];
			this.globalDelay += (long) delta * (long) (this.tempo - newTempo);
			this.tempo = newTempo;
			this.packet.offset += value;
			return 2;
		} else {
			this.packet.offset += value;
			return 3;
		}
	}

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "(I)I")
	private int nextEventData(@OriginalArg(0) int track) {
		@Pc(7) byte event = this.packet.data[this.packet.offset];
		@Pc(13) int historicalEvent;
		if (event < 0) {
			historicalEvent = event & 0xFF;
			this.eventHistory[track] = historicalEvent;
			this.packet.offset++;
		} else {
			historicalEvent = this.eventHistory[track];
		}
		if (historicalEvent != 240 && historicalEvent != 247) {
			return this.nextEventData(track, historicalEvent);
		}
		@Pc(42) int length = this.packet.gVarInt();
		if (historicalEvent == 247 && length > 0) {
			@Pc(57) int nextEvent = this.packet.data[this.packet.offset] & 0xFF;
			if (nextEvent >= 241 && nextEvent <= 243 || nextEvent == 246 || nextEvent == 248 || nextEvent >= 250 && nextEvent <= 252 || nextEvent == 254) {
				this.packet.offset++;
				this.eventHistory[track] = nextEvent;
				return this.nextEventData(track, nextEvent);
			}
		}
		this.packet.offset += length;
		return 0;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "([B)V")
	public void decode(@OriginalArg(0) byte[] data) {
		this.packet.data = data;
		this.packet.offset = 10;
		@Pc(12) int tracks = this.packet.g2();
		this.timeDivision = this.packet.g2();
		this.tempo = 500000;
		this.trackOffsets = new int[tracks];
		@Pc(27) int i = 0;
		while (i < tracks) {
			@Pc(35) int chunkId = this.packet.g4();
			@Pc(40) int chunkSize = this.packet.g4();
			if (chunkId == TRACK_HEADER) {
				this.trackOffsets[i] = this.packet.offset;
				i++;
			}
			this.packet.offset += chunkSize;
		}
		this.globalDelay = 0L;
		this.tracks = new int[tracks];
		for (i = 0; i < tracks; i++) {
			this.tracks[i] = this.trackOffsets[i];
		}
		this.trackDeltas = new int[tracks];
		this.eventHistory = new int[tracks];
	}

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "(I)V")
	public void updatePosition(@OriginalArg(0) int track) {
		this.tracks[track] = this.packet.offset;
	}

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "()I")
	public int activeTrack() {
		@Pc(3) int count = this.tracks.length;
		@Pc(5) int track = -1;
		@Pc(7) int min = Integer.MAX_VALUE;
		for (@Pc(9) int i = 0; i < count; i++) {
			if (this.tracks[i] >= 0 && this.trackDeltas[i] < min) {
				track = i;
				min = this.trackDeltas[i];
			}
		}
		return track;
	}

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "()V")
	public void finish() {
		this.packet.data = null;
		this.trackOffsets = null;
		this.tracks = null;
		this.trackDeltas = null;
		this.eventHistory = null;
	}

	@OriginalMember(owner = "client!ki", name = "g", descriptor = "()V")
	public void endTrack() {
		this.packet.offset = -1;
	}
}
