package Assignment1;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static Assignment1.MerkleTree.computeMerkleRoot;
import static Assignment1.SHA256.hash;

public class Block {
    String previousHash = null;
    String merkleRoot;
    long timestamp;
    String hash = null;

    Block(String previousHash, String merkleRoot) {
        this.previousHash = previousHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = Instant.now().toEpochMilli();
        this.hash = hashBlock();
    }

    // Generate the hash for the block
    private String hashBlock() {
        String data = previousHash + merkleRoot + timestamp;
        return hash(data);
    }

    public String getFormattedTimestamp() {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Mine a block
    public static Block mineBlock(String previousHash, List<Transaction> transactions) {
        String merkleRoot = computeMerkleRoot(transactions);
        return new Block(previousHash, merkleRoot);
    }

    // Validate the blockchain
    public static boolean validateBlockchain(List<Block> blockchain) {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            // Validate current block's hash
            if (!currentBlock.hash.equals(currentBlock.hashBlock())) {
                return false;
            }

            // Validate previous hash
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                return false;
            }
        }
        return true;
    }
}
