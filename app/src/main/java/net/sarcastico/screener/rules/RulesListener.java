package net.sarcastico.screener.rules;

import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;

/**
 * Created by cj on 2017-05-03.
 */

public class RulesListener implements RuleListener {
    @Override
    public boolean beforeEvaluate(Rule rule) {
        return true;
    }

    @Override
    public void beforeExecute(Rule rule) {

    }

    @Override
    public void onSuccess(Rule rule) {
        System.out.println("you are blacked out. rule: " + rule.toString());
        RulesContext.put("status", "failed");
        RulesContext.put("message", "you are blacked out. rule: " + rule.toString());
    }

    @Override
    public void onFailure(Rule rule, Exception e) {
        //System.out.println(this.getClass().getCanonicalName() + " " + e.getCause().getMessage());
        RulesContext.put("status", "failed");
        RulesContext.put("message", this.getClass().getCanonicalName() + " " + e.getCause().getMessage());

    }
}
