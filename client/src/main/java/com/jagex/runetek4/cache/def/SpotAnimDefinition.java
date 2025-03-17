package com.jagex.runetek4.cache.def;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.graphics.ModelUnlit;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!eg")
public final class SpotAnimDefinition {

	@OriginalMember(owner = "runetek4.client!ef", name = "b", descriptor = "Lclient!n;")
	public static final NodeCache modelCache = new NodeCache(30);
	@OriginalMember(owner = "runetek4.client!eg", name = "f", descriptor = "[S")
	private short[] aShortArray15;

	@OriginalMember(owner = "runetek4.client!eg", name = "g", descriptor = "[S")
	private short[] aShortArray16;

	@OriginalMember(owner = "runetek4.client!eg", name = "h", descriptor = "[S")
	private short[] aShortArray17;

	@OriginalMember(owner = "runetek4.client!eg", name = "m", descriptor = "I")
	public int anInt1751;

	@OriginalMember(owner = "runetek4.client!eg", name = "o", descriptor = "I")
	private int modelId;

	@OriginalMember(owner = "runetek4.client!eg", name = "s", descriptor = "[S")
	private short[] aShortArray18;

	@OriginalMember(owner = "runetek4.client!eg", name = "i", descriptor = "Z")
	public boolean aBoolean100 = false;

	@OriginalMember(owner = "runetek4.client!eg", name = "j", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "runetek4.client!eg", name = "k", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "runetek4.client!eg", name = "p", descriptor = "I")
	public int animationId = -1;

	@OriginalMember(owner = "runetek4.client!eg", name = "n", descriptor = "I")
	private int resizeY = 128;

	@OriginalMember(owner = "runetek4.client!eg", name = "b", descriptor = "I")
	private int resizeX = 128;

	@OriginalMember(owner = "runetek4.client!eg", name = "q", descriptor = "I")
	private int rotation = 0;

	@OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(Lclient!wa;B)V")
	public final void readValues(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(17) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.readValue(packet, opcode);
		}
	}

	@OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(Lclient!wa;II)V")
	private void readValue(@OriginalArg(0) Packet packet, @OriginalArg(1) int opcode) {
		if (opcode == 1) {
			this.modelId = packet.g2();
		} else if (opcode == 2) {
			this.animationId = packet.g2();
		} else if (opcode == 4) {
			this.resizeX = packet.g2();
		} else if (opcode == 5) {
			this.resizeY = packet.g2();
		} else if (opcode == 6) {
			this.rotation = packet.g2();
		} else if (opcode == 7) {
			this.ambient = packet.g1();
		} else if (opcode == 8) {
			this.contrast = packet.g1();
		} else if (opcode == 9) {
			this.aBoolean100 = true;
		} else {
			@Pc(78) int local78;
			@Pc(88) int local88;
			if (opcode == 40) {
				local78 = packet.g1();
				this.aShortArray15 = new short[local78];
				this.aShortArray18 = new short[local78];
				for (local88 = 0; local88 < local78; local88++) {
					this.aShortArray15[local88] = (short) packet.g2();
					this.aShortArray18[local88] = (short) packet.g2();
				}
			} else if (opcode == 41) {
				local78 = packet.g1();
				this.aShortArray16 = new short[local78];
				this.aShortArray17 = new short[local78];
				for (local88 = 0; local88 < local78; local88++) {
					this.aShortArray16[local88] = (short) packet.g2();
					this.aShortArray17[local88] = (short) packet.g2();
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(IBII)Lclient!ak;")
	public final Model getModel(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(13) Model model = (Model) modelCache.get((long) this.anInt1751);
		if (model == null) {
			@Pc(28) ModelUnlit modelUnlit = ModelUnlit.get(Static93.modelArchive, this.modelId);
			if (modelUnlit == null) {
				return null;
			}
			@Pc(40) int i;
			if (this.aShortArray15 != null) {
				for (i = 0; i < this.aShortArray15.length; i++) {
					modelUnlit.recolor(this.aShortArray15[i], this.aShortArray18[i]);
				}
			}
			if (this.aShortArray16 != null) {
				for (i = 0; i < this.aShortArray16.length; i++) {
					modelUnlit.retexture(this.aShortArray16[i], this.aShortArray17[i]);
				}
			}
			model = modelUnlit.applyLightning(this.ambient + 64, this.contrast + 850, -30, -50, -30);
			modelCache.put(model, (long) this.anInt1751);
		}
		@Pc(118) Model local118;
		if (this.animationId == -1 || arg1 == -1) {
			local118 = model.method4560(true, true, true);
		} else {
			local118 = SeqTypeList.getAnimationSequence(this.animationId).method4219(arg0, arg2, arg1, model);
		}
		if (this.resizeX != 128 || this.resizeY != 128) {
			local118.resize(this.resizeX, this.resizeY, this.resizeX);
		}
		if (this.rotation != 0) {
			if (this.rotation == 90) {
				local118.method4563();
			}
			if (this.rotation == 180) {
				local118.method4552();
			}
			if (this.rotation == 270) {
				local118.method4578();
			}
		}
		return local118;
	}
}
