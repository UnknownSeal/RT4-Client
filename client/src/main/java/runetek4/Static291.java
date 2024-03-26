package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.io.Packet;

public final class Static291 {

	@OriginalMember(owner = "runetek4.client!rf", name = "a", descriptor = "(Lclient!ve;II)Lclient!rf;")
	public static Song method3742(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(5) byte[] local5 = arg0.method4495(arg1, arg2);
		return local5 == null ? null : new Song(new Packet(local5));
	}
}
