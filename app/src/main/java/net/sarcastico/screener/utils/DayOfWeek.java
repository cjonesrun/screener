package net.sarcastico.screener.utils;

import java.util.Calendar;

/**
 * DayOfWeek enum
 */

public enum DayOfWeek
{
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY), WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY), FRIDAY(Calendar.FRIDAY), SATURDAY(Calendar.SATURDAY), SUNDAY(Calendar.SUNDAY);

    private int code;
    DayOfWeek(int code) {
        this.code = code;
    }

    public static DayOfWeek fromCode(int code) {
        for (DayOfWeek d : DayOfWeek.values())
            if (d.code == code)
                return d;
        return null;
    }

    public static DayOfWeek today() {
        return fromCode(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    }
}