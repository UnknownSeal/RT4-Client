package com.jagex.runetek4;

import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static17 {

	@OriginalMember(owner = "client!bg", name = "g", descriptor = "Lclient!i;")
	public static final PacketBit packet = new PacketBit(5000);

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(B)V")
	public static void method527() {
		Protocol.inboundBuffer.accessBits();
		@Pc(11) int local11 = Protocol.inboundBuffer.gBit(1);
		if (local11 == 0) {
			return;
		}
		@Pc(23) int local23 = Protocol.inboundBuffer.gBit(2);
		if (local23 == 0) {
			Static44.entityUpdateIds[Static116.entityUpdateCount++] = 2047;
			return;
		}
		@Pc(54) int local54;
		@Pc(64) int local64;
		if (local23 == 1) {
			local54 = Protocol.inboundBuffer.gBit(3);
			PlayerList.self.method2684(1, local54);
			local64 = Protocol.inboundBuffer.gBit(1);
			if (local64 == 1) {
				Static44.entityUpdateIds[Static116.entityUpdateCount++] = 2047;
			}
		} else if (local23 == 2) {
			if (Protocol.inboundBuffer.gBit(1) == 1) {
				local54 = Protocol.inboundBuffer.gBit(3);
				PlayerList.self.method2684(2, local54);
				local64 = Protocol.inboundBuffer.gBit(3);
				PlayerList.self.method2684(2, local64);
			} else {
				local54 = Protocol.inboundBuffer.gBit(3);
				PlayerList.self.method2684(0, local54);
			}
			local54 = Protocol.inboundBuffer.gBit(1);
			if (local54 == 1) {
				Static44.entityUpdateIds[Static116.entityUpdateCount++] = 2047;
			}
		} else if (local23 == 3) {
			local54 = Protocol.inboundBuffer.gBit(7);
			local64 = Protocol.inboundBuffer.gBit(1);
			Player.plane = Protocol.inboundBuffer.gBit(2);
			@Pc(163) int local163 = Protocol.inboundBuffer.gBit(1);
			if (local163 == 1) {
				Static44.entityUpdateIds[Static116.entityUpdateCount++] = 2047;
			}
			@Pc(181) int local181 = Protocol.inboundBuffer.gBit(7);
			PlayerList.self.teleport(local181, local64 == 1, local54);
		}
	}

}
