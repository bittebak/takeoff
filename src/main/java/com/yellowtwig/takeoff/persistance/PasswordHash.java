/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.persistance;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;

/**
 *
 * @author marcprive
 */
public class PasswordHash {

    public static boolean checkPassword(String password, String hash, String salt) {

        String calculatedHash = calculateHash(password, salt);
        return calculatedHash.equals(hash);

    }

    public static String calculateHash(String passwordToHash, String salt) {
        String md5 = null;
        String passwordWithSalt = passwordToHash + salt;

        if (null == passwordToHash) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(passwordWithSalt.getBytes(), 0, passwordWithSalt.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    //Generate a random salt.
    //The salt will be represented in a HEX string.
    public static String generateSalt() {
        //Always use a SecureRandom generator
        byte[] salt = new byte[16];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
       
            //Get a random salt
            sr.nextBytes(salt);
            
            //Converts message digest value in base 16 (hex) 
            String result = new BigInteger(1, salt).toString(16);
            return result;
        } catch (Exception ex) {
            //Generation of a randum numbe is not possible. Let's use the curren date and time.
            Date date = new Date();
            
            String result = Integer.toHexString(date.hashCode());
            return result;
        }
    }
}
