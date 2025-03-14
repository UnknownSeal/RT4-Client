package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.Node;
import com.jagex.runetek4.dash3d.entity.LocMergeEntity;
import com.jagex.runetek4.cache.def.ActorDefinition;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fl")
public final class AreaSound extends Node {

	@OriginalMember(owner = "client!fl", name = "p", descriptor = "I")
	public int anInt2028;

	@OriginalMember(owner = "client!fl", name = "q", descriptor = "I")
	public int anInt2029;

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
	public LocMergeEntity loc;

	@OriginalMember(owner = "client!fl", name = "F", descriptor = "I")
	public int anInt2037;

	@OriginalMember(owner = "client!fl", name = "I", descriptor = "Lclient!km;")
	public NPCEntity npc;

	@OriginalMember(owner = "client!fl", name = "K", descriptor = "I")
	public int maxInterval;

	@OriginalMember(owner = "client!fl", name = "L", descriptor = "I")
	public int anInt2041;

	@OriginalMember(owner = "client!fl", name = "M", descriptor = "Lclient!e;")
	public PlayerEntity player;

	@OriginalMember(owner = "client!fl", name = "N", descriptor = "I")
	public int radius;

	@OriginalMember(owner = "client!fl", name = "O", descriptor = "Z")
	public boolean multiLocOrNpc;

	@OriginalMember(owner = "client!fl", name = "R", descriptor = "I")
	public int sound;

	@OriginalMember(owner = "client!fl", name = "T", descriptor = "[I")
	public int[] sounds;

	@OriginalMember(owner = "client!fl", name = "G", descriptor = "I")
	public int anInt2038 = 0;

	@OriginalMember(owner = "client!fl", name = "c", descriptor = "(I)V")
	public void update() {
		@Pc(8) int sound = this.sound;
		if (this.loc != null) {
			@Pc(17) LocMergeEntity locType = this.loc.getVisible();
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
			@Pc(92) int npcSound = NPCEntity.getSound(this.npc);
			if (sound != npcSound) {
				@Pc(100) ActorDefinition actorDefinition = this.npc.type;
				this.sound = npcSound;
				if (actorDefinition.multinpc != null) {
					actorDefinition = actorDefinition.getMultiNPC();
				}
				if (actorDefinition == null) {
					this.radius = 0;
				} else {
					this.radius = actorDefinition.bgsound_range * 128;
				}
			}
		} else if (this.player != null) {
			this.sound = PlayerEntity.getSound(this.player);
			this.radius = this.player.anInt1664 * 128;
		}
		if (this.sound != sound && this.primaryStream != null) {
			Static204.soundStream.removeSubStream(this.primaryStream);
			this.primaryStream = null;
		}
	}
}