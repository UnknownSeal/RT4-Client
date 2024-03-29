package com.jagex.runetek4.game.config.loctype;

import com.jagex.runetek4.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.core.datastruct.JagStringWrapper;
import com.jagex.runetek4.core.datastruct.Node;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!pb")
public final class LocType {

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "[S")
	private short[] retex_s;

	@OriginalMember(owner = "client!pb", name = "b", descriptor = "[S")
	private short[] recol_s;

	@OriginalMember(owner = "client!pb", name = "n", descriptor = "[I")
	private int[] models;

	@OriginalMember(owner = "client!pb", name = "v", descriptor = "[S")
	private short[] retex_d;

	@OriginalMember(owner = "client!pb", name = "B", descriptor = "Lclient!sc;")
	private IterableMap params;

	@OriginalMember(owner = "client!pb", name = "H", descriptor = "[S")
	private short[] recol_d;

	@OriginalMember(owner = "client!pb", name = "P", descriptor = "[B")
	private byte[] recol_d_palette;

	@OriginalMember(owner = "client!pb", name = "X", descriptor = "[I")
	private int[] shapes;

	@OriginalMember(owner = "client!pb", name = "db", descriptor = "[I")
	public int[] multiloc;

	@OriginalMember(owner = "client!pb", name = "hb", descriptor = "I")
	public int anInt4426;

	@OriginalMember(owner = "client!pb", name = "wb", descriptor = "[I")
	public int[] bgsound_random;

	@OriginalMember(owner = "client!pb", name = "e", descriptor = "I")
	public int width = 1;

	@OriginalMember(owner = "client!pb", name = "i", descriptor = "Z")
	public boolean forcedecor = false;

	@OriginalMember(owner = "client!pb", name = "l", descriptor = "I")
	public int height = 1;

	@OriginalMember(owner = "client!pb", name = "C", descriptor = "Z")
	public boolean aBoolean211 = false;

	@OriginalMember(owner = "client!pb", name = "u", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!pb", name = "E", descriptor = "Lclient!na;")
	public JagString name = Static81.aClass100_475;

	@OriginalMember(owner = "client!pb", name = "D", descriptor = "Z")
	public boolean hardshadow = true;

	@OriginalMember(owner = "client!pb", name = "t", descriptor = "I")
	public int anInt4406 = -1;

	@OriginalMember(owner = "client!pb", name = "R", descriptor = "I")
	public int bgsound_maxdelay = 0;

	@OriginalMember(owner = "client!pb", name = "S", descriptor = "I")
	public int mapsceneicon = -1;

	@OriginalMember(owner = "client!pb", name = "G", descriptor = "B")
	private byte hillskew_mode = 0;

	@OriginalMember(owner = "client!pb", name = "r", descriptor = "Z")
	public boolean members = false;

	@OriginalMember(owner = "client!pb", name = "T", descriptor = "I")
	public int anInt4416 = -1;

	@OriginalMember(owner = "client!pb", name = "w", descriptor = "I")
	private int xoff = 0;

	@OriginalMember(owner = "client!pb", name = "W", descriptor = "I")
	public int bgsound_mindelay = 0;

	@OriginalMember(owner = "client!pb", name = "h", descriptor = "I")
	public int mapelement = -1;

	@OriginalMember(owner = "client!pb", name = "L", descriptor = "Z")
	public boolean aBoolean214 = false;

	@OriginalMember(owner = "client!pb", name = "Y", descriptor = "I")
	public int anInt4420 = -1;

	@OriginalMember(owner = "client!pb", name = "A", descriptor = "S")
	private short hillskew_value = -1;

	@OriginalMember(owner = "client!pb", name = "g", descriptor = "I")
	private int resizez = 128;

	@OriginalMember(owner = "client!pb", name = "z", descriptor = "[Lclient!na;")
	public JagString[] op = new JagString[5];

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
	public boolean shadow = true;

	@OriginalMember(owner = "client!pb", name = "nb", descriptor = "I")
	private int multivarp = -1;

	@OriginalMember(owner = "client!pb", name = "bb", descriptor = "I")
	public int anInt4423 = -1;

	@OriginalMember(owner = "client!pb", name = "pb", descriptor = "I")
	public int anInt4433 = 0;

	@OriginalMember(owner = "client!pb", name = "m", descriptor = "Z")
	public boolean aBoolean207 = true;

	@OriginalMember(owner = "client!pb", name = "qb", descriptor = "I")
	private int zoff = 0;

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "I")
	public int anInt4395 = 0;

	@OriginalMember(owner = "client!pb", name = "jb", descriptor = "I")
	public int walloff = 16;

	@OriginalMember(owner = "client!pb", name = "tb", descriptor = "Z")
	public boolean mapsceneiconrotate = false;

	@OriginalMember(owner = "client!pb", name = "N", descriptor = "I")
	private int yoff = 0;

	@OriginalMember(owner = "client!pb", name = "k", descriptor = "I")
	public int bgsound_range = 0;

	@OriginalMember(owner = "client!pb", name = "p", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!pb", name = "mb", descriptor = "I")
	public int anInt4430 = -1;

	@OriginalMember(owner = "client!pb", name = "I", descriptor = "Z")
	public boolean aBoolean213 = false;

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
			@Pc(9) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int code) {
		@Pc(38) int local38;
		if (code == 1) {
			int length = packet.g1();
			if (length > 0) {
				if (this.shapes == null || Static87.aBoolean130) {
					this.shapes = new int[length];
					this.models = new int[length];
					for (int index = 0; index < length; index++) {
						this.shapes[index] = packet.g2();
						this.models[index] = packet.g1();
					}
				} else {
					packet.pos += length * 3;
				}
			}
		} else if (code == 2) {
			this.name = packet.gjstr();
		} else if (code == 5) {
			int length = packet.g1();
			if (length > 0) {
				if (this.shapes == null || Static87.aBoolean130) {
					this.shapes = new int[length];
					this.models = null;
					for (int index = 0; index < length; index++) {
						this.shapes[index] = packet.g2();
					}
				} else {
					packet.pos += length * 2;
				}
			}
		} else if (code == 14) {
			this.width = packet.g1();
		} else if (code == 15) {
			this.height = packet.g1();
		} else if (code == 17) {
			this.blockwalk = 0;
			this.aBoolean207 = false;
		} else if (code == 18) {
			this.aBoolean207 = false;
		} else if (code == 19) {
			this.active = packet.g1();
		} else if (code == 21) {
			this.hillskew_mode = 1;
		} else if (code == 22) {
			this.sharelight = true;
		} else if (code == 23) {
			this.occlude = true;
		} else if (code == 24) {
			this.anInt4430 = packet.g2();
			if (this.anInt4430 == 65535) {
				this.anInt4430 = -1;
			}
		} else if (code == 27) {
			this.blockwalk = 1;
		} else if (code == 28) {
			this.walloff = packet.g1();
		} else if (code == 29) {
			this.ambient = packet.g1s();
		} else if (code == 39) {
			this.contrast = packet.g1s() * 5;
		} else if (code >= 30 && code < 35) {
			this.op[code - 30] = packet.gjstr();
			if (this.op[code - 30].method3111(LocalizedText.HIDDEN)) {
				this.op[code - 30] = null;
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
		} else if (code == 60) {
			this.mapelement = packet.g2();
		} else if (code == 62) {
			this.mirror = true;
		} else if (code == 64) {
			this.shadow = false;
		} else if (code == 65) {
			this.resizex = packet.g2();
		} else if (code == 66) {
			this.resizey = packet.g2();
		} else if (code == 67) {
			this.resizez = packet.g2();
		} else if (code == 69) {
			this.anInt4433 = packet.g1();
		} else if (code == 70) {
			this.xoff = packet.g2s();
		} else if (code == 71) {
			this.yoff = packet.g2s();
		} else if (code == 72) {
			this.zoff = packet.g2s();
		} else if (code == 73) {
			this.forcedecor = true;
		} else if (code == 74) {
			this.breakroutefinding = true;
		} else if (code == 75) {
			this.raiseobject = packet.g1();
		} else if (code == 77 || code == 92) {
			this.multivarbit = packet.g2();
			if (this.multivarbit == 65535) {
				this.multivarbit = -1;
			}
			this.multivarp = packet.g2();
			if (this.multivarp == 65535) {
				this.multivarp = -1;
			}

			int defaultId = -1;

			if (code == 92) {
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
		} else if (code == 78) {
			this.bgsound_sound = packet.g2();
			this.bgsound_range = packet.g1();
		} else if (code == 79) {
			this.bgsound_mindelay = packet.g2();
			this.bgsound_maxdelay = packet.g2();
			this.bgsound_range = packet.g1();
			int length = packet.g1();
			this.bgsound_random = new int[length];
			for (int index = 0; index < length; index++) {
				this.bgsound_random[index] = packet.g2();
			}
		} else if (code == 81) {
			this.hillskew_mode = 2;
			this.hillskew_value = (short) (packet.g1() * 256);
		} else if (code == 82) {
			this.istexture = true;
		} else if (code == 88) {
			this.hardshadow = false;
		} else if (code == 89) {
			this.randomanimframe = false;
		} else if (code == 90) {
			this.aBoolean211 = true;
		} else if (code == 91) {
			this.members = true;
		} else if (code == 93) {
			this.hillskew_mode = 3;
			this.hillskew_value = (short) packet.g2();
		} else if (code == 94) {
			this.hillskew_mode = 4;
		} else if (code == 95) {
			this.hillskew_mode = 5;
		} else if (code == 96) {
			this.aBoolean213 = true;
		} else if (code == 97) {
			this.mapsceneiconrotate = true;
		} else if (code == 98) {
			this.aBoolean214 = true;
		} else if (code == 99) {
			this.anInt4406 = packet.g1();
			this.anInt4416 = packet.g2();
		} else if (code == 100) {
			this.anInt4420 = packet.g1();
			this.anInt4423 = packet.g2();
		} else if (code == 101) {
			this.anInt4395 = packet.g1();
		} else if (code == 102) {
			this.mapsceneicon = packet.g2();
		} else if (code == 249) {
			int length = packet.g1();
			if (this.params == null) {
				local38 = Static165.bitceil(length);
				this.params = new IterableMap(local38);
			}
			for (int index = 0; index < length; index++) {
				@Pc(576) boolean local576 = packet.g1() == 1;
				@Pc(580) int local580 = packet.g3();
				@Pc(589) Node node;
				if (local576) {
					node = new JagStringWrapper(packet.gjstr());
				} else {
					node = new IntWrapper(packet.g4());
				}
				this.params.pushNode(node, (long) local580);
			}
		}
	}

	@OriginalMember(owner = "client!pb", name = "c", descriptor = "(I)V")
	public void postDecode() {
		if (this.active == -1) {
			this.active = 0;
			if (this.shapes != null && (this.models == null || this.models[0] == 10)) {
				this.active = 1;
			}
			for (@Pc(30) int index = 0; index < 5; index++) {
				if (this.op[index] != null) {
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
	public boolean method3416(@OriginalArg(1) int arg0) {
		if (this.models != null) {
			for (@Pc(18) int local18 = 0; local18 < this.models.length; local18++) {
				if (arg0 == this.models[local18]) {
					return Static121.aClass153_45.requestDownload(this.shapes[local18] & 0xFFFF, 0);
				}
			}
			return true;
		} else if (this.shapes == null) {
			return true;
		} else if (arg0 == 10) {
			@Pc(71) boolean local71 = true;
			for (@Pc(73) int local73 = 0; local73 < this.shapes.length; local73++) {
				local71 &= Static121.aClass153_45.requestDownload(this.shapes[local73] & 0xFFFF, 0);
			}
			return local71;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(I)Lclient!pb;")
	public LocType getVisible() {
		@Pc(26) int i = -1;
		if (this.multivarbit != -1) {
			i = Static155.method2945(this.multivarbit);
		} else if (this.multivarp != -1) {
			i = Static7.anIntArray75[this.multivarp];
		}
		if (i < 0 || i >= this.multiloc.length - 1 || this.multiloc[i] == -1) {
			@Pc(84) int local84 = this.multiloc[this.multiloc.length - 1];
			return local84 == -1 ? null : Static271.get(local84);
		} else {
			return Static271.get(this.multiloc[i]);
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(III)Lclient!gb;")
	private ModelUnlit method3418(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(7) ModelUnlit local7 = null;
		@Pc(10) boolean local10 = this.mirror;
		if (arg1 == 2 && arg0 > 3) {
			local10 = !local10;
		}
		@Pc(46) int local46;
		@Pc(48) int local48;
		if (this.models == null) {
			if (arg1 != 10) {
				return null;
			}
			if (this.shapes == null) {
				return null;
			}
			local46 = this.shapes.length;
			for (local48 = 0; local48 < local46; local48++) {
				@Pc(60) int local60 = this.shapes[local48];
				if (local10) {
					local60 += 65536;
				}
				local7 = (ModelUnlit) Static169.aClass99_24.method3106((long) local60);
				if (local7 == null) {
					local7 = ModelUnlit.get(Static121.aClass153_45, local60 & 0xFFFF);
					if (local7 == null) {
						return null;
					}
					if (local10) {
						local7.method1673();
					}
					Static169.aClass99_24.method3095(local7, (long) local60);
				}
				if (local46 > 1) {
					Static274.aClass8_Sub5Array5[local48] = local7;
				}
			}
			if (local46 > 1) {
				local7 = new ModelUnlit(Static274.aClass8_Sub5Array5, local46);
			}
		} else {
			local46 = -1;
			for (local48 = 0; local48 < this.models.length; local48++) {
				if (arg1 == this.models[local48]) {
					local46 = local48;
					break;
				}
			}
			if (local46 == -1) {
				return null;
			}
			local48 = this.shapes[local46];
			if (local10) {
				local48 += 65536;
			}
			local7 = (ModelUnlit) Static169.aClass99_24.method3106((long) local48);
			if (local7 == null) {
				local7 = ModelUnlit.get(Static121.aClass153_45, local48 & 0xFFFF);
				if (local7 == null) {
					return null;
				}
				if (local10) {
					local7.method1673();
				}
				Static169.aClass99_24.method3095(local7, (long) local48);
			}
		}
		@Pc(211) boolean local211;
		if (this.resizex == 128 && this.resizey == 128 && this.resizez == 128) {
			local211 = false;
		} else {
			local211 = true;
		}
		@Pc(230) boolean local230;
		if (this.xoff == 0 && this.yoff == 0 && this.zoff == 0) {
			local230 = false;
		} else {
			local230 = true;
		}
		@Pc(265) ModelUnlit local265 = new ModelUnlit(local7, arg0 == 0 && !local211 && !local230, this.recol_s == null, this.retex_s == null, true);
		if (arg1 == 4 && arg0 > 3) {
			local265.method1682();
			local265.method1672(45, 0, -45);
		}
		@Pc(285) int local285 = arg0 & 0x3;
		if (local285 == 1) {
			local265.method1662();
		} else if (local285 == 2) {
			local265.method1660();
		} else if (local285 == 3) {
			local265.method1689();
		}
		@Pc(315) int local315;
		if (this.recol_s != null) {
			for (local315 = 0; local315 < this.recol_s.length; local315++) {
				if (this.recol_d_palette == null || this.recol_d_palette.length <= local315) {
					local265.recolor(this.recol_s[local315], this.recol_d[local315]);
				} else {
					local265.recolor(this.recol_s[local315], Static62.aShortArray19[this.recol_d_palette[local315] & 0xFF]);
				}
			}
		}
		if (this.retex_s != null) {
			for (local315 = 0; local315 < this.retex_s.length; local315++) {
				local265.retexture(this.retex_s[local315], this.retex_d[local315]);
			}
		}
		if (local211) {
			local265.method1665(this.resizex, this.resizey, this.resizez);
		}
		if (local230) {
			local265.method1672(this.xoff, this.yoff, this.zoff);
		}
		return local265;
	}

	@OriginalMember(owner = "client!pb", name = "d", descriptor = "(I)Z")
	public boolean method3422() {
		if (this.multiloc == null) {
			return this.bgsound_sound != -1 || this.bgsound_random != null;
		}
		for (@Pc(44) int index = 0; index < this.multiloc.length; index++) {
			if (this.multiloc[index] != -1) {
				@Pc(70) LocType local70 = Static271.get(this.multiloc[index]);
				if (local70.bgsound_sound != -1 || local70.bgsound_random != null) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IIB)I")
	public int getParam(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.params == null) {
			return arg0;
		} else {
			@Pc(21) IntWrapper local21 = (IntWrapper) this.params.getNode((long) arg1);
			return local21 == null ? arg0 : local21.value;
		}
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Z)Z")
	public final boolean method3426() {
		if (this.shapes == null) {
			return true;
		}
		@Pc(13) boolean local13 = true;
		for (@Pc(15) int local15 = 0; local15 < this.shapes.length; local15++) {
			local13 &= Static121.aClass153_45.requestDownload(this.shapes[local15] & 0xFFFF, 0);
		}
		return local13;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IZZI)Lclient!td;")
	private GlModel method3427(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2) {
		@Pc(10) int local10 = this.ambient + 64;
		@Pc(17) int local17 = this.contrast * 5 + 768;
		@Pc(79) GlModel local79;
		@Pc(24) int local24;
		@Pc(177) int local177;
		if (this.models == null) {
			if (arg2 != 10) {
				return null;
			}
			if (this.shapes == null) {
				return null;
			}
			local24 = this.shapes.length;
			if (local24 == 0) {
				return null;
			}
			@Pc(135) long local135 = 0L;
			for (@Pc(137) int local137 = 0; local137 < local24; local137++) {
				local135 = (long) this.shapes[local137] + local135 * 67783L;
			}
			if (arg1) {
				local135 = ~local135;
			}
			local79 = (GlModel) Static169.aClass99_24.method3106(local135);
			if (local79 == null) {
				@Pc(175) ModelUnlit local175 = null;
				for (local177 = 0; local177 < local24; local177++) {
					local175 = ModelUnlit.get(Static121.aClass153_45, this.shapes[local177] & 0xFFFF);
					if (local175 == null) {
						return null;
					}
					if (local24 > 1) {
						Static274.aClass8_Sub5Array5[local177] = local175;
					}
				}
				if (local24 > 1) {
					local175 = new ModelUnlit(Static274.aClass8_Sub5Array5, local24);
				}
				local79 = new GlModel(local175, local10, local17, arg1);
				Static169.aClass99_24.method3095(local79, local135);
			}
		} else {
			local24 = -1;
			@Pc(26) int local26;
			for (local26 = 0; local26 < this.models.length; local26++) {
				if (this.models[local26] == arg2) {
					local24 = local26;
					break;
				}
			}
			if (local24 == -1) {
				return null;
			}
			local26 = this.shapes[local24];
			if (arg1) {
				local26 += 65536;
			}
			local79 = (GlModel) Static169.aClass99_24.method3106((long) local26);
			if (local79 == null) {
				@Pc(90) ModelUnlit local90 = ModelUnlit.get(Static121.aClass153_45, local26 & 0xFFFF);
				if (local90 == null) {
					return null;
				}
				local79 = new GlModel(local90, local10, local17, arg1);
				Static169.aClass99_24.method3095(local79, (long) local26);
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
			local351.method4575(45, 0, -45);
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
			local351.method4559(this.resizex, this.resizey, this.resizez);
		}
		if (this.xoff != 0 || this.yoff != 0 || this.zoff != 0) {
			local351.method4575(this.xoff, this.yoff, this.zoff);
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
	public final Class139 method3428(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[][] arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int[][] arg5, @OriginalArg(6) boolean arg6, @OriginalArg(7) SoftwareIndexedSprite arg7, @OriginalArg(9) boolean arg8, @OriginalArg(10) int arg9) {
		@Pc(29) long local29;
		if (GlRenderer.enabled) {
			if (this.models == null) {
				local29 = (this.anInt4426 << 10) + arg0;
			} else {
				local29 = arg0 + (this.anInt4426 << 10) + (arg3 << 3);
			}
			@Pc(225) Class139 local225 = (Class139) Static93.aClass99_14.method3106(local29);
			@Pc(235) GlModel local235;
			@Pc(265) SoftwareIndexedSprite local265;
			if (local225 == null) {
				local235 = this.method3427(arg0, false, arg3);
				if (local235 == null) {
					Static171.aClass139_1.aClass8_10 = null;
					Static171.aClass139_1.aClass36_Sub1_3 = null;
					return Static171.aClass139_1;
				}
				if (arg3 == 10 && arg0 > 3) {
					local235.method4554(256);
				}
				if (arg8) {
					local265 = local235.method4124(arg7);
				} else {
					local265 = null;
				}
				local225 = new Class139();
				local225.aClass8_10 = local235;
				local225.aClass36_Sub1_3 = local265;
				Static93.aClass99_14.method3095(local225, local29);
			} else {
				local235 = (GlModel) local225.aClass8_10;
				local265 = local225.aClass36_Sub1_3;
			}
			@Pc(298) boolean local298 = this.sharelight & arg6;
			@Pc(330) GlModel local330 = local235.method4117(this.hillskew_mode != 3, this.hillskew_mode == 0, true, true, true, !local298, true, true, true);
			if (this.hillskew_mode != 0) {
				local330.method4110(this.hillskew_mode, this.hillskew_value, local235, arg2, arg5, arg1, arg4, arg9);
			}
			local330.method4111(this.active == 0 && !this.aBoolean214, true, true, this.active == 0, true, false);
			Static171.aClass139_1.aClass8_10 = local330;
			local330.aBoolean259 = local298;
			Static171.aClass139_1.aClass36_Sub1_3 = local265;
			return Static171.aClass139_1;
		}
		if (this.models == null) {
			local29 = (this.anInt4426 << 10) + arg0;
		} else {
			local29 = (arg3 << 3) + ((this.anInt4426 << 10) + arg0);
		}
		@Pc(50) boolean local50;
		if (arg6 && this.sharelight) {
			local29 |= Long.MIN_VALUE;
			local50 = true;
		} else {
			local50 = false;
		}
		@Pc(60) Entity local60 = (Entity) Static93.aClass99_14.method3106(local29);
		if (local60 == null) {
			@Pc(69) ModelUnlit local69 = this.method3418(arg0, arg3);
			if (local69 == null) {
				Static171.aClass139_1.aClass8_10 = null;
				return Static171.aClass139_1;
			}
			local69.method1681();
			if (arg3 == 10 && arg0 > 3) {
				local69.method1682();
			}
			if (local50) {
				local69.aShort19 = (short) (this.ambient + 64);
				local60 = local69;
				local69.aShort18 = (short) (this.contrast * 5 + 768);
				local69.method1668();
			} else {
				local60 = new SoftwareModel(local69, this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50);
			}
			Static93.aClass99_14.method3095(local60, local29);
		}
		if (local50) {
			local60 = ((ModelUnlit) local60).method1675();
		}
		if (this.hillskew_mode != 0) {
			if (local60 instanceof SoftwareModel) {
				local60 = ((SoftwareModel) local60).method4586(this.hillskew_mode, this.hillskew_value, arg2, arg5, arg1, arg4, arg9, true);
			} else if (local60 instanceof ModelUnlit) {
				local60 = ((ModelUnlit) local60).method1670(this.hillskew_mode, this.hillskew_value, arg2, arg5, arg1, arg4, arg9);
			}
		}
		Static171.aClass139_1.aClass8_10 = local60;
		return Static171.aClass139_1;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(IILclient!ek;ILclient!tk;I[[IZII[[IIII)Lclient!sm;")
	public final Class139 method3429(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) SoftwareIndexedSprite arg2, @OriginalArg(3) int arg3, @OriginalArg(4) SeqType arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int[][] arg6, @OriginalArg(7) boolean arg7, @OriginalArg(8) int arg8, @OriginalArg(10) int[][] arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12) {
		@Pc(30) long local30;
		if (!GlRenderer.enabled) {
			if (this.models == null) {
				local30 = (this.anInt4426 << 10) + arg5;
			} else {
				local30 = arg5 + (this.anInt4426 << 10) + (arg11 << 3);
			}
			@Pc(195) SoftwareModel local195 = (SoftwareModel) Static262.aClass99_36.method3106(local30);
			if (local195 == null) {
				@Pc(204) ModelUnlit local204 = this.method3418(arg5, arg11);
				if (local204 == null) {
					return null;
				}
				local195 = new SoftwareModel(local204, this.ambient + 64, this.contrast * 5 + 768, -50, -10, -50);
				Static262.aClass99_36.method3095(local195, local30);
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
				local195.method4554(256);
			}
			if (this.hillskew_mode != 0) {
				if (!local234) {
					local195 = (SoftwareModel) local195.method4568(true, true, true);
				}
				local195 = local195.method4586(this.hillskew_mode, this.hillskew_value, arg6, arg9, arg1, arg3, arg0, false);
			}
			Static171.aClass139_1.aClass8_10 = local195;
			return Static171.aClass139_1;
		}
		if (this.models == null) {
			local30 = arg5 + (this.anInt4426 << 10);
		} else {
			local30 = (arg11 << 3) + ((this.anInt4426 << 10) + arg5);
		}
		@Pc(46) GlModel local46 = (GlModel) Static262.aClass99_36.method3106(local30);
		if (local46 == null) {
			local46 = this.method3427(arg5, true, arg11);
			if (local46 == null) {
				return null;
			}
			local46.method4099();
			local46.method4111(false, false, false, false, false, true);
			Static262.aClass99_36.method3095(local46, local30);
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
			local82.method4554(256);
		}
		if (arg7) {
			Static171.aClass139_1.aClass36_Sub1_3 = local82.method4124(arg2);
		} else {
			Static171.aClass139_1.aClass36_Sub1_3 = null;
		}
		if (this.hillskew_mode != 0) {
			if (!local80) {
				local82 = (GlModel) local82.method4568(true, true, true);
			}
			local82.method4110(this.hillskew_mode, this.hillskew_value, local46, arg6, arg9, arg1, arg3, arg0);
		}
		Static171.aClass139_1.aClass8_10 = local82;
		return Static171.aClass139_1;
	}

	@OriginalMember(owner = "client!pb", name = "a", descriptor = "(Lclient!na;II)Lclient!na;")
	public JagString getParam(@OriginalArg(0) JagString arg0, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return arg0;
		} else {
			@Pc(26) JagStringWrapper local26 = (JagStringWrapper) this.params.getNode((long) arg1);
			return local26 == null ? arg0 : local26.value;
		}
	}
}
