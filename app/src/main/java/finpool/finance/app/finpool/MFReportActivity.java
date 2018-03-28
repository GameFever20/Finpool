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
    private String client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfreport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = getIntent().getStringExtra("client");
        String group = getIntent().getStringExtra("id");
        fetchMFTransaction(client,group);

        recyclerView = findViewById(R.id.mfReport_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mfTransactionAdapter = new MFTransactionAdapter(mfTransactionArrayList, this);

        recyclerView.setAdapter(mfTransactionAdapter);

    }


    public void fetchMFTransaction(final String clientId, String groupId) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobileval.php?id="+groupId+"&client=" + clientId;

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

                                Intent intent = new Intent(MFReportActivity.this,MFReportDetailActivity.class);

                                intent.putExtra("client", client);
                                intent.putExtra("transaction",mfTransactionArrayList.get(position));


                                startActivity(intent);

                            }
                        });


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
