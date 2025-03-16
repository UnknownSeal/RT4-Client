package com.jagex.runetek4.cache.def;

import com.jagex.runetek4.node.NodeCache;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

// VarpType
@OriginalClass("client!eh")
public final class VarPlayerDefinition {

	@OriginalMember(owner = "runetek4.client!me", name = "P", descriptor = "[I")
	public static final int[] varcs = new int[2000];

	@OriginalMember(owner = "client!ah", name = "j", descriptor = "[I")
	public static final int[] varPlayers = new int[2500];

	@OriginalMember(owner = "runetek4.client!sm", name = "c", descriptor = "Lclient!n;")
	public static final NodeCache varPlayerDefinitionCache = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!ic", name = "e", descriptor = "[I")
	public static final int[] varPlayerCache = new int[2500];

	@OriginalMember(owner = "runetek4.client!gg", name = "ab", descriptor = "Lclient!ve;")
	public static CacheArchive gameDefinitionsCacheArchive;

	@OriginalMember(owner = "runetek4.client!nb", name = "p", descriptor = "I")
	public static int varPlayerDefinitionsSize;

	@OriginalMember(owner = "client!eh", name = "e", descriptor = "I")
	public int type = 0;

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(II)Lclient!eh;")
	public static VarPlayerDefinition getDefinition(@OriginalArg(1) int varPlayerIndex) {
		@Pc(10) VarPlayerDefinition definition = (VarPlayerDefinition) varPlayerDefinitionCache.get((long) varPlayerIndex);
		if (definition != null) {
			return definition;
		}
		@Pc(20) byte[] cacheData = gameDefinitionsCacheArchive.getfile(16, varPlayerIndex);
		definition = new VarPlayerDefinition();
		if (cacheData != null) {
			definition.decode(new Packet(cacheData));
		}
		varPlayerDefinitionCache.put(definition, (long) varPlayerIndex);
		return definition;
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(Lclient!ve;B)V")
	public static void initializeVarPlayerDefinitionCache(@OriginalArg(0) CacheArchive cacheArchive) {
		gameDefinitionsCacheArchive = cacheArchive;
		varPlayerDefinitionsSize = gameDefinitionsCacheArchive.fileLength(16);
	}

	@OriginalMember(owner = "client!bn", name = "c", descriptor = "(II)V")
	public static void clearVarPlayerDefinitionCache() {
		varPlayerDefinitionCache.clear(5);
	}

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(Lclient!wa;BI)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(2) int code) {
		if (code == 5) {
			this.type = packet.g2();
		}
	}
}
