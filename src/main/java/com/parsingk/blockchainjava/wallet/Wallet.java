package com.parsingk.blockchainjava.wallet;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String address;

    public Wallet(PrivateKey privateKey, PublicKey publicKey) throws NoSuchAlgorithmException {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.address = AddressFactory.getAddress(publicKey.getEncoded());
    }

    public String getAddress() {
        return address;
    }
}