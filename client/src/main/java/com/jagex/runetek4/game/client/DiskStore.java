package com.jagex.runetek4.game.client;

import java.io.EOFException;
import java.io.IOException;

import com.jagex.runetek4.BufferedFile;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ge")
public final class DiskStore {

	@OriginalMember(owner = "runetek4.client!wc", name = "i", descriptor = "[B")
	public static final byte[] aByteArray79 = new byte[520];
	@OriginalMember(owner = "client!ge", name = "a", descriptor = "Lclient!en;")
	private BufferedFile aClass38_1 = null;

	@OriginalMember(owner = "client!ge", name = "f", descriptor = "Lclient!en;")
	private BufferedFile aClass38_2 = null;

	@OriginalMember(owner = "client!ge", name = "l", descriptor = "I")
	private int anInt2156 = 65000;

	@OriginalMember(owner = "client!ge", name = "c", descriptor = "I")
	private final int anInt2150;

	@OriginalMember(owner = "client!ge", name = "<init>", descriptor = "(ILclient!en;Lclient!en;I)V")
	public DiskStore(@OriginalArg(0) int arg0, @OriginalArg(1) BufferedFile arg1, @OriginalArg(2) BufferedFile arg2, @OriginalArg(3) int arg3) {
		this.anInt2156 = arg3;
		this.aClass38_2 = arg2;
		this.anInt2150 = arg0;
		this.aClass38_1 = arg1;
	}

