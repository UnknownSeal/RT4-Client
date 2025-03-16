package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.dash3d.entity.LocMergeEntity;
import com.jagex.runetek4.cache.def.ActorDefinition;
import com.jagex.runetek4.dash3d.entity.NPCRenderable;
import com.jagex.runetek4.media.renderable.actor.Player;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class AreaSoundManager {
    @OriginalMember(owner = "client!ab", name = "n", descriptor = "Lclient!ih;")
    public static final LinkList locSounds = new LinkList();
    @OriginalMember(owner = "client!ma", name = "x", descriptor = "Lclient!ih;")
    public static final LinkList npcSounds = new LinkList();
    @OriginalMember(owner = "runetek4.client!he", name = "ab", descriptor = "Lclient!sc;")
    public static final HashTable playerSounds = new HashTable(16);

    @OriginalMember(owner = "client!jh", name = "a", descriptor = "(IZLclient!pb;ILclient!km;IILclient!e;)V")
    public static void add(@OriginalArg(0) int arg0, @OriginalArg(2) LocMergeEntity arg1, @OriginalArg(3) int arg2, @OriginalArg(4) NPCRenderable npcEntity, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) Player player) {
        @Pc(13) AreaSound areaSound = new AreaSound();
        areaSound.level = arg5;
        areaSound.anInt2029 = arg0 * 128;
        areaSound.anInt2041 = arg4 * 128;
        if (arg1 != null) {
            areaSound.sounds = arg1.bgsound_random;
            areaSound.radius = arg1.bgsound_range * 128;
            areaSound.maxInterval = arg1.bgsound_maxdelay;
            areaSound.loc = arg1;
            areaSound.sound = arg1.bgsound_sound;
            areaSound.minInterval = arg1.bgsound_mindelay;
            @Pc(57) int local57 = arg1.width;
            @Pc(60) int local60 = arg1.length;
            if (arg2 == 1 || arg2 == 3) {
                local57 = arg1.length;
                local60 = arg1.width;
            }
            areaSound.anInt2028 = (local60 + arg0) * 128;
            areaSound.anInt2037 = (arg4 + local57) * 128;
            if (arg1.multiloc != null) {
                areaSound.multiLocOrNpc = true;
                areaSound.update();
            }
            if (areaSound.sounds != null) {
                areaSound.remainingLoops = areaSound.minInterval + (int) (Math.random() * (double) (areaSound.maxInterval - areaSound.minInterval));
            }
            locSounds.addTail(areaSound);
        } else if (npcEntity != null) {
            areaSound.npc = npcEntity;
            @Pc(138) ActorDefinition actorDefinition = npcEntity.type;
            if (actorDefinition.multinpc != null) {
                areaSound.multiLocOrNpc = true;
                actorDefinition = actorDefinition.getMultiNPC();
            }
            if (actorDefinition != null) {
                areaSound.anInt2028 = (arg0 + actorDefinition.size) * 128;
                areaSound.anInt2037 = (arg4 + actorDefinition.size) * 128;
                areaSound.sound = NPCRenderable.getSound(npcEntity);
                areaSound.radius = actorDefinition.bgsound_range * 128;
            }
            npcSounds.addTail(areaSound);
        } else if (player != null) {
            areaSound.player = player;
            areaSound.anInt2037 = (arg4 + player.size()) * 128;
            areaSound.anInt2028 = (arg0 + player.size()) * 128;
            areaSound.sound = Player.getSound(player);
            areaSound.radius = player.anInt1664 * 128;
            playerSounds.pushNode(areaSound, player.name.toBase37());
        }
    }

    @OriginalMember(owner = "client!ra", name = "a", descriptor = "(ILclient!pb;BII)V")
    public static void remove(@OriginalArg(0) int arg0, @OriginalArg(1) LocMergeEntity locType, @OriginalArg(3) int arg2, @OriginalArg(4) int level) {
        for (@Pc(10) AreaSound areaSound = (AreaSound) locSounds.head(); areaSound != null; areaSound = (AreaSound) locSounds.next()) {
            if (areaSound.level == level && areaSound.anInt2041 == arg0 * 128 && areaSound.anInt2029 == arg2 * 128 && locType.anInt4426 == areaSound.loc.anInt4426) {
                if (areaSound.primaryStream != null) {
                    Static204.soundStream.removeSubStream(areaSound.primaryStream);
                    areaSound.primaryStream = null;
                }
                if (areaSound.secondaryStream != null) {
                    Static204.soundStream.removeSubStream(areaSound.secondaryStream);
                    areaSound.secondaryStream = null;
                }
                areaSound.unlink();
                return;
            }
        }
    }

    @OriginalMember(owner = "client!vd", name = "a", descriptor = "(BZ)V")
    public static void clear(@OriginalArg(1) boolean arg0) {
        @Pc(14) AreaSound areaSound;
        for (areaSound = (AreaSound) locSounds.head(); areaSound != null; areaSound = (AreaSound) locSounds.next()) {
            if (areaSound.primaryStream != null) {
                Static204.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            if (areaSound.secondaryStream != null) {
                Static204.soundStream.removeSubStream(areaSound.secondaryStream);
                areaSound.secondaryStream = null;
            }
            areaSound.unlink();
        }
        if (!arg0) {
            return;
        }
        for (areaSound = (AreaSound) npcSounds.head(); areaSound != null; areaSound = (AreaSound) npcSounds.next()) {
            if (areaSound.primaryStream != null) {
                Static204.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            areaSound.unlink();
        }
        for (areaSound = (AreaSound) playerSounds.peekFront(); areaSound != null; areaSound = (AreaSound) playerSounds.prev()) {
            if (areaSound.primaryStream != null) {
                Static204.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            areaSound.unlink();
        }
    }

    @OriginalMember(owner = "client!je", name = "k", descriptor = "(I)V")
    public static void setObjectSounds() {
        @Pc(6) AreaSound areaSound;
        for (areaSound = (AreaSound) locSounds.head(); areaSound != null; areaSound = (AreaSound) locSounds.next()) {
            if (areaSound.multiLocOrNpc) {
                areaSound.update();
            }
        }
        for (areaSound = (AreaSound) npcSounds.head(); areaSound != null; areaSound = (AreaSound) npcSounds.next()) {
            if (areaSound.multiLocOrNpc) {
                areaSound.update();
            }
        }
    }
}
