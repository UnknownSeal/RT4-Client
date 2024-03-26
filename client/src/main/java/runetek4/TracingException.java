package runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ld")
public final class TracingException extends RuntimeException {

	@OriginalMember(owner = "runetek4.client!ld", name = "e", descriptor = "Ljava/lang/String;")
	public String aString3;

	@OriginalMember(owner = "runetek4.client!ld", name = "f", descriptor = "Ljava/lang/Throwable;")
	public Throwable aThrowable1;
}
