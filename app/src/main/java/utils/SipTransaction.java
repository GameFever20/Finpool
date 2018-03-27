package utils;

/**
 * Created by bunny on 25/03/18.
 */

public class SipTransaction {

    String id, heading, sipAmount, currentInvestment, currentValue, absReturn, divPay, divInvest;

    public SipTransaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSipAmount() {
        return sipAmount;
    }

    public void setSipAmount(String sipAmount) {
        this.sipAmount = sipAmount;
    }

    public String getCurrentInvestment() {
        return currentInvestment;
    }

    public void setCurrentInvestment(String currentInvestment) {
        this.currentInvestment = currentInvestment;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getAbsReturn() {
        return absReturn;
    }

    public void setAbsReturn(String absReturn) {
        this.absReturn = absReturn;
    }

    public String getDivPay() {
        return divPay;
    }

    public void setDivPay(String divPay) {
        this.divPay = divPay;
    }

    public String getDivInvest() {
        return divInvest;
    }

    public void setDivInvest(String divInvest) {
        this.divInvest = divInvest;
    }
}
