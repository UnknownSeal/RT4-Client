package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!gj")
public abstract class GWCBasicWorld {

	@OriginalMember(owner = "runetek4.client!gj", name = "j", descriptor = "I")
	public int anInt377;

	@OriginalMember(owner = "runetek4.client!gj", name = "l", descriptor = "I")
	public int anInt379;

	@OriginalMember(owner = "runetek4.client!gj", name = "o", descriptor = "I")
	public int anInt381;

	@OriginalMember(owner = "runetek4.client!gj", name = "a", descriptor = "(I)Z")
	public final boolean method437() {
		return (this.anInt381 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!gj", name = "a", descriptor = "(Z)Z")
	public final boolean method439() {
		return (this.anInt381 & 0x4) != 0;
	}

	@OriginalMember(owner = "runetek4.client!gj", name = "c", descriptor = "(I)Z")
	public final boolean method441() {
		return (this.anInt381 & 0x8) != 0;
	}

	@OriginalMember(owner = "runetek4.client!gj", name = "d", descriptor = "(I)Z")
	public final boolean method442() {
		return (this.anInt381 & 0x2) != 0;
	}
}
