package com.jagex.runetek4.game.client.scene.entities;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.config.seqtype.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ra")
public final class ProjectileAnimation extends Entity {

	@OriginalMember(owner = "runetek4.client!ra", name = "u", descriptor = "D")
	private double velocityX;

	@OriginalMember(owner = "runetek4.client!ra", name = "A", descriptor = "D")
	public double y;

	@OriginalMember(owner = "runetek4.client!ra", name = "N", descriptor = "D")
	private double accelerationZ;

	@OriginalMember(owner = "runetek4.client!ra", name = "Q", descriptor = "Lclient!ga;")
	private ParticleSystem aClass47_Sub1_6;

	@OriginalMember(owner = "runetek4.client!ra", name = "U", descriptor = "D")
	private double velocityZ;

	@OriginalMember(owner = "runetek4.client!ra", name = "X", descriptor = "D")
	public double z;

	@OriginalMember(owner = "runetek4.client!ra", name = "ab", descriptor = "I")
	private int pitch;

	@OriginalMember(owner = "runetek4.client!ra", name = "bb", descriptor = "D")
	private double velocityY;

	@OriginalMember(owner = "runetek4.client!ra", name = "db", descriptor = "D")
	public double x;

	@OriginalMember(owner = "runetek4.client!ra", name = "eb", descriptor = "D")
	private double velocity;

	@OriginalMember(owner = "runetek4.client!ra", name = "hb", descriptor = "I")
	public int yaw;

	@OriginalMember(owner = "runetek4.client!ra", name = "v", descriptor = "I")
	private int frameCycle = 0;

	@OriginalMember(owner = "runetek4.client!ra", name = "I", descriptor = "Z")
	private boolean isMobile = false;

	@OriginalMember(owner = "runetek4.client!ra", name = "x", descriptor = "I")
	private int anInt4798 = -1;

	@OriginalMember(owner = "runetek4.client!ra", name = "fb", descriptor = "I")
	private int seqFrame = 0;

	@OriginalMember(owner = "runetek4.client!ra", name = "T", descriptor = "I")
	private int anInt4814 = -32768;

	@OriginalMember(owner = "runetek4.client!ra", name = "z", descriptor = "I")
	public final int lastCycle;

	@OriginalMember(owner = "runetek4.client!ra", name = "E", descriptor = "I")
	private final int sourceX;

	@OriginalMember(owner = "runetek4.client!ra", name = "cb", descriptor = "I")
	public final int anInt4819;

	@OriginalMember(owner = "runetek4.client!ra", name = "Y", descriptor = "I")
	private final int anInt4816;

	@OriginalMember(owner = "runetek4.client!ra", name = "M", descriptor = "I")
	public final int anInt4810;

	@OriginalMember(owner = "runetek4.client!ra", name = "mb", descriptor = "I")
	private final int elevationPitch;

	@OriginalMember(owner = "runetek4.client!ra", name = "Z", descriptor = "I")
	private final int sourceY;

	@OriginalMember(owner = "runetek4.client!ra", name = "F", descriptor = "I")
	public final int anInt4805;

	@OriginalMember(owner = "runetek4.client!ra", name = "w", descriptor = "I")
	private final int arcScale;

	@OriginalMember(owner = "runetek4.client!ra", name = "ib", descriptor = "I")
	public final int anInt4822;

	@OriginalMember(owner = "runetek4.client!ra", name = "S", descriptor = "I")
	private final int sourceZ;

	@OriginalMember(owner = "runetek4.client!ra", name = "gb", descriptor = "Lclient!tk;")
	private final SeqType seqType;

