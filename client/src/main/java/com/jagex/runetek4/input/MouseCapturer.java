package com.jagex.runetek4.input;

import com.jagex.runetek4.Mouse;
import com.jagex.runetek4.util.ThreadUtils;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!jd")
public final class MouseCapturer implements Runnable {

	@OriginalMember(owner = "runetek4.client!od", name = "f", descriptor = "Lclient!jd;")
	public static MouseCapturer instance;
	@OriginalMember(owner = "runetek4.client!tm", name = "e", descriptor = "Z")
	public static boolean enabled = false;
	@OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "I")
	public static int mouseRecorderPrevY = 0;
	@OriginalMember(owner = "runetek4.client!vh", name = "s", descriptor = "I")
	public static int mouseRecorderPrevX = 0;

	@OriginalMember(owner = "runetek4.client!jd", name = "e", descriptor = "Z")
	public boolean running = true;

	@OriginalMember(owner = "runetek4.client!jd", name = "f", descriptor = "Ljava/lang/Object;")
	public final Object lock = new Object();

	@OriginalMember(owner = "runetek4.client!jd", name = "k", descriptor = "I")
	public int samples = 0;

	@OriginalMember(owner = "runetek4.client!jd", name = "l", descriptor = "[I")
	public final int[] y = new int[500];

	@OriginalMember(owner = "runetek4.client!jd", name = "n", descriptor = "[I")
	public final int[] x = new int[500];

	@OriginalMember(owner = "runetek4.client!jd", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (this.running) {
			@Pc(12) Object local12 = this.lock;
			synchronized (this.lock) {
				if (this.samples < 500) {
					this.x[this.samples] = Mouse.lastMouseX;
					this.y[this.samples] = Mouse.lastMouseY;
					this.samples++;
				}
			}
			ThreadUtils.sleep(50L);
		}
	}
}
