package com.qibao.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * url safe base64
 * @author yemq
 */
public class UrlBase64 {
    public final static String ENCODING = "UTF-8";

    public static String encoded(byte[] data) throws UnsupportedEncodingException {
        byte[] b = Base64.encodeBase64URLSafe(data);
        return new String(b, ENCODING);
    }

    public static byte[] decode(String data) throws UnsupportedEncodingException {
        byte[] b = Base64.decodeBase64(data);
        return b;
    }
}
