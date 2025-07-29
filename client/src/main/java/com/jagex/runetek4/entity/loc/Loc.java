package com.jagex.runetek4.entity.loc;

import com.jagex.runetek4.graphics.effects.ParticleSystem;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.entity.entity.Entity;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.lighting.ShadowManager;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.ui.sprite.SoftwareIndexedSprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!dc")
public final class Loc extends Entity {

	@OriginalMember(owner = "client!kf", name = "h", descriptor = "[I")
	public static final int[] LAYERS = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };

	@OriginalMember(owner = "client!ci", name = "q", descriptor = "Lclient!ek;")
	public static SoftwareIndexedSprite sprite1 = null;

	@OriginalMember(owner = "runetek4.client!dc", name = "U", descriptor = "Lclient!ga;")
	private ParticleSystem particles;

	@OriginalMember(owner = "runetek4.client!dc", name = "x", descriptor = "I")
	private int shadowWorldX = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "t", descriptor = "Z")
	private boolean isMultiLoc = false;

	@OriginalMember(owner = "runetek4.client!dc", name = "s", descriptor = "I")
	private int shadowHeightOffset = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "B", descriptor = "Z")
	private boolean shadowInitialized = true;

	@OriginalMember(owner = "runetek4.client!dc", name = "Q", descriptor = "Lclient!ek;")
	private SoftwareIndexedSprite sprite2 = null;

	@OriginalMember(owner = "runetek4.client!dc", name = "T", descriptor = "I")
	private final int anInt1311 = -32768;

	@OriginalMember(owner = "runetek4.client!dc", name = "eb", descriptor = "I")
	private int shadowWorldZ = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "gb", descriptor = "I")
	private int lastShadowLocId = -1;

	@OriginalMember(owner = "runetek4.client!dc", name = "ib", descriptor = "I")
	private int lastShadowFrame = -1;

	@OriginalMember(owner = "runetek4.client!dc", name = "H", descriptor = "I")
	private final int plane;

	@OriginalMember(owner = "runetek4.client!dc", name = "w", descriptor = "I")
	private final int z;

	@OriginalMember(owner = "runetek4.client!dc", name = "N", descriptor = "I")
	private final int shape;

	@OriginalMember(owner = "runetek4.client!dc", name = "L", descriptor = "I")
	private final int x;

	@OriginalMember(owner = "runetek4.client!dc", name = "C", descriptor = "I")
	private final int locTypeId;

	@OriginalMember(owner = "runetek4.client!dc", name = "D", descriptor = "I")
	private final int rotation;

	@OriginalMember(owner = "runetek4.client!dc", name = "u", descriptor = "Lclient!tk;")
	private SeqType sequence;

	@OriginalMember(owner = "runetek4.client!dc", name = "y", descriptor = "I")
	private int frameIndex;

	@OriginalMember(owner = "runetek4.client!dc", name = "I", descriptor = "I")
	private int nextFrameIndex;

	@OriginalMember(owner = "runetek4.client!dc", name = "bb", descriptor = "I")
	private int frameDelay;

	@OriginalMember(owner = "runetek4.client!dc", name = "fb", descriptor = "I")
	private int lastUpdateTime;

	@OriginalMember(owner = "runetek4.client!dc", name = "<init>", descriptor = "(IIIIIIIZLclient!th;)V")
	public Loc(@OriginalArg(0) int locTypeId, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int plane, @OriginalArg(4) int shape, @OriginalArg(5) int rotation, @OriginalArg(6) int sequenceId, @OriginalArg(7) boolean randomStart, @OriginalArg(8) Entity replacedEntity) {
		this.plane = plane;
		this.z = z;
		this.shape = shape;
		this.x = x;
		this.locTypeId = locTypeId;
		this.rotation = rotation;
		@Pc(67) LocType local67;
		if (GlRenderer.enabled && replacedEntity != null) {
			if (replacedEntity instanceof Loc) {
				((Loc) replacedEntity).clearShadow();
			} else {
				local67 = LocTypeList.get(this.locTypeId);
				if (local67.multiloc != null) {
					local67 = local67.getMultiLoc();
				}
				if (local67 != null) {
					registerLocShadow(local67, 0, this.z, 0, this.x, this.shape, this.rotation, this.plane);
				}
			}
		}
		if (sequenceId != -1) {
			this.sequence = SeqTypeList.get(sequenceId);
			this.frameIndex = 0;
			if (this.sequence.frames.length <= 1) {
				this.nextFrameIndex = 0;
			} else {
				this.nextFrameIndex = 1;
			}
			this.frameDelay = 1;
			this.lastUpdateTime = Client.loop - 1;
			if (this.sequence.exactmove == 0 && replacedEntity != null && replacedEntity instanceof Loc) {
				@Pc(142) Loc local142 = (Loc) replacedEntity;
				if (this.sequence == local142.sequence) {
					this.frameIndex = local142.frameIndex;
					this.lastUpdateTime = local142.lastUpdateTime;
					this.frameDelay = local142.frameDelay;
					this.nextFrameIndex = local142.nextFrameIndex;
					return;
				}
			}
			if (randomStart && this.sequence.replayOff != -1) {
				this.frameIndex = (int) (Math.random() * (double) this.sequence.frames.length);
				this.nextFrameIndex = this.frameIndex + 1;
				if (this.nextFrameIndex >= this.sequence.frames.length) {
					this.nextFrameIndex -= this.sequence.replayOff;
					if (this.nextFrameIndex < 0 || this.nextFrameIndex >= this.sequence.frames.length) {
						this.nextFrameIndex = -1;
					}
				}
				this.frameDelay = (int) (Math.random() * (double) this.sequence.frameDelay[this.frameIndex]) + 1;
				this.lastUpdateTime = Client.loop - this.frameDelay;
			}
		}
		if (GlRenderer.enabled && replacedEntity != null) {
			this.updateGlModel(true);
		}
		if (replacedEntity == null) {
			local67 = LocTypeList.get(this.locTypeId);
			if (local67.multiloc != null) {
				this.isMultiLoc = true;
			}
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Lclient!pb;BIIIIIII)V")
	public static void registerLocShadow(@OriginalArg(0) LocType locType, @OriginalArg(2) int offsetZ, @OriginalArg(3) int rotation, @OriginalArg(4) int offsetX, @OriginalArg(5) int x, @OriginalArg(6) int shape, @OriginalArg(7) int z, @OriginalArg(8) int plane) {
		@Pc(5) int rotationMask = rotation & 0x3;
		@Pc(28) int effectiveWidth;
		@Pc(31) int effectiveLength;
		if (rotationMask == 1 || rotationMask == 3) {
			effectiveWidth = locType.length;
			effectiveLength = locType.width;
		} else {
			effectiveLength = locType.length;
			effectiveWidth = locType.width;
		}
		@Pc(53) int zEnd;
		@Pc(51) int zStart;
		if (z + effectiveLength > 104) {
			zStart = z + 1;
			zEnd = z;
		} else {
			zEnd = z + (effectiveLength >> 1);
			zStart = z + (effectiveLength + 1 >> 1);
		}
		@Pc(80) int worldX = (shape << 7) + (effectiveWidth << 6);
		@Pc(88) int worldZ = (z << 7) + (effectiveLength << 6);
		@Pc(96) int xStart;
		@Pc(100) int xEnd;
		if (shape + effectiveWidth > 104) {
			xStart = shape;
			xEnd = shape + 1;
		} else {
			xStart = shape + (effectiveWidth >> 1);
			xEnd = (effectiveWidth + 1 >> 1) + shape;
		}
		@Pc(120) int[][] currentPlaneHeights = SceneGraph.tileHeights[plane];
		@Pc(122) int heightOffset = 0;
		@Pc(148) int averageHeight = currentPlaneHeights[xStart][zStart] + currentPlaneHeights[xStart][zEnd] + currentPlaneHeights[xEnd][zEnd] + currentPlaneHeights[xEnd][zStart] >> 2;
		@Pc(158) int[][] upperPlaneHeights;
		if (plane != 0) {
			upperPlaneHeights = SceneGraph.tileHeights[0];
			heightOffset = averageHeight - (upperPlaneHeights[xStart][zStart] + upperPlaneHeights[xEnd][zEnd] + upperPlaneHeights[xStart][zEnd] + upperPlaneHeights[xEnd][zStart] >> 2);
		}
		upperPlaneHeights = null;
		if (plane < 3) {
			upperPlaneHeights = SceneGraph.tileHeights[plane + 1];
		}
		@Pc(215) LocEntity locEntity = locType.method3428(rotation, worldX, currentPlaneHeights, x, averageHeight, upperPlaneHeights, false, null, true, worldZ);
		ShadowManager.renderShadow(locEntity.sprite, worldX - offsetX, heightOffset, worldZ - offsetZ);
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int worldZ, @OriginalArg(4) int worldX) {
		if (GlRenderer.enabled) {
			this.updateGlModel(true);
		} else {
			this.updateAnimation(worldX, worldZ);
		}
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void render(@OriginalArg(0) int worldX, @OriginalArg(1) int worldY, @OriginalArg(2) int worldZ, @OriginalArg(3) int rotationX, @OriginalArg(4) int rotationY, @OriginalArg(5) int rotationZ, @OriginalArg(6) int scaleX, @OriginalArg(7) int scaleY, @OriginalArg(8) long flags, @OriginalArg(9) int lightLevel, @OriginalArg(10) ParticleSystem particleSystem) {
		@Pc(3) Entity model = this.getModel();
		if (model != null) {
			model.render(worldX, worldY, worldZ, rotationX, rotationY, rotationZ, scaleX, scaleY, flags, lightLevel, this.particles);
		}
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "c", descriptor = "(I)V")
	public final void clearShadow() {
		if (this.sprite2 != null) {
			ShadowManager.renderShadow(this.sprite2, this.shadowWorldX, this.shadowHeightOffset, this.shadowWorldZ);
		}
		this.lastShadowLocId = -1;
		this.lastShadowFrame = -1;
		this.sprite2 = null;
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() {
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(III)V")
	private void updateAnimation(@OriginalArg(0) int worldX, @OriginalArg(1) int worldZ) {
		if (this.sequence == null) {
			return;
		}
		@Pc(10) int elapsedTime = Client.loop - this.lastUpdateTime;
		if (elapsedTime > 100 && this.sequence.replayOff > 0) {
			@Pc(29) int nonReplayFrameCount = this.sequence.frames.length - this.sequence.replayOff;
			while (this.frameIndex < nonReplayFrameCount && this.sequence.frameDelay[this.frameIndex] < elapsedTime) {
				elapsedTime -= this.sequence.frameDelay[this.frameIndex];
				this.frameIndex++;
			}
			if (this.frameIndex >= nonReplayFrameCount) {
				@Pc(77) int replayDuration = 0;
				for (@Pc(79) int frameIndex = nonReplayFrameCount; frameIndex < this.sequence.frames.length; frameIndex++) {
					replayDuration += this.sequence.frameDelay[frameIndex];
				}
				elapsedTime %= replayDuration;
			}
			this.nextFrameIndex = this.frameIndex + 1;
			if (this.nextFrameIndex >= this.sequence.frames.length) {
				this.nextFrameIndex -= this.sequence.replayOff;
				if (this.nextFrameIndex < 0 || this.sequence.frames.length <= this.nextFrameIndex) {
					this.nextFrameIndex = -1;
				}
			}
		}
		while (elapsedTime > this.sequence.frameDelay[this.frameIndex]) {
			SoundPlayer.playSeqSound(worldX, this.sequence, worldZ, false, this.frameIndex);
			elapsedTime -= this.sequence.frameDelay[this.frameIndex];
			this.frameIndex++;
			if (this.sequence.frames.length <= this.frameIndex) {
				this.frameIndex -= this.sequence.replayOff;
				if (this.frameIndex < 0 || this.sequence.frames.length <= this.frameIndex) {
					this.sequence = null;
					break;
				}
			}
			this.nextFrameIndex = this.frameIndex + 1;
			if (this.sequence.frames.length <= this.nextFrameIndex) {
				this.nextFrameIndex -= this.sequence.replayOff;
				if (this.nextFrameIndex < 0 || this.nextFrameIndex >= this.sequence.frames.length) {
					this.nextFrameIndex = -1;
				}
			}
		}
		this.frameDelay = elapsedTime;
		this.lastUpdateTime = Client.loop - elapsedTime;
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "a", descriptor = "(ZI)Lclient!th;")
	private Entity updateGlModel(@OriginalArg(0) boolean shadowOnly) {
		@Pc(12) boolean isLevelTransition = SceneGraph.surfaceTileHeights != SceneGraph.tileHeights;
		@Pc(19) LocType locType = LocTypeList.get(this.locTypeId);
		@Pc(22) int originalAnimId = locType.anim;
		if (locType.multiloc != null) {
			locType = locType.getMultiLoc();
		}
		if (locType == null) {
			if (GlRenderer.enabled && !isLevelTransition) {
				this.clearShadow();
			}
			return null;
		}
		@Pc(69) int animationId;
		if (Client.game != 0 && this.isMultiLoc && (this.sequence == null || this.sequence != null && this.sequence.id != locType.anim)) {
			animationId = locType.anim;
			if (locType.anim == -1) {
				animationId = originalAnimId;
			}
			if (animationId == -1) {
				this.sequence = null;
			} else {
				this.sequence = SeqTypeList.get(animationId);
			}
			if (this.sequence != null) {
				if (locType.randomanimframe && this.sequence.replayOff != -1) {
					this.frameIndex = (int) (Math.random() * (double) this.sequence.frames.length);
					this.lastUpdateTime -= (int) (Math.random() * (double) this.sequence.frameDelay[this.frameIndex]);
				} else {
					this.frameIndex = 0;
					this.lastUpdateTime = Client.loop - 1;
				}
			}
		}
		animationId = this.z & 0x3;
		@Pc(160) int effectiveWidth;
		@Pc(157) int effectiveLength;
		if (animationId == 1 || animationId == 3) {
			effectiveLength = locType.width;
			effectiveWidth = locType.length;
		} else {
			effectiveWidth = locType.width;
			effectiveLength = locType.length;
		}
		@Pc(178) int xMax = this.shape + (effectiveWidth + 1 >> 1);
		@Pc(185) int xMin = (effectiveWidth >> 1) + this.shape;
		@Pc(192) int zMin = (effectiveLength >> 1) + this.rotation;
		@Pc(201) int zMax = (effectiveLength + 1 >> 1) + this.rotation;
		this.updateAnimation(zMin * 128, xMin * 128);
		@Pc(256) boolean needsShadowUpdate = !isLevelTransition && locType.hardshadow && (locType.id != this.lastShadowLocId || (this.frameIndex != this.lastShadowFrame || this.sequence != null && (this.sequence.aBoolean280 || SeqType.applyTweening) && this.frameIndex != this.nextFrameIndex) && Preferences.sceneryShadowsType >= 2);
		if (shadowOnly && !needsShadowUpdate) {
			return null;
		}
		@Pc(267) int[][] currentHeights = SceneGraph.tileHeights[this.plane];
		@Pc(293) int averageHeight = currentHeights[xMax][zMax] + currentHeights[xMin][zMax] + currentHeights[xMin][zMin] + currentHeights[xMax][zMin] >> 2;
		@Pc(302) int worldX = (effectiveWidth << 6) + (this.shape << 7);
		@Pc(311) int worldZ = (effectiveLength << 6) + (this.rotation << 7);
		@Pc(314) int[][] upperHeights = null;
		if (isLevelTransition) {
			upperHeights = SceneGraph.surfaceTileHeights[0];
		} else if (this.plane < 3) {
			upperHeights = SceneGraph.tileHeights[this.plane + 1];
		}
		if (GlRenderer.enabled && needsShadowUpdate) {
			ShadowManager.renderShadow(this.sprite2, this.shadowWorldX, this.shadowHeightOffset, this.shadowWorldZ);
		}
		@Pc(356) boolean useSharedSprite = this.sprite2 == null;
		@Pc(389) LocEntity locEntity;
		if (this.sequence == null) {
			locEntity = locType.method3428(this.z, worldX, currentHeights, this.x, averageHeight, upperHeights, false, useSharedSprite ? sprite1 : this.sprite2, needsShadowUpdate, worldZ);
		} else {
			locEntity = locType.method3429(worldZ, worldX, useSharedSprite ? sprite1 : this.sprite2, averageHeight, this.sequence, this.z, currentHeights, needsShadowUpdate, this.frameIndex, upperHeights, this.nextFrameIndex, this.x, this.frameDelay);
		}
		if (locEntity == null) {
			return null;
		}
		if (GlRenderer.enabled && needsShadowUpdate) {
			if (useSharedSprite) {
				sprite1 = locEntity.sprite;
			}
			@Pc(429) int heightOffset = 0;
			if (this.plane != 0) {
				@Pc(439) int[][] groundHeights = SceneGraph.tileHeights[0];
				heightOffset = averageHeight - (groundHeights[xMax][zMin] + groundHeights[xMin][zMin] + groundHeights[xMin][zMax] + groundHeights[xMax][zMax] >> 2);
			}
			@Pc(471) SoftwareIndexedSprite shadowSprite = locEntity.sprite;
			if (this.shadowInitialized && ShadowManager.method4209(shadowSprite, worldX, heightOffset, worldZ)) {
				this.shadowInitialized = false;
			}
			if (!this.shadowInitialized) {
				ShadowManager.method4211(shadowSprite, worldX, heightOffset, worldZ);
				this.sprite2 = shadowSprite;
				this.shadowWorldZ = worldZ;
				if (useSharedSprite) {
					sprite1 = null;
				}
				this.shadowHeightOffset = heightOffset;
				this.shadowWorldX = worldX;
			}
			this.lastShadowLocId = locType.id;
			this.lastShadowFrame = this.frameIndex;
		}
		return locEntity.model;
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "d", descriptor = "(I)Lclient!th;")
	public final Entity getModel() {
		return this.updateGlModel(false);
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "()I")
	@Override
	public final int getMinY() {
		return this.anInt1311;
	}
}
