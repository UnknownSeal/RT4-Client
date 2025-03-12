package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.network.Js5NetRequest;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!jb")
public final class Js5NetQueue {

	@OriginalMember(owner = "runetek4.client!jb", name = "A", descriptor = "J")
	private long aLong104;

	@OriginalMember(owner = "runetek4.client!jb", name = "B", descriptor = "Lclient!ma;")
	private BufferedSocket socket;

	@OriginalMember(owner = "runetek4.client!jb", name = "C", descriptor = "I")
	private int latency;

	@OriginalMember(owner = "runetek4.client!jb", name = "J", descriptor = "Lclient!pm;")
	private Js5NetRequest current;

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList urgent = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!jb", name = "q", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList inFlightUrgentRequests = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!jb", name = "v", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList prefetch = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!jb", name = "z", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList inFlightPrefetchRequests = new SecondaryLinkedList();

	@OriginalMember(owner = "client!jb", name = "E", descriptor = "Lclient!wa;")
	private final Packet outBuffer = new Packet(4);

	@OriginalMember(owner = "client!jb", name = "G", descriptor = "B")
	private byte encryptionKey = 0;

	@OriginalMember(owner = "client!jb", name = "I", descriptor = "I")
	public volatile int errors = 0;

	@OriginalMember(owner = "client!jb", name = "H", descriptor = "I")
	public volatile int response = 0;

	@OriginalMember(owner = "client!jb", name = "F", descriptor = "Lclient!wa;")
	private final Packet inBuffer = new Packet(8);

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(I)Z")
	public boolean isPrefetchRequestQueueFull() {
		return this.getPrefetchRequestCount() >= 20;
	}

