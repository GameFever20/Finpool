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
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class MFReportActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private MFTransactionAdapter mfTransactionAdapter;
    ArrayList<MFTransaction> mfTransactionArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfreport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String client = getIntent().getStringExtra("client");
        fetchMFTransaction(client);

        recyclerView = findViewById(R.id.mfReport_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mfTransactionAdapter = new MFTransactionAdapter(mfTransactionArrayList, this);

        recyclerView.setAdapter(mfTransactionAdapter);

    }


    public void fetchMFTransaction(String clientId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobileval.php?id=&client=" + clientId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        mfTransactionArrayList = new JsonParser().parseMFTransactionList(response);
                        mfTransactionAdapter = new MFTransactionAdapter(mfTransactionArrayList, MFReportActivity.this);

                        Log.d(TAG, "onResponse: " + mfTransactionArrayList);

                        recyclerView.setAdapter(mfTransactionAdapter);


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

}
