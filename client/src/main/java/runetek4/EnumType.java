package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.datastruct.IterableMap;
import runetek4.core.datastruct.Node;
import runetek4.core.io.Packet;

@OriginalClass("runetek4.client!ml")
public final class EnumType extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!ml", name = "N", descriptor = "I")
	public int anInt3950;

	@OriginalMember(owner = "runetek4.client!ml", name = "V", descriptor = "I")
	public int anInt3957;

	@OriginalMember(owner = "runetek4.client!ml", name = "X", descriptor = "Lclient!sc;")
	public IterableMap aClass133_16;

	@OriginalMember(owner = "runetek4.client!ml", name = "bb", descriptor = "Lclient!sc;")
	private IterableMap aClass133_17;

	@OriginalMember(owner = "runetek4.client!ml", name = "cb", descriptor = "I")
	private int anInt3960;

	@OriginalMember(owner = "runetek4.client!ml", name = "Z", descriptor = "Lclient!na;")
	private JagString aClass100_766 = Static87.aClass100_494;

	@OriginalMember(owner = "runetek4.client!ml", name = "a", descriptor = "(ILclient!wa;B)V")
	private void method3084(@OriginalArg(0) int arg0, @OriginalArg(1) Packet arg1) {
		if (arg0 == 1) {
			this.anInt3957 = arg1.g1();
		} else if (arg0 == 2) {
			this.anInt3950 = arg1.g1();
		} else if (arg0 == 3) {
			this.aClass100_766 = arg1.gjstr();
		} else if (arg0 == 4) {
			this.anInt3960 = arg1.g4();
		} else if (arg0 == 5 || arg0 == 6) {
			@Pc(41) int local41 = arg1.g2();
			this.aClass133_16 = new IterableMap(Static165.method3164(local41));
			for (@Pc(51) int local51 = 0; local51 < local41; local51++) {
				@Pc(58) int local58 = arg1.g4();
				@Pc(70) Node local70;
				if (arg0 == 5) {
					local70 = new StringNode(arg1.gjstr());
				} else {
					local70 = new IntNode(arg1.g4());
				}
				this.aClass133_16.pushNode(local70, (long) local58);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "a", descriptor = "(IB)Lclient!na;")
	public final JagString method3085(@OriginalArg(0) int arg0) {
		if (this.aClass133_16 == null) {
			return this.aClass100_766;
		} else {
			@Pc(26) StringNode local26 = (StringNode) this.aClass133_16.getNode((long) arg0);
			return local26 == null ? this.aClass100_766 : local26.aClass100_980;
		}
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "b", descriptor = "(Lclient!na;I)Z")
	public final boolean method3086(@OriginalArg(0) JagString arg0) {
		if (this.aClass133_16 == null) {
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

	@OriginalMember(owner = "runetek4.client!ml", name = "d", descriptor = "(I)V")
	private void method3087() {
		this.aClass133_17 = new IterableMap(this.aClass133_16.getSize());
		for (@Pc(22) StringNode local22 = (StringNode) this.aClass133_16.peekFront(); local22 != null; local22 = (StringNode) this.aClass133_16.prev()) {
			@Pc(36) Class3_Sub13 local36 = new Class3_Sub13(local22.aClass100_980, (int) local22.nodeId);
			this.aClass133_17.pushNode(local36, local22.aClass100_980.method3118());
		}
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "c", descriptor = "(II)I")
	public final int method3089(@OriginalArg(1) int arg0) {
		if (this.aClass133_16 == null) {
			return this.anInt3960;
		} else {
			@Pc(18) IntNode local18 = (IntNode) this.aClass133_16.getNode((long) arg0);
			return local18 == null ? this.anInt3960 : local18.anInt3141;
		}
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "d", descriptor = "(II)Z")
	public final boolean method3090(@OriginalArg(1) int arg0) {
		if (this.aClass133_16 == null) {
			return false;
		}
		if (this.aClass133_17 == null) {
			this.method3091();
		}
		@Pc(34) IntNode local34 = (IntNode) this.aClass133_17.getNode((long) arg0);
		return local34 != null;
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "e", descriptor = "(I)V")
	private void method3091() {
		this.aClass133_17 = new IterableMap(this.aClass133_16.getSize());
		for (@Pc(24) IntNode local24 = (IntNode) this.aClass133_16.peekFront(); local24 != null; local24 = (IntNode) this.aClass133_16.prev()) {
			@Pc(34) IntNode local34 = new IntNode((int) local24.nodeId);
			this.aClass133_17.pushNode(local34, (long) local24.anInt3141);
		}
	}

	@OriginalMember(owner = "runetek4.client!ml", name = "a", descriptor = "(Lclient!wa;I)V")
	public final void method3094(@OriginalArg(0) Packet arg0) {
		while (true) {
			@Pc(9) int local9 = arg0.g1();
			if (local9 == 0) {
				return;
			}
			this.method3084(local9, arg0);
		}
	}
}
