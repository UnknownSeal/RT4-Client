package com.jagex.runetek4;

import java.nio.ByteBuffer;

import com.jagex.runetek4.cache.media.ImageRGB;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!cf")
public class GlSprite extends Sprite {

	@OriginalMember(owner = "runetek4.client!cf", name = "O", descriptor = "I")
	public int anInt1870;

	@OriginalMember(owner = "runetek4.client!cf", name = "cb", descriptor = "I")
	public int anInt1874;

	@OriginalMember(owner = "runetek4.client!cf", name = "db", descriptor = "I")
	private int anInt1875;

	@OriginalMember(owner = "runetek4.client!cf", name = "L", descriptor = "I")
	protected int anInt1869 = 0;

	@OriginalMember(owner = "runetek4.client!cf", name = "ab", descriptor = "I")
	public int anInt1872 = -1;

	@OriginalMember(owner = "runetek4.client!cf", name = "Z", descriptor = "I")
	private int anInt1871 = -1;

	@OriginalMember(owner = "runetek4.client!cf", name = "bb", descriptor = "I")
	private int anInt1873 = 0;

	@OriginalMember(owner = "runetek4.client!cf", name = "<init>", descriptor = "(IIIIII[I)V")
	public GlSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int[] arg6) {
		this.anInt1860 = arg0;
		this.anInt1866 = arg1;
		this.anInt1863 = arg2;
		this.anInt1861 = arg3;
		this.anInt1867 = arg4;
		this.anInt1859 = arg5;
		this.method1430(arg6);
		this.method1431();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "<init>", descriptor = "(Lclient!mm;)V")
	public GlSprite(@OriginalArg(0) ImageRGB arg0) {
		this.anInt1860 = arg0.anInt1860;
		this.anInt1866 = arg0.anInt1866;
		this.anInt1863 = arg0.anInt1863;
		this.anInt1861 = arg0.anInt1861;
		this.anInt1867 = arg0.anInt1867;
		this.anInt1859 = arg0.anInt1859;
		this.method1430(arg0.pixels);
		this.method1431();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "d", descriptor = "(I)V")
	private void method1424(@OriginalArg(0) int arg0) {
		if (this.anInt1873 == arg0) {
			return;
		}
		this.anInt1873 = arg0;
		@Pc(9) GL2 local9 = GlRenderer.gl;
		if (arg0 == 2) {
			local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		} else {
			local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
		}
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "(IILclient!cf;)V")
	public final void method1425(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) GlSprite arg2) {
		if (arg2 == null) {
			return;
		}
		GlRenderer.method4149();
		GlRenderer.setTextureId(arg2.anInt1872);
		arg2.method1424(1);
		@Pc(11) GL2 local11 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local11.glActiveTexture(GL2.GL_TEXTURE1);
		local11.glEnable(GL2.GL_TEXTURE_2D);
		local11.glBindTexture(GL2.GL_TEXTURE_2D, arg2.anInt1872);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_REPLACE);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_PREVIOUS);
		@Pc(47) float local47 = (float) (arg0 - Static46.anInt1439) / (float) arg2.anInt1870;
		@Pc(56) float local56 = (float) (arg1 - Static46.anInt1438) / (float) arg2.anInt1874;
		@Pc(68) float local68 = (float) (arg0 + this.anInt1867 - Static46.anInt1439) / (float) arg2.anInt1870;
		@Pc(80) float local80 = (float) (arg1 + this.anInt1859 - Static46.anInt1438) / (float) arg2.anInt1874;
		@Pc(85) int local85 = arg0 + this.anInt1863;
		@Pc(90) int local90 = arg1 + this.anInt1861;
		local11.glBegin(GL2.GL_TRIANGLE_FAN);
		local11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		@Pc(107) float local107 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(115) float local115 = (float) this.anInt1859 / (float) this.anInt1874;
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local68, local56);
		local11.glTexCoord2f(local107, 0.0F);
		local11.glVertex2f((float) (local85 + this.anInt1867), (float) (GlRenderer.canvasHeight - local90));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local47, local56);
		local11.glTexCoord2f(0.0F, 0.0F);
		local11.glVertex2f((float) local85, (float) (GlRenderer.canvasHeight - local90));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local47, local80);
		local11.glTexCoord2f(0.0F, local115);
		local11.glVertex2f((float) local85, (float) (GlRenderer.canvasHeight - local90 - this.anInt1859));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local68, local80);
		local11.glTexCoord2f(local107, local115);
		local11.glVertex2f((float) (local85 + this.anInt1867), (float) (GlRenderer.canvasHeight - local90 - this.anInt1859));
		local11.glEnd();
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_MODULATE);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_TEXTURE);
		local11.glDisable(GL2.GL_TEXTURE_2D);
		local11.glActiveTexture(GL2.GL_TEXTURE0);
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "c", descriptor = "(IIIII)V")
	public final void method1426(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		GlRenderer.method4155();
		@Pc(2) GL2 local2 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		@Pc(16) float local16 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(24) float local24 = (float) this.anInt1859 / (float) this.anInt1874;
		@Pc(29) float local29 = local16 * (float) arg3;
		@Pc(34) float local34 = local24 * (float) arg4;
		@Pc(39) int local39 = arg0 + this.anInt1863;
		@Pc(46) int local46 = local39 + this.anInt1867 * arg3;
		@Pc(53) int local53 = GlRenderer.canvasHeight - arg1 - this.anInt1861;
		@Pc(60) int local60 = local53 - this.anInt1859 * arg4;
		@Pc(65) float local65 = (float) arg2 / 256.0F;
		local2.glBegin(GL2.GL_TRIANGLE_FAN);
		local2.glColor4f(1.0F, 1.0F, 1.0F, local65);
		local2.glTexCoord2f(local29, 0.0F);
		local2.glVertex2f((float) local46, (float) local53);
		local2.glTexCoord2f(0.0F, 0.0F);
		local2.glVertex2f((float) local39, (float) local53);
		local2.glTexCoord2f(0.0F, local34);
		local2.glVertex2f((float) local39, (float) local60);
		local2.glTexCoord2f(local29, local34);
		local2.glVertex2f((float) local46, (float) local60);
		local2.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "(IIIIIIIILclient!cf;)V")
	public final void method1427(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) GlSprite arg8) {
		if (arg8 == null) {
			return;
		}
		GlRenderer.method4149();
		GlRenderer.setTextureId(arg8.anInt1872);
		arg8.method1424(1);
		@Pc(11) GL2 local11 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local11.glActiveTexture(GL2.GL_TEXTURE1);
		local11.glEnable(GL2.GL_TEXTURE_2D);
		local11.glBindTexture(GL2.GL_TEXTURE_2D, arg8.anInt1872);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_REPLACE);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_PREVIOUS);
		@Pc(43) int local43 = -arg2 / 2;
		@Pc(48) int local48 = -arg3 / 2;
		@Pc(51) int local51 = -local43;
		@Pc(54) int local54 = -local48;
		@Pc(63) int local63 = (int) (Math.sin((double) arg6 / 326.11D) * 65536.0D);
		@Pc(72) int local72 = (int) (Math.cos((double) arg6 / 326.11D) * 65536.0D);
		@Pc(78) int local78 = local63 * arg7 >> 8;
		@Pc(84) int local84 = local72 * arg7 >> 8;
		@Pc(96) int local96 = (arg4 << 16) + local48 * local78 + local43 * local84;
		@Pc(108) int local108 = (arg5 << 16) + (local48 * local84 - local43 * local78);
		@Pc(120) int local120 = (arg4 << 16) + local48 * local78 + local51 * local84;
		@Pc(132) int local132 = (arg5 << 16) + (local48 * local84 - local51 * local78);
		@Pc(144) int local144 = (arg4 << 16) + local54 * local78 + local43 * local84;
		@Pc(156) int local156 = (arg5 << 16) + (local54 * local84 - local43 * local78);
		@Pc(168) int local168 = (arg4 << 16) + local54 * local78 + local51 * local84;
		@Pc(180) int local180 = (arg5 << 16) + (local54 * local84 - local51 * local78);
		@Pc(188) float local188 = (float) arg8.anInt1867 / (float) arg8.anInt1870;
		@Pc(196) float local196 = (float) arg8.anInt1859 / (float) arg8.anInt1874;
		local11.glBegin(GL2.GL_TRIANGLE_FAN);
		local11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		@Pc(211) float local211 = (float) this.anInt1870 * 65536.0F;
		@Pc(217) float local217 = (float) (this.anInt1874 * 65536);
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local188, 0.0F);
		local11.glTexCoord2f((float) local120 / local211, (float) local132 / local217);
		local11.glVertex2f((float) (arg0 + arg2), (float) (GlRenderer.canvasHeight - arg1));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, 0.0F, 0.0F);
		local11.glTexCoord2f((float) local96 / local211, (float) local108 / local217);
		local11.glVertex2f((float) arg0, (float) (GlRenderer.canvasHeight - arg1));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, 0.0F, local196);
		local11.glTexCoord2f((float) local144 / local211, (float) local156 / local217);
		local11.glVertex2f((float) arg0, (float) (GlRenderer.canvasHeight - arg1 - arg3));
		local11.glMultiTexCoord2f(GL2.GL_TEXTURE1, local188, local196);
		local11.glTexCoord2f((float) local168 / local211, (float) local180 / local217);
		local11.glVertex2f((float) (arg0 + arg2), (float) (GlRenderer.canvasHeight - arg1 - arg3));
		local11.glEnd();
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_MODULATE);
		local11.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_TEXTURE);
		local11.glDisable(GL2.GL_TEXTURE_2D);
		local11.glActiveTexture(GL2.GL_TEXTURE0);
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "d", descriptor = "(II)V")
	@Override
	public final void method1421(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		GlRenderer.method4149();
		@Pc(5) int local5 = arg0 + this.anInt1863;
		@Pc(10) int local10 = arg1 + this.anInt1861;
		@Pc(12) GL2 local12 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local12.glTranslatef((float) local5, (float) (GlRenderer.canvasHeight - local10), 0.0F);
		@Pc(35) float local35 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(43) float local43 = (float) this.anInt1859 / (float) this.anInt1874;
		local12.glBegin(GL2.GL_TRIANGLE_FAN);
		local12.glTexCoord2f(0.0F, 0.0F);
		local12.glVertex2f((float) this.anInt1867, 0.0F);
		local12.glTexCoord2f(local35, 0.0F);
		local12.glVertex2f(0.0F, 0.0F);
		local12.glTexCoord2f(local35, local43);
		local12.glVertex2f(0.0F, (float) -this.anInt1859);
		local12.glTexCoord2f(0.0F, local43);
		local12.glVertex2f((float) this.anInt1867, (float) -this.anInt1859);
		local12.glEnd();
		local12.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "e", descriptor = "(II)V")
	@Override
	public final void drawSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		GlRenderer.method4149();
		@Pc(5) int local5 = arg0 + this.anInt1863;
		@Pc(10) int local10 = arg1 + this.anInt1861;
		@Pc(12) GL2 local12 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local12.glTranslatef((float) local5, (float) (GlRenderer.canvasHeight - local10), 0.0F);
		local12.glCallList(this.anInt1871);
		local12.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "b", descriptor = "(IIIIII)V")
	public final void method1428(@OriginalArg(2) int arg0, @OriginalArg(3) int arg1, @OriginalArg(4) int arg2) {
		GlRenderer.method4149();
		@Pc(2) GL2 local2 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(2);
		@Pc(15) int local15 = 240 - (this.anInt1863 << 4);
		@Pc(22) int local22 = 240 - (this.anInt1861 << 4);
		local2.glTranslatef((float) arg0 / 16.0F, (float) GlRenderer.canvasHeight - (float) arg1 / 16.0F, 0.0F);
		local2.glRotatef((float) -arg2 * 0.005493164F, 0.0F, 0.0F, 1.0F);
		local2.glTranslatef((float) -local15 / 16.0F, (float) local22 / 16.0F, 0.0F);
		local2.glCallList(this.anInt1871);
		local2.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "(IIII)V")
	@Override
	public final void method1419(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		GlRenderer.method4149();
		@Pc(8) int local8 = this.anInt1867;
		@Pc(11) int local11 = this.anInt1859;
		@Pc(13) int local13 = 0;
		@Pc(15) int local15 = 0;
		@Pc(18) int local18 = this.anInt1860;
		@Pc(21) int local21 = this.anInt1866;
		@Pc(27) int local27 = (local18 << 16) / arg2;
		@Pc(33) int local33 = (local21 << 16) / arg3;
		@Pc(47) int local47;
		if (this.anInt1863 > 0) {
			local47 = ((this.anInt1863 << 16) + local27 - 1) / local27;
			arg0 += local47;
			local13 = local47 * local27 - (this.anInt1863 << 16);
		}
		if (this.anInt1861 > 0) {
			local47 = ((this.anInt1861 << 16) + local33 - 1) / local33;
			arg1 += local47;
			local15 = local47 * local33 - (this.anInt1861 << 16);
		}
		if (local8 < local18) {
			arg2 = ((local8 << 16) + local27 - local13 - 1) / local27;
		}
		if (local11 < local21) {
			arg3 = ((local11 << 16) + local33 - local15 - 1) / local33;
		}
		@Pc(123) GL2 local123 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(2);
		@Pc(132) float local132 = (float) arg0;
		@Pc(137) float local137 = local132 + (float) arg2;
		@Pc(142) float local142 = (float) (GlRenderer.canvasHeight - arg1);
		@Pc(147) float local147 = local142 - (float) arg3;
		@Pc(155) float local155 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(163) float local163 = (float) this.anInt1859 / (float) this.anInt1874;
		local123.glBegin(GL2.GL_TRIANGLE_FAN);
		local123.glTexCoord2f(local155, 0.0F);
		local123.glVertex2f(local137, local142);
		local123.glTexCoord2f(0.0F, 0.0F);
		local123.glVertex2f(local132, local142);
		local123.glTexCoord2f(0.0F, local163);
		local123.glVertex2f(local132, local147);
		local123.glTexCoord2f(local155, local163);
		local123.glVertex2f(local137, local147);
		local123.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "c", descriptor = "(II)V")
	@Override
	public final void method1415(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		GlRenderer.method4149();
		@Pc(5) int local5 = arg0 + this.anInt1863;
		@Pc(10) int local10 = arg1 + this.anInt1861;
		@Pc(12) GL2 local12 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local12.glTranslatef((float) local5, (float) (GlRenderer.canvasHeight - local10), 0.0F);
		local12.glCallList(this.anInt1871);
		local12.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() throws Throwable {
		if (this.anInt1872 != -1) {
			Static63.method1491(this.anInt1872, this.anInt1869, this.anInt1875);
			this.anInt1872 = -1;
			this.anInt1869 = 0;
		}
		if (this.anInt1871 != -1) {
			Static63.method1486(this.anInt1871, this.anInt1875);
			this.anInt1871 = -1;
		}
		super.finalize();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "(IIIIII)V")
	@Override
	protected final void method1416(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		GlRenderer.method4149();
		@Pc(2) GL2 local2 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		@Pc(15) int local15 = arg0 - (this.anInt1863 << 4);
		@Pc(22) int local22 = arg1 - (this.anInt1861 << 4);
		local2.glTranslatef((float) arg2 / 16.0F, (float) GlRenderer.canvasHeight - (float) arg3 / 16.0F, 0.0F);
		local2.glRotatef((float) arg4 * 0.005493164F, 0.0F, 0.0F, 1.0F);
		if (arg5 != 4096) {
			local2.glScalef((float) arg5 / 4096.0F, (float) arg5 / 4096.0F, 0.0F);
		}
		local2.glTranslatef((float) -local15 / 16.0F, (float) local22 / 16.0F, 0.0F);
		local2.glCallList(this.anInt1871);
		local2.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "b", descriptor = "(IIIII)V")
	@Override
	public final void method1422(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		GlRenderer.method4155();
		@Pc(8) int local8 = this.anInt1867;
		@Pc(11) int local11 = this.anInt1859;
		@Pc(13) int local13 = 0;
		@Pc(15) int local15 = 0;
		@Pc(18) int local18 = this.anInt1860;
		@Pc(21) int local21 = this.anInt1866;
		@Pc(27) int local27 = (local18 << 16) / arg2;
		@Pc(33) int local33 = (local21 << 16) / arg3;
		@Pc(47) int local47;
		if (this.anInt1863 > 0) {
			local47 = ((this.anInt1863 << 16) + local27 - 1) / local27;
			arg0 += local47;
			local13 = local47 * local27 - (this.anInt1863 << 16);
		}
		if (this.anInt1861 > 0) {
			local47 = ((this.anInt1861 << 16) + local33 - 1) / local33;
			arg1 += local47;
			local15 = local47 * local33 - (this.anInt1861 << 16);
		}
		if (local8 < local18) {
			arg2 = ((local8 << 16) + local27 - local13 - 1) / local27;
		}
		if (local11 < local21) {
			arg3 = ((local11 << 16) + local33 - local15 - 1) / local33;
		}
		@Pc(123) GL2 local123 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		@Pc(132) float local132 = (float) arg0;
		@Pc(137) float local137 = local132 + (float) arg2;
		@Pc(142) float local142 = (float) (GlRenderer.canvasHeight - arg1);
		@Pc(147) float local147 = local142 - (float) arg3;
		@Pc(155) float local155 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(163) float local163 = (float) this.anInt1859 / (float) this.anInt1874;
		@Pc(168) float local168 = (float) arg4 / 256.0F;
		local123.glBegin(GL2.GL_TRIANGLE_FAN);
		local123.glColor4f(1.0F, 1.0F, 1.0F, local168);
		local123.glTexCoord2f(local155, 0.0F);
		local123.glVertex2f(local137, local142);
		local123.glTexCoord2f(0.0F, 0.0F);
		local123.glVertex2f(local132, local142);
		local123.glTexCoord2f(0.0F, local163);
		local123.glVertex2f(local132, local147);
		local123.glTexCoord2f(local155, local163);
		local123.glVertex2f(local137, local147);
		local123.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "b", descriptor = "(IIII)V")
	public final void method1429(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		GlRenderer.method4149();
		@Pc(2) GL2 local2 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		@Pc(16) float local16 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(24) float local24 = (float) this.anInt1859 / (float) this.anInt1874;
		@Pc(29) float local29 = local16 * (float) arg2;
		@Pc(34) float local34 = local24 * (float) arg3;
		@Pc(39) int local39 = arg0 + this.anInt1863;
		@Pc(46) int local46 = local39 + this.anInt1867 * arg2;
		@Pc(53) int local53 = GlRenderer.canvasHeight - arg1 - this.anInt1861;
		@Pc(60) int local60 = local53 - this.anInt1859 * arg3;
		local2.glBegin(GL2.GL_TRIANGLE_FAN);
		local2.glTexCoord2f(local29, 0.0F);
		local2.glVertex2f((float) local46, (float) local53);
		local2.glTexCoord2f(0.0F, 0.0F);
		local2.glVertex2f((float) local39, (float) local53);
		local2.glTexCoord2f(0.0F, local34);
		local2.glVertex2f((float) local39, (float) local60);
		local2.glTexCoord2f(local29, local34);
		local2.glVertex2f((float) local46, (float) local60);
		local2.glEnd();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "([I)V")
	protected void method1430(@OriginalArg(0) int[] arg0) {
		this.anInt1870 = Static165.bitceil(this.anInt1867);
		this.anInt1874 = Static165.bitceil(this.anInt1859);
		@Pc(20) byte[] local20 = new byte[this.anInt1870 * this.anInt1874 * 4];
		@Pc(22) int local22 = 0;
		@Pc(24) int local24 = 0;
		@Pc(32) int local32 = (this.anInt1870 - this.anInt1867) * 4;
		for (@Pc(34) int local34 = 0; local34 < this.anInt1859; local34++) {
			for (@Pc(40) int local40 = 0; local40 < this.anInt1867; local40++) {
				@Pc(49) int local49 = arg0[local24++];
				if (local49 == 0) {
					local22 += 4;
				} else {
					local20[local22++] = (byte) (local49 >> 16);
					local20[local22++] = (byte) (local49 >> 8);
					local20[local22++] = (byte) local49;
					local20[local22++] = -1;
				}
			}
			local22 += local32;
		}
		@Pc(91) ByteBuffer local91 = ByteBuffer.wrap(local20);
		@Pc(93) GL2 local93 = GlRenderer.gl;
		if (this.anInt1872 == -1) {
			@Pc(100) int[] local100 = new int[1];
			local93.glGenTextures(1, local100, 0);
			this.anInt1872 = local100[0];
			this.anInt1875 = Static63.anInt1943;
		}
		GlRenderer.setTextureId(this.anInt1872);
		local93.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, this.anInt1870, this.anInt1874, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, local91);
		Static63.oncard_2d += local91.limit() - this.anInt1869;
		this.anInt1869 = local91.limit();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "(III)V")
	@Override
	public final void method1417(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		GlRenderer.method4155();
		@Pc(5) int local5 = arg0 + this.anInt1863;
		@Pc(10) int local10 = arg1 + this.anInt1861;
		@Pc(12) GL2 local12 = GlRenderer.gl;
		GlRenderer.setTextureId(this.anInt1872);
		this.method1424(1);
		local12.glColor4f(1.0F, 1.0F, 1.0F, (float) arg2 / 256.0F);
		local12.glTranslatef((float) local5, (float) (GlRenderer.canvasHeight - local10), 0.0F);
		local12.glCallList(this.anInt1871);
		local12.glLoadIdentity();
	}

	@OriginalMember(owner = "runetek4.client!cf", name = "a", descriptor = "()V")
	private void method1431() {
		@Pc(7) float local7 = (float) this.anInt1867 / (float) this.anInt1870;
		@Pc(15) float local15 = (float) this.anInt1859 / (float) this.anInt1874;
		@Pc(17) GL2 local17 = GlRenderer.gl;
		if (this.anInt1871 == -1) {
			this.anInt1871 = local17.glGenLists(1);
			this.anInt1875 = Static63.anInt1943;
		}
		local17.glNewList(this.anInt1871, GL2.GL_COMPILE);
		local17.glBegin(GL2.GL_TRIANGLE_FAN);
		local17.glTexCoord2f(local7, 0.0F);
		local17.glVertex2f((float) this.anInt1867, 0.0F);
		local17.glTexCoord2f(0.0F, 0.0F);
		local17.glVertex2f(0.0F, 0.0F);
		local17.glTexCoord2f(0.0F, local15);
		local17.glVertex2f(0.0F, (float) -this.anInt1859);
		local17.glTexCoord2f(local7, local15);
		local17.glVertex2f((float) this.anInt1867, (float) -this.anInt1859);
		local17.glEnd();
		local17.glEndList();
	}
}
