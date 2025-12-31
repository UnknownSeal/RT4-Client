package com.jagex.sound.core;

import com.jagex.core.datastruct.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ik")
public abstract class Sound extends Node {

	@OriginalMember(owner = "runetek4.client!ik", name = "p", descriptor = "I")
	public int position;

	@OriginalMember(owner = "runetek4.client!ik", name = "<init>", descriptor = "()V")
	protected Sound() {
	}
}
