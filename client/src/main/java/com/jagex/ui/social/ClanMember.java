package com.jagex.ui.social;

import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!kl")
public final class ClanMember extends Node {

	@OriginalMember(owner = "client!kl", name = "p", descriptor = "B")
	public byte rank;

	@OriginalMember(owner = "client!kl", name = "q", descriptor = "Lclient!na;")
	public JString worldName;

	@OriginalMember(owner = "client!kl", name = "t", descriptor = "Lclient!na;")
	public JString username;

	@OriginalMember(owner = "client!kl", name = "x", descriptor = "I")
	public int world;
}
