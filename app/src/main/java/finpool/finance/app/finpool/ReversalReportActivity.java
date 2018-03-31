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
import utils.ReversalTransaction;
import utils.ReversalTransactionAdapter;
import utils.SipTransaction;
import utils.SipTransactionAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class ReversalReportActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReversalTransactionAdapter reversalTransactionAdapter;
    ArrayList<ReversalTransaction> reversalTransactionArrayList = new ArrayList<>();

    TextView investorNameTextView, amountTextView;

    String client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = getIntent().getStringExtra("client");
        String group = getIntent().getStringExtra("id");
        fetchReversalReportTransaction(client, group);

        investorNameTextView = findViewById(R.id.reversaReport_Name_textView);
        amountTextView = findViewById(R.id.reversaReport_amount_textView);


        recyclerView = findViewById(R.id.reversalReport_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        reversalTransactionAdapter = new ReversalTransactionAdapter(reversalTransactionArrayList, this);

        recyclerView.setAdapter(reversalTransactionAdapter);


    }

    private void fetchReversalReportTransaction(String clientId, String groupId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/clientrev.php?id=" + groupId + "&cid=" + clientId;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        reversalTransactionArrayList = new JsonParser().parseReversalTransactionList(response);
                        reversalTransactionAdapter = new ReversalTransactionAdapter(reversalTransactionArrayList, ReversalReportActivity.this);

                        Log.d(TAG, "onResponse: " + reversalTransactionArrayList);

                        recyclerView.setAdapter(reversalTransactionAdapter);

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

        if (reversalTransactionArrayList.size() > 0) {
            ReversalTransaction reversalTransaction = reversalTransactionArrayList.get(reversalTransactionArrayList.size() - 1);
            reversalTransactionArrayList.remove(reversalTransactionArrayList.size() - 1);

            investorNameTextView.setText(client);
            amountTextView.setText(reversalTransaction.getAmount());


        }

    }

}
