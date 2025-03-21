package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.cache.media.SeqType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ra")
public final class ProjectileAnimation extends Entity {

	@OriginalMember(owner = "client!ra", name = "u", descriptor = "D")
	private double velocityX;

	@OriginalMember(owner = "client!ra", name = "A", descriptor = "D")
	public double y;

	@OriginalMember(owner = "client!ra", name = "N", descriptor = "D")
	private double accelerationZ;

	@OriginalMember(owner = "client!ra", name = "Q", descriptor = "Lclient!ga;")
	private ParticleSystem aClass47_Sub1_6;

	@OriginalMember(owner = "client!ra", name = "U", descriptor = "D")
	private double velocityZ;

	@OriginalMember(owner = "client!ra", name = "X", descriptor = "D")
	public double z;

	@OriginalMember(owner = "client!ra", name = "ab", descriptor = "I")
	private int pitch;

	@OriginalMember(owner = "client!ra", name = "bb", descriptor = "D")
	private double valocityY;

	@OriginalMember(owner = "client!ra", name = "db", descriptor = "D")
	public double x;

	@OriginalMember(owner = "client!ra", name = "eb", descriptor = "D")
	private double velocity;

	@OriginalMember(owner = "client!ra", name = "hb", descriptor = "I")
	public int yaw;

	@OriginalMember(owner = "client!ra", name = "v", descriptor = "I")
	private int frameCycle = 0;

	@OriginalMember(owner = "client!ra", name = "I", descriptor = "Z")
	private boolean isMobile = false;

	@OriginalMember(owner = "client!ra", name = "x", descriptor = "I")
	private int anInt4798 = -1;

	@OriginalMember(owner = "client!ra", name = "fb", descriptor = "I")
	private int seqFrame = 0;

	@OriginalMember(owner = "client!ra", name = "T", descriptor = "I")
	private int anInt4814 = -32768;

	@OriginalMember(owner = "client!ra", name = "z", descriptor = "I")
	public final int lastCycle;

	@OriginalMember(owner = "client!ra", name = "E", descriptor = "I")
	private final int sourceX;

	@OriginalMember(owner = "client!ra", name = "cb", descriptor = "I")
	public final int target;

	@OriginalMember(owner = "client!ra", name = "Y", descriptor = "I")
	private final int anInt4816;

	@OriginalMember(owner = "client!ra", name = "M", descriptor = "I")
	public final int level;

	@OriginalMember(owner = "client!ra", name = "mb", descriptor = "I")
	private final int elevationPitch;

	@OriginalMember(owner = "client!ra", name = "Z", descriptor = "I")
	private final int sourceY;

	@OriginalMember(owner = "client!ra", name = "F", descriptor = "I")
	public final int anInt4805;

	@OriginalMember(owner = "client!ra", name = "w", descriptor = "I")
	private final int arcScale;

	@OriginalMember(owner = "client!ra", name = "ib", descriptor = "I")
	public final int startCycle;

	@OriginalMember(owner = "client!ra", name = "S", descriptor = "I")
	private final int sourceZ;

	@OriginalMember(owner = "client!ra", name = "gb", descriptor = "Lclient!tk;")
	private final SeqType seqType;

	@OriginalMember(owner = "client!ra", name = "<init>", descriptor = "(IIIIIIIIIII)V")
	public ProjectileAnimation(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10) {
		this.lastCycle = arg6;
		this.sourceX = arg2;
		this.target = arg9;
		this.anInt4816 = arg0;
		this.level = arg1;
		this.elevationPitch = arg7;
		this.sourceY = arg3;
		this.isMobile = false;
		this.anInt4805 = arg10;
		this.arcScale = arg8;
		this.startCycle = arg5;
		this.sourceZ = arg4;
		@Pc(58) int local58 = Static34.method877(this.anInt4816).animationId;
		if (local58 == -1) {
			this.seqType = null;
		} else {
			this.seqType = SeqTypeList.getAnimationSequence(local58);
		}
	}

	@OriginalMember(owner = "client!ra", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!ra", name = "b", descriptor = "(I)Lclient!ak;")
	private Model method3703() {
		@Pc(14) SpotAnimDefinition local14 = Static34.method877(this.anInt4816);
		@Pc(24) Model local24 = local14.getModel(this.anInt4798, this.seqFrame, this.frameCycle);
		if (local24 == null) {
			return null;
		} else {
			local24.rotateX(this.pitch);
			return local24;
		}
	}

	@OriginalMember(owner = "client!ra", name = "b", descriptor = "(BI)V")
	public void update(@OriginalArg(1) int arg0) {
		this.x += this.velocityX * (double) arg0;
		this.y += this.valocityY * (double) arg0;
		this.isMobile = true;
		if (this.elevationPitch == -1) {
			this.z += this.velocityZ * (double) arg0;
		} else {
			this.z += (double) arg0 * this.accelerationZ * 0.5D * (double) arg0 + (double) arg0 * this.velocityZ;
			this.velocityZ += this.accelerationZ * (double) arg0;
		}
		this.yaw = (int) (Math.atan2(this.velocityX, this.valocityY) * 325.949D) + 1024 & 0x7FF;
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
	public void updateVelocity(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		@Pc(17) double local17;
		if (!this.isMobile) {
			@Pc(10) double local10 = arg0 - this.sourceY;
			local17 = arg3 - this.sourceX;
			@Pc(26) double local26 = Math.sqrt(local17 * local17 + local10 * local10);
			this.z = this.sourceZ;
			this.y = local10 * (double) this.arcScale / local26 + (double) this.sourceY;
			this.x = (double) this.arcScale * local17 / local26 + (double) this.sourceX;
		}
		local17 = this.lastCycle + 1 - arg1;
		this.valocityY = ((double) arg0 - this.y) / local17;
		this.velocityX = ((double) arg3 - this.x) / local17;
		this.velocity = Math.sqrt(this.valocityY * this.valocityY + this.velocityX * this.velocityX);
		if (this.elevationPitch == -1) {
			this.velocityZ = ((double) arg2 - this.z) / local17;
		} else {
			if (!this.isMobile) {
				this.velocityZ = -this.velocity * Math.tan((double) this.elevationPitch * 0.02454369D);
			}
			this.accelerationZ = ((double) arg2 - this.z - this.velocityZ * local17) * 2.0D / (local17 * local17);
		}
	}

	@OriginalMember(owner = "client!ra", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		@Pc(3) Model local3 = this.method3703();
		if (local3 != null) {
			local3.render(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_6);
			this.anInt4814 = local3.getMinY();
		}
	}

	@OriginalMember(owner = "client!ra", name = "b", descriptor = "()I")
	@Override
	public int getMinY() {
		return this.anInt4814;
	}
}
