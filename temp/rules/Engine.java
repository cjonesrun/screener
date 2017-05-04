package cbn.lottery.server.rules;

import java.time.LocalTime;
import java.util.Set;

import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

public class Engine {

	private RulesEngine engine;
	private boolean okAll;
	private boolean okAtLeastOne;
	
	public Engine() {
		this(null);
	}
	public Engine(Set<Rule> rules)
	{
		engine = RulesEngineBuilder.aNewRulesEngine()
				.withRuleListener(new Listener())
				//.withSkipOnFirstAppliedRule(true)
				//.withSkipOnFirstFailedRule(true)
				//.withSkipOnFirstNonTriggeredRule(true)
				.withSilentMode(false)
				.build();
		if (rules != null) {
			for (Rule r : rules) {
				addRule(r);
			}
		}
	}
	
	public void fire() {
		System.out.println("-- engine fire " + LocalTime.now());
		engine.fireRules();
		okAll = true;
		okAtLeastOne = false;
		
		for (Rule r : engine.getRules()) {
			Executable mr = (Executable)r;
			okAll &= mr.isExecuted(); 
			okAtLeastOne |= mr.isExecuted(); 
		}
	}
	public boolean okAllTrue() {
		return okAll;
	}
	
	public boolean okATLeaseOnTrue() {
		return okAtLeastOne;
	}
	
	public void addRule(Rule r) {
		engine.registerRule(r);
	}
}