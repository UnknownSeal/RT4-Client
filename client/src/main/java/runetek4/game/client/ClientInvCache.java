package runetek4.game.client;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.datastruct.Node;
import runetek4.Static20;

@OriginalClass("runetek4.client!qe")
public final class ClientInvCache extends Node {

	@OriginalMember(owner = "runetek4.client!qe", name = "p", descriptor = "[I")
	public int[] invSlotObjId = new int[] { -1 };

	@OriginalMember(owner = "runetek4.client!qe", name = "u", descriptor = "[I")
	public int[] invSlotObjCount = new int[] { 0 };

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) ClientInvCache local12 = (ClientInvCache) Static20.aClass133_2.getNode((long) arg3);
		if (local12 == null) {
			local12 = new ClientInvCache();
			Static20.aClass133_2.pushNode(local12, (long) arg3);
		}
		if (arg1 >= local12.invSlotObjId.length) {
			@Pc(39) int[] local39 = new int[arg1 + 1];
			@Pc(44) int[] local44 = new int[arg1 + 1];
			@Pc(46) int local46;
			for (local46 = 0; local46 < local12.invSlotObjId.length; local46++) {
				local39[local46] = local12.invSlotObjId[local46];
				local44[local46] = local12.invSlotObjCount[local46];
			}
			for (local46 = local12.invSlotObjId.length; local46 < arg1; local46++) {
				local39[local46] = -1;
				local44[local46] = 0;
			}
			local12.invSlotObjId = local39;
			local12.invSlotObjCount = local44;
		}
		local12.invSlotObjId[arg1] = arg0;
		local12.invSlotObjCount[arg1] = arg2;
	}
}
