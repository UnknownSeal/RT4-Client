package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.CollisionMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class PathFinder {
    @OriginalMember(owner = "runetek4.client!li", name = "h", descriptor = "[Lclient!mj;")
    public static final CollisionMap[] collisionMaps = new CollisionMap[4];
    @OriginalMember(owner = "runetek4.client!vc", name = "eb", descriptor = "[I")
    public static final int[] queueX = new int[4096];
    @OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
    public static final int[] queueZ = new int[4096];

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
    public static boolean tryMove(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
        if (PlayerList.self.getSize() == 2) {
            return Static2.method8(arg6, arg7, arg4, arg0, arg9, arg3, arg2, arg1, arg5, arg8, arg10);
        } else if (PlayerList.self.getSize() <= 2) {
            return Static45.method1171(arg5, arg4, arg10, arg9, arg8, arg2, arg1, arg3, arg7, arg0, arg6);
        } else {
            return Static96.method1955(arg9, arg6, arg8, arg1, PlayerList.self.getSize(), arg5, arg7, arg4, arg10, arg2, arg3, arg0);
        }
    }
}
