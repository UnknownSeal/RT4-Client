package com.jagex.runetek4.game.config.quickchatphrasetype;

import com.jagex.runetek4.*;
import com.jagex.runetek4.Class6;
import com.jagex.runetek4.node.CachedNode;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cb")
public final class QuickChatPhraseType extends CachedNode {

	@OriginalMember(owner = "client!cb", name = "L", descriptor = "[I")
	private int[] commands;

	@OriginalMember(owner = "client!cb", name = "M", descriptor = "[I")
	public int[] autoResponses;

	@OriginalMember(owner = "client!cb", name = "U", descriptor = "[[I")
	private int[][] anIntArrayArray5;

	@OriginalMember(owner = "client!cb", name = "V", descriptor = "[Lclient!na;")
	private JString[] aClass100Array35;

	@OriginalMember(owner = "client!cb", name = "O", descriptor = "Z")
	public boolean aBoolean60 = true;

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int code) {
		if (code == 1) {
			this.aClass100Array35 = packet.gjstr().method3147(60);
			return;
		}

		if (code == 2) {
			int length = packet.g1();
			this.autoResponses = new int[length];
			for (int index = 0; index < length; index++) {
				this.autoResponses[index] = packet.g2();
			}
		} else if (code == 3) {
			int length = packet.g1();
			this.commands = new int[length];
			this.anIntArrayArray5 = new int[length][];
			for (int i = 0; i < length; i++) {
				@Pc(49) int local49 = packet.g2();
				this.commands[i] = local49;
				this.anIntArrayArray5[i] = new int[Static264.anIntArray412[local49]];
				for (@Pc(64) int j = 0; j < Static264.anIntArray412[local49]; j++) {
					this.anIntArrayArray5[i][j] = packet.g2();
				}
			}
		} else if (code == 4) {
			this.aBoolean60 = false;
		}
	}

	@OriginalMember(owner = "client!cb", name = "e", descriptor = "(I)V")
	public void postDecode() {
		if (this.autoResponses != null) {
			for (@Pc(7) int index = 0; index < this.autoResponses.length; index++) {
				this.autoResponses[index] |= 0x8000;
			}
		}
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Lclient!wa;[IZ)V")
	public void putDynamics(@OriginalArg(0) Packet packet, @OriginalArg(1) int[] dynamics) {
		if (this.commands == null) {
			return;
		}
		for (@Pc(14) int index = 0; this.commands.length > index && index < dynamics.length; index++) {
			@Pc(38) int local38 = Static143.anIntArray329[this.method765(index)];
			if (local38 > 0) {
				packet.pVarLong(local38, dynamics[index]);
			}
		}
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(III)I")
	public int method764(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (this.commands == null || arg1 < 0 || arg1 > this.commands.length) {
			return -1;
		} else if (this.anIntArrayArray5[arg1] == null || arg0 < 0 || arg0 > this.anIntArrayArray5[arg1].length) {
			return -1;
		} else {
			return this.anIntArrayArray5[arg1][arg0];
		}
	}

	@OriginalMember(owner = "client!cb", name = "c", descriptor = "(II)I")
	public int method765(@OriginalArg(1) int arg0) {
		return this.commands == null || arg0 < 0 || arg0 > this.commands.length ? -1 : this.commands[arg0];
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Z)I")
	public int method767() {
		return this.commands == null ? 0 : this.commands.length;
	}

	@OriginalMember(owner = "client!cb", name = "f", descriptor = "(I)Lclient!na;")
	public JString method769() {
		@Pc(15) JString local15 = Static87.method1804(80);
		if (this.aClass100Array35 == null) {
			return Class6.aClass100_891;
		}
		local15.method3113(this.aClass100Array35[0]);
		for (@Pc(31) int local31 = 1; local31 < this.aClass100Array35.length; local31++) {
			local15.method3113(Static211.aClass100_231);
			local15.method3113(this.aClass100Array35[local31]);
		}
		return local15.method3156();
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(ILclient!wa;)Lclient!na;")
	public JString method770(@OriginalArg(1) Packet arg0) {
		@Pc(17) JString local17 = Static87.method1804(80);
		if (this.commands != null) {
			for (@Pc(22) int index = 0; index < this.commands.length; index++) {
				local17.method3113(this.aClass100Array35[index]);
				local17.method3113(Static89.method1838(this.anIntArrayArray5[index], arg0.gVarLong(Static16.anIntArray51[this.commands[index]]), this.commands[index]));
			}
		}
		local17.method3113(this.aClass100Array35[this.aClass100Array35.length - 1]);
		return local17.method3156();
	}
}