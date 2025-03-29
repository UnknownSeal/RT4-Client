package com.jagex.runetek4;

import com.jagex.runetek4.audio.SynthSound;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= FloorUnderlayTypeList.anInt5063) {
			@Pc(22) int local22 = IntUtils.clamp(Static106.anInt2869, arg1, Static267.anInt5773);
			@Pc(28) int local28 = IntUtils.clamp(Static106.anInt2869, arg0, Static267.anInt5773);
			Static101.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "client!ed", name = "c", descriptor = "(I)V")
	public static void clear() {
		FloorUnderlayTypeList.types.clean();
	}

	@OriginalMember(owner = "client!ed", name = "d", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(5) int local5 = 0; local5 < SoundPlayer.size; local5++) {
			@Pc(12) int local12 = SoundPlayer.delays[local5]--;
			if (SoundPlayer.delays[local5] >= -10) {
				@Pc(79) SynthSound local79 = SoundPlayer.sounds[local5];
				if (local79 == null) {
					local79 = SynthSound.create(client.js5Archive4, SoundPlayer.ids[local5], 0);
					if (local79 == null) {
						continue;
					}
					SoundPlayer.delays[local5] += local79.delay();
					SoundPlayer.sounds[local5] = local79;
				}
				if (SoundPlayer.delays[local5] < 0) {
					@Pc(209) int local209;
					if (SoundPlayer.positions[local5] == 0) {
						local209 = Preferences.soundEffectVolume;
					} else {
						@Pc(125) int local125 = (SoundPlayer.positions[local5] & 0xFF) * 128;
						@Pc(133) int local133 = SoundPlayer.positions[local5] >> 8 & 0xFF;
						@Pc(141) int local141 = SoundPlayer.positions[local5] >> 16 & 0xFF;
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
							SoundPlayer.delays[local5] = -100;
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
						local228.setLoops(SoundPlayer.loops[local5] - 1);
						client.soundStream.addSubStream(local228);
					}
					SoundPlayer.delays[local5] = -100;
				}
			} else {
				SoundPlayer.size--;
				for (@Pc(28) int local28 = local5; local28 < SoundPlayer.size; local28++) {
					SoundPlayer.ids[local28] = SoundPlayer.ids[local28 + 1];
					SoundPlayer.sounds[local28] = SoundPlayer.sounds[local28 + 1];
					SoundPlayer.loops[local28] = SoundPlayer.loops[local28 + 1];
					SoundPlayer.delays[local28] = SoundPlayer.delays[local28 + 1];
					SoundPlayer.positions[local28] = SoundPlayer.positions[local28 + 1];
				}
				local5--;
			}
		}
		if (MidiPlayer.jingle && !MidiPlayer.isPlaying()) {
			if (Preferences.musicVolume != 0 && MusicPlayer.groupId != -1) {
				MidiPlayer.playImmediate(client.js5Archive6, MusicPlayer.groupId, Preferences.musicVolume);
			}
			MidiPlayer.jingle = false;
		} else if (Preferences.musicVolume != 0 && MusicPlayer.groupId != -1 && !MidiPlayer.isPlaying()) {
			Protocol.outboundBuffer.pIsaac1(137);
			Protocol.outboundBuffer.p4(MusicPlayer.groupId);
			MusicPlayer.groupId = -1;
		}
	}
}
