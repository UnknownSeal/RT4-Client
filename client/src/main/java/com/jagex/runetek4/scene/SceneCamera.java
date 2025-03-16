package com.jagex.runetek4.scene;

import com.jagex.runetek4.*;
import com.jagex.runetek4.media.renderable.actor.Player;
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
        @Pc(33) int local33 = Camera.cameraX >> 7;
        @Pc(37) int local37 = Camera.cameraZ >> 7;
        @Pc(43) int local43 = SceneGraph.getTileHeight(Player.plane, Camera.cameraX, Camera.cameraZ);
        @Pc(45) int local45 = 0;
        @Pc(64) int local64;
        if (local33 > 3 && local37 > 3 && local33 < 100 && local37 < 100) {
            for (local64 = local33 - 4; local64 <= local33 + 4; local64++) {
                for (@Pc(73) int local73 = local37 - 4; local73 <= local37 + 4; local73++) {
                    @Pc(80) int local80 = Player.plane;
                    if (local80 < 3 && (SceneGraph.renderFlags[1][local64][local73] & 0x2) == 2) {
                        local80++;
                    }
                    @Pc(117) int local117 = (SceneGraph.aByteArrayArrayArray13[local80][local64][local73] & 0xFF) * 8 + local43 - SceneGraph.tileHeights[local80][local64][local73];
                    if (local117 > local45) {
                        local45 = local117;
                    }
                }
            }
        }
        local64 = local45 * 192;
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
