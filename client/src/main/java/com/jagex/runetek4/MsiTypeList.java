package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.msitype.MSIType;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MsiTypeList {
    @OriginalMember(owner = "runetek4.client!rl", name = "P", descriptor = "Lclient!n;")
    public static final NodeCache sprites = new NodeCache(64);

    @OriginalMember(owner = "runetek4.client!sk", name = "hb", descriptor = "Lclient!n;")
    public static final NodeCache types = new NodeCache(64);

    @OriginalMember(owner = "runetek4.client!uf", name = "r", descriptor = "Lclient!ve;")
    public static Js5 spritesArchive;

    @OriginalMember(owner = "runetek4.client!nk", name = "E", descriptor = "I")
	public static int redDelta;

    @OriginalMember(owner = "client!gl", name = "e", descriptor = "I")
    public static int greenDelta;

    @OriginalMember(owner = "runetek4.client!vk", name = "o", descriptor = "I")
    public static int blueDelta;

    @OriginalMember(owner = "runetek4.client!hj", name = "m", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "client!da", name = "c", descriptor = "(II)Lclient!aa;")
    public static MSIType get(@OriginalArg(0) int arg0) {
        @Pc(10) MSIType local10 = (MSIType) types.get(arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(20) byte[] bytes = archive.getfile(34, arg0);
        local10 = new MSIType();
        if (bytes != null) {
            local10.decode(new Packet(bytes));
        }
        types.put(local10, arg0);
        return local10;
    }

    @OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(Lclient!ve;Lclient!ve;B)V")
    public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
        spritesArchive = arg1;
        @Pc(12) int local12 = (int) (Math.random() * 21.0D) - 10;
        archive = arg0;
        @Pc(21) int local21 = (int) (Math.random() * 21.0D) - 10;
        archive.getGroupCapacity(34);
        @Pc(33) int local33 = (int) (Math.random() * 21.0D) - 10;
        @Pc(40) int local40 = (int) (Math.random() * 41.0D) - 20;
        blueDelta = local40 + local21;
        greenDelta = local12 + local40;
        redDelta = local40 + local33;
    }

    @OriginalMember(owner = "runetek4.client!qg", name = "h", descriptor = "(I)V")
    public static void clear() {
        types.clean();
        sprites.clean();
    }

    @OriginalMember(owner = "runetek4.client!vl", name = "b", descriptor = "(I)V")
	public static void removeSoft() {
		types.removeSoft();
		sprites.removeSoft();
	}

    @OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(II)V")
    public static void clean() {
        types.clean(5);
        sprites.clean(5);
    }
}
