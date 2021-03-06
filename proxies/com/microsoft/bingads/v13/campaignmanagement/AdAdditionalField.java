package com.microsoft.bingads.v13.campaignmanagement;

/**
 * Enum class for AdAdditionalField.
 */
public enum AdAdditionalField {

    IMPRESSION_TRACKING_URLS("ImpressionTrackingUrls");
        
    private final String value;

    AdAdditionalField(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AdAdditionalField fromValue(String v) {
        for (AdAdditionalField c : AdAdditionalField.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}