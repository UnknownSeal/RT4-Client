package runetek4.game.config.enumtype;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.*;
import runetek4.core.datastruct.IntWrapper;
import runetek4.core.datastruct.IterableMap;
import runetek4.core.datastruct.JagStringWrapper;
import runetek4.core.datastruct.Node;
import runetek4.core.io.Packet;

@OriginalClass("client!ml")
public final class EnumType extends SecondaryNode {

	@OriginalMember(owner = "client!ml", name = "N", descriptor = "I")
	public int outputtype;

	@OriginalMember(owner = "client!ml", name = "V", descriptor = "I")
	public int inputtype;

	@OriginalMember(owner = "client!ml", name = "X", descriptor = "Lclient!sc;")
	public IterableMap values;

	@OriginalMember(owner = "client!ml", name = "bb", descriptor = "Lclient!sc;")
	private IterableMap aClass133_17;

	@OriginalMember(owner = "client!ml", name = "cb", descriptor = "I")
	private int defaultInt;

	@OriginalMember(owner = "client!ml", name = "Z", descriptor = "Lclient!na;")
	private JagString defaultString = Static87.aClass100_494;

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
			this.inputtype = packet.g1();
		} else if (code == 2) {
			this.outputtype = packet.g1();
		} else if (code == 3) {
			this.defaultString = packet.gjstr();
		} else if (code == 4) {
			this.defaultInt = packet.g4();
		} else if (code == 5 || code == 6) {
			@Pc(41) int valuesCount = packet.g2();
			this.values = new IterableMap(Static165.bitceil(valuesCount));
			for (@Pc(51) int index = 0; index < valuesCount; index++) {
				@Pc(58) int local58 = packet.g4();
				@Pc(70) Node node;
				if (code == 5) {
					node = new JagStringWrapper(packet.gjstr());
				} else {
					node = new IntWrapper(packet.g4());
				}
				this.values.pushNode(node, local58);
			}
		}
	}

	@OriginalMember(owner = "client!ml", name = "c", descriptor = "(II)I")
	public int getValueInt(@OriginalArg(1) int arg0) {
		if (this.values == null) {
			return this.defaultInt;
		} else {
			@Pc(18) IntWrapper intWrapper = (IntWrapper) this.values.getNode(arg0);
			return intWrapper == null ? this.defaultInt : intWrapper.value;
		}
	}

	@OriginalMember(owner = "client!ml", name = "a", descriptor = "(IB)Lclient!na;")
	public JagString getValueString(@OriginalArg(0) int arg0) {
		if (this.values == null) {
			return this.defaultString;
		} else {
			@Pc(26) JagStringWrapper jagStringWrapper = (JagStringWrapper) this.values.getNode(arg0);
			return jagStringWrapper == null ? this.defaultString : jagStringWrapper.value;
		}
	}

	@OriginalMember(owner = "client!ml", name = "b", descriptor = "(Lclient!na;I)Z")
	public boolean method3086(@OriginalArg(0) JagString arg0) {
		if (this.values == null) {
			return false;
		}
		if (this.aClass133_17 == null) {
			this.method3087();
		}
		for (@Pc(38) Class3_Sub13 local38 = (Class3_Sub13) this.aClass133_17.getNode(arg0.method3118()); local38 != null; local38 = (Class3_Sub13) this.aClass133_17.next()) {
			if (local38.aClass100_503.method3108(arg0)) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ml", name = "d", descriptor = "(I)V")
	private void method3087() {
		this.aClass133_17 = new IterableMap(this.values.getSize());
		for (@Pc(22) JagStringWrapper local22 = (JagStringWrapper) this.values.peekFront(); local22 != null; local22 = (JagStringWrapper) this.values.prev()) {
			@Pc(36) Class3_Sub13 local36 = new Class3_Sub13(local22.value, (int) local22.nodeId);
			this.aClass133_17.pushNode(local36, local22.value.method3118());
		}
	}

	@OriginalMember(owner = "client!ml", name = "d", descriptor = "(II)Z")
	public boolean method3090(@OriginalArg(1) int arg0) {
		if (this.values == null) {
			return false;
		}
		if (this.aClass133_17 == null) {
			this.method3091();
		}
		@Pc(34) IntWrapper local34 = (IntWrapper) this.aClass133_17.getNode(arg0);
		return local34 != null;
	}

	@OriginalMember(owner = "client!ml", name = "e", descriptor = "(I)V")
	private void method3091() {
		this.aClass133_17 = new IterableMap(this.values.getSize());
		for (@Pc(24) IntWrapper local24 = (IntWrapper) this.values.peekFront(); local24 != null; local24 = (IntWrapper) this.values.prev()) {
			@Pc(34) IntWrapper local34 = new IntWrapper((int) local24.nodeId);
			this.aClass133_17.pushNode(local34, local24.value);
		}
	}
}