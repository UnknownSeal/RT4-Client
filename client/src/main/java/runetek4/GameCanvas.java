package runetek4;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!tm")
public final class GameCanvas extends Canvas {

	@OriginalMember(owner = "runetek4.client!tm", name = "m", descriptor = "Ljava/awt/runetek4.Component;")
	private final Component aComponent2;

	@OriginalMember(owner = "runetek4.client!tm", name = "<init>", descriptor = "(Ljava/awt/runetek4.Component;)V")
	public GameCanvas(@OriginalArg(0) Component arg0) {
		this.aComponent2 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!tm", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics arg0) {
		this.aComponent2.update(arg0);
	}

	@OriginalMember(owner = "runetek4.client!tm", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void paint(@OriginalArg(0) Graphics arg0) {
		this.aComponent2.paint(arg0);
	}
}
