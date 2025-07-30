package com.jagex.runetek4.graphics.model;

import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.graphics.geometry.TriangleNormal;
import com.jagex.runetek4.graphics.geometry.VertexNormal;
import com.jagex.runetek4.graphics.gl.GlModel;
import com.jagex.runetek4.ui.component.MiniMenu;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.ColorUtils;
import com.jagex.runetek4.util.math.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!w")
public final class SoftwareModel extends Model {

	@OriginalMember(owner = "runetek4.client!w", name = "nb", descriptor = "Lclient!w;")
	public static final SoftwareModel aClass8_Sub1_Sub2_3 = new SoftwareModel();
	@OriginalMember(owner = "runetek4.client!w", name = "ab", descriptor = "Lclient!w;")
	public static final SoftwareModel aClass8_Sub1_Sub2_1 = new SoftwareModel();
	@OriginalMember(owner = "runetek4.client!w", name = "eb", descriptor = "Lclient!w;")
	public static final SoftwareModel aClass8_Sub1_Sub2_2 = new SoftwareModel();
	@OriginalMember(owner = "runetek4.client!w", name = "pb", descriptor = "[Z")
	public static final boolean[] projectTriangle = new boolean[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "qb", descriptor = "[I")
	public static final int[] anIntArray541 = new int[12];
	@OriginalMember(owner = "runetek4.client!w", name = "rb", descriptor = "[I")
	public static final int[] anIntArray542 = new int[10];
	@OriginalMember(owner = "runetek4.client!w", name = "sb", descriptor = "[I")
	public static final int[] vertexDepth = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "tb", descriptor = "[I")
	public static final int[] projectSceneZ = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "ub", descriptor = "[I")
	public static final int[] normalTrianglePriority = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "xb", descriptor = "[I")
	public static final int[] anIntArray547 = new int[10];
	@OriginalMember(owner = "runetek4.client!w", name = "Ab", descriptor = "[I")
	public static final int[] projectSceneY = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Eb", descriptor = "[I")
	public static final int[] vertexScreenY = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Ib", descriptor = "[I")
	public static final int[] anIntArray553 = new int[10];
	@OriginalMember(owner = "runetek4.client!w", name = "Jb", descriptor = "[Z")
	public static final boolean[] testTriangleX = new boolean[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Kb", descriptor = "[[I")
	public static final int[][] priorityTriangles = new int[12][4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Nb", descriptor = "[I")
	public static final int[] highTrianglePriority = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Pb", descriptor = "[I")
	public static final int[] vertexScreenX = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Qb", descriptor = "[I")
	public static final int[] lowTrianglePriority = new int[12];
	@OriginalMember(owner = "runetek4.client!w", name = "Ub", descriptor = "[I")
	public static final int[] projectSceneX = new int[4096];
	@OriginalMember(owner = "runetek4.client!w", name = "Wb", descriptor = "[I")
	public static final int[] anIntArray561 = new int[8192];
	@OriginalMember(owner = "runetek4.client!w", name = "vb", descriptor = "[[I")
	public static int[][] anIntArrayArray43;
	@OriginalMember(owner = "runetek4.client!w", name = "Ob", descriptor = "Z")
	public static boolean aBoolean307 = false;
	@OriginalMember(owner = "runetek4.client!w", name = "Lb", descriptor = "[I")
	public static int[] anIntArray554 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "Vb", descriptor = "[S")
	public static short[] aShortArray95 = new short[1];
	@OriginalMember(owner = "runetek4.client!w", name = "Fb", descriptor = "[B")
	public static byte[] aByteArray78 = new byte[1];
	@OriginalMember(owner = "runetek4.client!w", name = "Tb", descriptor = "[I")
	public static int[] anIntArray559 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "wb", descriptor = "[I")
	public static int[] anIntArray546 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "Mb", descriptor = "I")
	public static int anInt5792;
	@OriginalMember(owner = "runetek4.client!w", name = "Gb", descriptor = "I")
	public static int anInt5791;
	@OriginalMember(owner = "runetek4.client!w", name = "Rb", descriptor = "I")
	public static int anInt5793;
	@OriginalMember(owner = "runetek4.client!w", name = "Db", descriptor = "[I")
	public static int[] anIntArray550;
	@OriginalMember(owner = "runetek4.client!w", name = "Bb", descriptor = "[I")
	public static int[] depthTriangles;
	@OriginalMember(owner = "runetek4.client!w", name = "yb", descriptor = "[[I")
	public static int[][] anIntArrayArray44;
	@OriginalMember(owner = "runetek4.client!w", name = "Sb", descriptor = "[I")
	public static int[] anIntArray558;
	@OriginalMember(owner = "runetek4.client!w", name = "Hb", descriptor = "[I")
	public static int[] anIntArray552;
	@OriginalMember(owner = "runetek4.client!w", name = "cb", descriptor = "[I")
	public static int[] anIntArray535 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "db", descriptor = "[I")
	public static int[] anIntArray536 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "fb", descriptor = "[B")
	public static byte[] aByteArray76 = new byte[1];
	@OriginalMember(owner = "runetek4.client!w", name = "hb", descriptor = "[I")
	public static int[] anIntArray537 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "ib", descriptor = "[S")
	public static short[] aShortArray93 = new short[1];
	@OriginalMember(owner = "runetek4.client!w", name = "jb", descriptor = "[I")
	public static int[] anIntArray538 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "kb", descriptor = "[S")
	public static short[] aShortArray94 = new short[1];
	@OriginalMember(owner = "runetek4.client!w", name = "lb", descriptor = "[I")
	public static int[] anIntArray539 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "mb", descriptor = "[B")
	public static byte[] aByteArray77 = new byte[1];
	@OriginalMember(owner = "runetek4.client!w", name = "ob", descriptor = "[I")
	public static int[] anIntArray540 = new int[1];
	@OriginalMember(owner = "runetek4.client!w", name = "zb", descriptor = "Z")
	public static boolean aBoolean306 = false;
	@OriginalMember(owner = "runetek4.client!w", name = "Cb", descriptor = "I")
	public static int anInt5790 = 0;

	@OriginalMember(owner = "runetek4.client!w", name = "t", descriptor = "[S")
	private short[] triangleColors;

	@OriginalMember(owner = "runetek4.client!w", name = "u", descriptor = "[[I")
	private int[][] boneVertices;

	@OriginalMember(owner = "runetek4.client!w", name = "v", descriptor = "[S")
	private short[] triangleSources;

	@OriginalMember(owner = "runetek4.client!w", name = "w", descriptor = "[[I")
	private int[][] boneTriangles;

	@OriginalMember(owner = "runetek4.client!w", name = "x", descriptor = "S")
	private short minX;

	@OriginalMember(owner = "runetek4.client!w", name = "y", descriptor = "[I")
	private int[] anIntArray523;

	@OriginalMember(owner = "runetek4.client!w", name = "A", descriptor = "[I")
	private int[] triangleVertexB;

	@OriginalMember(owner = "runetek4.client!w", name = "C", descriptor = "S")
	private short maxX;

	@OriginalMember(owner = "runetek4.client!w", name = "D", descriptor = "S")
	private short minZ;

	@OriginalMember(owner = "runetek4.client!w", name = "E", descriptor = "[B")
	private byte[] trianglePriorities;

	@OriginalMember(owner = "runetek4.client!w", name = "F", descriptor = "[I")
	private int[] textureFacesN;

	@OriginalMember(owner = "runetek4.client!w", name = "G", descriptor = "[I")
	private int[] textureFacesP;

	@OriginalMember(owner = "runetek4.client!w", name = "H", descriptor = "[I")
	public int[] vertexY;

	@OriginalMember(owner = "runetek4.client!w", name = "I", descriptor = "S")
	private short lengthXZ;

	@OriginalMember(owner = "runetek4.client!w", name = "J", descriptor = "[I")
	public int[] vertexX;

	@OriginalMember(owner = "runetek4.client!w", name = "K", descriptor = "S")
	private short aShort35;

	@OriginalMember(owner = "runetek4.client!w", name = "L", descriptor = "[I")
	private int[] triangleVertexC;

	@OriginalMember(owner = "runetek4.client!w", name = "M", descriptor = "S")
	private short maxZ;

	@OriginalMember(owner = "runetek4.client!w", name = "O", descriptor = "[I")
	private int[] textureFacesM;

	@OriginalMember(owner = "runetek4.client!w", name = "Q", descriptor = "S")
	private short maxY;

	@OriginalMember(owner = "runetek4.client!w", name = "R", descriptor = "[S")
	private short[] vertexSources;

	@OriginalMember(owner = "runetek4.client!w", name = "S", descriptor = "S")
	private short minY;

	@OriginalMember(owner = "runetek4.client!w", name = "T", descriptor = "[I")
	public int[] vertexZ;

	@OriginalMember(owner = "runetek4.client!w", name = "U", descriptor = "[I")
	private int[] triangleInfo;

	@OriginalMember(owner = "runetek4.client!w", name = "W", descriptor = "[I")
	private int[] anIntArray533;

	@OriginalMember(owner = "runetek4.client!w", name = "X", descriptor = "[B")
	private byte[] aByteArray74;

	@OriginalMember(owner = "runetek4.client!w", name = "Z", descriptor = "[I")
	private int[] triangleVertexA;

	@OriginalMember(owner = "runetek4.client!w", name = "bb", descriptor = "[B")
	private byte[] triangleAlpha;

	@OriginalMember(owner = "runetek4.client!w", name = "gb", descriptor = "[S")
	private short[] aShortArray92;

	@OriginalMember(owner = "runetek4.client!w", name = "z", descriptor = "B")
	private byte priority = 0;

	@OriginalMember(owner = "runetek4.client!w", name = "V", descriptor = "Z")
	public boolean boundsValid = false;

	@OriginalMember(owner = "runetek4.client!w", name = "P", descriptor = "I")
	public int vertexCount = 0;

	@OriginalMember(owner = "runetek4.client!w", name = "N", descriptor = "I")
	private int triangleCount = 0;

	@OriginalMember(owner = "runetek4.client!w", name = "Y", descriptor = "I")
	private int anInt5789 = 0;

	@OriginalMember(owner = "runetek4.client!w", name = "B", descriptor = "Z")
	private boolean aBoolean304 = false;

	static {
		if (aBoolean307) {
			anIntArray550 = new int[4096];
			depthTriangles = new int[4096];
		} else {
			anIntArray558 = new int[1600];
			anIntArrayArray44 = new int[1600][64];
			anIntArray552 = new int[32];
			anIntArrayArray43 = new int[32][512];
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "<init>", descriptor = "()V")
	public SoftwareModel() {
	}

	@OriginalMember(owner = "runetek4.client!w", name = "<init>", descriptor = "(Lclient!gb;IIIII)V")
	public SoftwareModel(@OriginalArg(0) RawModel model, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		model.calculateNormals();
		model.createBones();
		this.vertexCount = model.vertexCount;
		this.vertexX = model.vertexX;
		this.vertexY = model.vertexY;
		this.vertexZ = model.vertexZ;
		this.triangleCount = model.triangleCount;
		this.triangleVertexA = model.triangleVertexA;
		this.triangleVertexB = model.triangleVertexB;
		this.triangleVertexC = model.triangleVertexC;
		this.trianglePriorities = model.trianglePriorities;
		this.triangleAlpha = model.triangleAlpha;
		this.priority = model.modelPriority;
		this.triangleColors = model.unmodifiedTriangleColour;
		this.boneVertices = model.boneVertices;
		this.boneTriangles = model.boneTriangles;
		this.triangleSources = model.triangleSources;
		this.vertexSources = model.vertexSources;
		@Pc(102) int local102 = (int) Math.sqrt((double) (arg3 * arg3 + arg4 * arg4 + arg5 * arg5));
		@Pc(108) int local108 = arg2 * local102 >> 8;
		this.anIntArray533 = new int[this.triangleCount];
		this.anIntArray523 = new int[this.triangleCount];
		this.triangleInfo = new int[this.triangleCount];
		@Pc(133) int i;
		if (model.unmodifiedTriangleTexture == null) {
			this.aShortArray92 = null;
		} else {
			this.aShortArray92 = new short[this.triangleCount];
			for (i = 0; i < this.triangleCount; i++) {
				@Pc(142) short local142 = model.unmodifiedTriangleTexture[i];
				if (local142 != -1 && Rasterizer.textureProvider.method3236(local142)) {
					this.aShortArray92[i] = local142;
				} else {
					this.aShortArray92[i] = -1;
				}
			}
		}
		if (model.texturedFaceCount > 0 && model.triangleTextureIndex != null) {
			@Pc(177) int[] local177 = new int[model.texturedFaceCount];
			@Pc(179) int local179;
			for (local179 = 0; local179 < this.triangleCount; local179++) {
				if (model.triangleTextureIndex[local179] != -1) {
					local177[model.triangleTextureIndex[local179] & 0xFF]++;
				}
			}
			this.anInt5789 = 0;
			for (local179 = 0; local179 < model.texturedFaceCount; local179++) {
				if (local177[local179] > 0 && model.textureTypes[local179] == 0) {
					this.anInt5789++;
				}
			}
			this.textureFacesP = new int[this.anInt5789];
			this.textureFacesM = new int[this.anInt5789];
			this.textureFacesN = new int[this.anInt5789];
			local179 = 0;
			@Pc(248) int j;
			for (j = 0; j < model.texturedFaceCount; j++) {
				if (local177[j] > 0 && model.textureTypes[j] == 0) {
					this.textureFacesP[local179] = model.textureFacesP[j] & 0xFFFF;
					this.textureFacesM[local179] = model.textureFacesM[j] & 0xFFFF;
					this.textureFacesN[local179] = model.textureFacesN[j] & 0xFFFF;
					local177[j] = local179++;
				} else {
					local177[j] = -1;
				}
			}
			this.aByteArray74 = new byte[this.triangleCount];
			for (j = 0; j < this.triangleCount; j++) {
				if (model.triangleTextureIndex[j] == -1) {
					this.aByteArray74[j] = -1;
				} else {
					this.aByteArray74[j] = (byte) local177[model.triangleTextureIndex[j] & 0xFF];
					if (this.aByteArray74[j] == -1 && this.aShortArray92 != null) {
						this.aShortArray92[j] = -1;
					}
				}
			}
		}
		for (i = 0; i < this.triangleCount; i++) {
			@Pc(366) byte local366;
			if (model.triangleInfo == null) {
				local366 = 0;
			} else {
				local366 = model.triangleInfo[i];
			}
			@Pc(377) byte local377;
			if (model.triangleAlpha == null) {
				local377 = 0;
			} else {
				local377 = model.triangleAlpha[i];
			}
			@Pc(388) short local388;
			if (this.aShortArray92 == null) {
				local388 = -1;
			} else {
				local388 = this.aShortArray92[i];
			}
			if (local377 == -2) {
				local366 = 3;
			}
			if (local377 == -1) {
				local366 = 2;
			}
			@Pc(435) VertexNormal local435;
			@Pc(468) int local468;
			@Pc(614) TriangleNormal local614;
			if (local388 == -1) {
				if (local366 == 0) {
					@Pc(416) int local416 = model.unmodifiedTriangleColour[i] & 0xFFFF;
					if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexA[i]] == null) {
						local435 = model.vertexNormals[this.triangleVertexA[i]];
					} else {
						local435 = model.aVertexNormalArray2[this.triangleVertexA[i]];
					}
					local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude) << 17;
					this.anIntArray533[i] = local468 | ColorUtils.multiplyLightness2(local416, local468 >> 17);
					if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexB[i]] == null) {
						local435 = model.vertexNormals[this.triangleVertexB[i]];
					} else {
						local435 = model.aVertexNormalArray2[this.triangleVertexB[i]];
					}
					local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude) << 17;
					this.anIntArray523[i] = local468 | ColorUtils.multiplyLightness2(local416, local468 >> 17);
					if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexC[i]] == null) {
						local435 = model.vertexNormals[this.triangleVertexC[i]];
					} else {
						local435 = model.aVertexNormalArray2[this.triangleVertexC[i]];
					}
					local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude) << 17;
					this.triangleInfo[i] = local468 | ColorUtils.multiplyLightness2(local416, local468 >> 17);
				} else if (local366 == 1) {
					local614 = model.triangleNormals[i];
					local468 = arg1 + (arg3 * local614.x + arg4 * local614.y + arg5 * local614.z) / (local108 + local108 / 2) << 17;
					this.anIntArray533[i] = local468 | ColorUtils.multiplyLightness2(model.unmodifiedTriangleColour[i] & 0xFFFF, local468 >> 17);
					this.triangleInfo[i] = -1;
				} else if (local366 == 3) {
					this.anIntArray533[i] = 128;
					this.triangleInfo[i] = -1;
				} else {
					this.triangleInfo[i] = -2;
				}
			} else if (local366 == 0) {
				if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexA[i]] == null) {
					local435 = model.vertexNormals[this.triangleVertexA[i]];
				} else {
					local435 = model.aVertexNormalArray2[this.triangleVertexA[i]];
				}
				local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude);
				this.anIntArray533[i] = ColorUtils.method4582(local468);
				if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexB[i]] == null) {
					local435 = model.vertexNormals[this.triangleVertexB[i]];
				} else {
					local435 = model.aVertexNormalArray2[this.triangleVertexB[i]];
				}
				local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude);
				this.anIntArray523[i] = ColorUtils.method4582(local468);
				if (model.aVertexNormalArray2 == null || model.aVertexNormalArray2[this.triangleVertexC[i]] == null) {
					local435 = model.vertexNormals[this.triangleVertexC[i]];
				} else {
					local435 = model.aVertexNormalArray2[this.triangleVertexC[i]];
				}
				local468 = arg1 + (arg3 * local435.x + arg4 * local435.y + arg5 * local435.z) / (local108 * local435.magnitude);
				this.triangleInfo[i] = ColorUtils.method4582(local468);
			} else if (local366 == 1) {
				local614 = model.triangleNormals[i];
				local468 = arg1 + (arg3 * local614.x + arg4 * local614.y + arg5 * local614.z) / (local108 + local108 / 2);
				this.anIntArray533[i] = ColorUtils.method4582(local468);
				this.triangleInfo[i] = -1;
			} else {
				this.triangleInfo[i] = -2;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "<init>", descriptor = "([Lclient!w;I)V")
	private SoftwareModel(@OriginalArg(0) SoftwareModel[] arg0, @OriginalArg(1) int arg1) {
		@Pc(21) boolean local21 = false;
		@Pc(23) boolean local23 = false;
		@Pc(25) boolean local25 = false;
		@Pc(27) boolean local27 = false;
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.anInt5789 = 0;
		this.priority = -1;
		@Pc(45) int local45;
		@Pc(52) SoftwareModel local52;
		for (local45 = 0; local45 < arg1; local45++) {
			local52 = arg0[local45];
			if (local52 != null) {
				this.vertexCount += local52.vertexCount;
				this.triangleCount += local52.triangleCount;
				this.anInt5789 += local52.anInt5789;
				if (local52.trianglePriorities == null) {
					if (this.priority == -1) {
						this.priority = local52.priority;
					}
					if (this.priority != local52.priority) {
						local21 = true;
					}
				} else {
					local21 = true;
				}
				local23 |= local52.triangleAlpha != null;
				local25 |= local52.aShortArray92 != null;
				local27 |= local52.aByteArray74 != null;
			}
		}
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		this.triangleVertexA = new int[this.triangleCount];
		this.triangleVertexB = new int[this.triangleCount];
		this.triangleVertexC = new int[this.triangleCount];
		this.anIntArray533 = new int[this.triangleCount];
		this.anIntArray523 = new int[this.triangleCount];
		this.triangleInfo = new int[this.triangleCount];
		if (local21) {
			this.trianglePriorities = new byte[this.triangleCount];
		}
		if (local23) {
			this.triangleAlpha = new byte[this.triangleCount];
		}
		if (local25) {
			this.aShortArray92 = new short[this.triangleCount];
		}
		if (local27) {
			this.aByteArray74 = new byte[this.triangleCount];
		}
		if (this.anInt5789 > 0) {
			this.textureFacesP = new int[this.anInt5789];
			this.textureFacesM = new int[this.anInt5789];
			this.textureFacesN = new int[this.anInt5789];
		}
		this.triangleColors = new short[this.triangleCount];
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.anInt5789 = 0;
		for (local45 = 0; local45 < arg1; local45++) {
			local52 = arg0[local45];
			if (local52 != null) {
				@Pc(251) int local251;
				for (local251 = 0; local251 < local52.triangleCount; local251++) {
					this.triangleVertexA[this.triangleCount] = local52.triangleVertexA[local251] + this.vertexCount;
					this.triangleVertexB[this.triangleCount] = local52.triangleVertexB[local251] + this.vertexCount;
					this.triangleVertexC[this.triangleCount] = local52.triangleVertexC[local251] + this.vertexCount;
					this.anIntArray533[this.triangleCount] = local52.anIntArray533[local251];
					this.anIntArray523[this.triangleCount] = local52.anIntArray523[local251];
					this.triangleInfo[this.triangleCount] = local52.triangleInfo[local251];
					this.triangleColors[this.triangleCount] = local52.triangleColors[local251];
					if (local21) {
						if (local52.trianglePriorities == null) {
							this.trianglePriorities[this.triangleCount] = local52.priority;
						} else {
							this.trianglePriorities[this.triangleCount] = local52.trianglePriorities[local251];
						}
					}
					if (local23 && local52.triangleAlpha != null) {
						this.triangleAlpha[this.triangleCount] = local52.triangleAlpha[local251];
					}
					if (local25) {
						if (local52.aShortArray92 == null) {
							this.aShortArray92[this.triangleCount] = -1;
						} else {
							this.aShortArray92[this.triangleCount] = local52.aShortArray92[local251];
						}
					}
					if (local27) {
						if (local52.aByteArray74 == null || local52.aByteArray74[local251] == -1) {
							this.aByteArray74[this.triangleCount] = -1;
						} else {
							this.aByteArray74[this.triangleCount] = (byte) (local52.aByteArray74[local251] + this.anInt5789);
						}
					}
					this.triangleCount++;
				}
				for (local251 = 0; local251 < local52.anInt5789; local251++) {
					this.textureFacesP[this.anInt5789] = local52.textureFacesP[local251] + this.vertexCount;
					this.textureFacesM[this.anInt5789] = local52.textureFacesM[local251] + this.vertexCount;
					this.textureFacesN[this.anInt5789] = local52.textureFacesN[local251] + this.vertexCount;
					this.anInt5789++;
				}
				for (local251 = 0; local251 < local52.vertexCount; local251++) {
					this.vertexX[this.vertexCount] = local52.vertexX[local251];
					this.vertexY[this.vertexCount] = local52.vertexY[local251];
					this.vertexZ[this.vertexCount] = local52.vertexZ[local251];
					this.vertexCount++;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "m", descriptor = "()V")
	public static void method4580() {
		aBoolean307 = true;
		anIntArray550 = new int[4096];
		depthTriangles = new int[4096];
		anIntArray558 = null;
		anIntArrayArray44 = null;
		anIntArray552 = null;
		anIntArrayArray43 = null;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "o", descriptor = "()V")
	public static void method4583() {
		aBoolean307 = false;
		anIntArray550 = null;
		depthTriangles = null;
		anIntArray558 = new int[1600];
		anIntArrayArray44 = new int[1600][64];
		anIntArray552 = new int[32];
		anIntArrayArray43 = new int[32][512];
	}

	@OriginalMember(owner = "runetek4.client!w", name = "e", descriptor = "(I)V")
	private void drawTriangle(@OriginalArg(0) int arg0) {
		if (projectTriangle[arg0]) {
			this.drawProjectedTriangle(arg0);
			return;
		}
		@Pc(12) int local12 = this.triangleVertexA[arg0];
		@Pc(17) int local17 = this.triangleVertexB[arg0];
		@Pc(22) int local22 = this.triangleVertexC[arg0];
		Rasterizer.testX = testTriangleX[arg0];
		if (this.triangleAlpha == null) {
			Rasterizer.alpha = 0;
		} else {
			Rasterizer.alpha = this.triangleAlpha[arg0] & 0xFF;
		}
		if (this.aShortArray92 != null && this.aShortArray92[arg0] != -1) {
			@Pc(141) int local141;
			@Pc(146) int local146;
			@Pc(151) int local151;
			if (this.aByteArray74 == null || this.aByteArray74[arg0] == -1) {
				local141 = local12;
				local146 = local17;
				local151 = local22;
			} else {
				@Pc(136) int local136 = this.aByteArray74[arg0] & 0xFF;
				local141 = this.textureFacesP[local136];
				local146 = this.textureFacesM[local136];
				local151 = this.textureFacesN[local136];
			}
			if (this.triangleInfo[arg0] == -1) {
				Rasterizer.fillTexturedAlphaTriangle(vertexScreenY[local12], vertexScreenY[local17], vertexScreenY[local22], vertexScreenX[local12], vertexScreenX[local17], vertexScreenX[local22], this.anIntArray533[arg0], this.anIntArray533[arg0], this.anIntArray533[arg0], projectSceneX[local141], projectSceneX[local146], projectSceneX[local151], projectSceneY[local141], projectSceneY[local146], projectSceneY[local151], projectSceneZ[local141], projectSceneZ[local146], projectSceneZ[local151], this.aShortArray92[arg0]);
			} else {
				Rasterizer.fillTexturedAlphaTriangle(vertexScreenY[local12], vertexScreenY[local17], vertexScreenY[local22], vertexScreenX[local12], vertexScreenX[local17], vertexScreenX[local22], this.anIntArray533[arg0], this.anIntArray523[arg0], this.triangleInfo[arg0], projectSceneX[local141], projectSceneX[local146], projectSceneX[local151], projectSceneY[local141], projectSceneY[local146], projectSceneY[local151], projectSceneZ[local141], projectSceneZ[local146], projectSceneZ[local151], this.aShortArray92[arg0]);
			}
		} else if (this.triangleInfo[arg0] == -1) {
			Rasterizer.fillTriangle(vertexScreenY[local12], vertexScreenY[local17], vertexScreenY[local22], vertexScreenX[local12], vertexScreenX[local17], vertexScreenX[local22], Rasterizer.palette[this.anIntArray533[arg0] & 0xFFFF]);
		} else {
			Rasterizer.fillGouraudTriangle(vertexScreenY[local12], vertexScreenY[local17], vertexScreenY[local22], vertexScreenX[local12], vertexScreenX[local17], vertexScreenX[local22], this.anIntArray533[arg0] & 0xFFFF, this.anIntArray523[arg0] & 0xFFFF, this.triangleInfo[arg0] & 0xFFFF);
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "e", descriptor = "()V")
	@Override
	public final void method4552() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexX[local1] = -this.vertexX[local1];
			this.vertexZ[local1] = -this.vertexZ[local1];
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "n", descriptor = "()V")
	private void method4581() {
		for (@Pc(1) int local1 = 0; local1 < this.triangleCount; local1++) {
			@Pc(15) short local15 = this.aShortArray92 == null ? -1 : this.aShortArray92[local1];
			if (local15 == -1) {
				@Pc(25) int local25 = this.triangleColors[local1] & 0xFFFF;
				@Pc(38) int local38;
				if (this.triangleInfo[local1] == -1) {
					local38 = this.anIntArray533[local1] & 0xFFFE0000;
					this.anIntArray533[local1] = local38 | ColorUtils.multiplyLightness2(local25, local38 >> 17);
				} else if (this.triangleInfo[local1] != -2) {
					local38 = this.anIntArray533[local1] & 0xFFFE0000;
					this.anIntArray533[local1] = local38 | ColorUtils.multiplyLightness2(local25, local38 >> 17);
					local38 = this.anIntArray523[local1] & 0xFFFE0000;
					this.anIntArray523[local1] = local38 | ColorUtils.multiplyLightness2(local25, local38 >> 17);
					local38 = this.triangleInfo[local1] & 0xFFFE0000;
					this.triangleInfo[local1] = local38 | ColorUtils.multiplyLightness2(local25, local38 >> 17);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "k", descriptor = "()I")
	@Override
	public final int getMinZ() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.minZ;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "b", descriptor = "()I")
	@Override
	public final int getMinY() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.minY;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(ZZLclient!w;[B[S[I[I[I)Lclient!ak;")
	private Model copy(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) SoftwareModel arg2, @OriginalArg(3) byte[] arg3, @OriginalArg(4) short[] arg4, @OriginalArg(5) int[] arg5, @OriginalArg(6) int[] arg6, @OriginalArg(7) int[] arg7) {
		arg2.vertexCount = this.vertexCount;
		arg2.triangleCount = this.triangleCount;
		arg2.anInt5789 = this.anInt5789;
		if (arg2.vertexX == null || arg2.vertexX.length < this.vertexCount) {
			arg2.vertexX = new int[this.vertexCount + 100];
			arg2.vertexY = new int[this.vertexCount + 100];
			arg2.vertexZ = new int[this.vertexCount + 100];
		}
		@Pc(43) int local43;
		for (local43 = 0; local43 < this.vertexCount; local43++) {
			arg2.vertexX[local43] = this.vertexX[local43];
			arg2.vertexY[local43] = this.vertexY[local43];
			arg2.vertexZ[local43] = this.vertexZ[local43];
		}
		if (arg0) {
			arg2.triangleAlpha = this.triangleAlpha;
		} else {
			arg2.triangleAlpha = arg3;
			if (this.triangleAlpha == null) {
				for (local43 = 0; local43 < this.triangleCount; local43++) {
					arg2.triangleAlpha[local43] = 0;
				}
			} else {
				for (local43 = 0; local43 < this.triangleCount; local43++) {
					arg2.triangleAlpha[local43] = this.triangleAlpha[local43];
				}
			}
		}
		if (arg1) {
			arg2.triangleColors = this.triangleColors;
			arg2.anIntArray533 = this.anIntArray533;
			arg2.anIntArray523 = this.anIntArray523;
			arg2.triangleInfo = this.triangleInfo;
		} else {
			arg2.triangleColors = arg4;
			arg2.anIntArray533 = arg5;
			arg2.anIntArray523 = arg6;
			arg2.triangleInfo = arg7;
			for (local43 = 0; local43 < this.triangleCount; local43++) {
				arg2.triangleColors[local43] = this.triangleColors[local43];
				arg2.anIntArray533[local43] = this.anIntArray533[local43];
				arg2.anIntArray523[local43] = this.anIntArray523[local43];
				arg2.triangleInfo[local43] = this.triangleInfo[local43];
			}
		}
		arg2.triangleVertexA = this.triangleVertexA;
		arg2.triangleVertexB = this.triangleVertexB;
		arg2.triangleVertexC = this.triangleVertexC;
		arg2.trianglePriorities = this.trianglePriorities;
		arg2.aByteArray74 = this.aByteArray74;
		arg2.aShortArray92 = this.aShortArray92;
		arg2.priority = this.priority;
		arg2.textureFacesP = this.textureFacesP;
		arg2.textureFacesM = this.textureFacesM;
		arg2.textureFacesN = this.textureFacesN;
		arg2.boneVertices = this.boneVertices;
		arg2.boneTriangles = this.boneTriangles;
		arg2.vertexSources = this.vertexSources;
		arg2.triangleSources = this.triangleSources;
		arg2.pickable = this.pickable;
		arg2.boundsValid = false;
		return arg2;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "d", descriptor = "(I)V")
	@Override
	public final void rotateX(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = MathUtils.sin[arg0];
		@Pc(7) int local7 = MathUtils.cos[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.vertexY[local9] * local7 - this.vertexZ[local9] * local3 >> 16;
			this.vertexZ[local9] = this.vertexY[local9] * local3 + this.vertexZ[local9] * local7 >> 16;
			this.vertexY[local9] = local29;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "d", descriptor = "()Z")
	@Override
	protected final boolean method4551() {
		if (this.boneVertices == null) {
			return false;
		} else {
			anInt5793 = 0;
			anInt5791 = 0;
			anInt5792 = 0;
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "c", descriptor = "(I)V")
	@Override
	public final void rotateZ(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = MathUtils.sin[arg0];
		@Pc(7) int local7 = MathUtils.cos[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.vertexY[local9] * local3 + this.vertexX[local9] * local7 >> 16;
			this.vertexY[local9] = this.vertexY[local9] * local7 - this.vertexX[local9] * local3 >> 16;
			this.vertexX[local9] = local29;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void render(@OriginalArg(0) int yaw, @OriginalArg(1) int sinCameraPitch, @OriginalArg(2) int cosCameraPitch, @OriginalArg(3) int sinCameraYaw, @OriginalArg(4) int cosCameraYaw, @OriginalArg(5) int sceneX, @OriginalArg(6) int sceneY, @OriginalArg(7) int sceneZ, @OriginalArg(8) long key, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		@Pc(14) int a = sceneZ * cosCameraYaw - sceneX * sinCameraYaw >> 16;
		@Pc(24) int b = sceneY * sinCameraPitch + a * cosCameraPitch >> 16;
		@Pc(38) int c = b + (this.lengthXZ * cosCameraPitch + this.maxY * sinCameraPitch >> 16);
		@Pc(53) int d = b + (-this.lengthXZ * cosCameraPitch + this.minY * sinCameraPitch >> 16);
		if (c <= 50 || d >= 3500) {
			return;
		}
		@Pc(71) int e = sceneZ * sinCameraYaw + sceneX * cosCameraYaw >> 16;
		@Pc(78) int minScreenX = e + this.lengthXZ << 9;
		if (minScreenX / c <= Rasterizer.screenLowerX) {
			return;
		}
		@Pc(91) int maxScreenX = e - this.lengthXZ << 9;
		if (maxScreenX / c >= Rasterizer.screenUpperX) {
			return;
		}
		@Pc(107) int f = sceneY * cosCameraPitch - a * sinCameraPitch >> 16;
		@Pc(123) int minScreenY = f + (this.lengthXZ * sinCameraPitch + this.maxY * cosCameraPitch >> 16) << 9;
		if (minScreenY / c <= Rasterizer.screenLowerY) {
			return;
		}
		@Pc(146) int maxScreenY = f + (-this.lengthXZ * sinCameraPitch + this.minY * cosCameraPitch >> 16) << 9;
		if (maxScreenY / c >= Rasterizer.screenUpperY) {
			return;
		}
		@Pc(154) boolean project1 = false;
		@Pc(161) boolean project2 = d <= 50;
		@Pc(170) boolean project = project2 || this.anInt5789 > 0;
		@Pc(172) int cx = Rasterizer.centerX;
		@Pc(174) int cz = Rasterizer.centerY;
		@Pc(176) int yawsin = 0;
		@Pc(178) int yawcos = 0;
		if (yaw != 0) {
			yawsin = MathUtils.sin[yaw];
			yawcos = MathUtils.cos[yaw];
		}
		@Pc(190) boolean local190 = false;
		@Pc(204) int v;
		@Pc(223) int x;
		@Pc(208) int y;
		@Pc(227) int z;
		if (key > 0L && RawModel.allowInput && d > 0) {
			if (e > 0) {
				v = maxScreenX / c;
				y = minScreenX / d;
			} else {
				v = maxScreenX / d;
				y = minScreenX / c;
			}
			if (f > 0) {
				x = maxScreenY / c;
				z = minScreenY / d;
			} else {
				x = maxScreenY / d;
				z = minScreenY / c;
			}
			if (GlModel.anInt3582 >= v && GlModel.anInt3582 <= y && RawModel.anInt1053 >= x && RawModel.anInt1053 <= z) {
				v = 999999;
				y = -999999;
				x = 999999;
				z = -999999;
				@Pc(299) int[] local299 = new int[] { this.minX, this.maxX, this.minX, this.maxX, this.minX, this.maxX, this.minX, this.maxX};
				@Pc(342) int[] local342 = new int[] { this.minZ, this.minZ, this.maxZ, this.maxZ, this.minZ, this.minZ, this.maxZ, this.maxZ};
				@Pc(385) int[] local385 = new int[] { this.minY, this.minY, this.minY, this.minY, this.maxY, this.maxY, this.maxY, this.maxY};
				for (@Pc(387) int local387 = 0; local387 < 8; local387++) {
					@Pc(394) int local394 = local299[local387];
					@Pc(398) int local398 = local385[local387];
					@Pc(402) int local402 = local342[local387];
					@Pc(414) int local414;
					if (yaw != 0) {
						local414 = local402 * yawsin + local394 * yawcos >> 16;
						local402 = local402 * yawcos - local394 * yawsin >> 16;
						local394 = local414;
					}
					local394 += sceneX;
					local398 += sceneY;
					local402 += sceneZ;
					local414 = local402 * sinCameraYaw + local394 * cosCameraYaw >> 16;
					local402 = local402 * cosCameraYaw - local394 * sinCameraYaw >> 16;
					local394 = local414;
					local414 = local398 * cosCameraPitch - local402 * sinCameraPitch >> 16;
					local402 = local398 * sinCameraPitch + local402 * cosCameraPitch >> 16;
					if (local402 > 0) {
						@Pc(490) int local490 = (local394 << 9) / local402;
						@Pc(496) int local496 = (local414 << 9) / local402;
						if (local490 < v) {
							v = local490;
						}
						if (local490 > y) {
							y = local490;
						}
						if (local496 < x) {
							x = local496;
						}
						if (local496 > z) {
							z = local496;
						}
					}
				}
				if (GlModel.anInt3582 >= v && GlModel.anInt3582 <= y && RawModel.anInt1053 >= x && RawModel.anInt1053 <= z) {
					if (this.pickable) {
						Model.aLongArray11[MiniMenu.pickedEntityCount++] = key;
					} else {
						local190 = true;
					}
				}
			}
		}
		for (v = 0; v < this.vertexCount; v++) {
			x = this.vertexX[v];
			y = this.vertexY[v];
			z = this.vertexZ[v];
			@Pc(577) int w;
			if (yaw != 0) {
				w = z * yawsin + x * yawcos >> 16;
				z = z * yawcos - x * yawsin >> 16;
				x = w;
			}
			x += sceneX;
			y += sceneY;
			z += sceneZ;
			w = z * sinCameraYaw + x * cosCameraYaw >> 16;
			z = z * cosCameraYaw - x * sinCameraYaw >> 16;
			x = w;
			w = y * cosCameraPitch - z * sinCameraPitch >> 16;
			z = y * sinCameraPitch + z * cosCameraPitch >> 16;
			vertexDepth[v] = z - b;
			if (z >= 50) {
				vertexScreenX[v] = cx + (x << 9) / z;
				vertexScreenY[v] = cz + (w << 9) / z;
			} else {
				vertexScreenX[v] = -5000;
				project1 = true;
			}
			if (project) {
				projectSceneX[v] = x;
				projectSceneY[v] = w;
				projectSceneZ[v] = z;
			}
		}
		try {
			this.draw(project1, local190, key, b - d, c - d + 2, arg10);
		} catch (@Pc(713) Exception ignored) {
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(II[[I[[IIIIZ)Lclient!w;")
	public final SoftwareModel method4586(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[][] arg2, @OriginalArg(3) int[][] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) boolean arg7) {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		@Pc(9) int local9 = arg4 + this.minX;
		@Pc(14) int local14 = arg4 + this.maxX;
		@Pc(19) int local19 = arg6 + this.minZ;
		@Pc(24) int local24 = arg6 + this.maxZ;
		if ((arg0 == 1 || arg0 == 2 || arg0 == 3 || arg0 == 5) && (local9 < 0 || local14 + 128 >> 7 >= arg2.length || local19 < 0 || local24 + 128 >> 7 >= arg2[0].length)) {
			return this;
		}
		if (arg0 == 4 || arg0 == 5) {
			if (arg3 == null) {
				return this;
			}
			if (local9 < 0 || local14 + 128 >> 7 >= arg3.length || local19 < 0 || local24 + 128 >> 7 >= arg3[0].length) {
				return this;
			}
		} else {
			local9 >>= 0x7;
			local14 = local14 + 127 >> 7;
			local19 >>= 0x7;
			local24 = local24 + 127 >> 7;
			if (arg2[local9][local19] == arg5 && arg2[local14][local19] == arg5 && arg2[local9][local24] == arg5 && arg2[local14][local24] == arg5) {
				return this;
			}
		}
		@Pc(150) SoftwareModel local150;
		if (arg7) {
			local150 = new SoftwareModel();
			local150.vertexCount = this.vertexCount;
			local150.triangleCount = this.triangleCount;
			local150.anInt5789 = this.anInt5789;
			local150.triangleVertexA = this.triangleVertexA;
			local150.triangleVertexB = this.triangleVertexB;
			local150.triangleVertexC = this.triangleVertexC;
			local150.anIntArray533 = this.anIntArray533;
			local150.anIntArray523 = this.anIntArray523;
			local150.triangleInfo = this.triangleInfo;
			local150.trianglePriorities = this.trianglePriorities;
			local150.aByteArray74 = this.aByteArray74;
			local150.aShortArray92 = this.aShortArray92;
			local150.triangleColors = this.triangleColors;
			local150.triangleAlpha = this.triangleAlpha;
			local150.priority = this.priority;
			local150.textureFacesP = this.textureFacesP;
			local150.textureFacesM = this.textureFacesM;
			local150.textureFacesN = this.textureFacesN;
			local150.boneVertices = this.boneVertices;
			local150.boneTriangles = this.boneTriangles;
			local150.vertexSources = this.vertexSources;
			local150.triangleSources = this.triangleSources;
			local150.pickable = this.pickable;
			if (arg0 == 3) {
				local150.vertexX = ArrayUtils.copyOfNullable(this.vertexX);
				local150.vertexY = ArrayUtils.copyOfNullable(this.vertexY);
				local150.vertexZ = ArrayUtils.copyOfNullable(this.vertexZ);
			} else {
				local150.vertexX = this.vertexX;
				local150.vertexY = new int[local150.vertexCount];
				local150.vertexZ = this.vertexZ;
			}
		} else {
			local150 = this;
		}
		@Pc(285) int local285;
		@Pc(296) int local296;
		@Pc(303) int local303;
		@Pc(307) int local307;
		@Pc(311) int local311;
		@Pc(315) int local315;
		@Pc(319) int local319;
		@Pc(341) int local341;
		@Pc(367) int local367;
		@Pc(379) int local379;
		if (arg0 == 1) {
			for (local285 = 0; local285 < local150.vertexCount; local285++) {
				local296 = this.vertexX[local285] + arg4;
				local303 = this.vertexZ[local285] + arg6;
				local307 = local296 & 0x7F;
				local311 = local303 & 0x7F;
				local315 = local296 >> 7;
				local319 = local303 >> 7;
				local341 = arg2[local315][local319] * (128 - local307) + arg2[local315 + 1][local319] * local307 >> 7;
				local367 = arg2[local315][local319 + 1] * (128 - local307) + arg2[local315 + 1][local319 + 1] * local307 >> 7;
				local379 = local341 * (128 - local311) + local367 * local311 >> 7;
				local150.vertexY[local285] = this.vertexY[local285] + local379 - arg5;
			}
		} else {
			@Pc(506) int local506;
			if (arg0 == 2) {
				for (local285 = 0; local285 < local150.vertexCount; local285++) {
					local296 = (this.vertexY[local285] << 16) / this.minY;
					if (local296 < arg1) {
						local303 = this.vertexX[local285] + arg4;
						local307 = this.vertexZ[local285] + arg6;
						local311 = local303 & 0x7F;
						local315 = local307 & 0x7F;
						local319 = local303 >> 7;
						local341 = local307 >> 7;
						local367 = arg2[local319][local341] * (128 - local311) + arg2[local319 + 1][local341] * local311 >> 7;
						local379 = arg2[local319][local341 + 1] * (128 - local311) + arg2[local319 + 1][local341 + 1] * local311 >> 7;
						local506 = local367 * (128 - local315) + local379 * local315 >> 7;
						local150.vertexY[local285] = this.vertexY[local285] + (local506 - arg5) * (arg1 - local296) / arg1;
					} else {
						local150.vertexY[local285] = this.vertexY[local285];
					}
				}
			} else if (arg0 == 3) {
				local285 = (arg1 & 0xFF) * 4;
				local296 = (arg1 >> 8 & 0xFF) * 4;
				local150.method4573(arg2, arg4, arg5, arg6, local285, local296);
			} else if (arg0 == 4) {
				local285 = this.maxY - this.minY;
				for (local296 = 0; local296 < this.vertexCount; local296++) {
					local303 = this.vertexX[local296] + arg4;
					local307 = this.vertexZ[local296] + arg6;
					local311 = local303 & 0x7F;
					local315 = local307 & 0x7F;
					local319 = local303 >> 7;
					local341 = local307 >> 7;
					local367 = arg3[local319][local341] * (128 - local311) + arg3[local319 + 1][local341] * local311 >> 7;
					local379 = arg3[local319][local341 + 1] * (128 - local311) + arg3[local319 + 1][local341 + 1] * local311 >> 7;
					local506 = local367 * (128 - local315) + local379 * local315 >> 7;
					local150.vertexY[local296] = this.vertexY[local296] + local506 + local285 - arg5;
				}
			} else if (arg0 == 5) {
				local285 = this.maxY - this.minY;
				for (local296 = 0; local296 < this.vertexCount; local296++) {
					local303 = this.vertexX[local296] + arg4;
					local307 = this.vertexZ[local296] + arg6;
					local311 = local303 & 0x7F;
					local315 = local307 & 0x7F;
					local319 = local303 >> 7;
					local341 = local307 >> 7;
					local367 = arg2[local319][local341] * (128 - local311) + arg2[local319 + 1][local341] * local311 >> 7;
					local379 = arg2[local319][local341 + 1] * (128 - local311) + arg2[local319 + 1][local341 + 1] * local311 >> 7;
					local506 = local367 * (128 - local315) + local379 * local315 >> 7;
					local367 = arg3[local319][local341] * (128 - local311) + arg3[local319 + 1][local341] * local311 >> 7;
					local379 = arg3[local319][local341 + 1] * (128 - local311) + arg3[local319 + 1][local341 + 1] * local311 >> 7;
					@Pc(849) int local849 = local367 * (128 - local315) + local379 * local315 >> 7;
					@Pc(853) int local853 = local506 - local849;
					local150.vertexY[local296] = ((this.vertexY[local296] << 8) / local285 * local853 >> 8) - (arg5 - local506);
				}
			}
		}
		local150.boundsValid = false;
		return local150;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "i", descriptor = "()V")
	@Override
	public final void rotateCounterClockwise() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.vertexX[local1];
			this.vertexX[local1] = this.vertexZ[local1];
			this.vertexZ[local1] = -local10;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(IIIIIIIJ)V")
	@Override
	public final void setCamera(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) long arg6) {
		try {
			if (!this.boundsValid) {
				this.calculateBounds();
			}
			@Pc(6) int local6 = Rasterizer.centerX;
			@Pc(8) int local8 = Rasterizer.centerY;
			@Pc(12) int local12 = MathUtils.sin[0];
			@Pc(16) int local16 = MathUtils.cos[0];
			@Pc(20) int local20 = MathUtils.sin[arg0];
			@Pc(24) int local24 = MathUtils.cos[arg0];
			@Pc(28) int local28 = MathUtils.sin[arg1];
			@Pc(32) int local32 = MathUtils.cos[arg1];
			@Pc(36) int local36 = MathUtils.sin[arg2];
			@Pc(40) int local40 = MathUtils.cos[arg2];
			@Pc(50) int local50 = arg4 * local36 + arg5 * local40 >> 16;
			for (@Pc(52) int local52 = 0; local52 < this.vertexCount; local52++) {
				@Pc(61) int local61 = this.vertexX[local52];
				@Pc(66) int local66 = this.vertexY[local52];
				@Pc(71) int local71 = this.vertexZ[local52];
				@Pc(83) int local83;
				if (arg1 != 0) {
					local83 = local66 * local28 + local61 * local32 >> 16;
					local66 = local66 * local32 - local61 * local28 >> 16;
					local61 = local83;
				}
				if (arg0 != 0) {
					local83 = local71 * local20 + local61 * local24 >> 16;
					local71 = local71 * local24 - local61 * local20 >> 16;
					local61 = local83;
				}
				local61 += arg3;
				local66 += arg4;
				local71 += arg5;
				local83 = local66 * local40 - local71 * local36 >> 16;
				local71 = local66 * local36 + local71 * local40 >> 16;
				vertexDepth[local52] = local71 - local50;
				vertexScreenX[local52] = local6 + (local61 << 9) / local71;
				vertexScreenY[local52] = local8 + (local83 << 9) / local71;
				if (this.anInt5789 > 0) {
					projectSceneX[local52] = local61;
					projectSceneY[local52] = local83;
					projectSceneZ[local52] = local71;
				}
			}
			this.draw(false, arg6 >= 0L, arg6, this.aShort35, this.aShort35 << 1, null);
		} catch (@Pc(240) RuntimeException ignored) {
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "g", descriptor = "(I)V")
	private void drawProjectedTriangle(@OriginalArg(0) int arg0) {
		@Pc(1) int local1 = Rasterizer.centerX;
		@Pc(3) int local3 = Rasterizer.centerY;
		@Pc(5) int local5 = 0;
		@Pc(10) int local10 = this.triangleVertexA[arg0];
		@Pc(15) int local15 = this.triangleVertexB[arg0];
		@Pc(20) int local20 = this.triangleVertexC[arg0];
		@Pc(24) int local24 = projectSceneZ[local10];
		@Pc(28) int local28 = projectSceneZ[local15];
		@Pc(32) int local32 = projectSceneZ[local20];
		if (this.triangleAlpha == null) {
			Rasterizer.alpha = 0;
		} else {
			Rasterizer.alpha = this.triangleAlpha[arg0] & 0xFF;
		}
		@Pc(75) int local75;
		@Pc(79) int local79;
		@Pc(86) int local86;
		@Pc(99) int local99;
		if (local24 >= 50) {
			anIntArray542[0] = vertexScreenX[local10];
			anIntArray547[0] = vertexScreenY[local10];
			local5++;
			anIntArray553[0] = this.anIntArray533[arg0] & 0xFFFF;
		} else {
			local75 = projectSceneX[local10];
			local79 = projectSceneY[local10];
			local86 = this.anIntArray533[arg0] & 0xFFFF;
			if (local32 >= 50) {
				local99 = (50 - local24) * MathUtils.reciprical16[local32 - local24];
				anIntArray542[0] = local1 + (local75 + ((projectSceneX[local20] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[0] = local3 + (local79 + ((projectSceneY[local20] - local79) * local99 >> 16) << 9) / 50;
				local5++;
				anIntArray553[0] = local86 + (((this.triangleInfo[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
			if (local28 >= 50) {
				local99 = (50 - local24) * MathUtils.reciprical16[local28 - local24];
				anIntArray542[local5] = local1 + (local75 + ((projectSceneX[local15] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[local5] = local3 + (local79 + ((projectSceneY[local15] - local79) * local99 >> 16) << 9) / 50;
				anIntArray553[local5++] = local86 + (((this.anIntArray523[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
		}
		if (local28 >= 50) {
			anIntArray542[local5] = vertexScreenX[local15];
			anIntArray547[local5] = vertexScreenY[local15];
			anIntArray553[local5++] = this.anIntArray523[arg0] & 0xFFFF;
		} else {
			local75 = projectSceneX[local15];
			local79 = projectSceneY[local15];
			local86 = this.anIntArray523[arg0] & 0xFFFF;
			if (local24 >= 50) {
				local99 = (50 - local28) * MathUtils.reciprical16[local24 - local28];
				anIntArray542[local5] = local1 + (local75 + ((projectSceneX[local10] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[local5] = local3 + (local79 + ((projectSceneY[local10] - local79) * local99 >> 16) << 9) / 50;
				anIntArray553[local5++] = local86 + (((this.anIntArray533[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
			if (local32 >= 50) {
				local99 = (50 - local28) * MathUtils.reciprical16[local32 - local28];
				anIntArray542[local5] = local1 + (local75 + ((projectSceneX[local20] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[local5] = local3 + (local79 + ((projectSceneY[local20] - local79) * local99 >> 16) << 9) / 50;
				anIntArray553[local5++] = local86 + (((this.triangleInfo[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
		}
		if (local32 >= 50) {
			anIntArray542[local5] = vertexScreenX[local20];
			anIntArray547[local5] = vertexScreenY[local20];
			anIntArray553[local5++] = this.triangleInfo[arg0] & 0xFFFF;
		} else {
			local75 = projectSceneX[local20];
			local79 = projectSceneY[local20];
			local86 = this.triangleInfo[arg0] & 0xFFFF;
			if (local28 >= 50) {
				local99 = (50 - local32) * MathUtils.reciprical16[local28 - local32];
				anIntArray542[local5] = local1 + (local75 + ((projectSceneX[local15] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[local5] = local3 + (local79 + ((projectSceneY[local15] - local79) * local99 >> 16) << 9) / 50;
				anIntArray553[local5++] = local86 + (((this.anIntArray523[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
			if (local24 >= 50) {
				local99 = (50 - local32) * MathUtils.reciprical16[local24 - local32];
				anIntArray542[local5] = local1 + (local75 + ((projectSceneX[local10] - local75) * local99 >> 16) << 9) / 50;
				anIntArray547[local5] = local3 + (local79 + ((projectSceneY[local10] - local79) * local99 >> 16) << 9) / 50;
				anIntArray553[local5++] = local86 + (((this.anIntArray533[arg0] & 0xFFFF) - local86) * local99 >> 16);
			}
		}
		local75 = anIntArray542[0];
		local79 = anIntArray542[1];
		local86 = anIntArray542[2];
		local99 = anIntArray547[0];
		@Pc(614) int local614 = anIntArray547[1];
		@Pc(618) int local618 = anIntArray547[2];
		Rasterizer.testX = false;
		@Pc(709) int local709;
		@Pc(714) int local714;
		@Pc(719) int local719;
		@Pc(704) int local704;
		if (local5 == 3) {
			if (local75 < 0 || local79 < 0 || local86 < 0 || local75 > Rasterizer.width || local79 > Rasterizer.width || local86 > Rasterizer.width) {
				Rasterizer.testX = true;
			}
			if (this.aShortArray92 != null && this.aShortArray92[arg0] != -1) {
				if (this.aByteArray74 == null || this.aByteArray74[arg0] == -1) {
					local709 = local10;
					local714 = local15;
					local719 = local20;
				} else {
					local704 = this.aByteArray74[arg0] & 0xFF;
					local709 = this.textureFacesP[local704];
					local714 = this.textureFacesM[local704];
					local719 = this.textureFacesN[local704];
				}
				if (this.triangleInfo[arg0] == -1) {
					Rasterizer.fillTexturedAlphaTriangle(local99, local614, local618, local75, local79, local86, this.anIntArray533[arg0], this.anIntArray533[arg0], this.anIntArray533[arg0], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], this.aShortArray92[arg0]);
				} else {
					Rasterizer.fillTexturedAlphaTriangle(local99, local614, local618, local75, local79, local86, anIntArray553[0], anIntArray553[1], anIntArray553[2], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], this.aShortArray92[arg0]);
				}
			} else if (this.triangleInfo[arg0] == -1) {
				Rasterizer.fillTriangle(local99, local614, local618, local75, local79, local86, Rasterizer.palette[this.anIntArray533[arg0] & 0xFFFF]);
			} else {
				Rasterizer.fillGouraudTriangle(local99, local614, local618, local75, local79, local86, anIntArray553[0], anIntArray553[1], anIntArray553[2]);
			}
		}
		if (local5 != 4) {
			return;
		}
		if (local75 < 0 || local79 < 0 || local86 < 0 || local75 > Rasterizer.width || local79 > Rasterizer.width || local86 > Rasterizer.width || anIntArray542[3] < 0 || anIntArray542[3] > Rasterizer.width) {
			Rasterizer.testX = true;
		}
		if (this.aShortArray92 != null && this.aShortArray92[arg0] != -1) {
			if (this.aByteArray74 == null || this.aByteArray74[arg0] == -1) {
				local709 = local10;
				local714 = local15;
				local719 = local20;
			} else {
				local704 = this.aByteArray74[arg0] & 0xFF;
				local709 = this.textureFacesP[local704];
				local714 = this.textureFacesM[local704];
				local719 = this.textureFacesN[local704];
			}
			@Pc(984) short local984 = this.aShortArray92[arg0];
			if (this.triangleInfo[arg0] == -1) {
				Rasterizer.fillTexturedAlphaTriangle(local99, local614, local618, local75, local79, local86, this.anIntArray533[arg0], this.anIntArray533[arg0], this.anIntArray533[arg0], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], local984);
				Rasterizer.fillTexturedAlphaTriangle(local99, local618, anIntArray547[3], local75, local86, anIntArray542[3], this.anIntArray533[arg0], this.anIntArray533[arg0], this.anIntArray533[arg0], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], local984);
			} else {
				Rasterizer.fillTexturedAlphaTriangle(local99, local614, local618, local75, local79, local86, anIntArray553[0], anIntArray553[1], anIntArray553[2], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], local984);
				Rasterizer.fillTexturedAlphaTriangle(local99, local618, anIntArray547[3], local75, local86, anIntArray542[3], anIntArray553[0], anIntArray553[2], anIntArray553[3], projectSceneX[local709], projectSceneX[local714], projectSceneX[local719], projectSceneY[local709], projectSceneY[local714], projectSceneY[local719], projectSceneZ[local709], projectSceneZ[local714], projectSceneZ[local719], local984);
			}
		} else if (this.triangleInfo[arg0] == -1) {
			local709 = Rasterizer.palette[this.anIntArray533[arg0] & 0xFFFF];
			Rasterizer.fillTriangle(local99, local614, local618, local75, local79, local86, local709);
			Rasterizer.fillTriangle(local99, local618, anIntArray547[3], local75, local86, anIntArray542[3], local709);
		} else {
			Rasterizer.fillGouraudTriangle(local99, local614, local618, local75, local79, local86, anIntArray553[0], anIntArray553[1], anIntArray553[2]);
			Rasterizer.fillGouraudTriangle(local99, local618, anIntArray547[3], local75, local86, anIntArray542[3], anIntArray553[0], anIntArray553[2], anIntArray553[3]);
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "c", descriptor = "()I")
	@Override
	public final int getMaxZ() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.maxZ;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "b", descriptor = "(III)V")
	@Override
	public final void resize(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexX[local1] = this.vertexX[local1] * arg0 / 128;
			this.vertexY[local1] = this.vertexY[local1] * arg1 / 128;
			this.vertexZ[local1] = this.vertexZ[local1] * arg2 / 128;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(ZZZ)Lclient!ak;")
	@Override
	public final Model method4560(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2) {
		if (!arg0 && aByteArray77.length < this.triangleCount) {
			aByteArray77 = new byte[this.triangleCount + 100];
		}
		if (!arg1 && aShortArray94.length < this.triangleCount) {
			anIntArray539 = new int[this.triangleCount + 100];
			anIntArray540 = new int[this.triangleCount + 100];
			anIntArray538 = new int[this.triangleCount + 100];
			aShortArray94 = new short[this.triangleCount + 100];
		}
		return this.copy(arg0, arg1, aClass8_Sub1_Sub2_2, aByteArray77, aShortArray94, anIntArray539, anIntArray540, anIntArray538);
	}

	@OriginalMember(owner = "runetek4.client!w", name = "j", descriptor = "()I")
	@Override
	public final int getLengthXZ() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.lengthXZ;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(Lclient!ak;)Lclient!ak;")
	public final Model method4588(@OriginalArg(0) Model arg0) {
		return new SoftwareModel(new SoftwareModel[] { this, (SoftwareModel) arg0 }, 2);
	}

	@OriginalMember(owner = "runetek4.client!w", name = "g", descriptor = "()I")
	@Override
	public final int getMaxX() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.maxX;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "f", descriptor = "()V")
	@Override
	protected final void method4557() {
		if (this.aBoolean304) {
			this.method4581();
			this.aBoolean304 = false;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "b", descriptor = "(I)V")
	@Override
	public final void rotateY(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = MathUtils.sin[arg0];
		@Pc(7) int local7 = MathUtils.cos[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.vertexZ[local9] * local3 + this.vertexX[local9] * local7 >> 16;
			this.vertexZ[local9] = this.vertexZ[local9] * local7 - this.vertexX[local9] * local3 >> 16;
			this.vertexX[local9] = local29;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(I[IIIIZ)V")
	@Override
	protected final void method4569(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
		@Pc(2) int local2 = arg1.length;
		@Pc(6) int local6;
		@Pc(14) int local14;
		@Pc(33) int local33;
		@Pc(41) int local41;
		if (arg0 == 0) {
			local6 = 0;
			anInt5793 = 0;
			anInt5791 = 0;
			anInt5792 = 0;
			for (local14 = 0; local14 < local2; local14++) {
				@Pc(21) int local21 = arg1[local14];
				if (local21 < this.boneVertices.length) {
					@Pc(31) int[] local31 = this.boneVertices[local21];
					for (local33 = 0; local33 < local31.length; local33++) {
						local41 = local31[local33];
						anInt5793 += this.vertexX[local41];
						anInt5791 += this.vertexY[local41];
						anInt5792 += this.vertexZ[local41];
						local6++;
					}
				}
			}
			if (local6 > 0) {
				anInt5793 = anInt5793 / local6 + arg2;
				anInt5791 = anInt5791 / local6 + arg3;
				anInt5792 = anInt5792 / local6 + arg4;
			} else {
				anInt5793 = arg2;
				anInt5791 = arg3;
				anInt5792 = arg4;
			}
			return;
		}
		@Pc(117) int[] local117;
		@Pc(119) int local119;
		if (arg0 == 1) {
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneVertices.length) {
					local117 = this.boneVertices[local14];
					for (local119 = 0; local119 < local117.length; local119++) {
						local33 = local117[local119];
						this.vertexX[local33] += arg2;
						this.vertexY[local33] += arg3;
						this.vertexZ[local33] += arg4;
					}
				}
			}
			return;
		}
		@Pc(222) int local222;
		@Pc(240) int local240;
		if (arg0 == 2) {
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneVertices.length) {
					local117 = this.boneVertices[local14];
					for (local119 = 0; local119 < local117.length; local119++) {
						local33 = local117[local119];
						this.vertexX[local33] -= anInt5793;
						this.vertexY[local33] -= anInt5791;
						this.vertexZ[local33] -= anInt5792;
						if (arg4 != 0) {
							local41 = MathUtils.sin[arg4];
							local222 = MathUtils.cos[arg4];
							local240 = this.vertexY[local33] * local41 + this.vertexX[local33] * local222 + 32767 >> 16;
							this.vertexY[local33] = this.vertexY[local33] * local222 + 32767 - this.vertexX[local33] * local41 >> 16;
							this.vertexX[local33] = local240;
						}
						if (arg2 != 0) {
							local41 = MathUtils.sin[arg2];
							local222 = MathUtils.cos[arg2];
							local240 = this.vertexY[local33] * local222 + 32767 - this.vertexZ[local33] * local41 >> 16;
							this.vertexZ[local33] = this.vertexY[local33] * local41 + this.vertexZ[local33] * local222 + 32767 >> 16;
							this.vertexY[local33] = local240;
						}
						if (arg3 != 0) {
							local41 = MathUtils.sin[arg3];
							local222 = MathUtils.cos[arg3];
							local240 = this.vertexZ[local33] * local41 + this.vertexX[local33] * local222 + 32767 >> 16;
							this.vertexZ[local33] = this.vertexZ[local33] * local222 + 32767 - this.vertexX[local33] * local41 >> 16;
							this.vertexX[local33] = local240;
						}
						this.vertexX[local33] += anInt5793;
						this.vertexY[local33] += anInt5791;
						this.vertexZ[local33] += anInt5792;
					}
				}
			}
		} else if (arg0 == 3) {
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneVertices.length) {
					local117 = this.boneVertices[local14];
					for (local119 = 0; local119 < local117.length; local119++) {
						local33 = local117[local119];
						this.vertexX[local33] -= anInt5793;
						this.vertexY[local33] -= anInt5791;
						this.vertexZ[local33] -= anInt5792;
						this.vertexX[local33] = this.vertexX[local33] * arg2 / 128;
						this.vertexY[local33] = this.vertexY[local33] * arg3 / 128;
						this.vertexZ[local33] = this.vertexZ[local33] * arg4 / 128;
						this.vertexX[local33] += anInt5793;
						this.vertexY[local33] += anInt5791;
						this.vertexZ[local33] += anInt5792;
					}
				}
			}
		} else if (arg0 == 5) {
			if (this.boneTriangles != null && this.triangleAlpha != null) {
				for (local6 = 0; local6 < local2; local6++) {
					local14 = arg1[local6];
					if (local14 < this.boneTriangles.length) {
						local117 = this.boneTriangles[local14];
						for (local119 = 0; local119 < local117.length; local119++) {
							local33 = local117[local119];
							local41 = (this.triangleAlpha[local33] & 0xFF) + arg2 * 8;
							if (local41 < 0) {
								local41 = 0;
							} else if (local41 > 255) {
								local41 = 255;
							}
							this.triangleAlpha[local33] = (byte) local41;
						}
					}
				}
			}
		} else if (arg0 == 7 && this.boneTriangles != null) {
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneTriangles.length) {
					local117 = this.boneTriangles[local14];
					for (local119 = 0; local119 < local117.length; local119++) {
						local33 = local117[local119];
						local41 = this.triangleColors[local33] & 0xFFFF;
						local222 = local41 >> 10 & 0x3F;
						local240 = local41 >> 7 & 0x7;
						@Pc(652) int local652 = local41 & 0x7F;
						@Pc(658) int local658 = local222 + arg2 & 0x3F;
						local240 += arg3;
						if (local240 < 0) {
							local240 = 0;
						} else if (local240 > 7) {
							local240 = 7;
						}
						local652 += arg4;
						if (local652 < 0) {
							local652 = 0;
						} else if (local652 > 127) {
							local652 = 127;
						}
						this.triangleColors[local33] = (short) (local658 << 10 | local240 << 7 | local652);
					}
					this.aBoolean304 = true;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(IIIIIIII)Z")
	private boolean pointWithinTriangle(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int yA, @OriginalArg(3) int yB, @OriginalArg(4) int yC, @OriginalArg(5) int xA, @OriginalArg(6) int xB, @OriginalArg(7) int xC) {
		if (y < yA && y < yB && y < yC) {
			return false;
		} else if (y > yA && y > yB && y > yC) {
			return false;
		} else if (x < xA && x < xB && x < xC) {
			return false;
		} else {
			return x <= xA || x <= xB || x <= xC;
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "c", descriptor = "(ZZZ)Lclient!ak;")
	@Override
	public final Model method4572(@OriginalArg(0) boolean shareAlpha, @OriginalArg(1) boolean shareColors, @OriginalArg(2) boolean arg2) {
		if (!shareAlpha && aByteArray76.length < this.triangleCount) {
			aByteArray76 = new byte[this.triangleCount + 100];
		}
		if (!shareColors && aShortArray93.length < this.triangleCount) {
			anIntArray536 = new int[this.triangleCount + 100];
			anIntArray537 = new int[this.triangleCount + 100];
			anIntArray535 = new int[this.triangleCount + 100];
			aShortArray93 = new short[this.triangleCount + 100];
		}
		return this.copy(shareAlpha, shareColors, aClass8_Sub1_Sub2_1, aByteArray76, aShortArray93, anIntArray536, anIntArray537, anIntArray535);
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(ZZJIILclient!ga;)V")
	private void draw(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) long arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) ParticleSystem arg5) {
		if (arg4 >= 1600) {
			return;
		}
		@Pc(5) int local5 = 0;
		@Pc(7) int local7 = 0;
		@Pc(11) int local11;
		if (!aBoolean307) {
			for (local11 = 0; local11 < 1600; local11++) {
				anIntArray558[local11] = 0;
			}
			for (local11 = 0; local11 < 32; local11++) {
				anIntArray552[local11] = 0;
			}
			anInt5790 = 0;
		}
		@Pc(51) int local51;
		@Pc(56) int local56;
		@Pc(61) int local61;
		@Pc(65) int local65;
		@Pc(69) int local69;
		@Pc(88) int local88;
		@Pc(92) int local92;
		@Pc(96) int local96;
		@Pc(104) int local104;
		for (local11 = 0; local11 < this.triangleCount; local11++) {
			if (this.triangleInfo[local11] != -2) {
				local51 = this.triangleVertexA[local11];
				local56 = this.triangleVertexB[local11];
				local61 = this.triangleVertexC[local11];
				local65 = vertexScreenX[local51];
				local69 = vertexScreenX[local56];
				@Pc(73) int local73 = vertexScreenX[local61];
				if (arg0 && (local65 == -5000 || local69 == -5000 || local73 == -5000)) {
					local88 = projectSceneX[local51];
					local92 = projectSceneX[local56];
					local96 = projectSceneX[local61];
					@Pc(100) int local100 = projectSceneY[local51];
					local104 = projectSceneY[local56];
					@Pc(108) int local108 = projectSceneY[local61];
					@Pc(112) int local112 = projectSceneZ[local51];
					@Pc(116) int local116 = projectSceneZ[local56];
					@Pc(120) int local120 = projectSceneZ[local61];
					local88 -= local92;
					@Pc(128) int local128 = local96 - local92;
					@Pc(132) int local132 = local100 - local104;
					@Pc(136) int local136 = local108 - local104;
					@Pc(140) int local140 = local112 - local116;
					@Pc(144) int local144 = local120 - local116;
					@Pc(152) int local152 = local132 * local144 - local140 * local136;
					@Pc(160) int local160 = local140 * local128 - local88 * local144;
					@Pc(168) int local168 = local88 * local136 - local132 * local128;
					if (local92 * local152 + local104 * local160 + local116 * local168 > 0) {
						projectTriangle[local11] = true;
						if (aBoolean307) {
							anIntArray550[local5] = (vertexDepth[local51] + vertexDepth[local56] + vertexDepth[local61]) / 3;
							depthTriangles[local5++] = local11;
						} else {
							@Pc(224) int local224 = (vertexDepth[local51] + vertexDepth[local56] + vertexDepth[local61]) / 3 + arg3;
							if (anIntArray558[local224] < 64) {
								anIntArrayArray44[local224][anIntArray558[local224]++] = local11;
							} else {
								@Pc(247) int local247 = anIntArray558[local224];
								if (local247 == 64) {
									if (anInt5790 == 512) {
										continue;
									}
									anIntArray558[local224] = local247 = anInt5790++ + 65;
								}
								local247 -= 65;
								anIntArrayArray43[local247][anIntArray552[local247]++] = local11;
							}
						}
					}
				} else {
					if (arg1 && this.pointWithinTriangle(GlModel.anInt3582 + Rasterizer.centerX, RawModel.anInt1053 + Rasterizer.centerY, vertexScreenY[local51], vertexScreenY[local56], vertexScreenY[local61], local65, local69, local73)) {
						Model.aLongArray11[MiniMenu.pickedEntityCount++] = arg2;
						arg1 = false;
					}
					if ((local65 - local69) * (vertexScreenY[local61] - vertexScreenY[local56]) - (vertexScreenY[local51] - vertexScreenY[local56]) * (local73 - local69) > 0) {
						projectTriangle[local11] = false;
						if (local65 >= 0 && local69 >= 0 && local73 >= 0 && local65 <= Rasterizer.width && local69 <= Rasterizer.width && local73 <= Rasterizer.width) {
							testTriangleX[local11] = false;
						} else {
							testTriangleX[local11] = true;
						}
						if (aBoolean307) {
							anIntArray550[local5] = (vertexDepth[local51] + vertexDepth[local56] + vertexDepth[local61]) / 3;
							depthTriangles[local5++] = local11;
						} else {
							local88 = (vertexDepth[local51] + vertexDepth[local56] + vertexDepth[local61]) / 3 + arg3;
							if (anIntArray558[local88] < 64) {
								anIntArrayArray44[local88][anIntArray558[local88]++] = local11;
							} else {
								local92 = anIntArray558[local88];
								if (local92 == 64) {
									if (anInt5790 == 512) {
										continue;
									}
									anIntArray558[local88] = local92 = anInt5790++ + 65;
								}
								local92 -= 65;
								anIntArrayArray43[local92][anIntArray552[local92]++] = local11;
							}
						}
					}
				}
			}
		}
		if (aBoolean307) {
			ArrayUtils.sort(0, local5 - 1, anIntArray550, depthTriangles);
			if (this.trianglePriorities == null) {
				for (local11 = 0; local11 < local5; local11++) {
					this.drawTriangle(depthTriangles[local11]);
				}
				return;
			}
			for (local11 = 0; local11 < 12; local11++) {
				anIntArray541[local11] = 0;
				lowTrianglePriority[local11] = 0;
			}
			for (local11 = 0; local11 < local5; local11++) {
				local51 = depthTriangles[local11];
				local56 = anIntArray550[local11];
				@Pc(523) byte local523 = this.trianglePriorities[local51];
				local65 = anIntArray541[local523]++;
				priorityTriangles[local523][local65] = local51;
				if (local523 < 10) {
					lowTrianglePriority[local523] += local56;
				} else if (local523 == 10) {
					normalTrianglePriority[local65] = local56;
				} else {
					highTrianglePriority[local65] = local56;
				}
			}
		} else {
			@Pc(590) int[] local590;
			if (this.trianglePriorities == null) {
				for (local11 = arg4 - 1; local11 >= 0; local11--) {
					local51 = anIntArray558[local11];
					if (local51 > 0) {
						local56 = local51 > 64 ? 64 : local51;
						local590 = anIntArrayArray44[local11];
						for (local65 = 0; local65 < local56; local65++) {
							local69 = local590[local65];
							if (local69 < 65536) {
								this.drawTriangle(local590[local65]);
							}
						}
					}
					if (local51 > 64) {
						local56 = anIntArray558[local11] - 64 - 1;
						local590 = anIntArrayArray43[local56];
						for (local65 = 0; local65 < anIntArray552[local56]; local65++) {
							local69 = local590[local65];
							if (local69 < 65536) {
								this.drawTriangle(local590[local65]);
							}
						}
					}
				}
				return;
			}
			for (local11 = 0; local11 < 12; local11++) {
				anIntArray541[local11] = 0;
				lowTrianglePriority[local11] = 0;
			}
			for (local11 = arg4 - 1; local11 >= 0; local11--) {
				local51 = anIntArray558[local11];
				@Pc(704) byte local704;
				if (local51 > 0) {
					if (local51 > 64) {
						local56 = 64;
					} else {
						local56 = local51;
					}
					local590 = anIntArrayArray44[local11];
					for (local65 = 0; local65 < local56; local65++) {
						local69 = local590[local65];
						if (local69 < 65536) {
							local704 = this.trianglePriorities[local69];
							local88 = anIntArray541[local704]++;
							priorityTriangles[local704][local88] = local69;
							if (local704 < 10) {
								lowTrianglePriority[local704] += local11;
							} else if (local704 == 10) {
								normalTrianglePriority[local88] = local11;
							} else {
								highTrianglePriority[local88] = local11;
							}
						} else {
							anIntArray561[local7++] = (local69 >> 16) - 1;
						}
					}
				}
				if (local51 > 64) {
					local56 = anIntArray558[local11] - 64 - 1;
					local590 = anIntArrayArray43[local56];
					for (local65 = 0; local65 < anIntArray552[local56]; local65++) {
						local69 = local590[local65];
						if (local69 < 65536) {
							local704 = this.trianglePriorities[local69];
							local88 = anIntArray541[local704]++;
							priorityTriangles[local704][local88] = local69;
							if (local704 < 10) {
								lowTrianglePriority[local704] += local11;
							} else if (local704 == 10) {
								normalTrianglePriority[local88] = local11;
							} else {
								highTrianglePriority[local88] = local11;
							}
						} else {
							anIntArray561[local7++] = (local69 >> 16) - 1;
						}
					}
				}
			}
		}
		local11 = 0;
		if (anIntArray541[1] > 0 || anIntArray541[2] > 0) {
			local11 = (lowTrianglePriority[1] + lowTrianglePriority[2]) / (anIntArray541[1] + anIntArray541[2]);
		}
		local51 = 0;
		if (anIntArray541[3] > 0 || anIntArray541[4] > 0) {
			local51 = (lowTrianglePriority[3] + lowTrianglePriority[4]) / (anIntArray541[3] + anIntArray541[4]);
		}
		local56 = 0;
		if (anIntArray541[6] > 0 || anIntArray541[8] > 0) {
			local56 = (lowTrianglePriority[6] + lowTrianglePriority[8]) / (anIntArray541[6] + anIntArray541[8]);
		}
		local65 = 0;
		local69 = anIntArray541[10];
		@Pc(928) int[] local928 = priorityTriangles[10];
		@Pc(930) int[] local930 = normalTrianglePriority;
		if (local69 == 0) {
			local65 = 0;
			local69 = anIntArray541[11];
			local928 = priorityTriangles[11];
			local930 = highTrianglePriority;
		}
		if (local69 > 0) {
			local61 = local930[0];
		} else {
			local61 = -1000;
		}
		for (local92 = 0; local92 < 10; local92++) {
			while (local92 == 0 && local61 > local11) {
				this.drawTriangle(local928[local65++]);
				if (local65 == local69 && local928 != priorityTriangles[11]) {
					local65 = 0;
					local69 = anIntArray541[11];
					local928 = priorityTriangles[11];
					local930 = highTrianglePriority;
				}
				if (local65 < local69) {
					local61 = local930[local65];
				} else {
					local61 = -1000;
				}
			}
			while (local92 == 3 && local61 > local51) {
				this.drawTriangle(local928[local65++]);
				if (local65 == local69 && local928 != priorityTriangles[11]) {
					local65 = 0;
					local69 = anIntArray541[11];
					local928 = priorityTriangles[11];
					local930 = highTrianglePriority;
				}
				if (local65 < local69) {
					local61 = local930[local65];
				} else {
					local61 = -1000;
				}
			}
			while (local92 == 5 && local61 > local56) {
				this.drawTriangle(local928[local65++]);
				if (local65 == local69 && local928 != priorityTriangles[11]) {
					local65 = 0;
					local69 = anIntArray541[11];
					local928 = priorityTriangles[11];
					local930 = highTrianglePriority;
				}
				if (local65 < local69) {
					local61 = local930[local65];
				} else {
					local61 = -1000;
				}
			}
			local96 = anIntArray541[local92];
			@Pc(1096) int[] local1096 = priorityTriangles[local92];
			for (local104 = 0; local104 < local96; local104++) {
				this.drawTriangle(local1096[local104]);
			}
		}
		while (local61 != -1000) {
			this.drawTriangle(local928[local65++]);
			if (local65 == local69 && local928 != priorityTriangles[11]) {
				local65 = 0;
				local928 = priorityTriangles[11];
				local69 = anIntArray541[11];
				local930 = highTrianglePriority;
			}
			if (local65 < local69) {
				local61 = local930[local65];
			} else {
				local61 = -1000;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "l", descriptor = "()V")
	@Override
	public final void method4578() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.vertexZ[local1];
			this.vertexZ[local1] = this.vertexX[local1];
			this.vertexX[local1] = -local10;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "b", descriptor = "(IIIIIIII)V")
	public final void method4591(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		try {
			if (!this.boundsValid) {
				this.calculateBounds();
			}
			@Pc(6) int local6 = Rasterizer.centerX;
			@Pc(8) int local8 = Rasterizer.centerY;
			@Pc(12) int local12 = MathUtils.sin[0];
			@Pc(16) int local16 = MathUtils.cos[0];
			@Pc(20) int local20 = MathUtils.sin[arg0];
			@Pc(24) int local24 = MathUtils.cos[arg0];
			@Pc(28) int local28 = MathUtils.sin[arg1];
			@Pc(32) int local32 = MathUtils.cos[arg1];
			@Pc(36) int local36 = MathUtils.sin[arg2];
			@Pc(40) int local40 = MathUtils.cos[arg2];
			@Pc(50) int local50 = arg4 * local36 + arg5 * local40 >> 16;
			for (@Pc(52) int local52 = 0; local52 < this.vertexCount; local52++) {
				@Pc(61) int local61 = this.vertexX[local52];
				@Pc(66) int local66 = this.vertexY[local52];
				@Pc(71) int local71 = this.vertexZ[local52];
				@Pc(83) int local83;
				if (arg1 != 0) {
					local83 = local66 * local28 + local61 * local32 >> 16;
					local66 = local66 * local32 - local61 * local28 >> 16;
					local61 = local83;
				}
				if (arg0 != 0) {
					local83 = local71 * local20 + local61 * local24 >> 16;
					local71 = local71 * local24 - local61 * local20 >> 16;
					local61 = local83;
				}
				local61 += arg3;
				local66 += arg4;
				local71 += arg5;
				local83 = local66 * local40 - local71 * local36 >> 16;
				local71 = local66 * local36 + local71 * local40 >> 16;
				vertexDepth[local52] = local71 - local50;
				vertexScreenX[local52] = local6 + (local61 << 9) / arg6;
				vertexScreenY[local52] = local8 + (local83 << 9) / arg6;
				if (this.anInt5789 > 0) {
					projectSceneX[local52] = local61;
					projectSceneY[local52] = local83;
					projectSceneZ[local52] = local71;
				}
			}
			this.draw(false, false, 0L, this.aShort35, this.aShort35 << 1, null);
		} catch (@Pc(234) RuntimeException ignored) {
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "p", descriptor = "()V")
	private void calculateBounds() {
		@Pc(1) int local1 = 32767;
		@Pc(3) int local3 = 32767;
		@Pc(5) int local5 = 32767;
		@Pc(7) int local7 = -32768;
		@Pc(9) int local9 = -32768;
		@Pc(11) int local11 = -32768;
		@Pc(13) int local13 = 0;
		@Pc(15) int local15 = 0;
		for (@Pc(17) int local17 = 0; local17 < this.vertexCount; local17++) {
			@Pc(26) int local26 = this.vertexX[local17];
			@Pc(31) int local31 = this.vertexY[local17];
			@Pc(36) int local36 = this.vertexZ[local17];
			if (local26 < local1) {
				local1 = local26;
			}
			if (local26 > local7) {
				local7 = local26;
			}
			if (local31 < local3) {
				local3 = local31;
			}
			if (local31 > local9) {
				local9 = local31;
			}
			if (local36 < local5) {
				local5 = local36;
			}
			if (local36 > local11) {
				local11 = local36;
			}
			@Pc(74) int local74 = local26 * local26 + local36 * local36;
			if (local74 > local13) {
				local13 = local74;
			}
			local74 += local31 * local31;
			if (local74 > local15) {
				local15 = local74;
			}
		}
		this.minX = (short) local1;
		this.maxX = (short) local7;
		this.minY = (short) local3;
		this.maxY = (short) local9;
		this.minZ = (short) local5;
		this.maxZ = (short) local11;
		this.lengthXZ = (short) (Math.sqrt((double) local13) + 0.99D);
		this.aShort35 = (short) (Math.sqrt((double) local15) + 0.99D);
		this.boundsValid = true;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "h", descriptor = "()I")
	@Override
	public final int getMinX() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.minX;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(I[IIIIZI[I)V")
	@Override
	protected final void method4577(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int[] arg7) {
		@Pc(2) int local2 = arg1.length;
		@Pc(6) int local6;
		@Pc(14) int local14;
		@Pc(33) int local33;
		@Pc(41) int local41;
		@Pc(21) int local21;
		if (arg0 == 0) {
			local6 = 0;
			anInt5793 = 0;
			anInt5791 = 0;
			anInt5792 = 0;
			for (local14 = 0; local14 < local2; local14++) {
				local21 = arg1[local14];
				if (local21 < this.boneVertices.length) {
					@Pc(31) int[] local31 = this.boneVertices[local21];
					for (local33 = 0; local33 < local31.length; local33++) {
						local41 = local31[local33];
						if (this.vertexSources == null || (arg6 & this.vertexSources[local41]) != 0) {
							anInt5793 += this.vertexX[local41];
							anInt5791 += this.vertexY[local41];
							anInt5792 += this.vertexZ[local41];
							local6++;
						}
					}
				}
			}
			if (local6 > 0) {
				anInt5793 = anInt5793 / local6 + arg2;
				anInt5791 = anInt5791 / local6 + arg3;
				anInt5792 = anInt5792 / local6 + arg4;
				aBoolean306 = true;
			} else {
				anInt5793 = arg2;
				anInt5791 = arg3;
				anInt5792 = arg4;
			}
			return;
		}
		@Pc(204) int[] local204;
		@Pc(206) int local206;
		if (arg0 == 1) {
			if (arg7 != null) {
				local6 = arg7[0] * arg2 + arg7[1] * arg3 + arg7[2] * arg4 + 16384 >> 15;
				local14 = arg7[3] * arg2 + arg7[4] * arg3 + arg7[5] * arg4 + 16384 >> 15;
				local21 = arg7[6] * arg2 + arg7[7] * arg3 + arg7[8] * arg4 + 16384 >> 15;
				arg2 = local6;
				arg3 = local14;
				arg4 = local21;
			}
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneVertices.length) {
					local204 = this.boneVertices[local14];
					for (local206 = 0; local206 < local204.length; local206++) {
						local33 = local204[local206];
						if (this.vertexSources == null || (arg6 & this.vertexSources[local33]) != 0) {
							this.vertexX[local33] += arg2;
							this.vertexY[local33] += arg3;
							this.vertexZ[local33] += arg4;
						}
					}
				}
			}
			return;
		}
		@Pc(307) int local307;
		@Pc(329) int local329;
		@Pc(351) int local351;
		@Pc(392) int local392;
		@Pc(398) int local398;
		@Pc(404) int local404;
		@Pc(410) int local410;
		@Pc(418) int local418;
		@Pc(426) int local426;
		@Pc(579) int local579;
		@Pc(604) int local604;
		@Pc(608) int local608;
		@Pc(616) int local616;
		@Pc(621) int local621;
		@Pc(626) int local626;
		@Pc(631) int local631;
		@Pc(753) int[] local753;
		@Pc(755) int local755;
		@Pc(760) int local760;
		@Pc(765) int local765;
		@Pc(767) int local767;
		@Pc(893) int local893;
		if (arg0 == 2) {
			if (arg7 == null) {
				for (local6 = 0; local6 < local2; local6++) {
					local14 = arg1[local6];
					if (local14 < this.boneVertices.length) {
						local204 = this.boneVertices[local14];
						for (local206 = 0; local206 < local204.length; local206++) {
							local33 = local204[local206];
							if (this.vertexSources == null || (arg6 & this.vertexSources[local33]) != 0) {
								this.vertexX[local33] -= anInt5793;
								this.vertexY[local33] -= anInt5791;
								this.vertexZ[local33] -= anInt5792;
								if (arg4 != 0) {
									local41 = MathUtils.sin[arg4];
									local307 = MathUtils.cos[arg4];
									local329 = this.vertexY[local33] * local41 + this.vertexX[local33] * local307 + 32767 >> 16;
									this.vertexY[local33] = this.vertexY[local33] * local307 + 32767 - this.vertexX[local33] * local41 >> 16;
									this.vertexX[local33] = local329;
								}
								if (arg2 != 0) {
									local41 = MathUtils.sin[arg2];
									local307 = MathUtils.cos[arg2];
									local329 = this.vertexY[local33] * local307 + 32767 - this.vertexZ[local33] * local41 >> 16;
									this.vertexZ[local33] = this.vertexY[local33] * local41 + this.vertexZ[local33] * local307 + 32767 >> 16;
									this.vertexY[local33] = local329;
								}
								if (arg3 != 0) {
									local41 = MathUtils.sin[arg3];
									local307 = MathUtils.cos[arg3];
									local329 = this.vertexZ[local33] * local41 + this.vertexX[local33] * local307 + 32767 >> 16;
									this.vertexZ[local33] = this.vertexZ[local33] * local307 + 32767 - this.vertexX[local33] * local41 >> 16;
									this.vertexX[local33] = local329;
								}
								this.vertexX[local33] += anInt5793;
								this.vertexY[local33] += anInt5791;
								this.vertexZ[local33] += anInt5792;
							}
						}
					}
				}
			} else {
				local6 = arg7[9];
				local14 = arg7[10];
				local21 = arg7[11];
				local206 = arg7[12];
				local33 = arg7[13];
				local41 = arg7[14];
				if (aBoolean306) {
					local307 = arg7[0] * anInt5793 + arg7[3] * anInt5791 + arg7[6] * anInt5792 + 16384 >> 15;
					local329 = arg7[1] * anInt5793 + arg7[4] * anInt5791 + arg7[7] * anInt5792 + 16384 >> 15;
					local351 = arg7[2] * anInt5793 + arg7[5] * anInt5791 + arg7[8] * anInt5792 + 16384 >> 15;
					local307 += local206;
					local329 += local33;
					local351 += local41;
					anInt5793 = local307;
					anInt5791 = local329;
					anInt5792 = local351;
					aBoolean306 = false;
				}
				@Pc(374) int[] local374 = new int[9];
				local329 = MathUtils.cos[arg2] >> 1;
				local351 = MathUtils.sin[arg2] >> 1;
				local392 = MathUtils.cos[arg3] >> 1;
				local398 = MathUtils.sin[arg3] >> 1;
				local404 = MathUtils.cos[arg4] >> 1;
				local410 = MathUtils.sin[arg4] >> 1;
				local418 = local351 * local404 + 16384 >> 15;
				local426 = local351 * local410 + 16384 >> 15;
				local374[0] = local392 * local404 + local398 * local426 + 16384 >> 15;
				local374[1] = -local392 * local410 + local398 * local418 + 16384 >> 15;
				local374[2] = local398 * local329 + 16384 >> 15;
				local374[3] = local329 * local410 + 16384 >> 15;
				local374[4] = local329 * local404 + 16384 >> 15;
				local374[5] = -local351;
				local374[6] = -local398 * local404 + local392 * local426 + 16384 >> 15;
				local374[7] = local398 * local410 + local392 * local418 + 16384 >> 15;
				local374[8] = local392 * local329 + 16384 >> 15;
				@Pc(554) int local554 = local374[0] * -anInt5793 + local374[1] * -anInt5791 + local374[2] * -anInt5792 + 16384 >> 15;
				local579 = local374[3] * -anInt5793 + local374[4] * -anInt5791 + local374[5] * -anInt5792 + 16384 >> 15;
				local604 = local374[6] * -anInt5793 + local374[7] * -anInt5791 + local374[8] * -anInt5792 + 16384 >> 15;
				local608 = local554 + anInt5793;
				@Pc(612) int local612 = local579 + anInt5791;
				local616 = local604 + anInt5792;
				@Pc(619) int[] local619 = new int[9];
				for (local621 = 0; local621 < 3; local621++) {
					for (local626 = 0; local626 < 3; local626++) {
						local631 = 0;
						for (@Pc(633) int local633 = 0; local633 < 3; local633++) {
							local631 += local374[local621 * 3 + local633] * arg7[local626 * 3 + local633];
						}
						local619[local621 * 3 + local626] = local631 + 16384 >> 15;
					}
				}
				local621 = local374[0] * local206 + local374[1] * local33 + local374[2] * local41 + 16384 >> 15;
				local626 = local374[3] * local206 + local374[4] * local33 + local374[5] * local41 + 16384 >> 15;
				local631 = local374[6] * local206 + local374[7] * local33 + local374[8] * local41 + 16384 >> 15;
				local621 += local608;
				local626 += local612;
				local631 += local616;
				local753 = new int[9];
				for (local755 = 0; local755 < 3; local755++) {
					for (local760 = 0; local760 < 3; local760++) {
						local765 = 0;
						for (local767 = 0; local767 < 3; local767++) {
							local765 += arg7[local755 * 3 + local767] * local619[local760 + local767 * 3];
						}
						local753[local755 * 3 + local760] = local765 + 16384 >> 15;
					}
				}
				local755 = arg7[0] * local621 + arg7[1] * local626 + arg7[2] * local631 + 16384 >> 15;
				local760 = arg7[3] * local621 + arg7[4] * local626 + arg7[5] * local631 + 16384 >> 15;
				local765 = arg7[6] * local621 + arg7[7] * local626 + arg7[8] * local631 + 16384 >> 15;
				local755 += local6;
				local760 += local14;
				local765 += local21;
				for (local767 = 0; local767 < local2; local767++) {
					local893 = arg1[local767];
					if (local893 < this.boneVertices.length) {
						@Pc(903) int[] local903 = this.boneVertices[local893];
						for (@Pc(905) int local905 = 0; local905 < local903.length; local905++) {
							@Pc(913) int local913 = local903[local905];
							if (this.vertexSources == null || (arg6 & this.vertexSources[local913]) != 0) {
								@Pc(955) int local955 = local753[0] * this.vertexX[local913] + local753[1] * this.vertexY[local913] + local753[2] * this.vertexZ[local913] + 16384 >> 15;
								@Pc(986) int local986 = local753[3] * this.vertexX[local913] + local753[4] * this.vertexY[local913] + local753[5] * this.vertexZ[local913] + 16384 >> 15;
								@Pc(1017) int local1017 = local753[6] * this.vertexX[local913] + local753[7] * this.vertexY[local913] + local753[8] * this.vertexZ[local913] + 16384 >> 15;
								@Pc(1021) int local1021 = local955 + local755;
								@Pc(1025) int local1025 = local986 + local760;
								@Pc(1029) int local1029 = local1017 + local765;
								this.vertexX[local913] = local1021;
								this.vertexY[local913] = local1025;
								this.vertexZ[local913] = local1029;
							}
						}
					}
				}
			}
		} else if (arg0 == 3) {
			if (arg7 == null) {
				for (local6 = 0; local6 < local2; local6++) {
					local14 = arg1[local6];
					if (local14 < this.boneVertices.length) {
						local204 = this.boneVertices[local14];
						for (local206 = 0; local206 < local204.length; local206++) {
							local33 = local204[local206];
							if (this.vertexSources == null || (arg6 & this.vertexSources[local33]) != 0) {
								this.vertexX[local33] -= anInt5793;
								this.vertexY[local33] -= anInt5791;
								this.vertexZ[local33] -= anInt5792;
								this.vertexX[local33] = this.vertexX[local33] * arg2 / 128;
								this.vertexY[local33] = this.vertexY[local33] * arg3 / 128;
								this.vertexZ[local33] = this.vertexZ[local33] * arg4 / 128;
								this.vertexX[local33] += anInt5793;
								this.vertexY[local33] += anInt5791;
								this.vertexZ[local33] += anInt5792;
							}
						}
					}
				}
			} else {
				local6 = arg7[9];
				local14 = arg7[10];
				local21 = arg7[11];
				local206 = arg7[12];
				local33 = arg7[13];
				local41 = arg7[14];
				if (aBoolean306) {
					local307 = arg7[0] * anInt5793 + arg7[3] * anInt5791 + arg7[6] * anInt5792 + 16384 >> 15;
					local329 = arg7[1] * anInt5793 + arg7[4] * anInt5791 + arg7[7] * anInt5792 + 16384 >> 15;
					local351 = arg7[2] * anInt5793 + arg7[5] * anInt5791 + arg7[8] * anInt5792 + 16384 >> 15;
					local307 += local206;
					local329 += local33;
					local351 += local41;
					anInt5793 = local307;
					anInt5791 = local329;
					anInt5792 = local351;
					aBoolean306 = false;
				}
				local307 = arg2 << 15 >> 7;
				local329 = arg3 << 15 >> 7;
				local351 = arg4 << 15 >> 7;
				local392 = local307 * -anInt5793 + 16384 >> 15;
				local398 = local329 * -anInt5791 + 16384 >> 15;
				local404 = local351 * -anInt5792 + 16384 >> 15;
				local410 = local392 + anInt5793;
				local418 = local398 + anInt5791;
				local426 = local404 + anInt5792;
				@Pc(1481) int[] local1481 = new int[] { local307 * arg7[0] + 16384 >> 15, local307 * arg7[3] + 16384 >> 15, local307 * arg7[6] + 16384 >> 15, local329 * arg7[1] + 16384 >> 15, local329 * arg7[4] + 16384 >> 15, local329 * arg7[7] + 16384 >> 15, local351 * arg7[2] + 16384 >> 15, local351 * arg7[5] + 16384 >> 15, local351 * arg7[8] + 16384 >> 15 };
				local579 = local307 * local206 + 16384 >> 15;
				local604 = local329 * local33 + 16384 >> 15;
				local608 = local351 * local41 + 16384 >> 15;
				@Pc(1617) int local1617 = local579 + local410;
				@Pc(1621) int local1621 = local604 + local418;
				@Pc(1625) int local1625 = local608 + local426;
				@Pc(1628) int[] local1628 = new int[9];
				@Pc(1635) int local1635;
				for (local616 = 0; local616 < 3; local616++) {
					for (local1635 = 0; local1635 < 3; local1635++) {
						local621 = 0;
						for (local626 = 0; local626 < 3; local626++) {
							local621 += arg7[local616 * 3 + local626] * local1481[local1635 + local626 * 3];
						}
						local1628[local616 * 3 + local1635] = local621 + 16384 >> 15;
					}
				}
				local616 = arg7[0] * local1617 + arg7[1] * local1621 + arg7[2] * local1625 + 16384 >> 15;
				local1635 = arg7[3] * local1617 + arg7[4] * local1621 + arg7[5] * local1625 + 16384 >> 15;
				local621 = arg7[6] * local1617 + arg7[7] * local1621 + arg7[8] * local1625 + 16384 >> 15;
				local616 += local6;
				local1635 += local14;
				local621 += local21;
				for (local626 = 0; local626 < local2; local626++) {
					local631 = arg1[local626];
					if (local631 < this.boneVertices.length) {
						local753 = this.boneVertices[local631];
						for (local755 = 0; local755 < local753.length; local755++) {
							local760 = local753[local755];
							if (this.vertexSources == null || (arg6 & this.vertexSources[local760]) != 0) {
								local765 = local1628[0] * this.vertexX[local760] + local1628[1] * this.vertexY[local760] + local1628[2] * this.vertexZ[local760] + 16384 >> 15;
								local767 = local1628[3] * this.vertexX[local760] + local1628[4] * this.vertexY[local760] + local1628[5] * this.vertexZ[local760] + 16384 >> 15;
								local893 = local1628[6] * this.vertexX[local760] + local1628[7] * this.vertexY[local760] + local1628[8] * this.vertexZ[local760] + 16384 >> 15;
								@Pc(1896) int local1896 = local765 + local616;
								@Pc(1900) int local1900 = local767 + local1635;
								@Pc(1904) int local1904 = local893 + local621;
								this.vertexX[local760] = local1896;
								this.vertexY[local760] = local1900;
								this.vertexZ[local760] = local1904;
							}
						}
					}
				}
			}
		} else if (arg0 == 5) {
			if (this.boneTriangles != null && this.triangleAlpha != null) {
				for (local6 = 0; local6 < local2; local6++) {
					local14 = arg1[local6];
					if (local14 < this.boneTriangles.length) {
						local204 = this.boneTriangles[local14];
						for (local206 = 0; local206 < local204.length; local206++) {
							local33 = local204[local206];
							if (this.triangleSources == null || (arg6 & this.triangleSources[local33]) != 0) {
								local41 = (this.triangleAlpha[local33] & 0xFF) + arg2 * 8;
								if (local41 < 0) {
									local41 = 0;
								} else if (local41 > 255) {
									local41 = 255;
								}
								this.triangleAlpha[local33] = (byte) local41;
							}
						}
					}
				}
			}
		} else if (arg0 == 7 && this.boneTriangles != null) {
			for (local6 = 0; local6 < local2; local6++) {
				local14 = arg1[local6];
				if (local14 < this.boneTriangles.length) {
					local204 = this.boneTriangles[local14];
					for (local206 = 0; local206 < local204.length; local206++) {
						local33 = local204[local206];
						if (this.triangleSources == null || (arg6 & this.triangleSources[local33]) != 0) {
							local41 = this.triangleColors[local33] & 0xFFFF;
							local307 = local41 >> 10 & 0x3F;
							local329 = local41 >> 7 & 0x7;
							local351 = local41 & 0x7F;
							@Pc(2209) int local2209 = local307 + arg2 & 0x3F;
							local329 += arg3;
							if (local329 < 0) {
								local329 = 0;
							} else if (local329 > 7) {
								local329 = 7;
							}
							local351 += arg4;
							if (local351 < 0) {
								local351 = 0;
							} else if (local351 > 127) {
								local351 = 127;
							}
							this.triangleColors[local33] = (short) (local2209 << 10 | local329 << 7 | local351);
						}
					}
					this.aBoolean304 = true;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "a", descriptor = "(IIII)V")
	@Override
	protected final void method4567(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(3) int local3;
		@Pc(11) int local11;
		if (arg0 == 0) {
			local3 = 0;
			anInt5793 = 0;
			anInt5791 = 0;
			anInt5792 = 0;
			for (local11 = 0; local11 < this.vertexCount; local11++) {
				anInt5793 += this.vertexX[local11];
				anInt5791 += this.vertexY[local11];
				anInt5792 += this.vertexZ[local11];
				local3++;
			}
			if (local3 > 0) {
				anInt5793 = anInt5793 / local3 + arg1;
				anInt5791 = anInt5791 / local3 + arg2;
				anInt5792 = anInt5792 / local3 + arg3;
			} else {
				anInt5793 = arg1;
				anInt5791 = arg2;
				anInt5792 = arg3;
			}
		} else if (arg0 == 1) {
			for (local3 = 0; local3 < this.vertexCount; local3++) {
				this.vertexX[local3] += arg1;
				this.vertexY[local3] += arg2;
				this.vertexZ[local3] += arg3;
			}
		} else {
			@Pc(146) int local146;
			@Pc(164) int local164;
			if (arg0 == 2) {
				for (local3 = 0; local3 < this.vertexCount; local3++) {
					this.vertexX[local3] -= anInt5793;
					this.vertexY[local3] -= anInt5791;
					this.vertexZ[local3] -= anInt5792;
					if (arg3 != 0) {
						local11 = MathUtils.sin[arg3];
						local146 = MathUtils.cos[arg3];
						local164 = this.vertexY[local3] * local11 + this.vertexX[local3] * local146 + 32767 >> 16;
						this.vertexY[local3] = this.vertexY[local3] * local146 + 32767 - this.vertexX[local3] * local11 >> 16;
						this.vertexX[local3] = local164;
					}
					if (arg1 != 0) {
						local11 = MathUtils.sin[arg1];
						local146 = MathUtils.cos[arg1];
						local164 = this.vertexY[local3] * local146 + 32767 - this.vertexZ[local3] * local11 >> 16;
						this.vertexZ[local3] = this.vertexY[local3] * local11 + this.vertexZ[local3] * local146 + 32767 >> 16;
						this.vertexY[local3] = local164;
					}
					if (arg2 != 0) {
						local11 = MathUtils.sin[arg2];
						local146 = MathUtils.cos[arg2];
						local164 = this.vertexZ[local3] * local11 + this.vertexX[local3] * local146 + 32767 >> 16;
						this.vertexZ[local3] = this.vertexZ[local3] * local146 + 32767 - this.vertexX[local3] * local11 >> 16;
						this.vertexX[local3] = local164;
					}
					this.vertexX[local3] += anInt5793;
					this.vertexY[local3] += anInt5791;
					this.vertexZ[local3] += anInt5792;
				}
			} else if (arg0 == 3) {
				for (local3 = 0; local3 < this.vertexCount; local3++) {
					this.vertexX[local3] -= anInt5793;
					this.vertexY[local3] -= anInt5791;
					this.vertexZ[local3] -= anInt5792;
					this.vertexX[local3] = this.vertexX[local3] * arg1 / 128;
					this.vertexY[local3] = this.vertexY[local3] * arg2 / 128;
					this.vertexZ[local3] = this.vertexZ[local3] * arg3 / 128;
					this.vertexX[local3] += anInt5793;
					this.vertexY[local3] += anInt5791;
					this.vertexZ[local3] += anInt5792;
				}
			} else if (arg0 == 5) {
				for (local3 = 0; local3 < this.triangleCount; local3++) {
					local11 = (this.triangleAlpha[local3] & 0xFF) + arg1 * 8;
					if (local11 < 0) {
						local11 = 0;
					} else if (local11 > 255) {
						local11 = 255;
					}
					this.triangleAlpha[local3] = (byte) local11;
				}
			} else if (arg0 == 7) {
				for (local3 = 0; local3 < this.triangleCount; local3++) {
					local11 = this.triangleColors[local3] & 0xFFFF;
					local146 = local11 >> 10 & 0x3F;
					local164 = local11 >> 7 & 0x7;
					@Pc(492) int local492 = local11 & 0x7F;
					@Pc(498) int local498 = local146 + arg1 & 0x3F;
					local164 += arg2;
					if (local164 < 0) {
						local164 = 0;
					} else if (local164 > 7) {
						local164 = 7;
					}
					local492 += arg3;
					if (local492 < 0) {
						local492 = 0;
					} else if (local492 > 127) {
						local492 = 127;
					}
					this.triangleColors[local3] = (short) (local498 << 10 | local164 << 7 | local492);
				}
				this.aBoolean304 = true;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!w", name = "c", descriptor = "(III)V")
	@Override
	public final void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexX[local1] += arg0;
			this.vertexY[local1] += arg1;
			this.vertexZ[local1] += arg2;
		}
		this.boundsValid = false;
	}

	@OriginalMember(owner = "runetek4.client!w", name = "b", descriptor = "(ZZZ)Lclient!ak;")
	@Override
	public final Model method4568(@OriginalArg(0) boolean shareAlpha, @OriginalArg(1) boolean shareColors, @OriginalArg(2) boolean arg2) {
		if (!shareAlpha && aByteArray78.length < this.triangleCount) {
			aByteArray78 = new byte[this.triangleCount + 100];
		}
		if (!shareColors && aShortArray95.length < this.triangleCount) {
			anIntArray554 = new int[this.triangleCount + 100];
			anIntArray559 = new int[this.triangleCount + 100];
			anIntArray546 = new int[this.triangleCount + 100];
			aShortArray95 = new short[this.triangleCount + 100];
		}
		return this.copy(shareAlpha, shareColors, aClass8_Sub1_Sub2_3, aByteArray78, aShortArray95, anIntArray554, anIntArray559, anIntArray546);
	}
}
