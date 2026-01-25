package com.jagex.game.logic;

import com.jagex.core.algorithms.Quicksort;
import com.jagex.game.runetek4.config.objtype.ObjType;
import com.jagex.game.runetek4.config.objtype.ObjTypeList;
import com.jagex.game.runetek4.config.quickchatcattype.QuickChatPhraseTypeList;
import com.jagex.game.runetek4.config.quickchatcattype.QuickChatPhraseType;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Find {
    @OriginalMember(owner = "runetek4.client!nf", name = "c", descriptor = "[S")
    public static short[] results;
    @OriginalMember(owner = "client!fe", name = "x", descriptor = "I")
    public static int size;
    @OriginalMember(owner = "runetek4.client!ii", name = "l", descriptor = "I")
    public static int resultCount;

    private static int INITIAL_RESULT_CAPACITY = 16;
    private static int MAX_QUICKCHAT_RESULTS = 50;
    private static int MAX_ITEM_RESULTS = 250;
    private static int MEMBERS_PHRASE_OFFSET = 32768;

    // Sorted alphabetically
    @OriginalMember(owner = "client!bn", name = "a", descriptor = "(BZLclient!na;)V")
    public static void findQuickChatPhrases(@OriginalArg(1) boolean membersOnly, @OriginalArg(2) JString query) {
        @Pc(9) JString queryLowercase = query.toLowerCase();
        @Pc(11) int foundCount = 0;
        @Pc(22) short[] resultIds = new short[INITIAL_RESULT_CAPACITY];

        // Determine phrase ID range based on members filter
        @Pc(28) int startId = membersOnly ? MEMBERS_PHRASE_OFFSET : 0;
        @Pc(36) int endId = (membersOnly ? QuickChatPhraseTypeList.anInt1047 : QuickChatPhraseTypeList.anInt3490) + startId;

        // Search all phrases in range
        for (@Pc(38) int phraseId = startId; phraseId < endId; phraseId++) {
            @Pc(45) QuickChatPhraseType phrase = QuickChatPhraseTypeList.get(phraseId);
            if (phrase.aBoolean60 && phrase.getText().toLowerCase().indexOf(queryLowercase) != -1) {

                // Abort search if too many results
                if (foundCount >= MAX_QUICKCHAT_RESULTS) {
                    resultCount = -1;
                    results = null;
                    return;
                }

                // Grow array if needed
                if (foundCount >= resultIds.length) {
                    @Pc(79) short[] local79 = new short[resultIds.length * 2];
                    for (@Pc(81) int local81 = 0; local81 < foundCount; local81++) {
                        local79[local81] = resultIds[local81];
                    }
                    resultIds = local79;
                }
                resultIds[foundCount++] = (short) phraseId;
            }
        }

        // Store results
        results = resultIds;
        resultCount = foundCount;
        size = 0;

        // Sort aplhabetically
        @Pc(113) JString[] local113 = new JString[resultCount];
        for (@Pc(115) int local115 = 0; local115 < resultCount; local115++) {
            local113[local115] = QuickChatPhraseTypeList.get(resultIds[local115]).getText();
        }
        Quicksort.sortResults(local113, results);
    }

    @OriginalMember(owner = "runetek4.client!me", name = "a", descriptor = "(ZLclient!na;I)V")
    public static void findItem(@OriginalArg(0) boolean tradeableOnly, @OriginalArg(1) JString query) {
        @Pc(8) short[] resultIds = new short[INITIAL_RESULT_CAPACITY];
        @Pc(12) JString queryLowercase = query.toLowerCase();
        @Pc(14) int foundCount = 0;

        // Search all items
        for (@Pc(16) int local16 = 0; local16 < ObjTypeList.capacity; local16++) {
            @Pc(27) ObjType local27 = ObjTypeList.get(local16);

            // Apply filters and check name match
            if ((!tradeableOnly || local27.stockMarket)
                    && local27.certTemplate == -1
                    && local27.lentTemplate == -1
                    && local27.dummyItem == 0
                    && local27.name.toLowerCase().indexOf(queryLowercase) != -1) {

                // Abort search if too many results
                if (foundCount >= MAX_ITEM_RESULTS) {
                    results = null;
                    resultCount = -1;
                    return;
                }

                // Grow array if needed
                if (foundCount >= resultIds.length) {
                    @Pc(83) short[] local83 = new short[resultIds.length * 2];
                    for (@Pc(85) int local85 = 0; local85 < foundCount; local85++) {
                        local83[local85] = resultIds[local85];
                    }
                    resultIds = local83;
                }
                resultIds[foundCount++] = (short) local16;
            }
        }

        // Store results
        results = resultIds;
        size = 0;
        resultCount = foundCount;

        // Sort alphabetically
        @Pc(117) JString[] local117 = new JString[resultCount];
        for (@Pc(119) int local119 = 0; local119 < resultCount; local119++) {
            local117[local119] = ObjTypeList.get(resultIds[local119]).name;
        }
        Quicksort.sortResults(local117, results);
    }

}
