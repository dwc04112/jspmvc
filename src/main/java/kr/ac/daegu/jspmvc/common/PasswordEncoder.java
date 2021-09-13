package kr.ac.daegu.jspmvc.common;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String getEncodePassword(String inputPassword, String salt) {
        String passwordSalt = inputPassword + salt;
        String encodedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(passwordSalt.getBytes(StandardCharsets.UTF_8));
            encodedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("=========여기는 common 입니다=======");
        System.out.println("inputPassword = " + inputPassword);
        System.out.println("salt = " + passwordSalt);
        System.out.println("encodedPassword = " + encodedPassword);
        System.out.println("=========여기는 common 입니다=======");
        return encodedPassword;
    }
}
