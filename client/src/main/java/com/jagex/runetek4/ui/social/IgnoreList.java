package com.jagex.runetek4.ui.social;

import com.jagex.runetek4.util.data.Base37;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.ui.component.ComponentList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class IgnoreList {
    @OriginalMember(owner = "client!pf", name = "h", descriptor = "[J")
    public static final long[] encodedIgnores = new long[100];

    @OriginalMember(owner = "client!pi", name = "V", descriptor = "[Lclient!na;")
    public static final JString[] ignoreName37 = new JString[100];

    @OriginalMember(owner = "client!cl", name = "Z", descriptor = "I")
    public static int ignoreCount = 0;

    @OriginalMember(owner = "client!te", name = "b", descriptor = "(Lclient!na;I)Z")
    public static boolean contains(@OriginalArg(0) JString arg0) {
        if (arg0 == null) {
            return false;
        }
        for (@Pc(11) int local11 = 0; local11 < ignoreCount; local11++) {
            if (arg0.equalsIgnoreCase(ignoreName37[local11])) {
                return true;
            }
        }
        return false;
    }

    @OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(IJ)V")
    public static void addIgnore(@OriginalArg(1) long username) {
        if (username == 0L) {
            return;
        }
        if (ignoreCount >= 100) {
            Chat.addMessage(JString.EMPTY, 0, LocalizedText.IGNORELISTFULL);
            return;
        }
        @Pc(34) JString displayName = Base37.fromBase37(username).toTitleCase();
        @Pc(36) int i;
        for (i = 0; i < ignoreCount; i++) {
            if (encodedIgnores[i] == username) {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { displayName, LocalizedText.IGNORELISTDUPE}));
                return;
            }
        }
        for (i = 0; i < FriendList.friendCount; i++) {
            if (FriendList.encodedUsernames[i] == username) {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { LocalizedText.REMOVESOCIAL2, displayName, LocalizedText.REMOVEFRIEND}));
                return;
            }
        }
        if (displayName.strEquals(PlayerList.self.username)) {
            Chat.addMessage(JString.EMPTY, 0, LocalizedText.IGNORECANTADDSELF);
            return;
        }
        encodedIgnores[ignoreCount] = username;
        ignoreName37[ignoreCount++] = Base37.fromBase37(username);
        FriendList.transmitAt = ComponentList.transmitTimer;
        Protocol.outboundBuffer.pIsaac1(34);
        Protocol.outboundBuffer.p8(username);
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(JI)V")
    public static void remove(@OriginalArg(0) long username) {
        if (username == 0L) {
            return;
        }
        for (@Pc(12) int i = 0; i < ignoreCount; i++) {
            if (encodedIgnores[i] == username) {
                ignoreCount--;
                for (@Pc(36) int j = i; j < ignoreCount; j++) {
                    encodedIgnores[j] = encodedIgnores[j + 1];
                    ignoreName37[j] = ignoreName37[j + 1];
                }
                FriendList.transmitAt = ComponentList.transmitTimer;
                Protocol.outboundBuffer.pIsaac1(213);
                Protocol.outboundBuffer.p8(username);
                break;
            }
        }
    }
}
