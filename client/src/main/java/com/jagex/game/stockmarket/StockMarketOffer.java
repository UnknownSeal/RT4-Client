package com.jagex.game.stockmarket;

import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!sg")
public final class StockMarketOffer {

	@OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "B")
	private byte statusAndType;

	@OriginalMember(owner = "runetek4.client!sg", name = "f", descriptor = "I")
	public int completedCount;

	@OriginalMember(owner = "runetek4.client!sg", name = "g", descriptor = "I")
	public int count;

	@OriginalMember(owner = "runetek4.client!sg", name = "j", descriptor = "I")
	public int completedGold;

	@OriginalMember(owner = "runetek4.client!sg", name = "m", descriptor = "I")
	public int item;

	@OriginalMember(owner = "runetek4.client!sg", name = "s", descriptor = "I")
	public int price;

	@OriginalMember(owner = "runetek4.client!sg", name = "<init>", descriptor = "()V")
	public StockMarketOffer() {
	}

	@OriginalMember(owner = "runetek4.client!sg", name = "<init>", descriptor = "(Lclient!wa;)V")
	public StockMarketOffer(@OriginalArg(0) Packet packet) {
		this.statusAndType = packet.g1s();
		this.item = packet.g2();
		this.price = packet.g4();
		this.count = packet.g4();
		this.completedCount = packet.g4();
		this.completedGold = packet.g4();
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
