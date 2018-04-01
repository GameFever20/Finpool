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
import utils.MFTransactionDetail;
import utils.MFTransactionDetailAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class MFReportDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private MFTransactionDetailAdapter mfTransactionDetailAdapter;
    ArrayList<MFTransactionDetail> mfTransactionDetailArrayList = new ArrayList<>();

    MFTransaction mfTransaction;

    TextView currentValueStatusTextValue, investorNameTextValue, investedAmountTextValue, gainTextValue, sensexTextValue, divInvTextValue, divPayTextValue, cagrTextValue, absReturnTextValue, currentNavTextValue, schemeTextValue, countTextValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfreport_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String client = getIntent().getStringExtra("client");
        mfTransaction = (MFTransaction) getIntent().getSerializableExtra("transaction");

        fetchMFTransaction(client, mfTransaction.getProdcode(), mfTransaction.getFolio_no());


        currentValueStatusTextValue = findViewById(R.id.mfDetailActivity_currentValueStatus_textView);
        investorNameTextValue = findViewById(R.id.mfDetailActivity_investorName_textView);
        investedAmountTextValue = findViewById(R.id.mfDetailActivity_investedValue_textView);
        gainTextValue = findViewById(R.id.mfDetailActivity_gain_textView);
        sensexTextValue = findViewById(R.id.mfDetailActivity_sensex_textView);
        divInvTextValue = findViewById(R.id.mfDetailActivity_divInv_textView);
        divPayTextValue = findViewById(R.id.mfDetailActivity_divPay_textView);
        cagrTextValue = findViewById(R.id.mfDetailActivity_cagr_textView);
        absReturnTextValue = findViewById(R.id.mfDetailActivity_absReturn_textView);
        currentNavTextValue = findViewById(R.id.mfDetailActivity_currentNav_textView);
        schemeTextValue = findViewById(R.id.mfDetailActivity_schemeName_textView);
        countTextValue = findViewById(R.id.mfDetailActivity_count_textView);


        recyclerView = findViewById(R.id.mfReport_detail_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mfTransactionDetailAdapter = new MFTransactionDetailAdapter(mfTransactionDetailArrayList, this);

        recyclerView.setAdapter(mfTransactionDetailAdapter);
    }

    public void fetchMFTransaction(String clientId, String prodCode, String folioNo) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobilevalbrief.php?id=&client=" + clientId + "&FOLIO_NO=" + folioNo + "&PRODCODE=" + prodCode;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        mfTransactionDetailArrayList = new JsonParser().parseMFTransactionDetailList(response);
                        mfTransactionDetailAdapter = new MFTransactionDetailAdapter(mfTransactionDetailArrayList, MFReportDetailActivity.this);

                        Log.d(TAG, "onResponse: " + mfTransactionDetailArrayList);

                        recyclerView.setAdapter(mfTransactionDetailAdapter);

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


    public void refreshUI() {

        currentValueStatusTextValue.setText(mfTransaction.getCurrentValue());
        currentValueStatusTextValue.setText(mfTransaction.getCurrentValue());
        investedAmountTextValue.setText(mfTransaction.getAmount());
        gainTextValue.setText(mfTransaction.getGain());
        sensexTextValue.setText("82921");
        divInvTextValue.setText(mfTransaction.getDivInvest());
        divPayTextValue.setText(mfTransaction.getDivPay());
        cagrTextValue.setText(mfTransaction.getCagr());
        absReturnTextValue.setText(mfTransaction.getAbsReturn());
        currentNavTextValue.setText(mfTransaction.getNavdate());
        schemeTextValue.setText(mfTransaction.getScheme());
        countTextValue.setText("6");

    }

}
