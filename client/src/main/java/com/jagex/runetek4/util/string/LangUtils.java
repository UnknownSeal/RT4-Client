package com.jagex.runetek4.util.string;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LangUtils {
    @OriginalMember(owner = "runetek4.client!mc", name = "fb", descriptor = "Lclient!na;")
    public static final JString LANG_PT = JString.parse("pt");

    @OriginalMember(owner = "runetek4.client!mc", name = "mb", descriptor = "Lclient!na;")
    public static final JString LANG_EN = JString.parse("en");

    @OriginalMember(owner = "runetek4.client!mc", name = "vb", descriptor = "Lclient!na;")
    public static final JString LANG_DE = JString.parse("de");

    @OriginalMember(owner = "runetek4.client!mc", name = "pb", descriptor = "Lclient!na;")
    public static final JString LANG_FR = JString.parse("fr");

    @OriginalMember(owner = "runetek4.client!mc", name = "ob", descriptor = "[Lclient!na;")
    public static final JString[] LANGUAGES = new JString[] {LANG_EN, LANG_DE, LANG_FR, LANG_PT};

    @OriginalMember(owner = "runetek4.client!hm", name = "a", descriptor = "(Lclient!na;B)I")
    public static int method2053(@OriginalArg(0) JString arg0) {
        for (@Pc(12) int local12 = 0; local12 < LANGUAGES.length; local12++) {
            if (LANGUAGES[local12].equalsIgnoreCase(arg0)) {
                return local12;
            }
        }
        return -1;
    }
}
