package com.korsun.sunrise.db;

import java.util.Random;

/**
 * Created by okorsun on 31.07.16.
 */

public class MockTextGenerator {
    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random;

    public MockTextGenerator(int seed) {
        random = new Random(seed);
    }

    public String nextString(int length) {
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public String nextString(String prefix, int length) {
        return prefix + nextString(length);
    }

    public String nextNumberSequence(int length) {
        final StringBuilder builder = new StringBuilder(length);
        for (int idx = 0; idx < length; ++idx)
            builder.append(String.valueOf(random.nextInt(9)));
        return builder.toString();
    }

}
