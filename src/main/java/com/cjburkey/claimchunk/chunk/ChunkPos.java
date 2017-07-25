package com.cjburkey.claimchunk.chunk;

import java.util.regex.Pattern;

public final class ChunkPos {
	
	private final String world;
	private final int x;
	private final int z;
	
	public ChunkPos(String world, int x, int z) {
		this.world = world;
		this.x = x;
		this.z = z;
	}
	
	public String toString() {
		return world + "," + x + "," + z;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		result = prime * result + x;
		result = prime * result + z;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunkPos other = (ChunkPos) obj;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		if (x != other.x)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public static ChunkPos fromString(String in) {
		String[] split = in.split(Pattern.quote(","));
		if(split.length == 3) {
			try {
				int x = Integer.parseInt(split[1].trim());
				int z = Integer.parseInt(split[2].trim());
				return new ChunkPos(split[0], x, z);
			} catch(Exception e) {
			}
		}
		return null;
	}
	
}