package itzshmulik.survivelist.survivelistchristmas.model;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a spawn gift to find.
 *
 * @since 1.0.0
 * @author ms5984
 */
public record GiftLocation(int x, int y, int z) {
    /**
     * Construct a validated gift location.
     *
     * @param x block x
     * @param y block y
     * @param z block z
     * @throws IllegalArgumentException if <code>x</code>, <code>y</code>
     * or <code>z</code> is out of defined range
     */
    public GiftLocation(int x, int y, int z) {
        this.x = validateXZ(x);
        this.z = validateXZ(z);
        this.y = validateY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftLocation that = (GiftLocation) o;
        return x == that.x &&
                y == that.y &&
                z == that.z;
    }

    @Override
    public int hashCode() {
        return (x * 71) ^ (y * 1048573) ^ (z * 71);
    }

    static int validateXZ(int xz) {
        if (xz > 30_000_000 || xz < -30_000_000) throw new IllegalArgumentException();
        return xz;
    }

    static int validateY(int y) {
        if (y > 319 || y < -64) throw new IllegalArgumentException();
        return y;
    }

    /**
     * Get a new gift location from an existing block for comparison.
     *
     * @param block an existing block
     * @return a new gift location
     */
    public static GiftLocation from(@NotNull Block block) {
        return new GiftLocation(block.getX(), block.getY(), block.getZ());
    }
}
