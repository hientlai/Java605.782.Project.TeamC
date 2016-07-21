/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypt or Decrypt a string by DES
 *
 * @author Hien
 */
public class DESEncryptDecryptUntil {

    private static Cipher ecipher;
    private static Cipher dcipher;

    private static String key = "squirrel123";

    static {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, desKey);

            dcipher.init(Cipher.DECRYPT_MODE, desKey);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(DESEncryptDecryptUntil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * encrypt a string
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {

        try {

            // encode the string into a sequence of bytes using the named charset
            // storing the result into a new byte array. 
            byte[] utf8 = str.getBytes("UTF8");

            byte[] enc = ecipher.doFinal(utf8);

            // encode to base64
            enc = BASE64EncoderStream.encode(enc);

            return new String(enc);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     * decrypt a string
     *
     * @param str
     * @return
     */
    public static String decrypt(String str) {

        try {

            // decode with base64 to get bytes
            byte[] dec = BASE64DecoderStream.decode(str.getBytes());

            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset
            return new String(utf8, "UTF8");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
}
