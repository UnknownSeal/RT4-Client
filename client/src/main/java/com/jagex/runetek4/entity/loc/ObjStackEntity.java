package com.jagex.runetek4.entity.loc;

import com.jagex.runetek4.entity.entity.Entity;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!jj")
public final class ObjStackEntity {

	@OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "Lclient!th;")
	public Entity secondary;

	@OriginalMember(owner = "runetek4.client!jj", name = "b", descriptor = "I")
	public int anInt3057;

	@OriginalMember(owner = "runetek4.client!jj", name = "c", descriptor = "Lclient!th;")
	public Entity tertiary;

	@OriginalMember(owner = "runetek4.client!jj", name = "h", descriptor = "Lclient!th;")
	public Entity primary;

	@OriginalMember(owner = "runetek4.client!jj", name = "k", descriptor = "I")
	public int zFine;

	@OriginalMember(owner = "runetek4.client!jj", name = "n", descriptor = "I")
	public int offset;

	@OriginalMember(owner = "runetek4.client!jj", name = "o", descriptor = "I")
	public int xFine;

	@OriginalMember(owner = "runetek4.client!jj", name = "r", descriptor = "J")
	public long key;
}
