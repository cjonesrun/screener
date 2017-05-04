package cbn.lottery.server.rules;

import java.time.LocalTime;
import org.easyrules.core.BasicRule;

public class BasicTimeRule extends BasicRule implements Executable {

	public BasicTimeRule(String name, int priority, LocalTime start, LocalTime end) {
		this.setPriority(priority);
		this.description = name;
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public boolean isExecuted() {
		return executed;
	}

	
	@Override
	public boolean evaluate() {
		
		LocalTime now = LocalTime.now();
		
		System.out.println("- evaluating " + ruleDef());
		return (start.isBefore(now) && end.isAfter(now)); 
	}

	@Override
	public void execute() throws Exception {
		
		System.out.println("* executing " + ruleDef());
		executed = true;
	}
	
	private LocalTime start;
	private LocalTime end;
    
    private boolean executed = false;
    
    private String ruleDef() { 
    	return new StringBuilder(this.getName()).append("[").append(this.getPriority())
    			/*.append(" ").append(this.getDescription())*/.append("] <").append(start).append(" to ").append(end).append(">").toString();
    }
    
    
}