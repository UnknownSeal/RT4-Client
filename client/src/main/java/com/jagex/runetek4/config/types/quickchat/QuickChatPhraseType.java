package com.jagex.runetek4.config.types.quickchat;

import com.jagex.runetek4.*;
import com.jagex.runetek4.node.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cb")
public final class QuickChatPhraseType extends SecondaryNode {

    @OriginalMember(owner = "client!bf", name = "C", descriptor = "[I")
    public static final int[] anIntArray51 = new int[] { 2, 2, 4, 2, 1, 8, 4, 1, 4, 4, 2, 1, 1, 1, 4, 1 };

	@OriginalMember(owner = "runetek4.client!ld", name = "a", descriptor = "[I")
	public static final int[] DYNAMIC_COMMAND_ENCODE_BYTES = new int[] { 2, 2, 4, 0, 1, 8, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 };

	@OriginalMember(owner = "runetek4.client!rc", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_231 = JString.parse(")3)3)3");

	@OriginalMember(owner = "runetek4.client!qg", name = "U", descriptor = "Lclient!na;")
	public static final JString aClass100_891 = JString.parse("");

	@OriginalMember(owner = "runetek4.client!vh", name = "p", descriptor = "[I")
	public static final int[] anIntArray412 = new int[] { 1, 0, 0, 0, 1, 0, 2, 1, 1, 1, 0, 2, 0, 0, 1, 0 };

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

	@OriginalMember(owner = "runetek4.client!vh", name = "a", descriptor = "(ILclient!wa;)Lclient!bd;")
	public static QuickChatPhrase method3568(@OriginalArg(1) Packet arg0) {
		@Pc(3) QuickChatPhrase local3 = new QuickChatPhrase();
		local3.id = arg0.g2();
		local3.type = QuickChatPhraseTypeList.get(local3.id);
		return local3;
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int opcode) {
		if (opcode == 1) {
			this.aClass100Array35 = packet.gjstr().split(60);
			return;
		}

		if (opcode == 2) {
			int length = packet.g1();
			this.autoResponses = new int[length];
			for (int index = 0; index < length; index++) {
				this.autoResponses[index] = packet.g2();
			}
		} else if (opcode == 3) {
			int length = packet.g1();
			this.commands = new int[length];
			this.anIntArrayArray5 = new int[length][];
			for (int i = 0; i < length; i++) {
				@Pc(49) int local49 = packet.g2();
				this.commands[i] = local49;
				this.anIntArrayArray5[i] = new int[anIntArray412[local49]];
				for (@Pc(64) int j = 0; j < anIntArray412[local49]; j++) {
					this.anIntArrayArray5[i][j] = packet.g2();
				}
			}
		} else if (opcode == 4) {
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
	public void encodeMessage(@OriginalArg(0) Packet packet, @OriginalArg(1) int[] dynamics) {
		if (this.commands == null) {
			return;
		}
		for (@Pc(14) int index = 0; this.commands.length > index && index < dynamics.length; index++) {
			@Pc(38) int local38 = DYNAMIC_COMMAND_ENCODE_BYTES[this.getDynamicCommand(index)];
			if (local38 > 0) {
				packet.pVarLong(local38, dynamics[index]);
			}
		}
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(III)I")
	public int getDynamicCommandParam(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (this.commands == null || arg1 < 0 || arg1 > this.commands.length) {
			return -1;
		} else if (this.anIntArrayArray5[arg1] == null || arg0 < 0 || arg0 > this.anIntArrayArray5[arg1].length) {
			return -1;
		} else {
			return this.anIntArrayArray5[arg1][arg0];
		}
	}

	@OriginalMember(owner = "client!cb", name = "c", descriptor = "(II)I")
	public int getDynamicCommand(@OriginalArg(1) int arg0) {
		return this.commands == null || arg0 < 0 || arg0 > this.commands.length ? -1 : this.commands[arg0];
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(Z)I")
	public int getDynamicCommandCount() {
		return this.commands == null ? 0 : this.commands.length;
	}

	@OriginalMember(owner = "client!cb", name = "f", descriptor = "(I)Lclient!na;")
	public JString getText() {
		@Pc(15) JString local15 = JString.allocate(80);
		if (this.aClass100Array35 == null) {
			return aClass100_891;
		}
		local15.method3113(this.aClass100Array35[0]);
		for (@Pc(31) int local31 = 1; local31 < this.aClass100Array35.length; local31++) {
			local15.method3113(aClass100_231);
			local15.method3113(this.aClass100Array35[local31]);
		}
		return local15.method3156();
	}

	@OriginalMember(owner = "client!cb", name = "a", descriptor = "(ILclient!wa;)Lclient!na;")
	public JString decodeMessage(@OriginalArg(1) Packet arg0) {
		@Pc(17) JString local17 = JString.allocate(80);
		if (this.commands != null) {
			for (@Pc(22) int index = 0; index < this.commands.length; index++) {
				local17.method3113(this.aClass100Array35[index]);
				local17.method3113(QuickChatPhraseTypeList.method1838(this.anIntArrayArray5[index], arg0.gVarLong(anIntArray51[this.commands[index]]), this.commands[index]));
			}
		}
		local17.method3113(this.aClass100Array35[this.aClass100Array35.length - 1]);
		return local17.method3156();
	}
}