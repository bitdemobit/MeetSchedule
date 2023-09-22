package org.bitdemo.scheduler;

import org.bitdemo.scheduler.timeSlot.TimeSlot;

import java.time.ZonedDateTime;
import java.util.*;


public class Scheduler {


    /**
     * Find all slots that overlap perfectly, i.e., slots that start and end
     * at exactly the same time and exist in every list of TimeSlot objects
     * passed as argument
     * @param slots a list where each element is a list of TimeSlot
     * @return a list with the TimeSlot objects that exist in every list of
     * TimeSlot objects passed as argument
     */
    public static List<TimeSlot> findOverlaps(List<List<TimeSlot>> slots){

        if(slots.isEmpty())
            return new LinkedList<TimeSlot>();

        if(slots.size() == 1)
            return slots.get(0);

        HashSet<TimeSlot> res = new HashSet<>();

        Iterator<List<TimeSlot>> it = slots.listIterator();
        List<TimeSlot> sl1 = it.next();
        List<TimeSlot> sl2 = it.next();

        HashSet<TimeSlot> temp = new HashSet<>(sl1);
        for(TimeSlot z: sl2){
            if(temp.contains(z))
                res.add(z);
        }
        temp.clear();

        while(it.hasNext()){
            sl2 = it.next();
            temp = new HashSet<>(sl2);
            for(TimeSlot z: res){
                if( !temp.contains(z) )
                    res.remove(z);
            }
        }
        return (List<TimeSlot>)res.stream().toList();
    }



}
