package net.sarcastico.screener;

import net.sarcastico.screener.rules2.BasicTimeRule;
import net.sarcastico.screener.rules2.Engine;
import net.sarcastico.screener.utils.DayOfWeek;
import net.sarcastico.screener.utils.Time;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScreenerUnitTest {

    @Test
    public void test_Engine() throws Exception {
        Engine e = new Engine();
        e.addRule(new BasicTimeRule(DayOfWeek.today(), 2, Time.of(Time.now().h()-2,0), Time.of(Time.now().h()-1,0)));
        e.fire();

        assertFalse("",e.okAllTrue());
        assertFalse("",e.okATLeaseOnTrue());

        e = new Engine();
        e.addRule(new BasicTimeRule(DayOfWeek.today(), 2, Time.of(Time.now().h()-2,0), Time.of(Time.now().h()+1,0)));
        e.fire();

        assertTrue("",e.okAllTrue());
        assertTrue("",e.okATLeaseOnTrue());
    }

    @Test
    public void test_TimeComparison() throws Exception {

        Time t1 = Time.of(10, 15, 37);
        Time t2 = Time.of(10, 15, 37);

        // t1 == t2
        assertEquals(0, t1.compareTo(t2));
        assertEquals(0, t2.compareTo(t1));

        // t2 > t1 by second(s)
        t2 = Time.of(10,15,38);
        assertEquals(-1, t1.compareTo(t2));
        assertEquals(1, t2.compareTo(t1));

        // t2 > t1 by minute(s)
        t2 = Time.of(10,16,38);
        assertEquals(-1, t1.compareTo(t2));
        assertEquals(1, t2.compareTo(t1));

        // t2 > t1 by hour(s)
        t2 = Time.of(11,16,38);
        assertEquals(-1, t1.compareTo(t2));
        assertEquals(1, t2.compareTo(t1));
    }

    @Test
    public void test_TimeMath() throws Exception {
        // test hour roll over @24
        Time t1 = Time.of(23, 59, 59).plusHour(1);
        assertEquals(0, t1.h());

        // test min roll over @60
        t1 = Time.of(10,54,59).plusMinute(7);
        assertEquals(11,t1.h());
        assertEquals(1,t1.m());

        // test second && min roll over @60
        t1 = Time.of(10,40,54).plusSecond(7);assertEquals(10,t1.h());
        assertEquals(41,t1.m());
        assertEquals(1,t1.s());

        // test second, min && hour roll over @60
        t1 = Time.of(10,59,54).plusSecond(7);
        assertEquals(11,t1.h());
        assertEquals(0,t1.m());
        assertEquals(1,t1.s());

        // test hour roll over @0
        t1 = Time.of(0, 59, 59).plusHour(-1);
        assertEquals(23, t1.h());

        // test min roll over @0
        t1 = Time.of(5, 7, 30).plusMinute(-10);
        assertEquals(4, t1.h());
        assertEquals(57, t1.m());
    }
}