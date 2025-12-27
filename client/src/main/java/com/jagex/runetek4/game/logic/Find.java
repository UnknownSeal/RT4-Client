package com.jagex.runetek4.game.logic;

import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
import com.jagex.runetek4.util.string.JString;
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
        sortResults(local113, results);
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
        sortResults(local117, results);
    }

    // Sort alphabetically
    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "([Lclient!na;[SI)V")
    public static void sortResults(@OriginalArg(0) JString[] arg0, @OriginalArg(1) short[] arg1) {
        quicksort(arg1, arg0.length - 1, arg0, 0);
    }

    // Quicksort that sorts two arrays in parallel
    @OriginalMember(owner = "client!ed", name = "a", descriptor = "([SI[Lclient!na;II)V")
    public static void quicksort(@OriginalArg(0) short[] ids, @OriginalArg(1) int high, @OriginalArg(2) JString[] names, @OriginalArg(4) int low) {
        if (high <= low) {
            return;
        }

        @Pc(14) int partitionIndex = low;
        @Pc(21) int pivotIndex = (low + high) / 2;
        @Pc(25) JString pivotName = names[pivotIndex];

        // Move pivot to end
        names[pivotIndex] = names[high];
        names[high] = pivotName;
        @Pc(39) short pivotId = ids[pivotIndex];
        ids[pivotIndex] = ids[high];
        ids[high] = pivotId;

        // Partition step with intentional instability
        for (@Pc(51) int i = low; i < high; i++) {
            // Compare with instability
            // This prevents identical strings from always sorting in the same order
            if (pivotName == null || names[i] != null && names[i].method3139(pivotName) < (i & 0x1)) {
                // Swap names
                @Pc(80) JString tempNames = names[i];
                names[i] = names[partitionIndex];
                names[partitionIndex] = tempNames;

                // Swap ids in parallel
                @Pc(94) short tempId = ids[i];
                ids[i] = ids[partitionIndex];
                ids[partitionIndex++] = tempId;
            }
        }

        // Move pivot to final position
        names[high] = names[partitionIndex];
        names[partitionIndex] = pivotName;
        ids[high] = ids[partitionIndex];
        ids[partitionIndex] = pivotId;

        // Recursively sort partitions
        quicksort(ids, partitionIndex - 1, names, low);
        quicksort(ids, high, names, partitionIndex + 1);
    }
}