	@OriginalMember(owner = "client!jb", name = "b", descriptor = "(B)Z")
	public boolean loop() {
		@Pc(19) int available;
		if (this.socket != null) {
			@Pc(12) long now = MonotonicTime.get();
			int duration = (int) (now - this.aLong104);
			this.aLong104 = now;
			if (duration > 200) {
				duration = 200;
			}
			this.latency += duration;
			if (this.latency > 30000) {
				try {
					this.socket.closeGracefully();
				} catch (@Pc(43) Exception local43) {
				}
				this.socket = null;
			}
		}
		if (this.socket == null) {
			return this.getUrgentRequestCount() == 0 && this.getPrefetchRequestCount() == 0;
		}
		try {
			this.socket.checkError();
			@Pc(75) Js5NetRequest local75;
			for (local75 = (Js5NetRequest) this.urgent.head(); local75 != null; local75 = (Js5NetRequest) this.urgent.prev()) {
				this.outBuffer.position = 0;
				this.outBuffer.p1(1);
				this.outBuffer.p3((int) local75.secondaryNodeId);
				this.socket.write(4, this.outBuffer.data);
				this.inFlightUrgentRequests.pushBack(local75);
			}
			for (local75 = (Js5NetRequest) this.prefetch.head(); local75 != null; local75 = (Js5NetRequest) this.prefetch.prev()) {
				this.outBuffer.position = 0;
				this.outBuffer.p1(0);
				this.outBuffer.p3((int) local75.secondaryNodeId);
				this.socket.write(4, this.outBuffer.data);
				this.inFlightPrefetchRequests.pushBack(local75);
			}
			for (@Pc(172) int i = 0; i < 100; i++) {
				available = this.socket.available();
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
					if (archive > remaining - this.current.packet.position) {
						archive = remaining - this.current.packet.position;
					}
					if (archive > available) {
						archive = available;
					}
					this.socket.read(this.current.packet.position, archive, this.current.packet.data);
					if (this.encryptionKey != 0) {
						for (group = 0; group < archive; group++) {
							this.current.packet.data[this.current.packet.position + group] = (byte) (this.current.packet.data[this.current.packet.position + group] ^ this.encryptionKey);
						}
					}
					this.current.blockPosition += archive;
					this.current.packet.position += archive;
					if (this.current.packet.position == remaining) {
						this.current.secondaryRemove();
						this.current.awaitingResponse = false;
						this.current = null;
					} else if (this.current.blockPosition == 512) {
						this.current.blockPosition = 0;
					}
				} else {
					remaining = needed - this.inBuffer.position;
					if (remaining > available) {
						remaining = available;
					}
					this.socket.read(this.inBuffer.position, remaining, this.inBuffer.data);
					if (this.encryptionKey != 0) {
						for (archive = 0; archive < remaining; archive++) {
							this.inBuffer.data[archive + this.inBuffer.position] ^= this.encryptionKey;
						}
					}
					this.inBuffer.position += remaining;
					if (this.inBuffer.position >= needed) {
						if (this.current == null) {
							this.inBuffer.position = 0;

							archive = this.inBuffer.g1();
							group = this.inBuffer.g2();
							@Pc(471) int flags = this.inBuffer.g1();
							@Pc(480) int compressionType = flags & 0x7F;
							@Pc(491) boolean prefect = (flags & 0x80) != 0;
							@Pc(476) int length = this.inBuffer.g4();
							@Pc(501) long key = (archive << 16) + group;
							@Pc(568) int headerLength = compressionType == 0 ? 5 : 9;
							@Pc(509) Js5NetRequest request;
							if (prefect) {
								for (request = (Js5NetRequest) this.inFlightPrefetchRequests.head(); request != null && request.secondaryNodeId != key; request = (Js5NetRequest) this.inFlightPrefetchRequests.prev()) {
								}
							} else {
								for (request = (Js5NetRequest) this.inFlightUrgentRequests.head(); request != null && request.secondaryNodeId != key; request = (Js5NetRequest) this.inFlightUrgentRequests.prev()) {
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
							this.inBuffer.position = 0;
						} else if (this.current.blockPosition != 0) {
							throw new IOException();
						} else if (this.inBuffer.data[0] == -1) {
							this.current.blockPosition = 1;
							this.inBuffer.position = 0;
						} else {
							this.current = null;
						}
					}
				}
			}
			return true;
		} catch (@Pc(644) IOException ioException) {
			try {
				this.socket.closeGracefully();
			} catch (@Pc(650) Exception closeException) {
			}
			this.response = -2;
			this.errors++;
			this.socket = null;
			return this.getUrgentRequestCount() == 0 && this.getPrefetchRequestCount() == 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(Z)V")
	public final void serverDrop() {
		if (this.socket == null) {
			return;
		}
		try {
			this.outBuffer.position = 0;
			this.outBuffer.p1(7);
			this.outBuffer.p3(0);
			this.socket.write(4, this.outBuffer.data);
		} catch (@Pc(39) IOException local39) {
			try {
				this.socket.closeGracefully();
			} catch (@Pc(45) Exception local45) {
			}
			this.errors++;
			this.response = -2;
			this.socket = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "b", descriptor = "(I)I")
	private int getPrefetchRequestCount() {
		return this.prefetch.length() + this.inFlightPrefetchRequests.length();
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(ZZ)V")
	public void writeLoggedIn(@OriginalArg(0) boolean loggedIn) {
		if (this.socket == null) {
			return;
		}
		try {
			this.outBuffer.position = 0;
			this.outBuffer.p1(loggedIn ? 2 : 3);
			this.outBuffer.p3(0);
			this.socket.write(4, this.outBuffer.data);
		} catch (@Pc(42) IOException local42) {
			try {
				this.socket.closeGracefully();
			} catch (@Pc(48) Exception local48) {
			}
			this.errors++;
			this.response = -2;
			this.socket = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "c", descriptor = "(I)V")
	public final void method2323() {
		if (this.socket != null) {
			this.socket.method2833();
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(ZLclient!ma;I)V")
	public final void loggedOut(@OriginalArg(0) boolean arg0, @OriginalArg(1) BufferedSocket arg1) {
		if (this.socket != null) {
			try {
				this.socket.closeGracefully();
			} catch (@Pc(14) Exception local14) {
			}
			this.socket = null;
		}
		this.socket = arg1;
		this.method2331();
		this.writeLoggedIn(arg0);
		this.inBuffer.position = 0;
		this.current = null;
		while (true) {
			@Pc(44) Js5NetRequest local44 = (Js5NetRequest) this.inFlightUrgentRequests.pollFront();
			if (local44 == null) {
				while (true) {
					local44 = (Js5NetRequest) this.inFlightPrefetchRequests.pollFront();
					if (local44 == null) {
						if (this.encryptionKey != 0) {
							try {
								this.outBuffer.position = 0;
								this.outBuffer.p1(4);
								this.outBuffer.p1(this.encryptionKey);
								this.outBuffer.p2(0);
								this.socket.write(4, this.outBuffer.data);
							} catch (@Pc(107) IOException local107) {
								try {
									this.socket.closeGracefully();
								} catch (@Pc(113) Exception local113) {
								}
								this.response = -2;
								this.errors++;
								this.socket = null;
							}
						}
						this.latency = 0;
						this.aLong104 = MonotonicTime.get();
						return;
					}
					this.prefetch.pushBack(local44);
				}
			}
			this.urgent.pushBack(local44);
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "c", descriptor = "(B)Z")
	public boolean isUrgentsFull() {
		return this.getUrgentRequestCount() >= 20;
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "d", descriptor = "(B)V")
	public void method2327() {
		try {
			this.socket.closeGracefully();
		} catch (@Pc(17) Exception local17) {
		}
		this.response = -1;
		this.encryptionKey = (byte) (Math.random() * 255.0D + 1.0D);
		this.socket = null;
		this.errors++;
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "d", descriptor = "(I)I")
	public final int getUrgentRequestCount() {
		return this.urgent.length() + this.inFlightUrgentRequests.length();
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "b", descriptor = "(Z)V")
	public final void clientDrop() {
		if (this.socket != null) {
			this.socket.closeGracefully();
		}
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(IIBIZ)Lclient!pm;")
	public final Js5NetRequest method2330(@OriginalArg(1) int arg0, @OriginalArg(2) byte arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3) {
		@Pc(7) Js5NetRequest local7 = new Js5NetRequest();
		@Pc(14) long local14 = (long) (arg2 + (arg0 << 16));
		local7.urgent = arg3;
		local7.secondaryNodeId = local14;
		local7.offset = arg1;
		if (arg3) {
			if (this.getUrgentRequestCount() >= 20) {
				throw new RuntimeException();
			}
			this.urgent.pushBack(local7);
		} else if (this.getPrefetchRequestCount() < 20) {
			this.prefetch.pushBack(local7);
		} else {
			throw new RuntimeException();
		}
		return local7;
	}

	@OriginalMember(owner = "runetek4.client!jb", name = "e", descriptor = "(B)V")
	private void method2331() {
		if (this.socket == null) {
			return;
		}
		try {
			this.outBuffer.position = 0;
			this.outBuffer.p1(6);
			this.outBuffer.p3(3);
			this.socket.write(4, this.outBuffer.data);
		} catch (@Pc(37) IOException local37) {
			try {
				this.socket.closeGracefully();
			} catch (@Pc(43) Exception local43) {
			}
			this.errors++;
			this.socket = null;
			this.response = -2;
		}
	}
}
