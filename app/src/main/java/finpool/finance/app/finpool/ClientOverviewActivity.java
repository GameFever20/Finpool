package finpool.finance.app.finpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import utils.Client;
import utils.JsonParser;
import utils.MFTransaction;
import utils.MFTransactionAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class ClientOverviewActivity extends AppCompatActivity {

    private Client groupSlected;
    private Client clientSelected;

    TextView currentValueTextView, currentInvestedTextView, gainTextView, sensexTextView, divInvTextView, divPayTextView, cagrTextView, absReturnTextView;
    private ArrayList<MFTransaction> mfTransactionArrayList;

    TextView nameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        groupSlected = (Client) getIntent().getSerializableExtra("groupSelected");
        clientSelected = (Client) getIntent().getSerializableExtra("clientSelected");


        currentValueTextView = findViewById(R.id.mfReport_currentValueStatus_textView);
        currentInvestedTextView = findViewById(R.id.mfReport_currentInvested_textView);
        gainTextView = findViewById(R.id.mfReport_gain_textView);
        sensexTextView = findViewById(R.id.mfReport_sensex_textView);
        divInvTextView = findViewById(R.id.mfReport_divInv_textView);
        divPayTextView = findViewById(R.id.mfReport_divPay_textView);
        cagrTextView = findViewById(R.id.mfReport_cagr_textView);
        absReturnTextView = findViewById(R.id.mfReport_absReturn_textView);

        fetchMFTransaction(clientSelected.getId(), groupSlected.getId());


        nameTextView = findViewById(R.id.clientOverview_clientName_textView);

        nameTextView.setText(clientSelected.getName());


        try{
            getSupportActionBar().setTitle(clientSelected.getName());
            getSupportActionBar().setSubtitle(groupSlected.getName());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void fetchMFTransaction(final String clientId, String groupId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobileval.php?id=" + groupId + "&client=" + clientId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        mfTransactionArrayList = new JsonParser().parseMFTransactionList(response);
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

            currentValueTextView.setText(mfTransaction.getCurrentValue());
            currentInvestedTextView.setText(mfTransaction.getAmount());
            gainTextView.setText(mfTransaction.getGain());
            divInvTextView.setText(mfTransaction.getDivInvest());
            divPayTextView.setText(mfTransaction.getDivPay());
            cagrTextView.setText(mfTransaction.getCagr());
            absReturnTextView.setText(mfTransaction.getAbsReturn());


        }


    }

    public void onMFReportClick(View view) {
        Intent intent = new Intent(this, MFReportActivity.class);

        onReportClick(intent);
    }

    public void onSIPReportClick(View view) {
        Intent intent = new Intent(this, SipReportActivity.class);

        onReportClick(intent);
    }

    public void onReversalReportClick(View view) {
        Intent intent = new Intent(this, ReversalReportActivity.class);

        onReportClick(intent);

    }

    public void onTransactionReportClick(View view) {
        Intent intent = new Intent(this, TransactionReportActivity.class);

        onReportClick(intent);
    }


    public void onReportClick(Intent intent) {


        intent.putExtra("groupSelected", groupSlected);
        intent.putExtra("clientSelected", clientSelected);


        startActivity(intent);

    }
}
