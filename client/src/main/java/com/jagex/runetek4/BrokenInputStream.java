package com.jagex.runetek4;

import java.io.InputStream;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!qk")
public final class BrokenInputStream extends InputStream {

	@OriginalMember(owner = "runetek4.client!qk", name = "read", descriptor = "()I")
	@Override
	public final int read() {
		Static231.sleep(30000L);
		return -1;
	}
}
