package org.bitdemo.scheduler.timeSlot;
import java.time.ZonedDateTime;

/**
 * Universal time slot validator, i.e., it considers any time slot as valid.
 */
public class TimeSlotValidatorUniversal implements TimeSlotValidator {

    @Override
    public boolean isValid(ZonedDateTime start, ZonedDateTime end) {
        return true;
    }
}
