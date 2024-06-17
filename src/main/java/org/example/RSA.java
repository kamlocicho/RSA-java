package org.example;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public static BigInteger largePrime(int bits) {
        Random randomInteger = new Random();
        return BigInteger.probablePrime(bits, randomInteger);
    }

    public static BigInteger n(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }

    public static BigInteger phi(BigInteger p, BigInteger q) {
        return (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        } else {
            return gcd(b, a.mod(b));
        }
    }

    public static BigInteger createE(BigInteger phi) {
        Random rand = new Random();
        BigInteger e;
        do {
            do {
                e = new BigInteger(1024, rand);
            } while (e.min(phi).equals(phi));
        } while (!gcd(e, phi).equals(BigInteger.ONE));
        return e;
    }

    public static BigInteger[] extendedEuclidean(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return new BigInteger[] {
                a, BigInteger.ONE, BigInteger.ZERO
        }; // { a, 1, 0 }
        BigInteger[] vals = extendedEuclidean(b, a.mod(b));
        BigInteger d = vals[0];
        BigInteger p = vals[2];
        BigInteger q = vals[1].subtract(a.divide(b).multiply(vals[2]));
        return new BigInteger[] {
                d, p, q
        };
    }

    public static BigInteger stringCipher(String message) {
        message = message.toUpperCase();
        StringBuilder cipherString = new StringBuilder();
        int i = 0;
        while (i < message.length()) {
            int ch = message.charAt(i);
            cipherString.append(ch);
            i++;
        }
        return new BigInteger(cipherString.toString());
    }

    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
        return message.modPow(d, n);
    }

    public static String cipherToString(BigInteger message) {
        String cipherString = message.toString();
        StringBuilder output = new StringBuilder();
        int i = 0;
        while (i < cipherString.length()) {
            int temp = Integer.parseInt(cipherString.substring(i, i + 2));
            char ch = (char) temp;
            output.append(ch);
            i = i + 2;
        }
        return output.toString();
    }

}
