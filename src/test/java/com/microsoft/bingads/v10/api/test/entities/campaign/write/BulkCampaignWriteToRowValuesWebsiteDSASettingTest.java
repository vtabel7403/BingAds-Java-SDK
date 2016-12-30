package com.microsoft.bingads.v10.api.test.entities.campaign.write;

import com.microsoft.bingads.v10.api.test.entities.campaign.BulkCampaignTest;
import com.microsoft.bingads.v10.bulk.entities.BulkCampaign;
import com.microsoft.bingads.v10.campaignmanagement.ArrayOfSetting;
import com.microsoft.bingads.v10.campaignmanagement.CampaignType;
import com.microsoft.bingads.v10.campaignmanagement.DynamicSearchAdsSetting;
import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;

import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class BulkCampaignWriteToRowValuesWebsiteDSASettingTest extends BulkCampaignTest {
    @Parameterized.Parameter(value = 1)
    public String propertyValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", ""},
                {"bing.com", "bing.com"},
                {null, null},
        });
    }

    @Test
    public void testWrite() {
        testWriteProperty("Website", this.datum, this.propertyValue, new BiConsumer<BulkCampaign, String>() {
            @Override
            public void accept(BulkCampaign c, String v) {
                c.getCampaign().setCampaignType(Collections.singletonList(CampaignType.DYNAMIC_SEARCH_ADS));
                c.getCampaign().setSettings(new ArrayOfSetting());
                DynamicSearchAdsSetting setting = new DynamicSearchAdsSetting();
                setting.setDomainName(v);
                c.getCampaign().getSettings().getSettings().add(setting);
            }
        });
    }
}
