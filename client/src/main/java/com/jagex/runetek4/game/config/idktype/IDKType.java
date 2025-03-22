package com.jagex.runetek4.game.config.idktype;

import com.jagex.runetek4.IdkTypeList;
import com.jagex.runetek4.graphics.RawModel;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!dm")
public final class IDKType {

	@OriginalMember(owner = "client!dm", name = "b", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!dm", name = "i", descriptor = "[S")
	private short[] recol_s;

	@OriginalMember(owner = "client!dm", name = "s", descriptor = "[S")
	private short[] retex_d;

	@OriginalMember(owner = "client!dm", name = "t", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!dm", name = "w", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!dm", name = "x", descriptor = "[I")
	private final int[] heads = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!dm", name = "k", descriptor = "I")
	public int feature = -1;

	@OriginalMember(owner = "client!dm", name = "A", descriptor = "Z")
	public boolean disable = false;

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(13) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(BLclient!wa;I)V")
	private void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int opcode) {
		if (opcode == 1) {
			this.feature = packet.g1();
			return;
		}
		if (opcode == 2) {
			int length = packet.g1();
			this.models = new int[length];
			for (int index = 0; index < length; index++) {
				this.models[index] = packet.g2();
			}
		} else if (opcode == 3) {
			this.disable = true;
		} else if (opcode == 40) {
			int length = packet.g1();
			this.recol_s = new short[length];
			this.recol_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.recol_s[index] = (short) packet.g2();
				this.recol_d[index] = (short) packet.g2();
			}
		} else if (opcode == 41) {
			int length = packet.g1();
			this.retex_s = new short[length];
			this.retex_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.retex_s[index] = (short) packet.g2();
				this.retex_d[index] = (short) packet.g2();
			}
		} else if (opcode >= 60 && opcode < 70) {
			this.heads[opcode - 60] = packet.g2();
		}
	}

	@OriginalMember(owner = "client!dm", name = "c", descriptor = "(I)Z")
	public boolean hasReadyHeads() {
		@Pc(3) boolean ready = true;
		for (@Pc(12) int index = 0; index < 5; index++) {
			if (this.heads[index] != -1 && !IdkTypeList.modelsArchive.isFileReady(this.heads[index], 0)) {
				ready = false;
			}
		}
		return ready;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(Z)Lclient!gb;")
	public RawModel getHeadModel() {
		@Pc(13) int length = 0;
		@Pc(16) RawModel[] heads = new RawModel[5];
		for (@Pc(18) int index = 0; index < 5; index++) {
			if (this.heads[index] != -1) {
				heads[length++] = RawModel.create(IdkTypeList.modelsArchive, this.heads[index]);
			}
		}
		@Pc(52) RawModel head = new RawModel(heads, length);

		if (this.recol_s != null) {
			for (int index = 0; index < this.recol_s.length; index++) {
				head.recolor(this.recol_s[index], this.recol_d[index]);
			}
		}
		if (this.retex_s != null) {
			for (int index = 0; index < this.retex_s.length; index++) {
				head.retexture(this.retex_s[index], this.retex_d[index]);
			}
		}
		return head;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(I)Z")
	public boolean isBodyModelReady() {
		if (this.models == null) {
			return true;
		}
		@Pc(13) boolean ready = true;
		for (@Pc(22) int index = 0; index < this.models.length; index++) {
			if (!IdkTypeList.modelsArchive.isFileReady(this.models[index], 0)) {
				ready = false;
			}
		}
		return ready;
	}

	@OriginalMember(owner = "client!dm", name = "b", descriptor = "(B)Lclient!gb;")
	public RawModel getModel() {
		if (this.models == null) {
			return null;
		}
		@Pc(16) RawModel[] models = new RawModel[this.models.length];
		for (@Pc(18) int index = 0; index < this.models.length; index++) {
			models[index] = RawModel.create(IdkTypeList.modelsArchive, this.models[index]);
		}
		@Pc(56) RawModel body;
		if (models.length == 1) {
			body = models[0];
		} else {
			body = new RawModel(models, models.length);
		}

		if (this.recol_s != null) {
			for (int index = 0; index < this.recol_s.length; index++) {
				body.recolor(this.recol_s[index], this.recol_d[index]);
			}
		}
		if (this.retex_s != null) {
			for (int index = 0; index < this.retex_s.length; index++) {
				body.retexture(this.retex_s[index], this.retex_d[index]);
			}
		}
		return body;
	}
}