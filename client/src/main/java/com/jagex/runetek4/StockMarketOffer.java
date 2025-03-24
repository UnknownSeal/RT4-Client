package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!sg")
public final class StockMarketOffer {

	@OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "B")
	private byte statusAndType;

	@OriginalMember(owner = "runetek4.client!sg", name = "f", descriptor = "I")
	public int anInt5089;

	@OriginalMember(owner = "runetek4.client!sg", name = "g", descriptor = "I")
	public int anInt5090;

	@OriginalMember(owner = "runetek4.client!sg", name = "j", descriptor = "I")
	public int anInt5092;

	@OriginalMember(owner = "runetek4.client!sg", name = "m", descriptor = "I")
	public int anInt5094;

	@OriginalMember(owner = "runetek4.client!sg", name = "s", descriptor = "I")
	public int anInt5099;

	@OriginalMember(owner = "runetek4.client!sg", name = "<init>", descriptor = "()V")
	public StockMarketOffer() {
	}

	@OriginalMember(owner = "runetek4.client!sg", name = "<init>", descriptor = "(Lclient!wa;)V")
	public StockMarketOffer(@OriginalArg(0) Packet packet) {
		this.statusAndType = packet.g1s();
		this.anInt5094 = packet.g2();
		this.anInt5099 = packet.g4();
		this.anInt5090 = packet.g4();
		this.anInt5089 = packet.g4();
		this.anInt5092 = packet.g4();
	}

	@OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "(Z)I")
	public int getStatus() {
		return this.statusAndType & 0x7;
	}

	@OriginalMember(owner = "runetek4.client!sg", name = "b", descriptor = "(B)I")
	public int getType() {
		return (this.statusAndType & 0x8) == 8 ? 1 : 0;
	}
}
