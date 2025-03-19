package com.jagex.runetek4.js5.network;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.cache.Cache;
import com.jagex.runetek4.js5.Js5Request;
import com.jagex.runetek4.js5.Js5ResourceProvider;
import com.jagex.runetek4.js5.index.Js5Index;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.util.zip.CRC32;

@OriginalClass("client!bg")
public final class Js5CachedResourceProvider extends Js5ResourceProvider {

	@OriginalMember(owner = "runetek4.client!fn", name = "X", descriptor = "Ljava/util/zip/CRC32;")
	public static final CRC32 crc32 = new CRC32();

	@OriginalMember(owner = "client!bg", name = "m", descriptor = "Lclient!ii;")
	private Js5Index index;

	@OriginalMember(owner = "client!bg", name = "w", descriptor = "[B")
	private byte[] groupStatus;

	@OriginalMember(owner = "client!bg", name = "T", descriptor = "Z")
	private boolean prefetchAll;

	@OriginalMember(owner = "client!bg", name = "u", descriptor = "I")
	private int verifiedGroups = 0;

	@OriginalMember(owner = "client!bg", name = "p", descriptor = "Lclient!sc;")
	private final HashTable requests = new HashTable(16);

	@OriginalMember(owner = "client!bg", name = "S", descriptor = "I")
	private int group = 0;

	@OriginalMember(owner = "client!bg", name = "R", descriptor = "Lclient!ih;")
	private final LinkedList prefetchQueue = new LinkedList();

	@OriginalMember(owner = "client!bg", name = "V", descriptor = "J")
	private long orphanCheckTime = 0L;

	@OriginalMember(owner = "client!bg", name = "L", descriptor = "I")
	private final int archive;

	@OriginalMember(owner = "client!bg", name = "F", descriptor = "Lclient!ge;")
	private final Cache cache;

	@OriginalMember(owner = "client!bg", name = "Q", descriptor = "Z")
	private boolean verifyAll;

	@OriginalMember(owner = "client!bg", name = "U", descriptor = "Lclient!ih;")
	private LinkedList groupQueue;

	@OriginalMember(owner = "client!bg", name = "J", descriptor = "Lclient!k;")
	private final Js5CacheQueue cacheQueue;

	@OriginalMember(owner = "client!bg", name = "H", descriptor = "I")
	private final int expectedChecksum;

	@OriginalMember(owner = "client!bg", name = "W", descriptor = "Z")
	private final boolean discardOrphans;

	@OriginalMember(owner = "client!bg", name = "k", descriptor = "Lclient!ge;")
	private final Cache masterCache;

	@OriginalMember(owner = "client!bg", name = "D", descriptor = "Lclient!jb;")
	private final Js5NetQueue netQueue;

	@OriginalMember(owner = "client!bg", name = "t", descriptor = "I")
	private final int expectedVersion;

	@OriginalMember(owner = "client!bg", name = "x", descriptor = "Lclient!il;")
	private Js5Request currentRequest;

