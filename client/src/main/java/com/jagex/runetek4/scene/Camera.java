package com.jagex.runetek4.scene;

import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.util.math.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Camera {
    @OriginalMember(owner = "runetek4.client!id", name = "d", descriptor = "[[[I")
    public static final int[][][] cameraSplines = new int[2][][];
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
    public static int lookAtKeyframe = 0;
    @OriginalMember(owner = "runetek4.client!eb", name = "t", descriptor = "I")
    public static int lookAtSplineId = -1;
    @OriginalMember(owner = "runetek4.client!j", name = "K", descriptor = "I")
    public static int maxAnimationSpeed = 0;
    @OriginalMember(owner = "client!sh", name = "c", descriptor = "I")
    public static int minAnimationSpeed = 0;
    @OriginalMember(owner = "runetek4.client!t", name = "z", descriptor = "I")
    public static int animationTimer = 0;
    @OriginalMember(owner = "runetek4.client!k", name = "i", descriptor = "I")
    public static int positionKeyframe = 0;
    @OriginalMember(owner = "runetek4.client!me", name = "k", descriptor = "I")
    public static int cameraSplineId = -1;
    @OriginalMember(owner = "runetek4.client!lc", name = "n", descriptor = "I")
    public static int sceneBaseTileZ;
    @OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "I")
    public static int sceneBaseTileX;
    @OriginalMember(owner = "client!bc", name = "I", descriptor = "Z")
    public static boolean shouldReverse = false;
    @OriginalMember(owner = "runetek4.client!km", name = "Pc", descriptor = "I")
    public static int renderX;
    @OriginalMember(owner = "runetek4.client!kh", name = "f", descriptor = "I")
    public static int renderZ;
    @OriginalMember(owner = "runetek4.client!uc", name = "f", descriptor = "I")
    public static int targetTileX;
    @OriginalMember(owner = "runetek4.client!ug", name = "h", descriptor = "I")
    public static int lookAtTileZ;
    @OriginalMember(owner = "runetek4.client!vj", name = "d", descriptor = "I")
    public static int lookAtTileX;
    @OriginalMember(owner = "runetek4.client!tm", name = "g", descriptor = "I")
    public static int targetTileZ;
    @OriginalMember(owner = "runetek4.client!pa", name = "K", descriptor = "Z")
    public static boolean animationComplete = false;
    @OriginalMember(owner = "runetek4.client!gk", name = "d", descriptor = "F")
    public static float yawRadians;
    @OriginalMember(owner = "runetek4.client!il", name = "O", descriptor = "I")
    public static int interpolationSpeed;
    @OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "I")
    public static int lookAtHeightOffset;
    @OriginalMember(owner = "runetek4.client!kf", name = "f", descriptor = "I")
    public static int minAngleStep;
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
    public static float pitchRadians;
    @OriginalMember(owner = "runetek4.client!sk", name = "jb", descriptor = "I")
    public static int heightOffset;
    @OriginalMember(owner = "runetek4.client!t", name = "o", descriptor = "I")
    public static int angleInterpolationSpeed;
    @OriginalMember(owner = "runetek4.client!t", name = "A", descriptor = "I")
    public static int minInterpolationStep;
    @OriginalMember(owner = "client!cn", name = "L", descriptor = "I")
    public static int yawAccel = 0;

    @OriginalMember(owner = "client!jl", name = "c", descriptor = "(I)V")
    public static void updateLockedCamera() {
        @Pc(9) int targetWorldX = targetTileX * 128 + 64;
        @Pc(15) int targetWorldZ = targetTileZ * 128 + 64;
        @Pc(23) int targetHeight = SceneGraph.getTileHeight(Player.currentLevel, targetWorldZ, targetWorldX) - heightOffset;
        if (interpolationSpeed >= 100) {
            renderX = targetTileZ * 128 + 64;
            renderZ = targetTileX * 128 + 64;
            cameraY = SceneGraph.getTileHeight(Player.currentLevel, renderX, renderZ) - heightOffset;
        } else {
            if (renderX < targetWorldZ) {
                renderX += minInterpolationStep + interpolationSpeed * (targetWorldZ - renderX) / 1000;
                if (renderX > targetWorldZ) {
                    renderX = targetWorldZ;
                }
            }
            if (cameraY < targetHeight) {
                cameraY += (targetHeight - cameraY) * interpolationSpeed / 1000 + minInterpolationStep;
                if (cameraY > targetHeight) {
                    cameraY = targetHeight;
                }
            }
            if (renderX > targetWorldZ) {
                renderX -= minInterpolationStep + (renderX - targetWorldZ) * interpolationSpeed / 1000;
                if (renderX < targetWorldZ) {
                    renderX = targetWorldZ;
                }
            }
            if (renderZ < targetWorldX) {
                renderZ += minInterpolationStep + interpolationSpeed * (targetWorldX - renderZ) / 1000;
                if (targetWorldX < renderZ) {
                    renderZ = targetWorldX;
                }
            }
            if (targetHeight < cameraY) {
                cameraY -= (cameraY - targetHeight) * interpolationSpeed / 1000 + minInterpolationStep;
                if (targetHeight > cameraY) {
                    cameraY = targetHeight;
                }
            }
            if (renderZ > targetWorldX) {
                renderZ -= minInterpolationStep + (renderZ - targetWorldX) * interpolationSpeed / 1000;
                if (targetWorldX > renderZ) {
                    renderZ = targetWorldX;
                }
            }
        }
        targetWorldX = lookAtTileX * 128 + 64;
        targetWorldZ = lookAtTileZ * 128 + 64;
        targetHeight = SceneGraph.getTileHeight(Player.currentLevel, targetWorldZ, targetWorldX) - lookAtHeightOffset;
        @Pc(236) int heightDifference = targetHeight - cameraY;
        @Pc(241) int zDistance = targetWorldX - renderZ;
        @Pc(246) int xDistance = targetWorldZ - renderX;
        @Pc(257) int horizontalDistance = (int) Math.sqrt((double) (xDistance * xDistance + zDistance * zDistance));
        @Pc(268) int targetPitch = (int) (Math.atan2((double) heightDifference, (double) horizontalDistance) * 325.949D) & 0x7FF;
        if (targetPitch < 128) {
            targetPitch = 128;
        }
        if (targetPitch > 383) {
            targetPitch = 383;
        }
        @Pc(292) int targetYaw = (int) (-325.949D * Math.atan2((double) xDistance, (double) zDistance)) & 0x7FF;
        if (Camera.cameraPitch < targetPitch) {
            Camera.cameraPitch += minAngleStep + angleInterpolationSpeed * (targetPitch - Camera.cameraPitch) / 1000;
            if (Camera.cameraPitch > targetPitch) {
                Camera.cameraPitch = targetPitch;
            }
        }
        if (Camera.cameraPitch > targetPitch) {
            Camera.cameraPitch -= (Camera.cameraPitch - targetPitch) * angleInterpolationSpeed / 1000 + minAngleStep;
            if (Camera.cameraPitch < targetPitch) {
                Camera.cameraPitch = targetPitch;
            }
        }
        @Pc(350) int yawDelta = targetYaw - cameraYaw;
        if (yawDelta > 1024) {
            yawDelta -= 2048;
        }
        if (yawDelta < -1024) {
            yawDelta += 2048;
        }
        if (yawDelta > 0) {
            cameraYaw += yawDelta * angleInterpolationSpeed / 1000 + minAngleStep;
            cameraYaw &= 0x7FF;
        }
        if (yawDelta < 0) {
            cameraYaw -= angleInterpolationSpeed * -yawDelta / 1000 + minAngleStep;
            cameraYaw &= 0x7FF;
        }
        @Pc(404) int finalYawDelta = targetYaw - cameraYaw;
        if (finalYawDelta > 1024) {
            finalYawDelta -= 2048;
        }
        if (finalYawDelta < -1024) {
            finalYawDelta += 2048;
        }
        if (finalYawDelta < 0 && yawDelta > 0 || finalYawDelta > 0 && yawDelta < 0) {
            cameraYaw = targetYaw;
        }
    }

    @OriginalMember(owner = "runetek4.client!da", name = "d", descriptor = "(I)V")
    public static void updateLoginScreenCamera() {
        if (cameraSplineId == -1 || lookAtSplineId == -1) {
            return;
        }
        @Pc(27) int animationSpeedDelta = (animationTimer * (maxAnimationSpeed - minAnimationSpeed) >> 16) + minAnimationSpeed;
        @Pc(30) float[] cameraPosition = new float[3];
        animationTimer += animationSpeedDelta;
        if (animationTimer >= 65535) {
            animationTimer = 65535;
            if (animationComplete) {
                shouldReverse = false;
            } else {
                shouldReverse = true;
            }
            animationComplete = true;
        } else {
            animationComplete = false;
            shouldReverse = false;
        }
        @Pc(66) float animationProgress = (float) animationTimer / 65535.0F;
        @Pc(70) int positionSplineIndex = positionKeyframe * 2;
        @Pc(141) int keyframe0Scaled;
        @Pc(131) int keyframe1Scaled;
        @Pc(111) int splineTerm3;
        @Pc(119) int keyframe0;
        @Pc(146) int linearTerm;
        @Pc(155) int quadraticTerm;
        @Pc(173) int cubicTerm;
        for (@Pc(72) int coordinateAxis = 0; coordinateAxis < 3; coordinateAxis++) {
            splineTerm3 = (cameraSplines[cameraSplineId][positionSplineIndex + 2][coordinateAxis] + cameraSplines[cameraSplineId][positionSplineIndex + 2][coordinateAxis] - cameraSplines[cameraSplineId][positionSplineIndex + 3][coordinateAxis]) * 3;
            keyframe0 = cameraSplines[cameraSplineId][positionSplineIndex][coordinateAxis];
            keyframe1Scaled = cameraSplines[cameraSplineId][positionSplineIndex + 1][coordinateAxis] * 3;
            keyframe0Scaled = cameraSplines[cameraSplineId][positionSplineIndex][coordinateAxis] * 3;
            linearTerm = keyframe1Scaled - keyframe0Scaled;
            quadraticTerm = splineTerm3 + keyframe0Scaled - keyframe1Scaled * 2;
            cubicTerm = cameraSplines[cameraSplineId][positionSplineIndex + 2][coordinateAxis] + keyframe1Scaled - keyframe0 - splineTerm3;
            cameraPosition[coordinateAxis] = (float) keyframe0 + (((float) cubicTerm * animationProgress + (float) quadraticTerm) * animationProgress + (float) linearTerm) * animationProgress;
        }
        cameraY = (int) cameraPosition[1] * -1;
        renderX = (int) cameraPosition[0] - sceneBaseTileX * 128;
        renderZ = (int) cameraPosition[2] - sceneBaseTileZ * 128;
        @Pc(226) float[] lookAtPosition = new float[3];
        keyframe0Scaled = lookAtKeyframe * 2;
        for (keyframe1Scaled = 0; keyframe1Scaled < 3; keyframe1Scaled++) {
            splineTerm3 = cameraSplines[lookAtSplineId][keyframe0Scaled][keyframe1Scaled] * 3;
            linearTerm = (cameraSplines[lookAtSplineId][keyframe0Scaled + 2][keyframe1Scaled] + cameraSplines[lookAtSplineId][keyframe0Scaled + 2][keyframe1Scaled] - cameraSplines[lookAtSplineId][keyframe0Scaled + 3][keyframe1Scaled]) * 3;
            quadraticTerm = cameraSplines[lookAtSplineId][keyframe0Scaled][keyframe1Scaled];
            keyframe0 = cameraSplines[lookAtSplineId][keyframe0Scaled + 1][keyframe1Scaled] * 3;
            cubicTerm = keyframe0 - splineTerm3;
            @Pc(313) int lookAtQuadraticTerm = linearTerm + splineTerm3 - keyframe0 * 2;
            @Pc(331) int lookAtCubicTerm = cameraSplines[lookAtSplineId][keyframe0Scaled + 2][keyframe1Scaled] + keyframe0 - linearTerm - quadraticTerm;
            lookAtPosition[keyframe1Scaled] = (float) quadraticTerm + animationProgress * (animationProgress * (animationProgress * (float) lookAtCubicTerm + (float) lookAtQuadraticTerm) + (float) cubicTerm);
        }
        @Pc(363) float deltaX = lookAtPosition[0] - cameraPosition[0];
        @Pc(371) float deltaZ = lookAtPosition[2] - cameraPosition[2];
        @Pc(382) float deltaY = (lookAtPosition[1] - cameraPosition[1]) * -1.0F;
        @Pc(392) double horizontalDistance = Math.sqrt((double) (deltaZ * deltaZ + deltaX * deltaX));
        pitchRadians = (float) Math.atan2((double) deltaY, horizontalDistance);
        yawRadians = -((float) Math.atan2((double) deltaX, (double) deltaZ));
        cameraPitch = (int) ((double) pitchRadians * 325.949D) & 0x7FF;
        cameraYaw = (int) ((double) yawRadians * 325.949D) & 0x7FF;
    }

    @OriginalMember(owner = "runetek4.client!vd", name = "a", descriptor = "(IIIIBI)V")
    public static void setCameraLookAtTarget(@OriginalArg(0) int heightOffset, @OriginalArg(1) int tileX, @OriginalArg(2) int minAngleStep, @OriginalArg(3) int tileZ, @OriginalArg(5) int interpolationSpeed) {
        Camera.minAngleStep = minAngleStep;
        lookAtTileX = tileX;
        angleInterpolationSpeed = interpolationSpeed;
        lookAtTileZ = tileZ;
        lookAtHeightOffset = heightOffset;
        if (angleInterpolationSpeed >= 100) {
            @Pc(30) int targetWorldZ = lookAtTileZ * 128 + 64;
            @Pc(36) int targetWorldX = lookAtTileX * 128 + 64;
            @Pc(44) int targetHeight = SceneGraph.getTileHeight(Player.currentLevel, targetWorldZ, targetWorldX) - lookAtHeightOffset;
            @Pc(49) int heightDifference = targetHeight - cameraY;
            @Pc(54) int zDistance = targetWorldZ - renderX;
            @Pc(59) int xDistance = targetWorldX - renderZ;
            @Pc(70) int horizontalDistance = (int) Math.sqrt((double) (xDistance * xDistance + zDistance * zDistance));
            cameraPitch = (int) (Math.atan2((double) heightDifference, (double) horizontalDistance) * 325.949D) & 0x7FF;
            cameraYaw = (int) (Math.atan2((double) zDistance, (double) xDistance) * -325.949D) & 0x7FF;
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
    public static void setCameraTargetPosition(@OriginalArg(0) boolean forceImmediate, @OriginalArg(1) int minInterpolationStep, @OriginalArg(2) int heightOffset, @OriginalArg(3) int interpolationSpeed, @OriginalArg(5) int targetTileX, @OriginalArg(6) int targetTileZ) {
        Camera.interpolationSpeed = interpolationSpeed;
        Camera.heightOffset = heightOffset;
        Camera.targetTileZ = targetTileZ;
        Camera.minInterpolationStep = minInterpolationStep;
        Camera.targetTileX = targetTileX;
        if (forceImmediate && Camera.interpolationSpeed >= 100) {
            renderX = Camera.targetTileZ * 128 + 64;
            renderZ = Camera.targetTileX * 128 + 64;
            cameraY = SceneGraph.getTileHeight(Player.currentLevel, renderX, renderZ) - Camera.heightOffset;
        }
        cameraType = 2;
    }

    @OriginalMember(owner = "runetek4.client!cl", name = "e", descriptor = "(I)V")
    public static void resetCameraEffects() {
        for (@Pc(3) int modifierIndex = 0; modifierIndex < 5; modifierIndex++) {
            cameraModifierEnabled[modifierIndex] = false;
        }
        minAngleStep = 0;
        angleInterpolationSpeed = 0;
        cameraSplineId = -1;
        lookAtSplineId = -1;
        cameraType = 1;
    }

    @OriginalMember(owner = "runetek4.client!uf", name = "a", descriptor = "(B)V")
    public static void updatePlayerCamera() {
        @Pc(14) int targetPlayerX = PlayerList.self.xFine + cameraAnticheatOffsetX;
        @Pc(20) int targetPlayerZ = PlayerList.self.zFine + cameraAnticheatOffsetZ;
        if (cameraX - targetPlayerX < -500 || cameraX - targetPlayerX > 500 || cameraZ - targetPlayerZ < -500 || cameraZ - targetPlayerZ > 500) {
            cameraX = targetPlayerX;
            cameraZ = targetPlayerZ;
        }
        if (cameraZ != targetPlayerZ) {
            cameraZ += (targetPlayerZ - cameraZ) / 16;
        }
        if (cameraX != targetPlayerX) {
            cameraX += (targetPlayerX - cameraX) / 16;
        }
        if (Preferences.aBoolean63) {
            for (@Pc(93) int keyIndex = 0; keyIndex < ComponentList.keyQueueSize; keyIndex++) {
                @Pc(104) int keyCode = ComponentList.keyCodes[keyIndex];
                if (keyCode == 98) {
                    orbitCameraPitch = orbitCameraPitch + 47 & 0xFFFFFFF0;
                } else if (keyCode == Keyboard.KEY_UP) {
                    orbitCameraPitch = orbitCameraPitch - 17 & 0xFFFFFFF0;
                } else if (keyCode == Keyboard.KEY_DOWN) {
                    orbitCameraYaw = orbitCameraYaw - 65 & 0xFFFFFF80;
                } else if (keyCode == Keyboard.KEY_RIGHT) {
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
    public static void orbitCamera(@OriginalArg(0) int centerX, @OriginalArg(2) int height, @OriginalArg(3) int centerY, @OriginalArg(4) int distance, @OriginalArg(5) int yaw, @OriginalArg(6) int centerZ, @OriginalArg(7) int pitch) {
        @Pc(5) int heightOffset;
        @Pc(29) int scaledDistance;
        if (GlRenderer.enabled) {
            heightOffset = height - 334;
            if (heightOffset < 0) {
                heightOffset = 0;
            } else if (heightOffset > 100) {
                heightOffset = 100;
            }
            scaledDistance = heightOffset * (ClientScriptRunner.aShort27 - ClientScriptRunner.aShort30) / 100 + ClientScriptRunner.aShort30;
            distance = scaledDistance * distance >> 8;
        }
        heightOffset = 2048 - pitch & 0x7FF;
        scaledDistance = 2048 - yaw & 0x7FF;
        @Pc(55) int offsetX = 0;
        @Pc(57) int adjustedDistance = distance;
        @Pc(59) int offsetY = 0;
        @Pc(72) int sinValue;
        @Pc(68) int cosValue;
        if (heightOffset != 0) {
            cosValue = MathUtils.cos[heightOffset];
            sinValue = MathUtils.sin[heightOffset];
            offsetY = sinValue * -distance >> 16;
            adjustedDistance = cosValue * distance >> 16;
        }
        if (scaledDistance != 0) {
            sinValue = MathUtils.sin[scaledDistance];
            cosValue = MathUtils.cos[scaledDistance];
            offsetX = sinValue * adjustedDistance >> 16;
            adjustedDistance = adjustedDistance * cosValue >> 16;
        }
        cameraPitch = pitch;
        cameraYaw = yaw;
        renderZ = centerZ - adjustedDistance;
        renderX = centerX - offsetX;
        cameraY = centerY - offsetY;
    }
}
