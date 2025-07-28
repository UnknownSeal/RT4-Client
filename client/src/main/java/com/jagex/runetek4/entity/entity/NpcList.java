package com.jagex.runetek4.entity.entity;

import com.jagex.runetek4.*;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.spotanim.SpotAnimTypeList;
import com.jagex.runetek4.config.types.bas.BasType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class NpcList {
    @OriginalMember(owner = "runetek4.client!nm", name = "S", descriptor = "[Lclient!km;")
    public static final Npc[] npcs = new Npc[32768];
    @OriginalMember(owner = "runetek4.client!cj", name = "i", descriptor = "[I")
    public static final int[] npcIds = new int[32768];
    @OriginalMember(owner = "runetek4.client!wd", name = "g", descriptor = "I")
    public static int npcCount = 0;

    @OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(I)V")
    public static void updateNpcs() {
        for (@Pc(7) int i = 0; i < npcCount; i++) {
            @Pc(18) int id = npcIds[i];
            @Pc(22) Npc npc = npcs[id];
            if (npc != null) {
                updateEntity(npc.type.size, npc);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(IILclient!fe;)V")
    public static void updateEntity(@OriginalArg(0) int arg0, @OriginalArg(2) PathingEntity entity) {
        if (Client.loop < entity.anInt3395) {
            method553(entity);
        } else if (entity.anInt3386 >= Client.loop) {
            method4665(entity);
        } else {
            method2247(entity);
        }
        if (entity.xFine < 128 || entity.zFine < 128 || entity.xFine >= 13184 || entity.zFine >= 13184) {
            entity.primarySeqId = -1;
            entity.spotAnimId = -1;
            entity.anInt3395 = 0;
            entity.anInt3386 = 0;
            entity.xFine = entity.movementQueueX[0] * 128 + entity.getSize() * 64;
            entity.zFine = entity.movementQueueZ[0] * 128 + entity.getSize() * 64;
            entity.method2689();
        }
        if (entity == PlayerList.self && (entity.xFine < 1536 || entity.zFine < 1536 || entity.xFine >= 11776 || entity.zFine >= 11776)) {
            entity.spotAnimId = -1;
            entity.anInt3395 = 0;
            entity.anInt3386 = 0;
            entity.primarySeqId = -1;
            entity.xFine = entity.movementQueueX[0] * 128 + entity.getSize() * 64;
            entity.zFine = entity.movementQueueZ[0] * 128 + entity.getSize() * 64;
            entity.method2689();
        }
        method949(entity);
        method879(entity);
    }

    @OriginalMember(owner = "client!cm", name = "a", descriptor = "(ILclient!fe;)V")
    public static void method949(@OriginalArg(1) PathingEntity e) {
        if (e.anInt3376 == 0) {
            return;
        }
        @Pc(13) BasType local13 = e.getBasType();
        @Pc(43) int dstX;
        @Pc(36) int dstZ;
        if (e.faceEntity != -1 && e.faceEntity < 32768) {
            @Pc(26) Npc npc = npcs[e.faceEntity];
            if (npc != null) {
                dstZ = e.zFine - npc.zFine;
                dstX = e.xFine - npc.xFine;
                if (dstX != 0 || dstZ != 0) {
                    e.dstYaw = (int) (Math.atan2((double) dstX, (double) dstZ) * 325.949D) & 0x7FF;
                }
            }
        }
        @Pc(94) int local94;
        @Pc(70) int index;
        if (e.faceEntity >= 32768) {
            index = e.faceEntity - 32768;
            if (index == PlayerList.selfId) {
                index = 2047;
            }
            @Pc(83) Player player = PlayerList.players[index];
            if (player != null) {
                local94 = e.zFine - player.zFine;
                dstZ = e.xFine - player.xFine;
                if (dstZ != 0 || local94 != 0) {
                    e.dstYaw = (int) (Math.atan2((double) dstZ, (double) local94) * 325.949D) & 0x7FF;
                }
            }
        }
        if ((e.faceX != 0 || e.faceY != 0) && (e.movementQueueSize == 0 || e.anInt3417 > 0)) {
            index = e.xFine - (e.faceX - Camera.originX - Camera.originX) * 64;
            dstX = e.zFine - (e.faceY - Camera.originZ - Camera.originZ) * 64;
            if (index != 0 || dstX != 0) {
                e.dstYaw = (int) (Math.atan2((double) index, (double) dstX) * 325.949D) & 0x7FF;
            }
            e.faceY = 0;
            e.faceX = 0;
        }
        index = e.dstYaw - e.anInt3381 & 0x7FF;
        if (index == 0) {
            e.anInt3385 = 0;
            e.anInt3414 = 0;
        } else if (local13.yawAcceleration == 0) {
            e.anInt3385++;
            @Pc(226) boolean local226;
            if (index > 1024) {
                e.anInt3381 -= e.anInt3376;
                local226 = true;
                if (index < e.anInt3376 || index > 2048 - e.anInt3376) {
                    e.anInt3381 = e.dstYaw;
                    local226 = false;
                }
                if (local13.readyanim == e.movementSeqId && (e.anInt3385 > 25 || local226)) {
                    if (local13.standingCCWTurn == -1) {
                        e.movementSeqId = local13.walkanim;
                    } else {
                        e.movementSeqId = local13.standingCCWTurn;
                    }
                }
            } else {
                local226 = true;
                e.anInt3381 += e.anInt3376;
                if (e.anInt3376 > index || index > 2048 - e.anInt3376) {
                    local226 = false;
                    e.anInt3381 = e.dstYaw;
                }
                if (local13.readyanim == e.movementSeqId && (e.anInt3385 > 25 || local226)) {
                    if (local13.standingCWTurn == -1) {
                        e.movementSeqId = local13.walkanim;
                    } else {
                        e.movementSeqId = local13.standingCWTurn;
                    }
                }
            }
            e.anInt3381 &= 0x7FF;
        } else {
            if (local13.readyanim == e.movementSeqId && e.anInt3385 > 25) {
                if (local13.standingCWTurn == -1) {
                    e.movementSeqId = local13.walkanim;
                } else {
                    e.movementSeqId = local13.standingCWTurn;
                }
            }
            dstX = e.dstYaw << 5;
            if (dstX != e.anInt3402) {
                e.anInt3387 = 0;
                e.anInt3402 = dstX;
                dstZ = dstX - e.anInt3377 & 0xFFFF;
                local94 = e.anInt3414 * e.anInt3414 / (local13.yawAcceleration * 2);
                @Pc(471) int local471;
                if (e.anInt3414 > 0 && dstZ >= local94 && dstZ - local94 < 32768) {
                    e.anInt3397 = dstZ / 2;
                    e.aBoolean167 = true;
                    local471 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
                    if (local471 > 32767) {
                        local471 = 32767;
                    }
                    if (local471 < e.anInt3397) {
                        e.anInt3397 = dstZ - local471;
                    }
                } else if (e.anInt3414 < 0 && local94 <= 65536 - dstZ && 65536 - dstZ - local94 < 32768) {
                    e.anInt3397 = (65536 - dstZ) / 2;
                    e.aBoolean167 = true;
                    local471 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
                    if (local471 > 32767) {
                        local471 = 32767;
                    }
                    if (local471 < e.anInt3397) {
                        e.anInt3397 = 65536 - dstZ - local471;
                    }
                } else {
                    e.aBoolean167 = false;
                }
            }
            if (e.anInt3414 == 0) {
                dstZ = e.anInt3402 - e.anInt3377 & 0xFFFF;
                if (dstZ < local13.yawAcceleration) {
                    e.anInt3377 = e.anInt3402;
                } else {
                    e.anInt3387 = 0;
                    local94 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
                    e.aBoolean167 = true;
                    if (local94 > 32767) {
                        local94 = 32767;
                    }
                    if (dstZ >= 32768) {
                        e.anInt3414 = -local13.yawAcceleration;
                        e.anInt3397 = (65536 - dstZ) / 2;
                        if (local94 < e.anInt3397) {
                            e.anInt3397 = 65536 - dstZ - local94;
                        }
                    } else {
                        e.anInt3414 = local13.yawAcceleration;
                        e.anInt3397 = dstZ / 2;
                        if (local94 < e.anInt3397) {
                            e.anInt3397 = dstZ - local94;
                        }
                    }
                }
            } else if (e.anInt3414 <= 0) {
                if (e.anInt3387 >= e.anInt3397) {
                    e.aBoolean167 = false;
                }
                if (!e.aBoolean167) {
                    e.anInt3414 += local13.yawAcceleration;
                    if (e.anInt3414 > 0) {
                        e.anInt3414 = 0;
                    }
                } else if (-local13.yawMaxSpeed < e.anInt3414) {
                    e.anInt3414 -= local13.yawAcceleration;
                }
            } else {
                if (e.anInt3397 <= e.anInt3387) {
                    e.aBoolean167 = false;
                }
                if (!e.aBoolean167) {
                    e.anInt3414 -= local13.yawAcceleration;
                    if (e.anInt3414 < 0) {
                        e.anInt3414 = 0;
                    }
                } else if (e.anInt3414 < local13.yawMaxSpeed) {
                    e.anInt3414 += local13.yawAcceleration;
                }
            }
            e.anInt3377 += e.anInt3414;
            e.anInt3377 &= 0xFFFF;
            if (e.anInt3414 <= 0) {
                e.anInt3387 -= e.anInt3414;
            } else {
                e.anInt3387 += e.anInt3414;
            }
            e.anInt3381 = e.anInt3377 >> 5;
        }
    }

    @OriginalMember(owner = "client!ck", name = "a", descriptor = "(Lclient!fe;I)V")
    public static void method879(@OriginalArg(0) PathingEntity arg0) {
        arg0.seqStretches = false;
        @Pc(18) SeqType seq;
        if (arg0.movementSeqId != -1) {
            seq = SeqTypeList.get(arg0.movementSeqId);
            if (seq == null || seq.frames == null) {
                arg0.movementSeqId = -1;
            } else {
                arg0.anInt3396++;
                if (seq.frames.length > arg0.anInt3407 && arg0.anInt3396 > seq.frameDelay[arg0.anInt3407]) {
                    arg0.anInt3396 = 1;
                    arg0.anInt3407++;
                    arg0.anInt3388++;
                    SoundPlayer.playSeqSound(arg0.zFine, seq, arg0.xFine, arg0 == PlayerList.self, arg0.anInt3407);
                }
                if (arg0.anInt3407 >= seq.frames.length) {
                    arg0.anInt3407 = 0;
                    arg0.anInt3396 = 0;
                    SoundPlayer.playSeqSound(arg0.zFine, seq, arg0.xFine, PlayerList.self == arg0, arg0.anInt3407);
                }
                arg0.anInt3388 = arg0.anInt3407 + 1;
                if (arg0.anInt3388 >= seq.frames.length) {
                    arg0.anInt3388 = 0;
                }
            }
        }
        @Pc(156) int local156;
        if (arg0.spotAnimId != -1 && Client.loop >= arg0.spotAnimStart) {
            local156 = SpotAnimTypeList.get(arg0.spotAnimId).seqId;
            if (local156 == -1) {
                arg0.spotAnimId = -1;
            } else {
                @Pc(165) SeqType local165 = SeqTypeList.get(local156);
                if (local165 == null || local165.frames == null) {
                    arg0.spotAnimId = -1;
                } else {
                    if (arg0.spotanimId < 0) {
                        arg0.spotanimId = 0;
                        SoundPlayer.playSeqSound(arg0.zFine, local165, arg0.xFine, PlayerList.self == arg0, 0);
                    }
                    arg0.anInt3361++;
                    if (arg0.spotanimId < local165.frames.length && local165.frameDelay[arg0.spotanimId] < arg0.anInt3361) {
                        arg0.spotanimId++;
                        arg0.anInt3361 = 1;
                        SoundPlayer.playSeqSound(arg0.zFine, local165, arg0.xFine, PlayerList.self == arg0, arg0.spotanimId);
                    }
                    if (arg0.spotanimId >= local165.frames.length) {
                        arg0.spotAnimId = -1;
                    }
                    arg0.anInt3418 = arg0.spotanimId + 1;
                    if (local165.frames.length <= arg0.anInt3418) {
                        arg0.anInt3418 = -1;
                    }
                }
            }
        }
        if (arg0.primarySeqId != -1 && arg0.anInt3420 <= 1) {
            seq = SeqTypeList.get(arg0.primarySeqId);
            if (seq.loopType == 1 && arg0.anInt3405 > 0 && Client.loop >= arg0.anInt3395 && Client.loop > arg0.anInt3386) {
                arg0.anInt3420 = 1;
                return;
            }
        }
        if (arg0.primarySeqId != -1 && arg0.anInt3420 == 0) {
            seq = SeqTypeList.get(arg0.primarySeqId);
            if (seq == null || seq.frames == null) {
                arg0.primarySeqId = -1;
            } else {
                arg0.anInt3360++;
                if (arg0.anInt3425 < seq.frames.length && arg0.anInt3360 > seq.frameDelay[arg0.anInt3425]) {
                    arg0.anInt3360 = 1;
                    arg0.anInt3425++;
                    SoundPlayer.playSeqSound(arg0.zFine, seq, arg0.xFine, arg0 == PlayerList.self, arg0.anInt3425);
                }
                if (seq.frames.length <= arg0.anInt3425) {
                    arg0.anInt3425 -= seq.replayOff;
                    arg0.anInt3371++;
                    if (arg0.anInt3371 >= seq.replayCount) {
                        arg0.primarySeqId = -1;
                    } else if (arg0.anInt3425 >= 0 && seq.frames.length > arg0.anInt3425) {
                        SoundPlayer.playSeqSound(arg0.zFine, seq, arg0.xFine, PlayerList.self == arg0, arg0.anInt3425);
                    } else {
                        arg0.primarySeqId = -1;
                    }
                }
                arg0.anInt3373 = arg0.anInt3425 + 1;
                if (arg0.anInt3373 >= seq.frames.length) {
                    arg0.anInt3373 -= seq.replayOff;
                    if (seq.replayCount <= arg0.anInt3371 + 1) {
                        arg0.anInt3373 = -1;
                    } else if (arg0.anInt3373 < 0 || arg0.anInt3373 >= seq.frames.length) {
                        arg0.anInt3373 = -1;
                    }
                }
                arg0.seqStretches = seq.stretches;
            }
        }
        if (arg0.anInt3420 > 0) {
            arg0.anInt3420--;
        }
        for (local156 = 0; local156 < arg0.aPathingEntityClass147Array3.length; local156++) {
            @Pc(545) PathingEntity_Class147 local545 = arg0.aPathingEntityClass147Array3[local156];
            if (local545 != null) {
                if (local545.anInt5408 > 0) {
                    local545.anInt5408--;
                } else {
                    @Pc(570) SeqType local570 = SeqTypeList.get(local545.anInt5396);
                    if (local570 == null || local570.frames == null) {
                        arg0.aPathingEntityClass147Array3[local156] = null;
                    } else {
                        local545.anInt5404++;
                        if (local545.anInt5399 < local570.frames.length && local545.anInt5404 > local570.frameDelay[local545.anInt5399]) {
                            local545.anInt5399++;
                            local545.anInt5404 = 1;
                            SoundPlayer.playSeqSound(arg0.zFine, local570, arg0.xFine, arg0 == PlayerList.self, local545.anInt5399);
                        }
                        if (local570.frames.length <= local545.anInt5399) {
                            local545.anInt5400++;
                            local545.anInt5399 -= local570.replayOff;
                            if (local570.replayCount <= local545.anInt5400) {
                                arg0.aPathingEntityClass147Array3[local156] = null;
                            } else if (local545.anInt5399 >= 0 && local545.anInt5399 < local570.frames.length) {
                                SoundPlayer.playSeqSound(arg0.zFine, local570, arg0.xFine, PlayerList.self == arg0, local545.anInt5399);
                            } else {
                                arg0.aPathingEntityClass147Array3[local156] = null;
                            }
                        }
                        local545.anInt5398 = local545.anInt5399 + 1;
                        if (local570.frames.length <= local545.anInt5398) {
                            local545.anInt5398 -= local570.replayOff;
                            if (local545.anInt5400 + 1 >= local570.replayCount) {
                                local545.anInt5398 = -1;
                            } else if (local545.anInt5398 < 0 || local570.frames.length <= local545.anInt5398) {
                                local545.anInt5398 = -1;
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!bh", name = "a", descriptor = "(Lclient!fe;Z)V")
    public static void method553(@OriginalArg(0) PathingEntity arg0) {
        @Pc(8) int local8 = arg0.anInt3395 - Client.loop;
        @Pc(20) int local20 = arg0.anInt3380 * 128 + arg0.getSize() * 64;
        @Pc(36) int local36 = arg0.anInt3428 * 128 + arg0.getSize() * 64;
        if (arg0.anInt3431 == 0) {
            arg0.dstYaw = 1024;
        }
        arg0.xFine += (local20 - arg0.xFine) / local8;
        arg0.zFine += (local36 - arg0.zFine) / local8;
        if (arg0.anInt3431 == 1) {
            arg0.dstYaw = 1536;
        }
        arg0.anInt3417 = 0;
        if (arg0.anInt3431 == 2) {
            arg0.dstYaw = 0;
        }
        if (arg0.anInt3431 == 3) {
            arg0.dstYaw = 512;
        }
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(Lclient!fe;B)V")
    public static void method4665(@OriginalArg(0) PathingEntity arg0) {
        if (Client.loop == arg0.anInt3386 || arg0.primarySeqId == -1 || arg0.anInt3420 != 0 || arg0.anInt3360 + 1 > SeqTypeList.get(arg0.primarySeqId).frameDelay[arg0.anInt3425]) {
            @Pc(35) int local35 = arg0.anInt3386 - arg0.anInt3395;
            @Pc(41) int local41 = Client.loop - arg0.anInt3395;
            @Pc(52) int local52 = arg0.anInt3380 * 128 + arg0.getSize() * 64;
            @Pc(64) int local64 = arg0.anInt3428 * 128 + arg0.getSize() * 64;
            @Pc(75) int local75 = arg0.anInt3416 * 128 + arg0.getSize() * 64;
            @Pc(86) int local86 = arg0.anInt3392 * 128 + arg0.getSize() * 64;
            arg0.xFine = (local41 * local75 + local52 * (local35 - local41)) / local35;
            arg0.zFine = (local86 * local41 + local64 * (local35 - local41)) / local35;
        }
        arg0.anInt3417 = 0;
        if (arg0.anInt3431 == 0) {
            arg0.dstYaw = 1024;
        }
        if (arg0.anInt3431 == 1) {
            arg0.dstYaw = 1536;
        }
        if (arg0.anInt3431 == 2) {
            arg0.dstYaw = 0;
        }
        if (arg0.anInt3431 == 3) {
            arg0.dstYaw = 512;
        }
        arg0.anInt3381 = arg0.dstYaw;
    }

    @OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(BLclient!fe;)V")
    public static void method2247(@OriginalArg(1) PathingEntity arg0) {
        @Pc(9) BasType local9 = arg0.getBasType();
        arg0.movementSeqId = local9.readyanim;
        if (arg0.movementQueueSize == 0) {
            arg0.anInt3417 = 0;
            return;
        }
        if (arg0.primarySeqId != -1 && arg0.anInt3420 == 0) {
            @Pc(40) SeqType local40 = SeqTypeList.get(arg0.primarySeqId);
            if (arg0.anInt3405 > 0 && local40.loopType == 0) {
                arg0.anInt3417++;
                return;
            }
            if (arg0.anInt3405 <= 0 && local40.movetype == 0) {
                arg0.anInt3417++;
                return;
            }
        }
        @Pc(79) int local79 = arg0.xFine;
        @Pc(82) int local82 = arg0.zFine;
        @Pc(99) int local99 = arg0.movementQueueX[arg0.movementQueueSize - 1] * 128 + arg0.getSize() * 64;
        @Pc(116) int local116 = arg0.movementQueueZ[arg0.movementQueueSize - 1] * 128 + arg0.getSize() * 64;
        if (local99 - local79 > 256 || local99 - local79 < -256 || local116 - local82 > 256 || local116 - local82 < -256) {
            arg0.xFine = local99;
            arg0.zFine = local116;
            return;
        }
        if (local99 <= local79) {
            if (local79 <= local99) {
                if (local116 > local82) {
                    arg0.dstYaw = 1024;
                } else if (local82 > local116) {
                    arg0.dstYaw = 0;
                }
            } else if (local116 > local82) {
                arg0.dstYaw = 768;
            } else if (local116 < local82) {
                arg0.dstYaw = 256;
            } else {
                arg0.dstYaw = 512;
            }
        } else if (local116 > local82) {
            arg0.dstYaw = 1280;
        } else if (local82 > local116) {
            arg0.dstYaw = 1792;
        } else {
            arg0.dstYaw = 1536;
        }
        @Pc(224) int local224 = arg0.dstYaw - arg0.anInt3381 & 0x7FF;
        @Pc(227) int local227 = local9.walkFullTurnAnimationId;
        if (local224 > 1024) {
            local224 -= 2048;
        }
        @Pc(233) boolean local233 = true;
        @Pc(235) byte local235 = 1;
        if (local224 >= -256 && local224 <= 256) {
            local227 = local9.walkanim;
        } else if (local224 >= 256 && local224 < 768) {
            local227 = local9.walkCWTurnAnimationId;
        } else if (local224 >= -768 && local224 <= -256) {
            local227 = local9.walkCCWTurnAnimationId;
        }
        @Pc(273) int local273 = 4;
        if (local227 == -1) {
            local227 = local9.walkanim;
        }
        arg0.movementSeqId = local227;
        if (arg0 instanceof Npc) {
            local233 = ((Npc) arg0).type.walksmoothing;
        }
        if (local233) {
            if (arg0.anInt3381 != arg0.dstYaw && arg0.faceEntity == -1 && arg0.anInt3376 != 0) {
                local273 = 2;
            }
            if (arg0.movementQueueSize > 2) {
                local273 = 6;
            }
            if (arg0.movementQueueSize > 3) {
                local273 = 8;
            }
            if (arg0.anInt3417 > 0 && arg0.movementQueueSize > 1) {
                local273 = 8;
                arg0.anInt3417--;
            }
        } else {
            if (arg0.movementQueueSize > 1) {
                local273 = 6;
            }
            if (arg0.movementQueueSize > 2) {
                local273 = 8;
            }
            if (arg0.anInt3417 > 0 && arg0.movementQueueSize > 1) {
                arg0.anInt3417--;
                local273 = 8;
            }
        }
        if (arg0.movementQueueSpeed[arg0.movementQueueSize - 1] == 2) {
            local273 <<= 0x1;
            local235 = 2;
        } else if (arg0.movementQueueSpeed[arg0.movementQueueSize - 1] == 0) {
            local235 = 0;
            local273 >>= 0x1;
        }
        if (local273 < 8 || local9.runanim == -1) {
            if (local9.crawlanim != -1 && local235 == 0) {
                if (local9.walkFullTurnAnimationId == arg0.movementSeqId && local9.crawlanim_b != -1) {
                    arg0.movementSeqId = local9.crawlanim_b;
                } else if (local9.walkCCWTurnAnimationId == arg0.movementSeqId && local9.crawlanim_l != -1) {
                    arg0.movementSeqId = local9.crawlanim_l;
                } else if (local9.walkCWTurnAnimationId == arg0.movementSeqId && local9.crawlanim_r != -1) {
                    arg0.movementSeqId = local9.crawlanim_r;
                } else {
                    arg0.movementSeqId = local9.crawlanim;
                }
            }
        } else if (local9.walkFullTurnAnimationId == arg0.movementSeqId && local9.runanim_b != -1) {
            arg0.movementSeqId = local9.runanim_b;
        } else if (arg0.movementSeqId == local9.walkCCWTurnAnimationId && local9.runanim_l != -1) {
            arg0.movementSeqId = local9.runanim_l;
        } else if (arg0.movementSeqId == local9.walkCWTurnAnimationId && local9.runanim_r != -1) {
            arg0.movementSeqId = local9.runanim_r;
        } else {
            arg0.movementSeqId = local9.runanim;
        }
        if (local9.movementAcceleration != -1) {
            local273 <<= 0x7;
            if (arg0.movementQueueSize == 1) {
                @Pc(594) int local594 = (local99 >= arg0.xFine ? local99 - arg0.xFine : -local99 + arg0.xFine) << 7;
                @Pc(600) int local600 = arg0.anInt3358 * arg0.anInt3358;
                @Pc(622) int local622 = (local116 < arg0.zFine ? arg0.zFine - local116 : -arg0.zFine + local116) << 7;
                @Pc(629) int local629 = local594 > local622 ? local594 : local622;
                @Pc(636) int local636 = local9.movementAcceleration * 2 * local629;
                if (local636 < local600) {
                    arg0.anInt3358 /= 2;
                } else if (local629 < local600 / 2) {
                    arg0.anInt3358 -= local9.movementAcceleration;
                    if (arg0.anInt3358 < 0) {
                        arg0.anInt3358 = 0;
                    }
                } else if (arg0.anInt3358 < local273) {
                    arg0.anInt3358 += local9.movementAcceleration;
                    if (arg0.anInt3358 > local273) {
                        arg0.anInt3358 = local273;
                    }
                }
            } else if (local273 > arg0.anInt3358) {
                arg0.anInt3358 += local9.movementAcceleration;
                if (local273 < arg0.anInt3358) {
                    arg0.anInt3358 = local273;
                }
            } else if (arg0.anInt3358 > 0) {
                arg0.anInt3358 -= local9.movementAcceleration;
                if (arg0.anInt3358 < 0) {
                    arg0.anInt3358 = 0;
                }
            }
            local273 = arg0.anInt3358 >> 7;
            if (local273 < 1) {
                local273 = 1;
            }
        }
        if (local79 < local99) {
            arg0.xFine += local273;
            if (local99 < arg0.xFine) {
                arg0.xFine = local99;
            }
        } else if (local79 > local99) {
            arg0.xFine -= local273;
            if (local99 > arg0.xFine) {
                arg0.xFine = local99;
            }
        }
        if (local82 < local116) {
            arg0.zFine += local273;
            if (arg0.zFine > local116) {
                arg0.zFine = local116;
            }
        } else if (local116 < local82) {
            arg0.zFine -= local273;
            if (local116 > arg0.zFine) {
                arg0.zFine = local116;
            }
        }
        if (arg0.xFine == local99 && local116 == arg0.zFine) {
            arg0.movementQueueSize--;
            if (arg0.anInt3405 > 0) {
                arg0.anInt3405--;
            }
        }
    }
}
