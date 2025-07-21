package com.unknownseal.mixinlauncher.utils;

import com.jagex.runetek4.JString;

public class StringUtils {

    public static String fromJstring(JString jstr) {
        try {
            return new String(jstr.chars, 0, jstr.length, "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return new String(jstr.chars, 0, jstr.length);
        }
    }

}
