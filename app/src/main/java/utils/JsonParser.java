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
                client.setEmail(jsonObject.getString("email"));
                client.setMobileNumber(jsonObject.getString("mobile"));
                client.setUserID(jsonObject.getString("userid"));
                client.setPassword(jsonObject.getString("password"));


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

                if (i == jsonArray.length() - 1) {

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

                if (i == jsonArray.length() - 1) {

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


                if (i == jsonArray.length() - 1) {

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


    public PortfolioData parsePortFolioData(JSONArray jsonArray) {

        PortfolioData portfolioData = new PortfolioData();

        try {


            JSONObject jsonObject = jsonArray.getJSONObject(0);

            portfolioData.setSip(jsonObject.getString("sip"));
            portfolioData.setReverse(jsonObject.getString("reverse"));

            portfolioData.setTotal(jsonObject.getString("total"));
            portfolioData.setEquity(jsonObject.getString("equity"));
            portfolioData.setBalance(jsonObject.getString("balance"));
            portfolioData.setCash(jsonObject.getString("cash"));
            portfolioData.setOthers(jsonObject.getString("others"));
            portfolioData.setDebt(jsonObject.getString("debt"));

            portfolioData.setValTotal(jsonObject.getString("valtotal"));
            portfolioData.setValEquity(jsonObject.getString("valequity"));
            portfolioData.setValBalance(jsonObject.getString("valbalance"));
            portfolioData.setValCash(jsonObject.getString("valcash"));
            portfolioData.setValOthers(jsonObject.getString("valothers"));
            portfolioData.setValDebt(jsonObject.getString("valdebt"));

            portfolioData.setClient(jsonObject.getString("clients"));
            portfolioData.setDate(jsonObject.getString("date"));

            portfolioData.setTotper(jsonObject.getString("totper"));
            portfolioData.setEquper(jsonObject.getString("equper"));
            portfolioData.setBalper(jsonObject.getString("balper"));
            portfolioData.setCashper(jsonObject.getString("cashper"));
            portfolioData.setOthper(jsonObject.getString("othper"));
            portfolioData.setDebtper(jsonObject.getString("debtper"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return portfolioData;

    }

    public ArrayList<RecentTransaction> parseRecentTransactionList(JSONArray jsonArray) {
        ArrayList<RecentTransaction> recentTransactionArrayList = new ArrayList<>();

        try {

            RecentTransaction recentTransaction;

            for (int i = 0; i < jsonArray.length(); i++) {


                recentTransaction = new RecentTransaction();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                recentTransaction.setName(jsonObject.getString("invname"));
                recentTransaction.setScheme(jsonObject.getString("scheme"));
                recentTransaction.setAmount(jsonObject.getString("amountto"));
                recentTransaction.setDate(jsonObject.getString("date"));

                recentTransactionArrayList.add(recentTransaction);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recentTransactionArrayList;

    }

    public KYCStatus parseKycDetail(JSONObject jsonObject) {

        KYCStatus kycStatus = new KYCStatus();

        try {


            JSONObject jsonObject2 = jsonObject.getJSONObject("d");


            kycStatus.setName(jsonObject2.getString("Name"));
            kycStatus.setStatus(jsonObject2.getString("Status"));
            kycStatus.setKycDate(jsonObject2.getString("KYCDate"));
            kycStatus.setCurrentDate(jsonObject2.getString("CurrentDate"));
            kycStatus.setPanNo(jsonObject2.getString("PanNo"));
            kycStatus.setClientType(jsonObject2.getString("app_type"));

            if (kycStatus.getClientType()==null){
              kycStatus.setClientType("Corporate");
            }else{
                kycStatus.setClientType("Individual");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return kycStatus;

    }


    public ArrayList<TransactionReport> parseTransactionReportList(JSONArray jsonArray) {
        ArrayList<TransactionReport> transactionReportArrayList = new ArrayList<>();

        try {

            TransactionReport transactionReport;

            for (int i = 0; i < jsonArray.length(); i++) {




                transactionReport = new TransactionReport();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                transactionReport.setInvName(jsonObject.getString("INV_NAME"));
                transactionReport.setScheme(jsonObject.getString("SCHEME"));
                transactionReport.setFolio_no(jsonObject.getString("FOLIO_NO"));
                transactionReport.setSubType(jsonObject.getString("sub_type"));
                transactionReport.setSchemeType(jsonObject.getString("SCHEME_TYPTWO"));
                transactionReport.setTradeDate(jsonObject.getString("TRADDATE"));
                transactionReport.setAmount(jsonObject.getString("AMOUNT"));
                transactionReport.setPurchasePrice(jsonObject.getString("PURPRICE"));
                transactionReport.setUnits(jsonObject.getString("UNITS"));
                transactionReport.setRemarks(jsonObject.getString("Remarks"));


                transactionReportArrayList.add(transactionReport);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionReportArrayList;

    }


}
