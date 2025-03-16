package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class FriendList {
    @OriginalMember(owner = "runetek4.client!nc", name = "m", descriptor = "I")
    public static int state = 0;
    @OriginalMember(owner = "client!al", name = "m", descriptor = "I")
    public static int friendCount = 0;
}
