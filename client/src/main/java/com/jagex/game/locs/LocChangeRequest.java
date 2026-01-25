package com.jagex.game.locs;

import com.jagex.game.runetek4.config.loctype.LocTypeList;
import com.jagex.game.runetek4.config.loctype.LocType;
import com.jagex.core.datastruct.LinkedList;
import com.jagex.core.datastruct.Node;
import com.jagex.scene.SceneGraph;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cd")
public final class LocChangeRequest extends Node {

	// Pending loc change requests
	@OriginalMember(owner = "client!ca", name = "X", descriptor = "Lclient!ih;")
	public static LinkedList queue = new LinkedList();

	// Absolute Z
	@OriginalMember(owner = "runetek4.client!cd", name = "r", descriptor = "I")
	public int z;

	// Level/plane
	@OriginalMember(owner = "runetek4.client!cd", name = "t", descriptor = "I")
	public int level;

	// Original loc shape
	@OriginalMember(owner = "runetek4.client!cd", name = "w", descriptor = "I")
	public int originalShape;

	// New loc shape type
	@OriginalMember(owner = "runetek4.client!cd", name = "F", descriptor = "I")
	public int newShape;

	// Original loc conf id
	@OriginalMember(owner = "runetek4.client!cd", name = "x", descriptor = "I")
	public int originalId;

	// New loc conf id
	@OriginalMember(owner = "runetek4.client!cd", name = "I", descriptor = "I")
	public int newId;

	// New rotation angle
	@OriginalMember(owner = "runetek4.client!cd", name = "z", descriptor = "I")
	public int newAngle;

	// Original rotation angle
	@OriginalMember(owner = "runetek4.client!cd", name = "A", descriptor = "I")
	public int originalAngle;

	// Scene layer
	@OriginalMember(owner = "runetek4.client!cd", name = "G", descriptor = "I")
	public int layer;

	// Absolute X
	@OriginalMember(owner = "runetek4.client!cd", name = "H", descriptor = "I")
	public int x;

	// number of ticks before reverting
	// -1 = permanent
	@OriginalMember(owner = "runetek4.client!cd", name = "C", descriptor = "I")
	public int resetLoops = -1;

	// Delay in ticks until applying
	@OriginalMember(owner = "runetek4.client!cd", name = "E", descriptor = "I")
	public int setLoops = 0;

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void push(@OriginalArg(0) int level, @OriginalArg(1) int z, @OriginalArg(3) int newId, @OriginalArg(4) int x, @OriginalArg(5) int resetLoops, @OriginalArg(6) int angle, @OriginalArg(7) int layer, @OriginalArg(8) int shape, @OriginalArg(9) int setLoops) {
		@Pc(9) LocChangeRequest loc = null;

		// Check if a request already exists for this position and layer
		for (@Pc(14) LocChangeRequest l = (LocChangeRequest) queue.head(); l != null; l = (LocChangeRequest) queue.next()) {
			if (l.level == level && x == l.x && l.z == z && layer == l.layer) {
				loc = l;
				break;
			}
		}

		// Create a new request if none already exists
		if (loc == null) {
			loc = new LocChangeRequest();
			loc.x = x;
			loc.z = z;
			loc.level = level;
			loc.layer = layer;
			init(loc); // Capture original state from scene
			queue.push(loc);
		}

		// Update request with new change parameters
		loc.newShape = shape;
		loc.setLoops = setLoops;
		loc.resetLoops = resetLoops;
		loc.newId = angle;
		loc.newAngle = newId;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(10) LocChangeRequest request = (LocChangeRequest) queue.head(); request != null; request = (LocChangeRequest) queue.next()) {
			// Countdown reset timer (if not permanent)
			if (request.resetLoops > 0) {
				request.resetLoops--;
			}
			if (request.resetLoops != 0) {
				// Countdown set delay
				if (request.setLoops > 0) {
					request.setLoops--;
				}

				// Apply change when delay expires
				if (request.setLoops == 0 && request.x >= 1 && request.z >= 1 && request.x <= 102 && request.z <= 102 && (request.newId < 0 || isLocTypeReady(request.newId, request.newShape))) {
					SceneGraph.setLocationObject(request.newId, request.x, request.level, request.newAngle, request.z, request.newShape, request.layer);
					request.setLoops = -1; // Mark as applied

					// Remove request if it's effectively a no-op
					if (request.originalId == request.newId && request.originalId == -1) {
						request.unlink(); // Both original and new are empty
					} else if (request.newId == request.originalId && request.newAngle == request.originalAngle && request.originalShape == request.newShape) {
						request.unlink(); // Change matches origial exactly
					}
				}
			} else if (request.originalId < 0 || isLocTypeReady(request.originalId, request.originalShape)) {
				// Reset timer expired, revert to original state
				SceneGraph.setLocationObject(request.originalId, request.x, request.level, request.originalAngle, request.z, request.originalShape, request.layer);
				request.unlink(); // Revert complete, remove request
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!il", name = "a", descriptor = "(BII)Z")
	public static boolean isLocTypeReady(@OriginalArg(1) int locId, @OriginalArg(2) int shape) {
		// Normalize diagonal centerpiece to standard centerpiece
		if (shape == 11) {
			shape = 10;
		}

		// Normalize wall decoration variants to base type
		if (shape >= 5 && shape <= 8) {
			shape = 4;
		}
		@Pc(30) LocType locType = LocTypeList.get(locId);
		return locType.isReady(shape);
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(ILclient!cd;)V")
	public static void init(@OriginalArg(1) LocChangeRequest loc) {
		@Pc(5) long key = 0L;
		@Pc(7) int originalId = -1; // -1 = no loc exists
		@Pc(14) int originalShape = 0;

		// Retrieve loc key from scene layer
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

		// Decode loc properties from packed key
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
		for (@Pc(10) LocChangeRequest loc = (LocChangeRequest) queue.head(); loc != null; loc = (LocChangeRequest) queue.next()) {
			if (loc.resetLoops == -1) {
				// Permanent change
				loc.setLoops = 0;
				init(loc);
			} else {
				// Temp change
				loc.unlink();
			}
		}
	}
}
