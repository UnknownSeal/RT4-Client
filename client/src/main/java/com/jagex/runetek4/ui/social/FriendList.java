package com.jagex.runetek4.ui.social;

import com.jagex.runetek4.util.data.Base37;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.ui.component.ComponentList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class FriendList {

    @OriginalMember(owner = "runetek4.client!hd", name = "g", descriptor = "[J")
    public static final long[] encodedUsernames = new long[200];

    @OriginalMember(owner = "runetek4.client!rg", name = "r", descriptor = "[Lclient!na;")
    public static final JString[] worldNames = new JString[200];

    @OriginalMember(owner = "runetek4.client!ic", name = "l", descriptor = "[I")
    public static final int[] ranks = new int[200];

    @OriginalMember(owner = "runetek4.client!ia", name = "d", descriptor = "[I")
    public static final int[] friendWorlds = new int[200];

    @OriginalMember(owner = "runetek4.client!jh", name = "b", descriptor = "[Lclient!na;")
    public static final JString[] friendUsernames = new JString[200];

    @OriginalMember(owner = "runetek4.client!ab", name = "c", descriptor = "[Z")
    public static final boolean[] friendGame = new boolean[200];

    @OriginalMember(owner = "client!nc", name = "m", descriptor = "I")
    public static int state = 0;

    @OriginalMember(owner = "client!al", name = "m", descriptor = "I")
    public static int friendCount = 0;

    @OriginalMember(owner = "runetek4.client!p", name = "d", descriptor = "I")
    public static int transmitAt = 0;

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(Lclient!na;B)Z")
    public static boolean contains(@OriginalArg(0) JString username) {
        if (username == null) {
            return false;
        }
        for (@Pc(12) int i = 0; i < friendCount; i++) {
            if (username.equalsIgnoreCase(friendUsernames[i])) {
                return true;
            }
        }
        if (username.equalsIgnoreCase(PlayerList.self.username)) {
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "client!fb", name = "a", descriptor = "(JB)V")
    public static void addFriend(@OriginalArg(0) long username) {
        if (username == 0L) {
            return;
        }
        if (friendCount >= 100 && !LoginManager.playerMember || friendCount >= 200) {
            Chat.addMessage(JString.EMPTY, 0, LocalizedText.FRIENDLISTFULL);
            return;
        }
        @Pc(35) JString displayName = Base37.decode37(username).toTitleCase();
        @Pc(42) int i;
        for (i = 0; i < friendCount; i++) {
            if (encodedUsernames[i] == username) {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLISTDUPE}));
                return;
            }
        }
        for (i = 0; i < IgnoreList.ignoreCount; i++) {
            if (username == IgnoreList.encodedIgnores[i]) {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { LocalizedText.REMOVESOCIAL1, displayName, LocalizedText.REMOVEIGNORE}));
                return;
            }
        }
        if (displayName.strEquals(PlayerList.self.username)) {
            Chat.addMessage(JString.EMPTY, 0, LocalizedText.FRIENDCANTADDSELF);
            return;
        }
        friendUsernames[friendCount] = displayName;
        encodedUsernames[friendCount] = username;
        friendWorlds[friendCount] = 0;
        worldNames[friendCount] = JString.EMPTY;
        ranks[friendCount] = 0;
        friendGame[friendCount] = false;
        friendCount++;
        transmitAt = ComponentList.transmitTimer;
        Protocol.outboundBuffer.pIsaac1(120);
        Protocol.outboundBuffer.p8(username);
    }

    @OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "(JI)V")
    public static void removeFriend(@OriginalArg(0) long username) {
        if (username == 0L) {
            return;
        }
        for (@Pc(13) int local13 = 0; local13 < friendCount; local13++) {
            if (encodedUsernames[local13] == username) {
                friendCount--;
                for (@Pc(41) int i = local13; i < friendCount; i++) {
                    friendUsernames[i] = friendUsernames[i + 1];
                    friendWorlds[i] = friendWorlds[i + 1];
                    worldNames[i] = worldNames[i + 1];
                    encodedUsernames[i] = encodedUsernames[i + 1];
                    ranks[i] = ranks[i + 1];
                    friendGame[i] = friendGame[i + 1];
                }
                transmitAt = ComponentList.transmitTimer;
                Protocol.outboundBuffer.pIsaac1(57);
                Protocol.outboundBuffer.p8(username);
                break;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;I)V")
    public static void setRank(@OriginalArg(1) JString arg0, @OriginalArg(2) int arg1) {
        Protocol.outboundBuffer.pIsaac1(188);
        Protocol.outboundBuffer.p1b_alt1(arg1);
        Protocol.outboundBuffer.p8(arg0.encode37());
    }

    @OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(Lclient!na;I)I")
    public static int indexOf(@OriginalArg(0) JString arg0) {
        if (arg0 == null) {
            return -1;
        }
        for (@Pc(20) int local20 = 0; local20 < friendCount; local20++) {
            if (arg0.equalsIgnoreCase(friendUsernames[local20])) {
                return local20;
            }
        }
        return -1;
    }
}
