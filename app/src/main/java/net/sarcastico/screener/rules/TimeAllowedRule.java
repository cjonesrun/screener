package net.sarcastico.screener.rules;

import android.support.annotation.NonNull;

import org.easyrules.api.Rule;

/**
 * Created by cj on 2017-05-04.
 */

public class TimeAllowedRule implements Rule, Comparable {

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean evaluate() {
        return false;
    }

    @Override
    public void execute() throws Exception {

    }
}
