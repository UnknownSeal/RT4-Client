package com.jagex.runetek4.game.shared.framework.gwc;

import com.jagex.runetek4.JagString;
import com.jagex.runetek4.Static203;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass(".client!ba")
public final class GWCWorld extends GWCBasicWorld {

	@OriginalMember(owner = ".client!ba", name = "t", descriptor = "Lclient!na;")
	public JagString aClass100_69;

	@OriginalMember(owner = ".client!ba", name = "u", descriptor = "I")
	public int anInt382;

	@OriginalMember(owner = ".client!ba", name = "A", descriptor = "Lclient!na;")
	public JagString aClass100_71;

	@OriginalMember(owner = ".client!ba", name = "g", descriptor = "(I)Lclient!ee;")
	public final GWCLocation method445() {
		return Static203.aGWCLocationArray1[this.anInt377];
	}
}
