package com.jagex.runetek4;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.net.URL;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rc")
public abstract class GameShell extends Applet implements Runnable, FocusListener, WindowListener {

    @OriginalMember(owner = "runetek4.client!fk", name = "l", descriptor = "Lsignlink!ll;")
    public static SignLink signLink;
    @OriginalMember(owner = "client!rc", name = "b", descriptor = "Z")
	private boolean aBoolean71 = false;

	@OriginalMember(owner = "client!rc", name = "providesignlink", descriptor = "(Lsignlink!ll;)V")
	public static void providesignlink(@OriginalArg(0) SignLink arg0) {
		signLink = arg0;
		Static69.aClass213_4 = arg0;
	}

	@OriginalMember(owner = "client!la", name = "a", descriptor = "(Lsignlink!ll;Ljava/lang/Object;I)V")
	public static void method2708(@OriginalArg(0) SignLink arg0, @OriginalArg(1) Object arg1) {
		if (arg0.eventQueue == null) {
			return;
		}
		for (@Pc(19) int local19 = 0; local19 < 50 && arg0.eventQueue.peekEvent() != null; local19++) {
			PreciseSleep.sleep(1L);
		}
		if (arg1 != null) {
			arg0.eventQueue.postEvent(new ActionEvent(arg1, 1001, "dummy"));
		}
	}

