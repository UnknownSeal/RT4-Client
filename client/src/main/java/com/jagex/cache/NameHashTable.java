package com.jagex.cache;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!jg")
public final class NameHashTable {

	@OriginalMember(owner = "runetek4.client!jg", name = "f", descriptor = "[I")
	private final int[] table;

	@OriginalMember(owner = "runetek4.client!jg", name = "<init>", descriptor = "([I)V")
	public NameHashTable(@OriginalArg(0) int[] names) {
		@Pc(5) int size;
		for (size = 1; size <= (names.length >> 1) + names.length; size <<= 0x1) {
		}
		this.table = new int[size + size];
		@Pc(28) int i;
		for (i = 0; i < size + size; i++) {
			this.table[i] = -1;
		}
		i = 0;
		while (names.length > i) {
			@Pc(55) int pos;
			for (pos = size - 1 & names[i]; this.table[pos + pos + 1] != -1; pos = size - 1 & pos + 1) {
			}
			this.table[pos + pos] = names[i];
			this.table[pos + pos + 1] = i++;
		}
	}

	@OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(II)I")
	public final int find(@OriginalArg(0) int arg0) {
		@Pc(16) int size = (this.table.length >> 1) - 1;
		@Pc(20) int position = size & arg0;
		while (true) {
			@Pc(29) int i = this.table[position + position + 1];
			if (i == -1) {
				return -1;
			}
			if (arg0 == this.table[position + position]) {
				return i;
			}
			position = position + 1 & size;
		}
	}
}
