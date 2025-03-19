package com.jagex.runetek4.game.config.msitype;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!aa")
public final class MSIType {

	@OriginalMember(owner = "client!aa", name = "f", descriptor = "I")
	public int anInt11;

	@OriginalMember(owner = "client!aa", name = "i", descriptor = "I")
	public int spriteId;

	@OriginalMember(owner = "client!aa", name = "s", descriptor = "Z")
	public boolean aBoolean2 = false;

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(BLclient!wa;I)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(ILclient!wa;II)V")
	private void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 1) {
			this.spriteId = packet.g2();
		} else if (code == 2) {
			this.anInt11 = packet.g3();
		} else if (code == 3) {
			this.aBoolean2 = true;
		} else if (code == 4) {
			this.spriteId = -1;
		}
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(IB)Lclient!ek;")
	public SoftwareIndexedSprite method9(@OriginalArg(0) int arg0) {
		@Pc(17) SoftwareIndexedSprite local17 = (SoftwareIndexedSprite) Static219.aClass99_27.get((long) arg0 << 16 | this.spriteId);
		if (local17 != null) {
			return local17;
		}
		Static250.aClass153_101.isFileReady(this.spriteId);
		local17 = Static164.method3119(this.spriteId, Static250.aClass153_101);
		if (local17 != null) {
			local17.method1389(Static173.anInt4183, Static85.anInt2262, Static266.anInt5344);
			local17.innerWidth = local17.width;
			local17.innerHeight = local17.height;
			for (@Pc(59) int local59 = 0; local59 < arg0; local59++) {
				local17.method1395();
			}
			Static219.aClass99_27.put(local17, (long) arg0 << 16 | this.spriteId);
		}
		return local17;
	}
}
