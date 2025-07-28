package com.jagex.runetek4.ui.chat;

import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!of")
public interface QuickChatCommandDecoder {

	@OriginalMember(owner = "client!of", name = "a", descriptor = "(I[IIJ)Lclient!na;")
    JString decode(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(3) long arg2);
}