	@OriginalMember(owner = "runetek4.client!ra", name = "<init>", descriptor = "(IIIIIIIIIII)V")
	public ProjectileAnimation(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10) {
		this.lastCycle = arg6;
		this.sourceX = arg2;
		this.anInt4819 = arg9;
		this.anInt4816 = arg0;
		this.anInt4810 = arg1;
		this.elevationPitch = arg7;
		this.sourceY = arg3;
		this.isMobile = false;
		this.anInt4805 = arg10;
		this.arcScale = arg8;
		this.anInt4822 = arg5;
		this.sourceZ = arg4;
		@Pc(58) int local58 = Static34.method877(this.anInt4816).anInt1754;
		if (local58 == -1) {
			this.seqType = null;
		} else {
			this.seqType = Static36.method941(local58);
		}
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "b", descriptor = "(I)Lclient!ak;")
	private Model method3703() {
		@Pc(14) SpotAnimType local14 = Static34.method877(this.anInt4816);
		@Pc(24) Model local24 = local14.method1319(this.anInt4798, this.seqFrame, this.frameCycle);
		if (local24 == null) {
			return null;
		} else {
			local24.method4574(this.pitch);
			return local24;
		}
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "b", descriptor = "(BI)V")
	public final void method3704(@OriginalArg(1) int arg0) {
		this.isMobile = true;
		this.x += this.velocityX * (double) arg0;
		this.y += this.velocityY * (double) arg0;
		if (this.elevationPitch == -1) {
			this.z += this.velocityZ * (double) arg0;
		} else {
			this.z += (double) arg0 * this.accelerationZ * 0.5D * (double) arg0 + (double) arg0 * this.velocityZ;
			this.velocityZ += this.accelerationZ * (double) arg0;
		}
		this.yaw = (int) (Math.atan2(this.velocityX, this.velocityY) * 325.949D) + 1024 & 0x7FF;
		this.pitch = (int) (Math.atan2(this.velocityZ, this.velocity) * 325.949D) & 0x7FF;
		if (this.seqType == null) {
			return;
		}
		this.frameCycle += arg0;
		while (true) {
			do {
				do {
					if (this.frameCycle <= this.seqType.frames[this.seqFrame]) {
						return;
					}
					this.frameCycle -= this.seqType.frames[this.seqFrame];
					this.seqFrame++;
					if (this.seqFrame >= this.seqType.anIntArray473.length) {
						this.seqFrame -= this.seqType.replayoff;
						if (this.seqFrame < 0 || this.seqType.anIntArray473.length <= this.seqFrame) {
							this.seqFrame = 0;
						}
					}
					this.anInt4798 = this.seqFrame + 1;
				} while (this.seqType.anIntArray473.length > this.anInt4798);
				this.anInt4798 -= this.seqType.replayoff;
			} while (this.anInt4798 >= 0 && this.anInt4798 < this.seqType.anIntArray473.length);
			this.anInt4798 = -1;
		}
	}

	@OriginalMember(owner = "client!ra", name = "b", descriptor = "(IIIII)V")
	public void setTarget(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		@Pc(17) double local17;
		if (!this.isMobile) {
			@Pc(10) double local10 = (double) (arg0 - this.sourceY);
			local17 = arg3 - this.sourceX;
			@Pc(26) double local26 = Math.sqrt(local17 * local17 + local10 * local10);
			this.x = (double) this.arcScale * local17 / local26 + (double) this.sourceX;
			this.y = (double) this.arcScale * local10 / local26 + (double) this.sourceY;
			this.z = this.sourceZ;
		}
		local17 = this.lastCycle + 1 - arg1;
		this.velocityX = ((double) arg3 - this.x) / local17;
		this.velocityY = ((double) arg0 - this.y) / local17;
		this.velocity = Math.sqrt(this.velocityY * this.velocityY + this.velocityX * this.velocityX);

		if (this.elevationPitch == -1) {
			this.velocityZ = ((double) arg2 - this.z) / local17;
		} else {
			if (!this.isMobile) {
				this.velocityZ = -this.velocity * Math.tan((double) this.elevationPitch * 0.02454369D);
			}
			this.accelerationZ = ((double) arg2 - this.z - this.velocityZ * local17) * 2.0D / (local17 * local17);
		}
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void method4546(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		@Pc(3) Model local3 = this.method3703();
		if (local3 != null) {
			local3.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_6);
			this.anInt4814 = local3.method4549();
		}
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "b", descriptor = "()I")
	@Override
	public final int method4549() {
		return this.anInt4814;
	}
}
