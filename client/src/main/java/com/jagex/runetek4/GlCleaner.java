package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class GlCleaner {
    @OriginalMember(owner = "runetek4.client!fa", name = "b", descriptor = "Lclient!ih;")
    static final LinkedList aClass69_48 = new LinkedList();
    @OriginalMember(owner = "runetek4.client!fa", name = "g", descriptor = "Lclient!ih;")
    static final LinkedList aClass69_49 = new LinkedList();
    @OriginalMember(owner = "runetek4.client!fa", name = "h", descriptor = "Lclient!ih;")
    static final LinkedList aClass69_50 = new LinkedList();
    @OriginalMember(owner = "runetek4.client!fa", name = "i", descriptor = "Lclient!ih;")
    static final LinkedList aClass69_51 = new LinkedList();
    @OriginalMember(owner = "runetek4.client!fa", name = "j", descriptor = "[I")
    private static final int[] anIntArray151 = new int[1000];
    @OriginalMember(owner = "runetek4.client!fa", name = "d", descriptor = "J")
    private static long aLong71 = 0L;

    @OriginalMember(owner = "runetek4.client!fa", name = "c", descriptor = "()V")
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
                                        if (Static63.oncard_geometry + Static63.oncard_2d + Static63.oncard_texture > 100663296 && MonotonicTime.currentTimeMillis() > aLong71 + 60000L) {
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
                            Static63.oncard_texture -= local8.value;
                            if (local3 == 1000) {
                                local1.glDeleteTextures(local3, anIntArray151, 0);
                                local3 = 0;
                            }
                        }
                    }
                    anIntArray151[local3++] = (int) local8.nodeId;
                    Static63.oncard_2d -= local8.value;
                    if (local3 == 1000) {
                        local1.glDeleteTextures(local3, anIntArray151, 0);
                        local3 = 0;
                    }
                }
            }
            anIntArray151[local3++] = (int) local8.nodeId;
            Static63.oncard_geometry -= local8.value;
            if (local3 == 1000) {
                local1.glDeleteBuffers(local3, anIntArray151, 0);
                local3 = 0;
            }
        }
    }
}
