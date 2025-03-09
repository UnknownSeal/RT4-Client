package com.jagex.runetek4.game.scene.entities;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.config.bastype.BASType;
import com.jagex.runetek4.game.config.npctype.NPCType;
import com.jagex.runetek4.game.config.seqtype.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!km")
public final class NPCEntity extends PathingEntity {

	@OriginalMember(owner = "client!km", name = "rc", descriptor = "Lclient!me;")
	public NPCType type;

    @OriginalMember(owner = "client!ij", name = "a", descriptor = "(Lclient!km;I)I")
    public static int getSound(@OriginalArg(0) NPCEntity arg0) {
        @Pc(13) NPCType local13 = arg0.type;
        if (local13.multinpc != null) {
            local13 = local13.getMultiNPC();
            if (local13 == null) {
                return -1;
            }
        }
        @Pc(29) int local29 = local13.bgsound_walk;
        @Pc(33) BASType local33 = arg0.method2681();
        if (local33.anInt1037 == arg0.anInt3366) {
            local29 = local13.bgsound;
        } else if (arg0.anInt3366 == local33.anInt1058 || local33.anInt1054 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1045 || arg0.anInt3366 == local33.anInt1043) {
            local29 = local13.bgsound_run;
        } else if (local33.anInt1062 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1042 || arg0.anInt3366 == local33.anInt1048 || arg0.anInt3366 == local33.anInt1066) {
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
	public int getHeight() {
		return this.height;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void method4546(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.type == null) {
			return;
		}
		@Pc(29) SeqType local29 = this.primarySeqId != -1 && this.anInt3420 == 0 ? Static36.method941(this.primarySeqId) : null;
		@Pc(53) SeqType local53 = this.anInt3366 == -1 || this.anInt3366 == this.method2681().anInt1037 && local29 != null ? null : Static36.method941(this.anInt3366);
		@Pc(74) Model tmp = this.type.method2937(this.aClass147Array3, this.anInt3388, this.anInt3407, this.anInt3373, this.anInt3360, this.anInt3425, local53, this.anInt3396, local29);
		if (tmp == null) {
			return;
		}
		this.height = tmp.getHeight();
		@Pc(84) NPCType local84 = this.type;
		if (local84.multinpc != null) {
			local84 = local84.getMultiNPC();
		}
		@Pc(140) Model local140;
		if (Static209.aBoolean240 && local84.spotshadow) {
			local140 = Static41.method1043(this.type.spotshadowtrans_1, this.aBoolean171, local53 == null ? local29 : local53, this.x, this.type.spotshadowcolour_2, this.z, this.type.spotshadowcolour_1, this.type.size, tmp, arg0, local53 == null ? this.anInt3425 : this.anInt3407, this.y, this.type.spotshadowtrans_2);
			if (GlRenderer.enabled) {
				@Pc(144) float local144 = GlRenderer.method4179();
				@Pc(146) float local146 = GlRenderer.method4166();
				GlRenderer.disableDepthMask();
				GlRenderer.method4152(local144, local146 - 150.0F);
				local140.method4546(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.aClass47_Sub1_5);
				GlRenderer.enableDepthMask();
				GlRenderer.method4152(local144, local146);
			} else {
				local140.method4546(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, this.aClass47_Sub1_5);
			}
		}
		this.method2687(tmp);
		this.method2685(tmp, arg0);
		local140 = null;
		if (this.spotanimFrame != -1 && this.spotanimId != -1) {
			@Pc(211) SpotAnimType local211 = Static34.method877(this.spotanimFrame);
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
			tmp.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			return;
		}
		if (this.type.size == 1) {
			tmp.pickable = true;
		}
		tmp.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		if (local140 != null) {
			if (this.type.size == 1) {
				local140.pickable = true;
			}
			local140.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		}
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "(I)I")
	@Override
	protected int method2688() {
		if (Static266.game != 0 && this.type.multinpc != null) {
			@Pc(17) NPCType local17 = this.type.getMultiNPC();
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
	public void method2698(@OriginalArg(1) NPCType arg0) {
		this.type = arg0;
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}
}
