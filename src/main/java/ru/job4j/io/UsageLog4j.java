package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        byte b = 1;
        short s = 10;
        int i = 100;
        long l = 1000L;
        float f = 0.1F;
        double d = 0.01;
        char c = 'a';
        boolean bl = true;
        LOG.debug(String.format("%s%s", "byte value: {}, short value: {}, int value: {}, long value: {}, ",
                        "float value: {}, double value: {}, char value: {}, boolean value: "),
                b, s, i, l, f, d, c, bl);


    }
}
