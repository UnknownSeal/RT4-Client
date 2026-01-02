package com.jagex.game.inventory;

import com.jagex.game.runetek4.config.objtype.ObjType;
import com.jagex.cache.media.SoftwareSprite;
import com.jagex.game.runetek4.config.invtype.InvTypeList;
import com.jagex.game.runetek4.config.objtype.ObjTypeList;
import com.jagex.core.datastruct.key.IntNode;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.ui.sprite.GlSprite;
import com.jagex.core.utils.string.JString;
import com.jagex.core.utils.string.LocalizedText;
import com.jagex.graphics.model.SoftwareModel;
import com.jagex.graphics.raster.Rasterizer;
import com.jagex.graphics.raster.SoftwareRenderer;
import com.jagex.ui.sprite.Sprite;
import com.jagex.core.utils.math.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.Node;

@OriginalClass("client!qe")
public final class ClientInventory extends Node {

	@OriginalMember(owner = "client!cb", name = "I", descriptor = "[I")
	public static final int[] updates = new int[32];

	@OriginalMember(owner = "runetek4.client!kl", name = "u", descriptor = "Lclient!na;")
	public static final JString COLOR_WHITE = JString.parse("<col=ffffff>");

	@OriginalMember(owner = "runetek4.client!ol", name = "Y", descriptor = "Lclient!na;")
	public static final JString COLOR_SPRING_GREEN = JString.parse("<col=00ff80>");

	@OriginalMember(owner = "runetek4.client!ib", name = "g", descriptor = "Lclient!na;")
	public static final JString COLOR_YELLOW = JString.parse("<col=ffff00>");

