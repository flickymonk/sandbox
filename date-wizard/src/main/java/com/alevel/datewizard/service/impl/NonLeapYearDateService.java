package com.alevel.datewizard.service.impl;

import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;

public final class NonLeapYearDateService extends AbstractDateService {

    public NonLeapYearDateService(DayOfWeek januaryFirst) {
        super(januaryFirst);
    }

    @Override
    int getUpperThreshold() {
        return 365;
    }

    @Override
    int getMonthLength(Month month) {
        return month.getNumberOfDays(false);
    }
}
