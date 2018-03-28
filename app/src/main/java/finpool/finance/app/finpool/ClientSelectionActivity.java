package finpool.finance.app.finpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.Client;
import utils.ClientAdapter;
import utils.JsonParser;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class ClientSelectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Client> clientArrayList = new ArrayList<>();
    ClientAdapter clientAdapter;


    ArrayList<Client> groupArrayList = new ArrayList<>();
    ClientAdapter groupAdapter;

    private Spinner spinner;

    private BottomSheetBehavior mBottomSheetBehavior;
    TextView headingBottomSheetTextView;

    String groupUrl = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/ManageGroupapp.php";
    private String groupDetailUrl = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/ManageGroupdetails.php?id=";

    String selectedGroup = "", selectedClient = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.clientActivity_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        clientAdapter = new ClientAdapter(clientArrayList, this);

        recyclerView.setAdapter(clientAdapter);


        groupAdapter = new ClientAdapter(groupArrayList, this);


        spinner = (Spinner) findViewById(R.id.clientActivity_spinner);
        ArrayAdapter<String> adapter;

       /* groupArrayList.add("All");

        groupArrayList.add("Item 1");
        groupArrayList.add("Item 2");
        groupArrayList.add("Item 3");
        groupArrayList.add("Item 4");
        groupArrayList.add("Item 5");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, groupArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
*/

        fetchGroupList();

        initializeBottomSheet();
        headingBottomSheetTextView = findViewById(R.id.clientActivity_bottomsheet_heading);


    }


    private void initializeBottomSheet() {

        View bottomSheet = findViewById(R.id.clientActivity_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setHideable(false);


        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()

        {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {


                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


    }


    public void fetchGroupList() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, groupUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        groupArrayList = new JsonParser().parseGroupList(response);

                        Client client = new Client();
                        client.setName("All");
                        client.setId("");

                        groupArrayList.add(0,client);


                        groupAdapter = new ClientAdapter(groupArrayList, ClientSelectionActivity.this);

                        groupAdapter.setClickListener(new ClientAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {
                                fetchGroupDetails(groupArrayList.get(position).getId());
                                selectedGroup = groupArrayList.get(position).getId();
                            }
                        });

                        Log.d(TAG, "onResponse: " + groupArrayList);

                        recyclerView.setAdapter(groupAdapter);


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


    public void fetchGroupDetails(String groupId) {

        String url = groupDetailUrl + groupId;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        clientArrayList = new JsonParser().parseGroupDetails(response);

                        Client client = new Client();
                        client.setName("All");
                        client.setId("");

                        clientArrayList.add(0,client);

                        clientAdapter = new ClientAdapter(clientArrayList, ClientSelectionActivity.this);

                        Log.d(TAG, "onResponse: " + groupArrayList);

                        recyclerView.setAdapter(clientAdapter);

                        clientAdapter.setClickListener(new ClientAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {

                                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                headingBottomSheetTextView.setText("Select Report to show for " + clientArrayList.get(position));

                                if (position!=0){
                                    selectedGroup="";
                                }
                                selectedClient = clientArrayList.get(position).getId();

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


    public void onMFReportClick(View view) {

        Intent intent = new Intent(this, MFReportActivity.class);

        onReportClick(intent);

    }

    public void onWealthReportClick(View view) {
        Intent intent = new Intent(this, MFReportActivity.class);

        onReportClick(intent);

    }

    public void onTransactionReportClick(View view) {
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

    public void onReportClick(Intent intent) {

        intent.putExtra("id", selectedGroup);
        intent.putExtra("client",selectedClient );

        startActivity(intent);

    }

}
