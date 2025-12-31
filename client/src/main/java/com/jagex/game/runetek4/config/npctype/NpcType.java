package com.jagex.game.runetek4.config.npctype;

import com.jagex.graphics.animation.AnimFrameset;
import com.jagex.client.Client;
import com.jagex.game.runetek4.config.seqtype.SeqType;
import com.jagex.game.runetek4.config.seqtype.SeqTypeList;
import com.jagex.game.runetek4.config.bastype.BasType;
import com.jagex.game.runetek4.config.bastype.BasTypeList;
import com.jagex.game.state.VarpDomain;
import com.jagex.graphics.model.Model;
import com.jagex.graphics.model.RawModel;
import com.jagex.entity.PathingEntityAnimation;
import com.jagex.graphics.gl.GlModel;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.core.utils.string.JString;
import com.jagex.core.utils.string.LocalizedText;
import com.jagex.math.IntMath;
import com.jagex.core.utils.math.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.core.datastruct.key.IntNode;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.key.JstringNode;
import com.jagex.core.datastruct.Node;
import com.jagex.core.io.Packet;

@OriginalClass("client!me")
public final class NpcType {

	@OriginalMember(owner = "client!id", name = "e", descriptor = "[I")
	public static final int[] anIntArray259 = new int[14];

	@OriginalMember(owner = "client!lg", name = "c", descriptor = "[Lclient!tk;")
	public static final SeqType[] aClass144Array1 = new SeqType[14];

