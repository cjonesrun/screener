package cbn.lottery.server.rules;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class ForNextTimeRule extends BasicTimeRule{

	public ForNextTimeRule(String name, String desc, int priority, ChronoUnit t, int quantity) 
	{
		super(name, priority, LocalTime.now().minus(1, ChronoUnit.MILLIS), LocalTime.now().plus(quantity, t));
	}
	
	public static final void main(String[] s) throws Exception {
		// new java.time.DayOfWeek
		// new java.time.Month
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt.get(ChronoField.DAY_OF_WEEK));
		
		
		
	}

	
	
	
}
