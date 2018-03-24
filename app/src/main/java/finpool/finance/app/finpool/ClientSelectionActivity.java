package finpool.finance.app.finpool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import utils.ClientAdapter;

public class ClientSelectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> clientArrayList = new ArrayList<>();

    ArrayList<String> groupArrayList = new ArrayList<>();
    private Spinner spinner;


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

        ClientAdapter clientAdapter = new ClientAdapter(clientArrayList, this);

        recyclerView.setAdapter(clientAdapter);




        clientArrayList.add("Priyank Choudhary");
        clientArrayList.add("Ayush Choudhary");
        clientArrayList.add("Vaishali Malviya");
        clientArrayList.add("Pratap");
        clientArrayList.add("Rahul jain");
        clientArrayList.add("Abaas");
        clientArrayList.add("lalalal");
        clientArrayList.add("Mint");
        clientArrayList.add("Priyank Choudhary");
        clientArrayList.add("Priyank Choudhary");
        clientArrayList.add("Priyank Choudhary");
        clientArrayList.add("Priyank Choudhary");
        clientArrayList.add("Priyank Choudhary");


        clientAdapter.notifyDataSetChanged();


        spinner = (Spinner) findViewById(R.id.clientActivity_spinner);
        ArrayAdapter<String> adapter;

        groupArrayList.add("All");

        groupArrayList.add("Item 1");
        groupArrayList.add("Item 2");
        groupArrayList.add("Item 3");
        groupArrayList.add("Item 4");
        groupArrayList.add("Item 5");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, groupArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setSelection(0);


    }

}
