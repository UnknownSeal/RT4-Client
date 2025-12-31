package com.jagex.core.datastruct.ref;

import com.jagex.core.datastruct.key.SoftReferenceNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!db")
public final class SoftReferenceFactory extends ReferenceNodeFactory {

	@OriginalMember(owner = "runetek4.client!db", name = "a", descriptor = "(Lclient!gf;I)Lclient!gf;")
	@Override
	public final ReferenceNode create(@OriginalArg(0) ReferenceNode node) {
		return new SoftReferenceNode(node.get());
	}
}
