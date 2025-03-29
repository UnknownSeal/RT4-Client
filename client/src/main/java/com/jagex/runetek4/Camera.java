package com.jagex.runetek4;

import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Camera {
    @OriginalMember(owner = "runetek4.client!id", name = "d", descriptor = "[[[I")
    public static final int[][][] anIntArrayArrayArray9 = new int[2][][];
    @OriginalMember(owner = "runetek4.client!wh", name = "m", descriptor = "[I")
    public static final int[] cameraAmplitude = new int[5];
    @OriginalMember(owner = "runetek4.client!sa", name = "Q", descriptor = "[I")
    public static final int[] cameraModifierJitter = new int[5];
    @OriginalMember(owner = "runetek4.client!qg", name = "Y", descriptor = "[I")
    public static final int[] cameraFrequency = new int[5];
    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "[Z")
    public static final boolean[] cameraModifierEnabled = new boolean[5];
    @OriginalMember(owner = "runetek4.client!fl", name = "s", descriptor = "I")
    public static int orbitCameraPitch = 128;
    @OriginalMember(owner = "runetek4.client!eg", name = "d", descriptor = "I")
    public static int orbitCameraYaw = 0;
    @OriginalMember(owner = "runetek4.client!ef", name = "i", descriptor = "I")
    public static int pitchAccel = 0;
    @OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "I")
    public static int cameraZ;
    @OriginalMember(owner = "runetek4.client!gg", name = "bb", descriptor = "I")
    public static int cameraX;
    @OriginalMember(owner = "runetek4.client!tg", name = "b", descriptor = "I")
    public static int cameraPitch;
    @OriginalMember(owner = "runetek4.client!ol", name = "ib", descriptor = "I")
    public static int cameraYaw;
    @OriginalMember(owner = "runetek4.client!sg", name = "o", descriptor = "I")
    public static int cameraType;
    @OriginalMember(owner = "runetek4.client!g", name = "d", descriptor = "I")
    public static int anInt2119 = 0;
    @OriginalMember(owner = "runetek4.client!eb", name = "t", descriptor = "I")
    public static int anInt1694 = -1;
    @OriginalMember(owner = "runetek4.client!j", name = "K", descriptor = "I")
    public static int anInt5843 = 0;
    @OriginalMember(owner = "client!sh", name = "c", descriptor = "I")
    public static int anInt5101 = 0;
    @OriginalMember(owner = "runetek4.client!t", name = "z", descriptor = "I")
    public static int anInt5224 = 0;
    @OriginalMember(owner = "runetek4.client!k", name = "i", descriptor = "I")
    public static int anInt3125 = 0;
    @OriginalMember(owner = "runetek4.client!me", name = "k", descriptor = "I")
    public static int anInt3718 = -1;
    @OriginalMember(owner = "runetek4.client!lc", name = "n", descriptor = "I")
    public static int originZ;
    @OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "I")
    public static int originX;
    @OriginalMember(owner = "client!bc", name = "I", descriptor = "Z")
    public static boolean aBoolean16 = false;
    @OriginalMember(owner = "runetek4.client!km", name = "Pc", descriptor = "I")
    public static int renderX;
    @OriginalMember(owner = "runetek4.client!kh", name = "f", descriptor = "I")
    public static int renderZ;
    @OriginalMember(owner = "runetek4.client!uc", name = "f", descriptor = "I")
    public static int anInt4232;
    @OriginalMember(owner = "runetek4.client!ug", name = "h", descriptor = "I")
    public static int anInt5449;
    @OriginalMember(owner = "runetek4.client!vj", name = "d", descriptor = "I")
    public static int anInt5765;
    @OriginalMember(owner = "runetek4.client!tm", name = "g", descriptor = "I")
    public static int anInt5375;
    @OriginalMember(owner = "runetek4.client!pa", name = "K", descriptor = "Z")
    public static boolean aBoolean205 = false;
    @OriginalMember(owner = "runetek4.client!gk", name = "d", descriptor = "F")
    public static float aFloat10;
    @OriginalMember(owner = "runetek4.client!il", name = "O", descriptor = "I")
    public static int anInt4612;
    @OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "I")
    public static int anInt1744;
    @OriginalMember(owner = "runetek4.client!kf", name = "f", descriptor = "I")
    public static int anInt5230;
    @OriginalMember(owner = "runetek4.client!ke", name = "U", descriptor = "I")
    public static int cameraAnticheatOffsetX = 0;
    @OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "I")
    public static int cameraOffsetXModifier = 2;
    @OriginalMember(owner = "runetek4.client!ta", name = "B", descriptor = "I")
    public static int cameraPitchClamp = 0;
    @OriginalMember(owner = "runetek4.client!qk", name = "h", descriptor = "I")
    public static int cameraAnticheatOffsetZ = 0;
    @OriginalMember(owner = "runetek4.client!sj", name = "H", descriptor = "I")
    public static int cameraAnticheatAngle = 0;
    @OriginalMember(owner = "runetek4.client!af", name = "d", descriptor = "I")
    public static int cameraY;
    @OriginalMember(owner = "runetek4.client!lg", name = "d", descriptor = "F")
    public static float aFloat15;
    @OriginalMember(owner = "runetek4.client!sk", name = "jb", descriptor = "I")
    public static int anInt5203;
    @OriginalMember(owner = "runetek4.client!t", name = "o", descriptor = "I")
    public static int anInt5217;
    @OriginalMember(owner = "runetek4.client!t", name = "A", descriptor = "I")
    public static int anInt5225;
    @OriginalMember(owner = "client!cn", name = "L", descriptor = "I")
    public static int yawAccel = 0;

    @OriginalMember(owner = "client!jl", name = "c", descriptor = "(I)V")
    public static void updateLockedCamera() {
        @Pc(9) int local9 = anInt4232 * 128 + 64;
        @Pc(15) int local15 = anInt5375 * 128 + 64;
        @Pc(23) int local23 = SceneGraph.getTileHeight(Player.plane, local15, local9) - anInt5203;
        if (anInt4612 >= 100) {
            renderX = anInt5375 * 128 + 64;
            renderZ = anInt4232 * 128 + 64;
            cameraY = SceneGraph.getTileHeight(Player.plane, renderX, renderZ) - anInt5203;
        } else {
            if (renderX < local15) {
                renderX += anInt5225 + anInt4612 * (local15 - renderX) / 1000;
                if (renderX > local15) {
                    renderX = local15;
                }
            }
            if (cameraY < local23) {
                cameraY += (local23 - cameraY) * anInt4612 / 1000 + anInt5225;
                if (cameraY > local23) {
                    cameraY = local23;
                }
            }
            if (renderX > local15) {
                renderX -= anInt5225 + (renderX - local15) * anInt4612 / 1000;
                if (renderX < local15) {
                    renderX = local15;
                }
            }
            if (renderZ < local9) {
                renderZ += anInt5225 + anInt4612 * (local9 - renderZ) / 1000;
                if (local9 < renderZ) {
                    renderZ = local9;
                }
            }
            if (local23 < cameraY) {
                cameraY -= (cameraY - local23) * anInt4612 / 1000 + anInt5225;
                if (local23 > cameraY) {
                    cameraY = local23;
                }
            }
            if (renderZ > local9) {
                renderZ -= anInt5225 + (renderZ - local9) * anInt4612 / 1000;
                if (local9 > renderZ) {
                    renderZ = local9;
                }
            }
        }
        local9 = anInt5765 * 128 + 64;
        local15 = anInt5449 * 128 + 64;
        local23 = SceneGraph.getTileHeight(Player.plane, local15, local9) - anInt1744;
        @Pc(236) int local236 = local23 - cameraY;
        @Pc(241) int local241 = local9 - renderZ;
        @Pc(246) int local246 = local15 - renderX;
        @Pc(257) int local257 = (int) Math.sqrt((double) (local246 * local246 + local241 * local241));
        @Pc(268) int cameraPitch = (int) (Math.atan2((double) local236, (double) local257) * 325.949D) & 0x7FF;
        if (cameraPitch < 128) {
            cameraPitch = 128;
        }
        if (cameraPitch > 383) {
            cameraPitch = 383;
        }
        @Pc(292) int local292 = (int) (-325.949D * Math.atan2((double) local246, (double) local241)) & 0x7FF;
        if (Camera.cameraPitch < cameraPitch) {
            Camera.cameraPitch += anInt5230 + anInt5217 * (cameraPitch - Camera.cameraPitch) / 1000;
            if (Camera.cameraPitch > cameraPitch) {
                Camera.cameraPitch = cameraPitch;
            }
        }
        if (Camera.cameraPitch > cameraPitch) {
            Camera.cameraPitch -= (Camera.cameraPitch - cameraPitch) * anInt5217 / 1000 + anInt5230;
            if (Camera.cameraPitch < cameraPitch) {
                Camera.cameraPitch = cameraPitch;
            }
        }
        @Pc(350) int local350 = local292 - cameraYaw;
        if (local350 > 1024) {
            local350 -= 2048;
        }
        if (local350 < -1024) {
            local350 += 2048;
        }
        if (local350 > 0) {
            cameraYaw += local350 * anInt5217 / 1000 + anInt5230;
            cameraYaw &= 0x7FF;
        }
        if (local350 < 0) {
            cameraYaw -= anInt5217 * -local350 / 1000 + anInt5230;
            cameraYaw &= 0x7FF;
        }
        @Pc(404) int local404 = local292 - cameraYaw;
        if (local404 > 1024) {
            local404 -= 2048;
        }
        if (local404 < -1024) {
            local404 += 2048;
        }
        if (local404 < 0 && local350 > 0 || local404 > 0 && local350 < 0) {
            cameraYaw = local292;
        }
    }

    @OriginalMember(owner = "runetek4.client!da", name = "d", descriptor = "(I)V")
    public static void updateLoginScreenCamera() {
        if (anInt3718 == -1 || anInt1694 == -1) {
            return;
        }
        @Pc(27) int local27 = (anInt5224 * (anInt5843 - anInt5101) >> 16) + anInt5101;
        @Pc(30) float[] renderCoordinates = new float[3];
        anInt5224 += local27;
        if (anInt5224 >= 65535) {
            anInt5224 = 65535;
            if (aBoolean205) {
                aBoolean16 = false;
            } else {
                aBoolean16 = true;
            }
            aBoolean205 = true;
        } else {
            aBoolean205 = false;
            aBoolean16 = false;
        }
        @Pc(66) float local66 = (float) anInt5224 / 65535.0F;
        @Pc(70) int local70 = anInt3125 * 2;
        @Pc(141) int local141;
        @Pc(131) int local131;
        @Pc(111) int local111;
        @Pc(119) int local119;
        @Pc(146) int local146;
        @Pc(155) int local155;
        @Pc(173) int local173;
        for (@Pc(72) int local72 = 0; local72 < 3; local72++) {
            local111 = (anIntArrayArrayArray9[anInt3718][local70 + 2][local72] + anIntArrayArrayArray9[anInt3718][local70 + 2][local72] - anIntArrayArrayArray9[anInt3718][local70 + 3][local72]) * 3;
            local119 = anIntArrayArrayArray9[anInt3718][local70][local72];
            local131 = anIntArrayArrayArray9[anInt3718][local70 + 1][local72] * 3;
            local141 = anIntArrayArrayArray9[anInt3718][local70][local72] * 3;
            local146 = local131 - local141;
            local155 = local111 + local141 - local131 * 2;
            local173 = anIntArrayArrayArray9[anInt3718][local70 + 2][local72] + local131 - local119 - local111;
            renderCoordinates[local72] = (float) local119 + (((float) local173 * local66 + (float) local155) * local66 + (float) local146) * local66;
        }
        cameraY = (int) renderCoordinates[1] * -1;
        renderX = (int) renderCoordinates[0] - originX * 128;
        renderZ = (int) renderCoordinates[2] - originZ * 128;
        @Pc(226) float[] local226 = new float[3];
        local141 = anInt2119 * 2;
        for (local131 = 0; local131 < 3; local131++) {
            local111 = anIntArrayArrayArray9[anInt1694][local141][local131] * 3;
            local146 = (anIntArrayArrayArray9[anInt1694][local141 + 2][local131] + anIntArrayArrayArray9[anInt1694][local141 + 2][local131] - anIntArrayArrayArray9[anInt1694][local141 + 3][local131]) * 3;
            local155 = anIntArrayArrayArray9[anInt1694][local141][local131];
            local119 = anIntArrayArrayArray9[anInt1694][local141 + 1][local131] * 3;
            local173 = local119 - local111;
            @Pc(313) int local313 = local146 + local111 - local119 * 2;
            @Pc(331) int local331 = anIntArrayArrayArray9[anInt1694][local141 + 2][local131] + local119 - local146 - local155;
            local226[local131] = (float) local155 + local66 * (local66 * (local66 * (float) local331 + (float) local313) + (float) local173);
        }
        @Pc(363) float local363 = local226[0] - renderCoordinates[0];
        @Pc(371) float local371 = local226[2] - renderCoordinates[2];
        @Pc(382) float local382 = (local226[1] - renderCoordinates[1]) * -1.0F;
        @Pc(392) double local392 = Math.sqrt((double) (local371 * local371 + local363 * local363));
        aFloat15 = (float) Math.atan2((double) local382, local392);
        aFloat10 = -((float) Math.atan2((double) local363, (double) local371));
        cameraPitch = (int) ((double) aFloat15 * 325.949D) & 0x7FF;
        cameraYaw = (int) ((double) aFloat10 * 325.949D) & 0x7FF;
    }

    @OriginalMember(owner = "runetek4.client!vd", name = "a", descriptor = "(IIIIBI)V")
    public static void method3849(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
        anInt5230 = arg2;
        anInt5765 = arg1;
        anInt5217 = arg4;
        anInt5449 = arg3;
        anInt1744 = arg0;
        if (anInt5217 >= 100) {
            @Pc(30) int local30 = anInt5449 * 128 + 64;
            @Pc(36) int local36 = anInt5765 * 128 + 64;
            @Pc(44) int local44 = SceneGraph.getTileHeight(Player.plane, local30, local36) - anInt1744;
            @Pc(49) int local49 = local44 - cameraY;
            @Pc(54) int local54 = local30 - renderX;
            @Pc(59) int local59 = local36 - renderZ;
            @Pc(70) int local70 = (int) Math.sqrt((double) (local59 * local59 + local54 * local54));
            cameraPitch = (int) (Math.atan2((double) local49, (double) local70) * 325.949D) & 0x7FF;
            cameraYaw = (int) (Math.atan2((double) local54, (double) local59) * -325.949D) & 0x7FF;
            if (cameraPitch < 128) {
                cameraPitch = 128;
            }
            if (cameraPitch > 383) {
                cameraPitch = 383;
            }
        }
        cameraType = 2;
    }

    @OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(ZIIIBII)V")
    public static void method2722(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
        anInt4612 = arg3;
        anInt5203 = arg2;
        anInt5375 = arg5;
        anInt5225 = arg1;
        anInt4232 = arg4;
        if (arg0 && anInt4612 >= 100) {
            renderX = anInt5375 * 128 + 64;
            renderZ = anInt4232 * 128 + 64;
            cameraY = SceneGraph.getTileHeight(Player.plane, renderX, renderZ) - anInt5203;
        }
        cameraType = 2;
    }

    @OriginalMember(owner = "runetek4.client!cl", name = "e", descriptor = "(I)V")
    public static void resetCameraEffects() {
        for (@Pc(3) int local3 = 0; local3 < 5; local3++) {
            cameraModifierEnabled[local3] = false;
        }
        anInt5230 = 0;
        anInt5217 = 0;
        anInt3718 = -1;
        anInt1694 = -1;
        cameraType = 1;
    }

    @OriginalMember(owner = "runetek4.client!uf", name = "a", descriptor = "(B)V")
    public static void method4273() {
        @Pc(14) int playerX = PlayerList.self.xFine + cameraAnticheatOffsetX;
        @Pc(20) int playerZ = PlayerList.self.zFine + cameraAnticheatOffsetZ;
        if (cameraX - playerX < -500 || cameraX - playerX > 500 || cameraZ - playerZ < -500 || cameraZ - playerZ > 500) {
            cameraX = playerX;
            cameraZ = playerZ;
        }
        if (cameraZ != playerZ) {
            cameraZ += (playerZ - cameraZ) / 16;
        }
        if (cameraX != playerX) {
            cameraX += (playerX - cameraX) / 16;
        }
        if (Preferences.aBoolean63) {
            for (@Pc(93) int local93 = 0; local93 < InterfaceList.keyQueueSize; local93++) {
                @Pc(104) int code = InterfaceList.keyCodes[local93];
                if (code == 98) {
                    orbitCameraPitch = orbitCameraPitch + 47 & 0xFFFFFFF0;
                } else if (code == Keyboard.KEY_UP) {
                    orbitCameraPitch = orbitCameraPitch - 17 & 0xFFFFFFF0;
                } else if (code == Keyboard.KEY_DOWN) {
                    orbitCameraYaw = orbitCameraYaw - 65 & 0xFFFFFF80;
                } else if (code == Keyboard.KEY_RIGHT) {
                    orbitCameraYaw = orbitCameraYaw + 191 & 0xFFFFFF80;
                }
            }
        } else {
            if (Keyboard.pressedKeys[98]) {
                pitchAccel += (12 - pitchAccel) / 2;
            } else if (Keyboard.pressedKeys[99]) {
                pitchAccel += (-pitchAccel - 12) / 2;
            } else {
                pitchAccel /= 2;
            }
            if (Keyboard.pressedKeys[96]) {
                yawAccel += (-yawAccel - 24) / 2;
            } else if (Keyboard.pressedKeys[97]) {
                yawAccel += (24 - yawAccel) / 2;
            } else {
                yawAccel /= 2;
            }
            orbitCameraPitch += pitchAccel / 2;
            orbitCameraYaw += yawAccel / 2;
        }
        SceneCamera.clampCameraAngle();
    }

    @OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIIIIIII)V")
    public static void orbitCamera(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
        @Pc(5) int local5;
        @Pc(29) int local29;
        if (GlRenderer.enabled) {
            local5 = arg1 - 334;
            if (local5 < 0) {
                local5 = 0;
            } else if (local5 > 100) {
                local5 = 100;
            }
            local29 = local5 * (ClientScriptRunner.aShort27 - ClientScriptRunner.aShort30) / 100 + ClientScriptRunner.aShort30;
            arg3 = local29 * arg3 >> 8;
        }
        local5 = 2048 - arg6 & 0x7FF;
        local29 = 2048 - arg4 & 0x7FF;
        @Pc(55) int local55 = 0;
        @Pc(57) int local57 = arg3;
        @Pc(59) int local59 = 0;
        @Pc(72) int local72;
        @Pc(68) int local68;
        if (local5 != 0) {
            local68 = MathUtils.cos[local5];
            local72 = MathUtils.sin[local5];
            local59 = local72 * -arg3 >> 16;
            local57 = local68 * arg3 >> 16;
        }
        if (local29 != 0) {
            local72 = MathUtils.sin[local29];
            local68 = MathUtils.cos[local29];
            local55 = local72 * local57 >> 16;
            local57 = local57 * local68 >> 16;
        }
        cameraPitch = arg6;
        cameraYaw = arg4;
        renderZ = arg5 - local57;
        renderX = arg0 - local55;
        cameraY = arg2 - local59;
    }
}
