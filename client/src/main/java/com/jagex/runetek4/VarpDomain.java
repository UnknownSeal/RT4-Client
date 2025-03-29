package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.core.datastruct.HashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarpDomain {
    @OriginalMember(owner = "client!aj", name = "i", descriptor = "(I)V")
    public static void resetVarBits() {
        for (@Pc(3) int varpIndex = 0; varpIndex < VarPlayerDefinition.varPlayerDefinitionsSize; varpIndex++) {
            @Pc(19) VarPlayerDefinition varPlayerDefinition = VarPlayerDefinition.getDefinition(varpIndex);
            if (varPlayerDefinition != null && varPlayerDefinition.type == 0) {
                varPlayerDefinition.varPlayerCache[varpIndex] = 0;
                varPlayerDefinition.activeVarps[varpIndex] = 0;
            }
        }
        Static199.aClass133_20 = new HashTable(16);
    }

    @OriginalMember(owner = "client!gl", name = "a", descriptor = "(II)V")
    public static void refreshMagicVarp(@OriginalArg(1) int arg0) {
        InterfaceList.redrawActiveInterfaces();
        AreaSoundManager.setObjectSounds();
        @Pc(17) int varpType = VarPlayerDefinition.getDefinition(arg0).type;
        if (varpType == 0) {
            return;
        }
        @Pc(25) int varpValue = VarPlayerDefinition.activeVarps[arg0];
        if (varpType == 6) {
            Static79.chatEffectsDisabled = varpValue;
        }
        if (varpType == 5) {
            Static116.oneMouseButton = varpValue;
        }
        if (varpType == 9) {
            Static179.bankInsertMode = varpValue;
        }
    }

    @OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(BII)V")
    public static void set(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        VarPlayerDefinition.varPlayerCache[arg1] = arg0;
        @Pc(20) LongNode local20 = (LongNode) Static199.aClass133_20.get((long) arg1);
        if (local20 == null) {
            local20 = new LongNode(4611686018427387905L);
            Static199.aClass133_20.put(local20, (long) arg1);
        } else if (local20.value != 4611686018427387905L) {
            local20.value = MonotonicTime.currentTimeMillis() + 500L | 0x4000000000000000L;
        }
    }

    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IZI)V")
    public static void setVarbitClient(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(7) VarbitDefinition local7 = VarbitDefinition.getDefinition(arg0);
        @Pc(10) int local10 = local7.anInt3323;
        @Pc(16) int local16 = local7.anInt3318;
        @Pc(19) int local19 = local7.index;
        @Pc(25) int local25 = VarbitDefinition.varbitMasks[local10 - local16];
        if (arg1 < 0 || arg1 > local25) {
            arg1 = 0;
        }
        local25 <<= local16;
        Static148.method2766(local19, local25 & arg1 << local16 | VarPlayerDefinition.activeVarps[local19] & ~local25);
    }
}
