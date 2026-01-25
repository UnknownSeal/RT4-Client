package com.jagex.core.compress;

import java.util.zip.Inflater;

import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ha")
public final class GZipDecompressor {

	@OriginalMember(owner = "client!ha", name = "j", descriptor = "Ljava/util/zip/Inflater;")
	private Inflater inflater;

	@OriginalMember(owner = "client!ha", name = "<init>", descriptor = "()V")
	public GZipDecompressor() {
		this(-1, 1000000, 1000000);
	}

	@OriginalMember(owner = "client!ha", name = "<init>", descriptor = "(III)V")
	private GZipDecompressor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "([BLclient!wa;Z)V")
	public void gunzip(@OriginalArg(0) byte[] data, @OriginalArg(1) Packet packet) {
		if (packet.data[packet.offset] != 31 || packet.data[packet.offset + 1] != -117) {
			throw new RuntimeException("Invalid GZIP header!");
		}
		if (this.inflater == null) {
			this.inflater = new Inflater(true);
		}
		try {
			this.inflater.setInput(packet.data, packet.offset + 10, -8 - (packet.offset + 10) + packet.data.length);
			this.inflater.inflate(data);
		} catch (@Pc(64) Exception exception) {
			this.inflater.reset();
			throw new RuntimeException("Invalid GZIP compressed data!");
		}
		this.inflater.reset();
	}
}