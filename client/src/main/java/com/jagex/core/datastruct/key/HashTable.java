package com.jagex.core.datastruct.key;

import com.jagex.core.datastruct.Node2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!rm")
public final class HashTable {

	@OriginalMember(owner = "runetek4.client!rm", name = "e", descriptor = "[Lclient!rg;")
	private final Node2[] buckets;

	@OriginalMember(owner = "runetek4.client!rm", name = "<init>", descriptor = "(I)V")
	public HashTable(@OriginalArg(0) int arg0) {
		this.buckets = new Node2[arg0];
		for (@Pc(7) int local7 = 0; local7 < arg0; local7++) {
			@Pc(23) Node2 local23 = this.buckets[local7] = new Node2();
			local23.prev2 = local23;
			local23.next2 = local23;
		}
	}

}
