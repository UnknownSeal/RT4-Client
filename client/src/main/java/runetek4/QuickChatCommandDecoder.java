package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!of")
public interface QuickChatCommandDecoder {

	@OriginalMember(owner = "runetek4.client!of", name = "a", descriptor = "(I[IIJ)Lclient!na;")
    JagString method30(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(3) long arg2);
}
