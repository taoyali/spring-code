package com.tyl.utils;

import jakarta.validation.constraints.Pattern;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + "初始化失败, MessageDigest 不支持MD5Util.");
            nsaex.printStackTrace();
        }
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        return bufferToHex(messageDigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringBuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringBuffer);
        }
        return stringBuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringBuffer) {
        char c0 = hexDigits[(bt & 0XF0) >> 4];
        char c1 = hexDigits[(bt & 0X0F)];
        stringBuffer.append(c0);
        stringBuffer.append(c1);
    }
}
