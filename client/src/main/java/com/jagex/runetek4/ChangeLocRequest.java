package com.jagex.runetek4;

import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cd")
public final class ChangeLocRequest extends Node {

	@OriginalMember(owner = "client!ca", name = "X", descriptor = "Lclient!ih;")
	public static LinkedList queue = new LinkedList();
	@OriginalMember(owner = "runetek4.client!cd", name = "r", descriptor = "I")
	public int z;

	@OriginalMember(owner = "runetek4.client!cd", name = "t", descriptor = "I")
	public int level;

	@OriginalMember(owner = "runetek4.client!cd", name = "w", descriptor = "I")
	public int originalShape;

	@OriginalMember(owner = "runetek4.client!cd", name = "x", descriptor = "I")
	public int originalId;

	@OriginalMember(owner = "runetek4.client!cd", name = "z", descriptor = "I")
	public int anInt922;

	@OriginalMember(owner = "runetek4.client!cd", name = "A", descriptor = "I")
	public int originalAngle;

	@OriginalMember(owner = "runetek4.client!cd", name = "F", descriptor = "I")
	public int anInt926;

	@OriginalMember(owner = "runetek4.client!cd", name = "G", descriptor = "I")
	public int layer;

	@OriginalMember(owner = "runetek4.client!cd", name = "H", descriptor = "I")
	public int x;

	@OriginalMember(owner = "runetek4.client!cd", name = "I", descriptor = "I")
	public int anInt929;

	@OriginalMember(owner = "runetek4.client!cd", name = "C", descriptor = "I")
	public int resetLoops = -1;

	@OriginalMember(owner = "runetek4.client!cd", name = "E", descriptor = "I")
	public int setLoops = 0;

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void push(@OriginalArg(0) int level, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int layer, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		@Pc(9) ChangeLocRequest loc = null;
		for (@Pc(14) ChangeLocRequest l = (ChangeLocRequest) queue.head(); l != null; l = (ChangeLocRequest) queue.next()) {
			if (l.level == level && arg3 == l.x && l.z == arg1 && layer == l.layer) {
				loc = l;
				break;
			}
		}
		if (loc == null) {
			loc = new ChangeLocRequest();
			loc.x = arg3;
			loc.z = arg1;
			loc.level = level;
			loc.layer = layer;
			init(loc);
			queue.addTail(loc);
		}
		loc.anInt926 = arg7;
		loc.setLoops = arg8;
		loc.resetLoops = arg4;
		loc.anInt929 = arg5;
		loc.anInt922 = arg2;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(10) ChangeLocRequest local10 = (ChangeLocRequest) queue.head(); local10 != null; local10 = (ChangeLocRequest) queue.next()) {
			if (local10.resetLoops > 0) {
				local10.resetLoops--;
			}
			if (local10.resetLoops != 0) {
				if (local10.setLoops > 0) {
					local10.setLoops--;
				}
				if (local10.setLoops == 0 && local10.x >= 1 && local10.z >= 1 && local10.x <= 102 && local10.z <= 102 && (local10.anInt929 < 0 || method3557(local10.anInt929, local10.anInt926))) {
					SceneGraph.method1698(local10.anInt929, local10.x, local10.level, local10.anInt922, local10.z, local10.anInt926, local10.layer);
					local10.setLoops = -1;
					if (local10.originalId == local10.anInt929 && local10.originalId == -1) {
						local10.unlink();
					} else if (local10.anInt929 == local10.originalId && local10.anInt922 == local10.originalAngle && local10.originalShape == local10.anInt926) {
						local10.unlink();
					}
				}
			} else if (local10.originalId < 0 || method3557(local10.originalId, local10.originalShape)) {
				SceneGraph.method1698(local10.originalId, local10.x, local10.level, local10.originalAngle, local10.z, local10.originalShape, local10.layer);
				local10.unlink();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!il", name = "a", descriptor = "(BII)Z")
	public static boolean method3557(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (arg1 == 11) {
			arg1 = 10;
		}
		if (arg1 >= 5 && arg1 <= 8) {
			arg1 = 4;
		}
		@Pc(30) LocType local30 = LocTypeList.get(arg0);
		return local30.isReady(arg1);
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(ILclient!cd;)V")
	public static void init(@OriginalArg(1) ChangeLocRequest loc) {
		@Pc(5) long key = 0L;
		@Pc(7) int originalId = -1;
		@Pc(14) int originalShape = 0;
		if (loc.layer == 0) {
			key = SceneGraph.getWallKey(loc.level, loc.x, loc.z);
		}
		@Pc(31) int originalAngle = 0;
		if (loc.layer == 1) {
			key = SceneGraph.getWallDecorKey(loc.level, loc.x, loc.z);
		}
		if (loc.layer == 2) {
			key = SceneGraph.getSceneryKey(loc.level, loc.x, loc.z);
		}
		if (loc.layer == 3) {
			key = SceneGraph.getGroundDecorKey(loc.level, loc.x, loc.z);
		}
		if (key != 0L) {
			originalId = Integer.MAX_VALUE & (int) (key >>> 32);
			originalAngle = (int) key >> 20 & 0x3;
			originalShape = (int) key >> 14 & 0x1F;
		}
		loc.originalId = originalId;
		loc.originalShape = originalShape;
		loc.originalAngle = originalAngle;
	}

	@OriginalMember(owner = "runetek4.client!rl", name = "i", descriptor = "(I)V")
	public static void method3796() {
		for (@Pc(10) ChangeLocRequest loc = (ChangeLocRequest) queue.head(); loc != null; loc = (ChangeLocRequest) queue.next()) {
			if (loc.resetLoops == -1) {
				loc.setLoops = 0;
				init(loc);
			} else {
				loc.unlink();
			}
		}
	}
}