	@OriginalMember(owner = "client!ag", name = "cb", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array1 = new AnimFrameset[14];

	@OriginalMember(owner = "client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];

	@OriginalMember(owner = "client!rg", name = "A", descriptor = "[I")
	public static final int[] anIntArray492 = new int[14];

	@OriginalMember(owner = "client!kd", name = "mb", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array5 = new AnimFrameset[14];

	@OriginalMember(owner = "client!en", name = "t", descriptor = "[I")
	public static final int[] anIntArray148 = new int[14];

	@OriginalMember(owner = "client!me", name = "b", descriptor = "[S")
	private short[] retex_d;

	@OriginalMember(owner = "client!me", name = "c", descriptor = "[B")
	private byte[] recol_d_palette;

	@OriginalMember(owner = "client!me", name = "d", descriptor = "[S")
	private short[] recol_s;

	@OriginalMember(owner = "client!me", name = "g", descriptor = "[I")
	private int[] heads;

	@OriginalMember(owner = "client!me", name = "o", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!me", name = "t", descriptor = "[[I")
	private int[][] anIntArrayArray28;

	@OriginalMember(owner = "client!me", name = "z", descriptor = "[[I")
	private int[][] modeloffset;

	@OriginalMember(owner = "client!me", name = "J", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!me", name = "K", descriptor = "Lclient!sc;")
	private IterableHashTable params;

	@OriginalMember(owner = "client!me", name = "X", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!me", name = "cb", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!me", name = "hb", descriptor = "[I")
	public int[] multinpc;

	@OriginalMember(owner = "client!me", name = "A", descriptor = "I")
	public int bgsound = -1;

	@OriginalMember(owner = "client!me", name = "I", descriptor = "Z")
	public boolean active = true;

	@OriginalMember(owner = "client!me", name = "a", descriptor = "I")
	public int size = 1;

	@OriginalMember(owner = "client!me", name = "q", descriptor = "Z")
	public boolean walksmoothing = true;

	@OriginalMember(owner = "client!me", name = "O", descriptor = "I")
	public int bgsound_run = -1;

	@OriginalMember(owner = "client!me", name = "r", descriptor = "S")
	public short spotshadowcolour_2 = 0;

	@OriginalMember(owner = "client!vk", name = "d", descriptor = "Lclient!na;")
	public static final JString aClass100_1010 = JString.parse("null");

	@OriginalMember(owner = "client!me", name = "L", descriptor = "Lclient!na;")
	public JString name = aClass100_1010;

	@OriginalMember(owner = "client!me", name = "x", descriptor = "I")
	public int vislevel = -1;

	@OriginalMember(owner = "client!me", name = "Z", descriptor = "Z")
	public boolean minimap = true;

	@OriginalMember(owner = "client!me", name = "l", descriptor = "I")
	public int cursor1 = -1;

	@OriginalMember(owner = "client!me", name = "U", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!me", name = "V", descriptor = "I")
	public int miniMapMarkerObjectEntry = -1;

	@OriginalMember(owner = "client!me", name = "C", descriptor = "I")
	private int resizeh = 128;

	@OriginalMember(owner = "client!me", name = "N", descriptor = "B")
	public byte spotshadowtrans_2 = -16;

	@OriginalMember(owner = "client!me", name = "H", descriptor = "I")
	public int headIcon = -1;

	@OriginalMember(owner = "client!me", name = "F", descriptor = "B")
	public byte walkflags = 0;

	@OriginalMember(owner = "client!me", name = "e", descriptor = "Z")
	public boolean spotshadow = true;

	@OriginalMember(owner = "client!me", name = "ab", descriptor = "S")
	public short spotshadowcolour_1 = 0;

	@OriginalMember(owner = "client!me", name = "db", descriptor = "I")
	public int cursor2Op = -1;

	@OriginalMember(owner = "client!me", name = "R", descriptor = "I")
	public int hitBarId = -1;

	@OriginalMember(owner = "client!me", name = "h", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!me", name = "B", descriptor = "Z")
	public boolean drawabove = false;

	@OriginalMember(owner = "client!me", name = "M", descriptor = "I")
	public int turnspeed = 32;

	@OriginalMember(owner = "client!me", name = "u", descriptor = "[Lclient!na;")
	public final JString[] op = new JString[5];

	@OriginalMember(owner = "client!me", name = "ib", descriptor = "I")
	public int bgsound_walk = -1;

	@OriginalMember(owner = "client!me", name = "bb", descriptor = "B")
	public byte spotshadowtrans_1 = -96;

	@OriginalMember(owner = "client!me", name = "G", descriptor = "B")
	public byte respawndir = 7;

	@OriginalMember(owner = "client!me", name = "S", descriptor = "I")
	public int nas = -1;

	@OriginalMember(owner = "client!me", name = "mb", descriptor = "I")
	public int cursor1Op = -1;

	@OriginalMember(owner = "client!me", name = "gb", descriptor = "I")
	public int bgsound_range = 0;

	@OriginalMember(owner = "client!me", name = "E", descriptor = "I")
	private int resizev = 128;

	@OriginalMember(owner = "client!me", name = "s", descriptor = "I")
	private int multivarbit = -1;

	@OriginalMember(owner = "client!me", name = "fb", descriptor = "I")
	public int bgsound_crawl = -1;

	@OriginalMember(owner = "client!me", name = "D", descriptor = "I")
	public int overlayheight = -1;

	@OriginalMember(owner = "client!me", name = "Q", descriptor = "I")
	public int cursor2 = -1;

	@OriginalMember(owner = "client!me", name = "lb", descriptor = "I")
	private int multivarp = -1;

	@OriginalMember(owner = "client!me", name = "ob", descriptor = "I")
	public int cursorattack = -1;

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(opcode, packet);
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(IILclient!wa;)V")
	private void decode(@OriginalArg(1) int opcode, @OriginalArg(2) Packet packet) {
		@Pc(18) int local18;
		if (opcode == 1) {
			int length = packet.g1();
			this.models = new int[length];
			for (int index = 0; index < length; index++) {
				this.models[index] = packet.g2();
				if (this.models[index] == 65535) {
					this.models[index] = -1;
				}
			}
		} else if (opcode == 2) {
			this.name = packet.gjstr();
		} else if (opcode == 12) {
			this.size = packet.g1();
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
			for (local18 = 0; local18 < length; local18++) {
				this.retex_s[local18] = (short) packet.g2();
				this.retex_d[local18] = (short) packet.g2();
			}
		} else if (opcode == 42) {
			int length = packet.g1();
			this.recol_d_palette = new byte[length];
			for (int index = 0; index < length; index++) {
				this.recol_d_palette[index] = packet.g1s();
			}
		} else if (opcode == 60) {
			int length = packet.g1();
			this.heads = new int[length];
			for (int index = 0; index < length; index++) {
				this.heads[index] = packet.g2();
			}
		} else if (opcode == 93) {
			this.minimap = false;
		} else if (opcode == 95) {
			this.vislevel = packet.g2();
		} else if (opcode == 97) {
			this.resizeh = packet.g2();
		} else if (opcode == 98) {
			this.resizev = packet.g2();
		} else if (opcode == 99) {
			this.drawabove = true;
		} else if (opcode == 100) {
			this.ambient = packet.g1s();
		} else if (opcode == 101) {
			this.contrast = packet.g1s() * 5;
		} else if (opcode == 102) {
			this.headIcon = packet.g2();
		} else if (opcode == 103) {
			this.turnspeed = packet.g2();
		} else if (opcode == 106 || opcode == 118) {
			this.multivarbit = packet.g2();
			if (this.multivarbit == 65535) {
				this.multivarbit = -1;
			}

			this.multivarp = packet.g2();
			if (this.multivarp == 65535) {
				this.multivarp = -1;
			}

			int defaultId = -1;
			if (opcode == 118) {
				defaultId = packet.g2();
				if (defaultId == 65535) {
					defaultId = -1;
				}
			}
			int length = packet.g1();
			this.multinpc = new int[length + 2];
			for (int index = 0; index <= length; index++) {
				this.multinpc[index] = packet.g2();
				if (this.multinpc[index] == 65535) {
					this.multinpc[index] = -1;
				}
			}
			this.multinpc[length + 1] = defaultId;
		} else if (opcode == 107) {
			this.active = false;
		} else if (opcode == 109) {
			this.walksmoothing = false;
		} else if (opcode == 111) {
			this.spotshadow = false;
		} else if (opcode == 113) {
			this.spotshadowcolour_1 = (short) packet.g2();
			this.spotshadowcolour_2 = (short) packet.g2();
		} else if (opcode == 114) {
			this.spotshadowtrans_1 = packet.g1s();
			this.spotshadowtrans_2 = packet.g1s();
		} else if (opcode == 115) {
			packet.g1();
			packet.g1();
		} else if (opcode == 119) {
			this.walkflags = packet.g1s();
		} else if (opcode == 121) {
			this.modeloffset = new int[this.models.length][];
			int length = packet.g1();
			for (int index = 0; index < length; index++) {
				int offset = packet.g1();
				@Pc(439) int[] offsets = this.modeloffset[offset] = new int[3];
				offsets[0] = packet.g1s();
				offsets[1] = packet.g1s();
				offsets[2] = packet.g1s();
			}
		} else if (opcode == 122) {
			this.hitBarId = packet.g2();
		} else if (opcode == 123) {
			this.overlayheight = packet.g2();
		} else if (opcode == 125) {
			this.respawndir = packet.g1s();
		} else if (opcode == 126) {
			this.miniMapMarkerObjectEntry = packet.g2();
		} else if (opcode == 127) {
			this.nas = packet.g2();
		} else if (opcode == 128) {
			packet.g1();
		} else if (opcode == 134) {
			this.bgsound = packet.g2();
			if (this.bgsound == 65535) {
				this.bgsound = -1;
			}
			this.bgsound_crawl = packet.g2();
			if (this.bgsound_crawl == 65535) {
				this.bgsound_crawl = -1;
			}
			this.bgsound_walk = packet.g2();
			if (this.bgsound_walk == 65535) {
				this.bgsound_walk = -1;
			}
			this.bgsound_run = packet.g2();
			if (this.bgsound_run == 65535) {
				this.bgsound_run = -1;
			}
			this.bgsound_range = packet.g1();
		} else if (opcode == 135) {
			this.cursor1Op = packet.g1();
			this.cursor1 = packet.g2();
		} else if (opcode == 136) {
			this.cursor2Op = packet.g1();
			this.cursor2 = packet.g2();
		} else if (opcode == 137) {
			this.cursorattack = packet.g2();
		} else if (opcode == 249) {
			int length = packet.g1();
			if (this.params == null) {
				local18 = IntMath.bitceil(length);
				this.params = new IterableHashTable(local18);
			}
			for (int index = 0; index < length; index++) {
				@Pc(592) boolean local592 = packet.g1() == 1;
				@Pc(596) int local596 = packet.g3();
				@Pc(605) Node node;
				if (local592) {
					node = new JstringNode(packet.gjstr());
				} else {
					node = new IntNode(packet.g4());
				}
				this.params.put(node, local596);
			}
		}
	}

	@OriginalMember(owner = "client!me", name = "b", descriptor = "(I)V")
	public void postDecode() {
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(B)Lclient!me;")
	public NpcType getMultiNPC() {
		@Pc(5) int i = -1;
		if (this.multivarbit != -1) {
			i = VarpDomain.getVarbitValue(this.multivarbit);
		} else if (this.multivarp != -1) {
			i = VarpDomain.activeVarps[this.multivarp];
		}
		if (i < 0 || i >= this.multinpc.length - 1 || this.multinpc[i] == -1) {
			@Pc(55) int local55 = this.multinpc[this.multinpc.length - 1];
			return local55 == -1 ? null : NpcTypeList.get(local55);
		} else {
			return NpcTypeList.get(this.multinpc[i]);
		}
	}

	@OriginalMember(owner = "client!me", name = "b", descriptor = "(B)Z")
	public boolean isMultiNpcValid() {
		if (this.multinpc == null) {
			return true;
		}
		@Pc(16) int local16 = -1;
		if (this.multivarbit != -1) {
			local16 = VarpDomain.getVarbitValue(this.multivarbit);
		} else if (this.multivarp != -1) {
			local16 = VarpDomain.activeVarps[this.multivarp];
		}
		if (local16 < 0 || local16 >= this.multinpc.length - 1 || this.multinpc[local16] == -1) {
			@Pc(62) int local62 = this.multinpc[this.multinpc.length - 1];
			return local62 != -1;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(I)Z")
	public boolean hasAreaSound() {
		if (this.multinpc == null) {
			return this.bgsound != -1 || this.bgsound_walk != -1 || this.bgsound_run != -1;
		}
		for (@Pc(35) int index = 0; index < this.multinpc.length; index++) {
			if (this.multinpc[index] != -1) {
				@Pc(60) NpcType npcType = NpcTypeList.get(this.multinpc[index]);
				if (npcType.bgsound != -1 || npcType.bgsound_walk != -1 || npcType.bgsound_run != -1) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(III)I")
	public int getParam(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return arg1;
		} else {
			@Pc(18) IntNode local18 = (IntNode) this.params.get(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "([Lclient!ub;IBIIIILclient!tk;ILclient!tk;)Lclient!ak;")
	public Model getBodyModel(@OriginalArg(0) PathingEntityAnimation[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) SeqType arg6, @OriginalArg(8) int arg7, @OriginalArg(9) SeqType arg8) {
		if (this.multinpc != null) {
			@Pc(13) NpcType local13 = this.getMultiNPC();
			return local13 == null ? null : local13.getBodyModel(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
		}
		@Pc(40) Model local40 = (Model) NpcTypeList.models.get(this.id);
		@Pc(46) boolean local46;
		@Pc(173) int local173;
		@Pc(235) int local235;
		@Pc(221) int local221;
		@Pc(200) int local200;
		@Pc(207) int local207;
		@Pc(214) int local214;
		@Pc(228) int local228;
		@Pc(300) int local300;
		@Pc(324) int local324;
		@Pc(318) int local318;
		@Pc(330) int local330;
		if (local40 == null) {
			local46 = false;
			for (@Pc(48) int local48 = 0; local48 < this.models.length; local48++) {
				if (this.models[local48] != -1 && !NpcTypeList.modelsArchive.isFileReady(this.models[local48], 0)) {
					local46 = true;
				}
			}
			if (local46) {
				return null;
			}
			@Pc(84) RawModel[] local84 = new RawModel[this.models.length];
			for (@Pc(86) int local86 = 0; local86 < this.models.length; local86++) {
				if (this.models[local86] != -1) {
					local84[local86] = RawModel.create(NpcTypeList.modelsArchive, this.models[local86]);
					if (this.modeloffset != null && this.modeloffset[local86] != null && local84[local86] != null) {
						local84[local86].translate(this.modeloffset[local86][0], this.modeloffset[local86][1], this.modeloffset[local86][2]);
					}
				}
			}
			@Pc(156) BasType local156 = null;
			if (this.nas != -1) {
				local156 = BasTypeList.get(this.nas);
			}
			if (local156 != null && local156.modelRotateTranslate != null) {
				for (local173 = 0; local173 < local156.modelRotateTranslate.length; local173++) {
					if (local156.modelRotateTranslate[local173] != null && local84.length > local173 && local84[local173] != null) {
						local200 = local156.modelRotateTranslate[local173][2];
						local207 = local156.modelRotateTranslate[local173][3];
						local214 = local156.modelRotateTranslate[local173][4];
						local221 = local156.modelRotateTranslate[local173][1];
						local228 = local156.modelRotateTranslate[local173][5];
						local235 = local156.modelRotateTranslate[local173][0];
						if (this.anIntArrayArray28 == null) {
							this.anIntArrayArray28 = new int[local156.modelRotateTranslate.length][];
						}
						if (this.anIntArrayArray28[local173] == null) {
							@Pc(259) int[] local259 = this.anIntArrayArray28[local173] = new int[15];
							if (local207 == 0 && local214 == 0 && local228 == 0) {
								local259[13] = -local221;
								local259[14] = -local200;
								local259[0] = local259[4] = local259[8] = 32768;
								local259[12] = -local235;
							} else {
								local300 = MathUtils.cos[local207] >> 1;
								@Pc(306) int local306 = MathUtils.sin[local207] >> 1;
								@Pc(312) int local312 = MathUtils.cos[local214] >> 1;
								local318 = MathUtils.cos[local228] >> 1;
								local324 = MathUtils.sin[local214] >> 1;
								local330 = MathUtils.sin[local228] >> 1;
								local259[3] = local300 * local330 + 16384 >> 15;
								local259[8] = local300 * local312 + 16384 >> 15;
								local259[5] = -local306;
								@Pc(363) int local363 = local306 * local330 + 16384 >> 15;
								@Pc(371) int local371 = local318 * local306 + 16384 >> 15;
								local259[1] = -local330 * local312 + local371 * local324 + 16384 >> 15;
								local259[2] = local324 * local300 + 16384 >> 15;
								local259[6] = -local324 * local318 + local363 * local312 + 16384 >> 15;
								local259[14] = local259[8] * -local200 + -local221 * local259[5] + local259[2] * -local235 + 16384 >> 15;
								local259[4] = local300 * local318 + 16384 >> 15;
								local259[7] = -local324 * -local330 + local371 * local312 + 16384 >> 15;
								local259[0] = local324 * local363 + local312 * local318 + 16384 >> 15;
								local259[12] = local259[6] * -local200 + local259[3] * -local221 + -local235 * local259[0] + 16384 >> 15;
								local259[13] = -local200 * local259[7] + -local235 * local259[1] + -local221 * local259[4] + 16384 >> 15;
							}
							local259[10] = local221;
							local259[9] = local235;
							local259[11] = local200;
						}
						if (local207 != 0 || local214 != 0 || local228 != 0) {
							local84[local173].method1684(local207, local214, local228);
						}
						if (local235 != 0 || local221 != 0 || local200 != 0) {
							local84[local173].translate(local235, local221, local200);
						}
					}
				}
			}
			@Pc(593) RawModel local593;
			if (local84.length == 1) {
				local593 = local84[0];
			} else {
				local593 = new RawModel(local84, local84.length);
			}
			if (this.recol_s != null) {
				for (local173 = 0; local173 < this.recol_s.length; local173++) {
					if (this.recol_d_palette == null || this.recol_d_palette.length <= local173) {
						local593.recolor(this.recol_s[local173], this.recol_d[local173]);
					} else {
						local593.recolor(this.recol_s[local173], Client.aShortArray74[this.recol_d_palette[local173] & 0xFF]);
					}
				}
			}
			if (this.retex_s != null) {
				for (local173 = 0; local173 < this.retex_s.length; local173++) {
					local593.retexture(this.retex_s[local173], this.retex_d[local173]);
				}
			}
			local40 = local593.createModel(this.ambient + 64, this.contrast + 850, -30, -50, -30);
			if (GlRenderer.enabled) {
				((GlModel) local40).updateBuffers(false, false, false, false, false, true);
			}
			NpcTypeList.models.put(local40, this.id);
		}
		local46 = false;
		@Pc(721) boolean local721 = false;
		@Pc(723) boolean local723 = false;
		@Pc(725) boolean local725 = false;
		local173 = arg0 == null ? 0 : arg0.length;
		for (local235 = 0; local235 < local173; local235++) {
			if (arg0[local235] != null) {
				@Pc(753) SeqType local753 = SeqTypeList.get(arg0[local235].sequenceId);
				if (local753.frames != null) {
					aClass144Array1[local235] = local753;
					local207 = arg0[local235].direction;
					local46 = true;
					local200 = arg0[local235].frameIndex;
					local214 = local753.frames[local200];
					aClass3_Sub2_Sub7Array1[local235] = SeqTypeList.getAnimFrameset(local214 >>> 16);
					local214 &= 0xFFFF;
					anIntArray259[local235] = local214;
					if (aClass3_Sub2_Sub7Array1[local235] != null) {
						local723 |= aClass3_Sub2_Sub7Array1[local235].isColorTransformed(local214);
						local721 |= aClass3_Sub2_Sub7Array1[local235].isAlphaTransformed(local214);
						local725 |= local753.aBoolean278;
					}
					if ((local753.tween || SeqType.applyTweening) && local207 != -1 && local753.frames.length > local207) {
						anIntArray147[local235] = local753.frameDelay[local200];
						anIntArray492[local235] = arg0[local235].loopCount;
						local228 = local753.frames[local207];
						aClass3_Sub2_Sub7Array5[local235] = SeqTypeList.getAnimFrameset(local228 >>> 16);
						local228 &= 0xFFFF;
						anIntArray148[local235] = local228;
						if (aClass3_Sub2_Sub7Array5[local235] != null) {
							local723 |= aClass3_Sub2_Sub7Array5[local235].isColorTransformed(local228);
							local721 |= aClass3_Sub2_Sub7Array5[local235].isAlphaTransformed(local228);
						}
					} else {
						anIntArray147[local235] = 0;
						anIntArray492[local235] = 0;
						aClass3_Sub2_Sub7Array5[local235] = null;
						anIntArray148[local235] = -1;
					}
				}
			}
		}
		if (!local46 && arg8 == null && arg6 == null) {
			@Pc(933) Model local933 = local40.method4572(true, true, true);
			if (this.resizeh != 128 || this.resizev != 128) {
				local933.resize(this.resizeh, this.resizev, this.resizeh);
			}
			return local933;
		}
		local221 = -1;
		local235 = -1;
		local200 = 0;
		@Pc(962) AnimFrameset local962 = null;
		@Pc(964) AnimFrameset local964 = null;
		@Pc(1040) int local1040;
		if (arg8 != null) {
			local235 = arg8.frames[arg5];
			local228 = local235 >>> 16;
			local235 &= 0xFFFF;
			local962 = SeqTypeList.getAnimFrameset(local228);
			if (local962 != null) {
				local723 |= local962.isColorTransformed(local235);
				local721 |= local962.isAlphaTransformed(local235);
				local725 |= arg8.aBoolean278;
			}
			if ((arg8.tween || SeqType.applyTweening) && arg3 != -1 && arg8.frames.length > arg3) {
				local200 = arg8.frameDelay[arg5];
				local221 = arg8.frames[arg3];
				local1040 = local221 >>> 16;
				local221 &= 0xFFFF;
				if (local228 == local1040) {
					local964 = local962;
				} else {
					local964 = SeqTypeList.getAnimFrameset(local221 >>> 16);
				}
				if (local964 != null) {
					local723 |= local964.isColorTransformed(local221);
					local721 |= local964.isAlphaTransformed(local221);
				}
			}
		}
		local228 = -1;
		local1040 = -1;
		@Pc(1088) AnimFrameset local1088 = null;
		local300 = 0;
		@Pc(1092) AnimFrameset local1092 = null;
		if (arg6 != null) {
			local228 = arg6.frames[arg2];
			local324 = local228 >>> 16;
			local228 &= 0xFFFF;
			local1088 = SeqTypeList.getAnimFrameset(local324);
			if (local1088 != null) {
				local723 |= local1088.isColorTransformed(local228);
				local721 |= local1088.isAlphaTransformed(local228);
				local725 |= arg6.aBoolean278;
			}
			if ((arg6.tween || SeqType.applyTweening) && arg1 != -1 && arg1 < arg6.frames.length) {
				local300 = arg6.frameDelay[arg2];
				local1040 = arg6.frames[arg1];
				local318 = local1040 >>> 16;
				local1040 &= 0xFFFF;
				if (local318 == local324) {
					local1092 = local1088;
				} else {
					local1092 = SeqTypeList.getAnimFrameset(local1040 >>> 16);
				}
				if (local1092 != null) {
					local723 |= local1092.isColorTransformed(local1040);
					local721 |= local1092.isAlphaTransformed(local1040);
				}
			}
		}
		@Pc(1218) Model local1218 = local40.method4572(!local721, !local723, !local725);
		local330 = 1;
		for (local318 = 0; local318 < local173; local318++) {
			if (aClass3_Sub2_Sub7Array1[local318] != null) {
				local1218.blendAnimationWeighted(aClass3_Sub2_Sub7Array1[local318], anIntArray259[local318], aClass3_Sub2_Sub7Array5[local318], anIntArray148[local318], anIntArray492[local318] - 1, anIntArray147[local318], local330, aClass144Array1[local318].aBoolean278, this.anIntArrayArray28[local318]);
			}
			local330 <<= 0x1;
		}
		if (local962 != null && local1088 != null) {
			local1218.blendLayeredAnimation(local962, local235, local964, local221, arg4 - 1, local200, local1088, local228, local1092, local1040, arg7 - 1, local300, arg8.framegroup, arg8.aBoolean278 | arg6.aBoolean278);
		} else if (local962 != null) {
			local1218.blendAnimation(local962, local235, local964, local221, arg4 - 1, local200, arg8.aBoolean278);
		} else if (local1088 != null) {
			local1218.blendAnimation(local1088, local228, local1092, local1040, arg7 - 1, local300, arg6.aBoolean278);
		}
		for (local318 = 0; local318 < local173; local318++) {
			aClass3_Sub2_Sub7Array1[local318] = null;
			aClass3_Sub2_Sub7Array5[local318] = null;
			aClass144Array1[local318] = null;
		}
		if (this.resizeh != 128 || this.resizev != 128) {
			local1218.resize(this.resizeh, this.resizev, this.resizeh);
		}
		return local1218;
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(ILclient!na;Z)Lclient!na;")
	public JString getParam(@OriginalArg(0) int arg0, @OriginalArg(1) JString arg1) {
		if (this.params == null) {
			return arg1;
		} else {
			@Pc(18) JstringNode local18 = (JstringNode) this.params.get(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(Lclient!tk;IIII)Lclient!ak;")
	public Model getHeadModel(@OriginalArg(0) SeqType arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		if (this.multinpc != null) {
			@Pc(13) NpcType local13 = this.getMultiNPC();
			return local13 == null ? null : local13.getHeadModel(arg0, arg1, arg2, arg3);
		} else if (this.heads == null) {
			return null;
		} else {
			@Pc(41) Model local41 = (Model) NpcTypeList.headModels.get(this.id);
			if (local41 == null) {
				@Pc(46) boolean local46 = false;
				for (@Pc(48) int local48 = 0; local48 < this.heads.length; local48++) {
					if (!NpcTypeList.modelsArchive.isFileReady(this.heads[local48], 0)) {
						local46 = true;
					}
				}
				if (local46) {
					return null;
				}
				@Pc(82) RawModel[] local82 = new RawModel[this.heads.length];
				for (@Pc(84) int local84 = 0; local84 < this.heads.length; local84++) {
					local82[local84] = RawModel.create(NpcTypeList.modelsArchive, this.heads[local84]);
				}
				@Pc(119) RawModel local119;
				if (local82.length == 1) {
					local119 = local82[0];
				} else {
					local119 = new RawModel(local82, local82.length);
				}

				if (this.recol_s != null) {
					for (int index = 0; index < this.recol_s.length; index++) {
						if (this.recol_d_palette == null || index >= this.recol_d_palette.length) {
							local119.recolor(this.recol_s[index], this.recol_d[index]);
						} else {
							local119.recolor(this.recol_s[index], Client.aShortArray74[this.recol_d_palette[index] & 0xFF]);
						}
					}
				}
				if (this.retex_s != null) {
					for (int index = 0; index < this.retex_s.length; index++) {
						local119.retexture(this.retex_s[index], this.retex_d[index]);
					}
				}
				local41 = local119.createModel(64, 768, -50, -10, -50);
				NpcTypeList.headModels.put(local41, this.id);
			}
			if (arg0 != null) {
				local41 = arg0.method4215(local41, arg2, arg1, arg3);
			}
			return local41;
		}
	}
}
