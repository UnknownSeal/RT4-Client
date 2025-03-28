package com.jagex.runetek4;

import com.jagex.runetek4.node.CachedNode;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!bc")
public final class QuickChatCatType extends CachedNode {

	@OriginalMember(owner = "runetek4.client!bc", name = "O", descriptor = "[I")
	public int[] phraseShortcuts;

	@OriginalMember(owner = "runetek4.client!bc", name = "P", descriptor = "[I")
	public int[] subcategories;

	@OriginalMember(owner = "runetek4.client!bc", name = "T", descriptor = "[I")
	public int[] subcategoryShortcuts;

	@OriginalMember(owner = "runetek4.client!bc", name = "Y", descriptor = "Lclient!na;")
	public JString description;

	@OriginalMember(owner = "runetek4.client!bc", name = "ab", descriptor = "[I")
	public int[] phrases;

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(ZB)I")
	public static int method3933(@OriginalArg(1) byte arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!bc", name = "d", descriptor = "(I)V")
	public final void method465() {
		@Pc(8) int local8;
		if (this.phrases != null) {
			for (local8 = 0; local8 < this.phrases.length; local8++) {
				this.phrases[local8] |= 0x8000;
			}
		}
		if (this.subcategories != null) {
			for (local8 = 0; local8 < this.subcategories.length; local8++) {
				this.subcategories[local8] |= 0x8000;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!bc", name = "c", descriptor = "(II)I")
	public final int method466(@OriginalArg(0) int arg0) {
		if (this.phrases == null) {
			return -1;
		}
		for (@Pc(13) int local13 = 0; local13 < this.phrases.length; local13++) {
			if (arg0 == this.phraseShortcuts[local13]) {
				return this.phrases[local13];
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!bc", name = "a", descriptor = "(Lclient!wa;II)V")
	private void method467(@OriginalArg(0) Packet arg0, @OriginalArg(2) int opcode) {
		if (opcode == 1) {
			this.description = arg0.gjstr();
			return;
		}
		@Pc(28) int local28;
		@Pc(38) int local38;
		if (opcode == 2) {
			local28 = arg0.g1();
			this.subcategories = new int[local28];
			this.subcategoryShortcuts = new int[local28];
			for (local38 = 0; local38 < local28; local38++) {
				this.subcategories[local38] = arg0.g2();
				this.subcategoryShortcuts[local38] = method3933(arg0.g1s());
			}
		} else if (opcode == 3) {
			local28 = arg0.g1();
			this.phrases = new int[local28];
			this.phraseShortcuts = new int[local28];
			for (local38 = 0; local38 < local28; local38++) {
				this.phrases[local38] = arg0.g2();
				this.phraseShortcuts[local38] = method3933(arg0.g1s());
			}
		} else if (opcode == 4) {
		}
	}

	@OriginalMember(owner = "runetek4.client!bc", name = "a", descriptor = "(BI)I")
	public final int method469(@OriginalArg(1) int arg0) {
		if (this.subcategories == null) {
			return -1;
		}
		for (@Pc(21) int local21 = 0; local21 < this.subcategories.length; local21++) {
			if (this.subcategoryShortcuts[local21] == arg0) {
				return this.subcategories[local21];
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!bc", name = "a", descriptor = "(Lclient!wa;B)V")
	public final void method470(@OriginalArg(0) Packet arg0) {
		while (true) {
			@Pc(12) int local12 = arg0.g1();
			if (local12 == 0) {
				return;
			}
			this.method467(arg0, local12);
		}
	}
}
