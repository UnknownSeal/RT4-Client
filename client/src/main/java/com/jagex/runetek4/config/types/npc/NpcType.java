package com.jagex.runetek4.config.types.npc;

import com.jagex.runetek4.*;
import com.jagex.runetek4.client.client;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.bas.BasType;
import com.jagex.runetek4.config.types.bas.BasTypeList;
import com.jagex.runetek4.RawModel;
import com.jagex.runetek4.util.IntUtils;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.JagStringWrapper;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!me")
public final class NpcType {

	@OriginalMember(owner = "runetek4.client!id", name = "e", descriptor = "[I")
	public static final int[] anIntArray259 = new int[14];
	@OriginalMember(owner = "runetek4.client!lg", name = "c", descriptor = "[Lclient!tk;")
	public static final SeqType[] aClass144Array1 = new SeqType[14];
	@OriginalMember(owner = "client!ag", name = "cb", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array1 = new AnimFrameset[14];
	@OriginalMember(owner = "client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];
	@OriginalMember(owner = "runetek4.client!rg", name = "A", descriptor = "[I")
	public static final int[] anIntArray492 = new int[14];
	@OriginalMember(owner = "runetek4.client!kd", name = "mb", descriptor = "[Lclient!cl;")
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
	private int[] headModels;

	@OriginalMember(owner = "client!me", name = "o", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!me", name = "t", descriptor = "[[I")
	private int[][] anIntArrayArray28;

	@OriginalMember(owner = "client!me", name = "z", descriptor = "[[I")
	private int[][] modelOffsets;

	@OriginalMember(owner = "client!me", name = "J", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!me", name = "K", descriptor = "Lclient!sc;")
	private HashTable params;

	@OriginalMember(owner = "client!me", name = "X", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!me", name = "cb", descriptor = "[I")
	private int[] modelIndices;

	@OriginalMember(owner = "client!me", name = "hb", descriptor = "[I")
	public int[] multiNpcs;

	@OriginalMember(owner = "client!me", name = "A", descriptor = "I")
	public int idleSound = -1;

	@OriginalMember(owner = "client!me", name = "I", descriptor = "Z")
	public boolean interactive = true;

	@OriginalMember(owner = "client!me", name = "a", descriptor = "I")
	public int size = 1;

	@OriginalMember(owner = "client!me", name = "q", descriptor = "Z")
	public boolean rotationFlag = true;

	@OriginalMember(owner = "client!me", name = "O", descriptor = "I")
	public int runSound = -1;

	@OriginalMember(owner = "client!me", name = "r", descriptor = "S")
	public short spotShadowColor2 = 0;

	@OriginalMember(owner = "runetek4.client!vk", name = "d", descriptor = "Lclient!na;")
	public static final JString aClass100_1010 = JString.parse("null");

	@OriginalMember(owner = "client!me", name = "L", descriptor = "Lclient!na;")
	public JString name = aClass100_1010;

	@OriginalMember(owner = "client!me", name = "x", descriptor = "I")
	public int combatLevel = -1;

	@OriginalMember(owner = "client!me", name = "Z", descriptor = "Z")
	public boolean miniMapDisplay = true;

	@OriginalMember(owner = "client!me", name = "l", descriptor = "I")
	public int cursor1 = -1;

	@OriginalMember(owner = "client!me", name = "U", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!me", name = "V", descriptor = "I")
	public int miniMapMarkerObjectEntry = -1;

	@OriginalMember(owner = "client!me", name = "C", descriptor = "I")
	private int resizeX = 128;

	@OriginalMember(owner = "client!me", name = "N", descriptor = "B")
	public byte spotShadowTrans2 = -16;

	@OriginalMember(owner = "client!me", name = "H", descriptor = "I")
	public int headIcon = -1;

	@OriginalMember(owner = "client!me", name = "F", descriptor = "B")
	public byte loginScreenProperties = 0;

	@OriginalMember(owner = "client!me", name = "e", descriptor = "Z")
	public boolean spotShadow = true;

	@OriginalMember(owner = "client!me", name = "ab", descriptor = "S")
	public short spotShadowColor1 = 0;

	@OriginalMember(owner = "client!me", name = "db", descriptor = "I")
	public int cursor2Op = -1;

	@OriginalMember(owner = "client!me", name = "R", descriptor = "I")
	public int hitBarId = -1;

	@OriginalMember(owner = "client!me", name = "h", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!me", name = "B", descriptor = "Z")
	public boolean topRenderPriority = false;

	@OriginalMember(owner = "client!me", name = "M", descriptor = "I")
	public int rotationSpeed = 32;

	@OriginalMember(owner = "client!me", name = "u", descriptor = "[Lclient!na;")
	public final JString[] ops = new JString[5];

	@OriginalMember(owner = "client!me", name = "ib", descriptor = "I")
	public int walkSound = -1;

	@OriginalMember(owner = "client!me", name = "bb", descriptor = "B")
	public byte shadowColorModifier1 = -96;

	@OriginalMember(owner = "client!me", name = "G", descriptor = "B")
	public byte spawnDirection = 7;

	@OriginalMember(owner = "client!me", name = "S", descriptor = "I")
	public int bastypeid = -1;

	@OriginalMember(owner = "client!me", name = "mb", descriptor = "I")
	public int cursor1Op = -1;

	@OriginalMember(owner = "client!me", name = "gb", descriptor = "I")
	public int soundRadius = 0;

	@OriginalMember(owner = "client!me", name = "E", descriptor = "I")
	private int resizeY = 128;

	@OriginalMember(owner = "client!me", name = "s", descriptor = "I")
	private int multiNpcVarbit = -1;

	@OriginalMember(owner = "client!me", name = "fb", descriptor = "I")
	public int crawlSound = -1;

	@OriginalMember(owner = "client!me", name = "D", descriptor = "I")
	public int iconHeight = -1;

	@OriginalMember(owner = "client!me", name = "Q", descriptor = "I")
	public int cursor2 = -1;

	@OriginalMember(owner = "client!me", name = "lb", descriptor = "I")
	private int multiNpcVarp = -1;

	@OriginalMember(owner = "client!me", name = "ob", descriptor = "I")
	public int attackCursor = -1;

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
	private void decode(@OriginalArg(1) int code, @OriginalArg(2) Packet packet) {
		@Pc(12) int local12;
		@Pc(18) int local18;
		if (code == 1) {
			int length = packet.g1();
			this.modelIndices = new int[length];
			for (int index = 0; index < length; index++) {
				this.modelIndices[index] = packet.g2();
				if (this.modelIndices[index] == 65535) {
					this.modelIndices[index] = -1;
				}
			}
		} else if (code == 2) {
			this.name = packet.gjstr();
		} else if (code == 12) {
			this.size = packet.g1();
		} else if (code >= 30 && code < 35) {
			this.ops[code - 30] = packet.gjstr();
			if (this.ops[code - 30].equalsIgnoreCase(LocalizedText.HIDDEN)) {
				this.ops[code - 30] = null;
			}
		} else if (code == 40) {
			int length = packet.g1();
			this.recol_s = new short[length];
			this.recol_d = new short[length];
			for (int index = 0; index < length; index++) {
				this.recol_s[index] = (short) packet.g2();
				this.recol_d[index] = (short) packet.g2();
			}
		} else if (code == 41) {
			int length = packet.g1();
			this.retex_s = new short[length];
			this.retex_d = new short[length];
			for (local18 = 0; local18 < length; local18++) {
				this.retex_s[local18] = (short) packet.g2();
				this.retex_d[local18] = (short) packet.g2();
			}
		} else if (code == 42) {
			int length = packet.g1();
			this.recol_d_palette = new byte[length];
			for (int index = 0; index < length; index++) {
				this.recol_d_palette[index] = packet.g1s();
			}
		} else if (code == 60) {
			int length = packet.g1();
			this.headModels = new int[length];
			for (int index = 0; index < length; index++) {
				this.headModels[index] = packet.g2();
			}
		} else if (code == 93) {
			this.miniMapDisplay = false;
		} else if (code == 95) {
			this.combatLevel = packet.g2();
		} else if (code == 97) {
			this.resizeX = packet.g2();
		} else if (code == 98) {
			this.resizeY = packet.g2();
		} else if (code == 99) {
			this.topRenderPriority = true;
		} else if (code == 100) {
			this.ambient = packet.g1s();
		} else if (code == 101) {
			this.contrast = packet.g1s() * 5;
		} else if (code == 102) {
			this.headIcon = packet.g2();
		} else if (code == 103) {
			this.rotationSpeed = packet.g2();
		} else if (code == 106 || code == 118) {
			this.multiNpcVarbit = packet.g2();
			if (this.multiNpcVarbit == 65535) {
				this.multiNpcVarbit = -1;
			}

			this.multiNpcVarp = packet.g2();
			if (this.multiNpcVarp == 65535) {
				this.multiNpcVarp = -1;
			}

			int defaultId = -1;
			if (code == 118) {
				defaultId = packet.g2();
				if (defaultId == 65535) {
					defaultId = -1;
				}
			}
			int length = packet.g1();
			this.multiNpcs = new int[length + 2];
			for (int index = 0; index <= length; index++) {
				this.multiNpcs[index] = packet.g2();
				if (this.multiNpcs[index] == 65535) {
					this.multiNpcs[index] = -1;
				}
			}
			this.multiNpcs[length + 1] = defaultId;
		} else if (code == 107) {
			this.interactive = false;
		} else if (code == 109) {
			this.rotationFlag = false;
		} else if (code == 111) {
			this.spotShadow = false;
		} else if (code == 113) {
			this.spotShadowColor1 = (short) packet.g2();
			this.spotShadowColor2 = (short) packet.g2();
		} else if (code == 114) {
			this.shadowColorModifier1 = packet.g1s();
			this.spotShadowTrans2 = packet.g1s();
		} else if (code == 115) {
			packet.g1();
			packet.g1();
		} else if (code == 119) {
			this.loginScreenProperties = packet.g1s();
		} else if (code == 121) {
			this.modelOffsets = new int[this.modelIndices.length][];
			int length = packet.g1();
			for (int index = 0; index < length; index++) {
				int offset = packet.g1();
				@Pc(439) int[] offsets = this.modelOffsets[offset] = new int[3];
				offsets[0] = packet.g1s();
				offsets[1] = packet.g1s();
				offsets[2] = packet.g1s();
			}
		} else if (code == 122) {
			this.hitBarId = packet.g2();
		} else if (code == 123) {
			this.iconHeight = packet.g2();
		} else if (code == 125) {
			this.spawnDirection = packet.g1s();
		} else if (code == 126) {
			this.miniMapMarkerObjectEntry = packet.g2();
		} else if (code == 127) {
			this.bastypeid = packet.g2();
		} else if (code == 128) {
			packet.g1();
		} else if (code == 134) {
			this.idleSound = packet.g2();
			if (this.idleSound == 65535) {
				this.idleSound = -1;
			}
			this.crawlSound = packet.g2();
			if (this.crawlSound == 65535) {
				this.crawlSound = -1;
			}
			this.walkSound = packet.g2();
			if (this.walkSound == 65535) {
				this.walkSound = -1;
			}
			this.runSound = packet.g2();
			if (this.runSound == 65535) {
				this.runSound = -1;
			}
			this.soundRadius = packet.g1();
		} else if (code == 135) {
			this.cursor1Op = packet.g1();
			this.cursor1 = packet.g2();
		} else if (code == 136) {
			this.cursor2Op = packet.g1();
			this.cursor2 = packet.g2();
		} else if (code == 137) {
			this.attackCursor = packet.g2();
		} else if (code == 249) {
			int length = packet.g1();
			if (this.params == null) {
				local18 = IntUtils.bitceil(length);
				this.params = new HashTable(local18);
			}
			for (int index = 0; index < length; index++) {
				@Pc(592) boolean local592 = packet.g1() == 1;
				@Pc(596) int local596 = packet.g3();
				@Pc(605) Node node;
				if (local592) {
					node = new JagStringWrapper(packet.gjstr());
				} else {
					node = new IntWrapper(packet.g4());
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
		if (this.multiNpcVarbit != -1) {
			i = VarpDomain.getVarbitValue(this.multiNpcVarbit);
		} else if (this.multiNpcVarp != -1) {
			i = VarpDomain.activeVarps[this.multiNpcVarp];
		}
		if (i < 0 || i >= this.multiNpcs.length - 1 || this.multiNpcs[i] == -1) {
			@Pc(55) int local55 = this.multiNpcs[this.multiNpcs.length - 1];
			return local55 == -1 ? null : NpcTypeList.get(local55);
		} else {
			return NpcTypeList.get(this.multiNpcs[i]);
		}
	}

	@OriginalMember(owner = "client!me", name = "b", descriptor = "(B)Z")
	public boolean isMultiNpcValid() {
		if (this.multiNpcs == null) {
			return true;
		}
		@Pc(16) int local16 = -1;
		if (this.multiNpcVarbit != -1) {
			local16 = VarpDomain.getVarbitValue(this.multiNpcVarbit);
		} else if (this.multiNpcVarp != -1) {
			local16 = VarpDomain.activeVarps[this.multiNpcVarp];
		}
		if (local16 < 0 || local16 >= this.multiNpcs.length - 1 || this.multiNpcs[local16] == -1) {
			@Pc(62) int local62 = this.multiNpcs[this.multiNpcs.length - 1];
			return local62 != -1;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(I)Z")
	public boolean hasAreaSound() {
		if (this.multiNpcs == null) {
			return this.idleSound != -1 || this.walkSound != -1 || this.runSound != -1;
		}
		for (@Pc(35) int index = 0; index < this.multiNpcs.length; index++) {
			if (this.multiNpcs[index] != -1) {
				@Pc(60) NpcType npcType = NpcTypeList.get(this.multiNpcs[index]);
				if (npcType.idleSound != -1 || npcType.walkSound != -1 || npcType.runSound != -1) {
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
			@Pc(18) IntWrapper local18 = (IntWrapper) this.params.get(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "([Lclient!ub;IBIIIILclient!tk;ILclient!tk;)Lclient!ak;")
	public Model getBodyModel(@OriginalArg(0) PathingEntity_Class147[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) SeqType arg6, @OriginalArg(8) int arg7, @OriginalArg(9) SeqType arg8) {
		if (this.multiNpcs != null) {
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
			for (@Pc(48) int local48 = 0; local48 < this.modelIndices.length; local48++) {
				if (this.modelIndices[local48] != -1 && !NpcTypeList.modelsArchive.isFileReady(this.modelIndices[local48], 0)) {
					local46 = true;
				}
			}
			if (local46) {
				return null;
			}
			@Pc(84) RawModel[] local84 = new RawModel[this.modelIndices.length];
			for (@Pc(86) int local86 = 0; local86 < this.modelIndices.length; local86++) {
				if (this.modelIndices[local86] != -1) {
					local84[local86] = RawModel.create(NpcTypeList.modelsArchive, this.modelIndices[local86]);
					if (this.modelOffsets != null && this.modelOffsets[local86] != null && local84[local86] != null) {
						local84[local86].translate(this.modelOffsets[local86][0], this.modelOffsets[local86][1], this.modelOffsets[local86][2]);
					}
				}
			}
			@Pc(156) BasType local156 = null;
			if (this.bastypeid != -1) {
				local156 = BasTypeList.get(this.bastypeid);
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
						local593.recolor(this.recol_s[local173], client.aShortArray74[this.recol_d_palette[local173] & 0xFF]);
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
				((GlModel) local40).method4111(false, false, false, false, false, true);
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
				@Pc(753) SeqType local753 = SeqTypeList.get(arg0[local235].anInt5396);
				if (local753.frames != null) {
					aClass144Array1[local235] = local753;
					local207 = arg0[local235].anInt5398;
					local46 = true;
					local200 = arg0[local235].anInt5399;
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
						anIntArray492[local235] = arg0[local235].anInt5404;
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
			if (this.resizeX != 128 || this.resizeY != 128) {
				local933.resize(this.resizeX, this.resizeY, this.resizeX);
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
				local1218.method4565(aClass3_Sub2_Sub7Array1[local318], anIntArray259[local318], aClass3_Sub2_Sub7Array5[local318], anIntArray148[local318], anIntArray492[local318] - 1, anIntArray147[local318], local330, aClass144Array1[local318].aBoolean278, this.anIntArrayArray28[local318]);
			}
			local330 <<= 0x1;
		}
		if (local962 != null && local1088 != null) {
			local1218.method4570(local962, local235, local964, local221, arg4 - 1, local200, local1088, local228, local1092, local1040, arg7 - 1, local300, arg8.framegroup, arg8.aBoolean278 | arg6.aBoolean278);
		} else if (local962 != null) {
			local1218.method4558(local962, local235, local964, local221, arg4 - 1, local200, arg8.aBoolean278);
		} else if (local1088 != null) {
			local1218.method4558(local1088, local228, local1092, local1040, arg7 - 1, local300, arg6.aBoolean278);
		}
		for (local318 = 0; local318 < local173; local318++) {
			aClass3_Sub2_Sub7Array1[local318] = null;
			aClass3_Sub2_Sub7Array5[local318] = null;
			aClass144Array1[local318] = null;
		}
		if (this.resizeX != 128 || this.resizeY != 128) {
			local1218.resize(this.resizeX, this.resizeY, this.resizeX);
		}
		return local1218;
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(ILclient!na;Z)Lclient!na;")
	public JString getParam(@OriginalArg(0) int arg0, @OriginalArg(1) JString arg1) {
		if (this.params == null) {
			return arg1;
		} else {
			@Pc(18) JagStringWrapper local18 = (JagStringWrapper) this.params.get(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(Lclient!tk;IIII)Lclient!ak;")
	public Model getHeadModel(@OriginalArg(0) SeqType arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		if (this.multiNpcs != null) {
			@Pc(13) NpcType local13 = this.getMultiNPC();
			return local13 == null ? null : local13.getHeadModel(arg0, arg1, arg2, arg3);
		} else if (this.headModels == null) {
			return null;
		} else {
			@Pc(41) Model local41 = (Model) NpcTypeList.headModels.get(this.id);
			if (local41 == null) {
				@Pc(46) boolean local46 = false;
				for (@Pc(48) int local48 = 0; local48 < this.headModels.length; local48++) {
					if (!NpcTypeList.modelsArchive.isFileReady(this.headModels[local48], 0)) {
						local46 = true;
					}
				}
				if (local46) {
					return null;
				}
				@Pc(82) RawModel[] local82 = new RawModel[this.headModels.length];
				for (@Pc(84) int local84 = 0; local84 < this.headModels.length; local84++) {
					local82[local84] = RawModel.create(NpcTypeList.modelsArchive, this.headModels[local84]);
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
							local119.recolor(this.recol_s[index], client.aShortArray74[this.recol_d_palette[index] & 0xFF]);
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
