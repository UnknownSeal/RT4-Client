package com.jagex.runetek4.game.config.cursortype;

import com.jagex.runetek4.SoftwareSprite;
import com.jagex.runetek4.Static243;
import com.jagex.runetek4.VarpDefinition;
import com.jagex.runetek4.Static80;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ia")
public final class CursorType {

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "I")
	public int hotspoty;

	@OriginalMember(owner = "client!ia", name = "c", descriptor = "I")
	public int hotspotx;

	@OriginalMember(owner = "client!ia", name = "i", descriptor = "I")
	private int graphic;

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(Lclient!wa;IB)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(18) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(IIILclient!wa;)V")
	private void decode(@OriginalArg(3) Packet packet, @OriginalArg(2) int code) {
		if (code == 1) {
			this.graphic = packet.g2();
		} else if (code == 2) {
			this.hotspotx = packet.g1();
			this.hotspoty = packet.g1();
		}
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(B)Lclient!mm;")
	public SoftwareSprite getSprite() {
		@Pc(7) SoftwareSprite local7 = (SoftwareSprite) VarpDefinition.aClass99_5.get(this.graphic);
		if (local7 != null) {
			return local7;
		}
		local7 = Static80.method3613(Static243.aClass153_97, this.graphic);
		if (local7 != null) {
			VarpDefinition.aClass99_5.put(local7, this.graphic);
		}
		return local7;
	}
}