	@OriginalMember(owner = "runetek4.client!jj", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_594 = JString.parse("<)4col>");

	@OriginalMember(owner = "client!bj", name = "v", descriptor = "Lclient!sc;")
	public static IterableHashTable recentUse = new IterableHashTable(32);

    @OriginalMember(owner = "runetek4.client!ii", name = "c", descriptor = "I")
    public static int updateCount = 0;

    @OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] invSlotObjId = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] invSlotObjCount = new int[] { 0 };

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void setSlot(@OriginalArg(0) int objectId, @OriginalArg(1) int slotIndex, @OriginalArg(2) int count, @OriginalArg(3) int inventoryId) {
		@Pc(12) ClientInventory inventory = (ClientInventory) recentUse.get((long) inventoryId);
		if (inventory == null) {
			inventory = new ClientInventory();
			recentUse.put(inventory, (long) inventoryId);
		}
		if (inventory.invSlotObjId.length <= slotIndex) {
			@Pc(39) int[] newObjectIds = new int[slotIndex + 1];
			@Pc(44) int[] newCounts = new int[slotIndex + 1];
			for (int index = 0; index < inventory.invSlotObjId.length; index++) {
				newObjectIds[index] = inventory.invSlotObjId[index];
				newCounts[index] = inventory.invSlotObjCount[index];
			}
			for (int index = inventory.invSlotObjId.length; index < slotIndex; index++) {
				newObjectIds[index] = -1;
				newCounts[index] = 0;
			}
			inventory.invSlotObjId = newObjectIds;
			inventory.invSlotObjCount = newCounts;
		}
		inventory.invSlotObjId[slotIndex] = objectId;
		inventory.invSlotObjCount[slotIndex] = count;
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(BII)I")
	public static int getSlotTotal(@OriginalArg(1) int inventoryId, @OriginalArg(2) int slot) {
		@Pc(8) ClientInventory inv = (ClientInventory) recentUse.get(inventoryId);
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
	public static int getFreeSpace(@OriginalArg(0) int inventoryId) {
		if (inventoryId < 0) {
			return 0;
		}
		@Pc(17) ClientInventory inv = (ClientInventory) recentUse.get((long) inventoryId);
		if (inv == null) {
			return InvTypeList.get(inventoryId).size;
		}
		@Pc(31) int freeSpaces = 0;
		for (@Pc(33) int slotIndex = 0; slotIndex < inv.invSlotObjId.length; slotIndex++) {
			if (inv.invSlotObjId[slotIndex] == -1) {
				freeSpaces++;
			}
		}
		return freeSpaces + InvTypeList.get(inventoryId).size - inv.invSlotObjId.length;
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(B)V")
	public static void clear() {
		recentUse = new IterableHashTable(32);
	}

    @OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(IIZIII)Lclient!qf;")
    public static Sprite getObjectSprite(@OriginalArg(0) int shadowColor, @OriginalArg(1) int objectId, @OriginalArg(2) boolean hasOutline, @OriginalArg(3) int stackCount, @OriginalArg(4) int outlineType) {
        @Pc(27) int key = (hasOutline ? 65536 : 0) + objectId + (shadowColor << 17) + (outlineType << 19);
        @Pc(37) long uid = (long) key * 3849834839L + (long) stackCount * 3147483667L;
        @Pc(43) Sprite sprite = (Sprite) ObjTypeList.objectSpriteCache.get(uid);
        if (sprite != null) {
            return sprite;
        }
        Rasterizer.textureHasTransparency = false;
        sprite = renderObjectSprite(outlineType, false, objectId, hasOutline, shadowColor, stackCount, false);
        if (sprite != null && !Rasterizer.textureHasTransparency) {
            ObjTypeList.objectSpriteCache.put(sprite, uid);
        }
        return sprite;
    }

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "(III)I")
	public static int getItemCount(@OriginalArg(1) int inventoryId, @OriginalArg(2) int slotIndex) {
		@Pc(10) ClientInventory inventory = (ClientInventory) recentUse.get((long) inventoryId);
		if (inventory == null) {
			return 0;
		} else if (slotIndex >= 0 && slotIndex < inventory.invSlotObjCount.length) {
			return inventory.invSlotObjCount[slotIndex];
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(III)I")
	public static int getItemType(@OriginalArg(0) int inventoryId, @OriginalArg(2) int slotIndex) {
		@Pc(10) ClientInventory inventory = (ClientInventory) recentUse.get(inventoryId);
		if (inventory == null) {
			return -1;
		} else if (slotIndex >= 0 && slotIndex < inventory.invSlotObjId.length) {
			return inventory.invSlotObjId[slotIndex];
		} else {
			return -1;
		}
	}

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "(II)V")
	public static void delete(@OriginalArg(0) int inventoryId) {
		@Pc(14) ClientInventory inventory = (ClientInventory) recentUse.get((long) inventoryId);
		if (inventory != null) {
			inventory.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IBZIZIIZ)Lclient!qf;")
	public static Sprite renderObjectSprite(@OriginalArg(0) int shadowColor, @OriginalArg(2) boolean linked, @OriginalArg(3) int objectId, @OriginalArg(4) boolean drawText, @OriginalArg(5) int outlineType, @OriginalArg(6) int stack, @OriginalArg(7) boolean cert) {
		@Pc(5) ObjType objType = ObjTypeList.get(objectId);
		if (stack > 1 && objType.countObj != null) {
			@Pc(15) int stackedObjectId = -1;
			for (@Pc(17) int stackTierIndex = 0; stackTierIndex < 10; stackTierIndex++) {
				if (stack >= objType.countco[stackTierIndex] && objType.countco[stackTierIndex] != 0) {
					stackedObjectId = objType.countObj[stackTierIndex];
				}
			}
			if (stackedObjectId != -1) {
				objType = ObjTypeList.get(stackedObjectId);
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
			linkedSprite = (SoftwareSprite) renderObjectSprite(shadowColor, true, objType.lentLink, false, outlineType, stack, false);
			if (linkedSprite == null) {
				return null;
			}
		}
		@Pc(118) int[] pixels = SoftwareRenderer.pixels;
		@Pc(120) int width = SoftwareRenderer.width;
		@Pc(122) int height = SoftwareRenderer.height;
		@Pc(125) int[] clip = new int[4];
		SoftwareRenderer.saveClip(clip);
		@Pc(133) SoftwareSprite canvas = new SoftwareSprite(36, 32);
		SoftwareRenderer.setSize(canvas.pixels, 36, 32);
		Rasterizer.prepare();
		Rasterizer.setBounds(16, 16);
		@Pc(145) int zoom = objType.zoom2d;
		Rasterizer.jagged = false;
		if (cert) {
			zoom = (int) ((double) zoom * 1.5D);
		} else if (outlineType == 2) {
			zoom = (int) ((double) zoom * 1.04D);
		}
		@Pc(176) int pitchcos = MathUtils.cos[objType.xan2d] * zoom >> 16;
		@Pc(185) int pitchsin = MathUtils.sin[objType.xan2d] * zoom >> 16;
		model.setCamera(objType.yan2d, objType.zAngle2D, objType.xan2d, objType.xof2d, pitchsin + objType.yof2d - model.getMinY() / 2, objType.yof2d + pitchcos, -1L);
		if (outlineType >= 1) {
			canvas.drawOutline(1);
			if (outlineType >= 2) {
				canvas.drawOutline(16777215);
			}
			SoftwareRenderer.setSize(canvas.pixels, 36, 32);
		}
		if (shadowColor != 0) {
			canvas.drawShadow(shadowColor);
		}
		if (objType.certTemplate != -1) {
			linkedSprite.render(0, 0);
		} else if (objType.lentTemplate != -1) {
			SoftwareRenderer.setSize(linkedSprite.pixels, 36, 32);
			canvas.render(0, 0);
			canvas = linkedSprite;
		}
		if (drawText && (objType.stackable == 1 || stack != 1) && stack != -1) {
			ObjTypeList.font.renderLeft(getShortenedAmountText(stack), 0, 9, 16776960, 1);
		}
		SoftwareRenderer.setSize(pixels, width, height);
		SoftwareRenderer.restoreClip(clip);
		Rasterizer.prepare();
		Rasterizer.jagged = true;
		return GlRenderer.enabled && !linked ? new GlSprite(canvas) : canvas;
	}

	@OriginalMember(owner = "runetek4.client!eb", name = "b", descriptor = "(II)Lclient!na;")
	public static JString getShortenedAmountText(@OriginalArg(1) int amount) {
		if (amount < 100000) {
			return JString.concatenate(new JString[] {COLOR_YELLOW, JString.parseInt(amount), aClass100_594});
		} else if (amount >= 10000000) {
			return JString.concatenate(new JString[] {COLOR_SPRING_GREEN, JString.parseInt(amount / 1000000), LocalizedText.MILLION, aClass100_594});
		} else {
			return JString.concatenate(new JString[] {COLOR_WHITE, JString.parseInt(amount / 1000), LocalizedText.THOUSAND, aClass100_594});
		}
	}

    @OriginalMember(owner = "client!bd", name = "a", descriptor = "(BI)V")
    public static void clearInventory(@OriginalArg(1) int inventoryId) {
        @Pc(8) ClientInventory inventory = (ClientInventory) recentUse.get(inventoryId);
        if (inventory != null) {
            for (@Pc(24) int slotIndex = 0; slotIndex < inventory.invSlotObjId.length; slotIndex++) {
                inventory.invSlotObjId[slotIndex] = -1;
                inventory.invSlotObjCount[slotIndex] = 0;
            }
        }
    }

	@OriginalMember(owner = "runetek4.client!od", name = "a", descriptor = "(IZII)I")
	public static int getTotalParam(@OriginalArg(1) boolean multiplyByCount, @OriginalArg(2) int inventoryId, @OriginalArg(3) int paramId) {
		@Pc(19) ClientInventory inventory = (ClientInventory) recentUse.get((long) inventoryId);
		if (inventory == null) {
			return 0;
		}
		@Pc(27) int total = 0;
		for (@Pc(29) int slotIndex = 0; slotIndex < inventory.invSlotObjId.length; slotIndex++) {
			if (inventory.invSlotObjId[slotIndex] >= 0 && ObjTypeList.capacity > inventory.invSlotObjId[slotIndex]) {
				@Pc(56) ObjType objType = ObjTypeList.get(inventory.invSlotObjId[slotIndex]);
				if (objType.params != null) {
					@Pc(68) IntNode paramWrapper = (IntNode) objType.params.get((long) paramId);
					if (paramWrapper != null) {
						if (multiplyByCount) {
							total += inventory.invSlotObjCount[slotIndex] * paramWrapper.value;
						} else {
							total += paramWrapper.value;
						}
					}
				}
			}
		}
		return total;
	}
}