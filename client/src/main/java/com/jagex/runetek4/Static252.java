package com.jagex.runetek4;

import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static252 {

	@OriginalMember(owner = "runetek4.client!uh", name = "P", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_103;

	@OriginalMember(owner = "runetek4.client!uh", name = "Y", descriptor = "Lclient!na;")
	public static final JString aClass100_1049 = Static28.parse("huffman");

	@OriginalMember(owner = "runetek4.client!uh", name = "ab", descriptor = "Lclient!q;")
	public static final ReferenceNodeFactory aClass22_1 = Static44.method1147();

	@OriginalMember(owner = "runetek4.client!uh", name = "e", descriptor = "(I)V")
	public static void method4301() {
		ClientScriptRunner.aClass99_31.method3103();
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "f", descriptor = "(I)V")
	public static void method4302() {
		if (Static236.method4047() != 2) {
			return;
		}
		@Pc(27) byte local27 = (byte) (Static136.anInt3325 - 4 & 0xFF);
		@Pc(31) int local31 = Static136.anInt3325 % 104;
		@Pc(33) int local33;
		@Pc(40) int local40;
		for (local33 = 0; local33 < 4; local33++) {
			for (local40 = 0; local40 < 104; local40++) {
				Static266.aByteArrayArrayArray15[local33][local31][local40] = local27;
			}
		}
		if (Static55.currentLevel == 3) {
			return;
		}
		for (local33 = 0; local33 < 2; local33++) {
			Static79.anIntArray205[local33] = -1000000;
			Static149.anIntArray338[local33] = 1000000;
			Static267.anIntArray518[local33] = 0;
			Static243.anIntArray476[local33] = 1000000;
			Static50.anIntArray134[local33] = 0;
		}
		if (Static227.anInt5096 != 1) {
			local33 = Static207.getHeightmapY(Static55.currentLevel, Static138.cameraX, Static134.cameraZ);
			if (local33 - Static5.cameraY < 800 && (Static12.aByteArrayArrayArray2[Static55.currentLevel][Static138.cameraX >> 7][Static134.cameraZ >> 7] & 0x4) != 0) {
				Static254.method4348(false, Static138.cameraX >> 7, Static134.cameraZ >> 7, Static130.levelTiles, 1);
			}
			return;
		}
		if ((Static12.aByteArrayArrayArray2[Static55.currentLevel][Static173.localPlayer.x >> 7][Static173.localPlayer.z >> 7] & 0x4) != 0) {
			Static254.method4348(false, Static173.localPlayer.x >> 7, Static173.localPlayer.z >> 7, Static130.levelTiles, 0);
		}
		if (Static240.cameraPitch >= 310) {
			return;
		}
		@Pc(135) int local135 = Static173.localPlayer.z >> 7;
		local40 = Static134.cameraZ >> 7;
		@Pc(146) int local146;
		if (local40 < local135) {
			local146 = local135 - local40;
		} else {
			local146 = local40 - local135;
		}
		local33 = Static138.cameraX >> 7;
		@Pc(162) int local162 = Static173.localPlayer.x >> 7;
		@Pc(174) int local174;
		if (local162 > local33) {
			local174 = local162 - local33;
		} else {
			local174 = local33 - local162;
		}
		@Pc(192) int local192;
		@Pc(186) int local186;
		if (local174 <= local146) {
			local186 = 32768;
			local192 = local174 * 65536 / local146;
			while (local40 != local135) {
				if (local40 < local135) {
					local40++;
				} else if (local40 > local135) {
					local40--;
				}
				if ((Static12.aByteArrayArrayArray2[Static55.currentLevel][local33][local40] & 0x4) != 0) {
					Static254.method4348(false, local33, local40, Static130.levelTiles, 1);
					break;
				}
				local186 += local192;
				if (local186 >= 65536) {
					if (local162 > local33) {
						local33++;
					} else if (local162 < local33) {
						local33--;
					}
					local186 -= 65536;
					if ((Static12.aByteArrayArrayArray2[Static55.currentLevel][local33][local40] & 0x4) != 0) {
						Static254.method4348(false, local33, local40, Static130.levelTiles, 1);
						break;
					}
				}
			}
			return;
		}
		local186 = 32768;
		local192 = local146 * 65536 / local174;
		while (local162 != local33) {
			if (local162 > local33) {
				local33++;
			} else if (local33 > local162) {
				local33--;
			}
			if ((Static12.aByteArrayArrayArray2[Static55.currentLevel][local33][local40] & 0x4) != 0) {
				Static254.method4348(false, local33, local40, Static130.levelTiles, 1);
				break;
			}
			local186 += local192;
			if (local186 >= 65536) {
				if (local40 < local135) {
					local40++;
				} else if (local135 < local40) {
					local40--;
				}
				local186 -= 65536;
				if ((Static12.aByteArrayArrayArray2[Static55.currentLevel][local33][local40] & 0x4) != 0) {
					Static254.method4348(false, local33, local40, Static130.levelTiles, 1);
					break;
				}
			}
		}
	}
}
