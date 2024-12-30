package Assignment1;

import java.math.BigInteger;

public class DigitalSignature {
    RSA rsa;

    public DigitalSignature() {
        rsa = new RSA();
    }

    public BigInteger sign(BigInteger document) {
        return rsa.decrypt(document);
    }

    public boolean verify(BigInteger document, BigInteger signature) {
        BigInteger decryptedHash = rsa.encrypt(signature);
        return decryptedHash.equals(document);
    }

}