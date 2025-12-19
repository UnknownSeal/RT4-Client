package com.jagex.runetek4.game.locs;

import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.core.datastruct.LinkList;
import com.jagex.runetek4.entity.entity.Entity;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.entity.entity.PathingEntity;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.entity.loc.Loc;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cn")
public final class AttachLocRequest extends Node {

	@OriginalMember(owner = "runetek4.client!ka", name = "q", descriptor = "Lclient!ih;")
	public static final LinkList queue = new LinkList();

	@OriginalMember(owner = "runetek4.client!cn", name = "p", descriptor = "I")
	public int setLoops;

	@OriginalMember(owner = "runetek4.client!cn", name = "r", descriptor = "I")
	public int x0Delta;

	@OriginalMember(owner = "runetek4.client!cn", name = "t", descriptor = "I")
	public int locId;

	@OriginalMember(owner = "runetek4.client!cn", name = "u", descriptor = "I")
	public int x;

	@OriginalMember(owner = "runetek4.client!cn", name = "v", descriptor = "I")
	public int x1Delta;

	@OriginalMember(owner = "runetek4.client!cn", name = "w", descriptor = "I")
	public int entityId;

	@OriginalMember(owner = "runetek4.client!cn", name = "D", descriptor = "I")
	public int z0Delta;

	@OriginalMember(owner = "runetek4.client!cn", name = "E", descriptor = "I")
	public int shape;

	@OriginalMember(owner = "runetek4.client!cn", name = "H", descriptor = "I")
	public int z1Delta;

	@OriginalMember(owner = "runetek4.client!cn", name = "M", descriptor = "I")
	public int z;

	@OriginalMember(owner = "runetek4.client!cn", name = "N", descriptor = "I")
	public int resetLoops;

	@OriginalMember(owner = "runetek4.client!cn", name = "O", descriptor = "I")
	public int angle;

	@OriginalMember(owner = "runetek4.client!ph", name = "a", descriptor = "(B)V")
	public static void loop() {
		while (true) {
			@Pc(17) AttachLocRequest request = (AttachLocRequest) queue.removeHead();
			if (request == null) {
				return;
			}
			@Pc(40) PathingEntity entity;
			@Pc(29) int playerId;
			if (request.entityId < 0) {
				playerId = -request.entityId - 1;
				if (playerId == PlayerList.localPid) {
					entity = PlayerList.self;
				} else {
					entity = PlayerList.players[playerId];
				}
			} else {
				playerId = request.entityId - 1;
				entity = NpcList.npcs[playerId];
			}
			if (entity != null) {
				@Pc(63) LocType type = LocTypeList.get(request.locId);
				if (Player.currentLevel < 3) {
					// TODO why is this here?
				}
				@Pc(86) int width;
				@Pc(83) int length;
				if (request.angle == 1 || request.angle == 3) {
					length = type.width;
					width = type.length;
				} else {
					width = type.width;
					length = type.length;
				}
				@Pc(103) int centerX1 = (width + 1 >> 1) + request.x;
				@Pc(110) int centerX0 = (width >> 1) + request.x;
				@Pc(117) int centerZ0 = (length >> 1) + request.z;
				@Pc(126) int centerZ1 = (length + 1 >> 1) + request.z;
				@Pc(130) int[][] tileHeights = SceneGraph.tileHeights[Player.currentLevel];
				@Pc(157) int y = tileHeights[centerX1][centerZ1] + tileHeights[centerX0][centerZ1] + tileHeights[centerX0][centerZ0] + tileHeights[centerX1][centerZ0] >> 2;
				@Pc(159) Entity attachment = null;
				@Pc(164) int layer = Loc.LAYERS[request.shape];
				if (layer == 0) {
					@Pc(176) Wall wall = SceneGraph.getWall(Player.currentLevel, request.x, request.z);
					if (wall != null) {
						attachment = wall.primary;
					}
				} else if (layer == 1) {
					@Pc(231) WallDecor wallDecor = SceneGraph.getWallDecor(Player.currentLevel, request.x, request.z);
					if (wallDecor != null) {
						attachment = wallDecor.primary;
					}
				} else if (layer == 2) {
					@Pc(198) Scenery scenery = SceneGraph.getScenery(Player.currentLevel, request.x, request.z);
					if (scenery != null) {
						attachment = scenery.entity;
					}
				} else if (layer == 3) {
					@Pc(216) GroundDecor groundDecor = SceneGraph.getGroundDecor(Player.currentLevel, request.x, request.z);
					if (groundDecor != null) {
						attachment = groundDecor.entity;
					}
				}
				if (attachment != null) {
					ChangeLocRequest.push(Player.currentLevel, request.z, 0, request.x, request.resetLoops + 1, -1, layer, 0, request.setLoops + 1);
					entity.attachmentResetAt = request.resetLoops + Client.loop;
					entity.attachmentZFine = length * 64 + request.z * 128;
					entity.attachmentXFine = width * 64 + request.x * 128;
					entity.attachment = attachment;
					@Pc(292) int x0Delta = request.x0Delta;
					entity.attachmentY = y;
					entity.attachmentSetAt = Client.loop + request.setLoops;
					@Pc(304) int x1Delta = request.x1Delta;
					@Pc(307) int z0Delta = request.z0Delta;
					@Pc(310) int z1Delta = request.z1Delta;
					@Pc(316) int temp;
					if (x0Delta > x1Delta) {
						temp = x0Delta;
						x0Delta = x1Delta;
						x1Delta = temp;
					}
					entity.attachmentX1 = request.x + x1Delta;
					if (z0Delta > z1Delta) {
						temp = z0Delta;
						z0Delta = z1Delta;
						z1Delta = temp;
					}
					entity.attachmentZ0 = request.z + z0Delta;
					entity.attachmentZ1 = z1Delta + request.z;
					entity.atachmentX0 = request.x + x0Delta;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(IIIIIIIIIIIII)V")
	public static void push(@OriginalArg(0) int x1Delta, @OriginalArg(1) int setLoops, @OriginalArg(2) int entityId, @OriginalArg(3) int resetLoops, @OriginalArg(4) int z, @OriginalArg(5) int z1Delta, @OriginalArg(6) int angle, @OriginalArg(7) int x0Delta, @OriginalArg(8) int x, @OriginalArg(9) int shape, @OriginalArg(11) int z0Delta, @OriginalArg(12) int locId) {
		@Pc(7) AttachLocRequest req = new AttachLocRequest();
		req.angle = angle;
		req.resetLoops = resetLoops;
		req.setLoops = setLoops;
		req.z1Delta = z1Delta;
		req.entityId = entityId;
		req.x = x;
		req.z0Delta = z0Delta;
		req.z = z;
		req.locId = locId;
		req.x0Delta = x0Delta;
		req.x1Delta = x1Delta;
		req.shape = shape;
		queue.push(req);
	}
}
