package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.ObjType;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static71 {

	@OriginalMember(owner = "runetek4.client!fk", name = "e", descriptor = "I")
	public static int anInt1885;

	@OriginalMember(owner = "runetek4.client!fk", name = "j", descriptor = "[[I")
	public static int[][] anIntArrayArray10;

    @OriginalMember(owner = "runetek4.client!fk", name = "q", descriptor = "Lclient!uc;")
	public static MouseWheel mouseWheel;

	@OriginalMember(owner = "runetek4.client!fk", name = "g", descriptor = "Z")
	public static boolean aBoolean107 = true;

	@OriginalMember(owner = "runetek4.client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];

	@OriginalMember(owner = "client!fk", name = "a", descriptor = "(IB)Lclient!h;")
	public static ObjType get(@OriginalArg(0) int id) {
		@Pc(6) ObjType objType = (ObjType) Static27.aClass99_4.get((long) id);
		if (objType != null) {
			return objType;
		}
		@Pc(25) byte[] bytes = Static167.aClass153_61.getfile(Static18.method554(id), Static247.method4247(id));
		objType = new ObjType();
		objType.anInt2354 = id;
		if (bytes != null) {
			objType.decode(new Packet(bytes));
		}
		objType.postDecode();

		if (objType.certtemplate != -1) {
			objType.genCert(get(objType.certtemplate), get(objType.certlink));
		}
		if (objType.lenttemplate != -1) {
			objType.genLent(get(objType.lenttemplate), get(objType.lentlink));
		}
		if (!Static240.aBoolean276 && objType.members) {
			objType.name = LocalizedText.MEMBERS_OBJECT;
			objType.team = 0;
			objType.iops = Static143.aClass100Array104;
			objType.stockmarket = false;
			objType.ops = Static269.aClass100Array87;
		}
		Static27.aClass99_4.put(objType, (long) id);
		return objType;
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "a", descriptor = "(B)V")
	public static void method1441() {
		Static279.aClass99_38.method3103();
		Static56.aClass99_9.method3103();
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "b", descriptor = "(IB)V")
	public static void method1443() {
		Static83.aClass99_3.method3102(5);
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "b", descriptor = "(I)V")
	public static void updatePlayers() {
		for (@Pc(7) int currentPlayerIndex = -1; currentPlayerIndex < Static267.playerCount; currentPlayerIndex++) {
			@Pc(21) int actualIndex;
			if (currentPlayerIndex == -1) {
				actualIndex = 2047;
			} else {
				actualIndex = Static105.playerIds[currentPlayerIndex];
			}
			@Pc(31) PlayerEntity player = Static159.players[actualIndex];
			if (player != null) {
				Static263.updateEntity(player.size(), player);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "c", descriptor = "(I)V")
	public static void transmitVerifyId() {
		Static6.outboundBuffer.pIsaac1(177);
		Static6.outboundBuffer.p2(Static189.anInt4443);
	}
}
