package com.microsoft.bingads.v13.api.test.entities.campaign_negative_store_criterion.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v13.api.test.entities.campaign_negative_store_criterion.BulkCampaignNegativeStoreCriterionTest;
import com.microsoft.bingads.v13.bulk.entities.BulkCampaignNegativeStoreCriterion;

@RunWith(Parameterized.class)
public class BulkCampaignNegativeStoreCriterionWriteIdTest extends BulkCampaignNegativeStoreCriterionTest {

    @Parameter(value = 1)
    public Long propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"123", 123L},
                        {"9223372036854775807", 9223372036854775807L},
                        {null, null}
                }
        );
    }

    @Test
    public void testWrite() {
        testWriteProperty(
                "Id",
                datum,
                propertyValue,
                new BiConsumer<BulkCampaignNegativeStoreCriterion, Long>() {
                    @Override
                    public void accept(BulkCampaignNegativeStoreCriterion c, Long v) {
                        c.getNegativeCampaignCriterion().setId(v);
                    }
                }
        );
    }
}
