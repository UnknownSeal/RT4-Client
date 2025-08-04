package com.jagex.runetek4.ui.chat;

import com.jagex.runetek4.ui.social.ClanMember;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.network.Protocol;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ClanChat {
    @OriginalMember(owner = "runetek4.client!wj", name = "l", descriptor = "I")
    public static int transmitAt = 0;
    @OriginalMember(owner = "runetek4.client!mj", name = "u", descriptor = "B")
    public static byte rank;
    @OriginalMember(owner = "runetek4.client!e", name = "rc", descriptor = "B")
    public static byte minKick;
    @OriginalMember(owner = "runetek4.client!wb", name = "m", descriptor = "Lclient!na;")
    public static JString owner = null;
    @OriginalMember(owner = "client!be", name = "ac", descriptor = "Lclient!na;")
    public static JString name = null;
    @OriginalMember(owner = "runetek4.client!rg", name = "y", descriptor = "I")
    public static int size;
    @OriginalMember(owner = "runetek4.client!qc", name = "bb", descriptor = "[Lclient!kl;")
    public static ClanMember[] members;

    @OriginalMember(owner = "runetek4.client!kh", name = "b", descriptor = "(I)V")
    public static void leave() {
        Protocol.outboundBuffer.pIsaac1(104);
        Protocol.outboundBuffer.p8(0L);
    }

    @OriginalMember(owner = "runetek4.client!mf", name = "a", descriptor = "(JI)V")
    public static void join(@OriginalArg(0) long arg0) {
        if ((long) 0 != arg0) {
            Protocol.outboundBuffer.pIsaac1(104);
            Protocol.outboundBuffer.p8(arg0);
        }
    }

    @OriginalMember(owner = "runetek4.client!od", name = "a", descriptor = "(ILclient!na;)V")
    public static void kick(@OriginalArg(1) JString arg0) {
        if (members == null) {
            return;
        }
        @Pc(22) long local22 = arg0.encode37();
        @Pc(24) int local24 = 0;
        if (local22 == 0L) {
            return;
        }
        while (members.length > local24 && members[local24].nodeId != local22) {
            local24++;
        }
        if (local24 < members.length && members[local24] != null) {
            Protocol.outboundBuffer.pIsaac1(162);
            Protocol.outboundBuffer.p8(members[local24].nodeId);
        }
    }
}
