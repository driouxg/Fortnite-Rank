package com.dryoxapps.fortnite_rank.controller.domain;

/**
 * This enum contains platform names.
 *
 * @author Drioux.Guidry
 */
public enum PlayerPlatform {

    /* Personal Computer */
    PC("pc"),

    XBOX("xbl"),

    PSN("psn");

    private String platform;

    /**
     * Private constructor stores the state.
     *
     * @param platform The state.
     */
    private PlayerPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return platform;
    }

    /**
     * Convert from a string to a {@link PlayerPlatform} object.
     *
     * @param platform The platform as a string.
     * @return The found {@link PlayerPlatform} object, or {@code null} if the name doesn't match.
     */
    public static PlayerPlatform fromString(String platform) {
        for (PlayerPlatform pp : PlayerPlatform.values()) {
            if (pp.platform.equals(platform)) {
                return pp;
            }
        }

        return null;
    }
}
