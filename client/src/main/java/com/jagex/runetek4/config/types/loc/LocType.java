package com.jagex.runetek4.config.types.loc;

import com.jagex.runetek4.*;
import com.jagex.runetek4.client.client;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.LocEntity;
import com.jagex.runetek4.RawModel;
import com.jagex.runetek4.Entity;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.JagStringWrapper;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!pb")
public final class LocType {

	// Loc Shapes
	public static final int WALL_STRAIGHT = 0;
	public static final int WALL_DIAGONAL_CORNER = 1;
	public static final int WALL_L = 2;
	public static final int WALL_SQUARE_CORNER = 3;
	public static final int WALL_DIAGONAL = 9;

	public static final int WALLDECOR_STRAIGHT_NOOFFSET = 4;
	public static final int WALLDECOR_STRAIGHT_OFFSET = 5;
	public static final int WALLDECOR_DIAGONAL_OFFSET = 6;
	public static final int WALLDECOR_DIAGONAL_NOOFFSET = 7;
	public static final int WALLDECOR_DIAGONAL_BOTH = 8;

	public static final int ROOF_STRAIGHT = 12;
	public static final int ROOF_DIAGONAL_WITH_ROOFEDGE = 13;
	public static final int ROOF_DIAGONAL = 14;
	public static final int ROOF_L_CONCAVE = 15;
	public static final int ROOF_L_CONVEX = 16;
	public static final int ROOF_FLAT = 17;

	public static final int ROOFEDGE_STRAIGHT = 18;
	public static final int ROOFEDGE_DIAGONALCORNER = 19;
	public static final int ROOFEDGE_L = 20;
	public static final int ROOFEDGE_SQUARECORNER = 21;

	public static final int CENTREPIECE_STRAIGHT = 10;
	public static final int CENTREPIECE_DIAGONAL = 11;
	public static final int GROUND_DECOR = 22;

	@OriginalMember(owner = "runetek4.client!wf", name = "o", descriptor = "[Lclient!gb;")
	public static final RawModel[] tempModels = new RawModel[4];

	@OriginalMember(owner = "runetek4.client!ni", name = "n", descriptor = "Lclient!sm;")
    public static LocEntity aLocEntity_1 = new LocEntity();

