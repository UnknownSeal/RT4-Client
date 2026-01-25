package com.jagex.ui.component;

import com.jagex.core.datastruct.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!wk")
public final class SubInterface extends Node {

	@OriginalMember(owner = "client!wk", name = "r", descriptor = "I")
	public int interfaceId;

	@OriginalMember(owner = "client!wk", name = "s", descriptor = "I")
	public int modalType;
}
