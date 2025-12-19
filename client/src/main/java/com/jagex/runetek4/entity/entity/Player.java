package com.jagex.runetek4.entity.entity;

import com.jagex.runetek4.*;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.config.types.spotanim.SpotAnimType;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.spotanim.SpotAnimTypeList;
import com.jagex.runetek4.config.types.bas.BasType;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.game.logic.HintArrowManager;
import com.jagex.runetek4.game.inventory.Equipment;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.game.map.MapMarker;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.graphics.model.SoftwareModel;
import com.jagex.runetek4.graphics.lighting.ShadowModelList;
import com.jagex.runetek4.entity.loc.Loc;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.ui.component.MiniMap;
import com.jagex.runetek4.ui.component.Crosshair;
import com.jagex.runetek4.util.data.Base37;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!e")
public final class Player extends PathingEntity {

	@OriginalMember(owner = "runetek4.client!mj", name = "d", descriptor = "[Lclient!na;")
	public static final JString[] options = new JString[8];

	@OriginalMember(owner = "runetek4.client!a", name = "f", descriptor = "[Z")
	public static final boolean[] secondaryOptions = new boolean[8];

	@OriginalMember(owner = "runetek4.client!pg", name = "ab", descriptor = "[I")
	public static final int[] cursors = new int[8];

	@OriginalMember(owner = "runetek4.client!ba", name = "w", descriptor = "I")
    public static int inTutorialIsland = 0;

	@OriginalMember(owner = "runetek4.client!ee", name = "b", descriptor = "I")
	public static int currentLevel;

    @OriginalMember(owner = "runetek4.client!ea", name = "r", descriptor = "[[B")
    public static byte[][] aByteArrayArray8;

	@OriginalMember(owner = "runetek4.client!ib", name = "l", descriptor = "I")
	public static int anInt2863 = 0;

	@OriginalMember(owner = "client!bb", name = "E", descriptor = "I")
	public static int runEnergy = 0;

	@OriginalMember(owner = "runetek4.client!ug", name = "o", descriptor = "I")
	public static int weightCarried = 0;

