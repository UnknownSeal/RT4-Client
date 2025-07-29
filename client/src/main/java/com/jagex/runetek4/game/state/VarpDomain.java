package com.jagex.runetek4.game.state;

import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.config.types.varp.VarpType;
import com.jagex.runetek4.config.types.varbit.VarbitType;
import com.jagex.runetek4.config.types.varbit.VarBitTypeList;
import com.jagex.runetek4.config.types.varp.VarpTypeList;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.LongNode;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.ui.widget.WidgetList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarpDomain {

    @OriginalMember(owner = "client!gj", name = "q", descriptor = "[I")
    public static final int[] updatedVarps = new int[32];
    @OriginalMember(owner = "runetek4.client!ic", name = "e", descriptor = "[I")
    public static final int[] serverVarps = new int[2500];
    @OriginalMember(owner = "client!ah", name = "j", descriptor = "[I")
    public static final int[] activeVarps = new int[2500];
    @OriginalMember(owner = "runetek4.client!uj", name = "s", descriptor = "Lclient!na;")
    public static final JString NULL = JString.parse("null");
    @OriginalMember(owner = "runetek4.client!ea", name = "s", descriptor = "[I")
    public static final int[] varbitMasks = new int[32];
    @OriginalMember(owner = "client!fi", name = "n", descriptor = "I")
    public static int updatedVarpsWriterIndex = 0;
    @OriginalMember(owner = "runetek4.client!qc", name = "K", descriptor = "Lclient!sc;")
    public static HashTable pendingUpdates = new HashTable(16);
    @OriginalMember(owner = "client!ge", name = "m", descriptor = "I")
    public static int chatEffectsDisabled = 0;
    @OriginalMember(owner = "runetek4.client!jb", name = "n", descriptor = "I")
    public static int oneMouseButton = 0;
    @OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "I")
    public static int bankInsertMode = 0;

    @OriginalMember(owner = "client!aj", name = "i", descriptor = "(I)V")
    public static void resetVarBits() {
        for (@Pc(3) int varpIndex = 0; varpIndex < VarpTypeList.capacity; varpIndex++) {
            @Pc(19) VarpType varpType = VarpTypeList.get(varpIndex);
            if (varpType != null && varpType.clientCode == 0) {
                serverVarps[varpIndex] = 0;
                activeVarps[varpIndex] = 0;
            }
        }
        pendingUpdates = new HashTable(16);
    }

    @OriginalMember(owner = "client!gl", name = "a", descriptor = "(II)V")
    public static void refreshMagicVarp(@OriginalArg(1) int varpIndex) {
        WidgetList.redrawActiveInterfaces();
        AreaSoundManager.setObjectSounds();
        @Pc(17) int varpType = VarpTypeList.get(varpIndex).clientCode;
        if (varpType == 0) {
            return;
        }
        @Pc(25) int varpValue = activeVarps[varpIndex];
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
    public static void setVarpServer(@OriginalArg(1) int value, @OriginalArg(2) int id) {
        serverVarps[id] = value;
        @Pc(20) LongNode node = (LongNode) pendingUpdates.get((long) id);
        if (node == null) {
            node = new LongNode(4611686018427387905L);
            pendingUpdates.put(node, (long) id);
        } else if (node.value != 4611686018427387905L) {
            node.value = MonotonicTime.currentTimeMillis() + 500L | 0x4000000000000000L;
        }
    }

    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IZI)V")
    public static void setVarbitClient(@OriginalArg(0) int arg0, @OriginalArg(2) int value) {
        @Pc(7) VarbitType type = VarBitTypeList.get(arg0);
        @Pc(10) int end = type.endBit;
        @Pc(16) int start = type.startBit;
        @Pc(19) int varp = type.baseVar;
        @Pc(25) int mask = varbitMasks[end - start];
        if (value < 0 || value > mask) {
            value = 0;
        }
        mask <<= start;
        setVarpClient(varp, mask & value << start | activeVarps[varp] & ~mask);
    }

    @OriginalMember(owner = "client!cn", name = "a", descriptor = "(ZI)I")
    public static int poll(@OriginalArg(0) boolean arg0) {
        @Pc(4) long local4 = MonotonicTime.currentTimeMillis();
        for (@Pc(28) LongNode local28 = arg0 ? (LongNode) pendingUpdates.head() : (LongNode) pendingUpdates.next(); local28 != null; local28 = (LongNode) pendingUpdates.next()) {
            if ((local28.value & 0x3FFFFFFFFFFFFFFFL) < local4) {
                if ((local28.value & 0x4000000000000000L) != 0L) {
                    @Pc(58) int local58 = (int) local28.nodeId;
                    activeVarps[local58] = serverVarps[local58];
                    local28.unlink();
                    return local58;
                }
                local28.unlink();
            }
        }
        return -1;
    }

    @OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(III)V")
    public static void setVarpClient(@OriginalArg(0) int id, @OriginalArg(2) int value) {
        activeVarps[id] = value;
        @Pc(21) LongNode node = (LongNode) pendingUpdates.get((long) id);
        if (node == null) {
            node = new LongNode(MonotonicTime.currentTimeMillis() + 500L);
            pendingUpdates.put(node, (long) id);
        } else {
            node.value = MonotonicTime.currentTimeMillis() + 500L;
        }
    }

    @OriginalMember(owner = "runetek4.client!wd", name = "a", descriptor = "(BII)V")
    public static void setVarbitServer(@OriginalArg(1) int value, @OriginalArg(2) int arg1) {
        @Pc(14) VarbitType type = VarBitTypeList.get(arg1);
        @Pc(17) int varp = type.baseVar;
        @Pc(20) int end = type.endBit;
        @Pc(23) int start = type.startBit;
        @Pc(29) int mask = varbitMasks[end - start];
        if (value < 0 || mask < value) {
            value = 0;
        }
        mask <<= start;
        setVarpServer(value << start & mask | ~mask & serverVarps[varp], varp);
    }

    @OriginalMember(owner = "runetek4.client!me", name = "a", descriptor = "(II)I")
    public static int getVarbitValue(@OriginalArg(1) int varbitId) {
        @Pc(13) VarbitType type = VarBitTypeList.get(varbitId);
        @Pc(16) int varp = type.baseVar;
        @Pc(19) int end = type.endBit;
        @Pc(22) int start = type.startBit;
        @Pc(29) int mask = varbitMasks[end - start];
        return activeVarps[varp] >> start & mask;
    }
}
