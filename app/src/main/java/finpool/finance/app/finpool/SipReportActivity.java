package finpool.finance.app.finpool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

import utils.JsonParser;
import utils.MFTransaction;
import utils.MFTransactionAdapter;
import utils.SipTransaction;
import utils.SipTransactionAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class SipReportActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SipTransactionAdapter sipTransactionAdapter;
    ArrayList<SipTransaction> sipTransactionArrayList = new ArrayList<>();

    TextView investorNameTextView, currentInvestmentTextView, sipAmountTextView, sipCountTextView, currentValueTextView, divInvTextView, divPayTextView, absReturnTextView;

    String client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        client = getIntent().getStringExtra("client");
        String group = getIntent().getStringExtra("id");
        fetchSipReportTransaction(client, group);


        investorNameTextView = findViewById(R.id.sipReport_investorName_textView);
        currentInvestmentTextView = findViewById(R.id.sipReport_currentInvestment_textView);
        sipAmountTextView = findViewById(R.id.sipReport_sipAmount_textView);
        sipCountTextView = findViewById(R.id.sipReport_sipCount_textView);
        currentValueTextView = findViewById(R.id.sipReport_currentValue_textView);
        divInvTextView = findViewById(R.id.sipReport_divInv_textView);
        divPayTextView = findViewById(R.id.sipReport_divPay_textView);
        absReturnTextView = findViewById(R.id.sipReport_absReturn_textView);


        recyclerView = findViewById(R.id.sipReport_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        sipTransactionAdapter = new SipTransactionAdapter(sipTransactionArrayList, this);

        recyclerView.setAdapter(sipTransactionAdapter);


    }

    private void fetchSipReportTransaction(String clientId, String groupId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobilesip.php?id=" + groupId + "&client=" + clientId;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        sipTransactionArrayList = new JsonParser().parseSipTransactionList(response);
                        sipTransactionAdapter = new SipTransactionAdapter(sipTransactionArrayList, SipReportActivity.this);

                        Log.d(TAG, "onResponse: " + sipTransactionArrayList);

                        recyclerView.setAdapter(sipTransactionAdapter);

                        refreshUI();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "onErrorResponse: " + error);

                    }
                });


        jsonArrayRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(jsonArrayRequest, "Group request");


    }

    private void refreshUI() {

        if (sipTransactionArrayList.size() > 0) {
            SipTransaction sipTransaction = sipTransactionArrayList.get(sipTransactionArrayList.size() - 1);

            investorNameTextView.setText(client);
            currentInvestmentTextView.setText(sipTransaction.getScheme());
            sipAmountTextView.setText(sipTransaction.getSipAmount());
            sipCountTextView.setText(sipTransaction.getFolio_no());
            currentValueTextView.setText(sipTransaction.getCurrentValue());
            divInvTextView.setText(sipTransaction.getDivInvest());
            divPayTextView.setText(sipTransaction.getDivPay());
            absReturnTextView.setText(sipTransaction.getAbsReturn());

        }

    }

}
