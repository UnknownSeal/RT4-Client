package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class MusicPlayer {
    @OriginalMember(owner = "runetek4.client!uf", name = "m", descriptor = "I")
    public static int titleSong;
    @OriginalMember(owner = "runetek4.client!s", name = "c", descriptor = "I")
    public static int groupId = -1;
}
