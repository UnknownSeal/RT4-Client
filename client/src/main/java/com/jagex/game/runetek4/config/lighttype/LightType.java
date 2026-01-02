package com.jagex.game.runetek4.config.lighttype;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.core.io.Packet;

@OriginalClass("client!ic")
public final class LightType {

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "I")
	public int alphaMin = 2048;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "I")
	public int flickerType = 0;

	@OriginalMember(owner = "client!ic", name = "o", descriptor = "I")
	public int alphaMax = 0;

	@OriginalMember(owner = "client!ic", name = "p", descriptor = "I")
	public int lightType = 2048;

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "(ILclient!wa;I)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(code, packet);
		}
	}

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "(ILclient!wa;IZ)V")
	private void decode(@OriginalArg(0) int code, @OriginalArg(1) Packet packet) {
		if (code == 1) {
			this.flickerType = packet.g1();
		} else if (code == 2) {
			this.lightType = packet.g2();
		} else if (code == 3) {
			this.alphaMin = packet.g2();
		} else if (code == 4) {
			this.alphaMax = packet.g2s();
		}
	}
}