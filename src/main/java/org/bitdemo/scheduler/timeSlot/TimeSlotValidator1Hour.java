package org.bitdemo.scheduler.timeSlot;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Considers time slots starting at the beginning of an hour,
 * and lasting one hour, as valid.
 * Examples of valid time slots: 9h to 10h; 12h to 13h
 * Examples of invalid time slots: 9:01h to 10:01h; 12:30h to 13h
 */
public class TimeSlotValidator1Hour implements TimeSlotValidator{


    @Override
    public boolean isValid(ZonedDateTime start, ZonedDateTime end) {
        if( start.getMinute() != 0 || start.getSecond() != 0 )      // does not start at the beginning of an hour
            return false;

        return Duration.between(start, end).toHours() == 1;         // return true if duration is 1h
    }
}
