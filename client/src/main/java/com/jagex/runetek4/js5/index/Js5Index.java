package com.jagex.runetek4.js5.index;

import com.jagex.runetek4.IntHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ii")
public final class Js5Index {

	@OriginalMember(owner = "runetek4.client!ii", name = "b", descriptor = "[[I")
	public int[][] anIntArrayArray21;

	@OriginalMember(owner = "runetek4.client!ii", name = "d", descriptor = "[I")
	public int[] anIntArray268;

	@OriginalMember(owner = "runetek4.client!ii", name = "f", descriptor = "I")
	public int length;

	@OriginalMember(owner = "runetek4.client!ii", name = "h", descriptor = "Lclient!jg;")
	public IntHashTable aClass76_1;

	@OriginalMember(owner = "runetek4.client!ii", name = "m", descriptor = "[I")
	public int[] groupIds;

	@OriginalMember(owner = "runetek4.client!ii", name = "n", descriptor = "[I")
	public int[] groupCapacities;

	@OriginalMember(owner = "runetek4.client!ii", name = "o", descriptor = "[I")
	public int[] anIntArray271;

	@OriginalMember(owner = "runetek4.client!ii", name = "p", descriptor = "[I")
	public int[] groupSizes;

	@OriginalMember(owner = "runetek4.client!ii", name = "r", descriptor = "[I")
	public int[] anIntArray273;

	@OriginalMember(owner = "runetek4.client!ii", name = "s", descriptor = "[[I")
	public int[][] anIntArrayArray22;

	@OriginalMember(owner = "runetek4.client!ii", name = "u", descriptor = "I")
	public int capacity;

	@OriginalMember(owner = "runetek4.client!ii", name = "v", descriptor = "I")
	public int indexversion;

	@OriginalMember(owner = "runetek4.client!ii", name = "x", descriptor = "[Lclient!jg;")
	public IntHashTable[] aClass76Array1;

	@OriginalMember(owner = "runetek4.client!ii", name = "z", descriptor = "I")
	public final int crc;

	@OriginalMember(owner = "runetek4.client!ii", name = "<init>", descriptor = "([BI)V")
	public Js5Index(@OriginalArg(0) byte[] bytes, @OriginalArg(1) int crc) {
		this.crc = Packet.getcrc(bytes, bytes.length);
		if (this.crc != crc) {
			throw new RuntimeException("Invalid CRC - expected:" + crc + " got:" + this.crc);
		}
		this.method2293(bytes);
	}

	@OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "(I[B)V")
	private void method2293(@OriginalArg(1) byte[] arg0) {
		@Pc(12) Packet packet = new Packet(Js5.uncompress(arg0));
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
		this.anIntArray273 = new int[this.capacity];
		this.anIntArrayArray22 = new int[this.capacity][];
		this.anIntArray268 = new int[this.capacity];
		this.groupCapacities = new int[this.capacity];
		this.groupSizes = new int[this.capacity];
		if (info != 0) {
			this.anIntArray271 = new int[this.capacity];
			for (int i = 0; i < this.capacity; i++) {
				this.anIntArray271[i] = -1;
			}
			for (int i = 0; i < this.length; i++) {
				this.anIntArray271[this.groupIds[i]] = packet.g4();
			}
			this.aClass76_1 = new IntHashTable(this.anIntArray271);
		}
		for (int i = 0; i < this.length; i++) {
			this.anIntArray268[this.groupIds[i]] = packet.g4();
		}
		for (int i = 0; i < this.length; i++) {
			this.anIntArray273[this.groupIds[i]] = packet.g4();
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
			this.anIntArrayArray22[local273] = new int[local278];
			for (int j = 0; j < local278; j++) {
				@Pc(306) int local306 = this.anIntArrayArray22[local273][j] = local50 += packet.g2();
				if (local306 > local280) {
					local280 = local306;
				}
			}
			this.groupCapacities[local273] = local280 + 1;
			if (local280 + 1 == local278) {
				this.anIntArrayArray22[local273] = null;
			}
		}
		if (info == 0) {
			return;
		}
		this.aClass76Array1 = new IntHashTable[local59 + 1];
		this.anIntArrayArray21 = new int[local59 + 1][];
		for (int i = 0; i < this.length; i++) {
			local273 = this.groupIds[i];
			local278 = this.groupSizes[local273];
			this.anIntArrayArray21[local273] = new int[this.groupCapacities[local273]];
			for (int j = 0; j < this.groupCapacities[local273]; j++) {
				this.anIntArrayArray21[local273][j] = -1;
			}
			for (int j = 0; j < local278; j++) {
				if (this.anIntArrayArray22[local273] == null) {
					local288 = j;
				} else {
					local288 = this.anIntArrayArray22[local273][j];
				}
				this.anIntArrayArray21[local273][local288] = packet.g4();
			}
			this.aClass76Array1[local273] = new IntHashTable(this.anIntArrayArray21[local273]);
		}
	}
}
