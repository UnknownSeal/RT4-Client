package com.jagex.runetek4.scene;

import com.jagex.runetek4.entity.Entity;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ec")
public final class Scenery {

	@OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "I")
	public int zMin;

	@OriginalMember(owner = "runetek4.client!ec", name = "c", descriptor = "Lclient!th;")
	public Entity entity;

	@OriginalMember(owner = "runetek4.client!ec", name = "e", descriptor = "I")
	public int zMax;

	@OriginalMember(owner = "runetek4.client!ec", name = "f", descriptor = "I")
	public int yMin;

	@OriginalMember(owner = "runetek4.client!ec", name = "h", descriptor = "I")
	public int xMin;

	@OriginalMember(owner = "runetek4.client!ec", name = "j", descriptor = "I")
	public int yMax;

	@OriginalMember(owner = "runetek4.client!ec", name = "m", descriptor = "I")
	public int rotation;

	@OriginalMember(owner = "runetek4.client!ec", name = "o", descriptor = "I")
	public int type;

	@OriginalMember(owner = "runetek4.client!ec", name = "p", descriptor = "I")
	public int config;

	@OriginalMember(owner = "runetek4.client!ec", name = "s", descriptor = "I")
	public int level;

	@OriginalMember(owner = "runetek4.client!ec", name = "w", descriptor = "I")
	public int xMax;

	@OriginalMember(owner = "runetek4.client!ec", name = "x", descriptor = "I")
	public int animationId;

	@OriginalMember(owner = "runetek4.client!ec", name = "A", descriptor = "J")
	public long key = 0L;
}
