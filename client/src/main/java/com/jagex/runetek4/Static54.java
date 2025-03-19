package com.jagex.runetek4;

import com.jagex.runetek4.audio.SynthSound;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "client!ed", name = "D", descriptor = "Lclient!na;")
	public static final JString DETAILS = JString.parse("details");

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= FloorUnderlayTypeList.anInt5063) {
			@Pc(22) int local22 = Static78.method1690(Static106.anInt2869, arg1, Static267.anInt5773);
			@Pc(28) int local28 = Static78.method1690(Static106.anInt2869, arg0, Static267.anInt5773);
			Static101.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "client!ed", name = "c", descriptor = "(I)V")
	public static void clear() {
		FloorUnderlayTypeList.types.clean();
	}

	@OriginalMember(owner = "client!ed", name = "b", descriptor = "(II)Lclient!ba;")
	public static GWCWorld method1310(@OriginalArg(1) int arg0) {
		return WorldList.loaded && arg0 >= Static19.anInt636 && arg0 <= Static171.anInt4157 ? Static196.aClass10_Sub1Array2[arg0 - Static19.anInt636] : null;
	}

	@OriginalMember(owner = "client!ed", name = "d", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(5) int local5 = 0; local5 < SoundPlayer.size; local5++) {
			@Pc(12) int local12 = Static164.anIntArray362[local5]--;
			if (Static164.anIntArray362[local5] >= -10) {
				@Pc(79) SynthSound local79 = Static173.aClass138Array1[local5];
				if (local79 == null) {
					local79 = SynthSound.create(client.js5Archive4, Static200.anIntArray421[local5], 0);
					if (local79 == null) {
						continue;
					}
					Static164.anIntArray362[local5] += local79.delay();
					Static173.aClass138Array1[local5] = local79;
				}
				if (Static164.anIntArray362[local5] < 0) {
					@Pc(209) int local209;
					if (Static26.anIntArray68[local5] == 0) {
						local209 = Static125.anInt3104;
					} else {
						@Pc(125) int local125 = (Static26.anIntArray68[local5] & 0xFF) * 128;
						@Pc(133) int local133 = Static26.anIntArray68[local5] >> 8 & 0xFF;
						@Pc(141) int local141 = Static26.anIntArray68[local5] >> 16 & 0xFF;
						@Pc(151) int local151 = local133 * 128 + 64 - PlayerList.self.zFine;
						if (local151 < 0) {
							local151 = -local151;
						}
						@Pc(167) int local167 = local141 * 128 + 64 - PlayerList.self.xFine;
						if (local167 < 0) {
							local167 = -local167;
						}
						@Pc(180) int local180 = local167 + local151 - 128;
						if (local125 < local180) {
							Static164.anIntArray362[local5] = -100;
							continue;
						}
						if (local180 < 0) {
							local180 = 0;
						}
						local209 = Preferences.ambientSoundsVolume * (local125 - local180) / local125;
					}
					if (local209 > 0) {
						@Pc(223) PcmSound local223 = local79.toPcmSound().resample(client.pcmResampler);
						@Pc(228) SoundPcmStream local228 = Static284.method404(local223, local209);
						local228.setLoops(Static276.anIntArray563[local5] - 1);
						client.soundStream.addSubStream(local228);
					}
					Static164.anIntArray362[local5] = -100;
				}
			} else {
				SoundPlayer.size--;
				for (@Pc(28) int local28 = local5; local28 < SoundPlayer.size; local28++) {
					Static200.anIntArray421[local28] = Static200.anIntArray421[local28 + 1];
					Static173.aClass138Array1[local28] = Static173.aClass138Array1[local28 + 1];
					Static276.anIntArray563[local28] = Static276.anIntArray563[local28 + 1];
					Static164.anIntArray362[local28] = Static164.anIntArray362[local28 + 1];
					Static26.anIntArray68[local28] = Static26.anIntArray68[local28 + 1];
				}
				local5--;
			}
		}
		if (Static144.jingle && !Static136.method2655()) {
			if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1) {
				Static122.method2410(client.js5Archive6, BZip2State.anInt4363, Static12.anInt391);
			}
			Static144.jingle = false;
		} else if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1 && !Static136.method2655()) {
			Protocol.outboundBuffer.pIsaac1(137);
			Protocol.outboundBuffer.p4(BZip2State.anInt4363);
			BZip2State.anInt4363 = -1;
		}
	}
}
