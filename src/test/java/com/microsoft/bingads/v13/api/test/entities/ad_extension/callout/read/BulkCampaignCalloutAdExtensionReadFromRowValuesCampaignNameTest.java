package com.microsoft.bingads.v13.api.test.entities.ad_extension.callout.read;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.microsoft.bingads.internal.functionalinterfaces.Function;
import com.microsoft.bingads.v13.api.test.entities.ad_extension.callout.BulkCampaignCalloutAdExtensionTest;
import com.microsoft.bingads.v13.bulk.entities.BulkCampaignCalloutAdExtension;

public class BulkCampaignCalloutAdExtensionReadFromRowValuesCampaignNameTest extends BulkCampaignCalloutAdExtensionTest {
    @Parameterized.Parameter(value = 1)
    public String expectedResult;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Campaign Name String", "Campaign Name String"},
                {"", ""},
                {null, null}
        });
    }

    @Test
    public void testRead() {
        this.testReadProperty("Campaign", this.datum, this.expectedResult, new Function<BulkCampaignCalloutAdExtension, String>() {
            @Override
            public String apply(BulkCampaignCalloutAdExtension c) {
                return c.getCampaignName();
            }
        });
    }
}
