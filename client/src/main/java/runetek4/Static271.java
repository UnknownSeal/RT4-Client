package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.io.Packet;
import runetek4.game.config.loctype.LocType;

public final class Static271 {

	@OriginalMember(owner = "runetek4.client!wc", name = "c", descriptor = "I")
	public static int defaultPort;

	@OriginalMember(owner = "runetek4.client!wc", name = "g", descriptor = "I")
	public static int anInt5804 = 0;

	@OriginalMember(owner = "runetek4.client!wc", name = "h", descriptor = "[[I")
	public static final int[][] anIntArrayArray46 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

	@OriginalMember(owner = "runetek4.client!wc", name = "i", descriptor = "[B")
	public static final byte[] aByteArray79 = new byte[520];

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!e;I)V")
	public static void method4597(@OriginalArg(0) Player arg0) {
		@Pc(10) AreaSound local10 = (AreaSound) Static93.aClass133_7.getNode(arg0.aClass100_364.encode37());
		if (local10 == null) {
			return;
		}
		if (local10.aClass3_Sub3_Sub1_1 != null) {
			Static204.aClass3_Sub3_Sub2_1.method1347(local10.aClass3_Sub3_Sub1_1);
			local10.aClass3_Sub3_Sub1_1 = null;
		}
		local10.remove();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method4598(@OriginalArg(0) Packet arg0) {
		if (Static121.uid != null) {
			try {
				Static121.uid.method1459(0L);
				Static121.uid.method1458(arg0.data, arg0.pos, 24);
			} catch (@Pc(16) Exception local16) {
			}
		}
		arg0.pos += 24;
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(ZI)V")
	public static void method4600(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(4, arg0);
		local8.method1007();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(II)Lclient!pb;")
	public static LocType get(@OriginalArg(1) int id) {
		@Pc(15) LocType locType = (LocType) Static179.aClass99_25.method3106((long) id);
		if (locType != null) {
			return locType;
		}
		@Pc(30) byte[] bytes = Static146.aClass153_54.method4495(Static253.method4328(id), Static33.method872(id));
		locType = new LocType();
		locType.anInt4426 = id;
		if (bytes != null) {
			locType.decode(new Packet(bytes));
		}
		locType.postDecode();
		if (!Static30.aBoolean61 && locType.members) {
			locType.op = null;
		}
		if (locType.breakroutefinding) {
			locType.blockwalk = 0;
			locType.aBoolean207 = false;
		}
		Static179.aClass99_25.method3095(locType, (long) id);
		return locType;
	}
}
