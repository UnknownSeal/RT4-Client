package com.jagex.runetek4.ui.chat;

import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.ui.widget.WidgetList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Chat {
    @OriginalMember(owner = "client!ca", name = "eb", descriptor = "[I")
    public static final int[] types = new int[100];
    @OriginalMember(owner = "runetek4.client!fb", name = "l", descriptor = "[Lclient!na;")
    public static final JString[] clans = new JString[100];
    @OriginalMember(owner = "runetek4.client!sj", name = "q", descriptor = "[Lclient!na;")
    public static final JString[] messages = new JString[100];
    @OriginalMember(owner = "runetek4.client!th", name = "l", descriptor = "[I")
    public static final int[] phraseIds = new int[100];
    @OriginalMember(owner = "runetek4.client!mc", name = "Y", descriptor = "[Lclient!na;")
    public static final JString[] names = new JString[100];
    @OriginalMember(owner = "runetek4.client!t", name = "w", descriptor = "[J")
    public static final long[] recentMessages = new long[100];
    @OriginalMember(owner = "runetek4.client!dm", name = "u", descriptor = "I")
    public static int transmitAt = 0;
    @OriginalMember(owner = "runetek4.client!f", name = "Z", descriptor = "I")
    public static int size = 0;
    @OriginalMember(owner = "runetek4.client!gk", name = "i", descriptor = "I")
    public static int tradeFilter = 0;
    @OriginalMember(owner = "runetek4.client!dm", name = "m", descriptor = "I")
    public static int privateFilter = 0;
    @OriginalMember(owner = "runetek4.client!ej", name = "U", descriptor = "I")
    public static int publicFilter = 0;
    @OriginalMember(owner = "runetek4.client!ug", name = "e", descriptor = "I")
    public static int messageCounter = 0;

    @OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(IILclient!na;Lclient!na;BLclient!na;)V")
    public static void add(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JString arg2, @OriginalArg(3) JString arg3, @OriginalArg(5) JString arg4) {
        for (@Pc(14) int local14 = 99; local14 > 0; local14--) {
            types[local14] = types[local14 - 1];
            names[local14] = names[local14 - 1];
            messages[local14] = messages[local14 - 1];
            clans[local14] = clans[local14 - 1];
            phraseIds[local14] = phraseIds[local14 - 1];
        }
        size++;
        types[0] = arg1;
        names[0] = arg4;
        transmitAt = WidgetList.transmitTimer;
        phraseIds[0] = arg0;
        messages[0] = arg2;
        clans[0] = arg3;
    }

    @OriginalMember(owner = "runetek4.client!i", name = "a", descriptor = "(Lclient!na;ILclient!na;I)V")
    public static void addMessage(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JString arg2) {
        add(-1, arg1, arg2, null, arg0);
    }

    @OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ILclient!na;Lclient!na;Lclient!na;I)V")
    public static void method1598(@OriginalArg(1) JString arg0, @OriginalArg(2) JString arg1, @OriginalArg(3) JString arg2) {
        add(-1, 9, arg0, arg2, arg1);
    }
}
