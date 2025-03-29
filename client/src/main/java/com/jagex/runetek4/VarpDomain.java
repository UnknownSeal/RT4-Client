package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarpType;
import com.jagex.runetek4.cache.def.VarbitType;
import com.jagex.runetek4.core.datastruct.HashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarpDomain {

    @OriginalMember(owner = "client!gj", name = "q", descriptor = "[I")
    public static final int[] updatedVarps = new int[32];
    @OriginalMember(owner = "runetek4.client!ic", name = "e", descriptor = "[I")
    public static final int[] varp = new int[2500];
    @OriginalMember(owner = "client!ah", name = "j", descriptor = "[I")
    public static final int[] activeVarps = new int[2500];
    @OriginalMember(owner = "runetek4.client!uj", name = "s", descriptor = "Lclient!na;")
    public static final JString aClass100_1061 = JString.parse("null");
    @OriginalMember(owner = "runetek4.client!ea", name = "s", descriptor = "[I")
    public static final int[] varbitMasks = new int[32];
    @OriginalMember(owner = "client!fi", name = "n", descriptor = "I")
    public static int updatedVarpsWriterIndex = 0;
    @OriginalMember(owner = "runetek4.client!qc", name = "K", descriptor = "Lclient!sc;")
    public static HashTable aClass133_20 = new HashTable(16);
    @OriginalMember(owner = "client!ge", name = "m", descriptor = "I")
    public static int chatEffectsDisabled = 0;
    @OriginalMember(owner = "runetek4.client!jb", name = "n", descriptor = "I")
    public static int oneMouseButton = 0;
    @OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "I")
    public static int bankInsertMode = 0;

    @OriginalMember(owner = "client!aj", name = "i", descriptor = "(I)V")
    public static void resetVarBits() {
        for (@Pc(3) int varpIndex = 0; varpIndex < VarpTypeList.varpTypeSize; varpIndex++) {
            @Pc(19) VarpType varpType = VarpTypeList.get(varpIndex);
            if (varpType != null && varpType.clientCode == 0) {
                varp[varpIndex] = 0;
                activeVarps[varpIndex] = 0;
            }
        }
        aClass133_20 = new HashTable(16);
    }

    @OriginalMember(owner = "client!gl", name = "a", descriptor = "(II)V")
    public static void refreshMagicVarp(@OriginalArg(1) int arg0) {
        InterfaceList.redrawActiveInterfaces();
        AreaSoundManager.setObjectSounds();
        @Pc(17) int varpType = VarpTypeList.get(arg0).clientCode;
        if (varpType == 0) {
            return;
        }
        @Pc(25) int varpValue = activeVarps[arg0];
        if (varpType == 6) {
            chatEffectsDisabled = varpValue;
        }
        if (varpType == 5) {
            oneMouseButton = varpValue;
        }
        if (varpType == 9) {
            bankInsertMode = varpValue;
        }
    }

    @OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(BII)V")
    public static void set(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        varp[arg1] = arg0;
        @Pc(20) LongNode local20 = (LongNode) aClass133_20.get((long) arg1);
        if (local20 == null) {
            local20 = new LongNode(4611686018427387905L);
            aClass133_20.put(local20, (long) arg1);
        } else if (local20.value != 4611686018427387905L) {
            local20.value = MonotonicTime.currentTimeMillis() + 500L | 0x4000000000000000L;
        }
    }

    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IZI)V")
    public static void setVarbitClient(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(7) VarbitType local7 = VarBitTypeList.get(arg0);
        @Pc(10) int local10 = local7.endBit;
        @Pc(16) int local16 = local7.startBit;
        @Pc(19) int local19 = local7.baseVar;
        @Pc(25) int local25 = varbitMasks[local10 - local16];
        if (arg1 < 0 || arg1 > local25) {
            arg1 = 0;
        }
        local25 <<= local16;
        method2766(local19, local25 & arg1 << local16 | activeVarps[local19] & ~local25);
    }

    @OriginalMember(owner = "client!cn", name = "a", descriptor = "(ZI)I")
    public static int poll(@OriginalArg(0) boolean arg0) {
        @Pc(4) long local4 = MonotonicTime.currentTimeMillis();
        for (@Pc(28) LongNode local28 = arg0 ? (LongNode) aClass133_20.head() : (LongNode) aClass133_20.next(); local28 != null; local28 = (LongNode) aClass133_20.next()) {
            if ((local28.value & 0x3FFFFFFFFFFFFFFFL) < local4) {
                if ((local28.value & 0x4000000000000000L) != 0L) {
                    @Pc(58) int local58 = (int) local28.nodeId;
                    activeVarps[local58] = varp[local58];
                    local28.unlink();
                    return local58;
                }
                local28.unlink();
            }
        }
        return -1;
    }

    @OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(III)V")
    public static void method2766(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        activeVarps[arg0] = arg1;
        @Pc(21) LongNode local21 = (LongNode) aClass133_20.get((long) arg0);
        if (local21 == null) {
            local21 = new LongNode(MonotonicTime.currentTimeMillis() + 500L);
            aClass133_20.put(local21, (long) arg0);
        } else {
            local21.value = MonotonicTime.currentTimeMillis() + 500L;
        }
    }

    @OriginalMember(owner = "runetek4.client!wd", name = "a", descriptor = "(BII)V")
    public static void setVarbit(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        @Pc(14) VarbitType local14 = VarBitTypeList.get(arg1);
        @Pc(17) int local17 = local14.baseVar;
        @Pc(20) int local20 = local14.endBit;
        @Pc(23) int local23 = local14.startBit;
        @Pc(29) int local29 = varbitMasks[local20 - local23];
        if (arg0 < 0 || local29 < arg0) {
            arg0 = 0;
        }
        local29 <<= local23;
        set(arg0 << local23 & local29 | ~local29 & varp[local17], local17);
    }
}
