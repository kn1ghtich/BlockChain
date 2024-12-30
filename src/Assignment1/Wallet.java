package Assignment1;

import java.io.FileWriter;
import java.math.BigInteger;
import java.util.Base64;

public class Wallet {
    DigitalSignature ds;
    String publicAddress;

    public Wallet() {
        ds = new DigitalSignature();
        byte[] publicKeyBytes = bigIntArrayToByteArray(ds.rsa.getPublicKey());
        publicAddress = Base64.getEncoder().encodeToString(publicKeyBytes);
    }


    private byte[] bigIntArrayToByteArray(BigInteger[] bigInts) {
        int totalLength = 0;
        for (BigInteger bigInt : bigInts) {
            totalLength += bigInt.toByteArray().length;
        }


        byte[] result = new byte[totalLength];
        int currentIndex = 0;


        for (BigInteger bigInt : bigInts) {
            byte[] byteArray = bigInt.toByteArray();
            System.arraycopy(byteArray, 0, result, currentIndex, byteArray.length);
            currentIndex += byteArray.length;
        }

        return result;
    }

    public void createTransaction(String to, double amount) {
        BigInteger message = new BigInteger((publicAddress + ":" + to + ":" + amount).getBytes());
        BigInteger signature = ds.sign(message);

        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            writer.write(message + ":" + signature.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicAddress() {
        return publicAddress;
    }
}