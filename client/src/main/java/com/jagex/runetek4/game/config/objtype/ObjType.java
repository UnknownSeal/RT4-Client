package com.jagex.runetek4.game.config.objtype;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.config.seqtype.SeqType;
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

@OriginalClass("client!h")
public final class ObjType {

	@OriginalMember(owner = "client!h", name = "S", descriptor = "[I")
	public static final int[] table = new int[99];

	@OriginalMember(owner = "client!h", name = "a", descriptor = "[S")
	private short[] retex_d;

	@OriginalMember(owner = "client!h", name = "g", descriptor = "I")
	private int mesh;

	@OriginalMember(owner = "client!h", name = "y", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!h", name = "z", descriptor = "[I")
	public int[] countco;

	@OriginalMember(owner = "client!h", name = "G", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!h", name = "I", descriptor = "[S")
	private short[] recol_s;

	@OriginalMember(owner = "client!h", name = "W", descriptor = "[B")
	private byte[] recol_d_palette;

	@OriginalMember(owner = "client!h", name = "Y", descriptor = "I")
	public int anInt2354;

	@OriginalMember(owner = "client!h", name = "nb", descriptor = "Lclient!sc;")
	public IterableMap params;

	@OriginalMember(owner = "client!h", name = "tb", descriptor = "[I")
	public int[] countobj;

	@OriginalMember(owner = "client!h", name = "h", descriptor = "I")
	public int anInt2321 = -1;

	@OriginalMember(owner = "client!h", name = "K", descriptor = "I")
	private int womanwear3 = -1;

	@OriginalMember(owner = "client!h", name = "l", descriptor = "I")
	public int cost = 1;

	@OriginalMember(owner = "client!h", name = "e", descriptor = "I")
	private int womanhead2 = -1;

	@OriginalMember(owner = "client!h", name = "q", descriptor = "I")
	private int manwearxoff = 0;

	@OriginalMember(owner = "client!h", name = "H", descriptor = "I")
	private int womanhead = -1;

	@OriginalMember(owner = "client!h", name = "T", descriptor = "I")
	public int team = 0;

	@OriginalMember(owner = "client!h", name = "E", descriptor = "Lclient!na;")
	public JagString name = Static78.aClass100_464;

	@OriginalMember(owner = "client!h", name = "J", descriptor = "I")
	private int manwearzoff = 0;

	@OriginalMember(owner = "client!h", name = "V", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!h", name = "C", descriptor = "I")
	private int manwear3 = -1;

	@OriginalMember(owner = "client!h", name = "ib", descriptor = "I")
	private int manhead2 = -1;

	@OriginalMember(owner = "client!h", name = "cb", descriptor = "I")
	public int certtemplate = -1;

	@OriginalMember(owner = "client!h", name = "M", descriptor = "I")
	private int womanwearxoff = 0;

	@OriginalMember(owner = "client!h", name = "R", descriptor = "I")
	private int resizey = 128;

	@OriginalMember(owner = "client!h", name = "A", descriptor = "I")
	public int anInt2338 = -1;

	@OriginalMember(owner = "client!h", name = "n", descriptor = "I")
	public int anInt2327 = -1;

	@OriginalMember(owner = "client!h", name = "B", descriptor = "I")
	public int zan2d = 0;

	@OriginalMember(owner = "client!h", name = "u", descriptor = "I")
	public int lenttemplate = -1;

	@OriginalMember(owner = "client!h", name = "hb", descriptor = "I")
	public int lentlink = -1;

	@OriginalMember(owner = "client!h", name = "r", descriptor = "I")
	public int womanwear = -1;

	@OriginalMember(owner = "client!h", name = "F", descriptor = "I")
	private int manwear2 = -1;

	@OriginalMember(owner = "client!h", name = "f", descriptor = "I")
	public int yof2d = 0;

	@OriginalMember(owner = "client!h", name = "X", descriptor = "I")
	public int xan2d = 0;

	@OriginalMember(owner = "client!h", name = "ob", descriptor = "I")
	public int yan2d = 0;

	@OriginalMember(owner = "client!h", name = "pb", descriptor = "I")
	public int dummyitem = 0;

	@OriginalMember(owner = "client!h", name = "w", descriptor = "I")
	public int stackable = 0;

	@OriginalMember(owner = "client!h", name = "ab", descriptor = "I")
	public int certlink = -1;

	@OriginalMember(owner = "client!h", name = "fb", descriptor = "I")
	private int womanwear2 = -1;

	@OriginalMember(owner = "client!h", name = "Z", descriptor = "I")
	public int anInt2355 = -1;

	@OriginalMember(owner = "client!h", name = "mb", descriptor = "I")
	private int resizez = 128;

	@OriginalMember(owner = "client!h", name = "P", descriptor = "Z")
	public boolean members = false;

	@OriginalMember(owner = "client!h", name = "b", descriptor = "I")
	private int womanwearzoff = 0;

	@OriginalMember(owner = "client!h", name = "db", descriptor = "I")
	public int xof2d = 0;

	@OriginalMember(owner = "client!h", name = "sb", descriptor = "I")
	private int manhead = -1;

	@OriginalMember(owner = "client!h", name = "rb", descriptor = "I")
	private int womanwearyoff = 0;

	@OriginalMember(owner = "client!h", name = "eb", descriptor = "I")
	public int manwear = -1;

	@OriginalMember(owner = "client!h", name = "qb", descriptor = "[Lclient!na;")
	public JagString[] ops = new JagString[] { null, null, LocalizedText.TAKE, null, null };

	@OriginalMember(owner = "client!h", name = "ub", descriptor = "I")
	private int resizex = 128;

	@OriginalMember(owner = "client!h", name = "bb", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!h", name = "N", descriptor = "I")
	private int manwearyoff = 0;

	@OriginalMember(owner = "client!h", name = "U", descriptor = "[Lclient!na;")
	public JagString[] iops = new JagString[] { null, null, null, null, LocalizedText.DROP};

	@OriginalMember(owner = "client!h", name = "Ab", descriptor = "I")
	public int zoom2d = 2000;

	@OriginalMember(owner = "client!h", name = "xb", descriptor = "Z")
	public boolean stockmarket = false;

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BLclient!wa;I)V")
	private void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int code) {
		if (code == 1) {
			this.mesh = packet.g2();
		} else if (code == 2) {
			this.name = packet.gjstr();
		} else if (code == 4) {
			this.zoom2d = packet.g2();
		} else if (code == 5) {
			this.xan2d = packet.g2();
		} else if (code == 6) {
			this.yan2d = packet.g2();
		} else if (code == 7) {
			this.xof2d = packet.g2();
			if (this.xof2d > 32767) {
				this.xof2d -= 65536;
			}
		} else if (code == 8) {
			this.yof2d = packet.g2();
			if (this.yof2d > 32767) {
				this.yof2d -= 65536;
			}
		} else if (code == 11) {
			this.stackable = 1;
		} else if (code == 12) {
			this.cost = packet.g4();
		} else if (code == 16) {
			this.members = true;
		} else if (code == 23) {
			this.manwear = packet.g2();
		} else if (code == 24) {
			this.manwear2 = packet.g2();
		} else if (code == 25) {
			this.womanwear = packet.g2();
		} else if (code == 26) {
			this.womanwear2 = packet.g2();
		} else if (code >= 30 && code < 35) {
			this.ops[code - 30] = packet.gjstr();
			if (this.ops[code - 30].method3111(LocalizedText.HIDDEN)) {
				this.ops[code - 30] = null;
			}
		} else if (code >= 35 && code < 40) {
			this.iops[code - 35] = packet.gjstr();
		} else {
			@Pc(179) int local179;
			if (code == 40) {
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
				for (int index = 0; index < length; index++) {
					this.retex_s[index] = (short) packet.g2();
					this.retex_d[index] = (short) packet.g2();
				}
			} else if (code == 42) {
				int length = packet.g1();
				this.recol_d_palette = new byte[length];
				for (int index = 0; index < length; index++) {
					this.recol_d_palette[index] = packet.g1s();
				}
			} else if (code == 65) {
				this.stockmarket = true;
			} else if (code == 78) {
				this.manwear3 = packet.g2();
			} else if (code == 79) {
				this.womanwear3 = packet.g2();
			} else if (code == 90) {
				this.manhead = packet.g2();
			} else if (code == 91) {
				this.womanhead = packet.g2();
			} else if (code == 92) {
				this.manhead2 = packet.g2();
			} else if (code == 93) {
				this.womanhead2 = packet.g2();
			} else if (code == 95) {
				this.zan2d = packet.g2();
			} else if (code == 96) {
				this.dummyitem = packet.g1();
			} else if (code == 97) {
				this.certlink = packet.g2();
			} else if (code == 98) {
				this.certtemplate = packet.g2();
			} else if (code >= 100 && code < 110) {
				if (this.countobj == null) {
					this.countobj = new int[10];
					this.countco = new int[10];
				}
				this.countobj[code - 100] = packet.g2();
				this.countco[code - 100] = packet.g2();
			} else if (code == 110) {
				this.resizex = packet.g2();
			} else if (code == 111) {
				this.resizey = packet.g2();
			} else if (code == 112) {
				this.resizez = packet.g2();
			} else if (code == 113) {
				this.ambient = packet.g1s();
			} else if (code == 114) {
				this.contrast = packet.g1s() * 5;
			} else if (code == 115) {
				this.team = packet.g1();
			} else if (code == 121) {
				this.lentlink = packet.g2();
			} else if (code == 122) {
				this.lenttemplate = packet.g2();
			} else if (code == 125) {
				this.manwearxoff = packet.g1s();
				this.manwearyoff = packet.g1s();
				this.manwearzoff = packet.g1s();
			} else if (code == 126) {
				this.womanwearxoff = packet.g1s();
				this.womanwearyoff = packet.g1s();
				this.womanwearzoff = packet.g1s();
			} else if (code == 127) {
				this.anInt2338 = packet.g1();
				this.anInt2327 = packet.g2();
			} else if (code == 128) {
				this.anInt2355 = packet.g1();
				this.anInt2321 = packet.g2();
			} else if (code == 129) {
				packet.g1();
				packet.g2();
			} else if (code == 130) {
				packet.g1();
				packet.g2();
			} else if (code == 249) {
				int length = packet.g1();
				if (this.params == null) {
					local179 = Static165.bitceil(length);
					this.params = new IterableMap(local179);
				}
				for (int index = 0; index < length; index++) {
					@Pc(510) boolean isString = packet.g1() == 1;
					@Pc(514) int local514 = packet.g3();
					@Pc(523) Node node;
					if (isString) {
						node = new JagStringWrapper(packet.gjstr());
					} else {
						node = new IntWrapper(packet.g4());
					}
					this.params.pushNode(node, local514);
				}
			}
		}
	}

	@OriginalMember(owner = "client!h", name = "c", descriptor = "(I)V")
	public void postDecode() {
	}

	static {
		@Pc(4) int local4 = 0;
		for (@Pc(6) int local6 = 0; local6 < 99; local6++) {
			@Pc(13) int local13 = local6 + 1;
			@Pc(26) int local26 = (int) (Math.pow(2.0D, (double) local13 / 7.0D) * 300.0D + (double) local13);
			local4 += local26;
			table[local6] = local4 / 4;
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZZ)Z")
	public boolean method1816(@OriginalArg(0) boolean arg0) {
		@Pc(6) int local6 = this.manhead;
		@Pc(9) int local9 = this.manhead2;
		if (arg0) {
			local6 = this.womanhead;
			local9 = this.womanhead2;
		}
		if (local6 == -1) {
			return true;
		}
		@Pc(33) boolean local33 = Static230.aClass153_95.requestDownload(local6, 0);
		if (local9 != -1 && !Static230.aClass153_95.requestDownload(local9, 0)) {
			local33 = false;
		}
		return local33;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ILclient!na;I)Lclient!na;")
	public JagString getParam(@OriginalArg(1) JagString arg0, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return arg0;
		} else {
			@Pc(21) JagStringWrapper local21 = (JagStringWrapper) this.params.getNode(arg1);
			return local21 == null ? arg0 : local21.value;
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(II)Lclient!h;")
	public ObjType getMeshAddress(@OriginalArg(0) int n) {
		if (this.countobj != null && n > 1) {
			@Pc(23) int id = -1;
			for (@Pc(25) int index = 0; index < 10; index++) {
				if (n >= this.countco[index] && this.countco[index] != 0) {
					id = this.countobj[index];
				}
			}
			if (id != -1) {
				return Static71.get(id);
			}
		}
		return this;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BZ)Z")
	public boolean method1822(@OriginalArg(1) boolean arg0) {
		@Pc(6) int local6 = this.manwear2;
		@Pc(9) int local9 = this.manwear;
		@Pc(20) int local20 = this.manwear3;
		if (arg0) {
			local20 = this.womanwear3;
			local9 = this.womanwear;
			local6 = this.womanwear2;
		}
		if (local9 == -1) {
			return true;
		}
		@Pc(41) boolean local41 = Static230.aClass153_95.requestDownload(local9, 0);
		if (local6 != -1 && !Static230.aClass153_95.requestDownload(local6, 0)) {
			local41 = false;
		}
		if (local20 != -1 && !Static230.aClass153_95.requestDownload(local20, 0)) {
			local41 = false;
		}
		return local41;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BLclient!h;Lclient!h;)V")
	public void genLent(@OriginalArg(2) ObjType from, @OriginalArg(1) ObjType to) {
		this.recol_d_palette = to.recol_d_palette;
		this.manwearyoff = to.manwearyoff;
		this.params = to.params;
		this.manwear3 = to.manwear3;
		this.womanwear = to.womanwear;
		this.manwearzoff = to.manwearzoff;
		this.iops = new JagString[5];
		this.mesh = from.mesh;
		this.zoom2d = from.zoom2d;
		this.cost = 0;
		this.team = to.team;
		this.womanhead = to.womanhead;
		this.recol_s = to.recol_s;
		this.zan2d = from.zan2d;
		this.manwear2 = to.manwear2;
		this.yan2d = from.yan2d;
		this.manhead = to.manhead;
		this.manhead2 = to.manhead2;
		this.manwearxoff = to.manwearxoff;
		this.xan2d = from.xan2d;
		this.yof2d = from.yof2d;
		this.womanhead2 = to.womanhead2;
		this.womanwearxoff = to.womanwearxoff;
		this.recol_d = to.recol_d;
		this.womanwearyoff = to.womanwearyoff;
		this.womanwearzoff = to.womanwearzoff;
		this.xof2d = from.xof2d;
		this.manwear = to.manwear;
		this.womanwear2 = to.womanwear2;
		this.name = to.name;
		this.retex_d = to.retex_d;
		this.retex_s = to.retex_s;
		this.ops = to.ops;
		this.members = to.members;
		this.womanwear3 = to.womanwear3;
		if (to.iops != null) {
			System.arraycopy(to.iops, 0, this.iops, 0, 4);
		}
		this.iops[4] = LocalizedText.LENT_ITEM_RETURN;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(IIILclient!tk;II)Lclient!ak;")
	public Model method1824(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) SeqType arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (this.countobj != null && arg3 > 1) {
			@Pc(22) int local22 = -1;
			for (@Pc(24) int local24 = 0; local24 < 10; local24++) {
				if (arg3 >= this.countco[local24] && this.countco[local24] != 0) {
					local22 = this.countobj[local24];
				}
			}
			if (local22 != -1) {
				return Static71.get(local22).method1824(arg0, arg1, arg2, 1, arg4);
			}
		}
		@Pc(76) Model local76 = (Model) Static244.aClass99_32.get(this.anInt2354);
		if (local76 == null) {
			@Pc(85) ModelUnlit local85 = ModelUnlit.get(Static230.aClass153_95, this.mesh);
			if (local85 == null) {
				return null;
			}
			@Pc(97) int local97;
			if (this.recol_s != null) {
				for (local97 = 0; local97 < this.recol_s.length; local97++) {
					if (this.recol_d_palette == null || local97 >= this.recol_d_palette.length) {
						local85.recolor(this.recol_s[local97], this.recol_d[local97]);
					} else {
						local85.recolor(this.recol_s[local97], Static259.aShortArray87[this.recol_d_palette[local97] & 0xFF]);
					}
				}
			}
			if (this.retex_s != null) {
				for (local97 = 0; local97 < this.retex_s.length; local97++) {
					local85.retexture(this.retex_s[local97], this.retex_d[local97]);
				}
			}
			local76 = local85.method1679(this.ambient + 64, this.contrast + 768, -50, -10, -50);
			if (this.resizex != 128 || this.resizey != 128 || this.resizez != 128) {
				local76.resize(this.resizex, this.resizey, this.resizez);
			}
			local76.aBoolean303 = true;
			if (GlRenderer.enabled) {
				((GlModel) local76).method4111(false, false, false, false, false, true);
			}
			Static244.aClass99_32.method3095(local76, this.anInt2354);
		}
		if (arg2 != null) {
			local76 = arg2.method4215(local76, arg0, arg1, arg4);
		}
		return local76;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(III)I")
	public int getParam(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return arg0;
		} else {
			@Pc(25) IntWrapper local25 = (IntWrapper) this.params.getNode(arg1);
			return local25 == null ? arg0 : local25.value;
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZB)Lclient!gb;")
	public ModelUnlit method1830(@OriginalArg(0) boolean arg0) {
		@Pc(4) int local4 = this.manhead2;
		@Pc(17) int local17 = this.manhead;
		if (arg0) {
			local4 = this.womanhead2;
			local17 = this.womanhead;
		}
		if (local17 == -1) {
			return null;
		}
		@Pc(36) ModelUnlit local36 = ModelUnlit.get(Static230.aClass153_95, local17);
		if (local4 != -1) {
			@Pc(44) ModelUnlit local44 = ModelUnlit.get(Static230.aClass153_95, local4);
			@Pc(55) ModelUnlit[] local55 = new ModelUnlit[] { local36, local44 };
			local36 = new ModelUnlit(local55, 2);
		}
		@Pc(66) int local66;
		if (this.recol_s != null) {
			for (local66 = 0; local66 < this.recol_s.length; local66++) {
				local36.recolor(this.recol_s[local66], this.recol_d[local66]);
			}
		}
		if (this.retex_s != null) {
			for (local66 = 0; local66 < this.retex_s.length; local66++) {
				local36.retexture(this.retex_s[local66], this.retex_d[local66]);
			}
		}
		return local36;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZI)Lclient!gb;")
	public ModelUnlit method1831(@OriginalArg(0) boolean arg0) {
		@Pc(4) int local4 = this.manwear;
		@Pc(18) int local18 = this.manwear2;
		@Pc(21) int local21 = this.manwear3;
		if (arg0) {
			local21 = this.womanwear3;
			local4 = this.womanwear;
			local18 = this.womanwear2;
		}
		if (local4 == -1) {
			return null;
		}
		@Pc(43) ModelUnlit local43 = ModelUnlit.get(Static230.aClass153_95, local4);
		if (local18 != -1) {
			@Pc(54) ModelUnlit local54 = ModelUnlit.get(Static230.aClass153_95, local18);
			if (local21 == -1) {
				@Pc(68) ModelUnlit[] local68 = new ModelUnlit[] { local43, local54 };
				local43 = new ModelUnlit(local68, 2);
			} else {
				@Pc(81) ModelUnlit local81 = ModelUnlit.get(Static230.aClass153_95, local21);
				@Pc(96) ModelUnlit[] local96 = new ModelUnlit[] { local43, local54, local81 };
				local43 = new ModelUnlit(local96, 3);
			}
		}
		if (!arg0 && (this.manwearxoff != 0 || this.manwearyoff != 0 || this.manwearzoff != 0)) {
			local43.method1672(this.manwearxoff, this.manwearyoff, this.manwearzoff);
		}
		if (arg0 && (this.womanwearxoff != 0 || this.womanwearyoff != 0 || this.womanwearzoff != 0)) {
			local43.method1672(this.womanwearxoff, this.womanwearyoff, this.womanwearzoff);
		}
		@Pc(165) int local165;
		if (this.recol_s != null) {
			for (local165 = 0; local165 < this.recol_s.length; local165++) {
				local43.recolor(this.recol_s[local165], this.recol_d[local165]);
			}
		}
		if (this.retex_s != null) {
			for (local165 = 0; local165 < this.retex_s.length; local165++) {
				local43.retexture(this.retex_s[local165], this.retex_d[local165]);
			}
		}
		return local43;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(Lclient!h;Lclient!h;Z)V")
	public void genCert(@OriginalArg(1) ObjType from, @OriginalArg(0) ObjType to) {
		this.name = to.name;
		this.zoom2d = from.zoom2d;
		this.recol_s = from.recol_s;
		this.recol_d = from.recol_d;
		this.xan2d = from.xan2d;
		this.yan2d = from.yan2d;
		this.retex_d = from.retex_d;
		this.mesh = from.mesh;
		this.recol_d_palette = from.recol_d_palette;
		this.zan2d = from.zan2d;
		this.cost = to.cost;
		this.stackable = 1;
		this.yof2d = from.yof2d;
		this.xof2d = from.xof2d;
		this.retex_s = from.retex_s;
		this.members = to.members;
	}

	@OriginalMember(owner = "client!h", name = "d", descriptor = "(I)Lclient!w;")
	public SoftwareModel method1834() {
		@Pc(11) ModelUnlit local11 = ModelUnlit.get(Static230.aClass153_95, this.mesh);
		if (local11 == null) {
			return null;
		}
		@Pc(21) int local21;
		if (this.recol_s != null) {
			for (local21 = 0; local21 < this.recol_s.length; local21++) {
				if (this.recol_d_palette == null || local21 >= this.recol_d_palette.length) {
					local11.recolor(this.recol_s[local21], this.recol_d[local21]);
				} else {
					local11.recolor(this.recol_s[local21], Static259.aShortArray87[this.recol_d_palette[local21] & 0xFF]);
				}
			}
		}
		if (this.retex_s != null) {
			for (local21 = 0; local21 < this.retex_s.length; local21++) {
				local11.retexture(this.retex_s[local21], this.retex_d[local21]);
			}
		}
		@Pc(107) SoftwareModel local107 = local11.method1671(this.ambient + 64, 768 - -this.contrast);
		if (this.resizex != 128 || this.resizey != 128 || this.resizez != 128) {
			local107.resize(this.resizex, this.resizey, this.resizez);
		}
		return local107;
	}
}
