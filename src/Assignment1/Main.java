package Assignment1;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        System.out.println("-=| RSA Demonstration |=-");
        RSA rsa = new RSA();

        String originalMessage = "SE-2313";
        System.out.println("Original Message: " + originalMessage);

        BigInteger message = new BigInteger(originalMessage.getBytes());
        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + new String(decryptedMessage.toByteArray()));

        System.out.println("\n-=| Digital Signature Demonstration |=-");
        DigitalSignature ds = new DigitalSignature();
        String document = "This is a critical transaction";


        BigInteger docMessage = new BigInteger(1, document.getBytes());

        BigInteger signature = ds.sign(docMessage);
        System.out.println("Document: " + document);
        System.out.println("Signature: " + signature);

        boolean isSignatureValid = ds.verify(docMessage, signature);
        System.out.println("Signature valid: " + isSignatureValid);

        System.out.println("\n-=| Blockchain Demonstration with Wallet |=-");
        Wallet senderWallet = new Wallet();
        Wallet receiverWallet = new Wallet();

        System.out.println("Sender Public Address: " + senderWallet.getPublicAddress());
        System.out.println("Receiver Public Address: " + receiverWallet.getPublicAddress());

        List<Transaction> transactions = new ArrayList<>();
        DigitalSignature walletSignature = new DigitalSignature();

        String senderAddr = senderWallet.getPublicAddress();
        String receiverAddr = receiverWallet.getPublicAddress();
        double amount = 100.0;

        String transactionMessage = senderAddr + ":" + receiverAddr + ":" + amount;
        BigInteger transactionHash = hashStringToBigInteger(transactionMessage);

        BigInteger transactionSignature = walletSignature.sign(transactionHash);

        Transaction tx = new Transaction(senderAddr, receiverAddr, amount, transactionSignature.toString());
        transactions.add(tx);


        List<Block> blockchain = new ArrayList<>();
        Block genesisBlock = Block.mineBlock("0", transactions);
        blockchain.add(genesisBlock);

        Block newBlock = Block.mineBlock(blockchain.get(blockchain.size() - 1).hash, transactions);
        blockchain.add(newBlock);

        System.out.println("Validating blockchain...");
        boolean isBlockchainValid = Block.validateBlockchain(blockchain);

        if (isBlockchainValid) {
            System.out.println("Blockchain is valid!");
        } else {
            System.out.println("Blockchain is invalid!");
        }

        System.out.println("\nBlockchain:");
        for (Block block : blockchain) {
            System.out.println("Block Hash: " + block.hash);
            System.out.println("Previous Hash: " + block.previousHash);
            System.out.println("Timestamp: " + block.getFormattedTimestamp());
            System.out.println("Merkle Root: " + block.merkleRoot);
        }
    }

    public static BigInteger hashStringToBigInteger(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            return new BigInteger(1, hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }
}