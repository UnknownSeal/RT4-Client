package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ff")
public final class SynthEnvelope {

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "I")
	public int anInt1958;

	@OriginalMember(owner = "runetek4.client!ff", name = "d", descriptor = "I")
	public int anInt1959;

	@OriginalMember(owner = "runetek4.client!ff", name = "f", descriptor = "I")
	public int anInt1960;

	@OriginalMember(owner = "runetek4.client!ff", name = "g", descriptor = "I")
	private int anInt1961;

	@OriginalMember(owner = "runetek4.client!ff", name = "h", descriptor = "I")
	private int anInt1962;

	@OriginalMember(owner = "runetek4.client!ff", name = "i", descriptor = "I")
	private int anInt1963;

	@OriginalMember(owner = "runetek4.client!ff", name = "j", descriptor = "I")
	private int anInt1964;

	@OriginalMember(owner = "runetek4.client!ff", name = "k", descriptor = "I")
	private int anInt1965;

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "I")
	private int anInt1957 = 2;

	@OriginalMember(owner = "runetek4.client!ff", name = "e", descriptor = "[I")
	private int[] anIntArray157 = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "c", descriptor = "[I")
	private int[] anIntArray156 = new int[2];

	@OriginalMember(owner = "runetek4.client!ff", name = "<init>", descriptor = "()V")
	public SynthEnvelope() {
		this.anIntArray157[0] = 0;
		this.anIntArray157[1] = 65535;
		this.anIntArray156[0] = 0;
		this.anIntArray156[1] = 65535;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(I)I")
	public final int method1512(@OriginalArg(0) int arg0) {
		if (this.anInt1965 >= this.anInt1964) {
			this.anInt1962 = this.anIntArray156[this.anInt1961++] << 15;
			if (this.anInt1961 >= this.anInt1957) {
				this.anInt1961 = this.anInt1957 - 1;
			}
			this.anInt1964 = (int) ((double) this.anIntArray157[this.anInt1961] / 65536.0D * (double) arg0);
			if (this.anInt1964 > this.anInt1965) {
				this.anInt1963 = ((this.anIntArray156[this.anInt1961] << 15) - this.anInt1962) / (this.anInt1964 - this.anInt1965);
			}
		}
		this.anInt1962 += this.anInt1963;
		this.anInt1965++;
		return this.anInt1962 - this.anInt1963 >> 15;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "()V")
	public final void method1513() {
		this.anInt1964 = 0;
		this.anInt1961 = 0;
		this.anInt1963 = 0;
		this.anInt1962 = 0;
		this.anInt1965 = 0;
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "a", descriptor = "(Lclient!wa;)V")
	public final void method1514(@OriginalArg(0) Packet arg0) {
		this.anInt1957 = arg0.g1();
		this.anIntArray157 = new int[this.anInt1957];
		this.anIntArray156 = new int[this.anInt1957];
		for (@Pc(16) int local16 = 0; local16 < this.anInt1957; local16++) {
			this.anIntArray157[local16] = arg0.g2();
			this.anIntArray156[local16] = arg0.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!ff", name = "b", descriptor = "(Lclient!wa;)V")
	public final void method1515(@OriginalArg(0) Packet arg0) {
		this.anInt1958 = arg0.g1();
		this.anInt1959 = arg0.g4();
		this.anInt1960 = arg0.g4();
		this.method1514(arg0);
	}
}
