package com.jagex.entity.loc;

import com.jagex.core.datastruct.Node2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!pa")
public final class ClientObj extends Node2 {

	@OriginalMember(owner = "runetek4.client!pa", name = "T", descriptor = "Lclient!uj;")
	public final ObjStack value;

	@OriginalMember(owner = "runetek4.client!pa", name = "<init>", descriptor = "(Lclient!uj;)V")
	public ClientObj(@OriginalArg(0) ObjStack arg0) {
		this.value = arg0;
	}
}
