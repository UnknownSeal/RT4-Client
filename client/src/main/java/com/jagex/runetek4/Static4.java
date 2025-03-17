package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static4 {

	@OriginalMember(owner = "runetek4.client!ac", name = "e", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "runetek4.client!ac", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_7 = JString.parse("overlay");

	@OriginalMember(owner = "runetek4.client!ac", name = "n", descriptor = "I")
	public static int selectedInventorySlot = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "o", descriptor = "I")
	public static int updatedVarcsWriterIndex = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(IIII)I")
	public static int getRenderLevel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		if ((SceneGraph.renderFlags[arg2][arg1][arg0] & 0x8) == 0) {
			return arg2 <= 0 || (SceneGraph.renderFlags[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void method24(@OriginalArg(1) int arg0) {
		@Pc(16) DelayedStateChange local16 = Static238.method4143(1, arg0);
		local16.method1007();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(Lclient!na;I)I")
	public static int method25(@OriginalArg(0) JString arg0) {
		if (arg0 == null) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < FriendList.friendCount; local20++) {
			if (arg0.equalsIgnoreCase(Static122.friendName[local20])) {
				return local20;
			}
		}
		return -1;
	}
}