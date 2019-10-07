package com.alevel.datewizard.service.impl;

import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;

public final class LeapYearDateService extends AbstractDateService {

    public LeapYearDateService(DayOfWeek januaryFirst) {
        super(januaryFirst);
    }

    @Override
    int getUpperThreshold() {
        return 366;
    }

    @Override
    int getMonthLength(Month month) {
        return month.getNumberOfDays(true);
    }

}
