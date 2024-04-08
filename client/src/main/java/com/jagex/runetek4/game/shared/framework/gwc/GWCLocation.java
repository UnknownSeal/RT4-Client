package com.jagex.runetek4.game.shared.framework.gwc;

import com.jagex.runetek4.JagString;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ee")
public final class GWCLocation {

	@OriginalMember(owner = "client!ee", name = "i", descriptor = "Lclient!na;")
	public JagString name;

	@OriginalMember(owner = "client!ee", name = "h", descriptor = "I")
	public int flag;
}
