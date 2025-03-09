package com.jagex.runetek4.config;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!ni")
public final class FloType {

	@OriginalMember(owner = "client!ni", name = "d", descriptor = "I")
	public int anInt4149;

	@OriginalMember(owner = "client!ni", name = "i", descriptor = "I")
	public int anInt4154;

	@OriginalMember(owner = "client!ni", name = "r", descriptor = "I")
	public int anInt4158;

	@OriginalMember(owner = "client!ni", name = "u", descriptor = "I")
	public int chroma;

	@OriginalMember(owner = "client!ni", name = "b", descriptor = "I")
	private int colour = 0;

	@OriginalMember(owner = "client!ni", name = "m", descriptor = "I")
	public int material = -1;

	@OriginalMember(owner = "client!ni", name = "o", descriptor = "I")
	public int hardshadow = 128;

	@OriginalMember(owner = "client!ni", name = "l", descriptor = "Z")
	public boolean occlude = true;

	@OriginalMember(owner = "client!ni", name = "a", descriptor = "(ILclient!wa;I)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(7) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!ni", name = "a", descriptor = "(BILclient!wa;I)V")
	private void decode(@OriginalArg(2) Packet packet, @OriginalArg(1) int code) {
		if (code == 1) {
			this.colour = packet.g3();
			this.computeColour(this.colour);
		} else if (code == 2) {
			this.material = packet.g2();
			if (this.material == 65535) {
				this.material = -1;
			}
		} else if (code == 3) {
			this.hardshadow = packet.g2();
		} else if (code == 4) {
			this.occlude = false;
		}
	}

	@OriginalMember(owner = "client!ni", name = "a", descriptor = "(IB)V")
	private void computeColour(@OriginalArg(0) int arg0) {
		@Pc(8) double local8 = (double) (arg0 >> 16 & 0xFF) / 256.0D;
		@Pc(21) double local21 = (double) (arg0 >> 8 & 0xFF) / 256.0D;
		@Pc(23) double local23 = local8;
		@Pc(30) double local30 = (double) (arg0 & 0xFF) / 256.0D;
		if (local21 < local8) {
			local23 = local21;
		}
		if (local30 < local23) {
			local23 = local30;
		}
		@Pc(44) double local44 = local8;
		@Pc(54) double local54 = 0.0D;
		if (local21 > local8) {
			local44 = local21;
		}
		if (local30 > local44) {
			local44 = local30;
		}
		@Pc(68) double local68 = 0.0D;
		@Pc(74) double local74 = (local44 + local23) / 2.0D;
		if (local23 != local44) {
			if (local74 < 0.5D) {
				local68 = (local44 - local23) / (local44 + local23);
			}
			if (local44 == local8) {
				local54 = (local21 - local30) / (-local23 + local44);
			} else if (local21 == local44) {
				local54 = (local30 - local8) / (local44 - local23) + 2.0D;
			} else if (local30 == local44) {
				local54 = (local8 - local21) / (-local23 + local44) + 4.0D;
			}
			if (local74 >= 0.5D) {
				local68 = (local44 - local23) / ((2.0D - local44) - local23);
			}
		}

		this.anInt4149 = (int) (local68 * 256.0D);
		this.anInt4158 = (int) (local74 * 256.0D);
		if (this.anInt4158 < 0) {
			this.anInt4158 = 0;
		} else if (this.anInt4158 > 255) {
			this.anInt4158 = 255;
		}
		local54 /= 6.0D;
		if (this.anInt4149 < 0) {
			this.anInt4149 = 0;
		} else if (this.anInt4149 > 255) {
			this.anInt4149 = 255;
		}

		if (local74 > 0.5D) {
			this.chroma = (int) (local68 * (1.0D - local74) * 512.0D);
		} else {
			this.chroma = (int) (local68 * local74 * 512.0D);
		}
		if (this.chroma < 1) {
			this.chroma = 1;
		}

		this.anInt4154 = (int) ((double) this.chroma * local54);
	}
}