package com.jagex.runetek4.js5.network;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.core.datastruct.Node;
import com.jagex.runetek4.game.client.DiskStore;
import com.jagex.runetek4.js5.Js5Request;
import com.jagex.runetek4.js5.Js5ResourceProvider;
import com.jagex.runetek4.js5.index.Js5Index;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bg")
public final class Js5NetResourceProvider extends Js5ResourceProvider {

	@OriginalMember(owner = "client!bg", name = "m", descriptor = "Lclient!ii;")
	private Js5Index index;

	@OriginalMember(owner = "client!bg", name = "w", descriptor = "[B")
	private byte[] aByteArray9;

	@OriginalMember(owner = "client!bg", name = "T", descriptor = "Z")
	private boolean aBoolean36;

	@OriginalMember(owner = "client!bg", name = "u", descriptor = "I")
	private int anInt567 = 0;

	@OriginalMember(owner = "client!bg", name = "p", descriptor = "Lclient!sc;")
	private final IterableMap requests = new IterableMap(16);

	@OriginalMember(owner = "client!bg", name = "S", descriptor = "I")
	private int anInt578 = 0;

	@OriginalMember(owner = "client!bg", name = "R", descriptor = "Lclient!ih;")
	private final LinkedList aClass69_16 = new LinkedList();

	@OriginalMember(owner = "client!bg", name = "V", descriptor = "J")
	private long aLong20 = 0L;

	@OriginalMember(owner = "client!bg", name = "L", descriptor = "I")
	private final int anInt576;

	@OriginalMember(owner = "client!bg", name = "F", descriptor = "Lclient!ge;")
	private final DiskStore aClass49_2;

	@OriginalMember(owner = "client!bg", name = "Q", descriptor = "Z")
	private boolean aBoolean35;

	@OriginalMember(owner = "client!bg", name = "U", descriptor = "Lclient!ih;")
	private LinkedList aClass69_17;

	@OriginalMember(owner = "client!bg", name = "J", descriptor = "Lclient!k;")
	private final Js5CacheQueue aClass80_2;

	@OriginalMember(owner = "client!bg", name = "H", descriptor = "I")
	private final int anInt573;

	@OriginalMember(owner = "client!bg", name = "W", descriptor = "Z")
	private final boolean aBoolean37;

	@OriginalMember(owner = "client!bg", name = "k", descriptor = "Lclient!ge;")
	private final DiskStore aClass49_1;

	@OriginalMember(owner = "client!bg", name = "D", descriptor = "Lclient!jb;")
	private final Js5NetQueue tcpClient;

	@OriginalMember(owner = "client!bg", name = "t", descriptor = "I")
	private final int anInt566;

	@OriginalMember(owner = "client!bg", name = "x", descriptor = "Lclient!il;")
	private Js5Request currentRequest;

