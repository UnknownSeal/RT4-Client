package com.jagex.runetek4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class ClientScriptRunner {

	@OriginalMember(owner = "runetek4.client!t", name = "l", descriptor = "Lclient!ma;")
	public static BufferedSocket aClass95_4;

	@OriginalMember(owner = "runetek4.client!t", name = "o", descriptor = "I")
	public static int anInt5217;

	@OriginalMember(owner = "runetek4.client!t", name = "A", descriptor = "I")
	public static int anInt5225;

	@OriginalMember(owner = "runetek4.client!t", name = "G", descriptor = "[Lclient!ek;")
	public static SoftwareIndexedSprite[] aClass36_Sub1Array1;

	@OriginalMember(owner = "runetek4.client!t", name = "m", descriptor = "Z")
	public static volatile boolean focus_in = true;

	@OriginalMember(owner = "runetek4.client!t", name = "p", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_31 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!t", name = "v", descriptor = "[Lclient!na;")
	public static final JString[] aClass100Array160 = new JString[500];

	@OriginalMember(owner = "runetek4.client!t", name = "w", descriptor = "[J")
	public static final long[] aLongArray9 = new long[100];

	@OriginalMember(owner = "runetek4.client!t", name = "x", descriptor = "Z")
	public static boolean aBoolean254 = false;

	@OriginalMember(owner = "runetek4.client!t", name = "y", descriptor = "I")
	public static int anInt5223 = 0;

	@OriginalMember(owner = "runetek4.client!t", name = "z", descriptor = "I")
	public static int anInt5224 = 0;

	@OriginalMember(owner = "runetek4.client!t", name = "C", descriptor = "Lclient!na;")
	public static final JString aClass100_994 = Static28.parse(")3");

	@OriginalMember(owner = "runetek4.client!t", name = "E", descriptor = "[I")
	public static final int[] updatedVarcstrs = new int[32];

	@OriginalMember(owner = "runetek4.client!t", name = "F", descriptor = "I")
	public static int js5PrevErrors = 0;
	@OriginalMember(owner = "client!bb", name = "E", descriptor = "I")
	public static int energy = 0;

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(I)V")
	public static void method3999() {
		Static67.aClass99_20.method3104();
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(IIIZIII)V")
	public static void method4000(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(3) int local3 = 0;
		@Pc(5) Class102[] local5 = Static143.aClass102Array1;
		while (local5.length > local3) {
			@Pc(17) Class102 local17 = local5[local3];
			if (local17 != null && local17.headIconDrawType == 2) {
				Static198.method1026(arg0 >> 1, arg4, (local17.anInt4046 - Static142.originZ << 7) + local17.anInt4047, local17.anInt4050 * 2, arg2 >> 1, local17.anInt4045 + (local17.anInt4053 - Static225.originX << 7), arg3);
				if (Static65.anInt1951 > -1 && Static83.loopCycle % 20 < 10) {
					Static276.aClass3_Sub2_Sub1Array11[local17.anInt4048].drawSprite(arg1 + Static65.anInt1951 - 12, arg5 + -28 - -Static16.anInt548);
				}
			}
			local3++;
		}
	}

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(B)V")
	public static void method4001() {
		Static93.aClass99_13.method3103();
		Static125.aClass99_18.method3103();
		Static262.aClass99_35.method3103();
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(ZB)V")
	public static void method4002(@OriginalArg(0) boolean arg0) {
		@Pc(19) byte local19;
		@Pc(21) byte[][] local21;
		if (GlRenderer.enabled && arg0) {
			local21 = Static19.aByteArrayArray4;
			local19 = 1;
		} else {
			local19 = 4;
			local21 = Static156.aByteArrayArray11;
		}
		for (@Pc(29) int local29 = 0; local29 < local19; local29++) {
			Static107.method2261();
			for (@Pc(36) int local36 = 0; local36 < 13; local36++) {
				for (@Pc(43) int local43 = 0; local43 < 13; local43++) {
					@Pc(56) int local56 = Static187.anIntArrayArrayArray18[local29][local36][local43];
					if (local56 != -1) {
						@Pc(67) int local67 = local56 >> 24 & 0x3;
						if (!arg0 || local67 == 0) {
							@Pc(77) int local77 = local56 >> 1 & 0x3;
							@Pc(83) int local83 = local56 >> 14 & 0x3FF;
							@Pc(89) int local89 = local56 >> 3 & 0x7FF;
							@Pc(99) int local99 = local89 / 8 + (local83 / 8 << 8);
							for (@Pc(101) int local101 = 0; local101 < Static238.anIntArray470.length; local101++) {
								if (Static238.anIntArray470[local101] == local99 && local21[local101] != null) {
									Static217.method3771(Static148.levelCollisionMap, local29, local21[local101], local67, local77, local36 * 8, local43 * 8, arg0, (local83 & 0x7) * 8, (local89 & 0x7) * 8);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(BJII)Z")
	public static boolean method4003(@OriginalArg(1) long arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(12) int local12 = (int) arg0 >> 14 & 0x1F;
		@Pc(24) int local24 = (int) arg0 >> 20 & 0x3;
		@Pc(31) int local31 = (int) (arg0 >>> 32) & Integer.MAX_VALUE;
		if (local12 == 10 || local12 == 11 || local12 == 22) {
			@Pc(46) LocType local46 = Static271.get(local31);
			@Pc(62) int local62;
			@Pc(59) int local59;
			if (local24 == 0 || local24 == 2) {
				local59 = local46.length;
				local62 = local46.width;
			} else {
				local59 = local46.width;
				local62 = local46.length;
			}
			@Pc(73) int local73 = local46.forceapproach;
			if (local24 != 0) {
				local73 = (local73 << local24 & 0xF) + (local73 >> 4 - local24);
			}
			Static102.tryMove(Static173.localPlayer.pathTileZ[0], 0, local59, true, local73, arg2, local62, 0, 2, arg1, Static173.localPlayer.pathTileX[0]);
		} else {
			Static102.tryMove(Static173.localPlayer.pathTileZ[0], local24, 0, true, 0, arg2, 0, local12 + 1, 2, arg1, Static173.localPlayer.pathTileX[0]);
		}
		Static25.y = Static60.mouseClickY;
		Static17.crossCycle = 0;
		Static70.crossMode = 2;
		Static122.x = aClass6.mouseClickX;
		return true;
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(Lclient!i;II)V")
	public static void createClientScriptCheckPacket(@OriginalArg(0) PacketBit buffer) {
		while (true) {
			@Pc(18) ReflectionCheck clientScriptRunner = (ReflectionCheck) Static204.aClass69_113.head();
			if (clientScriptRunner == null) {
				return;
			}
			@Pc(23) boolean bool = false;
			@Pc(25) int i;
			for (i = 0; i < clientScriptRunner.scriptCount; i++) {
				if (clientScriptRunner.valueNodes[i] != null) {
					if (clientScriptRunner.valueNodes[i].status == 2) {
						clientScriptRunner.errorCodes[i] = -5;
					}
					if (clientScriptRunner.valueNodes[i].status == 0) {
						bool = true;
					}
				}
				if (clientScriptRunner.functionNodes[i] != null) {
					if (clientScriptRunner.functionNodes[i].status == 2) {
						clientScriptRunner.errorCodes[i] = -6;
					}
					if (clientScriptRunner.functionNodes[i].status == 0) {
						bool = true;
					}
				}
			}
			if (bool) {
				return;
			}
			buffer.pIsaac1(163);
			buffer.p1(0);
			i = buffer.position;
			buffer.p4(clientScriptRunner.scriptId);
			for (@Pc(121) int j = 0; j < clientScriptRunner.scriptCount; j++) {
				if (clientScriptRunner.errorCodes[j] == 0) {
					try {
						@Pc(151) int opcode = clientScriptRunner.anIntArray139[j];
						@Pc(168) Field field;
						@Pc(195) int fieldValue;
						if (opcode == 0) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							fieldValue = field.getInt(null);
							buffer.p1(0);
							buffer.p4(fieldValue);
						} else if (opcode == 1) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							field.setInt(null, clientScriptRunner.anIntArray137[j]);
							buffer.p1(0);
						} else if (opcode == 2) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							fieldValue = field.getModifiers();
							buffer.p1(0);
							buffer.p4(fieldValue);
						}
						@Pc(234) Method method;
						if (opcode == 3) {
							method = (Method) clientScriptRunner.functionNodes[j].result;
							@Pc(239) byte[][] argumentValueData = clientScriptRunner.argumentValues[j];
							@Pc(243) Object[] objects = new Object[argumentValueData.length];
							for (@Pc(245) int valueIndex = 0; valueIndex < argumentValueData.length; valueIndex++) {
								@Pc(259) ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(argumentValueData[valueIndex]));
								objects[valueIndex] = objectinputstream.readObject();
							}
							@Pc(272) Object object = method.invoke(null, objects);
							if (object == null) {
								buffer.p1(0);
							} else if (object instanceof Number) {
								buffer.p1(1);
								buffer.p8(((Number) object).longValue());
							} else if (object instanceof JString) {
								buffer.p1(2);
								buffer.pjstr((JString) object);
							} else {
								buffer.p1(4);
							}
						} else if (opcode == 4) {
							method = (Method) clientScriptRunner.functionNodes[j].result;
							fieldValue = method.getModifiers();
							buffer.p1(0);
							buffer.p4(fieldValue);
						}
					} catch (@Pc(338) ClassNotFoundException classnotfoundexception) {
						buffer.p1(-10);
					} catch (@Pc(344) InvalidClassException invalidclassexception) {
						buffer.p1(-11);
					} catch (@Pc(350) StreamCorruptedException streamcorruptedexception) {
						buffer.p1(-12);
					} catch (@Pc(356) OptionalDataException optionaldataexception) {
						buffer.p1(-13);
					} catch (@Pc(362) IllegalAccessException illegalaccessexception) {
						buffer.p1(-14);
					} catch (@Pc(368) IllegalArgumentException illegalargumentexception) {
						buffer.p1(-15);
					} catch (@Pc(374) InvocationTargetException invocationtargetexception) {
						buffer.p1(-16);
					} catch (@Pc(380) SecurityException securityexception) {
						buffer.p1(-17);
					} catch (@Pc(386) IOException ioexception) {
						buffer.p1(-18);
					} catch (@Pc(392) NullPointerException nullpointerexception) {
						buffer.p1(-19);
					} catch (@Pc(398) Exception exception) {
						buffer.p1(-20);
					} catch (@Pc(404) Throwable throwable) {
						buffer.p1(-21);
					}
				} else {
					buffer.p1(clientScriptRunner.errorCodes[j]);
				}
			}
			buffer.pCrc32(i);
			buffer.p1len(buffer.position - i);
			clientScriptRunner.unlink();
		}
	}
}
