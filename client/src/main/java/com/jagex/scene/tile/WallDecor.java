package com.jagex.scene.tile;

import com.jagex.entity.Entity;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!df")
public final class WallDecor {

	@OriginalMember(owner = "runetek4.client!df", name = "a", descriptor = "I")
	public int yFine;

	@OriginalMember(owner = "runetek4.client!df", name = "d", descriptor = "Lclient!th;")
	public Entity secondary;

	@OriginalMember(owner = "runetek4.client!df", name = "e", descriptor = "I")
	public int xFine;

	@OriginalMember(owner = "runetek4.client!df", name = "f", descriptor = "I")
	public int yOffset;

	@OriginalMember(owner = "runetek4.client!df", name = "g", descriptor = "I")
	public int zOffset;

	@OriginalMember(owner = "runetek4.client!df", name = "h", descriptor = "I")
	public int zFine;

	@OriginalMember(owner = "runetek4.client!df", name = "j", descriptor = "Lclient!th;")
	public Entity primary;

	@OriginalMember(owner = "runetek4.client!df", name = "k", descriptor = "I")
	public int xOffset;

	@OriginalMember(owner = "runetek4.client!df", name = "m", descriptor = "I")
	public int type;

	@OriginalMember(owner = "runetek4.client!df", name = "i", descriptor = "J")
	public long key = 0L;
}
