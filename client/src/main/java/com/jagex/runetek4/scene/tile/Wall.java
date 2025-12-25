package com.jagex.runetek4.scene.tile;

import com.jagex.runetek4.entity.Entity;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!jh")
public final class Wall {

    @OriginalMember(owner = "client!gd", name = "h", descriptor = "[I")
    public static final int[] CORNER_FLAGS = new int[] { 16, 32, 64, 128 };

    @OriginalMember(owner = "runetek4.client!jh", name = "a", descriptor = "I")
	public int zFine;

	@OriginalMember(owner = "runetek4.client!jh", name = "e", descriptor = "Lclient!th;")
	public Entity primary;

	@OriginalMember(owner = "runetek4.client!jh", name = "h", descriptor = "Lclient!th;")
	public Entity secondary;

	@OriginalMember(owner = "runetek4.client!jh", name = "k", descriptor = "I")
	public int xFine;

	@OriginalMember(owner = "runetek4.client!jh", name = "l", descriptor = "I")
	public int typeA;

	@OriginalMember(owner = "runetek4.client!jh", name = "o", descriptor = "I")
	public int yFine;

	@OriginalMember(owner = "runetek4.client!jh", name = "q", descriptor = "I")
	public int typeB;

	@OriginalMember(owner = "runetek4.client!jh", name = "d", descriptor = "J")
	public long key = 0L;
}
