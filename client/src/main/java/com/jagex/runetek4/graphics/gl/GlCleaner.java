package com.jagex.runetek4.graphics.gl;

import com.jagex.runetek4.core.datastruct.LinkList;
import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class GlCleaner {
    @OriginalMember(owner = "client!fa", name = "b", descriptor = "Lclient!ih;")
    static final LinkList aClass69_48 = new LinkList();
    @OriginalMember(owner = "client!fa", name = "g", descriptor = "Lclient!ih;")
    static final LinkList aClass69_49 = new LinkList();
    @OriginalMember(owner = "client!fa", name = "h", descriptor = "Lclient!ih;")
    static final LinkList aClass69_50 = new LinkList();
    @OriginalMember(owner = "client!fa", name = "i", descriptor = "Lclient!ih;")
    static final LinkList aClass69_51 = new LinkList();
    @OriginalMember(owner = "client!fa", name = "j", descriptor = "[I")
    private static final int[] anIntArray151 = new int[1000];
    @OriginalMember(owner = "client!fa", name = "e", descriptor = "I")
    public static int oncard_2d = 0;
    @OriginalMember(owner = "client!fa", name = "c", descriptor = "I")
    public static int contextId = 0;
    @OriginalMember(owner = "client!fa", name = "f", descriptor = "I")
    public static int oncard_geometry = 0;
    @OriginalMember(owner = "client!fa", name = "a", descriptor = "I")
    public static int oncard_texture = 0;
    @OriginalMember(owner = "client!fa", name = "d", descriptor = "J")
    private static long aLong71 = 0L;

    @OriginalMember(owner = "client!fa", name = "c", descriptor = "()V")
    public static synchronized void process() {
        @Pc(1) GL2 local1 = GlRenderer.gl;
        @Pc(3) int local3 = 0;
        while (true) {
            @Pc(8) IntWrapper local8 = (IntWrapper) aClass69_48.removeHead();
            if (local8 == null) {
                if (local3 > 0) {
                    local1.glDeleteBuffers(local3, anIntArray151, 0);
                    local3 = 0;
                }
                while (true) {
                    local8 = (IntWrapper) aClass69_49.removeHead();
                    if (local8 == null) {
                        while (true) {
                            local8 = (IntWrapper) aClass69_50.removeHead();
                            if (local8 == null) {
                                if (local3 > 0) {
                                    local1.glDeleteTextures(local3, anIntArray151, 0);
                                }
                                while (true) {
                                    local8 = (IntWrapper) aClass69_51.removeHead();
                                    if (local8 == null) {
                                        if (oncard_geometry + oncard_2d + oncard_texture > 100663296 && MonotonicTime.currentTimeMillis() > aLong71 + 60000L) {
                                            System.gc();
                                            aLong71 = MonotonicTime.currentTimeMillis();
                                        }
                                        return;
                                    }
                                    @Pc(126) int local126 = (int) local8.nodeId;
                                    local1.glDeleteLists(local126, 1);
                                }
                            }
                            anIntArray151[local3++] = (int) local8.nodeId;
                            oncard_texture -= local8.value;
                            if (local3 == 1000) {
                                local1.glDeleteTextures(local3, anIntArray151, 0);
                                local3 = 0;
                            }
                        }
                    }
                    anIntArray151[local3++] = (int) local8.nodeId;
                    oncard_2d -= local8.value;
                    if (local3 == 1000) {
                        local1.glDeleteTextures(local3, anIntArray151, 0);
                        local3 = 0;
                    }
                }
            }
            anIntArray151[local3++] = (int) local8.nodeId;
            oncard_geometry -= local8.value;
            if (local3 == 1000) {
                local1.glDeleteBuffers(local3, anIntArray151, 0);
                local3 = 0;
            }
        }
    }

    @OriginalMember(owner = "client!fa", name = "a", descriptor = "(III)V")
    public static synchronized void deleteTexture(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            aClass69_50.push(local8);
        }
    }

    @OriginalMember(owner = "client!fa", name = "a", descriptor = "(II)V")
    public static synchronized void deleteList(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
        if (arg1 == contextId) {
            @Pc(7) IntWrapper local7 = new IntWrapper();
            local7.nodeId = arg0;
            aClass69_51.push(local7);
        }
    }

    @OriginalMember(owner = "client!fa", name = "b", descriptor = "()V")
    public static synchronized void clear() {
        contextId++;
        aClass69_48.clear();
        aClass69_49.clear();
        aClass69_50.clear();
        aClass69_51.clear();
        oncard_geometry = 0;
        oncard_2d = 0;
        oncard_texture = 0;
    }

    @OriginalMember(owner = "client!fa", name = "b", descriptor = "(III)V")
    public static synchronized void deleteBuffer(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            aClass69_48.push(local8);
        }
    }

    @OriginalMember(owner = "client!fa", name = "c", descriptor = "(III)V")
    public static synchronized void deleteTexture2d(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            aClass69_49.push(local8);
        }
    }
}
