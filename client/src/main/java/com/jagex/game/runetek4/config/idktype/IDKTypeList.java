package com.jagex.game.runetek4.config.idktype;

import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class IDKTypeList {

	@OriginalMember(owner = "client!fe", name = "jb", descriptor = "Lclient!n;")
	public static final SoftLruHashTable types = new SoftLruHashTable(64);

	@OriginalMember(owner = "client!bd", name = "g", descriptor = "Lclient!ve;")
	public static Js5 modelsArchive;

	@OriginalMember(owner = "client!ri", name = "c", descriptor = "Lclient!ve;")
	public static Js5 archive;

	@OriginalMember(owner = "client!ec", name = "z", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!oi", name = "a", descriptor = "(I)V")
	public static void removeSoft() {
		types.removeSoft();
	}

	@OriginalMember(owner = "client!jl", name = "a", descriptor = "(ILclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		modelsArchive = arg0;
		archive = arg1;
		count = archive.getGroupCapacity(3);
	}

	@OriginalMember(owner = "client!gg", name = "d", descriptor = "(II)Lclient!dm;")
	public static IDKType get(@OriginalArg(0) int arg0) {
		@Pc(10) IDKType idkType = (IDKType) types.get(arg0);
		if (idkType != null) {
			return idkType;
		}
		@Pc(21) byte[] bytes = archive.getfile(3, arg0);
		idkType = new IDKType();
		if (bytes != null) {
			idkType.decode(new Packet(bytes));
		}
		types.put(idkType, arg0);
		return idkType;
	}

    @OriginalMember(owner = "client!te", name = "a", descriptor = "(IZ)V")
    public static void clean() {
        types.clean(5);
    }
}
