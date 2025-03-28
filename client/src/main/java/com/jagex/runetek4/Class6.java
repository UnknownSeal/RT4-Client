package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Class6 {

	@OriginalMember(owner = "runetek4.client!qg", name = "R", descriptor = "I")
	public static int anInt4741;

	@OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IZI)V")
	public static void method3655(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(7) VarbitDefinition local7 = VarbitDefinition.getDefinition(arg0);
		@Pc(10) int local10 = local7.anInt3323;
		@Pc(16) int local16 = local7.anInt3318;
		@Pc(19) int local19 = local7.index;
		@Pc(25) int local25 = VarbitDefinition.varbitMasks[local10 - local16];
		if (arg1 < 0 || arg1 > local25) {
			arg1 = 0;
		}
		local25 <<= local16;
		Static148.method2766(local19, local25 & arg1 << local16 | VarPlayerDefinition.activeVarps[local19] & ~local25);
	}

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
