package utils;

import java.io.Serializable;

/**
 * Created by bunny on 25/03/18.
 */

public class MFTransaction implements Serializable {

    String folio_no, scheme, cagr, amount,nav, currentValue, absReturn, divPay, divInvest, name, traddate, navdate, prodcode, gain;

    public MFTransaction() {
    }



    public String getCagr() {
        return cagr;
    }

    public void setCagr(String cagr) {
        this.cagr = cagr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolio_no() {
        return folio_no;
    }

    public void setFolio_no(String folio_no) {
        this.folio_no = folio_no;
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

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getTraddate() {
        return traddate;
    }

    public void setTraddate(String traddate) {
        this.traddate = traddate;
    }

    public String getNavdate() {
        return navdate;
    }

    public void setNavdate(String navdate) {
        this.navdate = navdate;
    }

    public String getProdcode() {
        return prodcode;
    }

    public void setProdcode(String prodcode) {
        this.prodcode = prodcode;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }
}
