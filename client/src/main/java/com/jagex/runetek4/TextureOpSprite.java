package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("runetek4.client!nh")
public class TextureOpSprite extends TextureOp {

	@OriginalMember(owner = "runetek4.client!nh", name = "U", descriptor = "I")
	protected int anInt3239;

	@OriginalMember(owner = "runetek4.client!nh", name = "X", descriptor = "I")
	protected int anInt3240;

	@OriginalMember(owner = "runetek4.client!nh", name = "Y", descriptor = "[I")
	protected int[] anIntArray305;

	@OriginalMember(owner = "runetek4.client!nh", name = "P", descriptor = "I")
	private int anInt3235 = -1;

	@OriginalMember(owner = "runetek4.client!nh", name = "<init>", descriptor = "()V")
	public TextureOpSprite() {
		super(0, false);
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "h", descriptor = "(I)Z")
	protected final boolean method2573() {
		if (this.anIntArray305 != null) {
			return true;
		} else if (this.anInt3235 < 0) {
			return false;
		} else {
			@Pc(43) SoftwareSprite local43 = Static215.anInt4868 < 0 ? SpriteLoader.loadSoftwareSpriteAutoDetect(Texture.spritesArchive, this.anInt3235) : SpriteLoader.loadSoftwareSprite(this.anInt3235, Texture.spritesArchive, Static215.anInt4868);
			local43.trim();
			this.anInt3240 = local43.height;
			this.anInt3239 = local43.width;
			this.anIntArray305 = local43.pixels;
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "f", descriptor = "(I)I")
	@Override
	public final int method4631() {
		return this.anInt3235;
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "b", descriptor = "(II)[[I")
	@Override
	public int[][] getColorOutput(@OriginalArg(1) int arg0) {
		@Pc(18) int[][] local18 = this.clearImageCache.get(arg0);
		if (this.clearImageCache.invalid && this.method2573()) {
			@Pc(31) int[] local31 = local18[0];
			@Pc(35) int[] local35 = local18[1];
			@Pc(39) int[] local39 = local18[2];
			@Pc(59) int local59 = (this.anInt3240 == Texture.height ? arg0 : this.anInt3240 * arg0 / Texture.height) * this.anInt3239;
			@Pc(65) int local65;
			@Pc(78) int local78;
			if (Texture.width == this.anInt3239) {
				for (local65 = 0; local65 < Texture.width; local65++) {
					local78 = this.anIntArray305[local59++];
					local39[local65] = (local78 & 0xFF) << 4;
					local35[local65] = local78 >> 4 & 0xFF0;
					local31[local65] = local78 >> 12 & 0xFF0;
				}
			} else {
				for (local65 = 0; local65 < Texture.width; local65++) {
					local78 = this.anInt3239 * local65 / Texture.width;
					@Pc(127) int local127 = this.anIntArray305[local59 + local78];
					local39[local65] = (local127 & 0xFF) << 4;
					local35[local65] = local127 >> 4 & 0xFF0;
					local31[local65] = local127 >> 12 & 0xFF0;
				}
			}
		}
		return local18;
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt3235 = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!nh", name = "e", descriptor = "(B)V")
	@Override
	public final void clearImageCache() {
		super.clearImageCache();
		this.anIntArray305 = null;
	}
}
