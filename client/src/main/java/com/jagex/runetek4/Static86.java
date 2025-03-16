package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.math.BigInteger;

public final class Static86 {

    // Jagex's RSA key:
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

	public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

    @OriginalMember(owner = "runetek4.client!gm", name = "ib", descriptor = "Lclient!ve;")
	public static Js5 aClass153_37;

	@OriginalMember(owner = "runetek4.client!gm", name = "R", descriptor = "I")
	public static int anInt2293 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "runetek4.client!gm", name = "W", descriptor = "Lclient!na;")
	public static final JString aClass100_488 = JString.parse("_");

	@OriginalMember(owner = "runetek4.client!gm", name = "bb", descriptor = "Z")
	public static boolean aBoolean129 = false;

	@OriginalMember(owner = "runetek4.client!gm", name = "db", descriptor = "Lclient!na;")
	public static final JString CROSS = JString.parse("cross");

	@OriginalMember(owner = "runetek4.client!gm", name = "gb", descriptor = "[I")
	public static final int[] WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };

	@OriginalMember(owner = "runetek4.client!gm", name = "h", descriptor = "(I)V")
	public static void method1800() {
		Static116.entityUpdateCount = 0;
		Static240.entityRemovalCount = 0;
		Static49.method1202();
		Static278.method4645();
		Static234.method4014();
		@Pc(19) int i;
		for (i = 0; i < Static240.entityRemovalCount; i++) {
			@Pc(30) int local30 = Static52.entityRemovalIds[i];
			if (NpcList.npcs[local30].cycle != client.loop) {
				if (NpcList.npcs[local30].type.hasBackgroundSound()) {
					AreaSoundManager.remove(NpcList.npcs[local30]);
				}
				NpcList.npcs[local30].method2698(null);
				NpcList.npcs[local30] = null;
			}
		}
		if (Static223.packetSize != Protocol.inboundBuffer.offset) {
			throw new RuntimeException("gnp1 pos:" + Protocol.inboundBuffer.offset + " psize:" + Static223.packetSize);
		}
		for (i = 0; i < NpcList.npcCount; i++) {
			if (NpcList.npcs[Static33.npcIds[i]] == null) {
				throw new RuntimeException("gnp2 pos:" + i + " size:" + NpcList.npcCount);
			}
		}
	}
}
