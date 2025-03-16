package com.jagex.runetek4.cache.bzip;

import com.jagex.runetek4.Bzip2DState;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Bzip2Decompressor {

	@OriginalMember(owner = "runetek4.client!oc", name = "a", descriptor = "Lclient!bb;")
	private static final Bzip2DState state = new Bzip2DState();
	@OriginalMember(owner = "runetek4.client!s", name = "a", descriptor = "[I")
	public static int[] tt;

	@OriginalMember(owner = "runetek4.client!oc", name = "a", descriptor = "(ILclient!bb;)I")
	private static int getBits(@OriginalArg(0) int arg0, @OriginalArg(1) Bzip2DState arg1) {
		while (arg1.bsLive < arg0) {
			arg1.bsBuff = arg1.bsBuff << 8 | arg1.strmNextIn[arg1.strmNextInPtr] & 0xFF;
			arg1.bsLive += 8;
			arg1.strmNextInPtr++;
			arg1.strmTotalInLo32++;
			if (arg1.strmTotalInLo32 == 0) {
			}
		}
		@Pc(17) int local17 = arg1.bsBuff >> arg1.bsLive - arg0 & (0x1 << arg0) - 1;
		arg1.bsLive -= arg0;
		return local17;
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "a", descriptor = "(Lclient!bb;)V")
	private static void unRleObufToOutputFast(@OriginalArg(0) Bzip2DState arg0) {
		@Pc(2) byte local2 = arg0.stateOutCh;
		@Pc(5) int local5 = arg0.stateOutLen;
		@Pc(8) int local8 = arg0.nblockused;
		@Pc(11) int local11 = arg0.k0;
		@Pc(13) int[] local13 = tt;
		@Pc(16) int local16 = arg0.tPos;
		@Pc(19) byte[] local19 = arg0.strmNextOut;
		@Pc(22) int local22 = arg0.strmNextOutPtr;
		@Pc(25) int local25 = arg0.strmAvailOut;
		@Pc(27) int local27 = local25;
		@Pc(32) int local32 = arg0.saveNblock + 1;
		returnNotr: while (true) {
			if (local5 > 0) {
				while (true) {
					if (local25 == 0) {
						break returnNotr;
					}
					if (local5 == 1) {
						if (local25 == 0) {
							local5 = 1;
							break returnNotr;
						}
						local19[local22] = local2;
						local22++;
						local25--;
						break;
					}
					local19[local22] = local2;
					local5--;
					local22++;
					local25--;
				}
			}
			@Pc(62) boolean local62 = true;
			@Pc(84) byte local84;
			while (local62) {
				local62 = false;
				if (local8 == local32) {
					local5 = 0;
					break returnNotr;
				}
				local2 = (byte) local11;
				local16 = local13[local16];
				local84 = (byte) (local16 & 0xFF);
				local16 >>= 0x8;
				local8++;
				if (local84 != local11) {
					local11 = local84;
					if (local25 == 0) {
						local5 = 1;
						break returnNotr;
					}
					local19[local22] = local2;
					local22++;
					local25--;
					local62 = true;
				} else if (local8 == local32) {
					if (local25 == 0) {
						local5 = 1;
						break returnNotr;
					}
					local19[local22] = local2;
					local22++;
					local25--;
					local62 = true;
				}
			}
			local5 = 2;
			local16 = local13[local16];
			local84 = (byte) (local16 & 0xFF);
			local16 >>= 0x8;
			local8++;
			if (local8 != local32) {
				if (local84 == local11) {
					local5 = 3;
					local16 = local13[local16];
					local84 = (byte) (local16 & 0xFF);
					local16 >>= 0x8;
					local8++;
					if (local8 != local32) {
						if (local84 == local11) {
							local16 = local13[local16];
							local84 = (byte) (local16 & 0xFF);
							local16 >>= 0x8;
							local8++;
							local5 = (local84 & 0xFF) + 4;
							local16 = local13[local16];
							local11 = (byte) (local16 & 0xFF);
							local16 >>= 0x8;
							local8++;
						} else {
							local11 = local84;
						}
					}
				} else {
					local11 = local84;
				}
			}
		}
		@Pc(215) int local215 = arg0.strmTotalOutLo32;
		arg0.strmTotalOutLo32 += local27 - local25;
		if (arg0.strmTotalOutLo32 < local215) {
		}
		arg0.stateOutCh = local2;
		arg0.stateOutLen = local5;
		arg0.nblockused = local8;
		arg0.k0 = local11;
		tt = local13;
		arg0.tPos = local16;
		arg0.strmNextOut = local19;
		arg0.strmNextOutPtr = local22;
		arg0.strmAvailOut = local25;
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "a", descriptor = "([I[I[I[BIII)V")
	private static void hbCreateDecodeTables(@OriginalArg(0) int[] limit, @OriginalArg(1) int[] base, @OriginalArg(2) int[] arg2, @OriginalArg(3) byte[] arg3, @OriginalArg(4) int minLen, @OriginalArg(5) int maxLen, @OriginalArg(6) int arg6) {
		@Pc(1) int pp = 0;
		@Pc(3) int i;
		for (i = minLen; i <= maxLen; i++) {
			for (@Pc(8) int j = 0; j < arg6; j++) {
				if (arg3[j] == i) {
					arg2[pp] = j;
					pp++;
				}
			}
		}
		for (i = 0; i < 23; i++) {
			base[i] = 0;
		}
		for (i = 0; i < arg6; i++) {
			base[arg3[i] + 1]++;
		}
		for (i = 1; i < 23; i++) {
			base[i] += base[i - 1];
		}
		for (i = 0; i < 23; i++) {
			limit[i] = 0;
		}
		@Pc(85) int vec = 0;
		for (i = minLen; i <= maxLen; i++) {
			vec += base[i + 1] - base[i];
			limit[i] = vec - 1;
			vec <<= 0x1;
		}
		for (i = minLen + 1; i <= maxLen; i++) {
			base[i] = (limit[i - 1] + 1 << 1) - base[i];
		}
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "b", descriptor = "(Lclient!bb;)V")
	private static void makeMapsD(@OriginalArg(0) Bzip2DState arg0) {
		arg0.nInUse = 0;
		for (@Pc(4) int local4 = 0; local4 < 256; local4++) {
			if (arg0.inUse[local4]) {
				arg0.seqToUnseq[arg0.nInUse] = (byte) local4;
				arg0.nInUse++;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "c", descriptor = "(Lclient!bb;)B")
	private static byte getUchar(@OriginalArg(0) Bzip2DState arg0) {
		return (byte) getBits(8, arg0);
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "d", descriptor = "(Lclient!bb;)V")
	private static void decompress(@OriginalArg(0) Bzip2DState s) {
		s.blockSize100k = 1;
		if (tt == null) {
			tt = new int[s.blockSize100k * 100000];
		}
		@Pc(56) boolean reading = true;
		while (true) {
			while (reading) {
				@Pc(61) byte uc = getUchar(s);
				if (uc == 23) {
					return;
				}
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getUchar(s);
				uc = getBit(s);
				if (uc != 0) {
				}
				s.origPtr = 0;
				uc = getUchar(s);
				s.origPtr = s.origPtr << 8 | uc & 0xFF;
				uc = getUchar(s);
				s.origPtr = s.origPtr << 8 | uc & 0xFF;
				uc = getUchar(s);
				s.origPtr = s.origPtr << 8 | uc & 0xFF;
				@Pc(141) int i;
				for (i = 0; i < 16; i++) {
					uc = getBit(s);
					if (uc == 1) {
						s.inUse16[i] = true;
					} else {
						s.inUse16[i] = false;
					}
				}
				for (i = 0; i < 256; i++) {
					s.inUse[i] = false;
				}
				@Pc(187) int j;
				for (i = 0; i < 16; i++) {
					if (s.inUse16[i]) {
						for (j = 0; j < 16; j++) {
							uc = getBit(s);
							if (uc == 1) {
								s.inUse[i * 16 + j] = true;
							}
						}
					}
				}
				makeMapsD(s);
				@Pc(216) int alphaSize = s.nInUse + 2;
				@Pc(220) int nGroups = getBits(3, s);
				@Pc(224) int nSelectors = getBits(15, s);
				for (i = 0; i < nSelectors; i++) {
					j = 0;
					while (true) {
						uc = getBit(s);
						if (uc == 0) {
							s.selectorMtf[i] = (byte) j;
							break;
						}
						j++;
					}
				}
				@Pc(250) byte[] pos = new byte[6];
				@Pc(252) byte v = 0;
				while (v < nGroups) {
					pos[v] = v++;
				}
				for (i = 0; i < nSelectors; i++) {
					v = s.selectorMtf[i];
					@Pc(279) byte tmp = pos[v];
					while (v > 0) {
						pos[v] = pos[v - 1];
						v--;
					}
					pos[0] = tmp;
					s.selector[i] = tmp;
				}
				@Pc(308) int t;
				for (t = 0; t < nGroups; t++) {
					@Pc(315) int curr = getBits(5, s);
					for (i = 0; i < alphaSize; i++) {
						while (true) {
							uc = getBit(s);
							if (uc == 0) {
								s.len[t][i] = (byte) curr;
								break;
							}
							uc = getBit(s);
							if (uc == 0) {
								curr++;
							} else {
								curr--;
							}
						}
					}
				}
				for (t = 0; t < nGroups; t++) {
					@Pc(354) byte minLen = 32;
					@Pc(356) byte maxLen = 0;
					for (i = 0; i < alphaSize; i++) {
						if (s.len[t][i] > maxLen) {
							maxLen = s.len[t][i];
						}
						if (s.len[t][i] < minLen) {
							minLen = s.len[t][i];
						}
					}
					hbCreateDecodeTables(s.limit[t], s.base[t], s.perm[t], s.len[t], minLen, maxLen, alphaSize);
					s.minLens[t] = minLen;
				}
				@Pc(425) int EOB = s.nInUse + 1;
				@Pc(427) byte groupNo = -1;
				for (i = 0; i <= 255; i++) {
					s.unzftab[i] = 0;
				}
				@Pc(443) int kk = 4095;
				@Pc(445) int ii;
				@Pc(449) int jj;
				for (ii = 15; ii >= 0; ii--) {
					for (jj = 15; jj >= 0; jj--) {
						s.mtfa[kk] = (byte) (ii * 16 + jj);
						kk--;
					}
					s.mtfbase[ii] = kk + 1;
				}
				@Pc(475) int nblock = 0;
				@Pc(478) int groupNo1 = groupNo + 1;
				@Pc(480) byte local480 = 50;
				@Pc(485) byte gSel = s.selector[0];
				@Pc(490) int gMinlen = s.minLens[gSel];
				@Pc(495) int[] gLimit = s.limit[gSel];
				@Pc(500) int[] gPerm = s.perm[gSel];
				@Pc(505) int[] gBase = s.base[gSel];
				@Pc(506) int gPos = local480 - 1;
				@Pc(508) int zn = gMinlen;
				@Pc(512) int zvec;
				@Pc(522) byte zj;
				for (zvec = getBits(gMinlen, s); zvec > gLimit[zn]; zvec = zvec << 1 | zj) {
					zn++;
					zj = getBit(s);
				}
				@Pc(537) int nextSym = gPerm[zvec - gBase[zn]];
				while (true) {
					while (nextSym != EOB) {
						if (nextSym == 0 || nextSym == 1) {
							@Pc(548) int es = -1;
							@Pc(550) int N = 1;
							do {
								if (nextSym == 0) {
									es += N;
								} else if (nextSym == 1) {
									es += N * 2;
								}
								N *= 2;
								if (gPos == 0) {
									groupNo1++;
									gPos = 50;
									gSel = s.selector[groupNo1];
									gMinlen = s.minLens[gSel];
									gLimit = s.limit[gSel];
									gPerm = s.perm[gSel];
									gBase = s.base[gSel];
								}
								gPos--;
								zn = gMinlen;
								for (zvec = getBits(gMinlen, s); zvec > gLimit[zn]; zvec = zvec << 1 | zj) {
									zn++;
									zj = getBit(s);
								}
								nextSym = gPerm[zvec - gBase[zn]];
							} while (nextSym == 0 || nextSym == 1);
							es++;
							uc = s.seqToUnseq[s.mtfa[s.mtfbase[0]] & 0xFF];
							s.unzftab[uc & 0xFF] += es;
							while (es > 0) {
								tt[nblock] = uc & 0xFF;
								nblock++;
								es--;
							}
						} else {
							@Pc(678) int nn = nextSym - 1;
							@Pc(686) int pp;
							if (nn < 16) {
								pp = s.mtfbase[0];
								uc = s.mtfa[pp + nn];
								while (nn > 3) {
									@Pc(700) int z = pp + nn;
									s.mtfa[z] = s.mtfa[z - 1];
									s.mtfa[z - 1] = s.mtfa[z - 2];
									s.mtfa[z - 2] = s.mtfa[z - 3];
									s.mtfa[z - 3] = s.mtfa[z - 4];
									nn -= 4;
								}
								while (nn > 0) {
									s.mtfa[pp + nn] = s.mtfa[pp + nn - 1];
									nn--;
								}
								s.mtfa[pp] = uc;
							} else {
								@Pc(776) int lno = nn / 16;
								@Pc(780) int off = nn % 16;
								pp = s.mtfbase[lno] + off;
								uc = s.mtfa[pp];
								while (pp > s.mtfbase[lno]) {
									s.mtfa[pp] = s.mtfa[pp - 1];
									pp--;
								}
								@Pc(815) int local815 = s.mtfbase[lno]++;
								while (lno > 0) {
									local815 = s.mtfbase[lno]--;
									s.mtfa[s.mtfbase[lno]] = s.mtfa[s.mtfbase[lno - 1] + 16 - 1];
									lno--;
								}
								local815 = s.mtfbase[0]--;
								s.mtfa[s.mtfbase[0]] = uc;
								if (s.mtfbase[0] == 0) {
									kk = 4095;
									for (ii = 15; ii >= 0; ii--) {
										for (jj = 15; jj >= 0; jj--) {
											s.mtfa[kk] = s.mtfa[s.mtfbase[ii] + jj];
											kk--;
										}
										s.mtfbase[ii] = kk + 1;
									}
								}
							}
							s.unzftab[s.seqToUnseq[uc & 0xFF] & 0xFF]++;
							tt[nblock] = s.seqToUnseq[uc & 0xFF] & 0xFF;
							nblock++;
							if (gPos == 0) {
								groupNo1++;
								gPos = 50;
								gSel = s.selector[groupNo1];
								gMinlen = s.minLens[gSel];
								gLimit = s.limit[gSel];
								gPerm = s.perm[gSel];
								gBase = s.base[gSel];
							}
							gPos--;
							zn = gMinlen;
							for (zvec = getBits(gMinlen, s); zvec > gLimit[zn]; zvec = zvec << 1 | zj) {
								zn++;
								zj = getBit(s);
							}
							nextSym = gPerm[zvec - gBase[zn]];
						}
					}
					s.stateOutLen = 0;
					s.stateOutCh = 0;
					s.cftab[0] = 0;
					for (i = 1; i <= 256; i++) {
						s.cftab[i] = s.unzftab[i - 1];
					}
					for (i = 1; i <= 256; i++) {
						s.cftab[i] += s.cftab[i - 1];
					}
					for (i = 0; i < nblock; i++) {
						uc = (byte) (tt[i] & 0xFF);
						tt[s.cftab[uc & 0xFF]] |= i << 8;
						s.cftab[uc & 0xFF]++;
					}
					s.tPos = tt[s.origPtr] >> 8;
					s.nblockused = 0;
					s.tPos = tt[s.tPos];
					s.k0 = (byte) (s.tPos & 0xFF);
					s.tPos >>= 0x8;
					s.nblockused++;
					s.saveNblock = nblock;
					unRleObufToOutputFast(s);
					if (s.nblockused == s.saveNblock + 1 && s.stateOutLen == 0) {
						reading = true;
						break;
					}
					reading = false;
					break;
				}
			}
			return;
		}
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "e", descriptor = "(Lclient!bb;)B")
	private static byte getBit(@OriginalArg(0) Bzip2DState arg0) {
		return (byte) getBits(1, arg0);
	}

	@OriginalMember(owner = "runetek4.client!oc", name = "a", descriptor = "([BI[BII)I")
	public static int bunzip2(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int length, @OriginalArg(2) byte[] arg2, @OriginalArg(3) int arg3) {
		@Pc(2) Bzip2DState local2 = state;
		synchronized (state) {
			state.strmNextIn = arg2;
			state.strmNextInPtr = 9;
			state.strmNextOut = arg0;
			state.strmNextOutPtr = 0;
			state.strmAvailOut = length;
			state.bsLive = 0;
			state.bsBuff = 0;
			state.strmTotalInLo32 = 0;
			state.strmTotalOutLo32 = 0;
			decompress(state);
			@Pc(37) int i = length - state.strmAvailOut;
			state.strmNextIn = null;
			state.strmNextOut = null;
			return i;
		}
	}
}
