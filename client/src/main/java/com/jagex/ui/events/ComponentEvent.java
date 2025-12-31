package com.jagex.ui.events;

import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node;
import com.jagex.ui.component.Component;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!jl")
public final class ComponentEvent extends Node {

	@OriginalMember(owner = "runetek4.client!jl", name = "q", descriptor = "Lclient!be;")
	public Component target;

	@OriginalMember(owner = "runetek4.client!jl", name = "r", descriptor = "Lclient!na;")
	public JString opBase;

	@OriginalMember(owner = "runetek4.client!jl", name = "w", descriptor = "I")
	public int mouseY;

	@OriginalMember(owner = "runetek4.client!jl", name = "z", descriptor = "I")
	public int keyChar;

	@OriginalMember(owner = "runetek4.client!jl", name = "A", descriptor = "I")
	public int keyCode;

	@OriginalMember(owner = "runetek4.client!jl", name = "B", descriptor = "I")
	public int op;

	@OriginalMember(owner = "runetek4.client!jl", name = "C", descriptor = "Z")
	public boolean shouldExecute;

	@OriginalMember(owner = "runetek4.client!jl", name = "D", descriptor = "I")
	public int mouseX;

	@OriginalMember(owner = "runetek4.client!jl", name = "E", descriptor = "[Ljava/lang/Object;")
	public Object[] arguments;

	@OriginalMember(owner = "runetek4.client!jl", name = "F", descriptor = "Lclient!be;")
	public Component source;
}
