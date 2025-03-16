package com.jagex.runetek4.media.renderable.actor;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.ActorDefinition;
import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.dash3d.entity.NPCRenderable;
import com.jagex.runetek4.dash3d.entity.Actor;
import com.jagex.runetek4.game.config.bastype.BASType;
import com.jagex.runetek4.cache.media.AnimationSequence;
import com.jagex.runetek4.game.world.entity.PlayerModel;
import com.jagex.runetek4.scene.Scene;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!e")
public final class Player extends Actor {

	@OriginalMember(owner = "client!ch", name = "v", descriptor = "[B")
	public static final byte[] aByteArray12 = new byte[] { 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
	@OriginalMember(owner = "runetek4.client!ba", name = "w", descriptor = "I")
    public static int overrideChat = 0;
    @OriginalMember(owner = "client!e", name = "Bc", descriptor = "Lclient!hh;")
	public PlayerModel model;

	@OriginalMember(owner = "client!e", name = "Mc", descriptor = "Lclient!na;")
	public JString name;

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
	public int anInt1664 = 0;

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
        @Pc(18) BASType local18 = arg0.method2681();
        if (local18.anInt1037 == arg0.secondarySeqId) {
            local14 = arg0.anInt1648;
        } else if (local18.anInt1058 == arg0.secondarySeqId || arg0.secondarySeqId == local18.anInt1054 || arg0.secondarySeqId == local18.anInt1045 || local18.anInt1043 == arg0.secondarySeqId) {
            local14 = arg0.anInt1670;
        } else if (arg0.secondarySeqId == local18.anInt1062 || arg0.secondarySeqId == local18.anInt1042 || arg0.secondarySeqId == local18.anInt1048 || arg0.secondarySeqId == local18.anInt1066) {
            local14 = arg0.anInt1658;
        }
        return local14;
    }

