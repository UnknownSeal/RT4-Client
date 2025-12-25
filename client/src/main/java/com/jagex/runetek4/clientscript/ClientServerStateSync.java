package com.jagex.runetek4.clientscript;

import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.core.node.SecondaryNode;

import com.jagex.runetek4.core.node.SecondaryLinkedList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!da")
public final class ClientServerStateSync extends SecondaryNode {

	@OriginalMember(owner = "client!runetek4.client", name = "U", descriptor = "Lclient!sc;")
	public static final HashTable pendingChanges = new HashTable(16);

	@OriginalMember(owner = "runetek4.client!la", name = "f", descriptor = "Lclient!ce;")
	public static final SecondaryLinkedList clientQueue = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!rh", name = "e", descriptor = "Lclient!ce;")
	public static final SecondaryLinkedList serverQueue = new SecondaryLinkedList();

	@OriginalMember(owner = "client!da", name = "T", descriptor = "I")
	public int intArg2;

	@OriginalMember(owner = "client!da", name = "U", descriptor = "I")
	public int intArg3;

	@OriginalMember(owner = "client!da", name = "V", descriptor = "I")
	public int intArg1;

	@OriginalMember(owner = "client!da", name = "W", descriptor = "Lclient!na;")
	public JString stringArg;

	private static final int TYPE_VARC = 1;
	private static final int TYPE_VARCSTR = 2;
	private static final int TYPE_COMPONENT_TEXT = 3;
	private static final int TYPE_COMPONENT_MODEL = 4;
	private static final int TYPE_COMPONENT_MODEL_ANIM = 5;
	private static final int TYPE_COMPONENT_COLOR = 6;
	private static final int TYPE_COMPONENT_HIDDEN = 7;
	private static final int TYPE_COMPONENT_MODEL_ANGLE = 8;
	private static final int TYPE_COMPONENT_OBJECT = 9;
	private static final int TYPE_COMPONENT_MODEL_OFFSET = 10;
	private static final int TYPE_COMPONENT_POSITION = 11;
	private static final int TYPE_COMPONENT_SCROLL = 12;
	private static final int TYPE_COMPONENT_MODEL_ROTATION = 13;

	private static final long CLIENT_DELAY_MS = 500L;

	@OriginalMember(owner = "client!da", name = "<init>", descriptor = "(II)V")
	public ClientServerStateSync(@OriginalArg(0) int changeType, @OriginalArg(1) int targetId) {
		this.nodeId = (long) changeType << 32 | (long) targetId;
	}

	@OriginalMember(owner = "client!bj", name = "d", descriptor = "(B)V")
	public static void clear() {
		pendingChanges.clear();
		clientQueue.clear();
		serverQueue.clear();
	}

	@OriginalMember(owner = "runetek4.client!te", name = "a", descriptor = "(III)Lclient!da;")
	public static ClientServerStateSync create(@OriginalArg(1) int changeType, @OriginalArg(2) int targetId) {
		@Pc(13) ClientServerStateSync change = (ClientServerStateSync) pendingChanges.get((long) targetId | (long) changeType << 32);
		if (change == null) {
			change = new ClientServerStateSync(changeType, targetId);
			pendingChanges.put(change, change.nodeId);
		}
		return change;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(III)V")
	public static void setComponentModelRotationSpeedServer(@OriginalArg(0) int rotationSpeed, @OriginalArg(1) int componentId) {
		@Pc(14) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_ROTATION, componentId);
		change.pushServer();
		change.intArg1 = rotationSpeed;
	}