	@OriginalMember(owner = "client!bg", name = "<init>", descriptor = "(ILclient!ge;Lclient!ge;Lclient!jb;Lclient!k;IIZ)V")
	public Js5NetResourceProvider(@OriginalArg(0) int arg0, @OriginalArg(1) DiskStore arg1, @OriginalArg(2) DiskStore arg2, @OriginalArg(3) Js5NetQueue arg3, @OriginalArg(4) Js5CacheQueue arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) boolean arg7) {
		this.anInt576 = arg0;
		this.aClass49_2 = arg1;
		if (this.aClass49_2 == null) {
			this.aBoolean35 = false;
		} else {
			this.aBoolean35 = true;
			this.aClass69_17 = new LinkedList();
		}
		this.aClass80_2 = arg4;
		this.anInt573 = arg5;
		this.aBoolean37 = arg7;
		this.aClass49_1 = arg2;
		this.tcpClient = arg3;
		this.anInt566 = arg6;
		if (this.aClass49_1 != null) {
			this.currentRequest = this.aClass80_2.method2471(this.aClass49_1, this.anInt576);
		}
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(II)V")
	@Override
	public void method522(@OriginalArg(0) int arg0) {
		if (this.aClass49_2 == null) {
			return;
		}
		@Pc(15) Node local15;
		for (local15 = this.aClass69_16.method2289(); local15 != null; local15 = this.aClass69_16.method2288()) {
			if (local15.nodeId == (long) arg0) {
				return;
			}
		}
		local15 = new Node();
		local15.nodeId = arg0;
		this.aClass69_16.method2282(local15);
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(I)Lclient!ii;")
	@Override
	public Js5Index fetchIndex() {
		if (this.index != null) {
			return this.index;
		}
		if (this.currentRequest == null) {
			if (this.tcpClient.isUrgentsFull()) {
				return null;
			}
			this.currentRequest = this.tcpClient.method2330(255, (byte) 0, this.anInt576, true);
		}
		if (this.currentRequest.awaitingResponse) {
			return null;
		}
		@Pc(52) byte[] local52 = this.currentRequest.getBytes();
		if (this.currentRequest instanceof Js5CacheRequest) {
			try {
				if (local52 == null) {
					throw new RuntimeException();
				}
				this.index = new Js5Index(local52, this.anInt573);
				if (this.anInt566 != this.index.indexversion) {
					throw new RuntimeException();
				}
			} catch (@Pc(88) RuntimeException local88) {
				this.index = null;
				if (this.tcpClient.isUrgentsFull()) {
					this.currentRequest = null;
				} else {
					this.currentRequest = this.tcpClient.method2330(255, (byte) 0, this.anInt576, true);
				}
				return null;
			}
		} else {
			try {
				if (local52 == null) {
					throw new RuntimeException();
				}
				this.index = new Js5Index(local52, this.anInt573);
			} catch (@Pc(131) RuntimeException local131) {
				this.tcpClient.method2327();
				this.index = null;
				if (this.tcpClient.isUrgentsFull()) {
					this.currentRequest = null;
				} else {
					this.currentRequest = this.tcpClient.method2330(255, (byte) 0, this.anInt576, true);
				}
				return null;
			}
			if (this.aClass49_1 != null) {
				this.aClass80_2.method2467(this.aClass49_1, local52, this.anInt576);
			}
		}
		if (this.aClass49_2 != null) {
			this.aByteArray9 = new byte[this.index.capacity];
			this.anInt567 = 0;
		}
		this.currentRequest = null;
		return this.index;
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(Z)V")
	public void method528() {
		if (this.aClass49_2 != null) {
			this.aBoolean36 = true;
			if (this.aClass69_17 == null) {
				this.aClass69_17 = new LinkedList();
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(I)I")
	public int method529() {
		return this.anInt567;
	}

	@OriginalMember(owner = "client!bg", name = "d", descriptor = "(I)I")
	public int method533() {
		if (this.index == null) {
			return 0;
		} else if (this.aBoolean35) {
			@Pc(25) Node local25 = this.aClass69_17.method2289();
			return local25 == null ? 0 : (int) local25.nodeId;
		} else {
			return this.index.length;
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(Z)V")
	public void method534() {
		if (this.aClass69_17 != null) {
			if (this.fetchIndex() == null) {
				return;
			}
			@Pc(32) boolean local32;
			@Pc(37) Node local37;
			@Pc(43) int local43;
			if (this.aBoolean35) {
				local32 = true;
				for (local37 = this.aClass69_17.method2289(); local37 != null; local37 = this.aClass69_17.method2288()) {
					local43 = (int) local37.nodeId;
					if (this.aByteArray9[local43] == 0) {
						this.method536(local43, 1);
					}
					if (this.aByteArray9[local43] == 0) {
						local32 = false;
					} else {
						local37.remove();
					}
				}
				while (this.index.groupSizes.length > this.anInt578) {
					if (this.index.groupSizes[this.anInt578] == 0) {
						this.anInt578++;
					} else {
						if (this.aClass80_2.anInt3131 >= 250) {
							local32 = false;
							break;
						}
						if (this.aByteArray9[this.anInt578] == 0) {
							this.method536(this.anInt578, 1);
						}
						if (this.aByteArray9[this.anInt578] == 0) {
							local32 = false;
							local37 = new Node();
							local37.nodeId = this.anInt578;
							this.aClass69_17.method2282(local37);
						}
						this.anInt578++;
					}
				}
				if (local32) {
					this.aBoolean35 = false;
					this.anInt578 = 0;
				}
			} else if (this.aBoolean36) {
				local32 = true;
				for (local37 = this.aClass69_17.method2289(); local37 != null; local37 = this.aClass69_17.method2288()) {
					local43 = (int) local37.nodeId;
					if (this.aByteArray9[local43] != 1) {
						this.method536(local43, 2);
					}
					if (this.aByteArray9[local43] == 1) {
						local37.remove();
					} else {
						local32 = false;
					}
				}
				while (this.index.groupSizes.length > this.anInt578) {
					if (this.index.groupSizes[this.anInt578] == 0) {
						this.anInt578++;
					} else {
						if (this.tcpClient.method2316()) {
							local32 = false;
							break;
						}
						if (this.aByteArray9[this.anInt578] != 1) {
							this.method536(this.anInt578, 2);
						}
						if (this.aByteArray9[this.anInt578] != 1) {
							local37 = new Node();
							local37.nodeId = this.anInt578;
							this.aClass69_17.method2282(local37);
							local32 = false;
						}
						this.anInt578++;
					}
				}
				if (local32) {
					this.anInt578 = 0;
					this.aBoolean36 = false;
				}
			} else {
				this.aClass69_17 = null;
			}
		}
		if (!this.aBoolean37 || this.aLong20 > MonotonicClock.currentTimeMillis()) {
			return;
		}
		for (@Pc(331) Js5Request local331 = (Js5Request) this.requests.peekFront(); local331 != null; local331 = (Js5Request) this.requests.prev()) {
			if (!local331.awaitingResponse) {
				if (local331.aBoolean227) {
					if (!local331.urgent) {
						throw new RuntimeException();
					}
					local331.remove();
				} else {
					local331.aBoolean227 = true;
				}
			}
		}
		this.aLong20 = MonotonicClock.currentTimeMillis() + 1000L;
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(II)I")
	@Override
	public int getPercentageComplete(@OriginalArg(0) int group) {
		@Pc(15) Js5Request local15 = (Js5Request) this.requests.getNode(group);
		return local15 == null ? 0 : local15.getPercentageComplete();
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(B)I")
	public int method535() {
		return this.index == null ? 0 : this.index.length;
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(III)Lclient!il;")
	private Js5Request method536(@OriginalArg(1) int group, @OriginalArg(0) int arg0) {
		@Pc(13) Js5Request request = (Js5Request) this.requests.getNode(group);
		if (request != null && arg0 == 0 && !request.urgent && request.awaitingResponse) {
			request.remove();
			request = null;
		}
		if (request == null) {
			if (arg0 == 0) {
				if (this.aClass49_2 == null || this.aByteArray9[group] == -1) {
					if (this.tcpClient.isUrgentsFull()) {
						return null;
					}
					request = this.tcpClient.method2330(this.anInt576, (byte) 2, group, true);
				} else {
					request = this.aClass80_2.method2471(this.aClass49_2, group);
				}
			} else if (arg0 == 1) {
				if (this.aClass49_2 == null) {
					throw new RuntimeException("fetchgroup_inner - VERIFY requested but no datafs available!");
				}
				request = this.aClass80_2.method2469(group, this.aClass49_2);
			} else if (arg0 == 2) {
				if (this.aClass49_2 == null) {
					throw new RuntimeException("fetchgroup_inner - PREFETCH requested but no datafs available!");
				}
				if (this.aByteArray9[group] != -1) {
					throw new RuntimeException("fetchgroup_inner - PREFETCH requested, but cache isn't known invalid!");
				}
				if (this.tcpClient.method2316()) {
					return null;
				}
				request = this.tcpClient.method2330(this.anInt576, (byte) 2, group, false);
			} else {
				throw new RuntimeException();
			}
			this.requests.pushNode(request, group);
		}
		if (request.awaitingResponse) {
			return null;
		}
		@Pc(161) byte[] local161 = request.getBytes();
		@Pc(199) int local199;
		@Pc(252) Js5NetRequest local252;
		if (!(request instanceof Js5CacheRequest)) {
			try {
				if (local161 == null || local161.length <= 2) {
					throw new RuntimeException();
				}
				Static74.aCRC32_1.reset();
				Static74.aCRC32_1.update(local161, 0, local161.length - 2);
				local199 = (int) Static74.aCRC32_1.getValue();
				if (this.index.anIntArray268[group] != local199) {
					throw new RuntimeException();
				}
				this.tcpClient.errors = 0;
				this.tcpClient.response = 0;
			} catch (@Pc(225) RuntimeException local225) {
				this.tcpClient.method2327();
				request.remove();
				if (request.urgent && !this.tcpClient.isUrgentsFull()) {
					local252 = this.tcpClient.method2330(this.anInt576, (byte) 2, group, true);
					this.requests.pushNode(local252, group);
				}
				return null;
			}
			local161[local161.length - 2] = (byte) (this.index.anIntArray273[group] >>> 8);
			local161[local161.length - 1] = (byte) this.index.anIntArray273[group];
			if (this.aClass49_2 != null) {
				this.aClass80_2.method2467(this.aClass49_2, local161, group);
				if (this.aByteArray9[group] != 1) {
					this.anInt567++;
					this.aByteArray9[group] = 1;
				}
			}
			if (!request.urgent) {
				request.remove();
			}
			return request;
		}
		try {
			if (local161 == null || local161.length <= 2) {
				throw new RuntimeException();
			}
			Static74.aCRC32_1.reset();
			Static74.aCRC32_1.update(local161, 0, local161.length - 2);
			local199 = (int) Static74.aCRC32_1.getValue();
			if (this.index.anIntArray268[group] != local199) {
				throw new RuntimeException();
			}
			@Pc(385) int local385 = ((local161[local161.length - 2] & 0xFF) << 8) + (local161[local161.length - 1] & 0xFF);
			if (local385 != (this.index.anIntArray273[group] & 0xFFFF)) {
				throw new RuntimeException();
			}
			if (this.aByteArray9[group] != 1) {
				if (this.aByteArray9[group] != 0) {
				}
				this.anInt567++;
				this.aByteArray9[group] = 1;
			}
			if (!request.urgent) {
				request.remove();
			}
			return request;
		} catch (@Pc(437) Exception local437) {
			this.aByteArray9[group] = -1;
			request.remove();
			if (request.urgent && !this.tcpClient.isUrgentsFull()) {
				local252 = this.tcpClient.method2330(this.anInt576, (byte) 2, group, true);
				this.requests.pushNode(local252, group);
			}
			return null;
		}
	}

	@OriginalMember(owner = "client!bg", name = "e", descriptor = "(I)V")
	public void method537() {
		if (this.aClass69_17 == null || this.fetchIndex() == null) {
			return;
		}
		for (@Pc(21) Node local21 = this.aClass69_16.method2289(); local21 != null; local21 = this.aClass69_16.method2288()) {
			@Pc(28) int local28 = (int) local21.nodeId;
			if (local28 < 0 || this.index.capacity <= local28 || this.index.groupSizes[local28] == 0) {
				local21.remove();
			} else {
				if (this.aByteArray9[local28] == 0) {
					this.method536(local28, 1);
				}
				if (this.aByteArray9[local28] == -1) {
					this.method536(local28, 2);
				}
				if (this.aByteArray9[local28] == 1) {
					local21.remove();
				}
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "f", descriptor = "(I)I")
	public int method538() {
		if (this.fetchIndex() == null) {
			return this.currentRequest == null ? 0 : this.currentRequest.getPercentageComplete();
		} else {
			return 100;
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(II)[B")
	@Override
	public byte[] fetchgroup(@OriginalArg(0) int group) {
		@Pc(9) Js5Request request = this.method536(group, 0);
		if (request == null) {
			return null;
		} else {
			@Pc(17) byte[] bytes = request.getBytes();
			request.remove();
			return bytes;
		}
	}
}
