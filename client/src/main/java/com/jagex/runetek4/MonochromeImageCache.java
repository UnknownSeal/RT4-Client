package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!nd")
public final class MonochromeImageCache {

	@OriginalMember(owner = "runetek4.client!nd", name = "c", descriptor = "[B")
	public static final byte[] aByteArray53 = new byte[32896];
	@OriginalMember(owner = "runetek4.client!a", name = "b", descriptor = "[I")
	public static final int[] anIntArray1 = new int[4096];
	@OriginalMember(owner = "client!bc", name = "N", descriptor = "Lclient!lb;")
	public static final MonochromeImageCacheEntry entry = new MonochromeImageCacheEntry(0, 0);

	@OriginalMember(owner = "runetek4.client!nd", name = "f", descriptor = "I")
	private int singleRow = -1;

	@OriginalMember(owner = "runetek4.client!nd", name = "k", descriptor = "I")
	private int size = 0;

	@OriginalMember(owner = "runetek4.client!nd", name = "g", descriptor = "Lclient!ih;")
	private LinkedList recentlyUsed = new LinkedList();

	@OriginalMember(owner = "runetek4.client!nd", name = "w", descriptor = "Z")
	public boolean invalid = false;

	@OriginalMember(owner = "runetek4.client!nd", name = "l", descriptor = "I")
	private final int height;

	@OriginalMember(owner = "runetek4.client!nd", name = "i", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "runetek4.client!nd", name = "o", descriptor = "[Lclient!lb;")
	private MonochromeImageCacheEntry[] entries;

	@OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "[[[I")
	private int[][][] pixels;

	static {
		@Pc(8) int local8 = 0;
		for (@Pc(10) int local10 = 0; local10 < 256; local10++) {
			for (@Pc(15) int local15 = 0; local15 <= local10; local15++) {
				aByteArray53[local8++] = (byte) (255.0D / Math.sqrt((double) ((float) (local15 * local15 + local10 * local10 + 65535) / 65535.0F)));
			}
		}

		for (@Pc(4) int local4 = 0; local4 < 4096; local4++) {
			MonochromeImageCache.anIntArray1[local4] = MonochromeImageCache.fade(local4);
		}
	}

	@OriginalMember(owner = "runetek4.client!nd", name = "<init>", descriptor = "(III)V")
	public MonochromeImageCache(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.height = arg1;
		this.capacity = arg0;
		this.entries = new MonochromeImageCacheEntry[this.height];
		this.pixels = new int[this.capacity][3][arg2];
	}

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BI)I")
	public static int fade(@OriginalArg(1) int t) {
		@Pc(13) int depth = t * (t * t >> 12) >> 12;
		@Pc(26) int x = t * 6 - 61440;
		@Pc(34) int y = (t * x >> 12) + 40960;
		return depth * y >> 12;
	}

	@OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(B)[[[I")
	public final int[][][] method3168() {
		if (this.height != this.capacity) {
			throw new RuntimeException("Can only retrieve a full image cache");
		}
		for (@Pc(27) int local27 = 0; local27 < this.capacity; local27++) {
			this.entries[local27] = entry;
		}
		return this.pixels;
	}

	@OriginalMember(owner = "runetek4.client!nd", name = "b", descriptor = "(B)V")
	public final void clear() {
		for (@Pc(7) int local7 = 0; local7 < this.capacity; local7++) {
			this.pixels[local7][0] = null;
			this.pixels[local7][1] = null;
			this.pixels[local7][2] = null;
			this.pixels[local7] = null;
		}
		this.entries = null;
		this.pixels = null;
		this.recentlyUsed.clear();
		this.recentlyUsed = null;
	}

	@OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(BI)[[I")
	public final int[][] get(@OriginalArg(1) int arg0) {
		if (this.capacity == this.height) {
			this.invalid = this.entries[arg0] == null;
			this.entries[arg0] = entry;
			return this.pixels[arg0];
		} else if (this.capacity == 1) {
			this.invalid = this.singleRow != arg0;
			this.singleRow = arg0;
			return this.pixels[0];
		} else {
			@Pc(44) MonochromeImageCacheEntry local44 = this.entries[arg0];
			if (local44 == null) {
				this.invalid = true;
				if (this.size < this.capacity) {
					local44 = new MonochromeImageCacheEntry(arg0, this.size);
					this.size++;
				} else {
					@Pc(80) MonochromeImageCacheEntry local80 = (MonochromeImageCacheEntry) this.recentlyUsed.tail();
					local44 = new MonochromeImageCacheEntry(arg0, local80.row);
					this.entries[local80.index] = null;
					local80.unlink();
				}
				this.entries[arg0] = local44;
			} else {
				this.invalid = false;
			}
			this.recentlyUsed.addHead(local44);
			return this.pixels[local44.row];
		}
	}
}
