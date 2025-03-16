package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class ClientProt {
    @OriginalMember(owner = "runetek4.client!ej", name = "i", descriptor = "(I)V")
    public static void sendWindowDetails() {
        Protocol.outboundBuffer.pIsaac1(243);
        Protocol.outboundBuffer.p1(DisplayMode.getWindowMode());
        Protocol.outboundBuffer.p2(GameShell.canvasWidth);
        Protocol.outboundBuffer.p2(GameShell.canvasHeigth);
        Protocol.outboundBuffer.p1(Preferences.antiAliasingMode);
    }
}
