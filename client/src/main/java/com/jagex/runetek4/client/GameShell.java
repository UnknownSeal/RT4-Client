package com.jagex.runetek4.client;

import java.awt.*;
import java.awt.event.*;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.exceptions.TracingException;
import com.jagex.runetek4.graphics.core.DisplayMode;
import com.jagex.runetek4.util.system.Timer;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.core.FrameBuffer;
import com.jagex.runetek4.graphics.raster.SoftwareRenderer;
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.util.system.SignLink;
import com.jagex.runetek4.util.system.ThreadUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rc")
public abstract class GameShell extends Canvas implements Runnable, FocusListener, WindowListener {

	@OriginalMember(owner = "client!sh", name = "l", descriptor = "[J")
	public static final long[] logicTimes = new long[32];
	@OriginalMember(owner = "client!ah", name = "k", descriptor = "[J")
	public static final long[] redrawTimes = new long[32];
	@OriginalMember(owner = "client!fk", name = "l", descriptor = "Lsignlink!ll;")
    public static SignLink signLink;
    @OriginalMember(owner = "client!md", name = "L", descriptor = "Ljava/awt/Canvas;")
    public static Canvas canvas;
	@OriginalMember(owner = "client!fh", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame fullScreenFrame;
	@OriginalMember(owner = "client!d", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame frame;
	@OriginalMember(owner = "client!fl", name = "U", descriptor = "I")
	public static int frameWidth;
	@OriginalMember(owner = "client!jh", name = "c", descriptor = "I")
	public static int frameHeight;
	@OriginalMember(owner = "client!dl", name = "d", descriptor = "I")
	public static int canvasWidth;
	@OriginalMember(owner = "client!uj", name = "B", descriptor = "I")
	public static int canvasHeigth;
	@OriginalMember(owner = "client!lf", name = "f", descriptor = "I")
	public static int leftMargin = 0;
	@OriginalMember(owner = "client!od", name = "e", descriptor = "I")
	public static int topMargin = 0;
	@OriginalMember(owner = "client!ca", name = "ab", descriptor = "Z")
	public static boolean focus;
	@OriginalMember(owner = "client!eh", name = "a", descriptor = "Z")
	public static boolean shutdown = false;
	@OriginalMember(owner = "runetek4.client!t", name = "m", descriptor = "Z")
	public static volatile boolean focus_in = true;
	@OriginalMember(owner = "client!fh", name = "P", descriptor = "Z")
	public static volatile boolean fullredraw = true;
	@OriginalMember(owner = "runetek4.client!cl", name = "bb", descriptor = "Z")
	public static volatile boolean canvasReplaceRecommended = false;
	@OriginalMember(owner = "runetek4.client!tk", name = "c", descriptor = "J")
	public static volatile long lastCanvasReplace = 0L;
	@OriginalMember(owner = "runetek4.client!sj", name = "F", descriptor = "Lclient!rc;")
	public static GameShell instance = null;
	@OriginalMember(owner = "runetek4.client!ve", name = "F", descriptor = "I")
	public static int logicTimePointer;
	@OriginalMember(owner = "runetek4.client!kd", name = "pb", descriptor = "I")
	public static int clientBuild;
	@OriginalMember(owner = "runetek4.client!tc", name = "f", descriptor = "Z")
	public static boolean isJava5OrLater = false;
	@OriginalMember(owner = "runetek4.client!sf", name = "d", descriptor = "I")
	public static int minimumDelay = 1;
	@OriginalMember(owner = "client!ba", name = "B", descriptor = "I")
	public static int VARIABLE_RENDER_RATE = 20;
	@OriginalMember(owner = "client!cm", name = "b", descriptor = "Ljava/lang/Thread;")
	public static Thread thread;
	@OriginalMember(owner = "client!fl", name = "w", descriptor = "J")
	public static long killtime = 0L;
	@OriginalMember(owner = "runetek4.client!qe", name = "v", descriptor = "Lclient!s;")
	public static Timer timer;
	@OriginalMember(owner = "runetek4.client!sg", name = "p", descriptor = "I")
	public static int logicCycles;
	@OriginalMember(owner = "runetek4.client!ii", name = "i", descriptor = "I")
	public static int redrawTimePointer;
	@OriginalMember(owner = "runetek4.client!ol", name = "fb", descriptor = "I")
	public static int partialRedraws = 500;
	@OriginalMember(owner = "runetek4.client!tk", name = "v", descriptor = "I")
	public static int fps = 0;
	@OriginalMember(owner = "runetek4.client!te", name = "C", descriptor = "I")
	public static int maxMemory = 64;
	@OriginalMember(owner = "client!rc", name = "b", descriptor = "Z")
	private boolean error = false;

	public static double canvasScale = 1.0d;

	public static double subpixelX = 0.5d;

	public static double subpixelY = 0.5d;

	@OriginalMember(owner = "client!la", name = "a", descriptor = "(Lsignlink!ll;Ljava/lang/Object;I)V")
	public static void flush(@OriginalArg(0) SignLink signLink, @OriginalArg(1) Object source) {
		if (signLink.eventQueue == null) {
			return;
		}
		for (@Pc(19) int local19 = 0; local19 < 50 && signLink.eventQueue.peekEvent() != null; local19++) {
			ThreadUtils.sleep(1L);
		}
		if (source != null) {
			signLink.eventQueue.postEvent(new ActionEvent(source, 1001, "dummy"));
		}
	}

    @OriginalMember(owner = "client!ja", name = "a", descriptor = "(II)V")
    public static void setFpsTarget(@OriginalArg(0) int fps) {
        VARIABLE_RENDER_RATE = 1000 / fps;
    }

    @OriginalMember(owner = "client!ta", name = "a", descriptor = "(Z)V")
    public static void resetTimer() {
        timer.reset();
        @Pc(10) int local10;
        for (local10 = 0; local10 < 32; local10++) {
            redrawTimes[local10] = 0L;
        }
        for (local10 = 0; local10 < 32; local10++) {
            logicTimes[local10] = 0L;
        }
        logicCycles = 0;
    }

	@OriginalMember(owner = "client!sd", name = "e", descriptor = "(I)V")
	public static void getMaxMemory() {
		maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1048576L) + 1;
	}

	@OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "(Z)V")
	public static void method3662() {
		@Pc(8) Container local8;
		if (fullScreenFrame != null) {
			local8 = fullScreenFrame;
		} else {
			local8 = frame;
		}
		frameWidth = local8.getSize().width;
		frameHeight = local8.getSize().height;
		@Pc(35) Insets local35;
		if (local8 == frame) {
			local35 = frame.getInsets();
			frameHeight -= local35.bottom + local35.top;
			frameWidth -= local35.right + local35.left;
		}
		if (DisplayMode.getWindowMode() >= 2) {
			canvasWidth = frameWidth;
			leftMargin = 0;
			topMargin = 0;
			canvasHeigth = frameHeight;
		} else {
			topMargin = 0;
			leftMargin = (frameWidth - 765) / 2;
			canvasHeigth = 503;
			canvasWidth = 765;
		}
		if (GlRenderer.enabled) {
			GlRenderer.setCanvasSize(canvasWidth, canvasHeigth);
		}
		canvas.setSize(canvasWidth, canvasHeigth);
		if (local8 == frame) {
			local35 = frame.getInsets();
			canvas.setLocation(local35.left + leftMargin, topMargin + local35.top);
		} else {
			canvas.setLocation(leftMargin, topMargin);
		}
		if (ComponentList.topLevelInterface != -1) {
			ComponentList.updateInterfaceLayout(true);
		}
		method2704();
	}

