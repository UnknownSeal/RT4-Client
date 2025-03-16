package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cd")
public final class ChangeLocRequest extends Node {

	@OriginalMember(owner = "runetek4.client!cd", name = "r", descriptor = "I")
	public int z;

	@OriginalMember(owner = "runetek4.client!cd", name = "t", descriptor = "I")
	public int anInt918;

	@OriginalMember(owner = "runetek4.client!cd", name = "w", descriptor = "I")
	public int anInt920;

	@OriginalMember(owner = "runetek4.client!cd", name = "x", descriptor = "I")
	public int anInt921;

	@OriginalMember(owner = "runetek4.client!cd", name = "z", descriptor = "I")
	public int anInt922;

	@OriginalMember(owner = "runetek4.client!cd", name = "A", descriptor = "I")
	public int anInt923;

	@OriginalMember(owner = "runetek4.client!cd", name = "F", descriptor = "I")
	public int anInt926;

	@OriginalMember(owner = "runetek4.client!cd", name = "G", descriptor = "I")
	public int anInt927;

	@OriginalMember(owner = "runetek4.client!cd", name = "H", descriptor = "I")
	public int x;

	@OriginalMember(owner = "runetek4.client!cd", name = "I", descriptor = "I")
	public int anInt929;

	@OriginalMember(owner = "runetek4.client!cd", name = "C", descriptor = "I")
	public int anInt924 = -1;

	@OriginalMember(owner = "runetek4.client!cd", name = "E", descriptor = "I")
	public int anInt925 = 0;

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void push(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		@Pc(9) ChangeLocRequest local9 = null;
		for (@Pc(14) ChangeLocRequest local14 = (ChangeLocRequest) Static26.spawnedLocations.head(); local14 != null; local14 = (ChangeLocRequest) Static26.spawnedLocations.next()) {
			if (local14.anInt918 == arg0 && arg3 == local14.x && local14.z == arg1 && arg6 == local14.anInt927) {
				local9 = local14;
				break;
			}
		}
		if (local9 == null) {
			local9 = new ChangeLocRequest();
			local9.x = arg3;
			local9.z = arg1;
			local9.anInt918 = arg0;
			local9.anInt927 = arg6;
			Static226.method3898(local9);
			Static26.spawnedLocations.addTail(local9);
		}
		local9.anInt926 = arg7;
		local9.anInt925 = arg8;
		local9.anInt924 = arg4;
		local9.anInt929 = arg5;
		local9.anInt922 = arg2;
	}
}
