package com.jagex.runetek4.network.security;

import com.jagex.runetek4.core.datastruct.LinkList;
import com.jagex.runetek4.PrivilegedRequest;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.util.system.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Anti botting system
 * Allows the server to inspect client code using reflection
 */

@OriginalClass("runetek4.client!ed")
public final class ReflectionCheck extends Node {

	@OriginalMember(owner = "runetek4.client!qi", name = "u", descriptor = "Lclient!ih;")
	public static LinkList queue = new LinkList(); // Queue of pending reflection check requests from the server

	@OriginalMember(owner = "runetek4.client!ed", name = "p", descriptor = "I")
	public int size; // Number of reflection operations in this check batch

	@OriginalMember(owner = "runetek4.client!ed", name = "u", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] functionNodes;

	@OriginalMember(owner = "runetek4.client!ed", name = "v", descriptor = "[I")
	public int[] fieldValues;

	@OriginalMember(owner = "runetek4.client!ed", name = "w", descriptor = "[I")
	public int[] errorCodes;

	/**
	 * Error codes for each reflection operation
	 * 0 = success
	 * -1 = ClassNotFoundException
	 * -2 = SecurityException
	 * -3 = NullPointerException
	 * -4 = Generic exception
	 * -5 = Throwable / Request failed
	 * -6 = Request error
	 * -10 to -21 = various exceptions during execution
	 */

	@OriginalMember(owner = "runetek4.client!ed", name = "y", descriptor = "[I")
	public int[] types;

	/**
	 * 0 = Get static field value
	 * 1 = Set static field value
	 * 2 = Get field modifiers
	 * 3 = Invoke static method
	 * 4 = Get method modifiers
	 */

	@OriginalMember(owner = "runetek4.client!ed", name = "B", descriptor = "[[[B")
	public byte[][][] methodArguments;

	@OriginalMember(owner = "runetek4.client!ed", name = "C", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] fieldRequests;

	@OriginalMember(owner = "runetek4.client!ed", name = "F", descriptor = "I")
	public int id; // Unique ID for this reflection check batch

    @OriginalMember(owner = "runetek4.client!j", name = "c", descriptor = "(I)V")
    public static void clear() {
        queue = new LinkList();
    }

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(Lclient!i;II)V")
	public static void createClientScriptCheckPacket(@OriginalArg(0) PacketBit buffer) {
		while (true) {
			@Pc(18) ReflectionCheck reflectionCheck = (ReflectionCheck) queue.head();
			if (reflectionCheck == null) {
				return;
			}
			@Pc(23) boolean hasPendingRequests = false;
			@Pc(25) int checkIndex;
			for (checkIndex = 0; checkIndex < reflectionCheck.size; checkIndex++) {
				if (reflectionCheck.fieldRequests[checkIndex] != null) {
					if (reflectionCheck.fieldRequests[checkIndex].status == 2) {
						reflectionCheck.errorCodes[checkIndex] = -5;
					}
					if (reflectionCheck.fieldRequests[checkIndex].status == 0) {
						hasPendingRequests = true;
					}
				}
				if (reflectionCheck.functionNodes[checkIndex] != null) {
					if (reflectionCheck.functionNodes[checkIndex].status == 2) {
						reflectionCheck.errorCodes[checkIndex] = -6;
					}
					if (reflectionCheck.functionNodes[checkIndex].status == 0) {
						hasPendingRequests = true;
					}
				}
			}
			if (hasPendingRequests) {
				return;
			}
			buffer.pIsaac1(163); // Packet opcode for reflection check response
			buffer.p1(0);
			checkIndex = buffer.offset;
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
			buffer.pCrc32(checkIndex);
			buffer.p1len(buffer.offset - checkIndex);
			reflectionCheck.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(Lsignlink!ll;Lclient!wa;IB)V")
	public static void push(@OriginalArg(0) SignLink arg0, @OriginalArg(1) Packet packet, @OriginalArg(2) int arg2) {
		@Pc(17) ReflectionCheck check = new ReflectionCheck();
		check.size = packet.g1();
		check.id = packet.g4();
		check.functionNodes = new PrivilegedRequest[check.size];
		check.errorCodes = new int[check.size];
		check.methodArguments = new byte[check.size][][];
		check.fieldRequests = new PrivilegedRequest[check.size];
		check.types = new int[check.size];
		check.fieldValues = new int[check.size];
		for (@Pc(59) int i = 0; i < check.size; i++) {
			try {
				@Pc(71) int opcode = packet.g1();
				@Pc(93) String className;
				@Pc(104) String memberName;
				@Pc(95) int valueOrArgCount;
				if (opcode == 0 || opcode == 1 || opcode == 2) {
					className = new String(packet.gjstr().method3148());
					valueOrArgCount = 0;
					memberName = new String(packet.gjstr().method3148());
					if (opcode == 1) {
						valueOrArgCount = packet.g4();
					}
					check.types[i] = opcode;
					check.fieldValues[i] = valueOrArgCount;
					check.fieldRequests[i] = arg0.getDeclaredField(memberName, classForName(className));
				} else if (opcode == 3 || opcode == 4) {
					className = new String(packet.gjstr().method3148());
					memberName = new String(packet.gjstr().method3148());
					valueOrArgCount = packet.g1();
					@Pc(171) String[] parameterTypeNames = new String[valueOrArgCount];
					for (@Pc(173) int local173 = 0; local173 < valueOrArgCount; local173++) {
						parameterTypeNames[local173] = new String(packet.gjstr().method3148());
					}
					@Pc(193) byte[][] argumentData = new byte[valueOrArgCount][];
					@Pc(210) int dataLength;
					if (opcode == 3) {
						for (@Pc(199) int argIndex = 0; argIndex < valueOrArgCount; argIndex++) {
							dataLength = packet.g4();
							argumentData[argIndex] = new byte[dataLength];
							packet.gdata(dataLength, argumentData[argIndex]);
						}
					}
					check.types[i] = opcode;
					@Pc(234) Class[] local234 = new Class[valueOrArgCount];
					for (dataLength = 0; dataLength < valueOrArgCount; dataLength++) {
						local234[dataLength] = classForName(parameterTypeNames[dataLength]);
					}
					check.functionNodes[i] = arg0.getDeclaredMethod(classForName(className), local234, memberName);
					check.methodArguments[i] = argumentData;
				}
			} catch (@Pc(269) ClassNotFoundException local269) {
				check.errorCodes[i] = -1;
			} catch (@Pc(276) SecurityException local276) {
				check.errorCodes[i] = -2;
			} catch (@Pc(283) NullPointerException local283) {
				check.errorCodes[i] = -3;
			} catch (@Pc(290) Exception local290) {
				check.errorCodes[i] = -4;
			} catch (@Pc(297) Throwable local297) {
				check.errorCodes[i] = -5;
			}
		}
		queue.push(check);
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
