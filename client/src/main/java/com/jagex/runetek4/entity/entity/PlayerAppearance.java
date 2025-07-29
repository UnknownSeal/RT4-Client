package com.jagex.runetek4.entity.entity;

import com.jagex.runetek4.graphics.animation.AnimFrameset;
import com.jagex.runetek4.config.types.idk.IDKTypeList;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.types.bas.BasType;
import com.jagex.runetek4.config.types.bas.BasTypeList;
import com.jagex.runetek4.config.types.idk.IDKType;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.graphics.gl.GlModel;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.graphics.model.RawModel;
import com.jagex.runetek4.core.node.SoftLruHashTable;
import com.jagex.runetek4.util.math.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hh")
public final class PlayerAppearance {

	@OriginalMember(owner = "runetek4.client!kh", name = "g", descriptor = "Lclient!hh;")
	public static final PlayerAppearance DEFAULT = new PlayerAppearance();

	@OriginalMember(owner = "runetek4.client!q", name = "d", descriptor = "[[S")
	public static final short[][] GAME1_DESTINATION_BODY_COLORS = new short[][] { { 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898 }, { 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10347, 10582, 10429, 10407, 10359, 8414, 9540, 10456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898 }, { 4300, 3294, 3303, 3264, 4506, 4382, 4387, 5293, 7622, 7384, 8412, 7496, 86, 123, 111, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 13766, 13745, 13726, 13890, 13743, 13852, 17602, 18605, 21660, 24000, 24997, 24088, 27972, 25903, 26904, 27193, 27175, 27156, 30020, 28975, 29976, 12482, 13485, 10392, 10692, 10669, 10776, 6717, 6695, 7830, 6971, 6951, 5910, 3389, 3369, 3356, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898, 10, 30, 50, 70, 90, 110, 310, 684, 704, 556, 940, 960, 6454, 6952, 6972, 2358, 2732, 2752, 10550, 10924, 10944, 10310, 10556, 10576, 14646, 15020, 15040, 19766, 20140, 20160, -29386, -29012, -28992, 31030, 31276, 31296, -24266, -23892, -23872, -19146, -18772, -18752, -14026, -13652, -13632, -6858, -6484, -6464, 522, 542, 6794, 6814, 11018, 11038, 14986, 15006, 21130, 21150, -28918, -28898, -22006, -21986, -12918, -12898 } };

