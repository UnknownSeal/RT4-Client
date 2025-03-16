package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jm")
public final class AnimBase extends Node {

	@OriginalMember(owner = "client!jm", name = "p", descriptor = "I")
	public final int anInt3113;

	@OriginalMember(owner = "client!jm", name = "x", descriptor = "I")
	public final int anInt3116;

	@OriginalMember(owner = "client!jm", name = "v", descriptor = "[[I")
	public final int[][] anIntArrayArray23;

	@OriginalMember(owner = "client!jm", name = "B", descriptor = "[I")
	public final int[] types;

	@OriginalMember(owner = "client!jm", name = "y", descriptor = "[Z")
	public final boolean[] aBooleanArray70;

	@OriginalMember(owner = "client!jm", name = "q", descriptor = "[I")
	public final int[] anIntArray290;

	@OriginalMember(owner = "client!jm", name = "<init>", descriptor = "(I[B)V")
	public AnimBase(@OriginalArg(0) int arg0, @OriginalArg(1) byte[] arg1) {
		this.anInt3113 = arg0;
		@Pc(9) Packet packet = new Packet(arg1);
		this.anInt3116 = packet.g1();
		this.anIntArrayArray23 = new int[this.anInt3116][];
		this.types = new int[this.anInt3116];
		this.aBooleanArray70 = new boolean[this.anInt3116];
		this.anIntArray290 = new int[this.anInt3116];
		@Pc(36) int _unused;

		for (int i = 0; i < this.anInt3116; i++) {
			this.types[i] = packet.g1();
		}
		for (int i = 0; i < this.anInt3116; i++) {
			this.aBooleanArray70[i] = packet.g1() == 1;
		}
		for (int i = 0; i < this.anInt3116; i++) {
			this.anIntArray290[i] = packet.g2();
		}
		for (int i = 0; i < this.anInt3116; i++) {
			this.anIntArrayArray23[i] = new int[packet.g1()];
		}
		for (int i = 0; i < this.anInt3116; i++) {
			for (@Pc(118) int j = 0; j < this.anIntArrayArray23[i].length; j++) {
				this.anIntArrayArray23[i][j] = packet.g1();
			}
		}
	}
}
