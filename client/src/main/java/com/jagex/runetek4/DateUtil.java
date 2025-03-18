package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    @OriginalMember(owner = "runetek4.client!cl", name = "K", descriptor = "Ljava/util/Calendar;")
    public static final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    @OriginalMember(owner = "runetek4.client!km", name = "Gc", descriptor = "Lclient!na;")
    static final JString JAN = JString.parse("Jan");

    @OriginalMember(owner = "runetek4.client!km", name = "Hc", descriptor = "Lclient!na;")
    static final JString FEB = JString.parse("Feb");

    @OriginalMember(owner = "runetek4.client!km", name = "zc", descriptor = "Lclient!na;")
    static final JString MAR = JString.parse("Mar");

    @OriginalMember(owner = "runetek4.client!km", name = "Tc", descriptor = "Lclient!na;")
    static final JString APR = JString.parse("Apr");

    @OriginalMember(owner = "runetek4.client!km", name = "xc", descriptor = "Lclient!na;")
    static final JString MAY = JString.parse("May");

    @OriginalMember(owner = "runetek4.client!km", name = "Wc", descriptor = "Lclient!na;")
    static final JString JUN = JString.parse("Jun");

    @OriginalMember(owner = "runetek4.client!km", name = "vc", descriptor = "Lclient!na;")
    static final JString JUL = JString.parse("Jul");

    @OriginalMember(owner = "runetek4.client!km", name = "Qc", descriptor = "Lclient!na;")
    static final JString AUG = JString.parse("Aug");

    @OriginalMember(owner = "runetek4.client!km", name = "cd", descriptor = "Lclient!na;")
    static final JString SEP = JString.parse("Sep");

    @OriginalMember(owner = "runetek4.client!km", name = "dd", descriptor = "Lclient!na;")
    static final JString OCT = JString.parse("Oct");

    @OriginalMember(owner = "runetek4.client!km", name = "yc", descriptor = "Lclient!na;")
    static final JString NOV = JString.parse("Nov");

    @OriginalMember(owner = "runetek4.client!km", name = "tc", descriptor = "Lclient!na;")
    static final JString DEC = JString.parse("Dec");

    @OriginalMember(owner = "runetek4.client!km", name = "Ac", descriptor = "[Lclient!na;")
    public static final JString[] MONTHS = new JString[] {JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC};


    @OriginalMember(owner = "runetek4.client!rl", name = "U", descriptor = "Lclient!na;")
    static final JString MON = JString.parse("Mon");

    @OriginalMember(owner = "runetek4.client!rl", name = "cb", descriptor = "Lclient!na;")
    static final JString TUE = JString.parse("Tue");

    @OriginalMember(owner = "runetek4.client!rl", name = "ab", descriptor = "Lclient!na;")
    static final JString WED = JString.parse("Wed");

    @OriginalMember(owner = "runetek4.client!rl", name = "bb", descriptor = "Lclient!na;")
    static final JString THU = JString.parse("Thu");

    @OriginalMember(owner = "runetek4.client!rl", name = "W", descriptor = "Lclient!na;")
    static final JString FRI = JString.parse("Fri");

    @OriginalMember(owner = "runetek4.client!rl", name = "T", descriptor = "Lclient!na;")
    static final JString SAT = JString.parse("Sat");

    @OriginalMember(owner = "runetek4.client!rl", name = "db", descriptor = "Lclient!na;")
    static final JString SUN = JString.parse("Sun");

    @OriginalMember(owner = "runetek4.client!rl", name = "Y", descriptor = "[Lclient!na;")
    public static final JString[] DAYS = new JString[] {SUN, MON, TUE, WED, THU, FRI, SAT};

    @OriginalMember(owner = "client!ck", name = "T", descriptor = "Lclient!na;")
    static final JString aClass100_207 = JString.parse("Jan");

    @OriginalMember(owner = "client!ck", name = "hb", descriptor = "Lclient!na;")
    static final JString aClass100_212 = JString.parse("Feb");

    @OriginalMember(owner = "client!ck", name = "gb", descriptor = "Lclient!na;")
    static final JString aClass100_211 = JString.parse("Mar");

    @OriginalMember(owner = "client!ck", name = "db", descriptor = "Lclient!na;")
    static final JString aClass100_210 = JString.parse("Apr");

    @OriginalMember(owner = "client!ck", name = "n", descriptor = "Lclient!na;")
    static final JString aClass100_199 = JString.parse("May");

    @OriginalMember(owner = "client!ck", name = "L", descriptor = "Lclient!na;")
    static final JString aClass100_204 = JString.parse("Jun");

    @OriginalMember(owner = "client!ck", name = "B", descriptor = "Lclient!na;")
    static final JString aClass100_202 = JString.parse("Jul");

    @OriginalMember(owner = "client!ck", name = "l", descriptor = "Lclient!na;")
    static final JString aClass100_198 = JString.parse("Aug");

    @OriginalMember(owner = "client!ck", name = "V", descriptor = "Lclient!na;")
    static final JString aClass100_208 = JString.parse("Sep");

    @OriginalMember(owner = "client!ck", name = "S", descriptor = "Lclient!na;")
    static final JString aClass100_206 = JString.parse("Oct");

    @OriginalMember(owner = "client!ck", name = "Y", descriptor = "Lclient!na;")
    static final JString aClass100_209 = JString.parse("Nov");

    @OriginalMember(owner = "client!ck", name = "O", descriptor = "Lclient!na;")
    static final JString aClass100_205 = JString.parse("Dec");

    @OriginalMember(owner = "client!ck", name = "f", descriptor = "[Lclient!na;")
    public static final JString[] aClass100Array40 = new JString[] {aClass100_207, aClass100_212, aClass100_211, aClass100_210, aClass100_199, aClass100_204, aClass100_202, aClass100_198, aClass100_208, aClass100_206, aClass100_209, aClass100_205};

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(JB)Lclient!na;")
    public static JString getDateString(@OriginalArg(0) long millis) {
        calendar.setTime(new Date(millis));
        @Pc(13) int day = calendar.get(Calendar.DAY_OF_WEEK);
        @Pc(17) int date = calendar.get(Calendar.DATE);
        @Pc(21) int month = calendar.get(Calendar.MONTH);
        @Pc(32) int year = calendar.get(Calendar.YEAR);
        @Pc(36) int hour = calendar.get(Calendar.HOUR_OF_DAY);
        @Pc(40) int minute = calendar.get(Calendar.MINUTE);
        @Pc(44) int second = calendar.get(Calendar.SECOND);
        return JString.concatenate(new JString[] { DAYS[day - 1], JString.COMMA_SIGN, JString.parseInt(date / 10), JString.parseInt(date % 10), JString.HYPHEN_SIGN, MONTHS[month], JString.HYPHEN_SIGN, JString.parseInt(year), JString.SPACE, JString.parseInt(hour / 10), JString.parseInt(hour % 10), JString.COLON_SIGN, JString.parseInt(minute / 10), JString.parseInt(minute % 10), JString.COLON_SIGN, JString.parseInt(second / 10), JString.parseInt(second % 10), JString.GMT});
    }
}
