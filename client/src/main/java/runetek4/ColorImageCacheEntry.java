package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!qi")
public final class ColorImageCacheEntry extends Node {

	@OriginalMember(owner = "runetek4.client!qi", name = "s", descriptor = "I")
	public final int anInt4761;

	@OriginalMember(owner = "runetek4.client!qi", name = "q", descriptor = "I")
	public final int anInt4759;

	@OriginalMember(owner = "runetek4.client!qi", name = "<init>", descriptor = "(II)V")
	public ColorImageCacheEntry(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.anInt4761 = arg1;
		this.anInt4759 = arg0;
	}
}
