package com.jagex.core.datastruct.key;

import com.jagex.core.datastruct.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import com.jagex.core.utils.string.JString;

@OriginalClass("client!sj")
public final class JstringNode extends Node {

	@OriginalMember(owner = "client!sj", name = "A", descriptor = "Lclient!na;")
	public JString value;

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "()V")
	public JstringNode() {
	}

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "(Lclient!na;)V")
	public JstringNode(@OriginalArg(0) JString value) {
		this.value = value;
	}
}
