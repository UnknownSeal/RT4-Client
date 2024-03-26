package runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!sc")
public final class IterableMap {

	@OriginalMember(owner = "runetek4.client!sc", name = "q", descriptor = "Lclient!ab;")
	private Node next;

	@OriginalMember(owner = "runetek4.client!sc", name = "u", descriptor = "J")
	private long currentNodeId;

	@OriginalMember(owner = "runetek4.client!sc", name = "C", descriptor = "Lclient!ab;")
	private Node prev;

	@OriginalMember(owner = "runetek4.client!sc", name = "F", descriptor = "I")
	private int currentNodeIndex = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "c", descriptor = "[Lclient!ab;")
	public final Node[] nodes;

	@OriginalMember(owner = "runetek4.client!sc", name = "h", descriptor = "I")
	public final int size;

	@OriginalMember(owner = "runetek4.client!sc", name = "<init>", descriptor = "(I)V")
	public IterableMap(@OriginalArg(0) int size) {
		this.size = size;
		this.nodes = new Node[size];
		for (@Pc(13) int index = 0; index < size; index++) {
			@Pc(25) Node node = this.nodes[index] = new Node();
			node.next = node;
			node.prev = node;
		}
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(I)V")
	public void removeAll() {
		for (@Pc(5) int index = 0; index < this.size; index++) {
			@Pc(14) Node node = this.nodes[index];
			while (true) {
				@Pc(17) Node next = node.prev;
				if (node == next) {
					break;
				}
				next.remove();
			}
		}
		this.next = null;
		this.prev = null;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "c", descriptor = "(I)Lclient!ab;")
	public Node peekFront() {
		this.currentNodeIndex = 0;
		return this.prev();
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "d", descriptor = "(I)Lclient!ab;")
	public Node prev() {
		if (this.currentNodeIndex > 0 && this.prev != this.nodes[this.currentNodeIndex - 1]) {
			Node local24 = this.prev;
			this.prev = local24.prev;
			return local24;
		}
		@Pc(24) Node local24;
		do {
			if (this.currentNodeIndex >= this.size) {
				return null;
			}
			local24 = this.nodes[this.currentNodeIndex++].prev;
		} while (this.nodes[this.currentNodeIndex - 1] == local24);
		this.prev = local24.prev;
		return local24;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(ILclient!ab;J)V")
	public void pushNode(@OriginalArg(1) Node node, @OriginalArg(2) long id) {
		if (node.next != null) {
			node.remove();
		}
		@Pc(21) Node local21 = this.nodes[(int) (id & (long) (this.size - 1))];
		node.prev = local21;
		node.nodeId = id;
		node.next = local21.next;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(JI)Lclient!ab;")
	public Node getNode(@OriginalArg(0) long id) {
		this.currentNodeId = id;
		@Pc(24) Node node = this.nodes[(int) (id & (long) (this.size - 1))];
		for (this.next = node.prev; this.next != node; this.next = this.next.prev) {
			if (id == this.next.nodeId) {
				@Pc(46) Node local46 = this.next;
				this.next = this.next.prev;
				return local46;
			}
		}
		this.next = null;
		return null;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "e", descriptor = "(I)I")
	public int length() {
		@Pc(15) int count = 0;
		for (@Pc(17) int index = 0; index < this.size; index++) {
			@Pc(26) Node local26 = this.nodes[index];
			@Pc(29) Node local29 = local26.prev;
			while (local29 != local26) {
				local29 = local29.prev;
				count++;
			}
		}
		return count;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "([Lclient!ab;I)I")
	public int addNodes(@OriginalArg(0) Node[] nodes) {
		@Pc(13) int count = 0;
		for (@Pc(15) int index = 0; index < this.size; index++) {
			@Pc(24) Node node = this.nodes[index];
			for (@Pc(27) Node local27 = node.prev; local27 != node; local27 = local27.prev) {
				nodes[count++] = local27;
			}
		}
		return count;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "f", descriptor = "(I)Lclient!ab;")
	public Node next() {
		if (this.next == null) {
			return null;
		}
		@Pc(23) Node node = this.nodes[(int) (this.currentNodeId & (long) (this.size - 1))];
		while (node != this.next) {
			if (this.next.nodeId == this.currentNodeId) {
				@Pc(45) Node local45 = this.next;
				this.next = this.next.prev;
				return local45;
			}
			this.next = this.next.prev;
		}
		this.next = null;
		return null;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "g", descriptor = "(I)I")
	public int getSize() {
		return this.size;
	}
}
