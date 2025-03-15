package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.dash3d.entity.LocMergeEntity;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static271 {

	@OriginalMember(owner = "runetek4.client!wc", name = "c", descriptor = "I")
	public static int defaultPort;

	@OriginalMember(owner = "runetek4.client!wc", name = "g", descriptor = "I")
	public static int anInt5804 = 0;

	@OriginalMember(owner = "runetek4.client!wc", name = "h", descriptor = "[[I")
	public static final int[][] anIntArrayArray46 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

    @OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!e;I)V")
	public static void method4597(@OriginalArg(0) PlayerEntity arg0) {
		@Pc(10) AreaSound local10 = (AreaSound) AreaSoundManager.playerSounds.getNode(arg0.name.toBase37());
		if (local10 == null) {
			return;
		}
		if (local10.primaryStream != null) {
			Static204.soundStream.removeSubStream(local10.primaryStream);
			local10.primaryStream = null;
		}
		local10.unlink();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method4598(@OriginalArg(0) Packet arg0) {
		if (client.uid != null) {
			try {
				client.uid.setReadIndex(0L);
				client.uid.method1458(arg0.data, arg0.position, 24);
			} catch (@Pc(16) Exception local16) {
			}
		}
		arg0.position += 24;
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(ZI)V")
	public static void method4600(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(4, arg0);
		local8.method1007();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(II)Lclient!pb;")
	public static LocMergeEntity get(@OriginalArg(1) int id) {
		@Pc(15) LocMergeEntity locType = (LocMergeEntity) Static179.aClass99_25.get((long) id);
		if (locType != null) {
			return locType;
		}
		@Pc(30) byte[] bytes = Static146.aClass153_54.getfile(Static253.method4328(id), Static33.method872(id));
		locType = new LocMergeEntity();
		locType.anInt4426 = id;
		if (bytes != null) {
			locType.decode(new Packet(bytes));
		}
		locType.postDecode();
		if (!Static30.aBoolean61 && locType.members) {
			locType.op = null;
		}
		if (locType.breakroutefinding) {
			locType.blockwalk = 0;
			locType.blockrange = false;
		}
		Static179.aClass99_25.put(locType, (long) id);
		return locType;
	}
}
