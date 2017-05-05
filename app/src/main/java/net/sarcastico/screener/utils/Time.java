package net.sarcastico.screener.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Time
 */

public class Time {

    int time;

    private Time(int h, int m, int s)
    {
        time = 60*60*h + 60*m + s;
    }
    private Time(int time) {
        this.time = time;
    }

    public static Time now() {
        Calendar c = Calendar.getInstance();
        return Time.of(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
    }

    public static Time time(int timeInSec) {
        return new Time(timeInSec);
    }
    public static Time of(int h, int m, int s)
    {
        return new Time(h,m,s);
    }

    public static Time of(int h, int m)
    {
        return new Time(h,m,0);
    }

    public int h() { return (time)/(60*60); }
    public int m() { return (time - h()*60*60) / 60; }
    public int s() { return (time - h()*60*60 - m()*60); }

    public boolean isBefore(Time other) {
        return (this.compareTo(other) < 0);
    }

    public boolean isAfter(Time other) {
        return (this.compareTo(other) > 0);
    }

    public Time plusHour(int h) {
        return new Time( wrap(time+60*60*h) % MAX );
    }

    public Time plusMinute(int min) {
        return new Time ( wrap(time+60*min) % MAX );
    }

    public Time plusSecond(int sec) {
        return new Time ( wrap(time+sec) % MAX );
    }

    private int wrap(int x) {
        while (x < 0)
            x += MAX;

        return x;
    }
    public int compareTo(Time t) {
        return this.time - t.time;
    }

    public String toString() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(0,0,0,h(),m(),s()));
    }

    private static int MAX = 24*60*60;
}
