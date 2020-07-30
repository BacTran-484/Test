package com.du11.coreapi.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void convertStringToDate_InputValid() {
        LocalDate expectedDate = DateUtils.convertStringToDate("1997-07-16");
        assertEquals(1997, expectedDate.getYear());
        assertEquals(7, expectedDate.getMonthValue());
        assertEquals(16, expectedDate.getDayOfMonth());
    }

    @Test
    void convertStringToDate_InputNull() {
        LocalDate expectedDate = DateUtils.convertStringToDate(null);
        assertNull(expectedDate);
    }

    @Test
    void convertStringToDate_InputEmpty() {
        LocalDate expectedDate = DateUtils.convertStringToDate("");
        assertNull(expectedDate);
    }
}