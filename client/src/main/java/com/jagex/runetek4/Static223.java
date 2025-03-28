package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static223 {

	@OriginalMember(owner = "runetek4.client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "()V")
	public static void method3858() {
		for (@Pc(1) int local1 = 0; local1 < SceneGraph.sceneryLen; local1++) {
			@Pc(8) Scenery local8 = SceneGraph.scenery[local1];
			SceneGraph.removeScenery(local8);
			SceneGraph.scenery[local1] = null;
		}
		SceneGraph.sceneryLen = 0;
	}

}
