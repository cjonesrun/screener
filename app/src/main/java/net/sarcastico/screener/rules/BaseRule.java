package net.sarcastico.screener.rules;

import android.support.annotation.NonNull;

import org.easyrules.api.Rule;

/**
 * Created by cj on 2017-05-04.
 */

public abstract class BaseRule implements Rule, Comparable {

    @Override
    public int compareTo(@NonNull Object o) {
        Rule in = (Rule)o;

        // first check priority
        int comp = new Integer(this.getPriority()).compareTo( new Integer(((Rule) o).getPriority()) );

        if (comp != 0)
            return comp;

        // if same priority, compare names
        return this.getName().compareTo(((Rule)o).getName());
    }
}
