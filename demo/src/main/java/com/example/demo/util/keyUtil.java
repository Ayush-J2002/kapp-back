package com.example.demo.util;

import javax.crypto.KeyGenerator;import javax.crypto.SecretKey;
public class keyUtil {
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256); 
        return keyGen.generateKey();
    }
}
