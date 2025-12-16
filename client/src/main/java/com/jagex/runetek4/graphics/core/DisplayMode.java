package com.jagex.runetek4.graphics.core;

import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.PrivilegedRequest;
import com.jagex.runetek4.client.*;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.gl.GlTile;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.data.js5.Js5TextureProvider;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.graphics.model.SoftwareModel;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.graphics.raster.SoftwareRenderer;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.system.SignLink;
import com.jagex.runetek4.util.system.ThreadUtils;
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
    @OriginalMember(owner = "runetek4.client!hi", name = "f", descriptor = "J")
    public static long aLong89 = 0L;
	@OriginalMember(owner = "runetek4.client!jk", name = "y", descriptor = "Z")
	public static boolean aBoolean156 = false;
	@OriginalMember(owner = "runetek4.client!rc", name = "M", descriptor = "Z")
	public static boolean aBoolean73 = false;

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
			ThreadUtils.sleep(10L);
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
			ThreadUtils.sleep(10L);
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
			@Pc(16) PrivilegedRequest request = signLink.exitFullScreen(frame);
			while (request.status == 0) {
				ThreadUtils.sleep(10L);
			}
			if (request.status == 1) {
				frame.setVisible(false);
				frame.dispose();
				return;
			}
			ThreadUtils.sleep(100L);
		}
	}

	@OriginalMember(owner = "runetek4.client!pm", name = "a", descriptor = "(ZIZIZII)V")
	public static void setWindowMode(@OriginalArg(0) boolean replaceCanvas, @OriginalArg(1) int mode, @OriginalArg(2) boolean arg2, @OriginalArg(3) int windowMode, @OriginalArg(5) int width, @OriginalArg(6) int height) {
		if (arg2) {
			GlRenderer.quit();
		}
		if (GameShell.fullScreenFrame != null && (mode != 3 || width != Preferences.fullScreenWidth || height != Preferences.fullScreenHeight)) {
			exitFullScreen(GameShell.fullScreenFrame, GameShell.signLink);
			GameShell.fullScreenFrame = null;
		}
		if (mode == 3 && GameShell.fullScreenFrame == null) {
			GameShell.fullScreenFrame = enterFullScreen(0, height, width, GameShell.signLink);
			if (GameShell.fullScreenFrame != null) {
				Preferences.fullScreenHeight = height;
				Preferences.fullScreenWidth = width;
				Preferences.write(GameShell.signLink);
			}
		}
		if (mode == 3 && GameShell.fullScreenFrame == null) {
			setWindowMode(true, Preferences.favoriteWorlds, true, windowMode, -1, -1);
			return;
		}
		@Pc(85) Container container;
		if (GameShell.fullScreenFrame != null) {
			container = GameShell.fullScreenFrame;
		} else {
			container = GameShell.frame;
		}
		GameShell.frameWidth = container.getSize().width;
		GameShell.frameHeight = container.getSize().height;
		@Pc(109) Insets insets;
		if (GameShell.frame == container) {
			insets = GameShell.frame.getInsets();
			GameShell.frameWidth -= insets.right + insets.left;
			GameShell.frameHeight -= insets.bottom + insets.top;
		}
		if (mode >= 2) {
			GameShell.canvasWidth = GameShell.frameWidth;
			GameShell.canvasHeigth = GameShell.frameHeight;
			GameShell.leftMargin = 0;
			GameShell.topMargin = 0;
		} else {
			GameShell.topMargin = 0;
			GameShell.leftMargin = (GameShell.frameWidth - 765) / 2;
			GameShell.canvasWidth = 765;
			GameShell.canvasHeigth = 503;
		}
		if (replaceCanvas) {
			Keyboard.stop(GameShell.canvas);
			Mouse.stop(GameShell.canvas);
			if (Client.mouseWheel != null) {
				Client.mouseWheel.stop(GameShell.canvas);
			}
			Client.instance.addCanvas();
			Keyboard.start(GameShell.canvas);
			Mouse.start(GameShell.canvas);
			if (Client.mouseWheel != null) {
				Client.mouseWheel.start(GameShell.canvas);
			}
		} else {
			if (GlRenderer.enabled) {
				GlRenderer.setCanvasSize(GameShell.canvasWidth, GameShell.canvasHeigth);
			}
			GameShell.canvas.setSize(GameShell.canvasWidth, GameShell.canvasHeigth);
			if (GameShell.frame == container) {
				insets = GameShell.frame.getInsets();
				GameShell.canvas.setLocation(insets.left + GameShell.leftMargin, insets.top + GameShell.topMargin);
			} else {
				GameShell.canvas.setLocation(GameShell.leftMargin, GameShell.topMargin);
			}
		}
		if (mode == 0 && windowMode > 0) {
			GlRenderer.createAndDestroyContext(GameShell.canvas);
		}
		if (arg2 && mode > 0) {
			GameShell.canvas.setIgnoreRepaint(true);
			if (!aBoolean73) {
				SceneGraph.clear();
				SoftwareRenderer.frameBuffer = null;
				SoftwareRenderer.frameBuffer = FrameBuffer.create(GameShell.canvasHeigth, GameShell.canvasWidth, GameShell.canvas);
				SoftwareRenderer.clearPixelBuffer();
				if (Client.gameState == 5) {
					LoadingBar.render(true, Fonts.b12Full);
				} else {
					Fonts.drawTextOnScreen(false, LocalizedText.LOADING);
				}
				try {
					@Pc(269) Graphics graphics = GameShell.canvas.getGraphics();
					SoftwareRenderer.frameBuffer.draw(graphics);
				} catch (@Pc(277) Exception ignored) {
				}
				GameShell.method2704();
				if (windowMode == 0) {
					SoftwareRenderer.frameBuffer = FrameBuffer.create(503, 765, GameShell.canvas);
				} else {
					SoftwareRenderer.frameBuffer = null;
				}
				@Pc(300) PrivilegedRequest request = GameShell.signLink.loadGlNatives(Client.instance.getClass());
				while (request.status == 0) {
					ThreadUtils.sleep(100L);
				}
				if (request.status == 1) {
					aBoolean73 = true;
				}
			}
			if (aBoolean73) {
				GlRenderer.init(GameShell.canvas, Preferences.antiAliasingMode * 2);
			}
		}
		if (!GlRenderer.enabled && mode > 0) {
			setWindowMode(true, 0, true, windowMode, -1, -1);
			return;
		}
		if (mode > 0 && windowMode == 0) {
			GameShell.thread.setPriority(5);
			SoftwareRenderer.frameBuffer = null;
			SoftwareModel.method4580();
			((Js5TextureProvider) Rasterizer.textureProvider).method3248(200);
			if (Preferences.highDetailLighting) {
				Rasterizer.setBrightness(0.7F);
			}
			LoginManager.method4637();
		} else if (mode == 0 && windowMode > 0) {
			GameShell.thread.setPriority(1);
			SoftwareRenderer.frameBuffer = FrameBuffer.create(503, 765, GameShell.canvas);
			SoftwareModel.method4583();
			ParticleSystem.quit();
			((Js5TextureProvider) Rasterizer.textureProvider).method3248(20);
			if (Preferences.highDetailLighting) {
				if (Preferences.brightness == 1) {
					Rasterizer.setBrightness(0.9F);
				}
				if (Preferences.brightness == 2) {
					Rasterizer.setBrightness(0.8F);
				}
				if (Preferences.brightness == 3) {
					Rasterizer.setBrightness(0.7F);
				}
				if (Preferences.brightness == 4) {
					Rasterizer.setBrightness(0.6F);
				}
			}
			GlTile.method1939();
			LoginManager.method4637();
		}
		SceneGraph.hdLighting = !SceneGraph.allLevelsAreVisible();
		if (arg2) {
			Client.method2721();
		}
		if (mode >= 2) {
			aBoolean156 = true;
		} else {
			aBoolean156 = false;
		}
		if (ComponentList.topLevelInterface != -1) {
			ComponentList.updateInterfaceLayout(true);
		}
		if (Protocol.gameServerSocket != null && (Client.gameState == 30 || Client.gameState == 25)) {
			ClientProt.sendWindowStatus();
		}
		for (@Pc(466) int local466 = 0; local466 < 100; local466++) {
			ComponentList.componentNeedsRedraw[local466] = true;
		}
		GameShell.fullredraw = true;
	}

	@OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(ZIIII)V")
	public static void setWindowMode(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		aLong89 = 0L;
		@Pc(4) int local4 = getWindowMode();
		if (arg1 == 3 || local4 == 3) {
			arg0 = true;
		}
		@Pc(44) boolean local44 = false;
		if (local4 > 0 != arg1 > 0) {
			local44 = true;
		}
		if (arg0 && arg1 > 0) {
			local44 = true;
		}
		setWindowMode(arg0, arg1, local44, local4, arg2, arg3);
	}

	@OriginalMember(owner = "runetek4.client!le", name = "a", descriptor = "(I)I")
	public static int getWindowMode() {
		if (GameShell.fullScreenFrame != null) {
			return 3;
		} else if (GlRenderer.enabled && aBoolean156) {
			return 2;
		} else if (GlRenderer.enabled && !aBoolean156) {
			return 1;
		} else {
			return 0;
		}
	}

}
