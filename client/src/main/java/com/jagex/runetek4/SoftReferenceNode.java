package com.jagex.runetek4;

import java.lang.ref.SoftReference;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!qd")
public final class SoftReferenceNode extends ReferenceNode {

	@OriginalMember(owner = "runetek4.client!qd", name = "U", descriptor = "Ljava/lang/ref/SoftReference;")
	private final SoftReference aSoftReference1;

	@OriginalMember(owner = "runetek4.client!qd", name = "<init>", descriptor = "(Ljava/lang/Object;)V")
	public SoftReferenceNode(@OriginalArg(0) Object arg0) {
		this.aSoftReference1 = new SoftReference(arg0);
	}

	@OriginalMember(owner = "runetek4.client!qd", name = "a", descriptor = "(Z)Ljava/lang/Object;")
	@Override
	public final Object method3618() {
		return this.aSoftReference1.get();
	}

	@OriginalMember(owner = "runetek4.client!qd", name = "e", descriptor = "(I)Z")
	@Override
	public final boolean method3619() {
		return true;
	}
}
