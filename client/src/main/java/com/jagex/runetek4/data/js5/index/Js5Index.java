package com.jagex.runetek4.data.js5.index;

import com.jagex.runetek4.data.cache.IntHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.data.js5.Js5Compression;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ii")
public final class Js5Index {

	@OriginalMember(owner = "runetek4.client!ii", name = "b", descriptor = "[[I")
	public int[][] fileNameHashes;

	@OriginalMember(owner = "runetek4.client!ii", name = "d", descriptor = "[I")
	public int[] groupChecksums;

	@OriginalMember(owner = "runetek4.client!ii", name = "f", descriptor = "I")
	public int length;

	@OriginalMember(owner = "runetek4.client!ii", name = "h", descriptor = "Lclient!jg;")
	public IntHashTable groupNameHashTable;

	@OriginalMember(owner = "runetek4.client!ii", name = "m", descriptor = "[I")
	public int[] groupIds;

	@OriginalMember(owner = "runetek4.client!ii", name = "n", descriptor = "[I")
	public int[] groupCapacities;

	@OriginalMember(owner = "runetek4.client!ii", name = "o", descriptor = "[I")
	public int[] groupNameHashes;

	@OriginalMember(owner = "runetek4.client!ii", name = "p", descriptor = "[I")
	public int[] groupSizes;

	@OriginalMember(owner = "runetek4.client!ii", name = "r", descriptor = "[I")
	public int[] groupVersions;

	@OriginalMember(owner = "runetek4.client!ii", name = "s", descriptor = "[[I")
	public int[][] fileIds;

	@OriginalMember(owner = "runetek4.client!ii", name = "u", descriptor = "I")
	public int capacity;

	@OriginalMember(owner = "runetek4.client!ii", name = "v", descriptor = "I")
	public int indexversion;

	@OriginalMember(owner = "runetek4.client!ii", name = "x", descriptor = "[Lclient!jg;")
	public IntHashTable[] fileNameHashTables;

	@OriginalMember(owner = "runetek4.client!ii", name = "z", descriptor = "I")
	public final int crc;

	@OriginalMember(owner = "runetek4.client!ii", name = "<init>", descriptor = "([BI)V")
	public Js5Index(@OriginalArg(0) byte[] bytes, @OriginalArg(1) int crc) {
		this.crc = Packet.crc32(bytes, bytes.length);
		if (this.crc != crc) {
			throw new RuntimeException("Invalid CRC - expected:" + crc + " got:" + this.crc);
		}
		this.decodeArchive(bytes);
	}

	@OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "(I[B)V")
	private void decodeArchive(@OriginalArg(1) byte[] arg0) {
		@Pc(12) Packet packet = new Packet(Js5Compression.uncompress(arg0));
		@Pc(16) int protocol = packet.g1();

		if (protocol != 5 && protocol != 6) {
			throw new RuntimeException("Incorrect JS5 protocol number: " + protocol);
		}

		if (protocol >= 6) {
			this.indexversion = packet.g4();
		} else {
			this.indexversion = 0;
		}

		@Pc(48) int info = packet.g1();
		@Pc(50) int local50 = 0;
		@Pc(59) int local59 = -1;

		this.length = packet.g2();
		this.groupIds = new int[this.length];

		for (int index = 0; index < this.length; index++) {
			this.groupIds[index] = local50 += packet.g2();
			if (this.groupIds[index] > local59) {
				local59 = this.groupIds[index];
			}
		}
		this.capacity = local59 + 1;
		this.groupVersions = new int[this.capacity];
		this.fileIds = new int[this.capacity][];
		this.groupChecksums = new int[this.capacity];
		this.groupCapacities = new int[this.capacity];
		this.groupSizes = new int[this.capacity];
		if (info != 0) {
			this.groupNameHashes = new int[this.capacity];
			for (int i = 0; i < this.capacity; i++) {
				this.groupNameHashes[i] = -1;
			}
			for (int i = 0; i < this.length; i++) {
				this.groupNameHashes[this.groupIds[i]] = packet.g4();
			}
			this.groupNameHashTable = new IntHashTable(this.groupNameHashes);
		}
		for (int i = 0; i < this.length; i++) {
			this.groupChecksums[this.groupIds[i]] = packet.g4();
		}
		for (int i = 0; i < this.length; i++) {
			this.groupVersions[this.groupIds[i]] = packet.g4();
		}
		for (int i = 0; i < this.length; i++) {
			this.groupSizes[this.groupIds[i]] = packet.g2();
		}
		@Pc(273) int local273;
		@Pc(278) int local278;
		@Pc(280) int local280;
		@Pc(288) int local288;
		for (int i = 0; i < this.length; i++) {
			local50 = 0;
			local273 = this.groupIds[i];
			local278 = this.groupSizes[local273];
			local280 = -1;
			this.fileIds[local273] = new int[local278];
			for (int j = 0; j < local278; j++) {
				@Pc(306) int local306 = this.fileIds[local273][j] = local50 += packet.g2();
				if (local306 > local280) {
					local280 = local306;
				}
			}
			this.groupCapacities[local273] = local280 + 1;
			if (local280 + 1 == local278) {
				this.fileIds[local273] = null;
			}
		}
		if (info == 0) {
			return;
		}
		this.fileNameHashTables = new IntHashTable[local59 + 1];
		this.fileNameHashes = new int[local59 + 1][];
		for (int i = 0; i < this.length; i++) {
			local273 = this.groupIds[i];
			local278 = this.groupSizes[local273];
			this.fileNameHashes[local273] = new int[this.groupCapacities[local273]];
			for (int j = 0; j < this.groupCapacities[local273]; j++) {
				this.fileNameHashes[local273][j] = -1;
			}
			for (int j = 0; j < local278; j++) {
				if (this.fileIds[local273] == null) {
					local288 = j;
				} else {
					local288 = this.fileIds[local273][j];
				}
				this.fileNameHashes[local273][local288] = packet.g4();
			}
			this.fileNameHashTables[local273] = new IntHashTable(this.fileNameHashes[local273]);
		}
	}
}
