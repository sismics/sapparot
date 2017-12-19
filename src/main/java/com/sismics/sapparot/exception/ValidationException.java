package com.sismics.sapparot.exception;

/**
 * Validation exception.
 *
 * @author jtremeaux
 */
public class ValidationException extends LocalizedException {
    private String key;

    public static final String KEY_GLOBAL = "global";

    private String[] args = new String[0];

    public ValidationException(String message) {
        super(message);
        this.key = KEY_GLOBAL;
    }

    public ValidationException(String key, String message) {
        super(message);
        this.key = key;
    }

    public ValidationException(String key, String message, String... args) {
        super(message);
        this.args = args;
        this.key = key;
    }

    public ValidationException(String key, String message, Throwable cause) {
        super(message, cause);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
