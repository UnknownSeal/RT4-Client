package com.jagex.runetek4.config.types.obj;

import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.graphics.model.RawModel;
import com.jagex.runetek4.graphics.gl.GlModel;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.graphics.model.SoftwareModel;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.JagStringWrapper;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!h")
public final class ObjType {

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
	public int id;

	@OriginalMember(owner = "client!h", name = "nb", descriptor = "Lclient!sc;")
	public HashTable params;

	@OriginalMember(owner = "client!h", name = "tb", descriptor = "[I")
	public int[] countObj;

	@OriginalMember(owner = "client!h", name = "h", descriptor = "I")
	public int cursor2 = -1;

	@OriginalMember(owner = "client!h", name = "K", descriptor = "I")
	private int womanwear3 = -1;

	@OriginalMember(owner = "client!h", name = "l", descriptor = "I")
	public int cost = 1;

	@OriginalMember(owner = "client!h", name = "e", descriptor = "I")
	private int womanHead2 = -1;

	@OriginalMember(owner = "client!h", name = "q", descriptor = "I")
	private int manWearXOff = 0;

	@OriginalMember(owner = "client!h", name = "H", descriptor = "I")
	private int womanHead = -1;

	@OriginalMember(owner = "client!h", name = "T", descriptor = "I")
	public int team = 0;

	@OriginalMember(owner = "client!gd", name = "b", descriptor = "Lclient!na;")
	public static final JString NULL = JString.parse("null");

	@OriginalMember(owner = "client!h", name = "E", descriptor = "Lclient!na;")
	public JString name = NULL;

	@OriginalMember(owner = "client!h", name = "J", descriptor = "I")
	private int manWearZOff = 0;

	@OriginalMember(owner = "client!h", name = "V", descriptor = "I")
	private int ambient = 0;

	@OriginalMember(owner = "client!h", name = "C", descriptor = "I")
	private int manwear3 = -1;

	@OriginalMember(owner = "client!h", name = "ib", descriptor = "I")
	private int manHead2 = -1;

	@OriginalMember(owner = "client!h", name = "cb", descriptor = "I")
	public int certTemplate = -1;

	@OriginalMember(owner = "client!h", name = "M", descriptor = "I")
	private int womanWearXOff = 0;

	@OriginalMember(owner = "client!h", name = "R", descriptor = "I")
	private int resizeY = 128;

	@OriginalMember(owner = "client!h", name = "A", descriptor = "I")
	public int cursor1Op = -1;

	@OriginalMember(owner = "client!h", name = "n", descriptor = "I")
	public int cursor1 = -1;

	@OriginalMember(owner = "client!h", name = "B", descriptor = "I")
	public int zAngle2D = 0;

	@OriginalMember(owner = "client!h", name = "u", descriptor = "I")
	public int lentTemplate = -1;

	@OriginalMember(owner = "client!h", name = "hb", descriptor = "I")
	public int lentLink = -1;

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
	public int dummyItem = 0;

	@OriginalMember(owner = "client!h", name = "w", descriptor = "I")
	public int stackable = 0;

	@OriginalMember(owner = "client!h", name = "ab", descriptor = "I")
	public int certLink = -1;

	@OriginalMember(owner = "client!h", name = "fb", descriptor = "I")
	private int womanwear2 = -1;

	@OriginalMember(owner = "client!h", name = "Z", descriptor = "I")
	public int cursor2Op = -1;

	@OriginalMember(owner = "client!h", name = "mb", descriptor = "I")
	private int resizeZ = 128;

	@OriginalMember(owner = "client!h", name = "P", descriptor = "Z")
	public boolean members = false;

	@OriginalMember(owner = "client!h", name = "b", descriptor = "I")
	private int womanWearZOff = 0;

	@OriginalMember(owner = "client!h", name = "db", descriptor = "I")
	public int xof2d = 0;

	@OriginalMember(owner = "client!h", name = "sb", descriptor = "I")
	private int manHead = -1;

	@OriginalMember(owner = "client!h", name = "rb", descriptor = "I")
	private int womanWearYOff = 0;

