package com.jagex.sound.vorbis;

import com.jagex.sound.pcm.PcmSound;
import com.jagex.js5.Js5;
import com.jagex.core.datastruct.Node;
import com.jagex.core.io.Packet;
import com.jagex.math.IntMath;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!jc")
public final class VorbisSound extends Node {

	@OriginalMember(owner = "runetek4.client!jc", name = "u", descriptor = "[Lclient!vb;")
	public static VorbisResidue[] residues;
	@OriginalMember(owner = "runetek4.client!jc", name = "A", descriptor = "[Lclient!uk;")
	public static VorbisMapping[] mappings;
	@OriginalMember(owner = "runetek4.client!jc", name = "p", descriptor = "[F")
	public static float[] B_1;
	@OriginalMember(owner = "runetek4.client!jc", name = "q", descriptor = "[F")
	public static float[] C_1;
	@OriginalMember(owner = "runetek4.client!jc", name = "t", descriptor = "[I")
	public static int[] BITREVERSE_0;
	@OriginalMember(owner = "runetek4.client!jc", name = "v", descriptor = "[Z")
	public static boolean[] modeBlockFlags;
	@OriginalMember(owner = "runetek4.client!jc", name = "y", descriptor = "I")
	public static int blockSize1;
	@OriginalMember(owner = "runetek4.client!jc", name = "B", descriptor = "[F")
	public static float[] A_1;
	@OriginalMember(owner = "runetek4.client!jc", name = "C", descriptor = "[F")
	public static float[] A_0;
	@OriginalMember(owner = "runetek4.client!jc", name = "E", descriptor = "I")
	public static int blockSize0;
	@OriginalMember(owner = "runetek4.client!jc", name = "F", descriptor = "[F")
	public static float[] currentWindow;
	@OriginalMember(owner = "runetek4.client!jc", name = "I", descriptor = "[Lclient!ji;")
	public static VorbisCodebook[] codebooks;
	@OriginalMember(owner = "runetek4.client!jc", name = "J", descriptor = "[Lclient!ie;")
	public static VorbisFloor[] floors;
	@OriginalMember(owner = "runetek4.client!jc", name = "L", descriptor = "[I")
	public static int[] BITREVERSE_1;
	@OriginalMember(owner = "runetek4.client!jc", name = "P", descriptor = "[F")
	public static float[] B_0;
	@OriginalMember(owner = "runetek4.client!jc", name = "S", descriptor = "[F")
	public static float[] C_0;
	@OriginalMember(owner = "runetek4.client!jc", name = "W", descriptor = "[I")
	public static int[] modeMappings;
	@OriginalMember(owner = "runetek4.client!jc", name = "Q", descriptor = "I")
	static int bitPosition;
	@OriginalMember(owner = "runetek4.client!jc", name = "T", descriptor = "[B")
	static byte[] bytes;
	@OriginalMember(owner = "runetek4.client!jc", name = "G", descriptor = "I")
	static int position;
	@OriginalMember(owner = "runetek4.client!jc", name = "H", descriptor = "Z")
	public static boolean headerDecoded = false;
	@OriginalMember(owner = "runetek4.client!jc", name = "r", descriptor = "I")
	private int prevN;

	@OriginalMember(owner = "runetek4.client!jc", name = "s", descriptor = "I")
	private int samplesLen;

	@OriginalMember(owner = "runetek4.client!jc", name = "w", descriptor = "[F")
	private float[] previousWindow;

	@OriginalMember(owner = "runetek4.client!jc", name = "x", descriptor = "Z")
	private boolean negativeBitRate;

	@OriginalMember(owner = "runetek4.client!jc", name = "z", descriptor = "I")
	private int nominalBitRate;

	@OriginalMember(owner = "runetek4.client!jc", name = "D", descriptor = "I")
	private int minBitRate;

	@OriginalMember(owner = "runetek4.client!jc", name = "K", descriptor = "I")
	private int sampleRate;

	@OriginalMember(owner = "runetek4.client!jc", name = "M", descriptor = "[[B")
	private byte[][] packets;

	@OriginalMember(owner = "runetek4.client!jc", name = "N", descriptor = "Z")
	private boolean prevNoResidue;

