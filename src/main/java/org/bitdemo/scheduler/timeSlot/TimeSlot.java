package org.bitdemo.scheduler.timeSlot;

import java.sql.Time;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TimeSlot {

    private ZonedDateTime start;
    private ZonedDateTime end;


    public TimeSlot(ZonedDateTime start, ZonedDateTime end, TimeSlotValidator validator){
        if( !validator.isValid(start, end) )
            throw new IllegalArgumentException();

        this.start = start;
        this.end = end;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public Duration getDuration(){
        return Duration.between(start, end);
    }

    /**
     * Computes the overlapping duration between this TimeSlot
     * and another TimeSlot t
     * @param t time slot t
     * @return the overlap duration or Duration.Zero either if
     * there is no overlap or if TimeSlot t starts before this
     * TimeSlot starts, or if TimeSlot t starts after this
     * TimeSlot ends
     */
    public Duration overlapDuration(TimeSlot t){
        if( t.getStart().isBefore(this.getStart())  ||
            t.getStart().isAfter(this.getEnd()) ||
            t.getStart().isEqual(this.getEnd()) )
            return Duration.ZERO;

        // TimeSlot t is shorter than this TimeSlot
        if( t.getEnd().isBefore(this.getEnd()) ){
            return Duration.between(this.getEnd(), t.getStart())
                    .minus( Duration.between(t.getEnd(), this.getEnd()) );
        }

        // TimeSlot t fits entirely in this TimeSlot
        return Duration.between(this.getEnd(), t.getStart());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(getStart(), timeSlot.getStart()) && Objects.equals(getEnd(), timeSlot.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }




}
