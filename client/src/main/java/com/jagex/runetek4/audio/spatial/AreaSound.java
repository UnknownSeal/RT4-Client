package com.jagex.runetek4.audio.spatial;

import com.jagex.runetek4.audio.core.SoundPcmStream;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.entity.npc.Npc;
import com.jagex.runetek4.entity.player.Player;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.npc.NpcType;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fl")
public final class AreaSound extends Node {

	@OriginalMember(owner = "client!fl", name = "p", descriptor = "I")
	public int maxZFine;

	@OriginalMember(owner = "client!fl", name = "q", descriptor = "I")
	public int minZFine;

	@OriginalMember(owner = "client!fl", name = "t", descriptor = "I")
	public int bgsound_mindelay;

	@OriginalMember(owner = "client!fl", name = "v", descriptor = "Lclient!b;")
	public SoundPcmStream primaryStream;

	@OriginalMember(owner = "client!fl", name = "x", descriptor = "I")
	public int level;

	@OriginalMember(owner = "client!fl", name = "y", descriptor = "Lclient!b;")
	public SoundPcmStream secondaryStream;

	@OriginalMember(owner = "client!fl", name = "z", descriptor = "I")
	public int remainingLoops;

	@OriginalMember(owner = "client!fl", name = "E", descriptor = "Lclient!pb;")
	public LocType locType;

	@OriginalMember(owner = "client!fl", name = "F", descriptor = "I")
	public int maxXFine;

	@OriginalMember(owner = "client!fl", name = "I", descriptor = "Lclient!km;")
	public Npc npc;

	@OriginalMember(owner = "client!fl", name = "K", descriptor = "I")
	public int bgsound_maxdelay;

	@OriginalMember(owner = "client!fl", name = "L", descriptor = "I")
	public int minXFine;

	@OriginalMember(owner = "client!fl", name = "M", descriptor = "Lclient!e;")
	public Player player;

	@OriginalMember(owner = "client!fl", name = "N", descriptor = "I")
	public int bgsound_range;

	@OriginalMember(owner = "client!fl", name = "O", descriptor = "Z")
	public boolean multiLocOrNpc;

	@OriginalMember(owner = "client!fl", name = "R", descriptor = "I")
	public int bgsound;

	@OriginalMember(owner = "client!fl", name = "T", descriptor = "[I")
	public int[] bgsound_random;

	@OriginalMember(owner = "client!fl", name = "G", descriptor = "I")
	public int movementSpeed = 0;

	@OriginalMember(owner = "client!fl", name = "c", descriptor = "(I)V")
	public void update() {
		@Pc(8) int prevSound = this.bgsound;
		if (this.locType != null) {
			@Pc(17) LocType locType = this.locType.getMultiLoc();
			if (locType == null) {
				this.bgsound = -1;
				this.bgsound_random = null;
				this.bgsound_maxdelay = 0;
				this.bgsound_range = 0;
				this.bgsound_mindelay = 0;
			} else {
				this.bgsound_maxdelay = locType.bgsound_maxdelay;
				this.bgsound = locType.bgsound_sound;
				this.bgsound_mindelay = locType.bgsound_mindelay;
				this.bgsound_range = locType.bgsound_range * 128;
				this.bgsound_random = locType.bgsound_random;
			}
		} else if (this.npc != null) {
			@Pc(92) int bgsound = Npc.getSound(this.npc);
			if (prevSound != bgsound) {
				@Pc(100) NpcType npcType = this.npc.type;
				this.bgsound = bgsound;
				if (npcType.multinpc != null) {
					npcType = npcType.getMultiNPC();
				}
				if (npcType == null) {
					this.bgsound_range = 0;
				} else {
					this.bgsound_range = npcType.bgsound_range * 128;
				}
			}
		} else if (this.player != null) {
			this.bgsound = Player.getSound(this.player);
			this.bgsound_range = this.player.soundRadius * 128;
		}
		if (this.bgsound != prevSound && this.primaryStream != null) {
			Client.soundStream.removeSubStream(this.primaryStream);
			this.primaryStream = null;
		}
	}
}