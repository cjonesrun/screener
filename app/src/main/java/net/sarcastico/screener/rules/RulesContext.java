package net.sarcastico.screener.rules;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cj on 2017-05-03.
 */

public class RulesContext {

    private static Map<String, Object> getMap() {
        if (rulesContext.get() == null)
            rulesContext.set(new HashMap<String,Object>());
        return rulesContext.get();
    }

    public static void put(String key, Object val){
        getMap().put(key, val);
    }

    public static <T> T get(String key) {
        return (T) getMap().get(key);
    }


    private static final ThreadLocal<Map<String,Object>> rulesContext = new ThreadLocal<Map<String,Object>>();
}
