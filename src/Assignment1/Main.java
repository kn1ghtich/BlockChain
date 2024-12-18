package Assignment1;

import java.util.ArrayList;
import java.util.List;


import static Assignment1.Block.mineBlock;
import static Assignment1.Block.validateBlockchain;

public class Main {
    public static void main(String[] args) {
        List<Block> blockchain = new ArrayList<>();

        // Genesis block
        List<Transaction> genesisTransactions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            genesisTransactions.add(new Transaction("GenesisSender", "GenesisReceiver", i * 10));
        }
        Block genesisBlock = mineBlock("0", genesisTransactions);
        blockchain.add(genesisBlock);

        // Add more blocks
        for (int i = 1; i <= 5; i++) {
            List<Transaction> transactions = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                transactions.add(new Transaction("Sender" + i, "Receiver" + i, j * 5));
            }
            Block newBlock = mineBlock(blockchain.get(blockchain.size() - 1).hash, transactions);
            blockchain.add(newBlock);
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