	@OriginalMember(owner = "client!h", name = "eb", descriptor = "I")
	public int manwear = -1;

	@OriginalMember(owner = "client!h", name = "qb", descriptor = "[Lclient!na;")
	public JString[] op = new JString[] { null, null, LocalizedText.TAKE, null, null };

	@OriginalMember(owner = "client!h", name = "ub", descriptor = "I")
	private int resizeX = 128;

	@OriginalMember(owner = "client!h", name = "bb", descriptor = "I")
	private int contrast = 0;

	@OriginalMember(owner = "client!h", name = "N", descriptor = "I")
	private int manWearYOff = 0;

	@OriginalMember(owner = "client!h", name = "U", descriptor = "[Lclient!na;")
	public JString[] iop = new JString[] { null, null, null, null, LocalizedText.DROP};

	@OriginalMember(owner = "client!h", name = "Ab", descriptor = "I")
	public int zoom2d = 2000;

	@OriginalMember(owner = "client!h", name = "xb", descriptor = "Z")
	public boolean stockMarket = false;

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	public static class Opcodes {

		public static final int model = 1;
		public static final int name = 2;
		public static final int zoom2d = 4;
		public static final int xan2d = 5;
		public static final int yan2d = 6;
		public static final int xof2d = 7;
		public static final int yof2d = 8;
		public static final int stackable_yes = 11;
		public static final int cost = 12;
		public static final int members = 16;
		public static final int manwear = 23;
		public static final int manwear2 = 24;
		public static final int womanwear = 25;
		public static final int womanwear2 = 26;
		public static final int op1 = 30;
		public static final int op5 = 34;
		public static final int iop1 = 35;
		public static final int iop5 = 39;
		public static final int recol = 40;
		public static final int retex = 41;
		public static final int stockmarket_yes = 65;
		public static final int manwear3 = 78;
		public static final int womanwear3 = 79;
		public static final int manhead = 90;
		public static final int womanhead = 91;
		public static final int manhead2 = 92;
		public static final int womanhead2 = 93;
		public static final int zan2d = 95;
		public static final int certlink = 97;
		public static final int certtemplate = 98;
		public static final int countobj1 = 100;
		public static final int countobj10 = 109;
		public static final int resizex = 110;
		public static final int resizey = 111;
		public static final int resizez = 112;
		public static final int ambient = 113;
		public static final int contrast = 114;
		public static final int team = 115;
		public static final int params = 249;

	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BLclient!wa;I)V")
	private void decode(@OriginalArg(1) Packet packet, @OriginalArg(2) int opcode) {
		if (opcode == Opcodes.model) {
			this.mesh = packet.g2();
		} else if (opcode == Opcodes.name) {
			this.name = packet.gjstr();
		} else if (opcode == Opcodes.zoom2d) {
			this.zoom2d = packet.g2();
		} else if (opcode == Opcodes.xan2d) {
			this.xan2d = packet.g2();
		} else if (opcode == Opcodes.yan2d) {
			this.yan2d = packet.g2();
		} else if (opcode == Opcodes.xof2d) {
			this.xof2d = packet.g2();
			if (this.xof2d > 32767) {
				this.xof2d -= 65536;
			}
		} else if (opcode == Opcodes.yof2d) {
			this.yof2d = packet.g2();
			if (this.yof2d > 32767) {
				this.yof2d -= 65536;
			}
		} else if (opcode == Opcodes.stackable_yes) {
			this.stackable = 1;
		} else if (opcode == Opcodes.cost) {
			this.cost = packet.g4();
		} else if (opcode == Opcodes.members) {
			this.members = true;
		} else if (opcode == Opcodes.manwear) {
			this.manwear = packet.g2();
		} else if (opcode == Opcodes.manwear2) {
			this.manwear2 = packet.g2();
		} else if (opcode == Opcodes.womanwear) {
			this.womanwear = packet.g2();
		} else if (opcode == Opcodes.womanwear2) {
			this.womanwear2 = packet.g2();
		} else if (opcode >= Opcodes.op1 && opcode <= Opcodes.op5) {
			this.op[opcode - Opcodes.op1] = packet.gjstr();
			if (this.op[opcode - Opcodes.op1].equalsIgnoreCase(LocalizedText.HIDDEN)) {
				this.op[opcode - Opcodes.op1] = null;
			}
		} else if (opcode >= Opcodes.iop1 && opcode <= Opcodes.iop5) {
			this.iop[opcode - Opcodes.iop1] = packet.gjstr();
		} else {
			@Pc(179) int local179;
			if (opcode == Opcodes.recol) {
				int colorCount = packet.g1();
				this.recol_s = new short[colorCount];
				this.recol_d = new short[colorCount];
				for (int colorIndex = 0; colorIndex < colorCount; colorIndex++) {
					this.recol_s[colorIndex] = (short) packet.g2();
					this.recol_d[colorIndex] = (short) packet.g2();
				}
			} else if (opcode == Opcodes.retex) {
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
			} else if (opcode == Opcodes.stockmarket_yes) {
				this.stockMarket = true;
			} else if (opcode == Opcodes.manwear3) {
				this.manwear3 = packet.g2();
			} else if (opcode == Opcodes.womanwear3) {
				this.womanwear3 = packet.g2();
			} else if (opcode == Opcodes.manhead) {
				this.manHead = packet.g2();
			} else if (opcode == Opcodes.womanhead) {
				this.womanHead = packet.g2();
			} else if (opcode == Opcodes.manhead2) {
				this.manHead2 = packet.g2();
			} else if (opcode == Opcodes.womanhead2) {
				this.womanHead2 = packet.g2();
			} else if (opcode == Opcodes.zan2d) {
				this.zAngle2D = packet.g2();
			} else if (opcode == 96) {
				this.dummyItem = packet.g1();
			} else if (opcode == Opcodes.certlink) {
				this.certLink = packet.g2();
			} else if (opcode == Opcodes.certtemplate) {
				this.certTemplate = packet.g2();
			} else if (opcode >= Opcodes.countobj1 && opcode <= Opcodes.countobj10) {
				if (this.countObj == null) {
					this.countObj = new int[10];
					this.countco = new int[10];
				}
				this.countObj[opcode - Opcodes.countobj1] = packet.g2();
				this.countco[opcode - Opcodes.countobj1] = packet.g2();
			} else if (opcode == Opcodes.resizex) {
				this.resizeX = packet.g2();
			} else if (opcode == Opcodes.resizey) {
				this.resizeY = packet.g2();
			} else if (opcode == Opcodes.resizez) {
				this.resizeZ = packet.g2();
			} else if (opcode == Opcodes.ambient) {
				this.ambient = packet.g1s();
			} else if (opcode == Opcodes.contrast) {
				this.contrast = packet.g1s() * 5;
			} else if (opcode == Opcodes.team) {
				this.team = packet.g1();
			} else if (opcode == 121) {
				this.lentLink = packet.g2();
			} else if (opcode == 122) {
				this.lentTemplate = packet.g2();
			} else if (opcode == 125) {
				this.manWearXOff = packet.g1s();
				this.manWearYOff = packet.g1s();
				this.manWearZOff = packet.g1s();
			} else if (opcode == 126) {
				this.womanWearXOff = packet.g1s();
				this.womanWearYOff = packet.g1s();
				this.womanWearZOff = packet.g1s();
			} else if (opcode == 127) {
				this.cursor1Op = packet.g1();
				this.cursor1 = packet.g2();
			} else if (opcode == 128) {
				this.cursor2Op = packet.g1();
				this.cursor2 = packet.g2();
			} else if (opcode == 129) { // Unused in current revision.
				packet.g1();
				packet.g2();
			} else if (opcode == 130) { // Unused in current revision.
				packet.g1();
				packet.g2();
			} else if (opcode == Opcodes.params) {
				int length = packet.g1();
				if (this.params == null) {
					local179 = IntUtils.bitceil(length);
					this.params = new HashTable(local179);
				}
				for (int index = 0; index < length; index++) {
					@Pc(510) boolean isString = packet.g1() == 1;
					@Pc(514) int key = packet.g3();
					@Pc(523) Node node;
					if (isString) {
						node = new JagStringWrapper(packet.gjstr());
					} else {
						node = new IntWrapper(packet.g4());
					}
					this.params.put(node, key);
				}
			}
		}
	}

