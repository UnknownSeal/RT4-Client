package com.jagex.runetek4;

import java.awt.Component;
import java.io.IOException;
import java.net.Socket;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static31 {

	@OriginalMember(owner = "client!ch", name = "w", descriptor = "I")
	public static int anInt987;

	@OriginalMember(owner = "client!ch", name = "v", descriptor = "[B")
	public static final byte[] aByteArray12 = new byte[] { 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };

	@OriginalMember(owner = "client!ch", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_193 = Static28.parse(":");

	@OriginalMember(owner = "client!ch", name = "y", descriptor = "[Z")
	public static final boolean[] aBooleanArray29 = new boolean[100];

	@OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
	public static final int[] cameraModifierCycle = new int[5];

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] anIntArrayArray6 = new int[104][104];

	@OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
	public static void method846() {
		if (!Static138.allLevelsvisible() && Static41.anInt1316 != Static55.currentLevel) {
			Static127.method2463(Static55.currentLevel, Static52.anInt1695, Static80.anInt4701, Static173.localPlayer.pathTileZ[0], false, Static173.localPlayer.pathTileX[0]);
		} else if (Static55.currentLevel != Static107.anInt2875 && Static137.method2665(Static55.currentLevel)) {
			Static107.anInt2875 = Static55.currentLevel;
			Static269.method2218();
		}
	}

	@OriginalMember(owner = "client!ch", name = "a", descriptor = "(Ljava/awt/Component;I)V")
	public static void method847(@OriginalArg(0) Component arg0) {
		arg0.removeKeyListener(Static10.aClass149_1);
		arg0.removeFocusListener(Static10.aClass149_1);
		Static114.anInt5844 = -1;
	}

	@OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
	public static void method848() {
		if (Static219.anInt4937 == 0) {
			return;
		}
		try {
			if (++Static20.anInt673 > 1500) {
				if (Static124.gameServerSocket != null) {
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
				}
				if (Static196.anInt4587 >= 1) {
					Static266.anInt5336 = -5;
					Static219.anInt4937 = 0;
					return;
				}
				Static20.anInt673 = 0;
				Static196.anInt4587++;
				Static219.anInt4937 = 1;
				if (Static208.worldListPort == client.worldListDefaultPort) {
					Static208.worldListPort = client.worldListAlternatePort;
				} else {
					Static208.worldListPort = client.worldListDefaultPort;
				}
			}
			if (Static219.anInt4937 == 1) {
				Static72.aClass212_3 = GameShell.signLink.openSocket(client.worldListHostname, Static208.worldListPort);
				Static219.anInt4937 = 2;
			}
			@Pc(126) int local126;
			if (Static219.anInt4937 == 2) {
				if (Static72.aClass212_3.status == 2) {
					throw new IOException();
				}
				if (Static72.aClass212_3.status != 1) {
					return;
				}
				Static124.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
				Static72.aClass212_3 = null;
				Static124.gameServerSocket.write(Static6.outboundBuffer.position, Static6.outboundBuffer.data);
				if (Static11.aClass62_1 != null) {
					Static11.aClass62_1.method3571();
				}
				if (Static147.aClass62_2 != null) {
					Static147.aClass62_2.method3571();
				}
				local126 = Static124.gameServerSocket.read();
				if (Static11.aClass62_1 != null) {
					Static11.aClass62_1.method3571();
				}
				if (Static147.aClass62_2 != null) {
					Static147.aClass62_2.method3571();
				}
				if (local126 != 101) {
					Static266.anInt5336 = local126;
					Static219.anInt4937 = 0;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				Static219.anInt4937 = 3;
			}
			if (Static219.anInt4937 == 3) {
				if (Static124.gameServerSocket.available() < 2) {
					return;
				}
				local126 = Static124.gameServerSocket.read() << 8 | Static124.gameServerSocket.read();
				Static176.method3303(local126);
				if (Static125.worldId == -1) {
					Static219.anInt4937 = 0;
					Static266.anInt5336 = 6;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				Static219.anInt4937 = 0;
				Static124.gameServerSocket.closeGracefully();
				Static124.gameServerSocket = null;
				Static49.method1208();
			}
		} catch (@Pc(210) IOException local210) {
			if (Static124.gameServerSocket != null) {
				Static124.gameServerSocket.closeGracefully();
				Static124.gameServerSocket = null;
			}
			if (Static196.anInt4587 < 1) {
				if (Static208.worldListPort == client.worldListDefaultPort) {
					Static208.worldListPort = client.worldListAlternatePort;
				} else {
					Static208.worldListPort = client.worldListDefaultPort;
				}
				Static219.anInt4937 = 1;
				Static20.anInt673 = 0;
				Static196.anInt4587++;
			} else {
				Static266.anInt5336 = -4;
				Static219.anInt4937 = 0;
			}
		}
	}
}
