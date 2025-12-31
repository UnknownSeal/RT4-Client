package com.jagex.js5.index;

import com.jagex.core.io.Packet;
import com.jagex.cache.Cache;
import com.jagex.js5.Js5CacheQueue;
import com.jagex.js5.Js5NetQueue;
import com.jagex.js5.network.Js5NetRequest;
import com.jagex.js5.network.Js5CachedResourceProvider;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!al")
public final class Js5MasterIndex {

	@OriginalMember(owner = "client!al", name = "a", descriptor = "Lclient!wa;")
	private Packet packet;

	@OriginalMember(owner = "client!al", name = "s", descriptor = "[Lclient!bg;")
	private Js5CachedResourceProvider[] resourceProviders;

	@OriginalMember(owner = "client!al", name = "j", descriptor = "Lclient!k;")
	private final Js5CacheQueue cacheQueue;

	@OriginalMember(owner = "client!al", name = "f", descriptor = "Lclient!jb;")
	private final Js5NetQueue netQueue;

	@OriginalMember(owner = "client!al", name = "c", descriptor = "Lclient!pm;")
	private Js5NetRequest request;

	@OriginalMember(owner = "client!al", name = "<init>", descriptor = "(Lclient!jb;Lclient!k;)V")
	public Js5MasterIndex(@OriginalArg(0) Js5NetQueue arg0, @OriginalArg(1) Js5CacheQueue arg1) {
		this.cacheQueue = arg1;
		this.netQueue = arg0;
		if (!this.netQueue.isUrgentsFull()) {
			this.request = this.netQueue.read(255, (byte) 0, 255, true);
		}
	}

	@OriginalMember(owner = "client!al", name = "b", descriptor = "(I)Z")
	public boolean isReady() {
		if (this.packet != null) {
			return true;
		}
		if (this.request == null) {
			if (this.netQueue.isUrgentsFull()) {
				return false;
			}
			this.request = this.netQueue.read(255, (byte) 0, 255, true);
		}
		if (this.request.awaitingResponse) {
			return false;
		} else {
			this.packet = new Packet(this.request.getBytes());
			this.resourceProviders = new Js5CachedResourceProvider[(this.packet.data.length - 5) / 8];
			return true;
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(B)V")
	public void method179() {
		if (this.resourceProviders == null) {
			return;
		}
		@Pc(13) int i;
		for (i = 0; i < this.resourceProviders.length; i++) {
			if (this.resourceProviders[i] != null) {
				this.resourceProviders[i].processPrefetchQueue();
			}
		}
		for (i = 0; i < this.resourceProviders.length; i++) {
			if (this.resourceProviders[i] != null) {
				this.resourceProviders[i].method534();
			}
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(IILclient!ge;Lclient!ge;)Lclient!bg;")
	public Js5CachedResourceProvider getResourceProvider(@OriginalArg(1) int arg0, @OriginalArg(2) Cache arg1, @OriginalArg(3) Cache arg2) {
		return this.getProvider(arg2, arg0, arg1);
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Lclient!ge;IIZLclient!ge;)Lclient!bg;")
	private Js5CachedResourceProvider getProvider(@OriginalArg(0) Cache arg0, @OriginalArg(2) int archiveId, @OriginalArg(4) Cache arg2) {
		if (this.packet == null) {
			throw new RuntimeException();
		}
		this.packet.offset = archiveId * 8 + 5;
		if (this.packet.data.length <= this.packet.offset) {
			throw new RuntimeException();
		} else if (this.resourceProviders[archiveId] == null) {
			@Pc(56) int indexcrc = this.packet.g4();
			@Pc(61) int version = this.packet.g4();
			@Pc(75) Js5CachedResourceProvider local75 = new Js5CachedResourceProvider(archiveId, arg0, arg2, this.netQueue, this.cacheQueue, indexcrc, version, true);
			this.resourceProviders[archiveId] = local75;
			return local75;
		} else {
			return this.resourceProviders[archiveId];
		}
	}
}
