package com.jagex.runetek4.js5.network;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5Request;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!pm")
public final class Js5NetRequest extends Js5Request {

	@OriginalMember(owner = "client!pm", name = "bb", descriptor = "Lclient!wa;")
	public Packet packet;

	@OriginalMember(owner = "client!pm", name = "U", descriptor = "B")
	public byte offset;

	@OriginalMember(owner = "client!pm", name = "Z", descriptor = "I")
	public int blockPosition;

	@OriginalMember(owner = "client!pm", name = "b", descriptor = "(Z)[B")
	@Override
	public byte[] getBytes() {
		if (this.awaitingResponse || this.packet.offset < this.packet.data.length - this.offset) {
			throw new RuntimeException("Not ready!");
		}
		return this.packet.data;
	}

	@OriginalMember(owner = "client!pm", name = "a", descriptor = "(Z)I")
	@Override
	public int getPercentageComplete() {
		return this.packet == null ? 0 : this.packet.offset * 100 / (this.packet.data.length - this.offset);
	}
}
