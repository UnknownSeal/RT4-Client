package com.jagex.runetek4;

import com.jagex.runetek4.util.SignLink;
import com.jagex.runetek4.util.ThreadUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.*;
import java.net.URL;

@OriginalClass("client!ld")
public final class TracingException extends RuntimeException {

	@OriginalMember(owner = "client!fh", name = "cb", descriptor = "Lsignlink!ll;")
	public static SignLink signLink;
	@OriginalMember(owner = "client!ld", name = "e", descriptor = "Ljava/lang/String;")
	public String aString3;

	@OriginalMember(owner = "client!ld", name = "f", descriptor = "Ljava/lang/Throwable;")
	public Throwable aThrowable1;

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
			message = replace(":", "%3a", message);
			message = replace("@", "%40", message);
			message = replace("&", "%26", message);
			message = replace("#", "%23", message);
			if (signLink.applet == null) {
				return;
			}
			@Pc(109) PrivilegedRequest privilegedRequest = signLink.openUrlStream(new URL(signLink.applet.getCodeBase(), "clienterror.ws?c=" + GameShell.clientBuild + "&u=" + Static101.aLong98 + "&v1=" + SignLink.javaVendor + "&v2=" + SignLink.javaVersion + "&e=" + message));
			while (privilegedRequest.status == 0) {
				ThreadUtils.sleep(1L);
			}
			if (privilegedRequest.status == 1) {
				@Pc(128) DataInputStream dataInputStream = (DataInputStream) privilegedRequest.result;
				dataInputStream.read();
				dataInputStream.close();
			}
		} catch (@Pc(135) Exception exception) {
		}
	}

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(ILjava/lang/Throwable;)Ljava/lang/String;")
	public static String toString(@OriginalArg(1) Throwable arg0) throws IOException {
		@Pc(24) String local24;
		if (arg0 instanceof TracingException) {
			@Pc(11) TracingException local11 = (TracingException) arg0;
			local24 = local11.aString3 + " | ";
			arg0 = local11.aThrowable1;
		} else {
			local24 = "";
		}
		@Pc(32) StringWriter local32 = new StringWriter();
		@Pc(37) PrintWriter local37 = new PrintWriter(local32);
		arg0.printStackTrace(local37);
		local37.close();
		@Pc(45) String local45 = local32.toString();
		@Pc(53) BufferedReader local53 = new BufferedReader(new StringReader(local45));
		@Pc(56) String local56 = local53.readLine();
		while (true) {
			@Pc(59) String local59 = local53.readLine();
			if (local59 == null) {
				return local24 + "| " + local56;
			}
			@Pc(65) int local65 = local59.indexOf(40);
			@Pc(72) int local72 = local59.indexOf(41, local65 + 1);
			@Pc(79) String local79;
			if (local65 == -1) {
				local79 = local59;
			} else {
				local79 = local59.substring(0, local65);
			}
			local79 = local79.trim();
			local79 = local79.substring(local79.lastIndexOf(32) + 1);
			local79 = local79.substring(local79.lastIndexOf(9) + 1);
			local24 = local24 + local79;
			if (local65 != -1 && local72 != -1) {
				@Pc(126) int local126 = local59.indexOf(".java:", local65);
				if (local126 >= 0) {
					local24 = local24 + local59.substring(local126 + 5, local72);
				}
			}
			local24 = local24 + ' ';
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
