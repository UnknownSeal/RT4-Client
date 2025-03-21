package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.CollisionMap;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.Scene;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static43 {

	@OriginalMember(owner = "client!dg", name = "a", descriptor = "(IIIIILclient!th;IJZ)Z")
	public static boolean addTemporary(@OriginalArg(0) int arg0, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int arg3, @OriginalArg(4) int padding, @OriginalArg(5) Entity arg5, @OriginalArg(6) int yaw, @OriginalArg(7) long arg7, @OriginalArg(8) boolean arg8) {
		if (arg5 == null) {
			return true;
		}
		@Pc(7) int x0 = x - padding;
		@Pc(11) int z0 = z - padding;
		@Pc(15) int x1 = x + padding;
		@Pc(19) int z1 = z + padding;
		if (arg8) {
			if (yaw > 640 && yaw < 1408) {
				z1 += 128;
			}
			if (yaw > 1152 && yaw < 1920) {
				x1 += 128;
			}
			if (yaw > 1664 || yaw < 384) {
				z0 -= 128;
			}
			if (yaw > 128 && yaw < 896) {
				x0 -= 128;
			}
		}
		x0 /= 128;
		z0 /= 128;
		x1 /= 128;
		z1 /= 128;
		return Static105.addLoc(arg0, x0, z0, x1 + 1 - x0, z1 - z0 + 1, x, z, arg3, arg5, yaw, true, arg7);
	}

	@OriginalMember(owner = "client!dg", name = "a", descriptor = "(IIIIIILclient!mj;)V")
	public static void method1144(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) CollisionMap arg5) {
		@Pc(9) long local9 = 0L;
		if (arg3 == 0) {
			local9 = SceneGraph.getWallKey(arg2, arg1, arg0);
		} else if (arg3 == 1) {
			local9 = SceneGraph.getWallDecorKey(arg2, arg1, arg0);
		} else if (arg3 == 2) {
			local9 = SceneGraph.getSceneryKey(arg2, arg1, arg0);
		} else if (arg3 == 3) {
			local9 = SceneGraph.getGroundDecorKey(arg2, arg1, arg0);
		}
		@Pc(57) int local57 = (int) local9 >> 14 & 0x1F;
		@Pc(70) int local70 = (int) (local9 >>> 32) & Integer.MAX_VALUE;
		@Pc(74) LocType local74 = LocTypeList.get(local70);
		if (local74.hasBackgroundSound()) {
			AreaSoundManager.remove(arg1, local74, arg0, arg2);
		}
		@Pc(92) int local92 = (int) local9 >> 20 & 0x3;
		if (local9 == 0L) {
			return;
		}
		@Pc(100) Entity local100 = null;
		@Pc(102) Entity local102 = null;
		if (arg3 == 0) {
			@Pc(110) Wall local110 = Static110.method2276(arg2, arg1, arg0);
			if (local110 != null) {
				local100 = local110.primary;
				local102 = local110.modelB;
			}
			if (local74.blockWalk != 0) {
				arg5.unflagWall(local92, local74.blockRange, arg0, local57, arg1);
			}
		} else if (arg3 == 1) {
			@Pc(233) WallDecor local233 = Static75.method1633(arg2, arg1, arg0);
			if (local233 != null) {
				local100 = local233.primary;
				local102 = local233.secondary;
			}
		} else if (arg3 == 2) {
			@Pc(148) Scenery local148 = Scene.getObject(arg2, arg1, arg0);
			if (local148 != null) {
				local100 = local148.entity;
			}
			if (local74.blockWalk != 0 && local74.width + arg1 < 104 && local74.width + arg0 < 104 && arg1 + local74.length < 104 && arg0 + local74.length < 104) {
				arg5.unflagScenery(arg1, local74.width, local74.blockRange, local92, local74.length, arg0);
			}
		} else if (arg3 == 3) {
			@Pc(211) GroundDecor local211 = Static267.method4526(arg2, arg1, arg0);
			if (local211 != null) {
				local100 = local211.entity;
			}
			if (local74.blockWalk == 1) {
				arg5.unflagGroundDecor(arg0, arg1);
			}
		}
		if (!GlRenderer.enabled || !local74.castShadow) {
			return;
		}
		if (local57 == 2) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, 0, local92 + 4, 0, local57, arg1, arg0, arg4);
			}
			if (local102 instanceof Loc) {
				((Loc) local102).method1046();
			} else {
				Loc.method181(local74, 0, local92 + 1 & 0x3, 0, local57, arg1, arg0, arg4);
			}
		} else if (local57 == 5) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, Static238.WALL_DECORATION_ROTATION_FORWARD_Z[local92] * 8, local92, Static34.WALL_DECORATION_ROTATION_FORWARD_X[local92] * 8, 4, arg1, arg0, arg4);
			}
		} else if (local57 == 6) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, Static64.anIntArray154[local92] * 8, local92 + 4, Static114.anIntArray565[local92] * 8, 4, arg1, arg0, arg4);
			}
		} else if (local57 == 7) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, 0, (local92 + 2 & 0x3) + 4, 0, 4, arg1, arg0, arg4);
			}
		} else if (local57 == 8) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, Static64.anIntArray154[local92] * 8, local92 + 4, Static114.anIntArray565[local92] * 8, 4, arg1, arg0, arg4);
			}
			if (local102 instanceof Loc) {
				((Loc) local102).method1046();
			} else {
				Loc.method181(local74, Static64.anIntArray154[local92] * 8, (local92 + 2 & 0x3) + 4, Static114.anIntArray565[local92] * 8, 4, arg1, arg0, arg4);
			}
		} else if (local57 == 11) {
			if (local100 instanceof Loc) {
				((Loc) local100).method1046();
			} else {
				Loc.method181(local74, 0, local92 + 4, 0, 10, arg1, arg0, arg4);
			}
		} else if (local100 instanceof Loc) {
			((Loc) local100).method1046();
		} else {
			Loc.method181(local74, 0, local92, 0, local57, arg1, arg0, arg4);
		}
	}
}
