package io.github.fanlzhichzu.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5
 *
 * @author fanlz
 * @date 2022/10/12 17:05
 **/
public class Md5Utils {

    public static String md5(String text) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = messageDigest.digest(text.getBytes());
        return new BigInteger(1, bytes).toString(16);
    }
}
