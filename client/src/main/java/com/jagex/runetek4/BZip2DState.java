package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!bb")
public final class BZip2DState {

	@OriginalMember(owner = "runetek4.client!bb", name = "e", descriptor = "B")
	public byte stateOutCh;

	@OriginalMember(owner = "runetek4.client!bb", name = "k", descriptor = "[B")
	public byte[] strmNextOut;

	@OriginalMember(owner = "runetek4.client!bb", name = "o", descriptor = "I")
	public int saveNblock;

	@OriginalMember(owner = "runetek4.client!bb", name = "u", descriptor = "I")
	public int strmAvailOut;

	@OriginalMember(owner = "runetek4.client!bb", name = "v", descriptor = "[B")
	public byte[] strmNextIn;

	@OriginalMember(owner = "runetek4.client!bb", name = "w", descriptor = "I")
	public int bsLive;

	@OriginalMember(owner = "runetek4.client!bb", name = "x", descriptor = "I")
	public int k0;

	@OriginalMember(owner = "runetek4.client!bb", name = "z", descriptor = "I")
	public int nblockused;

	@OriginalMember(owner = "runetek4.client!bb", name = "B", descriptor = "I")
	public int tPos;

	@OriginalMember(owner = "runetek4.client!bb", name = "D", descriptor = "I")
	public int origPtr;

	@OriginalMember(owner = "runetek4.client!bb", name = "F", descriptor = "I")
	public int nInUse;

	@OriginalMember(owner = "runetek4.client!bb", name = "I", descriptor = "I")
	public int bsBuff;

	@OriginalMember(owner = "runetek4.client!bb", name = "J", descriptor = "I")
	public int strmTotalOutLo32;

	@OriginalMember(owner = "runetek4.client!bb", name = "K", descriptor = "I")
	public int blockSize100k;

	@OriginalMember(owner = "runetek4.client!bb", name = "L", descriptor = "I")
	public int stateOutLen;

	@OriginalMember(owner = "runetek4.client!bb", name = "P", descriptor = "I")
	public int strmTotalInLo32;

	@OriginalMember(owner = "runetek4.client!bb", name = "b", descriptor = "[B")
	public final byte[] mtfa = new byte[4096];

	@OriginalMember(owner = "runetek4.client!bb", name = "f", descriptor = "[Z")
	public final boolean[] inUse = new boolean[256];

	@OriginalMember(owner = "runetek4.client!bb", name = "d", descriptor = "[[I")
	public final int[][] perm = new int[6][258];

	@OriginalMember(owner = "runetek4.client!bb", name = "h", descriptor = "[B")
	public final byte[] seqToUnseq = new byte[256];

	@OriginalMember(owner = "runetek4.client!bb", name = "a", descriptor = "[[I")
	public final int[][] base = new int[6][258];

	@OriginalMember(owner = "runetek4.client!bb", name = "l", descriptor = "I")
	public int strmNextOutPtr = 0;

	@OriginalMember(owner = "runetek4.client!bb", name = "i", descriptor = "[[I")
	public final int[][] limit = new int[6][258];

	@OriginalMember(owner = "runetek4.client!bb", name = "j", descriptor = "I")
	public int strmNextInPtr = 0;

	@OriginalMember(owner = "runetek4.client!bb", name = "C", descriptor = "[I")
	public final int[] unzftab = new int[256];

	@OriginalMember(owner = "runetek4.client!bb", name = "p", descriptor = "[I")
	public final int[] cftab = new int[257];

	@OriginalMember(owner = "runetek4.client!bb", name = "y", descriptor = "[B")
	public final byte[] selector = new byte[18002];

	@OriginalMember(owner = "runetek4.client!bb", name = "r", descriptor = "[Z")
	public final boolean[] inUse16 = new boolean[16];

	@OriginalMember(owner = "runetek4.client!bb", name = "q", descriptor = "[I")
	public final int[] mtfbase = new int[16];

	@OriginalMember(owner = "runetek4.client!bb", name = "H", descriptor = "[B")
	public final byte[] selectorMtf = new byte[18002];

	@OriginalMember(owner = "runetek4.client!bb", name = "G", descriptor = "[I")
	public final int[] minLens = new int[6];

	@OriginalMember(owner = "runetek4.client!bb", name = "O", descriptor = "[[B")
	public final byte[][] len = new byte[6][258];
}
