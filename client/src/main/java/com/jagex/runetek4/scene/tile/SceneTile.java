package com.jagex.runetek4.scene.tile;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.dash3d.entity.ObjStackEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!bj")
public final class SceneTile extends Node {

	@OriginalMember(owner = "runetek4.client!bj", name = "x", descriptor = "Z")
	public boolean aBoolean45;

	@OriginalMember(owner = "runetek4.client!bj", name = "y", descriptor = "I")
	public int entityCount;

	@OriginalMember(owner = "runetek4.client!bj", name = "A", descriptor = "Z")
	public boolean aBoolean46;

	@OriginalMember(owner = "runetek4.client!bj", name = "B", descriptor = "Lclient!fg;")
	public ComplexTile shapedTile;

	@OriginalMember(owner = "runetek4.client!bj", name = "C", descriptor = "I")
	public int checkLocSpans;

	@OriginalMember(owner = "runetek4.client!bj", name = "E", descriptor = "I")
	public int blockLocSpans;

	@OriginalMember(owner = "runetek4.client!bj", name = "G", descriptor = "Lclient!bm;")
	public GroundDecor groundDecor;

	@OriginalMember(owner = "runetek4.client!bj", name = "I", descriptor = "I")
	public int inverseBlockLocSpans;

	@OriginalMember(owner = "runetek4.client!bj", name = "J", descriptor = "Lclient!df;")
	public WallDecor wallDecor;

	@OriginalMember(owner = "runetek4.client!bj", name = "K", descriptor = "Lclient!jh;")
	public Wall wall;

	@OriginalMember(owner = "runetek4.client!bj", name = "M", descriptor = "Lclient!bj;")
	public SceneTile aClass3_Sub5_1;

	@OriginalMember(owner = "runetek4.client!bj", name = "N", descriptor = "Z")
	public boolean containsLocs;

	@OriginalMember(owner = "runetek4.client!bj", name = "S", descriptor = "Lclient!rh;")
	public GenericTile plainTile;

	@OriginalMember(owner = "runetek4.client!bj", name = "T", descriptor = "I")
	public int backWallTypes;

	@OriginalMember(owner = "runetek4.client!bj", name = "X", descriptor = "Lclient!jj;")
	public ObjStackEntity aClass79_1;

	@OriginalMember(owner = "runetek4.client!bj", name = "D", descriptor = "I")
	public int locSpans = 0;

	@OriginalMember(owner = "runetek4.client!bj", name = "w", descriptor = "[Lclient!ec;")
	public final Scenery[] sceneries = new Scenery[5];

	@OriginalMember(owner = "runetek4.client!bj", name = "P", descriptor = "[I")
	public final int[] anIntArray59 = new int[5];

	@OriginalMember(owner = "runetek4.client!bj", name = "H", descriptor = "I")
	public final int anInt666;

	@OriginalMember(owner = "runetek4.client!bj", name = "W", descriptor = "I")
	public int anInt672;

	@OriginalMember(owner = "runetek4.client!bj", name = "Q", descriptor = "I")
	public final int anInt668;

	@OriginalMember(owner = "runetek4.client!bj", name = "R", descriptor = "I")
	public final int anInt669;

	@OriginalMember(owner = "runetek4.client!bj", name = "<init>", descriptor = "(III)V")
	public SceneTile(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.anInt666 = arg2;
		this.anInt668 = this.anInt672 = arg0;
		this.anInt669 = arg1;
	}
}
