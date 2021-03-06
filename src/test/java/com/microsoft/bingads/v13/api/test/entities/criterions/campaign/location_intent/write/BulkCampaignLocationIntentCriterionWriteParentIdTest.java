package com.microsoft.bingads.v13.api.test.entities.criterions.campaign.location_intent.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v13.api.test.entities.criterions.campaign.location_intent.BulkCampaignLocationIntentCriterionTest;
import com.microsoft.bingads.v13.bulk.entities.BulkCampaignLocationIntentCriterion;

@RunWith(Parameterized.class)
public class BulkCampaignLocationIntentCriterionWriteParentIdTest extends BulkCampaignLocationIntentCriterionTest {

    @Parameter(value = 1)
    public Long propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"123", 123L},
                        {"9223372036854775807", 9223372036854775807L},
                }
        );
    }

    @Test
    public void testWrite() {
        testWriteProperty(
                "Parent Id",
                datum,
                propertyValue,
                new BiConsumer<BulkCampaignLocationIntentCriterion, Long>() {
                    @Override
                    public void accept(BulkCampaignLocationIntentCriterion c, Long v) {
                        c.getBiddableCampaignCriterion().setCampaignId(v);
                    }
                }
        );
    }
}
