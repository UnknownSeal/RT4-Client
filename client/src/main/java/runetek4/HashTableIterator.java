package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import runetek4.core.datastruct.IterableMap;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!l")
public final class HashTableIterator {

	@OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "Lclient!ab;")
	private Node aClass3_135;

	@OriginalMember(owner = "runetek4.client!l", name = "c", descriptor = "I")
	private int anInt3447 = 0;

	@OriginalMember(owner = "runetek4.client!l", name = "e", descriptor = "Lclient!sc;")
	private final IterableMap aClass133_10;

	@OriginalMember(owner = "runetek4.client!l", name = "<init>", descriptor = "(Lclient!sc;)V")
	public HashTableIterator(@OriginalArg(0) IterableMap arg0) {
		this.aClass133_10 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(I)Lclient!ab;")
	public final Node method2700() {
		@Pc(30) Node local30;
		if (this.anInt3447 > 0 && this.aClass133_10.nodes[this.anInt3447 - 1] != this.aClass3_135) {
			local30 = this.aClass3_135;
			this.aClass3_135 = local30.prev;
			return local30;
		}
		do {
			if (this.aClass133_10.size <= this.anInt3447) {
				return null;
			}
			local30 = this.aClass133_10.nodes[this.anInt3447++].prev;
		} while (local30 == this.aClass133_10.nodes[this.anInt3447 - 1]);
		this.aClass3_135 = local30.prev;
		return local30;
	}

	@OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(B)Lclient!ab;")
	public final Node method2701() {
		this.anInt3447 = 0;
		return this.method2700();
	}
}
