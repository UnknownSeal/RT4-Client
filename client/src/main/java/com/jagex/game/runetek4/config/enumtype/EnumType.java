package com.jagex.game.runetek4.config.enumtype;

import com.jagex.core.datastruct.key.IntNode;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.key.JstringNode;
import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node2;
import com.jagex.core.datastruct.Node;
import com.jagex.math.IntMath;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.core.io.Packet;

@OriginalClass("client!ml")
public final class EnumType extends Node2 {

	@OriginalMember(owner = "client!ml", name = "N", descriptor = "I")
	public int valueType;

	@OriginalMember(owner = "client!ml", name = "V", descriptor = "I")
	public int keyType;

	@OriginalMember(owner = "client!ml", name = "X", descriptor = "Lclient!sc;")
	public IterableHashTable table;

	@OriginalMember(owner = "client!ml", name = "bb", descriptor = "Lclient!sc;")
	private IterableHashTable inverseTable;

	@OriginalMember(owner = "client!ml", name = "cb", descriptor = "I")
	private int defaultInt;

	@OriginalMember(owner = "client!gn", name = "v", descriptor = "Lclient!na;")
	public static final JString NULL = JString.parse("null");

	@OriginalMember(owner = "client!ml", name = "Z", descriptor = "Lclient!na;")
	private JString defaultString = NULL;

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(code, packet);
		}
	}

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(ILclient!wa;B)V")
	private void decode(@OriginalArg(0) int code, @OriginalArg(1) Packet packet) {
		if (code == 1) {
			this.keyType = packet.g1();
		} else if (code == 2) {
			this.valueType = packet.g1();
		} else if (code == 3) {
			this.defaultString = packet.gjstr();
		} else if (code == 4) {
			this.defaultInt = packet.g4();
		} else if (code == 5 || code == 6) {
			@Pc(41) int valuesCount = packet.g2();
			this.table = new IterableHashTable(IntMath.bitceil(valuesCount));
			for (@Pc(51) int index = 0; index < valuesCount; index++) {
				@Pc(58) int local58 = packet.g4();
				@Pc(70) Node node;
				if (code == 5) {
					node = new JstringNode(packet.gjstr());
				} else {
					node = new IntNode(packet.g4());
				}
				this.table.put(node, local58);
			}
		}
	}

	@OriginalMember(owner = "client!ml", name = "c", descriptor = "(II)I")
	public int getValueInt(@OriginalArg(1) int arg0) {
		if (this.table == null) {
			return this.defaultInt;
		} else {
			@Pc(18) IntNode intNode = (IntNode) this.table.get(arg0);
			return intNode == null ? this.defaultInt : intNode.value;
		}
	}

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(IB)Lclient!na;")
	public JString getValueString(@OriginalArg(0) int arg0) {
		if (this.table == null) {
			return this.defaultString;
		} else {
			@Pc(26) JstringNode jstringNode = (JstringNode) this.table.get(arg0);
			return jstringNode == null ? this.defaultString : jstringNode.value;
		}
	}

	@OriginalMember(owner = "client!ml", name = "b", descriptor = "(Lclient!na;I)Z")
	public boolean method3086(@OriginalArg(0) JString arg0) {
		if (this.table == null) {
			return false;
		}
		if (this.inverseTable == null) {
			this.inverseStrings();
		}
		for (@Pc(38) EnumStringEntry local38 = (EnumStringEntry) this.inverseTable.get(arg0.longHashCode()); local38 != null; local38 = (EnumStringEntry) this.inverseTable.nextWithKey()) {
			if (local38.value.strEquals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ml", name = "d", descriptor = "(I)V")
	private void inverseStrings() {
		this.inverseTable = new IterableHashTable(this.table.getSize());
		for (@Pc(22) JstringNode local22 = (JstringNode) this.table.head(); local22 != null; local22 = (JstringNode) this.table.next()) {
			@Pc(36) EnumStringEntry local36 = new EnumStringEntry(local22.value, (int) local22.key);
			this.inverseTable.put(local36, local22.value.longHashCode());
		}
	}

	@OriginalMember(owner = "client!ml", name = "d", descriptor = "(II)Z")
	public boolean containsValue(@OriginalArg(1) int arg0) {
		if (this.table == null) {
			return false;
		}
		if (this.inverseTable == null) {
			this.inverseInts();
		}
		@Pc(34) IntNode local34 = (IntNode) this.inverseTable.get(arg0);
		return local34 != null;
	}

	@OriginalMember(owner = "client!ml", name = "e", descriptor = "(I)V")
	private void inverseInts() {
		this.inverseTable = new IterableHashTable(this.table.getSize());
		for (@Pc(24) IntNode local24 = (IntNode) this.table.head(); local24 != null; local24 = (IntNode) this.table.next()) {
			@Pc(34) IntNode local34 = new IntNode((int) local24.key);
			this.inverseTable.put(local34, local24.value);
		}
	}
}