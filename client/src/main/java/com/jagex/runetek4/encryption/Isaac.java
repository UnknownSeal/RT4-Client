package com.jagex.runetek4.encryption;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ij")
public final class Isaac {
	@OriginalMember(owner = "client!ij", name = "f", descriptor = "I")
	private int count;

	@OriginalMember(owner = "client!ij", name = "l", descriptor = "I")
	private int a;
	@OriginalMember(owner = "client!ij", name = "b", descriptor = "I")
	private int c;

	@OriginalMember(owner = "client!ij", name = "d", descriptor = "I")
	private int b;
	@OriginalMember(owner = "client!ij", name = "k", descriptor = "[I")
	private final int[] mem = new int[256];

	@OriginalMember(owner = "client!ij", name = "i", descriptor = "[I")
	private final int[] rsl = new int[256];

	@OriginalMember(owner = "client!ij", name = "<init>", descriptor = "([I)V")
	public Isaac(@OriginalArg(0) int[] seed) {
		for (@Pc(13) int index = 0; index < seed.length; index++) {
			this.rsl[index] = seed[index];
		}
		this.init();
	}

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(Z)V")
	private void init() {
		@Pc(14) int h = 0x9e3779b9;
		@Pc(16) int g = 0x9e3779b9;
		@Pc(18) int e = 0x9e3779b9;
		@Pc(20) int f = 0x9e3779b9;
		@Pc(22) int d = 0x9e3779b9;
		@Pc(24) int c = 0x9e3779b9;
		@Pc(26) int b = 0x9e3779b9;
		@Pc(27) int a = 0x9e3779b9;

		@Pc(29) int i;
		for (i = 0; i < 4; i++) {
			a ^= b << 11;
			d += a;
			b += c;
			b ^= c >>> 2;
			c += d;
			c ^= d << 8;
			e += c;
			f += b;
			d += f;
			d ^= f >>> 16;
			f += e;
			g += d;
			f ^= e << 10;
			h += f;
			e += g;
			e ^= g >>> 4;
			g += h;
			g ^= h << 8;
			b += g;
			a += e;
			h += a;
			h ^= a >>> 9;
			c += h;
			a += b;
		}
		for (i = 0; i < 256; i += 8) {
			a += this.rsl[i];
			b += this.rsl[i + 1];
			c += this.rsl[i + 2];
			d += this.rsl[i + 3];
			e += this.rsl[i + 5];
			f += this.rsl[i + 4];
			g += this.rsl[i + 6];
			h += this.rsl[i + 7];
			a ^= b << 11;
			b += c;
			b ^= c >>> 2;
			d += a;
			c += d;
			c ^= d << 8;
			f += b;
			d += f;
			d ^= f >>> 16;
			e += c;
			f += e;
			f ^= e << 10;
			h += f;
			g += d;
			e += g;
			e ^= g >>> 4;
			g += h;
			g ^= h << 8;
			a += e;
			h += a;
			b += g;
			h ^= a >>> 9;
			c += h;
			a += b;
			this.mem[i] = a;
			this.mem[i + 1] = b;
			this.mem[i + 2] = c;
			this.mem[i + 3] = d;
			this.mem[i + 4] = f;
			this.mem[i + 5] = e;
			this.mem[i + 6] = g;
			this.mem[i + 7] = h;
		}
		for (i = 0; i < 256; i += 8) {
			a += this.mem[i];
			b += this.mem[i + 1];
			c += this.mem[i + 2];
			d += this.mem[i + 3];
			f += this.mem[i + 4];
			e += this.mem[i + 5];
			g += this.mem[i + 6];
			h += this.mem[i + 7];
			a ^= b << 11;
			d += a;
			b += c;
			b ^= c >>> 2;
			c += d;
			f += b;
			c ^= d << 8;
			e += c;
			d += f;
			d ^= f >>> 16;
			f += e;
			f ^= e << 10;
			g += d;
			e += g;
			e ^= g >>> 4;
			h += f;
			g += h;
			a += e;
			g ^= h << 8;
			b += g;
			h += a;
			h ^= a >>> 9;
			c += h;
			a += b;
			this.mem[i] = a;
			this.mem[i + 1] = b;
			this.mem[i + 2] = c;
			this.mem[i + 3] = d;
			this.mem[i + 4] = f;
			this.mem[i + 5] = e;
			this.mem[i + 6] = g;
			this.mem[i + 7] = h;
		}
		this.generate();
		this.count = 256;
	}

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(I)I")
	public int getNextKey() {
		// TODO - Add proper support server side.
		boolean useIsaac = false;

		if (useIsaac) {
			if (this.count-- == 0) {
				this.generate();
				this.count = 255;
			}
			return this.rsl[this.count];
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!ij", name = "b", descriptor = "(I)V")
	private void generate() {
		this.b += ++this.c;

		for (@Pc(17) int i = 0; i < 256; i++) {
			@Pc(33) int x = this.mem[i];
			switch (i & 0x3) {
				case 0:
					this.a ^= this.a << 13;
					break;

				case 1:
					this.a ^= this.a >>> 6;
					break;

				case 2:
					this.a ^= this.a << 2;
					break;

				case 3:
					this.a ^= this.a >>> 16;
					break;
			}
			this.a += this.mem[i + 128 & 0xFF];
			@Pc(119) int y;
			this.mem[i] = y = this.b + this.a + this.mem[x >> 2 & 0xFF];
			this.rsl[i] = this.b = x + this.mem[y >> 8 >> 2 & 0xFF];
		}
	}
}