package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.SeqType;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static234 {

	@OriginalMember(owner = "runetek4.client!ta", name = "o", descriptor = "[I")
	public static int[] anIntArray454;

	@OriginalMember(owner = "runetek4.client!ta", name = "p", descriptor = "[I")
	public static int[] anIntArray455;

	@OriginalMember(owner = "runetek4.client!ta", name = "z", descriptor = "[I")
	public static final int[] anIntArray456 = new int[] { -1, 0, 8, 0, 2, 0, 0, 0, 0, 12, 0, 1, 0, 3, 7, 0, 15, 6, 0, 0, 4, 7, -2, -1, 2, 0, 2, 8, 0, 0, 0, 0, -2, 5, 0, 0, 8, 3, 6, 0, 0, 0, -1, 0, -1, 0, 0, 6, -2, 0, 12, 0, 0, 0, -1, -2, 10, 0, 0, 0, 3, 0, -1, 0, 0, 5, 6, 0, 0, 8, -1, -1, 0, 8, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 6, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, -2, 0, 0, 0, 0, 0, 12, 2, 0, -2, -2, 20, 0, 0, 10, 0, 15, 0, -1, 0, 8, -2, 0, 0, 0, 8, 0, 12, 0, 0, 7, 0, 0, 0, 0, 0, -1, -1, 0, 4, 5, 0, 0, 0, 6, 0, 0, 0, 0, 8, 9, 0, 0, 0, 2, -1, 0, -2, 0, 4, 14, 0, 0, 0, 24, 0, -2, 5, 0, 0, 0, 10, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 1, 0, 0, 2, -1, 1, 0, 0, 0, 0, 14, 0, 0, 0, 0, 10, 5, 0, 0, 0, 0, 0, -2, 0, 0, 9, 0, 0, 8, 0, 0, 0, 0, -2, 6, 0, 0, 0, -2, 0, 3, 0, 1, 7, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 3, 0, 0 };

	@OriginalMember(owner = "runetek4.client!ta", name = "B", descriptor = "I")
	public static int cameraPitchClamp = 0;

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(I)V")
	public static void method4014() {
		for (@Pc(3) int local3 = 0; local3 < Static116.entityUpdateCount; local3++) {
			@Pc(10) int local10 = Static44.entityUpdateIds[local3];
			@Pc(14) NPCEntity local14 = Static175.npcs[local10];
			@Pc(18) int local18 = Static57.in.g1();
			if ((local18 & 0x8) != 0) {
				local18 += Static57.in.g1() << 8;
			}
			@Pc(43) int local43;
			@Pc(47) int info;
			if ((local18 & 0x40) != 0) {
				local43 = Static57.in.g1();
				info = Static57.in.p1neg();
				local14.method2686(info, Static83.loopCycle, local43);
				local14.anInt3378 = Static83.loopCycle + 300;
				local14.anInt3372 = Static57.in.g1_alt3();
			}
			if ((local18 & 0x2) != 0) {
				local43 = Static57.in.p1neg();
				info = Static57.in.g1_alt3();
				local14.method2686(info, Static83.loopCycle, local43);
			}
			if ((local18 & 0x10) != 0) {
				local43 = Static57.in.g2();
				info = Static57.in.g1();
				if (local43 == 65535) {
					local43 = -1;
				}
				Static223.method3855(info, local43, local14);
			}
			if ((local18 & 0x4) != 0) {
				local14.targetId = Static57.in.g2sub();
				if (local14.targetId == 65535) {
					local14.targetId = -1;
				}
			}
			if ((local18 & 0x80) != 0) {
				local43 = Static57.in.g2sub();
				if (local43 == 65535) {
					local43 = -1;
				}
				info = Static57.in.g4me();
				@Pc(147) boolean local147 = true;
				if (local43 != -1 && local14.spotanimFrame != -1 && Static36.method941(Static34.method877(local43).anInt1754).priority < Static36.method941(Static34.method877(local14.spotanimFrame).anInt1754).priority) {
					local147 = false;
				}
				if (local147) {
					local14.spotanimFrame = local43;
					local14.spotanimLastCycle = (info & 0xFFFF) + Static83.loopCycle;
					local14.anInt3361 = 0;
					local14.spotanimId = 0;
					local14.spotanimOffset = info >> 16;
					local14.anInt3418 = 1;
					if (local14.spotanimLastCycle > Static83.loopCycle) {
						local14.spotanimId = -1;
					}
					if (local14.spotanimFrame != -1 && local14.spotanimLastCycle == Static83.loopCycle) {
						@Pc(227) int local227 = Static34.method877(local14.spotanimFrame).anInt1754;
						if (local227 != -1) {
							@Pc(236) SeqType local236 = Static36.method941(local227);
							if (local236 != null && local236.anIntArray473 != null) {
								Static152.method2836(local14.z, local236, local14.x, false, 0);
							}
						}
					}
				}
			}
			if ((local18 & 0x1) != 0) {
				if (local14.type.hasBackgroundSound()) {
					Static91.method1877(local14);
				}
				local14.method2698(Static214.get(Static57.in.g2le()));
				local14.setSize(local14.type.size);
				local14.anInt3365 = local14.type.bas;
				if (local14.type.hasBackgroundSound()) {
					AreaSoundManager.add(local14.pathTileZ[0], null, 0, local14, local14.pathTileX[0], Static55.currentLevel, null);
				}
			}
			if ((local18 & 0x20) != 0) {
				local14.chatMessage = Static57.in.gjstr();
				local14.chatLoops = 100;
			}
			if ((local18 & 0x100) != 0) {
				local43 = Static57.in.p1neg();
				@Pc(331) int[] local331 = new int[local43];
				@Pc(334) int[] local334 = new int[local43];
				@Pc(337) int[] local337 = new int[local43];
				for (@Pc(339) int local339 = 0; local339 < local43; local339++) {
					@Pc(350) int local350 = Static57.in.g2le();
					if (local350 == 65535) {
						local350 = -1;
					}
					local331[local339] = local350;
					local334[local339] = Static57.in.g1_alt3();
					local337[local339] = Static57.in.g2();
				}
				Static159.method3037(local337, local14, local334, local331);
			}
			if ((local18 & 0x200) != 0) {
				local14.anInt3382 = Static57.in.g2sub();
				local14.anInt3363 = Static57.in.g2();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(Lclient!ve;III)Z")
	public static boolean method4016(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(9) byte[] local9 = arg0.getfile(arg2, arg1);
		if (local9 == null) {
			return false;
		} else {
			Static84.method1770(local9);
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IZIII)V")
	public static void method4017(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (Static245.load(arg2)) {
			Static266.method4190(-1, arg1, arg3, arg0, Static241.components[arg2]);
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "([Lclient!mm;ILclient!ve;)V")
	public static void method4018(@OriginalArg(0) SoftwareSprite[] arg0, @OriginalArg(2) Js5 arg1) {
		Static119.aClass153_44 = arg1;
		Static241.aClass3_Sub2_Sub1_Sub1Array13 = arg0;
		Static258.aBooleanArray130 = new boolean[Static241.aClass3_Sub2_Sub1_Sub1Array13.length];
		Static228.aClass69_120.clear();
		@Pc(25) int local25 = Static119.aClass153_44.method4482(Static54.DETAILS);
		@Pc(30) int[] local30 = Static119.aClass153_44.method4503(local25);
		for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
			Static228.aClass69_120.addTail(Static210.method3713(new Packet(Static119.aClass153_44.getfile(local25, local30[local32]))));
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IIZII)V")
	public static void method4019(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static267.anInt5773 && arg3 <= Static106.anInt2869) {
			@Pc(15) int local15 = Static78.method1690(Static224.anInt5063, arg0, Static172.anInt4164);
			@Pc(21) int local21 = Static78.method1690(Static224.anInt5063, arg2, Static172.anInt4164);
			Static222.method3826(arg1, arg3, local21, local15);
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(Z)V")
	public static void method4020() {
		Static200.aClass93_1.method3394();
		@Pc(10) int local10;
		for (local10 = 0; local10 < 32; local10++) {
			VarpDefinition.aLongArray2[local10] = 0L;
		}
		for (local10 = 0; local10 < 32; local10++) {
			Static228.aLongArray8[local10] = 0L;
		}
		Static227.anInt5097 = 0;
	}
}
