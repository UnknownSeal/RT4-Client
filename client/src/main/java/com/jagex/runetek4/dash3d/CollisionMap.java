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
	private final int width;

	@OriginalMember(owner = "client!mj", name = "k", descriptor = "I")
	private final int length;

	@OriginalMember(owner = "client!mj", name = "m", descriptor = "[[I")
	public final int[][] flags;

	@OriginalMember(owner = "client!mj", name = "<init>", descriptor = "(II)V")
	public CollisionMap(@OriginalArg(0) int width, @OriginalArg(1) int length) {
		this.offsetZ = 0;
		this.offsetX = 0;
		this.width = width;
		this.length = length;
		this.flags = new int[this.width][this.length];
		this.reset();
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(I)V")
	public void reset() {
		for (@Pc(3) int x = 0; x < this.width; x++) {
			for (@Pc(13) int z = 0; z < this.length; z++) {
				if (x == 0 || z == 0 || x >= this.width - 5 || this.length - 5 <= z) {
					this.flags[x][z] = 0xFFFFFF;
				} else {
					this.flags[x][z] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IZIIII)V")
	public void unflagWall(@OriginalArg(0) int rotation, @OriginalArg(1) boolean blockProjectiles, @OriginalArg(3) int startZ, @OriginalArg(4) int shape, @OriginalArg(5) int startX) {
		@Pc(4) int x = startX - this.offsetX;
		@Pc(23) int z = startZ - this.offsetZ;
		if (shape == 0) {
			if (rotation == 0) {
				this.unflag(x, z, 0x80);
				this.unflag(x - 1, z, 0x8);
			}
			if (rotation == 1) {
				this.unflag(x, z, 2);
				this.unflag(x, z + 1, 32);
			}
			if (rotation == 2) {
				this.unflag(x, z, 8);
				this.unflag(x + 1, z, 128);
			}
			if (rotation == 3) {
				this.unflag(x, z, 32);
				this.unflag(x, z - 1, 2);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.unflag(x, z, 1);
				this.unflag(x + -1, z + 1, 16);
			}
			if (rotation == 1) {
				this.unflag(x, z, 4);
				this.unflag(x + 1, z + 1, 64);
			}
			if (rotation == 2) {
				this.unflag(x, z, 16);
				this.unflag(x - -1, z - 1, 1);
			}
			if (rotation == 3) {
				this.unflag(x, z, 64);
				this.unflag(x + -1, z - 1, 4);
			}
		}
		if (shape == 2) {
			if (rotation == 0) {
				this.unflag(x, z, 130);
				this.unflag(x - 1, z, 8);
				this.unflag(x, z + 1, 32);
			}
			if (rotation == 1) {
				this.unflag(x, z, 10);
				this.unflag(x, z + 1, 32);
				this.unflag(x + 1, z, 128);
			}
			if (rotation == 2) {
				this.unflag(x, z, 40);
				this.unflag(x + 1, z, 128);
				this.unflag(x, z - 1, 2);
			}
			if (rotation == 3) {
				this.unflag(x, z, 160);
				this.unflag(x, z - 1, 2);
				this.unflag(x - 1, z, 8);
			}
		}
		if (!blockProjectiles) {
			return;
		}
		if (shape == 0) {
			if (rotation == 0) {
				this.unflag(x, z, 65536);
				this.unflag(x - 1, z, 4096);
			}
			if (rotation == 1) {
				this.unflag(x, z, 1024);
				this.unflag(x, z + 1, 16384);
			}
			if (rotation == 2) {
				this.unflag(x, z, 4096);
				this.unflag(x + 1, z, 65536);
			}
			if (rotation == 3) {
				this.unflag(x, z, 16384);
				this.unflag(x, z - 1, 1024);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.unflag(x, z, 512);
				this.unflag(x + -1, z + 1, 8192);
			}
			if (rotation == 1) {
				this.unflag(x, z, 2048);
				this.unflag(x - -1, z + 1, 32768);
			}
			if (rotation == 2) {
				this.unflag(x, z, 8192);
				this.unflag(x + 1, z - 1, 512);
			}
			if (rotation == 3) {
				this.unflag(x, z, 32768);
				this.unflag(x + -1, z - 1, 2048);
			}
		}
		if (shape != 2) {
			return;
		}
		if (rotation == 0) {
			this.unflag(x, z, 66560);
			this.unflag(x - 1, z, 4096);
			this.unflag(x, z + 1, 16384);
		}
		if (rotation == 1) {
			this.unflag(x, z, 5120);
			this.unflag(x, z + 1, 16384);
			this.unflag(x + 1, z, 65536);
		}
		if (rotation == 2) {
			this.unflag(x, z, 20480);
			this.unflag(x + 1, z, 65536);
			this.unflag(x, z - 1, 1024);
		}
		if (rotation == 3) {
			this.unflag(x, z, 81920);
			this.unflag(x, z - 1, 1024);
			this.unflag(x - 1, z, 4096);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZII)V")
	public void addWall(@OriginalArg(5) int tileX, @OriginalArg(4) int tileZ, @OriginalArg(2) int shape, @OriginalArg(0) int rotation, @OriginalArg(3) boolean blockrange) {
		@Pc(4) int x = tileX - this.offsetX;
		@Pc(13) int z = tileZ - this.offsetZ;
		if (shape == 0) {
			if (rotation == 0) {
				this.flag(x, z, 0x80);
				this.flag(x - 1, z, 0x8);
			}
			if (rotation == 1) {
				this.flag(x, z, 0x2);
				this.flag(x, z + 1, 0x20);
			}
			if (rotation == 2) {
				this.flag(x, z, 0x8);
				this.flag(x + 1, z, 0x80);
			}
			if (rotation == 3) {
				this.flag(x, z,0x20);
				this.flag(x, z - 1, 0x2);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.flag(x, z, 0x1);
				this.flag(x - 1, z + 1, 0x10);
			}
			if (rotation == 1) {
				this.flag(x, z, 0x4);
				this.flag(x + 1, z + 1, 0x40);
			}
			if (rotation == 2) {
				this.flag(x, z, 0x10);
				this.flag(x + 1, z - 1, 0x1);
			}
			if (rotation == 3) {
				this.flag(x, z, 0x40);
				this.flag(x - 1, z - 1, 4);
			}
		}
		if (shape == 2) {
			if (rotation == 0) {
				this.flag(x, z, 0x82);
				this.flag(x - 1, z, 0x8);
				this.flag(x, z + 1, 0x20);
			}
			if (rotation == 1) {
				this.flag(x, z, 0xA);
				this.flag(x, z + 1, 0x20);
				this.flag(x + 1, z, 0x80);
			}
			if (rotation == 2) {
				this.flag(x, z, 0x28);
				this.flag(x + 1, z, 0x80);
				this.flag(x, z - 1, 0x2);
			}
			if (rotation == 3) {
				this.flag(x, z, 0xA0);
				this.flag(x, z - 1, 0x2);
				this.flag(x - 1, z, 0x8);
			}
		}
		if (!blockrange) {
			return;
		}
		if (shape == 0) {
			if (rotation == 0) {
				this.flag(x, z, 0x10000);
				this.flag(x - 1, z, 0x1000);
			}
			if (rotation == 1) {
				this.flag(x, z, 0x400);
				this.flag(x, z + 1, 0x4000);
			}
			if (rotation == 2) {
				this.flag(x, z, 0x1000);
				this.flag(x + 1, z, 0x10000);
			}
			if (rotation == 3) {
				this.flag(x, z, 0x4000);
				this.flag(x, z - 1, 0x400);
			}
		}
		if (shape == 1 || shape == 3) {
			if (rotation == 0) {
				this.flag(x, z, 512);
				this.flag(x - 1, z + 1, 8192);
			}
			if (rotation == 1) {
				this.flag(x, z, 2048);
				this.flag(x + 1, z + 1, 32768);
			}
			if (rotation == 2) {
				this.flag(x, z, 8192);
				this.flag(x + 1, z + -1, 512);
			}
			if (rotation == 3) {
				this.flag(x, z, 32768);
				this.flag(x - 1, z - 1, 2048);
			}
		}
		if (shape != 2) {
			return;
		}
		if (rotation == 0) {
			this.flag(x, z, 66560);
			this.flag(x - 1, z, 4096);
			this.flag(x, z + 1, 16384);
		}
		if (rotation == 1) {
			this.flag(x, z, 5120);
			this.flag(x, z + 1, 16384);
			this.flag(x + 1, z, 65536);
		}
		if (rotation == 2) {
			this.flag(x, z, 20480);
			this.flag(x + 1, z, 65536);
			this.flag(x, z - 1, 1024);
		}
		if (rotation == 3) {
			this.flag(x, z, 81920);
			this.flag(x, z - 1, 1024);
			this.flag(x - 1, z, 4096);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIIIIIB)Z")
	private boolean isInsideRect(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		if (arg1 + arg2 > arg7 && arg2 < arg0 + arg7) {
			return arg3 + arg5 > arg4 && arg6 + arg4 > arg5;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIZIIIII)Z")
	public boolean isAtWall(@OriginalArg(0) int srcZ, @OriginalArg(1) int destX, @OriginalArg(3) int destZ, @OriginalArg(4) int srcX, @OriginalArg(5) int destType, @OriginalArg(6) int size, @OriginalArg(7) int destAngle) {
		if (size == 1) {
			if (destX == srcX && destZ == srcZ) {
				return true;
			}
		} else if (destX <= srcX && destX + size - 1 >= srcX && srcZ <= srcZ && srcZ <= size + srcZ - 1) {
			return true;
		}
		@Pc(49) int endZ = destZ - this.offsetZ;
		@Pc(54) int startX = srcX - this.offsetX;
		@Pc(59) int startZ = srcZ - this.offsetZ;
		@Pc(64) int endX = destX - this.offsetX;
		if (size == 1) {
			if (destType == 0) {
				if (destAngle == 0) {
					if (endX == startX - 1 && endZ == startZ) {
						return true;
					}
					if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (startX == endX && startZ - 1 == endZ && (this.flags[endX][endZ] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (startX == endX && endZ == startZ + 1) {
						return true;
					}
					if (startX - 1 == endX && startZ == endZ && (this.flags[endX][endZ] & 0x12C0108) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & 0x12C0180) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (startX + 1 == endX && endZ == startZ) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (startX == endX && startZ - 1 == endZ && (this.flags[endX][endZ] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 3) {
					if (endX == startX && startZ - 1 == endZ) {
						return true;
					}
					if (startX - 1 == endX && startZ == endZ && (this.flags[endX][endZ] & 0x12C0108) == 0) {
						return true;
					}
					if (startX + 1 == endX && endZ == startZ && (this.flags[endX][endZ] & 0x12C0180) == 0) {
						return true;
					}
				}
			}
			if (destType == 2) {
				if (destAngle == 0) {
					if (endX == startX - 1 && startZ == endZ) {
						return true;
					}
					if (endX == startX && endZ == startZ + 1) {
						return true;
					}
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & 0x12C0180) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & 0x12C0108) == 0) {
						return true;
					}
					if (startX == endX && startZ + 1 == endZ) {
						return true;
					}
					if (endX == startX + 1 && startZ == endZ) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & 0x12C0108) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (startX + 1 == endX && startZ == endZ) {
						return true;
					}
					if (endX == startX && startZ - 1 == endZ) {
						return true;
					}
				} else if (destAngle == 3) {
					if (endX == startX - 1 && startZ == endZ) {
						return true;
					}
					if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & 0x12C0180) == 0) {
						return true;
					}
					if (endX == startX && endZ == startZ - 1) {
						return true;
					}
				}
			}
			if (destType == 9) {
				if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & 0x20) == 0) {
					return true;
				}
				if (endX == startX && endZ == startZ - 1 && (this.flags[endX][endZ] & 0x2) == 0) {
					return true;
				}
				if (endX == startX - 1 && endZ == startZ && (this.flags[endX][endZ] & 0x8) == 0) {
					return true;
				}
				if (startX + 1 == endX && startZ == endZ && (this.flags[endX][endZ] & 0x80) == 0) {
					return true;
				}
			}
		} else {
			@Pc(785) int x1 = size + endX - 1;
			@Pc(792) int z1 = endZ + size - 1;
			if (destType == 0) {
				if (destAngle == 0) {
					if (endX == startX - size && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ + 1 == endZ && (this.flags[startX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (startX >= endX && startX <= x1 && startZ + 1 == endZ) {
						return true;
					}
					if (endX == startX - size && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & 0x12C0108) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ <= startZ && z1 >= startZ && (this.flags[endX][startZ] & 0x12C0180) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (startX >= endX && x1 >= startX && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 3) {
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
					if (endX == startX - size && startZ >= endZ && z1 >= startZ && (this.flags[x1][startZ] & 0x12C0108) == 0) {
						return true;
					}
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & 0x12C0180) == 0) {
						return true;
					}
				}
			}
			if (destType == 2) {
				if (destAngle == 0) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && endZ == startZ + 1) {
						return true;
					}
					if (startX + 1 == endX && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & 0x12C0180) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & 0x12C0108) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && endZ == startZ + 1) {
						return true;
					}
					if (startX + 1 == endX && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (startX >= endX && startX <= x1 && endZ == startZ - size && (this.flags[startX][z1] & 0x12C0102) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (startX - size == endX && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & 0x12C0108) == 0) {
						return true;
					}
					if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
				} else if (destAngle == 3) {
					if (startX - size == endX && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && startX <= x1 && startZ + 1 == endZ && (this.flags[startX][endZ] & 0x12C0120) == 0) {
						return true;
					}
					if (endX == startX + 1 && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & 0x12C0180) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
				}
			}
			if (destType == 9) {
				if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x12C0120) == 0) {
					return true;
				}
				if (startX >= endX && startX <= x1 && endZ == startZ - size && (this.flags[startX][z1] & 0x12C0102) == 0) {
					return true;
				}
				if (startX - size == endX && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & 0x12C0108) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ >= endZ && z1 >= startZ && (this.flags[endX][startZ] & 0x12C0180) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IZBIII)V")
	public void flagScenery(@OriginalArg(0) int startX, @OriginalArg(1) boolean blocksProjectiles, @OriginalArg(3) int startZ, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(6) int z = startZ - this.offsetZ;
		@Pc(11) int x = startX - this.offsetX;
		@Pc(17) int flags = 256;
		if (blocksProjectiles) {
			flags = 131328;
		}
		for (@Pc(25) int x0 = x; x0 < x + arg3; x0++) {
			if (x0 >= 0 && x0 < this.width) {
				for (@Pc(47) int z0 = z; z0 < arg4 + z; z0++) {
					if (z0 >= 0 && this.length > z0) {
						this.flag(x0, z0, flags);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IBII)V")
	private void flag(@OriginalArg(2) int x, @OriginalArg(3) int z, @OriginalArg(0) int flags) {
		this.flags[x][z] |= flags;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIIIII)Z")
	public boolean isAtWallDecor(@OriginalArg(0) int srcZ, @OriginalArg(1) int destType, @OriginalArg(2) int srcX, @OriginalArg(3) int destZ, @OriginalArg(4) int size, @OriginalArg(5) int destAngle, @OriginalArg(6) int destX) {
		if (size == 1) {
			if (srcX == destX && srcZ == destZ) {
				return true;
			}
		} else if (destX <= srcX && size + destX - 1 >= srcX && srcZ <= srcZ && srcZ + size - 1 >= srcZ) {
			return true;
		}
		@Pc(62) int endX = destX - this.offsetX;
		@Pc(67) int startZ = srcZ - this.offsetZ;
		@Pc(72) int startX = srcX - this.offsetX;
		@Pc(77) int endZ = destZ - this.offsetZ;
		if (size == 1) {
			if (destType == 6 || destType == 7) {
				if (destType == 7) {
					destAngle = destAngle + 2 & 0x3;
				}
				if (destAngle == 0) {
					if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & 0x80) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & 0x2) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & 0x8) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & 0x2) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (endX == startX - 1 && endZ == startZ && (this.flags[endX][endZ] & 0x8) == 0) {
						return true;
					}
					if (startX == endX && startZ + 1 == endZ && (this.flags[endX][endZ] & 0x20) == 0) {
						return true;
					}
				} else if (destAngle == 3) {
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & 0x80) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & 0x20) == 0) {
						return true;
					}
				}
			}
			if (destType == 8) {
				if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & 0x20) == 0) {
					return true;
				}
				if (endX == startX && startZ - 1 == endZ && (this.flags[endX][endZ] & 0x2) == 0) {
					return true;
				}
				if (startX - 1 == endX && endZ == startZ && (this.flags[endX][endZ] & 0x8) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & 0x80) == 0) {
					return true;
				}
			}
		} else {
			@Pc(414) int x1 = endX + size - 1;
			@Pc(420) int z1 = endZ + size - 1;
			if (destType == 6 || destType == 7) {
				if (destType == 7) {
					destAngle = destAngle + 2 & 0x3;
				}
				if (destAngle == 0) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & 0x80) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & 0x2) == 0) {
						return true;
					}
				} else if (destAngle == 1) {
					if (startX - size == endX && startZ >= endZ && z1 >= startZ && (this.flags[x1][startZ] & 0x8) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & 0x2) == 0) {
						return true;
					}
				} else if (destAngle == 2) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & 0x8) == 0) {
						return true;
					}
					if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x20) == 0) {
						return true;
					}
				} else if (destAngle == 3) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & 0x80) == 0) {
						return true;
					}
					if (startX >= endX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x20) == 0) {
						return true;
					}
				}
			}
			if (destType == 8) {
				if (endX <= startX && x1 >= startX && endZ == startZ + 1 && (this.flags[startX][endZ] & 0x20) == 0) {
					return true;
				}
				if (startX >= endX && x1 >= startX && endZ == startZ - size && (this.flags[startX][z1] & 0x2) == 0) {
					return true;
				}
				if (endX == startX - size && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & 0x8) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & 0x80) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIZIIIII)Z")
	private boolean isOutsideRect(@OriginalArg(0) int arg0, @OriginalArg(1) int z1, @OriginalArg(2) int arg2, @OriginalArg(3) int destBlockedSides, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int destZ, @OriginalArg(8) int arg7, @OriginalArg(9) int destLength) {
		@Pc(9) int local9 = arg5 + arg7;
		@Pc(13) int destZ1 = destZ + destLength;
		@Pc(22) int local22 = arg2 + arg0;
		@Pc(27) int local27 = z1 + arg4;
		@Pc(45) int z0;
		@Pc(52) int local52;
		if (arg5 >= arg0 && arg5 < local22) {
			if (destZ1 == z1 && (destBlockedSides & 0x4) == 0) {
				z0 = arg5;
				local52 = local22 >= local9 ? local9 : local22;
				while (local52 > z0) {
					if ((this.flags[z0 - this.offsetX][destZ1 - this.offsetZ - 1] & 0x2) == 0) {
						return true;
					}
					z0++;
				}
			} else if (local27 == destZ && (destBlockedSides & 0x1) == 0) {
				z0 = arg5;
				local52 = local9 > local22 ? local22 : local9;
				while (local52 > z0) {
					if ((this.flags[z0 - this.offsetX][destZ - this.offsetZ] & 0x20) == 0) {
						return true;
					}
					z0++;
				}
			}
		} else if (local9 > arg0 && local9 <= local22) {
			if (z1 == destZ1 && (destBlockedSides & 0x4) == 0) {
				for (z0 = arg0; z0 < local9; z0++) {
					if ((this.flags[z0 - this.offsetX][destZ1 - this.offsetZ - 1] & 0x2) == 0) {
						return true;
					}
				}
			} else if (destZ == local27 && (destBlockedSides & 0x1) == 0) {
				for (z0 = arg0; z0 < local9; z0++) {
					if ((this.flags[z0 - this.offsetX][destZ - this.offsetZ] & 0x20) == 0) {
						return true;
					}
				}
			}
		} else if (destZ >= z1 && local27 > destZ) {
			if (local9 == arg0 && (destBlockedSides & 0x8) == 0) {
				z0 = destZ;
				local52 = local27 >= destZ1 ? destZ1 : local27;
				while (z0 < local52) {
					if ((this.flags[local9 - this.offsetX - 1][z0 - this.offsetZ] & 0x8) == 0) {
						return true;
					}
					z0++;
				}
			} else if (arg5 == local22 && (destBlockedSides & 0x2) == 0) {
				z0 = destZ;
				local52 = local27 < destZ1 ? local27 : destZ1;
				while (z0 < local52) {
					if ((this.flags[arg5 - this.offsetX][z0 - this.offsetZ] & 0x80) == 0) {
						return true;
					}
					z0++;
				}
			}
		} else if (z1 < destZ1 && local27 >= destZ1) {
			if (local9 == arg0 && (destBlockedSides & 0x8) == 0) {
				for (z0 = z1; z0 < destZ1; z0++) {
					if ((this.flags[local9 - this.offsetX - 1][z0 - this.offsetZ] & 0x8) == 0) {
						return true;
					}
				}
			} else if (local22 == arg5 && (destBlockedSides & 0x2) == 0) {
				for (z0 = z1; z0 < destZ1; z0++) {
					if ((this.flags[arg5 - this.offsetX][z0 - this.offsetZ] & 0x80) == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(III)V")
	public void flagTile(@OriginalArg(0) int startZ, @OriginalArg(2) int startX) {
		@Pc(12) int z = startZ - this.offsetZ;
		@Pc(17) int x = startX - this.offsetX;
		this.flags[x][z] |= 0x200000;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(ZIIIIIIII)Z")
	public boolean isInsideOrOutsideRect(@OriginalArg(1) int destX, @OriginalArg(2) int z, @OriginalArg(3) int x, @OriginalArg(4) int size, @OriginalArg(5) int destWidth, @OriginalArg(6) int arg5, @OriginalArg(7) int destZ, @OriginalArg(8) int destLength) {
		if (size > 1) {
			return this.isInsideRect(size, destWidth, destX, destLength, z, destZ, size, x) ? true : this.isOutsideRect(destX, destZ, destWidth, arg5, destLength, x, z, size, size);
		}
		@Pc(41) int destX1 = destWidth + destX - 1;
		@Pc(47) int destZ1 = destZ + destLength - 1;
		if (destX <= x && destX1 >= x && destZ <= z && z <= destZ1) {
			return true;
		} else if (destX - 1 == x && z >= destZ && z <= destZ1 && (this.flags[x - this.offsetX][z - this.offsetZ] & 0x8) == 0 && (arg5 & 0x8) == 0) {
			return true;
		} else if (x == destX1 + 1 && destZ <= z && destZ1 >= z && (this.flags[x - this.offsetX][z - this.offsetZ] & 0x80) == 0 && (arg5 & 0x2) == 0) {
			return true;
		} else if (z == destZ - 1 && destX <= x && destX1 >= x && (this.flags[x - this.offsetX][z - this.offsetZ] & 0x2) == 0 && (arg5 & 0x4) == 0) {
			return true;
		} else {
			return z == destZ1 + 1 && x >= destX && destX1 >= x && (this.flags[x - this.offsetX][z - this.offsetZ] & 0x20) == 0 && (arg5 & 0x1) == 0;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IBI)V")
	public void unflagGroundDecor(@OriginalArg(0) int startZ, @OriginalArg(2) int startX) {
		@Pc(4) int x = startX - this.offsetX;
		@Pc(9) int z = startZ - this.offsetZ;
		this.flags[x][z] &= 0xFFFBFFFF;
	}

	@OriginalMember(owner = "client!mj", name = "b", descriptor = "(IIIII)Z")
	public boolean method3054(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 == arg2 && arg0 == arg1) {
			return true;
		}
		@Pc(22) int local22 = arg1 - this.offsetZ;
		@Pc(33) int local33 = arg2 - this.offsetX;
		if (local33 < 0 || this.width <= local33 || local22 < 0 || local22 >= this.length) {
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
	private void unflag(@OriginalArg(2) int x, @OriginalArg(1) int z, @OriginalArg(3) int flags) {
		this.flags[x][z] &= ~flags;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZIII)V")
	public void unflagScenery(@OriginalArg(1) int startX, @OriginalArg(2) int width, @OriginalArg(3) boolean blockProjectiles, @OriginalArg(4) int angle, @OriginalArg(5) int length, @OriginalArg(6) int startZ) {
		@Pc(6) int x = startX - this.offsetX;
		@Pc(11) int z = startZ - this.offsetZ;
		@Pc(13) int flags = 256;
		if (blockProjectiles) {
			flags = 131328;
		}
		@Pc(40) int temp;
		if (angle == 1 || angle == 3) {
			temp = width;
			width = length;
			length = temp;
		}
		for (temp = x; temp < x + width; temp++) {
			if (temp >= 0 && temp < this.width) {
				for (@Pc(61) int z0 = z; z0 < length + z; z0++) {
					if (z0 >= 0 && this.length > z0) {
						this.unflag(temp, z0, flags);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "b", descriptor = "(III)V")
	public void method3057(@OriginalArg(0) int startX, @OriginalArg(1) int startZ) {
		@Pc(4) int z = startZ - this.offsetZ;
		@Pc(17) int x = startX - this.offsetX;
		this.flags[x][z] |= 0x40000;
	}
}
