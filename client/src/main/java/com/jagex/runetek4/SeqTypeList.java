package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SeqTypeList {
    @OriginalMember(owner = "runetek4.client!lc", name = "e", descriptor = "Lclient!n;")
    public static final NodeCache types = new NodeCache(64);
    @OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "Lclient!n;")
    public static final NodeCache animFramesets = new NodeCache(100);
    @OriginalMember(owner = "runetek4.client!tk", name = "s", descriptor = "Lclient!ve;")
    public static Js5 archive;
    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "Lclient!ve;")
    public static Js5 baseArchive;
    @OriginalMember(owner = "runetek4.client!se", name = "l", descriptor = "Lclient!ve;")
    public static Js5 animsArchive;

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "a", descriptor = "(IB)Lclient!tk;")
    public static SeqType get(@OriginalArg(0) int animationId) {
        @Pc(17) SeqType sequence = (SeqType) types.get((long) animationId);
        if (sequence != null) {
            return sequence;
        }
        @Pc(32) byte[] is = archive.getfile(getGroupId(animationId), getFileId(animationId));
        sequence = new SeqType();
        sequence.id = animationId;
        if (is != null) {
            sequence.decode(new Packet(is));
        }
        sequence.postDecode();
        types.put(sequence, (long) animationId);
        return sequence;
    }

    @OriginalMember(owner = "client!fl", name = "b", descriptor = "(II)Lclient!cl;")
    public static AnimFrameset getAnimFrameset(@OriginalArg(0) int id) {
        @Pc(19) AnimFrameset framset = (AnimFrameset) animFramesets.get(id);
        if (framset != null) {
            return framset;
        }
        framset = AnimFrameset.create(animsArchive, baseArchive, id);
        if (framset != null) {
            animFramesets.put(framset, id);
        }
        return framset;
    }

    @OriginalMember(owner = "runetek4.client!s", name = "a", descriptor = "(II)I")
    public static int getGroupId(@OriginalArg(0) int arg0) {
        return arg0 >>> 7;
    }

    @OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II)I")
    public static int getFileId(@OriginalArg(1) int arg0) {
        return arg0 & 0x7F;
    }

    @OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(Lclient!ve;Lclient!ve;ILclient!ve;)V")
    public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(3) Js5 arg2) {
        archive = arg1;
        baseArchive = arg0;
        animsArchive = arg2;
    }

    @OriginalMember(owner = "client!an", name = "a", descriptor = "(Z)V")
    public static void clear() {
        types.clean();
        animFramesets.clean();
    }

    @OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "(B)V")
    public static void removeSoft() {
        types.removeSoft();
        animFramesets.removeSoft();
    }

    @OriginalMember(owner = "runetek4.client!fl", name = "a", descriptor = "(IB)V")
    public static void clean() {
        types.clean(5);
        animFramesets.clean(5);
    }
}
