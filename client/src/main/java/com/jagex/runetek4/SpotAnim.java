package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.cache.media.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!bh")
public final class SpotAnim extends Entity {

	@OriginalMember(owner = "runetek4.client!bh", name = "y", descriptor = "Lclient!ga;")
	private ParticleSystem aClass47_Sub1_1;

	@OriginalMember(owner = "runetek4.client!bh", name = "B", descriptor = "I")
	private int anInt593 = 0;

	@OriginalMember(owner = "runetek4.client!bh", name = "O", descriptor = "I")
	private final int anInt602 = -1;

	@OriginalMember(owner = "runetek4.client!bh", name = "P", descriptor = "I")
	private int anInt603 = -32768;

	@OriginalMember(owner = "runetek4.client!bh", name = "T", descriptor = "Z")
	public boolean seqComplete = false;

	@OriginalMember(owner = "runetek4.client!bh", name = "U", descriptor = "I")
	private int anInt607 = 0;

	@OriginalMember(owner = "runetek4.client!bh", name = "I", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "runetek4.client!bh", name = "Q", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "runetek4.client!bh", name = "S", descriptor = "I")
	public final int level;

	@OriginalMember(owner = "runetek4.client!bh", name = "w", descriptor = "I")
	public final int startCycle;

	@OriginalMember(owner = "runetek4.client!bh", name = "F", descriptor = "I")
	private final int anInt596;

	@OriginalMember(owner = "runetek4.client!bh", name = "K", descriptor = "I")
	public final int anInt599;

	@OriginalMember(owner = "runetek4.client!bh", name = "J", descriptor = "Lclient!tk;")
	private SeqType aClass144_1;

	@OriginalMember(owner = "runetek4.client!bh", name = "<init>", descriptor = "(IIIIIII)V")
	public SpotAnim(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		this.z = arg3;
		this.x = arg2;
		this.level = arg1;
		this.startCycle = arg6 + arg5;
		this.anInt596 = arg0;
		this.anInt599 = arg4;
		@Pc(42) int local42 = Static34.method877(this.anInt596).animationId;
		if (local42 == -1) {
			this.seqComplete = true;
		} else {
			this.seqComplete = false;
			this.aClass144_1 = SeqTypeList.getAnimationSequence(local42);
		}
	}

	@OriginalMember(owner = "runetek4.client!bh", name = "b", descriptor = "(Z)Lclient!ak;")
	private Model method552() {
		@Pc(8) SpotAnimDefinition local8 = Static34.method877(this.anInt596);
		@Pc(26) Model local26;
		if (this.seqComplete) {
			local26 = local8.getModel(-1, -1, 0);
		} else {
			local26 = local8.getModel(this.anInt602, this.anInt593, this.anInt607);
		}
		return local26 == null ? null : local26;
	}

	@OriginalMember(owner = "runetek4.client!bh", name = "b", descriptor = "()I")
	@Override
	public final int getMinY() {
		return this.anInt603;
	}

	@OriginalMember(owner = "runetek4.client!bh", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		@Pc(7) Model local7 = this.method552();
		if (local7 != null) {
			local7.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_1);
			this.anInt603 = local7.getMinY();
		}
	}

	@OriginalMember(owner = "runetek4.client!bh", name = "a", descriptor = "(ZI)V")
	public final void update(@OriginalArg(1) int arg0) {
		if (this.seqComplete) {
			return;
		}
		this.anInt607 += arg0;
		while (this.anInt607 > this.aClass144_1.frames[this.anInt593]) {
			this.anInt607 -= this.aClass144_1.frames[this.anInt593];
			this.anInt593++;
			if (this.aClass144_1.anIntArray473.length <= this.anInt593) {
				this.seqComplete = true;
				break;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!bh", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}
}
