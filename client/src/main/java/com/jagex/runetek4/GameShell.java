package com.jagex.runetek4;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.net.URL;

import com.jagex.runetek4.util.SignLink;
import com.jagex.runetek4.util.ThreadUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rc")
public abstract class GameShell extends Applet implements Runnable, FocusListener, WindowListener {

    @OriginalMember(owner = "runetek4.client!fk", name = "l", descriptor = "Lsignlink!ll;")
    public static SignLink signLink;
    @OriginalMember(owner = "runetek4.client!md", name = "L", descriptor = "Ljava/awt/Canvas;")
    public static Canvas canvas;
	@OriginalMember(owner = "runetek4.client!fh", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame fullScreenFrame;
	@OriginalMember(owner = "runetek4.client!d", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame frame;
	@OriginalMember(owner = "runetek4.client!fl", name = "U", descriptor = "I")
	public static int frameWidth;
	@OriginalMember(owner = "runetek4.client!jh", name = "c", descriptor = "I")
	public static int frameHeight;
	@OriginalMember(owner = "runetek4.client!dl", name = "d", descriptor = "I")
	public static int canvasWidth;
	@OriginalMember(owner = "runetek4.client!uj", name = "B", descriptor = "I")
	public static int canvasHeigth;
	@OriginalMember(owner = "runetek4.client!lf", name = "f", descriptor = "I")
	public static int leftMargin = 0;
	@OriginalMember(owner = "runetek4.client!od", name = "e", descriptor = "I")
	public static int topMargin = 0;
	@OriginalMember(owner = "client!rc", name = "b", descriptor = "Z")
	private boolean aBoolean71 = false;

	@OriginalMember(owner = "client!rc", name = "providesignlink", descriptor = "(Lsignlink!ll;)V")
	public static void providesignlink(@OriginalArg(0) SignLink arg0) {
		signLink = arg0;
		Static69.aClass213_4 = arg0;
	}

	@OriginalMember(owner = "client!la", name = "a", descriptor = "(Lsignlink!ll;Ljava/lang/Object;I)V")
	public static void flush(@OriginalArg(0) SignLink arg0, @OriginalArg(1) Object arg1) {
		if (arg0.eventQueue == null) {
			return;
		}
		for (@Pc(19) int local19 = 0; local19 < 50 && arg0.eventQueue.peekEvent() != null; local19++) {
			ThreadUtils.sleep(1L);
		}
		if (arg1 != null) {
			arg0.eventQueue.postEvent(new ActionEvent(arg1, 1001, "dummy"));
		}
	}

    @OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(II)V")
    public static void setFpsTarget(@OriginalArg(0) int arg0) {
        Static11.anInt386 = 1000 / arg0;
    }

    @OriginalMember(owner = "client!rc", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent arg0) {
		ClientScriptRunner.focus_in = false;
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(B)V")
	protected abstract void mainloop();

	@OriginalMember(owner = "client!rc", name = "windowClosing", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosing(@OriginalArg(0) WindowEvent arg0) {
		this.destroy();
	}

	@OriginalMember(owner = "client!rc", name = "windowIconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowIconified(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "windowDeactivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeactivated(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "getAppletContext", descriptor = "()Ljava/applet/AppletContext;")
	@Override
	public final AppletContext getAppletContext() {
		if (frame == null) {
			return signLink == null || signLink.applet == this ? super.getAppletContext() : signLink.applet.getAppletContext();
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!rc", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
		ClientScriptRunner.focus_in = true;
		Static69.fullredraw = true;
	}

	@OriginalMember(owner = "client!rc", name = "windowClosed", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosed(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(I)Z")
	protected final boolean method925() {
		return true;
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(B)V")
	public final synchronized void addCanvas() {
		if (canvas != null) {
			canvas.removeFocusListener(this);
			canvas.getParent().remove(canvas);
		}
		@Pc(19) Container local19;
		if (fullScreenFrame != null) {
			local19 = fullScreenFrame;
		} else if (frame == null) {
			local19 = signLink.applet;
		} else {
			local19 = frame;
		}
		local19.setLayout(null);
		canvas = new GameCanvas(this);
		local19.add(canvas);
		canvas.setSize(canvasWidth, canvasHeigth);
		canvas.setVisible(true);
		if (local19 == frame) {
			@Pc(66) Insets local66 = frame.getInsets();
			canvas.setLocation(leftMargin + local66.left, local66.top + topMargin);
		} else {
			canvas.setLocation(leftMargin, topMargin);
		}
		canvas.addFocusListener(this);
		canvas.requestFocus();
		ClientScriptRunner.focus_in = true;
		Static69.fullredraw = true;
		Static26.focus = true;
		Static35.canvasReplaceRecommended = false;
		Static243.lastCanvasReplace = MonotonicTime.get();
	}

	@OriginalMember(owner = "client!rc", name = "destroy", descriptor = "()V")
	@Override
	public final void destroy() {
		if (Static230.anApplet_Sub1_1 == this && !Static58.shutdown) {
			Static72.killtime = MonotonicTime.get();
			ThreadUtils.sleep(5000L);
			Static69.aClass213_4 = null;
			this.shutdown(false);
		}
	}

	@OriginalMember(owner = "client!rc", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics arg0) {
		this.paint(arg0);
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(Ljava/lang/String;I)V")
	protected final void error(@OriginalArg(0) String message) {
		if (this.aBoolean71) {
			return;
		}
		this.aBoolean71 = true;
		System.out.println("error_game_" + message);
		try {
			this.getAppletContext().showDocument(new URL(this.getCodeBase(), "error_game_" + message + ".ws"), "_top");
		} catch (@Pc(47) Exception exception) {
		}
	}

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(B)V")
	protected abstract void mainquit();

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(I)V")
	protected abstract void method929();

	@OriginalMember(owner = "client!rc", name = "getDocumentBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getDocumentBase() {
		if (frame == null) {
			return signLink == null || signLink.applet == this ? super.getDocumentBase() : signLink.applet.getDocumentBase();
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!rc", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final synchronized void paint(@OriginalArg(0) Graphics arg0) {
		if (Static230.anApplet_Sub1_1 != this || Static58.shutdown) {
			return;
		}
		Static69.fullredraw = true;
		if (Static236.aBoolean256 && !GlRenderer.enabled && MonotonicTime.get() - Static243.lastCanvasReplace > 1000L) {
			@Pc(29) Rectangle local29 = arg0.getClipBounds();
			if (local29 == null || local29.width >= frameWidth && frameHeight <= local29.height) {
				Static35.canvasReplaceRecommended = true;
			}
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowDeiconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeiconified(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IZ)V")
	private void shutdown(@OriginalArg(1) boolean arg0) {
		synchronized (this) {
			if (Static58.shutdown) {
				return;
			}
			Static58.shutdown = true;
		}
		if (signLink.applet != null) {
			signLink.applet.destroy();
		}
		try {
			this.mainquit();
		} catch (@Pc(34) Exception local34) {
		}
		if (canvas != null) {
			try {
				canvas.removeFocusListener(this);
				canvas.getParent().remove(canvas);
			} catch (@Pc(45) Exception local45) {
			}
		}
		if (signLink != null) {
			try {
				signLink.stop();
			} catch (@Pc(53) Exception local53) {
			}
		}
		this.method929();
		if (frame != null) {
			try {
				System.exit(0);
			} catch (@Pc(77) Throwable local77) {
			}
		}
		System.out.println("Shutdown complete - clean:" + arg0);
	}

	@OriginalMember(owner = "client!rc", name = "windowActivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowActivated(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(Z)V")
	private void mainloopwrapper() {
		@Pc(6) long local6 = MonotonicTime.get();
		@Pc(10) long local10 = Static228.aLongArray8[Static261.anInt5741];
		Static228.aLongArray8[Static261.anInt5741] = local6;
		Static261.anInt5741 = Static261.anInt5741 + 1 & 0x1F;
		synchronized (this) {
			Static26.focus = ClientScriptRunner.focus_in;
		}
		this.mainloop();
		if (local10 != 0L && local6 <= local10) {
		}
	}

	@OriginalMember(owner = "client!rc", name = "e", descriptor = "(I)V")
	private void mainredrawwrapper() {
		@Pc(2) long local2 = MonotonicTime.get();
		@Pc(6) long local6 = aClass6.aLongArray2[Static111.anInt2903];
		aClass6.aLongArray2[Static111.anInt2903] = local2;
		Static111.anInt2903 = Static111.anInt2903 + 1 & 0x1F;
		if (local6 != 0L && local2 > local6) {
			@Pc(41) int local41 = (int) (local2 - local6);
			Static243.fps = ((local41 >> 1) + 32000) / local41;
		}
		if (Static184.anInt4355++ > 50) {
			Static69.fullredraw = true;
			Static184.anInt4355 -= 50;
			canvas.setSize(canvasWidth, canvasHeigth);
			canvas.setVisible(true);
			if (frame != null && fullScreenFrame == null) {
				@Pc(84) Insets local84 = frame.getInsets();
				canvas.setLocation(local84.left + leftMargin, topMargin + local84.top);
			} else {
				canvas.setLocation(leftMargin, topMargin);
			}
		}
		this.mainredraw();
	}

	@OriginalMember(owner = "client!rc", name = "f", descriptor = "(I)V")
	protected abstract void mainredraw();

	@OriginalMember(owner = "client!rc", name = "getCodeBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getCodeBase() {
		if (frame == null) {
			return signLink == null || signLink.applet == this ? super.getCodeBase() : signLink.applet.getCodeBase();
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!rc", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		try {
			if (SignLink.javaVendor != null) {
				@Pc(12) String local12 = SignLink.javaVendor.toLowerCase();
				if (local12.indexOf("sun") != -1 || local12.indexOf("apple") != -1) {
					@Pc(24) String local24 = SignLink.javaVersion;
					if (local24.equals("1.1") || local24.startsWith("1.1.") || local24.equals("1.2") || local24.startsWith("1.2.")) {
						this.error("wrongjava");
						return;
					}
					Static226.anInt5081 = 5;
				} else if (local12.indexOf("ibm") != -1 && (SignLink.javaVersion == null || SignLink.javaVersion.equals("1.4.2"))) {
					this.error("wrongjava");
					return;
				}
			}
			@Pc(76) int local76;
			if (SignLink.javaVersion != null && SignLink.javaVersion.startsWith("1.")) {
				local76 = 2;
				@Pc(78) int local78 = 0;
				while (local76 < SignLink.javaVersion.length()) {
					@Pc(90) char local90 = SignLink.javaVersion.charAt(local76);
					if (local90 < '0' || local90 > '9') {
						break;
					}
					local78 = local78 * 10 + local90 - 48;
					local76++;
				}
				if (local78 >= 5) {
					Static236.aBoolean256 = true;
				}
			}
			if (signLink.applet != null) {
				@Pc(125) Method local125 = SignLink.setFocusCycleRoot;
				if (local125 != null) {
					try {
						local125.invoke(signLink.applet, Boolean.TRUE);
					} catch (@Pc(142) Throwable local142) {
					}
				}
			}
			Static224.method3888();
			this.addCanvas();
			Static260.frameBuffer = FrameBuffer.create(canvasHeigth, canvasWidth, canvas);
			this.method935();
			Static200.aClass93_1 = Static70.method1547();
			while (Static72.killtime == 0L || Static72.killtime > MonotonicTime.get()) {
				Static227.anInt5097 = Static200.aClass93_1.method3391(Static226.anInt5081, Static11.anInt386);
				for (local76 = 0; local76 < Static227.anInt5097; local76++) {
					this.mainloopwrapper();
				}
				this.mainredrawwrapper();
				flush(signLink, canvas);
			}
		} catch (@Pc(198) Exception local198) {
			TracingException.report(null, local198);
			this.error("crash");
		}
		this.shutdown(true);
	}

	@OriginalMember(owner = "client!rc", name = "getParameter", descriptor = "(Ljava/lang/String;)Ljava/lang/String;")
	@Override
	public final String getParameter(@OriginalArg(0) String arg0) {
		if (frame == null) {
			return signLink == null || signLink.applet == this ? super.getParameter(arg0) : signLink.applet.getParameter(arg0);
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!rc", name = "g", descriptor = "(I)V")
	protected abstract void method935();

	@OriginalMember(owner = "client!rc", name = "stop", descriptor = "()V")
	@Override
	public final void stop() {
		if (Static230.anApplet_Sub1_1 == this && !Static58.shutdown) {
			Static72.killtime = MonotonicTime.get() + 4000L;
		}
	}

	@OriginalMember(owner = "client!rc", name = "init", descriptor = "()V")
	public abstract void init();

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IIZILjava/lang/String;III)V")
	protected final void method936(@OriginalArg(0) int arg0, @OriginalArg(4) String arg1) {
		try {
			canvasHeigth = 768;
			frameHeight = 768;
			leftMargin = 0;
			Static131.anInt3252 = 530;
			canvasWidth = 1024;
			frameWidth = 1024;
			topMargin = 0;
			Static230.anApplet_Sub1_1 = this;
			frame = new Frame();
			frame.setTitle("Jagex");
			frame.setResizable(true);
			frame.addWindowListener(this);
			frame.setVisible(true);
			frame.toFront();
			@Pc(44) Insets local44 = frame.getInsets();
			frame.setSize(local44.left + frameWidth + local44.right, local44.top + frameHeight + local44.bottom);
			Static69.aClass213_4 = signLink = new SignLink(null, arg0, arg1, 28);
			@Pc(76) PrivilegedRequest local76 = signLink.startThread(1, this);
			while (local76.status == 0) {
				ThreadUtils.sleep(10L);
			}
			Static37.aThread1 = (Thread) local76.result;
		} catch (@Pc(91) Exception local91) {
			TracingException.report(null, local91);
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowOpened", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowOpened(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!rc", name = "start", descriptor = "()V")
	@Override
	public final void start() {
		if (Static230.anApplet_Sub1_1 == this && !Static58.shutdown) {
			Static72.killtime = 0L;
		}
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(BIIII)V")
	protected final void method937(@OriginalArg(2) int arg0) {
		try {
			if (Static230.anApplet_Sub1_1 != null) {
				Static70.anInt2014++;
				if (Static70.anInt2014 >= 3) {
					this.error("alreadyloaded");
					return;
				}
				this.getAppletContext().showDocument(this.getDocumentBase(), "_self");
				return;
			}
			Static230.anApplet_Sub1_1 = this;
			topMargin = 0;
			Static131.anInt3252 = 1530;
			canvasWidth = 765;
			frameWidth = 765;
			leftMargin = 0;
			canvasHeigth = 503;
			frameHeight = 503;
			@Pc(54) String local54 = this.getParameter("openwinjs");
			if (local54 != null && local54.equals("1")) {
				Static40.aBoolean78 = true;
			} else {
				Static40.aBoolean78 = false;
			}
			if (signLink == null) {
				Static69.aClass213_4 = signLink = new SignLink(this, arg0, null, 0);
			}
			@Pc(86) PrivilegedRequest local86 = signLink.startThread(1, this);
			while (local86.status == 0) {
				ThreadUtils.sleep(10L);
			}
			Static37.aThread1 = (Thread) local86.result;
		} catch (@Pc(103) Exception local103) {
			TracingException.report(null, local103);
			this.error("crash");
		}
	}
}
