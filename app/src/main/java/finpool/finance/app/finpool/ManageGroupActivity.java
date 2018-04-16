package finpool.finance.app.finpool;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.Client;
import utils.ClientAdapter;
import utils.GroupAdapter;
import utils.JsonParser;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class ManageGroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ArrayList<Client> groupArrayList = new ArrayList<>();

    String groupUrl = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/ManageGroupapp.php";
    private String groupDetailUrl = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/ManageGroupdetails.php?id=";

    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private Spinner spinner;

    FloatingActionButton floatingActionButton;

    EditText groupNameEditText, emailIdEditText, mobileNumberEditText, userIdEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        spinner = findViewById(R.id.clientSelection_group_spinner);

        recyclerView = findViewById(R.id.manageGroupActivity_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        groupAdapter = new GroupAdapter(groupArrayList, this);

        recyclerView.setAdapter(groupAdapter);


        fetchGroupList();

        initializeBottomSheet();

        fetchGroupDetails("0");


    }

    private void initializeBottomSheet() {

        floatingActionButton = findViewById(R.id.manageGroupActivity_next_fab);
        floatingActionButton.hide();

        View bottomSheet = findViewById(R.id.manageGroupActivity_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setHideable(false);


        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()

        {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                    //floatingActionButton.show();

                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    //floatingActionButton.hide();
                } else {
                  //  floatingActionButton.hide();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        groupNameEditText = findViewById(R.id.manageGroup_groupName_editText);
        emailIdEditText = findViewById(R.id.manageGroup_emailId_editText);
        userIdEditText = findViewById(R.id.manageGroup_userID_editText);
        mobileNumberEditText = findViewById(R.id.manageGroup_mobileNumber_editText);


    }


    public void fetchGroupList() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, groupUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        groupArrayList = new JsonParser().parseGroupList(response);


                        groupAdapter = new GroupAdapter(groupArrayList, ManageGroupActivity.this);

                        recyclerView.setAdapter(groupAdapter);

                        groupAdapter.setClickListener(new GroupAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {
                                Intent intent = new Intent(ManageGroupActivity.this, GroupEditActivity.class);
                                intent.putExtra("groupSelected", groupArrayList.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(int position, View view) {
                                showDeleteDialog(position);
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

    private void showDeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete the group " + groupArrayList.get(position).getName());

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteGroupApi(groupArrayList.get(position));

                groupArrayList.remove(position);

                groupAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();

    }

    private void deleteGroupApi(final Client client) {
        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/groupdelete.php?id=" + client.getId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                Snackbar snackbar = Snackbar
                        .make(floatingActionButton, "Group deleted succesfully", Snackbar.LENGTH_LONG);

                snackbar.show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onResponse: " + error);

            }
        });

        stringRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(stringRequest, "Group request");


    }

    public void onAddGroupClick(View view) {

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    private void addGroupApi(final Client client) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/addgroup.php?name=" + clientArrayList.get(spinner.getSelectedItemPosition()).getName()
                + "&groupname=" + client.getName()
                + "&email=" + client.getEmail()
                + "&mobile=" + client.getMobileNumber()
                + "&userid=" + client.getUserID();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                Snackbar snackbar = Snackbar
                        .make(floatingActionButton, "Group Added succesfully", Snackbar.LENGTH_LONG);

                snackbar.show();

                fetchGroupList();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onResponse: " + error);

            }
        });

        stringRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(stringRequest, "Group request");


    }




    public void fetchGroupDetails(String groupId) {

        String url = groupDetailUrl + "0";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        clientArrayList = new JsonParser().parseGroupDetails(response);


                        ArrayList<String> groupStringArrayList = new ArrayList<>();
                        for (Client client1 : clientArrayList) {
                            groupStringArrayList.add(client1.getName());
                        }

                        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(ManageGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, groupStringArrayList);

                        spinner.setAdapter(groupAdapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                                groupNameEditText.setText(clientArrayList.get(spinner.getSelectedItemPosition()).getName());
                                emailIdEditText.setText(clientArrayList.get(spinner.getSelectedItemPosition()).getEmail());
                                mobileNumberEditText.setText(clientArrayList.get(spinner.getSelectedItemPosition()).getMobileNumber());
                                userIdEditText.setText(clientArrayList.get(spinner.getSelectedItemPosition()).getUserID());


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

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


    public void onNextClick(View view) {

        Client client = new Client();
        client.setName(groupNameEditText.getText().toString());
        client.setEmail(emailIdEditText.getText().toString());
        client.setMobileNumber(mobileNumberEditText.getText().toString());
        client.setUserID(userIdEditText.getText().toString());

        addGroupApi(client);


    }


}
