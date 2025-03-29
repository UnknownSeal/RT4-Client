package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Class6 {

	@OriginalMember(owner = "runetek4.client!qg", name = "R", descriptor = "I")
	public static int anInt4741;

    @OriginalMember(owner = "runetek4.client!cj", name = "a", descriptor = "(I)[Lclient!qf;")
    public static Sprite[] method870() {
        @Pc(6) Sprite[] local6 = new Sprite[Static165.anInt4038];
        for (@Pc(15) int local15 = 0; local15 < Static165.anInt4038; local15++) {
            @Pc(30) int local30 = SpriteLoader.innerWidths[local15] * SpriteLoader.innerHeights[local15];
            @Pc(34) byte[] local34 = SpriteLoader.pixels[local15];
            @Pc(37) int[] local37 = new int[local30];
            for (@Pc(39) int local39 = 0; local39 < local30; local39++) {
                local37[local39] = Static259.anIntArray513[local34[local39] & 0xFF];
            }
            if (GlRenderer.enabled) {
                local6[local15] = new GlSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local15], SpriteLoader.yOffsets[local15], SpriteLoader.innerWidths[local15], SpriteLoader.innerHeights[local15], local37);
            } else {
                local6[local15] = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local15], SpriteLoader.yOffsets[local15], SpriteLoader.innerWidths[local15], SpriteLoader.innerHeights[local15], local37);
            }
        }
        SpriteLoader.clear();
        return local6;
    }
}
