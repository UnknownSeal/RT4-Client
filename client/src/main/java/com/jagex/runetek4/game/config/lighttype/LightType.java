package com.jagex.runetek4.game.config.lighttype;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!ic")
public final class LightType {

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "I")
	public int anInt2867 = 2048;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "I")
	public int anInt2865 = 0;

	@OriginalMember(owner = "client!ic", name = "o", descriptor = "I")
	public int anInt2872 = 0;

	@OriginalMember(owner = "client!ic", name = "p", descriptor = "I")
	public int anInt2873 = 2048;

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
			this.anInt2865 = packet.g1();
		} else if (code == 2) {
			this.anInt2873 = packet.g2();
		} else if (code == 3) {
			this.anInt2867 = packet.g2();
		} else if (code == 4) {
			this.anInt2872 = packet.g2s();
		}
	}
}