package net.sarcastico.screener.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

import java.util.Calendar;

/**
 * Created by cj on 2017-05-03.
 */

@Rule
public class BlackoutRule {

    private int startHour, startMin, endHour, endMin;

    public BlackoutRule() {
        this(0,0,23,59);
    }

    public BlackoutRule(int sh, int sm, int eh, int em) {
        this.startHour = sh;
        this.startMin = sm;
        this.endHour = eh;
        this.endMin = em;
    }

    public String toString() {
        return "[start=" + startHour + ":" + startMin + " end="+endHour + ":" + endMin+"]";
    }

    private Calendar now;

    @Condition
    public boolean blackoutInEffect() {
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

        System.out.println(toString() + " " + now.getTime() + " " + start.getTime() + " " + end.getTime());
        return (now.after(start) && now.before(end));
    }

    @Action
    public void printFizz() throws Exception {
        //throw new BlackedOutException("caused by blackout rule: " + this.toString());
        System.out.println( "caused by blackout rule: " + this.toString() );
    }

    @Priority
    public int getPriority() {
        return 1;
    }
}
