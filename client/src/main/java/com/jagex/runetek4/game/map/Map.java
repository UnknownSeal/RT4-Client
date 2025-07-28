package com.jagex.runetek4.game.map;

import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.core.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!bn")
public final class Map extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!bn", name = "K", descriptor = "I")
	public int displayMinZ = 0;

	@OriginalMember(owner = "runetek4.client!bn", name = "L", descriptor = "I")
	public int backgroundColor = -1;

	@OriginalMember(owner = "runetek4.client!bn", name = "S", descriptor = "I")
	public int displayMinX = 12800;

	@OriginalMember(owner = "runetek4.client!bn", name = "Z", descriptor = "I")
	public int displayMaxZ = 0;

	@OriginalMember(owner = "runetek4.client!bn", name = "cb", descriptor = "I")
	public int displayMaxX = 12800;

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
	public Map(@OriginalArg(0) JString arg0, @OriginalArg(1) JString arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6) {
		this.originZ = arg3;
		this.backgroundColor = arg4;
		this.valid = arg5;
		this.group = arg0;
		this.name = arg1;
		this.defaultZoom = arg6;
		this.originX = arg2;
		if (this.defaultZoom == 255) {
			this.defaultZoom = 0;
		}
		this.chunks = new LinkedList();
	}

    @OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(Lclient!wa;Z)Lclient!bn;")
    public static Map create(@OriginalArg(0) Packet arg0) {
        @Pc(35) Map map = new Map(arg0.gjstr(), arg0.gjstr(), arg0.g2(), arg0.g2(), arg0.g4(), arg0.g1() == 1, arg0.g1());
        @Pc(39) int len = arg0.g1();
        for (@Pc(41) int i = 0; i < len; i++) {
            map.chunks.addTail(new MapChunk(arg0.g2(), arg0.g2(), arg0.g2(), arg0.g2()));
        }
        map.computeBounds();
        return map;
    }

    @OriginalMember(owner = "runetek4.client!bn", name = "a", descriptor = "(IBI)Z")
	public final boolean containsSource(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		if (this.displayMinX > arg1 || arg1 > this.displayMaxZ || arg0 < this.displayMaxX || arg0 > this.displayMinZ) {
			return false;
		}
		for (@Pc(33) MapChunk chunk = (MapChunk) this.chunks.head(); chunk != null; chunk = (MapChunk) this.chunks.next()) {
			if (chunk.containsDisplay(arg0, arg1)) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "runetek4.client!bn", name = "f", descriptor = "(B)V")
	public final void computeBounds() {
		this.displayMaxX = 12800;
		this.displayMaxZ = 0;
		this.displayMinZ = 0;
		this.displayMinX = 12800;
		for (@Pc(29) MapChunk local29 = (MapChunk) this.chunks.head(); local29 != null; local29 = (MapChunk) this.chunks.next()) {
			if (local29.displayMaxX < this.displayMaxX) {
				this.displayMaxX = local29.displayMaxX;
			}
			if (local29.displayMinX < this.displayMinX) {
				this.displayMinX = local29.displayMinX;
			}
			if (local29.displayMaxZ > this.displayMaxZ) {
				this.displayMaxZ = local29.displayMaxZ;
			}
			if (this.displayMinZ < local29.displayMinZ) {
				this.displayMinZ = local29.displayMinZ;
			}
		}
	}
}
