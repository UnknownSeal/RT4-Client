package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@OriginalClass("runetek4.client!ed")
public final class ReflectionCheck extends Node {

	@OriginalMember(owner = "runetek4.client!qi", name = "u", descriptor = "Lclient!ih;")
	public static LinkedList queue = new LinkedList();

	@OriginalMember(owner = "runetek4.client!ed", name = "p", descriptor = "I")
	public int size;

	@OriginalMember(owner = "runetek4.client!ed", name = "u", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] functionNodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "v", descriptor = "[I")
	public int[] fieldValues;

	@OriginalMember(owner = "runetek4.client!ed", name = "w", descriptor = "[I")
	public int[] errorCodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "y", descriptor = "[I")
	public int[] types;

	@OriginalMember(owner = "runetek4.client!ed", name = "B", descriptor = "[[[B")
	public byte[][][] methodArguments;

	@OriginalMember(owner = "runetek4.client!ed", name = "C", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] fieldRequests;

	@OriginalMember(owner = "runetek4.client!ed", name = "F", descriptor = "I")
	public int id;

    @OriginalMember(owner = "runetek4.client!j", name = "c", descriptor = "(I)V")
    public static void clear() {
        queue = new LinkedList();
    }

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(Lclient!i;II)V")
	public static void createClientScriptCheckPacket(@OriginalArg(0) PacketBit buffer) {
		while (true) {
			@Pc(18) ReflectionCheck reflectionCheck = (ReflectionCheck) queue.head();
			if (reflectionCheck == null) {
				return;
			}
			@Pc(23) boolean bool = false;
			@Pc(25) int i;
			for (i = 0; i < reflectionCheck.size; i++) {
				if (reflectionCheck.fieldRequests[i] != null) {
					if (reflectionCheck.fieldRequests[i].status == 2) {
						reflectionCheck.errorCodes[i] = -5;
					}
					if (reflectionCheck.fieldRequests[i].status == 0) {
						bool = true;
					}
				}
				if (reflectionCheck.functionNodes[i] != null) {
					if (reflectionCheck.functionNodes[i].status == 2) {
						reflectionCheck.errorCodes[i] = -6;
					}
					if (reflectionCheck.functionNodes[i].status == 0) {
						bool = true;
					}
				}
			}
			if (bool) {
				return;
			}
			buffer.pIsaac1(163);
			buffer.p1(0);
			i = buffer.offset;
			buffer.p4(reflectionCheck.id);
			for (@Pc(121) int j = 0; j < reflectionCheck.size; j++) {
				if (reflectionCheck.errorCodes[j] == 0) {
					try {
						@Pc(151) int opcode = reflectionCheck.types[j];
						@Pc(168) Field field;
						@Pc(195) int fieldValue;
						if (opcode == 0) {
							field = (Field) reflectionCheck.fieldRequests[j].result;
							fieldValue = field.getInt(null);
							buffer.p1(0);
							buffer.p4(fieldValue);
						} else if (opcode == 1) {
							field = (Field) reflectionCheck.fieldRequests[j].result;
							field.setInt(null, reflectionCheck.fieldValues[j]);
							buffer.p1(0);
						} else if (opcode == 2) {
							field = (Field) reflectionCheck.fieldRequests[j].result;
							fieldValue = field.getModifiers();
							buffer.p1(0);
							buffer.p4(fieldValue);
						}
						@Pc(234) Method method;
						if (opcode == 3) {
							method = (Method) reflectionCheck.functionNodes[j].result;
							@Pc(239) byte[][] argumentValueData = reflectionCheck.methodArguments[j];
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
							method = (Method) reflectionCheck.functionNodes[j].result;
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
					buffer.p1(reflectionCheck.errorCodes[j]);
				}
			}
			buffer.pCrc32(i);
			buffer.p1len(buffer.offset - i);
			reflectionCheck.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(Lsignlink!ll;Lclient!wa;IB)V")
	public static void push(@OriginalArg(0) SignLink arg0, @OriginalArg(1) Packet arg1, @OriginalArg(2) int arg2) {
		@Pc(17) ReflectionCheck check = new ReflectionCheck();
		check.size = arg1.g1();
		check.id = arg1.g4();
		check.functionNodes = new PrivilegedRequest[check.size];
		check.errorCodes = new int[check.size];
		check.methodArguments = new byte[check.size][][];
		check.fieldRequests = new PrivilegedRequest[check.size];
		check.types = new int[check.size];
		check.fieldValues = new int[check.size];
		for (@Pc(59) int local59 = 0; local59 < check.size; local59++) {
			try {
				@Pc(71) int local71 = arg1.g1();
				@Pc(93) String local93;
				@Pc(104) String local104;
				@Pc(95) int local95;
				if (local71 == 0 || local71 == 1 || local71 == 2) {
					local93 = new String(arg1.gjstr().method3148());
					local95 = 0;
					local104 = new String(arg1.gjstr().method3148());
					if (local71 == 1) {
						local95 = arg1.g4();
					}
					check.types[local59] = local71;
					check.fieldValues[local59] = local95;
					check.fieldRequests[local59] = arg0.getDeclaredField(local104, classForName(local93));
				} else if (local71 == 3 || local71 == 4) {
					local93 = new String(arg1.gjstr().method3148());
					local104 = new String(arg1.gjstr().method3148());
					local95 = arg1.g1();
					@Pc(171) String[] local171 = new String[local95];
					for (@Pc(173) int local173 = 0; local173 < local95; local173++) {
						local171[local173] = new String(arg1.gjstr().method3148());
					}
					@Pc(193) byte[][] local193 = new byte[local95][];
					@Pc(210) int local210;
					if (local71 == 3) {
						for (@Pc(199) int local199 = 0; local199 < local95; local199++) {
							local210 = arg1.g4();
							local193[local199] = new byte[local210];
							arg1.gdata(local210, local193[local199]);
						}
					}
					check.types[local59] = local71;
					@Pc(234) Class[] local234 = new Class[local95];
					for (local210 = 0; local210 < local95; local210++) {
						local234[local210] = classForName(local171[local210]);
					}
					check.functionNodes[local59] = arg0.getDeclaredMethod(classForName(local93), local234, local104);
					check.methodArguments[local59] = local193;
				}
			} catch (@Pc(269) ClassNotFoundException local269) {
				check.errorCodes[local59] = -1;
			} catch (@Pc(276) SecurityException local276) {
				check.errorCodes[local59] = -2;
			} catch (@Pc(283) NullPointerException local283) {
				check.errorCodes[local59] = -3;
			} catch (@Pc(290) Exception local290) {
				check.errorCodes[local59] = -4;
			} catch (@Pc(297) Throwable local297) {
				check.errorCodes[local59] = -5;
			}
		}
		queue.addTail(check);
	}

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(ILjava/lang/String;)Ljava/lang/Class;")
	public static Class classForName(@OriginalArg(1) String arg0) throws ClassNotFoundException {
		if (arg0.equals("B")) {
			return Byte.TYPE;
		} else if (arg0.equals("I")) {
			return Integer.TYPE;
		} else if (arg0.equals("S")) {
			return Short.TYPE;
		} else if (arg0.equals("J")) {
			return Long.TYPE;
		} else if (arg0.equals("Z")) {
			return Boolean.TYPE;
		} else if (arg0.equals("F")) {
			return Float.TYPE;
		} else if (arg0.equals("D")) {
			return Double.TYPE;
		} else if (arg0.equals("C")) {
			return Character.TYPE;
		} else {
			return Class.forName(arg0);
		}
	}
}
