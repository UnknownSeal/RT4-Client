package com.jagex.graphics.awt;

import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!e")
public final class AwtFullscreenAdapter {

	@OriginalMember(owner = "signlink!e", name = "b", descriptor = "Ljava/awt/DisplayMode;")
	private DisplayMode previousDisplayMode;

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "Ljava/awt/GraphicsDevice;")
	private GraphicsDevice fullscreenDevice;

	@OriginalMember(owner = "signlink!e", name = "<init>", descriptor = "()V")
	public AwtFullscreenAdapter() throws Exception {
		@Pc(3) GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.fullscreenDevice = env.getDefaultScreenDevice();
		if (!this.fullscreenDevice.isFullScreenSupported()) {
			@Pc(15) GraphicsDevice[] screenDevices = env.getScreenDevices();
			for (@Pc(19) int i = 0; i < screenDevices.length; i++) {
				@Pc(27) GraphicsDevice screenDevice = screenDevices[i];
				if (screenDevice != null && screenDevice.isFullScreenSupported()) {
					this.fullscreenDevice = screenDevice;
					return;
				}
			}
			throw new Exception();
		}
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(Ljava/awt/Frame;B)V")
	private void method5103(@OriginalArg(0) Frame frame) {
		@Pc(1) boolean wasValid = false;
		try {
			@Pc(6) Field validField = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
			validField.setAccessible(true);
			@Pc(16) boolean valid = (Boolean) validField.get(this.fullscreenDevice);
			if (valid) {
				validField.set(this.fullscreenDevice, Boolean.FALSE);
				wasValid = true;
			}
		} catch (@Pc(27) Throwable exception) {
		}
		try {
			this.fullscreenDevice.setFullScreenWindow(frame);
		} finally {
			if (wasValid) {
				try {
					@Pc(66) Field validField = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
					validField.set(this.fullscreenDevice, Boolean.TRUE);
				} catch (@Pc(73) Throwable exception) {
				}
			}
		}
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(IIIILjava/awt/Frame;I)V")
	public final void method5104(@OriginalArg(1) int refreshrate, @OriginalArg(2) int bits, @OriginalArg(3) int height, @OriginalArg(4) Frame frame, @OriginalArg(5) int width) {
		this.previousDisplayMode = this.fullscreenDevice.getDisplayMode();
		if (this.previousDisplayMode == null) {
			throw new NullPointerException();
		}
		frame.setUndecorated(true);
		frame.enableInputMethods(false);
		this.method5103(frame);
		if (refreshrate == 0) {
			@Pc(37) int currentrate = this.previousDisplayMode.getRefreshRate();
			@Pc(41) DisplayMode[] mode = this.fullscreenDevice.getDisplayModes();
			@Pc(43) boolean foundBetter = false;
			for (@Pc(45) int i = 0; i < mode.length; i++) {
				if (mode[i].getWidth() == width && mode[i].getHeight() == height && bits == mode[i].getBitDepth()) {
					@Pc(77) int potentialRefreshRate = mode[i].getRefreshRate();
					if (!foundBetter || Math.abs(potentialRefreshRate - currentrate) < Math.abs(refreshrate - currentrate)) {
						foundBetter = true;
						refreshrate = potentialRefreshRate;
					}
				}
			}
			if (!foundBetter) {
				refreshrate = currentrate;
			}
		}
		this.fullscreenDevice.setDisplayMode(new DisplayMode(width, height, bits, refreshrate));
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(Z)[I")
	public final int[] listmodes() {
		@Pc(9) DisplayMode[] modes = this.fullscreenDevice.getDisplayModes();
		@Pc(15) int[] packedModes = new int[modes.length << 2];
		for (@Pc(17) int i = 0; i < modes.length; i++) {
			packedModes[i << 2] = modes[i].getWidth();
			packedModes[(i << 2) + 1] = modes[i].getHeight();
			packedModes[(i << 2) + 2] = modes[i].getBitDepth();
			packedModes[(i << 2) + 3] = modes[i].getRefreshRate();
		}
		return packedModes;
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(I)V")
	public final void exit() {
		if (this.previousDisplayMode != null) {
			this.fullscreenDevice.setDisplayMode(this.previousDisplayMode);
			if (!this.fullscreenDevice.getDisplayMode().equals(this.previousDisplayMode)) {
				throw new RuntimeException("Did not return to correct resolution!");
			}
			this.previousDisplayMode = null;
		}
		this.method5103(null);
	}
}
