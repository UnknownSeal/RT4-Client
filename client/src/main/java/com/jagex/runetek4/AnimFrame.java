package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ne")
public final class AnimFrame {

	@OriginalMember(owner = "runetek4.client!ne", name = "p", descriptor = "[S")
	public static final short[] tempIndices = new short[500];

	@OriginalMember(owner = "runetek4.client!ne", name = "m", descriptor = "[S")
	public static final short[] tempX = new short[500];

	@OriginalMember(owner = "runetek4.client!ne", name = "j", descriptor = "[S")
	public static final short[] tempY = new short[500];

	@OriginalMember(owner = "runetek4.client!ne", name = "i", descriptor = "[S")
	public static final short[] tempZ = new short[500];

	@OriginalMember(owner = "runetek4.client!ne", name = "h", descriptor = "[B")
	public static final byte[] tempFlags = new byte[500];

	@OriginalMember(owner = "runetek4.client!ne", name = "k", descriptor = "[S")
	public static final short[] tempPrevOriginIndices = new short[500];

	@OriginalMember(owner = "client!ne", name = "c", descriptor = "Lclient!jm;")
	public AnimBase animBase = null;

	@OriginalMember(owner = "client!ne", name = "b", descriptor = "I")
	public int length = -1;

	@OriginalMember(owner = "client!ne", name = "e", descriptor = "Z")
	public boolean transformsAlpha = false;

	@OriginalMember(owner = "client!ne", name = "a", descriptor = "Z")
	public boolean transformsColor = false;

	@OriginalMember(owner = "client!ne", name = "d", descriptor = "[S")
	public final short[] indices;

	@OriginalMember(owner = "client!ne", name = "g", descriptor = "[S")
	public final short[] x;

	@OriginalMember(owner = "client!ne", name = "o", descriptor = "[S")
	public final short[] y;

	@OriginalMember(owner = "client!ne", name = "n", descriptor = "[S")
	public final short[] z;

	@OriginalMember(owner = "client!ne", name = "f", descriptor = "[S")
	public final short[] prevOriginIndices;

	@OriginalMember(owner = "client!ne", name = "l", descriptor = "[B")
	public final byte[] flags;

	@OriginalMember(owner = "client!ne", name = "<init>", descriptor = "([BLclient!jm;)V")
	public AnimFrame(@OriginalArg(0) byte[] arg0, @OriginalArg(1) AnimBase arg1) {
		this.animBase = arg1;
		@Pc(21) Packet local21 = new Packet(arg0);
		@Pc(26) Packet local26 = new Packet(arg0);
		local21.offset = 2;
		@Pc(33) int local33 = local21.g1();
		@Pc(35) int local35 = 0;
		@Pc(37) int local37 = -1;
		@Pc(39) int local39 = -1;
		local26.offset = local21.offset + local33;
		@Pc(47) int local47;
		for (local47 = 0; local47 < local33; local47++) {
			@Pc(56) int local56 = this.animBase.types[local47];
			if (local56 == 0) {
				local37 = local47;
			}
			@Pc(64) int local64 = local21.g1();
			if (local64 > 0) {
				if (local56 == 0) {
					local39 = local47;
				}
				tempIndices[local35] = (short) local47;
				@Pc(77) short local77 = 0;
				if (local56 == 3) {
					local77 = 128;
				}
				if ((local64 & 0x1) == 0) {
					tempX[local35] = local77;
				} else {
					tempX[local35] = (short) local26.gSmart1or2s();
				}
				if ((local64 & 0x2) == 0) {
					tempY[local35] = local77;
				} else {
					tempY[local35] = (short) local26.gSmart1or2s();
				}
				if ((local64 & 0x4) == 0) {
					tempZ[local35] = local77;
				} else {
					tempZ[local35] = (short) local26.gSmart1or2s();
				}
				tempFlags[local35] = (byte) (local64 >>> 3 & 0x3);
				if (local56 == 2) {
					tempX[local35] = (short) (((tempX[local35] & 0xFF) << 3) + (tempX[local35] >> 8 & 0x7));
					tempY[local35] = (short) (((tempY[local35] & 0xFF) << 3) + (tempY[local35] >> 8 & 0x7));
					tempZ[local35] = (short) (((tempZ[local35] & 0xFF) << 3) + (tempZ[local35] >> 8 & 0x7));
				}
				tempPrevOriginIndices[local35] = -1;
				if (local56 == 1 || local56 == 2 || local56 == 3) {
					if (local37 > local39) {
						tempPrevOriginIndices[local35] = (short) local37;
						local39 = local37;
					}
				} else if (local56 == 5) {
					this.transformsAlpha = true;
				} else if (local56 == 7) {
					this.transformsColor = true;
				}
				local35++;
			}
		}
		if (local26.offset != arg0.length) {
			throw new RuntimeException();
		}
		this.length = local35;
		this.indices = new short[local35];
		this.x = new short[local35];
		this.y = new short[local35];
		this.z = new short[local35];
		this.prevOriginIndices = new short[local35];
		this.flags = new byte[local35];
		for (local47 = 0; local47 < local35; local47++) {
			this.indices[local47] = tempIndices[local47];
			this.x[local47] = tempX[local47];
			this.y[local47] = tempY[local47];
			this.z[local47] = tempZ[local47];
			this.prevOriginIndices[local47] = tempPrevOriginIndices[local47];
			this.flags[local47] = tempFlags[local47];
		}
	}
}
