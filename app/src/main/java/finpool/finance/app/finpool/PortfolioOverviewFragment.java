package finpool.finance.app.finpool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;

import java.util.ArrayList;

import utils.JsonParser;
import utils.MFTransactionAdapter;
import utils.PortfolioData;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PortfolioOverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PortfolioOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioOverviewFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    PortfolioData portfolioData;

    TextView aumDateTextView, activeClientTextView, sipTextView, newSipTextView, totalBalanceTextView, totalInvestedTextView,
            equityBalanceTextView, equityInvestedTextView, balanceBalanceTextView, balanceInvestedTextView, debtBalanceTextView,
            debtInvestedTextView, cashBalanceTextView, cashInvestedTextView, otherBalanceTextView, otherInvestedTextView;
    private PieChart pieChart;


    public PortfolioOverviewFragment() {
    }


    public static PortfolioOverviewFragment newInstance(String param1, String param2) {
        PortfolioOverviewFragment fragment = new PortfolioOverviewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fetchPortfolioOverView();


    }

    private void fetchPortfolioOverView() {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/abtobusmanytwomobile.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        portfolioData = new JsonParser().parsePortFolioData(response);

                        Log.d(TAG, "onResponse: " + portfolioData);

                        if (aumDateTextView != null) {
                            refreshUI();
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

        View view = inflater.inflate(R.layout.fragment_portfolio_overview, container, false);

        aumDateTextView = view.findViewById(R.id.portfolioFragment_aumDate_textView);
        activeClientTextView = view.findViewById(R.id.portfolioFragment_activeClient_textView);
        sipTextView = view.findViewById(R.id.portfolioFragment_sip_textView);
        newSipTextView = view.findViewById(R.id.portfolioFragment_newSip_textView);
        totalBalanceTextView = view.findViewById(R.id.portfolioFragment_totalBalance_textView);
        totalInvestedTextView = view.findViewById(R.id.portfolioFragment_totalInvested_textView);
        equityBalanceTextView = view.findViewById(R.id.portfolioFragment_equityBalance_textView);
        equityInvestedTextView = view.findViewById(R.id.portfolioFragment_equityInvested_textView);
        balanceBalanceTextView = view.findViewById(R.id.portfolioFragment_balanceBalance_textView);
        balanceInvestedTextView = view.findViewById(R.id.portfolioFragment_balanceInvested_textView);
        debtBalanceTextView = view.findViewById(R.id.portfolioFragment_debtBalance_textView);
        debtInvestedTextView = view.findViewById(R.id.portfolioFragment_debtInvested_textView);
        cashBalanceTextView = view.findViewById(R.id.portfolioFragment_cashBalance_textView);
        cashInvestedTextView = view.findViewById(R.id.portfolioFragment_cashInvested_textView);
        otherBalanceTextView = view.findViewById(R.id.portfolioFragment_otherBalance_textView);
        otherInvestedTextView = view.findViewById(R.id.portfolioFragment_otherInvested_textView);

        pieChart = view.findViewById(R.id.chart);
        initializeChart();

        refreshUI();

        return view;
    }

    private void initializeChart() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(4f, 0));
        entries.add(new PieEntry(8f, 1));
        entries.add(new PieEntry(6f, 2));
        entries.add(new PieEntry(12f, 3));
        entries.add(new PieEntry(18f, 4));
        entries.add(new PieEntry(9f, 5));

        PieDataSet pieDataSet = new PieDataSet(entries,"Reports analysis");

        ArrayList<String> label = new ArrayList<>();
        label.add("Rp 1");
        label.add("Rp 2");
        label.add("Rp 3");
        label.add("Rp 4");
        label.add("Rp 5");
        label.add("Rp 6");


        PieData data = new PieData( pieDataSet);

        pieChart.setData(data);
    }

    private void refreshUI() {

        try {
            aumDateTextView.setText(portfolioData.getDate());
            activeClientTextView.setText(portfolioData.getClient());
            sipTextView.setText(portfolioData.getSip());
            newSipTextView.setText(portfolioData.getReverse());
            totalBalanceTextView.setText(portfolioData.getValTotal());
            totalInvestedTextView.setText(portfolioData.getTotal());
            equityBalanceTextView.setText(portfolioData.getValEquity());
            equityInvestedTextView.setText(portfolioData.getEquity());
            balanceBalanceTextView.setText(portfolioData.getValBalance());
            balanceInvestedTextView.setText(portfolioData.getBalance());
            debtBalanceTextView.setText(portfolioData.getValDebt());
            debtInvestedTextView.setText(portfolioData.getDebt());
            cashBalanceTextView.setText(portfolioData.getValCash());
            cashInvestedTextView.setText(portfolioData.getCash());
            otherBalanceTextView.setText(portfolioData.getValOthers());
            otherInvestedTextView.setText(portfolioData.getBalance());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