	@OriginalMember(owner = "client!bg", name = "<init>", descriptor = "(ILclient!ge;Lclient!ge;Lclient!jb;Lclient!k;IIZ)V")
	public Js5CachedResourceProvider(@OriginalArg(0) int archive, @OriginalArg(1) Cache cache, @OriginalArg(2) Cache masterCache, @OriginalArg(3) Js5NetQueue netQueue, @OriginalArg(4) Js5CacheQueue cacheQueue, @OriginalArg(5) int expectedChecksum, @OriginalArg(6) int expectedVersion, @OriginalArg(7) boolean discardOrphans) {
		this.archive = archive;
		this.cache = cache;
		if (this.cache == null) {
			this.verifyAll = false;
		} else {
			this.verifyAll = true;
			this.groupQueue = new LinkedList();
		}
		this.cacheQueue = cacheQueue;
		this.expectedChecksum = expectedChecksum;
		this.discardOrphans = discardOrphans;
		this.masterCache = masterCache;
		this.netQueue = netQueue;
		this.expectedVersion = expectedVersion;
		if (this.masterCache != null) {
			this.currentRequest = this.cacheQueue.readSynchronous(this.masterCache, this.archive);
		}
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(II)V")
	@Override
	public void prefetchGroup(@OriginalArg(0) int arg0) {
		if (this.cache == null) {
			return;
		}
		@Pc(15) Node local15;
		for (local15 = this.prefetchQueue.head(); local15 != null; local15 = this.prefetchQueue.next()) {
			if (local15.nodeId == (long) arg0) {
				return;
			}
		}
		local15 = new Node();
		local15.nodeId = arg0;
		this.prefetchQueue.addTail(local15);
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(I)Lclient!ii;")
	@Override
	public Js5Index fetchIndex() {
		if (this.index != null) {
			return this.index;
		}
		if (this.currentRequest == null) {
			if (this.netQueue.isUrgentsFull()) {
				return null;
			}
			this.currentRequest = this.netQueue.method2330(255, (byte) 0, this.archive, true);
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
				this.index = new Js5Index(local52, this.expectedChecksum);
				if (this.expectedVersion != this.index.indexversion) {
					throw new RuntimeException();
				}
			} catch (@Pc(88) RuntimeException local88) {
				this.index = null;
				if (this.netQueue.isUrgentsFull()) {
					this.currentRequest = null;
				} else {
					this.currentRequest = this.netQueue.method2330(255, (byte) 0, this.archive, true);
				}
				return null;
			}
		} else {
			try {
				if (local52 == null) {
					throw new RuntimeException();
				}
				this.index = new Js5Index(local52, this.expectedChecksum);
			} catch (@Pc(131) RuntimeException local131) {
				this.netQueue.rekey();
				this.index = null;
				if (this.netQueue.isUrgentsFull()) {
					this.currentRequest = null;
				} else {
					this.currentRequest = this.netQueue.method2330(255, (byte) 0, this.archive, true);
				}
				return null;
			}
			if (this.masterCache != null) {
				this.cacheQueue.method2467(this.masterCache, local52, this.archive);
			}
		}
		if (this.cache != null) {
			this.groupStatus = new byte[this.index.capacity];
			this.verifiedGroups = 0;
		}
		this.currentRequest = null;
		return this.index;
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(Z)V")
	public void prefetchAll() {
		if (this.cache != null) {
			this.prefetchAll = true;
			if (this.groupQueue == null) {
				this.groupQueue = new LinkedList();
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(I)I")
	public int getVerifiedGroups() {
		return this.verifiedGroups;
	}

	@OriginalMember(owner = "client!bg", name = "d", descriptor = "(I)I")
	public int getTotalVerifiedGroups() {
		if (this.index == null) {
			return 0;
		} else if (this.verifyAll) {
			@Pc(25) Node local25 = this.groupQueue.head();
			return local25 == null ? 0 : (int) local25.nodeId;
		} else {
			return this.index.length;
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(Z)V")
	public void method534() {
		if (this.groupQueue != null) {
			if (this.fetchIndex() == null) {
				return;
			}
			@Pc(32) boolean local32;
			@Pc(37) Node local37;
			@Pc(43) int local43;
			if (this.verifyAll) {
				local32 = true;
				for (local37 = this.groupQueue.head(); local37 != null; local37 = this.groupQueue.next()) {
					local43 = (int) local37.nodeId;
					if (this.groupStatus[local43] == 0) {
						this.fetchgroup_inner(local43, 1);
					}
					if (this.groupStatus[local43] == 0) {
						local32 = false;
					} else {
						local37.unlink();
					}
				}
				while (this.index.groupSizes.length > this.group) {
					if (this.index.groupSizes[this.group] == 0) {
						this.group++;
					} else {
						if (this.cacheQueue.anInt3131 >= 250) {
							local32 = false;
							break;
						}
						if (this.groupStatus[this.group] == 0) {
							this.fetchgroup_inner(this.group, 1);
						}
						if (this.groupStatus[this.group] == 0) {
							local32 = false;
							local37 = new Node();
							local37.nodeId = this.group;
							this.groupQueue.addTail(local37);
						}
						this.group++;
					}
				}
				if (local32) {
					this.verifyAll = false;
					this.group = 0;
				}
			} else if (this.prefetchAll) {
				local32 = true;
				for (local37 = this.groupQueue.head(); local37 != null; local37 = this.groupQueue.next()) {
					local43 = (int) local37.nodeId;
					if (this.groupStatus[local43] != 1) {
						this.fetchgroup_inner(local43, 2);
					}
					if (this.groupStatus[local43] == 1) {
						local37.unlink();
					} else {
						local32 = false;
					}
				}
				while (this.index.groupSizes.length > this.group) {
					if (this.index.groupSizes[this.group] == 0) {
						this.group++;
					} else {
						if (this.netQueue.isPrefetchRequestQueueFull()) {
							local32 = false;
							break;
						}
						if (this.groupStatus[this.group] != 1) {
							this.fetchgroup_inner(this.group, 2);
						}
						if (this.groupStatus[this.group] != 1) {
							local37 = new Node();
							local37.nodeId = this.group;
							this.groupQueue.addTail(local37);
							local32 = false;
						}
						this.group++;
					}
				}
				if (local32) {
					this.group = 0;
					this.prefetchAll = false;
				}
			} else {
				this.groupQueue = null;
			}
		}
		if (!this.discardOrphans || this.orphanCheckTime > MonotonicTime.currentTimeMillis()) {
			return;
		}
		for (@Pc(331) Js5Request local331 = (Js5Request) this.requests.head(); local331 != null; local331 = (Js5Request) this.requests.next()) {
			if (!local331.awaitingResponse) {
				if (local331.aBoolean227) {
					if (!local331.urgent) {
						throw new RuntimeException();
					}
					local331.unlink();
				} else {
					local331.aBoolean227 = true;
				}
			}
		}
		this.orphanCheckTime = MonotonicTime.currentTimeMillis() + 1000L;
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(II)I")
	@Override
	public int getPercentageComplete(@OriginalArg(0) int group) {
		@Pc(15) Js5Request local15 = (Js5Request) this.requests.getNode(group);
		return local15 == null ? 0 : local15.getPercentageComplete();
	}

	@OriginalMember(owner = "client!bg", name = "b", descriptor = "(B)I")
	public int getIndexSize() {
		return this.index == null ? 0 : this.index.length;
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(III)Lclient!il;")
	private Js5Request fetchgroup_inner(@OriginalArg(1) int group, @OriginalArg(0) int arg0) {
		@Pc(13) Js5Request request = (Js5Request) this.requests.getNode(group);
		if (request != null && arg0 == 0 && !request.urgent && request.awaitingResponse) {
			request.unlink();
			request = null;
		}
		if (request == null) {
			if (arg0 == 0) {
				if (this.cache == null || this.groupStatus[group] == -1) {
					if (this.netQueue.isUrgentsFull()) {
						return null;
					}
					request = this.netQueue.method2330(this.archive, (byte) 2, group, true);
				} else {
					request = this.cacheQueue.readSynchronous(this.cache, group);
				}
			} else if (arg0 == 1) {
				if (this.cache == null) {
					throw new RuntimeException("fetchgroup_inner - VERIFY requested but no datafs available!");
				}
				request = this.cacheQueue.method2469(group, this.cache);
			} else if (arg0 == 2) {
				if (this.cache == null) {
					throw new RuntimeException("fetchgroup_inner - PREFETCH requested but no datafs available!");
				}
				if (this.groupStatus[group] != -1) {
					throw new RuntimeException("fetchgroup_inner - PREFETCH requested, but cache isn't known invalid!");
				}
				if (this.netQueue.isPrefetchRequestQueueFull()) {
					return null;
				}
				request = this.netQueue.method2330(this.archive, (byte) 2, group, false);
			} else {
				throw new RuntimeException();
			}
			this.requests.put(request, group);
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
				crc32.reset();
				crc32.update(local161, 0, local161.length - 2);
				local199 = (int) crc32.getValue();
				if (this.index.anIntArray268[group] != local199) {
					throw new RuntimeException();
				}
				this.netQueue.errors = 0;
				this.netQueue.response = 0;
			} catch (@Pc(225) RuntimeException local225) {
				this.netQueue.rekey();
				request.unlink();
				if (request.urgent && !this.netQueue.isUrgentsFull()) {
					local252 = this.netQueue.method2330(this.archive, (byte) 2, group, true);
					this.requests.put(local252, group);
				}
				return null;
			}
			local161[local161.length - 2] = (byte) (this.index.anIntArray273[group] >>> 8);
			local161[local161.length - 1] = (byte) this.index.anIntArray273[group];
			if (this.cache != null) {
				this.cacheQueue.method2467(this.cache, local161, group);
				if (this.groupStatus[group] != 1) {
					this.verifiedGroups++;
					this.groupStatus[group] = 1;
				}
			}
			if (!request.urgent) {
				request.unlink();
			}
			return request;
		}
		try {
			if (local161 == null || local161.length <= 2) {
				throw new RuntimeException();
			}
			crc32.reset();
			crc32.update(local161, 0, local161.length - 2);
			local199 = (int) crc32.getValue();
			if (this.index.anIntArray268[group] != local199) {
				throw new RuntimeException();
			}
			@Pc(385) int local385 = ((local161[local161.length - 2] & 0xFF) << 8) + (local161[local161.length - 1] & 0xFF);
			if (local385 != (this.index.anIntArray273[group] & 0xFFFF)) {
				throw new RuntimeException();
			}
			if (this.groupStatus[group] != 1) {
				if (this.groupStatus[group] != 0) {
				}
				this.verifiedGroups++;
				this.groupStatus[group] = 1;
			}
			if (!request.urgent) {
				request.unlink();
			}
			return request;
		} catch (@Pc(437) Exception local437) {
			this.groupStatus[group] = -1;
			request.unlink();
			if (request.urgent && !this.netQueue.isUrgentsFull()) {
				local252 = this.netQueue.method2330(this.archive, (byte) 2, group, true);
				this.requests.put(local252, group);
			}
			return null;
		}
	}

	@OriginalMember(owner = "client!bg", name = "e", descriptor = "(I)V")
	public void method537() {
		if (this.groupQueue == null || this.fetchIndex() == null) {
			return;
		}
		for (@Pc(21) Node local21 = this.prefetchQueue.head(); local21 != null; local21 = this.prefetchQueue.next()) {
			@Pc(28) int local28 = (int) local21.nodeId;
			if (local28 < 0 || this.index.capacity <= local28 || this.index.groupSizes[local28] == 0) {
				local21.unlink();
			} else {
				if (this.groupStatus[local28] == 0) {
					this.fetchgroup_inner(local28, 1);
				}
				if (this.groupStatus[local28] == -1) {
					this.fetchgroup_inner(local28, 2);
				}
				if (this.groupStatus[local28] == 1) {
					local21.unlink();
				}
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "f", descriptor = "(I)I")
	public int getIndexPercentageComplete() {
		if (this.fetchIndex() == null) {
			return this.currentRequest == null ? 0 : this.currentRequest.getPercentageComplete();
		} else {
			return 100;
		}
	}

	@OriginalMember(owner = "client!bg", name = "c", descriptor = "(II)[B")
	@Override
	public byte[] fetchgroup(@OriginalArg(0) int group) {
		@Pc(9) Js5Request request = this.fetchgroup_inner(group, 0);
		if (request == null) {
			return null;
		} else {
			@Pc(17) byte[] bytes = request.getBytes();
			request.unlink();
			return bytes;
		}
	}
}
