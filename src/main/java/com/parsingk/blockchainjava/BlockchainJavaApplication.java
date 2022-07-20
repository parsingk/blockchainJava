package com.parsingk.blockchainjava;

import com.parsingk.blockchainjava.encrypt.ECDSA;
import com.parsingk.blockchainjava.encrypt.HashEncryption;
import com.parsingk.blockchainjava.wallet.Wallet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SpringBootApplication
public class BlockchainJavaApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        SpringApplication.run(BlockchainJavaApplication.class, args);
    }

}
