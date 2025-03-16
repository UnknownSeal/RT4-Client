package com.jagex.runetek4.game.config.bastype;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ck")
public final class BASType {

	@OriginalMember(owner = "client!ck", name = "g", descriptor = "[[I")
	public int[][] anIntArrayArray7;

	@OriginalMember(owner = "client!ck", name = "p", descriptor = "I")
	public int anInt1036 = -1;

	@OriginalMember(owner = "client!ck", name = "q", descriptor = "I")
	public int idleAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "w", descriptor = "I")
	public int runCCWTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "u", descriptor = "I")
	public int anInt1041 = 0;

	@OriginalMember(owner = "client!ck", name = "v", descriptor = "I")
	public int slowWalkFullTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "e", descriptor = "I")
	public int anInt1031 = 0;

	@OriginalMember(owner = "client!ck", name = "G", descriptor = "I")
	public int anInt1050 = 0;

	@OriginalMember(owner = "client!ck", name = "E", descriptor = "I")
	public int slowWalkCWTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "M", descriptor = "I")
	public int runFullTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "N", descriptor = "I")
	public int anInt1055 = 0;

	@OriginalMember(owner = "client!ck", name = "m", descriptor = "I")
	public int anInt1035 = -1;

	@OriginalMember(owner = "client!ck", name = "P", descriptor = "I")
	public int anInt1056 = -1;

	@OriginalMember(owner = "client!ck", name = "h", descriptor = "I")
	public int anInt1032 = -1;

	@OriginalMember(owner = "client!ck", name = "H", descriptor = "I")
	public int anInt1051 = -1;

	@OriginalMember(owner = "client!ck", name = "W", descriptor = "I")
	public int anInt1059 = 0;

	@OriginalMember(owner = "client!ck", name = "z", descriptor = "I")
	public int runCWTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "r", descriptor = "I")
	public int anInt1038 = 0;

	@OriginalMember(owner = "client!ck", name = "fb", descriptor = "I")
	public int anInt1065 = 0;

	@OriginalMember(owner = "client!ck", name = "ab", descriptor = "I")
	public int slowWalkAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "t", descriptor = "I")
	public int anInt1040 = 0;

	@OriginalMember(owner = "client!ck", name = "U", descriptor = "I")
	public int runAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "ib", descriptor = "I")
	public int slowWalkCCWTurnAnimationId = -1;

	@OriginalMember(owner = "client!ck", name = "bb", descriptor = "I")
	public int anInt1063 = 0;

	@OriginalMember(owner = "client!ck", name = "R", descriptor = "I")
	public int anInt1057 = -1;

	@OriginalMember(owner = "client!ck", name = "jb", descriptor = "I")
	public int anInt1067 = -1;

	@OriginalMember(owner = "client!ck", name = "cb", descriptor = "I")
	public int anInt1064 = 0;

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(16) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(IBLclient!wa;)V")
	private void decode(@OriginalArg(2) Packet packet, @OriginalArg(0) int code) {
		if (code == 1) {
			this.idleAnimationId = packet.g2();
			this.anInt1051 = packet.g2();
			if (this.anInt1051 == 65535) {
				this.anInt1051 = -1;
			}
			if (this.idleAnimationId == 65535) {
				this.idleAnimationId = -1;
			}
		} else if (code == 2) {
			this.slowWalkAnimationId = packet.g2();
		} else if (code == 3) {
			this.slowWalkFullTurnAnimationId = packet.g2();
		} else if (code == 4) {
			this.slowWalkCCWTurnAnimationId = packet.g2();
		} else if (code == 5) {
			this.slowWalkCWTurnAnimationId = packet.g2();
		} else if (code == 6) {
			this.runAnimationId = packet.g2();
		} else if (code == 7) {
			this.runFullTurnAnimationId = packet.g2();
		} else if (code == 8) {
			this.runCCWTurnAnimationId = packet.g2();
		} else if (code == 9) {
			this.runCWTurnAnimationId = packet.g2();
		} else if (code == 26) {
			this.anInt1059 = (short) (packet.g1() * 4);
			this.anInt1050 = (short) (packet.g1() * 4);
		} else if (code == 27) {
			if (this.anIntArrayArray7 == null) {
				this.anIntArrayArray7 = new int[12][];
			}
			@Pc(306) int local306 = packet.g1();
			this.anIntArrayArray7[local306] = new int[6];
			for (@Pc(314) int local314 = 0; local314 < 6; local314++) {
				this.anIntArrayArray7[local306][local314] = packet.g2s();
			}
		} else if (code == 29) {
			this.anInt1038 = packet.g1();
		} else if (code == 30) {
			this.anInt1031 = packet.g2();
		} else if (code == 31) {
			this.anInt1055 = packet.g1();
		} else if (code == 32) {
			this.anInt1040 = packet.g2();
		} else if (code == 33) {
			this.anInt1064 = packet.g2s();
		} else if (code == 34) {
			this.anInt1065 = packet.g1();
		} else if (code == 35) {
			this.anInt1063 = packet.g2();
		} else if (code == 36) {
			this.anInt1041 = packet.g2s();
		} else if (code == 37) {
			this.anInt1032 = packet.g1();
		} else if (code == 38) {
			this.anInt1036 = packet.g2();
		} else if (code == 39) {
			this.anInt1067 = packet.g2();
		} else if (code == 40) {
			this.anInt1056 = packet.g2();
		} else if (code == 41) {
			this.anInt1057 = packet.g2();
		} else if (code == 42) {
			this.anInt1035 = packet.g2();
		} else if (code == 43) {
			packet.g2();
		} else if (code == 44) {
			packet.g2();
		} else if (code == 45) {
			packet.g2();
		}
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(I)V")
	public void postDecode() {
	}
}
