package com.jagex.game.collision;

import com.jagex.core.constants.LocShapes;
import com.jagex.game.runetek4.config.loctype.LocType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static com.jagex.game.collision.CollisionFlag.*;
import static com.jagex.game.collision.CollisionFlag.WALL_SOUTH_AND_WEST_BLOCKRANGE;
import static com.jagex.game.collision.DirectionFlag.*;

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
	public void unflagWall(@OriginalArg(0) int rotation, @OriginalArg(1) boolean blockrange, @OriginalArg(3) int tileZ, @OriginalArg(4) int shape, @OriginalArg(5) int tileX) {
		@Pc(4) int x = tileX - this.offsetX;
		@Pc(23) int z = tileZ - this.offsetZ;
		if (shape == LocShapes.WALL_STRAIGHT) {
			if (rotation == 0) {
				this.unflag(x, z, WALL_WEST);
				this.unflag(x - 1, z, WALL_EAST);
			}
			if (rotation == 1) {
				this.unflag(x, z, WALL_NORTH);
				this.unflag(x, z + 1, WALL_SOUTH);
			}
			if (rotation == 2) {
				this.unflag(x, z, WALL_EAST);
				this.unflag(x + 1, z, WALL_WEST);
			}
			if (rotation == 3) {
				this.unflag(x, z, WALL_SOUTH);
				this.unflag(x, z - 1, WALL_NORTH);
			}
		}
		if (shape == LocShapes.WALL_DIAGONALCORNER || shape == LocShapes.WALL_SQUARECORNER) {
			if (rotation == 0) {
				this.unflag(x, z, WALL_NORTH_WEST);
				this.unflag(x + -1, z + 1, WALL_SOUTH_EAST);
			}
			if (rotation == 1) {
				this.unflag(x, z, WALL_NORTH_EAST);
				this.unflag(x + 1, z + 1, WALL_SOUTH_WEST);
			}
			if (rotation == 2) {
				this.unflag(x, z, WALL_SOUTH_EAST);
				this.unflag(x - -1, z - 1, WALL_NORTH_WEST);
			}
			if (rotation == 3) {
				this.unflag(x, z, WALL_SOUTH_WEST);
				this.unflag(x + -1, z - 1, WALL_NORTH_EAST);
			}
		}
		if (shape == LocShapes.WALL_L) {
			if (rotation == 0) {
				this.unflag(x, z, WALL_NORTH_AND_WEST);
				this.unflag(x - 1, z, WALL_EAST);
				this.unflag(x, z + 1, WALL_SOUTH);
			}
			if (rotation == 1) {
				this.unflag(x, z, WALL_NORTH_AND_EAST);
				this.unflag(x, z + 1, WALL_SOUTH);
				this.unflag(x + 1, z, WALL_WEST);
			}
			if (rotation == 2) {
				this.unflag(x, z, WALL_SOUTH_AND_EAST);
				this.unflag(x + 1, z, WALL_WEST);
				this.unflag(x, z - 1, WALL_NORTH);
			}
			if (rotation == 3) {
				this.unflag(x, z, WALL_SOUTH_AND_WEST);
				this.unflag(x, z - 1, WALL_NORTH);
				this.unflag(x - 1, z, WALL_EAST);
			}
		}
		if (!blockrange) {
			return;
		}
		if (shape == LocShapes.WALL_STRAIGHT) {
			if (rotation == 0) {
				this.unflag(x, z, WALL_WEST_BLOCKRANGE);
				this.unflag(x - 1, z, WALL_EAST_BLOCKRANGE);
			}
			if (rotation == 1) {
				this.unflag(x, z, WALL_NORTH_BLOCKRANGE);
				this.unflag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
			}
			if (rotation == 2) {
				this.unflag(x, z, WALL_EAST_BLOCKRANGE);
				this.unflag(x + 1, z, WALL_WEST_BLOCKRANGE);
			}
			if (rotation == 3) {
				this.unflag(x, z, WALL_SOUTH_BLOCKRANGE);
				this.unflag(x, z - 1, WALL_NORTH_BLOCKRANGE);
			}
		}
		if (shape == LocShapes.WALL_DIAGONALCORNER || shape == LocShapes.WALL_SQUARECORNER) {
			if (rotation == 0) {
				this.unflag(x, z, WALL_NORTH_WEST_BLOCKRANGE);
				this.unflag(x + -1, z + 1, WALL_SOUTH_EAST_BLOCKRANGE);
			}
			if (rotation == 1) {
				this.unflag(x, z, WALL_NORTH_EAST_BLOCKRANGE);
				this.unflag(x - -1, z + 1, WALL_SOUTH_WEST_BLOCKRANGE);
			}
			if (rotation == 2) {
				this.unflag(x, z, WALL_SOUTH_EAST_BLOCKRANGE);
				this.unflag(x + 1, z - 1, WALL_NORTH_WEST_BLOCKRANGE);
			}
			if (rotation == 3) {
				this.unflag(x, z, WALL_SOUTH_WEST_BLOCKRANGE);
				this.unflag(x + -1, z - 1, WALL_NORTH_EAST_BLOCKRANGE);
			}
		}
		if (shape != LocShapes.WALL_L) {
			return;
		}
		if (rotation == 0) {
			this.unflag(x, z, WALL_NORTH_AND_WEST_BLOCKRANGE);
			this.unflag(x - 1, z, WALL_EAST_BLOCKRANGE);
			this.unflag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
		}
		if (rotation == 1) {
			this.unflag(x, z, WALL_NORTH_AND_EAST_BLOCKRANGE);
			this.unflag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
			this.unflag(x + 1, z, WALL_WEST_BLOCKRANGE);
		}
		if (rotation == 2) {
			this.unflag(x, z, WALL_SOUTH_AND_EAST_BLOCKRANGE);
			this.unflag(x + 1, z, WALL_WEST_BLOCKRANGE);
			this.unflag(x, z - 1, WALL_NORTH_BLOCKRANGE);
		}
		if (rotation == 3) {
			this.unflag(x, z, WALL_SOUTH_AND_WEST_BLOCKRANGE);
			this.unflag(x, z - 1, WALL_NORTH_BLOCKRANGE);
			this.unflag(x - 1, z, WALL_EAST_BLOCKRANGE);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZII)V")
	public void flagWall(@OriginalArg(5) int tileX, @OriginalArg(4) int tileZ, @OriginalArg(2) int shape, @OriginalArg(0) int rotation, @OriginalArg(3) boolean blockrange) {
		@Pc(4) int x = tileX - this.offsetX;
		@Pc(13) int z = tileZ - this.offsetZ;
		if (shape == LocType.WALL_STRAIGHT) {
			if (rotation == 0) {
				this.flag(x, z, WALL_WEST);
				this.flag(x - 1, z, WALL_EAST);
			}
			if (rotation == 1) {
				this.flag(x, z, WALL_NORTH);
				this.flag(x, z + 1, WALL_SOUTH);
			}
			if (rotation == 2) {
				this.flag(x, z, WALL_EAST);
				this.flag(x + 1, z, WALL_WEST);
			}
			if (rotation == 3) {
				this.flag(x, z,WALL_SOUTH);
				this.flag(x, z - 1, WALL_NORTH);
			}
		}
		if (shape == LocType.WALL_DIAGONAL_CORNER || shape == LocType.WALL_SQUARE_CORNER) {
			if (rotation == 0) {
				this.flag(x, z, WALL_NORTH_WEST);
				this.flag(x - 1, z + 1, WALL_SOUTH_EAST);
			}
			if (rotation == 1) {
				this.flag(x, z, WALL_NORTH_EAST);
				this.flag(x + 1, z + 1, WALL_SOUTH_WEST);
			}
			if (rotation == 2) {
				this.flag(x, z, WALL_SOUTH_EAST);
				this.flag(x + 1, z - 1, WALL_NORTH_WEST);
			}
			if (rotation == 3) {
				this.flag(x, z, WALL_SOUTH_WEST);
				this.flag(x - 1, z - 1, WALL_NORTH_EAST);
			}
		}
		if (shape == LocType.WALL_L) {
			if (rotation == 0) {
				this.flag(x, z, WALL_NORTH_AND_WEST);
				this.flag(x - 1, z, WALL_EAST);
				this.flag(x, z + 1, WALL_SOUTH);
			}
			if (rotation == 1) {
				this.flag(x, z, WALL_NORTH_AND_EAST);
				this.flag(x, z + 1, WALL_SOUTH);
				this.flag(x + 1, z, WALL_WEST);
			}
			if (rotation == 2) {
				this.flag(x, z, WALL_SOUTH_AND_EAST);
				this.flag(x + 1, z, WALL_WEST);
				this.flag(x, z - 1, WALL_NORTH);
			}
			if (rotation == 3) {
				this.flag(x, z, WALL_SOUTH_AND_WEST);
				this.flag(x, z - 1, WALL_NORTH);
				this.flag(x - 1, z, WALL_EAST);
			}
		}
		if (!blockrange) {
			return;
		}
		if (shape == LocType.WALL_STRAIGHT) {
			if (rotation == 0) {
				this.flag(x, z, WALL_WEST_BLOCKRANGE);
				this.flag(x - 1, z, WALL_EAST_BLOCKRANGE);
			}
			if (rotation == 1) {
				this.flag(x, z, WALL_NORTH_BLOCKRANGE);
				this.flag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
			}
			if (rotation == 2) {
				this.flag(x, z, WALL_EAST_BLOCKRANGE);
				this.flag(x + 1, z, WALL_WEST_BLOCKRANGE);
			}
			if (rotation == 3) {
				this.flag(x, z, WALL_SOUTH_BLOCKRANGE);
				this.flag(x, z - 1, WALL_NORTH_BLOCKRANGE);
			}
		}
		if (shape == LocType.WALL_DIAGONAL_CORNER || shape == LocType.WALL_SQUARE_CORNER) {
			if (rotation == 0) {
				this.flag(x, z, WALL_NORTH_WEST_BLOCKRANGE);
				this.flag(x - 1, z + 1, WALL_SOUTH_EAST_BLOCKRANGE);
			}
			if (rotation == 1) {
				this.flag(x, z, WALL_NORTH_EAST_BLOCKRANGE);
				this.flag(x + 1, z + 1, WALL_SOUTH_WEST_BLOCKRANGE);
			}
			if (rotation == 2) {
				this.flag(x, z, WALL_SOUTH_EAST_BLOCKRANGE);
				this.flag(x + 1, z - 1, WALL_NORTH_WEST_BLOCKRANGE);
			}
			if (rotation == 3) {
				this.flag(x, z, WALL_SOUTH_WEST_BLOCKRANGE);
				this.flag(x - 1, z - 1, WALL_NORTH_EAST_BLOCKRANGE);
			}
		}
		if (shape != LocType.WALL_L) {
			return;
		}
		if (rotation == 0) {
			this.flag(x, z, WALL_NORTH_AND_WEST_BLOCKRANGE);
			this.flag(x - 1, z, WALL_EAST_BLOCKRANGE);
			this.flag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
		}
		if (rotation == 1) {
			this.flag(x, z, WALL_NORTH_AND_EAST_BLOCKRANGE);
			this.flag(x, z + 1, WALL_SOUTH_BLOCKRANGE);
			this.flag(x + 1, z, WALL_WEST_BLOCKRANGE);
		}
		if (rotation == 2) {
			this.flag(x, z, WALL_SOUTH_AND_EAST_BLOCKRANGE);
			this.flag(x + 1, z, WALL_WEST_BLOCKRANGE);
			this.flag(x, z - 1, WALL_NORTH_BLOCKRANGE);
		}
		if (rotation == 3) {
			this.flag(x, z, WALL_SOUTH_AND_WEST_BLOCKRANGE);
			this.flag(x, z - 1, WALL_NORTH_BLOCKRANGE);
			this.flag(x - 1, z, WALL_EAST_BLOCKRANGE);
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIIIIIIB)Z")
	private boolean isInsideRect(@OriginalArg(0) int destWidth, @OriginalArg(1) int width, @OriginalArg(2) int x, @OriginalArg(3) int length, @OriginalArg(4) int destZ, @OriginalArg(5) int z, @OriginalArg(6) int destLength, @OriginalArg(7) int destX) {
		if (width + x > destX && x < destWidth + destX) {
			return length + z > destZ && destLength + destZ > z;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIZIIIII)Z")
	public boolean isAtWall(@OriginalArg(0) int srcZ, @OriginalArg(1) int destX, @OriginalArg(3) int destZ, @OriginalArg(4) int srcX, @OriginalArg(5) int shape, @OriginalArg(6) int size, @OriginalArg(7) int rotation) {
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
			if (shape == LocShapes.WALL_STRAIGHT) {
				if (rotation == 0) {
					if (endX == startX - 1 && endZ == startZ) {
						return true;
					}
					if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (startX == endX && startZ - 1 == endZ && (this.flags[endX][endZ] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (startX == endX && endZ == startZ + 1) {
						return true;
					}
					if (startX - 1 == endX && startZ == endZ && (this.flags[endX][endZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (startX + 1 == endX && endZ == startZ) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (startX == endX && startZ - 1 == endZ && (this.flags[endX][endZ] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 3) {
					if (endX == startX && startZ - 1 == endZ) {
						return true;
					}
					if (startX - 1 == endX && startZ == endZ && (this.flags[endX][endZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (startX + 1 == endX && endZ == startZ && (this.flags[endX][endZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALL_L) {
				if (rotation == 0) {
					if (endX == startX - 1 && startZ == endZ) {
						return true;
					}
					if (endX == startX && endZ == startZ + 1) {
						return true;
					}
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (startX == endX && startZ + 1 == endZ) {
						return true;
					}
					if (endX == startX + 1 && startZ == endZ) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (startX + 1 == endX && startZ == endZ) {
						return true;
					}
					if (endX == startX && startZ - 1 == endZ) {
						return true;
					}
				} else if (rotation == 3) {
					if (endX == startX - 1 && startZ == endZ) {
						return true;
					}
					if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
					if (endX == startX && endZ == startZ - 1) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALL_DIAGONAL) {
				if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & WALL_SOUTH) == 0) {
					return true;
				}
				if (endX == startX && endZ == startZ - 1 && (this.flags[endX][endZ] & WALL_NORTH) == 0) {
					return true;
				}
				if (endX == startX - 1 && endZ == startZ && (this.flags[endX][endZ] & WALL_EAST) == 0) {
					return true;
				}
				if (startX + 1 == endX && startZ == endZ && (this.flags[endX][endZ] & WALL_WEST) == 0) {
					return true;
				}
			}
		} else {
			@Pc(785) int x1 = size + endX - 1;
			@Pc(792) int z1 = endZ + size - 1;
			if (shape == LocShapes.WALL_STRAIGHT) {
				if (rotation == 0) {
					if (endX == startX - size && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ + 1 == endZ && (this.flags[startX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (startX >= endX && startX <= x1 && startZ + 1 == endZ) {
						return true;
					}
					if (endX == startX - size && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ <= startZ && z1 >= startZ && (this.flags[endX][startZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (startX >= endX && x1 >= startX && endZ == startZ + 1 && (this.flags[startX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 3) {
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
					if (endX == startX - size && startZ >= endZ && z1 >= startZ && (this.flags[x1][startZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALL_L) {
				if (rotation == 0) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && endZ == startZ + 1) {
						return true;
					}
					if (startX + 1 == endX && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && endZ == startZ + 1) {
						return true;
					}
					if (startX + 1 == endX && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (startX >= endX && startX <= x1 && endZ == startZ - size && (this.flags[startX][z1] & (WALL | WALL_NORTH)) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (startX - size == endX && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & (WALL | WALL_EAST)) == 0) {
						return true;
					}
					if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (endX == startX + 1 && endZ <= startZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
				} else if (rotation == 3) {
					if (startX - size == endX && startZ >= endZ && startZ <= z1) {
						return true;
					}
					if (endX <= startX && startX <= x1 && startZ + 1 == endZ && (this.flags[startX][endZ] & (WALL | WALL_SOUTH)) == 0) {
						return true;
					}
					if (endX == startX + 1 && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & (WALL | WALL_WEST)) == 0) {
						return true;
					}
					if (endX <= startX && x1 >= startX && startZ - size == endZ) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALL_DIAGONAL) {
				if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & (WALL | WALL_SOUTH)) == 0) {
					return true;
				}
				if (startX >= endX && startX <= x1 && endZ == startZ - size && (this.flags[startX][z1] & (WALL | WALL_NORTH)) == 0) {
					return true;
				}
				if (startX - size == endX && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & (WALL | WALL_EAST)) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ >= endZ && z1 >= startZ && (this.flags[endX][startZ] & (WALL | WALL_WEST)) == 0) {
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
		@Pc(17) int flags = LOCATION;
		if (blocksProjectiles) {
			flags |= LOCATION_BLOCKRANGE;
		}
		for (@Pc(25) int x0 = x; x0 < x + arg3; x0++) {
			if (x0 >= 0 && x0 < this.sizeX) {
				for (@Pc(47) int z0 = z; z0 < arg4 + z; z0++) {
					if (z0 >= 0 && this.sizeZ > z0) {
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
	public boolean isAtDiagonalWallDecor(@OriginalArg(0) int srcZ, @OriginalArg(1) int shape, @OriginalArg(2) int srcX, @OriginalArg(3) int destZ, @OriginalArg(4) int size, @OriginalArg(5) int rotation, @OriginalArg(6) int destX) {
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
			if (shape == LocShapes.WALLDECOR_DIAGONAL_OFFSET || shape == LocShapes.WALLDECOR_DIAGONAL_NOOFFSET) {
				if (shape == LocShapes.WALLDECOR_DIAGONAL_NOOFFSET) {
					rotation = rotation + 2 & 0x3;
				}
				if (rotation == 0) {
					if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & WALL_WEST) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & WALL_NORTH) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (endX == startX - 1 && startZ == endZ && (this.flags[endX][endZ] & WALL_EAST) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ - 1 && (this.flags[endX][endZ] & WALL_NORTH) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (endX == startX - 1 && endZ == startZ && (this.flags[endX][endZ] & WALL_EAST) == 0) {
						return true;
					}
					if (startX == endX && startZ + 1 == endZ && (this.flags[endX][endZ] & WALL_SOUTH) == 0) {
						return true;
					}
				} else if (rotation == 3) {
					if (endX == startX + 1 && endZ == startZ && (this.flags[endX][endZ] & WALL_WEST) == 0) {
						return true;
					}
					if (startX == endX && endZ == startZ + 1 && (this.flags[endX][endZ] & WALL_SOUTH) == 0) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALLDECOR_DIAGONAL_BOTH) {
				if (endX == startX && startZ + 1 == endZ && (this.flags[endX][endZ] & WALL_SOUTH) == 0) {
					return true;
				}
				if (endX == startX && startZ - 1 == endZ && (this.flags[endX][endZ] & WALL_NORTH) == 0) {
					return true;
				}
				if (startX - 1 == endX && endZ == startZ && (this.flags[endX][endZ] & WALL_NORTH) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ == endZ && (this.flags[endX][endZ] & WALL_WEST) == 0) {
					return true;
				}
			}
		} else {
			@Pc(414) int x1 = endX + size - 1;
			@Pc(420) int z1 = endZ + size - 1;
			if (shape == LocShapes.WALLDECOR_DIAGONAL_OFFSET || shape == LocShapes.WALLDECOR_DIAGONAL_NOOFFSET) {
				if (shape == LocShapes.WALLDECOR_DIAGONAL_NOOFFSET) {
					rotation = rotation + 2 & 0x3;
				}
				if (rotation == 0) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & WALL_WEST) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & WALL_NORTH) == 0) {
						return true;
					}
				} else if (rotation == 1) {
					if (startX - size == endX && startZ >= endZ && z1 >= startZ && (this.flags[x1][startZ] & WALL_EAST) == 0) {
						return true;
					}
					if (startX >= endX && x1 >= startX && startZ - size == endZ && (this.flags[startX][z1] & WALL_NORTH) == 0) {
						return true;
					}
				} else if (rotation == 2) {
					if (endX == startX - size && startZ >= endZ && startZ <= z1 && (this.flags[x1][startZ] & WALL_EAST) == 0) {
						return true;
					}
					if (endX <= startX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & WALL_SOUTH) == 0) {
						return true;
					}
				} else if (rotation == 3) {
					if (startX + 1 == endX && endZ <= startZ && startZ <= z1 && (this.flags[endX][startZ] & WALL_WEST) == 0) {
						return true;
					}
					if (startX >= endX && startX <= x1 && endZ == startZ + 1 && (this.flags[startX][endZ] & WALL_SOUTH) == 0) {
						return true;
					}
				}
			}
			if (shape == LocShapes.WALLDECOR_DIAGONAL_BOTH) {
				if (endX <= startX && x1 >= startX && endZ == startZ + 1 && (this.flags[startX][endZ] & WALL_SOUTH) == 0) {
					return true;
				}
				if (startX >= endX && x1 >= startX && endZ == startZ - size && (this.flags[startX][z1] & WALL_NORTH) == 0) {
					return true;
				}
				if (endX == startX - size && endZ <= startZ && z1 >= startZ && (this.flags[x1][startZ] & WALL_EAST) == 0) {
					return true;
				}
				if (endX == startX + 1 && startZ >= endZ && startZ <= z1 && (this.flags[endX][startZ] & WALL_WEST) == 0) {
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
			if (destZ1 == z1 && (destBlockedSides & DirectionFlag.SOUTH) == 0) {
				z0 = arg5;
				local52 = local22 >= local9 ? local9 : local22;
				while (local52 > z0) {
					if ((this.flags[z0 - this.offsetX][destZ1 - this.offsetZ - 1] & WALL_EAST) == 0) {
						return true;
					}
					z0++;
				}
			} else if (local27 == destZ && (destBlockedSides & DirectionFlag.NORTH) == 0) {
				z0 = arg5;
				local52 = local9 > local22 ? local22 : local9;
				while (local52 > z0) {
					if ((this.flags[z0 - this.offsetX][destZ - this.offsetZ] & WALL_SOUTH) == 0) {
						return true;
					}
					z0++;
				}
			}
		} else if (local9 > arg0 && local9 <= local22) {
			if (z1 == destZ1 && (destBlockedSides & DirectionFlag.SOUTH) == 0) {
				for (z0 = arg0; z0 < local9; z0++) {
					if ((this.flags[z0 - this.offsetX][destZ1 - this.offsetZ - 1] & WALL_NORTH) == 0) {
						return true;
					}
				}
			} else if (destZ == local27 && (destBlockedSides & DirectionFlag.NORTH) == 0) {
				for (z0 = arg0; z0 < local9; z0++) {
					if ((this.flags[z0 - this.offsetX][destZ - this.offsetZ] & WALL_SOUTH) == 0) {
						return true;
					}
				}
			}
		} else if (destZ >= z1 && local27 > destZ) {
			if (local9 == arg0 && (destBlockedSides & DirectionFlag.WEST) == 0) {
				z0 = destZ;
				local52 = local27 >= destZ1 ? destZ1 : local27;
				while (z0 < local52) {
					if ((this.flags[local9 - this.offsetX - 1][z0 - this.offsetZ] & WALL_EAST) == 0) {
						return true;
					}
					z0++;
				}
			} else if (arg5 == local22 && (destBlockedSides & DirectionFlag.EAST) == 0) {
				z0 = destZ;
				local52 = local27 < destZ1 ? local27 : destZ1;
				while (z0 < local52) {
					if ((this.flags[arg5 - this.offsetX][z0 - this.offsetZ] & WALL_WEST) == 0) {
						return true;
					}
					z0++;
				}
			}
		} else if (z1 < destZ1 && local27 >= destZ1) {
			if (local9 == arg0 && (destBlockedSides & DirectionFlag.WEST) == 0) {
				for (z0 = z1; z0 < destZ1; z0++) {
					if ((this.flags[local9 - this.offsetX - 1][z0 - this.offsetZ] & WALL_EAST) == 0) {
						return true;
					}
				}
			} else if (local22 == arg5 && (destBlockedSides & DirectionFlag.EAST) == 0) {
				for (z0 = z1; z0 < destZ1; z0++) {
					if ((this.flags[arg5 - this.offsetX][z0 - this.offsetZ] & WALL_WEST) == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(III)V")
	public void flagBlocked(@OriginalArg(0) int startZ, @OriginalArg(2) int startX) {
		@Pc(12) int z = startZ - this.offsetZ;
		@Pc(17) int x = startX - this.offsetX;
		this.flags[x][z] |= BLOCKWALK;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(ZIIIIIIII)Z")
	public boolean isInsideOrOutsideRect(@OriginalArg(1) int destX, @OriginalArg(2) int z, @OriginalArg(3) int x, @OriginalArg(4) int size, @OriginalArg(5) int destWidth, @OriginalArg(6) int direction, @OriginalArg(7) int destZ, @OriginalArg(8) int destLength) {
		if (size > 1) {
			return this.isInsideRect(size, destWidth, destX, destLength, z, destZ, size, x) ? true : this.isOutsideRect(destX, destZ, destWidth, direction, destLength, x, z, size, size);
		}
		@Pc(41) int destX1 = destWidth + destX - 1;
		@Pc(47) int destZ1 = destZ + destLength - 1;
		if (destX <= x && destX1 >= x && destZ <= z && z <= destZ1) {
			return true;
		} else if (destX - 1 == x && z >= destZ && z <= destZ1 && (this.flags[x - this.offsetX][z - this.offsetZ] & WALL_EAST) == 0 && (direction & WEST) == 0) {
			return true;
		} else if (x == destX1 + 1 && destZ <= z && destZ1 >= z && (this.flags[x - this.offsetX][z - this.offsetZ] & WALL_WEST) == 0 && (direction & EAST) == 0) {
			return true;
		} else if (z == destZ - 1 && destX <= x && destX1 >= x && (this.flags[x - this.offsetX][z - this.offsetZ] & WALL_NORTH) == 0 && (direction & SOUTH) == 0) {
			return true;
		} else {
			return z == destZ1 + 1 && x >= destX && destX1 >= x && (this.flags[x - this.offsetX][z - this.offsetZ] & WALL_SOUTH) == 0 && (direction & NORTH) == 0;
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IBI)V")
	public void unflagGroundDecor(@OriginalArg(0) int startZ, @OriginalArg(2) int startX) {
		@Pc(4) int x = startX - this.offsetX;
		@Pc(9) int z = startZ - this.offsetZ;
		this.flags[x][z] &= ~GROUND_DECOR;
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
	private void unflag(@OriginalArg(2) int x, @OriginalArg(1) int z, @OriginalArg(3) int flags) {
		this.flags[x][z] &= ~flags;
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IIIZIII)V")
	public void unflagLoc(@OriginalArg(1) int startX, @OriginalArg(2) int width, @OriginalArg(3) boolean blockProjectiles, @OriginalArg(4) int rotation, @OriginalArg(5) int length, @OriginalArg(6) int startZ) {
		@Pc(6) int x = startX - this.offsetX;
		@Pc(11) int z = startZ - this.offsetZ;
		@Pc(13) int flags = LOCATION;
		if (blockProjectiles) {
			flags |= LOCATION_BLOCKRANGE;
		}
		@Pc(40) int temp;
		if (rotation == 1 || rotation == 3) {
			temp = width;
			width = length;
			length = temp;
		}
		for (temp = x; temp < x + width; temp++) {
			if (temp >= 0 && temp < this.sizeX) {
				for (@Pc(61) int z0 = z; z0 < length + z; z0++) {
					if (z0 >= 0 && this.sizeZ > z0) {
						this.unflag(temp, z0, flags);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "b", descriptor = "(III)V")
	public void flagGroundDecor(@OriginalArg(0) int startX, @OriginalArg(1) int startZ) {
		@Pc(4) int z = startZ - this.offsetZ;
		@Pc(17) int x = startX - this.offsetX;
		this.flags[x][z] |= GROUND_DECOR;
	}
}
