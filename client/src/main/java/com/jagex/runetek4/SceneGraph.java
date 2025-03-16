package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SceneGraph {
    @OriginalMember(owner = "client!fc", name = "a", descriptor = "()V")
    public static void clear() {
        @Pc(3) int local3;
        @Pc(9) int local9;
        @Pc(14) int local14;
        if (Static197.aClass3_Sub5ArrayArrayArray2 != null) {
            for (local3 = 0; local3 < Static197.aClass3_Sub5ArrayArrayArray2.length; local3++) {
                for (local9 = 0; local9 < Static152.anInt3594; local9++) {
                    for (local14 = 0; local14 < Static99.anInt2550; local14++) {
                        Static197.aClass3_Sub5ArrayArrayArray2[local3][local9][local14] = null;
                    }
                }
            }
        }
        Static36.aClass3_Sub14ArrayArray1 = null;
        if (Static276.aClass3_Sub5ArrayArrayArray3 != null) {
            for (local3 = 0; local3 < Static276.aClass3_Sub5ArrayArrayArray3.length; local3++) {
                for (local9 = 0; local9 < Static152.anInt3594; local9++) {
                    for (local14 = 0; local14 < Static99.anInt2550; local14++) {
                        Static276.aClass3_Sub5ArrayArrayArray3[local3][local9][local14] = null;
                    }
                }
            }
        }
        Static195.aClass3_Sub14ArrayArray3 = null;
        Static28.anInt917 = 0;
        if (Static91.aClass120Array1 != null) {
            for (local3 = 0; local3 < Static28.anInt917; local3++) {
                Static91.aClass120Array1[local3] = null;
            }
        }
        if (Static243.aClass31Array3 != null) {
            for (local3 = 0; local3 < Static22.anInt726; local3++) {
                Static243.aClass31Array3[local3] = null;
            }
            Static22.anInt726 = 0;
        }
        if (Static25.aClass31Array2 != null) {
            for (local3 = 0; local3 < Static25.aClass31Array2.length; local3++) {
                Static25.aClass31Array2[local3] = null;
            }
        }
    }
}
