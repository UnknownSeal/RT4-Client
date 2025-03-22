package com.jagex.runetek4;

import com.jagex.runetek4.audio.SynthSound;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.media.renderable.actor.Player;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class AreaSoundManager {
    @OriginalMember(owner = "client!ab", name = "n", descriptor = "Lclient!ih;")
    public static final LinkedList locSounds = new LinkedList();

    @OriginalMember(owner = "client!ma", name = "x", descriptor = "Lclient!ih;")
    public static final LinkedList npcSounds = new LinkedList();

    @OriginalMember(owner = "runetek4.client!he", name = "ab", descriptor = "Lclient!sc;")
    public static final HashTable playerSounds = new HashTable(16);

    @OriginalMember(owner = "client!jh", name = "a", descriptor = "(IZLclient!pb;ILclient!km;IILclient!e;)V")
    public static void add(@OriginalArg(0) int z, @OriginalArg(2) LocType loc, @OriginalArg(3) int arg2, @OriginalArg(4) Npc npc, @OriginalArg(5) int x, @OriginalArg(6) int level, @OriginalArg(7) Player player) {
        @Pc(13) AreaSound sound = new AreaSound();
        sound.level = level;
        sound.minZFine = z * 128;
        sound.minXFine = x * 128;
        if (loc != null) {
            sound.sounds = loc.bgSounds;
            sound.radius = loc.bgSoundRange * 128;
            sound.maxInterval = loc.bgSoundMax;
            sound.locType = loc;
            sound.sound = loc.bgSound;
            sound.minInterval = loc.bgSoundMin;
            @Pc(57) int local57 = loc.width;
            @Pc(60) int local60 = loc.length;
            if (arg2 == 1 || arg2 == 3) {
                local57 = loc.length;
                local60 = loc.width;
            }
            sound.maxZFine = (local60 + z) * 128;
            sound.maxXFine = (x + local57) * 128;
            if (loc.multiloc != null) {
                sound.multiLocOrNpc = true;
                sound.update();
            }
            if (sound.sounds != null) {
                sound.remainingLoops = sound.minInterval + (int) (Math.random() * (double) (sound.maxInterval - sound.minInterval));
            }
            locSounds.addTail(sound);
        } else if (npc != null) {
            sound.npc = npc;
            @Pc(138) NpcType npcType = npc.type;
            if (npcType.multiNpcs != null) {
                sound.multiLocOrNpc = true;
                npcType = npcType.getMultiNPC();
            }
            if (npcType != null) {
                sound.maxZFine = (z + npcType.size) * 128;
                sound.maxXFine = (x + npcType.size) * 128;
                sound.sound = Npc.getSound(npc);
                sound.radius = npcType.soundRadius * 128;
            }
            npcSounds.addTail(sound);
        } else if (player != null) {
            sound.player = player;
            sound.maxXFine = (x + player.getSize()) * 128;
            sound.maxZFine = (z + player.getSize()) * 128;
            sound.sound = Player.getSound(player);
            sound.radius = player.soundRadius * 128;
            playerSounds.put(sound, player.username.encode37());
        }
    }

    @OriginalMember(owner = "client!ra", name = "a", descriptor = "(ILclient!pb;BII)V")
    public static void remove(@OriginalArg(0) int arg0, @OriginalArg(1) LocType locType, @OriginalArg(3) int arg2, @OriginalArg(4) int level) {
        for (@Pc(10) AreaSound areaSound = (AreaSound) locSounds.head(); areaSound != null; areaSound = (AreaSound) locSounds.next()) {
            if (areaSound.level == level && areaSound.minXFine == arg0 * 128 && areaSound.minZFine == arg2 * 128 && locType.id == areaSound.locType.id) {
                if (areaSound.primaryStream != null) {
                    client.soundStream.removeSubStream(areaSound.primaryStream);
                    areaSound.primaryStream = null;
                }
                if (areaSound.secondaryStream != null) {
                    client.soundStream.removeSubStream(areaSound.secondaryStream);
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
                client.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            if (areaSound.secondaryStream != null) {
                client.soundStream.removeSubStream(areaSound.secondaryStream);
                areaSound.secondaryStream = null;
            }
            areaSound.unlink();
        }
        if (!arg0) {
            return;
        }
        for (areaSound = (AreaSound) npcSounds.head(); areaSound != null; areaSound = (AreaSound) npcSounds.next()) {
            if (areaSound.primaryStream != null) {
                client.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            areaSound.unlink();
        }
        for (areaSound = (AreaSound) playerSounds.head(); areaSound != null; areaSound = (AreaSound) playerSounds.next()) {
            if (areaSound.primaryStream != null) {
                client.soundStream.removeSubStream(areaSound.primaryStream);
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

    @OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!e;I)V")
    public static void remove(@OriginalArg(0) Player player) {
        @Pc(10) AreaSound areaSound = (AreaSound) playerSounds.getNode(player.username.encode37());
        if (areaSound == null) {
            return;
        }
        if (areaSound.primaryStream != null) {
            client.soundStream.removeSubStream(areaSound.primaryStream);
            areaSound.primaryStream = null;
        }
        areaSound.unlink();
    }

    @OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!km;Z)V")
    public static void remove(@OriginalArg(0) Npc arg0) {
        for (@Pc(13) AreaSound areaSound = (AreaSound) npcSounds.head(); areaSound != null; areaSound = (AreaSound) npcSounds.next()) {
            if (arg0 == areaSound.npc) {
                if (areaSound.primaryStream != null) {
                    client.soundStream.removeSubStream(areaSound.primaryStream);
                    areaSound.primaryStream = null;
                }
                areaSound.unlink();
                return;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(IIIII)V")
    public static void redraw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        @Pc(6) AreaSound areaSound;
        for (areaSound = (AreaSound) locSounds.head(); areaSound != null; areaSound = (AreaSound) locSounds.next()) {
            redraw(arg1, areaSound, arg3, arg0, arg2);
        }
        @Pc(37) byte movementSpeed;
        @Pc(42) BasType basType;
        @Pc(141) int sound;
        for (areaSound = (AreaSound) npcSounds.head(); areaSound != null; areaSound = (AreaSound) npcSounds.next()) {
            movementSpeed = 1;
            basType = areaSound.npc.getBasType();
            if (basType.idleAnimationId == areaSound.npc.movementSeqId) {
                movementSpeed = 0;
            } else if (basType.runAnimationId == areaSound.npc.movementSeqId || basType.runFullTurnAnimationId == areaSound.npc.movementSeqId || basType.runCWTurnAnimationId == areaSound.npc.movementSeqId || basType.runCCWTurnAnimationId == areaSound.npc.movementSeqId) {
                movementSpeed = 2;
            } else if (basType.slowWalkAnimationId == areaSound.npc.movementSeqId || basType.slowWalkFullTurnAnimationId == areaSound.npc.movementSeqId || areaSound.npc.movementSeqId == basType.slowWalkCWTurnAnimationId || basType.slowWalkCCWTurnAnimationId == areaSound.npc.movementSeqId) {
                movementSpeed = 3;
            }
            if (areaSound.movementSpeed != movementSpeed) {
                sound = Npc.getSound(areaSound.npc);
                if (sound != areaSound.sound) {
                    if (areaSound.primaryStream != null) {
                        client.soundStream.removeSubStream(areaSound.primaryStream);
                        areaSound.primaryStream = null;
                    }
                    areaSound.sound = sound;
                }
                areaSound.movementSpeed = movementSpeed;
            }
            areaSound.minXFine = areaSound.npc.xFine;
            areaSound.maxXFine = areaSound.npc.xFine + areaSound.npc.getSize() * 64;
            areaSound.minZFine = areaSound.npc.zFine;
            areaSound.maxZFine = areaSound.npc.zFine + areaSound.npc.getSize() * 64;
            redraw(arg1, areaSound, arg3, arg0, arg2);
        }
        for (areaSound = (AreaSound) playerSounds.head(); areaSound != null; areaSound = (AreaSound) playerSounds.next()) {
            movementSpeed = 1;
            basType = areaSound.player.getBasType();
            if (areaSound.player.movementSeqId == basType.idleAnimationId) {
                movementSpeed = 0;
            } else if (areaSound.player.movementSeqId == basType.runAnimationId || areaSound.player.movementSeqId == basType.runFullTurnAnimationId || areaSound.player.movementSeqId == basType.runCWTurnAnimationId || basType.runCCWTurnAnimationId == areaSound.player.movementSeqId) {
                movementSpeed = 2;
            } else if (basType.slowWalkAnimationId == areaSound.player.movementSeqId || areaSound.player.movementSeqId == basType.slowWalkFullTurnAnimationId || areaSound.player.movementSeqId == basType.slowWalkCWTurnAnimationId || areaSound.player.movementSeqId == basType.slowWalkCCWTurnAnimationId) {
                movementSpeed = 3;
            }
            if (areaSound.movementSpeed != movementSpeed) {
                sound = Player.getSound(areaSound.player);
                if (areaSound.sound != sound) {
                    if (areaSound.primaryStream != null) {
                        client.soundStream.removeSubStream(areaSound.primaryStream);
                        areaSound.primaryStream = null;
                    }
                    areaSound.sound = sound;
                }
                areaSound.movementSpeed = movementSpeed;
            }
            areaSound.minXFine = areaSound.player.xFine;
            areaSound.maxXFine = areaSound.player.xFine + areaSound.player.getSize() * 64;
            areaSound.minZFine = areaSound.player.zFine;
            areaSound.maxZFine = areaSound.player.zFine + areaSound.player.getSize() * 64;
            redraw(arg1, areaSound, arg3, arg0, arg2);
        }
    }

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(ILclient!fl;IIII)V")
    public static void redraw(@OriginalArg(0) int x, @OriginalArg(1) AreaSound areaSound, @OriginalArg(2) int arg2, @OriginalArg(3) int loops, @OriginalArg(4) int z) {
        if (areaSound.sound == -1 && areaSound.sounds == null) {
            return;
        }
        @Pc(20) int distance = 0;
        if (areaSound.maxXFine < x) {
            distance = x - areaSound.maxXFine;
        } else if (areaSound.minXFine > x) {
            distance = areaSound.minXFine - x;
        }
        if (areaSound.maxZFine < z) {
            distance += z - areaSound.maxZFine;
        } else if (z < areaSound.minZFine) {
            distance += areaSound.minZFine - z;
        }
        if (areaSound.radius == 0 || areaSound.radius < distance - 64 || Preferences.ambientSoundsVolume == 0 || arg2 != areaSound.level) {
            if (areaSound.primaryStream != null) {
                client.soundStream.removeSubStream(areaSound.primaryStream);
                areaSound.primaryStream = null;
            }
            if (areaSound.secondaryStream != null) {
                client.soundStream.removeSubStream(areaSound.secondaryStream);
                areaSound.secondaryStream = null;
            }
            return;
        }
        distance -= 64;
        if (distance < 0) {
            distance = 0;
        }
        @Pc(134) int volume = (areaSound.radius - distance) * Preferences.ambientSoundsVolume / areaSound.radius;
        if (areaSound.primaryStream != null) {
            areaSound.primaryStream.method386(volume);
        } else if (areaSound.sound >= 0) {
            @Pc(150) SynthSound synthSound = SynthSound.create(client.js5Archive4, areaSound.sound, 0);
            if (synthSound != null) {
                @Pc(158) PcmSound pcmSound = synthSound.toPcmSound().resample(client.pcmResampler);
                @Pc(163) SoundPcmStream stream = Static284.method404(pcmSound, volume);
                stream.setLoops(-1);
                client.soundStream.addSubStream(stream);
                areaSound.primaryStream = stream;
            }
        }
        if (areaSound.secondaryStream != null) {
            areaSound.secondaryStream.method386(volume);
            if (!areaSound.secondaryStream.hasNext()) {
                areaSound.secondaryStream = null;
            }
        } else if (areaSound.sounds != null && (areaSound.remainingLoops -= loops) <= 0) {
            @Pc(219) int index = (int) ((double) areaSound.sounds.length * Math.random());
            @Pc(227) SynthSound synthSound = SynthSound.create(client.js5Archive4, areaSound.sounds[index], 0);
            if (synthSound != null) {
                @Pc(236) PcmSound pcmSound = synthSound.toPcmSound().resample(client.pcmResampler);
                @Pc(241) SoundPcmStream stream = Static284.method404(pcmSound, volume);
                stream.setLoops(0);
                client.soundStream.addSubStream(stream);
                areaSound.remainingLoops = (int) ((double) (areaSound.maxInterval - areaSound.minInterval) * Math.random()) + areaSound.minInterval;
                areaSound.secondaryStream = stream;
            }
        }
    }
}
