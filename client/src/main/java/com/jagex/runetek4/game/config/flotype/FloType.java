package com.jagex.runetek4.game.config.flotype;

import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!wl")
public final class FloType {

	@OriginalMember(owner = "client!ca", name = "db", descriptor = "I")
	public static int anInt865 = 0;

	@OriginalMember(owner = "client!wl", name = "j", descriptor = "Z")
	public boolean blend = false;

	@OriginalMember(owner = "client!wl", name = "l", descriptor = "Z")
	public boolean hardShadow = true;

	@OriginalMember(owner = "client!wl", name = "p", descriptor = "I")
	public int material = -1;

	@OriginalMember(owner = "client!wl", name = "f", descriptor = "I")
	public int materialScale = 128;

	@OriginalMember(owner = "client!wl", name = "w", descriptor = "I")
	public int textureBrightness = 8;

	@OriginalMember(owner = "client!wl", name = "x", descriptor = "I")
	public int waterOpaity = 16;

	@OriginalMember(owner = "client!wl", name = "t", descriptor = "I")
	public int secondaryColor = -1;

	@OriginalMember(owner = "client!wl", name = "y", descriptor = "Z")
	public boolean occlude = true;

	@OriginalMember(owner = "client!wl", name = "z", descriptor = "I")
	public int baseColor = 0;

	@OriginalMember(owner = "client!wl", name = "m", descriptor = "I")
	public int waterColor = 1190717;

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(ILclient!wa;I)V")
	public void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int id) {
		while (true) {
			@Pc(5) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(opcode, packet, id);
		}
	}

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IILclient!wa;I)V")
	private void decode(@OriginalArg(1) int opcode, @OriginalArg(2) Packet packet, @OriginalArg(3) int arg2) {
		if (opcode == 1) {
			this.baseColor = convertColor(packet.g3());
		} else if (opcode == 2) {
			this.material = packet.g1();
		} else if (opcode == 3) {
			this.material = packet.g2();
			if (this.material == 65535) {
				this.material = -1;
			}
		} else if (opcode == 5) {
			this.occlude = false;
		} else if (opcode == 7) {
			this.secondaryColor = convertColor(packet.g3());
		} else if (opcode == 8) {
			anInt865 = arg2;
		} else if (opcode == 9) {
			this.materialScale = packet.g2();
		} else if (opcode == 10) {
			this.hardShadow = false;
		} else if (opcode == 11) {
			this.textureBrightness = packet.g1();
		} else if (opcode == 12) {
			this.blend = true;
		} else if (opcode == 13) {
			this.waterColor = packet.g3();
		} else if (opcode == 14) {
			this.waterOpaity = packet.g1();
		}
	}

	@OriginalMember(owner = "runetek4.client!be", name = "a", descriptor = "(II)I")
	public static int convertColor(@OriginalArg(1) int colour) {
		return colour == 16711935 ? -1 : ColorUtils.rgbToHsl(colour);
	}
}