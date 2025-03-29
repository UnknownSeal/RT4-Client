package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class ObjTypeList {

	@OriginalMember(owner = "runetek4.client!jd", name = "c", descriptor = "Lclient!n;")
	public static final SoftLruHashTable objectSpriteCache = new SoftLruHashTable(100);

	@OriginalMember(owner = "runetek4.client!tl", name = "c", descriptor = "Lclient!n;")
	public static final SoftLruHashTable models = new SoftLruHashTable(50);

	@OriginalMember(owner = "client!cb", name = "Y", descriptor = "Lclient!n;")
	public static final SoftLruHashTable types = new SoftLruHashTable(64);

	@OriginalMember(owner = "runetek4.client!nh", name = "eb", descriptor = "I")
	public static int capacity;

	@OriginalMember(owner = "runetek4.client!fk", name = "j", descriptor = "[[I")
	public static int[][] anIntArrayArray10;

	@OriginalMember(owner = "runetek4.client!um", name = "U", descriptor = "Lclient!dd;")
	public static SoftwareFont font;

	@OriginalMember(owner = "runetek4.client!sj", name = "r", descriptor = "Lclient!ve;")
	public static Js5 modelArchive;

	@OriginalMember(owner = "runetek4.client!tg", name = "f", descriptor = "Z")
	public static boolean aBoolean276;

	@OriginalMember(owner = "runetek4.client!wa", name = "X", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array87 = null;

	@OriginalMember(owner = "runetek4.client!ld", name = "g", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array104 = null;

	@OriginalMember(owner = "runetek4.client!nd", name = "n", descriptor = "Lclient!ve;")
	public static Js5 archive;

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

	@OriginalMember(owner = "client!fk", name = "a", descriptor = "(IB)Lclient!h;")
    public static ObjType get(@OriginalArg(0) int id) {
        @Pc(6) ObjType objType = (ObjType) types.get((long) id);
        if (objType != null) {
            return objType;
        }
        @Pc(25) byte[] bytes = archive.getfile(getGroupId(id), getFileId(id));
        objType = new ObjType();
        objType.id = id;
        if (bytes != null) {
            objType.decode(new Packet(bytes));
        }
        objType.postDecode();

        if (objType.certTemplate != -1) {
            objType.generateCertificate(get(objType.certTemplate), get(objType.certLink));
        }
        if (objType.lentTemplate != -1) {
            objType.generateLent(get(objType.lentTemplate), get(objType.lentLink));
        }
        if (!aBoolean276 && objType.members) {
            objType.name = LocalizedText.MEMBERS_OBJECT;
            objType.team = 0;
            objType.interfaceOptions = aClass100Array104;
            objType.stockMarket = false;
            objType.groundOptions = aClass100Array87;
        }
        types.put(objType, (long) id);
        return objType;
    }

	@OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(ZBLclient!ve;Lclient!dd;Lclient!ve;)V")
	public static void init(@OriginalArg(2) Js5 arg0, @OriginalArg(3) SoftwareFont arg1, @OriginalArg(4) Js5 arg2) {
		aBoolean276 = true;
		modelArchive = arg2;
		archive = arg0;
		@Pc(23) int local23 = archive.capacity() - 1;
		capacity = archive.getGroupCapacity(local23) + local23 * 256;
		aClass100Array104 = new JString[] { null, null, null, null, LocalizedText.DROP};
		aClass100Array87 = new JString[] { null, null, LocalizedText.TAKE, null, null };
		font = arg1;
	}

	@OriginalMember(owner = "runetek4.client!i", name = "r", descriptor = "(I)V")
	public static void removeSoft() {
		types.removeSoft();
		models.removeSoft();
		objectSpriteCache.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(B)V")
	public static void clear() {
		types.clear();
		models.clear();
		objectSpriteCache.clear();
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "c", descriptor = "(II)V")
	public static void clean() {
		types.clear(5);
		models.clear(5);
		objectSpriteCache.clear(5);
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(ZI)V")
	public static void setAllowMembers(@OriginalArg(0) boolean arg0) {
		if (arg0 != aBoolean276) {
			aBoolean276 = arg0;
			clear();
		}
	}

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Z)V")
	public static void clearModels() {
		models.clear();
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "e", descriptor = "(B)V")
	public static void clearSprites() {
		objectSpriteCache.clear();
	}

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(IB)I")
	public static int getFileId(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IB)I")
	public static int getGroupId(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}
}
