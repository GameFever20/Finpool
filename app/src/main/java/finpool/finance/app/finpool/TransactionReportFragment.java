package finpool.finance.app.finpool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

import utils.JsonParser;
import utils.TransactionReport;
import utils.TransactionReportAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionReportFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionReportFragment extends Fragment {

    private int transType;

    String url;

    RecyclerView recyclerView;
    ArrayList<TransactionReport> transactionReportArrayList = new ArrayList<>();
    TransactionReportAdapter transactionReportAdapter;

    public TransactionReportFragment() {
    }


    public static TransactionReportFragment newInstance(int transType) {
        TransactionReportFragment fragment = new TransactionReportFragment();
        Bundle args = new Bundle();
        args.putInt("transType", transType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            transType = getArguments().getInt("transType");
        }

        if (transType == 1) {

            url= "http://choureywealthcreation.com/admin/sdevloop/swealth/app/mobiletranred.php?client=&id=";
        } else {

        }

        transactionReportAdapter=new TransactionReportAdapter(transactionReportArrayList,getContext());

        fetchTransactionList();

    }

    public void fetchTransactionList() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        transactionReportArrayList = new JsonParser().parseTransactionReportList(response);

                        Log.d(TAG, "onResponse: " + transactionReportArrayList);

                        transactionReportAdapter = new TransactionReportAdapter(transactionReportArrayList, getContext());


                        if (recyclerView != null) {
                            recyclerView.setAdapter(transactionReportAdapter);
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction_report, container, false);

        recyclerView = view.findViewById(R.id.transactionFragment_recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView.setAdapter(transactionReportAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
