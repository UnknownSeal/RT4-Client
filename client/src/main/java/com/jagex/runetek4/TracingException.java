package com.jagex.runetek4;

import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.*;

@OriginalClass("client!ld")
public final class TracingException extends RuntimeException {

	@OriginalMember(owner = "client!fh", name = "cb", descriptor = "Lsignlink!ll;")
	public static SignLink signLink;

	@OriginalMember(owner = "client!ld", name = "e", descriptor = "Ljava/lang/String;")
	public String message;

	@OriginalMember(owner = "client!ld", name = "f", descriptor = "Ljava/lang/Throwable;")
	public Throwable cause;

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/Throwable;B)V")
	public static void report(@OriginalArg(0) String suffix, @OriginalArg(1) Throwable throwable) {
		try {
			@Pc(13) String message = "";
			if (throwable != null) {
				message = toString(throwable);
			}
			if (suffix != null) {
				if (throwable != null) {
					message = message + " | ";
				}
				message = message + suffix;
			}
			print(message);
		} catch (@Pc(135) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(ILjava/lang/Throwable;)Ljava/lang/String;")
	public static String toString(@OriginalArg(1) Throwable exception) throws IOException {
		@Pc(24) String message;
		if (exception instanceof TracingException) {
			@Pc(11) TracingException tracingException = (TracingException) exception;
			message = tracingException.message + " | ";
			exception = tracingException.cause;
		} else {
			message = "";
		}
		@Pc(32) StringWriter stringWriter = new StringWriter();
		@Pc(37) PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		printWriter.close();
		@Pc(45) String stackTrace = stringWriter.toString();
		@Pc(53) BufferedReader reader = new BufferedReader(new StringReader(stackTrace));
		@Pc(56) String firstLine = reader.readLine();
		while (true) {
			@Pc(59) String line = reader.readLine();
			if (line == null) {
				return message + "| " + firstLine;
			}
			@Pc(65) int openingBracketIndex = line.indexOf(40);
			@Pc(72) int closingBracketIndex = line.indexOf(41, openingBracketIndex + 1);
			@Pc(79) String classAndMethodName;
			if (openingBracketIndex == -1) {
				classAndMethodName = line;
			} else {
				classAndMethodName = line.substring(0, openingBracketIndex);
			}
			classAndMethodName = classAndMethodName.trim();
			classAndMethodName = classAndMethodName.substring(classAndMethodName.lastIndexOf(32) + 1);
			classAndMethodName = classAndMethodName.substring(classAndMethodName.lastIndexOf(9) + 1);
			message = message + classAndMethodName;
			if (openingBracketIndex != -1 && closingBracketIndex != -1) {
				@Pc(126) int javaSuffixIndex = line.indexOf(".java:", openingBracketIndex);
				if (javaSuffixIndex >= 0) {
					message = message + line.substring(javaSuffixIndex + 5, closingBracketIndex);
				}
			}
			message = message + ' ';
		}
	}

	@OriginalMember(owner = "client!af", name = "a", descriptor = "(ILjava/lang/String;)V")
	public static void print(@OriginalArg(1) String message) {
		System.out.println("Error: " + replace("%0a", "\n", message));
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;")
	public static String replace(@OriginalArg(0) String target, @OriginalArg(1) String replacement, @OriginalArg(3) String s) {
		for (@Pc(5) int i = s.indexOf(target); i != -1; i = s.indexOf(target, i + replacement.length())) {
			s = s.substring(0, i) + replacement + s.substring(target.length() + i);
		}
		return s;
	}
}
