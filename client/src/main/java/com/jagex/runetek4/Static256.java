package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static256 {

	@OriginalMember(owner = "runetek4.client!um", name = "U", descriptor = "Lclient!dd;")
	public static SoftwareFont aClass3_Sub2_Sub9_Sub1_1;

	@OriginalMember(owner = "runetek4.client!um", name = "a", descriptor = "(Z)V")
	public static void method4392() {
		PlayerEntity.overrideChat = 0;
		@Pc(17) int local17 = Static225.originX + (Static173.localPlayer.x >> 7);
		@Pc(25) int local25 = (Static173.localPlayer.z >> 7) + Static142.originZ;
		if (local17 >= 3053 && local17 <= 3156 && local25 >= 3056 && local25 <= 3136) {
			PlayerEntity.overrideChat = 1;
		}
		if (local17 >= 3072 && local17 <= 3118 && local25 >= 9492 && local25 <= 9535) {
			PlayerEntity.overrideChat = 1;
		}
		if (PlayerEntity.overrideChat == 1 && local17 >= 3139 && local17 <= 3199 && local25 >= 3008 && local25 <= 3062) {
			PlayerEntity.overrideChat = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!um", name = "c", descriptor = "(III)Z")
	public static boolean method4394(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < Static215.anInt4870; local1++) {
			@Pc(8) Class120 local8 = Static247.aClass120Array2[local1];
			@Pc(17) int local17;
			@Pc(29) int local29;
			@Pc(39) int local39;
			@Pc(49) int local49;
			@Pc(59) int local59;
			if (local8.anInt4462 == 1) {
				local17 = local8.anInt4460 - arg0;
				if (local17 > 0) {
					local29 = local8.anInt4458 + (local8.anInt4454 * local17 >> 8);
					local39 = local8.anInt4449 + (local8.anInt4450 * local17 >> 8);
					local49 = local8.anInt4444 + (local8.anInt4459 * local17 >> 8);
					local59 = local8.anInt4447 + (local8.anInt4463 * local17 >> 8);
					if (arg2 >= local29 && arg2 <= local39 && arg1 >= local49 && arg1 <= local59) {
						return true;
					}
				}
			} else if (local8.anInt4462 == 2) {
				local17 = arg0 - local8.anInt4460;
				if (local17 > 0) {
					local29 = local8.anInt4458 + (local8.anInt4454 * local17 >> 8);
					local39 = local8.anInt4449 + (local8.anInt4450 * local17 >> 8);
					local49 = local8.anInt4444 + (local8.anInt4459 * local17 >> 8);
					local59 = local8.anInt4447 + (local8.anInt4463 * local17 >> 8);
					if (arg2 >= local29 && arg2 <= local39 && arg1 >= local49 && arg1 <= local59) {
						return true;
					}
				}
			} else if (local8.anInt4462 == 3) {
				local17 = local8.anInt4458 - arg2;
				if (local17 > 0) {
					local29 = local8.anInt4460 + (local8.anInt4448 * local17 >> 8);
					local39 = local8.anInt4445 + (local8.anInt4456 * local17 >> 8);
					local49 = local8.anInt4444 + (local8.anInt4459 * local17 >> 8);
					local59 = local8.anInt4447 + (local8.anInt4463 * local17 >> 8);
					if (arg0 >= local29 && arg0 <= local39 && arg1 >= local49 && arg1 <= local59) {
						return true;
					}
				}
			} else if (local8.anInt4462 == 4) {
				local17 = arg2 - local8.anInt4458;
				if (local17 > 0) {
					local29 = local8.anInt4460 + (local8.anInt4448 * local17 >> 8);
					local39 = local8.anInt4445 + (local8.anInt4456 * local17 >> 8);
					local49 = local8.anInt4444 + (local8.anInt4459 * local17 >> 8);
					local59 = local8.anInt4447 + (local8.anInt4463 * local17 >> 8);
					if (arg0 >= local29 && arg0 <= local39 && arg1 >= local49 && arg1 <= local59) {
						return true;
					}
				}
			} else if (local8.anInt4462 == 5) {
				local17 = arg1 - local8.anInt4444;
				if (local17 > 0) {
					local29 = local8.anInt4460 + (local8.anInt4448 * local17 >> 8);
					local39 = local8.anInt4445 + (local8.anInt4456 * local17 >> 8);
					local49 = local8.anInt4458 + (local8.anInt4454 * local17 >> 8);
					local59 = local8.anInt4449 + (local8.anInt4450 * local17 >> 8);
					if (arg0 >= local29 && arg0 <= local39 && arg2 >= local49 && arg2 <= local59) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "runetek4.client!um", name = "a", descriptor = "(BI)Lclient!wl;")
	public static FloorOverlayType method4395(@OriginalArg(1) int arg0) {
		@Pc(6) FloorOverlayType local6 = (FloorOverlayType) ClientScriptRunner.aClass99_31.get((long) arg0);
		if (local6 != null) {
			return local6;
		}
		@Pc(30) byte[] local30 = Static35.aClass153_22.getfile(4, arg0);
		local6 = new FloorOverlayType();
		if (local30 != null) {
			local6.decode(new Packet(local30), arg0);
		}
		ClientScriptRunner.aClass99_31.put(local6, (long) arg0);
		return local6;
	}
}