	@OriginalMember(owner = "runetek4.client!sm", name = "k", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest lastLogAddress;

	@OriginalMember(owner = "runetek4.client!pa", name = "S", descriptor = "Lclient!na;")
	public static JString usernameInput = JString.EMPTY;

	@OriginalMember(owner = "runetek4.client!pa", name = "P", descriptor = "Lclient!na;")
	public static JString password = JString.EMPTY;

	@OriginalMember(owner = "client!em", name = "B", descriptor = "I")
	public static int systemUpdateTimer = 0;

	@OriginalMember(owner = "runetek4.client!jl", name = "H", descriptor = "I")
	public static int worldId = -1;

	@OriginalMember(owner = "runetek4.client!hm", name = "gb", descriptor = "J")
	public static long name37;

	@OriginalMember(owner = "client!e", name = "Bc", descriptor = "Lclient!hh;")
	public PlayerAppearance appearance;

	@OriginalMember(owner = "client!e", name = "Mc", descriptor = "Lclient!na;")
	public JString username;

	@OriginalMember(owner = "client!e", name = "tc", descriptor = "I")
	public int anInt1649 = -1;

	@OriginalMember(owner = "client!e", name = "wc", descriptor = "I")
	private int anInt1651 = 0;

	@OriginalMember(owner = "client!e", name = "uc", descriptor = "I")
	public int teamId = 0;

	@OriginalMember(owner = "client!e", name = "yc", descriptor = "I")
	public int combatLevel = 0;

	@OriginalMember(owner = "client!e", name = "Fc", descriptor = "I")
	public int combatLevelWithSummoning = 0;

	@OriginalMember(owner = "client!e", name = "Cc", descriptor = "I")
	public int anInt1654 = -1;

	@OriginalMember(owner = "client!e", name = "qc", descriptor = "I")
	public int anInt1648 = -1;

	@OriginalMember(owner = "client!e", name = "Pc", descriptor = "I")
	public int soundRadius = 0;

	@OriginalMember(owner = "client!e", name = "Hc", descriptor = "I")
	public int anInt1658 = -1;

	@OriginalMember(owner = "client!e", name = "Sc", descriptor = "I")
	public int combatRange = -1;

	@OriginalMember(owner = "client!e", name = "Oc", descriptor = "Z")
	public boolean lowMemory = false;

	@OriginalMember(owner = "client!e", name = "Xc", descriptor = "I")
	public int skill = 0;

	@OriginalMember(owner = "client!e", name = "Vc", descriptor = "I")
	public int anInt1669 = -1;

	@OriginalMember(owner = "client!e", name = "Wc", descriptor = "I")
	public int anInt1670 = -1;

    @OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(ILclient!e;)I")
    public static int getSound(@OriginalArg(1) Player player) {
        @Pc(14) int soundId = player.anInt1654;
        @Pc(18) BasType baseAnimSet = player.getBasType();
        if (baseAnimSet.readyanim == player.movementSeqId) {
            soundId = player.anInt1648;
        } else if (baseAnimSet.runanim == player.movementSeqId || player.movementSeqId == baseAnimSet.runanim_b || player.movementSeqId == baseAnimSet.runanim_r || baseAnimSet.runanim_l == player.movementSeqId) {
            soundId = player.anInt1670;
        } else if (player.movementSeqId == baseAnimSet.crawlanim || player.movementSeqId == baseAnimSet.crawlanim_b || player.movementSeqId == baseAnimSet.crawlanim_r || player.movementSeqId == baseAnimSet.crawlanim_l) {
            soundId = player.anInt1658;
        }
        return soundId;
    }

	@OriginalMember(owner = "runetek4.client!pa", name = "a", descriptor = "(IIILclient!e;)V")
	public static void animate(@OriginalArg(1) int delay, @OriginalArg(2) int sequenceId, @OriginalArg(3) Player player) {
		if (sequenceId == player.primarySeqId && sequenceId != -1) {
			@Pc(89) SeqType sequence = SeqTypeList.get(sequenceId);
			@Pc(92) int exactMoveType = sequence.exactmove;
			if (exactMoveType == 1) {
				player.animationDelay = delay;
				player.animationFrame = 0;
				player.animationDirection = 1;
				player.animationFrameDelay = 0;
				player.animationLoopCounter = 0;
				SoundPlayer.playSeqSound(player.zFine, sequence, player.xFine, PlayerList.self == player, player.animationFrameDelay);
			}
			if (exactMoveType == 2) {
				player.animationLoopCounter = 0;
			}
		} else if (sequenceId == -1 || player.primarySeqId == -1 || SeqTypeList.get(sequenceId).priority >= SeqTypeList.get(player.primarySeqId).priority) {
			player.animationDirection = 1;
			player.animationFrameDelay = 0;
			player.animationDelay = delay;
			player.movementQueueSnapshot = player.movementQueueSize;
			player.animationLoopCounter = 0;
			player.animationFrame = 0;
			player.primarySeqId = sequenceId;
			if (player.primarySeqId != -1) {
				SoundPlayer.playSeqSound(player.zFine, SeqTypeList.get(player.primarySeqId), player.xFine, player == PlayerList.self, player.animationFrameDelay);
			}
		}
	}

	@OriginalMember(owner = "client!ci", name = "a", descriptor = "([I[ILclient!e;B[I)V")
	public static void updateLayeredAnimations(@OriginalArg(0) int[] delays, @OriginalArg(1) int[] sequenceIds, @OriginalArg(2) Player player, @OriginalArg(4) int[] slotMasks) {
		for (@Pc(7) int i = 0; i < sequenceIds.length; i++) {
			@Pc(20) int sequenceId = sequenceIds[i];
			@Pc(24) int slotMask = slotMasks[i];
			@Pc(28) int delay = delays[i];
			@Pc(30) int slotIndex = 0;
			while (slotMask != 0 && slotIndex < player.layeredAnimations.length) {
				if ((slotMask & 0x1) != 0) {
					if (sequenceId == -1) {
						player.layeredAnimations[slotIndex] = null;
					} else {
						@Pc(68) SeqType sequence = SeqTypeList.get(sequenceId);
						@Pc(71) int exactMoveType = sequence.exactmove;
						@Pc(76) PathingEntityAnimation animationState = player.layeredAnimations[slotIndex];
						if (animationState != null) {
							if (sequenceId == animationState.sequenceId) {
								if (exactMoveType == 0) {
									animationState = player.layeredAnimations[slotIndex] = null;
								} else if (exactMoveType == 1) {
									animationState.frameTime = 0;
									animationState.direction = 1;
									animationState.frameIndex = 0;
									animationState.delay = delay;
									animationState.loopCount = 0;
									SoundPlayer.playSeqSound(player.zFine, sequence, player.xFine, player == PlayerList.self, 0);
								} else if (exactMoveType == 2) {
									animationState.frameTime = 0;
								}
							} else if (sequence.priority >= SeqTypeList.get(animationState.sequenceId).priority) {
								animationState = player.layeredAnimations[slotIndex] = null;
							}
						}
						if (animationState == null) {
							animationState = player.layeredAnimations[slotIndex] = new PathingEntityAnimation();
							animationState.sequenceId = sequenceId;
							animationState.direction = 1;
							animationState.loopCount = 0;
							animationState.delay = delay;
							animationState.frameIndex = 0;
							animationState.frameTime = 0;
							SoundPlayer.playSeqSound(player.zFine, sequence, player.xFine, player == PlayerList.self, 0);
						}
					}
				}
				slotIndex++;
				slotMask >>>= 0x1;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!um", name = "a", descriptor = "(Z)V")
	public static void updateTutorialIslandStatus() {
		inTutorialIsland = 0;
		@Pc(17) int playerWorldX = Camera.sceneBaseTileX + (PlayerList.self.xFine >> 7);
		@Pc(25) int playerWorldZ = (PlayerList.self.zFine >> 7) + Camera.sceneBaseTileZ;
		if (playerWorldX >= 3053 && playerWorldX <= 3156 && playerWorldZ >= 3056 && playerWorldZ <= 3136) {
			inTutorialIsland = 1;
		}
		if (playerWorldX >= 3072 && playerWorldX <= 3118 && playerWorldZ >= 9492 && playerWorldZ <= 9535) {
			inTutorialIsland = 1;
		}
		if (inTutorialIsland == 1 && playerWorldX >= 3139 && playerWorldX <= 3199 && playerWorldZ >= 3008 && playerWorldZ <= 3062) {
			inTutorialIsland = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(IIIIB)V")
	public static void method2310(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (Crosshair.CrosshairMode == 1) {
			Sprites.crosses[Crosshair.CrosshairCycle / 100].render(Crosshair.x - 8, Crosshair.y + -8);
		}
		if (Crosshair.CrosshairMode == 2) {
			Sprites.crosses[Crosshair.CrosshairCycle / 100 + 4].render(Crosshair.x - 8, Crosshair.y + -8);
		}
		updateTutorialIslandStatus();
	}

	@OriginalMember(owner = "client!e", name = "c", descriptor = "(B)I")
	@Override
	public int getSize() {
		return this.appearance == null || this.appearance.npcId == -1 ? super.getSize() : NpcTypeList.get(this.appearance.npcId).size;
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(I)I")
	@Override
	protected int getBasId() {
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(ILclient!wa;)V")
	public void decodeAppearance(@OriginalArg(1) Packet arg0) {
		arg0.offset = 0;
		@Pc(20) int appearanceFlags = arg0.g1();
		@Pc(22) int npcTransformId = -1;
		@Pc(26) int genderFlag = appearanceFlags & 0x1;
		@Pc(37) boolean hasSummoningLevel = (appearanceFlags & 0x4) != 0;
		@Pc(41) int oldSize = super.getSize();
		@Pc(44) int[] equipmentIds = new int[12];
		this.setSize((appearanceFlags >> 3 & 0x7) + 1);
		this.anInt1651 = appearanceFlags >> 6 & 0x3;
		this.xFine += (this.getSize() - oldSize) * 64;
		this.zFine += (this.getSize() - oldSize) * 64;
		this.anInt1669 = arg0.g1s();
		this.anInt1649 = arg0.g1s();
		this.teamId = 0;
		@Pc(111) int local111;
		@Pc(127) int local127;
		@Pc(134) int local134;
		@Pc(175) int local175;
		for (@Pc(102) int local102 = 0; local102 < 12; local102++) {
			local111 = arg0.g1();
			if (local111 == 0) {
				equipmentIds[local102] = 0;
			} else {
				local127 = arg0.g1();
				local134 = (local111 << 8) + local127;
				if (local102 == 0 && local134 == 65535) {
					npcTransformId = arg0.g2();
					this.teamId = arg0.g1();
					break;
				}
				if (local134 >= 32768) {
					local134 = Equipment.equipmentObjectIds[local134 - 32768];
					equipmentIds[local102] = local134 | 0x40000000;
					local175 = ObjTypeList.get(local134).team;
					if (local175 != 0) {
						this.teamId = local175;
					}
				} else {
					equipmentIds[local102] = local134 - 256 | Integer.MIN_VALUE;
				}
			}
		}
		@Pc(197) int[] bodyColors = new int[5];
		for (local111 = 0; local111 < 5; local111++) {
			local127 = arg0.g1();
			if (local127 < 0 || local127 >= PlayerAppearance.destinationBodyColors[local111].length) {
				local127 = 0;
			}
			bodyColors[local111] = local127;
		}
		this.anInt3365 = arg0.g2();
		@Pc(236) long local236 = arg0.g8();
		this.username = Base37.fromBase37(local236).toTitleCase();
		this.combatLevel = arg0.g1();
		if (hasSummoningLevel) {
			this.skill = arg0.g2();
			this.combatLevelWithSummoning = this.combatLevel;
			this.combatRange = -1;
		} else {
			this.skill = 0;
			this.combatLevelWithSummoning = arg0.g1();
			this.combatRange = arg0.g1();
			if (this.combatRange == 255) {
				this.combatRange = -1;
			}
		}
		local134 = this.soundRadius;
		this.soundRadius = arg0.g1();
		if (this.soundRadius == 0) {
			AreaSoundManager.remove(this);
		} else {
			@Pc(309) int local309 = this.anInt1658;
			@Pc(312) int local312 = this.anInt1654;
			@Pc(315) int local315 = this.anInt1670;
			local175 = this.anInt1648;
			this.anInt1648 = arg0.g2();
			this.anInt1658 = arg0.g2();
			this.anInt1654 = arg0.g2();
			this.anInt1670 = arg0.g2();
			if (this.soundRadius != local134 || this.anInt1648 != local175 || this.anInt1658 != local309 || local312 != this.anInt1654 || this.anInt1670 != local315) {
				AreaSoundManager.updatePlayerAreaSound(this);
			}
		}
		if (this.appearance == null) {
			this.appearance = new PlayerAppearance();
		}
		local175 = this.appearance.npcId;
		this.appearance.set(bodyColors, npcTransformId, genderFlag == 1, equipmentIds, this.anInt3365);
		if (local175 != npcTransformId) {
			this.xFine = this.movementQueueX[0] * 128 + this.getSize() * 64;
			this.zFine = this.movementQueueZ[0] * 128 + this.getSize() * 64;
		}
		if (this.particleSystem != null) {
			this.particleSystem.method1646();
		}
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.appearance == null) {
			return;
		}
		@Pc(25) SeqType local25 = this.primarySeqId != -1 && this.animationDelay == 0 ? SeqTypeList.get(this.primarySeqId) : null;
		@Pc(54) SeqType local54 = this.movementSeqId == -1 || this.lowMemory || this.movementSeqId == this.getBasType().readyanim && local25 != null ? null : SeqTypeList.get(this.movementSeqId);
		@Pc(76) Model local76 = this.appearance.createAnimatedBodyModel(this.layeredAnimations, this.animationDirection, local54, local25, this.anInt3396, this.anInt3388, this.animationFrame, this.animationFrameDelay, this.anInt3407);
		@Pc(79) int local79 = PlayerAppearance.getModelCacheSize();
		if (GlRenderer.enabled && GameShell.maxMemory < 96 && local79 > 50) {
			GlRenderer.updateOpenGLModelBuffers();
		}
		@Pc(102) int local102;
		if (Client.modeWhat != 0 && local79 < 50) {
			local102 = 50 - local79;
			while (anInt2863 < local102) {
				aByteArrayArray8[anInt2863] = new byte[102400];
				anInt2863++;
			}
			while (anInt2863 > local102) {
				anInt2863--;
				aByteArrayArray8[anInt2863] = null;
			}
		}
		if (local76 == null) {
			return;
		}
		this.minY = local76.getMinY();
		@Pc(184) Model model;
		if (Preferences.characterShadowsOn && (this.appearance.npcId == -1 || NpcTypeList.get(this.appearance.npcId).spotshadow)) {
			model = ShadowModelList.method1043(160, this.seqStretches, local54 == null ? local25 : local54, this.xFine, 0, this.zFine, 0, 1, local76, arg0, local54 == null ? this.animationFrameDelay : this.anInt3407, this.groundHeight, 240);
			if (GlRenderer.enabled) {
				@Pc(188) float local188 = GlRenderer.method4179();
				@Pc(190) float local190 = GlRenderer.method4166();
				GlRenderer.disableDepthMask();
				GlRenderer.method4152(local188, local190 - 150.0F);
				model.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
				GlRenderer.enableDepthMask();
				GlRenderer.method4152(local188, local190);
			} else {
				model.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
			}
		}
		if (PlayerList.self == this) {
			for (local102 = MiniMap.hintMapMarkers.length - 1; local102 >= 0; local102--) {
				@Pc(245) MapMarker local245 = MiniMap.hintMapMarkers[local102];
				if (local245 != null && local245.playerModelId != -1) {
					@Pc(291) int anchorX;
					@Pc(302) int anchorY;
					if (local245.type == 1 && local245.actorTargetId >= 0 && NpcList.npcs.length > local245.actorTargetId) {
						@Pc(278) Npc npc = NpcList.npcs[local245.actorTargetId];
						if (npc != null) {
							anchorX = npc.xFine / 32 - PlayerList.self.xFine / 32;
							anchorY = npc.zFine / 32 - PlayerList.self.zFine / 32;
							this.drawOnMinimap(null, anchorY, local76, anchorX, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.playerModelId, arg2, arg6);
						}
					}
					if (local245.type == 2) {
						@Pc(340) int local340 = (local245.targetX - Camera.sceneBaseTileX) * 4 + 2 - PlayerList.self.xFine / 32;
						anchorX = (local245.anInt4046 - Camera.sceneBaseTileZ) * 4 + 2 - PlayerList.self.zFine / 32;
						this.drawOnMinimap(null, anchorX, local76, local340, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.playerModelId, arg2, arg6);
					}
					if (local245.type == 10 && local245.actorTargetId >= 0 && PlayerList.players.length > local245.actorTargetId) {
						@Pc(395) Player player = PlayerList.players[local245.actorTargetId];
						if (player != null) {
							anchorX = player.xFine / 32 - PlayerList.self.xFine / 32;
							anchorY = player.zFine / 32 - PlayerList.self.zFine / 32;
							this.drawOnMinimap(null, anchorY, local76, anchorX, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.playerModelId, arg2, arg6);
						}
					}
				}
			}
		}
		this.method2687(local76);
		this.method2685(local76, arg0);
		model = null;
		if (!this.lowMemory && this.spotAnimId != -1 && this.spotanimId != -1) {
			@Pc(471) SpotAnimType spotanim = SpotAnimTypeList.get(this.spotAnimId);
			model = spotanim.constructModel(this.anInt3418, this.spotanimId, this.anInt3361);
			if (model != null) {
				model.translate(0, -this.spotAnimY, 0);
				if (spotanim.aBoolean100) {
					if (PathingEntity.anInt2640 != 0) {
						model.rotateX(PathingEntity.anInt2640);
					}
					if (PathingEntity.anInt2680 != 0) {
						model.rotateZ(PathingEntity.anInt2680);
					}
					if (PathingEntity.anInt1938 != 0) {
						model.translate(0, PathingEntity.anInt1938, 0);
					}
				}
			}
		}
		@Pc(515) Model loc = null;
		if (!this.lowMemory && this.attachment != null) {
			if (Client.loop >= this.attachmentResetAt) {
				this.attachment = null;
			}
			if (this.attachmentSetAt <= Client.loop && this.attachmentResetAt > Client.loop) {
				if (this.attachment instanceof Loc) {
					loc = (Model) ((Loc) this.attachment).getModel();
				} else {
					loc = (Model) this.attachment;
				}
				loc.translate(this.attachmentXFine - this.xFine, this.attachmentY + -this.groundHeight, this.attachmentZFine - this.zFine);
				if (this.dstYaw == 512) {
					loc.method4578();
				} else if (this.dstYaw == 1024) {
					loc.updateBounds();
				} else if (this.dstYaw == 1536) {
					loc.rotateCounterClockwise();
				}
			}
		}
		if (GlRenderer.enabled) {
			local76.pickable = true;
			local76.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
			if (model != null) {
				model.pickable = true;
				model.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
			}
		} else {
			if (model != null) {
				local76 = ((SoftwareModel) local76).method4588(model);
			}
			if (loc != null) {
				local76 = ((SoftwareModel) local76).method4588(loc);
			}
			local76.pickable = true;
			local76.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
		}
		if (loc == null) {
			return;
		}
		if (this.dstYaw == 512) {
			loc.rotateCounterClockwise();
		} else if (this.dstYaw == 1024) {
			loc.updateBounds();
		} else if (this.dstYaw == 1536) {
			loc.method4578();
		}
		loc.translate(this.xFine - this.attachmentXFine, -this.attachmentY + this.groundHeight, this.zFine - this.attachmentZFine);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(Lclient!ga;ILclient!ak;IIIIIIIIIIII)V")
	private void drawOnMinimap(@OriginalArg(0) ParticleSystem arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Model arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12, @OriginalArg(14) int arg13) {
		@Pc(12) int local12 = arg3 * arg3 + arg1 * arg1;
		if (local12 < 16 || local12 > 360000) {
			return;
		}
		@Pc(34) int local34 = (int) (Math.atan2(arg3, arg1) * 325.949D) & 0x7FF;
		@Pc(46) Model local46 = HintArrowManager.getModel(local34, this.zFine, arg11, this.xFine, arg2, this.groundHeight);
		if (local46 == null) {
			return;
		}
		if (!GlRenderer.enabled) {
			local46.render(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
			return;
		}
		@Pc(52) float local52 = GlRenderer.method4179();
		@Pc(54) float local54 = GlRenderer.method4166();
		GlRenderer.disableDepthMask();
		GlRenderer.method4152(local52, local54 - 150.0F);
		local46.render(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
		GlRenderer.enableDepthMask();
		GlRenderer.method4152(local52, local54);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(B)Z")
	@Override
	public boolean isVisible() {
		return this.appearance != null;
	}

	@OriginalMember(owner = "client!e", name = "e", descriptor = "(I)Lclient!na;")
	public JString getUsername() {
		@Pc(2) JString local2 = this.username;
		if (PlayerList.playerNames != null) {
			local2 = JString.concatenate(new JString[] { PlayerList.playerNames[this.anInt1651], local2 });
		}
		if (PlayerList.playerNames2 != null) {
			local2 = JString.concatenate(new JString[] { local2, PlayerList.playerNames2[this.anInt1651] });
		}
		return local2;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIII)V")
	@Override
	public void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(BIZI)V")
	public void teleport(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2) {
		super.teleport(this.getSize(), arg0, arg2, arg1);
	}

	@OriginalMember(owner = "client!e", name = "finalize", descriptor = "()V")
	@Override
	protected void finalize() {
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "()I")
	@Override
	public int getMinY() {
		return this.minY;
	}
}