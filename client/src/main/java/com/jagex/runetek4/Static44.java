package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.game.client.Inv;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static44 {

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "Z")
	public static boolean aBoolean83 = false;

	@OriginalMember(owner = "runetek4.client!dh", name = "d", descriptor = "[I")
	public static final int[] entityUpdateIds = new int[2048];

	@OriginalMember(owner = "runetek4.client!dh", name = "i", descriptor = "Lclient!na;")
	public static final JString aClass100_336 = JString.parse("<img=1>");

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Z)V")
	public static void method1146() {
		Protocol.outboundBuffer.offset = 0;
		Protocol.opcode3 = -1;
		ClientScriptRunner.aBoolean108 = false;
		Static223.packetSize = 0;
		LoginManager.mapFlagX = 0;
		MiniMenu.menuActionRow = 0;
		Protocol.opcode2 = -1;
		Minimap.state = 0;
		Static60.systemUpdateTimer = 0;
		Protocol.opcode4 = -1;
		Protocol.inboundBuffer.offset = 0;
		LoginManager.idleNetCycles = 0;
		Protocol.opcode = -1;
		@Pc(35) int local35;
		for (local35 = 0; local35 < PlayerList.players.length; local35++) {
			if (PlayerList.players[local35] != null) {
				PlayerList.players[local35].targetId = -1;
			}
		}
		for (local35 = 0; local35 < NpcList.npcs.length; local35++) {
			if (NpcList.npcs[local35] != null) {
				NpcList.npcs[local35].targetId = -1;
			}
		}
		Inv.clear();
		Camera.cameraType = 1;
		client.processGameStatus(30);
		for (local35 = 0; local35 < 100; local35++) {
			InterfaceList.aBooleanArray100[local35] = true;
		}
		ClientProt.sendWindowDetails();
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "b", descriptor = "(I)Lclient!q;")
	public static ReferenceNodeFactory method1147() {
		try {
			return (ReferenceNodeFactory) Class.forName("com.jagex.runetek4.SoftReferenceNodeFactory").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
	public static ComponentPointer method1148(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(9) ComponentPointer local9 = new ComponentPointer();
		local9.anInt5879 = arg2;
		local9.anInt5878 = arg0;
		InterfaceList.openInterfaces.put(local9, (long) arg1);
		ItemDefinition.method1753(arg0);
		@Pc(28) Component local28 = InterfaceList.getComponent(arg1);
		if (local28 != null) {
			Static43.method1143(local28);
		}
		if (ClientScriptRunner.aClass13_10 != null) {
			Static43.method1143(ClientScriptRunner.aClass13_10);
			ClientScriptRunner.aClass13_10 = null;
		}
		@Pc(45) int local45 = MiniMenu.menuActionRow;
		@Pc(53) int local53;
		for (local53 = 0; local53 < local45; local53++) {
			if (Static2.method5(Static39.aShortArray6[local53])) {
				Static200.method3628(local53);
			}
		}
		if (MiniMenu.menuActionRow == 1) {
			ClientScriptRunner.aBoolean108 = false;
			Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
		} else {
			Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			local53 = Fonts.b12Full.method2858(LocalizedText.CHOOSE_OPTION);
			for (@Pc(95) int local95 = 0; local95 < MiniMenu.menuActionRow; local95++) {
				@Pc(104) int local104 = Fonts.b12Full.method2858(Static269.method2228(local95));
				if (local104 > local53) {
					local53 = local104;
				}
			}
			Static24.anInt761 = local53 + 8;
			Static13.anInt436 = MiniMenu.menuActionRow * 15 + (InterfaceList.aBoolean298 ? 26 : 22);
		}
		if (local28 != null) {
			Static17.method531(local28, false);
		}
		Static74.method1626(arg0);
		if (InterfaceList.topLevelInterace != -1) {
			Static54.method1304(1, InterfaceList.topLevelInterace);
		}
		return local9;
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method1149(@OriginalArg(0) JString arg0) {
		@Pc(7) int local7 = Static91.method1879(arg0);
		if (local7 != -1) {
			Static80.method3616(Static203.aMapElementTypeList_1.aShortArray73[local7], Static203.aMapElementTypeList_1.aShortArray72[local7]);
		}
	}

}
