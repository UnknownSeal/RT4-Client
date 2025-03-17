package com.jagex.runetek4;

import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static104 {

	@OriginalMember(owner = "runetek4.client!ia", name = "d", descriptor = "[I")
	public static final int[] friendWorld = new int[200];

	@OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(BLclient!fe;)V")
	public static void method2247(@OriginalArg(1) PathingEntity arg0) {
		@Pc(9) BasType local9 = arg0.getBasType();
		arg0.movementSeqId = local9.idleAnimationId;
		if (arg0.movementQueueSize == 0) {
			arg0.anInt3417 = 0;
			return;
		}
		if (arg0.primarySeqId != -1 && arg0.anInt3420 == 0) {
			@Pc(40) SeqType local40 = SeqTypeList.getAnimationSequence(arg0.primarySeqId);
			if (arg0.anInt3405 > 0 && local40.anInt5363 == 0) {
				arg0.anInt3417++;
				return;
			}
			if (arg0.anInt3405 <= 0 && local40.anInt5349 == 0) {
				arg0.anInt3417++;
				return;
			}
		}
		@Pc(79) int local79 = arg0.xFine;
		@Pc(82) int local82 = arg0.zFine;
		@Pc(99) int local99 = arg0.movementQueueX[arg0.movementQueueSize - 1] * 128 + arg0.getSize() * 64;
		@Pc(116) int local116 = arg0.movementQueueZ[arg0.movementQueueSize - 1] * 128 + arg0.getSize() * 64;
		if (local99 - local79 > 256 || local99 - local79 < -256 || local116 - local82 > 256 || local116 - local82 < -256) {
			arg0.xFine = local99;
			arg0.zFine = local116;
			return;
		}
		if (local99 <= local79) {
			if (local79 <= local99) {
				if (local116 > local82) {
					arg0.dstYaw = 1024;
				} else if (local82 > local116) {
					arg0.dstYaw = 0;
				}
			} else if (local116 > local82) {
				arg0.dstYaw = 768;
			} else if (local116 < local82) {
				arg0.dstYaw = 256;
			} else {
				arg0.dstYaw = 512;
			}
		} else if (local116 > local82) {
			arg0.dstYaw = 1280;
		} else if (local82 > local116) {
			arg0.dstYaw = 1792;
		} else {
			arg0.dstYaw = 1536;
		}
		@Pc(224) int local224 = arg0.dstYaw - arg0.anInt3381 & 0x7FF;
		@Pc(227) int local227 = local9.walkFullTurnAnimationId;
		if (local224 > 1024) {
			local224 -= 2048;
		}
		@Pc(233) boolean local233 = true;
		@Pc(235) byte local235 = 1;
		if (local224 >= -256 && local224 <= 256) {
			local227 = local9.walkAnimation;
		} else if (local224 >= 256 && local224 < 768) {
			local227 = local9.walkCWTurnAnimationId;
		} else if (local224 >= -768 && local224 <= -256) {
			local227 = local9.walkCCWTurnAnimationId;
		}
		@Pc(273) int local273 = 4;
		if (local227 == -1) {
			local227 = local9.walkAnimation;
		}
		arg0.movementSeqId = local227;
		if (arg0 instanceof Npc) {
			local233 = ((Npc) arg0).type.walksmoothing;
		}
		if (local233) {
			if (arg0.anInt3381 != arg0.dstYaw && arg0.targetId == -1 && arg0.anInt3376 != 0) {
				local273 = 2;
			}
			if (arg0.movementQueueSize > 2) {
				local273 = 6;
			}
			if (arg0.movementQueueSize > 3) {
				local273 = 8;
			}
			if (arg0.anInt3417 > 0 && arg0.movementQueueSize > 1) {
				local273 = 8;
				arg0.anInt3417--;
			}
		} else {
			if (arg0.movementQueueSize > 1) {
				local273 = 6;
			}
			if (arg0.movementQueueSize > 2) {
				local273 = 8;
			}
			if (arg0.anInt3417 > 0 && arg0.movementQueueSize > 1) {
				arg0.anInt3417--;
				local273 = 8;
			}
		}
		if (arg0.movementQueueSpeed[arg0.movementQueueSize - 1] == 2) {
			local273 <<= 0x1;
			local235 = 2;
		} else if (arg0.movementQueueSpeed[arg0.movementQueueSize - 1] == 0) {
			local235 = 0;
			local273 >>= 0x1;
		}
		if (local273 < 8 || local9.runAnimationId == -1) {
			if (local9.slowWalkAnimationId != -1 && local235 == 0) {
				if (local9.walkFullTurnAnimationId == arg0.movementSeqId && local9.slowWalkFullTurnAnimationId != -1) {
					arg0.movementSeqId = local9.slowWalkFullTurnAnimationId;
				} else if (local9.walkCCWTurnAnimationId == arg0.movementSeqId && local9.slowWalkCCWTurnAnimationId != -1) {
					arg0.movementSeqId = local9.slowWalkCCWTurnAnimationId;
				} else if (local9.walkCWTurnAnimationId == arg0.movementSeqId && local9.slowWalkCWTurnAnimationId != -1) {
					arg0.movementSeqId = local9.slowWalkCWTurnAnimationId;
				} else {
					arg0.movementSeqId = local9.slowWalkAnimationId;
				}
			}
		} else if (local9.walkFullTurnAnimationId == arg0.movementSeqId && local9.runFullTurnAnimationId != -1) {
			arg0.movementSeqId = local9.runFullTurnAnimationId;
		} else if (arg0.movementSeqId == local9.walkCCWTurnAnimationId && local9.runCCWTurnAnimationId != -1) {
			arg0.movementSeqId = local9.runCCWTurnAnimationId;
		} else if (arg0.movementSeqId == local9.walkCWTurnAnimationId && local9.runCWTurnAnimationId != -1) {
			arg0.movementSeqId = local9.runCWTurnAnimationId;
		} else {
			arg0.movementSeqId = local9.runAnimationId;
		}
		if (local9.movementAcceleration != -1) {
			local273 <<= 0x7;
			if (arg0.movementQueueSize == 1) {
				@Pc(594) int local594 = (local99 >= arg0.xFine ? local99 - arg0.xFine : -local99 + arg0.xFine) << 7;
				@Pc(600) int local600 = arg0.anInt3358 * arg0.anInt3358;
				@Pc(622) int local622 = (local116 < arg0.zFine ? arg0.zFine - local116 : -arg0.zFine + local116) << 7;
				@Pc(629) int local629 = local594 > local622 ? local594 : local622;
				@Pc(636) int local636 = local9.movementAcceleration * 2 * local629;
				if (local636 < local600) {
					arg0.anInt3358 /= 2;
				} else if (local629 < local600 / 2) {
					arg0.anInt3358 -= local9.movementAcceleration;
					if (arg0.anInt3358 < 0) {
						arg0.anInt3358 = 0;
					}
				} else if (arg0.anInt3358 < local273) {
					arg0.anInt3358 += local9.movementAcceleration;
					if (arg0.anInt3358 > local273) {
						arg0.anInt3358 = local273;
					}
				}
			} else if (local273 > arg0.anInt3358) {
				arg0.anInt3358 += local9.movementAcceleration;
				if (local273 < arg0.anInt3358) {
					arg0.anInt3358 = local273;
				}
			} else if (arg0.anInt3358 > 0) {
				arg0.anInt3358 -= local9.movementAcceleration;
				if (arg0.anInt3358 < 0) {
					arg0.anInt3358 = 0;
				}
			}
			local273 = arg0.anInt3358 >> 7;
			if (local273 < 1) {
				local273 = 1;
			}
		}
		if (local79 < local99) {
			arg0.xFine += local273;
			if (local99 < arg0.xFine) {
				arg0.xFine = local99;
			}
		} else if (local79 > local99) {
			arg0.xFine -= local273;
			if (local99 > arg0.xFine) {
				arg0.xFine = local99;
			}
		}
		if (local82 < local116) {
			arg0.zFine += local273;
			if (arg0.zFine > local116) {
				arg0.zFine = local116;
			}
		} else if (local116 < local82) {
			arg0.zFine -= local273;
			if (local116 > arg0.zFine) {
				arg0.zFine = local116;
			}
		}
		if (arg0.xFine == local99 && local116 == arg0.zFine) {
			arg0.movementQueueSize--;
			if (arg0.anInt3405 > 0) {
				arg0.anInt3405--;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(IB)I")
	public static int method2251(@OriginalArg(0) int arg0) {
		@Pc(11) int local11 = arg0 & 0x3F;
		@Pc(17) int local17 = arg0 >> 6 & 0x3;
		if (local11 == 18) {
			if (local17 == 0) {
				return 1;
			}
			if (local17 == 1) {
				return 2;
			}
			if (local17 == 2) {
				return 4;
			}
			if (local17 == 3) {
				return 8;
			}
		} else if (local11 == 19 || local11 == 21) {
			if (local17 == 0) {
				return 16;
			}
			if (local17 == 1) {
				return 32;
			}
			if (local17 == 2) {
				return 64;
			}
			if (local17 == 3) {
				return 128;
			}
		}
		return 0;
	}

}
