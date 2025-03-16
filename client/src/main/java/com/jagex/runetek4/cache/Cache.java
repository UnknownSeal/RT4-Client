package com.jagex.runetek4.cache;

import java.io.EOFException;
import java.io.IOException;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ge")
public final class Cache {

	@OriginalMember(owner = "runetek4.client!wc", name = "i", descriptor = "[B")
	public static final byte[] buffer = new byte[520];

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "Lclient!en;")
	private BufferedFile data = null;

	@OriginalMember(owner = "client!ge", name = "f", descriptor = "Lclient!en;")
	private BufferedFile index = null;

	@OriginalMember(owner = "client!ge", name = "l", descriptor = "I")
	private int maxLength = 65000;

	@OriginalMember(owner = "client!ge", name = "c", descriptor = "I")
	private final int archive;

	@OriginalMember(owner = "client!ge", name = "<init>", descriptor = "(ILclient!en;Lclient!en;I)V")
	public Cache(@OriginalArg(0) int archive, @OriginalArg(1) BufferedFile data, @OriginalArg(2) BufferedFile index, @OriginalArg(3) int maxLength) {
		this.maxLength = maxLength;
		this.index = index;
		this.archive = archive;
		this.data = data;
	}

	@OriginalMember(owner = "client!ge", name = "toString", descriptor = "()Ljava/lang/String;")
	@Override
	public String toString() {
		return "Cache:" + this.archive;
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(II[BB)Z")
	public boolean put(@OriginalArg(0) int arg0, @OriginalArg(1) int fileSize, @OriginalArg(2) byte[] arg2) {
		@Pc(7) BufferedFile local7 = this.data;
		synchronized (this.data) {
			if (fileSize < 0 || fileSize > this.maxLength) {
				throw new IllegalArgumentException();
			}
			@Pc(35) boolean bool = this.put(fileSize, arg0, arg2, true);
			if (!bool) {
				bool = this.put(fileSize, arg0, arg2, false);
			}
			return bool;
		}
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(IB)[B")
	public byte[] get(@OriginalArg(0) int index) {
		@Pc(9) BufferedFile local9 = this.data;
		synchronized (this.data) {
			try {
				@Pc(27) Object local27;
				if (this.index.length() < (long) (index * 6L + 6)) {
					local27 = null;
					return (byte[]) local27;
				}
				this.index.seek(index * 6L);
				this.index.read(0, buffer, 6);
				@Pc(69) int fileBlock = ((buffer[3] & 0xFF) << 16) - (-((buffer[4] & 0xFF) << 8) - (buffer[5] & 0xFF));
				@Pc(99) int fileSize = (buffer[2] & 0xFF) + ((buffer[1] & 0xFF) << 8) + ((buffer[0] & 0xFF) << 16);
				if (fileSize < 0 || this.maxLength < fileSize) {
					local27 = null;
					return (byte[]) local27;
				} else if (fileBlock <= 0 || (long) fileBlock > this.data.length() / 520L) {
					local27 = null;
					return (byte[]) local27;
				} else {
					@Pc(134) byte[] local134 = new byte[fileSize];
					@Pc(136) int read = 0;
					@Pc(138) int local138 = 0;
					while (read < fileSize) {
						if (fileBlock == 0) {
							local27 = null;
							return (byte[]) local27;
						}
						@Pc(157) int remaining = fileSize - read;
						this.data.seek(fileBlock * 520L);
						if (remaining > 512) {
							remaining = 512;
						}
						this.data.read(0, buffer, remaining + 8);
						@Pc(197) int local197 = ((buffer[0] & 0xFF) << 8) + (buffer[1] & 0xFF);
						@Pc(211) int local211 = (buffer[3] & 0xFF) + ((buffer[2] & 0xFF) << 8);
						@Pc(217) int local217 = buffer[7] & 0xFF;
						@Pc(239) int local239 = (buffer[6] & 0xFF) + ((buffer[5] & 0xFF) << 8) + ((buffer[4] & 0xFF) << 16);
						if (index != local197 || local138 != local211 || this.archive != local217) {
							local27 = null;
							return (byte[]) local27;
						}
						if (local239 < 0 || (long) local239 > this.data.length() / 520L) {
							local27 = null;
							return (byte[]) local27;
						}
						for (@Pc(272) int local272 = 0; local272 < remaining; local272++) {
							local134[read++] = buffer[local272 + 8];
						}
						local138++;
						fileBlock = local239;
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
	private boolean put(@OriginalArg(1) int arg0, @OriginalArg(2) int index, @OriginalArg(3) byte[] arg2, @OriginalArg(4) boolean arg3) {
		@Pc(9) BufferedFile local9 = this.data;
		synchronized (this.data) {
			try {
				@Pc(67) int sector;
				@Pc(27) boolean local27;
				if (arg3) {
					if (this.index.length() < (long) (index * 6L + 6)) {
						local27 = false;
						return local27;
					}
					this.index.seek(index * 6L);
					this.index.read(0, buffer, 6);
					sector = ((buffer[3] & 0xFF) << 16) + (buffer[4] << 8 & 0xFF00) + (buffer[5] & 0xFF);
					if (sector <= 0 || this.data.length() / 520L < (long) sector) {
						local27 = false;
						return local27;
					}
				} else {
					sector = (int) ((this.data.length() + 519L) / 520L);
					if (sector == 0) {
						sector = 1;
					}
				}
				buffer[0] = (byte) (arg0 >> 16);
				buffer[4] = (byte) (sector >> 8);
				@Pc(125) int local125 = 0;
				buffer[5] = (byte) sector;
				buffer[2] = (byte) arg0;
				buffer[3] = (byte) (sector >> 16);
				@Pc(156) int local156 = 0;
				buffer[1] = (byte) (arg0 >> 8);
				this.index.seek(index * 6L);
				this.index.write(buffer, 0, 6);
				while (true) {
					if (local125 < arg0) {
						label134: {
							@Pc(189) int local189 = 0;
							@Pc(248) int local248;
							if (arg3) {
								this.data.seek(sector * 520L);
								try {
									this.data.read(0, buffer, 8);
								} catch (@Pc(209) EOFException local209) {
									break label134;
								}
								local189 = ((buffer[4] & 0xFF) << 16) + ((buffer[5] & 0xFF) << 8) + (buffer[6] & 0xFF);
								local248 = (buffer[1] & 0xFF) + ((buffer[0] & 0xFF) << 8);
								@Pc(254) int local254 = buffer[7] & 0xFF;
								@Pc(268) int local268 = (buffer[3] & 0xFF) + ((buffer[2] & 0xFF) << 8);
								if (local248 != index || local156 != local268 || this.archive != local254) {
									local27 = false;
									return local27;
								}
								if (local189 < 0 || (long) local189 > this.data.length() / 520L) {
									local27 = false;
									return local27;
								}
							}
							local248 = arg0 - local125;
							if (local189 == 0) {
								arg3 = false;
								local189 = (int) ((this.data.length() + 519L) / 520L);
								if (local189 == 0) {
									local189++;
								}
								if (local189 == sector) {
									local189++;
								}
							}
							buffer[7] = (byte) this.archive;
							buffer[0] = (byte) (index >> 8);
							if (arg0 - local125 <= 512) {
								local189 = 0;
							}
							buffer[4] = (byte) (local189 >> 16);
							if (local248 > 512) {
								local248 = 512;
							}
							buffer[1] = (byte) index;
							buffer[6] = (byte) local189;
							buffer[2] = (byte) (local156 >> 8);
							buffer[3] = (byte) local156;
							local156++;
							buffer[5] = (byte) (local189 >> 8);
							this.data.seek(sector * 520L);
							sector = local189;
							this.data.write(buffer, 0, 8);
							this.data.write(arg2, local125, local248);
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
