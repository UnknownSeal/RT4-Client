package com.jagex.runetek4.graphics.render;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!pc")
public interface MaterialRenderer {

	@OriginalMember(owner = "runetek4.client!pc", name = "a", descriptor = "()V")
	void unbind();

	@OriginalMember(owner = "runetek4.client!pc", name = "b", descriptor = "()V")
	void bind();

	@OriginalMember(owner = "runetek4.client!pc", name = "a", descriptor = "(I)V")
	void setArgument(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!pc", name = "c", descriptor = "()I")
	int getFlags();
}
