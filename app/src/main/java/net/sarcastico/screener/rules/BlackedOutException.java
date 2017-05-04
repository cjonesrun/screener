package net.sarcastico.screener.rules;

/**
 * Created by cj on 2017-05-03.
 */

public class BlackedOutException extends Exception {
    public BlackedOutException() {
        super();
    }

    public BlackedOutException(String message) {
        super(message);
    }

    public BlackedOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlackedOutException(Throwable cause) {
        super(cause);
    }
}
