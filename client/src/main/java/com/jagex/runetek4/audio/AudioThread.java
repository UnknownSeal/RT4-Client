package com.jagex.runetek4.audio;

import com.jagex.runetek4.core.exceptions.TracingException;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.util.system.SignLink;
import com.jagex.runetek4.util.system.ThreadUtils;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cj")
public final class AudioThread implements Runnable {

	@OriginalMember(owner = "runetek4.client!cj", name = "m", descriptor = "Lsignlink!ll;")
	public SignLink signLink;

	@OriginalMember(owner = "runetek4.client!cj", name = "p", descriptor = "[Lclient!vh;")
	public final AudioChannel[] channels = new AudioChannel[2];

	@OriginalMember(owner = "runetek4.client!cj", name = "g", descriptor = "Z")
	public volatile boolean stop = false;

	@OriginalMember(owner = "runetek4.client!cj", name = "t", descriptor = "Z")
	public volatile boolean running = false;

	@OriginalMember(owner = "runetek4.client!cj", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		this.running = true;
		try {
			while (!this.stop) {
				for (@Pc(9) int i = 0; i < 2; i++) {
					@Pc(19) AudioChannel local19 = this.channels[i];
					if (local19 != null) {
						local19.loop();
					}
				}
				ThreadUtils.sleep(10L);
				GameShell.flush(this.signLink, null);
			}
		} catch (@Pc(43) Exception exception) {
			exception.printStackTrace();
			TracingException.report(null, exception);
		} finally {
			this.running = false;
		}
	}
}
