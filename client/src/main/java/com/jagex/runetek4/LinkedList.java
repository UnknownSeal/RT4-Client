package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ih")
public final class LinkedList {

	@OriginalMember(owner = "runetek4.client!ih", name = "p", descriptor = "Lclient!ab;")
	private Node aClass3_110;

	@OriginalMember(owner = "runetek4.client!ih", name = "m", descriptor = "Lclient!ab;")
	public final Node aClass3_109 = new Node();

	@OriginalMember(owner = "runetek4.client!ih", name = "<init>", descriptor = "()V")
	public LinkedList() {
		this.aClass3_109.next = this.aClass3_109;
		this.aClass3_109.prev = this.aClass3_109;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(I)V")
	public final void clear() {
		while (true) {
			@Pc(5) Node local5 = this.aClass3_109.prev;
			if (local5 == this.aClass3_109) {
				this.aClass3_110 = null;
				return;
			}
			local5.remove();
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "b", descriptor = "(I)Lclient!ab;")
	public final Node method2279() {
		@Pc(7) Node local7 = this.aClass3_109.next;
		if (this.aClass3_109 == local7) {
			this.aClass3_110 = null;
			return null;
		} else {
			this.aClass3_110 = local7.next;
			return local7;
		}
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(ZLclient!ab;)V")
	public void addTail(@OriginalArg(1) Node arg0) {
		if (arg0.next != null) {
			arg0.remove();
		}
		arg0.prev = this.aClass3_109;
		arg0.next = this.aClass3_109.next;
		arg0.next.prev = arg0;
		arg0.prev.next = arg0;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(ILclient!ab;)V")
	public final void method2283(@OriginalArg(1) Node arg0) {
		if (arg0.next != null) {
			arg0.remove();
		}
		arg0.prev = this.aClass3_109.prev;
		arg0.next = this.aClass3_109;
		arg0.next.prev = arg0;
		arg0.prev.next = arg0;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "d", descriptor = "(I)Lclient!ab;")
	public final Node method2286() {
		@Pc(13) Node local13 = this.aClass3_110;
		if (this.aClass3_109 == local13) {
			this.aClass3_110 = null;
			return null;
		} else {
			this.aClass3_110 = local13.next;
			return local13;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(B)Lclient!ab;")
	public final Node method2287() {
		@Pc(3) Node local3 = this.aClass3_109.prev;
		if (this.aClass3_109 == local3) {
			return null;
		} else {
			local3.remove();
			return local3;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "e", descriptor = "(I)Lclient!ab;")
	public final Node next() {
		@Pc(12) Node local12 = this.aClass3_110;
		if (local12 == this.aClass3_109) {
			this.aClass3_110 = null;
			return null;
		} else {
			this.aClass3_110 = local12.prev;
			return local12;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "f", descriptor = "(I)Lclient!ab;")
	public final Node head() {
		@Pc(3) Node local3 = this.aClass3_109.prev;
		if (this.aClass3_109 == local3) {
			this.aClass3_110 = null;
			return null;
		} else {
			this.aClass3_110 = local3.prev;
			return local3;
		}
	}
}
