package com.digdes.school;

public class StringUtils {
    public static String substringBefore(String incoming, String separator) {
        return incoming.substring(0, incoming.indexOf(separator));
    }

    public static String substringAfter(String incoming, String separator) {
        return incoming.substring(incoming.indexOf(separator) + separator.length());
    }
}
