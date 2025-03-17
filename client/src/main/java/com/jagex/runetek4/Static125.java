package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static125 {

	@OriginalMember(owner = "runetek4.client!jl", name = "I", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray10;

	@OriginalMember(owner = "runetek4.client!jl", name = "v", descriptor = "I")
	public static int anInt3096 = 0;

	@OriginalMember(owner = "runetek4.client!jl", name = "x", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_18 = new NodeCache(50);

	@OriginalMember(owner = "runetek4.client!jl", name = "G", descriptor = "Lclient!n;")
	public static final NodeCache varbitDefinitionCache = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!jl", name = "H", descriptor = "I")
	public static int worldId = -1;

	@OriginalMember(owner = "runetek4.client!jl", name = "J", descriptor = "I")
	public static int anInt3104 = 127;

	@OriginalMember(owner = "runetek4.client!jl", name = "a", descriptor = "(ILclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static14.aClass153_8 = arg0;
		Static216.aClass153_31 = arg1;
		Static53.anInt1716 = Static216.aClass153_31.getGroupCapacity(3);
	}

}