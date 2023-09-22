package org.bitdemo.scheduler;

import org.bitdemo.scheduler.timeSlot.TimeSlot;
import org.bitdemo.scheduler.timeSlot.TimeSlotValidator;
import org.bitdemo.scheduler.timeSlot.TimeSlotValidator1Hour;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    @Test
    @DisplayName("find overlaps")
    public void findOverLaps(){

        LocalDate localDate = LocalDate.of(2023, 9, 16);
        ZoneId zoneId = ZoneId.of("Europe/Lisbon");

        LinkedList<List<TimeSlot>> list = new LinkedList<List<TimeSlot>>();
        TimeSlotValidator validator = new TimeSlotValidator1Hour();

        LinkedList<TimeSlot> list1 = new LinkedList<>();
        list1.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(13,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(14,  0, 0), zoneId ),
                                 validator));

        list1.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(2,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(3,  0, 0), zoneId ),
                                 validator));

        list1.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(18,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(19,  0, 0), zoneId ),
                                 validator));
        list.add(list1);


        LinkedList<TimeSlot> list2 = new LinkedList<>();
        list2.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(6,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(7,  0, 0), zoneId ),
                                 validator));

        list2.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(19,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(20,  0, 0), zoneId ),
                                 validator));

        list2.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(2,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(3,  0, 0), zoneId ),
                                 validator));
        list.add(list2);

        LinkedList<TimeSlot> list3 = new LinkedList<>();
        list3.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(14,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(15,  0, 0), zoneId ),
                                 validator));

        list3.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(2,  0, 0), zoneId ),
                                 ZonedDateTime.of( localDate, LocalTime.of(3,  0, 0), zoneId ),
                                 validator));
        list.add(list3);



        LinkedList<TimeSlot> res = new LinkedList<>();
        res.add(  new TimeSlot(ZonedDateTime.of( localDate, LocalTime.of(2,  0, 0), zoneId ),
                               ZonedDateTime.of( localDate, LocalTime.of(3,  0, 0), zoneId ),
                               validator));

        assertEquals(   new HashSet<>(res), new HashSet<>(Scheduler.findOverlaps(list)));

    }

}