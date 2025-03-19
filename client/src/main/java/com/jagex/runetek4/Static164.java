package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static164 {

	@OriginalMember(owner = "runetek4.client!na", name = "o", descriptor = "I")
	public static int anInt3988 = 0;

	@OriginalMember(owner = "runetek4.client!na", name = "cb", descriptor = "Lclient!na;")
	public static final JString ALLYREQ = JString.parse(":allyreq:");

	@OriginalMember(owner = "runetek4.client!na", name = "mb", descriptor = "[I")
	public static final int[] anIntArray362 = new int[50];

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IIIIIIIZ)Z")
	public static boolean method3109(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(6) int arg4) {
		@Pc(14) long local14 = SceneGraph.getWallKey(arg4, arg0 + 0, arg2);
		@Pc(28) int local28;
		@Pc(35) int local35;
		@Pc(42) int local42;
		@Pc(46) LocType local46;
		@Pc(65) int local65;
		@Pc(75) int[] local75;
		@Pc(90) int local90;
		if (local14 != 0L) {
			local28 = (int) local14 >> 20 & 0x3;
			local35 = (int) local14 >> 14 & 0x1F;
			local42 = Integer.MAX_VALUE & (int) (local14 >>> 32);
			local46 = LocTypeList.get(local42);
			if (local46.mapsceneicon == -1) {
				local65 = arg1;
				if (local14 > 0L) {
					local65 = arg3;
				}
				local75 = SoftwareRaster.destinationPixels;
				local90 = (52736 - arg2 * 512) * 4 + arg0 * 4 + 24624;
				if (local35 == 0 || local35 == 2) {
					if (local28 == 0) {
						local75[local90] = local65;
						local75[local90 + 512] = local65;
						local75[local90 + 1024] = local65;
						local75[local90 + 1536] = local65;
					} else if (local28 == 1) {
						local75[local90] = local65;
						local75[local90 + 1] = local65;
						local75[local90 + 2] = local65;
						local75[local90 + 3] = local65;
					} else if (local28 == 2) {
						local75[local90 + 3] = local65;
						local75[local90 + 3 + 512] = local65;
						local75[local90 + 3 + 1024] = local65;
						local75[local90 + 3 + 1536] = local65;
					} else if (local28 == 3) {
						local75[local90 + 1536] = local65;
						local75[local90 + 1536 + 1] = local65;
						local75[local90 + 1538] = local65;
						local75[local90 + 3 + 1536] = local65;
					}
				}
				if (local35 == 3) {
					if (local28 == 0) {
						local75[local90] = local65;
					} else if (local28 == 1) {
						local75[local90 + 3] = local65;
					} else if (local28 == 2) {
						local75[local90 + 3 + 1536] = local65;
					} else if (local28 == 3) {
						local75[local90 + 1536] = local65;
					}
				}
				if (local35 == 2) {
					if (local28 == 3) {
						local75[local90] = local65;
						local75[local90 + 512] = local65;
						local75[local90 + 1024] = local65;
						local75[local90 + 1536] = local65;
					} else if (local28 == 0) {
						local75[local90] = local65;
						local75[local90 + 1] = local65;
						local75[local90 + 2] = local65;
						local75[local90 + 3] = local65;
					} else if (local28 == 1) {
						local75[local90 + 3] = local65;
						local75[local90 + 512 + 3] = local65;
						local75[local90 + 1024 + 3] = local65;
						local75[local90 + 1536 + 3] = local65;
					} else if (local28 == 2) {
						local75[local90 + 1536] = local65;
						local75[local90 + 1536 + 1] = local65;
						local75[local90 + 1536 + 2] = local65;
						local75[local90 + 1539] = local65;
					}
				}
			} else if (!Static33.method867(arg0, local46, arg2, local28)) {
				return false;
			}
		}
		local14 = SceneGraph.getSceneryKey(arg4, arg0 + 0, arg2);
		if (local14 != 0L) {
			local28 = (int) local14 >> 20 & 0x3;
			local35 = (int) local14 >> 14 & 0x1F;
			local42 = (int) (local14 >>> 32) & Integer.MAX_VALUE;
			local46 = LocTypeList.get(local42);
			if (local46.mapsceneicon == -1) {
				if (local35 == 9) {
					local65 = 15658734;
					if (local14 > 0L) {
						local65 = 15597568;
					}
					local90 = arg0 * 4 + (103 - arg2) * 2048 + 24624;
					local75 = SoftwareRaster.destinationPixels;
					if (local28 == 0 || local28 == 2) {
						local75[local90 + 1536] = local65;
						local75[local90 + 1025] = local65;
						local75[local90 + 512 + 2] = local65;
						local75[local90 + 3] = local65;
					} else {
						local75[local90] = local65;
						local75[local90 + 512 + 1] = local65;
						local75[local90 + 1024 + 2] = local65;
						local75[local90 + 1536 + 3] = local65;
					}
				}
			} else if (!Static33.method867(arg0, local46, arg2, local28)) {
				return false;
			}
		}
		local14 = SceneGraph.getGroundDecorKey(arg4, arg0 + 0, arg2);
		if (local14 != 0L) {
			local28 = (int) local14 >> 20 & 0x3;
			local35 = (int) (local14 >>> 32) & Integer.MAX_VALUE;
			@Pc(586) LocType local586 = LocTypeList.get(local35);
			if (local586.mapsceneicon != -1 && !Static33.method867(arg0, local586, arg2, local28)) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(Lclient!ba;Lclient!ba;IIIZZ)I")
	public static int method3115(@OriginalArg(0) GWCWorld arg0, @OriginalArg(1) GWCWorld arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) boolean arg5) {
		@Pc(8) int local8 = Static270.method4595(arg1, arg3, arg0, arg5);
		if (local8 != 0) {
			return arg5 ? -local8 : local8;
		} else if (arg2 == -1) {
			return 0;
		} else {
			@Pc(42) int local42 = Static270.method4595(arg1, arg2, arg0, arg4);
			return arg4 ? -local42 : local42;
		}
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(Lclient!ve;IZ)Lclient!mm;")
	public static SoftwareSprite method3117(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1) {
		return Static254.method4346(arg0, arg1) ? Static196.method3537() : null;
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IZILclient!ve;)Lclient!ek;")
	public static SoftwareIndexedSprite method3119(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return SpriteLoader.decode(arg1, 0, arg0) ? Static134.method2619() : null;
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IBZIZIIZ)Lclient!qf;")
	public static Sprite method3150(@OriginalArg(0) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) boolean backColor) {
		@Pc(5) ObjType definition = ObjTypeList.get(arg2);
		if (arg5 > 1 && definition.countobj != null) {
			@Pc(15) int local15 = -1;
			for (@Pc(17) int local17 = 0; local17 < 10; local17++) {
				if (arg5 >= definition.countco[local17] && definition.countco[local17] != 0) {
					local15 = definition.countobj[local17];
				}
			}
			if (local15 != -1) {
				definition = ObjTypeList.get(local15);
			}
		}
		@Pc(60) SoftwareModel local60 = definition.method1834();
		if (local60 == null) {
			return null;
		}
		@Pc(71) SoftwareSprite local71 = null;
		if (definition.certtemplate != -1) {
			local71 = (SoftwareSprite) method3150(0, true, definition.certlink, false, 1, 10, true);
			if (local71 == null) {
				return null;
			}
		} else if (definition.lenttemplate != -1) {
			local71 = (SoftwareSprite) method3150(arg0, true, definition.lentlink, false, arg4, arg5, false);
			if (local71 == null) {
				return null;
			}
		}
		@Pc(118) int[] pixels = SoftwareRaster.destinationPixels;
		@Pc(120) int local120 = SoftwareRaster.destinationWidth;
		@Pc(122) int local122 = Rasterizer.destinationHeight;
		@Pc(125) int[] local125 = new int[4];
		Rasterizer.getViewportDimensions(local125);
		@Pc(133) SoftwareSprite rendered = new SoftwareSprite(36, 32);
		SoftwareRaster.setSize(rendered.pixels, 36, 32);
		Rasterizer.setSize();
		Rasterizer.setBounds(16, 16);
		@Pc(145) int local145 = definition.zoom2d;
		Pix3D.aBoolean136 = false;
		if (backColor) {
			local145 = (int) ((double) local145 * 1.5D);
		} else if (arg4 == 2) {
			local145 = (int) ((double) local145 * 1.04D);
		}
		@Pc(176) int local176 = MathUtils.cos[definition.xan2d] * local145 >> 16;
		@Pc(185) int local185 = MathUtils.sin[definition.xan2d] * local145 >> 16;
		local60.setCamera(definition.yan2d, definition.zan2d, definition.xan2d, definition.xof2d, local185 + definition.yof2d - local60.getMinY() / 2, definition.yof2d + local176, -1L);
		if (arg4 >= 1) {
			rendered.drawOutline(1);
			if (arg4 >= 2) {
				rendered.drawOutline(16777215);
			}
			SoftwareRaster.setSize(rendered.pixels, 36, 32);
		}
		if (arg0 != 0) {
			rendered.drawShadow(arg0);
		}
		if (definition.certtemplate != -1) {
			local71.render(0, 0);
		} else if (definition.lenttemplate != -1) {
			SoftwareRaster.setSize(local71.pixels, 36, 32);
			rendered.render(0, 0);
			rendered = local71;
		}
		if (arg3 && (definition.stackable == 1 || arg5 != 1) && arg5 != -1) {
			Static256.aClass3_Sub2_Sub9_Sub1_1.renderLeft(Component.getShortenedAmountText(arg5), 0, 9, 16776960, 1);
		}
		SoftwareRaster.setSize(pixels, local120, local122);
		Rasterizer.setViewportDimensions(local125);
		Rasterizer.setSize();
		Pix3D.aBoolean136 = true;
		return GlRenderer.enabled && !arg1 ? new GlSprite(rendered) : rendered;
	}
}
