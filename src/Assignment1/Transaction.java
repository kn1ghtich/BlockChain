package Assignment1;

public class Transaction {
    String sender;
    String receiver;
    double amount;
    String signature;

    public Transaction(String sender, String receiver, double amount, String signature) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return sender + ":" + receiver + ":" + amount;
    }
}