	@OriginalMember(owner = "client!rc", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent arg0) {
		Static233.focus_in = false;
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
		if (Static39.frame == null) {
			return signLink == null || signLink.anApplet2 == this ? super.getAppletContext() : signLink.anApplet2.getAppletContext();
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "client!rc", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
		Static233.focus_in = true;
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
	public final synchronized void method926() {
		if (Static154.canvas != null) {
			Static154.canvas.removeFocusListener(this);
			Static154.canvas.getParent().remove(Static154.canvas);
		}
		@Pc(19) Container local19;
		if (Static69.aFrame2 != null) {
			local19 = Static69.aFrame2;
		} else if (Static39.frame == null) {
			local19 = signLink.anApplet2;
		} else {
			local19 = Static39.frame;
		}
		local19.setLayout(null);
		Static154.canvas = new GameCanvas(this);
		local19.add(Static154.canvas);
		Static154.canvas.setSize(Static48.canvasWidth, Static254.canvasHeigth);
		Static154.canvas.setVisible(true);
		if (local19 == Static39.frame) {
			@Pc(66) Insets local66 = Static39.frame.getInsets();
			Static154.canvas.setLocation(Static145.leftMargin + local66.left, local66.top + Static178.topMargin);
		} else {
			Static154.canvas.setLocation(Static145.leftMargin, Static178.topMargin);
		}
		Static154.canvas.addFocusListener(this);
		Static154.canvas.requestFocus();
		Static233.focus_in = true;
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
			PreciseSleep.sleep(5000L);
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
		if (Static39.frame == null) {
			return signLink == null || signLink.anApplet2 == this ? super.getDocumentBase() : signLink.anApplet2.getDocumentBase();
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
			if (local29 == null || local29.width >= Static72.frameWid && Static122.frameHei <= local29.height) {
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
		if (signLink.anApplet2 != null) {
			signLink.anApplet2.destroy();
		}
		try {
			this.mainquit();
		} catch (@Pc(34) Exception local34) {
		}
		if (Static154.canvas != null) {
			try {
				Static154.canvas.removeFocusListener(this);
				Static154.canvas.getParent().remove(Static154.canvas);
			} catch (@Pc(45) Exception local45) {
			}
		}
		if (signLink != null) {
			try {
				signLink.method5124();
			} catch (@Pc(53) Exception local53) {
			}
		}
		this.method929();
		if (Static39.frame != null) {
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
			Static26.focus = Static233.focus_in;
		}
		this.mainloop();
		if (local10 != 0L && local6 <= local10) {
		}
	}

	@OriginalMember(owner = "client!rc", name = "e", descriptor = "(I)V")
	private void mainredrawwrapper() {
		@Pc(2) long local2 = MonotonicTime.get();
		@Pc(6) long local6 = VarpDefinition.aLongArray2[Static111.anInt2903];
		VarpDefinition.aLongArray2[Static111.anInt2903] = local2;
		Static111.anInt2903 = Static111.anInt2903 + 1 & 0x1F;
		if (local6 != 0L && local2 > local6) {
			@Pc(41) int local41 = (int) (local2 - local6);
			Static243.fps = ((local41 >> 1) + 32000) / local41;
		}
		if (Static184.anInt4355++ > 50) {
			Static69.fullredraw = true;
			Static184.anInt4355 -= 50;
			Static154.canvas.setSize(Static48.canvasWidth, Static254.canvasHeigth);
			Static154.canvas.setVisible(true);
			if (Static39.frame != null && Static69.aFrame2 == null) {
				@Pc(84) Insets local84 = Static39.frame.getInsets();
				Static154.canvas.setLocation(local84.left + Static145.leftMargin, Static178.topMargin + local84.top);
			} else {
				Static154.canvas.setLocation(Static145.leftMargin, Static178.topMargin);
			}
		}
		this.mainredraw();
	}

	@OriginalMember(owner = "client!rc", name = "f", descriptor = "(I)V")
	protected abstract void mainredraw();

	@OriginalMember(owner = "client!rc", name = "getCodeBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getCodeBase() {
		if (Static39.frame == null) {
			return signLink == null || signLink.anApplet2 == this ? super.getCodeBase() : signLink.anApplet2.getCodeBase();
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
			if (signLink.anApplet2 != null) {
				@Pc(125) Method local125 = SignLink.aMethod5;
				if (local125 != null) {
					try {
						local125.invoke(signLink.anApplet2, Boolean.TRUE);
					} catch (@Pc(142) Throwable local142) {
					}
				}
			}
			Static224.method3888();
			this.method926();
			Static260.aClass27_2 = Static131.method2579(Static254.canvasHeigth, Static48.canvasWidth, Static154.canvas);
			this.method935();
			Static200.aClass93_1 = Static70.method1547();
			while (Static72.killtime == 0L || Static72.killtime > MonotonicTime.get()) {
				Static227.anInt5097 = Static200.aClass93_1.method3391(Static226.anInt5081, Static11.anInt386);
				for (local76 = 0; local76 < Static227.anInt5097; local76++) {
					this.mainloopwrapper();
				}
				this.mainredrawwrapper();
				method2708(signLink, Static154.canvas);
			}
		} catch (@Pc(198) Exception local198) {
			Static89.report(null, local198);
			this.error("crash");
		}
		this.shutdown(true);
	}

	@OriginalMember(owner = "client!rc", name = "getParameter", descriptor = "(Ljava/lang/String;)Ljava/lang/String;")
	@Override
	public final String getParameter(@OriginalArg(0) String arg0) {
		if (Static39.frame == null) {
			return signLink == null || signLink.anApplet2 == this ? super.getParameter(arg0) : signLink.anApplet2.getParameter(arg0);
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
			Static254.canvasHeigth = 768;
			Static122.frameHei = 768;
			Static145.leftMargin = 0;
			Static131.anInt3252 = 530;
			Static48.canvasWidth = 1024;
			Static72.frameWid = 1024;
			Static178.topMargin = 0;
			Static230.anApplet_Sub1_1 = this;
			Static39.frame = new Frame();
			Static39.frame.setTitle("Jagex");
			Static39.frame.setResizable(true);
			Static39.frame.addWindowListener(this);
			Static39.frame.setVisible(true);
			Static39.frame.toFront();
			@Pc(44) Insets local44 = Static39.frame.getInsets();
			Static39.frame.setSize(local44.left + Static72.frameWid + local44.right, local44.top + Static122.frameHei + local44.bottom);
			Static69.aClass213_4 = signLink = new SignLink(null, arg0, arg1, 28);
			@Pc(76) PrivilegedRequest local76 = signLink.method5130(1, this);
			while (local76.status == 0) {
				PreciseSleep.sleep(10L);
			}
			Static37.aThread1 = (Thread) local76.result;
		} catch (@Pc(91) Exception local91) {
			Static89.report(null, local91);
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
			Static178.topMargin = 0;
			Static131.anInt3252 = 1530;
			Static48.canvasWidth = 765;
			Static72.frameWid = 765;
			Static145.leftMargin = 0;
			Static254.canvasHeigth = 503;
			Static122.frameHei = 503;
			@Pc(54) String local54 = this.getParameter("openwinjs");
			if (local54 != null && local54.equals("1")) {
				Static40.aBoolean78 = true;
			} else {
				Static40.aBoolean78 = false;
			}
			if (signLink == null) {
				Static69.aClass213_4 = signLink = new SignLink(this, arg0, null, 0);
			}
			@Pc(86) PrivilegedRequest local86 = signLink.method5130(1, this);
			while (local86.status == 0) {
				PreciseSleep.sleep(10L);
			}
			Static37.aThread1 = (Thread) local86.result;
		} catch (@Pc(103) Exception local103) {
			Static89.report(null, local103);
			this.error("crash");
		}
	}
}
