package com.jagex.runetek4.game.scene.entities;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.config.npctype.NPCType;
import com.jagex.runetek4.game.config.seqtype.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!km")
public final class NPCEntity extends PathingEntity {

	@OriginalMember(owner = "client!km", name = "rc", descriptor = "Lclient!me;")
	public NPCType npcType;

	@OriginalMember(owner = "client!km", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() {
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "()I")
	@Override
	public final int method4549() {
		return this.height;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void method4546(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.npcType == null) {
			return;
		}
		@Pc(29) SeqType local29 = this.anInt3369 != -1 && this.anInt3420 == 0 ? Static36.method941(this.anInt3369) : null;
		@Pc(53) SeqType local53 = this.anInt3366 == -1 || this.anInt3366 == this.method2681().anInt1037 && local29 != null ? null : Static36.method941(this.anInt3366);
		@Pc(74) Model local74 = this.npcType.method2937(this.aClass147Array3, this.anInt3388, this.anInt3407, this.anInt3373, this.anInt3360, this.anInt3425, local53, this.anInt3396, local29);
		if (local74 == null) {
			return;
		}
		this.height = local74.method4549();
		@Pc(84) NPCType local84 = this.npcType;
		if (local84.multinpc != null) {
			local84 = local84.getMultiNPC();
		}
		@Pc(140) Model local140;
		if (Static209.aBoolean240 && local84.spotshadow) {
			local140 = Static41.method1043(this.npcType.spotshadowtrans_1, this.aBoolean171, local53 == null ? local29 : local53, this.anInt3412, this.npcType.spotshadowcolour_2, this.anInt3421, this.npcType.spotshadowcolour_1, this.npcType.size, local74, arg0, local53 == null ? this.anInt3425 : this.anInt3407, this.anInt3424, this.npcType.spotshadowtrans_2);
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
		this.method2687(local74);
		this.method2685(local74, arg0);
		local140 = null;
		if (this.anInt3432 != -1 && this.anInt3399 != -1) {
			@Pc(211) SpotAnimType local211 = Static34.method877(this.anInt3432);
			local140 = local211.method1319(this.anInt3418, this.anInt3399, this.anInt3361);
			if (local140 != null) {
				local140.translate(0, -this.anInt3394, 0);
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
				local74 = ((SoftwareModel) local74).method4588(local140);
			}
			if (this.npcType.size == 1) {
				local74.aBoolean303 = true;
			}
			local74.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			return;
		}
		if (this.npcType.size == 1) {
			local74.aBoolean303 = true;
		}
		local74.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		if (local140 != null) {
			if (this.npcType.size == 1) {
				local140.aBoolean303 = true;
			}
			local140.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		}
	}

	@OriginalMember(owner = "client!km", name = "b", descriptor = "(I)I")
	@Override
	protected int method2688() {
		if (Static266.game != 0 && this.npcType.multinpc != null) {
			@Pc(17) NPCType local17 = this.npcType.getMultiNPC();
			if (local17 != null && local17.bas != -1) {
				return local17.bas;
			}
		}
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (this.npcType == null) {
			;
		}
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(B)Z")
	@Override
	public boolean exists() {
		return this.npcType != null;
	}

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(ILclient!me;)V")
	public final void method2698(@OriginalArg(1) NPCType arg0) {
		this.npcType = arg0;
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}
}
