package com.microsoft.bingads.v13.api.test.entities.budget.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v13.api.test.entities.budget.BulkBudgetTest;
import com.microsoft.bingads.v13.bulk.entities.BulkBudget;
import com.microsoft.bingads.v13.campaignmanagement.BudgetLimitType;

public class BulkBudgetWriteToRowValuesBudgetTypeTest extends BulkBudgetTest {

    @Parameter(value = 1)
    public BudgetLimitType propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"DailyBudgetAccelerated", BudgetLimitType.DAILY_BUDGET_ACCELERATED},
            {"DailyBudgetStandard", BudgetLimitType.DAILY_BUDGET_STANDARD},
            {null, null}
        });
    }

    @Test
    public void testWrite() {
        this.<BudgetLimitType>testWriteProperty("Budget Type", this.datum, this.propertyValue, new BiConsumer<BulkBudget, BudgetLimitType>() {
            @Override
            public void accept(BulkBudget c, BudgetLimitType v) {
                c.getBudget().setBudgetType(v);;
            }
        });
    }
}