    @OriginalMember(owner = "client!pb", name = "a", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!pb", name = "b", descriptor = "[S")
	private short[] recol_s;

	@OriginalMember(owner = "client!pb", name = "n", descriptor = "[I")
	private int[] shapes;

	@OriginalMember(owner = "client!pb", name = "v", descriptor = "[S")
	private short[] retex_d;

	@OriginalMember(owner = "client!pb", name = "B", descriptor = "Lclient!sc;")
	private HashTable params;

	@OriginalMember(owner = "client!pb", name = "H", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!pb", name = "P", descriptor = "[B")
	private byte[] recol_d_palette;

	@OriginalMember(owner = "client!pb", name = "X", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!pb", name = "db", descriptor = "[I")
	public int[] multiloc;

	@OriginalMember(owner = "client!pb", name = "hb", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!pb", name = "wb", descriptor = "[I")
	public int[] bgsound_random;

	@OriginalMember(owner = "client!pb", name = "e", descriptor = "I")
	public int width = 1;

	@OriginalMember(owner = "client!pb", name = "i", descriptor = "Z")
	public boolean forcedecor = false;

	@OriginalMember(owner = "client!pb", name = "l", descriptor = "I")
	public int length = 1;

	@OriginalMember(owner = "client!pb", name = "C", descriptor = "Z")
	public boolean renderUnderFeet = false;

	@OriginalMember(owner = "client!pb", name = "u", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "runetek4.client!gg", name = "W", descriptor = "Lclient!na;")
	public static final JString NULL = JString.parse("null");

	@OriginalMember(owner = "client!pb", name = "E", descriptor = "Lclient!na;")
	public JString name = NULL;

	@OriginalMember(owner = "client!pb", name = "D", descriptor = "Z")
	public boolean hardshadow = true;

	@OriginalMember(owner = "client!pb", name = "t", descriptor = "I")
	public int cursor1op = -1;

	@OriginalMember(owner = "client!pb", name = "R", descriptor = "I")
	public int bgsound_maxdelay = 0;

	@OriginalMember(owner = "client!pb", name = "S", descriptor = "I")
	public int mapsceneicon = -1;

	@OriginalMember(owner = "client!pb", name = "G", descriptor = "B")
	private byte hillchange = 0;

	@OriginalMember(owner = "client!pb", name = "r", descriptor = "Z")
	public boolean members = false;

	@OriginalMember(owner = "client!pb", name = "T", descriptor = "I")
	public int cursor1 = -1;

	@OriginalMember(owner = "client!pb", name = "w", descriptor = "I")
	private int xoff = 0;

	@OriginalMember(owner = "client!pb", name = "W", descriptor = "I")
	public int bgsound_mindelay = 0;

	@OriginalMember(owner = "client!pb", name = "h", descriptor = "I")
	public int mapfunction = -1;

	@OriginalMember(owner = "client!pb", name = "L", descriptor = "Z")
	public boolean aBoolean214 = false;

	@OriginalMember(owner = "client!pb", name = "Y", descriptor = "I")
	public int cursor2op = -1;

	@OriginalMember(owner = "client!pb", name = "A", descriptor = "S")
	private short hillchange_value = -1;

	@OriginalMember(owner = "client!pb", name = "g", descriptor = "I")
	private int resizez = 128;

	@OriginalMember(owner = "client!pb", name = "z", descriptor = "[Lclient!na;")
	public JString[] op = new JString[5];

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "I")
	private int resizex = 128;

	@OriginalMember(owner = "client!pb", name = "s", descriptor = "Z")
	public boolean randomanimframe = true;

	@OriginalMember(owner = "client!pb", name = "o", descriptor = "I")
	private int resizey = 128;

	@OriginalMember(owner = "client!pb", name = "y", descriptor = "Z")
	public boolean breakroutefinding = false;

	@OriginalMember(owner = "client!pb", name = "kb", descriptor = "I")
	public int active = -1;

	@OriginalMember(owner = "client!pb", name = "lb", descriptor = "Z")
	public boolean istexture = false;

	@OriginalMember(owner = "client!pb", name = "fb", descriptor = "Z")
	public boolean hashardshadow = true;

	@OriginalMember(owner = "client!pb", name = "nb", descriptor = "I")
	private int multivarp = -1;

	@OriginalMember(owner = "client!pb", name = "bb", descriptor = "I")
	public int cursor2 = -1;

	@OriginalMember(owner = "client!pb", name = "pb", descriptor = "I")
	public int blocksides = 0;

	@OriginalMember(owner = "client!pb", name = "m", descriptor = "Z")
	public boolean blockrange = true;

	@OriginalMember(owner = "client!pb", name = "qb", descriptor = "I")
	private int zoff = 0;

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "I")
	public int mapsceneiconrotationoffset = 0;

	@OriginalMember(owner = "client!pb", name = "jb", descriptor = "I")
	public int walloff = 16;

	@OriginalMember(owner = "client!pb", name = "tb", descriptor = "Z")
	public boolean mapsceneiconrorate = false;

	@OriginalMember(owner = "client!pb", name = "N", descriptor = "I")
	private int yoff = 0;

	@OriginalMember(owner = "client!pb", name = "k", descriptor = "I")
	public int bgsound_range = 0;

	@OriginalMember(owner = "client!pb", name = "p", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!pb", name = "mb", descriptor = "I")
	public int anim = -1;

	@OriginalMember(owner = "client!pb", name = "I", descriptor = "Z")
	public boolean hasAnimation = false;

	@OriginalMember(owner = "client!pb", name = "O", descriptor = "I")
	public int bgsound_sound = -1;

	@OriginalMember(owner = "client!pb", name = "ub", descriptor = "I")
	public int blockwalk = 2;

	@OriginalMember(owner = "client!pb", name = "sb", descriptor = "Z")
	private boolean mirror = false;

	@OriginalMember(owner = "client!pb", name = "gb", descriptor = "I")
	private int multivarbit = -1;

	@OriginalMember(owner = "client!pb", name = "yb", descriptor = "I")
	public int raiseobject = -1;

	@OriginalMember(owner = "client!pb", name = "zb", descriptor = "Z")
	private boolean sharelight = false;

	@OriginalMember(owner = "client!pb", name = "Ab", descriptor = "Z")
	public boolean occlude = false;

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(9) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int opcode) {
		if (opcode == 1) {
			int count = packet.g1();
			if (count > 0) {
				if (this.models == null || SceneGraph.aBoolean130) {
					this.models = new int[count];
					this.shapes = new int[count];
					for (int len = 0; len < count; len++) {
						this.models[len] = packet.g2();
						this.shapes[len] = packet.g1();
					}
				} else {
					packet.offset += count * 3;
				}
			}
		} else if (opcode == 2) {
			this.name = packet.gjstr();
		} else if (opcode == 5) {
			// Represents the same data as opcode 1, a packing optimization for shape 10 (centerpiece).
			int count = packet.g1();
			if (count > 0) {
				if (this.models == null || SceneGraph.aBoolean130) {
					this.models = new int[count];
					this.shapes = null;
					for (int len = 0; len < count; len++) {
						this.models[len] = packet.g2();
					}
				} else {
					packet.offset += count * 2;
				}
			}
		} else if (opcode == 14) {
			this.width = packet.g1();
		} else if (opcode == 15) {
			this.length = packet.g1();
		} else if (opcode == 17) {
			this.blockwalk = 0;
			this.blockrange = false;
		} else if (opcode == 18) {
			this.blockrange = false;
		} else if (opcode == 19) {
			this.active = packet.g1();
		} else if (opcode == 21) {
			this.hillchange = 1;
		} else if (opcode == 22) {
			this.sharelight = true;
		} else if (opcode == 23) {
			this.occlude = true;
		} else if (opcode == 24) {
			this.anim = packet.g2();
			if (this.anim == 65535) {
				this.anim = -1;
			}
		} else if (opcode == 27) {
			this.blockwalk = 1;
		} else if (opcode == 28) {
			this.walloff = packet.g1();
		} else if (opcode == 29) {
			this.ambient = packet.g1s();
		} else if (opcode == 39) {
			this.contrast = packet.g1s() * 5;
		} else if (opcode >= 30 && opcode < 35) {
			this.op[opcode - 30] = packet.gjstr();
			if (this.op[opcode - 30].equalsIgnoreCase(LocalizedText.HIDDEN)) {
				this.op[opcode - 30] = null;
			}
		} else if (opcode == 40) {
			int length = packet.g1();
			this.recol_s = new short[length];
			this.recol_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.recol_s[index] = (short) packet.g2();
				this.recol_d[index] = (short) packet.g2();
			}
		} else if (opcode == 41) {
			int length = packet.g1();
			this.retex_s = new short[length];
			this.retex_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.retex_s[index] = (short) packet.g2();
				this.retex_d[index] = (short) packet.g2();
			}
		} else if (opcode == 42) {
			int length = packet.g1();
			this.recol_d_palette = new byte[length];
			for (int index = 0; index < length; index++) {
				this.recol_d_palette[index] = packet.g1s();
			}
		} else if (opcode == 60) {
			this.mapfunction = packet.g2();
		} else if (opcode == 62) {
			this.mirror = true;
		} else if (opcode == 64) {
			this.hashardshadow = false;
		} else if (opcode == 65) {
			this.resizex = packet.g2();
		} else if (opcode == 66) {
			this.resizey = packet.g2();
		} else if (opcode == 67) {
			this.resizez = packet.g2();
		} else if (opcode == 69) {
			this.blocksides = packet.g1();
		} else if (opcode == 70) {
			this.xoff = packet.g2s();
		} else if (opcode == 71) {
			this.yoff = packet.g2s();
		} else if (opcode == 72) {
			this.zoff = packet.g2s();
		} else if (opcode == 73) {
			this.forcedecor = true;
		} else if (opcode == 74) {
			this.breakroutefinding = true;
		} else if (opcode == 75) {
			this.raiseobject = packet.g1();
		} else if (opcode == 77 || opcode == 92) {
			this.multivarbit = packet.g2();
			if (this.multivarbit == 65535) {
				this.multivarbit = -1;
			}
			this.multivarp = packet.g2();
			if (this.multivarp == 65535) {
				this.multivarp = -1;
			}

			int defaultId = -1;

			if (opcode == 92) {
				defaultId = packet.g2();
				if (defaultId == 65535) {
					defaultId = -1;
				}
			}

			int length = packet.g1();
			this.multiloc = new int[length + 2];
			for (@Pc(790) int index = 0; index <= length; index++) {
				this.multiloc[index] = packet.g2();
				if (this.multiloc[index] == 65535) {
					this.multiloc[index] = -1;
				}
			}
			this.multiloc[length + 1] = defaultId;
		} else if (opcode == 78) {
			this.bgsound_sound = packet.g2();
			this.bgsound_range = packet.g1();
		} else if (opcode == 79) {
			this.bgsound_mindelay = packet.g2();
			this.bgsound_maxdelay = packet.g2();
			this.bgsound_range = packet.g1();
			int length = packet.g1();
			this.bgsound_random = new int[length];
			for (int index = 0; index < length; index++) {
				this.bgsound_random[index] = packet.g2();
			}
		} else if (opcode == 81) {
			this.hillchange = 2;
			this.hillchange_value = (short) (packet.g1() * 256);
		} else if (opcode == 82) {
			this.istexture = true;
		} else if (opcode == 88) {
			this.hardshadow = false;
		} else if (opcode == 89) {
			this.randomanimframe = false;
		} else if (opcode == 90) {
			this.renderUnderFeet = true; // TODO(Proper name)
		} else if (opcode == 91) {
			this.members = true;
		} else if (opcode == 93) {
			this.hillchange = 3;
			this.hillchange_value = (short) packet.g2();
		} else if (opcode == 94) {
			this.hillchange = 4;
		} else if (opcode == 95) {
			this.hillchange = 5;
		} else if (opcode == 96) {
			this.hasAnimation = true; // TODO(Name needs verification)
		} else if (opcode == 97) {
			this.mapsceneiconrorate = true;
		} else if (opcode == 98) {
			this.aBoolean214 = true; // TODO: 'animated'?
		} else if (opcode == 99) {
			this.cursor1op = packet.g1();
			this.cursor1 = packet.g2();
		} else if (opcode == 100) {
			this.cursor2op = packet.g1();
			this.cursor2 = packet.g2();
		} else if (opcode == 101) {
			this.mapsceneiconrotationoffset = packet.g1(); // TODO(Name needs verification)
		} else if (opcode == 102) {
			this.mapsceneicon = packet.g2();
		} else if (opcode == 249) {
			int length = packet.g1();
			if (this.params == null) {
				int len = IntUtils.bitceil(length);
				this.params = new HashTable(len);
			}
			for (int index = 0; index < length; index++) {
				@Pc(576) boolean isString = packet.g1() == 1;
				@Pc(580) int key = packet.g3();
				@Pc(589) Node node;
				if (isString) {
					node = new JagStringWrapper(packet.gjstr());
				} else {
					node = new IntWrapper(packet.g4());
				}
				this.params.put(node, key);
			}
		} else {
			System.out.println("Error unrecognised loc config code: " + opcode);
		}
	}

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "(I)V")
	public void postDecode() {
		if (this.active == -1) {
			this.active = 0;
			if (this.models != null && (this.shapes == null || this.shapes[0] == 10)) {
				this.active = 1;
			}
			for (@Pc(30) int i = 0; i < 5; i++) {
				if (this.op[i] != null) {
					this.active = 1;
					break;
				}
			}
		}
		if (this.raiseobject == -1) {
			this.raiseobject = this.blockwalk == 0 ? 0 : 1;
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(II)Z")
	public boolean isReady(@OriginalArg(1) int shape) {
		if (this.shapes != null) {
			for (@Pc(18) int i = 0; i < this.shapes.length; i++) {
				if (shape == this.shapes[i]) {
					return LocTypeList.modelsArchive.isFileReady(this.models[i] & 0xFFFF, 0);
				}
			}
			return true;
		} else if (this.models == null) {
			return true;
		} else if (shape == LocType.CENTREPIECE_STRAIGHT) {
			@Pc(71) boolean ready = true;
			for (@Pc(73) int i = 0; i < this.models.length; i++) {
				ready &= LocTypeList.modelsArchive.isFileReady(this.models[i] & 0xFFFF, 0);
			}
			return ready;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(I)Lclient!pb;")
	public LocType getMultiLoc() {
		@Pc(26) int i = -1;
		if (this.multivarbit != -1) {
			i = VarpDomain.getVarbitValue(this.multivarbit);
		} else if (this.multivarp != -1) {
			i = VarpDomain.activeVarps[this.multivarp];
		}
		if (i < 0 || i >= this.multiloc.length - 1 || this.multiloc[i] == -1) {
			@Pc(84) int local84 = this.multiloc[this.multiloc.length - 1];
			return local84 == -1 ? null : LocTypeList.get(local84);
		} else {
			return LocTypeList.get(this.multiloc[i]);
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(III)Lclient!gb;")
	private RawModel getRawModel(@OriginalArg(0) int rotation, @OriginalArg(1) int arg1) {
		@Pc(7) RawModel model = null;
		@Pc(10) boolean flipped = this.mirror;
		if (arg1 == 2 && rotation > 3) {
			flipped = !flipped;
		}
		@Pc(46) int modelId;
		@Pc(48) int modelId2;
		if (this.shapes == null) {
			if (arg1 != 10) {
				return null;
			}
			if (this.models == null) {
				return null;
			}
			modelId = this.models.length;
			for (modelId2 = 0; modelId2 < modelId; modelId2++) {
				@Pc(60) int local60 = this.models[modelId2];
				if (flipped) {
					local60 += 65536;
				}
				model = (RawModel) LocTypeList.modelCacheStatic.get(local60);
				if (model == null) {
					model = RawModel.create(LocTypeList.modelsArchive, local60 & 0xFFFF);
					if (model == null) {
						return null;
					}
					if (flipped) {
						model.rotateY180();
					}
					LocTypeList.modelCacheStatic.put(model, local60);
				}
				if (modelId > 1) {
					tempModels[modelId2] = model;
				}
			}
			if (modelId > 1) {
				model = new RawModel(tempModels, modelId);
			}
		} else {
			modelId = -1;
			for (modelId2 = 0; modelId2 < this.shapes.length; modelId2++) {
				if (arg1 == this.shapes[modelId2]) {
					modelId = modelId2;
					break;
				}
			}
			if (modelId == -1) {
				return null;
			}
			modelId2 = this.models[modelId];
			if (flipped) {
				modelId2 += 65536;
			}
			model = (RawModel) LocTypeList.modelCacheStatic.get(modelId2);
			if (model == null) {
				model = RawModel.create(LocTypeList.modelsArchive, modelId2 & 0xFFFF);
				if (model == null) {
					return null;
				}
				if (flipped) {
					model.rotateY180();
				}
				LocTypeList.modelCacheStatic.put(model, modelId2);
			}
		}
		@Pc(211) boolean scaled;
		scaled = this.resizex != 128 || this.resizey != 128 || this.resizez != 128;

		@Pc(230) boolean translated;
		translated = this.xoff != 0 || this.yoff != 0 || this.zoff != 0;

		@Pc(265) RawModel modified = new RawModel(model, rotation == 0 && !scaled && !translated, this.recol_s == null, this.retex_s == null, true);
		if (arg1 == 4 && rotation > 3) {
			modified.method1682();
			modified.translate(45, 0, -45);
		}
		@Pc(285) int local285 = rotation & 0x3;
		if (local285 == 1) {
			modified.swapXz();
		} else if (local285 == 2) {
			modified.negateXz();
		} else if (local285 == 3) {
			modified.method1689();
		}
		@Pc(315) int local315;
		if (this.recol_s != null) {
			for (local315 = 0; local315 < this.recol_s.length; local315++) {
				if (this.recol_d_palette == null || this.recol_d_palette.length <= local315) {
					modified.recolor(this.recol_s[local315], this.recol_d[local315]);
				} else {
					modified.recolor(this.recol_s[local315], client.aShortArray19[this.recol_d_palette[local315] & 0xFF]);
				}
			}
		}
		if (this.retex_s != null) {
			for (local315 = 0; local315 < this.retex_s.length; local315++) {
				modified.retexture(this.retex_s[local315], this.retex_d[local315]);
			}
		}
		if (scaled) {
			modified.resize(this.resizex, this.resizey, this.resizez);
		}
		if (translated) {
			modified.translate(this.xoff, this.yoff, this.zoff);
		}
		return modified;
	}

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "(I)Z")
	public boolean hasBackgroundSound() {
		if (this.multiloc == null) {
			return this.bgsound_sound != -1 || this.bgsound_random != null;
		}
		for (@Pc(44) int i = 0; i < this.multiloc.length; i++) {
			if (this.multiloc[i] != -1) {
				@Pc(70) LocType locType = LocTypeList.get(this.multiloc[i]);
				if (locType.bgsound_sound != -1 || locType.bgsound_random != null) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IIB)I")
	public int getParam(@OriginalArg(0) int defaultValue, @OriginalArg(1) int arg1) {
		if (this.params == null) {
			return defaultValue;
		} else {
			@Pc(21) IntWrapper value = (IntWrapper) this.params.get(arg1);
			return value == null ? defaultValue : value.value;
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Z)Z")
	public boolean isReady() {
		if (this.models == null) {
			return true;
		}
		@Pc(13) boolean ready = true;
		for (@Pc(15) int i = 0; i < this.models.length; i++) {
			ready &= LocTypeList.modelsArchive.isFileReady(this.models[i] & 0xFFFF, 0);
		}
		return ready;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IZZI)Lclient!td;")
	private GlModel getGlModel(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2) {
		@Pc(10) int local10 = this.ambient + 64;
		@Pc(17) int local17 = this.contrast * 5 + 768;
		@Pc(79) GlModel local79;
		@Pc(24) int local24;
		@Pc(177) int local177;
		if (this.shapes == null) {
			if (arg2 != 10) {
				return null;
			}
			if (this.models == null) {
				return null;
			}
			local24 = this.models.length;
			if (local24 == 0) {
				return null;
			}
			@Pc(135) long local135 = 0L;
			for (@Pc(137) int local137 = 0; local137 < local24; local137++) {
				local135 = (long) this.models[local137] + local135 * 67783L;
			}
			if (arg1) {
				local135 = ~local135;
			}
			local79 = (GlModel) LocTypeList.modelCacheStatic.get(local135);
			if (local79 == null) {
				@Pc(175) RawModel local175 = null;
				for (local177 = 0; local177 < local24; local177++) {
					local175 = RawModel.create(LocTypeList.modelsArchive, this.models[local177] & 0xFFFF);
					if (local175 == null) {
						return null;
					}
					if (local24 > 1) {
						tempModels[local177] = local175;
					}
				}
				if (local24 > 1) {
					local175 = new RawModel(tempModels, local24);
				}
				local79 = new GlModel(local175, local10, local17, arg1);
				LocTypeList.modelCacheStatic.put(local79, local135);
			}
		} else {
			local24 = -1;
			@Pc(26) int local26;
			for (local26 = 0; local26 < this.shapes.length; local26++) {
				if (this.shapes[local26] == arg2) {
					local24 = local26;
					break;
				}
			}
			if (local24 == -1) {
				return null;
			}
			local26 = this.models[local24];
			if (arg1) {
				local26 += 65536;
			}
			local79 = (GlModel) LocTypeList.modelCacheStatic.get(local26);
			if (local79 == null) {
				@Pc(90) RawModel local90 = RawModel.create(LocTypeList.modelsArchive, local26 & 0xFFFF);
				if (local90 == null) {
					return null;
				}
				local79 = new GlModel(local90, local10, local17, arg1);
				LocTypeList.modelCacheStatic.put(local79, local26);
			}
		}
		@Pc(236) boolean local236 = this.mirror;
		if (arg2 == 2 && arg0 > 3) {
			local236 = !local236;
		}
		@Pc(264) boolean local264 = this.resizey == 128 && this.yoff == 0;
		@Pc(294) boolean local294 = arg0 == 0 && this.resizex == 128 && this.resizez == 128 && this.xoff == 0 && this.zoff == 0 && !local236;
		@Pc(351) GlModel local351 = local79.method4117(local294, local264, this.recol_s == null, local79.method4094() == local10, arg0 == 0 && !local236, true, local17 == local79.method4115(), !local236, this.retex_s == null);
		if (local236) {
			local351.method4122();
		}
		if (arg2 == 4 && arg0 > 3) {
			local351.method4123();
			local351.translate(45, 0, -45);
		}
		@Pc(374) int local374 = arg0 & 0x3;
		if (local374 == 1) {
			local351.method4116();
		} else if (local374 == 2) {
			local351.method4102();
		} else if (local374 == 3) {
			local351.method4093();
		}
		if (this.recol_s != null) {
			for (local177 = 0; local177 < this.recol_s.length; local177++) {
				local351.method4109(this.recol_s[local177], this.recol_d[local177]);
			}
		}
		if (this.retex_s != null) {
			for (local177 = 0; local177 < this.retex_s.length; local177++) {
				local351.method4107(this.retex_s[local177], this.retex_d[local177]);
			}
		}
		if (this.resizex != 128 || this.resizey != 128 || this.resizez != 128) {
			local351.resize(this.resizex, this.resizey, this.resizez);
		}
		if (this.xoff != 0 || this.yoff != 0 || this.zoff != 0) {
			local351.translate(this.xoff, this.yoff, this.zoff);
		}
		if (local10 != local351.method4094()) {
			local351.method4105(local10);
		}
		if (local351.method4115() != local17) {
			local351.method4100(local17);
		}
		return local351;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(II[[III[[IZLclient!ek;BZI)Lclient!sm;")
	public final LocEntity method3428(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[][] arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int[][] arg5, @OriginalArg(6) boolean arg6, @OriginalArg(7) SoftwareIndexedSprite arg7, @OriginalArg(9) boolean arg8, @OriginalArg(10) int arg9) {
		@Pc(29) long local29;
		if (GlRenderer.enabled) {
			if (this.shapes == null) {
				local29 = ((long) this.id << 10) + arg0;
			} else {
				local29 = arg0 + ((long) this.id << 10) + ((long) arg3 << 3);
			}
			@Pc(225) LocEntity loc = (LocEntity) LocTypeList.aClass99_14.get(local29);
			@Pc(235) GlModel local235;
			@Pc(265) SoftwareIndexedSprite local265;
			if (loc == null) {
				local235 = this.getGlModel(arg0, false, arg3);
				if (local235 == null) {
					aLocEntity_1.model = null;
					aLocEntity_1.sprite = null;
					return aLocEntity_1;
				}
				if (arg3 == 10 && arg0 > 3) {
					local235.rotateY(256);
				}
				if (arg8) {
					local265 = local235.method4124(arg7);
				} else {
					local265 = null;
				}
				loc = new LocEntity();
				loc.model = local235;
				loc.sprite = local265;
				LocTypeList.aClass99_14.put(loc, local29);
			} else {
				local235 = (GlModel) loc.model;
				local265 = loc.sprite;
			}
			@Pc(298) boolean local298 = this.sharelight & arg6;
			@Pc(330) GlModel local330 = local235.method4117(this.hillchange != 3, this.hillchange == 0, true, true, true, !local298, true, true, true);
			if (this.hillchange != 0) {
				local330.method4110(this.hillchange, this.hillchange_value, local235, arg2, arg5, arg1, arg4, arg9);
			}
			local330.method4111(this.active == 0 && !this.aBoolean214, true, true, this.active == 0, true, false);
			aLocEntity_1.model = local330;
			local330.aBoolean259 = local298;
			aLocEntity_1.sprite = local265;
			return aLocEntity_1;
		}
		if (this.shapes == null) {
			local29 = ((long) this.id << 10) + arg0;
		} else {
			local29 = ((long) arg3 << 3) + (((long) this.id << 10) + arg0);
		}
		@Pc(50) boolean local50;
		if (arg6 && this.sharelight) {
			local29 |= Long.MIN_VALUE;
			local50 = true;
		} else {
			local50 = false;
		}
		@Pc(60) Entity local60 = (Entity) LocTypeList.aClass99_14.get(local29);
		if (local60 == null) {
			@Pc(69) RawModel local69 = this.getRawModel(arg0, arg3);
			if (local69 == null) {
				aLocEntity_1.model = null;
				return aLocEntity_1;
			}
			local69.resetBones();
			if (arg3 == 10 && arg0 > 3) {
				local69.method1682();
			}
			if (local50) {
				local69.aShort19 = (short) (this.ambient + 64);
				local60 = local69;
				local69.aShort18 = (short) (this.contrast * 5 + 768);
				local69.calculateNormals();
			} else {
				local60 = new SoftwareModel(local69, this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50);
			}
			LocTypeList.aClass99_14.put(local60, local29);
		}
		if (local50) {
			local60 = ((RawModel) local60).method1675();
		}
		if (this.hillchange != 0) {
			if (local60 instanceof SoftwareModel) {
				local60 = ((SoftwareModel) local60).method4586(this.hillchange, this.hillchange_value, arg2, arg5, arg1, arg4, arg9, true);
			} else if (local60 instanceof RawModel) {
				local60 = ((RawModel) local60).method1670(this.hillchange, this.hillchange_value, arg2, arg5, arg1, arg4, arg9);
			}
		}
		aLocEntity_1.model = local60;
		return aLocEntity_1;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IILclient!ek;ILclient!tk;I[[IZII[[IIII)Lclient!sm;")
	public final LocEntity method3429(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) SoftwareIndexedSprite arg2, @OriginalArg(3) int arg3, @OriginalArg(4) SeqType arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int[][] arg6, @OriginalArg(7) boolean arg7, @OriginalArg(8) int arg8, @OriginalArg(10) int[][] arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12) {
		@Pc(30) long local30;
		if (!GlRenderer.enabled) {
			if (this.shapes == null) {
				local30 = ((long) this.id << 10) + arg5;
			} else {
				local30 = arg5 + ((long) this.id << 10) + ((long) arg11 << 3);
			}
			@Pc(195) SoftwareModel local195 = (SoftwareModel) LocTypeList.aClass99_36.get(local30);
			if (local195 == null) {
				@Pc(204) RawModel local204 = this.getRawModel(arg5, arg11);
				if (local204 == null) {
					return null;
				}
				local195 = new SoftwareModel(local204, this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50);
				LocTypeList.aClass99_36.put(local195, local30);
			}
			@Pc(234) boolean local234 = false;
			if (arg4 != null) {
				local234 = true;
				local195 = (SoftwareModel) arg4.method4214(arg8, arg10, local195, arg5, arg12);
			}
			if (arg11 == 10 && arg5 > 3) {
				if (!local234) {
					local234 = true;
					local195 = (SoftwareModel) local195.method4568(true, true, true);
				}
				local195.rotateY(256);
			}
			if (this.hillchange != 0) {
				if (!local234) {
					local195 = (SoftwareModel) local195.method4568(true, true, true);
				}
				local195 = local195.method4586(this.hillchange, this.hillchange_value, arg6, arg9, arg1, arg3, arg0, false);
			}
			aLocEntity_1.model = local195;
			return aLocEntity_1;
		}
		if (this.shapes == null) {
			local30 = arg5 + ((long) this.id << 10);
		} else {
			local30 = ((long) arg11 << 3) + (((long) this.id << 10) + arg5);
		}
		@Pc(46) GlModel local46 = (GlModel) LocTypeList.aClass99_36.get(local30);
		if (local46 == null) {
			local46 = this.getGlModel(arg5, true, arg11);
			if (local46 == null) {
				return null;
			}
			local46.createBones();
			local46.method4111(false, false, false, false, false, true);
			LocTypeList.aClass99_36.put(local46, local30);
		}
		@Pc(80) boolean local80 = false;
		@Pc(82) GlModel local82 = local46;
		if (arg4 != null) {
			local82 = (GlModel) arg4.method4216(arg10, arg8, arg12, arg5, local46);
			local80 = true;
		}
		if (arg11 == 10 && arg5 > 3) {
			if (!local80) {
				local82 = (GlModel) local82.method4568(true, true, true);
				local80 = true;
			}
			local82.rotateY(256);
		}
		if (arg7) {
			aLocEntity_1.sprite = local82.method4124(arg2);
		} else {
			aLocEntity_1.sprite = null;
		}
		if (this.hillchange != 0) {
			if (!local80) {
				local82 = (GlModel) local82.method4568(true, true, true);
			}
			local82.method4110(this.hillchange, this.hillchange_value, local46, arg6, arg9, arg1, arg3, arg0);
		}
		aLocEntity_1.model = local82;
		return aLocEntity_1;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!na;II)Lclient!na;")
	public JString getParam(@OriginalArg(0) JString defaultValue, @OriginalArg(2) int key) {
		if (this.params == null) {
			return defaultValue;
		} else {
			@Pc(26) JagStringWrapper value = (JagStringWrapper) this.params.get((long) key);
			return value == null ? defaultValue : value.value;
		}
	}
}