package com.jagex.runetek4.util.string;

import com.jagex.runetek4.ui.component.MiniMenu;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class StringUtils {
    @OriginalMember(owner = "client!ag", name = "ab", descriptor = "Lclient!na;")
    public static final JString aClass100_17 = JString.parse("0");
    @OriginalMember(owner = "client!ca", name = "cb", descriptor = "Lclient!na;")
    public static final JString nonBreakingSpace = JString.getNbsp();
    @OriginalMember(owner = "runetek4.client!rm", name = "i", descriptor = "Lclient!na;")
    public static final JString ASTERISK = JString.parse("(Z");
    @OriginalMember(owner = "client!fm", name = "W", descriptor = "Lclient!na;")
    public static final JString aClass100_453 = JString.parse(")2");
    @OriginalMember(owner = "client!cg", name = "h", descriptor = "Lclient!na;")
    public static final JString aClass100_185 = JString.parse(")3");
    @OriginalMember(owner = "runetek4.client!tl", name = "i", descriptor = "Lclient!na;")
    public static final JString aClass100_1017 = JString.parse(")1");

    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(IB)Lclient!na;")
    public static JString toString(@OriginalArg(0) int arg0) {
        return arg0 >= 999999999 ? ASTERISK : JString.parseInt(arg0);
    }

    @OriginalMember(owner = "runetek4.client!oj", name = "a", descriptor = "(IZIJI)Lclient!na;")
    public static JString formatNumber(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) long arg3) {
        @Pc(9) JString local9 = JString.allocate(0);
        if (arg3 < 0L) {
            arg3 = -arg3;
            local9.method3113(aClass100_453);
        }
        @Pc(26) JString local26 = aClass100_1017;
        @Pc(28) JString local28 = aClass100_185;
        if (arg0 == 1) {
            local26 = aClass100_185;
            local28 = aClass100_1017;
        }
        if (arg0 == 2) {
            local28 = aClass100_1017;
            local26 = nonBreakingSpace;
        }
        if (arg0 == 3) {
            local26 = aClass100_185;
            local28 = aClass100_1017;
        }
        @Pc(59) JString local59 = JString.allocate(0);
        @Pc(61) int local61;
        for (local61 = 0; local61 < arg2; local61++) {
            local59.method3113(JString.parseInt((int) (arg3 % 10L)));
            arg3 /= 10L;
        }
        local61 = 0;
        @Pc(137) JString newComponent;
        if (arg3 == 0L) {
            newComponent = aClass100_17;
        } else {
            @Pc(95) JString local95 = JString.allocate(0);
            while (arg3 > 0L) {
                if (arg1 && local61 != 0 && local61 % 3 == 0) {
                    local95.method3113(local26);
                }
                local95.method3113(JString.parseInt((int) (arg3 % 10L)));
                local61++;
                arg3 /= 10L;
            }
            newComponent = local95;
        }
        if (local59.length() > 0) {
            local59.method3113(local28);
        }
        return JString.concatenate(new JString[] { local9, newComponent.method3124(), local59.method3124() });
    }

    @OriginalMember(owner = "runetek4.client!fi", name = "a", descriptor = "(BI)Lclient!na;")
    public static JString formatNumber(@OriginalArg(1) int number) {
        @Pc(9) JString numberStr = JString.parseInt(number);
        for (@Pc(21) int insertPos = numberStr.length() - 3; insertPos > 0; insertPos -= 3) {
            numberStr = JString.concatenate(new JString[] { numberStr.substring(insertPos, 0), JString.COMMA_SEPARATOR, numberStr.substring(insertPos) });
        }
        if (numberStr.length() > 9) {
            return JString.concatenate(new JString[] { JString.COLOR_SPRING_GREEN_SPACE, numberStr.substring(numberStr.length() - 8, 0), LocalizedText.MILLION_SHORT, MiniMenu.OPEN_PARENTHESIS, numberStr, JString.aClass100_583 });
        } else if (numberStr.length() > 6) {
            return JString.concatenate(new JString[] { JString.COLOR_WHITE_SPACE, numberStr.substring(numberStr.length() - 4, 0), LocalizedText.THOUSAND_SHORT, MiniMenu.OPEN_PARENTHESIS, numberStr, JString.aClass100_583 });
        } else {
            return JString.concatenate(new JString[] { JString.COLOR_YELLOW_SPACE, numberStr, JString.aClass100_978 });
        }
    }
}
