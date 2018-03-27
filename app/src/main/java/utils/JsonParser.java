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


}
