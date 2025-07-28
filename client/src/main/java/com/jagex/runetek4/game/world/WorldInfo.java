package com.jagex.runetek4.game.world;

import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ee")
public final class WorldInfo {

	@OriginalMember(owner = "client!ee", name = "i", descriptor = "Lclient!na;")
	public JString name;

	@OriginalMember(owner = "client!ee", name = "h", descriptor = "I")
	public int flag;
}
