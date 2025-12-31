package com.jagex.sign;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;

import com.jagex.core.utils.SystemTimer;
import com.jagex.graphics.awt.AwtFullscreenAdapter;
import com.jagex.graphics.awt.AwtMouseAdapter;
import com.jagex.runetek4.*;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!ll")
public final class SignLink implements Runnable {

	@OriginalMember(owner = "signlink!ll", name = "o", descriptor = "Ljava/lang/String;")
	public static String javaVersion;

	@OriginalMember(owner = "signlink!ll", name = "n", descriptor = "Ljava/lang/String;")
	public static String osName;

	@OriginalMember(owner = "signlink!ll", name = "l", descriptor = "Ljava/lang/String;")
	private static String osNameRaw;

	@OriginalMember(owner = "signlink!ll", name = "v", descriptor = "Ljava/lang/String;")
	private static String homeDir;

	@OriginalMember(owner = "signlink!ll", name = "j", descriptor = "Ljava/lang/String;")
	private static String osVersion;

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "Ljava/lang/String;")
	public static String javaVendor;

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "Ljava/lang/String;")
	private static String osArch;

	@OriginalMember(owner = "signlink!ll", name = "r", descriptor = "Ljava/lang/reflect/Method;")
	public static Method setFocusTraversalKeysEnabled;

	@OriginalMember(owner = "signlink!ll", name = "e", descriptor = "I")
	public static final int anInt5928 = 1;

	@OriginalMember(owner = "signlink!ll", name = "w", descriptor = "Ljava/util/Hashtable;")
	private static final Hashtable fileCache = new Hashtable(16);

	@OriginalMember(owner = "signlink!ll", name = "q", descriptor = "J")
	private static volatile long breakConnectionsUntil = 0L;

	@OriginalMember(owner = "signlink!ll", name = "A", descriptor = "Lsignlink!ai;")
	private AudioSource audioSource;

	@OriginalMember(owner = "signlink!ll", name = "g", descriptor = "Lsignlink!qm;")
	public FileOnDisk cacheData = null;

	@OriginalMember(owner = "signlink!ll", name = "p", descriptor = "Lsignlink!im;")
	private SignedResource requestQueueTail = null;

	@OriginalMember(owner = "signlink!ll", name = "f", descriptor = "Z")
	private boolean stop = false;

	@OriginalMember(owner = "signlink!ll", name = "h", descriptor = "Lsignlink!qm;")
	public FileOnDisk cacheMasterIndex = null;

	@OriginalMember(owner = "signlink!ll", name = "d", descriptor = "Lsignlink!qm;")
	public FileOnDisk uid = null;

	@OriginalMember(owner = "signlink!ll", name = "y", descriptor = "Lsignlink!im;")
	private SignedResource requestQueueHead = null;

	@OriginalMember(owner = "signlink!ll", name = "x", descriptor = "Ljava/lang/String;")
	private final String cacheSubDir;

	@OriginalMember(owner = "signlink!ll", name = "z", descriptor = "I")
	private final int cacheId;

	@OriginalMember(owner = "signlink!ll", name = "k", descriptor = "Ljava/awt/EventQueue;")
	public EventQueue eventQueue;

	@OriginalMember(owner = "signlink!ll", name = "c", descriptor = "[Lsignlink!qm;")
	public FileOnDisk[] cacheIndexes;

	@OriginalMember(owner = "signlink!ll", name = "t", descriptor = "Lsignlink!e;")
	private AwtFullscreenAdapter fullScreenManager;

	@OriginalMember(owner = "signlink!ll", name = "s", descriptor = "Lsignlink!g;")
	private AwtMouseAdapter cursorManager;

	@OriginalMember(owner = "signlink!ll", name = "m", descriptor = "Ljava/lang/Thread;")
	private final Thread thread;

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ZLjava/lang/String;)Lsignlink!qm;")
	private static FileOnDisk method5117(@OriginalArg(1) String cacheSubDir) {
		@Pc(41) String[] cacheLocations = new String[] { "c:/rscache/", "/rscache/", homeDir, "c:/windows/", "c:/winnt/", "c:/", "/tmp/", "" };
		for (@Pc(43) int i = 0; i < cacheLocations.length; i++) {
			@Pc(51) String cacheLocation = cacheLocations[i];
			if (cacheLocation.length() <= 0 || (new File(cacheLocation)).exists()) {
				try {
					return new FileOnDisk(new File(cacheLocation, "jagex_" + cacheSubDir + "_preferences.dat"), "rw", 10000L);
				} catch (@Pc(84) Exception ignored) {
				}
			}
		}
		return null;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;IZLjava/lang/String;)Ljava/io/File;")
	public static File getFile(@OriginalArg(0) String cacheSubDir, @OriginalArg(1) int storeId, @OriginalArg(3) String arg2) {
		@Pc(4) File cachedFile = (File) fileCache.get(arg2);
		if (cachedFile != null) {
			return cachedFile;
		}
		@Pc(53) String[] cacheLocations = new String[] { "c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", homeDir, "/tmp/", "" };
		@Pc(78) String[] cacheDirs = new String[] { ".jagex_cache_" + storeId, ".file_store_" + storeId };
		for (@Pc(80) int attempt = 0; attempt < 2; attempt++) {
			for (@Pc(87) int i = 0; i < cacheDirs.length; i++) {
				for (@Pc(93) int j = 0; j < cacheLocations.length; j++) {
					@Pc(128) String path = cacheLocations[j] + cacheDirs[i] + "/" + (cacheSubDir == null ? "" : cacheSubDir + "/") + arg2;
					@Pc(130) RandomAccessFile randomAccessFile = null;
					try {
						@Pc(135) File file = new File(path);
						if (attempt != 0 || file.exists()) {
							@Pc(145) String cacheLocation = cacheLocations[j];
							if (attempt != 1 || cacheLocation.length() <= 0 || (new File(cacheLocation)).exists()) {
								(new File(cacheLocations[j] + cacheDirs[i])).mkdir();
								if (cacheSubDir != null) {
									(new File(cacheLocations[j] + cacheDirs[i] + "/" + cacheSubDir)).mkdir();
								}
								randomAccessFile = new RandomAccessFile(file, "rw");
								@Pc(210) int firstByte = randomAccessFile.read();
								randomAccessFile.seek(0L);
								randomAccessFile.write(firstByte);
								randomAccessFile.seek(0L);
								randomAccessFile.close();
								fileCache.put(arg2, file);
								return file;
							}
						}
					} catch (@Pc(229) Exception ex) {
						try {
							if (randomAccessFile != null) {
								randomAccessFile.close();
							}
						} catch (@Pc(239) Exception ignored) {
						}
					}
				}
			}
		}
		throw new RuntimeException();
	}

	@OriginalMember(owner = "signlink!ll", name = "<init>", descriptor = "(Ljava/applet/Applet;ILjava/lang/String;I)V")
	public SignLink(@OriginalArg(1) int arg1, @OriginalArg(2) String arg2, @OriginalArg(3) int arg3) throws Exception {
		javaVersion = "1.1";
		this.cacheSubDir = arg2;
		this.cacheId = arg1;
		javaVendor = "Unknown";
		try {
			javaVendor = System.getProperty("java.vendor");
			javaVersion = System.getProperty("java.version");
		} catch (@Pc(43) Exception ignored) {
		}
		try {
			osNameRaw = System.getProperty("os.name");
		} catch (@Pc(48) Exception exception) {
			osNameRaw = "Unknown";
		}
		osName = osNameRaw.toLowerCase();
		try {
			osArch = System.getProperty("os.arch").toLowerCase();
		} catch (@Pc(59) Exception exception) {
			osArch = "";
		}
		try {
			osVersion = System.getProperty("os.version").toLowerCase();
		} catch (@Pc(67) Exception exception) {
			osVersion = "";
		}
		try {
			homeDir = System.getProperty("user.home");
			if (homeDir != null) {
				homeDir = homeDir + "/";
			}
		} catch (@Pc(86) Exception ignored) {
		}
		if (homeDir == null) {
			homeDir = "~/";
		}
		try {
			this.eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
		} catch (@Pc(97) Throwable ignored) {
		}
		this.uid = new FileOnDisk(getFile(null, this.cacheId, "random.dat"), "rw", 25L);
		this.cacheData = new FileOnDisk(getFile(this.cacheSubDir, this.cacheId, "main_file_cache.dat2"), "rw", 104857600L);
		this.cacheMasterIndex = new FileOnDisk(getFile(this.cacheSubDir, this.cacheId, "main_file_cache.idx255"), "rw", 1048576L);
		this.cacheIndexes = new FileOnDisk[arg3];
		for (@Pc(200) int i = 0; i < arg3; i++) {
			this.cacheIndexes[i] = new FileOnDisk(getFile(this.cacheSubDir, this.cacheId, "main_file_cache.idx" + i), "rw", 1048576L);
		}
		try {
			this.fullScreenManager = new AwtFullscreenAdapter();
		} catch (@Pc(239) Throwable ignored) {
		}
		try {
			this.cursorManager = new AwtMouseAdapter();
		} catch (@Pc(246) Throwable ignored) {
		}
		@Pc(249) ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
		for (@Pc(252) ThreadGroup group = rootGroup.getParent(); group != null; group = group.getParent()) {
			rootGroup = group;
		}
		@Pc(263) Thread[] threads = new Thread[1000];
		rootGroup.enumerate(threads);
		for (@Pc(269) int i = 0; i < threads.length; i++) {
			if (threads[i] != null && threads[i].getName().startsWith("AWT")) {
				threads[i].setPriority(1);
			}
		}
		this.stop = false;
		this.thread = new Thread(this);
		this.thread.setPriority(10);
		this.thread.setDaemon(true);
		this.thread.start();
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(I)V")
	public final void breakConnection() {
		breakConnectionsUntil = SystemTimer.safetime() + 5000L;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Z)Z")
	public final boolean isFullScreenSupported() {
		return this.fullScreenManager != null;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;I)Lsignlink!im;")
	public final SignedResource openPreferences(@OriginalArg(0) String cacheSubDir) {
		return this.enqueue(12, 0, cacheSubDir, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "([IIILjava/awt/Component;Ljava/awt/Point;I)Lsignlink!im;")
	public final SignedResource setCursor(@OriginalArg(0) int[] pixels, @OriginalArg(2) int width, @OriginalArg(3) Component component, @OriginalArg(4) Point hotSpot, @OriginalArg(5) int height) {
		return this.enqueue(17, height, new Object[] { component, pixels, hotSpot }, width);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IILjava/lang/Object;II)Lsignlink!im;")
	private SignedResource enqueue(@OriginalArg(0) int type, @OriginalArg(1) int arg1, @OriginalArg(2) Object objectArg, @OriginalArg(3) int arg3) {
		@Pc(3) SignedResource request = new SignedResource();
		request.intArg1 = arg1;
		request.intArg2 = arg3;
		request.type = type;
		request.objectArg = objectArg;
		synchronized (this) {
			if (this.requestQueueTail == null) {
				this.requestQueueTail = this.requestQueueHead = request;
			} else {
				this.requestQueueTail.next = request;
				this.requestQueueTail = request;
			}
			this.notify();
			return request;
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/awt/Frame;I)Lsignlink!im;")
	public final SignedResource exitFullScreen(@OriginalArg(0) Frame frame) {
		return this.enqueue(7, 0, frame, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(BLjava/lang/String;I)Lsignlink!im;")
	public final SignedResource openSocket(@OriginalArg(1) String hostname, @OriginalArg(2) int port) {
		System.out.println("openSocket(" + hostname + ":" + port + ")");
		return this.enqueue(1, 0, hostname, port);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/Class;I)Lsignlink!im;")
	public final SignedResource unloadGlNatives(@OriginalArg(0) Class targetClass) {
		return this.enqueue(11, 0, targetClass, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/Class;[Ljava/lang/Class;ILjava/lang/String;)Lsignlink!im;")
	public final SignedResource getDeclaredMethod(@OriginalArg(0) Class targetClass, @OriginalArg(1) Class[] parameterTypes, @OriginalArg(3) String name) {
		return this.enqueue(8, 0, new Object[] { targetClass, name, parameterTypes }, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (true) {
			@Pc(16) SignedResource local16;
			synchronized (this) {
				while (true) {
					if (this.stop) {
						return;
					}
					if (this.requestQueueHead != null) {
						local16 = this.requestQueueHead;
						this.requestQueueHead = this.requestQueueHead.next;
						if (this.requestQueueHead == null) {
							this.requestQueueTail = null;
						}
						break;
					}
					try {
						this.wait();
					} catch (@Pc(33) InterruptedException ignored) {
					}
				}
			}
			try {
				@Pc(45) int local45 = local16.type;
				if (local45 == 1) {
					if (breakConnectionsUntil > SystemTimer.safetime()) {
						throw new IOException();
					}
					local16.result = new Socket(InetAddress.getByName((String) local16.objectArg), local16.intArg2);
				} else if (local45 == 2) {
					@Pc(813) Thread local813 = new Thread((Runnable) local16.objectArg);
					local813.setDaemon(true);
					local813.start();
					local813.setPriority(local16.intArg2);
					local16.result = local813;
				} else if (local45 == 4) {
					if (breakConnectionsUntil > SystemTimer.safetime()) {
						throw new IOException();
					}
					local16.result = new DataInputStream(((URL) local16.objectArg).openStream());
				} else {
					@Pc(687) Object[] local687;
					if (local45 == 8) {
						local687 = (Object[]) local16.objectArg;
						if (((Class) local687[0]).getClassLoader() == null) {
							throw new SecurityException();
						}
						local16.result = ((Class) local687[0]).getDeclaredMethod((String) local687[1], (Class[]) local687[2]);
					} else if (local45 == 9) {
						local687 = (Object[]) local16.objectArg;
						if (((Class) local687[0]).getClassLoader() == null) {
							throw new SecurityException();
						}
						local16.result = ((Class) local687[0]).getDeclaredField((String) local687[1]);
					} else {
						@Pc(147) String local147;
						if (local45 == 3) {
							if (SystemTimer.safetime() < breakConnectionsUntil) {
								throw new IOException();
							}
							local147 = (local16.intArg2 >> 24 & 0xFF) + "." + (local16.intArg2 >> 16 & 0xFF) + "." + (local16.intArg2 >> 8 & 0xFF) + "." + (local16.intArg2 & 0xFF);
							local16.result = InetAddress.getByName(local147).getHostName();
						} else if (local45 == 5) {
							local16.result = this.fullScreenManager.listmodes();
						} else if (local45 == 6) {
							@Pc(168) Frame local168 = new Frame("Jagex Full Screen");
							local16.result = local168;
							local168.setResizable(false);
							this.fullScreenManager.method5104(local16.intArg1 & 0xFFFF, local16.intArg1 >> 16, local16.intArg2 & 0xFFFF, local168, local16.intArg2 >>> 16);
						} else if (local45 == 7) {
							this.fullScreenManager.exit();
						} else if (local45 == 10) {
							// TODO remove this if it's not causing any issues
							// initSingleton() should not be necessary on newer JOGL
                            //GLProfile.initSingleton();
						} else {
							@Pc(490) int local490;
							if (local45 == 11) {
								GLProfile.shutdown();
							} else if (local45 == 12) {
								local147 = (String) local16.objectArg;
								@Pc(558) FileOnDisk local558 = method5117(local147);
								local16.result = local558;
							} else if (local45 == 14) {
								@Pc(570) int local570 = local16.intArg1;
								@Pc(573) int local573 = local16.intArg2;
								this.cursorManager.movemouse(local573, local570);
							} else if (local45 == 15) {
								@Pc(591) boolean local591 = local16.intArg2 != 0;
								@Pc(595) Component local595 = (Component) local16.objectArg;
								this.cursorManager.showcursor(local595, local591);
							} else if (local45 == 17) {
								local687 = (Object[]) local16.objectArg;
								this.cursorManager.setcustomcursor((Point) local687[2], local16.intArg2, (Component) local687[0], local16.intArg1, (int[]) local687[1]);
							} else if (local45 == 16) {
								try {
									if (!osName.startsWith("win")) {
										throw new Exception();
									}
									local147 = (String) local16.objectArg;
									if (!local147.startsWith("http://") && !local147.startsWith("https://")) {
										throw new Exception();
									}
									@Pc(636) String local636 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?&=,.%+-_#:/*";
									for (local490 = 0; local490 < local147.length(); local490++) {
										if (local636.indexOf(local147.charAt(local490)) == -1) {
											throw new Exception();
										}
									}
									Runtime.getRuntime().exec("cmd /c start \"j\" \"" + local147 + "\"");
									local16.result = null;
								} catch (@Pc(674) Exception local674) {
									local16.result = local674;
								}
							} else {
								throw new Exception();
							}
						}
					}
				}
				local16.status = 1;
			} catch (@Pc(830) ThreadDeath local830) {
				throw local830;
			} catch (@Pc(833) Throwable local833) {
				local16.status = 2;
			}
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ILjava/lang/Class;)Lsignlink!im;")
	public final SignedResource loadGlNatives(@OriginalArg(1) Class targetClass) {
		return this.enqueue(10, 0, targetClass, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "(I)V")
	public final void stop() {
		synchronized (this) {
			this.stop = true;
			this.notifyAll();
		}
		try {
			this.thread.join();
		} catch (@Pc(21) InterruptedException ignored) {
		}
		if (this.cacheData != null) {
			try {
				this.cacheData.close();
			} catch (@Pc(39) IOException ignored) {
			}
		}
		if (this.cacheMasterIndex != null) {
			try {
				this.cacheMasterIndex.close();
			} catch (@Pc(49) IOException ignored) {
			}
		}
		if (this.cacheIndexes != null) {
			for (@Pc(55) int i = 0; i < this.cacheIndexes.length; i++) {
				if (this.cacheIndexes[i] != null) {
					try {
						this.cacheIndexes[i].close();
					} catch (@Pc(79) IOException ignored) {
					}
				}
			}
		}
		if (this.uid != null) {
			try {
				this.uid.close();
			} catch (@Pc(93) IOException ignored) {
			}
		}
	}

	@OriginalMember(owner = "signlink!ll", name = "b", descriptor = "(B)Lsignlink!ai;")
	public final AudioSource getAudioSource() {
		return this.audioSource;
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(ILjava/lang/String;Ljava/lang/Class;)Lsignlink!im;")
	public final SignedResource getDeclaredField(@OriginalArg(1) String name, @OriginalArg(2) Class targetClass) {
		return this.enqueue(9, 0, new Object[] { targetClass, name }, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(II)Lsignlink!im;")
	public final SignedResource getReverseDns(@OriginalArg(1) int ip) {
		return this.enqueue(3, 0, null, ip);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IIIII)Lsignlink!im;")
	public final SignedResource enterFullScreen(@OriginalArg(1) int bitDepth, @OriginalArg(2) int height, @OriginalArg(3) int width) {
		return this.enqueue(6, bitDepth << 16, null, (width << 16) + height);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(IILjava/lang/Runnable;)Lsignlink!im;")
	public final SignedResource startThread(@OriginalArg(1) int priority, @OriginalArg(2) Runnable runnable) {
		return this.enqueue(2, 0, runnable, priority);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(Ljava/lang/String;Z)Lsignlink!im;")
	public final SignedResource openUrl(@OriginalArg(0) String url) {
		return this.enqueue(16, 0, url, 0);
	}

	@OriginalMember(owner = "signlink!ll", name = "a", descriptor = "(B)Lsignlink!im;")
	public final SignedResource getDisplayModes() {
		return this.enqueue(5, 0, null, 0);
	}
}
