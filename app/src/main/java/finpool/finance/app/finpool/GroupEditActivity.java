package finpool.finance.app.finpool;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Spinner;

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
import utils.JsonParser;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class GroupEditActivity extends AppCompatActivity {


    private Spinner spinner;


    private RecyclerView recyclerView;
    private ClientAdapter clientAdapter;
    private ArrayList<Client> clientArrayList =  new ArrayList<>();

    private String groupDetailUrl = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/ManageGroupdetails.php?id=";

    ArrayList<String> clientStringArrayList= new ArrayList<>();

    Client groupSelected;
    private ArrayList<Client> groupMemberArrayList;


    String addMemberURL= "http://choureywealthcreation.com/admin/sdevloop/swealth/app/addmember.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groupSelected =(Client) getIntent().getSerializableExtra("groupSelected");

        spinner = findViewById(R.id.clientSelection_group_spinner);

        recyclerView = findViewById(R.id.manageGroupActivity_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        clientAdapter = new ClientAdapter(clientArrayList, this);

        recyclerView.setAdapter(clientAdapter);

        fetchGroupDetails(groupSelected.getId());


        fetchGroupDetails();

    }

    public void fetchGroupDetails(String groupId) {

        String url = groupDetailUrl + groupId;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        groupMemberArrayList = new JsonParser().parseGroupDetails(response);


                        clientAdapter = new ClientAdapter(groupMemberArrayList, GroupEditActivity.this);

                        recyclerView.setAdapter(clientAdapter);

                        clientAdapter.setClickListener(new ClientAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View view) {

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

    public void fetchGroupDetails() {

        String url = groupDetailUrl + "0";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        clientArrayList = new JsonParser().parseGroupDetails(response);




                         clientStringArrayList = new ArrayList<>();
                        for (Client client1 : clientArrayList) {
                            clientStringArrayList.add(client1.getName());
                        }







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

    public void showMemberList(){

        AlertDialog.Builder builder = new AlertDialog.Builder(GroupEditActivity.this);
        builder.setTitle("Select member to add");


              builder.setItems(clientStringArrayList.toArray(new CharSequence[clientStringArrayList.size()]), new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {

                      addMemberApi(clientArrayList.get(i));

                  }
              });



         builder.create();

         builder.show();

    }

    private void addMemberApi(final Client client) {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/addmember.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onResponse: "+error);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", client.getName());
                params.put("id", groupSelected.getId());

                return params;
            }

        };

        stringRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(stringRequest, "Group request");


    }

    public void onAddMemberClick(View view) {
        showMemberList();
    }

    private void showDeleteDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete the group "+groupMemberArrayList.get(position).getName());

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteGroupApi(groupMemberArrayList.get(position));

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

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/memberdelete.php?name='"+client.getName()+"'&id="+groupSelected.getId();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onResponse: "+error);

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", client.getName());
                params.put("id", groupSelected.getId());

                return params;
            }

        };

        stringRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(stringRequest, "Group request");


    }


}