	@OriginalMember(owner = "runetek4.client!l", name = "b", descriptor = "(I)V")
	public static void method2704() {
		@Pc(7) int local7 = topMargin;
		@Pc(9) int local9 = leftMargin;
		@Pc(16) int local16 = frameHeight - canvasHeigth - local7;
		@Pc(23) int local23 = frameWidth - local9 - canvasWidth;
		if (local9 <= 0 && local23 <= 0 && local7 <= 0 && local16 <= 0) {
			return;
		}
		try {
			@Pc(46) Container local46;
			if (fullScreenFrame != null) {
				local46 = fullScreenFrame;
			} else {
				local46 = frame;
			}
			@Pc(59) int local59 = 0;
			@Pc(61) int local61 = 0;
			if (frame == local46) {
				@Pc(68) Insets local68 = frame.getInsets();
				local61 = local68.left;
				local59 = local68.top;
			}
			@Pc(77) Graphics local77 = local46.getGraphics();
			local77.setColor(Color.black);
			if (local9 > 0) {
				local77.fillRect(local61, local59, local9, frameHeight);
			}
			if (local7 > 0) {
				local77.fillRect(local61, local59, frameWidth, local7);
			}
			if (local23 > 0) {
				local77.fillRect(local61 + frameWidth - local23, local59, local23, frameHeight);
			}
			if (local16 > 0) {
				local77.fillRect(local61, local59 + frameHeight - local16, frameWidth, local16);
			}
		} catch (@Pc(132) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!rc", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent event) {
		focus_in = false;
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(B)V")
	protected abstract void mainloop();

	@OriginalMember(owner = "client!rc", name = "windowClosing", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosing(@OriginalArg(0) WindowEvent event) {
		this.destroy();
	}

	@OriginalMember(owner = "client!rc", name = "windowIconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowIconified(@OriginalArg(0) WindowEvent event) {
	}

	@OriginalMember(owner = "client!rc", name = "windowDeactivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeactivated(@OriginalArg(0) WindowEvent event) {
	}

	@OriginalMember(owner = "client!rc", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent event) {
		focus_in = true;
		fullredraw = true;
	}

	@OriginalMember(owner = "client!rc", name = "windowClosed", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosed(@OriginalArg(0) WindowEvent event) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(B)V")
	public final synchronized void addCanvas() {
		if (canvas != null) {
			canvas.removeFocusListener(this);
			canvas.getParent().remove(canvas);
		}
		@Pc(19) Container container;
		if (fullScreenFrame != null) {
			container = fullScreenFrame;
		} else {
			container = frame;
		}
		container.setLayout(null);
		canvas = new GameCanvas(this);
		container.add(canvas);
		canvas.setSize(canvasWidth, canvasHeigth);
		canvas.setVisible(true);
		if (container == frame) {
			@Pc(66) Insets insets = frame.getInsets();
			canvas.setLocation(leftMargin + insets.left, insets.top + topMargin);
		} else {
			canvas.setLocation(leftMargin, topMargin);
		}
		canvas.addFocusListener(this);
		canvas.requestFocus();
		focus_in = true;
		fullredraw = true;
		focus = true;
		canvasReplaceRecommended = false;
		lastCanvasReplace = MonotonicTime.currentTimeMillis();
	}

	@OriginalMember(owner = "client!rc", name = "destroy", descriptor = "()V")
	public final void destroy() {
		if (instance == this && !shutdown) {
			killtime = MonotonicTime.currentTimeMillis();
			ThreadUtils.sleep(5000L);
			TracingException.signLink = null;
			this.shutdown(false);
		}
	}

	@OriginalMember(owner = "client!rc", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics graphics) {
		this.paint(graphics);
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(Ljava/lang/String;I)V")
	protected final void error(@OriginalArg(0) String message) {
		if (this.error) {
			return;
		}
		this.error = true;
		System.out.println("error_game_" + message);
	}

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(B)V")
	protected abstract void mainQuit();

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(I)V")
	protected abstract void reset();

	@OriginalMember(owner = "client!rc", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final synchronized void paint(@OriginalArg(0) Graphics arg0) {
		if (instance != this || shutdown) {
			return;
		}
		fullredraw = true;
		if (isJava5OrLater && !GlRenderer.enabled && MonotonicTime.currentTimeMillis() - lastCanvasReplace > 1000L) {
			@Pc(29) Rectangle clipBounds = arg0.getClipBounds();
			if (clipBounds == null || clipBounds.width >= frameWidth && frameHeight <= clipBounds.height) {
				canvasReplaceRecommended = true;
			}
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowDeiconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeiconified(@OriginalArg(0) WindowEvent event) {
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IZ)V")
	private void shutdown(@OriginalArg(1) boolean clean) {
		synchronized (this) {
			if (shutdown) {
				return;
			}
			shutdown = true;
		}
		try {
			this.mainQuit();
		} catch (@Pc(34) Exception ignored) {
		}
		if (canvas != null) {
			try {
				canvas.removeFocusListener(this);
				canvas.getParent().remove(canvas);
			} catch (@Pc(45) Exception ignored) {
			}
		}
		if (signLink != null) {
			try {
				signLink.stop();
			} catch (@Pc(53) Exception ignored) {
			}
		}
		this.reset();
		if (frame != null) {
			try {
				System.exit(0);
			} catch (@Pc(77) Throwable ignored) {
			}
		}
		System.out.println("Shutdown complete - clean:" + clean);
	}

	@OriginalMember(owner = "client!rc", name = "windowActivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowActivated(@OriginalArg(0) WindowEvent event) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(Z)V")
	private void mainLoopWrapper() {
		@Pc(6) long local6 = MonotonicTime.currentTimeMillis();
		@Pc(10) long local10 = logicTimes[logicTimePointer];
		logicTimes[logicTimePointer] = local6;
		logicTimePointer = logicTimePointer + 1 & 0x1F;
		synchronized (this) {
			focus = focus_in;
		}
		this.mainloop();
		if (local10 != 0L && local6 <= local10) {
			// TODO why is this here?
		}
	}

	public static GraphicsDevice getCurrentDevice() {
		GraphicsConfiguration config = frame.getGraphicsConfiguration();
		GraphicsDevice myScreen = config.getDevice();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] allScreens = env.getScreenDevices();
		for (int i = 0; i < allScreens.length; i++) {
			if (allScreens[i].equals(myScreen)) {
				return allScreens[i];
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!rc", name = "e", descriptor = "(I)V")
	private void mainRedrawWrapper() {
		@Pc(2) long now = MonotonicTime.currentTimeMillis();
		@Pc(6) long local6 = redrawTimes[redrawTimePointer];
		redrawTimes[redrawTimePointer] = now;
		redrawTimePointer = redrawTimePointer + 1 & 0x1F;
		if (local6 != 0L && now > local6) {
			@Pc(41) int local41 = (int) (now - local6);
			fps = ((local41 >> 1) + 32000) / local41;
		}
		if (partialRedraws++ > 50) {
			fullredraw = true;
			partialRedraws -= 50;
			canvas.setSize(canvasWidth, canvasHeigth);
			canvas.setVisible(true);
			canvasScale = getCurrentDevice().getDefaultConfiguration().getDefaultTransform().getScaleX();
			if (frame != null && fullScreenFrame == null) {
				@Pc(84) Insets insets = frame.getInsets();
				canvas.setLocation(insets.left + leftMargin, topMargin + insets.top);
			} else {
				canvas.setLocation(leftMargin, topMargin);
			}
		}
		this.mainRedraw();
	}

	@OriginalMember(owner = "client!rc", name = "f", descriptor = "(I)V")
	protected abstract void mainRedraw();

	@OriginalMember(owner = "client!rc", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		try {
			if (SignLink.javaVendor != null) {
				@Pc(12) String javaVendor = SignLink.javaVendor.toLowerCase();
				if (javaVendor.indexOf("sun") != -1 || javaVendor.indexOf("apple") != -1) {
					@Pc(24) String javaVersion = SignLink.javaVersion;
					if (javaVersion.equals("1.1") || javaVersion.startsWith("1.1.") || javaVersion.equals("1.2") || javaVersion.startsWith("1.2.")) {
						this.error("wrongjava");
						return;
					}
					minimumDelay = 5;
				} else if (javaVendor.indexOf("ibm") != -1 && (SignLink.javaVersion == null || SignLink.javaVersion.equals("1.4.2"))) {
					this.error("wrongjava");
					return;
				}
			}
			@Pc(76) int i;
			if (SignLink.javaVersion != null && SignLink.javaVersion.startsWith("1.")) {
				i = 2;
				@Pc(78) int minorVersion = 0;
				while (i < SignLink.javaVersion.length()) {
					@Pc(90) char c = SignLink.javaVersion.charAt(i);
					if (c < '0' || c > '9') {
						break;
					}
					minorVersion = minorVersion * 10 + c - 48;
					i++;
				}
				if (minorVersion >= 5) {
					isJava5OrLater = true;
				}
			}
			getMaxMemory();
			this.addCanvas();
			SoftwareRenderer.frameBuffer = FrameBuffer.create(canvasHeigth, canvasWidth, canvas);
			this.mainInit();
			timer = Timer.create();
			while (killtime == 0L || killtime > MonotonicTime.currentTimeMillis()) {
				logicCycles = timer.sleep(minimumDelay, VARIABLE_RENDER_RATE);
				for (i = 0; i < logicCycles; i++) {
					this.mainLoopWrapper();
				}
				this.mainRedrawWrapper();
				flush(signLink, canvas);
			}
		} catch (@Pc(198) Exception local198) {
			TracingException.report(null, local198);
			this.error("crash");
		}
		this.shutdown(true);
	}

	@OriginalMember(owner = "client!rc", name = "g", descriptor = "(I)V")
	protected abstract void mainInit();

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IIZILjava/lang/String;III)V")
	protected final void startApplication(@OriginalArg(0) int cacheId, @OriginalArg(4) String cacheSubDir) {
		try {
			canvasHeigth = 768;
			frameHeight = 768;
			leftMargin = 0;
			clientBuild = 530;
			canvasWidth = 1024;
			frameWidth = 1024;
			topMargin = 0;
			instance = this;
			frame = new Frame();
			frame.setTitle("Jagex");
			frame.setResizable(true);
			frame.addWindowListener(this);
			frame.setVisible(true);
			frame.toFront();
			@Pc(44) Insets insets = frame.getInsets();
			frame.setSize(insets.left + frameWidth + insets.right, insets.top + frameHeight + insets.bottom);
			TracingException.signLink = signLink = new SignLink(cacheId, cacheSubDir, 28);
			@Pc(76) PrivilegedRequest request = signLink.startThread(1, this);
			while (request.status == 0) {
				ThreadUtils.sleep(10L);
			}
			thread = (Thread) request.result;
		} catch (@Pc(91) Exception local91) {
			TracingException.report(null, local91);
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowOpened", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowOpened(@OriginalArg(0) WindowEvent event) {
	}
}
