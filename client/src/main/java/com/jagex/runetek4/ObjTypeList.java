package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.node.NodeCache;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class ObjTypeList {

	@OriginalMember(owner = "runetek4.client!jd", name = "c", descriptor = "Lclient!n;")
	public static final NodeCache objectSpriteCache = new NodeCache(100);
	@OriginalMember(owner = "runetek4.client!nh", name = "eb", descriptor = "I")
	public static int capacity;

	@OriginalMember(owner = "runetek4.client!nh", name = "Z", descriptor = "I")
	public static int anInt3241 = 128;

	@OriginalMember(owner = "runetek4.client!fk", name = "j", descriptor = "[[I")
	public static int[][] anIntArrayArray10;
	@OriginalMember(owner = "runetek4.client!um", name = "U", descriptor = "Lclient!dd;")
	public static SoftwareFont font;

	@OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(IIIII)I")
	public static int method2569(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(22) int local22 = 65536 - MathUtils.cos[arg2 * 1024 / arg3] >> 1;
		return (arg0 * (65536 - local22) >> 16) + (arg1 * local22 >> 16);
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(I[B)Z")
	public static boolean method2572(@OriginalArg(1) byte[] arg0) {
		@Pc(13) Packet local13 = new Packet(arg0);
		@Pc(17) int local17 = local13.g1();
		if (local17 != 1) {
			return false;
		}
		@Pc(33) boolean local33 = local13.g1() == 1;
		if (local33) {
			Static97.method1962(local13);
		}
		Static69.method1546(local13);
		return true;
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(BII)V")
	public static void method2575(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		VarPlayerDefinition.varPlayerCache[arg1] = arg0;
		@Pc(20) LongNode local20 = (LongNode) Static199.aClass133_20.getNode((long) arg1);
		if (local20 == null) {
			local20 = new LongNode(4611686018427387905L);
			Static199.aClass133_20.put(local20, (long) arg1);
		} else if (local20.aLong55 != 4611686018427387905L) {
			local20.aLong55 = MonotonicTime.currentTimeMillis() + 500L | 0x4000000000000000L;
		}
	}

    @OriginalMember(owner = "client!fk", name = "a", descriptor = "(IB)Lclient!h;")
    public static ObjType get(@OriginalArg(0) int id) {
        @Pc(6) ObjType objType = (ObjType) Static27.aClass99_4.get((long) id);
        if (objType != null) {
            return objType;
        }
        @Pc(25) byte[] bytes = CacheArchive.aClass153_61.getfile(Static18.method554(id), Static247.method4247(id));
        objType = new ObjType();
        objType.anInt2354 = id;
        if (bytes != null) {
            objType.readValues(new Packet(bytes));
        }
        objType.postDecode();

        if (objType.certtemplate != -1) {
            objType.genCert(get(objType.certtemplate), get(objType.certlink));
        }
        if (objType.lenttemplate != -1) {
            objType.genLent(get(objType.lenttemplate), get(objType.lentlink));
        }
        if (!Static240.aBoolean276 && objType.members) {
            objType.name = LocalizedText.MEMBERS_OBJECT;
            objType.team = 0;
            objType.interfaceOptions = Static143.aClass100Array104;
            objType.stockmarket = false;
            objType.groundOptions = Static269.aClass100Array87;
        }
        Static27.aClass99_4.put(objType, (long) id);
        return objType;
    }
}
