package com.sismics.sapparot.string;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author jtremeaux
 */
public class StringUtil {
    /**
     * Converts an inputStream to a String using the UTF-8 encoding.
     *
     * @param is The input stream
     * @return The string
     */
    public static String toString(InputStream is) {
        try {
            return CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
