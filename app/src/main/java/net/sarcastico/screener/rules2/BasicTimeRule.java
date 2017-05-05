package net.sarcastico.screener.rules2;

import net.sarcastico.screener.utils.DayOfWeek;
import net.sarcastico.screener.utils.Time;

import org.easyrules.core.BasicRule;

public class BasicTimeRule extends BasicRule implements Executable {

	public BasicTimeRule(DayOfWeek dow, int priority, Time start, Time end) {
		this.setPriority(priority);
		this.description = name;
		this.dow = dow;
		this.start = start;
		this.end = end;

        this.name = buildRuleName(dow);
	}

	public boolean isExecuted() {
		return executed;
	}

	
	@Override
	public boolean evaluate() {
		
		Time now = Time.now();
		
		System.out.println("- evaluating " + ruleDef());
		return (start.isBefore(now) && end.isAfter(now)); 
	}

	@Override
	public void execute() throws Exception {
		
		System.out.println("* executing " + ruleDef());
		executed = true;
	}
	
	private Time start;
	private Time end;
    private DayOfWeek dow;
    private boolean executed = false;

    private String buildRuleName(DayOfWeek dow) {
        return dow.toString() + "-rule-" + start + "-" + end;
    }
    private String ruleDef() { 
    	return new StringBuilder(this.getName()).append("[").append(this.getPriority())
    			/*.append(" ").append(this.getDescription())*/.append("] <").append(start).append(" to ").append(end).append(">").toString();
    }
    
    
}