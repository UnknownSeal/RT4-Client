package com.jagex.ui.chat;

import com.jagex.network.ClientProt;
import com.jagex.ui.social.ClanMember;
import com.jagex.core.utils.string.JString;
import com.jagex.network.Protocol;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ClanChat {
    @OriginalMember(owner = "client!wj", name = "l", descriptor = "I")
    public static int transmitAt = 0;

    @OriginalMember(owner = "client!mj", name = "u", descriptor = "B")
    public static byte rank;

    @OriginalMember(owner = "client!e", name = "rc", descriptor = "B")
    public static byte minKick;

    @OriginalMember(owner = "client!wb", name = "m", descriptor = "Lclient!na;")
    public static JString owner = null;

    @OriginalMember(owner = "client!be", name = "ac", descriptor = "Lclient!na;")
    public static JString name = null;

    @OriginalMember(owner = "client!rg", name = "y", descriptor = "I")
    public static int size;

    @OriginalMember(owner = "client!qc", name = "bb", descriptor = "[Lclient!kl;")
    public static ClanMember[] members;

    @OriginalMember(owner = "client!kh", name = "b", descriptor = "(I)V")
    public static void leave() {
        Protocol.outboundBuffer.pIsaac1(ClientProt.CLANCHAT_JOINCHANNEL);
        Protocol.outboundBuffer.p8(0L);
    }

    @OriginalMember(owner = "client!mf", name = "a", descriptor = "(JI)V")
    public static void join(@OriginalArg(0) long clanName) {
        if ((long) 0 != clanName) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.CLANCHAT_JOINCHANNEL);
            Protocol.outboundBuffer.p8(clanName);
        }
    }

    @OriginalMember(owner = "client!od", name = "a", descriptor = "(ILclient!na;)V")
    public static void kick(@OriginalArg(1) JString userToKick) {
        if (members == null) {
            return;
        }
        @Pc(22) long username37 = userToKick.encode37();
        @Pc(24) int i = 0;
        if (username37 == 0L) {
            return;
        }
        while (members.length > i && members[i].key != username37) {
            i++;
        }
        if (i < members.length && members[i] != null) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.CLANCHAT_LEAVECHANNEL);
            Protocol.outboundBuffer.p8(members[i].key);
        }
    }
}
