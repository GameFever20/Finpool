package utils;

/**
 * Created by bunny on 25/03/18.
 */

public class RecentTransaction {

    String name, scheme, amount, date;
    int transactionType;
//0= recent purchase , 1 recent sip purchase, 2recent redemption, 3 recent suspend

    public RecentTransaction() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }
}
