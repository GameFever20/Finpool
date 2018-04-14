package finpool.finance.app.finpool;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.interfaces.AlertActionListener;
import com.irozon.alertview.objects.AlertAction;

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

    Client groupSlected, clientSelected;

    boolean isSelectingGroup = true;

    ProgressDialog progress;

    AutoCompleteTextView autoCompleteTextView;

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

        spinner = findViewById(R.id.clientSelection_group_spinner);


        clientAdapter = new ClientAdapter(clientArrayList, this);

        recyclerView.setAdapter(clientAdapter);


        groupAdapter = new ClientAdapter(groupArrayList, this);


        fetchGroupList();

        initializeBottomSheet();

        progress = new ProgressDialog(this);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchDialog();
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                autoCompleteTextView.requestFocus();
            }
        });


    }

    private void openSearchDialog() {

        final ArrayList<String> stringArrayList = new ArrayList<>();
        for(Client client:clientArrayList){
            stringArrayList.add(client.getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,stringArrayList);

        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);



       autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               int position = stringArrayList.indexOf(autoCompleteTextView.getText().toString());

               if (position != 0) {
                   selectedGroup = "";
                   groupSlected = new Client();
                   groupSlected.setId("");
               }
               selectedClient = clientArrayList.get(position).getId();
               clientSelected = clientArrayList.get(position);

               //showDialogChooser();

               openClientOverview();

               autoCompleteTextView.setText("");

           }
       });

    }

    @Override
    public void onBackPressed() {



        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }

    }

    private void showGroupList() {

        recyclerView.setAdapter(groupAdapter);


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


        autoCompleteTextView = findViewById(R.id.clientActivity_autoTextView);



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

                        groupArrayList.add(0, client);

                        ArrayList<String> groupStringArrayList = new ArrayList<>();
                        for (Client client1 : groupArrayList) {
                            groupStringArrayList.add(client1.getName());
                        }

                        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(ClientSelectionActivity.this, android.R.layout.simple_spinner_dropdown_item, groupStringArrayList);

                        spinner.setAdapter(groupAdapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                                fetchGroupDetails(groupArrayList.get(position).getId());
                                selectedGroup = groupArrayList.get(position).getId();
                                groupSlected = groupArrayList.get(position);

                                isSelectingGroup = false;
                                showDialog();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                       /* groupAdapter = new ClientAdapter(groupArrayList, ClientSelectionActivity.this);

                        groupAdapter.setClickListener(new ClientAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {
                                fetchGroupDetails(groupArrayList.get(position).getId());
                                selectedGroup = groupArrayList.get(position).getId();
                                groupSlected = groupArrayList.get(position);

                                isSelectingGroup = false;
                                showDialog();
                            }
                        });

                        Log.d(TAG, "onResponse: " + groupArrayList);

                        recyclerView.setAdapter(groupAdapter);*/


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

                        hideDialog();
                        Log.d(TAG, "onResponse: " + response);

                        clientArrayList = new JsonParser().parseGroupDetails(response);

                        final Client client = new Client();
                        client.setName("All");
                        client.setId("");

                        clientArrayList.add(0, client);

                        clientAdapter = new ClientAdapter(clientArrayList, ClientSelectionActivity.this);

                        Log.d(TAG, "onResponse: " + groupArrayList);

                        recyclerView.setAdapter(clientAdapter);

                        clientAdapter.setClickListener(new ClientAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {

                                //mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                //headingBottomSheetTextView.setText("Select Report to show for " + clientArrayList.get(position));


                                if (position != 0) {
                                    selectedGroup = "";
                                    groupSlected = new Client();
                                    groupSlected.setId("");
                                }
                                selectedClient = clientArrayList.get(position).getId();
                                clientSelected = clientArrayList.get(position);

                                //showDialogChooser();

                                openClientOverview();

                            }

                            @Override
                            public void onItemLongClick(int position, View view) {

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

    private void openClientOverview() {

        Intent intent = new Intent(ClientSelectionActivity.this, ClientOverviewActivity.class);

        intent.putExtra("id", selectedGroup);
        intent.putExtra("client", selectedClient);

        intent.putExtra("groupSelected", groupSlected);
        intent.putExtra("clientSelected", clientSelected);


        startActivity(intent);

    }

    private void showDialogChooser() {

        AlertView alert = new AlertView("Select Report to show", "Client - " + selectedClient + " => " + selectedGroup, AlertStyle.IOS);

        alert.addAction(new AlertAction("MF Report", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                onMFReportClick(recyclerView);

            }
        }));

        alert.addAction(new AlertAction("SIP Report", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                onSIPReportClick(recyclerView);

            }
        }));

        alert.addAction(new AlertAction("Reversal Report", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                onReversalReportClick(recyclerView);

            }
        }));

        alert.addAction(new AlertAction("Transaction Report", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                onTransactionReportClick(recyclerView);

            }
        }));

        alert.addAction(new AlertAction("Wealth Report", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                onWealthReportClick(recyclerView);

            }
        }));

        alert.addAction(new AlertAction("Cancel", AlertActionStyle.NEGATIVE, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {


            }
        }));

        alert.show(this);

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
        Intent intent = new Intent(this, TransactionReportActivity.class);

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
        intent.putExtra("client", selectedClient);

        intent.putExtra("groupSelected", groupSlected);
        intent.putExtra("clientSelected", clientSelected);


        startActivity(intent);

    }


    public void showDialog() {
        try {
            progress.setMessage("Getting Clients ");

            progress.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDialog() {
        try {
            progress.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void OngroupSelectClick(View view) {

        spinner.performClick();

    }
}
