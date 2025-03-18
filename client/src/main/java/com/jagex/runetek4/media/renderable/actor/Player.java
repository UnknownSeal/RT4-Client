package com.jagex.runetek4.media.renderable.actor;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import com.jagex.runetek4.scene.Scene;
import com.jagex.runetek4.scene.tile.SceneTile;
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
    public static int overrideChat = 0;
	@OriginalMember(owner = "runetek4.client!ee", name = "b", descriptor = "I")
	public static int plane;
    @OriginalMember(owner = "runetek4.client!ea", name = "r", descriptor = "[[B")
    public static byte[][] aByteArrayArray8;
	@OriginalMember(owner = "runetek4.client!ib", name = "l", descriptor = "I")
	public static int anInt2863 = 0;
	@OriginalMember(owner = "client!bb", name = "E", descriptor = "I")
	public static int energy = 0;
	@OriginalMember(owner = "runetek4.client!ug", name = "o", descriptor = "I")
	public static int weightCarried = 0;
	@OriginalMember(owner = "runetek4.client!sm", name = "k", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest lastLogAddress;
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
	public int anInt1656 = 0;

	@OriginalMember(owner = "client!e", name = "Cc", descriptor = "I")
	public int anInt1654 = -1;

	@OriginalMember(owner = "client!e", name = "qc", descriptor = "I")
	public int anInt1648 = -1;

	@OriginalMember(owner = "client!e", name = "Pc", descriptor = "I")
	public int soundRadius = 0;

	@OriginalMember(owner = "client!e", name = "Hc", descriptor = "I")
	public int anInt1658 = -1;

	@OriginalMember(owner = "client!e", name = "Sc", descriptor = "I")
	public int anInt1667 = -1;

	@OriginalMember(owner = "client!e", name = "Oc", descriptor = "Z")
	public boolean lowMemory = false;

	@OriginalMember(owner = "client!e", name = "Xc", descriptor = "I")
	public int anInt1671 = 0;

	@OriginalMember(owner = "client!e", name = "Vc", descriptor = "I")
	public int anInt1669 = -1;

	@OriginalMember(owner = "client!e", name = "Wc", descriptor = "I")
	public int anInt1670 = -1;

    @OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(ILclient!e;)I")
    public static int getSound(@OriginalArg(1) Player arg0) {
        @Pc(14) int local14 = arg0.anInt1654;
        @Pc(18) BasType local18 = arg0.getBasType();
        if (local18.idleAnimationId == arg0.movementSeqId) {
            local14 = arg0.anInt1648;
        } else if (local18.runAnimationId == arg0.movementSeqId || arg0.movementSeqId == local18.runFullTurnAnimationId || arg0.movementSeqId == local18.runCWTurnAnimationId || local18.runCCWTurnAnimationId == arg0.movementSeqId) {
            local14 = arg0.anInt1670;
        } else if (arg0.movementSeqId == local18.slowWalkAnimationId || arg0.movementSeqId == local18.slowWalkFullTurnAnimationId || arg0.movementSeqId == local18.slowWalkCWTurnAnimationId || arg0.movementSeqId == local18.slowWalkCCWTurnAnimationId) {
            local14 = arg0.anInt1658;
        }
        return local14;
    }

	@OriginalMember(owner = "client!bf", name = "c", descriptor = "(I)V")
	public static void method501() {
		if (!GlRenderer.enabled || PreciseSleep.aBoolean252) {
			return;
		}
		@Pc(14) SceneTile[][][] local14 = Static130.levelTiles;
		for (@Pc(22) int local22 = 0; local22 < local14.length; local22++) {
			@Pc(30) SceneTile[][] local30 = local14[local22];
			for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
				for (@Pc(42) int local42 = 0; local42 < local30[local32].length; local42++) {
					@Pc(54) SceneTile local54 = local30[local32][local42];
					if (local54 != null) {
						@Pc(71) GlModel local71;
						if (local54.groundDecor != null && local54.groundDecor.entity instanceof GlModel) {
							local71 = (GlModel) local54.groundDecor.entity;
							if ((local54.groundDecor.key & Long.MIN_VALUE) == 0L) {
								local71.method4111(false, true, true, false, true, true);
							} else {
								local71.method4111(true, true, true, true, true, true);
							}
						}
						if (local54.wallDecor != null) {
							if (local54.wallDecor.primary instanceof GlModel) {
								local71 = (GlModel) local54.wallDecor.primary;
								if ((local54.wallDecor.key & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.wallDecor.secondary instanceof GlModel) {
								local71 = (GlModel) local54.wallDecor.secondary;
								if ((Long.MIN_VALUE & local54.wallDecor.key) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						if (local54.wall != null) {
							if (local54.wall.primary instanceof GlModel) {
								local71 = (GlModel) local54.wall.primary;
								if ((local54.wall.aLong107 & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.wall.modelB instanceof GlModel) {
								local71 = (GlModel) local54.wall.modelB;
								if ((Long.MIN_VALUE & local54.wall.aLong107) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						for (@Pc(270) int local270 = 0; local270 < local54.entityCount; local270++) {
							if (local54.sceneries[local270].entity instanceof GlModel) {
								@Pc(293) GlModel local293 = (GlModel) local54.sceneries[local270].entity;
								if ((Long.MIN_VALUE & local54.sceneries[local270].hash) == 0L) {
									local293.method4111(false, true, true, false, true, true);
								} else {
									local293.method4111(true, true, true, true, true, true);
								}
							}
						}
					}
				}
			}
		}
		PreciseSleep.aBoolean252 = true;
	}

	@OriginalMember(owner = "client!e", name = "c", descriptor = "(B)I")
	@Override
	public int getSize() {
		return this.appearance == null || this.appearance.transformationNpcId == -1 ? super.getSize() : NpcType.getDefinition(this.appearance.transformationNpcId).size;
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(I)I")
	@Override
	protected int method2688() {
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(ILclient!wa;)V")
	public void read(@OriginalArg(1) Packet arg0) {
		arg0.offset = 0;
		@Pc(20) int local20 = arg0.g1();
		@Pc(22) int local22 = -1;
		@Pc(26) int local26 = local20 & 0x1;
		@Pc(37) boolean local37 = (local20 & 0x4) != 0;
		@Pc(41) int local41 = super.getSize();
		@Pc(44) int[] local44 = new int[12];
		this.setSize((local20 >> 3 & 0x7) + 1);
		this.anInt1651 = local20 >> 6 & 0x3;
		this.xFine += (this.getSize() - local41) * 64;
		this.zFine += (this.getSize() - local41) * 64;
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
				local44[local102] = 0;
			} else {
				local127 = arg0.g1();
				local134 = (local111 << 8) + local127;
				if (local102 == 0 && local134 == 65535) {
					local22 = arg0.g2();
					this.teamId = arg0.g1();
					break;
				}
				if (local134 >= 32768) {
					local134 = Equipment.objIds[local134 - 32768];
					local44[local102] = local134 | 0x40000000;
					local175 = ObjTypeList.get(local134).team;
					if (local175 != 0) {
						this.teamId = local175;
					}
				} else {
					local44[local102] = local134 - 256 | Integer.MIN_VALUE;
				}
			}
		}
		@Pc(197) int[] local197 = new int[5];
		for (local111 = 0; local111 < 5; local111++) {
			local127 = arg0.g1();
			if (local127 < 0 || local127 >= PlayerAppearance.destinationBodyColors[local111].length) {
				local127 = 0;
			}
			local197[local111] = local127;
		}
		this.anInt3365 = arg0.g2();
		@Pc(236) long local236 = arg0.g8();
		this.username = Base37.decode37(local236).toTitleCase();
		this.combatLevel = arg0.g1();
		if (local37) {
			this.anInt1671 = arg0.g2();
			this.anInt1656 = this.combatLevel;
			this.anInt1667 = -1;
		} else {
			this.anInt1671 = 0;
			this.anInt1656 = arg0.g1();
			this.anInt1667 = arg0.g1();
			if (this.anInt1667 == 255) {
				this.anInt1667 = -1;
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
				Static214.method4359(this);
			}
		}
		if (this.appearance == null) {
			this.appearance = new PlayerAppearance();
		}
		local175 = this.appearance.transformationNpcId;
		this.appearance.set(local197, local22, local26 == 1, local44, this.anInt3365);
		if (local175 != local22) {
			this.xFine = this.movementQueueX[0] * 128 + this.getSize() * 64;
			this.zFine = this.movementQueueZ[0] * 128 + this.getSize() * 64;
		}
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.appearance == null) {
			return;
		}
		@Pc(25) SeqType local25 = this.primarySeqId != -1 && this.anInt3420 == 0 ? SeqTypeList.getAnimationSequence(this.primarySeqId) : null;
		@Pc(54) SeqType local54 = this.movementSeqId == -1 || this.lowMemory || this.movementSeqId == this.getBasType().idleAnimationId && local25 != null ? null : SeqTypeList.getAnimationSequence(this.movementSeqId);
		@Pc(76) Model local76 = this.appearance.method1954(this.aClass147Array3, this.anInt3373, local54, local25, this.anInt3396, this.anInt3388, this.anInt3360, this.anInt3425, this.anInt3407);
		@Pc(79) int local79 = Static198.method1029();
		if (GlRenderer.enabled && Static238.anInt5316 < 96 && local79 > 50) {
			method501();
		}
		@Pc(102) int local102;
		if (client.modeWhat != 0 && local79 < 50) {
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
		this.height = local76.getMinY();
		@Pc(184) Model model;
		if (Static209.aBoolean240 && (this.appearance.transformationNpcId == -1 || NpcType.getDefinition(this.appearance.transformationNpcId).spotshadow)) {
			model = Scene.method1043(160, this.seqStretches, local54 == null ? local25 : local54, this.xFine, 0, this.zFine, 0, 1, local76, arg0, local54 == null ? this.anInt3425 : this.anInt3407, this.y, 240);
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
				@Pc(245) Class102 local245 = MiniMap.hintMapMarkers[local102];
				if (local245 != null && local245.anInt4052 != -1) {
					@Pc(291) int anchorX;
					@Pc(302) int anchorY;
					if (local245.headIconDrawType == 1 && local245.hintIconNpcTarget >= 0 && NpcList.npcs.length > local245.hintIconNpcTarget) {
						@Pc(278) Npc npc = NpcList.npcs[local245.hintIconNpcTarget];
						if (npc != null) {
							anchorX = npc.xFine / 32 - PlayerList.self.xFine / 32;
							anchorY = npc.zFine / 32 - PlayerList.self.zFine / 32;
							this.drawOnMinimap(null, anchorY, local76, anchorX, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
						}
					}
					if (local245.headIconDrawType == 2) {
						@Pc(340) int local340 = (local245.anInt4053 - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
						anchorX = (local245.anInt4046 - Camera.originZ) * 4 + 2 - PlayerList.self.zFine / 32;
						this.drawOnMinimap(null, anchorX, local76, local340, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
					}
					if (local245.headIconDrawType == 10 && local245.hintIconNpcTarget >= 0 && PlayerList.players.length > local245.hintIconNpcTarget) {
						@Pc(395) Player player = PlayerList.players[local245.hintIconNpcTarget];
						if (player != null) {
							anchorX = player.xFine / 32 - PlayerList.self.xFine / 32;
							anchorY = player.zFine / 32 - PlayerList.self.zFine / 32;
							this.drawOnMinimap(null, anchorY, local76, anchorX, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
						}
					}
				}
			}
		}
		this.method2687(local76);
		this.method2685(local76, arg0);
		model = null;
		if (!this.lowMemory && this.spotanimFrame != -1 && this.spotanimId != -1) {
			@Pc(471) SpotAnimDefinition spotanim = Static34.method877(this.spotanimFrame);
			model = spotanim.getModel(this.anInt3418, this.spotanimId, this.anInt3361);
			if (model != null) {
				model.translate(0, -this.spotanimOffset, 0);
				if (spotanim.aBoolean100) {
					if (Static101.anInt2640 != 0) {
						model.method4574(Static101.anInt2640);
					}
					if (Static102.anInt2680 != 0) {
						model.method4564(Static102.anInt2680);
					}
					if (Static62.anInt1938 != 0) {
						model.translate(0, Static62.anInt1938, 0);
					}
				}
			}
		}
		@Pc(515) Model loc = null;
		if (!this.lowMemory && this.attachment != null) {
			if (client.loop >= this.attachmentResetAt) {
				this.attachment = null;
			}
			if (this.attachmentSetAt <= client.loop && this.attachmentResetAt > client.loop) {
				if (this.attachment instanceof Loc) {
					loc = (Model) ((Loc) this.attachment).method1049();
				} else {
					loc = (Model) this.attachment;
				}
				loc.translate(this.attachmentXFine - this.xFine, this.attachmentY + -this.y, this.attachmentZFine - this.zFine);
				if (this.dstYaw == 512) {
					loc.method4578();
				} else if (this.dstYaw == 1024) {
					loc.method4552();
				} else if (this.dstYaw == 1536) {
					loc.method4563();
				}
			}
		}
		if (GlRenderer.enabled) {
			local76.pickable = true;
			local76.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			if (model != null) {
				model.pickable = true;
				model.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			}
		} else {
			if (model != null) {
				local76 = ((SoftwareModel) local76).method4588(model);
			}
			if (loc != null) {
				local76 = ((SoftwareModel) local76).method4588(loc);
			}
			local76.pickable = true;
			local76.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		}
		if (loc == null) {
			return;
		}
		if (this.dstYaw == 512) {
			loc.method4563();
		} else if (this.dstYaw == 1024) {
			loc.method4552();
		} else if (this.dstYaw == 1536) {
			loc.method4578();
		}
		loc.translate(this.xFine - this.attachmentXFine, -this.attachmentY + this.y, this.zFine - this.attachmentZFine);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(Lclient!ga;ILclient!ak;IIIIIIIIIIII)V")
	private void drawOnMinimap(@OriginalArg(0) ParticleSystem arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Model arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12, @OriginalArg(14) int arg13) {
		@Pc(12) int local12 = arg3 * arg3 + arg1 * arg1;
		if (local12 < 16 || local12 > 360000) {
			return;
		}
		@Pc(34) int local34 = (int) (Math.atan2(arg3, arg1) * 325.949D) & 0x7FF;
		@Pc(46) Model local46 = Static220.method3800(local34, this.zFine, arg11, this.xFine, arg2, this.y);
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
		if (Static103.aClass100Array88 != null) {
			local2 = JString.concatenate(new JString[] { Static103.aClass100Array88[this.anInt1651], local2 });
		}
		if (Static263.aClass100Array174 != null) {
			local2 = JString.concatenate(new JString[] { local2, Static263.aClass100Array174[this.anInt1651] });
		}
		return local2;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
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
		return this.height;
	}
}