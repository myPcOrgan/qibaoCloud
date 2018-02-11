package com.qibao.user.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5 {
	static Log log = LogFactory.getLog(Md5.class);
	public static final String SIGN_TYPE = "MD5";

	public Md5() {
	}

	public static String getMd5String(String str) {
		try {
			MessageDigest msgDigest = MessageDigest.getInstance(SIGN_TYPE);
			msgDigest.update(str.getBytes("utf-8"));
			byte[] enbyte = msgDigest.digest();
			return byte2hex(enbyte).toLowerCase();
		} catch (NoSuchAlgorithmException var3) {
			log.error(var3.getMessage(), var3);
			throw new RuntimeException(var3.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	private static String byte2hex(byte[] bytes) {
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
					.substring(1).toUpperCase());
		}
		return retString.toString();
	}
}
