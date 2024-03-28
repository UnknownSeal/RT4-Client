package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ce")
public final class SecondaryLinkedList {

	@OriginalMember(owner = "runetek4.client!ce", name = "n", descriptor = "Lclient!rg;")
	private SecondaryNode aClass3_Sub2_22;

	@OriginalMember(owner = "runetek4.client!ce", name = "l", descriptor = "Lclient!rg;")
	private final SecondaryNode aClass3_Sub2_21 = new SecondaryNode();

	@OriginalMember(owner = "runetek4.client!ce", name = "<init>", descriptor = "()V")
	public SecondaryLinkedList() {
		this.aClass3_Sub2_21.secondaryPrev = this.aClass3_Sub2_21;
		this.aClass3_Sub2_21.secondaryNext = this.aClass3_Sub2_21;
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "a", descriptor = "(I)I")
	public final int method793() {
		@Pc(3) int local3 = 0;
		@Pc(7) SecondaryNode local7 = this.aClass3_Sub2_21.secondaryPrev;
		while (local7 != this.aClass3_Sub2_21) {
			local7 = local7.secondaryPrev;
			local3++;
		}
		return local3;
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "b", descriptor = "(B)Lclient!rg;")
	public final SecondaryNode method795() {
		@Pc(3) SecondaryNode local3 = this.aClass3_Sub2_21.secondaryPrev;
		if (this.aClass3_Sub2_21 == local3) {
			this.aClass3_Sub2_22 = null;
			return null;
		} else {
			this.aClass3_Sub2_22 = local3.secondaryPrev;
			return local3;
		}
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "b", descriptor = "(I)Lclient!rg;")
	public final SecondaryNode method796() {
		@Pc(7) SecondaryNode local7 = this.aClass3_Sub2_21.secondaryPrev;
		if (local7 == this.aClass3_Sub2_21) {
			return null;
		} else {
			local7.secondaryRemove();
			return local7;
		}
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "c", descriptor = "(I)Lclient!rg;")
	public final SecondaryNode method797() {
		@Pc(2) SecondaryNode local2 = this.aClass3_Sub2_22;
		if (local2 == this.aClass3_Sub2_21) {
			this.aClass3_Sub2_22 = null;
			return null;
		} else {
			this.aClass3_Sub2_22 = local2.secondaryPrev;
			return local2;
		}
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "a", descriptor = "(Lclient!rg;B)V")
	public final void method798(@OriginalArg(0) SecondaryNode arg0) {
		if (arg0.secondaryNext != null) {
			arg0.secondaryRemove();
		}
		arg0.secondaryNext = this.aClass3_Sub2_21.secondaryNext;
		arg0.secondaryPrev = this.aClass3_Sub2_21;
		arg0.secondaryNext.secondaryPrev = arg0;
		arg0.secondaryPrev.secondaryNext = arg0;
	}

	@OriginalMember(owner = "runetek4.client!ce", name = "d", descriptor = "(I)V")
	public final void method802() {
		while (true) {
			@Pc(15) SecondaryNode local15 = this.aClass3_Sub2_21.secondaryPrev;
			if (this.aClass3_Sub2_21 == local15) {
				this.aClass3_Sub2_22 = null;
				return;
			}
			local15.secondaryRemove();
		}
	}
}
