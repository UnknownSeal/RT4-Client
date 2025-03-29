package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static123 {

	@OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "(BI)Lclient!lk;")
	public static StructType method2417(@OriginalArg(1) int arg0) {
		@Pc(10) StructType local10 = (StructType) PreciseSleep.aClass54_13.get((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(26) byte[] local26 = Static23.aClass153_11.getfile(26, arg0);
		local10 = new StructType();
		if (local26 != null) {
			local10.decode(new Packet(local26));
		}
		PreciseSleep.aClass54_13.put(local10, (long) arg0);
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "(B)[F")
	public static float[] method2422() {
		@Pc(3) float local3 = FogManager.getLightingModelAmbient() + FogManager.getLight0Diffuse();
		@Pc(9) int local9 = FogManager.getLightColor();
		@Pc(18) float local18 = (float) (local9 >> 16 & 0xFF) / 255.0F;
		ColorUtils.aFloatArray28[3] = 1.0F;
		@Pc(37) float local37 = (float) (local9 >> 8 & 0xFF) / 255.0F;
		@Pc(39) float local39 = 0.58823526F;
		@Pc(46) float local46 = (float) (local9 & 0xFF) / 255.0F;
		ColorUtils.aFloatArray28[2] = Static257.aFloatArray2[2] * local46 * local39 * local3;
		ColorUtils.aFloatArray28[0] = Static257.aFloatArray2[0] * local18 * local39 * local3;
		ColorUtils.aFloatArray28[1] = local3 * local39 * local37 * Static257.aFloatArray2[1];
		return ColorUtils.aFloatArray28;
	}

}
