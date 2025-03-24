package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!mf")
public final class MidiNote extends Node {

	@OriginalMember(owner = "runetek4.client!mf", name = "s", descriptor = "I")
	public int anInt3763;

	@OriginalMember(owner = "runetek4.client!mf", name = "t", descriptor = "I")
	public int anInt3764;

	@OriginalMember(owner = "runetek4.client!mf", name = "u", descriptor = "I")
	public int anInt3765;

	@OriginalMember(owner = "runetek4.client!mf", name = "w", descriptor = "Lclient!wh;")
	public Midi_Class162 aMidiClass162_1;

	@OriginalMember(owner = "runetek4.client!mf", name = "y", descriptor = "I")
	public int anInt3767;

	@OriginalMember(owner = "runetek4.client!mf", name = "z", descriptor = "Lclient!b;")
	public SoundPcmStream stream;

	@OriginalMember(owner = "runetek4.client!mf", name = "A", descriptor = "I")
	public int anInt3768;

	@OriginalMember(owner = "runetek4.client!mf", name = "B", descriptor = "Lclient!kj;")
	public PcmSound sound;

	@OriginalMember(owner = "runetek4.client!mf", name = "C", descriptor = "I")
	public int anInt3769;

	@OriginalMember(owner = "runetek4.client!mf", name = "D", descriptor = "I")
	public int anInt3770;

	@OriginalMember(owner = "runetek4.client!mf", name = "E", descriptor = "I")
	public int anInt3771;

	@OriginalMember(owner = "runetek4.client!mf", name = "F", descriptor = "I")
	public int anInt3772;

	@OriginalMember(owner = "runetek4.client!mf", name = "G", descriptor = "I")
	public int channel;

	@OriginalMember(owner = "runetek4.client!mf", name = "H", descriptor = "I")
	public int anInt3774;

	@OriginalMember(owner = "runetek4.client!mf", name = "I", descriptor = "I")
	public int anInt3775;

	@OriginalMember(owner = "runetek4.client!mf", name = "J", descriptor = "I")
	public int anInt3776;

	@OriginalMember(owner = "runetek4.client!mf", name = "L", descriptor = "I")
	public int anInt3777;

	@OriginalMember(owner = "runetek4.client!mf", name = "N", descriptor = "I")
	public int anInt3779;

	@OriginalMember(owner = "runetek4.client!mf", name = "Q", descriptor = "I")
	public int anInt3781;

	@OriginalMember(owner = "runetek4.client!mf", name = "R", descriptor = "I")
	public int anInt3782;

	@OriginalMember(owner = "runetek4.client!mf", name = "V", descriptor = "Lclient!jk;")
	public MidiInstrument instrument;

	@OriginalMember(owner = "runetek4.client!mf", name = "d", descriptor = "(I)V")
	public final void method2957() {
		this.aMidiClass162_1 = null;
		this.sound = null;
		this.stream = null;
		this.instrument = null;
	}
}
