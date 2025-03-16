package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

// DisplayMode
@OriginalClass("client!od")
public final class DisplayMode {

	@OriginalMember(owner = "runetek4.client!ib", name = "i", descriptor = "[Lclient!od;")
	public static DisplayMode[] modes;
	@OriginalMember(owner = "client!od", name = "j", descriptor = "I")
	public int width;

	@OriginalMember(owner = "client!od", name = "k", descriptor = "I")
	public int refreshRate;

	@OriginalMember(owner = "client!od", name = "l", descriptor = "I")
	public int height;

	@OriginalMember(owner = "client!od", name = "m", descriptor = "I")
	public int bitDepth;

	@OriginalMember(owner = "runetek4.client!ab", name = "c", descriptor = "(B)[Lclient!od;")
	public static DisplayMode[] getModes() {
		if (modes == null) {
			@Pc(16) DisplayMode[] modes = getModesInternal(GameShell.signLink);
			@Pc(20) DisplayMode[] validModes = new DisplayMode[modes.length];
			@Pc(22) int validModesSize = 0;
			mode: for (@Pc(24) int i = 0; i < modes.length; i++) {
				@Pc(32) DisplayMode mode = modes[i];
				if ((mode.bitDepth <= 0 || mode.bitDepth >= 24) && mode.width >= 800 && mode.height >= 600) {
					for (@Pc(52) int j = 0; j < validModesSize; j++) {
						@Pc(59) DisplayMode other = validModes[j];
						if (mode.width == other.width && other.height == mode.height) {
							if (mode.bitDepth > other.bitDepth) {
								validModes[j] = mode;
							}
							continue mode;
						}
					}
					validModes[validModesSize] = mode;
					validModesSize++;
				}
			}
			DisplayMode.modes = new DisplayMode[validModesSize];
			ArrayUtils.copy(validModes, 0, DisplayMode.modes, 0, validModesSize);
			@Pc(112) int[] resolutions = new int[DisplayMode.modes.length];
			for (@Pc(114) int i = 0; i < DisplayMode.modes.length; i++) {
				@Pc(122) DisplayMode mode = DisplayMode.modes[i];
				resolutions[i] = mode.height * mode.width;
			}
			ArrayUtils.sort(resolutions, DisplayMode.modes);
		}
		return modes;
	}

	@OriginalMember(owner = "runetek4.client!pm", name = "a", descriptor = "(ILsignlink!ll;)[Lclient!od;")
	public static DisplayMode[] getModesInternal(@OriginalArg(1) SignLink signLink) {
		if (!signLink.isFullScreenSupported()) {
			return new DisplayMode[0];
		}
		@Pc(17) PrivilegedRequest request = signLink.getDisplayModes();
		while (request.status == 0) {
			PreciseSleep.sleep(10L);
		}
		if (request.status == 2) {
			return new DisplayMode[0];
		}
		@Pc(39) int[] result = (int[]) request.result;
		@Pc(45) DisplayMode[] modes = new DisplayMode[result.length >> 2];
		for (@Pc(47) int i = 0; i < modes.length; i++) {
			@Pc(59) DisplayMode mode = new DisplayMode();
			modes[i] = mode;
			mode.width = result[i << 2];
			mode.height = result[(i << 2) + 1];
			mode.bitDepth = result[(i << 2) + 2];
			mode.refreshRate = result[(i << 2) + 3];
		}
		return modes;
	}

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(IIIIILsignlink!ll;)Ljava/awt/Frame;")
	public static Frame enterFullScreen(@OriginalArg(2) int bitDepth, @OriginalArg(3) int height, @OriginalArg(4) int width, @OriginalArg(5) SignLink signLink) {
		if (!signLink.isFullScreenSupported()) {
			return null;
		}
		@Pc(20) DisplayMode[] modes = getModesInternal(signLink);
		if (modes == null) {
			return null;
		}
		@Pc(27) boolean valid = false;
		for (@Pc(29) int i = 0; i < modes.length; i++) {
			if (width == modes[i].width && height == modes[i].height && (!valid || modes[i].bitDepth > bitDepth)) {
				bitDepth = modes[i].bitDepth;
				valid = true;
			}
		}
		if (!valid) {
			return null;
		}
		@Pc(90) PrivilegedRequest request = signLink.enterFullScreen(bitDepth, height, width);
		while (request.status == 0) {
			PreciseSleep.sleep(10L);
		}
		@Pc(103) Frame frame = (Frame) request.result;
		if (frame == null) {
			return null;
		} else if (request.status == 2) {
			exitFullScreen(frame, signLink);
			return null;
		} else {
			return frame;
		}
	}

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Ljava/awt/Frame;ZLsignlink!ll;)V")
	public static void exitFullScreen(@OriginalArg(0) Frame frame, @OriginalArg(2) SignLink signLink) {
		while (true) {
			@Pc(16) PrivilegedRequest request = signLink.method5115(frame);
			while (request.status == 0) {
				PreciseSleep.sleep(10L);
			}
			if (request.status == 1) {
				frame.setVisible(false);
				frame.dispose();
				return;
			}
			PreciseSleep.sleep(100L);
		}
	}
}
