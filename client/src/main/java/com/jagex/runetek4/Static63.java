package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IntWrapper;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static63 {

	@OriginalMember(owner = "client!fa", name = "a", descriptor = "I")
	public static int oncard_texture = 0;

	@OriginalMember(owner = "client!fa", name = "c", descriptor = "I")
	public static int anInt1943 = 0;

	@OriginalMember(owner = "client!fa", name = "e", descriptor = "I")
	public static int oncard_2d = 0;

	@OriginalMember(owner = "client!fa", name = "f", descriptor = "I")
	public static int oncard_geometry = 0;

	@OriginalMember(owner = "client!fa", name = "a", descriptor = "(III)V")
	public static synchronized void method1485(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (arg2 == anInt1943) {
			@Pc(8) IntWrapper local8 = new IntWrapper(arg1);
			local8.nodeId = arg0;
			GlCleaner.aClass69_50.addTail(local8);
		}
	}

	@OriginalMember(owner = "client!fa", name = "a", descriptor = "(II)V")
	public static synchronized void method1486(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (arg1 == anInt1943) {
			@Pc(7) IntWrapper local7 = new IntWrapper();
			local7.nodeId = arg0;
			GlCleaner.aClass69_51.addTail(local7);
		}
	}

	@OriginalMember(owner = "client!fa", name = "b", descriptor = "()V")
	public static synchronized void clear() {
		anInt1943++;
		GlCleaner.aClass69_48.clear();
		GlCleaner.aClass69_49.clear();
		GlCleaner.aClass69_50.clear();
		GlCleaner.aClass69_51.clear();
		oncard_geometry = 0;
		oncard_2d = 0;
		oncard_texture = 0;
	}

	@OriginalMember(owner = "client!fa", name = "b", descriptor = "(III)V")
	public static synchronized void method1489(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (arg2 == anInt1943) {
			@Pc(8) IntWrapper local8 = new IntWrapper(arg1);
			local8.nodeId = arg0;
			GlCleaner.aClass69_48.addTail(local8);
		}
	}

	@OriginalMember(owner = "client!fa", name = "c", descriptor = "(III)V")
	public static synchronized void method1491(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (arg2 == anInt1943) {
			@Pc(8) IntWrapper local8 = new IntWrapper(arg1);
			local8.nodeId = arg0;
			GlCleaner.aClass69_49.addTail(local8);
		}
	}
}
