package com.jagex.game;

import com.jagex.core.utils.SystemTimer;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node2;

import com.jagex.core.datastruct.Queue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!da")
public final class DelayedStateChange extends Node2 {

	@OriginalMember(owner = "client!runetek4.client", name = "U", descriptor = "Lclient!sc;")
	public static final IterableHashTable changes = new IterableHashTable(16);

	@OriginalMember(owner = "runetek4.client!la", name = "f", descriptor = "Lclient!ce;")
	public static final Queue delayed = new Queue();

	@OriginalMember(owner = "runetek4.client!rh", name = "e", descriptor = "Lclient!ce;")
	public static final Queue immediate = new Queue();

	@OriginalMember(owner = "client!da", name = "T", descriptor = "I")
	public int tertiaryData;

	@OriginalMember(owner = "client!da", name = "U", descriptor = "I")
	public int primaryData;

	@OriginalMember(owner = "client!da", name = "V", descriptor = "I")
	public int secondaryData;

	@OriginalMember(owner = "client!da", name = "W", descriptor = "Lclient!na;")
	public JString stringArg;

	private static final int TYPE_SETVARC = 1;
	private static final int TYPE_SETVARCSTR = 2;
	private static final int TYPE_IF_SETTEXT = 3;
	private static final int TYPE_IF_SETMODEL = 4;
	private static final int TYPE_IF_SETMODELANIM = 5;
	private static final int TYPE_IF_SETCOLOR = 6;
	private static final int TYPE_IF_SETHIDE = 7;
	private static final int TYPE_IF_SETMODELANGLE = 8;
	private static final int TYPE_IF_SETOBJECT = 9;
	private static final int TYPE_IF_SETMODELOFFSET = 10;
	private static final int TYPE_IF_SETPOSITION = 11;
	private static final int TYPE_IF_SETSCROLLPOS = 12;
	private static final int TYPE_IF_SETMODELROTATION = 13;

	private static final long CLIENT_DELAY_MS = 500L;

	@OriginalMember(owner = "client!da", name = "<init>", descriptor = "(II)V")
	public DelayedStateChange(@OriginalArg(0) int changeType, @OriginalArg(1) int targetId) {
		this.key = (long) changeType << 32 | (long) targetId;
	}

	@OriginalMember(owner = "client!bj", name = "d", descriptor = "(B)V")
	public static void clear() {
		changes.clear();
		delayed.clear();
		immediate.clear();
	}

	@OriginalMember(owner = "runetek4.client!te", name = "a", descriptor = "(III)Lclient!da;")
	public static DelayedStateChange getOrAdd(@OriginalArg(1) int changeType, @OriginalArg(2) int value) {
		@Pc(13) DelayedStateChange change = (DelayedStateChange) changes.get((long) value | (long) changeType << 32);
		if (change == null) {
			change = new DelayedStateChange(changeType, value);
			changes.put(change, change.key);
		}
		return change;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(III)V")
	public static void interfaceSetModelRotation(@OriginalArg(0) int rotationSpeed, @OriginalArg(1) int componentId) {
		@Pc(14) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELROTATION, componentId);
		change.setClientServerUpdate();
		change.secondaryData = rotationSpeed;
	}

