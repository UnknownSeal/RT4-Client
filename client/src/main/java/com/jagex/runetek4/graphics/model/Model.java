package com.jagex.runetek4.graphics.model;

import com.jagex.runetek4.graphics.animation.AnimBase;
import com.jagex.runetek4.graphics.animation.AnimFrame;
import com.jagex.runetek4.graphics.animation.AnimFrameset;
import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.entity.entity.Entity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ak")
public abstract class Model extends Entity {

	@OriginalMember(owner = "runetek4.client!vc", name = "V", descriptor = "[J")
	public static final long[] aLongArray11 = new long[1000];

	@OriginalMember(owner = "runetek4.client!ak", name = "s", descriptor = "Z")
	public boolean pickable = false;

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "([[III)I")
	public static int interpolateHeight(@OriginalArg(0) int[][] heightMap, @OriginalArg(1) int worldX, @OriginalArg(2) int worldZ) {
		@Pc(3) int gridX = worldX >> 7;
		@Pc(7) int gridZ = worldZ >> 7;
		if (gridX < 0 || gridZ < 0 || gridX >= heightMap.length || gridZ >= heightMap[0].length) {
			return 0;
		}
		@Pc(27) int fracX = worldX & 0x7F;
		@Pc(31) int fracZ = worldZ & 0x7F;
		@Pc(53) int interpTop = heightMap[gridX][gridZ] * (128 - fracX) + heightMap[gridX + 1][gridZ] * fracX >> 7;
		@Pc(79) int interpBottom = heightMap[gridX][gridZ + 1] * (128 - fracX) + heightMap[gridX + 1][gridZ + 1] * fracX >> 7;
		return interpTop * (128 - fracZ) + interpBottom * fracZ >> 7;
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "c", descriptor = "()I")
	public abstract int getMaxZ();

	@OriginalMember(owner = "runetek4.client!ak", name = "b", descriptor = "()I")
	public abstract int getMinY();

	@OriginalMember(owner = "runetek4.client!ak", name = "d", descriptor = "()Z")
	protected abstract boolean supportsAnimation();

	@OriginalMember(owner = "runetek4.client!ak", name = "e", descriptor = "()V")
	public abstract void updateBounds();

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(Lclient!jm;Lclient!ne;Lclient!ne;II[ZZZI[I)V")
	private void applyAnimationFrames(@OriginalArg(0) AnimBase animBase, @OriginalArg(1) AnimFrame fromFrame, @OriginalArg(2) AnimFrame toFrame, @OriginalArg(3) int blendStep, @OriginalArg(4) int totalSteps, @OriginalArg(5) boolean[] boneFilter, @OriginalArg(6) boolean filterMode, @OriginalArg(7) boolean updateNormals, @OriginalArg(8) int visibilityMask, @OriginalArg(9) int[] skinWeights) {
		@Pc(5) int fromFrameIndex;
		if (toFrame == null || blendStep == 0) {
			for (fromFrameIndex = 0; fromFrameIndex < fromFrame.length; fromFrameIndex++) {
				@Pc(14) short local14 = fromFrame.indices[fromFrameIndex];
				if (boneFilter == null || boneFilter[local14] == filterMode || animBase.types[local14] == 0) {
					@Pc(32) short local32 = fromFrame.prevOriginIndices[fromFrameIndex];
					@Pc(42) int local42;
					if (local32 != -1) {
						local42 = visibilityMask & animBase.parts[local32];
						if (local42 == 65535) {
							this.applyBoneTransformHierarchy(0, animBase.bones[local32], 0, 0, 0, updateNormals);
						} else {
							this.method4577(0, animBase.bones[local32], 0, 0, 0, updateNormals, local42, skinWeights);
						}
					}
					local42 = visibilityMask & animBase.parts[local14];
					if (local42 == 65535) {
						this.applyBoneTransformHierarchy(animBase.types[local14], animBase.bones[local14], fromFrame.x[fromFrameIndex], fromFrame.y[fromFrameIndex], fromFrame.z[fromFrameIndex], updateNormals);
					} else {
						this.method4577(animBase.types[local14], animBase.bones[local14], fromFrame.x[fromFrameIndex], fromFrame.y[fromFrameIndex], fromFrame.z[fromFrameIndex], updateNormals, local42, skinWeights);
					}
				}
			}
			return;
		}
		fromFrameIndex = 0;
		@Pc(136) int toFrameIndex = 0;
		for (@Pc(138) int transformIndex = 0; transformIndex < animBase.transforms; transformIndex++) {
			@Pc(144) boolean hasFromTransform = false;
			if (fromFrameIndex < fromFrame.length && fromFrame.indices[fromFrameIndex] == transformIndex) {
				hasFromTransform = true;
			}
			@Pc(158) boolean hasToTransform = false;
			if (toFrameIndex < toFrame.length && toFrame.indices[toFrameIndex] == transformIndex) {
				hasToTransform = true;
			}
			if (hasFromTransform || hasToTransform) {
				if (boneFilter == null || boneFilter[transformIndex] == filterMode || animBase.types[transformIndex] == 0) {
					@Pc(196) short defaultValue = 0;
					@Pc(201) int transformType = animBase.types[transformIndex];
					if (transformType == 3) {
						defaultValue = 128;
					}
					@Pc(213) short fromX;
					@Pc(218) short fromY;
					@Pc(223) short fromZ;
					@Pc(228) short fromPrevBone;
					@Pc(233) byte fromFlags;
					if (hasFromTransform) {
						fromX = fromFrame.x[fromFrameIndex];
						fromY = fromFrame.y[fromFrameIndex];
						fromZ = fromFrame.z[fromFrameIndex];
						fromPrevBone = fromFrame.prevOriginIndices[fromFrameIndex];
						fromFlags = fromFrame.flags[fromFrameIndex];
						fromFrameIndex++;
					} else {
						fromX = defaultValue;
						fromY = defaultValue;
						fromZ = defaultValue;
						fromPrevBone = -1;
						fromFlags = 0;
					}
					@Pc(252) short toX;
					@Pc(257) short toY;
					@Pc(262) short toZ;
					@Pc(267) short toPrevBone;
					@Pc(272) byte toFlags;
					if (hasToTransform) {
						toX = toFrame.x[toFrameIndex];
						toY = toFrame.y[toFrameIndex];
						toZ = toFrame.z[toFrameIndex];
						toPrevBone = toFrame.prevOriginIndices[toFrameIndex];
						toFlags = toFrame.flags[toFrameIndex];
						toFrameIndex++;
					} else {
						toX = defaultValue;
						toY = defaultValue;
						toZ = defaultValue;
						toPrevBone = -1;
						toFlags = 0;
					}
					@Pc(294) int finalX;
					@Pc(296) int finalY;
					@Pc(298) int finalZ;
					@Pc(308) int local308;
					if ((fromFlags & 0x2) != 0 || (toFlags & 0x1) != 0) {
						finalX = fromX;
						finalY = fromY;
						finalZ = fromZ;
					} else if (transformType == 2) {
						local308 = toX - fromX & 0x7FF;
						@Pc(314) int local314 = toY - fromY & 0x7FF;
						@Pc(320) int local320 = toZ - fromZ & 0x7FF;
						if (local308 >= 1024) {
							local308 -= 2048;
						}
						if (local314 >= 1024) {
							local314 -= 2048;
						}
						if (local320 >= 1024) {
							local320 -= 2048;
						}
						finalX = fromX + local308 * blendStep / totalSteps & 0x7FF;
						finalY = fromY + local314 * blendStep / totalSteps & 0x7FF;
						finalZ = fromZ + local320 * blendStep / totalSteps & 0x7FF;
					} else if (transformType == 7) {
						local308 = toX - fromX & 0x3F;
						if (local308 >= 32) {
							local308 -= 64;
						}
						finalX = fromX + local308 * blendStep / totalSteps & 0x3F;
						finalY = fromY + (toY - fromY) * blendStep / totalSteps;
						finalZ = fromZ + (toZ - fromZ) * blendStep / totalSteps;
					} else {
						finalX = fromX + (toX - fromX) * blendStep / totalSteps;
						finalY = fromY + (toY - fromY) * blendStep / totalSteps;
						finalZ = fromZ + (toZ - fromZ) * blendStep / totalSteps;
					}
					if (fromPrevBone != -1) {
						local308 = visibilityMask & animBase.parts[fromPrevBone];
						if (local308 == 65535) {
							this.applyBoneTransformHierarchy(0, animBase.bones[fromPrevBone], 0, 0, 0, updateNormals);
						} else {
							this.method4577(0, animBase.bones[fromPrevBone], 0, 0, 0, updateNormals, local308, skinWeights);
						}
					} else if (toPrevBone != -1) {
						local308 = visibilityMask & animBase.parts[toPrevBone];
						if (local308 == 65535) {
							this.applyBoneTransformHierarchy(0, animBase.bones[toPrevBone], 0, 0, 0, updateNormals);
						} else {
							this.method4577(0, animBase.bones[toPrevBone], 0, 0, 0, updateNormals, local308, skinWeights);
						}
					}
					local308 = visibilityMask & animBase.parts[transformIndex];
					if (local308 == 65535) {
						this.applyBoneTransformHierarchy(transformType, animBase.bones[transformIndex], finalX, finalY, finalZ, updateNormals);
					} else {
						this.method4577(transformType, animBase.bones[transformIndex], finalX, finalY, finalZ, updateNormals, local308, skinWeights);
					}
				} else {
					if (hasFromTransform) {
						fromFrameIndex++;
					}
					if (hasToTransform) {
						toFrameIndex++;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "b", descriptor = "(I)V")
	public abstract void rotateY(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(Lclient!cl;I)V")
	public final void applyShadowAnimation(@OriginalArg(0) AnimFrameset frameset, @OriginalArg(1) int frameId) {
		if (frameId == -1 || !this.supportsAnimation()) {
			return;
		}
		@Pc(12) AnimFrame frame = frameset.frames[frameId];
		@Pc(15) AnimBase animBase = frame.animBase;
		for (@Pc(17) int transformIndex = 0; transformIndex < frame.length; transformIndex++) {
			@Pc(26) short boneIndex = frame.indices[transformIndex];
			if (animBase.shadow[boneIndex]) {
				if (frame.prevOriginIndices[transformIndex] != -1) {
					this.applyBoneTransform(0, 0, 0, 0);
				}
				this.applyBoneTransform(animBase.types[boneIndex], frame.x[transformIndex], frame.y[transformIndex], frame.z[transformIndex]);
			}
		}
		this.finishTransforms();
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "f", descriptor = "()V")
	protected abstract void finishTransforms();

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(Lclient!cl;ILclient!cl;IIIZ)V")
	public final void blendAnimation(@OriginalArg(0) AnimFrameset fromFrameset, @OriginalArg(1) int fromFrameId, @OriginalArg(2) AnimFrameset toFrameset, @OriginalArg(3) int toFrameId, @OriginalArg(4) int blendStep, @OriginalArg(5) int totalSteps, @OriginalArg(6) boolean updateNormals) {
		if (fromFrameId == -1 || !this.supportsAnimation()) {
			return;
		}
		@Pc(12) AnimFrame fromFrame = fromFrameset.frames[fromFrameId];
		@Pc(15) AnimBase animBase = fromFrame.animBase;
		@Pc(17) AnimFrame toFrame = null;
		if (toFrameset != null) {
			toFrame = toFrameset.frames[toFrameId];
			if (toFrame.animBase != animBase) {
				toFrame = null;
			}
		}
		this.applyAnimationFrames(animBase, fromFrame, toFrame, blendStep, totalSteps, null, false, updateNormals, 65535, null);
		this.finishTransforms();
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "b", descriptor = "(III)V")
	public abstract void resize(@OriginalArg(0) int scaleX, @OriginalArg(1) int scaleY, @OriginalArg(2) int scaleZ);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(ZZZ)Lclient!ak;")
	public abstract Model copy(@OriginalArg(0) boolean copyVertices, @OriginalArg(1) boolean copyNormals, @OriginalArg(2) boolean copyTextures);

	@OriginalMember(owner = "runetek4.client!ak", name = "g", descriptor = "()I")
	public abstract int getMaxX();

	@OriginalMember(owner = "runetek4.client!ak", name = "h", descriptor = "()I")
	public abstract int getMinX();

	@OriginalMember(owner = "runetek4.client!ak", name = "i", descriptor = "()V")
	public abstract void rotateCounterClockwise();

	@OriginalMember(owner = "runetek4.client!ak", name = "c", descriptor = "(I)V")
	public abstract void rotateZ(@OriginalArg(0) int angle);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(Lclient!cl;ILclient!cl;IIIIZ[I)V")
	public final void blendAnimationWeighted(@OriginalArg(0) AnimFrameset fromFrameset, @OriginalArg(1) int fromFrameId, @OriginalArg(2) AnimFrameset toFrameset, @OriginalArg(3) int toFrameId, @OriginalArg(4) int blendStep, @OriginalArg(5) int totalSteps, @OriginalArg(6) int visibilityMask, @OriginalArg(7) boolean updateNormals, @OriginalArg(8) int[] skinWeights) {
		if (fromFrameId == -1 || !this.supportsAnimation()) {
			return;
		}
		@Pc(12) AnimFrame fromFrame = fromFrameset.frames[fromFrameId];
		@Pc(15) AnimBase animBase = fromFrame.animBase;
		@Pc(17) AnimFrame toFrame = null;
		if (toFrameset != null) {
			toFrame = toFrameset.frames[toFrameId];
			if (toFrame.animBase != animBase) {
				toFrame = null;
			}
		}
		this.applyAnimationFrames(animBase, fromFrame, toFrame, blendStep, totalSteps, null, false, updateNormals, visibilityMask, skinWeights);
		this.finishTransforms();
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "j", descriptor = "()I")
	public abstract int getLengthXZ();

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(IIII)V")
	protected abstract void applyBoneTransform(@OriginalArg(0) int transformType, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int z);

	@OriginalMember(owner = "runetek4.client!ak", name = "b", descriptor = "(ZZZ)Lclient!ak;")
	public abstract Model createVariant(@OriginalArg(0) boolean shareVertices, @OriginalArg(1) boolean shareNormals, @OriginalArg(2) boolean shareTextures);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(I[IIIIZ)V")
	protected abstract void applyBoneTransformHierarchy(@OriginalArg(0) int transformType, @OriginalArg(1) int[] boneIndices, @OriginalArg(2) int x, @OriginalArg(3) int y, @OriginalArg(4) int z, @OriginalArg(5) boolean updateNormals);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(Lclient!cl;ILclient!cl;IIILclient!cl;ILclient!cl;III[ZZ)V")
	public final void blendLayeredAnimation(@OriginalArg(0) AnimFrameset primaryFrameset, @OriginalArg(1) int primaryFrameId, @OriginalArg(2) AnimFrameset primaryBlendFrameset, @OriginalArg(3) int primaryBlendFrameId, @OriginalArg(4) int primaryBlendStep, @OriginalArg(5) int primaryTotalSteps, @OriginalArg(6) AnimFrameset secondaryFrameset, @OriginalArg(7) int secondaryFrameId, @OriginalArg(8) AnimFrameset secondaryBlendFrameset, @OriginalArg(9) int secondaryBlendFrameId, @OriginalArg(10) int secondaryBlendStep, @OriginalArg(11) int secondaryTotalSteps, @OriginalArg(12) boolean[] boneFilter, @OriginalArg(13) boolean updateNormals) {
		if (primaryFrameId == -1) {
			return;
		}
		if (boneFilter == null || secondaryFrameId == -1) {
			this.blendAnimation(primaryFrameset, primaryFrameId, primaryBlendFrameset, primaryBlendFrameId, primaryBlendStep, primaryTotalSteps, updateNormals);
		} else if (this.supportsAnimation()) {
			@Pc(27) AnimFrame primaryFrame = primaryFrameset.frames[primaryFrameId];
			@Pc(30) AnimBase animBase = primaryFrame.animBase;
			@Pc(32) AnimFrame primaryBlendFrame = null;
			if (primaryBlendFrameset != null) {
				primaryBlendFrame = primaryBlendFrameset.frames[primaryBlendFrameId];
				if (primaryBlendFrame.animBase != animBase) {
					primaryBlendFrame = null;
				}
			}
			@Pc(50) AnimFrame secondaryFrame = secondaryFrameset.frames[secondaryFrameId];
			@Pc(52) AnimFrame secondaryBlendFrame = null;
			if (secondaryBlendFrameset != null) {
				secondaryBlendFrame = secondaryBlendFrameset.frames[secondaryBlendFrameId];
				if (secondaryBlendFrame.animBase != animBase) {
					secondaryBlendFrame = null;
				}
			}
			this.applyAnimationFrames(animBase, primaryFrame, primaryBlendFrame, primaryBlendStep, primaryTotalSteps, boneFilter, false, updateNormals, 65535, null);
			this.applyBoneTransformHierarchy(0, new int[0], 0, 0, 0, updateNormals);
			this.applyAnimationFrames(animBase, secondaryFrame, secondaryBlendFrame, secondaryBlendStep, secondaryTotalSteps, boneFilter, true, updateNormals, 65535, null);
			this.finishTransforms();
		}
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(IIIIIIIJ)V")
	public abstract void setCamera(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) long arg6);

	@OriginalMember(owner = "runetek4.client!ak", name = "c", descriptor = "(ZZZ)Lclient!ak;")
	public abstract Model method4572(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "([[IIIIII)V")
	protected final void method4573(@OriginalArg(0) int[][] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(10) int local10 = -arg4 / 2;
		@Pc(15) int local15 = -arg5 / 2;
		@Pc(24) int local24 = interpolateHeight(arg0, arg1 + local10, arg3 + local15);
		@Pc(28) int local28 = arg4 / 2;
		@Pc(33) int local33 = -arg5 / 2;
		@Pc(42) int local42 = interpolateHeight(arg0, arg1 + local28, arg3 + local33);
		@Pc(47) int local47 = -arg4 / 2;
		@Pc(51) int local51 = arg5 / 2;
		@Pc(60) int local60 = interpolateHeight(arg0, arg1 + local47, arg3 + local51);
		@Pc(64) int local64 = arg4 / 2;
		@Pc(68) int local68 = arg5 / 2;
		@Pc(77) int local77 = interpolateHeight(arg0, arg1 + local64, arg3 + local68);
		@Pc(84) int local84 = local24 < local42 ? local24 : local42;
		@Pc(91) int local91 = local60 < local77 ? local60 : local77;
		@Pc(98) int local98 = local42 < local77 ? local42 : local77;
		@Pc(105) int local105 = local24 < local60 ? local24 : local60;
		if (arg5 != 0) {
			@Pc(120) int local120 = (int) (Math.atan2((double) (local84 - local91), (double) arg5) * 325.95D) & 0x7FF;
			if (local120 != 0) {
				this.rotateX(local120);
			}
		}
		if (arg4 != 0) {
			@Pc(140) int local140 = (int) (Math.atan2((double) (local105 - local98), (double) arg4) * 325.95D) & 0x7FF;
			if (local140 != 0) {
				this.rotateZ(local140);
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

	@OriginalMember(owner = "runetek4.client!ak", name = "d", descriptor = "(I)V")
	public abstract void rotateX(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "runetek4.client!ak", name = "c", descriptor = "(III)V")
	public abstract void translate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2);

	@OriginalMember(owner = "runetek4.client!ak", name = "k", descriptor = "()I")
	public abstract int getMinZ();

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	public abstract void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10);

	@OriginalMember(owner = "runetek4.client!ak", name = "a", descriptor = "(I[IIIIZI[I)V")
	protected abstract void method4577(@OriginalArg(0) int arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int[] arg7);

	@OriginalMember(owner = "runetek4.client!ak", name = "l", descriptor = "()V")
	public abstract void method4578();
}
