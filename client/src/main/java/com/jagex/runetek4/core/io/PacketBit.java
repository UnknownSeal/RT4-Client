package com.jagex.runetek4.core.io;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.encryption.Isaac;

@OriginalClass("client!i")
public final class PacketBit extends Packet {

	@OriginalMember(owner = "client!i", name = "Xb", descriptor = "Lclient!ij;")
	private Isaac isaac;

	@OriginalMember(owner = "client!bh", name = "G", descriptor = "[I")
	public static final int[] BITMASK = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };

	@OriginalMember(owner = "client!i", name = "fc", descriptor = "I")
	private int bitPos;

	@OriginalMember(owner = "client!i", name = "<init>", descriptor = "(I)V")
	public PacketBit(@OriginalArg(0) int size) {
		super(size);
	}

	@OriginalMember(owner = "client!i", name = "a", descriptor = "([IZ)V")
	public void Isaac(@OriginalArg(0) int[] seed) {
		this.isaac = new Isaac(seed);
	}

	@OriginalMember(owner = "client!i", name = "r", descriptor = "(II)V")
	public void pIsaac1(@OriginalArg(1) int value) {
		this.data[this.pos++] = (byte) (value + this.isaac.takeNextValue());
	}

	@OriginalMember(owner = "client!i", name = "s", descriptor = "(I)I")
	public int gIssac1() {
		return this.data[this.pos++] - this.isaac.takeNextValue() & 0xFF;
	}

	@OriginalMember(owner = "client!i", name = "a", descriptor = "(BI[BI)V")
	public void method2237(@OriginalArg(2) byte[] arg0, @OriginalArg(3) int arg1) {
		for (@Pc(17) int local17 = 0; local17 < arg1; local17++) {
			arg0[local17] = (byte) (this.data[this.pos++] - this.isaac.takeNextValue());
		}
	}

	@OriginalMember(owner = "client!i", name = "q", descriptor = "(B)V")
	public void accessBits() {
		this.bitPos = this.pos * 8;
	}

	@OriginalMember(owner = "client!i", name = "f", descriptor = "(BI)I")
	public int gBit(@OriginalArg(1) int arg0) {
		@Pc(6) int local6 = this.bitPos >> 3;
		@Pc(14) int local14 = 8 - (this.bitPos & 0x7);
		@Pc(16) int local16 = 0;
		this.bitPos += arg0;
		while (local14 < arg0) {
			local16 += (this.data[local6++] & BITMASK[local14]) << arg0 - local14;
			arg0 -= local14;
			local14 = 8;
		}
		if (local14 == arg0) {
			local16 += this.data[local6] & BITMASK[local14];
		} else {
			local16 += this.data[local6] >> local14 - arg0 & BITMASK[arg0];
		}
		return local16;
	}

	@OriginalMember(owner = "client!i", name = "h", descriptor = "(Z)V")
	public void accessBytes() {
		this.pos = (this.bitPos + 7) / 8;
	}

	@OriginalMember(owner = "client!i", name = "q", descriptor = "(II)I")
	public int bitsAvailable(@OriginalArg(0) int arg0) {
		return arg0 * 8 - this.bitPos;
	}
}
