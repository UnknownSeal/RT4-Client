package com.jagex.runetek4.game.config.idktype;

import com.jagex.runetek4.graphics.ModelUnlit;
import com.jagex.runetek4.Static14;
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
	public int type = -1;

	@OriginalMember(owner = "client!dm", name = "A", descriptor = "Z")
	public boolean disable = false;

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(13) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(BLclient!wa;I)V")
	private void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int code) {
		if (code == 1) {
			this.type = packet.g1();
			return;
		}
		if (code == 2) {
			int length = packet.g1();
			this.models = new int[length];
			for (int index = 0; index < length; index++) {
				this.models[index] = packet.g2();
			}
		} else if (code == 3) {
			this.disable = true;
		} else if (code == 40) {
			int length = packet.g1();
			this.recol_s = new short[length];
			this.recol_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.recol_s[index] = (short) packet.g2();
				this.recol_d[index] = (short) packet.g2();
			}
		} else if (code == 41) {
			int length = packet.g1();
			this.retex_s = new short[length];
			this.retex_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.retex_s[index] = (short) packet.g2();
				this.retex_d[index] = (short) packet.g2();
			}
		} else if (code >= 60 && code < 70) {
			this.heads[code - 60] = packet.g2();
		}
	}

	@OriginalMember(owner = "client!dm", name = "c", descriptor = "(I)Z")
	public boolean hasReadyHeads() {
		@Pc(3) boolean ready = true;
		for (@Pc(12) int index = 0; index < 5; index++) {
			if (this.heads[index] != -1 && !Static14.aClass153_8.requestDownload(this.heads[index], 0)) {
				ready = false;
			}
		}
		return ready;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(Z)Lclient!gb;")
	public ModelUnlit getHeadModel() {
		@Pc(13) int length = 0;
		@Pc(16) ModelUnlit[] heads = new ModelUnlit[5];
		for (@Pc(18) int index = 0; index < 5; index++) {
			if (this.heads[index] != -1) {
				heads[length++] = ModelUnlit.get(Static14.aClass153_8, this.heads[index]);
			}
		}
		@Pc(52) ModelUnlit head = new ModelUnlit(heads, length);

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
	public boolean hasReadyModels() {
		if (this.models == null) {
			return true;
		}
		@Pc(13) boolean ready = true;
		for (@Pc(22) int index = 0; index < this.models.length; index++) {
			if (!Static14.aClass153_8.requestDownload(this.models[index], 0)) {
				ready = false;
			}
		}
		return ready;
	}

	@OriginalMember(owner = "client!dm", name = "b", descriptor = "(B)Lclient!gb;")
	public ModelUnlit getModel() {
		if (this.models == null) {
			return null;
		}
		@Pc(16) ModelUnlit[] models = new ModelUnlit[this.models.length];
		for (@Pc(18) int index = 0; index < this.models.length; index++) {
			models[index] = ModelUnlit.get(Static14.aClass153_8, this.models[index]);
		}
		@Pc(56) ModelUnlit body;
		if (models.length == 1) {
			body = models[0];
		} else {
			body = new ModelUnlit(models, models.length);
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