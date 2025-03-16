package com.jagex.runetek4;

import com.jagex.runetek4.node.CachedNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!rm")
public final class SecondaryHashTable {

	@OriginalMember(owner = "runetek4.client!rm", name = "e", descriptor = "[Lclient!rg;")
	private final CachedNode[] aClass3_Sub2Array1;

	@OriginalMember(owner = "runetek4.client!rm", name = "<init>", descriptor = "(I)V")
	public SecondaryHashTable(@OriginalArg(0) int arg0) {
		this.aClass3_Sub2Array1 = new CachedNode[arg0];
		for (@Pc(7) int local7 = 0; local7 < arg0; local7++) {
			@Pc(23) CachedNode local23 = this.aClass3_Sub2Array1[local7] = new CachedNode();
			local23.nextCachedNode = local23;
			local23.previousCachedNode = local23;
		}
	}
}
