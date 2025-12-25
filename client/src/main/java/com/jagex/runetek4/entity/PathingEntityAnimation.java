package com.jagex.runetek4.entity;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ub")
public final class PathingEntityAnimation {

	@OriginalMember(owner = "runetek4.client!ub", name = "b", descriptor = "I")
	public int sequenceId;

	@OriginalMember(owner = "runetek4.client!ub", name = "d", descriptor = "I")
	public int direction;

	@OriginalMember(owner = "runetek4.client!ub", name = "f", descriptor = "I")
	public int frameIndex;

	@OriginalMember(owner = "runetek4.client!ub", name = "g", descriptor = "I")
	public int frameTime;

	@OriginalMember(owner = "runetek4.client!ub", name = "l", descriptor = "I")
	public int loopCount;

	@OriginalMember(owner = "runetek4.client!ub", name = "q", descriptor = "I")
	public int delay;
}
