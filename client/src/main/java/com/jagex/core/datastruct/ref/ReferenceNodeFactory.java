package com.jagex.core.datastruct.ref;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!q")
public abstract class ReferenceNodeFactory {

	@OriginalMember(owner = "client!uh", name = "ab", descriptor = "Lclient!q;")
	public static final ReferenceNodeFactory INSTANCE = create();

	@OriginalMember(owner = "client!dh", name = "b", descriptor = "(I)Lclient!q;")
	public static ReferenceNodeFactory create() {
		try {
			return new SoftReferenceFactory();
		} catch (@Pc(15) Throwable exception) {
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!q", name = "a", descriptor = "(Lclient!gf;I)Lclient!gf;")
	public abstract ReferenceNode create(@OriginalArg(0) ReferenceNode node);
}
