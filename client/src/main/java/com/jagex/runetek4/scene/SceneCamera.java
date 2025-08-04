package com.jagex.runetek4.scene;

import com.jagex.runetek4.entity.entity.Player;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SceneCamera {
    @OriginalMember(owner = "runetek4.client!gn", name = "b", descriptor = "(B)V")
    public static void clampCameraAngle() {
        if (Camera.orbitCameraPitch < 128) {
            Camera.orbitCameraPitch = 128;
        }
        if (Camera.orbitCameraPitch > 383) {
            Camera.orbitCameraPitch = 383;
        }
        Camera.orbitCameraYaw &= 0x7FF;
        @Pc(33) int cameraTileX = Camera.cameraX >> 7;
        @Pc(37) int cameraTileZ = Camera.cameraZ >> 7;
        @Pc(43) int cameraBaseHeight = SceneGraph.getTileHeight(Player.plane, Camera.cameraX, Camera.cameraZ);
        @Pc(45) int maxHeightDifference = 0;
        @Pc(64) int local64;
        if (cameraTileX > 3 && cameraTileZ > 3 && cameraTileX < 100 && cameraTileZ < 100) {
            for (local64 = cameraTileX - 4; local64 <= cameraTileX + 4; local64++) {
                for (@Pc(73) int tileZ = cameraTileZ - 4; tileZ <= cameraTileZ + 4; tileZ++) {
                    @Pc(80) int checkPlane = Player.plane;
                    if (checkPlane < 3 && (SceneGraph.renderFlags[1][local64][tileZ] & 0x2) == 2) {
                        checkPlane++;
                    }
                    @Pc(117) int heightDifference = (SceneGraph.tileSettings[checkPlane][local64][tileZ] & 0xFF) * 8 + cameraBaseHeight - SceneGraph.tileHeights[checkPlane][local64][tileZ];
                    if (heightDifference > maxHeightDifference) {
                        maxHeightDifference = heightDifference;
                    }
                }
            }
        }
        local64 = maxHeightDifference * 192;
        if (local64 > 98048) {
            local64 = 98048;
        }
        if (local64 < 32768) {
            local64 = 32768;
        }
        if (Camera.cameraPitchClamp < local64) {
            Camera.cameraPitchClamp += (local64 - Camera.cameraPitchClamp) / 24;
        } else if (local64 < Camera.cameraPitchClamp) {
            Camera.cameraPitchClamp += (local64 - Camera.cameraPitchClamp) / 80;
        }
    }
}
