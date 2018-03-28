package utils;

/**
 * Created by bunny on 25/03/18.
 */

public class ReversalTransaction {

    String name, scheme, amount, reversalDate;

    public ReversalTransaction() {
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

    public String getReversalDate() {
        return reversalDate;
    }

    public void setReversalDate(String reversalDate) {
        this.reversalDate = reversalDate;
    }
}
