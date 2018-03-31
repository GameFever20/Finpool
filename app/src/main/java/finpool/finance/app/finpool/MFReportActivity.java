package finpool.finance.app.finpool;

import android.content.Intent;
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

import utils.ClientAdapter;
import utils.JsonParser;
import utils.MFTransaction;
import utils.MFTransactionAdapter;
import utils.SipTransaction;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class MFReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private MFTransactionAdapter mfTransactionAdapter;
    ArrayList<MFTransaction> mfTransactionArrayList = new ArrayList<>();
    private String client;

    TextView investorNameTextView, currentValueTextView, currentInvestedTextView, currentValueStatusTextView, gainTextView, sensexTextView, divInvTextView, divPayTextView, cagrTextView, absReturnTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfreport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = getIntent().getStringExtra("client");
        String group = getIntent().getStringExtra("id");
        fetchMFTransaction(client, group);


        investorNameTextView = findViewById(R.id.mfReport_investorName_textView);
        currentValueTextView = findViewById(R.id.mfReport_currentValue_textView);
        currentInvestedTextView = findViewById(R.id.mfReport_currentInvested_textView);
        currentValueStatusTextView = findViewById(R.id.mfReport_currentValueStatus_textView);
        gainTextView = findViewById(R.id.mfReport_gain_textView);
        sensexTextView = findViewById(R.id.mfReport_sensex_textView);
        divInvTextView = findViewById(R.id.mfReport_divInv_textView);
        divPayTextView = findViewById(R.id.mfReport_divPay_textView);
        cagrTextView = findViewById(R.id.mfReport_cagr_textView);
        absReturnTextView = findViewById(R.id.mfReport_absReturn_textView);


        recyclerView = findViewById(R.id.mfReport_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mfTransactionAdapter = new MFTransactionAdapter(mfTransactionArrayList, this);

        recyclerView.setAdapter(mfTransactionAdapter);

    }


    public void fetchMFTransaction(final String clientId, String groupId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobileval.php?id=" + groupId + "&client=" + clientId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        mfTransactionArrayList = new JsonParser().parseMFTransactionList(response);
                        mfTransactionAdapter = new MFTransactionAdapter(mfTransactionArrayList, MFReportActivity.this);

                        Log.d(TAG, "onResponse: " + mfTransactionArrayList);

                        recyclerView.setAdapter(mfTransactionAdapter);

                        mfTransactionAdapter.setClickListener(new MFTransactionAdapter.ClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                Intent intent = new Intent(MFReportActivity.this, MFReportDetailActivity.class);

                                intent.putExtra("client", client);
                                intent.putExtra("transaction", mfTransactionArrayList.get(position));


                                startActivity(intent);

                            }
                        });

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

        if (mfTransactionArrayList.size() > 0) {
            MFTransaction mfTransaction = mfTransactionArrayList.get(mfTransactionArrayList.size() - 1);
            mfTransactionArrayList.remove(mfTransactionArrayList.size() - 1);

            investorNameTextView.setText(client);
            currentValueTextView.setText(mfTransaction.getCurrentValue());
            currentValueStatusTextView.setText(mfTransaction.getCurrentValue());
            currentInvestedTextView.setText(mfTransaction.getAmount());
            gainTextView.setText(mfTransaction.getGain());
            divInvTextView.setText(mfTransaction.getDivInvest());
            divPayTextView.setText(mfTransaction.getDivPay());
            cagrTextView.setText(mfTransaction.getCagr());
            absReturnTextView.setText(mfTransaction.getAbsReturn());


        }


    }

}
