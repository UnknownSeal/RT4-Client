package com.jagex.runetek4;

import com.jagex.runetek4.audio.SynthSound;
import com.jagex.runetek4.cache.media.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SoundPlayer {
    @OriginalMember(owner = "runetek4.client!qe", name = "t", descriptor = "[I")
    public static final int[] ids = new int[50];
    @OriginalMember(owner = "runetek4.client!wh", name = "g", descriptor = "[I")
    public static final int[] loops = new int[50];
    @OriginalMember(owner = "runetek4.client!na", name = "mb", descriptor = "[I")
    public static final int[] delays = new int[50];
    @OriginalMember(owner = "runetek4.client!nk", name = "n", descriptor = "[Lclient!sl;")
    public static final SynthSound[] sounds = new SynthSound[50];
    @OriginalMember(owner = "client!ca", name = "fb", descriptor = "[I")
    public static final int[] positions = new int[50];
    @OriginalMember(owner = "runetek4.client!pe", name = "j", descriptor = "I")
    public static int size = 0;

    @OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "(ILclient!tk;IIZI)V")
    public static void playSeqSound(@OriginalArg(0) int arg0, @OriginalArg(1) SeqType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3, @OriginalArg(5) int arg4) {
        if (size >= 50 || (arg1.sound == null || arg4 >= arg1.sound.length || arg1.sound[arg4] == null)) {
            return;
        }
        @Pc(36) int local36 = arg1.sound[arg4][0];
        @Pc(40) int local40 = local36 >> 8;
        @Pc(57) int local57;
        if (arg1.sound[arg4].length > 1) {
            local57 = (int) ((double) arg1.sound[arg4].length * Math.random());
            if (local57 > 0) {
                local40 = arg1.sound[arg4][local57];
            }
        }
        @Pc(73) int local73 = local36 >> 5 & 0x7;
        @Pc(77) int local77 = local36 & 0x1F;
        if (local77 == 0) {
            if (arg3) {
                Static26.method744(local73, local40, 0);
            }
        } else if (Preferences.ambientSoundsVolume != 0) {
            ids[size] = local40;
            loops[size] = local73;
            @Pc(111) int local111 = (arg0 - 64) / 128;
            local57 = (arg2 - 64) / 128;
            delays[size] = 0;
            sounds[size] = null;
            positions[size] = local77 + (local57 << 16) + (local111 << 8);
            size++;
        }
    }
}
