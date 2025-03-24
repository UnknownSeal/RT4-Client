package com.jagex.runetek4.cache.def;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

// VarbitDefinition
@OriginalClass("client!kk")
public final class VarbitDefinition {

	@OriginalMember(owner = "runetek4.client!ea", name = "s", descriptor = "[I")
	public static final int[] varbitMasks = new int[32];
	@OriginalMember(owner = "client!kk", name = "c", descriptor = "I")
	public int anInt3318;

	@OriginalMember(owner = "client!kk", name = "h", descriptor = "I")
	public int anInt3323;

	@OriginalMember(owner = "client!kk", name = "l", descriptor = "I")
	public int index;

    @OriginalMember(owner = "runetek4.client!me", name = "a", descriptor = "(II)I")
    public static int getVarbitValue(@OriginalArg(1) int varbitId) {
        @Pc(13) VarbitDefinition varbitDefinition = getDefinition(varbitId);
        @Pc(16) int varPlayerIndex = varbitDefinition.index;
        @Pc(19) int mostSignificantBit = varbitDefinition.anInt3323;
        @Pc(22) int leastSignificantBit = varbitDefinition.anInt3318;
        @Pc(29) int mask = varbitMasks[mostSignificantBit - leastSignificantBit];
        return VarPlayerDefinition.activeVarps[varPlayerIndex] >> leastSignificantBit & mask;
    }

	@OriginalMember(owner = "runetek4.client!jl", name = "a", descriptor = "(IB)Lclient!kk;")
	public static VarbitDefinition getDefinition(@OriginalArg(0) int varbitId) {
		@Pc(10) VarbitDefinition varbitDefinition = (VarbitDefinition) Static125.varbitDefinitionCache.get((long) varbitId);
		if (varbitDefinition != null) {
			return varbitDefinition;
		}
		@Pc(31) byte[] cacheData = Static172.gameDefinitionsJs5.getfile(Static254.method4349(varbitId), Static274.method3845(varbitId));
		varbitDefinition = new VarbitDefinition();
		if (cacheData != null) {
			varbitDefinition.decode(new Packet(cacheData));
		}
		Static125.varbitDefinitionCache.put(varbitDefinition, (long) varbitId);
		return varbitDefinition;
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(2) int opcode) {
		if (opcode == 1) {
			this.index = packet.g2();
			this.anInt3318 = packet.g1();
			this.anInt3323 = packet.g1();
		}
	}
}
