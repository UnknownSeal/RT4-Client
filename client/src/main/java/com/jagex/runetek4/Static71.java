package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.def.ItemDefinition;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static71 {

	@OriginalMember(owner = "runetek4.client!fk", name = "e", descriptor = "I")
	public static int anInt1885;

	@OriginalMember(owner = "runetek4.client!fk", name = "g", descriptor = "Z")
	public static boolean aBoolean107 = true;

	@OriginalMember(owner = "runetek4.client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];

	@OriginalMember(owner = "client!fk", name = "a", descriptor = "(IB)Lclient!h;")
	public static ItemDefinition get(@OriginalArg(0) int id) {
		@Pc(6) ItemDefinition itemDefinition = (ItemDefinition) Static27.aClass99_4.get((long) id);
		if (itemDefinition != null) {
			return itemDefinition;
		}
		@Pc(25) byte[] bytes = CacheArchive.aClass153_61.getfile(Static18.method554(id), Static247.method4247(id));
		itemDefinition = new ItemDefinition();
		itemDefinition.anInt2354 = id;
		if (bytes != null) {
			itemDefinition.readValues(new Packet(bytes));
		}
		itemDefinition.postDecode();

		if (itemDefinition.certtemplate != -1) {
			itemDefinition.genCert(get(itemDefinition.certtemplate), get(itemDefinition.certlink));
		}
		if (itemDefinition.lenttemplate != -1) {
			itemDefinition.genLent(get(itemDefinition.lenttemplate), get(itemDefinition.lentlink));
		}
		if (!Static240.aBoolean276 && itemDefinition.members) {
			itemDefinition.name = LocalizedText.MEMBERS_OBJECT;
			itemDefinition.team = 0;
			itemDefinition.interfaceOptions = Static143.aClass100Array104;
			itemDefinition.stockmarket = false;
			itemDefinition.groundOptions = Static269.aClass100Array87;
		}
		Static27.aClass99_4.put(itemDefinition, (long) id);
		return itemDefinition;
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "a", descriptor = "(B)V")
	public static void removeSoft() {
		Static279.aClass99_38.removeSoft();
		SpotAnimDefinition.modelCache.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "b", descriptor = "(IB)V")
	public static void method1443() {
		Static83.aClass99_3.clear(5);
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "c", descriptor = "(I)V")
	public static void transmitVerifyId() {
		Protocol.outboundBuffer.pIsaac1(177);
		Protocol.outboundBuffer.p2(Protocol.verifyId);
	}
}
