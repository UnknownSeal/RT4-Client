package com.jagex.game.map;

import com.jagex.core.datastruct.LinkedList;
import com.jagex.cache.media.SoftwareSprite;
import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MapList {
    @OriginalMember(owner = "client!sh", name = "a", descriptor = "Lclient!ih;")
    public static final LinkedList areas = new LinkedList();
    @OriginalMember(owner = "client!ed", name = "D", descriptor = "Lclient!na;")
    public static final JString DETAILS = JString.parse("details");
    @OriginalMember(owner = "client!je", name = "W", descriptor = "Lclient!ve;")
    public static Js5 archive;
    @OriginalMember(owner = "runetek4.client!th", name = "p", descriptor = "[Lclient!mm;")
    public static SoftwareSprite[] sprites;
    @OriginalMember(owner = "runetek4.client!va", name = "G", descriptor = "[Z")
    public static boolean[] aBooleanArray130;

    @OriginalMember(owner = "client!ce", name = "a", descriptor = "(IBI)Lclient!bn;")
    public static Map getContainingSource(@OriginalArg(0) int z, @OriginalArg(2) int x) {
        for (@Pc(10) Map map = (Map) areas.head(); map != null; map = (Map) areas.next()) {
            if (map.valid && map.containsCoordinate(x, z)) {
                return map;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "([Lclient!mm;ILclient!ve;)V")
    public static void init(@OriginalArg(0) SoftwareSprite[] arg0, @OriginalArg(2) Js5 arg1) {
        archive = arg1;
        sprites = arg0;
        aBooleanArray130 = new boolean[sprites.length];
        areas.clear();
        @Pc(25) int local25 = archive.getGroupId(DETAILS);
        @Pc(30) int[] local30 = archive.getFileIds(local25);
        for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
            areas.push(Map.create(new Packet(archive.getfile(local25, local30[local32]))));
        }
    }

    @OriginalMember(owner = "runetek4.client!jk", name = "a", descriptor = "(ILclient!na;)Lclient!bn;")
    public static Map get(@OriginalArg(1) JString arg0) {
        for (@Pc(15) Map local15 = (Map) areas.head(); local15 != null; local15 = (Map) areas.next()) {
            if (local15.id.strEquals(arg0)) {
                return local15;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!hb", name = "b", descriptor = "(II)Z")
	public static boolean method1855(@OriginalArg(0) int arg0) {
		return arg0 >= 0 && aBooleanArray130.length > arg0 ? aBooleanArray130[arg0] : false;
	}

    @OriginalMember(owner = "runetek4.client!ui", name = "d", descriptor = "(II)V")
    public static void method4332(@OriginalArg(0) int arg0) {
        if (arg0 >= 0 && aBooleanArray130.length > arg0) {
            aBooleanArray130[arg0] = !aBooleanArray130[arg0];
        }
    }
}
