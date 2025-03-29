package com.jagex.runetek4.game.config.flotype;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class FloorOverlayTypeList {
    @OriginalMember(owner = "runetek4.client!t", name = "p", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);
    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "I")
    public static int capacity;
    @OriginalMember(owner = "runetek4.client!cl", name = "J", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!qc", name = "a", descriptor = "(ZLclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        archive = arg0;
        capacity = archive.getGroupCapacity(4);
    }

    @OriginalMember(owner = "runetek4.client!um", name = "a", descriptor = "(BI)Lclient!wl;")
    public static FloorOverlayType method4395(@OriginalArg(1) int id) {
        @Pc(6) FloorOverlayType floorOverlay = (FloorOverlayType) types.get((long) id);
        if (floorOverlay != null) {
            return floorOverlay;
        }
        @Pc(30) byte[] local30 = archive.getfile(4, id);
        floorOverlay = new FloorOverlayType();
        if (local30 != null) {
            floorOverlay.decode(new Packet(local30), id);
        }
        types.put(floorOverlay, (long) id);
        return floorOverlay;
    }

    @OriginalMember(owner = "client!uh", name = "e", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
    }

    @OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(I)V")
    public static void clear() {
        types.clear();
    }

    @OriginalMember(owner = "client!aj", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clear(5);
    }
}
