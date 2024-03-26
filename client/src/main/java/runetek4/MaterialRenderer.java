package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!pc")
public interface MaterialRenderer {

	@OriginalMember(owner = "runetek4.client!pc", name = "a", descriptor = "()V")
	void method4602();

	@OriginalMember(owner = "runetek4.client!pc", name = "b", descriptor = "()V")
	void method4603();

	@OriginalMember(owner = "runetek4.client!pc", name = "a", descriptor = "(I)V")
	void method4604(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!pc", name = "c", descriptor = "()I")
	int method4605();
}
