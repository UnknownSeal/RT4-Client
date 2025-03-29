package com.jagex.runetek4.game.config.enumtype;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.datastruct.*;
import com.jagex.runetek4.node.SecondaryNode;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!ml")
public final class EnumType extends SecondaryNode {

	@OriginalMember(owner = "client!ml", name = "N", descriptor = "I")
	public int valueType;

	@OriginalMember(owner = "client!ml", name = "V", descriptor = "I")
	public int keyType;

	@OriginalMember(owner = "client!ml", name = "X", descriptor = "Lclient!sc;")
	public HashTable table;

	@OriginalMember(owner = "client!ml", name = "bb", descriptor = "Lclient!sc;")
	private HashTable inverseTable;

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
			this.table = new HashTable(IntUtils.bitceil(valuesCount));
			for (@Pc(51) int index = 0; index < valuesCount; index++) {
				@Pc(58) int local58 = packet.g4();
				@Pc(70) Node node;
				if (code == 5) {
					node = new JagStringWrapper(packet.gjstr());
				} else {
					node = new IntWrapper(packet.g4());
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
			@Pc(18) IntWrapper intWrapper = (IntWrapper) this.table.getNode(arg0);
			return intWrapper == null ? this.defaultInt : intWrapper.value;
		}
	}

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(IB)Lclient!na;")
	public JString getValueString(@OriginalArg(0) int arg0) {
		if (this.table == null) {
			return this.defaultString;
		} else {
			@Pc(26) JagStringWrapper jagStringWrapper = (JagStringWrapper) this.table.getNode(arg0);
			return jagStringWrapper == null ? this.defaultString : jagStringWrapper.value;
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
		for (@Pc(38) EnumStringEntry local38 = (EnumStringEntry) this.inverseTable.getNode(arg0.longHashCode()); local38 != null; local38 = (EnumStringEntry) this.inverseTable.nextWithKey()) {
			if (local38.value.strEquals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ml", name = "d", descriptor = "(I)V")
	private void inverseStrings() {
		this.inverseTable = new HashTable(this.table.getSize());
		for (@Pc(22) JagStringWrapper local22 = (JagStringWrapper) this.table.head(); local22 != null; local22 = (JagStringWrapper) this.table.next()) {
			@Pc(36) EnumStringEntry local36 = new EnumStringEntry(local22.value, (int) local22.nodeId);
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
		@Pc(34) IntWrapper local34 = (IntWrapper) this.inverseTable.getNode(arg0);
		return local34 != null;
	}

	@OriginalMember(owner = "client!ml", name = "e", descriptor = "(I)V")
	private void inverseInts() {
		this.inverseTable = new HashTable(this.table.getSize());
		for (@Pc(24) IntWrapper local24 = (IntWrapper) this.table.head(); local24 != null; local24 = (IntWrapper) this.table.next()) {
			@Pc(34) IntWrapper local34 = new IntWrapper((int) local24.nodeId);
			this.inverseTable.put(local34, local24.value);
		}
	}
}