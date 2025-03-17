package com.jagex.runetek4;

import java.nio.IntBuffer;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static46 {

	@OriginalMember(owner = "runetek4.client!dj", name = "a", descriptor = "Lclient!cf;")
	public static GlSprite aClass3_Sub2_Sub1_Sub2_1 = null;

	@OriginalMember(owner = "runetek4.client!dj", name = "c", descriptor = "I")
	public static int anInt1439 = 0;

	@OriginalMember(owner = "runetek4.client!dj", name = "a", descriptor = "()V")
	public static void method1173() {
		aClass3_Sub2_Sub1_Sub2_1 = null;
	}

	@OriginalMember(owner = "runetek4.client!dj", name = "a", descriptor = "(IIII)V")
	public static void method1174(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		GlRenderer.method4162();
		@Pc(5) float local5 = (float) arg0 + 0.3F;
		@Pc(10) float local10 = local5 + (float) arg2;
		@Pc(18) float local18 = (float) GlRenderer.canvasHeight - (float) arg1 - 0.3F;
		@Pc(20) GL2 local20 = GlRenderer.gl;
		local20.glBegin(GL2.GL_LINES);
		local20.glColor3ub((byte) (arg3 >> 16), (byte) (arg3 >> 8), (byte) arg3);
		local20.glVertex2f(local5, local18);
		local20.glVertex2f(local10, local18);
		local20.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!dj", name = "b", descriptor = "(IIII)V")
	public static void method1176(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		GlRenderer.method4162();
		@Pc(5) float local5 = (float) arg0 + 0.3F;
		@Pc(13) float local13 = (float) GlRenderer.canvasHeight - (float) arg1 - 0.3F;
		@Pc(18) float local18 = local13 - (float) arg2;
		@Pc(20) GL2 local20 = GlRenderer.gl;
		local20.glBegin(GL2.GL_LINES);
		local20.glColor3ub((byte) (arg3 >> 16), (byte) (arg3 >> 8), (byte) arg3);
		local20.glVertex2f(local5, local13);
		local20.glVertex2f(local5, local18);
		local20.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!dj", name = "a", descriptor = "([IIIII)V")
	public static void method1178(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		GlRenderer.method4162();
		@Pc(2) GL2 local2 = GlRenderer.gl;
		local2.glRasterPos2i(arg1, GlRenderer.canvasHeight - arg2);
		local2.glPixelZoom(1.0F, -1.0F);
		local2.glDisable(GL2.GL_BLEND);
		local2.glDisable(GL2.GL_ALPHA_TEST);
		local2.glDrawPixels(arg3, arg4, GL2.GL_BGRA, GlRenderer.bigEndian ? GL2.GL_UNSIGNED_INT_8_8_8_8_REV : GL2.GL_UNSIGNED_BYTE, IntBuffer.wrap(arg0));
		local2.glEnable(GL2.GL_ALPHA_TEST);
		local2.glEnable(GL2.GL_BLEND);
		local2.glPixelZoom(1.0F, 1.0F);
	}

	@OriginalMember(owner = "runetek4.client!dj", name = "d", descriptor = "()V")
	public static void method1184() {
		GlRenderer.gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
	}

	@OriginalMember(owner = "runetek4.client!dj", name = "a", descriptor = "(Lclient!cf;)V")
	public static void method1188(@OriginalArg(0) GlSprite arg0) {
		if (arg0.height != GlRaster.clipBottom - GlRaster.clipTop) {
			throw new IllegalArgumentException();
		}
		aClass3_Sub2_Sub1_Sub2_1 = arg0;
	}
}
