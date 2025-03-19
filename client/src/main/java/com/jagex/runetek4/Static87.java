package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static87 {

	@OriginalMember(owner = "client!gn", name = "t", descriptor = "Lclient!ve;")
	public static Js5 aClass153_38;

	@OriginalMember(owner = "client!gn", name = "d", descriptor = "Z")
	public static boolean aBoolean130 = false;

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(BI)Lclient!na;")
	public static JString method1804(@OriginalArg(1) int capacity) {
		@Pc(13) JString local13 = new JString();
		local13.anInt4030 = 0;
		local13.aByteArray52 = new byte[capacity];
		return local13;
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(ZI)V")
	public static void method1805(@OriginalArg(0) boolean arg0) {
		@Pc(7) byte local7;
		@Pc(9) byte[][] local9;
		if (GlRenderer.enabled && arg0) {
			local7 = 1;
			local9 = Static186.aByteArrayArray14;
		} else {
			local7 = 4;
			local9 = Static273.aByteArrayArray13;
		}
		@Pc(18) int local18 = local9.length;
		@Pc(20) int local20;
		@Pc(38) int local38;
		@Pc(49) int local49;
		@Pc(53) byte[] local53;
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (Static238.anIntArray470[local20] >> 8) * 64 - Camera.originX;
			local49 = (Static238.anIntArray470[local20] & 0xFF) * 64 - Camera.originZ;
			local53 = local9[local20];
			if (local53 != null) {
				client.audioLoop();
				Static269.method2203(PathFinder.collisionMaps, arg0, Static80.anInt4701 * 8 - 48, local49, local38, (Static52.anInt1695 - 6) * 8, local53);
			}
		}
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (Static238.anIntArray470[local20] >> 8) * 64 - Camera.originX;
			local49 = (Static238.anIntArray470[local20] & 0xFF) * 64 - Camera.originZ;
			local53 = local9[local20];
			if (local53 == null && Static52.anInt1695 < 800) {
				client.audioLoop();
				for (@Pc(130) int local130 = 0; local130 < local7; local130++) {
					Static23.method645(local130, local49, local38, 64, 64);
				}
			}
		}
	}

}
