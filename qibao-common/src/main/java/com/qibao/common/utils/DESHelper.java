package com.qibao.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 模    块：DESHelper
 * 包    名：com.wzitech.gamegold.facade.utils
 * 项目名称：dada
 * 作    者：Shawn
 * 创建时间：2/26/14 7:08 PM
 * 描    述：
 * 更新纪录：1. Shawn 创建于 2/26/14 7:08 PM
 */
public class DESHelper {
    public static String decrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(getIv2());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        return new String(cipher.doFinal(UrlBase64.decode(message)));
    }

    public static String encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(getIv2());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return UrlBase64.encoded(cipher.doFinal(message.getBytes("UTF-8")));
    }

    private static byte[] getIv2() {
        byte ivBytes[] = new byte[8];
        ivBytes[0] = 0x18;
        ivBytes[1] = 0x37;
        ivBytes[2] = 0x56;
        ivBytes[3] = 0x68;
        ivBytes[4] = (byte) 0x99;
        ivBytes[5] = (byte) 0xAB;
        ivBytes[6] = (byte) 0xCD;
        ivBytes[7] = (byte) 0xEF;

        return ivBytes;
    }
}
