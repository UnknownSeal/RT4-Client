package com.jagex.runetek4.dash3d;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!mj")
public final class CollisionMap {


	@OriginalMember(owner = "client!mj", name = "p", descriptor = "I")
	private final int offsetZ;

	@OriginalMember(owner = "client!mj", name = "v", descriptor = "I")
	private final int offsetX;

	@OriginalMember(owner = "client!mj", name = "e", descriptor = "I")
	private final int sizeX;

	@OriginalMember(owner = "client!mj", name = "k", descriptor = "I")
	private final int sizeZ;

	@OriginalMember(owner = "client!mj", name = "m", descriptor = "[[I")
	public final int[][] flags;

	@OriginalMember(owner = "client!mj", name = "<init>", descriptor = "(II)V")
	public CollisionMap(@OriginalArg(0) int sizeX, @OriginalArg(1) int sizeZ) {
		this.offsetZ = 0;
		this.offsetX = 0;
		this.sizeX = sizeX;
		this.sizeZ = sizeZ;
		this.flags = new int[this.sizeX][this.sizeZ];
		this.reset();
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(I)V")
	public void reset() {
		for (@Pc(3) int x = 0; x < this.sizeX; x++) {
			for (@Pc(13) int z = 0; z < this.sizeZ; z++) {
				if (x == 0 || z == 0 || x >= this.sizeX - 5 || this.sizeZ - 5 <= z) {
					this.flags[x][z] = 0xFFFFFF;
				} else {
					this.flags[x][z] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IZIIII)V")
	public void method3039(@OriginalArg(0) int rotation, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int shape, @OriginalArg(5) int arg4) {
		@Pc(4) int x = arg4 - this.offsetX;
		@Pc(23) int z = arg2 - this.offsetZ;
		if (shape == 0) {
			if (rotation == 0) {
				this.remCMap(x, z, 0x80);
				this.remCMap(x - 1, z, 0x8);
			}
			if (rotation == 1) {
				this.remCMap(x, z, 2);
				this.remCMap(x, z + 1, 32);
			}
			if (rotation == 2) {
				this.remCMap(x, z, 8);
				this.remCMap(x + 1, z, 128);
			}
			if (rotation == 3) {
				this.remCMap(x, z, 32);
				this.remCMap(x, z - 1, 2);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.remCMap(x, z, 1);
				this.remCMap(x + -1, z + 1, 16);
			}
			if (rotation == 1) {
				this.remCMap(x, z, 4);
				this.remCMap(x + 1, z + 1, 64);
			}
			if (rotation == 2) {
				this.remCMap(x, z, 16);
				this.remCMap(x - -1, z - 1, 1);
			}
			if (rotation == 3) {
				this.remCMap(x, z, 64);
				this.remCMap(x + -1, z - 1, 4);
			}
		}
		if (shape == 2) {
			if (rotation == 0) {
				this.remCMap(x, z, 130);
				this.remCMap(x - 1, z, 8);
				this.remCMap(x, z + 1, 32);
			}
			if (rotation == 1) {
				this.remCMap(x, z, 10);
				this.remCMap(x, z + 1, 32);
				this.remCMap(x + 1, z, 128);
			}
			if (rotation == 2) {
				this.remCMap(x, z, 40);
				this.remCMap(x + 1, z, 128);
				this.remCMap(x, z - 1, 2);
			}
			if (rotation == 3) {
				this.remCMap(x, z, 160);
				this.remCMap(x, z - 1, 2);
				this.remCMap(x - 1, z, 8);
			}
		}
		if (!arg1) {
			return;
		}
		if (shape == 0) {
			if (rotation == 0) {
				this.remCMap(x, z, 65536);
				this.remCMap(x - 1, z, 4096);
			}
			if (rotation == 1) {
				this.remCMap(x, z, 1024);
				this.remCMap(x, z + 1, 16384);
			}
			if (rotation == 2) {
				this.remCMap(x, z, 4096);
				this.remCMap(x + 1, z, 65536);
			}
			if (rotation == 3) {
				this.remCMap(x, z, 16384);
				this.remCMap(x, z - 1, 1024);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.remCMap(x, z, 512);
				this.remCMap(x + -1, z + 1, 8192);
			}
			if (rotation == 1) {
				this.remCMap(x, z, 2048);
				this.remCMap(x - -1, z + 1, 32768);
			}
			if (rotation == 2) {
				this.remCMap(x, z, 8192);
				this.remCMap(x + 1, z - 1, 512);
			}
			if (rotation == 3) {
				this.remCMap(x, z, 32768);
				this.remCMap(x + -1, z - 1, 2048);
			}
		}
		if (shape != 2) {
			return;
		}
		if (rotation == 0) {
			this.remCMap(x, z, 66560);
			this.remCMap(x - 1, z, 4096);
			this.remCMap(x, z + 1, 16384);
		}
		if (rotation == 1) {
			this.remCMap(x, z, 5120);
			this.remCMap(x, z + 1, 16384);
			this.remCMap(x + 1, z, 65536);
		}
		if (rotation == 2) {
			this.remCMap(x, z, 20480);
			this.remCMap(x + 1, z, 65536);
			this.remCMap(x, z - 1, 1024);
		}
		if (rotation == 3) {
			this.remCMap(x, z, 81920);
			this.remCMap(x, z - 1, 1024);
			this.remCMap(x - 1, z, 4096);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZII)V")
	public void addWall(@OriginalArg(5) int tileX, @OriginalArg(4) int tileZ, @OriginalArg(2) int shape, @OriginalArg(0) int rotation, @OriginalArg(3) boolean blockrange) {
		@Pc(4) int x = tileX - this.offsetX;
		@Pc(13) int z = tileZ - this.offsetZ;
		if (shape == 0) {
			if (rotation == 0) {
				this.addCMap(x, z, 0x80);
				this.addCMap(x - 1, z, 0x8);
			}
			if (rotation == 1) {
				this.addCMap(x, z, 0x2);
				this.addCMap(x, z + 1, 0x20);
			}
			if (rotation == 2) {
				this.addCMap(x, z, 0x8);
				this.addCMap(x + 1, z, 0x80);
			}
			if (rotation == 3) {
				this.addCMap(x, z,0x20);
				this.addCMap(x, z - 1, 0x2);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.addCMap(x, z, 0x1);
				this.addCMap(x - 1, z + 1, 0x10);
			}
			if (rotation == 1) {
				this.addCMap(x, z, 0x4);
				this.addCMap(x + 1, z + 1, 0x40);
			}
			if (rotation == 2) {
				this.addCMap(x, z, 0x10);
				this.addCMap(x + 1, z - 1, 0x1);
			}
			if (rotation == 3) {
				this.addCMap(x, z, 0x40);
				this.addCMap(x - 1, z - 1, 4);
			}
		}
		if (shape == 2) {
			if (rotation == 0) {
				this.addCMap(x, z, 0x82);
				this.addCMap(x - 1, z, 0x8);
				this.addCMap(x, z + 1, 0x20);
			}
			if (rotation == 1) {
				this.addCMap(x, z, 0xA);
				this.addCMap(x, z + 1, 0x20);
				this.addCMap(x + 1, z, 0x80);
			}
			if (rotation == 2) {
				this.addCMap(x, z, 0x28);
				this.addCMap(x + 1, z, 0x80);
				this.addCMap(x, z - 1, 0x2);
			}
			if (rotation == 3) {
				this.addCMap(x, z, 0xA0);
				this.addCMap(x, z - 1, 0x2);
				this.addCMap(x - 1, z, 0x8);
			}
		}
		if (!blockrange) {
			return;
		}
		if (shape == 0) {
			if (rotation == 0) {
				this.addCMap(x, z, 0x10000);
				this.addCMap(x - 1, z, 0x1000);
			}
			if (rotation == 1) {
				this.addCMap(x, z, 0x400);
				this.addCMap(x, z + 1, 0x4000);
			}
			if (rotation == 2) {
				this.addCMap(x, z, 0x1000);
				this.addCMap(x + 1, z, 0x10000);
			}
			if (rotation == 3) {
				this.addCMap(x, z, 0x4000);
				this.addCMap(x, z - 1, 0x400);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.addCMap(x, z, 512);
				this.addCMap(x - 1, z + 1, 8192);
			}
			if (rotation == 1) {
				this.addCMap(x, z, 2048);
				this.addCMap(x + 1, z + 1, 32768);
			}
			if (rotation == 2) {
				this.addCMap(x, z, 8192);
				this.addCMap(x + 1, z + -1, 512);
			}
			if (rotation == 3) {
				this.addCMap(x, z, 32768);
				this.addCMap(x - 1, z - 1, 2048);
			}
		}
		if (shape != 2) {
			return;
		}
		if (rotation == 0) {
			this.addCMap(x, z, 66560);
			this.addCMap(x - 1, z, 4096);
			this.addCMap(x, z + 1, 16384);
		}
		if (rotation == 1) {
			this.addCMap(x, z, 5120);
			this.addCMap(x, z + 1, 16384);
			this.addCMap(x + 1, z, 65536);
		}
		if (rotation == 2) {
			this.addCMap(x, z, 20480);
			this.addCMap(x + 1, z, 65536);
			this.addCMap(x, z - 1, 1024);
		}
		if (rotation == 3) {
			this.addCMap(x, z, 81920);
			this.addCMap(x, z - 1, 1024);
			this.addCMap(x - 1, z, 4096);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIIIIIB)Z")
	private boolean method3041(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		if (arg1 + arg2 > arg7 && arg2 < arg0 + arg7) {
			return arg3 + arg5 > arg4 && arg6 + arg4 > arg5;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIZIIIII)Z")
	public boolean method3042(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 == 1) {
			if (arg1 == arg3 && arg2 == arg0) {
				return true;
			}
		} else if (arg1 <= arg3 && arg1 + arg5 - 1 >= arg3 && arg0 <= arg0 && arg0 <= arg5 + arg0 - 1) {
			return true;
		}
		@Pc(49) int local49 = arg2 - this.offsetZ;
		@Pc(54) int local54 = arg3 - this.offsetX;
		@Pc(59) int local59 = arg0 - this.offsetZ;
		@Pc(64) int local64 = arg1 - this.offsetX;
		if (arg5 == 1) {
			if (arg4 == 0) {
				if (arg6 == 0) {
					if (local64 == local54 - 1 && local49 == local59) {
						return true;
					}
					if (local64 == local54 && local59 + 1 == local49 && (this.flags[local64][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local54 == local64 && local59 - 1 == local49 && (this.flags[local64][local49] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 1) {
					if (local54 == local64 && local49 == local59 + 1) {
						return true;
					}
					if (local54 - 1 == local64 && local59 == local49 && (this.flags[local64][local49] & 0x12C0108) == 0) {
						return true;
					}
					if (local64 == local54 + 1 && local49 == local59 && (this.flags[local64][local49] & 0x12C0180) == 0) {
						return true;
					}
				} else if (arg6 == 2) {
					if (local54 + 1 == local64 && local49 == local59) {
						return true;
					}
					if (local54 == local64 && local49 == local59 + 1 && (this.flags[local64][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local54 == local64 && local59 - 1 == local49 && (this.flags[local64][local49] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 3) {
					if (local64 == local54 && local59 - 1 == local49) {
						return true;
					}
					if (local54 - 1 == local64 && local59 == local49 && (this.flags[local64][local49] & 0x12C0108) == 0) {
						return true;
					}
					if (local54 + 1 == local64 && local49 == local59 && (this.flags[local64][local49] & 0x12C0180) == 0) {
						return true;
					}
				}
			}
			if (arg4 == 2) {
				if (arg6 == 0) {
					if (local64 == local54 - 1 && local59 == local49) {
						return true;
					}
					if (local64 == local54 && local49 == local59 + 1) {
						return true;
					}
					if (local64 == local54 + 1 && local49 == local59 && (this.flags[local64][local49] & 0x12C0180) == 0) {
						return true;
					}
					if (local54 == local64 && local49 == local59 - 1 && (this.flags[local64][local49] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 1) {
					if (local64 == local54 - 1 && local59 == local49 && (this.flags[local64][local49] & 0x12C0108) == 0) {
						return true;
					}
					if (local54 == local64 && local59 + 1 == local49) {
						return true;
					}
					if (local64 == local54 + 1 && local59 == local49) {
						return true;
					}
					if (local54 == local64 && local49 == local59 - 1 && (this.flags[local64][local49] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 2) {
					if (local64 == local54 - 1 && local59 == local49 && (this.flags[local64][local49] & 0x12C0108) == 0) {
						return true;
					}
					if (local54 == local64 && local49 == local59 + 1 && (this.flags[local64][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local54 + 1 == local64 && local59 == local49) {
						return true;
					}
					if (local64 == local54 && local59 - 1 == local49) {
						return true;
					}
				} else if (arg6 == 3) {
					if (local64 == local54 - 1 && local59 == local49) {
						return true;
					}
					if (local64 == local54 && local59 + 1 == local49 && (this.flags[local64][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local64 == local54 + 1 && local59 == local49 && (this.flags[local64][local49] & 0x12C0180) == 0) {
						return true;
					}
					if (local64 == local54 && local49 == local59 - 1) {
						return true;
					}
				}
			}
			if (arg4 == 9) {
				if (local64 == local54 && local59 + 1 == local49 && (this.flags[local64][local49] & 0x20) == 0) {
					return true;
				}
				if (local64 == local54 && local49 == local59 - 1 && (this.flags[local64][local49] & 0x2) == 0) {
					return true;
				}
				if (local64 == local54 - 1 && local49 == local59 && (this.flags[local64][local49] & 0x8) == 0) {
					return true;
				}
				if (local54 + 1 == local64 && local59 == local49 && (this.flags[local64][local49] & 0x80) == 0) {
					return true;
				}
			}
		} else {
			@Pc(785) int local785 = arg5 + local64 - 1;
			@Pc(792) int local792 = local49 + arg5 - 1;
			if (arg4 == 0) {
				if (arg6 == 0) {
					if (local64 == local54 - arg5 && local49 <= local59 && local59 <= local792) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local59 + 1 == local49 && (this.flags[local54][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local59 - arg5 == local49 && (this.flags[local54][local792] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 1) {
					if (local54 >= local64 && local54 <= local785 && local59 + 1 == local49) {
						return true;
					}
					if (local64 == local54 - arg5 && local49 <= local59 && local792 >= local59 && (this.flags[local785][local59] & 0x12C0108) == 0) {
						return true;
					}
					if (local64 == local54 + 1 && local49 <= local59 && local792 >= local59 && (this.flags[local64][local59] & 0x12C0180) == 0) {
						return true;
					}
				} else if (arg6 == 2) {
					if (local54 + 1 == local64 && local49 <= local59 && local59 <= local792) {
						return true;
					}
					if (local54 >= local64 && local785 >= local54 && local49 == local59 + 1 && (this.flags[local54][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local54 >= local64 && local785 >= local54 && local59 - arg5 == local49 && (this.flags[local54][local792] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 3) {
					if (local64 <= local54 && local785 >= local54 && local59 - arg5 == local49) {
						return true;
					}
					if (local64 == local54 - arg5 && local59 >= local49 && local792 >= local59 && (this.flags[local785][local59] & 0x12C0108) == 0) {
						return true;
					}
					if (local54 + 1 == local64 && local49 <= local59 && local59 <= local792 && (this.flags[local64][local59] & 0x12C0180) == 0) {
						return true;
					}
				}
			}
			if (arg4 == 2) {
				if (arg6 == 0) {
					if (local64 == local54 - arg5 && local59 >= local49 && local59 <= local792) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local49 == local59 + 1) {
						return true;
					}
					if (local54 + 1 == local64 && local59 >= local49 && local59 <= local792 && (this.flags[local64][local59] & 0x12C0180) == 0) {
						return true;
					}
					if (local54 >= local64 && local785 >= local54 && local59 - arg5 == local49 && (this.flags[local54][local792] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 1) {
					if (local64 == local54 - arg5 && local59 >= local49 && local59 <= local792 && (this.flags[local785][local59] & 0x12C0108) == 0) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local49 == local59 + 1) {
						return true;
					}
					if (local54 + 1 == local64 && local59 >= local49 && local59 <= local792) {
						return true;
					}
					if (local54 >= local64 && local54 <= local785 && local49 == local59 - arg5 && (this.flags[local54][local792] & 0x12C0102) == 0) {
						return true;
					}
				} else if (arg6 == 2) {
					if (local54 - arg5 == local64 && local49 <= local59 && local792 >= local59 && (this.flags[local785][local59] & 0x12C0108) == 0) {
						return true;
					}
					if (local64 <= local54 && local54 <= local785 && local49 == local59 + 1 && (this.flags[local54][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local64 == local54 + 1 && local49 <= local59 && local59 <= local792) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local59 - arg5 == local49) {
						return true;
					}
				} else if (arg6 == 3) {
					if (local54 - arg5 == local64 && local59 >= local49 && local59 <= local792) {
						return true;
					}
					if (local64 <= local54 && local54 <= local785 && local59 + 1 == local49 && (this.flags[local54][local49] & 0x12C0120) == 0) {
						return true;
					}
					if (local64 == local54 + 1 && local59 >= local49 && local59 <= local792 && (this.flags[local64][local59] & 0x12C0180) == 0) {
						return true;
					}
					if (local64 <= local54 && local785 >= local54 && local59 - arg5 == local49) {
						return true;
					}
				}
			}
			if (arg4 == 9) {
				if (local64 <= local54 && local54 <= local785 && local49 == local59 + 1 && (this.flags[local54][local49] & 0x12C0120) == 0) {
					return true;
				}
				if (local54 >= local64 && local54 <= local785 && local49 == local59 - arg5 && (this.flags[local54][local792] & 0x12C0102) == 0) {
					return true;
				}
				if (local54 - arg5 == local64 && local59 >= local49 && local59 <= local792 && (this.flags[local785][local59] & 0x12C0108) == 0) {
					return true;
				}
				if (local64 == local54 + 1 && local59 >= local49 && local792 >= local59 && (this.flags[local64][local59] & 0x12C0180) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IZBIII)V")
	public void method3043(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(6) int local6 = arg2 - this.offsetZ;
		@Pc(11) int local11 = arg0 - this.offsetX;
		@Pc(17) int local17 = 256;
		if (arg1) {
			local17 = 131328;
		}
		for (@Pc(25) int local25 = local11; local25 < local11 + arg3; local25++) {
			if (local25 >= 0 && local25 < this.sizeX) {
				for (@Pc(47) int local47 = local6; local47 < arg4 + local6; local47++) {
					if (local47 >= 0 && this.sizeZ > local47) {
						this.addCMap(local25, local47, local17);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IBII)V")
	private void addCMap(@OriginalArg(2) int x, @OriginalArg(3) int z, @OriginalArg(0) int flags) {
		this.flags[x][z] |= flags;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIIIII)Z")
	public boolean method3046(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		if (arg4 == 1) {
			if (arg2 == arg6 && arg0 == arg3) {
				return true;
			}
		} else if (arg6 <= arg2 && arg4 + arg6 - 1 >= arg2 && arg0 <= arg0 && arg0 + arg4 - 1 >= arg0) {
			return true;
		}
		@Pc(62) int local62 = arg6 - this.offsetX;
		@Pc(67) int local67 = arg0 - this.offsetZ;
		@Pc(72) int local72 = arg2 - this.offsetX;
		@Pc(77) int local77 = arg3 - this.offsetZ;
		if (arg4 == 1) {
			if (arg1 == 6 || arg1 == 7) {
				if (arg1 == 7) {
					arg5 = arg5 + 2 & 0x3;
				}
				if (arg5 == 0) {
					if (local62 == local72 + 1 && local67 == local77 && (this.flags[local62][local77] & 0x80) == 0) {
						return true;
					}
					if (local72 == local62 && local77 == local67 - 1 && (this.flags[local62][local77] & 0x2) == 0) {
						return true;
					}
				} else if (arg5 == 1) {
					if (local62 == local72 - 1 && local67 == local77 && (this.flags[local62][local77] & 0x8) == 0) {
						return true;
					}
					if (local72 == local62 && local77 == local67 - 1 && (this.flags[local62][local77] & 0x2) == 0) {
						return true;
					}
				} else if (arg5 == 2) {
					if (local62 == local72 - 1 && local77 == local67 && (this.flags[local62][local77] & 0x8) == 0) {
						return true;
					}
					if (local72 == local62 && local67 + 1 == local77 && (this.flags[local62][local77] & 0x20) == 0) {
						return true;
					}
				} else if (arg5 == 3) {
					if (local62 == local72 + 1 && local77 == local67 && (this.flags[local62][local77] & 0x80) == 0) {
						return true;
					}
					if (local72 == local62 && local77 == local67 + 1 && (this.flags[local62][local77] & 0x20) == 0) {
						return true;
					}
				}
			}
			if (arg1 == 8) {
				if (local62 == local72 && local67 + 1 == local77 && (this.flags[local62][local77] & 0x20) == 0) {
					return true;
				}
				if (local62 == local72 && local67 - 1 == local77 && (this.flags[local62][local77] & 0x2) == 0) {
					return true;
				}
				if (local72 - 1 == local62 && local77 == local67 && (this.flags[local62][local77] & 0x8) == 0) {
					return true;
				}
				if (local62 == local72 + 1 && local67 == local77 && (this.flags[local62][local77] & 0x80) == 0) {
					return true;
				}
			}
		} else {
			@Pc(414) int local414 = local62 + arg4 - 1;
			@Pc(420) int local420 = local77 + arg4 - 1;
			if (arg1 == 6 || arg1 == 7) {
				if (arg1 == 7) {
					arg5 = arg5 + 2 & 0x3;
				}
				if (arg5 == 0) {
					if (local72 + 1 == local62 && local77 <= local67 && local67 <= local420 && (this.flags[local62][local67] & 0x80) == 0) {
						return true;
					}
					if (local72 >= local62 && local414 >= local72 && local67 - arg4 == local77 && (this.flags[local72][local420] & 0x2) == 0) {
						return true;
					}
				} else if (arg5 == 1) {
					if (local72 - arg4 == local62 && local67 >= local77 && local420 >= local67 && (this.flags[local414][local67] & 0x8) == 0) {
						return true;
					}
					if (local72 >= local62 && local414 >= local72 && local67 - arg4 == local77 && (this.flags[local72][local420] & 0x2) == 0) {
						return true;
					}
				} else if (arg5 == 2) {
					if (local62 == local72 - arg4 && local67 >= local77 && local67 <= local420 && (this.flags[local414][local67] & 0x8) == 0) {
						return true;
					}
					if (local62 <= local72 && local72 <= local414 && local77 == local67 + 1 && (this.flags[local72][local77] & 0x20) == 0) {
						return true;
					}
				} else if (arg5 == 3) {
					if (local72 + 1 == local62 && local77 <= local67 && local67 <= local420 && (this.flags[local62][local67] & 0x80) == 0) {
						return true;
					}
					if (local72 >= local62 && local72 <= local414 && local77 == local67 + 1 && (this.flags[local72][local77] & 0x20) == 0) {
						return true;
					}
				}
			}
			if (arg1 == 8) {
				if (local62 <= local72 && local414 >= local72 && local77 == local67 + 1 && (this.flags[local72][local77] & 0x20) == 0) {
					return true;
				}
				if (local72 >= local62 && local414 >= local72 && local77 == local67 - arg4 && (this.flags[local72][local420] & 0x2) == 0) {
					return true;
				}
				if (local62 == local72 - arg4 && local77 <= local67 && local420 >= local67 && (this.flags[local414][local67] & 0x8) == 0) {
					return true;
				}
				if (local62 == local72 + 1 && local67 >= local77 && local67 <= local420 && (this.flags[local62][local67] & 0x80) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIZIIIII)Z")
	private boolean method3048(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		@Pc(9) int local9 = arg5 + arg7;
		@Pc(13) int local13 = arg6 + arg8;
		@Pc(22) int local22 = arg2 + arg0;
		@Pc(27) int local27 = arg1 + arg4;
		@Pc(45) int local45;
		@Pc(52) int local52;
		if (arg5 >= arg0 && arg5 < local22) {
			if (local13 == arg1 && (arg3 & 0x4) == 0) {
				local45 = arg5;
				local52 = local22 >= local9 ? local9 : local22;
				while (local52 > local45) {
					if ((this.flags[local45 - this.offsetX][local13 - this.offsetZ - 1] & 0x2) == 0) {
						return true;
					}
					local45++;
				}
			} else if (local27 == arg6 && (arg3 & 0x1) == 0) {
				local45 = arg5;
				local52 = local9 > local22 ? local22 : local9;
				while (local52 > local45) {
					if ((this.flags[local45 - this.offsetX][arg6 - this.offsetZ] & 0x20) == 0) {
						return true;
					}
					local45++;
				}
			}
		} else if (local9 > arg0 && local9 <= local22) {
			if (arg1 == local13 && (arg3 & 0x4) == 0) {
				for (local45 = arg0; local45 < local9; local45++) {
					if ((this.flags[local45 - this.offsetX][local13 - this.offsetZ - 1] & 0x2) == 0) {
						return true;
					}
				}
			} else if (arg6 == local27 && (arg3 & 0x1) == 0) {
				for (local45 = arg0; local45 < local9; local45++) {
					if ((this.flags[local45 - this.offsetX][arg6 - this.offsetZ] & 0x20) == 0) {
						return true;
					}
				}
			}
		} else if (arg6 >= arg1 && local27 > arg6) {
			if (local9 == arg0 && (arg3 & 0x8) == 0) {
				local45 = arg6;
				local52 = local27 >= local13 ? local13 : local27;
				while (local45 < local52) {
					if ((this.flags[local9 - this.offsetX - 1][local45 - this.offsetZ] & 0x8) == 0) {
						return true;
					}
					local45++;
				}
			} else if (arg5 == local22 && (arg3 & 0x2) == 0) {
				local45 = arg6;
				local52 = local27 < local13 ? local27 : local13;
				while (local45 < local52) {
					if ((this.flags[arg5 - this.offsetX][local45 - this.offsetZ] & 0x80) == 0) {
						return true;
					}
					local45++;
				}
			}
		} else if (arg1 < local13 && local27 >= local13) {
			if (local9 == arg0 && (arg3 & 0x8) == 0) {
				for (local45 = arg1; local45 < local13; local45++) {
					if ((this.flags[local9 - this.offsetX - 1][local45 - this.offsetZ] & 0x8) == 0) {
						return true;
					}
				}
			} else if (local22 == arg5 && (arg3 & 0x2) == 0) {
				for (local45 = arg1; local45 < local13; local45++) {
					if ((this.flags[arg5 - this.offsetX][local45 - this.offsetZ] & 0x80) == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(III)V")
	public void method3051(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(12) int local12 = arg0 - this.offsetZ;
		@Pc(17) int local17 = arg1 - this.offsetX;
		this.flags[local17][local12] |= 0x200000;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(ZIIIIIIII)Z")
	public boolean method3052(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		if (arg3 > 1) {
			return this.method3041(arg3, arg4, arg0, arg7, arg1, arg6, arg3, arg2) ? true : this.method3048(arg0, arg6, arg4, arg5, arg7, arg2, arg1, arg3, arg3);
		}
		@Pc(41) int local41 = arg4 + arg0 - 1;
		@Pc(47) int local47 = arg6 + arg7 - 1;
		if (arg0 <= arg2 && local41 >= arg2 && arg6 <= arg1 && arg1 <= local47) {
			return true;
		} else if (arg0 - 1 == arg2 && arg1 >= arg6 && arg1 <= local47 && (this.flags[arg2 - this.offsetX][arg1 - this.offsetZ] & 0x8) == 0 && (arg5 & 0x8) == 0) {
			return true;
		} else if (arg2 == local41 + 1 && arg6 <= arg1 && local47 >= arg1 && (this.flags[arg2 - this.offsetX][arg1 - this.offsetZ] & 0x80) == 0 && (arg5 & 0x2) == 0) {
			return true;
		} else if (arg1 == arg6 - 1 && arg0 <= arg2 && local41 >= arg2 && (this.flags[arg2 - this.offsetX][arg1 - this.offsetZ] & 0x2) == 0 && (arg5 & 0x4) == 0) {
			return true;
		} else {
			return arg1 == local47 + 1 && arg2 >= arg0 && local41 >= arg2 && (this.flags[arg2 - this.offsetX][arg1 - this.offsetZ] & 0x20) == 0 && (arg5 & 0x1) == 0;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IBI)V")
	public void method3053(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(4) int local4 = arg1 - this.offsetX;
		@Pc(9) int local9 = arg0 - this.offsetZ;
		this.flags[local4][local9] &= 0xFFFBFFFF;
	}

	@OriginalMember(owner = "client!mj", name = "b", descriptor = "(IIIII)Z")
	public boolean method3054(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 == arg2 && arg0 == arg1) {
			return true;
		}
		@Pc(22) int local22 = arg1 - this.offsetZ;
		@Pc(33) int local33 = arg2 - this.offsetX;
		if (local33 < 0 || this.sizeX <= local33 || local22 < 0 || local22 >= this.sizeZ) {
			return false;
		}
		@Pc(61) int local61 = arg0 - this.offsetZ;
		@Pc(66) int local66 = arg3 - this.offsetX;
		@Pc(77) int local77;
		if (local33 > local66) {
			local77 = local33 - local66;
		} else {
			local77 = local66 - local33;
		}
		@Pc(96) int local96;
		if (local22 <= local61) {
			local96 = local61 - local22;
		} else {
			local96 = local22 - local61;
		}
		@Pc(117) int local117;
		@Pc(111) int local111;
		if (local77 <= local96) {
			local111 = 32768;
			local117 = local77 * 65536 / local96;
			while (local61 != local22) {
				if (local22 > local61) {
					if ((this.flags[local66][local61] & 0x12C0102) != 0) {
						return false;
					}
					local61++;
				} else if (local22 < local61) {
					if ((this.flags[local66][local61] & 0x12C0120) != 0) {
						return false;
					}
					local61--;
				}
				local111 += local117;
				if (local111 >= 65536) {
					local111 -= 65536;
					if (local66 < local33) {
						if ((this.flags[local66][local61] & 0x12C0108) != 0) {
							return false;
						}
						local66++;
					} else if (local66 > local33) {
						if ((this.flags[local66][local61] & 0x12C0180) != 0) {
							return false;
						}
						local66--;
					}
				}
			}
		} else {
			local117 = local96 * 65536 / local77;
			local111 = 32768;
			while (local33 != local66) {
				if (local66 < local33) {
					if ((this.flags[local66][local61] & 0x12C0108) != 0) {
						return false;
					}
					local66++;
				} else if (local33 < local66) {
					if ((this.flags[local66][local61] & 0x12C0180) != 0) {
						return false;
					}
					local66--;
				}
				local111 += local117;
				if (local111 >= 65536) {
					local111 -= 65536;
					if (local61 < local22) {
						if ((this.flags[local66][local61] & 0x12C0102) != 0) {
							return false;
						}
						local61++;
					} else if (local22 < local61) {
						if ((this.flags[local66][local61] & 0x12C0120) != 0) {
							return false;
						}
						local61--;
					}
				}
			}
		}
		return (this.flags[local33][local22] & 0x1240100) == 0;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(BIII)V")
	private void remCMap(@OriginalArg(2) int x, @OriginalArg(1) int z, @OriginalArg(3) int flags) {
		this.flags[x][z] &= ~flags;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZIII)V")
	public void unflagScenery(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) boolean arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(6) int local6 = arg0 - this.offsetX;
		@Pc(11) int local11 = arg5 - this.offsetZ;
		@Pc(13) int local13 = 256;
		if (arg2) {
			local13 = 131328;
		}
		@Pc(40) int local40;
		if (arg3 == 1 || arg3 == 3) {
			local40 = arg1;
			arg1 = arg4;
			arg4 = local40;
		}
		for (local40 = local6; local40 < local6 + arg1; local40++) {
			if (local40 >= 0 && local40 < this.sizeX) {
				for (@Pc(61) int local61 = local11; local61 < arg4 + local11; local61++) {
					if (local61 >= 0 && this.sizeZ > local61) {
						this.remCMap(local40, local61, local13);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "b", descriptor = "(III)V")
	public void method3057(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(4) int local4 = arg1 - this.offsetZ;
		@Pc(17) int local17 = arg0 - this.offsetX;
		this.flags[local17][local4] |= 0x40000;
	}
}
