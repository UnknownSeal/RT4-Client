package com.jagex.runetek4.config;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.config.bastype.BASType;
import com.jagex.runetek4.graphics.ModelUnlit;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.core.datastruct.JagStringWrapper;
import com.jagex.runetek4.core.datastruct.Node;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!me")
public final class NPCType {

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
	private IterableMap params;

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

	@OriginalMember(owner = "client!me", name = "L", descriptor = "Lclient!na;")
	public JString name = Static266.aClass100_1010;

	@OriginalMember(owner = "client!me", name = "x", descriptor = "I")
	public int vislevel = -1;

	@OriginalMember(owner = "client!me", name = "Z", descriptor = "Z")
	public boolean minimap = true;

	@OriginalMember(owner = "client!me", name = "l", descriptor = "I")
	public int anInt3719 = -1;

	@OriginalMember(owner = "client!me", name = "U", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!me", name = "V", descriptor = "I")
	public int anInt3739 = -1;

	@OriginalMember(owner = "client!me", name = "C", descriptor = "I")
	private int resizeh = 128;

	@OriginalMember(owner = "client!me", name = "N", descriptor = "B")
	public byte spotshadowtrans_2 = -16;

	@OriginalMember(owner = "client!me", name = "H", descriptor = "I")
	public int anInt3732 = -1;

	@OriginalMember(owner = "client!me", name = "F", descriptor = "B")
	public byte walkflags = 0;

	@OriginalMember(owner = "client!me", name = "e", descriptor = "Z")
	public boolean spotshadow = true;

	@OriginalMember(owner = "client!me", name = "ab", descriptor = "S")
	public short spotshadowcolour_1 = 0;

	@OriginalMember(owner = "client!me", name = "db", descriptor = "I")
	public int anInt3743 = -1;

	@OriginalMember(owner = "client!me", name = "R", descriptor = "I")
	public int anInt3736 = -1;

	@OriginalMember(owner = "client!me", name = "h", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!me", name = "B", descriptor = "Z")
	public boolean drawabove = false;

	@OriginalMember(owner = "client!me", name = "M", descriptor = "I")
	public int turnspeed = 32;

	@OriginalMember(owner = "client!me", name = "u", descriptor = "[Lclient!na;")
	public final JString[] ops = new JString[5];

	@OriginalMember(owner = "client!me", name = "ib", descriptor = "I")
	public int bgsound_walk = -1;

	@OriginalMember(owner = "client!me", name = "bb", descriptor = "B")
	public byte spotshadowtrans_1 = -96;

	@OriginalMember(owner = "client!me", name = "G", descriptor = "B")
	public byte respawndir = 7;

	@OriginalMember(owner = "client!me", name = "S", descriptor = "I")
	public int bas = -1;

	@OriginalMember(owner = "client!me", name = "mb", descriptor = "I")
	public int anInt3750 = -1;

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
	public int anInt3735 = -1;

	@OriginalMember(owner = "client!me", name = "lb", descriptor = "I")
	private int multivarp = -1;

	@OriginalMember(owner = "client!me", name = "ob", descriptor = "I")
	public int cursorattack = -1;

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(code, packet);
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(IILclient!wa;)V")
	private void decode(@OriginalArg(1) int code, @OriginalArg(2) Packet packet) {
		@Pc(12) int local12;
		@Pc(18) int local18;
		if (code == 1) {
			int length = packet.g1();
			this.models = new int[length];
			for (int index = 0; index < length; index++) {
				this.models[index] = packet.g2();
				if (this.models[index] == 65535) {
					this.models[index] = -1;
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
			this.heads = new int[length];
			for (int index = 0; index < length; index++) {
				this.heads[index] = packet.g2();
			}
		} else if (code == 93) {
			this.minimap = false;
		} else if (code == 95) {
			this.vislevel = packet.g2();
		} else if (code == 97) {
			this.resizeh = packet.g2();
		} else if (code == 98) {
			this.resizev = packet.g2();
		} else if (code == 99) {
			this.drawabove = true;
		} else if (code == 100) {
			this.ambient = packet.g1s();
		} else if (code == 101) {
			this.contrast = packet.g1s() * 5;
		} else if (code == 102) {
			this.anInt3732 = packet.g2();
		} else if (code == 103) {
			this.turnspeed = packet.g2();
		} else if (code == 106 || code == 118) {
			this.multivarbit = packet.g2();
			if (this.multivarbit == 65535) {
				this.multivarbit = -1;
			}

			this.multivarp = packet.g2();
			if (this.multivarp == 65535) {
				this.multivarp = -1;
			}

			int defaultId = -1;
			if (code == 118) {
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
		} else if (code == 107) {
			this.active = false;
		} else if (code == 109) {
			this.walksmoothing = false;
		} else if (code == 111) {
			this.spotshadow = false;
		} else if (code == 113) {
			this.spotshadowcolour_1 = (short) packet.g2();
			this.spotshadowcolour_2 = (short) packet.g2();
		} else if (code == 114) {
			this.spotshadowtrans_1 = packet.g1s();
			this.spotshadowtrans_2 = packet.g1s();
		} else if (code == 115) {
			packet.g1();
			packet.g1();
		} else if (code == 119) {
			this.walkflags = packet.g1s();
		} else if (code == 121) {
			this.modeloffset = new int[this.models.length][];
			int length = packet.g1();
			for (int index = 0; index < length; index++) {
				int offset = packet.g1();
				@Pc(439) int[] offsets = this.modeloffset[offset] = new int[3];
				offsets[0] = packet.g1s();
				offsets[1] = packet.g1s();
				offsets[2] = packet.g1s();
			}
		} else if (code == 122) {
			this.anInt3736 = packet.g2();
		} else if (code == 123) {
			this.overlayheight = packet.g2();
		} else if (code == 125) {
			this.respawndir = packet.g1s();
		} else if (code == 126) {
			this.anInt3739 = packet.g2();
		} else if (code == 127) {
			this.bas = packet.g2();
		} else if (code == 128) {
			packet.g1();
		} else if (code == 134) {
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
		} else if (code == 135) {
			this.anInt3750 = packet.g1();
			this.anInt3719 = packet.g2();
		} else if (code == 136) {
			this.anInt3743 = packet.g1();
			this.anInt3735 = packet.g2();
		} else if (code == 137) {
			this.cursorattack = packet.g2();
		} else if (code == 249) {
			int length = packet.g1();
			if (this.params == null) {
				local18 = Static165.bitceil(length);
				this.params = new IterableMap(local18);
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
				this.params.pushNode(node, local596);
			}
		}
	}

	@OriginalMember(owner = "client!me", name = "b", descriptor = "(I)V")
	public void postDecode() {
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(B)Lclient!me;")
	public NPCType getMultiNPC() {
		@Pc(5) int i = -1;
		if (this.multivarbit != -1) {
			i = Static155.method2945(this.multivarbit);
		} else if (this.multivarp != -1) {
			i = Static7.varps[this.multivarp];
		}
		if (i < 0 || i >= this.multinpc.length - 1 || this.multinpc[i] == -1) {
			@Pc(55) int local55 = this.multinpc[this.multinpc.length - 1];
			return local55 == -1 ? null : Static214.get(local55);
		} else {
			return Static214.get(this.multinpc[i]);
		}
	}

	@OriginalMember(owner = "client!me", name = "b", descriptor = "(B)Z")
	public boolean method2933() {
		if (this.multinpc == null) {
			return true;
		}
		@Pc(16) int local16 = -1;
		if (this.multivarbit != -1) {
			local16 = Static155.method2945(this.multivarbit);
		} else if (this.multivarp != -1) {
			local16 = Static7.varps[this.multivarp];
		}
		if (local16 < 0 || local16 >= this.multinpc.length - 1 || this.multinpc[local16] == -1) {
			@Pc(62) int local62 = this.multinpc[this.multinpc.length - 1];
			return local62 != -1;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(I)Z")
	public boolean hasBackgroundSound() {
		if (this.multinpc == null) {
			return this.bgsound != -1 || this.bgsound_walk != -1 || this.bgsound_run != -1;
		}
		for (@Pc(35) int index = 0; index < this.multinpc.length; index++) {
			if (this.multinpc[index] != -1) {
				@Pc(60) NPCType npcType = Static214.get(this.multinpc[index]);
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
			@Pc(18) IntWrapper local18 = (IntWrapper) this.params.getNode(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "([Lclient!ub;IBIIIILclient!tk;ILclient!tk;)Lclient!ak;")
	public Model method2937(@OriginalArg(0) Class147[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) SeqType arg6, @OriginalArg(8) int arg7, @OriginalArg(9) SeqType arg8) {
		if (this.multinpc != null) {
			@Pc(13) NPCType local13 = this.getMultiNPC();
			return local13 == null ? null : local13.method2937(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
		}
		@Pc(40) Model local40 = (Model) Static125.aClass99_18.get(this.id);
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
				if (this.models[local48] != -1 && !Static86.aClass153_37.requestDownload(this.models[local48], 0)) {
					local46 = true;
				}
			}
			if (local46) {
				return null;
			}
			@Pc(84) ModelUnlit[] local84 = new ModelUnlit[this.models.length];
			for (@Pc(86) int local86 = 0; local86 < this.models.length; local86++) {
				if (this.models[local86] != -1) {
					local84[local86] = ModelUnlit.get(Static86.aClass153_37, this.models[local86]);
					if (this.modeloffset != null && this.modeloffset[local86] != null && local84[local86] != null) {
						local84[local86].translate(this.modeloffset[local86][0], this.modeloffset[local86][1], this.modeloffset[local86][2]);
					}
				}
			}
			@Pc(156) BASType local156 = null;
			if (this.bas != -1) {
				local156 = Static90.method1856(this.bas);
			}
			if (local156 != null && local156.anIntArrayArray7 != null) {
				for (local173 = 0; local173 < local156.anIntArrayArray7.length; local173++) {
					if (local156.anIntArrayArray7[local173] != null && local84.length > local173 && local84[local173] != null) {
						local200 = local156.anIntArrayArray7[local173][2];
						local207 = local156.anIntArrayArray7[local173][3];
						local214 = local156.anIntArrayArray7[local173][4];
						local221 = local156.anIntArrayArray7[local173][1];
						local228 = local156.anIntArrayArray7[local173][5];
						local235 = local156.anIntArrayArray7[local173][0];
						if (this.anIntArrayArray28 == null) {
							this.anIntArrayArray28 = new int[local156.anIntArrayArray7.length][];
						}
						if (this.anIntArrayArray28[local173] == null) {
							@Pc(259) int[] local259 = this.anIntArrayArray28[local173] = new int[15];
							if (local207 == 0 && local214 == 0 && local228 == 0) {
								local259[13] = -local221;
								local259[14] = -local200;
								local259[0] = local259[4] = local259[8] = 32768;
								local259[12] = -local235;
							} else {
								local300 = MathUtils.anIntArray225[local207] >> 1;
								@Pc(306) int local306 = MathUtils.anIntArray223[local207] >> 1;
								@Pc(312) int local312 = MathUtils.anIntArray225[local214] >> 1;
								local318 = MathUtils.anIntArray225[local228] >> 1;
								local324 = MathUtils.anIntArray223[local214] >> 1;
								local330 = MathUtils.anIntArray223[local228] >> 1;
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
			@Pc(593) ModelUnlit local593;
			if (local84.length == 1) {
				local593 = local84[0];
			} else {
				local593 = new ModelUnlit(local84, local84.length);
			}
			if (this.recol_s != null) {
				for (local173 = 0; local173 < this.recol_s.length; local173++) {
					if (this.recol_d_palette == null || this.recol_d_palette.length <= local173) {
						local593.recolor(this.recol_s[local173], this.recol_d[local173]);
					} else {
						local593.recolor(this.recol_s[local173], Static232.aShortArray74[this.recol_d_palette[local173] & 0xFF]);
					}
				}
			}
			if (this.retex_s != null) {
				for (local173 = 0; local173 < this.retex_s.length; local173++) {
					local593.retexture(this.retex_s[local173], this.retex_d[local173]);
				}
			}
			local40 = local593.method1679(this.ambient + 64, this.contrast + 850, -30, -50, -30);
			if (GlRenderer.enabled) {
				((GlModel) local40).method4111(false, false, false, false, false, true);
			}
			Static125.aClass99_18.put(local40, this.id);
		}
		local46 = false;
		@Pc(721) boolean local721 = false;
		@Pc(723) boolean local723 = false;
		@Pc(725) boolean local725 = false;
		local173 = arg0 == null ? 0 : arg0.length;
		for (local235 = 0; local235 < local173; local235++) {
			if (arg0[local235] != null) {
				@Pc(753) SeqType local753 = Static36.method941(arg0[local235].anInt5396);
				if (local753.anIntArray473 != null) {
					Static146.aClass144Array1[local235] = local753;
					local207 = arg0[local235].anInt5398;
					local46 = true;
					local200 = arg0[local235].anInt5399;
					local214 = local753.anIntArray473[local200];
					Static6.aClass3_Sub2_Sub7Array1[local235] = Static72.method1566(local214 >>> 16);
					local214 &= 0xFFFF;
					Static107.anIntArray259[local235] = local214;
					if (Static6.aClass3_Sub2_Sub7Array1[local235] != null) {
						local723 |= Static6.aClass3_Sub2_Sub7Array1[local235].method903(local214);
						local721 |= Static6.aClass3_Sub2_Sub7Array1[local235].method901(local214);
						local725 |= local753.aBoolean278;
					}
					if ((local753.aBoolean277 || Static204.tween) && local207 != -1 && local753.anIntArray473.length > local207) {
						Static71.anIntArray147[local235] = local753.frames[local200];
						Static214.anIntArray492[local235] = arg0[local235].anInt5404;
						local228 = local753.anIntArray473[local207];
						Static131.aClass3_Sub2_Sub7Array5[local235] = Static72.method1566(local228 >>> 16);
						local228 &= 0xFFFF;
						Static61.anIntArray148[local235] = local228;
						if (Static131.aClass3_Sub2_Sub7Array5[local235] != null) {
							local723 |= Static131.aClass3_Sub2_Sub7Array5[local235].method903(local228);
							local721 |= Static131.aClass3_Sub2_Sub7Array5[local235].method901(local228);
						}
					} else {
						Static71.anIntArray147[local235] = 0;
						Static214.anIntArray492[local235] = 0;
						Static131.aClass3_Sub2_Sub7Array5[local235] = null;
						Static61.anIntArray148[local235] = -1;
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
			local235 = arg8.anIntArray473[arg5];
			local228 = local235 >>> 16;
			local235 &= 0xFFFF;
			local962 = Static72.method1566(local228);
			if (local962 != null) {
				local723 |= local962.method903(local235);
				local721 |= local962.method901(local235);
				local725 |= arg8.aBoolean278;
			}
			if ((arg8.aBoolean277 || Static204.tween) && arg3 != -1 && arg8.anIntArray473.length > arg3) {
				local200 = arg8.frames[arg5];
				local221 = arg8.anIntArray473[arg3];
				local1040 = local221 >>> 16;
				local221 &= 0xFFFF;
				if (local228 == local1040) {
					local964 = local962;
				} else {
					local964 = Static72.method1566(local221 >>> 16);
				}
				if (local964 != null) {
					local723 |= local964.method903(local221);
					local721 |= local964.method901(local221);
				}
			}
		}
		local228 = -1;
		local1040 = -1;
		@Pc(1088) AnimFrameset local1088 = null;
		local300 = 0;
		@Pc(1092) AnimFrameset local1092 = null;
		if (arg6 != null) {
			local228 = arg6.anIntArray473[arg2];
			local324 = local228 >>> 16;
			local228 &= 0xFFFF;
			local1088 = Static72.method1566(local324);
			if (local1088 != null) {
				local723 |= local1088.method903(local228);
				local721 |= local1088.method901(local228);
				local725 |= arg6.aBoolean278;
			}
			if ((arg6.aBoolean277 || Static204.tween) && arg1 != -1 && arg1 < arg6.anIntArray473.length) {
				local300 = arg6.frames[arg2];
				local1040 = arg6.anIntArray473[arg1];
				local318 = local1040 >>> 16;
				local1040 &= 0xFFFF;
				if (local318 == local324) {
					local1092 = local1088;
				} else {
					local1092 = Static72.method1566(local1040 >>> 16);
				}
				if (local1092 != null) {
					local723 |= local1092.method903(local1040);
					local721 |= local1092.method901(local1040);
				}
			}
		}
		@Pc(1218) Model local1218 = local40.method4572(!local721, !local723, !local725);
		local330 = 1;
		for (local318 = 0; local318 < local173; local318++) {
			if (Static6.aClass3_Sub2_Sub7Array1[local318] != null) {
				local1218.method4565(Static6.aClass3_Sub2_Sub7Array1[local318], Static107.anIntArray259[local318], Static131.aClass3_Sub2_Sub7Array5[local318], Static61.anIntArray148[local318], Static214.anIntArray492[local318] - 1, Static71.anIntArray147[local318], local330, Static146.aClass144Array1[local318].aBoolean278, this.anIntArrayArray28[local318]);
			}
			local330 <<= 0x1;
		}
		if (local962 != null && local1088 != null) {
			local1218.method4570(local962, local235, local964, local221, arg4 - 1, local200, local1088, local228, local1092, local1040, arg7 - 1, local300, arg8.aBooleanArray123, arg8.aBoolean278 | arg6.aBoolean278);
		} else if (local962 != null) {
			local1218.method4558(local962, local235, local964, local221, arg4 - 1, local200, arg8.aBoolean278);
		} else if (local1088 != null) {
			local1218.method4558(local1088, local228, local1092, local1040, arg7 - 1, local300, arg6.aBoolean278);
		}
		for (local318 = 0; local318 < local173; local318++) {
			Static6.aClass3_Sub2_Sub7Array1[local318] = null;
			Static131.aClass3_Sub2_Sub7Array5[local318] = null;
			Static146.aClass144Array1[local318] = null;
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
			@Pc(18) JagStringWrapper local18 = (JagStringWrapper) this.params.getNode(arg0);
			return local18 == null ? arg1 : local18.value;
		}
	}

	@OriginalMember(owner = "client!me", name = "a", descriptor = "(Lclient!tk;IIII)Lclient!ak;")
	public Model getHeadModel(@OriginalArg(0) SeqType arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		if (this.multinpc != null) {
			@Pc(13) NPCType local13 = this.getMultiNPC();
			return local13 == null ? null : local13.getHeadModel(arg0, arg1, arg2, arg3);
		} else if (this.heads == null) {
			return null;
		} else {
			@Pc(41) Model local41 = (Model) Static262.aClass99_35.get(this.id);
			if (local41 == null) {
				@Pc(46) boolean local46 = false;
				for (@Pc(48) int local48 = 0; local48 < this.heads.length; local48++) {
					if (!Static86.aClass153_37.requestDownload(this.heads[local48], 0)) {
						local46 = true;
					}
				}
				if (local46) {
					return null;
				}
				@Pc(82) ModelUnlit[] local82 = new ModelUnlit[this.heads.length];
				for (@Pc(84) int local84 = 0; local84 < this.heads.length; local84++) {
					local82[local84] = ModelUnlit.get(Static86.aClass153_37, this.heads[local84]);
				}
				@Pc(119) ModelUnlit local119;
				if (local82.length == 1) {
					local119 = local82[0];
				} else {
					local119 = new ModelUnlit(local82, local82.length);
				}

				if (this.recol_s != null) {
					for (int index = 0; index < this.recol_s.length; index++) {
						if (this.recol_d_palette == null || index >= this.recol_d_palette.length) {
							local119.recolor(this.recol_s[index], this.recol_d[index]);
						} else {
							local119.recolor(this.recol_s[index], Static232.aShortArray74[this.recol_d_palette[index] & 0xFF]);
						}
					}
				}
				if (this.retex_s != null) {
					for (int index = 0; index < this.retex_s.length; index++) {
						local119.retexture(this.retex_s[index], this.retex_d[index]);
					}
				}
				local41 = local119.method1679(64, 768, -50, -10, -50);
				Static262.aClass99_35.put(local41, this.id);
			}
			if (arg0 != null) {
				local41 = arg0.method4215(local41, arg2, arg1, arg3);
			}
			return local41;
		}
	}
}
