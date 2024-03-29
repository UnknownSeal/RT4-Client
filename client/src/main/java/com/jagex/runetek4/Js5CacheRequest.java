package com.jagex.runetek4;

import com.jagex.runetek4.game.client.DiskStore;
import com.jagex.runetek4.js5.Js5Request;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!c")
public final class Js5CacheRequest extends Js5Request {

	@OriginalMember(owner = "runetek4.client!c", name = "cb", descriptor = "I")
	public int anInt824;

	@OriginalMember(owner = "runetek4.client!c", name = "X", descriptor = "Lclient!ge;")
	public DiskStore aClass49_3;

	@OriginalMember(owner = "runetek4.client!c", name = "ab", descriptor = "[B")
	public byte[] bytes;


	@OriginalMember(owner = "runetek4.client!c", name = "b", descriptor = "(Z)[B")
	@Override
	public byte[] getBytes() {
		if (this.awaitingResponse) {
			throw new RuntimeException("Not ready!");
		}
		return this.bytes;
	}

	@OriginalMember(owner = "runetek4.client!c", name = "a", descriptor = "(Z)I")
	@Override
	public int getPercentageComplete() {
		return this.awaitingResponse ? 0 : 100;
	}
}
