package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!bf")
public final class ServerActiveProperties extends Node {

	@OriginalMember(owner = "runetek4.client!bf", name = "q", descriptor = "I")
	public final int targetParam;

	@OriginalMember(owner = "runetek4.client!bf", name = "x", descriptor = "I")
	public final int events;

	@OriginalMember(owner = "runetek4.client!bf", name = "<init>", descriptor = "(II)V")
	public ServerActiveProperties(@OriginalArg(0) int events, @OriginalArg(1) int targetParam) {
		this.targetParam = targetParam;
		this.events = events;
	}

	@OriginalMember(owner = "runetek4.client!qc", name = "a", descriptor = "(BI)I")
	public static int getTargetMask(@OriginalArg(1) int events) {
		return events >> 11 & 0x7F;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "a", descriptor = "(IB)Z")
	public boolean isButtonEnabled(@OriginalArg(0) int button) {
		return (this.events >> button + 1 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "d", descriptor = "(I)Z")
	public boolean isObjReplaceEnabled() {
		return (this.events >> 29 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "e", descriptor = "(B)I")
	public int getDragDepth() {
		return this.events >> 18 & 0x7;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "e", descriptor = "(I)Z")
	public boolean isResumePauseButtonEnabled() {
		return (this.events & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "f", descriptor = "(I)Z")
	public boolean isObjUseEnabled() {
		return (this.events >> 31 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "g", descriptor = "(I)Z")
	public boolean isUseTarget() {
		return (this.events >> 22 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "a", descriptor = "(Z)Z")
	public boolean isDragTarget() {
		return (this.events >> 21 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "h", descriptor = "(I)Z")
	public boolean isObjOpsEnabled() {
		return (this.events >> 30 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "f", descriptor = "(B)Z")
	public boolean isObjSwapEnabled() {
		return (this.events >> 28 & 0x1) != 0;
	}

	@OriginalMember(owner = "runetek4.client!bf", name = "i", descriptor = "(I)I")
	public int getTargetMask() {
		return getTargetMask(this.events);
	}
}
