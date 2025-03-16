package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheIndex;
import com.jagex.runetek4.node.NodeQueue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!k")
public final class Js5CacheQueue implements Runnable {

	@OriginalMember(owner = "runetek4.client!k", name = "q", descriptor = "Lclient!ce;")
	private final NodeQueue aClass16_6 = new NodeQueue();

	@OriginalMember(owner = "runetek4.client!k", name = "s", descriptor = "I")
	public int anInt3131 = 0;

	@OriginalMember(owner = "runetek4.client!k", name = "w", descriptor = "Z")
	private boolean aBoolean161 = false;

	@OriginalMember(owner = "runetek4.client!k", name = "v", descriptor = "Ljava/lang/Thread;")
	private Thread aThread2;

	@OriginalMember(owner = "runetek4.client!k", name = "<init>", descriptor = "()V")
	public Js5CacheQueue() {
		@Pc(20) PrivilegedRequest local20 = GameShell.signLink.putThreadNode(5, this);
		while (local20.status == 0) {
			PreciseSleep.sleep(10L);
		}
		if (local20.status == 2) {
			throw new RuntimeException();
		}
		this.aThread2 = (Thread) local20.result;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!c;I)V")
	private void method2461(@OriginalArg(0) Js5CacheRequest arg0) {
		@Pc(7) NodeQueue local7 = this.aClass16_6;
		synchronized (this.aClass16_6) {
			this.aClass16_6.pushBack(arg0);
			this.anInt3131++;
			this.aClass16_6.notifyAll();
		}
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(I)V")
	public final void method2466() {
		this.aBoolean161 = true;
		@Pc(6) NodeQueue local6 = this.aClass16_6;
		synchronized (this.aClass16_6) {
			this.aClass16_6.notifyAll();
		}
		try {
			this.aThread2.join();
		} catch (@Pc(23) InterruptedException local23) {
		}
		this.aThread2 = null;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!ge;I[BI)Lclient!c;")
	public final Js5CacheRequest method2467(@OriginalArg(0) CacheIndex arg0, @OriginalArg(2) byte[] arg1, @OriginalArg(3) int arg2) {
		@Pc(7) Js5CacheRequest local7 = new Js5CacheRequest();
		local7.bytes = arg1;
		local7.urgent = false;
		local7.secondaryNodeId = arg2;
		local7.aClass49_3 = arg0;
		local7.anInt824 = 2;
		this.method2461(local7);
		return local7;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IILclient!ge;)Lclient!c;")
	public final Js5CacheRequest method2469(@OriginalArg(0) int arg0, @OriginalArg(2) CacheIndex arg1) {
		@Pc(7) Js5CacheRequest local7 = new Js5CacheRequest();
		local7.aClass49_3 = arg1;
		local7.anInt824 = 3;
		local7.urgent = false;
		local7.secondaryNodeId = arg0;
		this.method2461(local7);
		return local7;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!ge;BI)Lclient!c;")
	public final Js5CacheRequest method2471(@OriginalArg(0) CacheIndex arg0, @OriginalArg(2) int arg1) {
		@Pc(9) Js5CacheRequest local9 = new Js5CacheRequest();
		local9.anInt824 = 1;
		@Pc(16) NodeQueue local16 = this.aClass16_6;
		synchronized (this.aClass16_6) {
			@Pc(31) Js5CacheRequest local31 = (Js5CacheRequest) this.aClass16_6.head();
			while (true) {
				if (local31 == null) {
					break;
				}
				if (local31.secondaryNodeId == (long) arg1 && local31.aClass49_3 == arg0 && local31.anInt824 == 2) {
					local9.bytes = local31.bytes;
					local9.awaitingResponse = false;
					return local9;
				}
				local31 = (Js5CacheRequest) this.aClass16_6.prev();
			}
		}
		local9.bytes = arg0.get(arg1);
		local9.awaitingResponse = false;
		local9.urgent = true;
		return local9;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (!this.aBoolean161) {
			@Pc(12) NodeQueue local12 = this.aClass16_6;
			@Pc(19) Js5CacheRequest local19;
			synchronized (this.aClass16_6) {
				local19 = (Js5CacheRequest) this.aClass16_6.pollFront();
				if (local19 == null) {
					try {
						this.aClass16_6.wait();
					} catch (@Pc(35) InterruptedException local35) {
					}
					continue;
				}
				this.anInt3131--;
			}
			try {
				if (local19.anInt824 == 2) {
					local19.aClass49_3.put((int) local19.secondaryNodeId, local19.bytes.length, local19.bytes);
				} else if (local19.anInt824 == 3) {
					local19.bytes = local19.aClass49_3.get((int) local19.secondaryNodeId);
				}
			} catch (@Pc(83) Exception local83) {
				Static89.report(null, local83);
			}
			local19.awaitingResponse = false;
		}
	}
}
