package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.game.client.ClientInvCache;

public final class Static11 {

	@OriginalMember(owner = "runetek4.client!ba", name = "D", descriptor = "Lclient!vh;")
	public static AudioChannel aClass62_1;

	@OriginalMember(owner = "runetek4.client!ba", name = "w", descriptor = "I")
	public static int anInt384 = 0;

	@OriginalMember(owner = "runetek4.client!ba", name = "x", descriptor = "Z")
	public static boolean aBoolean15 = true;

	@OriginalMember(owner = "runetek4.client!ba", name = "B", descriptor = "I")
	public static int anInt386 = 20;

	@OriginalMember(owner = "runetek4.client!ba", name = "e", descriptor = "(I)V")
	public static void method443() {
		Static262.aClass99_35.method3104();
	}

	@OriginalMember(owner = "runetek4.client!ba", name = "a", descriptor = "(IB)I")
	public static int method446(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return 0;
		}
		@Pc(17) ClientInvCache local17 = (ClientInvCache) Static20.aClass133_2.getNode((long) arg0);
		if (local17 == null) {
			return Static246.method4241(arg0).anInt3706;
		}
		@Pc(31) int local31 = 0;
		for (@Pc(33) int local33 = 0; local33 < local17.invSlotObjId.length; local33++) {
			if (local17.invSlotObjId[local33] == -1) {
				local31++;
			}
		}
		return local31 + Static246.method4241(arg0).anInt3706 - local17.invSlotObjId.length;
	}
}