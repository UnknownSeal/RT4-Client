package com.jagex.runetek4;

import java.awt.Component;

import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static107 {

	@OriginalMember(owner = "runetek4.client!id", name = "i", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray10;

	@OriginalMember(owner = "runetek4.client!id", name = "k", descriptor = "I")
	public static int anInt2878;

    @OriginalMember(owner = "runetek4.client!id", name = "b", descriptor = "I")
	public static int anInt2875 = -1;

	@OriginalMember(owner = "runetek4.client!id", name = "c", descriptor = "Lclient!na;")
	public static final JString FPSON = Static28.parse("::fpson");

	@OriginalMember(owner = "runetek4.client!id", name = "d", descriptor = "[[[I")
	public static final int[][][] anIntArrayArrayArray9 = new int[2][][];

	@OriginalMember(owner = "runetek4.client!id", name = "e", descriptor = "[I")
	public static final int[] anIntArray259 = new int[14];

	@OriginalMember(owner = "runetek4.client!id", name = "f", descriptor = "Z")
	public static boolean aBoolean147 = false;

	@OriginalMember(owner = "runetek4.client!id", name = "j", descriptor = "Lclient!of;")
	public static QuickChatCommandDecoder anInterface3_1 = null;

	@OriginalMember(owner = "runetek4.client!id", name = "b", descriptor = "(I)V")
	public static void method2261() {
		if (Static147.aClass62_2 != null) {
			Static147.aClass62_2.loop();
		}
		if (Static11.aClass62_1 != null) {
			Static11.aClass62_1.loop();
		}
	}

	@OriginalMember(owner = "runetek4.client!id", name = "a", descriptor = "(ILsignlink!ll;Ljava/awt/runetek4.Component;II)Lclient!vh;")
	public static AudioChannel method2262(@OriginalArg(0) int arg0, @OriginalArg(1) SignLink arg1, @OriginalArg(2) Component arg2, @OriginalArg(3) int arg3) {
		if (AudioChannel.sampleRate == 0) {
			throw new IllegalStateException();
		}
		try {
			@Pc(33) AudioChannel local33 = (AudioChannel) Class.forName("com.jagex.runetek4.JavaAudioChannel").getDeclaredConstructor().newInstance();
			local33.sampleRate2 = arg0;
			local33.samples = new int[(AudioChannel.stereo ? 2 : 1) * 256];
			local33.method3576(arg2);
			local33.bufferCapacity = (arg0 & -1024) + 1024;
			if (local33.bufferCapacity > 16384) {
				local33.bufferCapacity = 16384;
			}
			local33.open(local33.bufferCapacity);
			if (AudioChannel.threadPriority > 0 && AudioChannel.thread == null) {
				AudioChannel.thread = new AudioThread();
				AudioChannel.thread.signLink = arg1;
				arg1.startThread(AudioChannel.threadPriority, AudioChannel.thread);
			}
			if (AudioChannel.thread != null) {
				if (AudioChannel.thread.channels[arg3] != null) {
					throw new IllegalArgumentException();
				}
				AudioChannel.thread.channels[arg3] = local33;
			}
			return local33;
		} catch (@Pc(109) Throwable local109) {
			try {
				@Pc(120) SignLinkAudioChannel local120 = new SignLinkAudioChannel(arg1, arg3);
				local120.samples = new int[(AudioChannel.stereo ? 2 : 1) * 256];
				local120.sampleRate2 = arg0;
				local120.method3576(arg2);
				local120.bufferCapacity = 16384;
				local120.open(local120.bufferCapacity);
				if (AudioChannel.threadPriority > 0 && AudioChannel.thread == null) {
					AudioChannel.thread = new AudioThread();
					AudioChannel.thread.signLink = arg1;
					arg1.startThread(AudioChannel.threadPriority, AudioChannel.thread);
				}
				if (AudioChannel.thread != null) {
					if (AudioChannel.thread.channels[arg3] != null) {
						throw new IllegalArgumentException();
					}
					AudioChannel.thread.channels[arg3] = local120;
				}
				return local120;
			} catch (@Pc(186) Throwable local186) {
				return new AudioChannel();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!id", name = "a", descriptor = "(IIBII)V")
	public static void method2263(@OriginalArg(3) int arg0, @OriginalArg(4) int arg1) {
		Static224.anInt5063 = arg1;
		Static172.anInt4164 = 0;
		Static267.anInt5773 = 0;
		Static106.anInt2869 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!id", name = "a", descriptor = "(Lclient!ve;B)V")
	public static void method2264(@OriginalArg(0) Js5 arg0) {
		Static85.aClass153_36 = arg0;
	}
}
