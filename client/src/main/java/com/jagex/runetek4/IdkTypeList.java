package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.idktype.IDKType;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class IdkTypeList {

	@OriginalMember(owner = "client!fe", name = "jb", descriptor = "Lclient!n;")
	public static final SoftLruHashTable types = new SoftLruHashTable(64);

	@OriginalMember(owner = "client!bd", name = "g", descriptor = "Lclient!ve;")
	public static Js5 modelsArchive;

	@OriginalMember(owner = "runetek4.client!ri", name = "c", descriptor = "Lclient!ve;")
	public static Js5 archive;

	@OriginalMember(owner = "client!ec", name = "z", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(I)V")
	public static void removeSoft() {
		types.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!jl", name = "a", descriptor = "(ILclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		modelsArchive = arg0;
		archive = arg1;
		count = archive.getGroupCapacity(3);
	}

	@OriginalMember(owner = "runetek4.client!gg", name = "d", descriptor = "(II)Lclient!dm;")
	public static IDKType get(@OriginalArg(0) int arg0) {
		@Pc(10) IDKType idkType = (IDKType) types.get((long) arg0);
		if (idkType != null) {
			return idkType;
		}
		@Pc(21) byte[] bytes = archive.getfile(3, arg0);
		idkType = new IDKType();
		if (bytes != null) {
			idkType.decode(new Packet(bytes));
		}
		types.put(idkType, (long) arg0);
		return idkType;
	}

    @OriginalMember(owner = "runetek4.client!te", name = "a", descriptor = "(IZ)V")
    public static void clean() {
        types.clean(5);
    }
}
