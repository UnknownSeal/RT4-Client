package com.jagex.runetek4;

import java.io.IOException;
import java.net.Socket;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static216 {

	@OriginalMember(owner = "runetek4.client!ri", name = "c", descriptor = "Lclient!ve;")
	public static Js5 aClass153_31;

	@OriginalMember(owner = "runetek4.client!ri", name = "d", descriptor = "[I")
	public static int[] anIntArray188;

	@OriginalMember(owner = "runetek4.client!ri", name = "b", descriptor = "[I")
	public static final int[] anIntArray187 = new int[14];

	@OriginalMember(owner = "runetek4.client!ri", name = "a", descriptor = "(B)V")
	public static void method1639() {
		if (LoginManager.step == 0 || LoginManager.step == 5) {
			return;
		}
		try {
			if (++Static92.anInt2430 > 2000) {
				if (Static124.gameServerSocket != null) {
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
				}
				if (Static276.anInt5816 >= 1) {
					LoginManager.reply = -5;
					LoginManager.step = 0;
					return;
				}
				Static92.anInt2430 = 0;
				if (client.port == client.defaultPort) {
					client.port = client.alternatePort;
				} else {
					client.port = client.defaultPort;
				}
				LoginManager.step = 1;
				Static276.anInt5816++;
			}
			if (LoginManager.step == 1) {
				Static72.aClass212_3 = GameShell.signLink.openSocket(client.hostname, client.port);
				LoginManager.step = 2;
			}
			if (LoginManager.step == 2) {
				if (Static72.aClass212_3.status == 2) {
					throw new IOException();
				}
				if (Static72.aClass212_3.status != 1) {
					return;
				}
				Static124.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
				Static72.aClass212_3 = null;
				@Pc(106) long local106 = Static101.aLong98 = LoginManager.username.encode37();
				Protocol.outboundBuffer.offset = 0;
				Protocol.outboundBuffer.p1(14);
				@Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
				Protocol.outboundBuffer.p1(local120);
				Static124.gameServerSocket.write(2, Protocol.outboundBuffer.data);
				if (client.musicChannel != null) {
					client.musicChannel.method3571();
				}
				if (client.soundChannel != null) {
					client.soundChannel.method3571();
				}
				@Pc(150) int local150 = Static124.gameServerSocket.read();
				if (client.musicChannel != null) {
					client.musicChannel.method3571();
				}
				if (client.soundChannel != null) {
					client.soundChannel.method3571();
				}
				if (local150 != 0) {
					LoginManager.reply = local150;
					LoginManager.step = 0;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				LoginManager.step = 3;
			}
			if (LoginManager.step == 3) {
				if (Static124.gameServerSocket.available() < 8) {
					return;
				}
				Static124.gameServerSocket.read(0, 8, Protocol.inboundBuffer.data);
				Protocol.inboundBuffer.offset = 0;
				Static193.aLong147 = Protocol.inboundBuffer.g8();
				@Pc(210) int[] seed = new int[4];
				Protocol.outboundBuffer.offset = 0;
				seed[2] = (int) (Static193.aLong147 >> 32);
				seed[3] = (int) Static193.aLong147;
				seed[1] = (int) (Math.random() * 9.9999999E7D);
				seed[0] = (int) (Math.random() * 9.9999999E7D);
				Protocol.outboundBuffer.p1(10);
				Protocol.outboundBuffer.p4(seed[0]);
				Protocol.outboundBuffer.p4(seed[1]);
				Protocol.outboundBuffer.p4(seed[2]);
				Protocol.outboundBuffer.p4(seed[3]);
				Protocol.outboundBuffer.p8(LoginManager.username.encode37());
				Protocol.outboundBuffer.pjstr(LoginManager.password);
				Protocol.outboundBuffer.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
				Static17.aClass3_Sub15_Sub1_2.offset = 0;
				if (client.gameState == 40) {
					Static17.aClass3_Sub15_Sub1_2.p1(18);
				} else {
					Static17.aClass3_Sub15_Sub1_2.p1(16);
				}

				Static17.aClass3_Sub15_Sub1_2.p2(Protocol.outboundBuffer.offset + Static229.method3937(client.settings) + 159);
				Static17.aClass3_Sub15_Sub1_2.p4(530);
				Static17.aClass3_Sub15_Sub1_2.p1(LoginManager.anInt39);
				Static17.aClass3_Sub15_Sub1_2.p1(client.advertSuppressed ? 1 : 0);
				Static17.aClass3_Sub15_Sub1_2.p1(1);
				Static17.aClass3_Sub15_Sub1_2.p1(DisplayMode.getWindowMode());
				Static17.aClass3_Sub15_Sub1_2.p2(GameShell.canvasWidth);
				Static17.aClass3_Sub15_Sub1_2.p2(GameShell.canvasHeigth);
				Static17.aClass3_Sub15_Sub1_2.p1(Preferences.antiAliasingMode);
				client.writeUid(Static17.aClass3_Sub15_Sub1_2);
				Static17.aClass3_Sub15_Sub1_2.pjstr(client.settings);
				Static17.aClass3_Sub15_Sub1_2.p4(client.affiliate);
				Static17.aClass3_Sub15_Sub1_2.p4(Static145.method2746());
				Preferences.sentToServer = true;
				Static17.aClass3_Sub15_Sub1_2.p2(Protocol.verifyId);
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive0.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive1.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive2.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive3.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive4.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive5.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive6.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive7.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive8.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive9.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive10.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive11.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive12.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive13.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive14.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive15.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive16.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive17.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive18.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive19.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive20.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive21.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive22.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive23.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive24.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive25.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive26.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive27.getChecksum());
				Static17.aClass3_Sub15_Sub1_2.pdata(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
				Static124.gameServerSocket.write(Static17.aClass3_Sub15_Sub1_2.offset, Static17.aClass3_Sub15_Sub1_2.data);
				Protocol.outboundBuffer.Isaac(seed);
				for (@Pc(583) int i = 0; i < 4; i++) {
					seed[i] += 50;
				}
				Protocol.inboundBuffer.Isaac(seed);
				LoginManager.step = 4;
			}
			if (LoginManager.step == 4) {
				if (Static124.gameServerSocket.available() < 1) {
					return;
				}
				@Pc(623) int local623 = Static124.gameServerSocket.read();
				if (local623 == 21) {
					LoginManager.step = 7;
				} else if (local623 == 29) {
					LoginManager.step = 10;
				} else if (local623 == 1) {
					LoginManager.step = 5;
					LoginManager.reply = local623;
					return;
				} else if (local623 == 2) {
					LoginManager.step = 8;
				} else if (local623 == 15) {
					LoginManager.step = 0;
					LoginManager.reply = local623;
					return;
				} else if (local623 == 23 && Static276.anInt5816 < 1) {
					LoginManager.step = 1;
					Static276.anInt5816++;
					Static92.anInt2430 = 0;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				} else {
					LoginManager.reply = local623;
					LoginManager.step = 0;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
			}
			if (LoginManager.step == 6) {
				Protocol.outboundBuffer.offset = 0;
				Protocol.outboundBuffer.pIsaac1(17);
				Static124.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
				LoginManager.step = 4;
				return;
			}
			if (LoginManager.step == 7) {
				if (Static124.gameServerSocket.available() >= 1) {
					PreciseSleep.anInt5202 = (Static124.gameServerSocket.read() + 3) * 60;
					LoginManager.step = 0;
					LoginManager.reply = 21;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				return;
			}
			if (LoginManager.step == 10) {
				if (Static124.gameServerSocket.available() >= 1) {
					LoginManager.disallowResult = Static124.gameServerSocket.read();
					LoginManager.step = 0;
					LoginManager.reply = 29;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				return;
			}
			if (LoginManager.step == 8) {
				if (Static124.gameServerSocket.available() < 14) {
					return;
				}
				Static124.gameServerSocket.read(0, 14, Protocol.inboundBuffer.data);
				Protocol.inboundBuffer.offset = 0;
				Static191.staffModLevel = Protocol.inboundBuffer.g1();
				Static249.anInt5431 = Protocol.inboundBuffer.g1();
				Static124.aBoolean157 = Protocol.inboundBuffer.g1() == 1;
				Static207.parentalChatConsent = Protocol.inboundBuffer.g1() == 1;
				Static25.aBoolean57 = Protocol.inboundBuffer.g1() == 1;
				Static86.aBoolean129 = Protocol.inboundBuffer.g1() == 1;
				Static245.enabled = Protocol.inboundBuffer.g1() == 1;
				PlayerList.selfId = Protocol.inboundBuffer.g2();
				Class6.members = Protocol.inboundBuffer.g1() == 1;
				Static2.membersWorld = Protocol.inboundBuffer.g1() == 1;
				Static189.method3438(Static2.membersWorld);
				CacheArchive.method186(Static2.membersWorld);
				if (!client.advertSuppressed) {
					if (Static124.aBoolean157 && !Static25.aBoolean57 || Class6.members) {
						try {
							Static167.aClass100_781.browserControlCall(GameShell.signLink.applet);
						} catch (@Pc(910) Throwable local910) {
						}
					} else {
						try {
							Static56.aClass100_380.browserControlCall(GameShell.signLink.applet);
						} catch (@Pc(920) Throwable local920) {
						}
					}
				}
				Protocol.opcode = Protocol.inboundBuffer.gIssac1();
				Static223.packetSize = Protocol.inboundBuffer.g2();
				LoginManager.step = 9;
			}
			if (LoginManager.step == 9) {
				if (Static124.gameServerSocket.available() < Static223.packetSize) {
					return;
				}
				Protocol.inboundBuffer.offset = 0;
				Static124.gameServerSocket.read(0, Static223.packetSize, Protocol.inboundBuffer.data);
				LoginManager.reply = 2;
				LoginManager.step = 0;
				client.method4221();
				Static80.anInt4701 = -1;
				Static75.method1629(false);
				Protocol.opcode = -1;
				return;
			}
		} catch (@Pc(977) IOException local977) {
			if (Static124.gameServerSocket != null) {
				Static124.gameServerSocket.closeGracefully();
				Static124.gameServerSocket = null;
			}
			if (Static276.anInt5816 >= 1) {
				LoginManager.step = 0;
				LoginManager.reply = -4;
			} else {
				LoginManager.step = 1;
				Static92.anInt2430 = 0;
				Static276.anInt5816++;
				if (client.defaultPort == client.port) {
					client.port = client.alternatePort;
				} else {
					client.port = client.defaultPort;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ri", name = "a", descriptor = "(II)I")
	public static int method1640(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg1 = arg1 * (arg0 & 0x7F) >> 7;
		if (arg1 < 2) {
			arg1 = 2;
		} else if (arg1 > 126) {
			arg1 = 126;
		}
		return (arg0 & 0xFF80) + arg1;
	}
}
