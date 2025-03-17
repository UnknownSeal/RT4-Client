package com.jagex.runetek4;

import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Protocol {
    @OriginalMember(owner = "client!ag", name = "P", descriptor = "Lclient!i;")
    public static final PacketBit outboundBuffer = new PacketBit(5000);
    @OriginalMember(owner = "runetek4.client!eg", name = "e", descriptor = "Lclient!i;")
    public static final PacketBit inboundBuffer = new PacketBit(65536);
    @OriginalMember(owner = "runetek4.client!qi", name = "t", descriptor = "I")
    public static int anInt4762 = 0;
    @OriginalMember(owner = "runetek4.client!fe", name = "R", descriptor = "Z")
    public static boolean prevFocus = true;
    @OriginalMember(owner = "runetek4.client!dm", name = "q", descriptor = "I")
    public static int opcode4 = 0;
    @OriginalMember(owner = "runetek4.client!af", name = "k", descriptor = "I")
    public static int opcode3 = 0;
    @OriginalMember(owner = "runetek4.client!na", name = "l", descriptor = "I")
    public static int opcode = 0;
    @OriginalMember(owner = "runetek4.client!sj", name = "t", descriptor = "I")
    public static int opcode2 = 0;
    @OriginalMember(owner = "runetek4.client!pm", name = "ab", descriptor = "Z")
    public static boolean aBoolean228 = true;
    @OriginalMember(owner = "runetek4.client!pe", name = "a", descriptor = "I")
	public static int verifyId = 0;
    @OriginalMember(owner = "runetek4.client!od", name = "i", descriptor = "I")
    public static int sceneDelta = 0;
    @OriginalMember(owner = "runetek4.client!cj", name = "n", descriptor = "Lsignlink!im;")
    public static PrivilegedRequest openUrlRequest;
    @OriginalMember(owner = "runetek4.client!na", name = "W", descriptor = "Z")
    public static boolean newTab;

    @OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(Z)V")
    public static void readPlayerInfo() {
        @Pc(6) int local6 = inboundBuffer.gBit(8);

        if (PlayerList.playerCount > local6) {
            for (int inxed = local6; inxed < PlayerList.playerCount; inxed++) {
                Static52.entityRemovalIds[Static240.entityRemovalCount++] = PlayerList.playerIds[inxed];
            }
        }
        if (local6 > PlayerList.playerCount) {
            throw new RuntimeException("gppov1");
        }

        PlayerList.playerCount = 0;

        for (int index = 0; index < local6; index++) {
            @Pc(75) int local75 = PlayerList.playerIds[index];
            @Pc(79) Player local79 = PlayerList.players[local75];
            @Pc(84) int local84 = inboundBuffer.gBit(1);
            if (local84 == 0) {
                PlayerList.playerIds[PlayerList.playerCount++] = local75;
                local79.cycle = client.loop;
            } else {
                @Pc(107) int local107 = inboundBuffer.gBit(2);
                if (local107 == 0) {
                    PlayerList.playerIds[PlayerList.playerCount++] = local75;
                    local79.cycle = client.loop;
                    Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                } else {
                    @Pc(153) int local153;
                    @Pc(163) int local163;
                    if (local107 == 1) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.cycle = client.loop;
                        local153 = inboundBuffer.gBit(3);
                        local79.method2684(1, local153);
                        local163 = inboundBuffer.gBit(1);
                        if (local163 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                        }
                    } else if (local107 == 2) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.cycle = client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local153 = inboundBuffer.gBit(3);
                            local79.method2684(2, local153);
                            local163 = inboundBuffer.gBit(3);
                            local79.method2684(2, local163);
                        } else {
                            local153 = inboundBuffer.gBit(3);
                            local79.method2684(0, local153);
                        }
                        local153 = inboundBuffer.gBit(1);
                        if (local153 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                        }
                    } else if (local107 == 3) {
                        Static52.entityRemovalIds[Static240.entityRemovalCount++] = local75;
                    }
                }
            }
        }
    }
}
