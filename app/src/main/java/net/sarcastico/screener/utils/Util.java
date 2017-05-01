package net.sarcastico.screener.utils;

import org.json.JSONObject;

/**
 * Utils
 */

public class Util {

    public static JSONObject build(String[] keys, String[] vals) {
        try {
            JSONObject o = new JSONObject();

            for (int i=0; i<keys.length; i++)
            {
                o.put(keys[i], vals[i]);
            }
            return o;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
