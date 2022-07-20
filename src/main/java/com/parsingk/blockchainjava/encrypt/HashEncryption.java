package com.parsingk.blockchainjava.encrypt;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncryption {

    public static byte[] sha256(byte[] bytes) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(bytes);
    }

    public static String sha256Hex(byte[] bytes) throws NoSuchAlgorithmException {
        return String.format("%064x", new BigInteger(1, sha256(bytes)));
    }

    public static byte[] ripemd160(byte[] bytes) {
        RIPEMD160Digest ripemd160Digest = new RIPEMD160Digest();
        ripemd160Digest.update(bytes, 0, bytes.length);

        byte[] ripemd160Bytes = new byte[ripemd160Digest.getDigestSize()];
        ripemd160Digest.doFinal(ripemd160Bytes, 0);

        return ripemd160Bytes;
    }
}