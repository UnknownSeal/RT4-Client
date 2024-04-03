package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cj")
public final class AudioThread implements Runnable {

	@OriginalMember(owner = "runetek4.client!cj", name = "m", descriptor = "Lsignlink!ll;")
	public SignLink canvas;

	@OriginalMember(owner = "runetek4.client!cj", name = "p", descriptor = "[Lclient!vh;")
	public final AudioChannel[] aClass62Array1 = new AudioChannel[2];

	@OriginalMember(owner = "runetek4.client!cj", name = "g", descriptor = "Z")
	public volatile boolean aBoolean62 = false;

	@OriginalMember(owner = "runetek4.client!cj", name = "t", descriptor = "Z")
	public volatile boolean aBoolean64 = false;

	@OriginalMember(owner = "runetek4.client!cj", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		this.aBoolean64 = true;
		try {
			while (!this.aBoolean62) {
				for (@Pc(9) int local9 = 0; local9 < 2; local9++) {
					@Pc(19) AudioChannel local19 = this.aClass62Array1[local9];
					if (local19 != null) {
						local19.method3565();
					}
				}
				PreciseSleep.sleep(10L);
				GameShell.method2708(this.canvas, null);
			}
		} catch (@Pc(43) Exception local43) {
			Static89.report(null, local43);
		} finally {
			this.aBoolean64 = false;
		}
	}
}
