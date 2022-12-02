package com.weesftw.algorithms.impl;

import com.weesftw.algorithms.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SHA256Digest implements Hash {

    @Override
    public String calculate(String input) {
        try {
            var buffer = new StringBuilder();
            var digest = MessageDigest.getInstance("SHA-256");
            var bytes = digest.digest(input.getBytes(UTF_8));

            for (byte b : bytes) {
                buffer.append(format("%02x", b));
            }

           return buffer.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
