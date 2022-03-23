package com.example.inventrolabstask1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IpConverterTest {
    IpConverter ipConverter = new IpConverter();

    @Test
    void testIpV4ToLong() {
        long result = ipConverter.ipV4ToLong("128.32.10.0");
        Assertions.assertEquals(2149583360L, result);
    }

    @Test
    void testLongToIpV4() {
        String result = IpConverter.longToIpV4(2149583360L);
        Assertions.assertEquals("128.32.10.0", result);
    }
}