	@OriginalMember(owner = "client!ge", name = "toString", descriptor = "()Ljava/lang/String;")
	@Override
	public String toString() {
		return "Cache:" + this.anInt2150;
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(II[BB)Z")
	public boolean method1700(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) byte[] arg2) {
		@Pc(7) BufferedFile local7 = this.aClass38_1;
		synchronized (this.aClass38_1) {
			if (arg1 < 0 || arg1 > this.anInt2156) {
				throw new IllegalArgumentException();
			}
			@Pc(35) boolean local35 = this.method1704(arg1, arg0, arg2, true);
			if (!local35) {
				local35 = this.method1704(arg1, arg0, arg2, false);
			}
			return local35;
		}
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(IB)[B")
	public byte[] method1701(@OriginalArg(0) int arg0) {
		@Pc(9) BufferedFile local9 = this.aClass38_1;
		synchronized (this.aClass38_1) {
			try {
				@Pc(27) Object local27;
				if (this.aClass38_2.method1451() < (long) (arg0 * 6L + 6)) {
					local27 = null;
					return (byte[]) local27;
				}
				this.aClass38_2.method1459(arg0 * 6L);
				this.aClass38_2.method1453(0, aByteArray79, 6);
				@Pc(69) int local69 = ((aByteArray79[3] & 0xFF) << 16) - (-((aByteArray79[4] & 0xFF) << 8) - (aByteArray79[5] & 0xFF));
				@Pc(99) int local99 = (aByteArray79[2] & 0xFF) + ((aByteArray79[1] & 0xFF) << 8) + ((aByteArray79[0] & 0xFF) << 16);
				if (local99 < 0 || this.anInt2156 < local99) {
					local27 = null;
					return (byte[]) local27;
				} else if (local69 <= 0 || (long) local69 > this.aClass38_1.method1451() / 520L) {
					local27 = null;
					return (byte[]) local27;
				} else {
					@Pc(134) byte[] local134 = new byte[local99];
					@Pc(136) int local136 = 0;
					@Pc(138) int local138 = 0;
					while (local136 < local99) {
						if (local69 == 0) {
							local27 = null;
							return (byte[]) local27;
						}
						@Pc(157) int local157 = local99 - local136;
						this.aClass38_1.method1459(local69 * 520L);
						if (local157 > 512) {
							local157 = 512;
						}
						this.aClass38_1.method1453(0, aByteArray79, local157 + 8);
						@Pc(197) int local197 = ((aByteArray79[0] & 0xFF) << 8) + (aByteArray79[1] & 0xFF);
						@Pc(211) int local211 = (aByteArray79[3] & 0xFF) + ((aByteArray79[2] & 0xFF) << 8);
						@Pc(217) int local217 = aByteArray79[7] & 0xFF;
						@Pc(239) int local239 = (aByteArray79[6] & 0xFF) + ((aByteArray79[5] & 0xFF) << 8) + ((aByteArray79[4] & 0xFF) << 16);
						if (arg0 != local197 || local138 != local211 || this.anInt2150 != local217) {
							local27 = null;
							return (byte[]) local27;
						}
						if (local239 < 0 || (long) local239 > this.aClass38_1.method1451() / 520L) {
							local27 = null;
							return (byte[]) local27;
						}
						for (@Pc(272) int local272 = 0; local272 < local157; local272++) {
							local134[local136++] = aByteArray79[local272 + 8];
						}
						local138++;
						local69 = local239;
					}
					@Pc(297) byte[] local297 = local134;
					return local297;
				}
			} catch (@Pc(301) IOException local301) {
				return null;
			}
		}
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(BII[BZ)Z")
	private boolean method1704(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) byte[] arg2, @OriginalArg(4) boolean arg3) {
		@Pc(9) BufferedFile local9 = this.aClass38_1;
		synchronized (this.aClass38_1) {
			try {
				@Pc(67) int local67;
				@Pc(27) boolean local27;
				if (arg3) {
					if (this.aClass38_2.method1451() < (long) (arg1 * 6L + 6)) {
						local27 = false;
						return local27;
					}
					this.aClass38_2.method1459(arg1 * 6L);
					this.aClass38_2.method1453(0, aByteArray79, 6);
					local67 = ((aByteArray79[3] & 0xFF) << 16) + (aByteArray79[4] << 8 & 0xFF00) + (aByteArray79[5] & 0xFF);
					if (local67 <= 0 || this.aClass38_1.method1451() / 520L < (long) local67) {
						local27 = false;
						return local27;
					}
				} else {
					local67 = (int) ((this.aClass38_1.method1451() + 519L) / 520L);
					if (local67 == 0) {
						local67 = 1;
					}
				}
				aByteArray79[0] = (byte) (arg0 >> 16);
				aByteArray79[4] = (byte) (local67 >> 8);
				@Pc(125) int local125 = 0;
				aByteArray79[5] = (byte) local67;
				aByteArray79[2] = (byte) arg0;
				aByteArray79[3] = (byte) (local67 >> 16);
				@Pc(156) int local156 = 0;
				aByteArray79[1] = (byte) (arg0 >> 8);
				this.aClass38_2.method1459(arg1 * 6L);
				this.aClass38_2.method1458(aByteArray79, 0, 6);
				while (true) {
					if (local125 < arg0) {
						label134: {
							@Pc(189) int local189 = 0;
							@Pc(248) int local248;
							if (arg3) {
								this.aClass38_1.method1459(local67 * 520L);
								try {
									this.aClass38_1.method1453(0, aByteArray79, 8);
								} catch (@Pc(209) EOFException local209) {
									break label134;
								}
								local189 = ((aByteArray79[4] & 0xFF) << 16) + ((aByteArray79[5] & 0xFF) << 8) + (aByteArray79[6] & 0xFF);
								local248 = (aByteArray79[1] & 0xFF) + ((aByteArray79[0] & 0xFF) << 8);
								@Pc(254) int local254 = aByteArray79[7] & 0xFF;
								@Pc(268) int local268 = (aByteArray79[3] & 0xFF) + ((aByteArray79[2] & 0xFF) << 8);
								if (local248 != arg1 || local156 != local268 || this.anInt2150 != local254) {
									local27 = false;
									return local27;
								}
								if (local189 < 0 || (long) local189 > this.aClass38_1.method1451() / 520L) {
									local27 = false;
									return local27;
								}
							}
							local248 = arg0 - local125;
							if (local189 == 0) {
								arg3 = false;
								local189 = (int) ((this.aClass38_1.method1451() + 519L) / 520L);
								if (local189 == 0) {
									local189++;
								}
								if (local189 == local67) {
									local189++;
								}
							}
							aByteArray79[7] = (byte) this.anInt2150;
							aByteArray79[0] = (byte) (arg1 >> 8);
							if (arg0 - local125 <= 512) {
								local189 = 0;
							}
							aByteArray79[4] = (byte) (local189 >> 16);
							if (local248 > 512) {
								local248 = 512;
							}
							aByteArray79[1] = (byte) arg1;
							aByteArray79[6] = (byte) local189;
							aByteArray79[2] = (byte) (local156 >> 8);
							aByteArray79[3] = (byte) local156;
							local156++;
							aByteArray79[5] = (byte) (local189 >> 8);
							this.aClass38_1.method1459(local67 * 520L);
							local67 = local189;
							this.aClass38_1.method1458(aByteArray79, 0, 8);
							this.aClass38_1.method1458(arg2, local125, local248);
							local125 += local248;
							continue;
						}
					}
					local27 = true;
					return local27;
				}
			} catch (@Pc(453) IOException local453) {
				return false;
			}
		}
	}
}
