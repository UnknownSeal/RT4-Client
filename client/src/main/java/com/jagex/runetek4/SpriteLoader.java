package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class SpriteLoader {
    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIBLclient!ve;)Lclient!qf;")
    public static Sprite loadSprites(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1) {
        return Static234.method4016(arg1, 0, arg0) ? Static82.method1764() : null;
    }

    @OriginalMember(owner = "runetek4.client!gd", name = "a", descriptor = "(ILclient!ve;II)Lclient!mm;")
    public static SoftwareSprite loadSoftwareSprite(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(3) int arg2) {
        return Static234.method4016(arg1, arg0, arg2) ? Static196.method3537() : null;
    }
}
