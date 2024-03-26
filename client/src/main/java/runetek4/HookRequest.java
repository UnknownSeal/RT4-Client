package runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!jl")
public final class HookRequest extends Node {

	@OriginalMember(owner = "runetek4.client!jl", name = "q", descriptor = "Lclient!be;")
	public Component aClass13_16;

	@OriginalMember(owner = "runetek4.client!jl", name = "r", descriptor = "Lclient!na;")
	public JagString aClass100_598;

	@OriginalMember(owner = "runetek4.client!jl", name = "w", descriptor = "I")
	public int anInt3097;

	@OriginalMember(owner = "runetek4.client!jl", name = "z", descriptor = "I")
	public int anInt3099;

	@OriginalMember(owner = "runetek4.client!jl", name = "A", descriptor = "I")
	public int anInt3100;

	@OriginalMember(owner = "runetek4.client!jl", name = "B", descriptor = "I")
	public int anInt3101;

	@OriginalMember(owner = "runetek4.client!jl", name = "C", descriptor = "Z")
	public boolean aBoolean158;

	@OriginalMember(owner = "runetek4.client!jl", name = "D", descriptor = "I")
	public int anInt3102;

	@OriginalMember(owner = "runetek4.client!jl", name = "E", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray31;

	@OriginalMember(owner = "runetek4.client!jl", name = "F", descriptor = "Lclient!be;")
	public Component source;
}
