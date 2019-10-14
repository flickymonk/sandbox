package com.alevel.datewizard.service.impl;

import com.alevel.datewizard.model.DateOfYear;
import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonLeapYearDateServiceTest {

    private NonLeapYearDateService defaultService;

    @BeforeEach
    void setUp() {
        defaultService = new NonLeapYearDateService(DayOfWeek.MON);
    }

    @Test
    void upperThresholdIs365() {
        assertEquals(365, defaultService.getUpperThreshold());
    }

    @Test
    void getMonthLengthShouldReturnValueForNonLeapYear() {
        assertEquals(31, defaultService.getMonthLength(Month.JAN));
        assertEquals(28, defaultService.getMonthLength(Month.FEB));
        assertEquals(31, defaultService.getMonthLength(Month.MAR));
        assertEquals(30, defaultService.getMonthLength(Month.APR));
        assertEquals(31, defaultService.getMonthLength(Month.MAY));
        assertEquals(30, defaultService.getMonthLength(Month.JUN));
        assertEquals(31, defaultService.getMonthLength(Month.JUL));
        assertEquals(31, defaultService.getMonthLength(Month.AUG));
        assertEquals(30, defaultService.getMonthLength(Month.SEP));
        assertEquals(31, defaultService.getMonthLength(Month.OCT));
        assertEquals(30, defaultService.getMonthLength(Month.NOV));
        assertEquals(31, defaultService.getMonthLength(Month.DEC));
    }

    @Test
    void whenDayNumberLessThanOrZero_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(0));
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(-1));
    }

    @Test
    void whenGreaterThan365_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(366));
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(367));
    }

    @Test
    void whenDayIs60_AndJan1Mon_shouldBeThuMarch1st() {
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.THU), 60);
    }

    @Test
    void whenDayIs365_AndJan1Mon_shouldBeMonDec31() {
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.MON), 365);
    }

    @Test
    void shouldAdjustWeekDayBasedOnFirstDay() {
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.MON), 1, DayOfWeek.MON);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.TUE), 1, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.WED), 1, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.THU), 1, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.FRI), 1, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.SAT), 1, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(1, Month.JAN, DayOfWeek.SUN), 1, DayOfWeek.SUN);

        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.TUE), 2, DayOfWeek.MON);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.WED), 2, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.THU), 2, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.FRI), 2, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.SAT), 2, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.SUN), 2, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(2, Month.JAN, DayOfWeek.MON), 2, DayOfWeek.SUN);

        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.THU), 32, DayOfWeek.MON);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.FRI), 32, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.SAT), 32, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.SUN), 32, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.MON), 32, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.TUE), 32, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(1, Month.FEB, DayOfWeek.WED), 32, DayOfWeek.SUN);

        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.FRI), 61, DayOfWeek.MON);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.SAT), 61, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.SUN), 61, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.MON), 61, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.TUE), 61, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.WED), 61, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(2, Month.MAR, DayOfWeek.THU), 61, DayOfWeek.SUN);

        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.TUE), 365, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.WED), 365, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.THU), 365, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.FRI), 365, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.SAT), 365, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.SUN), 365, DayOfWeek.SUN);
    }

    private void assertDateOfYear(DateOfYear expected, int day) {
        assertDateOfYear(expected, day, defaultService);
    }
    
    private static void assertDateOfYear(DateOfYear expected, int day, DayOfWeek januaryFirst) {
        assertDateOfYear(expected, day, new NonLeapYearDateService(januaryFirst));
    }
    
    private static void assertDateOfYear(DateOfYear expected, int day, NonLeapYearDateService service) {
        assertEquals(expected, service.getDateOfYear(day));
    }
}