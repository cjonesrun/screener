package net.sarcastico.screener.rules2;

import net.sarcastico.screener.utils.DayOfWeek;
import net.sarcastico.screener.utils.Time;

public class Test {

	public static void main(String[] args) {

		Engine e = new Engine();
		// for collections of BasicTimeRule, only one needs to be fired for the OK to be given
		//e.addRule(new BasicTimeRule("rule-10-to-18", "ok from 10h to 18h", 3, LocalTime.of(10, 0), LocalTime.of(18, 0)));
		/*e.addRule(new BasicTimeRule("rule-10-to-15", "OK from 10h to 15h", 3, LocalTime.of(10, 0), LocalTime.of(15, 30)));
		
		e.fire();
		System.out.println("all-true=" + e.okAllTrue() + " one-true=" + e.okATLeaseOnTrue());
		
		// add in a 10min window
		e.addRule(new ForNextTimeRule("rule-add-10-min-to-now", "OK for next 10 minutes", 3, ChronoUnit.MINUTES, 10));
		
		e.fire();
		System.out.println("all-true=" + e.okAllTrue() + " one-true=" + e.okATLeaseOnTrue());*/
		
		buildWeeklyRuleSets(e);
		e.fire();
		
		System.out.println("all-true=" + e.okAllTrue() + " one-true=" + e.okATLeaseOnTrue());
	}
	
	
	public static void buildWeeklyRuleSets(Engine e) {
		
		// THURSDAY rules
		e.addRule(new BasicTimeRule(DayOfWeek.THURSDAY, 2, Time.of(12, 0), Time.of(18, 0)));
		e.addRule(new BasicTimeRule(DayOfWeek.THURSDAY, 3, Time.of(10, 0), Time.of(12, 0)));
		
		
		/*RuleSet tue = new RuleSet();
		RuleSet wed = new RuleSet();
		RuleSet thu = new RuleSet();
		RuleSet fri = new RuleSet();
		RuleSet sat = new RuleSet();
		RuleSet sun = new RuleSet();*/
	}


}