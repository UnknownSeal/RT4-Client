package com.jagex.runetek4;

import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static118 {

	@OriginalMember(owner = "runetek4.client!jd", name = "d", descriptor = "[[[B")
	public static byte[][][] levelShademap;

	@OriginalMember(owner = "runetek4.client!jd", name = "c", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_16 = new NodeCache(100);

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(B)I")
	public static int method2352() {
		Static232.anInt5212 = 0;
		return Static119.method2385();
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
