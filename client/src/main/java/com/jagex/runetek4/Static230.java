package com.jagex.runetek4;

import java.io.UnsupportedEncodingException;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.quickchatphrasetype.QuickChatPhraseType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static230 {

	@OriginalMember(owner = "runetek4.client!sj", name = "r", descriptor = "Lclient!ve;")
	public static Js5 modelArchive;

	@OriginalMember(owner = "runetek4.client!sj", name = "D", descriptor = "I")
	public static int anInt5158;

	@OriginalMember(owner = "runetek4.client!sj", name = "p", descriptor = "I")
	public static int anInt5150 = 1;

	@OriginalMember(owner = "runetek4.client!sj", name = "t", descriptor = "I")
	public static int anInt5152 = 0;

	@OriginalMember(owner = "runetek4.client!sj", name = "u", descriptor = "Z")
	public static boolean aBoolean250 = false;

	@OriginalMember(owner = "runetek4.client!sj", name = "w", descriptor = "Lclient!na;")
	public static final JString aClass100_978 = Static28.parse("<)4col>");

	@OriginalMember(owner = "runetek4.client!sj", name = "F", descriptor = "Lclient!rc;")
	public static GameShell anApplet_Sub1_1 = null;

	@OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(I)V")
	public static void method3947() {
		Static250.aClass99_33.method3104();
		Static139.aClass99_21.method3104();
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(Z)V")
	public static void method3948() {
		try {
			if (Static14.anInt441 == 1) {
				@Pc(16) int local16 = Static172.aClass3_Sub3_Sub4_2.method4440();
				if (local16 > 0 && Static172.aClass3_Sub3_Sub4_2.method4414()) {
					local16 -= Static57.anInt1757;
					if (local16 < 0) {
						local16 = 0;
					}
					Static172.aClass3_Sub3_Sub4_2.method4447(local16);
					return;
				}
				Static172.aClass3_Sub3_Sub4_2.method4446();
				Static172.aClass3_Sub3_Sub4_2.method4426();
				Static144.aClass3_Sub29_1 = null;
				Static27.aClass89_1 = null;
				if (Static172.aClass153_70 == null) {
					Static14.anInt441 = 0;
				} else {
					Static14.anInt441 = 2;
				}
			}
		} catch (@Pc(62) Exception local62) {
			local62.printStackTrace();
			Static172.aClass3_Sub3_Sub4_2.method4446();
			Static172.aClass153_70 = null;
			Static144.aClass3_Sub29_1 = null;
			Static14.anInt441 = 0;
			Static27.aClass89_1 = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(BII)I")
	public static int method3949(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (arg0 == -2) {
			return 12345678;
		} else if (arg0 == -1) {
			if (arg1 < 2) {
				arg1 = 2;
			} else if (arg1 > 126) {
				arg1 = 126;
			}
			return arg1;
		} else {
			arg1 = (arg0 & 0x7F) * arg1 >> 7;
			if (arg1 < 2) {
				arg1 = 2;
			} else if (arg1 > 126) {
				arg1 = 126;
			}
			return (arg0 & 0xFF80) + arg1;
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(IIBIII)V")
	public static void method3950(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 >= Static172.anInt4164 && arg3 <= Static224.anInt5063 && Static267.anInt5773 <= arg4 && Static106.anInt2869 >= arg2) {
			Static176.method3308(arg2, arg3, arg4, arg0, arg1);
		} else {
			Static163.method3105(arg1, arg3, arg4, arg0, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(Ljava/lang/String;I)Lclient!na;")
	public static JString method3952(@OriginalArg(0) String arg0) {
		@Pc(14) byte[] local14;
		try {
			local14 = arg0.getBytes("ISO-8859-1");
		} catch (@Pc(16) UnsupportedEncodingException local16) {
			local14 = arg0.getBytes();
		}
		@Pc(23) JString local23 = new JString();
		local23.aByteArray52 = local14;
		local23.anInt4030 = 0;
		for (@Pc(31) int local31 = 0; local31 < local14.length; local31++) {
			if (local14[local31] != 0) {
				local14[local23.anInt4030++] = local14[local31];
			}
		}
		return local23;
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "b", descriptor = "(II)Lclient!cb;")
	public static QuickChatPhraseType list(@OriginalArg(1) int id) {
		@Pc(10) QuickChatPhraseType cached = (QuickChatPhraseType) Static249.recentUse.get((long) id);
		if (cached != null) {
			return cached;
		}

		@Pc(27) byte[] bytes;
		if (id >= 32768) {
			bytes = Static262.configClientLarge.getfile(1, id & 0x7FFF);
		} else {
			bytes = Static238.configClientSmall.getfile(1, id);
		}

		QuickChatPhraseType quickChatPhraseType = new QuickChatPhraseType();
		if (bytes != null) {
			quickChatPhraseType.decode(new Packet(bytes));
		}
		if (id >= 32768) {
			quickChatPhraseType.postDecode();
		}
		Static249.recentUse.put(quickChatPhraseType, (long) id);
		return quickChatPhraseType;
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(ILclient!na;)V")
	public static void method3954(@OriginalArg(1) JString arg0) {
		Static47.aClass100_991 = arg0;
		if (GameShell.signLink.applet == null) {
			return;
		}
		try {
			@Pc(17) JString local17 = Static272.aClass100_989.method3153(GameShell.signLink.applet);
			@Pc(23) JString local23 = Static246.aClass100_1029.method3153(GameShell.signLink.applet);
			@Pc(48) JString local48 = Static34.method882(new JString[] { local17, Static142.aClass100_667, arg0, Static276.aClass100_1095, local23 });
			if (arg0.length() == 0) {
				local48 = Static34.method882(new JString[] { local48, Static245.aClass100_1018 });
			} else {
				local48 = Static34.method882(new JString[] { local48, Static263.aClass100_1082, Static33.method873(MonotonicTime.get() + 94608000000L), Static64.MAX_AGE, Static154.method2929(94608000L) });
			}
			Static34.method882(new JString[] { BZip2State.aClass100_821, local48, Static223.aClass100_946 }).method3134(GameShell.signLink.applet);
		} catch (@Pc(124) Throwable local124) {
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(II)V")
	public static void method3956(@OriginalArg(0) int arg0) {
		if (Static14.anInt441 == 0) {
			Static172.aClass3_Sub3_Sub4_2.method4447(arg0);
		} else {
			Static253.anInt5527 = arg0;
		}
	}
}
