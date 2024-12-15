package Assignment1;

public class Transaction {
    String sender;
    String receiver;
    double amount;

    Transaction(String sender, String receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return sender + ":" + receiver + ":" + amount;
    }
}
