package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.nio.FloatBuffer;

@OriginalClass("runetek4.client!ga")
public final class ParticleSystem extends ParticleNode {

	static {
		new SecondaryHashTable(8);
		new Packet(131056);
	}

	@OriginalMember(owner = "runetek4.client!ga", name = "a", descriptor = "()V")
	public static void load() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		if (local1.isExtensionAvailable("GL_ARB_point_parameters")) {
			@Pc(20) float[] local20 = new float[] { 1.0F, 0.0F, 5.0E-4F };
			local1.glPointParameterfv(GL2.GL_POINT_DISTANCE_ATTENUATION, local20, 0);
			@Pc(28) FloatBuffer local28 = FloatBuffer.allocate(1);
			local1.glGetFloatv(GL2.GL_POINT_SIZE_MAX, local28);
			@Pc(36) float local36 = local28.get(0);
			if (local36 > 1024.0F) {
				local36 = 1024.0F;
			}
			local1.glPointParameterf(GL2.GL_POINT_SIZE_MIN, 1.0F);
			local1.glPointParameterf(GL2.GL_POINT_SIZE_MAX, local36);
		}
		if (local1.isExtensionAvailable("GL_ARB_point_sprite")) {
		}
	}

	@OriginalMember(owner = "runetek4.client!ga", name = "d", descriptor = "()V")
	public final void method1646() {
	}
}
