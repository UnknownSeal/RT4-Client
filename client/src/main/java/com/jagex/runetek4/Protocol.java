package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Protocol {
    @OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(Z)V")
    public static void readPlayerInfo() {
        @Pc(6) int local6 = Static57.in.gBit(8);

        if (Static267.playerCount > local6) {
            for (int inxed = local6; inxed < Static267.playerCount; inxed++) {
                Static52.entityRemovalIds[Static240.entityRemovalCount++] = Static105.playerIds[inxed];
            }
        }
        if (local6 > Static267.playerCount) {
            throw new RuntimeException("gppov1");
        }

        Static267.playerCount = 0;

        for (int index = 0; index < local6; index++) {
            @Pc(75) int local75 = Static105.playerIds[index];
            @Pc(79) Player local79 = Static159.players[local75];
            @Pc(84) int local84 = Static57.in.gBit(1);
            if (local84 == 0) {
                Static105.playerIds[Static267.playerCount++] = local75;
                local79.cycle = client.loop;
            } else {
                @Pc(107) int local107 = Static57.in.gBit(2);
                if (local107 == 0) {
                    Static105.playerIds[Static267.playerCount++] = local75;
                    local79.cycle = client.loop;
                    Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                } else {
                    @Pc(153) int local153;
                    @Pc(163) int local163;
                    if (local107 == 1) {
                        Static105.playerIds[Static267.playerCount++] = local75;
                        local79.cycle = client.loop;
                        local153 = Static57.in.gBit(3);
                        local79.method2684(1, local153);
                        local163 = Static57.in.gBit(1);
                        if (local163 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                        }
                    } else if (local107 == 2) {
                        Static105.playerIds[Static267.playerCount++] = local75;
                        local79.cycle = client.loop;
                        if (Static57.in.gBit(1) == 1) {
                            local153 = Static57.in.gBit(3);
                            local79.method2684(2, local153);
                            local163 = Static57.in.gBit(3);
                            local79.method2684(2, local163);
                        } else {
                            local153 = Static57.in.gBit(3);
                            local79.method2684(0, local153);
                        }
                        local153 = Static57.in.gBit(1);
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
