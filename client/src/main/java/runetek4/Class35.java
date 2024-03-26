package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.io.Packet;

@OriginalClass("runetek4.client!eh")
public final class Class35 {

	@OriginalMember(owner = "runetek4.client!eh", name = "e", descriptor = "I")
	public int anInt1765 = 0;

	@OriginalMember(owner = "runetek4.client!eh", name = "a", descriptor = "(ILclient!wa;)V")
	public final void method1323(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(5) int local5 = arg0.g1();
			if (local5 == 0) {
				return;
			}
			this.method1325(arg0, local5);
		}
	}

	@OriginalMember(owner = "runetek4.client!eh", name = "a", descriptor = "(Lclient!wa;BI)V")
	private void method1325(@OriginalArg(0) Packet arg0, @OriginalArg(2) int arg1) {
		if (arg1 == 5) {
			this.anInt1765 = arg0.g2();
		}
	}
}
