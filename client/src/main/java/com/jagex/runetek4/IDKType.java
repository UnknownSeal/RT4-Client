package com.jagex.runetek4;

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
		@Pc(22) int local22;
		@Pc(28) int local28;
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
			for (local28 = 0; local28 < length; local28++) {
				this.recol_s[local28] = (short) packet.g2();
				this.recol_d[local28] = (short) packet.g2();
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

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(Z)Lclient!gb;")
	public ModelUnlit method1198() {
		@Pc(13) int local13 = 0;
		@Pc(16) ModelUnlit[] local16 = new ModelUnlit[5];
		for (@Pc(18) int local18 = 0; local18 < 5; local18++) {
			if (this.heads[local18] != -1) {
				local16[local13++] = Static77.method1686(Static14.aClass153_8, this.heads[local18]);
			}
		}
		@Pc(52) ModelUnlit local52 = new ModelUnlit(local16, local13);
		@Pc(58) int local58;
		if (this.recol_s != null) {
			for (local58 = 0; local58 < this.recol_s.length; local58++) {
				local52.method1687(this.recol_s[local58], this.recol_d[local58]);
			}
		}
		if (this.retex_s != null) {
			for (local58 = 0; local58 < this.retex_s.length; local58++) {
				local52.method1669(this.retex_s[local58], this.retex_d[local58]);
			}
		}
		return local52;
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
	public ModelUnlit method1204() {
		if (this.models == null) {
			return null;
		}
		@Pc(16) ModelUnlit[] local16 = new ModelUnlit[this.models.length];
		for (@Pc(18) int local18 = 0; local18 < this.models.length; local18++) {
			local16[local18] = Static77.method1686(Static14.aClass153_8, this.models[local18]);
		}
		@Pc(56) ModelUnlit local56;
		if (local16.length == 1) {
			local56 = local16[0];
		} else {
			local56 = new ModelUnlit(local16, local16.length);
		}
		@Pc(70) int local70;
		if (this.recol_s != null) {
			for (local70 = 0; local70 < this.recol_s.length; local70++) {
				local56.method1687(this.recol_s[local70], this.recol_d[local70]);
			}
		}
		if (this.retex_s != null) {
			for (local70 = 0; local70 < this.retex_s.length; local70++) {
				local56.method1669(this.retex_s[local70], this.retex_d[local70]);
			}
		}
		return local56;
	}

	@OriginalMember(owner = "client!dm", name = "c", descriptor = "(I)Z")
	public boolean method1205() {
		@Pc(3) boolean local3 = true;
		for (@Pc(12) int local12 = 0; local12 < 5; local12++) {
			if (this.heads[local12] != -1 && !Static14.aClass153_8.requestDownload(this.heads[local12], 0)) {
				local3 = false;
			}
		}
		return local3;
	}
}
