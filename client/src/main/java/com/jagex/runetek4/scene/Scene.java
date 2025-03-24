package com.jagex.runetek4.scene;

import com.jagex.runetek4.*;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Scene {

    @OriginalMember(owner = "runetek4.client!dk", name = "a", descriptor = "(III)Lclient!ec;")
    public static Scenery getObject(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile tile = SceneGraph.tiles[arg0][arg1][arg2];
        if (tile == null) {
            return null;
        }
        for (@Pc(13) int i = 0; i < tile.entityCount; i++) {
            @Pc(22) Scenery scenery = tile.sceneries[i];
            if ((scenery.hash >> 29 & 0x3L) == 2L && scenery.anInt1701 == arg1 && scenery.anInt1696 == arg2) {
                Static266.method4193(scenery);
                return scenery;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(IIIILclient!th;JZ)V")
    public static void addGroundDecoration(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) Entity entity, @OriginalArg(5) long arg5, @OriginalArg(6) boolean arg6) {
        if (entity == null) {
            return;
        }
        @Pc(6) GroundDecor groundDecor = new GroundDecor();
        groundDecor.entity = entity;
        groundDecor.xFine = arg1 * 128 + 64;
        groundDecor.zFine = arg2 * 128 + 64;
        groundDecor.anInt733 = arg3;
        groundDecor.key = arg5;
        groundDecor.aBoolean49 = arg6;
        if (SceneGraph.tiles[arg0][arg1][arg2] == null) {
            SceneGraph.tiles[arg0][arg1][arg2] = new Tile(arg0, arg1, arg2);
        }
        SceneGraph.tiles[arg0][arg1][arg2].groundDecor = groundDecor;
    }
}
