package com.alevel.datewizard.service;

import com.alevel.datewizard.model.DateOfYear;
import com.alevel.datewizard.model.DayOfWeek;
import com.alevel.datewizard.model.Month;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceFactoryTest {

    @Test
    void whenDoesNotDivideBy4_returnsNonLeap() {
        assertNonLeap(2019, DayOfWeek.TUE);
        assertNonLeap(2017, DayOfWeek.FRI);
        assertNonLeap(1, DayOfWeek.SUN);
        assertNonLeap(-3, DayOfWeek.WED);
    }

    @Test
    void whenDividesBy4ButNot100_returnsLeap() {
        assertLeap(2016, DayOfWeek.MON);
        assertLeap(0, DayOfWeek.SAT);
        assertLeap(-4, DayOfWeek.THR);
    }

    @Test
    void whenDividesBy100_returnsNonLeap() {
        assertNonLeap(100, DayOfWeek.MON);
        assertNonLeap(1000, DayOfWeek.MON);
        assertNonLeap(-100, DayOfWeek.MON);
        assertNonLeap(2200, DayOfWeek.MON);
    }

    @Test
    void whenDividesBy400_returnsLeap() {
        assertLeap(2000, DayOfWeek.MON);
        assertLeap(400, DayOfWeek.MON);
        assertLeap(0, DayOfWeek.MON);
        assertLeap(-400, DayOfWeek.MON);
    }

    private static void assertNonLeap(int year, DayOfWeek januaryFirst) {
        DateOfYear day60 = getDay60(year, januaryFirst);
        assertEquals(1, day60.getDayOfMonth());
        assertEquals(Month.MAR, day60.getMonth());
    }

    private static void assertLeap(int year, DayOfWeek januaryFirst) {
        DateOfYear day60 = getDay60(year, januaryFirst);
        assertEquals(29, day60.getDayOfMonth());
        assertEquals(Month.FEB, day60.getMonth());
    }

    private static DateOfYear getDay60(int year, DayOfWeek januaryFirst) {
        DateService dateService = DateServiceFactory.forYear(year, januaryFirst);
        return dateService.getDateOfYear(60);
    }
}