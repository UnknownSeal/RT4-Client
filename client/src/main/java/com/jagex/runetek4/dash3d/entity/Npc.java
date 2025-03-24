package com.jagex.runetek4.dash3d.entity;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.SpotAnimType;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.media.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!km")
public final class Npc extends PathingEntity {

	@OriginalMember(owner = "client!km", name = "rc", descriptor = "Lclient!me;")
	public NpcType type;

    @OriginalMember(owner = "client!ij", name = "a", descriptor = "(Lclient!km;I)I")
    public static int getSound(@OriginalArg(0) Npc arg0) {
        @Pc(13) NpcType npcType = arg0.type;
        if (npcType.multiNpcs != null) {
            npcType = npcType.getMultiNPC();
            if (npcType == null) {
                return -1;
            }
        }
        @Pc(29) int sound = npcType.walkSound;
        @Pc(33) BasType npcBas = arg0.getBasType();
        if (npcBas.idleAnimationId == arg0.movementSeqId) {
            sound = npcType.idleSound;
        } else if (arg0.movementSeqId == npcBas.runAnimationId || npcBas.runFullTurnAnimationId == arg0.movementSeqId || arg0.movementSeqId == npcBas.runCWTurnAnimationId || arg0.movementSeqId == npcBas.runCCWTurnAnimationId) {
            sound = npcType.runSound;
        } else if (npcBas.slowWalkAnimationId == arg0.movementSeqId || arg0.movementSeqId == npcBas.slowWalkFullTurnAnimationId || arg0.movementSeqId == npcBas.slowWalkCWTurnAnimationId || arg0.movementSeqId == npcBas.slowWalkCCWTurnAnimationId) {
            sound = npcType.crawlSound;
        }
        return sound;
    }

    @OriginalMember(owner = "client!km", name = "finalize", descriptor = "()V")
	@Override
	public void finalize() {
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "()I")
	@Override
	public int getMinY() {
		return this.minY;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int orientation, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.type == null) {
			return;
		}
		@Pc(29) SeqType local29 = this.primarySeqId != -1 && this.anInt3420 == 0 ? SeqTypeList.getAnimationSequence(this.primarySeqId) : null;
		@Pc(53) SeqType local53 = this.movementSeqId == -1 || this.movementSeqId == this.getBasType().idleAnimationId && local29 != null ? null : SeqTypeList.getAnimationSequence(this.movementSeqId);
		@Pc(74) Model body = this.type.getBodyModel(this.aPathingEntityClass147Array3, this.anInt3388, this.anInt3407, this.anInt3373, this.anInt3360, this.anInt3425, local53, this.anInt3396, local29);
		if (body == null) {
			return;
		}
		this.minY = body.getMinY();
		@Pc(84) NpcType local84 = this.type;
		if (local84.multiNpcs != null) {
			local84 = local84.getMultiNPC();
		}
		@Pc(140) Model model;
		if (Preferences.characterShadowsOn && local84.spotShadow) {
			model = ShadowModelList.method1043(this.type.shadowColorModifier1, this.seqStretches, local53 == null ? local29 : local53, this.xFine, this.type.spotShadowColor2, this.zFine, this.type.spotShadowColor1, this.type.size, body, orientation, local53 == null ? this.anInt3425 : this.anInt3407, this.y, this.type.spotShadowTrans2);
			if (GlRenderer.enabled) {
				@Pc(144) float local144 = GlRenderer.method4179();
				@Pc(146) float local146 = GlRenderer.method4166();
				GlRenderer.disableDepthMask();
				GlRenderer.method4152(local144, local146 - 150.0F);
				model.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.particleSystem);
				GlRenderer.enableDepthMask();
				GlRenderer.method4152(local144, local146);
			} else {
				model.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.particleSystem);
			}
		}
		this.method2687(body);
		this.method2685(body, orientation);
		model = null;
		if (this.spotanimFrame != -1 && this.spotanimId != -1) {
			@Pc(211) SpotAnimType spotAnimType = Static34.method877(this.spotanimFrame);
			model = spotAnimType.constructModel(this.anInt3418, this.spotanimId, this.anInt3361);
			if (model != null) {
				model.translate(0, -this.spotanimOffset, 0);
				if (spotAnimType.aBoolean100) {
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
		if (!GlRenderer.enabled) {
			if (model != null) {
				body = ((SoftwareModel) body).method4588(model);
			}
			if (this.type.size == 1) {
				body.pickable = true;
			}
			body.render(orientation, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
			return;
		}
		if (this.type.size == 1) {
			body.pickable = true;
		}
		body.render(orientation, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
		if (model != null) {
			if (this.type.size == 1) {
				model.pickable = true;
			}
			model.render(orientation, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.particleSystem);
		}
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "(I)I")
	@Override
	protected int getBasId() {
		if (client.game != 0 && this.type.multiNpcs != null) {
			@Pc(17) NpcType local17 = this.type.getMultiNPC();
			if (local17 != null && local17.bastypeid != -1) {
				return local17.bastypeid;
			}
		}
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (this.type == null) {
			;
		}
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(B)Z")
	@Override
	public boolean isVisible() {
		return this.type != null;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(ILclient!me;)V")
	public void setNpcType(@OriginalArg(1) NpcType arg0) {
		this.type = arg0;
		if (this.particleSystem != null) {
			this.particleSystem.method1646();
		}
	}
}
