package com.du11.coreapi.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils(){}

    public static LocalDate convertStringToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().equals(""))
            return null;
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
    }
}
