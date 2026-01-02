package com.jagex.js5;

import java.io.IOException;

import com.jagex.network.BufferedSocket;
import com.jagex.core.utils.SystemTimer;
import com.jagex.core.io.Packet;
import com.jagex.js5.network.Js5NetRequest;
import com.jagex.core.datastruct.Queue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!jb")
public final class Js5NetQueue {

	private static final int PREFETCH_LIMIT = 20;
	private static final int URGENT_LIMIT = 20;
	private static final int LATENCY_LIMIT = 30000;

	@OriginalMember(owner = "runetek4.client!jb", name = "A", descriptor = "J")
	private long lastAttempt;

	@OriginalMember(owner = "runetek4.client!jb", name = "B", descriptor = "Lclient!ma;")
	private BufferedSocket updateServerSocket;

	@OriginalMember(owner = "runetek4.client!jb", name = "C", descriptor = "I")
	private int latency;

	@OriginalMember(owner = "runetek4.client!jb", name = "J", descriptor = "Lclient!pm;")
	private Js5NetRequest current;

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "Lclient!ce;")
	private final Queue urgent = new Queue();

	@OriginalMember(owner = "runetek4.client!jb", name = "q", descriptor = "Lclient!ce;")
	private final Queue inFlightUrgentRequests = new Queue();

	@OriginalMember(owner = "runetek4.client!jb", name = "v", descriptor = "Lclient!ce;")
	private final Queue prefetch = new Queue();

	@OriginalMember(owner = "runetek4.client!jb", name = "z", descriptor = "Lclient!ce;")
	private final Queue inFlightPrefetchRequests = new Queue();

	@OriginalMember(owner = "client!jb", name = "E", descriptor = "Lclient!wa;")
	private final Packet outPacket = new Packet(4);

	@OriginalMember(owner = "client!jb", name = "G", descriptor = "B")
	private byte encryptionKey = 0;

	@OriginalMember(owner = "client!jb", name = "I", descriptor = "I")
	public volatile int errors = 0;

	@OriginalMember(owner = "client!jb", name = "H", descriptor = "I")
	public volatile int response = Js5ResponseCode.OK;

	@OriginalMember(owner = "client!jb", name = "F", descriptor = "Lclient!wa;")
	private final Packet inPacket = new Packet(8);

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(I)Z")
	public boolean isPrefetchRequestQueueFull() {
		return this.getPrefetchRequestCount() >= 20;
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(B)Z")
	public boolean loop() {
		@Pc(19) int available;
		if (this.updateServerSocket != null) {
			@Pc(12) long now = SystemTimer.safetime();
			int deltaTime = (int) (now - this.lastAttempt);
			this.lastAttempt = now;
			if (deltaTime > 200) {
				deltaTime = 200;
			}
			this.latency += deltaTime;
			if (this.latency > LATENCY_LIMIT) {
				try {
					this.updateServerSocket.closeGracefully();
				} catch (@Pc(43) Exception ignored) {
				}
				this.updateServerSocket = null;
			}
		}
		if (this.updateServerSocket == null) {
			return this.getUrgentRequestCount() == 0 && this.getPrefetchRequestCount() == 0;
		}
		try {
			this.updateServerSocket.checkError();
			@Pc(75) Js5NetRequest message;
			for (message = (Js5NetRequest) this.urgent.first(); message != null; message = (Js5NetRequest) this.urgent.next()) {
				this.outPacket.offset = 0;
				this.outPacket.p1(Js5RequestCode.URGENT);
				this.outPacket.p3((int) message.key2);
				this.updateServerSocket.write(4, this.outPacket.data);
				this.inFlightUrgentRequests.add(message);
			}
			for (message = (Js5NetRequest) this.prefetch.first(); message != null; message = (Js5NetRequest) this.prefetch.next()) {
				this.outPacket.offset = 0;
				this.outPacket.p1(Js5RequestCode.PREFETCH);
				this.outPacket.p3((int) message.key2);
				this.updateServerSocket.write(4, this.outPacket.data);
				this.inFlightPrefetchRequests.add(message);
			}
			for (@Pc(172) int i = 0; i < 100; i++) {
				available = this.updateServerSocket.available();
				if (available < 0) {
					throw new IOException();
				}
				if (available == 0) {
					break;
				}
				this.latency = 0;
				@Pc(196) byte needed = 0;
				if (this.current == null) {
					needed = 8;
				} else if (this.current.blockPosition == 0) {
					needed = 1;
				}
				@Pc(228) int remaining;
				@Pc(235) int archive;
				@Pc(283) int group;
				if (needed <= 0) {
					remaining = this.current.packet.data.length - this.current.offset;
					archive = 512 - this.current.blockPosition;
					if (archive > remaining - this.current.packet.offset) {
						archive = remaining - this.current.packet.offset;
					}
					if (archive > available) {
						archive = available;
					}
					this.updateServerSocket.read(this.current.packet.offset, archive, this.current.packet.data);
					if (this.encryptionKey != 0) {
						for (group = 0; group < archive; group++) {
							this.current.packet.data[this.current.packet.offset + group] = (byte) (this.current.packet.data[this.current.packet.offset + group] ^ this.encryptionKey);
						}
					}
					this.current.blockPosition += archive;
					this.current.packet.offset += archive;
					if (this.current.packet.offset == remaining) {
						this.current.unlink2();
						this.current.awaitingResponse = false;
						this.current = null;
					} else if (this.current.blockPosition == 512) {
						this.current.blockPosition = 0;
					}
				} else {
					remaining = needed - this.inPacket.offset;
					if (remaining > available) {
						remaining = available;
					}
					this.updateServerSocket.read(this.inPacket.offset, remaining, this.inPacket.data);
					if (this.encryptionKey != 0) {
						for (archive = 0; archive < remaining; archive++) {
							this.inPacket.data[archive + this.inPacket.offset] ^= this.encryptionKey;
						}
					}
					this.inPacket.offset += remaining;
					if (this.inPacket.offset >= needed) {
						if (this.current == null) {
							this.inPacket.offset = 0;

							archive = this.inPacket.g1();
							group = this.inPacket.g2();
							@Pc(471) int flags = this.inPacket.g1();
							@Pc(480) int compressionType = flags & 0x7F;
							@Pc(491) boolean prefect = (flags & 0x80) != 0;
							@Pc(476) int length = this.inPacket.g4();
							@Pc(501) long key = (archive << 16) + group;
							@Pc(568) int headerLength = compressionType == 0 ? 5 : 9;
							@Pc(509) Js5NetRequest request;
							if (prefect) {
								for (request = (Js5NetRequest) this.inFlightPrefetchRequests.first(); request != null && request.key2 != key; request = (Js5NetRequest) this.inFlightPrefetchRequests.next()) {
								}
							} else {
								for (request = (Js5NetRequest) this.inFlightUrgentRequests.first(); request != null && request.key2 != key; request = (Js5NetRequest) this.inFlightUrgentRequests.next()) {
								}
							}

							if (request == null) {
								throw new IOException();
							}

							this.current = request;
							this.current.packet = new Packet(length + headerLength + this.current.offset);
							this.current.packet.p1(compressionType);
							this.current.packet.p4(length);
							this.current.blockPosition = 8;
							this.inPacket.offset = 0;
						} else if (this.current.blockPosition != 0) {
							throw new IOException("BlockPosition is not 0");
						} else if (this.inPacket.data[0] == -1) {
							this.current.blockPosition = 1;
							this.inPacket.offset = 0;
						} else {
							this.current = null;
						}
					}
				}
			}
			return true;
		} catch (@Pc(644) IOException exception) {
			try {
				this.updateServerSocket.closeGracefully();
			} catch (@Pc(650) Exception ignored) {
			}
			exception.printStackTrace();
			this.response = Js5ResponseCode.CONNECTED;
			this.errors++;
			this.updateServerSocket = null;
			return this.getUrgentRequestCount() == 0 && this.getPrefetchRequestCount() == 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(Z)V")
	public void serverDrop() {
		if (this.updateServerSocket == null) {
			return;
		}
		try {
			this.outPacket.offset = 0;
			this.outPacket.p1(Js5RequestCode.DISCONNECTED);
			this.outPacket.p3(0);
			this.updateServerSocket.write(4, this.outPacket.data);
		} catch (@Pc(39) IOException exception) {
			try {
				this.updateServerSocket.closeGracefully();
			} catch (@Pc(45) Exception ignored) {
			}
			exception.printStackTrace();
			this.errors++;
			this.response = Js5ResponseCode.CONNECTED;
			this.updateServerSocket = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "b", descriptor = "(I)I")
	private int getPrefetchRequestCount() {
		return this.prefetch.size() + this.inFlightPrefetchRequests.size();
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(ZZ)V")
	public void writeLoggedIn(@OriginalArg(0) boolean loggedIn) {
		if (this.updateServerSocket == null) {
			return;
		}
		try {
			this.outPacket.offset = 0;
			this.outPacket.p1(loggedIn ? Js5RequestCode.LOGGED_IN : Js5RequestCode.LOGGED_OUT);
			this.outPacket.p3(0);
			this.updateServerSocket.write(4, this.outPacket.data);
		} catch (@Pc(42) IOException exception) {
			try {
				this.updateServerSocket.closeGracefully();
			} catch (@Pc(48) Exception ignored) {
			}
			exception.printStackTrace();
			this.errors++;
			this.response = Js5ResponseCode.CONNECTED;
			this.updateServerSocket = null;
		}
	}

	@OriginalMember(owner = "client!jb", name = "c", descriptor = "(I)V")
	public void breakConnection() {
		if (this.updateServerSocket != null) {
			this.updateServerSocket.breakConnection();
		}
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ZLclient!ma;I)V")
	public void start(@OriginalArg(0) boolean isLoggedIn, @OriginalArg(1) BufferedSocket socket) {
		if (this.updateServerSocket != null) {
			try {
				this.updateServerSocket.closeGracefully();
			} catch (@Pc(14) Exception ignored) {
			}
			this.updateServerSocket = null;
		}
		this.updateServerSocket = socket;
		this.connected();
		this.writeLoggedIn(isLoggedIn);
		this.inPacket.offset = 0;
		this.current = null;
		while (true) {
			@Pc(44) Js5NetRequest local44 = (Js5NetRequest) this.inFlightUrgentRequests.removeFirst();
			if (local44 == null) {
				while (true) {
					local44 = (Js5NetRequest) this.inFlightPrefetchRequests.removeFirst();
					if (local44 == null) {
						if (this.encryptionKey != 0) {
							try {
								this.outPacket.offset = 0;
								this.outPacket.p1(Js5RequestCode.ENCRYPTION);
								this.outPacket.p1(this.encryptionKey);
								this.outPacket.p2(0);
								this.updateServerSocket.write(4, this.outPacket.data);
							} catch (@Pc(107) IOException exception) {
								try {
									this.updateServerSocket.closeGracefully();
								} catch (@Pc(113) Exception ignored) {
								}
								exception.printStackTrace();
								this.response = Js5ResponseCode.CONNECTED;
								this.errors++;
								this.updateServerSocket = null;
							}
						}
						this.latency = 0;
						this.lastAttempt = SystemTimer.safetime();
						return;
					}
					this.prefetch.add(local44);
				}
			}
			this.urgent.add(local44);
		}
	}

	@OriginalMember(owner = "client!jb", name = "c", descriptor = "(B)Z")
	public boolean isUrgentsFull() {
		return this.getUrgentRequestCount() >= URGENT_LIMIT;
	}

	@OriginalMember(owner = "client!jb", name = "d", descriptor = "(B)V")
	public void rekey() {
		try {
			this.updateServerSocket.closeGracefully();
		} catch (@Pc(17) Exception ignored) {
		}
		this.response = Js5ResponseCode.DISCONNECTED;
		this.encryptionKey = (byte) (Math.random() * 255.0D + 1.0D);
		this.updateServerSocket = null;
		this.errors++;
	}

	@OriginalMember(owner = "client!jb", name = "d", descriptor = "(I)I")
	public int getUrgentRequestCount() {
		return this.urgent.size() + this.inFlightUrgentRequests.size();
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(Z)V")
	public void clientDrop() {
		if (this.updateServerSocket != null) {
			this.updateServerSocket.closeGracefully();
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(IIBIZ)Lclient!pm;")
	public final Js5NetRequest read(@OriginalArg(1) int archiveId, @OriginalArg(2) byte offset, @OriginalArg(3) int groupId, @OriginalArg(4) boolean urgent) {
		@Pc(7) Js5NetRequest message = new Js5NetRequest();
		@Pc(14) long key = (long) (groupId + (archiveId << 16));
		message.urgent = urgent;
		message.key2 = key;
		message.offset = offset;
		if (urgent) {
			if (this.getUrgentRequestCount() >= URGENT_LIMIT) {
				throw new RuntimeException();
			}
			this.urgent.add(message);
		} else if (this.getPrefetchRequestCount() < PREFETCH_LIMIT) {
			this.prefetch.add(message);
		} else {
			throw new RuntimeException();
		}
		return message;
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "e", descriptor = "(B)V")
	private void connected() {
		if (this.updateServerSocket == null) {
			return;
		}
		try {
			this.outPacket.offset = 0;
			this.outPacket.p1(Js5RequestCode.CONNECTED);
			this.outPacket.p3(3);
			this.updateServerSocket.write(4, this.outPacket.data);
		} catch (@Pc(37) IOException exception) {
			try {
				this.updateServerSocket.closeGracefully();
			} catch (@Pc(43) Exception ignored) {
			}
			exception.printStackTrace();
			this.errors++;
			this.updateServerSocket = null;
			this.response = Js5ResponseCode.CONNECTED;
		}
	}
}
