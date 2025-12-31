package com.jagex.clientscript;

import com.jagex.cache.cs.ClientScript;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!hj")
public final class GoSubFrame {

	@OriginalMember(owner = "runetek4.client!hj", name = "b", descriptor = "[I")
	public int[] localInts;

	@OriginalMember(owner = "runetek4.client!hj", name = "f", descriptor = "[Lclient!na;")
	public JString[] stringLocals;

	@OriginalMember(owner = "runetek4.client!hj", name = "h", descriptor = "Lclient!qc;")
	public ClientScript script;

	@OriginalMember(owner = "runetek4.client!hj", name = "k", descriptor = "I")
	public int pc = -1;
}
