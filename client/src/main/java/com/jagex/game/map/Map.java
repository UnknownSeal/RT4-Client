package com.jagex.game.map;

import com.jagex.core.datastruct.LinkedList;
import com.jagex.core.io.Packet;
import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!bn")
public final class Map extends Node2 {

	@OriginalMember(owner = "runetek4.client!bn", name = "K", descriptor = "I")
	public int displayMaxX = 0;

	@OriginalMember(owner = "runetek4.client!bn", name = "L", descriptor = "I")
	public int backgroundColor = -1;

	@OriginalMember(owner = "runetek4.client!bn", name = "S", descriptor = "I")
	public int displayMinZ = 12800;

	@OriginalMember(owner = "runetek4.client!bn", name = "Z", descriptor = "I")
	public int displayMaxZ = 0;

	@OriginalMember(owner = "runetek4.client!bn", name = "cb", descriptor = "I")
	public int displayMinX = 12800;

	@OriginalMember(owner = "runetek4.client!bn", name = "P", descriptor = "Z")
	public boolean valid = true;

	@OriginalMember(owner = "runetek4.client!bn", name = "db", descriptor = "I")
	public int defaultZoom = -1;

	@OriginalMember(owner = "runetek4.client!bn", name = "T", descriptor = "I")
	public final int originZ;

	@OriginalMember(owner = "runetek4.client!bn", name = "bb", descriptor = "Lclient!na;")
	public final JString group;

	@OriginalMember(owner = "runetek4.client!bn", name = "Q", descriptor = "Lclient!na;")
	public final JString name;

	@OriginalMember(owner = "runetek4.client!bn", name = "Y", descriptor = "I")
	public final int originX;

	@OriginalMember(owner = "runetek4.client!bn", name = "ab", descriptor = "Lclient!ih;")
	public final LinkedList chunks;

	@OriginalMember(owner = "runetek4.client!bn", name = "<init>", descriptor = "(Lclient!na;Lclient!na;IIIZI)V")
	public Map(@OriginalArg(0) JString group, @OriginalArg(1) JString name, @OriginalArg(2) int originX, @OriginalArg(3) int originZ, @OriginalArg(4) int backgroundColor, @OriginalArg(5) boolean valid, @OriginalArg(6) int defaultZoom) {
		this.originZ = originZ;
		this.backgroundColor = backgroundColor;
		this.valid = valid;
		this.group = group;
		this.name = name;
		this.defaultZoom = defaultZoom;
		this.originX = originX;
		if (this.defaultZoom == 255) {
			this.defaultZoom = 0;
		}
		this.chunks = new LinkedList();
	}

    @OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(Lclient!wa;Z)Lclient!bn;")
    public static Map create(@OriginalArg(0) Packet packet) {
        @Pc(35) Map map = new Map(packet.gjstr(), packet.gjstr(), packet.g2(), packet.g2(), packet.g4(), packet.g1() == 1, packet.g1());
        @Pc(39) int chunkCount = packet.g1();
        for (@Pc(41) int chunkIndex = 0; chunkIndex < chunkCount; chunkIndex++) {
            map.chunks.push(new MapChunk(packet.g2(), packet.g2(), packet.g2(), packet.g2()));
        }
        map.computeBounds();
        return map;
    }

    @OriginalMember(owner = "runetek4.client!bn", name = "a", descriptor = "(IBI)Z")
	public final boolean containsCoordinate(@OriginalArg(0) int worldX, @OriginalArg(2) int worldZ) {
		if (this.displayMinZ > worldZ || worldZ > this.displayMaxZ || worldX < this.displayMinX || worldX > this.displayMaxX) {
			return false;
		}
		for (@Pc(33) MapChunk chunk = (MapChunk) this.chunks.head(); chunk != null; chunk = (MapChunk) this.chunks.next()) {
			if (chunk.containsDisplay(worldX, worldZ)) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "runetek4.client!bn", name = "f", descriptor = "(B)V")
	public final void computeBounds() {
		this.displayMinX = 12800;
		this.displayMaxZ = 0;
		this.displayMaxX = 0;
		this.displayMinZ = 12800;
		for (@Pc(29) MapChunk local29 = (MapChunk) this.chunks.head(); local29 != null; local29 = (MapChunk) this.chunks.next()) {
			if (local29.displayMaxX < this.displayMinX) {
				this.displayMinX = local29.displayMaxX;
			}
			if (local29.displayMinX < this.displayMinZ) {
				this.displayMinZ = local29.displayMinX;
			}
			if (local29.displayMaxZ > this.displayMaxZ) {
				this.displayMaxZ = local29.displayMaxZ;
			}
			if (this.displayMaxX < local29.displayMinZ) {
				this.displayMaxX = local29.displayMinZ;
			}
		}
	}
}
