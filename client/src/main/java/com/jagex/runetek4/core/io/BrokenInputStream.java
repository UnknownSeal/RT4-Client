package com.jagex.runetek4.core.io;

import java.io.InputStream;

import com.jagex.runetek4.util.system.ThreadUtils;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!qk")
public final class BrokenInputStream extends InputStream {

	@OriginalMember(owner = "client!qk", name = "read", descriptor = "()I")
	@Override
	public int read() {
		ThreadUtils.sleep(30000L);
		return -1;
	}
}
