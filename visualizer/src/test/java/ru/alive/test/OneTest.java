package ru.alive.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dart
 * @since 11/14/12 4:29 AM
 */
public class OneTest {

    private static final Logger log = LoggerFactory.getLogger(OneTest.class);

    @Test
    public void test() throws UnknownHostException {
        log.info(InetAddress.getLocalHost().getCanonicalHostName());
    }
}
