package com.jagex.runetek4;

import com.jagex.runetek4.client.client;
import com.jagex.runetek4.node.Node;
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
	public int minInterval;

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
	public int maxInterval;

	@OriginalMember(owner = "client!fl", name = "L", descriptor = "I")
	public int minXFine;

	@OriginalMember(owner = "client!fl", name = "M", descriptor = "Lclient!e;")
	public Player player;

	@OriginalMember(owner = "client!fl", name = "N", descriptor = "I")
	public int radius;

	@OriginalMember(owner = "client!fl", name = "O", descriptor = "Z")
	public boolean multiLocOrNpc;

	@OriginalMember(owner = "client!fl", name = "R", descriptor = "I")
	public int sound;

	@OriginalMember(owner = "client!fl", name = "T", descriptor = "[I")
	public int[] sounds;

	@OriginalMember(owner = "client!fl", name = "G", descriptor = "I")
	public int movementSpeed = 0;

	@OriginalMember(owner = "client!fl", name = "c", descriptor = "(I)V")
	public void update() {
		@Pc(8) int prevSound = this.sound;
		if (this.locType != null) {
			@Pc(17) LocType locType = this.locType.getMultiLoc();
			if (locType == null) {
				this.sound = -1;
				this.sounds = null;
				this.maxInterval = 0;
				this.radius = 0;
				this.minInterval = 0;
			} else {
				this.maxInterval = locType.bgsound_maxdelay;
				this.sound = locType.bgsound_sound;
				this.minInterval = locType.bgsound_mindelay;
				this.radius = locType.bgsound_range * 128;
				this.sounds = locType.bgsound_random;
			}
		} else if (this.npc != null) {
			@Pc(92) int sound = Npc.getSound(this.npc);
			if (prevSound != sound) {
				@Pc(100) NpcType npcType = this.npc.type;
				this.sound = sound;
				if (npcType.multinpc != null) {
					npcType = npcType.getMultiNPC();
				}
				if (npcType == null) {
					this.radius = 0;
				} else {
					this.radius = npcType.bgsound_range * 128;
				}
			}
		} else if (this.player != null) {
			this.sound = Player.getSound(this.player);
			this.radius = this.player.soundRadius * 128;
		}
		if (this.sound != prevSound && this.primaryStream != null) {
			client.soundStream.removeSubStream(this.primaryStream);
			this.primaryStream = null;
		}
	}
}