	@OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "(Lclient!na;BI)V")
	public static void setComponentTextServer(@OriginalArg(0) JString text, @OriginalArg(2) int id) {
		@Pc(10) ClientServerStateSync change = create(TYPE_VARCSTR, id);
		change.pushServer();
		change.stringArg = text;
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "c", descriptor = "(III)V")
	public static void setComponentHiddenServer(@OriginalArg(1) int componentId, @OriginalArg(2) int hidden) {
		@Pc(14) ClientServerStateSync change = create(TYPE_COMPONENT_HIDDEN, componentId);
		change.pushServer();
		change.intArg1 = hidden;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "c", descriptor = "(III)V")
	public static void updateVarC(@OriginalArg(0) int varcId, @OriginalArg(1) int value) {
		@Pc(8) ClientServerStateSync change = create(TYPE_VARC, varcId);
		change.pushServer();
		change.intArg1 = value;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(IIIBI)V")
	public static void updateComponentModel(@OriginalArg(0) int modelType, @OriginalArg(1) int modelId, @OriginalArg(2) int componentId, @OriginalArg(4) int zoom) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL, componentId);
		change.pushServer();
		change.intArg3 = zoom;
		change.intArg2 = modelType;
		change.intArg1 = modelId;
	}

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(III)V")
	public static void setComponentModelAnimServer(@OriginalArg(0) int componentId, @OriginalArg(1) int seqId) {
		@Pc(14) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_ANIM, componentId);
		change.pushServer();
		change.intArg1 = seqId;
	}

	@OriginalMember(owner = "runetek4.client!ve", name = "a", descriptor = "(BIIII)V")
	public static void updateView(@OriginalArg(1) int viewX, @OriginalArg(2) int componentId, @OriginalArg(3) int viewY, @OriginalArg(4) int viewZ) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_ANGLE, componentId);
		change.pushServer();
		change.intArg2 = viewX;
		change.intArg1 = viewZ;
		change.intArg3 = viewY;
	}

	@OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "(IIB)V")
	public static void setColor(@OriginalArg(0) int color, @OriginalArg(1) int componentId) {
		@Pc(4) ClientServerStateSync change = create(TYPE_COMPONENT_COLOR, componentId);
		change.pushServer();
		change.intArg1 = color;
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(IIII)V")
	public static void setComponentPositionServer(@OriginalArg(0) int x, @OriginalArg(1) int componentId, @OriginalArg(3) int y) {
		@Pc(18) ClientServerStateSync change = create(TYPE_COMPONENT_POSITION, componentId);
		change.pushServer();
		change.intArg3 = y;
		change.intArg1 = x;
	}

	@OriginalMember(owner = "client!gf", name = "a", descriptor = "(Lclient!na;II)V")
	public static void setComponentTextServer2(@OriginalArg(0) JString text, @OriginalArg(2) int componentId) {
		@Pc(6) ClientServerStateSync change = create(TYPE_COMPONENT_TEXT, componentId);
		change.pushServer();
		change.stringArg = text;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(IB)V")
	public static void setComponentObjClient(@OriginalArg(0) int componentId) {
		@Pc(14) ClientServerStateSync change = create(TYPE_COMPONENT_OBJECT, componentId);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "a", descriptor = "(BI)V")
	public static void setComponentModelAngleClient(@OriginalArg(1) int componentId) {
		@Pc(4) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_ANGLE, componentId);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(II)V")
	public static void setComponentModelOffsetClient(@OriginalArg(0) int componentId) {
		@Pc(12) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_OFFSET, componentId);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(ZI)V")
	public static void setComponentModelClient(@OriginalArg(1) int id) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(IB)V")
	public static void setComponentScrollPositionClient(@OriginalArg(0) int id) {
		@Pc(12) ClientServerStateSync change = create(TYPE_COMPONENT_SCROLL, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static ClientServerStateSync poll() {
		@Pc(10) ClientServerStateSync change = (ClientServerStateSync) serverQueue.head();
		if (change != null) {
			change.unlink();
			change.unlinkCachedNode();
			return change;
		}
		do {
			change = (ClientServerStateSync) clientQueue.head();
			if (change == null) {
				return null;
			}
			if (change.getTime() > MonotonicTime.currentTimeMillis()) {
				return null;
			}
			change.unlink();
			change.unlinkCachedNode();
		} while ((Long.MIN_VALUE & change.secondaryKey) == 0L);
		return change;
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(IIIIB)V")
	public static void setComponentModelOffsetServer(@OriginalArg(0) int componentId, @OriginalArg(1) int offsetX, @OriginalArg(2) int offsetY, @OriginalArg(3) int offsetZ) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_OFFSET, componentId);
		change.pushServer();
		change.intArg3 = offsetY;
		change.intArg1 = offsetZ;
		change.intArg2 = offsetX;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(II)V")
	public static void setComponentTextClient(@OriginalArg(0) int id) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_TEXT, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(II)V")
	public static void setComponentModelAnimClient(@OriginalArg(0) int id) {
		@Pc(8) ClientServerStateSync change = create(TYPE_COMPONENT_MODEL_ANIM, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BIII)V")
	public static void setComponentObjectServer(@OriginalArg(1) int componentId, @OriginalArg(2) int objId, @OriginalArg(3) int objCount) {
		@Pc(12) ClientServerStateSync change = create(TYPE_COMPONENT_OBJECT, componentId);
		change.pushServer();
		change.intArg1 = objCount;
		change.intArg3 = objId;
	}

	@OriginalMember(owner = "runetek4.client!si", name = "b", descriptor = "(IIB)V")
	public static void setComponentScrollPositionServer(@OriginalArg(0) int scrollAmount, @OriginalArg(1) int componentId) {
		@Pc(16) ClientServerStateSync change = create(TYPE_COMPONENT_SCROLL, componentId);
		change.pushServer();
		change.intArg1 = scrollAmount;
	}

	@OriginalMember(owner = "runetek4.client!tm", name = "a", descriptor = "(II)V")
	public static void setComponentColorClient(@OriginalArg(0) int id) {
		@Pc(16) ClientServerStateSync change = create(TYPE_COMPONENT_COLOR, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(II)V")
	public static void setComponentPositionClient(@OriginalArg(1) int id) {
		@Pc(17) ClientServerStateSync change = create(TYPE_COMPONENT_POSITION, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void setVarcClient(@OriginalArg(1) int varcId) {
		@Pc(16) ClientServerStateSync changed = create(TYPE_VARC, varcId);
		changed.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ha", name = "b", descriptor = "(II)V")
	public static void setVarcstrClient(@OriginalArg(1) int id) {
		@Pc(8) ClientServerStateSync change = create(TYPE_VARCSTR, id);
		change.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!he", name = "c", descriptor = "(II)V")
	public static void setComponentHiddenClient(@OriginalArg(1) int id) {
		@Pc(12) ClientServerStateSync change = create(TYPE_COMPONENT_HIDDEN, id);
		change.pushClient();
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Z)V")
	public final void pushClient() {
		this.secondaryKey = MonotonicTime.currentTimeMillis() + CLIENT_DELAY_MS | Long.MIN_VALUE & this.secondaryKey;
		clientQueue.addTail(this);
	}

	@OriginalMember(owner = "client!da", name = "b", descriptor = "(Z)J")
	public final long getTime() {
		return this.secondaryKey & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "client!da", name = "e", descriptor = "(I)I")
	public final int getType() {
		return (int) (this.nodeId >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "client!da", name = "f", descriptor = "(B)I")
	public final int getId() {
		return (int) this.nodeId;
	}

	@OriginalMember(owner = "client!da", name = "g", descriptor = "(B)V")
	public final void pushServer() {
		this.secondaryKey |= Long.MIN_VALUE;
		if (this.getTime() == 0L) {
			serverQueue.addTail(this);
		}
	}
}
