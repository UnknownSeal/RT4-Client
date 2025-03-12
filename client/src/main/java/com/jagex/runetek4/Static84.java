package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.CachedNode;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.SeqType;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static84 {

	@OriginalMember(owner = "runetek4.client!gk", name = "d", descriptor = "F")
	public static float aFloat10;

	@OriginalMember(owner = "runetek4.client!gk", name = "e", descriptor = "Lclient!ve;")
	public static Js5 aClass153_35;

	@OriginalMember(owner = "runetek4.client!gk", name = "j", descriptor = "I")
	public static int anInt2257;

	@OriginalMember(owner = "runetek4.client!gk", name = "l", descriptor = "Lclient!qf;")
	public static Sprite aClass3_Sub2_Sub1_4;

	@OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
	public static final int[] anIntArray209 = new int[4096];

	@OriginalMember(owner = "runetek4.client!gk", name = "g", descriptor = "Z")
	public static boolean aBoolean127 = false;

	@OriginalMember(owner = "runetek4.client!gk", name = "h", descriptor = "I")
	public static int anInt2255 = 0;

	@OriginalMember(owner = "runetek4.client!gk", name = "i", descriptor = "I")
	public static int anInt2256 = 0;

	@OriginalMember(owner = "runetek4.client!gk", name = "a", descriptor = "(IIBLclient!e;)V")
	public static void getPlayerExtended(@OriginalArg(0) int flags, @OriginalArg(1) int arg1, @OriginalArg(3) PlayerEntity player) {
		@Pc(13) int chatFlags;
		@Pc(17) int staffModLevel;
		@Pc(24) int local24;
		if ((flags & 0x80) != 0) {

			chatFlags = Static57.in.g2le();
			staffModLevel = Static57.in.g1();
			@Pc(21) int len = Static57.in.g1();
			local24 = Static57.in.position;

			@Pc(35) boolean quickChat = (chatFlags & 0x8000) != 0;

			if (player.name != null && player.model != null) {
				@Pc(48) long encodedUsername = player.name.toBase37();
				@Pc(50) boolean ignored = false;
				if (staffModLevel <= 1) {
					if (!quickChat && (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129)) {
						ignored = true;
					} else {
						for (@Pc(69) int i = 0; i < Static35.ignoreCount; i++) {
							if (Static190.ignoreName37[i] == encodedUsername) {
								ignored = true;
								break;
							}
						}
					}
				}
				if (!ignored && PlayerEntity.overrideChat == 0) {
					Static270.chatBuffer.position = 0;
					Static57.in.gBytesRev(Static270.chatBuffer.data, len);
					Static270.chatBuffer.position = 0;

					@Pc(106) int phraseId = -1;

					@Pc(127) JString message;
					if (quickChat) {
						@Pc(112) QuickChatPhrase quickChatPhrase = QuickChatPhrase.decode(Static270.chatBuffer);
						chatFlags &= 0x7FFF;
						phraseId = quickChatPhrase.anInt439;
						message = quickChatPhrase.aQuickChatPhraseType_1.method770(Static270.chatBuffer);
					} else {
						message = Static218.method2862(Static65.method1497(Static270.chatBuffer).method3116());
					}
					player.chatMessage = message.trim();
					player.chatEffect = chatFlags & 0xFF;
					player.chatLoops = 150;
					player.chatColor = chatFlags >> 8;
					if (staffModLevel == 2) {
						Static154.add(phraseId, quickChat ? 17 : 1, message, null, Static34.method882(new JString[] { Static44.aClass100_336, player.getName() }));
					} else if (staffModLevel == 1) {
						Static154.add(phraseId, quickChat ? 17 : 1, message, null, Static34.method882(new JString[] { Static65.aClass100_435, player.getName() }));
					} else {
						Static154.add(phraseId, quickChat ? 17 : 2, message, null, player.getName());
					}
				}
			}
			Static57.in.position = local24 + len;
		}
		if ((flags & 0x1) != 0) {
			chatFlags = Static57.in.gSmart1or2();
			staffModLevel = Static57.in.g1add();
			player.method2686(staffModLevel, Static83.loopCycle, chatFlags);
			player.anInt3378 = Static83.loopCycle + 300;
			player.anInt3372 = Static57.in.g1_alt3();
		}
		if ((flags & 0x8) != 0) {
			chatFlags = Static57.in.g2();
			if (chatFlags == 65535) {
				chatFlags = -1;
			}
			staffModLevel = Static57.in.g1();
			Static186.method3415(staffModLevel, chatFlags, player);
		}
		if ((flags & 0x4) != 0) {
			chatFlags = Static57.in.g1add();
			@Pc(309) byte[] local309 = new byte[chatFlags];
			@Pc(314) Packet local314 = new Packet(local309);
			Static57.in.gdata(chatFlags, local309);
			Static115.playerAppearanceBuffer[arg1] = local314;
			player.read(local314);
		}
		if ((flags & 0x2) != 0) {
			player.targetId = Static57.in.g2sub();
			if (player.targetId == 65535) {
				player.targetId = -1;
			}
		}
		if ((flags & 0x400) != 0) {
			player.anInt3380 = Static57.in.p1neg();
			player.anInt3428 = Static57.in.g1();
			player.anInt3416 = Static57.in.g1add();
			player.anInt3392 = Static57.in.g1();
			player.anInt3395 = Static57.in.g2le() + Static83.loopCycle;
			player.anInt3386 = Static57.in.g2le() + Static83.loopCycle;
			player.anInt3431 = Static57.in.p1neg();
			player.pathLength = 1;
			player.anInt3405 = 0;
		}
		if ((flags & 0x20) != 0) {
			player.chatMessage = Static57.in.gjstr();
			if (player.chatMessage.method3149(0) == 126) {
				player.chatMessage = player.chatMessage.substring(1);
				Static103.addMessage(player.getName(), 2, player.chatMessage);
			} else if (player == Static173.localPlayer) {
				Static103.addMessage(player.getName(), 2, player.chatMessage);
			}
			player.chatEffect = 0;
			player.chatColor = 0;
			player.chatLoops = 150;
		}
		if ((flags & 0x200) != 0) {
			chatFlags = Static57.in.gSmart1or2();
			staffModLevel = Static57.in.g1_alt3();
			player.method2686(staffModLevel, Static83.loopCycle, chatFlags);
		}
		if ((flags & 0x800) != 0) {
			chatFlags = Static57.in.p1neg();
			@Pc(502) int[] local502 = new int[chatFlags];
			@Pc(505) int[] local505 = new int[chatFlags];
			@Pc(508) int[] local508 = new int[chatFlags];
			for (@Pc(510) int local510 = 0; local510 < chatFlags; local510++) {
				@Pc(521) int local521 = Static57.in.g2le();
				if (local521 == 65535) {
					local521 = -1;
				}
				local502[local510] = local521;
				local505[local510] = Static57.in.g1add();
				local508[local510] = Static57.in.g2();
			}
			Static32.method865(local505, local502, player, local508);
		}
		if ((flags & 0x100) != 0) {
			chatFlags = Static57.in.g2le();
			if (chatFlags == 65535) {
				chatFlags = -1;
			}
			staffModLevel = Static57.in.p4rme();
			@Pc(573) boolean local573 = true;
			if (chatFlags != -1 && player.spotanimFrame != -1 && Static36.method941(Static34.method877(chatFlags).anInt1754).priority < Static36.method941(Static34.method877(player.spotanimFrame).anInt1754).priority) {
				local573 = false;
			}
			if (local573) {
				player.spotanimLastCycle = (staffModLevel & 0xFFFF) + Static83.loopCycle;
				player.anInt3361 = 0;
				player.spotanimId = 0;
				player.spotanimFrame = chatFlags;
				if (player.spotanimLastCycle > Static83.loopCycle) {
					player.spotanimId = -1;
				}
				player.spotanimOffset = staffModLevel >> 16;
				player.anInt3418 = 1;
				if (player.spotanimFrame != -1 && Static83.loopCycle == player.spotanimLastCycle) {
					local24 = Static34.method877(player.spotanimFrame).anInt1754;
					if (local24 != -1) {
						@Pc(663) SeqType local663 = Static36.method941(local24);
						if (local663 != null && local663.anIntArray473 != null) {
							Static152.method2836(player.z, local663, player.x, player == Static173.localPlayer, 0);
						}
					}
				}
			}
		}
		if ((flags & 0x40) != 0) {
			player.anInt3382 = Static57.in.g2();
			player.anInt3363 = Static57.in.g2leadd();
		}
	}

	@OriginalMember(owner = "runetek4.client!gk", name = "a", descriptor = "([BI)V")
	public static void method1770(@OriginalArg(0) byte[] arg0) {
		@Pc(4) Packet local4 = new Packet(arg0);
		local4.position = arg0.length - 2;
		Static165.anInt4038 = local4.g2();
		Static26.anIntArray66 = new int[Static165.anInt4038];
		Static254.anIntArray488 = new int[Static165.anInt4038];
		Static274.anIntArray440 = new int[Static165.anInt4038];
		Static159.aBooleanArray87 = new boolean[Static165.anInt4038];
		Static64.aByteArrayArray9 = new byte[Static165.anInt4038][];
		Static269.anIntArray252 = new int[Static165.anInt4038];
		aClass6.aByteArrayArray5 = new byte[Static165.anInt4038][];
		local4.position = arg0.length - Static165.anInt4038 * 8 - 7;
		Static124.anInt3080 = local4.g2();
		Static227.anInt5091 = local4.g2();
		@Pc(66) int local66 = (local4.g1() & 0xFF) + 1;
		@Pc(68) int local68;
		for (local68 = 0; local68 < Static165.anInt4038; local68++) {
			Static274.anIntArray440[local68] = local4.g2();
		}
		for (local68 = 0; local68 < Static165.anInt4038; local68++) {
			Static269.anIntArray252[local68] = local4.g2();
		}
		for (local68 = 0; local68 < Static165.anInt4038; local68++) {
			Static254.anIntArray488[local68] = local4.g2();
		}
		for (local68 = 0; local68 < Static165.anInt4038; local68++) {
			Static26.anIntArray66[local68] = local4.g2();
		}
		local4.position = arg0.length + 3 - Static165.anInt4038 * 8 - local66 * 3 - 7;
		Static259.anIntArray513 = new int[local66];
		for (local68 = 1; local68 < local66; local68++) {
			Static259.anIntArray513[local68] = local4.g3();
			if (Static259.anIntArray513[local68] == 0) {
				Static259.anIntArray513[local68] = 1;
			}
		}
		local4.position = 0;
		for (local68 = 0; local68 < Static165.anInt4038; local68++) {
			@Pc(195) int local195 = Static254.anIntArray488[local68];
			@Pc(199) int local199 = Static26.anIntArray66[local68];
			@Pc(203) int local203 = local195 * local199;
			@Pc(206) byte[] local206 = new byte[local203];
			@Pc(208) boolean local208 = false;
			aClass6.aByteArrayArray5[local68] = local206;
			@Pc(215) byte[] local215 = new byte[local203];
			Static64.aByteArrayArray9[local68] = local215;
			@Pc(223) int local223 = local4.g1();
			@Pc(232) int local232;
			if ((local223 & 0x1) == 0) {
				for (local232 = 0; local232 < local203; local232++) {
					local206[local232] = local4.g1s();
				}
				if ((local223 & 0x2) != 0) {
					for (local232 = 0; local232 < local203; local232++) {
						@Pc(343) byte local343 = local215[local232] = local4.g1s();
						local208 |= local343 != -1;
					}
				}
			} else {
				local232 = 0;
				label88: while (true) {
					@Pc(241) int local241;
					if (local232 >= local195) {
						if ((local223 & 0x2) == 0) {
							break;
						}
						local232 = 0;
						while (true) {
							if (local232 >= local195) {
								break label88;
							}
							for (local241 = 0; local241 < local199; local241++) {
								@Pc(291) byte local291 = local215[local195 * local241 + local232] = local4.g1s();
								local208 |= local291 != -1;
							}
							local232++;
						}
					}
					for (local241 = 0; local241 < local199; local241++) {
						local206[local232 + local241 * local195] = local4.g1s();
					}
					local232++;
				}
			}
			Static159.aBooleanArray87[local68] = local208;
		}
	}

	@OriginalMember(owner = "runetek4.client!gk", name = "b", descriptor = "(B)V")
	public static void method1771() {
		Static62.anIntArray150 = Static206.method3679(0.4F);
	}

	@OriginalMember(owner = "runetek4.client!gk", name = "a", descriptor = "(Lclient!rg;Lclient!rg;B)V")
	public static void method1772(@OriginalArg(0) CachedNode arg0, @OriginalArg(1) CachedNode arg1) {
		if (arg1.secondaryNext != null) {
			arg1.clear();
		}
		arg1.secondaryNext = arg0;
		arg1.secondaryPrev = arg0.secondaryPrev;
		arg1.secondaryNext.secondaryPrev = arg1;
		arg1.secondaryPrev.secondaryNext = arg1;
	}
}
