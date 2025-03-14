package com.jagex.runetek4.js5;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.index.Js5Index;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ve")
public final class Js5 {

	@OriginalMember(owner = "client!sh", name = "k", descriptor = "Z")
	public static final boolean RAISE_EXCEPTIONS = false;

	@OriginalMember(owner = "client!kf", name = "j", descriptor = "I")
	public static final int MAX_LENGTH = 0;

	@OriginalMember(owner = "client!ve", name = "A", descriptor = "[[Ljava/lang/Object;")
	private Object[][] unpacked;

	@OriginalMember(owner = "client!ve", name = "M", descriptor = "[Ljava/lang/Object;")
	private Object[] packed;

	@OriginalMember(owner = "client!ve", name = "o", descriptor = "Lclient!ii;")
	private Js5Index index = null;

	@OriginalMember(owner = "client!ve", name = "i", descriptor = "Lclient!v;")
	private final Js5ResourceProvider provider;

	@OriginalMember(owner = "client!ve", name = "c", descriptor = "Z")
	private final boolean aBoolean296;

	@OriginalMember(owner = "client!ve", name = "g", descriptor = "Z")
	private final boolean discardUnpacked;

	@OriginalMember(owner = "client!ve", name = "<init>", descriptor = "(Lclient!v;ZZ)V")
	public Js5(@OriginalArg(0) Js5ResourceProvider arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2) {
		this.provider = arg0;
		this.aBoolean296 = arg1;
		this.discardUnpacked = arg2;
	}

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(B[B)[B")
	public static byte[] uncompress(@OriginalArg(1) byte[] src) {
		@Pc(17) Packet packet = new Packet(src);
		@Pc(21) int type = packet.g1();
		@Pc(25) int len = packet.g4();
		if (len < 0 || MAX_LENGTH != 0 && len > MAX_LENGTH) {
			throw new RuntimeException("ctype=" + type + " clen=" + len + " maxsize=" + MAX_LENGTH);
		} else if (type == 0) {
			@Pc(53) byte[] bytes = new byte[len];
			packet.gdata(len, bytes);
			return bytes;
		} else {
			@Pc(65) int unpackedLength = packet.g4();
			if (unpackedLength < 0 || MAX_LENGTH != 0 && unpackedLength > MAX_LENGTH) {
				throw new RuntimeException("ctype=" + type + " clen=" + len + " ulen=" + unpackedLength + " maxsize=" + MAX_LENGTH);
			}
			@Pc(85) byte[] bytes = new byte[unpackedLength];
			if (type == 1) {
				BZip2.read(bytes, unpackedLength, src, len);
			} else {
				Static156.aClass56_1.method1842(bytes, packet);
			}
			return bytes;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(B)Z")
	public boolean fetchAll() {
		if (!this.isIndexReady()) {
			return false;
		}

		@Pc(20) boolean success = true;
		for (@Pc(22) int index = 0; index < this.index.groupIds.length; index++) {
			@Pc(38) int groupId = this.index.groupIds[index];
			if (this.packed[groupId] == null) {
				this.fetchgroup(groupId);
				if (this.packed[groupId] == null) {
					success = false;
				}
			}
		}
		return success;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(II)I")
	private int getPercentageComplete(@OriginalArg(0) int group) {
		if (this.isGroupValid(group)) {
			return this.packed[group] == null ? this.provider.getPercentageComplete(group) : 100;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(IZZ)V")
	public void method4477(@OriginalArg(1) boolean arg0) {
		if (!this.isIndexReady()) {
			return;
		}
		if (arg0) {
			this.index.anIntArray271 = null;
			this.index.aClass76_1 = null;
		}
		this.index.aClass76Array1 = null;
		this.index.anIntArrayArray21 = null;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(ILclient!na;)I")
	public int method4478(@OriginalArg(1) JString arg0) {
		if (this.isIndexReady()) {
			@Pc(15) JString local15 = arg0.toLowerCase();
			@Pc(29) int local29 = this.index.aClass76_1.method2405(local15.method3154());
			return this.getPercentageComplete(local29);
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(II)Z")
	public boolean method4479(@OriginalArg(1) int arg0) {
		if (!this.isGroupValid(arg0)) {
			return false;
		} else if (this.packed[arg0] == null) {
			this.fetchgroup(arg0);
			return this.packed[arg0] != null;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(B)I")
	public int getChecksum() {
		if (!this.isIndexReady()) {
			throw new IllegalStateException("");
		}
		return this.index.crc;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(Lclient!na;B)I")
	public int method4482(@OriginalArg(0) JString arg0) {
		if (this.isIndexReady()) {
			@Pc(16) JString local16 = arg0.toLowerCase();
			@Pc(27) int local27 = this.index.aClass76_1.method2405(local16.method3154());
			return this.isGroupValid(local27) ? local27 : -1;
		} else {
			return -1;
		}
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(I)I")
	public int capacity() {
		return this.isIndexReady() ? this.index.groupCapacities.length : -1;
	}

	@OriginalMember(owner = "client!ve", name = "c", descriptor = "(I)Z")
	private boolean isIndexReady() {
		if (this.index == null) {
			this.index = this.provider.fetchIndex();
			if (this.index == null) {
				return false;
			}
			this.unpacked = new Object[this.index.capacity][];
			this.packed = new Object[this.index.capacity];
		}
		return true;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(ILclient!na;Lclient!na;)[B")
	public byte[] method4485(@OriginalArg(1) JString arg0, @OriginalArg(2) JString arg1) {
		if (!this.isIndexReady()) {
			return null;
		}
		@Pc(15) JString local15 = arg1.toLowerCase();
		@Pc(19) JString local19 = arg0.toLowerCase();
		@Pc(28) int local28 = this.index.aClass76_1.method2405(local15.method3154());
		if (this.isGroupValid(local28)) {
			@Pc(53) int local53 = this.index.aClass76Array1[local28].method2405(local19.method3154());
			return this.getfile(local28, local53);
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(ILclient!na;)V")
	public void method4486(@OriginalArg(1) JString arg0) {
		if (this.isIndexReady()) {
			@Pc(13) JString local13 = arg0.toLowerCase();
			@Pc(22) int local22 = this.index.aClass76_1.method2405(local13.method3154());
			this.method4493(local22);
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(Lclient!na;BLclient!na;)Z")
	public boolean method4487(@OriginalArg(0) JString arg0, @OriginalArg(2) JString arg1) {
		if (!this.isIndexReady()) {
			return false;
		}
		@Pc(17) JString local17 = arg1.toLowerCase();
		@Pc(21) JString local21 = arg0.toLowerCase();
		@Pc(30) int local30 = this.index.aClass76_1.method2405(local17.method3154());
		if (this.isGroupValid(local30)) {
			@Pc(49) int local49 = this.index.aClass76Array1[local30].method2405(local21.method3154());
			return this.requestDownload(local30, local49);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(I[III)[B")
	public byte[] getfile(@OriginalArg(0) int group, @OriginalArg(3) int file, @OriginalArg(1) int[] key) {
		if (!this.isGroupValid(group, file)) {
			return null;
		}
		if (this.unpacked[group] == null || this.unpacked[group][file] == null) {
			@Pc(30) boolean success = this.unpackGroup(group, key);
			if (!success) {
				this.fetchgroup(group);
				success = this.unpackGroup(group, key);
				if (!success) {
					return null;
				}
			}
		}
		@Pc(64) byte[] bytes = Static138.method2696(this.unpacked[group][file], false);
		if (this.discardUnpacked) {
			this.unpacked[group][file] = null;
			if (this.index.groupCapacities[group] == 1) {
				this.unpacked[group] = null;
			}
		}
		return bytes;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(BLclient!na;)Z")
	public boolean method4489(@OriginalArg(1) JString arg0) {
		if (this.isIndexReady()) {
			@Pc(14) JString local14 = arg0.toLowerCase();
			@Pc(25) int local25 = this.index.aClass76_1.method2405(local14.method3154());
			return this.method4479(local25);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ve", name = "c", descriptor = "(II)V")
	public void unloadFile(@OriginalArg(1) int arg0) {
		if (this.isGroupValid(arg0) && this.unpacked != null) {
			this.unpacked[arg0] = null;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(BII)Z")
	public boolean requestDownload(@OriginalArg(2) int group, @OriginalArg(1) int file) {
		if (!this.isGroupValid(group, file)) {
			return false;
		} else if (this.unpacked[group] != null && this.unpacked[group][file] != null) {
			return true;
		} else if (this.packed[group] == null) {
			this.fetchgroup(group);
			return this.packed[group] != null;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(ZI)Z")
	private boolean isGroupValid(@OriginalArg(1) int arg0) {
		if (!this.isIndexReady()) {
			return false;
		} else if (arg0 >= 0 && this.index.groupCapacities.length > arg0 && this.index.groupCapacities[arg0] != 0) {
			return true;
		} else if (RAISE_EXCEPTIONS) {
			throw new IllegalArgumentException(Integer.toString(arg0));
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ve", name = "d", descriptor = "(II)V")
	private void method4493(@OriginalArg(1) int arg0) {
		this.provider.method522(arg0);
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(IZ[I)Z")
	private boolean unpackGroup(@OriginalArg(0) int group, @OriginalArg(2) int[] key) {
		if (!this.isGroupValid(group)) {
			return false;
		} else if (this.packed[group] == null) {
			return false;
		} else {
			@Pc(25) int[] local25 = this.index.anIntArrayArray22[group];
			@Pc(31) int local31 = this.index.groupSizes[group];

			if (this.unpacked[group] == null) {
				this.unpacked[group] = new Object[this.index.groupCapacities[group]];
			}

			@Pc(48) boolean local48 = true;
			@Pc(53) Object[] local53 = this.unpacked[group];
			for (@Pc(55) int local55 = 0; local55 < local31; local55++) {
				@Pc(62) int local62;
				if (local25 == null) {
					local62 = local55;
				} else {
					local62 = local25[local55];
				}
				if (local53[local62] == null) {
					local48 = false;
					break;
				}
			}
			if (local48) {
				return true;
			}
			@Pc(114) byte[] local114;
			if (key == null || key[0] == 0 && key[1] == 0 && key[2] == 0 && key[3] == 0) {
				local114 = Static138.method2696(this.packed[group], false);
			} else {
				local114 = Static138.method2696(this.packed[group], true);
				@Pc(128) Packet packet = new Packet(local114);
				packet.decryptXtea(key, packet.data.length);
			}

			@Pc(140) byte[] bytes;
			try {
				bytes = uncompress(local114);
			} catch (@Pc(142) RuntimeException local142) {
				System.out.println("T3 - " + (key != null) + "," + group + "," + local114.length + "," + Packet.getcrc(local114, local114.length) + "," + Packet.getcrc(local114, local114.length - 2) + "," + this.index.anIntArray268[group] + "," + this.index.crc);
				bytes = new byte[] { 0 };
			}
			if (this.aBoolean296) {
				this.packed[group] = null;
			}
			@Pc(213) int local213;
			if (local31 > 1) {
				local213 = bytes.length;
				@Pc(216) int local216 = local213 - 1;
				@Pc(220) int local220 = bytes[local216] & 0xFF;
				@Pc(228) int local228 = local216 - local31 * local220 * 4;
				@Pc(233) Packet local233 = new Packet(bytes);
				local233.position = local228;
				@Pc(239) int[] local239 = new int[local31];
				@Pc(250) int local250;
				@Pc(252) int local252;
				for (@Pc(241) int local241 = 0; local241 < local220; local241++) {
					local250 = 0;
					for (local252 = 0; local252 < local31; local252++) {
						local250 += local233.g4();
						if (local25 == null) {
						}
						local239[local252] += local250;
					}
				}
				@Pc(282) byte[][] local282 = new byte[local31][];
				for (local250 = 0; local250 < local31; local250++) {
					local282[local250] = new byte[local239[local250]];
					local239[local250] = 0;
				}
				local233.position = local228;
				local250 = 0;
				@Pc(320) int local320;
				for (local252 = 0; local252 < local220; local252++) {
					local320 = 0;
					for (@Pc(322) int local322 = 0; local322 < local31; local322++) {
						local320 += local233.g4();
						Static289.method2612(bytes, local250, local282[local322], local239[local322], local320);
						local250 += local320;
						local239[local322] += local320;
					}
				}
				for (local252 = 0; local252 < local31; local252++) {
					if (local25 == null) {
						local320 = local252;
					} else {
						local320 = local25[local252];
					}
					if (this.discardUnpacked) {
						local53[local320] = local282[local252];
					} else {
						local53[local320] = Static33.method869(local282[local252]);
					}
				}
			} else {
				if (local25 == null) {
					local213 = 0;
				} else {
					local213 = local25[0];
				}
				if (this.discardUnpacked) {
					local53[local213] = bytes;
				} else {
					local53[local213] = Static33.method869(bytes);
				}
			}
			return true;
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(IBI)[B")
	public byte[] getfile(@OriginalArg(0) int group, @OriginalArg(2) int file) {
		return this.getfile(group, file, null);
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(ZI)V")
	private void fetchgroup(@OriginalArg(1) int arg0) {
		if (this.aBoolean296) {
			this.packed[arg0] = this.provider.fetchgroup(arg0);
		} else {
			this.packed[arg0] = Static33.method869(this.provider.fetchgroup(arg0));
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(Lclient!na;I)Z")
	public boolean method4497(@OriginalArg(0) JString arg0) {
		if (this.isIndexReady()) {
			@Pc(19) JString local19 = arg0.toLowerCase();
			@Pc(28) int local28 = this.index.aClass76_1.method2405(local19.method3154());
			return local28 >= 0;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ve", name = "c", descriptor = "(B)I")
	public int getPercentageComplete() {
		if (!this.isIndexReady()) {
			return 0;
		}

		@Pc(18) int total = 0;
		@Pc(20) int complete = 0;

		for (@Pc(22) int index = 0; index < this.packed.length; index++) {
			if (this.index.groupSizes[index] > 0) {
				total += 100;
				complete += this.getPercentageComplete(index);
			}
		}
		if (total == 0) {
			return 100;
		} else {
			return complete * 100 / total;
		}
	}

	@OriginalMember(owner = "client!ve", name = "d", descriptor = "(B)V")
	public void method4499() {
		if (this.unpacked != null) {
			for (@Pc(17) int local17 = 0; local17 < this.unpacked.length; local17++) {
				this.unpacked[local17] = null;
			}
		}
	}

	@OriginalMember(owner = "client!ve", name = "e", descriptor = "(II)[B")
	public byte[] method4500(@OriginalArg(0) int arg0) {
		if (!this.isIndexReady()) {
			return null;
		} else if (this.index.groupCapacities.length == 1) {
			return this.getfile(0, arg0);
		} else if (!this.isGroupValid(arg0)) {
			return null;
		} else if (this.index.groupCapacities[arg0] == 1) {
			return this.getfile(arg0, 0);
		} else {
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(III)Z")
	private boolean isGroupValid(@OriginalArg(0) int group, @OriginalArg(2) int file) {
		if (!this.isIndexReady()) {
			return false;
		} else if (group >= 0 && file >= 0 && group < this.index.groupCapacities.length && this.index.groupCapacities[group] > file) {
			return true;
		} else if (RAISE_EXCEPTIONS) {
			throw new IllegalArgumentException(group + "," + file);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ve", name = "b", descriptor = "(III)[B")
	public byte[] method4502(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (!this.isGroupValid(arg1, arg0)) {
			return null;
		}
		if (this.unpacked[arg1] == null || this.unpacked[arg1][arg0] == null) {
			@Pc(34) boolean local34 = this.unpackGroup(arg1, null);
			if (!local34) {
				this.fetchgroup(arg1);
				local34 = this.unpackGroup(arg1, null);
				if (!local34) {
					return null;
				}
			}
		}
		return Static138.method2696(this.unpacked[arg1][arg0], false);
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(BI)[I")
	public int[] method4503(@OriginalArg(1) int arg0) {
		if (!this.isGroupValid(arg0)) {
			return null;
		}
		@Pc(22) int[] local22 = this.index.anIntArrayArray22[arg0];
		if (local22 == null) {
			local22 = new int[this.index.groupSizes[arg0]];
			@Pc(34) int local34 = 0;
			while (local22.length > local34) {
				local22[local34] = local34++;
			}
		}
		return local22;
	}

	@OriginalMember(owner = "client!ve", name = "a", descriptor = "(IB)I")
	public int fileLength(@OriginalArg(0) int arg0) {
		return this.isGroupValid(arg0) ? this.index.groupCapacities[arg0] : 0;
	}

	@OriginalMember(owner = "client!ve", name = "f", descriptor = "(II)Z")
	public boolean method4506(@OriginalArg(1) int arg0) {
		if (!this.isIndexReady()) {
			return false;
		} else if (this.index.groupCapacities.length == 1) {
			return this.requestDownload(0, arg0);
		} else if (!this.isGroupValid(arg0)) {
			return false;
		} else if (this.index.groupCapacities[arg0] == 1) {
			return this.requestDownload(arg0, 0);
		} else {
			throw new RuntimeException();
		}
	}
}
