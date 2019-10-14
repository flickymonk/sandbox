package com.alevel.datewizard.service.impl;

import com.alevel.datewizard.model.DateOfYear;
import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeapYearDateServiceTest {

    private LeapYearDateService defaultService;

    @BeforeEach
    void setUp() {
        defaultService = new LeapYearDateService(DayOfWeek.MON);
    }

    @Test
    void upperThresholdIs366() {
        assertEquals(366, defaultService.getUpperThreshold());
    }

    @Test
    void getMonthLengthShouldReturnValueForLeapYear() {
        assertEquals(31, defaultService.getMonthLength(Month.JAN));
        assertEquals(29, defaultService.getMonthLength(Month.FEB));
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
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(367));
        assertThrows(IllegalArgumentException.class, () -> defaultService.getDateOfYear(368));
    }

    @Test
    void whenDayIs60_AndJan1Mon_shouldBeThuFeb39() {
        assertDateOfYear(new DateOfYear(29, Month.FEB, DayOfWeek.THU), 60);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.FRI), 61);
    }

    @Test
    void whenDayIs366_AndJan1Mon_shouldBeTueDec31() {
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.TUE), 366);
    }

    @Test
    void shouldAdjustWeekDayBasedOnFirstDay() {
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.FRI), 61, DayOfWeek.MON);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.SAT), 61, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.SUN), 61, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.MON), 61, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.TUE), 61, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.WED), 61, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(1, Month.MAR, DayOfWeek.THU), 61, DayOfWeek.SUN);

        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.WED), 366, DayOfWeek.TUE);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.THU), 366, DayOfWeek.WED);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.FRI), 366, DayOfWeek.THU);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.SAT), 366, DayOfWeek.FRI);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.SUN), 366, DayOfWeek.SAT);
        assertDateOfYear(new DateOfYear(31, Month.DEC, DayOfWeek.MON), 366, DayOfWeek.SUN);
    }

    private void assertDateOfYear(DateOfYear expected, int day) {
        assertDateOfYear(expected, day, defaultService);
    }

    private static void assertDateOfYear(DateOfYear expected, int day, DayOfWeek januaryFirst) {
        assertDateOfYear(expected, day, new LeapYearDateService(januaryFirst));
    }

    private static void assertDateOfYear(DateOfYear expected, int day, LeapYearDateService service) {
        assertEquals(expected, service.getDateOfYear(day));
    }
}