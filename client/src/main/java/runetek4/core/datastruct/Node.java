package runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ab")
public class Node {

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "J")
	public long nodeId;

	@OriginalMember(owner = "runetek4.client!ab", name = "d", descriptor = "Lclient!ab;")
	public Node prev;

	@OriginalMember(owner = "runetek4.client!ab", name = "l", descriptor = "Lclient!ab;")
	public Node next;

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(I)Z")
	public final boolean hasNext() {
		return this.next != null;
	}

	@OriginalMember(owner = "runetek4.client!ab", name = "b", descriptor = "(I)V")
	public final void remove() {
		if (this.next != null) {
			this.next.prev = this.prev;
			this.prev.next = this.next;
			this.next = null;
			this.prev = null;
		}
	}
}