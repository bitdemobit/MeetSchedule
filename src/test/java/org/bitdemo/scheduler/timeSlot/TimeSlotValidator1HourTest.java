package org.bitdemo.scheduler.timeSlot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;


import static org.junit.jupiter.api.Assertions.*;


class TimeSlotValidator1HourTest {

    @Test
    @DisplayName("1h slot starting at the beginning of the hour")
    void isValid1HIn() {
        TimeSlotValidator1Hour validator = new TimeSlotValidator1Hour();

        LocalDate localDate = LocalDate.of(2023, 9, 16);
        ZoneId zoneId = ZoneId.of("Europe/Lisbon");

        ZonedDateTime start = ZonedDateTime.of( localDate, LocalTime.of(13,  0, 0), zoneId );
        ZonedDateTime end   = ZonedDateTime.of( localDate, LocalTime.of(14,  0, 0), zoneId );

        assertTrue(validator.isValid(start, end));
    }


    @Test
    @DisplayName("1h slot not starting at the beginning of the hour")
    void isValid1HOut() {
        TimeSlotValidator1Hour validator = new TimeSlotValidator1Hour();

        LocalDate localDate = LocalDate.of(2023, 9, 16);
        ZoneId zoneId = ZoneId.of("Europe/Lisbon");

        ZonedDateTime start = ZonedDateTime.of( localDate, LocalTime.of(13,  1, 0), zoneId );
        ZonedDateTime end   = ZonedDateTime.of( localDate, LocalTime.of(14,  1, 0), zoneId );
        assertFalse(validator.isValid(start, end));

        ZonedDateTime start1 = ZonedDateTime.of( localDate, LocalTime.of(13,  0, 1), zoneId );
        ZonedDateTime end1   = ZonedDateTime.of( localDate, LocalTime.of(14,  0, 1), zoneId );
        assertFalse(validator.isValid(start1, end1));

    }


    @Test
    @DisplayName("1h slot starting at the beginning of 23h")
    void isValid1HInDifDays() {
        TimeSlotValidator1Hour validator = new TimeSlotValidator1Hour();

        LocalDate localDate = LocalDate.of(2023, 9, 16);
        ZoneId zoneId = ZoneId.of("Europe/Lisbon");

        ZonedDateTime start = ZonedDateTime.of( localDate, LocalTime.of(23,  0, 0), zoneId );
        LocalDate localDate1 = LocalDate.of(2023, 9, 17);
        ZonedDateTime end   = ZonedDateTime.of( localDate1, LocalTime.of(0,  0, 0), zoneId );
        assertTrue(validator.isValid(start, end));

        LocalDate localDate2 = LocalDate.of(2023, 9, 16);
        ZonedDateTime start3 = ZonedDateTime.of( localDate2, LocalTime.of(23,  0, 0), zoneId );
        LocalDate localDate3 = LocalDate.of(2023, 9, 16);
        ZonedDateTime end3   = ZonedDateTime.of( localDate3, LocalTime.of(0,  0, 0), zoneId );
        assertFalse(validator.isValid(start3, end3));

    }


}