package com.parsingk.blockchainjava;


import com.parsingk.blockchainjava.encrypt.ECDSA;
import com.parsingk.blockchainjava.wallet.AddressFactory;
import com.parsingk.blockchainjava.wallet.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

public class WalletTest {

    private Wallet wallet;

    @BeforeEach
    public void before() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        ECDSA ecdsa = new ECDSA();
        ECDSA.Claim claim = ecdsa.getNewKeyPair();

        wallet = new Wallet(claim.getPrivateKey(), claim.getPublicKey());
    }

    @Test
    public void createWallet() {
        assert wallet.getAddress() != null && wallet.getAddress().length() > 0;
    }

    @Test
    public void validAddress() throws NoSuchAlgorithmException {
        assert AddressFactory.validateAddress(wallet.getAddress());
    }
}
