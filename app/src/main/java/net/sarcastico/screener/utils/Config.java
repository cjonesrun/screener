package net.sarcastico.screener.utils;

import android.util.Log;

import java.net.URL;

/**
 * App config
 */

public class Config {
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    public static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world", "1@2.com:123"
    };

    public static final URL base() {
        try {
            // use 10.0.2.2 to access host's localhost
            return new URL("http://192.168.1.128:8080/");
        } catch (Exception e) {
            Log.e("URL", e.getMessage(), e);
            return null;
        }
    }
}
