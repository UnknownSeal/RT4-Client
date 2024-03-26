package runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!ik")
public abstract class Sound extends Node {

	@OriginalMember(owner = "runetek4.client!ik", name = "p", descriptor = "I")
	public int anInt3313;

	@OriginalMember(owner = "runetek4.client!ik", name = "<init>", descriptor = "()V")
	protected Sound() {
	}
}
