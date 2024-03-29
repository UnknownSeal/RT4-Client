package com.jagex.runetek4.js5.index;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.client.DiskStore;
import com.jagex.runetek4.js5.network.Js5NetRequest;
import com.jagex.runetek4.js5.network.Js5NetResourceProvider;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!al")
public final class Js5MasterIndex {

	@OriginalMember(owner = "client!al", name = "a", descriptor = "Lclient!wa;")
	private Packet aClass3_Sub15_1;

	@OriginalMember(owner = "client!al", name = "s", descriptor = "[Lclient!bg;")
	private Js5NetResourceProvider[] aClass14_Sub1Array1;

	@OriginalMember(owner = "client!al", name = "j", descriptor = "Lclient!k;")
	private final Js5CacheQueue aClass80_1;

	@OriginalMember(owner = "client!al", name = "f", descriptor = "Lclient!jb;")
	private final Js5NetQueue aClass73_1;

	@OriginalMember(owner = "client!al", name = "c", descriptor = "Lclient!pm;")
	private Js5NetRequest aClass3_Sub2_Sub5_Sub2_1;

	@OriginalMember(owner = "client!al", name = "<init>", descriptor = "(Lclient!jb;Lclient!k;)V")
	public Js5MasterIndex(@OriginalArg(0) Js5NetQueue arg0, @OriginalArg(1) Js5CacheQueue arg1) {
		this.aClass80_1 = arg1;
		this.aClass73_1 = arg0;
		if (!this.aClass73_1.isUrgentsFull()) {
			this.aClass3_Sub2_Sub5_Sub2_1 = this.aClass73_1.method2330(255, (byte) 0, 255, true);
		}
	}

	@OriginalMember(owner = "client!al", name = "b", descriptor = "(I)Z")
	public boolean method178() {
		if (this.aClass3_Sub15_1 != null) {
			return true;
		}
		if (this.aClass3_Sub2_Sub5_Sub2_1 == null) {
			if (this.aClass73_1.isUrgentsFull()) {
				return false;
			}
			this.aClass3_Sub2_Sub5_Sub2_1 = this.aClass73_1.method2330(255, (byte) 0, 255, true);
		}
		if (this.aClass3_Sub2_Sub5_Sub2_1.awaitingResponse) {
			return false;
		} else {
			this.aClass3_Sub15_1 = new Packet(this.aClass3_Sub2_Sub5_Sub2_1.getBytes());
			this.aClass14_Sub1Array1 = new Js5NetResourceProvider[(this.aClass3_Sub15_1.data.length - 5) / 8];
			return true;
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(B)V")
	public void method179() {
		if (this.aClass14_Sub1Array1 == null) {
			return;
		}
		@Pc(13) int local13;
		for (local13 = 0; local13 < this.aClass14_Sub1Array1.length; local13++) {
			if (this.aClass14_Sub1Array1[local13] != null) {
				this.aClass14_Sub1Array1[local13].method537();
			}
		}
		for (local13 = 0; local13 < this.aClass14_Sub1Array1.length; local13++) {
			if (this.aClass14_Sub1Array1[local13] != null) {
				this.aClass14_Sub1Array1[local13].method534();
			}
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(IILclient!ge;Lclient!ge;)Lclient!bg;")
	public Js5NetResourceProvider method180(@OriginalArg(1) int arg0, @OriginalArg(2) DiskStore arg1, @OriginalArg(3) DiskStore arg2) {
		return this.method188(arg2, arg0, arg1);
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Lclient!ge;IIZLclient!ge;)Lclient!bg;")
	private Js5NetResourceProvider method188(@OriginalArg(0) DiskStore arg0, @OriginalArg(2) int arg1, @OriginalArg(4) DiskStore arg2) {
		if (this.aClass3_Sub15_1 == null) {
			throw new RuntimeException();
		}
		this.aClass3_Sub15_1.pos = arg1 * 8 + 5;
		if (this.aClass3_Sub15_1.data.length <= this.aClass3_Sub15_1.pos) {
			throw new RuntimeException();
		} else if (this.aClass14_Sub1Array1[arg1] == null) {
			@Pc(56) int local56 = this.aClass3_Sub15_1.g4();
			@Pc(61) int local61 = this.aClass3_Sub15_1.g4();
			@Pc(75) Js5NetResourceProvider local75 = new Js5NetResourceProvider(arg1, arg0, arg2, this.aClass73_1, this.aClass80_1, local56, local61, true);
			this.aClass14_Sub1Array1[arg1] = local75;
			return local75;
		} else {
			return this.aClass14_Sub1Array1[arg1];
		}
	}
}
