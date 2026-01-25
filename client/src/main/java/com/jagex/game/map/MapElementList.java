package com.jagex.game.map;

import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!se")
public final class MapElementList {

	@OriginalMember(owner = "client!se", name = "i", descriptor = "I")
	public final int anInt5074;

	@OriginalMember(owner = "client!se", name = "j", descriptor = "[Lclient!na;")
	public final JString[] text;

	@OriginalMember(owner = "client!se", name = "d", descriptor = "[S")
	public final short[] aShortArray72;

	@OriginalMember(owner = "client!se", name = "n", descriptor = "[I")
	public final int[] anIntArray444;

	@OriginalMember(owner = "client!se", name = "u", descriptor = "[B")
	public final byte[] aByteArray69;

	@OriginalMember(owner = "client!se", name = "q", descriptor = "[S")
	public final short[] aShortArray73;

	@OriginalMember(owner = "client!se", name = "<init>", descriptor = "(I)V")
	public MapElementList(@OriginalArg(0) int arg0) {
		this.anInt5074 = arg0;
		this.text = new JString[this.anInt5074];
		this.aShortArray72 = new short[this.anInt5074];
		this.anIntArray444 = new int[this.anInt5074];
		this.aByteArray69 = new byte[this.anInt5074];
		this.aShortArray73 = new short[this.anInt5074];
	}

	@OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(ILclient!na;Lclient!ve;)Lclient!se;")
	public static MapElementList create(@OriginalArg(1) JString groupName, @OriginalArg(2) Js5 js5Archive) {
		@Pc(10) int groupId = js5Archive.getGroupId(groupName);
		if (groupId == -1) {
			return new MapElementList(0);
		}
		@Pc(29) int[] fileIds = js5Archive.getFileIds(groupId);
		@Pc(35) MapElementList elementList = new MapElementList(fileIds.length);
		for (@Pc(37) int elementIndex = 0; elementIndex < elementList.anInt5074; elementIndex++) {
			@Pc(56) Packet packet = new Packet(js5Archive.getfile(groupId, fileIds[elementIndex]));
			elementList.text[elementIndex] = packet.gjstr();
			elementList.aByteArray69[elementIndex] = packet.g1s();
			elementList.aShortArray73[elementIndex] = (short) packet.g2();
			elementList.aShortArray72[elementIndex] = (short) packet.g2();
			elementList.anIntArray444[elementIndex] = packet.g4();
		}
		return elementList;
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(IB)Z")
	public final boolean hasFlag8Set(@OriginalArg(0) int elementIndex) {
		return (this.aByteArray69[elementIndex] & 0x8) != 0;
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(II)Z")
	public final boolean hasFlag4Set(@OriginalArg(0) int elementIndex) {
		return (this.aByteArray69[elementIndex] & 0x4) != 0;
	}

	@OriginalMember(owner = "client!se", name = "b", descriptor = "(II)I")
	public final int getLowerTwoBits(@OriginalArg(0) int elementIndex) {
		return this.aByteArray69[elementIndex] & 0x3;
	}

	@OriginalMember(owner = "client!se", name = "c", descriptor = "(II)Z")
	public final boolean isFlag16Clear(@OriginalArg(0) int elementIndex) {
		return (this.aByteArray69[elementIndex] & 0x10) == 0;
	}
}
