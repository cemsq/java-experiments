package cem.wtime;

import org.joda.time.LocalTime;

public class WorkingTime {

    private int timeInMinutes;
    private LocalTime time;

    public WorkingTime(LocalTime time) {
        this.time = new LocalTime(time);

        timeInMinutes = time.getHourOfDay() * 60;
        timeInMinutes += time.getMinuteOfHour();
    }

    public static void parse(String time) {

    }

    public static WorkingTime fromMinutes(long min) {
        LocalTime localTime;
        if (min > 0) {
            long seg = min * 60;
            long millis = seg * 1000;

            localTime = LocalTime.fromMillisOfDay(millis);
        } else {
            localTime = LocalTime.fromMillisOfDay(0);
        }

        return new WorkingTime(localTime);
    }

    public WorkingTime plus(WorkingTime b) {
        long min = this.timeInMinutes + b.timeInMinutes;

        return fromMinutes(min);
    }

    public WorkingTime minus(WorkingTime b) {
        long min = this.timeInMinutes - b.timeInMinutes;

        return fromMinutes(min);
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    @Override
    public String toString() {
        String str = "";
        if (time.getHourOfDay() > 0) {
            str = String.format("%sh", time.getHourOfDay());
        }

        if (time.getMinuteOfHour() > 0) {
            str += String.format(" %sm", time.getMinuteOfHour());
        }

        return str;
    }
}
