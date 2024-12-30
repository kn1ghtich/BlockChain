package Assignment1;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;

    public RSA() {
        SecureRandom random = new SecureRandom();
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger cipherText) {
        return cipherText.modPow(d, n);
    }

    public BigInteger[] getPublicKey() {
        return new BigInteger[]{e, n};
    }

    public BigInteger[] getPrivateKey() {
        return new BigInteger[]{d, n};
    }

}