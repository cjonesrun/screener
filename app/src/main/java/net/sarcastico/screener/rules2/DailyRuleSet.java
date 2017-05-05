package net.sarcastico.screener.rules2;

import net.sarcastico.screener.utils.DayOfWeek;

import org.easyrules.core.CompositeRule;

/**
 * All rules in a rule set must evaluate to true in order for execute to be invoked (where all rule.execute() 
 * will be invoked)
 * 
 * @author cjones
 *
 */
public class DailyRuleSet extends CompositeRule implements Executable {
	
    public DailyRuleSet(String name, int priority, DayOfWeek dow, BasicTimeRule... basicTimeRules)
    {
   
    	this.name = name;
    	this.description = name;
    	this.setPriority(priority);
    	this.dow = dow;
    	for (Object rule : rules) {
            addRule(rule);
        }
    }

	@Override
	public boolean evaluate() {
		/*if (LocalDateTime.now().getDayOfWeek().equals(dow) ) {
			System.out.println(this.getClass().getSimpleName() + ": evaluating ruleset for " + name);
			return super.evaluate();	
		} else {
			System.out.println(this.getClass().getSimpleName() + ": skipping ruleset for " + name);
			return false;
		}*/
		return true;
	}

	@Override
	public void execute() throws Exception {
		System.out.println(this.getClass().getSimpleName() + ": executing ruleset for " + name);
		executed = true;
		super.execute();
	}

	@Override
    public int getPriority() {
        return priority;
    }
	
	@Override
	public boolean isExecuted() {
		// TODO Auto-generated method stub
		return executed;
	}

	public void setPriority(int p) {
		this.priority = p;
	}
	private int priority = 1;
	private boolean executed = false;
	private DayOfWeek dow;
}