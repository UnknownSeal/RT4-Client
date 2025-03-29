package com.jagex.runetek4.game.client;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.Node;

@OriginalClass("client!qe")
public final class Inv extends Node {

	@OriginalMember(owner = "client!cb", name = "I", descriptor = "[I")
	public static final int[] updatedInventories = new int[32];
	@OriginalMember(owner = "runetek4.client!kl", name = "u", descriptor = "Lclient!na;")
	public static final JString aClass100_637 = JString.parse("<col=ffffff>");
	@OriginalMember(owner = "runetek4.client!ol", name = "Y", descriptor = "Lclient!na;")
	public static final JString aClass100_819 = JString.parse("<col=00ff80>");
	@OriginalMember(owner = "runetek4.client!ib", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_559 = JString.parse("<col=ffff00>");
	@OriginalMember(owner = "runetek4.client!jj", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_594 = JString.parse("<)4col>");
	@OriginalMember(owner = "client!bj", name = "v", descriptor = "Lclient!sc;")
	public static HashTable objectContainerCache = new HashTable(32);
    @OriginalMember(owner = "runetek4.client!ii", name = "c", descriptor = "I")
    public static int updatedInventoriesWriterIndex = 0;
    @OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] invSlotObjId = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] invSlotObjCount = new int[] { 0 };

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) Inv local12 = (Inv) objectContainerCache.getNode((long) arg3);
		if (local12 == null) {
			local12 = new Inv();
			objectContainerCache.put(local12, (long) arg3);
		}
		if (local12.invSlotObjId.length <= arg1) {
			@Pc(39) int[] local39 = new int[arg1 + 1];
			@Pc(44) int[] local44 = new int[arg1 + 1];
			for (int index = 0; index < local12.invSlotObjId.length; index++) {
				local39[index] = local12.invSlotObjId[index];
				local44[index] = local12.invSlotObjCount[index];
			}
			for (int index = local12.invSlotObjId.length; index < arg1; index++) {
				local39[index] = -1;
				local44[index] = 0;
			}
			local12.invSlotObjId = local39;
			local12.invSlotObjCount = local44;
		}
		local12.invSlotObjId[arg1] = arg0;
		local12.invSlotObjCount[arg1] = arg2;
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(BII)I")
	public static int getSlotTotal(@OriginalArg(1) int arg0, @OriginalArg(2) int slot) {
		@Pc(8) Inv inv = (Inv) objectContainerCache.getNode(arg0);
		if (inv == null) {
			return 0;
		} else if (slot == -1) {
			return 0;
		} else {
			@Pc(25) int total = 0;
			for (@Pc(27) int index = 0; index < inv.invSlotObjCount.length; index++) {
				if (slot == inv.invSlotObjId[index]) {
					total += inv.invSlotObjCount[index];
				}
			}
			return total;
		}
	}

	@OriginalMember(owner = "client!ba", name = "a", descriptor = "(IB)I")
	public static int getFreeSpace(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return 0;
		}
		@Pc(17) Inv inv = (Inv) objectContainerCache.getNode((long) arg0);
		if (inv == null) {
			return InvTypeList.get(arg0).size;
		}
		@Pc(31) int freeSpaces = 0;
		for (@Pc(33) int local33 = 0; local33 < inv.invSlotObjId.length; local33++) {
			if (inv.invSlotObjId[local33] == -1) {
				freeSpaces++;
			}
		}
		return freeSpaces + InvTypeList.get(arg0).size - inv.invSlotObjId.length;
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(B)V")
	public static void clear() {
		objectContainerCache = new HashTable(32);
	}

    @OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(IIZIII)Lclient!qf;")
    public static Sprite getObjectSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
        @Pc(27) int key = (arg2 ? 65536 : 0) + arg1 + (arg0 << 17) + (arg4 << 19);
        @Pc(37) long uid = (long) key * 3849834839L + (long) arg3 * 3147483667L;
        @Pc(43) Sprite sprite = (Sprite) ObjTypeList.objectSpriteCache.get(uid);
        if (sprite != null) {
            return sprite;
        }
        Rasterizer.textureHasTransparency = false;
        sprite = renderObjectSprite(arg4, false, arg1, arg2, arg0, arg3, false);
        if (sprite != null && !Rasterizer.textureHasTransparency) {
            ObjTypeList.objectSpriteCache.put(sprite, uid);
        }
        return sprite;
    }

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "(III)I")
	public static int getItemCount(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) Inv local10 = (Inv) objectContainerCache.getNode((long) arg0);
		if (local10 == null) {
			return 0;
		} else if (arg1 >= 0 && arg1 < local10.invSlotObjCount.length) {
			return local10.invSlotObjCount[arg1];
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(III)I")
	public static int getItemType(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) Inv local10 = (Inv) objectContainerCache.getNode(arg0);
		if (local10 == null) {
			return -1;
		} else if (arg1 >= 0 && arg1 < local10.invSlotObjId.length) {
			return local10.invSlotObjId[arg1];
		} else {
			return -1;
		}
	}

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "(II)V")
	public static void delete(@OriginalArg(0) int arg0) {
		@Pc(14) Inv local14 = (Inv) objectContainerCache.getNode((long) arg0);
		if (local14 != null) {
			local14.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IBZIZIIZ)Lclient!qf;")
	public static Sprite renderObjectSprite(@OriginalArg(0) int arg0, @OriginalArg(2) boolean linked, @OriginalArg(3) int arg2, @OriginalArg(4) boolean drawText, @OriginalArg(5) int arg4, @OriginalArg(6) int stack, @OriginalArg(7) boolean cert) {
		@Pc(5) ObjType objType = ObjTypeList.get(arg2);
		if (stack > 1 && objType.countObj != null) {
			@Pc(15) int local15 = -1;
			for (@Pc(17) int local17 = 0; local17 < 10; local17++) {
				if (stack >= objType.countco[local17] && objType.countco[local17] != 0) {
					local15 = objType.countObj[local17];
				}
			}
			if (local15 != -1) {
				objType = ObjTypeList.get(local15);
			}
		}
		@Pc(60) SoftwareModel model = objType.getInvModel();
		if (model == null) {
			return null;
		}
		@Pc(71) SoftwareSprite linkedSprite = null;
		if (objType.certTemplate != -1) {
			linkedSprite = (SoftwareSprite) renderObjectSprite(0, true, objType.certLink, false, 1, 10, true);
			if (linkedSprite == null) {
				return null;
			}
		} else if (objType.lentTemplate != -1) {
			linkedSprite = (SoftwareSprite) renderObjectSprite(arg0, true, objType.lentLink, false, arg4, stack, false);
			if (linkedSprite == null) {
				return null;
			}
		}
		@Pc(118) int[] pixels = SoftwareRaster.pixels;
		@Pc(120) int width = SoftwareRaster.width;
		@Pc(122) int height = Rasterizer.destinationHeight;
		@Pc(125) int[] clip = new int[4];
		SoftwareRaster.saveClip(clip);
		@Pc(133) SoftwareSprite canvas = new SoftwareSprite(36, 32);
		SoftwareRaster.setSize(canvas.pixels, 36, 32);
		Rasterizer.prepare();
		Rasterizer.setBounds(16, 16);
		@Pc(145) int zoom = objType.zoom2d;
		Rasterizer.jagged = false;
		if (cert) {
			zoom = (int) ((double) zoom * 1.5D);
		} else if (arg4 == 2) {
			zoom = (int) ((double) zoom * 1.04D);
		}
		@Pc(176) int pitchcos = MathUtils.cos[objType.xAngle2D] * zoom >> 16;
		@Pc(185) int pitchsin = MathUtils.sin[objType.xAngle2D] * zoom >> 16;
		model.setCamera(objType.yAngle2D, objType.zAngle2D, objType.xAngle2D, objType.xOffset2D, pitchsin + objType.yOffset2D - model.getMinY() / 2, objType.yOffset2D + pitchcos, -1L);
		if (arg4 >= 1) {
			canvas.drawOutline(1);
			if (arg4 >= 2) {
				canvas.drawOutline(16777215);
			}
			SoftwareRaster.setSize(canvas.pixels, 36, 32);
		}
		if (arg0 != 0) {
			canvas.drawShadow(arg0);
		}
		if (objType.certTemplate != -1) {
			linkedSprite.render(0, 0);
		} else if (objType.lentTemplate != -1) {
			SoftwareRaster.setSize(linkedSprite.pixels, 36, 32);
			canvas.render(0, 0);
			canvas = linkedSprite;
		}
		if (drawText && (objType.stackable == 1 || stack != 1) && stack != -1) {
			ObjTypeList.font.renderLeft(getShortenedAmountText(stack), 0, 9, 16776960, 1);
		}
		SoftwareRaster.setSize(pixels, width, height);
		SoftwareRaster.restoreClip(clip);
		Rasterizer.prepare();
		Rasterizer.jagged = true;
		return GlRenderer.enabled && !linked ? new GlSprite(canvas) : canvas;
	}

	@OriginalMember(owner = "runetek4.client!eb", name = "b", descriptor = "(II)Lclient!na;")
	public static JString getShortenedAmountText(@OriginalArg(1) int amount) {
		if (amount < 100000) {
			return JString.concatenate(new JString[] {aClass100_559, JString.parseInt(amount), aClass100_594});
		} else if (amount >= 10000000) {
			return JString.concatenate(new JString[] {aClass100_819, JString.parseInt(amount / 1000000), LocalizedText.MILLION, aClass100_594});
		} else {
			return JString.concatenate(new JString[] {aClass100_637, JString.parseInt(amount / 1000), LocalizedText.THOUSAND, aClass100_594});
		}
	}
}