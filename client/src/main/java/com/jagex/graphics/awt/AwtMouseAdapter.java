package com.jagex.graphics.awt;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

@OriginalClass("signlink!g")
public final class AwtMouseAdapter {

	@OriginalMember(owner = "signlink!g", name = "b", descriptor = "Ljava/awt/Component;")
	private Component component;

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "Ljava/awt/Robot;")
	private final Robot robot = new Robot();

	@OriginalMember(owner = "signlink!g", name = "<init>", descriptor = "()V")
	public AwtMouseAdapter() throws Exception {
	}

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(BLjava/awt/Point;ILjava/awt/Component;I[I)V")
	public final void setcustomcursor(@OriginalArg(1) Point hotSpot, @OriginalArg(2) int width, @OriginalArg(3) Component component, @OriginalArg(4) int height, @OriginalArg(5) int[] rgb) {
		if (rgb == null) {
			component.setCursor(null);
		} else {
			@Pc(13) BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
			image.setRGB(0, 0, width, height, rgb, 0, width);
			component.setCursor(component.getToolkit().createCustomCursor(image, hotSpot, null));
		}
	}

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(III)V")
	public final void movemouse(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		this.robot.mouseMove(arg0, arg1);
	}

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(Ljava/awt/Component;IZ)V")
	public final void showcursor(@OriginalArg(0) Component component, @OriginalArg(2) boolean delete) {
		if (delete) {
			component = null;
		} else if (component == null) {
			throw new NullPointerException();
		}
		if (component == this.component) {
			return;
		}
		if (this.component != null) {
			this.component.setCursor(null);
			this.component = null;
		}
		if (component != null) {
			component.setCursor(component.getToolkit().createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), null));
			this.component = component;
		}
	}
}
