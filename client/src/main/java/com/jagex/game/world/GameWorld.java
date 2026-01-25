package com.jagex.game.world;

import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ba")
public final class GameWorld extends World {

	@OriginalMember(owner = "client!ba", name = "t", descriptor = "Lclient!na;")
	public JString activity;

	@OriginalMember(owner = "client!ba", name = "u", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!ba", name = "A", descriptor = "Lclient!na;")
	public JString hostname;

	@OriginalMember(owner = "client!ba", name = "g", descriptor = "(I)Lclient!ee;")
	public Country getWorldInfo() {
		return WorldList.countries[this.country];
	}
}