	@OriginalMember(owner = "client!h", name = "c", descriptor = "(I)V")
	public void postDecode() {
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZZ)Z")
	public boolean isHeadModelReady(@OriginalArg(0) boolean female) {
		@Pc(6) int primaryId = this.manHead;
		@Pc(9) int secondaryId = this.manHead2;
		if (female) {
			primaryId = this.womanHead;
			secondaryId = this.womanHead2;
		}
		if (primaryId == -1) {
			return true;
		}
		@Pc(33) boolean ready = ObjTypeList.modelArchive.isFileReady(primaryId, 0);
		if (secondaryId != -1 && !ObjTypeList.modelArchive.isFileReady(secondaryId, 0)) {
			ready = false;
		}
		return ready;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ILclient!na;I)Lclient!na;")
	public JString getParam(@OriginalArg(1) JString defaultValue, @OriginalArg(2) int id) {
		if (this.params == null) {
			return defaultValue;
		} else {
			@Pc(21) JagStringWrapper node = (JagStringWrapper) this.params.get(id);
			return node == null ? defaultValue : node.value;
		}
	}

	/*
	 * This method gets the stackable obj models dependent on the defined amount "trigger" values.
	 * An example of this would be coins/arrows.
	 */
	@OriginalMember(owner = "client!h", name = "a", descriptor = "(II)Lclient!h;")
	public ObjType getMeshAddress(@OriginalArg(0) int count) {
		if (this.countObj != null && count > 1) {
			@Pc(23) int id = -1;
			for (@Pc(25) int index = 0; index < 10; index++) {
				if (count >= this.countco[index] && this.countco[index] != 0) {
					id = this.countObj[index];
				}
			}
			if (id != -1) {
				return ObjTypeList.get(id);
			}
		}
		return this;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BZ)Z")
	public boolean isWearModelReady(@OriginalArg(1) boolean woman) {
		@Pc(6) int wear2 = this.manwear2;
		@Pc(9) int wear1 = this.manwear;
		@Pc(20) int wear3 = this.manwear3;
		if (woman) {
			wear3 = this.womanwear3;
			wear1 = this.womanwear;
			wear2 = this.womanwear2;
		}
		if (wear1 == -1) {
			return true;
		}
		@Pc(41) boolean ready = ObjTypeList.modelArchive.isFileReady(wear1, 0);
		if (wear2 != -1 && !ObjTypeList.modelArchive.isFileReady(wear2, 0)) {
			ready = false;
		}
		if (wear3 != -1 && !ObjTypeList.modelArchive.isFileReady(wear3, 0)) {
			ready = false;
		}
		return ready;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BLclient!h;Lclient!h;)V")
	public void generateLent(@OriginalArg(2) ObjType from, @OriginalArg(1) ObjType to) {
		this.recol_d_palette = to.recol_d_palette;
		this.manWearYOff = to.manWearYOff;
		this.params = to.params;
		this.manwear3 = to.manwear3;
		this.womanwear = to.womanwear;
		this.manWearZOff = to.manWearZOff;
		this.iop = new JString[5];
		this.mesh = from.mesh;
		this.zoom2d = from.zoom2d;
		this.cost = 0;
		this.team = to.team;
		this.womanHead = to.womanHead;
		this.recol_s = to.recol_s;
		this.zAngle2D = from.zAngle2D;
		this.manwear2 = to.manwear2;
		this.yan2d = from.yan2d;
		this.manHead = to.manHead;
		this.manHead2 = to.manHead2;
		this.manWearXOff = to.manWearXOff;
		this.xan2d = from.xan2d;
		this.yof2d = from.yof2d;
		this.womanHead2 = to.womanHead2;
		this.womanWearXOff = to.womanWearXOff;
		this.recol_d = to.recol_d;
		this.womanWearYOff = to.womanWearYOff;
		this.womanWearZOff = to.womanWearZOff;
		this.xof2d = from.xof2d;
		this.manwear = to.manwear;
		this.womanwear2 = to.womanwear2;
		this.name = to.name;
		this.retex_d = to.retex_d;
		this.retex_s = to.retex_s;
		this.op = to.op;
		this.members = to.members;
		this.womanwear3 = to.womanwear3;
		if (to.iop != null) {
			System.arraycopy(to.iop, 0, this.iop, 0, 4);
		}
		this.iop[4] = LocalizedText.LENT_ITEM_RETURN;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(IIILclient!tk;II)Lclient!ak;")
	public Model getModel(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) SeqType seqType, @OriginalArg(4) int count, @OriginalArg(5) int arg4) {
		if (this.countObj != null && count > 1) {
			@Pc(22) int countId = -1;
			for (@Pc(24) int i = 0; i < 10; i++) {
				if (count >= this.countco[i] && this.countco[i] != 0) {
					countId = this.countObj[i];
				}
			}
			if (countId != -1) {
				return ObjTypeList.get(countId).getModel(arg0, arg1, seqType, 1, arg4);
			}
		}
		@Pc(76) Model model = (Model) ObjTypeList.models.get(this.id);
		if (model == null) {
			@Pc(85) RawModel model2 = RawModel.create(ObjTypeList.modelArchive, this.mesh);
			if (model2 == null) {
				return null;
			}
			@Pc(97) int i;
			if (this.recol_s != null) {
				for (i = 0; i < this.recol_s.length; i++) {
					if (this.recol_d_palette == null || i >= this.recol_d_palette.length) {
						model2.recolor(this.recol_s[i], this.recol_d[i]);
					} else {
						model2.recolor(this.recol_s[i], Client.aShortArray87[this.recol_d_palette[i] & 0xFF]);
					}
				}
			}
			if (this.retex_s != null) {
				for (i = 0; i < this.retex_s.length; i++) {
					model2.retexture(this.retex_s[i], this.retex_d[i]);
				}
			}
			model = model2.createModel(this.ambient + 64, this.contrast + 768, -50, -10, -50);
			if (this.resizeX != 128 || this.resizeY != 128 || this.resizeZ != 128) {
				model.resize(this.resizeX, this.resizeY, this.resizeZ);
			}
			model.pickable = true;
			if (GlRenderer.enabled) {
				((GlModel) model).method4111(false, false, false, false, false, true);
			}
			ObjTypeList.models.put(model, this.id);
		}
		if (seqType != null) {
			model = seqType.method4215(model, arg0, arg1, arg4);
		}
		return model;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(III)I")
	public int getParam(@OriginalArg(0) int defaultValue, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return defaultValue;
		} else {
			@Pc(25) IntWrapper node = (IntWrapper) this.params.get(arg1);
			return node == null ? defaultValue : node.value;
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZB)Lclient!gb;")
	public RawModel getHeadModel(@OriginalArg(0) boolean woman) {
		@Pc(4) int head2 = this.manHead2;
		@Pc(17) int head = this.manHead;
		if (woman) {
			head2 = this.womanHead2;
			head = this.womanHead;
		}
		if (head == -1) {
			return null;
		}
		@Pc(36) RawModel model = RawModel.create(ObjTypeList.modelArchive, head);
		if (head2 != -1) {
			@Pc(44) RawModel model2 = RawModel.create(ObjTypeList.modelArchive, head2);
			@Pc(55) RawModel[] models = new RawModel[] { model, model2 };
			model = new RawModel(models, 2);
		}
		@Pc(66) int i;
		if (this.recol_s != null) {
			for (i = 0; i < this.recol_s.length; i++) {
				model.recolor(this.recol_s[i], this.recol_d[i]);
			}
		}
		if (this.retex_s != null) {
			for (i = 0; i < this.retex_s.length; i++) {
				model.retexture(this.retex_s[i], this.retex_d[i]);
			}
		}
		return model;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(ZI)Lclient!gb;")
	public RawModel getBodyModel(@OriginalArg(0) boolean female) {
		@Pc(4) int wear1 = this.manwear;
		@Pc(18) int wear2 = this.manwear2;
		@Pc(21) int wear3 = this.manwear3;
		if (female) {
			wear3 = this.womanwear3;
			wear1 = this.womanwear;
			wear2 = this.womanwear2;
		}
		if (wear1 == -1) {
			return null;
		}
		@Pc(43) RawModel model = RawModel.create(ObjTypeList.modelArchive, wear1);
		if (wear2 != -1) {
			@Pc(54) RawModel model2 = RawModel.create(ObjTypeList.modelArchive, wear2);
			if (wear3 == -1) {
				@Pc(68) RawModel[] models = new RawModel[] { model, model2 };
				model = new RawModel(models, 2);
			} else {
				@Pc(81) RawModel model3 = RawModel.create(ObjTypeList.modelArchive, wear3);
				@Pc(96) RawModel[] models = new RawModel[] { model, model2, model3 };
				model = new RawModel(models, 3);
			}
		}
		if (!female && (this.manWearXOff != 0 || this.manWearYOff != 0 || this.manWearZOff != 0)) {
			model.translate(this.manWearXOff, this.manWearYOff, this.manWearZOff);
		}
		if (female && (this.womanWearXOff != 0 || this.womanWearYOff != 0 || this.womanWearZOff != 0)) {
			model.translate(this.womanWearXOff, this.womanWearYOff, this.womanWearZOff);
		}
		@Pc(165) int local165;
		if (this.recol_s != null) {
			for (local165 = 0; local165 < this.recol_s.length; local165++) {
				model.recolor(this.recol_s[local165], this.recol_d[local165]);
			}
		}
		if (this.retex_s != null) {
			for (local165 = 0; local165 < this.retex_s.length; local165++) {
				model.retexture(this.retex_s[local165], this.retex_d[local165]);
			}
		}
		return model;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(Lclient!h;Lclient!h;Z)V")
	public void generateCertificate(@OriginalArg(1) ObjType from, @OriginalArg(0) ObjType to) {
		this.name = to.name;
		this.zoom2d = from.zoom2d;
		this.recol_s = from.recol_s;
		this.recol_d = from.recol_d;
		this.xan2d = from.xan2d;
		this.yan2d = from.yan2d;
		this.retex_d = from.retex_d;
		this.mesh = from.mesh;
		this.recol_d_palette = from.recol_d_palette;
		this.zAngle2D = from.zAngle2D;
		this.cost = to.cost;
		this.stackable = 1;
		this.yof2d = from.yof2d;
		this.xof2d = from.xof2d;
		this.retex_s = from.retex_s;
		this.members = to.members;
	}

	@OriginalMember(owner = "client!h", name = "d", descriptor = "(I)Lclient!w;")
	public SoftwareModel getInvModel() {
		@Pc(11) RawModel model = RawModel.create(ObjTypeList.modelArchive, this.mesh);
		if (model == null) {
			return null;
		}
		@Pc(21) int i;
		if (this.recol_s != null) {
			for (i = 0; i < this.recol_s.length; i++) {
				if (this.recol_d_palette == null || i >= this.recol_d_palette.length) {
					model.recolor(this.recol_s[i], this.recol_d[i]);
				} else {
					model.recolor(this.recol_s[i], Client.aShortArray87[this.recol_d_palette[i] & 0xFF]);
				}
			}
		}
		if (this.retex_s != null) {
			for (i = 0; i < this.retex_s.length; i++) {
				model.retexture(this.retex_s[i], this.retex_d[i]);
			}
		}
		@Pc(107) SoftwareModel softwareModel = model.createSoftwareModel(this.ambient + 64, 768 - -this.contrast);
		if (this.resizeX != 128 || this.resizeY != 128 || this.resizeZ != 128) {
			softwareModel.resize(this.resizeX, this.resizeY, this.resizeZ);
		}
		return softwareModel;
	}


	@OriginalMember(owner = "runetek4.client!gg", name = "a", descriptor = "([[IZ)V")
	public static void method1751(@OriginalArg(0) int[][] arg0) {
		ObjTypeList.anIntArrayArray10 = arg0;
	}

}
