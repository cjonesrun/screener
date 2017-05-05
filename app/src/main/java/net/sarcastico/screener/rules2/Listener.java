package net.sarcastico.screener.rules2;

import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;

public class Listener implements RuleListener {

	@Override
	public boolean beforeEvaluate(Rule r) {
		//System.out.println("beforeEvaluate: " + r.toString());
		return true;
	}

	@Override
	public void beforeExecute(Rule r) {
		//System.out.println("beforeExecute: " + r.toString());
		
	}

	@Override
	public void onFailure(Rule r, Exception e) {
		//e.getCause().printStackTrace();
		//System.out.println("onFailure: " + r.toString() + " " + e.getCause().getMessage());
		
	}

	@Override
	public void onSuccess(Rule r) {
		//System.out.println("onSuccess: " + r.toString());
	}

}
