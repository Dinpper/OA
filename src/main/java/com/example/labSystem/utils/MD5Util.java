package com.example.labSystem.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String md5(String str) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(str.getBytes());
            byte[] b = e.digest();
            StringBuffer sb = new StringBuffer("");

            for(int offset = 0; offset < b.length; ++offset) {
                int temp = b[offset];
                if(temp < 0) {
                    temp += 256;
                }

                if(temp < 16) {
                    sb.append("0");
                }

                sb.append(Integer.toHexString(temp));
            }

            str = sb.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        }

        return str;
    }
}