    @OriginalMember(owner = "client!e", name = "c", descriptor = "(B)I")
	@Override
	public int size() {
		return this.model == null || this.model.transformationNpcId == -1 ? super.size() : ActorDefinition.getDefinition(this.model.transformationNpcId).size;
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(I)I")
	@Override
	protected int method2688() {
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(ILclient!wa;)V")
	public void read(@OriginalArg(1) Packet arg0) {
		arg0.position = 0;
		@Pc(20) int local20 = arg0.g1();
		@Pc(22) int local22 = -1;
		@Pc(26) int local26 = local20 & 0x1;
		@Pc(37) boolean local37 = (local20 & 0x4) != 0;
		@Pc(41) int local41 = super.size();
		@Pc(44) int[] local44 = new int[12];
		this.setSize((local20 >> 3 & 0x7) + 1);
		this.anInt1651 = local20 >> 6 & 0x3;
		this.x += (this.size() - local41) * 64;
		this.z += (this.size() - local41) * 64;
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
					local134 = Static234.anIntArray455[local134 - 32768];
					local44[local102] = local134 | 0x40000000;
					local175 = Static71.get(local134).team;
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
			if (local127 < 0 || local127 >= Static33.aShortArrayArray2[local111].length) {
				local127 = 0;
			}
			local197[local111] = local127;
		}
		this.anInt3365 = arg0.g2();
		@Pc(236) long local236 = arg0.g8();
		this.name = Static79.decode37(local236).method3125();
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
		local134 = this.anInt1664;
		this.anInt1664 = arg0.g1();
		if (this.anInt1664 == 0) {
			Static271.method4597(this);
		} else {
			@Pc(309) int local309 = this.anInt1658;
			@Pc(312) int local312 = this.anInt1654;
			@Pc(315) int local315 = this.anInt1670;
			local175 = this.anInt1648;
			this.anInt1648 = arg0.g2();
			this.anInt1658 = arg0.g2();
			this.anInt1654 = arg0.g2();
			this.anInt1670 = arg0.g2();
			if (this.anInt1664 != local134 || this.anInt1648 != local175 || this.anInt1658 != local309 || local312 != this.anInt1654 || this.anInt1670 != local315) {
				Static214.method4359(this);
			}
		}
		if (this.model == null) {
			this.model = new PlayerModel();
		}
		local175 = this.model.transformationNpcId;
		this.model.method1950(local197, local22, local26 == 1, local44, this.anInt3365);
		if (local175 != local22) {
			this.x = this.pathTileX[0] * 128 + this.size() * 64;
			this.z = this.pathTileZ[0] * 128 + this.size() * 64;
		}
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.model == null) {
			return;
		}
		@Pc(25) AnimationSequence local25 = this.primarySeqId != -1 && this.anInt3420 == 0 ? AnimationSequence.getAnimationSequence(this.primarySeqId) : null;
		@Pc(54) AnimationSequence local54 = this.secondarySeqId == -1 || this.lowMemory || this.secondarySeqId == this.method2681().anInt1037 && local25 != null ? null : AnimationSequence.getAnimationSequence(this.secondarySeqId);
		@Pc(76) Model local76 = this.model.method1954(this.aClass147Array3, this.anInt3373, local54, local25, this.anInt3396, this.anInt3388, this.anInt3360, this.anInt3425, this.anInt3407);
		@Pc(79) int local79 = Static198.method1029();
		if (GlRenderer.enabled && Static238.anInt5316 < 96 && local79 > 50) {
			Static16.method501();
		}
		@Pc(102) int local102;
		if (ItemDefinition.modeWhat != 0 && local79 < 50) {
			local102 = 50 - local79;
			while (Static105.anInt2863 < local102) {
				Static51.aByteArrayArray8[Static105.anInt2863] = new byte[102400];
				Static105.anInt2863++;
			}
			while (Static105.anInt2863 > local102) {
				Static105.anInt2863--;
				Static51.aByteArrayArray8[Static105.anInt2863] = null;
			}
		}
		if (local76 == null) {
			return;
		}
		this.height = local76.getHeight();
		@Pc(184) Model model;
		if (Static209.aBoolean240 && (this.model.transformationNpcId == -1 || ActorDefinition.getDefinition(this.model.transformationNpcId).spotshadow)) {
			model = Scene.method1043(160, this.seqStretches, local54 == null ? local25 : local54, this.x, 0, this.z, 0, 1, local76, arg0, local54 == null ? this.anInt3425 : this.anInt3407, this.y, 240);
			if (GlRenderer.enabled) {
				@Pc(188) float local188 = GlRenderer.method4179();
				@Pc(190) float local190 = GlRenderer.method4166();
				GlRenderer.disableDepthMask();
				GlRenderer.method4152(local188, local190 - 150.0F);
				model.draw(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
				GlRenderer.enableDepthMask();
				GlRenderer.method4152(local188, local190);
			} else {
				model.draw(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
			}
		}
		if (Static173.localPlayer == this) {
			for (local102 = Static143.aClass102Array1.length - 1; local102 >= 0; local102--) {
				@Pc(245) Class102 local245 = Static143.aClass102Array1[local102];
				if (local245 != null && local245.anInt4052 != -1) {
					@Pc(291) int anchorX;
					@Pc(302) int anchorY;
					if (local245.headIconDrawType == 1 && local245.hintIconNpcTarget >= 0 && Static175.npcs.length > local245.hintIconNpcTarget) {
						@Pc(278) NPCRenderable npc = Static175.npcs[local245.hintIconNpcTarget];
						if (npc != null) {
							anchorX = npc.x / 32 - Static173.localPlayer.x / 32;
							anchorY = npc.z / 32 - Static173.localPlayer.z / 32;
							this.drawOnMinimap(null, anchorY, local76, anchorX, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
						}
					}
					if (local245.headIconDrawType == 2) {
						@Pc(340) int local340 = (local245.anInt4053 - Static225.originX) * 4 + 2 - Static173.localPlayer.x / 32;
						anchorX = (local245.anInt4046 - Static142.originZ) * 4 + 2 - Static173.localPlayer.z / 32;
						this.drawOnMinimap(null, anchorX, local76, local340, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
					}
					if (local245.headIconDrawType == 10 && local245.hintIconNpcTarget >= 0 && Static159.players.length > local245.hintIconNpcTarget) {
						@Pc(395) Player player = Static159.players[local245.hintIconNpcTarget];
						if (player != null) {
							anchorX = player.x / 32 - Static173.localPlayer.x / 32;
							anchorY = player.z / 32 - Static173.localPlayer.z / 32;
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
		if (!this.lowMemory && this.locModel != null) {
			if (Static83.loopCycle >= this.locStopCycle) {
				this.locModel = null;
			}
			if (this.locStartCycle <= Static83.loopCycle && this.locStopCycle > Static83.loopCycle) {
				if (this.locModel instanceof Loc) {
					loc = (Model) ((Loc) this.locModel).method1049();
				} else {
					loc = (Model) this.locModel;
				}
				loc.translate(this.locOffsetX - this.x, this.locOffsetY + -this.y, this.locOffsetZ - this.z);
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
			local76.draw(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			if (model != null) {
				model.pickable = true;
				model.draw(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			}
		} else {
			if (model != null) {
				local76 = ((SoftwareModel) local76).method4588(model);
			}
			if (loc != null) {
				local76 = ((SoftwareModel) local76).method4588(loc);
			}
			local76.pickable = true;
			local76.draw(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
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
		loc.translate(this.x - this.locOffsetX, -this.locOffsetY + this.y, this.z - this.locOffsetZ);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(Lclient!ga;ILclient!ak;IIIIIIIIIIII)V")
	private void drawOnMinimap(@OriginalArg(0) ParticleSystem arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Model arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12, @OriginalArg(14) int arg13) {
		@Pc(12) int local12 = arg3 * arg3 + arg1 * arg1;
		if (local12 < 16 || local12 > 360000) {
			return;
		}
		@Pc(34) int local34 = (int) (Math.atan2(arg3, arg1) * 325.949D) & 0x7FF;
		@Pc(46) Model local46 = Static220.method3800(local34, this.z, arg11, this.x, arg2, this.y);
		if (local46 == null) {
			return;
		}
		if (!GlRenderer.enabled) {
			local46.draw(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
			return;
		}
		@Pc(52) float local52 = GlRenderer.method4179();
		@Pc(54) float local54 = GlRenderer.method4166();
		GlRenderer.disableDepthMask();
		GlRenderer.method4152(local52, local54 - 150.0F);
		local46.draw(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
		GlRenderer.enableDepthMask();
		GlRenderer.method4152(local52, local54);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(B)Z")
	@Override
	public boolean isVisible() {
		return this.model != null;
	}

	@OriginalMember(owner = "client!e", name = "e", descriptor = "(I)Lclient!na;")
	public JString getName() {
		@Pc(2) JString local2 = this.name;
		if (Static103.aClass100Array88 != null) {
			local2 = Static34.method882(new JString[] { Static103.aClass100Array88[this.anInt1651], local2 });
		}
		if (Static263.aClass100Array174 != null) {
			local2 = Static34.method882(new JString[] { local2, Static263.aClass100Array174[this.anInt1651] });
		}
		return local2;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(BIZI)V")
	public void teleport(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2) {
		super.teleport(this.size(), arg0, arg2, arg1);
	}

	@OriginalMember(owner = "client!e", name = "finalize", descriptor = "()V")
	@Override
	protected void finalize() {
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "()I")
	@Override
	public int getHeight() {
		return this.height;
	}
}