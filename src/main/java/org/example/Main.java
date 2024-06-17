package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger p = RSA.largePrime(512);
        BigInteger q = RSA.largePrime(512);

        BigInteger n = RSA.n(p, q);
        BigInteger phi = RSA.phi(p, q);
        BigInteger e = RSA.createE(phi);
        BigInteger d = RSA.extendedEuclidean(e, phi)[1];

        String message="This is message for encryption";
        BigInteger cipherMessage = RSA.stringCipher(message);

        BigInteger encrypted = RSA.encrypt(cipherMessage, e, n);
        System.out.println("Encrypted: " + encrypted);
        BigInteger decrypted = RSA.decrypt(encrypted, d, n);
        System.out.println("Decrypted: " + decrypted);

        String restoredMessage = RSA.cipherToString(decrypted);

        System.out.println("Original: " + message);
        System.out.println("Restored: " + restoredMessage);
    }
}