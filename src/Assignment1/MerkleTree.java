package Assignment1;

import java.util.ArrayList;
import java.util.List;

import static Assignment1.SHA256.hash;

public class MerkleTree {
    public static String computeMerkleRoot(List<Transaction> transactions) {
        List<String> hashList = new ArrayList<>();
        for (Transaction tx : transactions) {
            hashList.add(hash(tx.toString()));
        }

        while (hashList.size() > 1) {
            List<String> newHashList = new ArrayList<>();
            for (int i = 0; i < hashList.size(); i += 2) {
                if (i + 1 < hashList.size()) {
                    newHashList.add(hash(hashList.get(i) + hashList.get(i + 1)));
                } else {
                    newHashList.add(hash(hashList.get(i) + hashList.get(i)));
                }
            }
            hashList = newHashList;
        }

        return hashList.get(0);
    }
}
