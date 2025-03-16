package com.jagex.runetek4;

import com.jagex.runetek4.audio.vorbis.VorbisCodebook;
import com.jagex.runetek4.audio.vorbis.VorbisFloor;
import com.jagex.runetek4.audio.vorbis.VorbisSound;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static117 {

	@OriginalMember(owner = "runetek4.client!jc", name = "p", descriptor = "[F")
	public static float[] aFloatArray9;

	@OriginalMember(owner = "runetek4.client!jc", name = "q", descriptor = "[F")
	public static float[] aFloatArray10;

	@OriginalMember(owner = "runetek4.client!jc", name = "t", descriptor = "[I")
	public static int[] anIntArray276;

	@OriginalMember(owner = "runetek4.client!jc", name = "v", descriptor = "[Z")
	public static boolean[] aBooleanArray63;

	@OriginalMember(owner = "runetek4.client!jc", name = "y", descriptor = "I")
	public static int anInt2975;

	@OriginalMember(owner = "runetek4.client!jc", name = "B", descriptor = "[F")
	public static float[] aFloatArray12;

	@OriginalMember(owner = "runetek4.client!jc", name = "C", descriptor = "[F")
	public static float[] aFloatArray13;

	@OriginalMember(owner = "runetek4.client!jc", name = "E", descriptor = "I")
	public static int anInt2978;

	@OriginalMember(owner = "runetek4.client!jc", name = "F", descriptor = "[F")
	public static float[] aFloatArray14;

	@OriginalMember(owner = "runetek4.client!jc", name = "I", descriptor = "[Lclient!ji;")
	public static VorbisCodebook[] aClass78Array1;

	@OriginalMember(owner = "runetek4.client!jc", name = "J", descriptor = "[Lclient!ie;")
	public static VorbisFloor[] aVorbisFloorArray1;

	@OriginalMember(owner = "runetek4.client!jc", name = "L", descriptor = "[I")
	public static int[] anIntArray277;

	@OriginalMember(owner = "runetek4.client!jc", name = "P", descriptor = "[F")
	public static float[] aFloatArray15;

	@OriginalMember(owner = "runetek4.client!jc", name = "S", descriptor = "[F")
	public static float[] aFloatArray16;

	@OriginalMember(owner = "runetek4.client!jc", name = "W", descriptor = "[I")
	public static int[] anIntArray278;

	@OriginalMember(owner = "runetek4.client!jc", name = "H", descriptor = "Z")
	private static boolean aBoolean149 = false;

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "(Lclient!ve;)Z")
	private static boolean method2344(@OriginalArg(0) CacheArchive arg0) {
		if (!aBoolean149) {
			@Pc(7) byte[] local7 = arg0.getfile(0, 0);
			if (local7 == null) {
				return false;
			}
			VorbisSound.method2349(local7);
			aBoolean149 = true;
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!jc", name = "a", descriptor = "(Lclient!ve;II)Lclient!jc;")
	public static VorbisSound method2345(@OriginalArg(0) CacheArchive arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (method2344(arg0)) {
			@Pc(16) byte[] local16 = arg0.getfile(arg1, arg2);
			return local16 == null ? null : new VorbisSound(local16);
		} else {
			arg0.requestDownload(arg1, arg2);
			return null;
		}
	}
}