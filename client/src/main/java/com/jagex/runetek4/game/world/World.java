package com.jagex.runetek4.game.world;

import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ba")
public final class World extends AbstractWorld {

	@OriginalMember(owner = "client!ba", name = "t", descriptor = "Lclient!na;")
	public JString activity;

	@OriginalMember(owner = "client!ba", name = "u", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!ba", name = "A", descriptor = "Lclient!na;")
	public JString hostname;

	@OriginalMember(owner = "client!ba", name = "g", descriptor = "(I)Lclient!ee;")
	public WorldInfo getWorldInfo() {
		return WorldList.countries[this.country];
	}
}
