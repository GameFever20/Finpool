package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bunny on 27/03/18.
 */

public class JsonParser {


    public ArrayList<Client> parseGroupList(JSONArray jsonArray) {
        ArrayList<Client> clientArrayList = new ArrayList<>();

        try {

            Client client;

            for (int i = 0; i < jsonArray.length(); i++) {
                client = new Client();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                client.setName(jsonObject.getString("groupname"));
                client.setId(jsonObject.getString("groupid"));

                clientArrayList.add(client);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientArrayList;

    }

    public ArrayList<Client> parseGroupDetails(JSONArray jsonArray) {
        ArrayList<Client> clientArrayList = new ArrayList<>();

        try {

            Client client;

            for (int i = 0; i < jsonArray.length(); i++) {
                client = new Client();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                client.setName(jsonObject.getString("name"));
                client.setId(jsonObject.getString("clientid"));

                clientArrayList.add(client);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientArrayList;

    }

    public ArrayList<MFTransaction> parseMFTransactionList(JSONArray jsonArray) {
        ArrayList<MFTransaction> mfTransactionArrayList = new ArrayList<>();

        try {

            MFTransaction mfTransaction;

            for (int i = 0; i < jsonArray.length(); i++) {

                if (i==jsonArray.length()-1) {

                    mfTransaction = new MFTransaction();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    mfTransaction.setScheme("Total Amount");

                    mfTransaction.setAmount(jsonObject.getString("amount"));
                    mfTransaction.setCurrentValue(jsonObject.getString("valuation"));
                    mfTransaction.setDivInvest(jsonObject.getString("divinv"));
                    mfTransaction.setDivPay(jsonObject.getString("divpay"));
                    mfTransaction.setGain(jsonObject.getString("gain"));
                    mfTransaction.setCagr(jsonObject.getString("cagr"));
                    mfTransaction.setAbsReturn(jsonObject.getString("abs"));


                    mfTransactionArrayList.add(mfTransaction);

                    break;
                }


                mfTransaction = new MFTransaction();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                mfTransaction.setName(jsonObject.getString("inv_name"));
                mfTransaction.setProdcode(jsonObject.getString("prodcode"));
                mfTransaction.setScheme(jsonObject.getString("scheme"));
                mfTransaction.setFolio_no(jsonObject.getString("folio_no"));
                mfTransaction.setTraddate(jsonObject.getString("traddate"));
                mfTransaction.setAmount(jsonObject.getString("amount"));
                mfTransaction.setNav(jsonObject.getString("nav"));
                mfTransaction.setNavdate(jsonObject.getString("navdate"));
                mfTransaction.setCurrentValue(jsonObject.getString("valuation"));
                mfTransaction.setDivInvest(jsonObject.getString("divinv"));
                mfTransaction.setDivPay(jsonObject.getString("divpay"));
                mfTransaction.setGain(jsonObject.getString("gain"));
                mfTransaction.setCagr(jsonObject.getString("cagr"));
                mfTransaction.setAbsReturn(jsonObject.getString("abs"));



                mfTransactionArrayList.add(mfTransaction);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mfTransactionArrayList;

    }

    public ArrayList<MFTransactionDetail> parseMFTransactionDetailList(JSONArray jsonArray) {
        ArrayList<MFTransactionDetail> mfTransactionDetailArrayList = new ArrayList<>();

        try {

            MFTransactionDetail mfTransactionDetail;

            for (int i = 0; i < jsonArray.length(); i++) {



                mfTransactionDetail = new MFTransactionDetail();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                mfTransactionDetail.setTradeDate(jsonObject.getString("TRADDATE"));
                mfTransactionDetail.setAmount(jsonObject.getString("AMOUNT"));
                mfTransactionDetail.setPurchase(jsonObject.getString("PURPRICE"));
                mfTransactionDetail.setUnits(jsonObject.getString("UNITS"));
                mfTransactionDetail.setSensex(jsonObject.getString("SENSEX"));
                mfTransactionDetail.setNavDate(jsonObject.getString("NAVDATE"));
                mfTransactionDetail.setValuation(jsonObject.getString("VALUATION"));
                mfTransactionDetail.setDivInvest(jsonObject.getString("DIVINV"));
                mfTransactionDetail.setDivPay(jsonObject.getString("DIVPAY"));
                mfTransactionDetail.setSpan(jsonObject.getString("SPAN"));
                mfTransactionDetail.setCagr(jsonObject.getString("CARG"));
                mfTransactionDetail.setAbs(jsonObject.getString("ABS"));






                mfTransactionDetailArrayList.add(mfTransactionDetail);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mfTransactionDetailArrayList;

    }


    public ArrayList<SipTransaction> parseSipTransactionList(JSONArray jsonArray) {
        ArrayList<SipTransaction> sipTransactionArrayList = new ArrayList<>();

        try {

            SipTransaction sipTransaction;

            for (int i = 0; i < jsonArray.length(); i++) {

                if (i==jsonArray.length()-1){

                    sipTransaction = new SipTransaction();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    sipTransaction.setScheme("Total Amount");
                    sipTransaction.setFolio_no(String.valueOf(jsonObject.getInt("sipcount")));
                    sipTransaction.setSipAmount(String.valueOf(jsonObject.getInt("sip")));
                    sipTransaction.setCurrentInvestment(String.valueOf(jsonObject.getInt("amount")));
                    sipTransaction.setCurrentValue(String.valueOf(jsonObject.getDouble("val")));

                    sipTransaction.setDivPay(String.valueOf(jsonObject.getDouble("divpay")));
                    sipTransaction.setDivInvest(String.valueOf(jsonObject.getDouble("divinv")));
                    sipTransaction.setAbsReturn(String.valueOf(jsonObject.getDouble("abs")));

                    sipTransactionArrayList.add(sipTransaction);

                    break;
                }




                sipTransaction = new SipTransaction();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                sipTransaction.setScheme(jsonObject.getString("scheme"));
                sipTransaction.setFolio_no(jsonObject.getString("folio_no"));
                sipTransaction.setSipAmount(jsonObject.getString("sip"));
                sipTransaction.setCurrentInvestment(String.valueOf(jsonObject.getInt("amount")));
                sipTransaction.setCurrentValue(String.valueOf(jsonObject.getDouble("val")));

                sipTransaction.setDivPay(jsonObject.getString("divpay"));
                sipTransaction.setDivInvest(jsonObject.getString("divinv"));
                sipTransaction.setAbsReturn(String.valueOf(jsonObject.getDouble("abs")));

                sipTransactionArrayList.add(sipTransaction);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sipTransactionArrayList;

    }

    public ArrayList<ReversalTransaction> parseReversalTransactionList(JSONArray jsonArray) {
        ArrayList<ReversalTransaction> reversalTransactionArrayList = new ArrayList<>();

        try {

            ReversalTransaction reversalTransaction;

            for (int i = 0; i < jsonArray.length(); i++) {


                if (i==jsonArray.length()-1){

                    reversalTransaction = new ReversalTransaction();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    reversalTransaction.setScheme("Total Amount");
                    reversalTransaction.setAmount(jsonObject.getString("amountto"));


                    reversalTransactionArrayList.add(reversalTransaction);

                    break;

                }


                reversalTransaction = new ReversalTransaction();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                reversalTransaction.setName(jsonObject.getString("invname"));
                reversalTransaction.setScheme(jsonObject.getString("scheme"));
                reversalTransaction.setAmount(jsonObject.getString("amountto"));
                reversalTransaction.setReversalDate(jsonObject.getString("date"));

                reversalTransactionArrayList.add(reversalTransaction);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reversalTransactionArrayList;

    }



}
