package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static118 {

	@OriginalMember(owner = "runetek4.client!jd", name = "d", descriptor = "[[[B")
	public static byte[][][] levelShademap;

	@OriginalMember(owner = "runetek4.client!jd", name = "i", descriptor = "Lclient!be;")
	public static Component component;

	@OriginalMember(owner = "runetek4.client!jd", name = "c", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_16 = new NodeCache(100);

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(B)I")
	public static int method2352() {
		Static232.anInt5212 = 0;
		return Static119.method2385();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(IB)V")
	public static void method2353(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(12, arg0);
		local12.method1007();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II[Lclient!be;)V")
	public static void method2354(@OriginalArg(1) int arg0, @OriginalArg(2) Component[] arg1) {
		for (@Pc(7) int local7 = 0; local7 < arg1.length; local7++) {
			@Pc(15) Component local15 = arg1[local7];
			if (local15 != null && local15.overlayer == arg0 && (!local15.aBoolean32 || !Static36.method947(local15))) {
				if (local15.INVENTORY == 0) {
					if (!local15.aBoolean32 && Static36.method947(local15) && local15 != Static180.aClass13_22) {
						continue;
					}
					method2354(local15.anInt507, arg1);
					if (local15.createdComponents != null) {
						method2354(local15.anInt507, local15.createdComponents);
					}
					@Pc(73) ComponentPointer local73 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) local15.anInt507);
					if (local73 != null) {
						Static96.method1949(local73.anInt5878);
					}
				}
				if (local15.INVENTORY == 6) {
					@Pc(105) int local105;
					if (local15.anInt522 != -1 || local15.anInt462 != -1) {
						@Pc(100) boolean local100 = Static154.method2926(local15);
						if (local100) {
							local105 = local15.anInt462;
						} else {
							local105 = local15.anInt522;
						}
						if (local105 != -1) {
							@Pc(118) SeqType local118 = SeqType.getAnimationSequence(local105);
							if (local118 != null) {
								local15.anInt500 += Protocol.sceneDelta;
								while (local15.anInt500 > local118.frames[local15.anInt510]) {
									local15.anInt500 -= local118.frames[local15.anInt510];
									local15.anInt510++;
									if (local118.anIntArray473.length <= local15.anInt510) {
										local15.anInt510 -= local118.replayoff;
										if (local15.anInt510 < 0 || local118.anIntArray473.length <= local15.anInt510) {
											local15.anInt510 = 0;
										}
									}
									local15.anInt496 = local15.anInt510 + 1;
									if (local118.anIntArray473.length <= local15.anInt496) {
										local15.anInt496 -= local118.replayoff;
										if (local15.anInt496 < 0 || local118.anIntArray473.length <= local15.anInt496) {
											local15.anInt496 = -1;
										}
									}
									Static43.method1143(local15);
								}
							}
						}
					}
					if (local15.modelRotationSpeed != 0 && !local15.aBoolean32) {
						@Pc(239) int local239 = local15.modelRotationSpeed >> 16;
						@Pc(243) int local243 = local239 * Protocol.sceneDelta;
						local105 = local15.modelRotationSpeed << 16 >> 16;
						local15.modelXAngle = local243 + local15.modelXAngle & 0x7FF;
						local105 *= Protocol.sceneDelta;
						local15.modelYAngle = local15.modelYAngle + local105 & 0x7FF;
						Static43.method1143(local15);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II[Lclient!na;I)Lclient!na;")
	public static JString method2355(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JString[] arg2) {
		@Pc(5) int local5 = 0;
		for (@Pc(7) int local7 = 0; local7 < arg1; local7++) {
			if (arg2[arg0 + local7] == null) {
				arg2[local7 + arg0] = Static193.aClass100_853;
			}
			local5 += arg2[local7 + arg0].anInt4030;
		}
		@Pc(39) byte[] local39 = new byte[local5];
		@Pc(41) int local41 = 0;
		for (@Pc(43) int local43 = 0; local43 < arg1; local43++) {
			@Pc(52) JString local52 = arg2[local43 + arg0];
			JString.copy(local52.aByteArray52, 0, local39, local41, local52.anInt4030);
			local41 += local52.anInt4030;
		}
		@Pc(71) JString local71 = new JString();
		local71.anInt4030 = local5;
		local71.aByteArray52 = local39;
		return local71;
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II)I")
	public static int method2356(@OriginalArg(1) int arg0) {
		return arg0 & 0x7F;
	}
}
