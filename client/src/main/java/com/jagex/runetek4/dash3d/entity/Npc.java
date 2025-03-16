package com.jagex.runetek4.dash3d.entity;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.media.AnimationSequence;
import com.jagex.runetek4.scene.Scene;
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
        @Pc(13) NpcType local13 = arg0.type;
        if (local13.multiNpcs != null) {
            local13 = local13.getMultiNPC();
            if (local13 == null) {
                return -1;
            }
        }
        @Pc(29) int local29 = local13.bgsound_walk;
        @Pc(33) BasType local33 = arg0.getBasType();
        if (local33.idleAnimationId == arg0.movementSeqId) {
            local29 = local13.bgsound;
        } else if (arg0.movementSeqId == local33.runAnimationId || local33.runFullTurnAnimationId == arg0.movementSeqId || arg0.movementSeqId == local33.runCWTurnAnimationId || arg0.movementSeqId == local33.runCCWTurnAnimationId) {
            local29 = local13.bgsound_run;
        } else if (local33.slowWalkAnimationId == arg0.movementSeqId || arg0.movementSeqId == local33.slowWalkFullTurnAnimationId || arg0.movementSeqId == local33.slowWalkCWTurnAnimationId || arg0.movementSeqId == local33.slowWalkCCWTurnAnimationId) {
            local29 = local13.bgsound_crawl;
        }
        return local29;
    }

    @OriginalMember(owner = "client!km", name = "finalize", descriptor = "()V")
	@Override
	public void finalize() {
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "()I")
	@Override
	public int getMinY() {
		return this.height;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.type == null) {
			return;
		}
		@Pc(29) AnimationSequence local29 = this.primarySeqId != -1 && this.anInt3420 == 0 ? AnimationSequence.getAnimationSequence(this.primarySeqId) : null;
		@Pc(53) AnimationSequence local53 = this.movementSeqId == -1 || this.movementSeqId == this.getBasType().idleAnimationId && local29 != null ? null : AnimationSequence.getAnimationSequence(this.movementSeqId);
		@Pc(74) Model tmp = this.type.method2937(this.aClass147Array3, this.anInt3388, this.anInt3407, this.anInt3373, this.anInt3360, this.anInt3425, local53, this.anInt3396, local29);
		if (tmp == null) {
			return;
		}
		this.height = tmp.getMinY();
		@Pc(84) NpcType local84 = this.type;
		if (local84.multiNpcs != null) {
			local84 = local84.getMultiNPC();
		}
		@Pc(140) Model local140;
		if (Static209.aBoolean240 && local84.spotshadow) {
			local140 = Scene.method1043(this.type.spotshadowtrans_1, this.seqStretches, local53 == null ? local29 : local53, this.xFine, this.type.spotshadowcolour_2, this.zFine, this.type.spotshadowcolour_1, this.type.size, tmp, arg0, local53 == null ? this.anInt3425 : this.anInt3407, this.y, this.type.spotshadowtrans_2);
			if (GlRenderer.enabled) {
				@Pc(144) float local144 = GlRenderer.method4179();
				@Pc(146) float local146 = GlRenderer.method4166();
				GlRenderer.disableDepthMask();
				GlRenderer.method4152(local144, local146 - 150.0F);
				local140.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.aClass47_Sub1_5);
				GlRenderer.enableDepthMask();
				GlRenderer.method4152(local144, local146);
			} else {
				local140.render(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.aClass47_Sub1_5);
			}
		}
		this.method2687(tmp);
		this.method2685(tmp, arg0);
		local140 = null;
		if (this.spotanimFrame != -1 && this.spotanimId != -1) {
			@Pc(211) SpotAnimDefinition local211 = Static34.method877(this.spotanimFrame);
			local140 = local211.getModel(this.anInt3418, this.spotanimId, this.anInt3361);
			if (local140 != null) {
				local140.translate(0, -this.spotanimOffset, 0);
				if (local211.aBoolean100) {
					if (Static101.anInt2640 != 0) {
						local140.method4574(Static101.anInt2640);
					}
					if (Static102.anInt2680 != 0) {
						local140.method4564(Static102.anInt2680);
					}
					if (Static62.anInt1938 != 0) {
						local140.translate(0, Static62.anInt1938, 0);
					}
				}
			}
		}
		if (!GlRenderer.enabled) {
			if (local140 != null) {
				tmp = ((SoftwareModel) tmp).method4588(local140);
			}
			if (this.type.size == 1) {
				tmp.pickable = true;
			}
			tmp.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			return;
		}
		if (this.type.size == 1) {
			tmp.pickable = true;
		}
		tmp.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		if (local140 != null) {
			if (this.type.size == 1) {
				local140.pickable = true;
			}
			local140.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		}
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "(I)I")
	@Override
	protected int method2688() {
		if (Static266.game != 0 && this.type.multiNpcs != null) {
			@Pc(17) NpcType local17 = this.type.getMultiNPC();
			if (local17 != null && local17.bas != -1) {
				return local17.bas;
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
	public void method2698(@OriginalArg(1) NpcType arg0) {
		this.type = arg0;
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}
}
