package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class MiniMenu {
    @OriginalMember(owner = "runetek4.client!sk", name = "kb", descriptor = "I")
    public static int menuActionRow = 0;
    @OriginalMember(owner = "runetek4.client!vd", name = "C", descriptor = "I")
    public static int anInt5014 = 0;
    @OriginalMember(owner = "runetek4.client!th", name = "n", descriptor = "Z")
    public static boolean aBoolean302 = false;
    @OriginalMember(owner = "runetek4.client!pk", name = "bb", descriptor = "Lclient!na;")
    public static JString walkText;
    @OriginalMember(owner = "runetek4.client!em", name = "D", descriptor = "I")
    public static int gregorianDateSeed;

    @OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
    public static boolean method4265(@OriginalArg(1) Component arg0) {
        if (arg0.contentType == 205) {
            Game.idleTimeout = 250;
            return true;
        } else {
            return false;
        }
    }
}
