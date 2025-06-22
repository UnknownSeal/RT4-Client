package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gb")
public final class RawModel extends Entity {

	@OriginalMember(owner = "client!gb", name = "V", descriptor = "[I")
	public static final int[] SIN = MathUtils.sin;
	@OriginalMember(owner = "client!gb", name = "N", descriptor = "[I")
	public static final int[] COS = MathUtils.cos;
	@OriginalMember(owner = "client!gb", name = "L", descriptor = "[I")
	public static final int[] anIntArray194 = new int[10000];
	@OriginalMember(owner = "client!gb", name = "Z", descriptor = "[I")
	public static final int[] anIntArray199 = new int[10000];
	@OriginalMember(owner = "client!d", name = "db", descriptor = "Z")
	public static boolean allowInput = false;
	@OriginalMember(owner = "client!ck", name = "K", descriptor = "I")
	public static int anInt1053 = 0;
	@OriginalMember(owner = "client!gb", name = "S", descriptor = "I")
	public static int anInt2138 = 0;
	@OriginalMember(owner = "client!gb", name = "s", descriptor = "[B")
	public byte[] triangleAlpha;

	@OriginalMember(owner = "client!gb", name = "t", descriptor = "S")
	private short maxY;

	@OriginalMember(owner = "client!gb", name = "u", descriptor = "[B")
	public byte[] textureRotationY;

	@OriginalMember(owner = "client!gb", name = "v", descriptor = "[S")
	public short[] textureFacesM;

	@OriginalMember(owner = "client!gb", name = "w", descriptor = "[I")
	public int[] triangleBones;

	@OriginalMember(owner = "client!gb", name = "z", descriptor = "S")
	private short minX;

	@OriginalMember(owner = "client!gb", name = "A", descriptor = "[S")
	public short[] texturesScaleZ;

	@OriginalMember(owner = "client!gb", name = "B", descriptor = "[B")
	public byte[] textureTriangleTranslationU;

	@OriginalMember(owner = "client!gb", name = "D", descriptor = "S")
	private short minY;

	@OriginalMember(owner = "client!gb", name = "E", descriptor = "[S")
	public short[] triangleSources;

	@OriginalMember(owner = "client!gb", name = "F", descriptor = "[[I")
	public int[][] boneTriangles;

	@OriginalMember(owner = "client!gb", name = "G", descriptor = "[B")
	public byte[] textureTypes;

	@OriginalMember(owner = "client!gb", name = "H", descriptor = "[S")
	public short[] unmodifiedTriangleTexture;

	@OriginalMember(owner = "client!gb", name = "I", descriptor = "[B")
	public byte[] triangleInfo;

	@OriginalMember(owner = "client!gb", name = "J", descriptor = "[I")
	public int[] vertexBones;

	@OriginalMember(owner = "client!gb", name = "M", descriptor = "I")
	public int texturedFaceCount;

	@OriginalMember(owner = "client!gb", name = "O", descriptor = "[I")
	public int[] triangleVertexC;

	@OriginalMember(owner = "client!gb", name = "P", descriptor = "[I")
	public int[] triangleVertexA;

	@OriginalMember(owner = "client!gb", name = "Q", descriptor = "[B")
	public byte[] triangleTextureIndex;

	@OriginalMember(owner = "client!gb", name = "R", descriptor = "[B")
	public byte[] aByteArray32;

	@OriginalMember(owner = "client!gb", name = "T", descriptor = "[B")
	public byte[] textureTriangleTranslationV;

	@OriginalMember(owner = "client!gb", name = "U", descriptor = "[S")
	public short[] unmodifiedTriangleColour;

	@OriginalMember(owner = "client!gb", name = "W", descriptor = "[Lclient!hd;")
	public VertexNormal[] vertexNormals;

	@OriginalMember(owner = "client!gb", name = "X", descriptor = "S")
	private short maxX;

	@OriginalMember(owner = "client!gb", name = "Y", descriptor = "S")
	private short maxZ;

	@OriginalMember(owner = "client!gb", name = "ab", descriptor = "S")
	public short aShort18;

	@OriginalMember(owner = "client!gb", name = "bb", descriptor = "[B")
	public byte[] aByteArray34;

	@OriginalMember(owner = "client!gb", name = "cb", descriptor = "[I")
	public int[] triangleVertexB;

	@OriginalMember(owner = "client!gb", name = "db", descriptor = "S")
	public short aShort19;

	@OriginalMember(owner = "client!gb", name = "eb", descriptor = "S")
	private short minZ;

	@OriginalMember(owner = "client!gb", name = "fb", descriptor = "[I")
	public int[] vertexY;

	@OriginalMember(owner = "client!gb", name = "gb", descriptor = "[S")
	public short[] texturesScaleY;

	@OriginalMember(owner = "client!gb", name = "hb", descriptor = "[Lclient!hd;")
	public VertexNormal[] aVertexNormalArray2;

	@OriginalMember(owner = "client!gb", name = "ib", descriptor = "[S")
	public short[] textureFacesP;

	@OriginalMember(owner = "client!gb", name = "jb", descriptor = "[I")
	public int[] vertexX;

	@OriginalMember(owner = "client!gb", name = "kb", descriptor = "[Lclient!qj;")
	public TriangleNormal[] triangleNormals;

	@OriginalMember(owner = "client!gb", name = "mb", descriptor = "[S")
	public short[] texturesScaleX;

	@OriginalMember(owner = "client!gb", name = "nb", descriptor = "[B")
	public byte[] trianglePriorities;

	@OriginalMember(owner = "client!gb", name = "ob", descriptor = "[[I")
	public int[][] boneVertices;

	@OriginalMember(owner = "client!gb", name = "pb", descriptor = "[S")
	public short[] textureFacesN;

	@OriginalMember(owner = "client!gb", name = "qb", descriptor = "[I")
	public int[] vertexZ;

	@OriginalMember(owner = "client!gb", name = "rb", descriptor = "[S")
	public short[] vertexSources;

	@OriginalMember(owner = "client!gb", name = "C", descriptor = "Z")
	private boolean boundsValid = false;

	@OriginalMember(owner = "client!gb", name = "y", descriptor = "I")
	public int triangleCount = 0;

	@OriginalMember(owner = "client!gb", name = "x", descriptor = "B")
	public byte modelPriority = 0;

