package com.jagex.runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import com.jagex.runetek4.JString;

@OriginalClass("client!sj")
public final class JagStringWrapper extends Node {

	@OriginalMember(owner = "client!sj", name = "A", descriptor = "Lclient!na;")
	public JString value;

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "()V")
	public JagStringWrapper() {
	}

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "(Lclient!na;)V")
	public JagStringWrapper(@OriginalArg(0) JString value) {
		this.value = value;
	}
}
