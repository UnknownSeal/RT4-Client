package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SeqTypeList {
    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "a", descriptor = "(IB)Lclient!tk;")
    public static SeqType getAnimationSequence(@OriginalArg(0) int animationId) {
        @Pc(17) SeqType seqType = (SeqType) Static142.animationSequenceCache.get((long) animationId);
        if (seqType != null) {
            return seqType;
        }
        @Pc(32) byte[] is = Static243.aClass153_98.getfile(BZip2State.method3389(animationId), Static118.method2356(animationId));
        seqType = new SeqType();
        seqType.anInt5361 = animationId;
        if (is != null) {
            seqType.decode(new Packet(is));
        }
        seqType.postDecode();
        Static142.animationSequenceCache.put(seqType, (long) animationId);
        return seqType;
    }

    @OriginalMember(owner = "client!fl", name = "b", descriptor = "(II)Lclient!cl;")
    public static AnimFrameset getAnimFrameset(@OriginalArg(0) int arg0) {
        @Pc(19) AnimFrameset local19 = (AnimFrameset) Static267.skeletonCache.get(arg0);
        if (local19 != null) {
            return local19;
        }
        local19 = AnimFrameset.create(Static225.aClass153_92, Static5.aClass153_1, arg0);
        if (local19 != null) {
            Static267.skeletonCache.put(local19, arg0);
        }
        return local19;
    }
}
