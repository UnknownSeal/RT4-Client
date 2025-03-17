package com.jagex.runetek4;

import java.io.IOException;
import java.net.Socket;

import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static31 {

	@OriginalMember(owner = "client!ch", name = "w", descriptor = "I")
	public static int anInt987;

	@OriginalMember(owner = "client!ch", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_193 = JString.parse(":");

	@OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
	public static final int[] cameraModifierCycle = new int[5];

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] anIntArrayArray6 = new int[104][104];

	@OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
	public static void method846() {
		if (!Static138.allLevelsvisible() && Static41.anInt1316 != Player.plane) {
			Static127.method2463(Player.plane, Static52.anInt1695, Static80.anInt4701, PlayerList.self.movementQueueZ[0], false, PlayerList.self.movementQueueX[0]);
		} else if (Player.plane != LightingManager.anInt2875 && Static137.method2665(Player.plane)) {
			LightingManager.anInt2875 = Player.plane;
			Static269.method2218();
		}
	}

	@OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
	public static void method848() {
		if (LoginManager.autoStep == 0) {
			return;
		}
		try {
			if (++LoginManager.anInt673 > 1500) {
				if (Static124.gameServerSocket != null) {
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
				}
				if (LoginManager.anInt4587 >= 1) {
					LoginManager.reply = -5;
					LoginManager.autoStep = 0;
					return;
				}
				LoginManager.anInt673 = 0;
				LoginManager.anInt4587++;
				LoginManager.autoStep = 1;
				if (client.worldListPort == client.worldListDefaultPort) {
					client.worldListPort = client.worldListAlternatePort;
				} else {
					client.worldListPort = client.worldListDefaultPort;
				}
			}
			if (LoginManager.autoStep == 1) {
				Static72.aClass212_3 = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
				LoginManager.autoStep = 2;
			}
			@Pc(126) int local126;
			if (LoginManager.autoStep == 2) {
				if (Static72.aClass212_3.status == 2) {
					throw new IOException();
				}
				if (Static72.aClass212_3.status != 1) {
					return;
				}
				Static124.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
				Static72.aClass212_3 = null;
				Static124.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
				if (client.musicChannel != null) {
					client.musicChannel.method3571();
				}
				if (client.soundChannel != null) {
					client.soundChannel.method3571();
				}
				local126 = Static124.gameServerSocket.read();
				if (client.musicChannel != null) {
					client.musicChannel.method3571();
				}
				if (client.soundChannel != null) {
					client.soundChannel.method3571();
				}
				if (local126 != 101) {
					LoginManager.reply = local126;
					LoginManager.autoStep = 0;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				LoginManager.autoStep = 3;
			}
			if (LoginManager.autoStep == 3) {
				if (Static124.gameServerSocket.available() < 2) {
					return;
				}
				local126 = Static124.gameServerSocket.read() << 8 | Static124.gameServerSocket.read();
				Static176.method3303(local126);
				if (Static125.worldId == -1) {
					LoginManager.autoStep = 0;
					LoginManager.reply = 6;
					Static124.gameServerSocket.closeGracefully();
					Static124.gameServerSocket = null;
					return;
				}
				LoginManager.autoStep = 0;
				Static124.gameServerSocket.closeGracefully();
				Static124.gameServerSocket = null;
				LoginManager.method1208();
			}
		} catch (@Pc(210) IOException local210) {
			if (Static124.gameServerSocket != null) {
				Static124.gameServerSocket.closeGracefully();
				Static124.gameServerSocket = null;
			}
			if (LoginManager.anInt4587 < 1) {
				if (client.worldListPort == client.worldListDefaultPort) {
					client.worldListPort = client.worldListAlternatePort;
				} else {
					client.worldListPort = client.worldListDefaultPort;
				}
				LoginManager.autoStep = 1;
				LoginManager.anInt673 = 0;
				LoginManager.anInt4587++;
			} else {
				LoginManager.reply = -4;
				LoginManager.autoStep = 0;
			}
		}
	}
}
