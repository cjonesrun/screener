package net.sarcastico.screener.rules;

import android.support.annotation.NonNull;

import org.easyrules.annotation.Priority;
import org.easyrules.api.Rule;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by cj on 2017-05-03.
 */

public class TimeOfDayBlackoutRule extends BaseRule {

    private int startHour, startMin, endHour, endMin;
    int priority = 2;
    String name;

    public TimeOfDayBlackoutRule() {
        this(0,0,23,59);
    }

    public TimeOfDayBlackoutRule(int sh, int sm, int eh, int em) {
        this(2,sh,sm,eh,em);
    }
    public TimeOfDayBlackoutRule(int priority, int sh, int sm, int eh, int em) {
        this.priority = priority;
        this.startHour = sh;
        this.startMin = sm;
        this.endHour = eh;
        this.endMin = em;

        this.name = this.getClass().getSimpleName() + UUID.randomUUID().toString();
    }

    public String toString() {
        return "[priority=" + getPriority() + " start=" + startHour + ":" + startMin + " end="+endHour + ":" + endMin+"]";
    }

    private Calendar now;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Rule: " + toString();
    }

    @Priority
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean evaluate() {
        now = Calendar.getInstance();
        Calendar start = (Calendar) now.clone();
        Calendar end = (Calendar) now.clone();
        start.set(Calendar.HOUR_OF_DAY,startHour);
        start.set(Calendar.MINUTE, startMin);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        end.set(Calendar.HOUR_OF_DAY,endHour);
        end.set(Calendar.MINUTE, endMin);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);

        System.out.println("blackout time: "   +toString());
        return (now.after(start) && now.before(end));
    }

    @Override
    public void execute() throws Exception {
        //throw new BlackedOutException("caused by blackout rule: " + this.toString());
        System.out.println( "caused by blackout rule: " + this.toString() );
    }
}
