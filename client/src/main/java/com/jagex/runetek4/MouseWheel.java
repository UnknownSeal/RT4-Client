package com.jagex.runetek4;

import java.awt.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!uc")
public abstract class MouseWheel {

    @OriginalMember(owner = "runetek4.client!eh", name = "j", descriptor = "I")
    public static int wheelRotation = 0;

    @OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(B)Lclient!uc;")
    public static MouseWheel create() {
        try {
            return new JavaMouseWheel();
        } catch (@Pc(15) Throwable local15) {
            return null;
        }
    }

    @OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(I)I")
	public abstract int getRotation();

	@OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(ZLjava/awt/runetek4.Component;)V")
	public abstract void stop(@OriginalArg(1) Component arg0);

	@OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(Ljava/awt/runetek4.Component;I)V")
	public abstract void start(@OriginalArg(0) Component arg0);
}
