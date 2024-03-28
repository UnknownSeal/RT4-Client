package com.jagex.runetek4.game.config.flotype;

import com.jagex.runetek4.Static105;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.Static26;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!wl")
public final class FloorOverlayType {

	@OriginalMember(owner = "client!wl", name = "j", descriptor = "Z")
	public boolean blend = false;

	@OriginalMember(owner = "client!wl", name = "l", descriptor = "Z")
	public boolean hardshadow = true;

	@OriginalMember(owner = "client!wl", name = "p", descriptor = "I")
	public int material = -1;

	@OriginalMember(owner = "client!wl", name = "f", descriptor = "I")
	public int materialscale = 128;

	@OriginalMember(owner = "client!wl", name = "w", descriptor = "I")
	public int priority = 8;

	@OriginalMember(owner = "client!wl", name = "x", descriptor = "I")
	public int waterfogscale = 16;

	@OriginalMember(owner = "client!wl", name = "t", descriptor = "I")
	public int averagecolour = -1;

	@OriginalMember(owner = "client!wl", name = "y", descriptor = "Z")
	public boolean occlude = true;

	@OriginalMember(owner = "client!wl", name = "z", descriptor = "I")
	public int rgb = 0;

	@OriginalMember(owner = "client!wl", name = "m", descriptor = "I")
	public int waterfogcolour = 1190717;

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(ILclient!wa;I)V")
	public void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int arg1) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code, arg1);
		}
	}

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IILclient!wa;I)V")
	private void decode(@OriginalArg(2) Packet packet, @OriginalArg(1) int code, @OriginalArg(3) int arg2) {
		if (code == 1) {
			this.rgb = convertColour(packet.g3());
		} else if (code == 2) {
			this.material = packet.g1();
		} else if (code == 3) {
			this.material = packet.g2();
			if (this.material == 65535) {
				this.material = -1;
			}
		} else if (code == 5) {
			this.occlude = false;
		} else if (code == 7) {
			this.averagecolour = convertColour(packet.g3());
		} else if (code == 8) {
			Static26.anInt865 = arg2;
		} else if (code == 9) {
			this.materialscale = packet.g2();
		} else if (code == 10) {
			this.hardshadow = false;
		} else if (code == 11) {
			this.priority = packet.g1();
		} else if (code == 12) {
			this.blend = true;
		} else if (code == 13) {
			this.waterfogcolour = packet.g3();
		} else if (code == 14) {
			this.waterfogscale = packet.g1();
		}
	}

	@OriginalMember(owner = "runetek4.client!be", name = "a", descriptor = "(II)I")
	public static int convertColour(@OriginalArg(1) int colour) {
		return colour == 16711935 ? -1 : Static105.hslToRgb(colour);
	}
}