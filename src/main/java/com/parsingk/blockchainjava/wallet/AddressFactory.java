package com.parsingk.blockchainjava.wallet;

import com.parsingk.blockchainjava.encrypt.Base58;
import com.parsingk.blockchainjava.encrypt.HashEncryption;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AddressFactory {
    private static final byte VERSION = 0x00;
    private static final int ADDRESS_CHECKSUM_LENGTH = 4;

    protected static String getAddress(byte[] encryptedPublicKey) throws NoSuchAlgorithmException {
        byte[] publicKeyHash = hashPublicKey(encryptedPublicKey);
        byte[] versionArray = {VERSION};

        byte[] payload = concat(versionArray, publicKeyHash);
        byte[] checkSum = checksum(payload);

        byte[] fullPayload = concat(payload, checkSum);

        return Base58.encode(fullPayload);
    }

    public static boolean validateAddress(String address) throws NoSuchAlgorithmException {
        byte[] publicKeyHash = Base58.decode(address);
        byte[] actualCheckSum = Arrays.copyOfRange(publicKeyHash, publicKeyHash.length - ADDRESS_CHECKSUM_LENGTH, publicKeyHash.length);
        byte version = publicKeyHash[0];
        byte[] versionArray = {version};

        publicKeyHash = Arrays.copyOfRange(publicKeyHash, 1, publicKeyHash.length - ADDRESS_CHECKSUM_LENGTH);
        byte[] targetChecksum = checksum(concat(versionArray, publicKeyHash));

        return Arrays.compare(actualCheckSum, targetChecksum) == 0;
    }

    private static byte[] hashPublicKey(byte[] encryptedPublicKey) throws NoSuchAlgorithmException {
        byte[] publicKeyHash = HashEncryption.sha256(encryptedPublicKey);
        return HashEncryption.ripemd160(publicKeyHash);
    }

    private static byte[] concat(byte[] a, byte[] b) {
        byte[] payload = new byte[a.length + b.length];
        System.arraycopy(a, 0, payload, 0, a.length);
        System.arraycopy(b, 0, payload, a.length, b.length);

        return payload;
    }

    private static byte[] checksum(byte[] payload) throws NoSuchAlgorithmException {
        byte[] firstSHA = HashEncryption.sha256(payload);
        byte[] secondSHA = HashEncryption.sha256(firstSHA);

        return Arrays.copyOfRange(secondSHA, 0, ADDRESS_CHECKSUM_LENGTH);
    }
}
