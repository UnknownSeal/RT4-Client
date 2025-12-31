package com.jagex.js5.index;

import com.jagex.cache.NameHashTable;
import com.jagex.core.io.Packet;
import com.jagex.js5.Js5Compression;
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
	public NameHashTable groupNameHashTable;

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
	public NameHashTable[] fileNameHashTables;

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
		@Pc(50) int id = 0;
		@Pc(59) int lastGroup = -1;

		this.length = packet.g2();
		this.groupIds = new int[this.length];

		for (int index = 0; index < this.length; index++) {
			this.groupIds[index] = id += packet.g2();
			if (this.groupIds[index] > lastGroup) {
				lastGroup = this.groupIds[index];
			}
		}
		this.capacity = lastGroup + 1;
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
			this.groupNameHashTable = new NameHashTable(this.groupNameHashes);
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
		@Pc(273) int groupId;
		@Pc(278) int fileCount;
		@Pc(280) int lastFile;
		@Pc(288) int fileId;
		for (int i = 0; i < this.length; i++) {
			id = 0;
			groupId = this.groupIds[i];
			fileCount = this.groupSizes[groupId];
			lastFile = -1;
			this.fileIds[groupId] = new int[fileCount];
			for (int j = 0; j < fileCount; j++) {
				@Pc(306) int local306 = this.fileIds[groupId][j] = id += packet.g2();
				if (local306 > lastFile) {
					lastFile = local306;
				}
			}
			this.groupCapacities[groupId] = lastFile + 1;
			if (lastFile + 1 == fileCount) {
				this.fileIds[groupId] = null;
			}
		}
		if (info == 0) {
			return;
		}
		this.fileNameHashTables = new NameHashTable[lastGroup + 1];
		this.fileNameHashes = new int[lastGroup + 1][];
		for (int i = 0; i < this.length; i++) {
			groupId = this.groupIds[i];
			fileCount = this.groupSizes[groupId];
			this.fileNameHashes[groupId] = new int[this.groupCapacities[groupId]];
			for (int j = 0; j < this.groupCapacities[groupId]; j++) {
				this.fileNameHashes[groupId][j] = -1;
			}
			for (int j = 0; j < fileCount; j++) {
				if (this.fileIds[groupId] == null) {
					fileId = j;
				} else {
					fileId = this.fileIds[groupId][j];
				}
				this.fileNameHashes[groupId][fileId] = packet.g4();
			}
			this.fileNameHashTables[groupId] = new NameHashTable(this.fileNameHashes[groupId]);
		}
	}
}
