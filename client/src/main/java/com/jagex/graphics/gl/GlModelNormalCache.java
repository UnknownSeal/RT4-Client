package com.jagex.graphics.gl;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

//GlModel_Class23
@OriginalClass("client!de")
public final class GlModelNormalCache {

	@OriginalMember(owner = "client!de", name = "a", descriptor = "[S")
	public short[] normalMagnitude;

	@OriginalMember(owner = "client!de", name = "b", descriptor = "[S")
	public short[] normalX;

	@OriginalMember(owner = "client!de", name = "c", descriptor = "[S")
	public short[] normalZ;

	@OriginalMember(owner = "client!de", name = "d", descriptor = "[S")
	public short[] normalY;
}
