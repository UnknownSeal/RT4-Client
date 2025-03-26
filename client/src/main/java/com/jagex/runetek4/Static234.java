package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static234 {

	@OriginalMember(owner = "runetek4.client!ta", name = "o", descriptor = "[I")
	public static int[] anIntArray454;

	@OriginalMember(owner = "runetek4.client!ta", name = "z", descriptor = "[I")
	public static final int[] anIntArray456 = new int[] { -1, 0, 8, 0, 2, 0, 0, 0, 0, 12, 0, 1, 0, 3, 7, 0, 15, 6, 0, 0, 4, 7, -2, -1, 2, 0, 2, 8, 0, 0, 0, 0, -2, 5, 0, 0, 8, 3, 6, 0, 0, 0, -1, 0, -1, 0, 0, 6, -2, 0, 12, 0, 0, 0, -1, -2, 10, 0, 0, 0, 3, 0, -1, 0, 0, 5, 6, 0, 0, 8, -1, -1, 0, 8, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 6, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, -2, 0, 0, 0, 0, 0, 12, 2, 0, -2, -2, 20, 0, 0, 10, 0, 15, 0, -1, 0, 8, -2, 0, 0, 0, 8, 0, 12, 0, 0, 7, 0, 0, 0, 0, 0, -1, -1, 0, 4, 5, 0, 0, 0, 6, 0, 0, 0, 0, 8, 9, 0, 0, 0, 2, -1, 0, -2, 0, 4, 14, 0, 0, 0, 24, 0, -2, 5, 0, 0, 0, 10, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 1, 0, 0, 2, -1, 1, 0, 0, 0, 0, 14, 0, 0, 0, 0, 10, 5, 0, 0, 0, 0, 0, -2, 0, 0, 9, 0, 0, 8, 0, 0, 0, 0, -2, 6, 0, 0, 0, -2, 0, 3, 0, 1, 7, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 3, 0, 0 };

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(I)V")
	public static void method4014() {
		for (@Pc(3) int local3 = 0; local3 < Static116.entityUpdateCount; local3++) {
			@Pc(10) int local10 = Static44.entityUpdateIds[local3];
			@Pc(14) Npc local14 = NpcList.npcs[local10];
			@Pc(18) int local18 = Protocol.inboundBuffer.g1();
			if ((local18 & 0x8) != 0) {
				local18 += Protocol.inboundBuffer.g1() << 8;
			}
			@Pc(43) int local43;
			@Pc(47) int info;
			if ((local18 & 0x40) != 0) {
				local43 = Protocol.inboundBuffer.g1();
				info = Protocol.inboundBuffer.p1neg();
				local14.addHit(info, client.loop, local43);
				local14.hitpointsBarVisibleUntil = client.loop + 300;
				local14.hitpointsBar = Protocol.inboundBuffer.g1_alt3();
			}
			if ((local18 & 0x2) != 0) {
				local43 = Protocol.inboundBuffer.p1neg();
				info = Protocol.inboundBuffer.g1_alt3();
				local14.addHit(info, client.loop, local43);
			}
			if ((local18 & 0x10) != 0) {
				local43 = Protocol.inboundBuffer.g2();
				info = Protocol.inboundBuffer.g1();
				if (local43 == 65535) {
					local43 = -1;
				}
				Static223.method3855(info, local43, local14);
			}
			if ((local18 & 0x4) != 0) {
				local14.faceEntity = Protocol.inboundBuffer.g2sub();
				if (local14.faceEntity == 65535) {
					local14.faceEntity = -1;
				}
			}
			if ((local18 & 0x80) != 0) {
				local43 = Protocol.inboundBuffer.g2sub();
				if (local43 == 65535) {
					local43 = -1;
				}
				info = Protocol.inboundBuffer.g4me();
				@Pc(147) boolean local147 = true;
				if (local43 != -1 && local14.spotAnimId != -1 && SeqTypeList.getAnimationSequence(Static34.method877(local43).animationId).priority < SeqTypeList.getAnimationSequence(Static34.method877(local14.spotAnimId).animationId).priority) {
					local147 = false;
				}
				if (local147) {
					local14.spotAnimId = local43;
					local14.spotAnimStart = (info & 0xFFFF) + client.loop;
					local14.anInt3361 = 0;
					local14.spotanimId = 0;
					local14.spotAnimY = info >> 16;
					local14.anInt3418 = 1;
					if (local14.spotAnimStart > client.loop) {
						local14.spotanimId = -1;
					}
					if (local14.spotAnimId != -1 && local14.spotAnimStart == client.loop) {
						@Pc(227) int local227 = Static34.method877(local14.spotAnimId).animationId;
						if (local227 != -1) {
							@Pc(236) SeqType local236 = SeqTypeList.getAnimationSequence(local227);
							if (local236 != null && local236.anIntArray473 != null) {
								SoundPlayer.playSeqSound(local14.zFine, local236, local14.xFine, false, 0);
							}
						}
					}
				}
			}
			if ((local18 & 0x1) != 0) {
				if (local14.type.hasAreaSound()) {
					AreaSoundManager.remove(local14);
				}
				local14.setNpcType(NpcTypeList.get(Protocol.inboundBuffer.g2le()));
				local14.setSize(local14.type.size);
				local14.anInt3365 = local14.type.bastypeid;
				if (local14.type.hasAreaSound()) {
					AreaSoundManager.add(local14.movementQueueZ[0], null, 0, local14, local14.movementQueueX[0], Player.plane, null);
				}
			}
			if ((local18 & 0x20) != 0) {
				local14.chatMessage = Protocol.inboundBuffer.gjstr();
				local14.chatLoops = 100;
			}
			if ((local18 & 0x100) != 0) {
				local43 = Protocol.inboundBuffer.p1neg();
				@Pc(331) int[] local331 = new int[local43];
				@Pc(334) int[] local334 = new int[local43];
				@Pc(337) int[] local337 = new int[local43];
				for (@Pc(339) int local339 = 0; local339 < local43; local339++) {
					@Pc(350) int local350 = Protocol.inboundBuffer.g2le();
					if (local350 == 65535) {
						local350 = -1;
					}
					local331[local339] = local350;
					local334[local339] = Protocol.inboundBuffer.g1_alt3();
					local337[local339] = Protocol.inboundBuffer.g2();
				}
				Static159.method3037(local337, local14, local334, local331);
			}
			if ((local18 & 0x200) != 0) {
				local14.faceX = Protocol.inboundBuffer.g2sub();
				local14.faceY = Protocol.inboundBuffer.g2();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IIZII)V")
	public static void method4019(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static267.anInt5773 && arg3 <= Static106.anInt2869) {
			@Pc(15) int local15 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg0, Static172.anInt4164);
			@Pc(21) int local21 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg2, Static172.anInt4164);
			Static222.method3826(arg1, arg3, local21, local15);
		}
	}

}