	@OriginalMember(owner = "runetek4.client!jc", name = "O", descriptor = "I")
	private int prevQuarter;

	@OriginalMember(owner = "runetek4.client!jc", name = "R", descriptor = "[B")
	private byte[] samples;

	@OriginalMember(owner = "runetek4.client!jc", name = "U", descriptor = "I")
	private int packetIndex;

	@OriginalMember(owner = "runetek4.client!jc", name = "V", descriptor = "I")
	private int currentOff;

	@OriginalMember(owner = "runetek4.client!jc", name = "<init>", descriptor = "([B)V")
	public VorbisSound(@OriginalArg(0) byte[] bytes) {
		this.decode(bytes);
	}

	@OriginalMember(owner = "client!jc", name = "e", descriptor = "(I)I")
	public static int gbit(@OriginalArg(0) int n) {
		@Pc(1) int value = 0;
		@Pc(3) int read = 0;
		@Pc(12) int bitOff;
		while (n >= 8 - bitPosition) {
			bitOff = 8 - bitPosition;
			@Pc(18) int mask = (0x1 << bitOff) - 1;
			value += (bytes[position] >> bitPosition & mask) << read;
			bitPosition = 0;
			position++;
			read += bitOff;
			n -= bitOff;
		}
		if (n > 0) {
			bitOff = (0x1 << n) - 1;
			value += (bytes[position] >> bitPosition & bitOff) << read;
			bitPosition += n;
		}
		return value;
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "b", descriptor = "()I")
	public static int g1() {
		@Pc(7) int value = bytes[position] >> bitPosition & 0x1;
		bitPosition++;
		position += bitPosition >> 3;
		bitPosition &= 0x7;
		return value;
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "([BI)V")
	public static void setData(@OriginalArg(0) byte[] arg0) {
		bytes = arg0;
		position = 0;
		bitPosition = 0;
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "b", descriptor = "([B)V")
	public static void decodeHeader(@OriginalArg(0) byte[] arg0) {
		setData(arg0);
		blockSize0 = 0x1 << gbit(4);
		blockSize1 = 0x1 << gbit(4);
		currentWindow = new float[blockSize1];
		@Pc(17) int i;
		@Pc(26) int n;
		@Pc(30) int n_2;
		@Pc(34) int n_4;
		@Pc(38) int n_8;
		for (i = 0; i < 2; i++) {
			n = i == 0 ? blockSize0 : blockSize1;
			n_2 = n >> 1;
			n_4 = n >> 2;
			n_8 = n >> 3;
			@Pc(41) float[] A = new float[n_2];
			for (@Pc(43) int k = 0; k < n_4; k++) {
				A[k * 2] = (float) Math.cos((double) (k * 4) * 3.141592653589793D / (double) n);
				A[k * 2 + 1] = -((float) Math.sin((double) (k * 4) * 3.141592653589793D / (double) n));
			}
			@Pc(86) float[] B = new float[n_2];
			for (@Pc(88) int k = 0; k < n_4; k++) {
				B[k * 2] = (float) Math.cos((double) (k * 2 + 1) * 3.141592653589793D / (double) (n * 2));
				B[k * 2 + 1] = (float) Math.sin((double) (k * 2 + 1) * 3.141592653589793D / (double) (n * 2));
			}
			@Pc(138) float[] C = new float[n_4];
			for (@Pc(140) int k = 0; k < n_8; k++) {
				C[k * 2] = (float) Math.cos((double) (k * 4 + 2) * 3.141592653589793D / (double) n);
				C[k * 2 + 1] = -((float) Math.sin((double) (k * 4 + 2) * 3.141592653589793D / (double) n));
			}
			@Pc(187) int[] bitreverse = new int[n_8];
			@Pc(193) int bits = IntMath.bitCount(n_8 - 1);
			for (@Pc(195) int j = 0; j < n_8; j++) {
				bitreverse[j] = IntMath.bitReverse(bits, j);
			}
			if (i == 0) {
				A_0 = A;
				B_0 = B;
				C_0 = C;
				BITREVERSE_0 = bitreverse;
			} else {
				A_1 = A;
				B_1 = B;
				C_1 = C;
				BITREVERSE_1 = bitreverse;
			}
		}
		i = gbit(8) + 1;
		codebooks = new VorbisCodebook[i];
		for (n = 0; n < i; n++) {
			codebooks[n] = new VorbisCodebook();
		}
		n = gbit(6) + 1;
		for (n_2 = 0; n_2 < n; n_2++) {
			gbit(16);
		}
		n = gbit(6) + 1;
		floors = new VorbisFloor[n];
		for (n_2 = 0; n_2 < n; n_2++) {
			floors[n_2] = new VorbisFloor();
		}
		n_2 = gbit(6) + 1;
		residues = new VorbisResidue[n_2];
		for (n_4 = 0; n_4 < n_2; n_4++) {
			residues[n_4] = new VorbisResidue();
		}
		n_4 = gbit(6) + 1;
		mappings = new VorbisMapping[n_4];
		for (n_8 = 0; n_8 < n_4; n_8++) {
			mappings[n_8] = new VorbisMapping();
		}
		n_8 = gbit(6) + 1;
		modeBlockFlags = new boolean[n_8];
		modeMappings = new int[n_8];
		for (@Pc(340) int k = 0; k < n_8; k++) {
			modeBlockFlags[k] = g1() != 0;
			gbit(16);
			gbit(16);
			modeMappings[k] = gbit(8);
		}
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "c", descriptor = "(I)F")
	public static float float32Unpack(@OriginalArg(0) int x) {
		@Pc(3) int mantiss = x & 0x1FFFFF;
		@Pc(7) int sign = x & Integer.MIN_VALUE;
		@Pc(13) int exponent = x >> 21 & 0x3FF;
		if (sign != 0) {
			mantiss = -mantiss;
		}
		return (float) ((double) mantiss * Math.pow(2.0D, exponent - 788));
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "(Lclient!ve;)Z")
	public static boolean decodeHeader(@OriginalArg(0) Js5 arg0) {
		if (!headerDecoded) {
			@Pc(7) byte[] data = arg0.getfile(0, 0);
			if (data == null) {
				return false;
			}
			decodeHeader(data);
			headerDecoded = true;
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "(Lclient!ve;II)Lclient!jc;")
	public static VorbisSound create(@OriginalArg(0) Js5 vorbis, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (decodeHeader(vorbis)) {
			@Pc(16) byte[] data = vorbis.getfile(arg1, arg2);
			return data == null ? null : new VorbisSound(data);
		} else {
			vorbis.isFileReady(arg1, arg2);
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "([I)Lclient!kj;")
	public PcmSound toPcmSound(@OriginalArg(0) int[] maxSamples) {
		if (maxSamples != null && maxSamples[0] <= 0) {
			return null;
		}
		if (this.samples == null) {
			this.prevN = 0;
			this.previousWindow = new float[blockSize1];
			this.samples = new byte[this.samplesLen];
			this.currentOff = 0;
			this.packetIndex = 0;
		}
		while (this.packetIndex < this.packets.length) {
			if (maxSamples != null && maxSamples[0] <= 0) {
				return null;
			}
			@Pc(47) float[] data = this.decode(this.packetIndex);
			if (data != null) {
				@Pc(52) int off = this.currentOff;
				@Pc(55) int length = data.length;
				if (length > this.samplesLen - off) {
					length = this.samplesLen - off;
				}
				for (@Pc(68) int i = 0; i < length; i++) {
					@Pc(80) int v = (int) (data[i] * 128.0F + 128.0F);
					if ((v & 0xFFFFFF00) != 0) {
						v = ~v >> 31;
					}
					this.samples[off++] = (byte) (v - 128);
				}
				if (maxSamples != null) {
					maxSamples[0] -= off - this.currentOff;
				}
				this.currentOff = off;
			}
			this.packetIndex++;
		}
		this.previousWindow = null;
		@Pc(129) byte[] data = this.samples;
		this.samples = null;
		return new PcmSound(this.sampleRate, data, this.nominalBitRate, this.minBitRate, this.negativeBitRate);
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "([B)V")
	private void decode(@OriginalArg(0) byte[] arg0) {
		@Pc(4) Packet local4 = new Packet(arg0);
		this.sampleRate = local4.g4();
		this.samplesLen = local4.g4();
		this.nominalBitRate = local4.g4();
		this.minBitRate = local4.g4();
		if (this.minBitRate < 0) {
			this.minBitRate = ~this.minBitRate;
			this.negativeBitRate = true;
		}
		@Pc(40) int local40 = local4.g4();
		this.packets = new byte[local40][];
		for (@Pc(46) int local46 = 0; local46 < local40; local46++) {
			@Pc(51) int local51 = 0;
			@Pc(55) int local55;
			do {
				local55 = local4.g1();
				local51 += local55;
			} while (local55 >= 255);
			@Pc(67) byte[] local67 = new byte[local51];
			local4.gdata(local51, local67);
			this.packets[local46] = local67;
		}
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "d", descriptor = "(I)[F")
	private float[] decode(@OriginalArg(0) int packet) {
		setData(this.packets[packet]);
		g1();
		@Pc(15) int modeNumber = gbit(IntMath.bitCount(modeMappings.length - 1));
		@Pc(19) boolean blockflag = modeBlockFlags[modeNumber];
		@Pc(25) int n = blockflag ? blockSize1 : blockSize0;
		@Pc(27) boolean previousWindowFlag = false;
		@Pc(29) boolean nextWindowFlag = false;
		if (blockflag) {
			previousWindowFlag = g1() != 0;
			nextWindowFlag = g1() != 0;
		}
		@Pc(47) int windowCenter = n >> 1;
		@Pc(59) int leftWindowStart;
		@Pc(67) int leftWindowEnd;
		@Pc(71) int leftN;
		if (blockflag && !previousWindowFlag) {
			leftWindowStart = (n >> 2) - (blockSize0 >> 2);
			leftWindowEnd = (n >> 2) + (blockSize0 >> 2);
			leftN = blockSize0 >> 1;
		} else {
			leftWindowStart = 0;
			leftWindowEnd = windowCenter;
			leftN = n >> 1;
		}
		@Pc(94) int rightWindowStart;
		@Pc(104) int rightWindowEnd;
		@Pc(108) int rightN;
		if (blockflag && !nextWindowFlag) {
			rightWindowStart = n - (n >> 2) - (blockSize0 >> 2);
			rightWindowEnd = n + (blockSize0 >> 2) - (n >> 2);
			rightN = blockSize0 >> 1;
		} else {
			rightWindowStart = windowCenter;
			rightWindowEnd = n;
			rightN = n >> 1;
		}
		@Pc(123) VorbisMapping mapping = mappings[modeMappings[modeNumber]];
		@Pc(126) int submapNumber = mapping.mux;
		@Pc(131) int floorNumber = mapping.submapFloor[submapNumber];
		@Pc(140) boolean noDecodeFloor = !floors[floorNumber].decodePacket();
		for (@Pc(144) int i = 0; i < mapping.submaps; i++) {
			@Pc(155) VorbisResidue residue = residues[mapping.submapResidue[i]];
			@Pc(157) float[] v = currentWindow;
			residue.decode(v, n >> 1, noDecodeFloor);
		}
		@Pc(176) int floor;
		if (!noDecodeFloor) {
			floorNumber = mapping.mux;
			floor = mapping.submapFloor[floorNumber];
			floors[floor].synthesize(currentWindow, n >> 1);
		}
		@Pc(212) int local212;
		if (noDecodeFloor) {
			for (floorNumber = n >> 1; floorNumber < n; floorNumber++) {
				currentWindow[floorNumber] = 0.0F;
			}
		} else {
			floorNumber = n >> 1;
			floor = n >> 2;
			local212 = n >> 3;
			@Pc(214) float[] local214 = currentWindow;
			@Pc(216) int local216;
			for (local216 = 0; local216 < floorNumber; local216++) {
				local214[local216] *= 0.5F;
			}
			for (local216 = floorNumber; local216 < n; local216++) {
				local214[local216] = -local214[n - local216 - 1];
			}
			@Pc(252) float[] local252 = blockflag ? A_1 : A_0;
			@Pc(258) float[] local258 = blockflag ? B_1 : B_0;
			@Pc(264) float[] local264 = blockflag ? C_1 : C_0;
			@Pc(270) int[] local270 = blockflag ? BITREVERSE_1 : BITREVERSE_0;
			@Pc(272) int local272;
			@Pc(291) float local291;
			@Pc(309) float local309;
			@Pc(315) float local315;
			@Pc(323) float local323;
			for (local272 = 0; local272 < floor; local272++) {
				local291 = local214[local272 * 4] - local214[n - local272 * 4 - 1];
				local309 = local214[local272 * 4 + 2] - local214[n - local272 * 4 - 3];
				local315 = local252[local272 * 2];
				local323 = local252[local272 * 2 + 1];
				local214[n - local272 * 4 - 1] = local291 * local315 - local309 * local323;
				local214[n - local272 * 4 - 3] = local291 * local323 + local309 * local315;
			}
			@Pc(432) float local432;
			@Pc(442) float local442;
			for (local272 = 0; local272 < local212; local272++) {
				local291 = local214[floorNumber + local272 * 4 + 3];
				local309 = local214[floorNumber + local272 * 4 + 1];
				local315 = local214[local272 * 4 + 3];
				local323 = local214[local272 * 4 + 1];
				local214[floorNumber + local272 * 4 + 3] = local291 + local315;
				local214[floorNumber + local272 * 4 + 1] = local309 + local323;
				local432 = local252[floorNumber - local272 * 4 - 4];
				local442 = local252[floorNumber - local272 * 4 - 3];
				local214[local272 * 4 + 3] = (local291 - local315) * local432 - (local309 - local323) * local442;
				local214[local272 * 4 + 1] = (local309 - local323) * local432 + (local291 - local315) * local442;
			}
			local272 = IntMath.bitCount(n - 1);
			@Pc(488) int local488;
			@Pc(499) int local499;
			@Pc(503) int local503;
			@Pc(505) int local505;
			for (local488 = 0; local488 < local272 - 3; local488++) {
				local499 = n >> local488 + 2;
				local503 = 0x8 << local488;
				for (local505 = 0; local505 < 0x2 << local488; local505++) {
					@Pc(518) int local518 = n - local499 * 2 * local505;
					@Pc(528) int local528 = n - local499 * (local505 * 2 + 1);
					for (@Pc(530) int local530 = 0; local530 < n >> local488 + 4; local530++) {
						@Pc(541) int local541 = local530 * 4;
						@Pc(549) float local549 = local214[local518 - local541 - 1];
						@Pc(557) float local557 = local214[local518 - local541 - 3];
						@Pc(565) float local565 = local214[local528 - local541 - 1];
						@Pc(573) float local573 = local214[local528 - local541 - 3];
						local214[local518 - local541 - 1] = local549 + local565;
						local214[local518 - local541 - 3] = local557 + local573;
						@Pc(599) float local599 = local252[local530 * local503];
						@Pc(607) float local607 = local252[local530 * local503 + 1];
						local214[local528 - local541 - 1] = (local549 - local565) * local599 - (local557 - local573) * local607;
						local214[local528 - local541 - 3] = (local557 - local573) * local599 + (local549 - local565) * local607;
					}
				}
			}
			for (local488 = 1; local488 < local212 - 1; local488++) {
				local499 = local270[local488];
				if (local488 < local499) {
					local503 = local488 * 8;
					local505 = local499 * 8;
					local432 = local214[local503 + 1];
					local214[local503 + 1] = local214[local505 + 1];
					local214[local505 + 1] = local432;
					local432 = local214[local503 + 3];
					local214[local503 + 3] = local214[local505 + 3];
					local214[local505 + 3] = local432;
					local432 = local214[local503 + 5];
					local214[local503 + 5] = local214[local505 + 5];
					local214[local505 + 5] = local432;
					local432 = local214[local503 + 7];
					local214[local503 + 7] = local214[local505 + 7];
					local214[local505 + 7] = local432;
				}
			}
			for (local488 = 0; local488 < floorNumber; local488++) {
				local214[local488] = local214[local488 * 2 + 1];
			}
			for (local488 = 0; local488 < local212; local488++) {
				local214[n - local488 * 2 - 1] = local214[local488 * 4];
				local214[n - local488 * 2 - 2] = local214[local488 * 4 + 1];
				local214[n - floor - local488 * 2 - 1] = local214[local488 * 4 + 2];
				local214[n - floor - local488 * 2 - 2] = local214[local488 * 4 + 3];
			}
			for (local488 = 0; local488 < local212; local488++) {
				local309 = local264[local488 * 2];
				local315 = local264[local488 * 2 + 1];
				local323 = local214[floorNumber + local488 * 2];
				local432 = local214[floorNumber + local488 * 2 + 1];
				local442 = local214[n - local488 * 2 - 2];
				@Pc(908) float local908 = local214[n - local488 * 2 - 1];
				@Pc(920) float local920 = local315 * (local323 - local442) + local309 * (local432 + local908);
				local214[floorNumber + local488 * 2] = (local323 + local442 + local920) * 0.5F;
				local214[n - local488 * 2 - 2] = (local323 + local442 - local920) * 0.5F;
				@Pc(962) float local962 = local315 * (local432 + local908) - local309 * (local323 - local442);
				local214[floorNumber + local488 * 2 + 1] = (local432 + local962 - local908) * 0.5F;
				local214[n - local488 * 2 - 1] = (local908 + local962 - local432) * 0.5F;
			}
			for (local488 = 0; local488 < floor; local488++) {
				local214[local488] = local214[local488 * 2 + floorNumber] * local258[local488 * 2] + local214[local488 * 2 + floorNumber + 1] * local258[local488 * 2 + 1];
				local214[floorNumber - local488 - 1] = local214[local488 * 2 + floorNumber] * local258[local488 * 2 + 1] - local214[local488 * 2 + floorNumber + 1] * local258[local488 * 2];
			}
			for (local488 = 0; local488 < floor; local488++) {
				local214[n + local488 - floor] = -local214[local488];
			}
			for (local488 = 0; local488 < floor; local488++) {
				local214[local488] = local214[floor + local488];
			}
			for (local488 = 0; local488 < floor; local488++) {
				local214[floor + local488] = -local214[floor - local488 - 1];
			}
			for (local488 = 0; local488 < floor; local488++) {
				local214[floorNumber + local488] = local214[n - local488 - 1];
			}
			for (local488 = leftWindowStart; local488 < leftWindowEnd; local488++) {
				local309 = (float) Math.sin(((double) (local488 - leftWindowStart) + 0.5D) / (double) leftN * 0.5D * 3.141592653589793D);
				currentWindow[local488] *= (float) Math.sin((double) local309 * 1.5707963267948966D * (double) local309);
			}
			for (local488 = rightWindowStart; local488 < rightWindowEnd; local488++) {
				local309 = (float) Math.sin(((double) (local488 - rightWindowStart) + 0.5D) / (double) rightN * 0.5D * 3.141592653589793D + 1.5707963267948966D);
				currentWindow[local488] *= (float) Math.sin((double) local309 * 1.5707963267948966D * (double) local309);
			}
		}
		@Pc(1228) float[] local1228 = null;
		if (this.prevN > 0) {
			floor = this.prevN + n >> 2;
			local1228 = new float[floor];
			@Pc(1257) int local1257;
			if (!this.prevNoResidue) {
				for (local212 = 0; local212 < this.prevQuarter; local212++) {
					local1257 = (this.prevN >> 1) + local212;
					local1228[local212] += this.previousWindow[local1257];
				}
			}
			if (!noDecodeFloor) {
				for (local212 = leftWindowStart; local212 < n >> 1; local212++) {
					local1257 = local1228.length + local212 - (n >> 1);
					local1228[local1257] += currentWindow[local212];
				}
			}
		}
		@Pc(1301) float[] local1301 = this.previousWindow;
		this.previousWindow = currentWindow;
		currentWindow = local1301;
		this.prevN = n;
		this.prevQuarter = rightWindowEnd - (n >> 1);
		this.prevNoResidue = noDecodeFloor;
		return local1228;
	}
}
