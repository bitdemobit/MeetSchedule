package org.bitdemo.scheduler.timeSlot;

import java.time.ZonedDateTime;

public interface TimeSlotValidator {

    boolean isValid(ZonedDateTime start, ZonedDateTime end);
}
