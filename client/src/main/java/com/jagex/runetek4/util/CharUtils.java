package com.jagex.runetek4.util;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class CharUtils {
    @OriginalMember(owner = "runetek4.client!va", name = "a", descriptor = "(IB)Z")
    public static boolean isLetter(@OriginalArg(0) int arg0) {
        return arg0 >= 97 && arg0 <= 122 || arg0 >= 65 && arg0 <= 90;
    }

    @OriginalMember(owner = "client!bn", name = "d", descriptor = "(II)Z")
    public static boolean isDigit(@OriginalArg(1) int arg0) {
        return arg0 >= 48 && arg0 <= 57;
    }

    @OriginalMember(owner = "runetek4.client!gj", name = "a", descriptor = "(II)Z")
    public static boolean method433(@OriginalArg(0) int arg0) {
        if (arg0 >= 97 && arg0 <= 122) {
            return true;
        } else if (arg0 >= 65 && arg0 <= 90) {
            return true;
        } else {
            return arg0 >= 48 && arg0 <= 57;
        }
    }

    @OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(II)Z")
    public static boolean isValidChar(@OriginalArg(1) int arg0) {
        if (arg0 >= 32 && arg0 <= 126) {
            return true;
        } else if (arg0 >= 160 && arg0 <= 255) {
            return true;
        } else {
            return arg0 == 128 || arg0 == 140 || arg0 == 151 || arg0 == 156 || arg0 == 159;
        }
    }

    @OriginalMember(owner = "runetek4.client!ld", name = "a", descriptor = "(IB)I")
    public static int toUpperCase(@OriginalArg(0) int arg0) {
        if (arg0 >= 97 && arg0 <= 122 || arg0 >= 224 && arg0 <= 254 && arg0 != 247) {
            return arg0 - 32;
        } else if (arg0 == 255) {
            return 159;
        } else if (arg0 == 156) {
            return 140;
        } else {
            return arg0;
        }
    }

    @OriginalMember(owner = "runetek4.client!sk", name = "c", descriptor = "(II)I")
    public static int toLowerCase(@OriginalArg(1) int arg0) {
        if (arg0 >= 65 && arg0 <= 90 || arg0 >= 192 && arg0 <= 222 && arg0 != 215) {
            return arg0 + 32;
        } else if (arg0 == 159) {
            return 255;
        } else if (arg0 == 140) {
            return 156;
        } else {
            return arg0;
        }
    }
}
