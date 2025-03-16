package com.jagex.runetek4.util;

import com.jagex.runetek4.Static53;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ArrayUtils {
    @OriginalMember(owner = "runetek4.client!kg", name = "a", descriptor = "([Ljava/lang/Object;I[Ljava/lang/Object;II)V")
    public static void copy(@OriginalArg(0) Object[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Object[] arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
        @Pc(15) int local15;
        if (arg0 == arg2) {
            if (arg1 == arg3) {
                return;
            }
            if (arg3 > arg1 && arg3 < arg1 + arg4) {
                local15 = arg4 - 1;
                @Pc(19) int local19 = arg1 + local15;
                @Pc(23) int local23 = arg3 + local15;
                local15 = local19 - local15;
                local15 += 7;
                while (local19 >= local15) {
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                    arg2[local23--] = arg0[local19--];
                }
                local15 -= 7;
                while (local19 >= local15) {
                    arg2[local23--] = arg0[local19--];
                }
                return;
            }
        }
        local15 = arg4 + arg1;
        @Pc(115) int local115 = local15 - 7;
        while (arg1 < local115) {
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
            arg2[arg3++] = arg0[arg1++];
        }
        local15 = local115 + 7;
        while (arg1 < local15) {
            arg2[arg3++] = arg0[arg1++];
        }
    }

    @OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(I[I[Ljava/lang/Object;)V")
    public static void sort(@OriginalArg(1) int[] arg0, @OriginalArg(2) Object[] arg1) {
        Static53.method1292(arg1, arg0.length - 1, arg0, 0);
    }
}
