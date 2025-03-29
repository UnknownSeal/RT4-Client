package com.jagex.runetek4.cache.cs;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!qc")
public final class ClientScript extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!qc", name = "I", descriptor = "I")
	public int intArgs;

	@OriginalMember(owner = "runetek4.client!qc", name = "L", descriptor = "I")
	public int localIntCount;

	@OriginalMember(owner = "runetek4.client!qc", name = "N", descriptor = "I")
	public int stringArgs;

	@OriginalMember(owner = "runetek4.client!qc", name = "O", descriptor = "[I")
	public int[] opcodes;

	@OriginalMember(owner = "runetek4.client!qc", name = "Q", descriptor = "[Lclient!sc;")
	public HashTable[] switchTables;

	@OriginalMember(owner = "runetek4.client!qc", name = "R", descriptor = "Lclient!na;")
	public JString name;

	@OriginalMember(owner = "runetek4.client!qc", name = "S", descriptor = "I")
	public int localStringCount;

	@OriginalMember(owner = "runetek4.client!qc", name = "T", descriptor = "[Lclient!na;")
	public JString[] stringOperands;

	@OriginalMember(owner = "runetek4.client!qc", name = "W", descriptor = "[I")
	public int[] intOperands;

}
