package com.jagex.runetek4.game.locs;

import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.scene.SceneGraph;
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
	public static void push(@OriginalArg(0) int level, @OriginalArg(1) int z, @OriginalArg(3) int id, @OriginalArg(4) int x, @OriginalArg(5) int resetLoops, @OriginalArg(6) int rotation, @OriginalArg(7) int layer, @OriginalArg(8) int type, @OriginalArg(9) int setLoops) {
		@Pc(9) ChangeLocRequest loc = null;
		for (@Pc(14) ChangeLocRequest l = (ChangeLocRequest) queue.head(); l != null; l = (ChangeLocRequest) queue.next()) {
			if (l.level == level && x == l.x && l.z == z && layer == l.layer) {
				loc = l;
				break;
			}
		}
		if (loc == null) {
			loc = new ChangeLocRequest();
			loc.x = x;
			loc.z = z;
			loc.level = level;
			loc.layer = layer;
			init(loc);
			queue.addTail(loc);
		}
		loc.anInt926 = type;
		loc.setLoops = setLoops;
		loc.resetLoops = resetLoops;
		loc.anInt929 = rotation;
		loc.anInt922 = id;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(10) ChangeLocRequest request = (ChangeLocRequest) queue.head(); request != null; request = (ChangeLocRequest) queue.next()) {
			if (request.resetLoops > 0) {
				request.resetLoops--;
			}
			if (request.resetLoops != 0) {
				if (request.setLoops > 0) {
					request.setLoops--;
				}
				if (request.setLoops == 0 && request.x >= 1 && request.z >= 1 && request.x <= 102 && request.z <= 102 && (request.anInt929 < 0 || isLocTypeReady(request.anInt929, request.anInt926))) {
					SceneGraph.setLocationObject(request.anInt929, request.x, request.level, request.anInt922, request.z, request.anInt926, request.layer);
					request.setLoops = -1;
					if (request.originalId == request.anInt929 && request.originalId == -1) {
						request.unlink();
					} else if (request.anInt929 == request.originalId && request.anInt922 == request.originalAngle && request.originalShape == request.anInt926) {
						request.unlink();
					}
				}
			} else if (request.originalId < 0 || isLocTypeReady(request.originalId, request.originalShape)) {
				SceneGraph.setLocationObject(request.originalId, request.x, request.level, request.originalAngle, request.z, request.originalShape, request.layer);
				request.unlink();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!il", name = "a", descriptor = "(BII)Z")
	public static boolean isLocTypeReady(@OriginalArg(1) int locId, @OriginalArg(2) int shape) {
		if (shape == 11) {
			shape = 10;
		}
		if (shape >= 5 && shape <= 8) {
			shape = 4;
		}
		@Pc(30) LocType locType = LocTypeList.get(locId);
		return locType.isReady(shape);
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
	public static void refreshRequests() {
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