	@OriginalMember(owner = "runetek4.client!ci", name = "u", descriptor = "[[S")
	public static final short[][] GAME1_DESTINATION_SKIN_COLORS = new short[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 24, 44, 64, 84, 104, 304, 678, 698, 550, 934, 954, 6448, 6946, 6966, 2352, 2726, 2746, 10544, 10918, 10938, 10304, 10550, 10570, 14640, 15014, 15034, 19760, 20134, 20154, -29392, -29018, -28998, 31024, 31270, 31290, -24272, -23898, -23878, -19152, -18778, -18758, -14032, -13658, -13638, -6864, -6490, -6470, 516, 536, 6788, 6808, 11012, 11032, 14980, 15000, 21124, 21144, -28924, -28904, -22012, -21992, -12924, -12904 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10339, 10574, 10425, 10398, 10345, 7512, 8507, 7378, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 24, 44, 64, 84, 104, 304, 678, 698, 550, 934, 954, 6448, 6946, 6966, 2352, 2726, 2746, 10544, 10918, 10938, 10304, 10550, 10570, 14640, 15014, 15034, 19760, 20134, 20154, -29392, -29018, -28998, 31024, 31270, 31290, -24272, -23898, -23878, -19152, -18778, -18758, -14032, -13658, -13638, -6864, -6490, -6470, 516, 536, 6788, 6808, 11012, 11032, 14980, 15000, 21124, 21144, -28924, -28904, -22012, -21992, -12924, -12904 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13753, 13737, 13719, 13883, 13863, 13974, 19643, 18601, 16532, 23993, 25121, 24980, 26944, 26921, 24854, 27191, 27171, 26130, 26941, 28696, 30100, 12477, 10407, 10388, 10685, 10665, 10646, 6711, 6693, 6674, 6965, 7073, 7056, 2361, 4387, 3346, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 24, 44, 64, 84, 104, 304, 678, 698, 550, 934, 954, 6448, 6946, 6966, 2352, 2726, 2746, 10544, 10918, 10938, 10304, 10550, 10570, 14640, 15014, 15034, 19760, 20134, 20154, -29392, -29018, -28998, 31024, 31270, 31290, -24272, -23898, -23878, -19152, -18778, -18758, -14032, -13658, -13638, -6864, -6490, -6470, 516, 536, 6788, 6808, 11012, 11032, 14980, 15000, 21124, 21144, -28924, -28904, -22012, -21992, -12924, -12904 } };

	@OriginalMember(owner = "runetek4.client!aa", name = "h", descriptor = "[S")
	public static final short[] GAME1_SOURCE_BODY_COLORS = new short[] { 960, 957, -21568, -21571, 22464 };

	@OriginalMember(owner = "client!bj", name = "t", descriptor = "[S")
	public static final short[] GAME1_SOURCE_SKIN_COLORS = new short[] { -4160, -4163, -8256, -8259, 22461 };

	@OriginalMember(owner = "runetek4.client!md", name = "V", descriptor = "[[S")
	public static final short[][] GAME0_DESTINATION_BODY_COLORS = new short[][] { { 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, -31839, 22433, 2983, -11343, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 8741, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 25239, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 25238, 8742, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };

	@OriginalMember(owner = "runetek4.client!pk", name = "V", descriptor = "[S")
	public static final short[] GAME0_SOURCE_SKIN_COLORS = new short[] { -10304, 9104, -1, -1, -1 };

	@OriginalMember(owner = "runetek4.client!dg", name = "c", descriptor = "[[S")
	public static final short[][] GAME0_DESTINATION_SKIN_COLORS = new short[][] { { 6554, 115, 10304, 28, 5702, 7756, 5681, 4510, -31835, 22437, 2859, -11339, 16, 5157, 10446, 3658, -27314, -21965, 472, 580, 784, 21966, 28950, -15697, -14002 }, { 9104, 10275, 7595, 3610, 7975, 8526, 918, -26734, 24466, 10145, -6882, 5027, 1457, 16565, -30545, 25486, 24, 5392, 10429, 3673, -27335, -21957, 192, 687, 412, 21821, 28835, -15460, -14019 }, new short[0], new short[0], new short[0] };

	@OriginalMember(owner = "runetek4.client!vd", name = "B", descriptor = "[S")
	public static final short[] GAME0_SOURCE_BODY_COLORS = new short[] { 6798, 8741, 25238, 4626, 4550 };
	@OriginalMember(owner = "runetek4.client!wd", name = "d", descriptor = "[I")
	public static final int[] BASE_PART_MAP = new int[] { 8, 11, 4, 6, 9, 7, 10, 0 };
	@OriginalMember(owner = "runetek4.client!r", name = "b", descriptor = "[Lclient!tk;")
	public static final SeqType[] layerSequences = new SeqType[14];
	@OriginalMember(owner = "runetek4.client!ri", name = "b", descriptor = "[I")
	public static final int[] tweenFrameIds = new int[14];
	@OriginalMember(owner = "runetek4.client!uf", name = "j", descriptor = "Lclient!n;")
	public static final SoftLruHashTable bodyModels = new SoftLruHashTable(260);
	@OriginalMember(owner = "runetek4.client!l", name = "b", descriptor = "Lclient!n;")
	public static final SoftLruHashTable headModels = new SoftLruHashTable(5);
	@OriginalMember(owner = "runetek4.client!qi", name = "x", descriptor = "[I")
	public static final int[] MALE_FEATURES = new int[] { 0, 1, 2, 3, 4, 5, 6, 14 };
	@OriginalMember(owner = "runetek4.client!mc", name = "ab", descriptor = "[I")
	public static final int[] FEMALE_FEATURES = new int[] { 7, 8, 9, 10, 11, 12, 13, 15 };
	@OriginalMember(owner = "runetek4.client!wh", name = "j", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] primaryFramesets = new AnimFrameset[14];
	@OriginalMember(owner = "runetek4.client!vf", name = "g", descriptor = "[I")
	public static final int[] frameDelays = new int[14];
	@OriginalMember(owner = "runetek4.client!kf", name = "d", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] tweenFramesets = new AnimFrameset[14];
	@OriginalMember(owner = "client!fm", name = "fb", descriptor = "[I")
	public static final int[] frameTimes = new int[14];
	@OriginalMember(owner = "runetek4.client!th", name = "i", descriptor = "[I")
	public static final int[] primaryFrameIds = new int[14];

	@OriginalMember(owner = "runetek4.client!cj", name = "e", descriptor = "[[S")
	public static short[][] destinationBodyColors;

	@OriginalMember(owner = "runetek4.client!nj", name = "m", descriptor = "[[S")
	public static short[][] destinationSkinColors;
	@OriginalMember(owner = "runetek4.client!qe", name = "r", descriptor = "[S")
	public static short[] sourceBodyColors;
	@OriginalMember(owner = "runetek4.client!mj", name = "C", descriptor = "[S")
	public static short[] sourceSkinColors;

	@OriginalMember(owner = "client!hh", name = "e", descriptor = "I")
	public int npcId;

	@OriginalMember(owner = "client!hh", name = "i", descriptor = "J")
	private long prevChecksum;

	@OriginalMember(owner = "client!hh", name = "l", descriptor = "[I")
	private int[] identikit;

	@OriginalMember(owner = "client!hh", name = "m", descriptor = "I")
	private int basId;

	@OriginalMember(owner = "client!hh", name = "q", descriptor = "J")
	private long checksum;

	@OriginalMember(owner = "client!hh", name = "t", descriptor = "[I")
	private int[] colors;

	@OriginalMember(owner = "client!hh", name = "w", descriptor = "[[I")
	private int[][] transformationMatrices;

	@OriginalMember(owner = "client!hh", name = "x", descriptor = "Z")
	public boolean gender;

	@OriginalMember(owner = "runetek4.client!q", name = "a", descriptor = "(B)I")
	public static int getModelCacheSize() {
		return bodyModels.size();
	}

	@OriginalMember(owner = "runetek4.client!wk", name = "b", descriptor = "(II)V")
	public static void clean() {
		bodyModels.clean(5);
		headModels.clean(5);
	}

	@OriginalMember(owner = "runetek4.client!ph", name = "b", descriptor = "(B)V")
	public static void removeSoft() {
		bodyModels.removeSoft();
		headModels.removeSoft();
	}

    @OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(I)V")
    public static void clear() {
        bodyModels.clean();
        headModels.clean();
    }

    @OriginalMember(owner = "client!hh", name = "a", descriptor = "(IIIILclient!tk;III)Lclient!ak;")
	public final Model createHeadModel(@OriginalArg(0) int animFrame, @OriginalArg(1) int hairId, @OriginalArg(2) int faceId, @OriginalArg(3) int animFrame2, @OriginalArg(4) SeqType sequence, @OriginalArg(5) int animLoop, @OriginalArg(6) int beardId) {
		@Pc(24) long cacheKey = (long) faceId | (long) (beardId << 16) | (long) hairId << 32;
		@Pc(30) Model model = (Model) headModels.get(cacheKey);
		if (model == null) {
			@Pc(36) RawModel[] headComponents = new RawModel[3];
			@Pc(38) int componentCount = 0;
			if (!IDKTypeList.get(faceId).hasReadyHeads() || !IDKTypeList.get(beardId).hasReadyHeads() || !IDKTypeList.get(hairId).hasReadyHeads()) {
				return null;
			}
			@Pc(66) RawModel rawModel = IDKTypeList.get(faceId).getHeadModel();
			if (rawModel != null) {
				componentCount++;
				headComponents[0] = rawModel;
			}
			rawModel = IDKTypeList.get(beardId).getHeadModel();
			if (rawModel != null) {
				headComponents[componentCount++] = rawModel;
			}
			rawModel = IDKTypeList.get(hairId).getHeadModel();
			if (rawModel != null) {
				headComponents[componentCount++] = rawModel;
			}
			rawModel = new RawModel(headComponents, componentCount);
			for (@Pc(110) int colorIndex = 0; colorIndex < 5; colorIndex++) {
				if (this.colors[colorIndex] < destinationBodyColors[colorIndex].length) {
					rawModel.recolor(sourceBodyColors[colorIndex], destinationBodyColors[colorIndex][this.colors[colorIndex]]);
				}
				if (destinationSkinColors[colorIndex].length > this.colors[colorIndex]) {
					rawModel.recolor(sourceSkinColors[colorIndex], destinationSkinColors[colorIndex][this.colors[colorIndex]]);
				}
			}
			model = rawModel.createModel(64, 768, -50, -10, -50);
			headModels.put(model, cacheKey);
		}
		if (sequence != null) {
			model = sequence.method4215(model, animFrame, animFrame2, animLoop);
		}
		return model;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(I)V")
	private void updateChecksum() {
		@Pc(8) long previousChecksum = this.checksum;
		this.checksum = -1L;
		@Pc(13) long[] crcTable = Packet.CRC64_TABLE;
		this.checksum = crcTable[(int) (((long) (this.basId >> 8) ^ this.checksum) & 0xFFL)] ^ this.checksum >>> 8;
		this.checksum = crcTable[(int) ((this.checksum ^ (long) this.basId) & 0xFFL)] ^ this.checksum >>> 8;
		@Pc(53) int i;
		for (i = 0; i < 12; i++) {
			this.checksum = this.checksum >>> 8 ^ crcTable[(int) ((this.checksum ^ (long) (this.identikit[i] >> 24)) & 0xFFL)];
			this.checksum = this.checksum >>> 8 ^ crcTable[(int) ((this.checksum ^ (long) (this.identikit[i] >> 16)) & 0xFFL)];
			this.checksum = crcTable[(int) (((long) (this.identikit[i] >> 8) ^ this.checksum) & 0xFFL)] ^ this.checksum >>> 8;
			this.checksum = this.checksum >>> 8 ^ crcTable[(int) ((this.checksum ^ (long) this.identikit[i]) & 0xFFL)];
		}
		for (i = 0; i < 5; i++) {
			this.checksum = crcTable[(int) (((long) this.colors[i] ^ this.checksum) & 0xFFL)] ^ this.checksum >>> 8;
		}
		this.checksum = crcTable[(int) (((long) (this.gender ? 1 : 0) ^ this.checksum) & 0xFFL)] ^ this.checksum >>> 8;
		if (previousChecksum != 0L && this.checksum != previousChecksum) {
			bodyModels.remove(previousChecksum);
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(ZZ)V")
	public final void setGender(@OriginalArg(0) boolean woman) {
		this.gender = woman;
		this.updateChecksum();
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "([IIZI[II)V")
	public final void set(@OriginalArg(0) int[] colors, @OriginalArg(1) int npcId, @OriginalArg(2) boolean female, @OriginalArg(4) int[] arg3, @OriginalArg(5) int arg4) {
		if (arg4 != this.basId) {
			this.basId = arg4;
			this.transformationMatrices = null;
		}
		if (arg3 == null) {
			arg3 = new int[12];
			for (@Pc(24) int featureIndex = 0; featureIndex < 8; featureIndex++) {
				for (@Pc(31) int idkId = 0; idkId < IDKTypeList.count; idkId++) {
					@Pc(38) IDKType type = IDKTypeList.get(idkId);
					if (type != null && !type.disable && type.feature == (female ? FEMALE_FEATURES[featureIndex] : MALE_FEATURES[featureIndex])) {
						arg3[BASE_PART_MAP[featureIndex]] = Integer.MIN_VALUE | idkId;
						break;
					}
				}
			}
		}
		this.npcId = npcId;
		this.gender = female;
		this.colors = colors;
		this.identikit = arg3;
		this.updateChecksum();
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IZI)V")
	public final void setColor(@OriginalArg(0) int i, @OriginalArg(2) int color) {
		this.colors[i] = color;
		this.updateChecksum();
	}

	@OriginalMember(owner = "client!hh", name = "b", descriptor = "(I)I")
	public final int getHeadModelId() {
		return this.npcId == -1 ? (this.identikit[8] << 10) + ((this.colors[0] << 25) + (this.colors[4] << 20)) + (this.identikit[0] << 15) + (this.identikit[11] << 5) + this.identikit[1] : NpcTypeList.get(this.npcId).id + 305419896;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(III)V")
	public final void setIdentikit(@OriginalArg(0) int arg0, @OriginalArg(1) int identikit) {
		@Pc(7) int part = BASE_PART_MAP[arg0];
		if (0 != this.identikit[part] && IDKTypeList.get(identikit) != null) {
			this.identikit[part] = identikit | Integer.MIN_VALUE;
			this.updateChecksum();
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "([Lclient!ub;ILclient!tk;Lclient!tk;IIIIZII)Lclient!ak;")
	public final Model createAnimatedBodyModel(@OriginalArg(0) PathingEntity_Class147[] animationStates, @OriginalArg(1) int tweenFrame, @OriginalArg(2) SeqType primarySequence, @OriginalArg(3) SeqType weaponSequence, @OriginalArg(4) int primaryFrame2, @OriginalArg(5) int primaryFrame1, @OriginalArg(7) int weaponFrame2, @OriginalArg(9) int weaponFrame1, @OriginalArg(10) int primaryTweenFrame) {
		if (this.npcId != -1) {
			return NpcTypeList.get(this.npcId).getBodyModel(animationStates, primaryFrame1, primaryTweenFrame, tweenFrame, weaponFrame2, weaponFrame1, primarySequence, primaryFrame2, weaponSequence);
		}
		@Pc(38) int[] equipmentIds = this.identikit;
		@Pc(41) long modelCacheKey = this.checksum;
		if (weaponSequence != null && (weaponSequence.mainHand >= 0 || weaponSequence.offhand >= 0)) {
			equipmentIds = new int[12];
			for (@Pc(61) int i = 0; i < 12; i++) {
				equipmentIds[i] = this.identikit[i];
			}
			if (weaponSequence.mainHand >= 0) {
				if (weaponSequence.mainHand == 65535) {
					modelCacheKey ^= 0xFFFFFFFF00000000L;
					equipmentIds[5] = 0;
				} else {
					equipmentIds[5] = weaponSequence.mainHand | 0x40000000;
					modelCacheKey ^= (long) equipmentIds[5] << 32;
				}
			}
			if (weaponSequence.offhand >= 0) {
				if (weaponSequence.offhand == 65535) {
					equipmentIds[3] = 0;
					modelCacheKey ^= 0xFFFFFFFFL;
				} else {
					equipmentIds[3] = weaponSequence.offhand | 0x40000000;
					modelCacheKey ^= equipmentIds[3];
				}
			}
		}
		@Pc(154) Model cachedModel = (Model) bodyModels.get(modelCacheKey);
		@Pc(158) boolean hasUnreadyModels;
		@Pc(353) int translateY;
		@Pc(360) int translateZ;
		@Pc(374) int rotateX;
		@Pc(367) int rotateY;
		@Pc(381) int rotateZ;
		@Pc(451) int local451;
		@Pc(457) int local457;
		@Pc(475) int local475;
		@Pc(481) int local481;
		@Pc(598) int local598;
		@Pc(346) int translateX;
		if (cachedModel == null) {
			hasUnreadyModels = false;
			@Pc(169) int equipmentId;
			for (@Pc(160) int slotIndex = 0; slotIndex < 12; slotIndex++) {
				equipmentId = equipmentIds[slotIndex];
				if ((equipmentId & 0x40000000) == 0) {
					if ((equipmentId & Integer.MIN_VALUE) != 0 && !IDKTypeList.get(equipmentId & 0x3FFFFFFF).isBodyModelReady()) {
						hasUnreadyModels = true;
					}
				} else if (!ObjTypeList.get(equipmentId & 0x3FFFFFFF).isWearModelReady(this.gender)) {
					hasUnreadyModels = true;
				}
			}
			if (hasUnreadyModels) {
				if (this.prevChecksum != -1L) {
					cachedModel = (Model) bodyModels.get(this.prevChecksum);
				}
				if (cachedModel == null) {
					return null;
				}
			}
			if (cachedModel == null) {
				@Pc(239) RawModel[] bodyParts = new RawModel[12];
				@Pc(250) int partIndex;
				for (equipmentId = 0; equipmentId < 12; equipmentId++) {
					partIndex = equipmentIds[equipmentId];
					@Pc(272) RawModel partModel;
					if ((partIndex & 0x40000000) != 0) {
						partModel = ObjTypeList.get(partIndex & 0x3FFFFFFF).getBodyModel(this.gender);
						if (partModel != null) {
							bodyParts[equipmentId] = partModel;
						}
					} else if ((Integer.MIN_VALUE & partIndex) != 0) {
						partModel = IDKTypeList.get(partIndex & 0x3FFFFFFF).getModel();
						if (partModel != null) {
							bodyParts[equipmentId] = partModel;
						}
					}
				}
				@Pc(303) BasType baseAnimSet = null;
				if (this.basId != -1) {
					baseAnimSet = BasTypeList.get(this.basId);
				}
				if (baseAnimSet != null && baseAnimSet.modelRotateTranslate != null) {
					for (partIndex = 0; partIndex < baseAnimSet.modelRotateTranslate.length; partIndex++) {
						if (baseAnimSet.modelRotateTranslate[partIndex] != null && bodyParts[partIndex] != null) {
							translateX = baseAnimSet.modelRotateTranslate[partIndex][0];
							translateY = baseAnimSet.modelRotateTranslate[partIndex][1];
							translateZ = baseAnimSet.modelRotateTranslate[partIndex][2];
							rotateY = baseAnimSet.modelRotateTranslate[partIndex][4];
							rotateX = baseAnimSet.modelRotateTranslate[partIndex][3];
							rotateZ = baseAnimSet.modelRotateTranslate[partIndex][5];
							if (this.transformationMatrices == null) {
								this.transformationMatrices = new int[baseAnimSet.modelRotateTranslate.length][];
							}
							if (this.transformationMatrices[partIndex] == null) {
								@Pc(404) int[] transformMatrix = this.transformationMatrices[partIndex] = new int[15];
								if (rotateX == 0 && rotateY == 0 && rotateZ == 0) {
									transformMatrix[12] = -translateX;
									transformMatrix[13] = -translateY;
									transformMatrix[0] = transformMatrix[4] = transformMatrix[8] = 32768;
									transformMatrix[14] = -translateZ;
								} else {
									local451 = MathUtils.cos[rotateX] >> 1;
									local457 = MathUtils.sin[rotateX] >> 1;
									@Pc(463) int local463 = MathUtils.cos[rotateY] >> 1;
									@Pc(469) int local469 = MathUtils.sin[rotateY] >> 1;
									local475 = MathUtils.cos[rotateZ] >> 1;
									local481 = MathUtils.sin[rotateZ] >> 1;
									transformMatrix[4] = local451 * local475 + 16384 >> 15;
									transformMatrix[5] = -local457;
									transformMatrix[3] = local481 * local451 + 16384 >> 15;
									transformMatrix[2] = local451 * local469 + 16384 >> 15;
									transformMatrix[8] = local463 * local451 + 16384 >> 15;
									@Pc(534) int local534 = local481 * local457 + 16384 >> 15;
									transformMatrix[0] = local469 * local534 + local475 * local463 + 16384 >> 15;
									transformMatrix[14] = transformMatrix[8] * -translateZ + -translateY * transformMatrix[5] + transformMatrix[2] * -translateX + 16384 >> 15;
									transformMatrix[6] = local463 * local534 + local475 * -local469 + 16384 >> 15;
									local598 = local475 * local457 + 16384 >> 15;
									transformMatrix[7] = -local481 * -local469 + local598 * local463 + 16384 >> 15;
									transformMatrix[1] = local469 * local598 + local463 * -local481 + 16384 >> 15;
									transformMatrix[12] = -translateY * transformMatrix[3] + transformMatrix[0] * -translateX + -translateZ * transformMatrix[6] + 16384 >> 15;
									transformMatrix[13] = -translateY * transformMatrix[4] + transformMatrix[1] * -translateX + -translateZ * transformMatrix[7] + 16384 >> 15;
								}
								transformMatrix[9] = translateX;
								transformMatrix[11] = translateZ;
								transformMatrix[10] = translateY;
							}
							if (rotateX != 0 || rotateY != 0 || rotateZ != 0) {
								bodyParts[partIndex].method1684(rotateX, rotateY, rotateZ);
							}
							if (translateX != 0 || translateY != 0 || translateZ != 0) {
								bodyParts[partIndex].translate(translateX, translateY, translateZ);
							}
						}
					}
				}
				@Pc(740) RawModel model = new RawModel(bodyParts, bodyParts.length);
				for (translateX = 0; translateX < 5; translateX++) {
					if (destinationBodyColors[translateX].length > this.colors[translateX]) {
						model.recolor(sourceBodyColors[translateX], destinationBodyColors[translateX][this.colors[translateX]]);
					}
					if (destinationSkinColors[translateX].length > this.colors[translateX]) {
						model.recolor(sourceSkinColors[translateX], destinationSkinColors[translateX][this.colors[translateX]]);
					}
				}
				cachedModel = model.createModel(64, 850, -30, -50, -30);
				if (GlRenderer.enabled) {
					((GlModel) cachedModel).updateBuffers(false, false, true, false, false, true);
				}
				bodyModels.put(cachedModel, modelCacheKey);
				this.prevChecksum = modelCacheKey;
			}
		}
		hasUnreadyModels = false;
		@Pc(827) boolean hasAlphaTransforms = false;
		translateX = animationStates == null ? 0 : animationStates.length;
		@Pc(836) boolean hasColorTransforms = false;
		@Pc(838) boolean hasSpecialEffects = false;
		@Pc(979) int local979;
		for (translateY = 0; translateY < translateX; translateY++) {
			if (animationStates[translateY] != null) {
				@Pc(858) SeqType layerSequence = SeqTypeList.get(animationStates[translateY].sequenceId);
				if (layerSequence.frames != null) {
					hasUnreadyModels = true;
					layerSequences[translateY] = layerSequence;
					rotateX = animationStates[translateY].frameIndex;
					rotateY = animationStates[translateY].direction;
					rotateZ = layerSequence.frames[rotateX];
					primaryFramesets[translateY] = SeqTypeList.getAnimFrameset(rotateZ >>> 16);
					rotateZ &= 0xFFFF;
					primaryFrameIds[translateY] = rotateZ;
					if (primaryFramesets[translateY] != null) {
						hasColorTransforms |= primaryFramesets[translateY].isColorTransformed(rotateZ);
						hasAlphaTransforms |= primaryFramesets[translateY].isAlphaTransformed(rotateZ);
						hasSpecialEffects |= layerSequence.aBoolean278;
					}
					if ((layerSequence.tween || SeqType.applyTweening) && rotateY != -1 && rotateY < layerSequence.frames.length) {
						frameDelays[translateY] = layerSequence.frameDelay[rotateX];
						frameTimes[translateY] = animationStates[translateY].loopCount;
						local979 = layerSequence.frames[rotateY];
						tweenFramesets[translateY] = SeqTypeList.getAnimFrameset(local979 >>> 16);
						@Pc(991) int local991 = local979 & 0xFFFF;
						tweenFrameIds[translateY] = local991;
						if (tweenFramesets[translateY] != null) {
							hasColorTransforms |= tweenFramesets[translateY].isColorTransformed(local991);
							hasAlphaTransforms |= tweenFramesets[translateY].isAlphaTransformed(local991);
						}
					} else {
						frameDelays[translateY] = 0;
						frameTimes[translateY] = 0;
						tweenFramesets[translateY] = null;
						tweenFrameIds[translateY] = -1;
					}
				}
			}
		}
		if (!hasUnreadyModels && weaponSequence == null && primarySequence == null) {
			return cachedModel;
		}
		translateY = -1;
		translateZ = -1;
		rotateX = 0;
		@Pc(1040) AnimFrameset weaponFrameset1 = null;
		@Pc(1042) AnimFrameset weaponFrameset2 = null;
		if (weaponSequence != null) {
			translateY = weaponSequence.frames[weaponFrame1];
			local979 = translateY >>> 16;
			weaponFrameset2 = SeqTypeList.getAnimFrameset(local979);
			translateY &= 0xFFFF;
			if (weaponFrameset2 != null) {
				hasColorTransforms |= weaponFrameset2.isColorTransformed(translateY);
				hasAlphaTransforms |= weaponFrameset2.isAlphaTransformed(translateY);
				hasSpecialEffects |= weaponSequence.aBoolean278;
			}
			if ((weaponSequence.tween || SeqType.applyTweening) && tweenFrame != -1 && weaponSequence.frames.length > tweenFrame) {
				translateZ = weaponSequence.frames[tweenFrame];
				local451 = translateZ >>> 16;
				translateZ &= 0xFFFF;
				rotateX = weaponSequence.frameDelay[weaponFrame1];
				if (local979 == local451) {
					weaponFrameset1 = weaponFrameset2;
				} else {
					weaponFrameset1 = SeqTypeList.getAnimFrameset(translateZ >>> 16);
				}
				if (weaponFrameset1 != null) {
					hasColorTransforms |= weaponFrameset1.isColorTransformed(translateZ);
					hasAlphaTransforms |= weaponFrameset1.isAlphaTransformed(translateZ);
				}
			}
		}
		local979 = -1;
		local451 = -1;
		@Pc(1154) AnimFrameset primaryFrameset1 = null;
		@Pc(1156) AnimFrameset primaryFrameset2 = null;
		local457 = 0;
		if (primarySequence != null) {
			local979 = primarySequence.frames[primaryTweenFrame];
			local475 = local979 >>> 16;
			local979 &= 0xFFFF;
			primaryFrameset1 = SeqTypeList.getAnimFrameset(local475);
			if (primaryFrameset1 != null) {
				hasColorTransforms |= primaryFrameset1.isColorTransformed(local979);
				hasAlphaTransforms |= primaryFrameset1.isAlphaTransformed(local979);
				hasSpecialEffects |= primarySequence.aBoolean278;
			}
			if ((primarySequence.tween || SeqType.applyTweening) && primaryFrame1 != -1 && primarySequence.frames.length > primaryFrame1) {
				local457 = primarySequence.frameDelay[primaryTweenFrame];
				local451 = primarySequence.frames[primaryFrame1];
				local481 = local451 >>> 16;
				local451 &= 0xFFFF;
				if (local475 == local481) {
					primaryFrameset2 = primaryFrameset1;
				} else {
					primaryFrameset2 = SeqTypeList.getAnimFrameset(local451 >>> 16);
				}
				if (primaryFrameset2 != null) {
					hasColorTransforms |= primaryFrameset2.isColorTransformed(local451);
					hasAlphaTransforms |= primaryFrameset2.isAlphaTransformed(local451);
				}
			}
		}
		@Pc(1284) Model animatedModel = cachedModel.method4572(!hasAlphaTransforms, !hasColorTransforms, !hasSpecialEffects);
		local481 = 0;
		local598 = 1;
		while (local481 < translateX) {
			if (primaryFramesets[local481] != null) {
				animatedModel.method4565(primaryFramesets[local481], primaryFrameIds[local481], tweenFramesets[local481], tweenFrameIds[local481], frameTimes[local481] - 1, frameDelays[local481], local598, layerSequences[local481].aBoolean278, this.transformationMatrices[local481]);
			}
			local481++;
			local598 <<= 0x1;
		}
		if (weaponFrameset2 != null && primaryFrameset1 != null) {
			animatedModel.method4570(weaponFrameset2, translateY, weaponFrameset1, translateZ, weaponFrame2 - 1, rotateX, primaryFrameset1, local979, primaryFrameset2, local451, primaryFrame2 - 1, local457, weaponSequence.framegroup, weaponSequence.aBoolean278 | primarySequence.aBoolean278);
		} else if (weaponFrameset2 != null) {
			animatedModel.method4558(weaponFrameset2, translateY, weaponFrameset1, translateZ, weaponFrame2 - 1, rotateX, weaponSequence.aBoolean278);
		} else if (primaryFrameset1 != null) {
			animatedModel.method4558(primaryFrameset1, local979, primaryFrameset2, local451, primaryFrame2 - 1, local457, primarySequence.aBoolean278);
		}
		for (local481 = 0; local481 < translateX; local481++) {
			primaryFramesets[local481] = null;
			tweenFramesets[local481] = null;
			layerSequences[local481] = null;
		}
		return animatedModel;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IBLclient!tk;II)Lclient!ak;")
	public final Model getStaticModel(@OriginalArg(0) int animFrame, @OriginalArg(2) SeqType sequence, @OriginalArg(3) int animLoop, @OriginalArg(4) int animFrame2) {
		if (this.npcId != -1) {
			return NpcTypeList.get(this.npcId).getHeadModel(sequence, animFrame, animFrame2, animLoop);
		}
		@Pc(30) Model cachedModel = (Model) headModels.get(this.checksum);
		if (cachedModel == null) {
			@Pc(42) boolean hasUnreadyModels = false;
			@Pc(52) int equipmentId;
			for (@Pc(44) int slotIndex = 0; slotIndex < 12; slotIndex++) {
				equipmentId = this.identikit[slotIndex];
				if ((equipmentId & 0x40000000) == 0) {
					if ((equipmentId & Integer.MIN_VALUE) != 0 && !IDKTypeList.get(equipmentId & 0x3FFFFFFF).hasReadyHeads()) {
						hasUnreadyModels = true;
					}
				} else if (!ObjTypeList.get(equipmentId & 0x3FFFFFFF).isHeadModelReady(this.gender)) {
					hasUnreadyModels = true;
				}
			}
			if (hasUnreadyModels) {
				return null;
			}
			@Pc(100) RawModel[] headComponents = new RawModel[12];
			equipmentId = 0;
			@Pc(114) int j;
			for (@Pc(104) int equipmentSlot = 0; equipmentSlot < 12; equipmentSlot++) {
				j = this.identikit[equipmentSlot];
				@Pc(134) RawModel headModel;
				if ((j & 0x40000000) != 0) {
					headModel = ObjTypeList.get(j & 0x3FFFFFFF).getHeadModel(this.gender);
					if (headModel != null) {
						headComponents[equipmentId++] = headModel;
					}
				} else if ((Integer.MIN_VALUE & j) != 0) {
					headModel = IDKTypeList.get(j & 0x3FFFFFFF).getHeadModel();
					if (headModel != null) {
						headComponents[equipmentId++] = headModel;
					}
				}
			}
			@Pc(171) RawModel combinedModel = new RawModel(headComponents, equipmentId);
			for (j = 0; j < 5; j++) {
				if (destinationBodyColors[j].length > this.colors[j]) {
					combinedModel.recolor(sourceBodyColors[j], destinationBodyColors[j][this.colors[j]]);
				}
				if (destinationSkinColors[j].length > this.colors[j]) {
					combinedModel.recolor(sourceSkinColors[j], destinationSkinColors[j][this.colors[j]]);
				}
			}
			cachedModel = combinedModel.createModel(64, 768, -50, -10, -50);
			headModels.put(cachedModel, this.checksum);
		}
		if (sequence != null) {
			cachedModel = sequence.method4215(cachedModel, animFrame2, animFrame, animLoop);
		}
		return cachedModel;
	}
}