	@OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "(Lclient!na;BI)V")
	public static void setVarcstr(@OriginalArg(0) JString text, @OriginalArg(2) int id) {
		@Pc(10) DelayedStateChange change = getOrAdd(TYPE_SETVARCSTR, id);
		change.setClientServerUpdate();
		change.stringArg = text;
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "c", descriptor = "(III)V")
	public static void interfaceSetHide(@OriginalArg(1) int idAndSlot, @OriginalArg(2) int visible) {
		@Pc(14) DelayedStateChange change = getOrAdd(TYPE_IF_SETHIDE, idAndSlot);
		change.setClientServerUpdate();
		change.secondaryData = visible;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "c", descriptor = "(III)V")
	public static void updateVarC(@OriginalArg(0) int varcId, @OriginalArg(1) int value) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_SETVARC, varcId);
		change.setClientServerUpdate();
		change.secondaryData = value;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(IIIBI)V")
	public static void interfaceSetModel(@OriginalArg(0) int objData, @OriginalArg(1) int obj, @OriginalArg(2) int idAndSlot, @OriginalArg(4) int objType) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODEL, idAndSlot);
		change.setClientServerUpdate();
		change.primaryData = objType;
		change.tertiaryData = objData;
		change.secondaryData = obj;
	}

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(III)V")
	public static void interfaceSetModelAnimation(@OriginalArg(0) int componentId, @OriginalArg(1) int seqId) {
		@Pc(14) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELANIM, componentId);
		change.setClientServerUpdate();
		change.secondaryData = seqId;
	}

	@OriginalMember(owner = "runetek4.client!ve", name = "a", descriptor = "(BIIII)V")
	public static void updateView(@OriginalArg(1) int viewX, @OriginalArg(2) int componentId, @OriginalArg(3) int viewY, @OriginalArg(4) int viewZ) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELANGLE, componentId);
		change.setClientServerUpdate();
		change.tertiaryData = viewX;
		change.secondaryData = viewZ;
		change.primaryData = viewY;
	}

	@OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "(IIB)V")
	public static void interfaceSetColor(@OriginalArg(0) int colour, @OriginalArg(1) int componentId) {
		@Pc(4) DelayedStateChange change = getOrAdd(TYPE_IF_SETCOLOR, componentId);
		change.setClientServerUpdate();
		change.secondaryData = colour;
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(IIII)V")
	public static void interfaceSetPosition(@OriginalArg(0) int x, @OriginalArg(1) int componentId, @OriginalArg(3) int y) {
		@Pc(18) DelayedStateChange change = getOrAdd(TYPE_IF_SETPOSITION, componentId);
		change.setClientServerUpdate();
		change.primaryData = y;
		change.secondaryData = x;
	}

	@OriginalMember(owner = "client!gf", name = "a", descriptor = "(Lclient!na;II)V")
	public static void interfaceSetText(@OriginalArg(0) JString text, @OriginalArg(2) int idAndSlot) {
		@Pc(6) DelayedStateChange change = getOrAdd(TYPE_IF_SETTEXT, idAndSlot);
		change.setClientServerUpdate();
		change.stringArg = text;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(IB)V")
	public static void interfaceResetObject(@OriginalArg(0) int idAndSlot) {
		@Pc(14) DelayedStateChange change = getOrAdd(TYPE_IF_SETOBJECT, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "a", descriptor = "(BI)V")
	public static void interfaceResetModelAngle(@OriginalArg(1) int idAndSlot) {
		@Pc(4) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELANGLE, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(II)V")
	public static void interfaceResetModelOffset(@OriginalArg(0) int idAndSlot) {
		@Pc(12) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELOFFSET, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(ZI)V")
	public static void interfaceResetModel(@OriginalArg(1) int id) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODEL, id);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(IB)V")
	public static void interfaceResetScrollPosition(@OriginalArg(0) int idAndSlot) {
		@Pc(12) DelayedStateChange change = getOrAdd(TYPE_IF_SETSCROLLPOS, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static DelayedStateChange removeFirst() {
		@Pc(10) DelayedStateChange change = (DelayedStateChange) immediate.first();
		if (change != null) {
			change.unlink();
			change.unlink2();
			return change;
		}
		do {
			change = (DelayedStateChange) delayed.first();
			if (change == null) {
				return null;
			}
			if (change.getTime() > SystemTimer.safetime()) {
				return null;
			}
			change.unlink();
			change.unlink2();
		} while ((Long.MIN_VALUE & change.key2) == 0L);
		return change;
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(IIIIB)V")
	public static void interfaceSetModelOffset(@OriginalArg(0) int idAndSlot, @OriginalArg(1) int xof2d, @OriginalArg(2) int yof2d, @OriginalArg(3) int zan2d) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELOFFSET, idAndSlot);
		change.setClientServerUpdate();
		change.primaryData = yof2d;
		change.secondaryData = zan2d;
		change.tertiaryData = xof2d;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(II)V")
	public static void interfaceResetTextFont(@OriginalArg(0) int idAndSlot) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETTEXT, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(II)V")
	public static void interfaceResetModelAnim(@OriginalArg(0) int idAndSlot) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_IF_SETMODELANIM, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BIII)V")
	public static void interfaceSetObject(@OriginalArg(1) int idAndSlot, @OriginalArg(2) int objId, @OriginalArg(3) int objCount) {
		@Pc(12) DelayedStateChange change = getOrAdd(TYPE_IF_SETOBJECT, idAndSlot);
		change.setClientServerUpdate();
		change.secondaryData = objCount;
		change.primaryData = objId;
	}

	@OriginalMember(owner = "runetek4.client!si", name = "b", descriptor = "(IIB)V")
	public static void interfaceSetScrollPosition(@OriginalArg(0) int position, @OriginalArg(1) int idAndSlot) {
		@Pc(16) DelayedStateChange change = getOrAdd(TYPE_IF_SETSCROLLPOS, idAndSlot);
		change.setClientServerUpdate();
		change.secondaryData = position;
	}

	@OriginalMember(owner = "runetek4.client!tm", name = "a", descriptor = "(II)V")
	public static void interfaceResetColor(@OriginalArg(0) int idAndSlot) {
		@Pc(16) DelayedStateChange change = getOrAdd(TYPE_IF_SETCOLOR, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(II)V")
	public static void interfaceResetPosition(@OriginalArg(1) int idAndSlot) {
		@Pc(17) DelayedStateChange change = getOrAdd(TYPE_IF_SETPOSITION, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void resetVarc(@OriginalArg(1) int id) {
		@Pc(16) DelayedStateChange changed = getOrAdd(TYPE_SETVARC, id);
		changed.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!ha", name = "b", descriptor = "(II)V")
	public static void resetVarcstr(@OriginalArg(1) int id) {
		@Pc(8) DelayedStateChange change = getOrAdd(TYPE_SETVARCSTR, id);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "runetek4.client!he", name = "c", descriptor = "(II)V")
	public static void interfaceResetHide(@OriginalArg(1) int idAndSlot) {
		@Pc(12) DelayedStateChange change = getOrAdd(TYPE_IF_SETHIDE, idAndSlot);
		change.prepareDelayed();
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Z)V")
	public final void prepareDelayed() {
		this.key2 = SystemTimer.safetime() + CLIENT_DELAY_MS | Long.MIN_VALUE & this.key2;
		delayed.add(this);
	}

	@OriginalMember(owner = "client!da", name = "b", descriptor = "(Z)J")
	public final long getTime() {
		return this.key2 & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "client!da", name = "e", descriptor = "(I)I")
	public final int getType() {
		return (int) (this.key >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "client!da", name = "f", descriptor = "(B)I")
	public final int getId() {
		return (int) this.key;
	}

	@OriginalMember(owner = "client!da", name = "g", descriptor = "(B)V")
	public final void setClientServerUpdate() {
		this.key2 |= Long.MIN_VALUE;
		if (this.getTime() == 0L) {
			immediate.add(this);
		}
	}
}
