package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class GlCleaner {
    @OriginalMember(owner = "client!fa", name = "b", descriptor = "Lclient!ih;")
    static final LinkedList buffers = new LinkedList();

    @OriginalMember(owner = "client!fa", name = "g", descriptor = "Lclient!ih;")
    static final LinkedList textures2d = new LinkedList();

    @OriginalMember(owner = "client!fa", name = "h", descriptor = "Lclient!ih;")
    static final LinkedList textures = new LinkedList();

    @OriginalMember(owner = "client!fa", name = "i", descriptor = "Lclient!ih;")
    static final LinkedList lists = new LinkedList();

    @OriginalMember(owner = "client!fa", name = "j", descriptor = "[I")
    private static final int[] ids = new int[1000];

    @OriginalMember(owner = "client!fa", name = "e", descriptor = "I")
    public static int oncard_2d = 0;

    @OriginalMember(owner = "client!fa", name = "c", descriptor = "I")
    public static int contextId = 0;

    @OriginalMember(owner = "client!fa", name = "f", descriptor = "I")
    public static int oncard_geometry = 0;

    @OriginalMember(owner = "client!fa", name = "a", descriptor = "I")
    public static int oncard_texture = 0;

    @OriginalMember(owner = "client!fa", name = "d", descriptor = "J")
    private static long lastGcTime = 0L;

    @OriginalMember(owner = "client!fa", name = "c", descriptor = "()V")
    public static synchronized void process() {
        @Pc(1) GL2 gl2 = GlRenderer.gl;
        @Pc(3) int n = 0;
        while (true) {
            @Pc(8) IntWrapper buffer = (IntWrapper) buffers.removeHead();
            if (buffer == null) {
                if (n > 0) {
                    gl2.glDeleteBuffers(n, ids, 0);
                    n = 0;
                }
                while (true) {
                    IntWrapper texture2d = (IntWrapper) textures2d.removeHead();
                    if (texture2d == null) {
                        while (true) {
                            IntWrapper texture = (IntWrapper) textures.removeHead();
                            if (texture == null) {
                                if (n > 0) {
                                    gl2.glDeleteTextures(n, ids, 0);
                                }
                                while (true) {
                                    IntWrapper list = (IntWrapper) lists.removeHead();
                                    if (list == null) {
                                        if (oncard_geometry + oncard_2d + oncard_texture > 0x6000000 && MonotonicTime.currentTimeMillis() > lastGcTime + 60000L) {
                                            System.gc();
                                            lastGcTime = MonotonicTime.currentTimeMillis();
                                        }
                                        return;
                                    }
                                    @Pc(126) int id = (int) list.nodeId;
                                    gl2.glDeleteLists(id, 1);
                                }
                            }
                            ids[n++] = (int) texture.nodeId;
                            oncard_texture -= texture.value;
                            if (n == 1000) {
                                gl2.glDeleteTextures(n, ids, 0);
                                n = 0;
                            }
                        }
                    }
                    ids[n++] = (int) texture2d.nodeId;
                    oncard_2d -= texture2d.value;
                    if (n == 1000) {
                        gl2.glDeleteTextures(n, ids, 0);
                        n = 0;
                    }
                }
            }
            ids[n++] = (int) buffer.nodeId;
            oncard_geometry -= buffer.value;
            if (n == 1000) {
                gl2.glDeleteBuffers(n, ids, 0);
                n = 0;
            }
        }
    }

    @OriginalMember(owner = "client!fa", name = "a", descriptor = "(III)V")
    public static synchronized void deleteTexture(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            textures.addTail(local8);
        }
    }

    @OriginalMember(owner = "client!fa", name = "a", descriptor = "(II)V")
    public static synchronized void deleteList(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
        if (arg1 == contextId) {
            @Pc(7) IntWrapper local7 = new IntWrapper();
            local7.nodeId = arg0;
            lists.addTail(local7);
        }
    }

    @OriginalMember(owner = "client!fa", name = "b", descriptor = "()V")
    public static synchronized void clear() {
        contextId++;
        buffers.clear();
        textures2d.clear();
        textures.clear();
        lists.clear();
        oncard_geometry = 0;
        oncard_2d = 0;
        oncard_texture = 0;
    }

    @OriginalMember(owner = "client!fa", name = "b", descriptor = "(III)V")
    public static synchronized void deleteBuffer(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            buffers.addTail(local8);
        }
    }

    @OriginalMember(owner = "client!fa", name = "c", descriptor = "(III)V")
    public static synchronized void deleteTexture2d(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        if (arg2 == contextId) {
            @Pc(8) IntWrapper local8 = new IntWrapper(arg1);
            local8.nodeId = arg0;
            textures2d.addTail(local8);
        }
    }
}
