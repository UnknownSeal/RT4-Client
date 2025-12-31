package com.jagex.core.algorithms;

import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Quicksort {
    // Sort alphabetically
    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "([Lclient!na;[SI)V")
    public static void sortResults(@OriginalArg(0) JString[] arg0, @OriginalArg(1) short[] arg1) {
        sort(arg1, arg0.length - 1, arg0, 0);
    }

    // Quicksort that sorts two arrays in parallel
    @OriginalMember(owner = "client!ed", name = "a", descriptor = "([SI[Lclient!na;II)V")
    public static void sort(@OriginalArg(0) short[] ids, @OriginalArg(1) int end, @OriginalArg(2) JString[] strings, @OriginalArg(4) int start) {
        if (end <= start) {
            return;
        }

        @Pc(14) int partitionIndex = start;
        @Pc(21) int pivotIndex = (start + end) / 2;
        @Pc(25) JString pivotStr = strings[pivotIndex];

        // Move pivot to end
        strings[pivotIndex] = strings[end];
        strings[end] = pivotStr;
        @Pc(39) short pivotId = ids[pivotIndex];
        ids[pivotIndex] = ids[end];
        ids[end] = pivotId;

        // Partition step with intentional instability
        for (@Pc(51) int i = start; i < end; i++) {
            // Compare with instability
            // This prevents identical strings from always sorting in the same order
            if (pivotStr == null || strings[i] != null && strings[i].method3139(pivotStr) < (i & 0x1)) {
                // Swap names
                @Pc(80) JString tempNames = strings[i];
                strings[i] = strings[partitionIndex];
                strings[partitionIndex] = tempNames;

                // Swap ids in parallel
                @Pc(94) short tempId = ids[i];
                ids[i] = ids[partitionIndex];
                ids[partitionIndex++] = tempId;
            }
        }

        // Move pivot to final position
        strings[end] = strings[partitionIndex];
        strings[partitionIndex] = pivotStr;
        ids[end] = ids[partitionIndex];
        ids[partitionIndex] = pivotId;

        // Recursively sort partitions
        sort(ids, partitionIndex - 1, strings, start);
        sort(ids, end, strings, partitionIndex + 1);
    }
}
