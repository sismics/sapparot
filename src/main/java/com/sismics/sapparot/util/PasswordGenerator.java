package com.sismics.sapparot.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jtremeaux
 */
public class PasswordGenerator {
    public static String generatePassword(int length) {
        final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(alphabet.length())));
        }
        return password.toString();
    }
}
