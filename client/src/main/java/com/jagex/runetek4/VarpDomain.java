package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.core.datastruct.HashTable;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarpDomain {
    @OriginalMember(owner = "client!aj", name = "i", descriptor = "(I)V")
    public static void resetVarBits() {
        for (@Pc(3) int varpIndex = 0; varpIndex < VarPlayerDefinition.varPlayerDefinitionsSize; varpIndex++) {
            @Pc(19) VarPlayerDefinition varPlayerDefinition = VarPlayerDefinition.getDefinition(varpIndex);
            if (varPlayerDefinition != null && varPlayerDefinition.type == 0) {
                varPlayerDefinition.varPlayerCache[varpIndex] = 0;
                varPlayerDefinition.varPlayers[varpIndex] = 0;
            }
        }
        Static199.aClass133_20 = new HashTable(16);
    }
}
