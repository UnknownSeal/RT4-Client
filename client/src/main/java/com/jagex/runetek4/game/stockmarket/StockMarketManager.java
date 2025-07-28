package com.jagex.runetek4.game.stockmarket;

import org.openrs2.deob.annotation.OriginalMember;

public class StockMarketManager {
    @OriginalMember(owner = "runetek4.client!si", name = "X", descriptor = "[Lclient!sg;")
    public static final StockMarketOffer[] offers = new StockMarketOffer[6];
    @OriginalMember(owner = "runetek4.client!ql", name = "d", descriptor = "I")
    public static int transmitAt = 0;
}