	@OriginalMember(owner = "client!gb", name = "lb", descriptor = "I")
	public int vertexCount = 0;

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "()V")
	private RawModel() {
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "([B)V")
	public RawModel(@OriginalArg(0) byte[] src) {
		if (src[src.length - 1] == -1 && src[src.length - 2] == -1) {
			this.decodeNew(src);
		} else {
			this.decodeOld(src);
		}
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "(III)V")
	public RawModel(@OriginalArg(0) int vertexCount, @OriginalArg(1) int triangleCount, @OriginalArg(2) int arg2) {
		this.vertexX = new int[vertexCount];
		this.vertexY = new int[vertexCount];
		this.vertexZ = new int[vertexCount];
		this.vertexBones = new int[vertexCount];
		this.triangleVertexA = new int[triangleCount];
		this.triangleVertexB = new int[triangleCount];
		this.triangleVertexC = new int[triangleCount];
		this.triangleInfo = new byte[triangleCount];
		this.trianglePriorities = new byte[triangleCount];
		this.triangleAlpha = new byte[triangleCount];
		this.unmodifiedTriangleColour = new short[triangleCount];
		this.unmodifiedTriangleTexture = new short[triangleCount];
		this.triangleTextureIndex = new byte[triangleCount];
		this.triangleBones = new int[triangleCount];
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "([Lclient!gb;I)V")
	public RawModel(@OriginalArg(0) RawModel[] count, @OriginalArg(1) int arg1) {
		@Pc(15) boolean keepInfo = false;
		@Pc(17) boolean copyPriority = false;
		@Pc(19) boolean keepAlpha = false;
		@Pc(21) boolean keepBones = false;
		@Pc(23) boolean keepTextures = false;
		@Pc(25) boolean keepTextureIndex = false;
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedFaceCount = 0;
		this.modelPriority = -1;
		@Pc(43) int i;
		for (i = 0; i < arg1; i++) {
			@Pc(50) RawModel model = count[i];
			if (model != null) {
				this.vertexCount += model.vertexCount;
				this.triangleCount += model.triangleCount;
				this.texturedFaceCount += model.texturedFaceCount;
				if (model.trianglePriorities == null) {
					if (this.modelPriority == -1) {
						this.modelPriority = model.modelPriority;
					}
					if (this.modelPriority != model.modelPriority) {
						copyPriority = true;
					}
				} else {
					copyPriority = true;
				}
				keepInfo |= model.triangleInfo != null;
				keepAlpha |= model.triangleAlpha != null;
				keepBones |= model.triangleBones != null;
				keepTextures |= model.unmodifiedTriangleTexture != null;
				keepTextureIndex |= model.triangleTextureIndex != null;
			}
		}
		this.vertexX = new int[this.vertexCount];
		this.vertexY = new int[this.vertexCount];
		this.vertexZ = new int[this.vertexCount];
		this.vertexBones = new int[this.vertexCount];
		this.vertexSources = new short[this.vertexCount];
		this.triangleVertexA = new int[this.triangleCount];
		this.triangleVertexB = new int[this.triangleCount];
		this.triangleVertexC = new int[this.triangleCount];
		if (keepInfo) {
			this.triangleInfo = new byte[this.triangleCount];
		}
		if (copyPriority) {
			this.trianglePriorities = new byte[this.triangleCount];
		}
		if (keepAlpha) {
			this.triangleAlpha = new byte[this.triangleCount];
		}
		if (keepBones) {
			this.triangleBones = new int[this.triangleCount];
		}
		if (keepTextures) {
			this.unmodifiedTriangleTexture = new short[this.triangleCount];
		}
		if (keepTextureIndex) {
			this.triangleTextureIndex = new byte[this.triangleCount];
		}
		this.unmodifiedTriangleColour = new short[this.triangleCount];
		this.triangleSources = new short[this.triangleCount];
		if (this.texturedFaceCount > 0) {
			this.textureTypes = new byte[this.texturedFaceCount];
			this.textureFacesP = new short[this.texturedFaceCount];
			this.textureFacesM = new short[this.texturedFaceCount];
			this.textureFacesN = new short[this.texturedFaceCount];
			this.texturesScaleX = new short[this.texturedFaceCount];
			this.texturesScaleY = new short[this.texturedFaceCount];
			this.texturesScaleZ = new short[this.texturedFaceCount];
			this.textureRotationY = new byte[this.texturedFaceCount];
			this.aByteArray32 = new byte[this.texturedFaceCount];
			this.aByteArray34 = new byte[this.texturedFaceCount];
			this.textureTriangleTranslationU = new byte[this.texturedFaceCount];
			this.textureTriangleTranslationV = new byte[this.texturedFaceCount];
		}
		this.vertexCount = 0;
		this.triangleCount = 0;
		this.texturedFaceCount = 0;
		for (i = 0; i < arg1; i++) {
			@Pc(323) short local323 = (short) (0x1 << i);
			@Pc(327) RawModel model = count[i];
			if (model != null) {
				@Pc(331) int f;
				for (f = 0; f < model.triangleCount; f++) {
					if (keepInfo && model.triangleInfo != null) {
						this.triangleInfo[this.triangleCount] = model.triangleInfo[f];
					}
					if (copyPriority) {
						if (model.trianglePriorities == null) {
							this.trianglePriorities[this.triangleCount] = model.modelPriority;
						} else {
							this.trianglePriorities[this.triangleCount] = model.trianglePriorities[f];
						}
					}
					if (keepAlpha && model.triangleAlpha != null) {
						this.triangleAlpha[this.triangleCount] = model.triangleAlpha[f];
					}
					if (keepBones && model.triangleBones != null) {
						this.triangleBones[this.triangleCount] = model.triangleBones[f];
					}
					if (keepTextures) {
						if (model.unmodifiedTriangleTexture == null) {
							this.unmodifiedTriangleTexture[this.triangleCount] = -1;
						} else {
							this.unmodifiedTriangleTexture[this.triangleCount] = model.unmodifiedTriangleTexture[f];
						}
					}
					if (keepTextureIndex) {
						if (model.triangleTextureIndex == null || model.triangleTextureIndex[f] == -1) {
							this.triangleTextureIndex[this.triangleCount] = -1;
						} else {
							this.triangleTextureIndex[this.triangleCount] = (byte) (model.triangleTextureIndex[f] + this.texturedFaceCount);
						}
					}
					this.unmodifiedTriangleColour[this.triangleCount] = model.unmodifiedTriangleColour[f];
					this.triangleSources[this.triangleCount] = local323;
					this.triangleVertexA[this.triangleCount] = this.addVertex(model, model.triangleVertexA[f], local323);
					this.triangleVertexB[this.triangleCount] = this.addVertex(model, model.triangleVertexB[f], local323);
					this.triangleVertexC[this.triangleCount] = this.addVertex(model, model.triangleVertexC[f], local323);
					this.triangleCount++;
				}
				for (f = 0; f < model.texturedFaceCount; f++) {
					@Pc(530) byte local530 = this.textureTypes[this.texturedFaceCount] = model.textureTypes[f];
					if (local530 == 0) {
						this.textureFacesP[this.texturedFaceCount] = (short) this.addVertex(model, model.textureFacesP[f], local323);
						this.textureFacesM[this.texturedFaceCount] = (short) this.addVertex(model, model.textureFacesM[f], local323);
						this.textureFacesN[this.texturedFaceCount] = (short) this.addVertex(model, model.textureFacesN[f], local323);
					}
					if (local530 >= 1 && local530 <= 3) {
						this.textureFacesP[this.texturedFaceCount] = model.textureFacesP[f];
						this.textureFacesM[this.texturedFaceCount] = model.textureFacesM[f];
						this.textureFacesN[this.texturedFaceCount] = model.textureFacesN[f];
						this.texturesScaleX[this.texturedFaceCount] = model.texturesScaleX[f];
						this.texturesScaleY[this.texturedFaceCount] = model.texturesScaleY[f];
						this.texturesScaleZ[this.texturedFaceCount] = model.texturesScaleZ[f];
						this.textureRotationY[this.texturedFaceCount] = model.textureRotationY[f];
						this.aByteArray32[this.texturedFaceCount] = model.aByteArray32[f];
						this.aByteArray34[this.texturedFaceCount] = model.aByteArray34[f];
					}
					if (local530 == 2) {
						this.textureTriangleTranslationU[this.texturedFaceCount] = model.textureTriangleTranslationU[f];
						this.textureTriangleTranslationV[this.texturedFaceCount] = model.textureTriangleTranslationV[f];
					}
					this.texturedFaceCount++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "<init>", descriptor = "(Lclient!gb;ZZZZ)V")
	public RawModel(@OriginalArg(0) RawModel other, @OriginalArg(1) boolean reuseVertices, @OriginalArg(2) boolean reuseColors, @OriginalArg(3) boolean reuseTextures, @OriginalArg(4) boolean arg4) {
		this.vertexCount = other.vertexCount;
		this.triangleCount = other.triangleCount;
		this.texturedFaceCount = other.texturedFaceCount;
		@Pc(57) int local57;
		if (reuseVertices) {
			this.vertexX = other.vertexX;
			this.vertexY = other.vertexY;
			this.vertexZ = other.vertexZ;
		} else {

			// otherwise, copy them

			this.vertexX = new int[this.vertexCount];
			this.vertexY = new int[this.vertexCount];
			this.vertexZ = new int[this.vertexCount];
			for (local57 = 0; local57 < this.vertexCount; local57++) {
				this.vertexX[local57] = other.vertexX[local57];
				this.vertexY[local57] = other.vertexY[local57];
				this.vertexZ[local57] = other.vertexZ[local57];
			}
		}
		if (reuseColors) {
			this.unmodifiedTriangleColour = other.unmodifiedTriangleColour;
		} else {
			this.unmodifiedTriangleColour = new short[this.triangleCount];
			for (local57 = 0; local57 < this.triangleCount; local57++) {
				this.unmodifiedTriangleColour[local57] = other.unmodifiedTriangleColour[local57];
			}
		}
		if (reuseTextures || other.unmodifiedTriangleTexture == null) {
			this.unmodifiedTriangleTexture = other.unmodifiedTriangleTexture;
		} else {
			this.unmodifiedTriangleTexture = new short[this.triangleCount];
			for (local57 = 0; local57 < this.triangleCount; local57++) {
				this.unmodifiedTriangleTexture[local57] = other.unmodifiedTriangleTexture[local57];
			}
		}
		this.triangleAlpha = other.triangleAlpha;
		this.triangleVertexA = other.triangleVertexA;
		this.triangleVertexB = other.triangleVertexB;
		this.triangleVertexC = other.triangleVertexC;
		this.triangleInfo = other.triangleInfo;
		this.trianglePriorities = other.trianglePriorities;
		this.triangleTextureIndex = other.triangleTextureIndex;
		this.modelPriority = other.modelPriority;
		this.textureTypes = other.textureTypes;
		this.textureFacesP = other.textureFacesP;
		this.textureFacesM = other.textureFacesM;
		this.textureFacesN = other.textureFacesN;
		this.texturesScaleX = other.texturesScaleX;
		this.texturesScaleY = other.texturesScaleY;
		this.texturesScaleZ = other.texturesScaleZ;
		this.textureRotationY = other.textureRotationY;
		this.aByteArray32 = other.aByteArray32;
		this.aByteArray34 = other.aByteArray34;
		this.textureTriangleTranslationU = other.textureTriangleTranslationU;
		this.textureTriangleTranslationV = other.textureTriangleTranslationV;
		this.vertexBones = other.vertexBones;
		this.triangleBones = other.triangleBones;
		this.boneVertices = other.boneVertices;
		this.boneTriangles = other.boneTriangles;
		this.vertexNormals = other.vertexNormals;
		this.triangleNormals = other.triangleNormals;
		this.aVertexNormalArray2 = other.aVertexNormalArray2;
		this.aShort19 = other.aShort19;
		this.aShort18 = other.aShort18;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!ve;II)Lclient!gb;")
	public static RawModel create(@OriginalArg(0) Js5 archive, @OriginalArg(1) int id) {
		@Pc(5) byte[] data = archive.getfile(id, 0);
		return data == null ? null : new RawModel(data);
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([[III)I")
	public static int method1680(@OriginalArg(0) int[][] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(3) int local3 = arg1 >> 7;
		@Pc(7) int local7 = arg2 >> 7;
		if (local3 < 0 || local7 < 0 || local3 >= arg0.length || local7 >= arg0[0].length) {
			return 0;
		}
		@Pc(27) int local27 = arg1 & 0x7F;
		@Pc(31) int local31 = arg2 & 0x7F;
		@Pc(53) int local53 = arg0[local3][local7] * (128 - local27) + arg0[local3 + 1][local7] * local27 >> 7;
		@Pc(79) int local79 = arg0[local3][local7 + 1] * (128 - local27) + arg0[local3 + 1][local7 + 1] * local27 >> 7;
		return local53 * (128 - local31) + local79 * local31 >> 7;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "()V")
	public void negateXz() {
		for (@Pc(1) int v = 0; v < this.vertexCount; v++) {
			this.vertexX[v] = -this.vertexX[v];
			this.vertexZ[v] = -this.vertexZ[v];
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "e", descriptor = "()V")
	public void swapXz() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.vertexX[local1];
			this.vertexX[local1] = this.vertexZ[local1];
			this.vertexZ[local1] = -local10;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(I)V")
	private void rotate(@OriginalArg(0) int arg0) {
		@Pc(3) int sin = SIN[arg0];
		@Pc(7) int cos = COS[arg0];
		for (@Pc(9) int v = 0; v < this.vertexCount; v++) {
			@Pc(29) int temp = this.vertexY[v] * sin + this.vertexX[v] * cos >> 16;
			this.vertexY[v] = this.vertexY[v] * cos - this.vertexX[v] * sin >> 16;
			this.vertexX[v] = temp;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "f", descriptor = "()V")
	private void calculateBounds() {
		if (this.boundsValid) {
			return;
		}
		this.boundsValid = true;
		@Pc(8) int minX = 32767;
		@Pc(10) int minY = 32767;
		@Pc(12) int minZ = 32767;
		@Pc(14) int maxX = -32768;
		@Pc(16) int maxY = -32768;
		@Pc(18) int maxZ = -32768;
		for (@Pc(20) int v = 0; v < this.vertexCount; v++) {
			@Pc(29) int x = this.vertexX[v];
			@Pc(34) int y = this.vertexY[v];
			@Pc(39) int z = this.vertexZ[v];
			if (x < minX) {
				minX = x;
			}
			if (x > maxX) {
				maxX = x;
			}
			if (y < minY) {
				minY = y;
			}
			if (y > maxY) {
				maxY = y;
			}
			if (z < minZ) {
				minZ = z;
			}
			if (z > maxZ) {
				maxZ = z;
			}
		}
		this.minX = (short) minX;
		this.maxX = (short) maxX;
		this.minY = (short) minY;
		this.maxY = (short) maxY;
		this.minZ = (short) minZ;
		this.maxZ = (short) maxZ;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(III)V")
	public void resize(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int v = 0; v < this.vertexCount; v++) {
			this.vertexX[v] = this.vertexX[v] * arg0 / 128;
			this.vertexY[v] = this.vertexY[v] * arg1 / 128;
			this.vertexZ[v] = this.vertexZ[v] * arg2 / 128;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(III)Lclient!th;")
	@Override
	public Entity createModel() {
		return this.createModel(this.aShort19, this.aShort18, -50, -10, -50);
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!gb;IS)I")
	private int addVertex(@OriginalArg(0) RawModel m, @OriginalArg(1) int arg1, @OriginalArg(2) short arg2) {
		@Pc(4) int x = m.vertexX[arg1];
		@Pc(9) int y = m.vertexY[arg1];
		@Pc(14) int z = m.vertexZ[arg1];
		for (@Pc(16) int i = 0; i < this.vertexCount; i++) {
			if (x == this.vertexX[i] && y == this.vertexY[i] && z == this.vertexZ[i]) {
				this.vertexSources[i] |= arg2;
				return i;
			}
		}
		this.vertexX[this.vertexCount] = x;
		this.vertexY[this.vertexCount] = y;
		this.vertexZ[this.vertexCount] = z;
		this.vertexSources[this.vertexCount] = arg2;
		if (m.vertexBones != null) {
			this.vertexBones[this.vertexCount] = m.vertexBones[arg1];
		}
		return this.vertexCount++;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([[IIIIII)V")
	private void method1667(@OriginalArg(0) int[][] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(10) int local10 = -arg4 / 2;
		@Pc(15) int local15 = -arg5 / 2;
		@Pc(24) int local24 = method1680(arg0, arg1 + local10, arg3 + local15);
		@Pc(28) int local28 = arg4 / 2;
		@Pc(33) int local33 = -arg5 / 2;
		@Pc(42) int local42 = method1680(arg0, arg1 + local28, arg3 + local33);
		@Pc(47) int local47 = -arg4 / 2;
		@Pc(51) int local51 = arg5 / 2;
		@Pc(60) int local60 = method1680(arg0, arg1 + local47, arg3 + local51);
		@Pc(64) int local64 = arg4 / 2;
		@Pc(68) int local68 = arg5 / 2;
		@Pc(77) int local77 = method1680(arg0, arg1 + local64, arg3 + local68);
		@Pc(84) int local84 = local24 < local42 ? local24 : local42;
		@Pc(91) int local91 = local60 < local77 ? local60 : local77;
		@Pc(98) int local98 = local42 < local77 ? local42 : local77;
		@Pc(105) int local105 = local24 < local60 ? local24 : local60;
		if (arg5 != 0) {
			@Pc(120) int local120 = (int) (Math.atan2(local84 - local91, arg5) * 325.95D) & 0x7FF;
			if (local120 != 0) {
				this.method1677(local120);
			}
		}
		if (arg4 != 0) {
			@Pc(140) int local140 = (int) (Math.atan2(local105 - local98, arg4) * 325.95D) & 0x7FF;
			if (local140 != 0) {
				this.rotate(local140);
			}
		}
		@Pc(149) int local149 = local24 + local77;
		if (local42 + local60 < local149) {
			local149 = local42 + local60;
		}
		local149 = (local149 >> 1) - arg2;
		if (local149 != 0) {
			this.translate(0, local149, 0);
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
	}

	@OriginalMember(owner = "client!gb", name = "g", descriptor = "()V")
	public void calculateNormals() {
		if (this.vertexNormals != null) {
			return;
		}
		this.vertexNormals = new VertexNormal[this.vertexCount];
		@Pc(10) int local10;
		for (local10 = 0; local10 < this.vertexCount; local10++) {
			this.vertexNormals[local10] = new VertexNormal();
		}
		for (local10 = 0; local10 < this.triangleCount; local10++) {
			@Pc(34) int local34 = this.triangleVertexA[local10];
			@Pc(39) int local39 = this.triangleVertexB[local10];
			@Pc(44) int local44 = this.triangleVertexC[local10];
			@Pc(54) int local54 = this.vertexX[local39] - this.vertexX[local34];
			@Pc(64) int local64 = this.vertexY[local39] - this.vertexY[local34];
			@Pc(74) int local74 = this.vertexZ[local39] - this.vertexZ[local34];
			@Pc(84) int local84 = this.vertexX[local44] - this.vertexX[local34];
			@Pc(94) int local94 = this.vertexY[local44] - this.vertexY[local34];
			@Pc(104) int local104 = this.vertexZ[local44] - this.vertexZ[local34];
			@Pc(112) int local112 = local64 * local104 - local94 * local74;
			@Pc(120) int local120 = local74 * local84 - local104 * local54;
			@Pc(128) int local128;
			for (local128 = local54 * local94 - local84 * local64; local112 > 8192 || local120 > 8192 || local128 > 8192 || local112 < -8192 || local120 < -8192 || local128 < -8192; local128 >>= 0x1) {
				local112 >>= 0x1;
				local120 >>= 0x1;
			}
			@Pc(174) int local174 = (int) Math.sqrt(local112 * local112 + local120 * local120 + local128 * local128);
			if (local174 <= 0) {
				local174 = 1;
			}
			local112 = local112 * 256 / local174;
			local120 = local120 * 256 / local174;
			local128 = local128 * 256 / local174;
			@Pc(201) byte local201;
			if (this.triangleInfo == null) {
				local201 = 0;
			} else {
				local201 = this.triangleInfo[local10];
			}
			if (local201 == 0) {
				@Pc(214) VertexNormal local214 = this.vertexNormals[local34];
				local214.x += local112;
				local214.y += local120;
				local214.z += local128;
				local214.magnitude++;
				@Pc(243) VertexNormal local243 = this.vertexNormals[local39];
				local243.x += local112;
				local243.y += local120;
				local243.z += local128;
				local243.magnitude++;
				@Pc(272) VertexNormal local272 = this.vertexNormals[local44];
				local272.x += local112;
				local272.y += local120;
				local272.z += local128;
				local272.magnitude++;
			} else if (local201 == 1) {
				if (this.triangleNormals == null) {
					this.triangleNormals = new TriangleNormal[this.triangleCount];
				}
				@Pc(317) TriangleNormal local317 = this.triangleNormals[local10] = new TriangleNormal();
				local317.x = local112;
				local317.y = local120;
				local317.z = local128;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(SS)V")
	public void retexture(@OriginalArg(0) short arg0, @OriginalArg(1) short arg1) {
		if (this.unmodifiedTriangleTexture == null) {
			return;
		}
		for (@Pc(5) int local5 = 0; local5 < this.triangleCount; local5++) {
			if (this.unmodifiedTriangleTexture[local5] == arg0) {
				this.unmodifiedTriangleTexture[local5] = arg1;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(II[[I[[IIIIZZ)Lclient!gb;")
	public RawModel method1670(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[][] arg2, @OriginalArg(3) int[][] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		this.calculateBounds();
		@Pc(6) int local6 = arg4 + this.minX;
		@Pc(11) int local11 = arg4 + this.maxX;
		@Pc(16) int local16 = arg6 + this.minZ;
		@Pc(21) int local21 = arg6 + this.maxZ;
		if ((arg0 == 1 || arg0 == 2 || arg0 == 3 || arg0 == 5) && (local6 < 0 || local11 + 128 >> 7 >= arg2.length || local16 < 0 || local21 + 128 >> 7 >= arg2[0].length)) {
			return this;
		}
		if (arg0 == 4 || arg0 == 5) {
			if (arg3 == null) {
				return this;
			}
			if (local6 < 0 || local11 + 128 >> 7 >= arg3.length || local16 < 0 || local21 + 128 >> 7 >= arg3[0].length) {
				return this;
			}
		} else {
			local6 >>= 0x7;
			local11 = local11 + 127 >> 7;
			local16 >>= 0x7;
			local21 = local21 + 127 >> 7;
			if (arg2[local6][local16] == arg5 && arg2[local11][local16] == arg5 && arg2[local6][local21] == arg5 && arg2[local11][local21] == arg5) {
				return this;
			}
		}
		@Pc(147) RawModel local147 = new RawModel();
		local147.vertexCount = this.vertexCount;
		local147.triangleCount = this.triangleCount;
		local147.texturedFaceCount = this.texturedFaceCount;
		local147.triangleVertexA = this.triangleVertexA;
		local147.triangleVertexB = this.triangleVertexB;
		local147.triangleVertexC = this.triangleVertexC;
		local147.triangleInfo = this.triangleInfo;
		local147.trianglePriorities = this.trianglePriorities;
		local147.triangleAlpha = this.triangleAlpha;
		local147.triangleTextureIndex = this.triangleTextureIndex;
		local147.unmodifiedTriangleColour = this.unmodifiedTriangleColour;
		local147.unmodifiedTriangleTexture = this.unmodifiedTriangleTexture;
		local147.modelPriority = this.modelPriority;
		local147.textureTypes = this.textureTypes;
		local147.textureFacesP = this.textureFacesP;
		local147.textureFacesM = this.textureFacesM;
		local147.textureFacesN = this.textureFacesN;
		local147.texturesScaleX = this.texturesScaleX;
		local147.texturesScaleY = this.texturesScaleY;
		local147.texturesScaleZ = this.texturesScaleZ;
		local147.textureRotationY = this.textureRotationY;
		local147.aByteArray32 = this.aByteArray32;
		local147.aByteArray34 = this.aByteArray34;
		local147.textureTriangleTranslationU = this.textureTriangleTranslationU;
		local147.textureTriangleTranslationV = this.textureTriangleTranslationV;
		local147.vertexBones = this.vertexBones;
		local147.triangleBones = this.triangleBones;
		local147.boneVertices = this.boneVertices;
		local147.boneTriangles = this.boneTriangles;
		local147.aShort19 = this.aShort19;
		local147.aShort18 = this.aShort18;
		local147.vertexNormals = this.vertexNormals;
		local147.triangleNormals = this.triangleNormals;
		local147.aVertexNormalArray2 = this.aVertexNormalArray2;
		if (arg0 == 3) {
			local147.vertexX = ArrayUtils.copyOfNullable(this.vertexX);
			local147.vertexY = ArrayUtils.copyOfNullable(this.vertexY);
			local147.vertexZ = ArrayUtils.copyOfNullable(this.vertexZ);
		} else {
			local147.vertexX = this.vertexX;
			local147.vertexY = new int[local147.vertexCount];
			local147.vertexZ = this.vertexZ;
		}
		@Pc(326) int local326;
		@Pc(337) int local337;
		@Pc(344) int local344;
		@Pc(348) int local348;
		@Pc(352) int local352;
		@Pc(356) int local356;
		@Pc(360) int local360;
		@Pc(382) int local382;
		@Pc(408) int local408;
		@Pc(420) int local420;
		if (arg0 == 1) {
			for (local326 = 0; local326 < local147.vertexCount; local326++) {
				local337 = this.vertexX[local326] + arg4;
				local344 = this.vertexZ[local326] + arg6;
				local348 = local337 & 0x7F;
				local352 = local344 & 0x7F;
				local356 = local337 >> 7;
				local360 = local344 >> 7;
				local382 = arg2[local356][local360] * (128 - local348) + arg2[local356 + 1][local360] * local348 >> 7;
				local408 = arg2[local356][local360 + 1] * (128 - local348) + arg2[local356 + 1][local360 + 1] * local348 >> 7;
				local420 = local382 * (128 - local352) + local408 * local352 >> 7;
				local147.vertexY[local326] = this.vertexY[local326] + local420 - arg5;
			}
		} else {
			@Pc(547) int local547;
			if (arg0 == 2) {
				for (local326 = 0; local326 < local147.vertexCount; local326++) {
					local337 = (this.vertexY[local326] << 16) / this.minY;
					if (local337 < arg1) {
						local344 = this.vertexX[local326] + arg4;
						local348 = this.vertexZ[local326] + arg6;
						local352 = local344 & 0x7F;
						local356 = local348 & 0x7F;
						local360 = local344 >> 7;
						local382 = local348 >> 7;
						local408 = arg2[local360][local382] * (128 - local352) + arg2[local360 + 1][local382] * local352 >> 7;
						local420 = arg2[local360][local382 + 1] * (128 - local352) + arg2[local360 + 1][local382 + 1] * local352 >> 7;
						local547 = local408 * (128 - local356) + local420 * local356 >> 7;
						local147.vertexY[local326] = this.vertexY[local326] + (local547 - arg5) * (arg1 - local337) / arg1;
					} else {
						local147.vertexY[local326] = this.vertexY[local326];
					}
				}
			} else if (arg0 == 3) {
				local326 = (arg1 & 0xFF) * 4;
				local337 = (arg1 >> 8 & 0xFF) * 4;
				this.method1667(arg2, arg4, arg5, arg6, local326, local337);
			} else if (arg0 == 4) {
				local326 = this.maxY - this.minY;
				for (local337 = 0; local337 < this.vertexCount; local337++) {
					local344 = this.vertexX[local337] + arg4;
					local348 = this.vertexZ[local337] + arg6;
					local352 = local344 & 0x7F;
					local356 = local348 & 0x7F;
					local360 = local344 >> 7;
					local382 = local348 >> 7;
					local408 = arg3[local360][local382] * (128 - local352) + arg3[local360 + 1][local382] * local352 >> 7;
					local420 = arg3[local360][local382 + 1] * (128 - local352) + arg3[local360 + 1][local382 + 1] * local352 >> 7;
					local547 = local408 * (128 - local356) + local420 * local356 >> 7;
					local147.vertexY[local337] = this.vertexY[local337] + local547 + local326 - arg5;
				}
			} else if (arg0 == 5) {
				local326 = this.maxY - this.minY;
				for (local337 = 0; local337 < this.vertexCount; local337++) {
					local344 = this.vertexX[local337] + arg4;
					local348 = this.vertexZ[local337] + arg6;
					local352 = local344 & 0x7F;
					local356 = local348 & 0x7F;
					local360 = local344 >> 7;
					local382 = local348 >> 7;
					local408 = arg2[local360][local382] * (128 - local352) + arg2[local360 + 1][local382] * local352 >> 7;
					local420 = arg2[local360][local382 + 1] * (128 - local352) + arg2[local360 + 1][local382 + 1] * local352 >> 7;
					local547 = local408 * (128 - local356) + local420 * local356 >> 7;
					local408 = arg3[local360][local382] * (128 - local352) + arg3[local360 + 1][local382] * local352 >> 7;
					local420 = arg3[local360][local382 + 1] * (128 - local352) + arg3[local360 + 1][local382 + 1] * local352 >> 7;
					@Pc(890) int local890 = local408 * (128 - local356) + local420 * local356 >> 7;
					@Pc(894) int local894 = local547 - local890;
					local147.vertexY[local337] = ((this.vertexY[local337] << 8) / local326 * local894 >> 8) - (arg5 - local547);
				}
			}
		}
		this.boundsValid = false;
		return local147;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(IIIII)Lclient!w;")
	public SoftwareModel createSoftwareModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		return new SoftwareModel(this, arg0, arg1, -50, -10, -50);
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIII)V")
	@Override
	public void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(III)V")
	public void translate(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int z) {
		for (@Pc(1) int v = 0; v < this.vertexCount; v++) {
			this.vertexX[v] += x;
			this.vertexY[v] += y;
			this.vertexZ[v] += z;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "h", descriptor = "()V")
	public void rotateY180() {
		@Pc(1) int local1;
		for (local1 = 0; local1 < this.vertexCount; local1++) {
			this.vertexZ[local1] = -this.vertexZ[local1];
		}
		for (local1 = 0; local1 < this.triangleCount; local1++) {
			@Pc(27) int local27 = this.triangleVertexA[local1];
			this.triangleVertexA[local1] = this.triangleVertexC[local1];
			this.triangleVertexC[local1] = local27;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "()Z")
	@Override
	public boolean method4543() {
		return true;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "([B)V")
	private void decodeNew(@OriginalArg(0) byte[] src) {
		@Pc(4) Packet packet1 = new Packet(src);
		@Pc(9) Packet packet2 = new Packet(src);
		@Pc(14) Packet packet3 = new Packet(src);
		@Pc(19) Packet packet4 = new Packet(src);
		@Pc(24) Packet packet5 = new Packet(src);
		@Pc(29) Packet packet6 = new Packet(src);
		@Pc(34) Packet packet7 = new Packet(src);
		packet1.offset = src.length - 23;
		@Pc(44) int vertexCount = packet1.g2();
		@Pc(48) int triangleCount = packet1.g2();
		@Pc(52) int texturedCount = packet1.g1();
		@Pc(56) int hasInfo = packet1.g1();
		@Pc(65) boolean hasTriangleInfo = (hasInfo & 0x1) == 1;
		@Pc(74) boolean hasParticleEmitters = (hasInfo & 0x2) == 2;
		@Pc(78) int priority = packet1.g1();
		@Pc(82) int hasAlpha = packet1.g1();
		@Pc(86) int hasTriangleBones = packet1.g1();
		@Pc(90) int hasTextures = packet1.g1();
		@Pc(94) int hasVertexBones = packet1.g1();
		@Pc(98) int dxDataLength = packet1.g2();
		@Pc(102) int dyDataLength = packet1.g2();
		@Pc(106) int dzDataLength = packet1.g2();
		@Pc(110) int vertexIndexDataLength = packet1.g2();
		@Pc(114) int triangleTextureDataLength = packet1.g2();
		@Pc(116) int simpleTextureFaceCount = 0;
		@Pc(118) int complexTextureFaceCount = 0;
		@Pc(120) int cubeTextureFaceCount = 0;
		@Pc(131) int i;
		if (texturedCount > 0) {
			this.textureTypes = new byte[texturedCount];
			packet1.offset = 0;
			for (i = 0; i < texturedCount; i++) {
				@Pc(143) byte local143 = this.textureTypes[i] = packet1.g1s();
				if (local143 == 0) {
					simpleTextureFaceCount++;
				}
				if (local143 >= 1 && local143 <= 3) {
					complexTextureFaceCount++;
				}
				if (local143 == 2) {
					cubeTextureFaceCount++;
				}
			}
		}
		i = texturedCount + vertexCount;
		@Pc(169) int triangleInfoDataOffset = i;
		if (hasTriangleInfo) {
			i += triangleCount;
		}
		@Pc(177) int triangleTypeDataOffset = i;
		i += triangleCount;
		@Pc(183) int trianglePriorityDataOffset = i;
		if (priority == 255) {
			i += triangleCount;
		}
		@Pc(192) int triangleBonesDataOffset = i;
		if (hasTriangleBones == 1) {
			i += triangleCount;
		}
		@Pc(201) int vertexBonesDataOffset = i;
		if (hasVertexBones == 1) {
			i += vertexCount;
		}
		@Pc(210) int triangleAlphaDataOffset = i;
		if (hasAlpha == 1) {
			i += triangleCount;
		}
		@Pc(219) int vertexIndexDataOffset = i;
		i += vertexIndexDataLength;
		@Pc(225) int triangleTexturesDataOffset = i;
		if (hasTextures == 1) {
			i += triangleCount * 2;
		}
		@Pc(236) int triangleTextureIndexDataOffset = i;
		i += triangleTextureDataLength;
		@Pc(242) int triangleColorDataOffset = i;
		i += triangleCount * 2;
		@Pc(250) int dxDataOffset = i;
		i += dxDataLength;
		@Pc(256) int dyDataOffset = i;
		i += dyDataLength;
		@Pc(262) int dzDataOffset = i;
		i += dzDataLength;
		@Pc(268) int simplePmnDataOffset = i;
		i += simpleTextureFaceCount * 6;
		@Pc(276) int complexPmnDataOffset = i;
		i += complexTextureFaceCount * 6;
		@Pc(284) int complexScaleDataOffset = i;
		i += complexTextureFaceCount * 6;
		@Pc(292) int complexRotationDataOffset = i;
		i += complexTextureFaceCount;
		@Pc(298) int cube1DataOffset = i;
		i += complexTextureFaceCount;
		@Pc(304) int cube2DataOffset = i;
		i += complexTextureFaceCount + cubeTextureFaceCount * 2;
		this.vertexCount = vertexCount;
		this.triangleCount = triangleCount;
		this.texturedFaceCount = texturedCount;
		this.vertexX = new int[vertexCount];
		this.vertexY = new int[vertexCount];
		this.vertexZ = new int[vertexCount];
		this.triangleVertexA = new int[triangleCount];
		this.triangleVertexB = new int[triangleCount];
		this.triangleVertexC = new int[triangleCount];
		if (hasVertexBones == 1) {
			this.vertexBones = new int[vertexCount];
		}
		if (hasTriangleInfo) {
			this.triangleInfo = new byte[triangleCount];
		}
		if (priority == 255) {
			this.trianglePriorities = new byte[triangleCount];
		} else {
			this.modelPriority = (byte) priority;
		}
		if (hasAlpha == 1) {
			this.triangleAlpha = new byte[triangleCount];
		}
		if (hasTriangleBones == 1) {
			this.triangleBones = new int[triangleCount];
		}
		if (hasTextures == 1) {
			this.unmodifiedTriangleTexture = new short[triangleCount];
		}
		if (hasTextures == 1 && texturedCount > 0) {
			this.triangleTextureIndex = new byte[triangleCount];
		}
		this.unmodifiedTriangleColour = new short[triangleCount];
		if (texturedCount > 0) {
			this.textureFacesP = new short[texturedCount];
			this.textureFacesM = new short[texturedCount];
			this.textureFacesN = new short[texturedCount];
			if (complexTextureFaceCount > 0) {
				this.texturesScaleX = new short[complexTextureFaceCount];
				this.texturesScaleY = new short[complexTextureFaceCount];
				this.texturesScaleZ = new short[complexTextureFaceCount];
				this.textureRotationY = new byte[complexTextureFaceCount];
				this.aByteArray32 = new byte[complexTextureFaceCount];
				this.aByteArray34 = new byte[complexTextureFaceCount];
			}
			if (cubeTextureFaceCount > 0) {
				this.textureTriangleTranslationU = new byte[cubeTextureFaceCount];
				this.textureTriangleTranslationV = new byte[cubeTextureFaceCount];
			}
		}
		packet1.offset = texturedCount;
		packet2.offset = dxDataOffset;
		packet3.offset = dyDataOffset;
		packet4.offset = dzDataOffset;
		packet5.offset = vertexBonesDataOffset;
		@Pc(473) int prevVertexX = 0;
		@Pc(475) int prevVertexY = 0;
		@Pc(477) int prevVertexZ = 0;
		@Pc(479) int local479;
		@Pc(486) int local486;
		@Pc(488) int local488;
		@Pc(498) int local498;
		@Pc(508) int local508;
		for (local479 = 0; local479 < vertexCount; local479++) {
			local486 = packet1.g1();
			local488 = 0;
			if ((local486 & 0x1) != 0) {
				local488 = packet2.gSmart1or2s();
			}
			local498 = 0;
			if ((local486 & 0x2) != 0) {
				local498 = packet3.gSmart1or2s();
			}
			local508 = 0;
			if ((local486 & 0x4) != 0) {
				local508 = packet4.gSmart1or2s();
			}
			this.vertexX[local479] = prevVertexX + local488;
			this.vertexY[local479] = prevVertexY + local498;
			this.vertexZ[local479] = prevVertexZ + local508;
			prevVertexX = this.vertexX[local479];
			prevVertexY = this.vertexY[local479];
			prevVertexZ = this.vertexZ[local479];
			if (hasVertexBones == 1) {
				this.vertexBones[local479] = packet5.g1();
			}
		}
		packet1.offset = triangleColorDataOffset;
		packet2.offset = triangleInfoDataOffset;
		packet3.offset = trianglePriorityDataOffset;
		packet4.offset = triangleAlphaDataOffset;
		packet5.offset = triangleBonesDataOffset;
		packet6.offset = triangleTexturesDataOffset;
		packet7.offset = triangleTextureIndexDataOffset;
		for (local479 = 0; local479 < triangleCount; local479++) {
			this.unmodifiedTriangleColour[local479] = (short) packet1.g2();
			if (hasTriangleInfo) {
				this.triangleInfo[local479] = packet2.g1s();
			}
			if (priority == 255) {
				this.trianglePriorities[local479] = packet3.g1s();
			}
			if (hasAlpha == 1) {
				this.triangleAlpha[local479] = packet4.g1s();
			}
			if (hasTriangleBones == 1) {
				this.triangleBones[local479] = packet5.g1();
			}
			if (hasTextures == 1) {
				this.unmodifiedTriangleTexture[local479] = (short) (packet6.g2() - 1);
			}
			if (this.triangleTextureIndex != null) {
				if (this.unmodifiedTriangleTexture[local479] == -1) {
					this.triangleTextureIndex[local479] = -1;
				} else {
					this.triangleTextureIndex[local479] = (byte) (packet7.g1() - 1);
				}
			}
		}
		packet1.offset = vertexIndexDataOffset;
		packet2.offset = triangleTypeDataOffset;
		local479 = 0;
		local486 = 0;
		local488 = 0;
		local498 = 0;
		@Pc(700) int local700;
		for (local508 = 0; local508 < triangleCount; local508++) {
			local700 = packet2.g1();
			if (local700 == 1) {
				local479 = packet1.gSmart1or2s() + local498;
				local486 = packet1.gSmart1or2s() + local479;
				local488 = packet1.gSmart1or2s() + local486;
				local498 = local488;
				this.triangleVertexA[local508] = local479;
				this.triangleVertexB[local508] = local486;
				this.triangleVertexC[local508] = local488;
			}
			if (local700 == 2) {
				local486 = local488;
				local488 = packet1.gSmart1or2s() + local498;
				local498 = local488;
				this.triangleVertexA[local508] = local479;
				this.triangleVertexB[local508] = local486;
				this.triangleVertexC[local508] = local488;
			}
			if (local700 == 3) {
				local479 = local488;
				local488 = packet1.gSmart1or2s() + local498;
				local498 = local488;
				this.triangleVertexA[local508] = local479;
				this.triangleVertexB[local508] = local486;
				this.triangleVertexC[local508] = local488;
			}
			if (local700 == 4) {
				@Pc(803) int local803 = local479;
				local479 = local486;
				local486 = local803;
				local488 = packet1.gSmart1or2s() + local498;
				local498 = local488;
				this.triangleVertexA[local508] = local479;
				this.triangleVertexB[local508] = local803;
				this.triangleVertexC[local508] = local488;
			}
		}
		packet1.offset = simplePmnDataOffset;
		packet2.offset = complexPmnDataOffset;
		packet3.offset = complexScaleDataOffset;
		packet4.offset = complexRotationDataOffset;
		packet5.offset = cube1DataOffset;
		packet6.offset = cube2DataOffset;
		for (local508 = 0; local508 < texturedCount; local508++) {
			local700 = this.textureTypes[local508] & 0xFF;
			if (local700 == 0) {
				this.textureFacesP[local508] = (short) packet1.g2();
				this.textureFacesM[local508] = (short) packet1.g2();
				this.textureFacesN[local508] = (short) packet1.g2();
			}
			if (local700 == 1) {
				this.textureFacesP[local508] = (short) packet2.g2();
				this.textureFacesM[local508] = (short) packet2.g2();
				this.textureFacesN[local508] = (short) packet2.g2();
				this.texturesScaleX[local508] = (short) packet3.g2();
				this.texturesScaleY[local508] = (short) packet3.g2();
				this.texturesScaleZ[local508] = (short) packet3.g2();
				this.textureRotationY[local508] = packet4.g1s();
				this.aByteArray32[local508] = packet5.g1s();
				this.aByteArray34[local508] = packet6.g1s();
			}
			if (local700 == 2) {
				this.textureFacesP[local508] = (short) packet2.g2();
				this.textureFacesM[local508] = (short) packet2.g2();
				this.textureFacesN[local508] = (short) packet2.g2();
				this.texturesScaleX[local508] = (short) packet3.g2();
				this.texturesScaleY[local508] = (short) packet3.g2();
				this.texturesScaleZ[local508] = (short) packet3.g2();
				this.textureRotationY[local508] = packet4.g1s();
				this.aByteArray32[local508] = packet5.g1s();
				this.aByteArray34[local508] = packet6.g1s();
				this.textureTriangleTranslationU[local508] = packet6.g1s();
				this.textureTriangleTranslationV[local508] = packet6.g1s();
			}
			if (local700 == 3) {
				this.textureFacesP[local508] = (short) packet2.g2();
				this.textureFacesM[local508] = (short) packet2.g2();
				this.textureFacesN[local508] = (short) packet2.g2();
				this.texturesScaleX[local508] = (short) packet3.g2();
				this.texturesScaleY[local508] = (short) packet3.g2();
				this.texturesScaleZ[local508] = (short) packet3.g2();
				this.textureRotationY[local508] = packet4.g1s();
				this.aByteArray32[local508] = packet5.g1s();
				this.aByteArray34[local508] = packet6.g1s();
			}
		}
		if (!hasParticleEmitters) {
			return;
		}
		packet1.offset = i;
		local508 = packet1.g1();
		if (local508 > 0) {
			packet1.offset += local508 * 4;
		}
		local700 = packet1.g1();
		if (local700 > 0) {
			packet1.offset += local700 * 4;
		}
	}

	@OriginalMember(owner = "client!gb", name = "i", descriptor = "()Lclient!gb;")
	public RawModel method1675() {
		@Pc(3) RawModel m = new RawModel();
		if (this.triangleInfo != null) {
			m.triangleInfo = new byte[this.triangleCount];
			System.arraycopy(this.triangleInfo, 0, m.triangleInfo, 0, this.triangleCount);
		}
		m.vertexCount = this.vertexCount;
		m.triangleCount = this.triangleCount;
		m.texturedFaceCount = this.texturedFaceCount;
		m.vertexX = this.vertexX;
		m.vertexY = this.vertexY;
		m.vertexZ = this.vertexZ;
		m.triangleVertexA = this.triangleVertexA;
		m.triangleVertexB = this.triangleVertexB;
		m.triangleVertexC = this.triangleVertexC;
		m.trianglePriorities = this.trianglePriorities;
		m.triangleAlpha = this.triangleAlpha;
		m.triangleTextureIndex = this.triangleTextureIndex;
		m.unmodifiedTriangleColour = this.unmodifiedTriangleColour;
		m.unmodifiedTriangleTexture = this.unmodifiedTriangleTexture;
		m.modelPriority = this.modelPriority;
		m.textureTypes = this.textureTypes;
		m.textureFacesP = this.textureFacesP;
		m.textureFacesM = this.textureFacesM;
		m.textureFacesN = this.textureFacesN;
		m.texturesScaleX = this.texturesScaleX;
		m.texturesScaleY = this.texturesScaleY;
		m.texturesScaleZ = this.texturesScaleZ;
		m.textureRotationY = this.textureRotationY;
		m.aByteArray32 = this.aByteArray32;
		m.aByteArray34 = this.aByteArray34;
		m.textureTriangleTranslationU = this.textureTriangleTranslationU;
		m.textureTriangleTranslationV = this.textureTriangleTranslationV;
		m.vertexBones = this.vertexBones;
		m.triangleBones = this.triangleBones;
		m.boneVertices = this.boneVertices;
		m.boneTriangles = this.boneTriangles;
		m.vertexNormals = this.vertexNormals;
		m.triangleNormals = this.triangleNormals;
		m.aShort19 = this.aShort19;
		m.aShort18 = this.aShort18;
		return m;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(IIIBSB)I")
	public int addTriangle(@OriginalArg(0) int vertexA, @OriginalArg(1) int vertexB, @OriginalArg(2) int vertexC, @OriginalArg(4) short color, @OriginalArg(5) byte alpha) {
		this.triangleVertexA[this.triangleCount] = vertexA;
		this.triangleVertexB[this.triangleCount] = vertexB;
		this.triangleVertexC[this.triangleCount] = vertexC;
		this.triangleInfo[this.triangleCount] = 1;
		this.triangleTextureIndex[this.triangleCount] = -1;
		this.unmodifiedTriangleColour[this.triangleCount] = color;
		this.unmodifiedTriangleTexture[this.triangleCount] = -1;
		this.triangleAlpha[this.triangleCount] = alpha;
		return this.triangleCount++;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(I)V")
	private void method1677(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = SIN[arg0];
		@Pc(7) int local7 = COS[arg0];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.vertexY[local9] * local7 - this.vertexZ[local9] * local3 >> 16;
			this.vertexZ[local9] = this.vertexY[local9] * local3 + this.vertexZ[local9] * local7 >> 16;
			this.vertexY[local9] = local29;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "j", descriptor = "()V")
	private void invalidate() {
		this.vertexNormals = null;
		this.aVertexNormalArray2 = null;
		this.triangleNormals = null;
		this.boundsValid = false;
	}

	@OriginalMember(owner = "client!gb", name = "c", descriptor = "(IIIII)Lclient!ak;")
	public Model createModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (GlRenderer.enabled) {
			@Pc(9) GlModel model = new GlModel(this, arg0, arg1, true);
			model.createBones();
			return model;
		} else {
			return new SoftwareModel(this, arg0, arg1, arg2, arg3, arg4);
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "()I")
	@Override
	public int getMinY() {
		if (!this.boundsValid) {
			this.calculateBounds();
		}
		return this.minY;
	}

	@OriginalMember(owner = "client!gb", name = "k", descriptor = "()V")
	public void resetBones() {
		this.vertexBones = null;
		this.triangleBones = null;
		this.boneVertices = null;
		this.boneTriangles = null;
	}

	@OriginalMember(owner = "client!gb", name = "a", descriptor = "(Lclient!th;IIIZ)V")
	@Override
	public void method4544(@OriginalArg(0) Entity arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) boolean arg4) {
		@Pc(2) RawModel m = (RawModel) arg0;
		m.calculateBounds();
		m.calculateNormals();
		anInt2138++;
		@Pc(12) int local12 = 0;
		@Pc(15) int[] local15 = m.vertexX;
		@Pc(18) int local18 = m.vertexCount;
		@Pc(20) int local20;
		for (local20 = 0; local20 < this.vertexCount; local20++) {
			@Pc(29) VertexNormal local29 = this.vertexNormals[local20];
			if (local29.magnitude != 0) {
				@Pc(40) int local40 = this.vertexY[local20] - arg2;
				if (local40 >= m.minY && local40 <= m.maxY) {
					@Pc(56) int local56 = this.vertexX[local20] - arg1;
					if (local56 >= m.minX && local56 <= m.maxX) {
						@Pc(72) int local72 = this.vertexZ[local20] - arg3;
						if (local72 >= m.minZ && local72 <= m.maxZ) {
							for (@Pc(83) int local83 = 0; local83 < local18; local83++) {
								@Pc(91) VertexNormal local91 = m.vertexNormals[local83];
								if (local56 == local15[local83] && local72 == m.vertexZ[local83] && local40 == m.vertexY[local83] && local91.magnitude != 0) {
									if (this.aVertexNormalArray2 == null) {
										this.aVertexNormalArray2 = new VertexNormal[this.vertexCount];
									}
									if (m.aVertexNormalArray2 == null) {
										m.aVertexNormalArray2 = new VertexNormal[local18];
									}
									@Pc(131) VertexNormal local131 = this.aVertexNormalArray2[local20];
									if (local131 == null) {
										local131 = this.aVertexNormalArray2[local20] = new VertexNormal(local29);
									}
									@Pc(148) VertexNormal local148 = m.aVertexNormalArray2[local83];
									if (local148 == null) {
										local148 = m.aVertexNormalArray2[local83] = new VertexNormal(local91);
									}
									local131.x += local91.x;
									local131.y += local91.y;
									local131.z += local91.z;
									local131.magnitude += local91.magnitude;
									local148.x += local29.x;
									local148.y += local29.y;
									local148.z += local29.z;
									local148.magnitude += local29.magnitude;
									local12++;
									anIntArray194[local20] = anInt2138;
									anIntArray199[local83] = anInt2138;
								}
							}
						}
					}
				}
			}
		}
		if (local12 < 3 || !arg4) {
			return;
		}
		for (local20 = 0; local20 < this.triangleCount; local20++) {
			if (anIntArray194[this.triangleVertexA[local20]] == anInt2138 && anIntArray194[this.triangleVertexB[local20]] == anInt2138 && anIntArray194[this.triangleVertexC[local20]] == anInt2138) {
				if (this.triangleInfo == null) {
					this.triangleInfo = new byte[this.triangleCount];
				}
				this.triangleInfo[local20] = 2;
			}
		}
		for (local20 = 0; local20 < m.triangleCount; local20++) {
			if (anIntArray199[m.triangleVertexA[local20]] == anInt2138 && anIntArray199[m.triangleVertexB[local20]] == anInt2138 && anIntArray199[m.triangleVertexC[local20]] == anInt2138) {
				if (m.triangleInfo == null) {
					m.triangleInfo = new byte[m.triangleCount];
				}
				m.triangleInfo[local20] = 2;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(I)V")
	public void method1682() {
		@Pc(3) int local3 = SIN[256];
		@Pc(7) int local7 = COS[256];
		for (@Pc(9) int local9 = 0; local9 < this.vertexCount; local9++) {
			@Pc(29) int local29 = this.vertexZ[local9] * local3 + this.vertexX[local9] * local7 >> 16;
			this.vertexZ[local9] = this.vertexZ[local9] * local7 - this.vertexX[local9] * local3 >> 16;
			this.vertexX[local9] = local29;
		}
		this.invalidate();
	}

	@OriginalMember(owner = "client!gb", name = "l", descriptor = "()V")
	public void createBones() {
		@Pc(5) int[] bonesLen;
		@Pc(7) int maxBone;
		@Pc(22) int temp;
		@Pc(9) int i;
		@Pc(18) int bone;
		if (this.vertexBones != null) {
			bonesLen = new int[256];
			maxBone = 0;
			for (i = 0; i < this.vertexCount; i++) {
				bone = this.vertexBones[i];
				temp = bonesLen[bone]++;
				if (bone > maxBone) {
					maxBone = bone;
				}
			}
			this.boneVertices = new int[maxBone + 1][];
			for (i = 0; i <= maxBone; i++) {
				this.boneVertices[i] = new int[bonesLen[i]];
				bonesLen[i] = 0;
			}
			i = 0;
			while (i < this.vertexCount) {
				bone = this.vertexBones[i];
				this.boneVertices[bone][bonesLen[bone]++] = i++;
			}
			this.vertexBones = null;
		}
		if (this.triangleBones == null) {
			return;
		}
		bonesLen = new int[256];
		maxBone = 0;
		for (i = 0; i < this.triangleCount; i++) {
			bone = this.triangleBones[i];
			temp = bonesLen[bone]++;
			if (bone > maxBone) {
				maxBone = bone;
			}
		}
		this.boneTriangles = new int[maxBone + 1][];
		for (i = 0; i <= maxBone; i++) {
			this.boneTriangles[i] = new int[bonesLen[i]];
			bonesLen[i] = 0;
		}
		i = 0;
		while (i < this.triangleCount) {
			bone = this.triangleBones[i];
			this.boneTriangles[bone][bonesLen[bone]++] = i++;
		}
		this.triangleBones = null;
	}

	@OriginalMember(owner = "client!gb", name = "d", descriptor = "(III)V")
	public void method1684(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(5) int local5;
		@Pc(9) int local9;
		@Pc(11) int local11;
		@Pc(31) int local31;
		if (arg2 != 0) {
			local5 = SIN[arg2];
			local9 = COS[arg2];
			for (local11 = 0; local11 < this.vertexCount; local11++) {
				local31 = this.vertexY[local11] * local5 + this.vertexX[local11] * local9 >> 16;
				this.vertexY[local11] = this.vertexY[local11] * local9 - this.vertexX[local11] * local5 >> 16;
				this.vertexX[local11] = local31;
			}
		}
		if (arg0 != 0) {
			local5 = SIN[arg0];
			local9 = COS[arg0];
			for (local11 = 0; local11 < this.vertexCount; local11++) {
				local31 = this.vertexY[local11] * local9 - this.vertexZ[local11] * local5 >> 16;
				this.vertexZ[local11] = this.vertexY[local11] * local5 + this.vertexZ[local11] * local9 >> 16;
				this.vertexY[local11] = local31;
			}
		}
		if (arg1 == 0) {
			return;
		}
		local5 = SIN[arg1];
		local9 = COS[arg1];
		for (local11 = 0; local11 < this.vertexCount; local11++) {
			local31 = this.vertexZ[local11] * local5 + this.vertexX[local11] * local9 >> 16;
			this.vertexZ[local11] = this.vertexZ[local11] * local9 - this.vertexX[local11] * local5 >> 16;
			this.vertexX[local11] = local31;
		}
	}

	@OriginalMember(owner = "client!gb", name = "e", descriptor = "(III)I")
	public int method1685(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			if (this.vertexX[local1] == arg0 && this.vertexY[local1] == 0 && this.vertexZ[local1] == arg1) {
				return local1;
			}
		}
		this.vertexX[this.vertexCount] = arg0;
		this.vertexY[this.vertexCount] = 0;
		this.vertexZ[this.vertexCount] = arg1;
		return this.vertexCount++;
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "(SS)V")
	public void recolor(@OriginalArg(0) short arg0, @OriginalArg(1) short arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.triangleCount; local1++) {
			if (this.unmodifiedTriangleColour[local1] == arg0) {
				this.unmodifiedTriangleColour[local1] = arg1;
			}
		}
	}

	@OriginalMember(owner = "client!gb", name = "b", descriptor = "([B)V")
	private void decodeOld(@OriginalArg(0) byte[] src) {
		@Pc(1) boolean hasTriangleInfo = false;
		@Pc(3) boolean hasTextures = false;
		@Pc(8) Packet packet1 = new Packet(src);
		@Pc(13) Packet packet2 = new Packet(src);
		@Pc(18) Packet packet3 = new Packet(src);
		@Pc(23) Packet packet4 = new Packet(src);
		@Pc(28) Packet packet5 = new Packet(src);
		packet1.offset = src.length - 18;
		@Pc(38) int vertexCount = packet1.g2();
		@Pc(42) int triangleCount = packet1.g2();
		@Pc(46) int texturedCount = packet1.g1();
		@Pc(50) int hasInfo = packet1.g1();
		@Pc(54) int hasPriorities = packet1.g1();
		@Pc(58) int hasAlpha = packet1.g1();
		@Pc(62) int hasTriangleBones = packet1.g1();
		@Pc(66) int hasVertexBones = packet1.g1();
		@Pc(70) int dxDataLength = packet1.g2();
		@Pc(74) int dyDataLength = packet1.g2();
		@Pc(78) int dzDataLength = packet1.g2();
		@Pc(82) int vertexIndexDataLength = packet1.g2();
		@Pc(90) int offset = vertexCount;
		@Pc(92) int triangleTypeDataOffset = offset;
		offset += triangleCount;
		@Pc(98) int trianglePriorityDataOffset = offset;
		if (hasPriorities == 255) {
			offset += triangleCount;
		}
		@Pc(107) int triangleBonesDataOffset = offset;
		if (hasTriangleBones == 1) {
			offset += triangleCount;
		}
		@Pc(116) int triangleInfoDataOffset = offset;
		if (hasInfo == 1) {
			offset += triangleCount;
		}
		@Pc(125) int vertexBonesOffset = offset;
		if (hasVertexBones == 1) {
			offset += vertexCount;
		}
		@Pc(134) int triangleAlphaDataOffset = offset;
		if (hasAlpha == 1) {
			offset += triangleCount;
		}
		@Pc(143) int vertexIndexDataOffset = offset;
		offset += vertexIndexDataLength;
		@Pc(149) int triangleColorDataOffset = offset;
		offset += triangleCount * 2;
		@Pc(157) int pmnDataOffset = offset;
		offset += texturedCount * 6;
		@Pc(165) int dxDataOffset = offset;
		offset += dxDataLength;
		@Pc(171) int dyDataOffset = offset;
		offset += dyDataLength;
		this.vertexCount = vertexCount;
		this.triangleCount = triangleCount;
		this.texturedFaceCount = texturedCount;
		this.vertexX = new int[vertexCount];
		this.vertexY = new int[vertexCount];
		this.vertexZ = new int[vertexCount];
		this.triangleVertexA = new int[triangleCount];
		this.triangleVertexB = new int[triangleCount];
		this.triangleVertexC = new int[triangleCount];
		if (texturedCount > 0) {
			this.textureTypes = new byte[texturedCount];
			this.textureFacesP = new short[texturedCount];
			this.textureFacesM = new short[texturedCount];
			this.textureFacesN = new short[texturedCount];
		}
		if (hasVertexBones == 1) {
			this.vertexBones = new int[vertexCount];
		}
		if (hasInfo == 1) {
			this.triangleInfo = new byte[triangleCount];
			this.triangleTextureIndex = new byte[triangleCount];
			this.unmodifiedTriangleTexture = new short[triangleCount];
		}
		if (hasPriorities == 255) {
			this.trianglePriorities = new byte[triangleCount];
		} else {
			this.modelPriority = (byte) hasPriorities;
		}
		if (hasAlpha == 1) {
			this.triangleAlpha = new byte[triangleCount];
		}
		if (hasTriangleBones == 1) {
			this.triangleBones = new int[triangleCount];
		}
		this.unmodifiedTriangleColour = new short[triangleCount];
		packet1.offset = 0;
		packet2.offset = dxDataOffset;
		packet3.offset = dyDataOffset;
		packet4.offset = offset;
		packet5.offset = vertexBonesOffset;
		@Pc(301) int prevVertexX = 0;
		@Pc(303) int prevVertexY = 0;
		@Pc(305) int prevVertexZ = 0;
		@Pc(307) int a;
		@Pc(314) int b;
		@Pc(316) int c;
		@Pc(326) int last;
		@Pc(336) int t;
		for (a = 0; a < vertexCount; a++) {
			b = packet1.g1();
			c = 0;
			if ((b & 0x1) != 0) {
				c = packet2.gSmart1or2s();
			}
			last = 0;
			if ((b & 0x2) != 0) {
				last = packet3.gSmart1or2s();
			}
			t = 0;
			if ((b & 0x4) != 0) {
				t = packet4.gSmart1or2s();
			}
			this.vertexX[a] = prevVertexX + c;
			this.vertexY[a] = prevVertexY + last;
			this.vertexZ[a] = prevVertexZ + t;
			prevVertexX = this.vertexX[a];
			prevVertexY = this.vertexY[a];
			prevVertexZ = this.vertexZ[a];
			if (hasVertexBones == 1) {
				this.vertexBones[a] = packet5.g1();
			}
		}
		packet1.offset = triangleColorDataOffset;
		packet2.offset = triangleInfoDataOffset;
		packet3.offset = trianglePriorityDataOffset;
		packet4.offset = triangleAlphaDataOffset;
		packet5.offset = triangleBonesDataOffset;
		for (a = 0; a < triangleCount; a++) {
			this.unmodifiedTriangleColour[a] = (short) packet1.g2();
			if (hasInfo == 1) {
				b = packet2.g1();
				if ((b & 0x1) == 1) {
					this.triangleInfo[a] = 1;
					hasTriangleInfo = true;
				} else {
					this.triangleInfo[a] = 0;
				}
				if ((b & 0x2) == 2) {
					this.triangleTextureIndex[a] = (byte) (b >> 2);
					this.unmodifiedTriangleTexture[a] = this.unmodifiedTriangleColour[a];
					this.unmodifiedTriangleColour[a] = 127;
					if (this.unmodifiedTriangleTexture[a] != -1) {
						hasTextures = true;
					}
				} else {
					this.triangleTextureIndex[a] = -1;
					this.unmodifiedTriangleTexture[a] = -1;
				}
			}
			if (hasPriorities == 255) {
				this.trianglePriorities[a] = packet3.g1s();
			}
			if (hasAlpha == 1) {
				this.triangleAlpha[a] = packet4.g1s();
			}
			if (hasTriangleBones == 1) {
				this.triangleBones[a] = packet5.g1();
			}
		}
		packet1.offset = vertexIndexDataOffset;
		packet2.offset = triangleTypeDataOffset;
		a = 0;
		b = 0;
		c = 0;
		last = 0;
		@Pc(545) int type;
		@Pc(648) int index;
		for (t = 0; t < triangleCount; t++) {
			type = packet2.g1();
			if (type == 1) {
				a = packet1.gSmart1or2s() + last;
				b = packet1.gSmart1or2s() + a;
				c = packet1.gSmart1or2s() + b;
				last = c;
				this.triangleVertexA[t] = a;
				this.triangleVertexB[t] = b;
				this.triangleVertexC[t] = c;
			}
			if (type == 2) {
				b = c;
				c = packet1.gSmart1or2s() + last;
				last = c;
				this.triangleVertexA[t] = a;
				this.triangleVertexB[t] = b;
				this.triangleVertexC[t] = c;
			}
			if (type == 3) {
				a = c;
				c = packet1.gSmart1or2s() + last;
				last = c;
				this.triangleVertexA[t] = a;
				this.triangleVertexB[t] = b;
				this.triangleVertexC[t] = c;
			}
			if (type == 4) {
				index = a;
				a = b;
				b = index;
				c = packet1.gSmart1or2s() + last;
				last = c;
				this.triangleVertexA[t] = a;
				this.triangleVertexB[t] = index;
				this.triangleVertexC[t] = c;
			}
		}
		packet1.offset = pmnDataOffset;
		for (t = 0; t < texturedCount; t++) {
			this.textureTypes[t] = 0;
			this.textureFacesP[t] = (short) packet1.g2();
			this.textureFacesM[t] = (short) packet1.g2();
			this.textureFacesN[t] = (short) packet1.g2();
		}
		if (this.triangleTextureIndex != null) {
			@Pc(721) boolean hasFaceTextures = false;
			for (type = 0; type < triangleCount; type++) {
				index = this.triangleTextureIndex[type] & 0xFF;
				if (index != 255) {
					if ((this.textureFacesP[index] & 0xFFFF) == this.triangleVertexA[type] && (this.textureFacesM[index] & 0xFFFF) == this.triangleVertexB[type] && (this.textureFacesN[index] & 0xFFFF) == this.triangleVertexC[type]) {
						this.triangleTextureIndex[type] = -1;
					} else {
						hasFaceTextures = true;
					}
				}
			}
			if (!hasFaceTextures) {
				this.triangleTextureIndex = null;
			}
		}
		if (!hasTextures) {
			this.unmodifiedTriangleTexture = null;
		}
		if (!hasTriangleInfo) {
			this.triangleInfo = null;
		}
	}

	@OriginalMember(owner = "client!gb", name = "m", descriptor = "()V")
	public void method1689() {
		for (@Pc(1) int local1 = 0; local1 < this.vertexCount; local1++) {
			@Pc(10) int local10 = this.vertexZ[local1];
			this.vertexZ[local1] = this.vertexX[local1];
			this.vertexX[local1] = -local10;
		}
		this.invalidate();
	}
}
