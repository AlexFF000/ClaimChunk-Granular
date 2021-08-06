package com.cjburkey.claimchunk.config.spread;

import com.cjburkey.claimchunk.config.ccconfig.CCConfig;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.UUID;

public class FullSpreadProfile extends SpreadProfile {

    private final String str_inClaimed;
    private final String str_inUnclaimed;

    public boolean inClaimed = true;
    public boolean inUnclaimed = true;

    public FullSpreadProfile(@NotNull String key) {
        super(key);

        str_inClaimed = key + ".from_claimed.into_same_claimed";
        str_inUnclaimed = key + ".from_unclaimed.into_unclaimed";
    }

    // Clone
    public FullSpreadProfile(FullSpreadProfile other) {
        super(other);

        this.str_inClaimed = other.str_inClaimed;
        this.str_inUnclaimed = other.str_inUnclaimed;

        this.inClaimed = other.inClaimed;
        this.inUnclaimed = other.inUnclaimed;
    }

    // TODO: Double check this?
    @Override
    public boolean getShouldCancel(@NotNull UUID sourceOwner,
                                   @NotNull UUID newOwner) {
        if (Objects.equals(sourceOwner, newOwner)) {
            // Disable block spread from unclaimed chunks to unclaimed chunks
            if (!inUnclaimed && sourceOwner == null) return true;

            // Disable block spread from claimed chunks into the same owner's chunks
            if (!inClaimed && sourceOwner != null) return true;
        }

        // Defer to general spread protection
        return super.getShouldCancel(sourceOwner, newOwner);
    }

    @Override
    public void toCCConfig(CCConfig config) {
        super.toCCConfig(config);

        config.set(str_inClaimed, inClaimed);
        config.set(str_inUnclaimed, inUnclaimed);
    }

    @Override
    public void fromCCConfig(CCConfig config) {
        super.fromCCConfig(config);

        inClaimed = config.getBool(str_inClaimed, inClaimed);
        inUnclaimed = config.getBool(str_inUnclaimed, inUnclaimed);
    }

}
