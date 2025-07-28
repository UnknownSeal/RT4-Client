package com.jagex.runetek4.audio.midi;

import com.jagex.runetek4.core.io.Packet;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ki")
public final class MidiDecoder {

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "[B")
	public static final byte[] STATUS_LENGTHS = new byte[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "[I")
	public int[] times;

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "[I")
	private int[] positions;

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "I")
	public int division;

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "[I")
	private int[] startPositions;

	@OriginalMember(owner = "client!ki", name = "g", descriptor = "J")
	private long startMillis;

	@OriginalMember(owner = "client!ki", name = "h", descriptor = "[I")
	private int[] statuses;

	@OriginalMember(owner = "client!ki", name = "i", descriptor = "I")
	private int tempo;

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "Lclient!wa;")
	private final Packet packet = new Packet(null);

	@OriginalMember(owner = "client!ki", name = "<init>", descriptor = "()V")
	public MidiDecoder() {
	}

	@OriginalMember(owner = "client!ki", name = "<init>", descriptor = "([B)V")
	public MidiDecoder(@OriginalArg(0) byte[] bytes) {
		this.init(bytes);
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(I)J")
	public long getTimeMillis(@OriginalArg(0) int time) {
		return this.startMillis + (long) time * (long) this.tempo;
	}

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "()Z")
	public boolean hasNextTrack() {
		@Pc(3) int tracks = this.positions.length;
		for (@Pc(5) int i = 0; i < tracks; i++) {
			if (this.positions[i] >= 0) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(J)V")
	public void setStartMillis(@OriginalArg(0) long millis) {
		this.startMillis = millis;
		@Pc(6) int tracks = this.positions.length;
		for (@Pc(8) int i = 0; i < tracks; i++) {
			this.times[i] = 0;
			this.statuses[i] = 0;
			this.packet.offset = this.startPositions[i];
			this.addDeltaTime(i);
			this.positions[i] = this.packet.offset;
		}
	}

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "()Z")
	public boolean isValid() {
		return this.packet.data != null;
	}

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "()I")
	public int getTrackCount() {
		return this.positions.length;
	}

	@OriginalMember(owner = "client!ki", name = "b", descriptor = "(I)I")
	public int getNextEvent(@OriginalArg(0) int track) {
		return this.getNextEventInternal(track);
	}

	@OriginalMember(owner = "client!ki", name = "c", descriptor = "(I)V")
	public void loadTrackPosition(@OriginalArg(0) int track) {
		this.packet.offset = this.positions[track];
	}

	@OriginalMember(owner = "client!ki", name = "d", descriptor = "(I)V")
	public void addDeltaTime(@OriginalArg(0) int track) {
		@Pc(4) int deltaTime = this.packet.gVarInt();
		this.times[track] += deltaTime;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "(II)I")
	private int getNextEvent(@OriginalArg(0) int arg0, @OriginalArg(1) int status) {
		@Pc(12) int event;
		if (status != 255) {
			@Pc(78) byte len = STATUS_LENGTHS[status - 128];
			event = status;
			if (len >= 1) {
				event = status | this.packet.g1() << 8;
			}
			if (len >= 2) {
				event |= this.packet.g1() << 16;
			}
			return event;
		}
		@Pc(7) int type = this.packet.g1();
		event = this.packet.gVarInt();
		if (type == 47) {
			this.packet.offset += event;
			return 1;
		} else if (type == 81) {
			@Pc(32) int tempo = this.packet.g3();
			event -= 3;
			@Pc(38) int time = this.times[arg0];
			this.startMillis += (long) time * (long) (this.tempo - tempo);
			this.tempo = tempo;
			this.packet.offset += event;
			return 2;
		} else {
			this.packet.offset += event;
			return 3;
		}
	}

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "(I)I")
	private int getNextEventInternal(@OriginalArg(0) int track) {
		@Pc(7) byte statusByte = this.packet.data[this.packet.offset];
		@Pc(13) int status;
		if (statusByte < 0) {
			status = statusByte & 0xFF;
			this.statuses[track] = status;
			this.packet.offset++;
		} else {
			status = this.statuses[track];
		}
		if (status != 240 && status != 247) {
			return this.getNextEvent(track, status);
		}
		@Pc(42) int len = this.packet.gVarInt();
		if (status == 247 && len > 0) {
			@Pc(57) int status2 = this.packet.data[this.packet.offset] & 0xFF;
			if (status2 >= 241 && status2 <= 243 || status2 == 246 || status2 == 248 || status2 >= 250 && status2 <= 252 || status2 == 254) {
				this.packet.offset++;
				this.statuses[track] = status2;
				return this.getNextEvent(track, status2);
			}
		}
		this.packet.offset += len;
		return 0;
	}

	@OriginalMember(owner = "client!ki", name = "a", descriptor = "([B)V")
	public void init(@OriginalArg(0) byte[] bytes) {
		this.packet.data = bytes;
		this.packet.offset = 10;
		@Pc(12) int tracks = this.packet.g2();
		this.division = this.packet.g2();
		this.tempo = 500000;
		this.startPositions = new int[tracks];
		@Pc(27) int i = 0;
		while (i < tracks) {
			@Pc(35) int id = this.packet.g4();
			@Pc(40) int len = this.packet.g4();
			if (id == 1297379947) {
				this.startPositions[i] = this.packet.offset;
				i++;
			}
			this.packet.offset += len;
		}
		this.startMillis = 0L;
		this.positions = new int[tracks];
		for (i = 0; i < tracks; i++) {
			this.positions[i] = this.startPositions[i];
		}
		this.times = new int[tracks];
		this.statuses = new int[tracks];
	}

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "(I)V")
	public void saveTrackPosition(@OriginalArg(0) int track) {
		this.positions[track] = this.packet.offset;
	}

	@OriginalMember(owner = "client!ki", name = "e", descriptor = "()I")
	public int getNextTrack() {
		@Pc(3) int tracks = this.positions.length;
		@Pc(5) int track = -1;
		@Pc(7) int minTime = Integer.MAX_VALUE;
		for (@Pc(9) int i = 0; i < tracks; i++) {
			if (this.positions[i] >= 0 && this.times[i] < minTime) {
				track = i;
				minTime = this.times[i];
			}
		}
		return track;
	}

	@OriginalMember(owner = "client!ki", name = "f", descriptor = "()V")
	public void release() {
		this.packet.data = null;
		this.startPositions = null;
		this.positions = null;
		this.times = null;
		this.statuses = null;
	}

	@OriginalMember(owner = "client!ki", name = "g", descriptor = "()V")
	public void loadEndOfTrackPosition() {
		this.packet.offset = -1;
	}
}
