package com.sismics.sapparot.exception;

/**
 * Localized exception.
 *
 * @author jtremeaux
 */
public class LocalizedException extends RuntimeException {
    private String[] args;

    public LocalizedException(String message) {
        super(message);
    }

    public LocalizedException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public LocalizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
