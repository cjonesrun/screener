package net.sarcastico.screener.rules;

import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

public class BlackoutRuleEngine {

    public static void main(String[] args) {

        BlackoutRuleEngine engine = new BlackoutRuleEngine();

        // register rules
        engine.addRule(new TimeOfDayBlackoutRule(2,7,0,8,0));
        engine.addRule(new TimeOfDayBlackoutRule(2,8,0,8,15));
        engine.addRule(new TimeOfDayBlackoutRule(2,22,0,23,59));

        engine.checkRules();
    }


    private RulesEngine engine;

    public BlackoutRuleEngine() {
        build();
    }
    public void build() {
        // create rules engine
        engine = RulesEngineBuilder.aNewRulesEngine()
                    .withSkipOnFirstAppliedRule(true)
                    .withRuleListener(new RulesListener())
                    .withSilentMode(false)
                    .build();
    }

    public void addRule(Rule r) {
        engine.registerRule(r);
    }

    public boolean checkRules()
    {
        RulesContext.put("status","OK");
        RulesContext.put("message","initial-state");

        engine.fireRules();

        System.out.println("result=" + RulesContext.get("status"));
        System.out.println("message=" + RulesContext.get("message"));

        return "OK".equals(RulesContext.get("status").toString());
    }





}