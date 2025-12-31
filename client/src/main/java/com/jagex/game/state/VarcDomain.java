package com.jagex.game.state;

import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalMember;

public class VarcDomain {

    @OriginalMember(owner = "runetek4.client!me", name = "P", descriptor = "[I")
    public static final int[] varcs = new int[2000];

    @OriginalMember(owner = "runetek4.client!km", name = "Bc", descriptor = "[I")
    public static final int[] updatedVarcs = new int[32];

    @OriginalMember(owner = "runetek4.client!t", name = "E", descriptor = "[I")
    public static final int[] updatedVarcstrs = new int[32];
    @OriginalMember(owner = "runetek4.client!sf", name = "h", descriptor = "[Lclient!na;")
    public static final JString[] varcstrs = new JString[1000];

    @OriginalMember(owner = "runetek4.client!ac", name = "o", descriptor = "I")
    public static int updatedVarcsWriterIndex = 0;

    @OriginalMember(owner = "client!fl", name = "B", descriptor = "I")
    public static int updatedVarcstrsWriterIndex = 0;
}
