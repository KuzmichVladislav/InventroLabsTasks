package com.example.inventrolabstask1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Задание 1 "IP-адреса"
 * IpV4 адрес состоит из 4 октетов, значит его можно хранить в переменной типа int32.
 * Реализуйте пару функций, которые однозначно преобразуют строковое представление IpV4 адрес (вида "127.0.0.1") в значение типа int32 и наоборот.
 * <p>
 * Примеры:
 * 2149583360 ==> "128.32.10.0"
 * 255        ==> "0.0.0.255"
 */

public class IpConverter {

    public long ipV4ToLong(String ip) {
        int[] razr = {24, 16, 8, 0};
        AtomicInteger index = new AtomicInteger();
        return Arrays.stream(ip.split("\\.")).map(Integer::valueOf)
                .mapToLong(x -> (long) (x * Math.pow(2, razr[index.getAndIncrement()])))
                .sum();
    }

    public static String longToIpV4(long ip) {
        return String.format("%d.%d.%d.%d",
                ip >>> 24,
                (ip >> 16) & 0xff,
                (ip >> 8) & 0xff,
                ip & 0xff);
    }
}
