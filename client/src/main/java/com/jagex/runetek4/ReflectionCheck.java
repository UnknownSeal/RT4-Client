package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ed")
public final class ReflectionCheck extends Node {

	@OriginalMember(owner = "runetek4.client!ed", name = "p", descriptor = "I")
	public int scriptCount;

	@OriginalMember(owner = "runetek4.client!ed", name = "u", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] functionNodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "v", descriptor = "[I")
	public int[] anIntArray137;

	@OriginalMember(owner = "runetek4.client!ed", name = "w", descriptor = "[I")
	public int[] errorCodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "y", descriptor = "[I")
	public int[] anIntArray139;

	@OriginalMember(owner = "runetek4.client!ed", name = "B", descriptor = "[[[B")
	public byte[][][] argumentValues;

	@OriginalMember(owner = "runetek4.client!ed", name = "C", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] valueNodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "F", descriptor = "I")
	public int scriptId;
}
