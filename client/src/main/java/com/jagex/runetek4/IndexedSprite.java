package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ok")
public abstract class IndexedSprite {

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "I")
	public int anInt4270;

	@OriginalMember(owner = "runetek4.client!ok", name = "d", descriptor = "I")
	public int anInt4273;

	@OriginalMember(owner = "runetek4.client!ok", name = "i", descriptor = "I")
	public int anInt4276;

	@OriginalMember(owner = "runetek4.client!ok", name = "k", descriptor = "I")
	public int anInt4278;

	@OriginalMember(owner = "runetek4.client!ok", name = "l", descriptor = "I")
	public int anInt4279;

	@OriginalMember(owner = "runetek4.client!ok", name = "m", descriptor = "I")
	public int anInt4280;

	@OriginalMember(owner = "runetek4.client!ok", name = "<init>", descriptor = "()V")
	protected IndexedSprite() {
	}

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "(III)V")
	public abstract void method3335(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2);

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "(II)V")
	public abstract void method3336(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1);
}
