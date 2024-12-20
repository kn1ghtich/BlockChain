package Assignment1;

import java.util.*;

import static Assignment1.Block.validateBlockchain;

public class Main {
    public static void main(String[] args) {
        List<Block> blockchain = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        //Making transaction
        for (int i = 0; i < 10; i++) {
            transactions.add(new Transaction("Bauyrzhan", "Daniil", i * 10));
        }

        //Mining BlockChain
        blockchain.add(Block.mineBlock("0", transactions));
        for (int i = 0; i < 10; i++) {
            blockchain.add(Block.mineBlock(blockchain.get(i).hash, transactions));
        }

        // Validate blockchain
        System.out.println("Blockchain is valid: " + validateBlockchain(blockchain));

        // Print blockchain
        for (Block block : blockchain) {
            System.out.println("Block Hash: " + block.hash);
            System.out.println("Previous Hash: " + block.previousHash);
            System.out.println("Timestamp: " + block.getFormattedTimestamp());
            System.out.println("Merkle Root: " + block.merkleRoot);
            System.out.println();
        }

    }

}

