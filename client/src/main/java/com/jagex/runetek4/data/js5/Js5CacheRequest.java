package com.jagex.runetek4.data.js5;

import com.jagex.runetek4.data.cache.Cache;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!c")
public final class Js5CacheRequest extends Js5Request {

	@OriginalMember(owner = "runetek4.client!c", name = "cb", descriptor = "I")
	public int type;

	@OriginalMember(owner = "runetek4.client!c", name = "X", descriptor = "Lclient!ge;")
	public Cache cache;

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
