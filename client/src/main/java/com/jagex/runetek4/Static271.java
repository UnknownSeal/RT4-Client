package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static271 {

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method4598(@OriginalArg(0) Packet arg0) {
		if (client.uid != null) {
			try {
				client.uid.seek(0L);
				client.uid.write(arg0.data, arg0.offset, 24);
			} catch (@Pc(16) Exception local16) {
			}
		}
		arg0.offset += 24;
	}

}
