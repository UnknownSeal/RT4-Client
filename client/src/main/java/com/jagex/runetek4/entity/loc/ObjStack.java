package com.jagex.runetek4.entity.loc;

import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.entity.entity.Entity;
import com.jagex.runetek4.graphics.model.Model;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!uj")
public final class ObjStack extends Entity {

	@OriginalMember(owner = "runetek4.client!uj", name = "v", descriptor = "I")
	public int count;

	@OriginalMember(owner = "runetek4.client!uj", name = "D", descriptor = "I")
	public int id;

	@OriginalMember(owner = "runetek4.client!uj", name = "z", descriptor = "I")
	private int minY = -32768;

	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void render(@OriginalArg(0) int worldX, @OriginalArg(1) int worldY, @OriginalArg(2) int worldZ, @OriginalArg(3) int rotationX, @OriginalArg(4) int rotationY, @OriginalArg(5) int rotationZ, @OriginalArg(6) int scaleX, @OriginalArg(7) int scaleY, @OriginalArg(8) long flags, @OriginalArg(9) int lightLevel, @OriginalArg(10) ParticleSystem particleSystem) {
		@Pc(16) Model model = ObjTypeList.get(this.id).getModel(-1, 0, null, this.count, 0);
		if (model != null) {
			model.render(worldX, worldY, worldZ, rotationX, rotationY, rotationZ, scaleX, scaleY, flags, lightLevel, particleSystem);
			this.minY = model.getMinY();
		}
	}

	@OriginalMember(owner = "runetek4.client!uj", name = "b", descriptor = "()I")
	@Override
	public final int getMinY() {
		return this.minY;
	}
}
