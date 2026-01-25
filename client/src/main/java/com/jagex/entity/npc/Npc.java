package com.jagex.entity.npc;

import com.jagex.game.runetek4.config.spotanim.SpotAnimType;
import com.jagex.client.Preferences;
import com.jagex.client.Client;
import com.jagex.game.runetek4.config.seqtype.SeqTypeList;
import com.jagex.game.runetek4.config.spotanim.SpotAnimTypeList;
import com.jagex.game.runetek4.config.bastype.BasType;
import com.jagex.game.runetek4.config.npctype.NpcType;
import com.jagex.game.runetek4.config.seqtype.SeqType;
import com.jagex.entity.PathingEntity;
import com.jagex.graphics.effects.ParticleSystem;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.graphics.model.Model;
import com.jagex.graphics.model.SoftwareModel;
import com.jagex.graphics.lighting.ShadowModelList;
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
        if (npcType.multinpc != null) {
            npcType = npcType.getMultiNPC();
            if (npcType == null) {
                return -1;
            }
        }
        @Pc(29) int sound = npcType.bgsound_walk;
        @Pc(33) BasType npcBas = arg0.getBasType();
        if (npcBas.readyanim == arg0.movementSeqId) {
            sound = npcType.bgsound;
        } else if (arg0.movementSeqId == npcBas.runanim || npcBas.runanim_b == arg0.movementSeqId || arg0.movementSeqId == npcBas.runanim_r || arg0.movementSeqId == npcBas.runanim_l) {
            sound = npcType.bgsound_run;
        } else if (npcBas.crawlanim == arg0.movementSeqId || arg0.movementSeqId == npcBas.crawlanim_b || arg0.movementSeqId == npcBas.crawlanim_r || arg0.movementSeqId == npcBas.crawlanim_l) {
            sound = npcType.bgsound_crawl;
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
		@Pc(29) SeqType local29 = this.primarySeqId != -1 && this.animationDelay == 0 ? SeqTypeList.get(this.primarySeqId) : null;
		@Pc(53) SeqType local53 = this.movementSeqId == -1 || this.movementSeqId == this.getBasType().readyanim && local29 != null ? null : SeqTypeList.get(this.movementSeqId);
		@Pc(74) Model body = this.type.getBodyModel(this.layeredAnimations, this.anInt3388, this.anInt3407, this.animationDirection, this.animationFrame, this.animationFrameDelay, local53, this.anInt3396, local29);
		if (body == null) {
			return;
		}
		this.minY = body.getMinY();
		@Pc(84) NpcType local84 = this.type;
		if (local84.multinpc != null) {
			local84 = local84.getMultiNPC();
		}
		@Pc(140) Model model;
		if (Preferences.characterShadowsOn && local84.spotshadow) {
			model = ShadowModelList.method1043(this.type.spotshadowtrans_1, this.seqStretches, local53 == null ? local29 : local53, this.xFine, this.type.spotshadowcolour_2, this.zFine, this.type.spotshadowcolour_1, this.type.size, body, orientation, local53 == null ? this.animationFrameDelay : this.anInt3407, this.groundHeight, this.type.spotshadowtrans_2);
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
		if (this.spotAnimId != -1 && this.spotanimId != -1) {
			@Pc(211) SpotAnimType spotAnimType = SpotAnimTypeList.get(this.spotAnimId);
			model = spotAnimType.constructModel(this.anInt3418, this.spotanimId, this.anInt3361);
			if (model != null) {
				model.translate(0, -this.spotAnimY, 0);
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
		if (Client.game != 0 && this.type.multinpc != null) {
			@Pc(17) NpcType local17 = this.type.getMultiNPC();
			if (local17 != null && local17.nas != -1) {
				return local17.nas;
			}
		}
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIII)V")
	@Override
	public void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (this.type == null) {
			// TODO Why is this here?
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
